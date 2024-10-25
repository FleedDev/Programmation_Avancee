import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cemaphore cemaphoreBinaire = new CemaphoreBinaire(1);
		Affichage TA = new Affichage("AAA", cemaphoreBinaire);
		Affichage TB = new Affichage("BB", cemaphoreBinaire);
		Affichage TC = new Affichage("CCCCCCC", cemaphoreBinaire);
		Affichage TD = new Affichage("DDDDDDDDD", cemaphoreBinaire);

		TB.start();

		TA.start();

		TC.start();

		TD.start();

		//ils ne s'affichent pas dans le même dans le ordre
		//B est le premier processus utilisé mais les autres ayant le même niveau d'importance
		//semaphore regarde la portion de code si y'a personne permet de passer mais sinon il ne peut pas
		//le garde dit wait, ils sont donc en attente à l'accès a la resource
		//quand le thread arrive à la fin de la zone il envois un signal 'je libère la place' signal
		//si un signal est prêt, l'os execute le nouveau process, puis signal et remet dans le tableau
		//semaphore commence par wait et termine par signal
	}

}
