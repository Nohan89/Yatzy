package models;

import java.util.Random;

public class RaffleCup {
    private Die[] dice = new Die[5];
    private Random random = new Random();

    public RaffleCup() {
        for (int i = 0; i < dice.length; i++) {
            int value = random.nextInt(6) + 1;
            dice[i] = new Die();
        }
    }

    public void throwDice() {
        for (int i = 0; i < dice.length; i++) {
            int newValue = random.nextInt(6) + 1;
            dice[i] = new Die (newValue);
        }
    }

    public Die[] getDice() {
        return dice;
    }
}
