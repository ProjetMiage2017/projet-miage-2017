package IA;

import java.awt.Point;
import java.util.ArrayList;

import jeu.Plateau;

public class Objectif {
	public static ArrayList<Objectif> listeObjectif = new ArrayList<Objectif>();
	
	private int type;
	
	private Point position;
	
	public Objectif(int type, Point position) {
		this.type = type;
		this.position = position;
		this.listeObjectif.add(this);
	}
	
	public int type() {
		return this.type;
	}
	
	public Point position() {
		return this.position;
	}

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

		return this.position + " " + typeStr;
	}
}
