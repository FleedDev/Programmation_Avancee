import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame
{

    private final int LARG=1000, HAUT=500, NBRECOL=1;
    int NbLigne;
    //JButton sonBouton;

    public UneFenetre(int nbligne)
    {
        // TODO
        super("UnMobile");
        NbLigne=nbligne;
        Container leConteneur = getContentPane();
        leConteneur.setLayout(new GridLayout(NbLigne,NBRECOL));

        CemaphoreGenerale cemaphoreGenerale=new CemaphoreGenerale(3);
        for (int i = 0; i < NbLigne; i++){
            UnMobile sonMobile = new UnMobile(LARG,HAUT/nbligne,cemaphoreGenerale);
            Thread laTache = new Thread (sonMobile);
            leConteneur.add(sonMobile);
            laTache.start();
        }
        setVisible(true);
        setSize(LARG,HAUT);


        // parler du thread /1
        //parler des boutons /2
        //le blocage c'est dans le même niveau d'execution le runnable ne peut pas donc pas suspendre et resume
        //Pour chaque thred ça ressosurce dans laquelle il peint c'est un couloir,
        // Nous on dit dans la partie X de N sont ressource critique.
        // Pour chaque N nombre de ressource qui donne accès a N nombre de ressources
        //Modifier le code des mobiles dans le même cas que Affichage puis identifier
        //une section critique il faudra faire un wait dans la section critique et puis donc
        //effectuer un SémaphoreGénérale

        //Il est possible qu'il est des problèmes de synchronisation
        //et donc que certains mobiles ne passe jamais la barrière

        //Problème rencomtré : on initialise les nouveaux thread dans la boucle pas dehors
        //on déclare un sémaphore et c'est le même sémaphore



    }

}
