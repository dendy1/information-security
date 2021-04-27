package org.vsu.lab3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LCG {

    private static Logger logger = LoggerFactory.getLogger(LCG.class);

    public static Float lastRepeatingElement;

    public static List<Float> generateSequence(int U, int M, int p) {
        List<Float> sequence = new ArrayList<>();

        float firstNumber = (float)U / p;
        sequence.add(firstNumber);

        long lastU = ((long) U * M) % p;
        float lastGenerated = (float) lastU / p;

        while (!sequence.contains(lastGenerated)) {
            sequence.add(lastGenerated);
            lastU = (lastU * M) % p;
            lastGenerated = (float) lastU / p;
        }

        lastRepeatingElement = lastGenerated;

        return sequence;
    }
}
