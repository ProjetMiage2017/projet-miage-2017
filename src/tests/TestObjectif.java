package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import IA.Objectif;
import jeu.Plateau;

/**
 * Tests de la classe Objectif
 * @TODO
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class TestObjectif {

	private static int TODO = 5;
	/**
	 * Réinitialise la liste d'objectifs au début et à la fin de chaque test
	 */
	@Before	@After
	public void setUpAndTearDown() {
		Objectif.listeObjectif.clear();
	}
	
	/**
	 * Teste la construction d'un objectif
	 */
	@Test
	public void testObjectif() {
		Objectif o = new Objectif(Plateau.CHERCHE_JOUEUR, new Point(5, 5),TODO);
		Objectif lastAdded = Objectif.listeObjectif.get(Objectif.listeObjectif.size() - 1);
		assertEquals(o, lastAdded);
		assertEquals(Plateau.CHERCHE_JOUEUR, lastAdded.type());
		assertEquals(new Point(5, 5), lastAdded.position());
	}
	
	/**
	 * Teste la méthode toString()
	 */
	@Test
	public void testToString() {
		assertEquals("(5,5) Joueur", new Objectif(Plateau.CHERCHE_JOUEUR, new Point(5, 5),TODO).toString());
		assertEquals("(15,5) Livre", new Objectif(Plateau.CHERCHE_LIVRE, new Point(15, 5),TODO).toString());
		assertEquals("(18,55) Lit", new Objectif(Plateau.CHERCHE_LIT, new Point(18, 55),TODO).toString());
		assertEquals("(1,1) Case", new Objectif(0, new Point(1, 1),TODO).toString());
	}

}
