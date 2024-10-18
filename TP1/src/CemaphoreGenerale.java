public final class CemaphoreGenerale extends Cemaphore {
    int valeurinitiale;

    // Constructeur qui initialise correctement la valeur initiale
    protected CemaphoreGenerale(int valeurInitiale) {
        super((valeurInitiale != 0) ? valeurInitiale : 1);
        this.valeurinitiale = valeurInitiale; // Affecte correctement la valeur initiale
    }

    @Override
    public final synchronized void syncSignal() {
        super.syncSignal();
        // Vérifie si la valeur dépasse la valeur initiale et la réinitialise si nécessaire
        if (valeur > valeurinitiale) {
            valeur = valeurinitiale;
        }
    }
}

