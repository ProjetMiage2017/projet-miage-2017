package tests;

import java.awt.Point;

import jeu.Joueur;

public class FauxJoueur extends Joueur {
	int esprit = 100;
	int couleur = 1;
	int livres = 0;
	String nom = "Faux joueur";
	
	public void setEsprit(int e) {
		this.esprit = e;
	}
	
	@Override
	public int donneEsprit() {
		return this.esprit;
	}
	
	public void setCouleur(int c) {
		this.couleur = c;
	}
	
	@Override
	public int donneCouleurNumerique() {
		return this.couleur;
	}
	
	@Override
	public Point donnePosition() {
		return new Point(2, 7 + this.donneCouleurNumerique());
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		Joueur j = (Joueur)obj;
		return j.donnePosition().equals(this.donnePosition()) && j.donneCouleurNumerique() == this.donneCouleurNumerique();
	}
}
