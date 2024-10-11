import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

class UneFenetre extends JFrame
{
    UnMobile sonMobile;
    private final int LARG=1000, HAUT=500, NBRELIG=10, NBRECOL=1;
    //JButton sonBouton;

    public UneFenetre()
    {
        // TODO
        super("UnMobile");
        Thread laTache;
        Container leConteneur = getContentPane();
        leConteneur.setLayout(new GridLayout(NBRELIG,NBRECOL));

        for (int i = 0; i < NBRELIG; i++){
            sonMobile = new UnMobile(LARG,HAUT);
            laTache = new Thread (sonMobile);
            leConteneur.add(sonMobile);
            laTache.start();
        }
        setVisible(true);
        setSize(LARG,HAUT);



        //le blocage c'est dans le même niveau d'execution le runnable ne peut pas donc pas suspendre et resume
    }

}
