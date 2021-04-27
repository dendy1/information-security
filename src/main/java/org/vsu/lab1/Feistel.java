package org.vsu.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Feistel {

    private static final Logger logger = LoggerFactory.getLogger(Feistel.class);

    public static void main(String[] args) {
        int rounds = 12;
        long secretKey = 0x96EA704CFB1CF672L;

        long message = 0x123456789ABCDEF0L;
        logger.info("ORIGINAL  MESSAGE: {}", Long.toHexString(message));

        long encryptedMessage = compute(message, rounds, secretKey, false);
        logger.info("ENCRYPTED MESSAGE: {}", Long.toHexString(encryptedMessage));

        long decryptedMessage = compute(encryptedMessage, rounds, secretKey, true);
        logger.info("DECRYPTED MESSAGE: {}", Long.toHexString(decryptedMessage));
    }

    public static long compute(long message, int rounds, long secretKey, boolean reverse) {
        int[] keys = generateRoundKeys(rounds, secretKey);

        int left = (int)(message >>> 32);
        int right = (int)(message);

        int round = reverse ? rounds - 1 : 0;

        for (int i = 0; i < rounds; i++) {

            logger.debug("ROUND {} IN. LEFT: {}. RIGHT: {}",
                    round + 1,
                    Integer.toHexString(left),
                    Integer.toHexString(right)
            );

            if (i < rounds - 1) {
                int temp = left;
                left = right ^ f(left, keys[round]);
                right = temp;
            }
            else {
                right = right ^ f(left, keys[round]);
            }

            logger.debug("ROUND {} OUT. LEFT: {}. RIGHT: {}",
                    round + 1,
                    Integer.toHexString(left),
                    Integer.toHexString(right)
            );

            round += reverse ? -1 : 1;
        }

        return (Integer.toUnsignedLong(left) << 32) | Integer.toUnsignedLong(right);
    }

    private static int[] generateRoundKeys(int rounds, long secretKey) {
        int[] keys = new int[rounds];
        for (int i = 0; i < rounds; i++) {
            keys[i] = (int)rightShift64(secretKey, i * 8);
        }
        return keys;
    }

    private static int f(int left, int key) {
        return leftShift32(left, 9) ^ (~(rightShift32(key, 11) ^ left));
    }

    private static int rightShift32(int x, int t) {
        return ((x >>> t) | (x << (32 - t)));
    }

    private static long rightShift64(long x, int t) {
        return ((x >>> t) | (x << (64 - t)));
    }

    private static int leftShift32(int x, int t) {
        return ((x << t) | (x >>> (32 - t)));
    }

    private static long leftShift64(long x, int t) {
        return ((x << t) | (x >>> (64 - t)));
    }
}
