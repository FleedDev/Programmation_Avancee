import java.lang.String;

class Exclusion{}
public class Affichage extends Thread{
	String texte;
	Exclusion exclusion = new Exclusion();
	Cemaphore CemaphoreBinaire;

	public Affichage (String txt, Cemaphore cemaphoreBinaire){
		texte=txt;
		CemaphoreBinaire=cemaphoreBinaire;
	}
	
	public void run(){

		CemaphoreBinaire.syncWait();
		//section critique du code
		for (int i = 0; i < texte.length(); i++) {
			System.out.print(texte.charAt(i));
			try {
				sleep(100);
			} catch (InterruptedException e) {
			}

		}
		CemaphoreBinaire.syncSignal();


		//section critique toute la boucle for
		//j'ai deux thread indépendant j'ai besoin de les afficher
		//mais séparément sans que les deux se mélange
		//pour cela je vais synchronise

	}
}
