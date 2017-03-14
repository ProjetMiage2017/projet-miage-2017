package tests;

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
	
	
	/**
	 *  Modificateur de la variable esprit
	 *   @param e esprit
	 */
	public void setEsprit(int e) {
		this.esprit = e;
	}
	
	
	/**
	 *  Accésseur de la variable esprit
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
	 *  Accésseur de la variable couleur
	 *  @return couleur
	 */
	@Override
	public int donneCouleurNumerique() {
		return this.couleur;
	}
}
