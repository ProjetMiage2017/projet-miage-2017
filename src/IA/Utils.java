package IA;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.astar.Node;

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
	public static int getTurnsToKill(Joueur J){
		return J.donneEsprit()/20;
	}
	
	/**
	 * Fonction qui determine les degats reÃ§us suite Ã  un combat
	 * @param J l'ennemi
	 * @return degatsRecus
	 */
	public static int getDamageGivenBy(Joueur J, boolean avantage){
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
	public static boolean isEnnemyKillable(Joueur J, boolean avantage){
		return getDamageGivenBy(J, avantage) < Brain.JOUEUR.donneEsprit();	
	}
	
	/**
	 * Fonction qui verifie si c'est rentable de tuer un ennemi
	 * @param J l'ennemi
	 * @return isWorth
	 */
	public static boolean isEnnemyWorthToKill(Joueur J, boolean avantage){
		if(isEnnemyKillable(J, avantage)){
			if(avantage){
				return Brain.PLATEAU.nombreDeLivresJoueur(J.donneCouleurNumerique())*20< getDamageGivenBy(J, avantage);
			}
			else{
				//@TODO
				//nb de tour à calculer pour être sur qu'on attrape le mecet si il est attrapable
				int x = 0;
				return Brain.PLATEAU.nombreDeLivresJoueur(J.donneCouleurNumerique())*20 + x < getDamageGivenBy(J, avantage);
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
		int distancePointLePlusProche = Utils.getDistanceWithObstacles(position, pp);
		for(Point p : points) {
			int dist = Utils.getDistanceWithObstacles(position, p);
			if (distancePointLePlusProche > dist) {
				pp = p;
				distancePointLePlusProche = dist;
			}
		}
		return pp;
	}
	
	/**
	 * Renvoie la distance (horizontale + verticale) entre deux points
	 * @param depart point de dÃ©part
	 * @param arrivee point d'arrivÃ©e
	 * @return distance
	 * @TODO a delete ?
	 */
	public static int getDistance(Point depart, Point arrivee) {
		return (int) Math.abs(depart.getX() - arrivee.getX() + depart.getY() - arrivee.getY());
	}
	
	/**
	 * Renvoie la distance rï¿½elle entre deux points
	 * @param depart 
	 * @param arrivee
	 * @return distance
	 */
	public static int getDistanceWithObstacles(Point depart, Point arrivee){
		ArrayList<Node> chemin = Brain.PLATEAU.donneCheminEntre(depart, arrivee);
		return chemin == null ? null : chemin.size();
	}
	
	/**
	 * DÃ©termine la position relative d'un point par rapport Ã  un autre
	 * @param referentiel
	 * @param destination
	 * @return position relative
	 */
	public static Action pointCardinal(Point referentiel, Point destination) {
		if (referentiel.equals(destination)) return Action.RIEN;
		
		if (referentiel.getY() == destination.getY()) {
			return referentiel.getX() < destination.getX() ? Action.DROITE : Action.GAUCHE;
		} else if (referentiel.getX() == destination.getX()) {
			return referentiel.getY() < destination.getY() ? Action.BAS : Action.HAUT; 
		}
		
		return Action.RIEN;
	}

	/** 
	 * Determine l'adversaire le plus proche
	 * @return l'adversaire 
	 */
	public static Joueur getJoueurLePlusProche(){
		Joueur[] joueurs = getJoueurs(Brain.PLATEAU);
		Joueur adversaireLePlusProche = null;
		int distAdversaireLePlusProche = 0;
		for(Joueur j : joueurs) {
			int distJoueur = Utils.getDistanceWithObstacles(Brain.JOUEUR.donnePosition(), j.donnePosition());
			//si c'est notre joueur on passe
			if (j.donneCouleurNumerique() == Brain.JOUEUR.donneCouleurNumerique()) continue;
			
			if (adversaireLePlusProche == null || distAdversaireLePlusProche > distJoueur) {
				adversaireLePlusProche = j;
				distAdversaireLePlusProche = distJoueur;
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
		return getDistanceWithObstacles(Brain.JOUEUR.donnePosition(), adversaireLePlusProche.donnePosition());
	}
	
	/** 
	 * Determine le livre le plus proche. Si 2 livres sont autant proches, alors vise les livres deja capturï¿½s par les autres joueurs en prioritï¿½
	 * @return la coord du livre
	 */
	public static Point getLivreLePlusProche(){
		Point[] livres = getLivres(Brain.PLATEAU);
		Point livreLePlusProche = null;
		int distLivreLePlusProche = 0;
		for(Point livre : livres) {
			int proprietaire = Plateau.donneProprietaireDuLivre(Brain.PLATEAU.donneContenuCellule(livre));
			int distLivre = Utils.getDistanceWithObstacles(Brain.JOUEUR.donnePosition(), livre);
			if (proprietaire == Brain.JOUEUR.donneCouleurNumerique() +1) continue; 
			if (livreLePlusProche == null || distLivreLePlusProche > distLivre) {
					livreLePlusProche = livre;
					distLivreLePlusProche = distLivre;
				}
			else if(distLivreLePlusProche == distLivre){
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
		return getDistanceWithObstacles(Brain.JOUEUR.donnePosition(), livreLePlusProche);
	}
	
	/** 
	 * Determine le lit le plus proche. 
	 * @return la coord du livre
	 */
	public static Point getLitLePlusProche(){
		Point[] lits = getLits(Brain.PLATEAU);

		return Utils.pointLePlusProche(Brain.JOUEUR.donnePosition(), lits);
	}
	
	/** 
	 * Determine la case dspo opposee au point passï¿½ en paramettre par rapport au joueur
	 * @TODO ameliorer l'algo, voir mï¿½me si on ne peut pas fuir vers des lits pour essayer de contre-attaquer
	 * @param le point
	 * @return le point dispo oppose
	 * @TODO delete ?
	 */
	public static Point getCaseDispoOpposeA(Point caseAFuir){
		Point caseJoueur = Brain.JOUEUR.donnePosition();
		//xC=2xA-xB 
		Point caseParfaite = new Point(2*caseAFuir.x - caseJoueur.x, 2*caseAFuir.y - caseJoueur.y);
		//tenir compte des joueurs plus tard
		if(Brain.PLATEAU.joueurPeutAllerIci(caseParfaite.x, caseParfaite.y, false, true)){
			return caseParfaite;
		}
		//pourri @TODO trouver une autre coord
		else{
			return getLitLePlusProche();
		}
	}
	/**
	 * Determine la case à aller pour fuir
	 * @param caseAFuir
	 * @return case destination
	 */
	
	public static Point fuir(Point caseAFuir){
		Point caseJoueur = Brain.JOUEUR.donnePosition();
		Joueur.Action actionInterdite = pointCardinal(caseJoueur, caseAFuir);
		Point caseParfaite;
		Point casePossible1;
		Point casePossible2;
		System.err.println(actionInterdite);

		switch(actionInterdite){
			case DROITE :
				caseParfaite = new Point(caseJoueur.x-1 , caseJoueur.y); 
				casePossible1 = new Point(caseJoueur.x , caseJoueur.y-1);
				casePossible2 = new Point(caseJoueur.x , caseJoueur.y+1);
				break;
			case GAUCHE :
				caseParfaite = new Point(caseJoueur.x+1 , caseJoueur.y);
				casePossible1 = new Point(caseJoueur.x , caseJoueur.y-1);
				casePossible2 = new Point(caseJoueur.x-1 , caseJoueur.y+1);
				break;
			case HAUT :
				caseParfaite = new Point(caseJoueur.x , caseJoueur.y+1);
				casePossible1 = new Point(caseJoueur.x-1 , caseJoueur.y);
				casePossible2 = new Point(caseJoueur.x+1 , caseJoueur.y);
				break;
			case BAS :
				caseParfaite = new Point(caseJoueur.x , caseJoueur.y-1);
				casePossible1 = new Point(caseJoueur.x-1 , caseJoueur.y);
				casePossible2 = new Point(caseJoueur.x+1 , caseJoueur.y+1);
				break;
			default :
				caseParfaite = caseAFuir;
				casePossible1 = caseAFuir;
				casePossible2 = caseAFuir;
				break;
		}
		if(Brain.PLATEAU.joueurPeutAllerIci(caseParfaite.x, caseParfaite.y, false, true)){
			return caseParfaite;
		}
		else if(Brain.PLATEAU.joueurPeutAllerIci(casePossible1.x, casePossible1.y, false, true)){
			return casePossible1;
		}
		else if(Brain.PLATEAU.joueurPeutAllerIci(casePossible2.x, casePossible2.y, false, true)){
			return casePossible2;
		}
		else{
			return caseAFuir;
		}

	}
	//
	//@TODO Fonction qui regarde si les objectifs ne tournent pas trop en boucle 
	//@TODO Si ca tourne en boucle et que ce n'est pas à notre avantage, essayer une nouvelle stratégie
}
