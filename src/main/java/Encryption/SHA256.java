package Encryption;

import java.security.MessageDigest;

/**
 * This class is used to hash (SHA256) String.
 * The hash is used in passwords.
 */

public class SHA256 {
    public static String getHashedString(String input) {
        String hashedString;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(input.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            hashedString = hexString.toString(); // Converting hexString to String because it's a byte array
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return hashedString;
    }
}
