import java.awt.*;
import javax.swing.*;



class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonCote=40;
	int sonTemps;
	Cemaphore cemaphoreGeneral ;
    
    UnMobile(int telleLargeur, int telleHauteur, Cemaphore parCemaphore)
    {
	super();
	cemaphoreGeneral = parCemaphore;
	saLargeur = telleLargeur;
	saHauteur = telleHauteur;
	sonTemps = (int)(Math.random()*350) ;
	setSize(telleLargeur, telleHauteur);
    }

	public void run()
    {
		while(true){
			for (sonDebDessin = 0; sonDebDessin + sonCote > saLargeur/3; sonDebDessin+= sonPas)
			{
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
			cemaphoreGeneral.syncWait();
			for (;sonDebDessin > saLargeur/3 ; sonDebDessin+= sonPas)
			{
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
			cemaphoreGeneral.syncSignal();
			for (;sonDebDessin > sonDebDessin/3 ; sonDebDessin+= sonPas)
			{
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
			for (; sonDebDessin + sonCote < saLargeur/3; sonDebDessin-= sonPas)
			{
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
			cemaphoreGeneral.syncWait();
			for (;sonDebDessin > saLargeur/3 ; sonDebDessin-= sonPas)
			{
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
			cemaphoreGeneral.syncSignal();
			for (;sonDebDessin > -sonPas; sonDebDessin-= sonPas)
			{
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
		}
    }

    public void paintComponent(Graphics telCG)
    {
	super.paintComponent(telCG);
	telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}