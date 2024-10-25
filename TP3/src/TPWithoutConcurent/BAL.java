import javax.management.monitor.Monitor;
import java.util.ArrayList;

class BAL {
    private ArrayList<String> lettres;
    private boolean disponible;

    public BAL() {
        lettres = new ArrayList<>();
        disponible = false;
    }

    public synchronized void retirer(ArrayList<String> lettresDestinataire) {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        lettresDestinataire.clear();
        lettresDestinataire.addAll(lettres);
        lettres.clear();
        disponible = false;
        notifyAll();
    }

    public synchronized void deposer(ArrayList<String> nouvellesLettres) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        lettres.addAll(nouvellesLettres);
        disponible = true;
        notifyAll();
    }
}
