package IA;

import java.awt.Point;

import clientdesarenes.Bot;
import jeu.Plateau;
import jeu.Joueur.*;

/*
 * Classe permettant de réaliser le choix de l'action
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class Brain {
	/*
	 *  Plateau sur lequel le joueur joue
	 */
	public static Plateau PLATEAU;
	/*
	 *  Joueur
	 */
	public static Bot JOUEUR;
	
	
	/*
	 * Constantes utiles
	 */

	int NB_LIVRE_NULL = 0;
	int NB_LIVRE_FAIBLE = 1; //@TODO la calculer en fonctions des livres max sur la map
	int NB_LIVRE_MOYEN = 2;
	int NB_LIVRE_IMPORTANT = 3;
	
	int NB_ESPRIT_FAIBLE = 21;
	int NB_ESPRIT_MILIEU = 50;
	int NB_ESPRIT_MOYEN = 61;
	int NB_ESPRIT_IMPORTANT = 81;
	
	/*
	 * Constructeur
	 */
	public Brain(Plateau plateau, Bot joueur) {
		PLATEAU = plateau;
		JOUEUR = joueur;
	}

	/*
	 * Fonction principale qui détermine le choix de l'action. Utilise toutes les autres fonctions de la classe
	 * @param plateau
	 * @return returnAction
	 */
	public Action run(){
		Action returnAction = Action.RIEN;
		Point positionJoueur = JOUEUR.donnePosition();
		int nbLivreJoueur = Utils.getNombreLivre(JOUEUR);
		int nbEspritJoueur = JOUEUR.donneEsprit();
		
		System.out.println(nbLivreJoueur);
		
		//@TODO
		if(nbLivreJoueur <= NB_LIVRE_FAIBLE){
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
				livreNullEspritFaible();
			}
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
			}
			else if (nbEspritJoueur < NB_ESPRIT_MOYEN){
			}
			else if(nbEspritJoueur < NB_ESPRIT_IMPORTANT){
			}
			else{	
			}
		}
		else if(nbLivreJoueur <= NB_LIVRE_MOYEN){
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
			}
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
			}
			else if (nbEspritJoueur < NB_ESPRIT_MOYEN){
			}
			else if(nbEspritJoueur < NB_ESPRIT_IMPORTANT){
			}
			else{	
			}
		}
		else if(nbLivreJoueur <= NB_LIVRE_IMPORTANT){
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
			}
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
			}
			else if (nbEspritJoueur < NB_ESPRIT_MOYEN){
			}
			else if(nbEspritJoueur < NB_ESPRIT_IMPORTANT){
			}
			else{	
			}
		}
		else{
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
			}
			if (nbEspritJoueur < NB_ESPRIT_FAIBLE){
			}
			else if (nbEspritJoueur < NB_ESPRIT_MOYEN){
			}
			else if(nbEspritJoueur < NB_ESPRIT_IMPORTANT){
			}
			else{	
			}
		}
			
				
		return returnAction;
	}
	
	public Action livreNullEspritFaible(){
		Action returnAction = Action.RIEN;
		
		return returnAction;
	}

	
	
}
