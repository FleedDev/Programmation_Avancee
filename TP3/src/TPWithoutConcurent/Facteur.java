package TPWithoutConcurent;

import java.util.ArrayList;

public class Facteur implements Runnable {
    private BAL chBAL;
    private ArrayList<String> letter;

    public Facteur(BAL chBAL) {
        this.chBAL = chBAL;
    }

    public void run(){
        chBAL.deposer(letter);
    }
}
