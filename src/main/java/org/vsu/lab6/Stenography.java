package org.vsu.lab6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vsu.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Stenography {

    private static final Logger logger = LoggerFactory.getLogger(Stenography.class);

    private static final int NUM_OF_REPEATS = 15;
    private static final float ENERGY = 0.15f;

    private static int currentX;
    private static int currentY;

    public static void main(String[] args) {
        String secretMessage = "Hello, World!";
        String inputFilePath = "lenna.png";
        String outputFilePath = "lenna_output.png";

        try {
            logger.info("Original Text: {}", secretMessage);

            // Reading input image file
            File inputFile = Utils.getFileFromResource(inputFilePath);
            BufferedImage inputImage = ImageIO.read(inputFile);

            // Encoding image
            BufferedImage encodedImage = encodeText(inputImage, secretMessage);
            File outputFile = Utils.getFileFromResource(outputFilePath);
            ImageIO.write(encodedImage, "gif", outputFile);

            // Decoding image
            File encodedFile = Utils.getFileFromResource(outputFilePath);
            BufferedImage encImage = ImageIO.read(encodedFile);
            String decodedText = decodeText(encImage);

            logger.info("Decoded Text: {}", decodedText);
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    public static BufferedImage encodeText(BufferedImage image, String secretText) throws StenographyException {
        byte[] message = prepareText(secretText);

        currentX = currentY = 3;
        BufferedImage result = Utils.makeImageCopy(image);

        // Checking if image is big enough for text encoding
        if (message.length * 8 * NUM_OF_REPEATS > (image.getWidth() / 4 - 1) * (image.getHeight() / 4 - 1)) {
            throw new StenographyException("Image to small for this text");
        }

        for (byte b : message) {
            writeByte(result, b);
        }

        return result;
    }

    public static String decodeText(BufferedImage encodedImage) throws StenographyException {

        currentX = currentY = 3;

        /* Getting a length of encoded text */
        byte lenByte0 = readByte(encodedImage);
        byte lenByte1 = readByte(encodedImage);
        byte lenByte2 = readByte(encodedImage);
        byte lenByte3 = readByte(encodedImage);

        /* Converting lenByte into a decimal number */
        int messageLength = ((lenByte0 & 0xff) << 24) |
                ((lenByte1 & 0xff) << 16) |
                ((lenByte2 & 0xff) << 8) |
                (lenByte3 & 0xff);

        if (messageLength <= 0 || messageLength * 8 * NUM_OF_REPEATS > (encodedImage.getWidth() / 4 - 1) * (encodedImage.getHeight() / 4 - 1)) {
            throw new StenographyException("Decoding error.");
        }

        /* Decoding */
        byte[] msgBytes = new byte[messageLength];
        for(int i = 0; i < messageLength; i++) {
            msgBytes[i] = readByte(encodedImage);
        }

        /* Converting byte array to string */
        return new String(msgBytes);
    }

    private static void writeByte(BufferedImage image, byte byteValue) {
        for (int i = 7; i >= 0; i--) {
            int bitValue = (byteValue >>> i) & 1;
            writeBit(image, bitValue);
        }
    }

    private static void writeBit(BufferedImage image, int bit) {
        for (int i = 0; i < NUM_OF_REPEATS; i++) {

            if (currentX + 4 > image.getWidth()) {
                currentX = 3;
                currentY += 4;
            }

            writeIntoPixel(image, currentX, currentY, bit);
            currentX += 4;
        }
    }

    private static void writeIntoPixel(BufferedImage image, int x, int y, int bit) {
        Color pixelColor = new Color(image.getRGB(x, y));
        int red = pixelColor.getRed();
        int green = pixelColor.getGreen();
        int blue = pixelColor.getBlue();

        int pixelBrightness = (int)(0.299 * red + 0.587 * green + 0.114 * blue);

        int modifiedBlue;

        if (bit > 0) {
            modifiedBlue = (int) (blue + Stenography.ENERGY * pixelBrightness);
        } else {
            modifiedBlue = (int) (blue - Stenography.ENERGY * pixelBrightness);
        }

        if (modifiedBlue > 255) {
            modifiedBlue = 255;
        }
        if (modifiedBlue < 0) {
            modifiedBlue = 0;
        }

        Color modifiedPixelColor = new Color(red, green, modifiedBlue);
        image.setRGB(x, y, modifiedPixelColor.getRGB());
    }

    private static byte readByte(BufferedImage image) {
        byte byteVal = 0;

        /* Getting a byte from 8 bits */
        for( int i = 0; i < 8; i++ ) {

            /* Left shift founded bits and add a bit to the right */
            byteVal = (byte)((byteVal << 1) | (readBit(image) & 1));
        }

        return byteVal;
    }

    private static byte readBit(BufferedImage image) {
        /* Probabilistic estimate of an information bit */
        float bitEstimate = 0;

        for (int i = 0; i < NUM_OF_REPEATS; i++ ) {
            if (currentX + 4 > image.getWidth()) {
                currentX = 3;
                currentY += 4;
            }

            bitEstimate += readFromPixel(image, currentX, currentY);
            currentX += 4;
        }

        bitEstimate /= NUM_OF_REPEATS;

        /* if more than half of NUM_OF_REPEATS read bits were 1s, so consider 1 was encoded */
        if (bitEstimate > 0.5) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int readFromPixel(BufferedImage image, int x, int y) {

        /* Summing up all the blue components of surrounding points */
        int estimate = 0;

        for (int i = 1; i <= 3; i++) {
            Color pixel = new Color(image.getRGB(x + i, y) );
            estimate += pixel.getBlue();
        }

        for (int i = 1; i <= 3; i++) {
            Color pixel = new Color( image.getRGB(x - i, y));
            estimate += pixel.getBlue();
        }

        for (int i = 1; i <= 3; i++) {
            Color pixel = new Color(image.getRGB(x, y + i));
            estimate += pixel.getBlue();
        }

        for (int i = 1; i <= 3; i++) {
            Color pixel = new Color(image.getRGB(x, y - i));
            estimate += pixel.getBlue();
        }

        /* Average */
        estimate /= 12;

        Color pixel = new Color(image.getRGB(x, y));
        int blue = pixel.getBlue();

        if (blue > estimate) {
            return 1;
        } else {
            return 0;
        }
    }

    private static byte[] prepareText(String text) {
        /* Converting text to byte array */
        byte[] msgBytes = text.getBytes();

        /* Getting length of a byte array */
        byte[] lenBytes = BitHelper.getByteArrayLength(msgBytes);

        /* Preparing information to insert */
        byte[] message = new byte[msgBytes.length + 4];
        System.arraycopy(lenBytes, 0, message, 0, lenBytes.length);
        System.arraycopy(msgBytes, 0, message, lenBytes.length, msgBytes.length);

        return message;
    }
}
