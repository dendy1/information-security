package org.vsu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Utils {

    public static String join(long[] array, String delimeter) {
        StringJoiner sj = new StringJoiner(delimeter);
        LongStream.of(array).forEach(x -> sj.add(Long.toHexString(x)));
        return sj.toString();
    }

    public static String join(int[] array, String delimeter) {
        StringJoiner sj = new StringJoiner(delimeter);
        IntStream.of(array).forEach(x -> sj.add(Integer.toHexString(x)));
        return sj.toString();
    }

    public static double erf(double x)
    {
        double a1 = 0.254829592;
        double a2 = -0.284496736;
        double a3 = 1.421413741;
        double a4 = -1.453152027;
        double a5 = 1.061405429;
        double p = 0.3275911;


        int sign = 1;
        if (x < 0)
            sign = -1;

        x = Math.abs(x);

        double t = 1.0 / (1.0 + p * x);
        double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);

        return sign * y;
    }

    public static double erfC(double x)
    {
        return 1 - erf(x);
    }

    public static double normalCDF(double value)
    {
        return 0.5 * erfC(-value * Math.sqrt(2));
    }

    public static BufferedImage makeImageCopy(BufferedImage imageToCopy) {
        BufferedImage result = new BufferedImage(imageToCopy.getWidth(), imageToCopy.getHeight(), imageToCopy.getType());
        Graphics g = result.getGraphics();
        g.drawImage(imageToCopy, 0, 0, null);
        return result;
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = Utils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    public static File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = Utils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }
}
