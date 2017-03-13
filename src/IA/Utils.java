package IA;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.*;
import jeu.Joueur.Action;
import jeu.astar.Node;
import IA.Brain;

/*
 * Classe regroupant les fonctions utiles au Brain
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class Utils {
	
	/**
	 * Fonction qui calcule le nombre de livres total
	 * @param J
	 * @return nb livres
	 */
	public static int getTotalLivre(){
		HashMap<Integer, ArrayList<Point> > hmLivres= Brain.PLATEAU.cherche(new Point(Brain.PLATEAU.donneTaille()/2,Brain.PLATEAU.donneTaille()/2), Brain.PLATEAU.donneTaille()/2, Plateau.CHERCHE_LIVRE);
		return hmLivres.get(2).size();
	}
	
	/**
	 * Fonction qui calcule notre position en terme de livre par rapport aux autres joueurs
	 * @param J
	 * @return position
	 */
	public static int getPositionForLivres(){
		int position = 0 ;
		//@TODO
		return position;
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
	 * Renvoie le point le plus proche par rapport à une position
	 * @param position position par rapport à laquelle mesurer les distances
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
	 * @param depart point de départ
	 * @param arrivee point d'arrivée
	 * @return distance
	 */
	public static int getDistance(Point depart, Point arrivee) {
		return (int) Math.abs(depart.getX() - arrivee.getX() + depart.getY() - arrivee.getY());
	}
	
	/**
	 * Détermine la position relative d'un point par rapport à un autre
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

	//@TODO fonction prevision perte d'energie selon l'objectif

}
