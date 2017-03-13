package tests;

import jeu.Joueur;

public class FauxJoueur extends Joueur {
	int esprit = 100;
	int couleur = 1;
	
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
}
