import java.applet.Applet;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.*;

@SuppressWarnings({ "deprecation", "serial" })
public class Forme extends Applet implements Runnable {

	/*
	 * Temperature = tp Humidity = hu Pressure = pr
	 */

	// Elements
	Label l_tp, l_hu, l_pr, unit_tp, unit_hu, unit_pr;
	JProgressBar pb_tp, pb_hu, pb_pr;
	TextField tf_tp, tf_hu, tf_pr;
	// Panels
	Panel p_main, p_tp, p_hu, p_pr;
	// Dimension and size
	Dimension pbdim;
	Dimension tfdim;
	int tflen = 10;
	// Update Values
	String nomFichier, ligne;
	double val_tp, val_hu, val_pr;
	// Thread
	private Thread checkValues;
	int freq = 100;

	public void init() {
		setLayout(new GridLayout());
		setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));

		nomFichier = "Values.txt";

		pbdim = new Dimension(300, 30);
		tfdim = new Dimension(1000, 1000);

		pb_tp = new JProgressBar();
		pb_hu = new JProgressBar();
		pb_pr = new JProgressBar(860, 1090);

		pb_tp.setPreferredSize(pbdim);
		pb_hu.setPreferredSize(pbdim);
		pb_pr.setPreferredSize(pbdim);

		tf_tp = new TextField(tflen);
		tf_hu = new TextField(tflen);
		tf_pr = new TextField(tflen);

		paramTF(tf_tp);
		paramTF(tf_hu);
		paramTF(tf_pr);

		l_tp = new Label("Température");
		l_hu = new Label("Humidité");
		l_pr = new Label("Pression");
		unit_tp = new Label("°C  ");
		unit_hu = new Label("%   ");
		unit_pr = new Label("hPa");

		p_main = new Panel();
		p_tp = new Panel();
		p_hu = new Panel();
		p_pr = new Panel();

		p_main.setLayout(new GridLayout(6, 1));
		p_tp.setLayout(new FlowLayout());
		p_hu.setLayout(new FlowLayout());
		p_pr.setLayout(new FlowLayout());

		p_main.add(l_tp);
		p_main.add(p_tp);
		p_main.add(l_hu);
		p_main.add(p_hu);
		p_main.add(l_pr);
		p_main.add(p_pr);

		p_tp.add(pb_tp);
		p_hu.add(pb_hu);
		p_pr.add(pb_pr);

		p_tp.add(tf_tp);
		p_pr.add(tf_pr);
		p_hu.add(tf_hu);

		p_tp.add(unit_tp);
		p_hu.add(unit_hu);
		p_pr.add(unit_pr);

		add(p_main);

	}

	public void paramTF(TextField tf) {
		tf.setPreferredSize(tfdim);
		tf.setEditable(false);
		tf.setFocusable(false);
	}

	public void changeBar(JProgressBar pb, TextField tf, double db) {
		pb.setValue((int) db);
		tf.setText("" + ((int) (db * 100) / 100.0));
		repaint();
	}

	public void changeTpBar(JProgressBar pb, TextField tf, double db) {
		if (db < 0) {
			pb.setMinimum(0);
			pb.setMaximum(100);
			pb.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		} else {
			pb.setMinimum(0);
			pb.setMaximum(70);
			pb.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		}
		pb.setValue(Math.abs((int) db));
		tf.setText("" + ((int) (db * 100) / 100.0));
		repaint();
	}

	public void updateValues() {
		try {
			
			URL url = getClass().getResource("../bin/" + nomFichier);
			//System.out.println("DEBUG = " + "url : " + url);
			InputStream ips = url.openStream();
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			ligne = br.readLine();
			//System.out.println("DEBUG = " + "Lecture : " + ligne);

			br.close();
			StringTokenizer st = new StringTokenizer(ligne, ":", false);
			val_tp = Double.parseDouble(st.nextToken());
			val_hu = Double.parseDouble(st.nextToken());
			val_pr = Double.parseDouble(st.nextToken());
			//System.out.println("DEBUG = " + "update tp : " + val_tp);
			//System.out.println("DEBUG = " + "update hu : " + val_hu);
			//System.out.println("DEBUG = " + "update pr : " + val_pr);

		} catch (ArrayIndexOutOfBoundsException argEmpty) {
			System.out.println("Saisir le nom du fichier à copier");
		} catch (FileNotFoundException notFound) {
			System.out.println("Le fichier spécifié est introuvable");
		} catch (NumberFormatException nexept) {
			System.out.println("Problème de format");
		} catch (Exception er) {
			System.out.println(er.toString());
			System.out.println("Update ERROR");
		}

	}

	public void start() {
		// Au début de l'applet, création et démarrage du thread
		if (checkValues == null) {
			checkValues = new Thread(this);
			checkValues.start();
		}
	}

	@SuppressWarnings("static-access")
	public void run() {
		while (checkValues != null) {
			updateValues();
			changeTpBar(pb_tp, tf_tp, val_tp);
			changeBar(pb_hu, tf_hu, val_hu);
			changeBar(pb_pr, tf_pr, val_pr);
			try {
				checkValues.sleep(freq);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
				System.out.println("Thread ERROR");
			}
		}
	}

	public void stop() {
		// A la fin de l'applet, arrêt du thread
		checkValues.stop();
		checkValues = null;
	}
}
