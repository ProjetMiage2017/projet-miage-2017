package IA;

import java.awt.Point;

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
		int returnNbLivres = 0 ;
		Brain.PLATEAU.cherche(new Point(Brain.PLATEAU.donneTaille()/2,Brain.PLATEAU.donneTaille()/2), Brain.PLATEAU.donneTaille(), Brain.PLATEAU.ENDROIT_LIVRE_J1);
		return returnNbLivres;
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
