package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import IA.Utils;
import jeu.Joueur;
import jeu.Joueur.Action;

/**
 * Classe de test de la classe Utils qui regroupe les fonctions utiles au Brain
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class TestUtils extends TestBase {

	/**
	 * Compte les livres dans le plateau, sans passer par Utils
	 * @return nombre de livres
	 */
	protected int getBookCount() {
		return (plateauStr.length() - plateauStr.replace("$-", "").length()) / 2; // http://stackoverflow.com/a/8910767
	}
	
	/**
	 * Compte les lits dans le plateau, sans passer par Utils
	 * @return nombre de livres
	 */
	protected int getBedCount() {
		return (plateauStr.length() - plateauStr.replace("[]", "").length()) / 2; // http://stackoverflow.com/a/8910767
	}
	
	/**
	 * Teste la méthode getTotalLivre()
	 */
	@Test
	public void testGetTotalLivre() {
		assertEquals(getBookCount(), Utils.getTotalLivre());
	}
	
	/**
	 * Teste la méthode getPositionForLivres()
	 */
	@Test
	public void testGetPositionForLivres() {
		//TODO
	}
	
	/**
	 * Teste la méthode getTurnsToKill()
	 */
	@Test
	public void testGetTurnsToKill() {
		for (int i = 100; i >= 0; i -= 20) {
			adversaire.setEsprit(i);
			assertEquals(i / 20, Utils.getTurnsToKill(adversaire));
		}
	}
	
	/**
	 * Teste la méthode getDamageGivenBy()
	 */
	@Test
	public void testGetDamageGivenBy() {
		for (int i = 0; i <= 5; i++) {
			adversaire.setEsprit(i * 20);
			assertEquals(((i-1) * 20) + (i-1), Utils.getDamageGivenBy(adversaire, true));
			assertEquals((i * 20) + (i-1), Utils.getDamageGivenBy(adversaire, false));
		}
		
	}
	
	/**
	 * Teste la méthode isEnnemyKillable()
	 */
	@Test
	public void testIsEnnemyKillable() {
		for (int i : new int[]{200, 100, 50, 0}) {
			adversaire.setEsprit(i);
			assertEquals(i < 100, Utils.isEnnemyKillable(adversaire, false));
			assertEquals(i <= 100, Utils.isEnnemyKillable(adversaire, true));
		}
	}
	
	/**
	 * Teste la méthode isEnnemyWorthToKill()
	 */
	@Test
	public void testIsEnnemyWorthToKill() {
		adversaire.setEsprit(200);
		assertFalse(Utils.isEnnemyWorthToKill(adversaire, false));
		assertFalse(Utils.isEnnemyWorthToKill(adversaire, true));
		
		adversaire.setEsprit(100);
		assertFalse(Utils.isEnnemyWorthToKill(adversaire, false));
		assertTrue(Utils.isEnnemyWorthToKill(adversaire, true));
	}
	
	/**
	 * Teste la méthode getJoueurs()
	 */
	@Test
	public void testGetJoueurs() {
		Joueur[] j = Utils.getJoueurs(plateau);
		for (int i = 0; i < 3; i++) {
			assertEquals(i+1, j[i].donneCouleurNumerique());
		}
		
		assertEquals(adversaire, j[0]);
		assertEquals(j3, j[1]);
		assertEquals(j4, j[2]);
	}
	
	/**
	 * Teste la méthode getLivres()
	 */
	@Test
	public void testGetLivres() {
		assertEquals(getBookCount(), Utils.getLivres(plateau).length);
	}
	
	/**
	 * Teste la méthode getLits()
	 */
	@Test
	public void testGetLits() {
		assertEquals(getBedCount(), Utils.getLits(plateau).length);
	}
	
	/**
	 * Teste la méthode pointLePlusProche()
	 */
	@Test
	public void testPointLePlusProche() {
		Point[] points = new Point[]{
				new Point(5, 0), new Point(6, 0), new Point(4, 1)
		};
		
		assertEquals(new Point(5, 0), Utils.pointLePlusProche(new Point(5, 1), points));
	}
	
	/**
	 * Teste la méthode getDistance()
	 */
	@Test
	public void testGetDistance() {
		assertEquals(10, Utils.getDistance(new Point(5, 5), new Point(10, 10)));
		assertEquals(10, Utils.getDistance(new Point(0, 0), new Point(0, 10)));
		assertEquals(10, Utils.getDistance(new Point(0, 0), new Point(10, 0)));
	}
	
	/**
	 * Teste la méthode pointCardinal()
	 */
	@Test
	public void testPointCardinal() {
		assertEquals(Action.GAUCHE, Utils.pointCardinal(new Point (2,2), new Point (0, 2)));
		assertEquals(Action.DROITE, Utils.pointCardinal(new Point (2,2), new Point (5, 2)));
		assertEquals(Action.HAUT, Utils.pointCardinal(new Point (2,2), new Point (2, 0)));
		assertEquals(Action.BAS, Utils.pointCardinal(new Point (2,2), new Point (2, 5)));
		assertEquals(Action.RIEN, Utils.pointCardinal(new Point (2,2), new Point (2, 2)));
		assertEquals(Action.RIEN, Utils.pointCardinal(new Point (2,2), new Point (3, 3)));
	}
	
	/**
	 * Teste la méthode getDistanceWithObstacles()
	 */
	@Test
	public void testGetDistanceWithObstacles() {
		assertEquals(2, Utils.getDistanceWithObstacles(new Point(4,0), new Point(6,0)));
		assertEquals(3, Utils.getDistanceWithObstacles(new Point(4,0), new Point(6,1)));
		assertEquals(9, Utils.getDistanceWithObstacles(new Point(4,0), new Point(6,5)));
		
		joueur.setPosition(new Point(1, 8));
		assertEquals(1, Utils.getDistanceWithObstacles(joueur.donnePosition(), adversaire.donnePosition()));
	}
	
	/**
	 * Test la méthode getJoueurLePlusProche()
	 */
	@Test
	public void testGetJoueurLePlusProche() {
		joueur.setPosition(new Point(4, 1));
		assertEquals(adversaire, Utils.getJoueurLePlusProche());
		joueur.setPosition(new Point(2, 12));
		assertEquals(j4, Utils.getJoueurLePlusProche());
	}
	
	@Test
	public void testGetLivreLePlusProche() {
		joueur.setPosition(new Point(4, 0));
		assertEquals(new Point(3, 0), Utils.getLivreLePlusProche());
	}
}
