import java.awt.*;

@SuppressWarnings("serial")
public class Affiche_Data extends Frame {

	Forme form;
	Double ftp, fhu, fpr;

	/*
	 * INFOS METEO UTILES
	 * https://www.meteociel.fr/observations-meteo/pression.php
	 */

	public Affiche_Data() {
		addWindowListener(new Fermeture());
		setTitle("Weather Click");
		setLayout(new GridLayout());
		setSize(800, 400);

		form = new Forme();

		add(form);
		form.init();
		form.start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Affiche_Data affiche = new Affiche_Data();
		affiche.setVisible(true);
	}
}
