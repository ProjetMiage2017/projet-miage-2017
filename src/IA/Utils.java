package IA;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.*;
import IA.Brain;

/*
 * Classe regroupant les fonctions utiles au Brain
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class Utils {
	
	/*
	 * Fonction qui calcule le nombre de livres total
	 * @param J
	 * @return nb livres
	 */
	public static int getTotalLivre(){
		HashMap<Integer, ArrayList<Point> > hmLivres= Brain.PLATEAU.cherche(new Point(Brain.PLATEAU.donneTaille()/2,Brain.PLATEAU.donneTaille()/2), Brain.PLATEAU.donneTaille()/2, Brain.PLATEAU.CHERCHE_LIVRE);
		return hmLivres.get(2).size();
	}
	
	/*
	 * Fonction qui calcule notre position en terme de livre par rapport aux autres joueurs
	 * @param J
	 * @return position
	 */
	public static int getPositionForLivres(){
		int position = 0 ;
		//@TODO
		return position;
	}
	
	
	//@TODO fonction prevision perte d'energie selon l'objectif

}
