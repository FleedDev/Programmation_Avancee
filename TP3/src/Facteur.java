import java.lang.reflect.Array;
import java.util.ArrayList;

class Facteur implements Runnable {
    private BAL chBAL;
    private ArrayList<String> lettres;

    public Facteur(BAL chBAL, ArrayList<String> lettres) {
        this.chBAL = chBAL;
        this.lettres = lettres;
    }

    public void run() {
        chBAL.deposer(lettres);
    }
}
