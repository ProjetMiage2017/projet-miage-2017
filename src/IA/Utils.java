package IA;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.*;
import jeu.Joueur.Action;
import IA.Brain;

/**
 * Classe regroupant les fonctions utiles au Brain
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class Utils {
	
	/**
	 * Fonction qui calcule le nombre de livres total
	 * @return nb livres
	 */
	public static int getTotalLivre(){
		HashMap<Integer, ArrayList<Point> > hmLivres= Brain.PLATEAU.cherche(new Point(Brain.PLATEAU.donneTaille()/2,Brain.PLATEAU.donneTaille()/2), Brain.PLATEAU.donneTaille()/2, Plateau.CHERCHE_LIVRE);
		return hmLivres.get(2).size();
	}
	
	/**
	 * Fonction qui calcule notre position en terme de livre par rapport aux autres joueurs
	 * @return position
	 * @TODO
	 * @TODO delete ?
	 */
	public static int getPositionForLivres(){
		int position = 0 ;
		return position;
	}
	
	/**
	 * Fonction qui calcule le nombre de tours qu'on met pour tuer un ennemi
	 * @param J l'ennemi
	 * @return nb tours
	 */
	public int getTurnsToKill(Joueur J){
		return J.donneEsprit()/20;
	}
	
	/**
	 * Fonction qui determine les degats reçus suite à un combat
	 * @param J l'ennemi
	 * @return degatsRecus
	 */
	public int getDamageGivenBy(Joueur J, boolean avantage){
		// nb tour (-1 si on a l avantage) * degats + nb de PE que peut nous couter les actions
		if(avantage)
			return (getTurnsToKill(J) -1)*20 + getTurnsToKill(J)-1;
		else
			return getTurnsToKill(J)*20 + getTurnsToKill(J)-1;
	}
	
	/**
	 * Fonction qui determine si on assez de PE pour tuer l'ennemi si on engage ou pas le combat
	 * @param J l'ennemi
	 * @param avantage si on engage le combat
	 * @return isKillable
	 */
	public boolean isEnnemyKillable(Joueur J, boolean avantage){
		return getDamageGivenBy(J, avantage) < Brain.JOUEUR.donneEsprit();	
	}
	
	/**
	 * Fonction qui verifie si c'est rentable de tuer un ennemi
	 * @param J l'ennemi
	 * @return isWorth
	 * @TODO
	 */
	public boolean isEnnemyWorthToKill(Joueur J, boolean avantage){
		if(isEnnemyKillable(J, avantage)){
			if(avantage){
				return Brain.PLATEAU.nombreDeLivresJoueur(J.donneCouleurNumerique())*20< getDamageGivenBy(J, avantage);
			}
			else{
				// en admettant qu on met au moins 15 tours a l attraper
				return Brain.PLATEAU.nombreDeLivresJoueur(J.donneCouleurNumerique())*20 + 15 < getDamageGivenBy(J, avantage);
			}
		}
		else{
			return false;
		}
	}
	
	/**
	 * Renvoie tous les joueurs sur le plateau
	 * @param t plateau
	 * @return tableau de joueurs
	 */
	public static Joueur[] getJoueurs(Plateau t) {
		HashMap<Integer, ArrayList<Point>> recherche = t.cherche(new Point(t.donneTaille() / 2, t.donneTaille() / 2), t.donneTaille() / 2, Plateau.CHERCHE_JOUEUR);
		Joueur[] joueurs = new Joueur[recherche.get(Plateau.CHERCHE_JOUEUR).size()];
		for (int i = 0; i < joueurs.length; i++) {
			joueurs[i] = t.donneJoueurEnPosition(recherche.get(Plateau.CHERCHE_JOUEUR).get(i));
		}
		return joueurs;
	}
	
	/**
	 * Renvoie tous les livres sur le plateau
	 * @param t plateau
	 * @return tableau de points
	 */
	public static Point[] getLivres(Plateau t) {
		HashMap<Integer, ArrayList<Point>> recherche = t.cherche(new Point(t.donneTaille() / 2, t.donneTaille() / 2), t.donneTaille() / 2, Plateau.CHERCHE_LIVRE);
		return recherche.get(Plateau.CHERCHE_LIVRE).toArray(new Point[0]);
	}
	
	/**
	 * Renvoie tous les lits sur le plateau
	 * @param t plateau
	 * @return tableau de points
	 */
	public static Point[] getLits(Plateau t) {
		HashMap<Integer, ArrayList<Point>> recherche = t.cherche(new Point(t.donneTaille() / 2, t.donneTaille() / 2), t.donneTaille() / 2, Plateau.CHERCHE_LIT);
		return recherche.get(Plateau.CHERCHE_LIT).toArray(new Point[0]);
	}
	
	/**
	 * Renvoie le point le plus proche par rapport Ã  une position
	 * @param position position par rapport Ã  laquelle mesurer les distances
	 * @param points tableau de points
	 * @return point le plus proche
	 */
	public static Point pointLePlusProche(Point position, Point[] points) {
		Point pp = points[0];
		int distance = Utils.getDistance(position, pp);
		for(Point p : points) {
			int dist = Utils.getDistance(position, p);
			if (dist < distance) {
				pp = p;
			}
		}
		return pp;
	}
	
	/**
	 * Renvoie la distance (horizontale + verticale) entre deux points
	 * @param depart point de dÃ©part
	 * @param arrivee point d'arrivÃ©e
	 * @return distance
	 */
	public static int getDistance(Point depart, Point arrivee) {
		return (int) Math.abs(depart.getX() - arrivee.getX() + depart.getY() - arrivee.getY());
	}
	
	/**
	 * DÃ©termine la position relative d'un point par rapport Ã  un autre
	 * @param referentiel
	 * @param destination
	 * @return position relative
	 */
	public static Action pointCardinal(Point referentiel, Point destination) {
		if (referentiel.getY() == destination.getY()) {
			return referentiel.getX() < destination.getX() ? Action.DROITE : Action.GAUCHE;
		} else {
			return referentiel.getY() < destination.getY() ? Action.BAS : Action.HAUT; 
		}
	}

	/** 
	 * Determine l'adversaire le plus proche
	 * @return l'adversaire 
	 */
	public static Joueur getJoueurLePlusProche(){
		Joueur[] joueurs = getJoueurs(Brain.PLATEAU);
		Joueur adversaireLePlusProche = null;
		for(Joueur j : joueurs) {
			//si c'est notre joueur on passe
			if (j.donneCouleurNumerique() == Brain.JOUEUR.donneCouleurNumerique()) continue;
			
			int dist = Utils.getDistance(Brain.JOUEUR.donnePosition(), j.donnePosition());
			if (adversaireLePlusProche == null || dist < Utils.getDistance(Brain.JOUEUR.donnePosition(), adversaireLePlusProche.donnePosition())) {
				adversaireLePlusProche = j;
			}
		}
		return adversaireLePlusProche;
	}
	
	/** 
	 * Determine la distance avec l'adversaire le plus proche
	 * @return la distance
	 */
	public static int getDistanceDuJoueurLePlusProche(){
		Joueur adversaireLePlusProche = getJoueurLePlusProche();
		return getDistance(Brain.JOUEUR.donnePosition(), adversaireLePlusProche.donnePosition());
	}
	
	/** 
	 * Determine le livre le plus proche. Si 2 livres sont autant proches, alors vise les livres deja capturés par les autres joueurs en priorité
	 * @return la coord du livre
	 */
	public static Point getLivreLePlusProche(){
		Point[] livres = getLivres(Brain.PLATEAU);
		Point livreLePlusProche = null;
		for(Point livre : livres) {
			int proprietaire = Plateau.donneProprietaireDuLivre(Brain.PLATEAU.donneContenuCellule(livre));
			if (proprietaire == Brain.JOUEUR.donneCouleurNumerique() +1) continue; 
			int dist = Utils.getDistance(Brain.JOUEUR.donnePosition(), livre);
			if (livreLePlusProche == null || dist <= Utils.getDistance(Brain.JOUEUR.donnePosition(), livre)) {
				//0 etant un livre sans proprietaire
				if(livreLePlusProche == null || Plateau.donneProprietaireDuLivre(Brain.PLATEAU.donneContenuCellule(livreLePlusProche)) != 0){
					livreLePlusProche = livre;
				}
			}
		}
		return livreLePlusProche;
	}
	
	/** 
	 * Determine la distance avec le livre le plus proche
	 * @return la distance
	 */
	public static int getDistanceDuLivreLePlusProche(){
		Point livreLePlusProche = getLivreLePlusProche();
		return getDistance(Brain.JOUEUR.donnePosition(), livreLePlusProche);
	}
	
	
	//@TODO fonction prevision perte d'energie selon l'objectif

}
