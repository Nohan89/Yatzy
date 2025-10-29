package models;


import java.util.HashMap;
import java.util.Map;
/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {

    /**
     *
     * @param dice
     */

    private Die[] dice;

    public YatzyResultCalculator(Die[] dice) {
        this.dice = dice;
    }

    private Map<Integer, Integer> countValues() {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Die die : dice) {
            int eyes = die.getEyes();
            counts.put(eyes, counts.getOrDefault(eyes, 0) + 1);
        }
        return counts;
    }

    private int sumAllDice() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.getEyes();
        }
        return sum;
    }

    /**
     * Calculates the score for Yatzy uppersection
     *
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */
    public int upperSectionScore(int eyes) {
        int sum = 0;
        for (Die die : dice) {
            if (die.getEyes() == eyes) {
                sum += eyes;
            }
        }
        return sum;
    }

    public int onePairScore() {
        Map<Integer, Integer> counts = new= countValues();
        int highestPair = 0;
        for (int value = 6; value >= 1; value--) {
            if (counts.getOrDefault(value, 0) >= 2) {
                highestPair = value;
                break;
            }
        }
        return highestPair;
    }

    public int twoPairScore() {
        Map<Integer, Integer> counts = countValues();
        int pairsFound = 0;
        int score = 0;
        for (int value = 6; value >= 1; value--) {
            if (counts.getOrDefault(value, 0) >= 2) {
                pairsFound++;
                score += value * 2;
            }
        }
        if (pairsFound >= 2) {
            return score;
        }
        return 0;
    }

    public int threeOfAKindScore() {
        Map<Integer, Integer> counts = countValues();
        for (int value = 6; value >= 1; value--) {
            if (counts.getOrDefault(value, 0) >= 3) {
                return value * 3;
            }
        }
        return 0;
    }

    public int fourOfAKindScore() {
        Map<Integer, Integer> counts = countValues();
        for (int value = 6; value >= 1; value--) {
            if (counts.getOrDefault(value, 0) >= 4) {
                return value * 4;
            }
        }
        return 0;
    }

    public int smallStraightScore() {
        boolean[] seen = new boolean[7];
        for (Die die : dice) {
            seen[die.getEyes()] = true;
        }
        for (int i = 1; i <= 5; i++) {
            if (!seen[i]) {
                return 0;
            }
        }
        return 15;
    }

    public int largeStraightScore() {
        boolean[] seen = new boolean[7];
        for (Die die : dice) {
            seen[die.getEyes()] = true;
        }
        for (int i = 2; i <= 6; i++) {
            if (!seen[i]) {
                return 0;
            }
            return 20;
        }

        public int fullHouseScore () {
            Map<Integer, Integer> counts = countValues();
            boolean hasThree = false;
            boolean hasTwo = false;

            for (int count : counts.values()) {
                if (count == 3) {
                    hasThree = true;
                } else if (count == 2) {
                    hasTwo = true;
                }
            }

            if (hasThree && hasTwo && counts.size() == 2) {
                return sumAllDice();
            }
            return 0;
        }

        public int chanceScore () {
            return sumAllDice();
        }

        public int yatzyScore () {
            Map<Integer, Integer> counts = countValues();
            if (counts.size() == 1) {
                return 50;
            }
            return 0;
        }
    }
}
