package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class TestBrain extends TestBase {

	/**
	 * Test de comportement ; peu de livres et adversaire proche avec peu d'esprit
	 */
	@Test
	public void testLowBookKillableOpponent() {
		joueur.setLivres(1);
		adversaire.setEsprit(12);
		
		joueur.setPosition(new Point(4, 1));
		
		//brain.run();
		//System.out.println(brain.objectif());
	}

}
