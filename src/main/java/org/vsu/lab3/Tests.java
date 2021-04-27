package org.vsu.lab3;

import org.vsu.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tests {

    public static int optimalSize(double beta, double delta) {
        double F = 1 / Utils.normalCDF(0.5 * (1 - beta));
        double F_d = 0.5 * F / delta;
        return !Double.isInfinite(F_d) && !Double.isNaN(F_d) ? (int)Math.ceil(F_d * F_d) : 0;
    }

    public static double starrySky(List<Float> sequence) {
        int counter = 0;
        for (int i = 0; i < sequence.size() / 2; i++) {
            float seq1 = sequence.get(2 * i);
            float seq2 = sequence.get(2 * i + 1);
            if (Math.sqrt(seq1 * seq1 + seq2 * seq2) < 1) {
                counter++;
            }
        }

        double piExp = 8.0 * counter / sequence.size();
        return Math.abs(piExp - Math.PI);
    }

    public static float uniformityOfDistribuion(List<Float> sequence, int sections) {
        int dims = 3;
        int N = sequence.size() / dims;
        int M = (int)Math.pow(sections, dims);

        // 4-d гистограмма в одномерной развёртке
        List<Integer> ms = new ArrayList<>();

        for (int i = 0; i < M; i++)
            ms.add(0);

        // Заполняем гистограмму
        for (int i = 0; i < dims * N; i += dims) {
            int lineIndex3 = lineIndex3(sequence.get(i), sequence.get(i + 1), sequence.get(i + 2), sections);
            ms.set(lineIndex3, ms.get(lineIndex3) + 1);
        }

        // Вычисляем хи-квадрат экспериментальное
        float xi2 = 0;
        float N_M = (float)N / M;

        //System.out.println("v " + (M - 2));
        //System.out.println("bars");
        //System.out.println(ms.stream().map(Object::toString).collect(Collectors.joining(", ")));
        for (int j = 0; j < ms.size(); j++)
        {
            xi2 += (ms.get(j) - N_M) * (ms.get(j) - N_M);
        }

        return xi2 / N_M;
    }

    private static int lineIndex3(float x, float y, float z, int sections)
    {
        int i = (int)(x * sections);
        int j = (int)(y * sections);
        int k = (int)(z * sections);

        int idPart = i * (sections * sections) + j * sections + k;

        return (idPart >= sections * sections * sections) ?
                sections * sections * sections - 1 :
                idPart;
    }

    private static int lineIndex4(float x, float y, float z, float n, int sections)
    {
        int i = (int)(x * sections);
        int j = (int)(y * sections);
        int k = (int)(z * sections);
        int l = (int)(n * sections);

        int idPart = i * (sections * sections * sections) + j * (sections * sections) + k * sections + l;

        return (idPart >= sections * sections * sections * sections) ?
                sections * sections * sections * sections - 1 :
                idPart;
    }

    public static Periods periods(List<Float> sequence, Float lastRepeatedElement) {
        int aperiodicityInterval;
        int periodicityInterval;
        Map<Float, Integer> sequenceMap = new HashMap<>();

        for (int i = 0; i < sequence.size(); i++) {
            sequenceMap.put(sequence.get(i), i);
        }

        aperiodicityInterval = sequenceMap.size();
        periodicityInterval = aperiodicityInterval - sequenceMap.get(lastRepeatedElement);

        return new Periods(aperiodicityInterval, periodicityInterval);
    }

    public static class Periods {
        private Integer aperiodicityInterval;
        private Integer periodicityInterval;

        public Periods(Integer aperiodicityInterval, Integer periodicityInterval) {
            this.aperiodicityInterval = aperiodicityInterval;
            this.periodicityInterval = periodicityInterval;
        }

        public Integer getAperiodicityInterval() {
            return aperiodicityInterval;
        }

        public void setAperiodicityInterval(Integer aperiodicityInterval) {
            this.aperiodicityInterval = aperiodicityInterval;
        }

        public Integer getPeriodicityInterval() {
            return periodicityInterval;
        }

        public void setPeriodicityInterval(Integer periodicityInterval) {
            this.periodicityInterval = periodicityInterval;
        }
    }
}
