import javax.management.monitor.Monitor;
import java.util.ArrayList;

public class BAL extends Thread {
    private ArrayList<String>  letter;
    private Boolean available;
    public BAL() {}

    public synchronized void retirer(ArrayList<String> letter) {
        if (!available) {
            letter = null;
        }
        this.letter = getLetter();
    }

    public synchronized void deposer(ArrayList<String> letter) {
        if (letter == null) {
            //Facteur unFacteur = new Facteur();
            //Thread laTache = new Thread(unFacteur);
            setLetter(letter);
            available = true;
        }
    }

    public ArrayList<String> getLetter() {
        return letter;
    }

    public void setLetter(ArrayList<String> letter) {
        this.letter = letter;
    }
}
