package org.vsu.lab3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NeumannGenerator {

    private static Logger logger = LoggerFactory.getLogger(NeumannGenerator.class);

    public static Float lastRepeatingElement;

    public static List<Float> generateSequence(Float firstNumber, int maxDigits, int decimalPlace) {
        List<Float> sequence = new ArrayList<>();
        sequence.add(firstNumber);

        Float lastGenerated = generateNext(firstNumber, maxDigits, decimalPlace);

        while (!sequence.contains(lastGenerated)) {
            sequence.add(lastGenerated);
            lastGenerated = generateNext(lastGenerated, maxDigits, decimalPlace);
        }

        lastRepeatingElement = lastGenerated;

        return sequence;
    }

    private static Float generateNext(Float lastGenerated, int maxDigits, int decimalPlace) {
        float squared = lastGenerated * lastGenerated;
        return (int)(squared * Math.pow(10, maxDigits)) * (float)Math.pow(10, -decimalPlace) - (int)(squared * Math.pow(10, maxDigits - decimalPlace));
    }
}
