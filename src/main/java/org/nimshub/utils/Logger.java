package org.nimshub.utils;

import java.util.logging.Level;

/**
 * This class act as a helper by logging capabilities to other services
 */
public class Logger {

    public void log(Level level, String message) {
        String formattedMessage = String.format(" %s: %s\n", level.getName(), message);
        System.out.print(formattedMessage);
    }

    public void info(String message) {log(Level.INFO, message);}


}
