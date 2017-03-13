package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import IA.Brain;
import IA.Utils;
import clientdesarenes.Bot;
import jeu.Joueur;
import jeu.MaitreDuJeu;

/**
 * Classe de test des méthodes de Utils
 * @author alain
 */
public class TestUtils {
	/**
	 * Maitre du jeu utilisé pour les tests
	 */
	static MaitreDuJeu mj;
	
	/**
	 * Plateau utilisé pour les tests
	 */
	static FauxPlateau plateau;
	
	/**
	 * Chaîne de caractère représentant un plateau utilisée pour les tests
	 */
	static String plateauStr;
	
	/**
	 * Bot utilisé pour les tests
	 */
	static Bot joueur;
	
	/**
	 * Faux adversaire utilisé pour les tests
	 */
	static FauxJoueur adversaire;
	
	/**
	 * Initialise le jeu avant de lancer les tests
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		plateauStr =  "+----------------------------------------+\n"
					+ "|######$-    $-############$-    $-######|\n"
					+ "|######        ##        ##        ######|\n"
					+ "|####[]    ####            ####    []####|\n"
					+ "|##      ####  ##        ##  ####      ##|\n"
					+ "|####            $-    $-            ####|\n"
					+ "|##########  @1            @4  ##########|\n"
					+ "|############  ####    ####  ############|\n"
					+ "|$-##$-        ############        $-##$-|\n"
					+ "|  $-      $-################$-      $-  |\n"
					+ "|        ########################        |\n"
					+ "|        ########################        |\n"
					+ "|  $-      $-################$-      $-  |\n"
					+ "|$-##$-        ############        $-##$-|\n"
					+ "|############  ####    ####  ############|\n"
					+ "|##########  @2            @3  ##########|\n"
					+ "|####            $-    $-            ####|\n"
					+ "|##      ####  ##        ##  ####      ##|\n"
					+ "|####[]    ####            ####    []####|\n"
					+ "|######        ##        ##        ######|\n"
					+ "|######$-    $-############$-    $-######|\n"
					+ "+----------------------------------------+";
		plateau = new FauxPlateau(1, plateauStr);
		
		mj = new MaitreDuJeu(plateau);
		joueur = new Bot("test", "test");
		adversaire = new FauxJoueur();
		
		Brain.PLATEAU = plateau;
		Brain.JOUEUR = joueur;
	}

	/**
	 * Teste la méthode getTotalLivre()
	 */
	@Test
	public void testGetTotalLivre() {
		int expected = (plateauStr.length() - plateauStr.replace("$-", "").length()) / 2; // compter les occurrences - http://stackoverflow.com/a/8910767
		assertEquals(expected, Utils.getTotalLivre());
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
	}
}
