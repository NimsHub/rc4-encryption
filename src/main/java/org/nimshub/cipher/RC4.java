package org.nimshub.cipher;

import org.nimshub.exceptions.InvalidEncryptionKeyException;
import org.nimshub.utils.Logger;

import java.util.Arrays;
import static org.nimshub.utils.Swapper.*;


/**
 * Implementation of RC4 stream cipher
 *
 * @author Sabaragamuwa S.B.N.M
 * @regNo EG/2018/3443
 */
public class RC4 {
    Logger logger = new Logger();
    private static final int S_ARRAY_LENGTH = 256;
    private static final int KEY_LENGTH = 4;
    /**
     * Key array
     */
    private byte[] key = new byte[S_ARRAY_LENGTH - 1];
    /**
     * S-Array
     */
    private int[] sArray = new int[S_ARRAY_LENGTH];

    public RC4() {
        reset();
    }

    /**
     * Resetting elements
     */
    private void reset() {
        Arrays.fill(key, (byte) 0);
        Arrays.fill(sArray, 0);
    }

    /**
     * Setup key
     *
     * @param key key to be setup
     * @throws InvalidEncryptionKeyException if key length is smaller than 5 or bigger than 255
     */
    private void setKey(String key) throws InvalidEncryptionKeyException {
        if (key.length() != KEY_LENGTH) {
            throw new InvalidEncryptionKeyException("Key length has to be "
                    + KEY_LENGTH);
        }
        this.key = key.getBytes();
    }

    /**
     * Encrypt given message String with given Charset and key
     *
     * @param message message to be encrypted
     * @param key     key
     * @return encrypted message
     * @throws InvalidEncryptionKeyException if key length is smaller than 5 or bigger than 255
     */
    public byte[] encrypt(String message, String key)
            throws InvalidEncryptionKeyException {
        logger.info("encryption in progress");
        reset();
        setKey(key);
        logger.info(String.format("message to be encrypted : %s", message));
        logger.info(String.format("encrypt with key : %s", key));
        byte[] crypt = crypt(message.getBytes());
        reset();
        logger.info(String.format("encrypted message : %s", new String(crypt)));
        return crypt;
    }


    /**
     * Decrypt given byte[] message array with given charset and key
     *
     * @param message message to be decrypted
     * @param key     key
     * @return string in given charset
     * @throws InvalidEncryptionKeyException if key length is not equal to 4
     */
    public String decrypt(byte[] message, String key)
            throws InvalidEncryptionKeyException {
        logger.info("decryption in progress");
        logger.info(String.format("message to be decrypted : %s", new String(message)));
        reset();
        setKey(key);
        logger.info(String.format("decrypt with key : %s", key));
        byte[] msg = crypt(message);
        reset();
        String decryptedMsg = new String(msg);
        logger.info(String.format("decrypted message : %s", decryptedMsg));
        return decryptedMsg;
    }

    /**
     * Initialize S_ARRAY with given key.
     *
     * @return sArray int array
     */
    private int[] initSArray() {
        for (int i = 0; i < S_ARRAY_LENGTH; i++) {
            sArray[i] = i;
        }
        logger.info("Initialized S Array");
        return sArray;
    }

    /**
     * Key scheduling algorithm
     *
     * @param key byte[]
     * @return sArray : int[]
     */
    private int[] keyScheduler(byte[] key) {
        sArray = initSArray();
        int j = 0;
        for (int i = 0; i < S_ARRAY_LENGTH; i++) {
            j = (j + sArray[i] + (key[i % key.length]) & 0xFF) % S_ARRAY_LENGTH;
            swap(i, j, sArray);
        }
        return sArray;
    }

    /**
     * Crypt given byte array
     *
     * @param msg array to be crypt
     * @return crypted byte array
     */
    private byte[] crypt(final byte[] msg) {
        sArray = keyScheduler(key);
        byte[] code = new byte[msg.length];
        int i = 0;
        int j = 0;
        //  * Stream generation using pseudo random
        for (int n = 0; n < msg.length; n++) {
            i = (i + 1) % S_ARRAY_LENGTH;
            j = (j + sArray[i]) % S_ARRAY_LENGTH;
            swap(i, j, sArray);
            int keyStreamElement = sArray[(sArray[i] + sArray[j]) % S_ARRAY_LENGTH];
            code[n] = (byte) (keyStreamElement ^ msg[n]);
        }
        logger.info("key stream generated");
        return code;
    }

}