package org.vsu.lab4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RSA {

    private static final Logger logger = LoggerFactory.getLogger(RSA.class);

    public static void main(String[] args) {

        // Input values
        BigInteger e = new BigInteger("12971");
        BigInteger n = new BigInteger("471145798109971");

        // Prime numbers of n (2432251×193707721 (2 distinct prime factors))
        BigInteger p = new BigInteger("2432251");
        BigInteger q = new BigInteger("193707721");

        String encodedMessage = "334795697842808423513191687578172510862135388341954737406265";
        String decodedMessage = RSA(e, n, p, q, encodedMessage);

        logger.info("ENCODED MESSAGE: {}", encodedMessage);
        logger.info("DECODED MESSAGE: {}", decodedMessage);
    }

    private static String RSA(BigInteger e, BigInteger n, BigInteger p, BigInteger q, String codedMessage) {

        // (p - 1) * (q - 1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Вычисление обратного элемента для e по модулю phi (расширенный алгоритм Евклида)
        BigInteger d = modInverse(e, phi);

        StringBuilder decodedMessage = new StringBuilder();
        for (String blockStr : stringChunkonizer(codedMessage, 15))
        {
            BigInteger blockInt = new BigInteger(blockStr);
            BigInteger decodedBlock = blockInt.modPow(d, n);
            decodedMessage.append(decodedBlock.toString());
        }

        return parseToASCII(decodedMessage.toString());
    }

    // Вычисление обратного элемента для a по модулю b (расширенный алгоритм Евклида)
    private static BigInteger modInverse(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ONE))
            return BigInteger.ZERO;

        BigInteger b0 = b;

        BigInteger x = BigInteger.ONE;
        BigInteger y = BigInteger.ZERO;

        while (a.compareTo(BigInteger.ONE) > 0) {
            // a / b
            BigInteger q = a.divide(b);

            // b = a / b
            // a = b
            BigInteger tmp = a;
            a = b;
            b = tmp.mod(b);

            // y = x - (q * y)
            // x = y
            tmp = x;
            x = y;
            y = tmp.subtract(q.multiply(y));
        }

        return x.compareTo(BigInteger.ZERO) < 0 ? x.add(b0) : x;
    }

    private static String parseToASCII(String str)
    {
        byte[] bytes = new byte[str.length() / 2];

        for (int i = 0; i < str.length() / 2; i++)
        {
            String num = str.substring(2 * i, 2 * i + 2);
            bytes[i] = num.isEmpty() ? 0 : Byte.parseByte(num);
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

    static List<String> stringChunkonizer(String str, int maxChunkSize) {
        return IntStream
                .iterate(0, i -> i + maxChunkSize)
                .limit((int) Math.ceil(str.length() / (float)maxChunkSize))
                .mapToObj(i -> str.substring(i, Math.min(i + maxChunkSize, str.length())))
                .collect(Collectors.toList());
    }
}
