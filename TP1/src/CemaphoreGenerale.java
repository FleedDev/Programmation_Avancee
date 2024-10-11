public final class CemaphoreGenerale extends Cemaphore {
    int valeurinitiale;
    protected CemaphoreGenerale(int valeurInitiale) {
        super((valeurInitiale != 0) ? valeurInitiale : 1);
    }

    public final synchronized void syncSignal(){
        super.syncSignal();
        //System.out.print(valeur);
        if (valeur>valeurinitiale) valeur = valeurinitiale ;
    }
}
