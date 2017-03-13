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
	
	/*
	 * Constructeur
	 */
	public void setPlateauEtJoueur(Plateau plateau, Bot joueur) {
		PLATEAU = plateau;
		JOUEUR = joueur;
		NB_LIVRE_TOTAL = Utils.getTotalLivre();
		NB_LIVRE_FAIBLE = NB_LIVRE_TOTAL/8;
		NB_LIVRE_MOYEN = NB_LIVRE_TOTAL/4;
		NB_LIVRE_IMPORTANT = NB_LIVRE_TOTAL/2;
	}
	
	protected Objectif nouvelObjectif() {		
		int nombreLivres = JOUEUR.nombreLivres();
		Joueur[] joueurs = Utils.getJoueurs(PLATEAU);
		
		Joueur adversaireLePlusProche = null;
		int distanceAdversaireProche = 0;
		for(Joueur j : joueurs) {
			if (j.donneCouleurNumerique() == JOUEUR.donneCouleurNumerique()) continue;
			
			int dist = Utils.getDistance(JOUEUR.donnePosition(), j.donnePosition());
			if (adversaireLePlusProche == null || dist < Utils.getDistance(JOUEUR.donnePosition(), adversaireLePlusProche.donnePosition())) {
				adversaireLePlusProche = j;
				distanceAdversaireProche = dist;
			}
		}
		
		Point[] livres = Utils.getLivres(PLATEAU);
		Point[] lits = Utils.getLits(PLATEAU);
		
		if (nombreLivres <= NB_LIVRE_FAIBLE) { // Peu de livres
			if (distanceAdversaireProche == 1 && adversaireLePlusProche.donneEsprit() < JOUEUR.donneEsprit()) { // Adversaire proche + tuable en un coup
				return new Objectif(Plateau.CHERCHE_JOUEUR, adversaireLePlusProche.donnePosition());
			} else {
				ArrayList<Point> livresProches = new ArrayList<Point>();
				Point livreAdverse = null;
				int distanceLivreProche = 0;
				for(Point livre : livres) {
					int dist = Utils.getDistance(JOUEUR.donnePosition(), livre);
					if (livresProches.size() == 0 || dist <= Utils.getDistance(JOUEUR.donnePosition(), livresProches.get(0))) {
						livresProches.add(livre);
						distanceLivreProche = dist;
						
						if (Plateau.donneProprietaireDuLivre(PLATEAU.donneContenuCellule(livre)) != 0) {
							livreAdverse = livre;
						}
					}
				}
				
				if (livreAdverse == null) { // Aucun des livres à proximité n'appartient à quelqu'un
					return new Objectif(Plateau.CHERCHE_LIVRE, livresProches.get(0)); 
				} else if (JOUEUR.donneEsprit() > NB_ESPRIT_FAIBLE + distanceLivreProche) { // Livre appartenant à quelqu'un
					return new Objectif(Plateau.CHERCHE_LIVRE, livreAdverse);
				} else { // Aucun des livres n'est prenable
					return new Objectif(Plateau.CHERCHE_LIT, Utils.pointLePlusProche(JOUEUR.donnePosition(), lits));
				}
			}
		}
		
		return null;
	}

	/*
	 * Fonction principale qui détermine le choix de l'action. Utilise toutes les autres fonctions de la classe
	 * @param plateau
	 * @return returnAction
	 */
	public Action run(){
		this.objectif = this.nouvelObjectif();
		// TODO: Se déplacer vers l'objectif
		Node objNode = PLATEAU.donneCheminEntre(JOUEUR.donnePosition(), this.objectif.position()).get(0);
		Point objPoint = new Point(objNode.getPosX(), objNode.getPosY());
		return Utils.pointCardinal(JOUEUR.donnePosition(), objPoint);
	}
	
	public Action livreNullEspritFaible(){
		Action returnAction = Action.RIEN;
		
		return returnAction;
	}

}
