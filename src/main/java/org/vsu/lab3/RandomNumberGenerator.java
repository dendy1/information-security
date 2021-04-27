package org.vsu.lab3;

import org.apache.commons.math3.primes.Primes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RandomNumberGenerator {

    private static Logger logger = LoggerFactory.getLogger(RandomNumberGenerator.class);

    public static void main(String[] args) {
        double beta = 0.95, delta = 0.1;
        logger.info("Оптимальная длина случайной последовательности для достоверного эксперимента: {}", Tests.optimalSize(beta, delta));

        lcg();
    }

    private static void lcg() {
        logger.info("\n========= Линейный конгруэнтный генератор =========");
        List<Float> lcgSequence = LCG.generateSequence(147541, 21674449, 63132869);
        logger.info("Size: {}", lcgSequence.size());
        logger.info("Sequence: {}", lcgSequence);
        Tests.Periods lcgPeriods = Tests.periods(lcgSequence, LCG.lastRepeatingElement);
        logger.info("Periodicity Interval: {}. Aperiodicity Interval: {}.", lcgPeriods.getPeriodicityInterval(), lcgPeriods.getAperiodicityInterval());

        logger.info("\n========= Tests =========");
        List<TestData> testData = new ArrayList<>();
        int startPrime = 50000;
        int step = 500;
        for (int i = 0; i < 30; i++) {
            logger.info("\nTest: {}", i);
            int U = Primes.nextPrime(startPrime + step);
            int M = Primes.nextPrime(U + step);
            int p = Primes.nextPrime(M + step);

            List<Float> lcgSequenceTest = LCG.generateSequence(U, M, p);
            logger.info("U: {}, M: {}, p: {}", U, M, p);
            logger.info("Size: {}", lcgSequenceTest.size());

            Tests.Periods periods = Tests.periods(lcgSequenceTest, LCG.lastRepeatingElement);
            double starrySky = Tests.starrySky(lcgSequenceTest);
            float xi2 = Tests.uniformityOfDistribuion(lcgSequenceTest, 2);

            testData.add(new TestData(U, M, p, lcgSequenceTest.size(), periods, starrySky, xi2));
            startPrime = p;
        }

        List<TestData> filtered = testData
                .stream()
                .filter(a -> a.getSequenceSize() > 10000)
                .sorted(Comparator.comparing(TestData::getSequenceSize))
                .collect(Collectors.toList());

        logger.info("\nSORTED TEST DATA");
        for (TestData data: filtered) {
            logger.info("U: {} , M: {}, p: {}, size: {}, periodicity: {}, starrySky: {}, xi2: {}",
                    data.getU(), data.getM(), data.getP(), data.getSequenceSize(), data.getPeriodicity(),
                    data.getStarrySkyCriteria(), data.getXi2());
        }


        testData.clear();
        filtered.clear();

        startPrime = 10000;
        step = 5000;
        for (int i = 0; i < 50; i++) {
            logger.info("\nTest: {}", i);
            int U = Primes.nextPrime(startPrime + step);
            int M = 21674449;
            int p = 63132869;

            List<Float> lcgSequenceTest = LCG.generateSequence(U, M, p);
            logger.info("U: {}, M: {}, p: {}", U, M, p);
            logger.info("Size: {}", lcgSequenceTest.size());

            Tests.Periods periods = Tests.periods(lcgSequenceTest, LCG.lastRepeatingElement);
            double starrySky = Tests.starrySky(lcgSequenceTest);
            float xi2 = Tests.uniformityOfDistribuion(lcgSequenceTest, 2);

            testData.add(new TestData(U, M, p, lcgSequenceTest.size(), periods, starrySky, xi2));
            startPrime = U;
        }

        filtered = testData
                .stream()
                .filter(a -> a.getSequenceSize() > 10000)
                .sorted(Comparator.comparing(TestData::getSequenceSize))
                .collect(Collectors.toList());

        logger.info("\nSORTED TEST DATA");
        for (TestData data: filtered) {
            logger.info("U: {} , M: {}, p: {}, size: {}, periodicity: {}, starrySky: {}, xi2: {}",
                    data.getU(), data.getM(), data.getP(), data.getSequenceSize(), data.getPeriodicity(),
                    data.getStarrySkyCriteria(), data.getXi2());
        }

    }

    private static void neumann() {
        logger.info("\n========= Генератор Неймана =========");
        List<Float> neumannSequence = NeumannGenerator.generateSequence(0.51111111f, 8, 5);
        logger.info("Size: {}", neumannSequence.size());
        logger.info("Sequence: {}", neumannSequence);

        logger.info("\n========= Tests =========");
        Tests.Periods periods = Tests.periods(neumannSequence, NeumannGenerator.lastRepeatingElement);
        float xi2 = Tests.uniformityOfDistribuion(neumannSequence, 2);
        double starrySky = Tests.starrySky(neumannSequence);

        logger.info("Periodicity Interval: {}. Aperiodicity Interval: {}.", periods.getPeriodicityInterval(), periods.getAperiodicityInterval());
        logger.info("StarrySky Criteria: {}", starrySky);
        logger.info("xi2 Experimental: {}", xi2);
    }

    private static class TestData {
        private Integer U;
        private Integer M;
        private Integer p;
        private Integer sequenceSize;
        private Tests.Periods periods;
        private double starrySkyCriteria;
        private float xi2;

        public TestData(Integer u, Integer m, Integer p, Integer sequenceSize, Tests.Periods periods, double starrySkyCriteria, float xi2) {
            U = u;
            M = m;
            this.p = p;
            this.sequenceSize = sequenceSize;
            this.periods = periods;
            this.starrySkyCriteria = starrySkyCriteria;
            this.xi2 = xi2;
        }

        public Integer getU() {
            return U;
        }

        public void setU(Integer u) {
            U = u;
        }

        public Integer getM() {
            return M;
        }

        public void setM(Integer m) {
            M = m;
        }

        public Integer getP() {
            return p;
        }

        public void setP(Integer p) {
            this.p = p;
        }

        public Integer getSequenceSize() {
            return sequenceSize;
        }

        public void setSequenceSize(Integer sequenceSize) {
            this.sequenceSize = sequenceSize;
        }

        public Tests.Periods getPeriods() {
            return periods;
        }

        public void setPeriods(Tests.Periods periods) {
            this.periods = periods;
        }

        public double getStarrySkyCriteria() {
            return starrySkyCriteria;
        }

        public void setStarrySkyCriteria(double starrySkyCriteria) {
            this.starrySkyCriteria = starrySkyCriteria;
        }

        public float getXi2() {
            return xi2;
        }

        public void setXi2(float xi2) {
            this.xi2 = xi2;
        }

        public Integer getAperiodicity() {
            return periods.getAperiodicityInterval();
        }

        public Integer getPeriodicity() {
            return periods.getPeriodicityInterval();
        }
    }
}
