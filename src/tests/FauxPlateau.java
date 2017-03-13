package tests;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.Joueur;
import jeu.Plateau;

public class FauxPlateau extends Plateau {
	public FauxPlateau(int n, String s) {
		super(n, s);
	}
	
	@Override
	public int nombreDeLivresJoueur(int c) {
		return c*2;
	}

	@SuppressWarnings({ "serial", "unused" })
	@Override
	public HashMap<Integer, ArrayList<Point>> cherche(Point depart, int rayon, int masqueDeRecherche) {
		if (masqueDeRecherche != Plateau.CHERCHE_JOUEUR) return super.cherche(depart, rayon, masqueDeRecherche);
		
		HashMap<Integer, ArrayList<Point>> map = new HashMap<Integer, ArrayList<Point>>();
		ArrayList<Point> list = new ArrayList<Point>();
		for (int i = 1; i <= 3; i++) {
			list.add(new Point(i, i));
		}
		map.put(masqueDeRecherche, list);
		
		return map;
	}
	
	@Override
	public Joueur donneJoueurEnPosition(Point pos) {
		FauxJoueur fj = new FauxJoueur();
		fj.setCouleur((int) pos.getX());
		return fj;
	}
}
