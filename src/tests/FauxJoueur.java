package tests;

import java.awt.Point;

import jeu.Joueur;

/**
 * Classe permettant de creer un faux jouer pour les testUnitaires
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */

public class FauxJoueur extends Joueur {

	/**
	 *  Nombre de point d'esprit
	 */
	int esprit = 100;
	
	/**
	 *  Coleur du jouer
	 */
	int couleur = 1;
	int livres = 0;
	String nom = "Faux joueur";
	
	
	/**
	 *  Modificateur de la variable esprit
	 *   @param e esprit
	 */
	public void setEsprit(int e) {
		this.esprit = e;
	}
	
	
	/**
	 *  Acc�sseur de la variable esprit
	 *  @return esprit
	 */
	@Override
	public int donneEsprit() {
		return this.esprit;
	}
	
	
	/**
	 *  Modificateur de la variable couleur
	 *  @param c couleur
	 */
	public void setCouleur(int c) {
		this.couleur = c;
	}
	
	
	/**
	 *  Acc�sseur de la variable couleur
	 *  @return couleur
	 */
	@Override
	public int donneCouleurNumerique() {
		return this.couleur;
	}
	
	@Override
	public Point donnePosition() {
		return new Point(2, 7 + this.donneCouleurNumerique());
	}
	
	@Override
	public boolean equals(Object obj) {
		Joueur j = (Joueur)obj;
		return j.donnePosition().equals(this.donnePosition()) && j.donneCouleurNumerique() == this.donneCouleurNumerique();
	}
}
