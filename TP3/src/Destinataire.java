import java.util.ArrayList;

public class Destinataire implements Runnable {
    private BAL chBAL;
    private ArrayList<String> letter;

    public Destinataire(BAL parBAL) {
        this.chBAL = parBAL;
    }

    public void run() {
        chBAL.retirer(letter);
    }
}
