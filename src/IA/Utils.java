package IA;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.*;
import IA.Brain;

/*
 * Classe regroupant les fonctions utiles au Brain
 * @author Jeremy Rossignol / Alain Drillon / Abdoulbak Mohamedfouad
 */
public class Utils {
	
	/*
	 * Fonction qui calcule le nombre de livres total
	 * @return nb livres
	 */
	public static int getTotalLivre(){
		HashMap<Integer, ArrayList<Point> > hmLivres= Brain.PLATEAU.cherche(new Point(Brain.PLATEAU.donneTaille()/2,Brain.PLATEAU.donneTaille()/2), Brain.PLATEAU.donneTaille()/2, Brain.PLATEAU.CHERCHE_LIVRE);
		return hmLivres.get(2).size();
	}
	
	/*
	 * Fonction qui calcule notre position en terme de livre par rapport aux autres joueurs
	 * @return position
	 * @TODO
	 * @TODO delete ?
	 */
	public static int getPositionForLivres(){
		int position = 0 ;
		return position;
	}
	
	/*
	 * Fonction qui calcule le nombre de tours qu'on met pour tuer un ennemi
	 * @param J l'ennemi
	 * @return nb tours
	 */
	public int getTurnsToKill(Joueur J){
		return J.donneEsprit()/20;
	}
	
	/*
	 * Fonction qui determine les degats reçus suite à un combat
	 * @param J l'ennemi
	 * @return degatsRecus
	 */
	public int getDamageGivenBy(Joueur J, boolean avantage){
		// nb tour (-1 si on a l avantage) * degats + nb de PE que peut nous couter les actions
		if(avantage)
			return (getTurnsToKill(J) -1)*20 + getTurnsToKill(J)-1;
		else
			return getTurnsToKill(J)*20 + getTurnsToKill(J)-1;
	}
	
	/*
	 * Fonction qui determine si on assez de PE pour tuer l'ennemi si on engage ou pas le combat
	 * @param J l'ennemi
	 * @param avantage si on engage le combat
	 * @return isKillable
	 */
	public boolean isEnnemyKillable(Joueur J, boolean avantage){
		return getDamageGivenBy(J, avantage) < Brain.JOUEUR.donneEsprit();	
	}
	
	/*
	 * Fonction qui verifie si c'est rentable de tuer un ennemi
	 * @param J l'ennemi
	 * @return isWorth
	 * @TODO
	 */
	public boolean isEnnemyWorthToKill(Joueur J, boolean avantage){
		if(isEnnemyKillable(J, avantage)){
			if(avantage){
				return Brain.PLATEAU.nombreDeLivresJoueur(J.donneCouleurNumerique())*20< getDamageGivenBy(J, avantage);
			}
			else{
				// en admettant qu on met au moins 15 tours a l attraper
				return Brain.PLATEAU.nombreDeLivresJoueur(J.donneCouleurNumerique())*20 + 15 < getDamageGivenBy(J, avantage);
			}
		}
		else{
			return false;
		}
	}
	
	
	//@TODO fonction prevision perte d'energie selon l'objectif

}
