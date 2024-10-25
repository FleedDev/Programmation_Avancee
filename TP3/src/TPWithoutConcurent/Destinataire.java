package TPWithoutConcurent;

import java.util.ArrayList;

class Destinataire implements Runnable {
    private BAL chBAL;
    private ArrayList<String> lettres;

    public Destinataire(BAL chBAL, ArrayList<String> lettres) {
        this.chBAL = chBAL;
        this.lettres = lettres;
    }

    public void run() {
        chBAL.retirer(lettres);
    }
}
