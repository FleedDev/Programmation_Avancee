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
	}

}
