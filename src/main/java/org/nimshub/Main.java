package org.nimshub;

import org.nimshub.cipher.RC4;
import org.nimshub.utils.Printer;

import static org.nimshub.utils.Constants.*;

public class Main {
    public static void main(String[] args) {
        testRC4Encryption();
    }

    /**
     * Test method for illustrate the functionality of RC4 encryption
     */
    private static void testRC4Encryption() {

        RC4 cipher = new RC4();

        byte[] encryptedMsg = cipher.encrypt(STUDENT_NAME, REG_NO);
        String decryptedMsg = cipher.decrypt(encryptedMsg, REG_NO);

        Printer.print("\nOriginal message",STUDENT_NAME);
        Printer.print("Encryption Key",REG_NO);
        Printer.print("Encrypted message",new String(encryptedMsg));
        Printer.print("Decrypted message",decryptedMsg);
    }
}