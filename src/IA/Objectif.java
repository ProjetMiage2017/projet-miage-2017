package IA;

import java.awt.Point;
import java.util.ArrayList;

import jeu.Plateau;


/**
 * Classe qui defini les  types d'objectifs possible
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */

public class Objectif {
	
	/**
	 * Garde la liste des objectifs
	 */
	public static ArrayList<Objectif> listeObjectif = new ArrayList<Objectif>();
	
	/**
	 *  Type de l'objectif
	 */
	private int type;
	
	/**
	 *  Position de l'objectif
	 */
	private Point position;
	
	
	/**
	 *  Construteur
	 *  @param type un type
	 *  @param position un point
	 */
	public Objectif(int type, Point position) {
		this.type = type;
		this.position = position;
		Objectif.listeObjectif.add(this);
	}
	
	/**
	 *  Methode qui retourne le type de l'objectif
	 *  @return type
	 */
	public int type() {
		return this.type;
	}
	
	
	/**
	 *  Methode qui retourne la position de l'objectif
	 *  @return position
	 */
	public Point position() {
		return this.position;
	}

	
	/**
	 *  Methode qui affiche l'objectif
	 *  @return typeStr
	 */
	public String toString() {
		String typeStr = "Case";
		switch(this.type) {
		case Plateau.CHERCHE_JOUEUR:
			typeStr = "Joueur";
			break;
		case Plateau.CHERCHE_LIT:
			typeStr = "Lit";
			break;
		case Plateau.CHERCHE_LIVRE:
			typeStr = "Livre";
			break;
		default:
			typeStr = "Case";
		}

		return "(" + (int)this.position.getX() + "," + (int)this.position.getY() + ") " + typeStr;
	}
}
