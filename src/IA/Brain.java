package IA;

import clientdesarenes.Bot;
import jeu.Plateau;
import jeu.Joueur.*;

/*
 * Classe permettant de réaliser le choix de l'action
 * @author Jeremy Rossignol
 */
public class Brain {
	/*
	 *  plateau sur lequel le joueur joue
	 */
	private Plateau plateau;
	/*
	 *  joueur
	 */
	private Bot joueur;
	
	/*
	 * Constructeur
	 */
	public Brain(Plateau plateau, Bot joueur) {
		super();
		this.plateau = plateau;
		this.joueur = joueur;
	}

	/*
	 * Fonction principale qui détermine le choix de l'action. Utilise toutes les autres fonctions de la classe
	 * @param plateau
	 * @return returnAction
	 */
	public Action run(){
		Action returnAction = Action.RIEN;
		//@TODO
		return returnAction;
	}
	
	/*
	 * Fonction qui calcule si l'on possède beaucoup d elivres par rapport aux autres ou pas
	 * @return position et avance sur le precedent 
	 */
	public int compareNbLivres(){
		int returnNbLivres = 0 ;
		//this.plateau.cherche(this.plateau.ENDROIT_LIVRE_J1);
		//@TODO
		return returnNbLivres;
	}
	
	
}
