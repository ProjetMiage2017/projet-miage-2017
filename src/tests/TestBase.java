package tests;

import java.awt.Point;

import org.junit.BeforeClass;

import IA.Brain;
import clientdesarenes.Bot;
import jeu.MaitreDuJeu;

/**
 * Classe Test de Base
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class TestBase {
	/**
	 * Maitre du jeu utilis� pour les tests
	 */
	protected  static MaitreDuJeu mj;
	
	/**
	 * Plateau utilis� pour les tests
	 */
	protected  static FauxPlateau plateau;
	
	/**
	 * cha�ne de caract�re repr�sentant un plateau utilis� pour les tests
	 */
	protected  static String plateauStr;
	
	/**
	 * Bot utilis� pour les tests
	 */
	protected  static FauxBot joueur;
	
	/**
	 * Faux adversaire utilis� pour les tests
	 */
	protected  static FauxJoueur adversaire;
	
	/**
	 * Faux adversaire utilis� pour les tests
	 */
	protected  static FauxJoueur j3;
	
	/**
	 * Faux adversaire utilisé pour les tests
	 */
	protected  static FauxJoueur j4;
	
	/**
	 * IA utilisée pour les tests
	 */
	protected  static Brain brain;
	
	/**
	 * Initialise le jeu avant de lancer les tests
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		plateauStr = MaitreDuJeu.PLATEAU_PAR_DEFAUT;
		plateau = new FauxPlateau(1, plateauStr);
		
		mj = new MaitreDuJeu(plateau);
		joueur = new FauxBot("test", "test");
		adversaire = new FauxJoueur();
		j3 = new FauxJoueur();
		j4 = new FauxJoueur();
		
		mj.metJoueurEnPosition(0, joueur);
		mj.metJoueurEnPosition(1, adversaire);
		mj.metJoueurEnPosition(2, j3);
		mj.metJoueurEnPosition(3, j4);
		
		adversaire.setCouleur(1);
		j3.setCouleur(2);
		j4.setCouleur(3);
		
		brain = new Brain();
		brain.setPlateauEtJoueur(plateau, joueur);
	}
}
