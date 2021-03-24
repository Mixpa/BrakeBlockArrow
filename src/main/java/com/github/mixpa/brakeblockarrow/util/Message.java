package com.github.mixpa.brakeblockarrow.util;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Message {
    public static final String PREFIX = "[BrakeBlockArrow]";
    public static final String DEBUG_PREFIX = "[DebugAAR]";
    public static final Logger LOGGER = LogManager.getLogManager().getLogger("Minecraft");

    public static void debugInfo(String message) {
        LOGGER.info(DEBUG_PREFIX + " " + message);
    }

    public static String message(String message) {
        return PREFIX + message;
    }
}
