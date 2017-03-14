package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import jeu.Plateau;

/**
 * Classe de test de la classe Brain qui permet de réaliser le choix de l'action
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class TestBrain extends TestBase {

	/**
	 * Test de comportement ; peu de livres et adversaire proche avec peu d'esprit
	 */
	@Test
	public void testLowBookCountKillableOpponent() {
		joueur.setLivres(0);
		adversaire.setEsprit(12);
		joueur.setPosition(new Point(4, 0));
		brain.run();
		
		assertEquals(new Point(3, 0), brain.objectif().position());
		assertEquals(Plateau.CHERCHE_LIVRE, brain.objectif().type());
	}

	/**
	 * Test de comportement : peu de livres, pas d'adversaire proche et un livre proche
	 */
	@Test
	public void testLowBookCountBookAvailable() {
		joueur.setLivres(0);
		joueur.setEsprit(100);
		adversaire.setEsprit(100);
		joueur.setPosition(new Point(3, 3));
		brain.run();
		
		assertEquals(new Point(3, 0), brain.objectif().position());
		assertEquals(Plateau.CHERCHE_LIVRE, brain.objectif().type());
	}
	
	/**
	 * Test de comportement : peu de livres, pas d'adversaire proche, livre trop loin
	 */
	@Test
	public void testLowBookCountBookNotAvailable() {
		joueur.setLivres(0);
		joueur.setEsprit(0);
		adversaire.setEsprit(100);
		joueur.setPosition(new Point(3, 3));
		brain.run();
		
		assertEquals(new Point(2, 2), brain.objectif().position());
		assertEquals(Plateau.CHERCHE_LIT, brain.objectif().type());
	}
}
