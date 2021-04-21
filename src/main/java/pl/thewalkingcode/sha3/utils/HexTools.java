package pl.thewalkingcode.sha3.utils;

import java.security.InvalidParameterException;

public class HexTools {

    public static String convertToHex(final byte[] bytes) {
        if (bytes == null) {
            throw new InvalidParameterException("Bytes array cannot be null");
        }

        StringBuilder hex = new StringBuilder();
        for (byte temp : bytes) {
            append(temp, hex);
        }

        return hex.toString();
    }

    private static void append(final byte temp, final StringBuilder hex) {
        byte leastSignificantHalf = (byte) (temp & 0x0f);
        byte mostSignificantHalf = (byte) ((temp >> 4) & 0x0f);

        hex.append(convertDigitToHexValue(mostSignificantHalf));
        hex.append(convertDigitToHexValue(leastSignificantHalf));
    }

    private static char convertDigitToHexValue(byte value) {
        return value < 10 ? (char) ('0' + value) : (char) ('A' + value - 10);
    }
}
