package tests;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.Joueur;
import jeu.Plateau;


/**
 * Classe permettant de creer un faux plateau pour les testUnitaires
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */

public class FauxPlateau extends Plateau {
	
	
	/**
	 * Crée une nouvelle instance de Plateau à partir d'une chaine encodée 
	 * par un champ de ce qui est renvoyé par Plateau.encode().
	 * @param n nombre de tours
	 * @param s une chaine de caractère
	 */
	public FauxPlateau(int n, String s) {
		super(n, s);
	}
	
	
	/**
	 * Fonction qui renturn le nombre de livre du joueur
	 * @param c numero du jouer
	 * @param c*2
	 */
	@Override
	public int nombreDeLivresJoueur(int c) {
		return c*2;
	}
    
	/**
	 * Fonction de recherche 
	 * @param depart  point de depart
	 * @param rayon   rayon de depart
	 * @param masqueDeRecherche masque de recherche
	 * @return map une map
	 */
	
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
	
	
	/**
	 * Fonction qui retourne un objet jouer avec sa postion
	 * @param pos une position
	 *  @return fj
	 */
	@Override
	public Joueur donneJoueurEnPosition(Point pos) {
		FauxJoueur fj = new FauxJoueur();
		fj.setCouleur((int) pos.getX());
		return fj;
	}
}
