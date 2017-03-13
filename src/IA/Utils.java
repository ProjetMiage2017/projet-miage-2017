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
	 * Fonction qui calcule le nombr ede livres du joueur J
	 * @param J
	 * @return nb livres
	 */
	public static int getNombreLivre(Joueur J){
		int ENDROIT_LIVRE_J = 0;
		switch(J.donneCouleurNumerique()){
			case 0 : ENDROIT_LIVRE_J = Brain.PLATEAU.ENDROIT_LIVRE_J1; break;
			case 1 : ENDROIT_LIVRE_J = Brain.PLATEAU.ENDROIT_LIVRE_J2; break;
			case 2 : ENDROIT_LIVRE_J = Brain.PLATEAU.ENDROIT_LIVRE_J3; break;
			case 3 : ENDROIT_LIVRE_J = Brain.PLATEAU.ENDROIT_LIVRE_J4; break;
		}
		System.out.println(J.donneCouleurNumerique());
		System.out.println(Brain.PLATEAU.donneTaille());
		HashMap<Integer, ArrayList<Point> > hmLivres= Brain.PLATEAU.cherche(new Point(Brain.PLATEAU.donneTaille()/2,Brain.PLATEAU.donneTaille()/2), Brain.PLATEAU.donneTaille()/2, Brain.PLATEAU.MASQUE_ENDROITS|ENDROIT_LIVRE_J);
		 System.out.println(hmLivres);
		return hmLivres.size();
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
