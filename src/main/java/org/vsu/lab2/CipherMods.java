package org.vsu.lab2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vsu.Utils;
import org.vsu.lab1.Feistel;

public class CipherMods {

    private static final Logger logger = LoggerFactory.getLogger(CipherMods.class);

    public static void main(String[] args) {
        int rounds = 12;
        long secretKey = 0x96EA704CFB1CF672L;

        long[] messages = new long[] { 0x123456789ABCDEF0L, 0x123456789ABCDEF0L, 0x1FBA85C953ABCFD0L, 0x1FBA85C953ABCFD0L };
        long initializationVector = 0x18FD47203C7A23BCL;

        logger.info("Electronic Codebook (ECB)");
        logger.info("ORIGINAL MESSAGE : {}", Utils.join(messages, ""));
        long[] encryptedMessages = electronicCodebook(messages, rounds, secretKey, false);
        logger.info("ENCRYPTED MESSAGE: {}", Utils.join(encryptedMessages, ""));
        long[] decryptedMessaged = electronicCodebook(encryptedMessages, rounds, secretKey, true);
        logger.info("DECRYPTED MESSAGE: {}\n", Utils.join(decryptedMessaged, ""));


        logger.info("Cipher Block Chaining (CBC)");
        logger.info("ORIGINAL MESSAGE : {}", Utils.join(messages, ""));
        encryptedMessages = cipherBlockChaining(messages, initializationVector, rounds, secretKey, false);
        logger.info("ENCRYPTED MESSAGE: {}", Utils.join(encryptedMessages, ""));
        decryptedMessaged = cipherBlockChaining(encryptedMessages, initializationVector, rounds, secretKey, true);
        logger.info("DECRYPTED MESSAGE: {}\n", Utils.join(decryptedMessaged, ""));


        logger.info("Output Feedback (OFB)");
        logger.info("ORIGINAL MESSAGE : {}", Utils.join(messages, ""));
        encryptedMessages = outputFeedback(messages, initializationVector, rounds, secretKey, false);
        logger.info("ENCRYPTED MESSAGE: {}", Utils.join(encryptedMessages, ""));
        decryptedMessaged = outputFeedback(encryptedMessages, initializationVector, rounds, secretKey, true);
        logger.info("DECRYPTED MESSAGE: {}\n", Utils.join(decryptedMessaged, ""));


        logger.info("Cipher Feedback (CFB)");
        logger.info("ORIGINAL MESSAGE : {}", Utils.join(messages, ""));
        encryptedMessages = cipherFeedback(messages, initializationVector, rounds, secretKey, false);
        logger.info("ENCRYPTED MESSAGE: {}", Utils.join(encryptedMessages, ""));
        decryptedMessaged = cipherFeedback(encryptedMessages, initializationVector, rounds, secretKey, true);
        logger.info("DECRYPTED MESSAGE: {}\n", Utils.join(decryptedMessaged, ""));
    }

    // Электронная кодовая книга
    private static long[] electronicCodebook(long[] initialMessages, int rounds, long secretKey, boolean reverse) {
        long[] processedMessages = new long[initialMessages.length];

        for (int i = 0; i < initialMessages.length; i++) {
            processedMessages[i] = Feistel.compute(initialMessages[i], rounds, secretKey, reverse);
            logger.debug("ORIGINAL: {}. ENCRYPTED: {}", Long.toHexString(initialMessages[i]), Long.toHexString(processedMessages[i]));
        }

        return processedMessages;
    }

    // Режим сцепления блоков
    private static long[] cipherBlockChaining(long[] initialMessages, long initializationVector, int rounds, long secretKey, boolean reverse) {
        long[] processedMessages = new long[initialMessages.length];
        long lastEncrypted = initializationVector;

        for (int i = 0; i < initialMessages.length; i++) {
            if (reverse) {
                processedMessages[i] = Feistel.compute(initialMessages[i], rounds, secretKey, reverse) ^ lastEncrypted;
                lastEncrypted = initialMessages[i];
                logger.debug("ENCRYPTED: {}. DECRYPTED: {}", Long.toHexString(initialMessages[i]), Long.toHexString(processedMessages[i]));
            } else {
                processedMessages[i] = Feistel.compute(initialMessages[i] ^ lastEncrypted, rounds, secretKey, reverse);
                lastEncrypted = processedMessages[i];
                logger.debug("ORIGINAL: {}. ENCRYPTED: {}", Long.toHexString(initialMessages[i]), Long.toHexString(processedMessages[i]));
            }
        }

        return processedMessages;
    }

    // Режим обратной связи по шифртексту
    private static long[] outputFeedback(long[] initialMessages, long initializationVector, int rounds, long secretKey, boolean reverse) {
        long[] processedMessages = new long[initialMessages.length];
        long lastEncrypted = initializationVector;

        for (int i = 0; i < initialMessages.length; i++) {
            lastEncrypted = Feistel.compute(lastEncrypted, rounds, secretKey, false);
            processedMessages[i] = lastEncrypted ^ initialMessages[i];
            logger.debug("ORIGINAL: {}. PROCESSED: {}", Long.toHexString(initialMessages[i]), Long.toHexString(processedMessages[i]));
        }

        return processedMessages;
    }

    // Режим обратной связи по выходу
    private static long[] cipherFeedback(long[] initialMessages, long initializationVector, int rounds, long secretKey, boolean reverse) {
        long[] processedMessages = new long[initialMessages.length];
        long lastEncrypted = initializationVector;

        for (int i = 0; i < initialMessages.length; i++) {
            processedMessages[i] = Feistel.compute(lastEncrypted, rounds, secretKey, false) ^ initialMessages[i];

            if (reverse) {
                lastEncrypted = initialMessages[i];
            } else {
                lastEncrypted = processedMessages[i];
            }

            logger.debug("ORIGINAL: {}. PROCESSED: {}", Long.toHexString(initialMessages[i]), Long.toHexString(processedMessages[i]));
        }

        return processedMessages;
    }
}
