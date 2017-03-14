package IA;

import java.awt.Point;
import java.util.ArrayList;

import clientdesarenes.Bot;
import jeu.Plateau;
import jeu.astar.Node;
import jeu.Joueur;
import jeu.Joueur.*;

/**
 * Classe permettant de réaliser le choix de l'action
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class Brain {
	/**
	 *  Plateau sur lequel le joueur joue
	 */
	public static Plateau PLATEAU;
	/**
	 *  Joueur
	 */
	public static Bot JOUEUR; 
	
	/**
	 * Objectif actuel du bot
	 */
	private Objectif objectif;
	
	/*
	 * Constantes utiles
	 */
	
	int NB_LIVRE_TOTAL = 0;
	
	int NB_LIVRE_NULL = 0;
	int NB_LIVRE_FAIBLE = 1;
	int NB_LIVRE_MOYEN = 2;
	int NB_LIVRE_IMPORTANT = 3;
	
	int NB_ESPRIT_FAIBLE = 21;
	int NB_ESPRIT_MILIEU = 50;
	int NB_ESPRIT_MOYEN = 61;
	int NB_ESPRIT_IMPORTANT = 81;
	
	/**

	 * Fonction qui met � jour le joueur et le plateau
	 * @param plateau un plateau
	 * @param joueur  un Bot

	 * Fonction qui met � jour le joueur et le plateau

	 */
	public void setPlateauEtJoueur(Plateau plateau, Bot joueur) {
		PLATEAU = plateau;
		JOUEUR = joueur;
		NB_LIVRE_TOTAL = Utils.getTotalLivre();
		NB_LIVRE_FAIBLE = NB_LIVRE_TOTAL/8; //9
		NB_LIVRE_MOYEN = NB_LIVRE_TOTAL/4; //5
		NB_LIVRE_IMPORTANT = NB_LIVRE_TOTAL/2;
	
		//@TODO un nb de livre qui se met � jour selon les nombres de livre des joueurs?
	}
	
	/**
	 * Fonction qui determine un nouvel Objectif
	 * @return le nouvel objectif
	 */
	protected Objectif nouvelObjectif() {		
		int nombreLivres = JOUEUR.nombreLivres();		
		Joueur adversaireLePlusProche = Utils.getJoueurLePlusProche();
		int distanceAdversaireProche = Utils.getDistanceDuJoueurLePlusProche();
		
		Point livreProche = Utils.getLivreLePlusProche();
		int distanceLivreProche = Utils.getDistanceDuLivreLePlusProche();
		
		Point litProche = Utils.getLitLePlusProche();
				
		//cas desesp�r�
		if (nombreLivres <= NB_LIVRE_NULL) {
			//Cas ennemi a proximite
			if (distanceAdversaireProche <= 1) { 
				//Cas ennemi tuable, ou mon esprit est faible et je veux me suicider, ou l'ennemi est rentable � tuer, donc il vaut le risque
				//@TODO cas spawn a  cote
				if(Utils.isEnnemyKillable(adversaireLePlusProche, true) || 
						JOUEUR.donneEsprit() < NB_ESPRIT_FAIBLE ||
						(Utils.isEnnemyWorthToKill(adversaireLePlusProche, false))){
					if(distanceAdversaireProche <1)
						return new Objectif(Plateau.CHERCHE_JOUEUR, JOUEUR.donnePosition());
					return new Objectif(Plateau.CHERCHE_JOUEUR, adversaireLePlusProche.donnePosition());
				}
				else{	
					return new Objectif(Plateau.CHERCHE_LIVRE, livreProche);
				}	
			} 
			
			else {				
				if (JOUEUR.donneEsprit() > NB_ESPRIT_FAIBLE + distanceLivreProche) { // cas on peut aller chercher le livre
					return new Objectif(Plateau.CHERCHE_LIVRE, livreProche);
				}
				else { // Aucun des livres n'est prenable ou pas assez d'esprit
					//@TODO suicide
					return new Objectif(Plateau.CHERCHE_LIT, litProche);
					
				}
			}
		}
		//cas ou on peut tenter des actions risqu�es
		else if(nombreLivres <= NB_LIVRE_FAIBLE){
			//Cas ennemi a proximite et tuable
			if (distanceAdversaireProche <= 1 && Utils.isEnnemyKillable(adversaireLePlusProche, true)) {
				if(distanceAdversaireProche <1)
					return new Objectif(Plateau.CHERCHE_JOUEUR, JOUEUR.donnePosition());
				return new Objectif(Plateau.CHERCHE_JOUEUR, adversaireLePlusProche.donnePosition());
			}
			else {		
				if (JOUEUR.donneEsprit() > NB_ESPRIT_FAIBLE + distanceLivreProche) { // cas on peut aller chercher le livre
					return new Objectif(Plateau.CHERCHE_LIVRE, livreProche);
				}
				else { // Aucun des livres n'est prenable
					return new Objectif(Plateau.CHERCHE_LIT, litProche);
				}
			}
		}
		//cas ou il faut adapter un comportement assez s�curis�
		//@TODO
		else if(nombreLivres <= NB_LIVRE_MOYEN){
			//Cas ennemi a proximite
			if (distanceAdversaireProche <= 1) {
				// si il est tuable
				if(Utils.isEnnemyKillable(adversaireLePlusProche, true)){
					if(distanceAdversaireProche <1)
						return new Objectif(Plateau.CHERCHE_JOUEUR, JOUEUR.donnePosition());
					return new Objectif(Plateau.CHERCHE_JOUEUR, adversaireLePlusProche.donnePosition());
				}
				//sinon fuir
				else{
					System.err.println("FUITE ACTIVE");
					return new Objectif(0, Utils.fuir(adversaireLePlusProche.donnePosition()));
				}	
			}
			//fuir si ennemi a 2 cases et plus fort
			/*else if (distanceAdversaireProche <= 2 && Utils.isEnnemyKillable(adversaireLePlusProche, false)) {
				return new Objectif(0, Utils.fuir(adversaireLePlusProche.donnePosition()));
			}*/
			else {				
				if (JOUEUR.donneEsprit() > NB_ESPRIT_FAIBLE + distanceLivreProche) { // cas on peut aller chercher le livre
					return new Objectif(Plateau.CHERCHE_LIVRE, livreProche);
				}
				else { // Aucun des livres n'est prenable
					return new Objectif(Plateau.CHERCHE_LIT, litProche);
				}
			}
		}
		//Cas ou il faut fuir mais on tente tout de meme de tuer les agresseurs (self-defense)
		//@TODO
		else if(nombreLivres <= NB_LIVRE_IMPORTANT){
			//Cas ennemi a proximite
			if (distanceAdversaireProche <= 1) {
				// si il est tuable
				if(Utils.isEnnemyKillable(adversaireLePlusProche, true)){
					if(distanceAdversaireProche <1)
						return new Objectif(Plateau.CHERCHE_JOUEUR, JOUEUR.donnePosition());
					return new Objectif(Plateau.CHERCHE_JOUEUR, adversaireLePlusProche.donnePosition());
				}
				//sinon fuir
				else{
					System.err.println("FUITE ACTIVE");
					return new Objectif(0, Utils.fuir(adversaireLePlusProche.donnePosition()));
				}	
			}
			//fuir si ennemi a 2 cases et plus fort
			/*else if (distanceAdversaireProche <= 2 && Utils.isEnnemyKillable(adversaireLePlusProche, false)) {
				return new Objectif(0, Utils.fuir(adversaireLePlusProche.donnePosition()));
			}*/
			else {
				//utilisation de NB_ESPRIT_MILIEU contrairement aux fonctions precedentes histoire de garder une zone de confort
				if (JOUEUR.donneEsprit() > NB_ESPRIT_MILIEU + distanceLivreProche) { // cas on peut aller chercher le livre
					return new Objectif(Plateau.CHERCHE_LIVRE, livreProche);
				}
				else { // Aucun des livres n'est prenable
					return new Objectif(Plateau.CHERCHE_LIT, litProche);
				}
			}
		}
		//Cas ou il faut juste fuir et camper � un lit (sans en abuser pour ne pas perdre de PC) pour garder nos livre sle plus longtemps possible
		//@TODO
		else{

			if (JOUEUR.donneEsprit() < NB_ESPRIT_IMPORTANT){
				return new Objectif(Plateau.CHERCHE_LIT, litProche);
			}
			else{
				//@TODO
				//campe a c�t� du lit ou se dirige vers le lit le plus proche s'il n'est pas encore a c�t�
			}
			return new Objectif(Plateau.CHERCHE_LIT, litProche);
		}
	}

	/**
	 * Fonction principale qui détermine le choix de l'action. Utilise toutes les autres fonctions de la classe
	 * @return returnAction
	 */
	public Action run(){

		this.objectif = this.nouvelObjectif();
		if (this.objectif == null) return Action.RIEN;
		System.out.println("OBJECTIF = " + this.objectif);

		Node objNode = PLATEAU.donneCheminEntre(JOUEUR.donnePosition(), this.objectif.position()).get(0);
		Point objPoint = new Point(objNode.getPosX(), objNode.getPosY());
		return Utils.pointCardinal(JOUEUR.donnePosition(), objPoint);
	}

	/**
	 * get objectif courant
	 * @return objectif
	 */
	public Objectif objectif() {
		return this.objectif;
	}
}
