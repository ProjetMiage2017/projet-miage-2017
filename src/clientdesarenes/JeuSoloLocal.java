package clientdesarenes;

import gui.FenetreDeJeu;
import jeu.MaitreDuJeu;
import jeu.Plateau;


public class JeuSoloLocal {
    
    public static void main(String[] args) {
        Plateau p;
        
        //tour 190 : rencontre entre carapuce et bulbizarre
        p = new Plateau(1200, MaitreDuJeu.PLATEAU_PAR_DEFAUT);
        //p = Plateau.generePlateauAleatoire( 1200, 10, 4, 8, 7);
        
        MaitreDuJeu jeu = new MaitreDuJeu(p);
        
        jeu.metJoueurEnPosition(0, new Bot("Carapuce", "s")); //
        jeu.metJoueurEnPosition(3, new Bot("Bulbizarre", "cli4_PASS4"));
        
        //jeu.metJoueurEnPosition(1, new jeu.Joueur("Rouge"));
        //jeu.metJoueurEnPosition(2, new jeu.Joueur("Jaune"));
        //jeu.metJoueurEnPosition(1, new Bot("Rouge", "cli4_PASS3")); //
        //jeu.metJoueurEnPosition(2, new Bot("Jaune", "cli4_PASS2"));
       
        FenetreDeJeu f = new FenetreDeJeu(jeu, true);
        f.setMouseClickListener((int x, int y, int bt) -> {
            System.out.println("On a cliqué sur la cellule " + x + "," + y);
        });
        
        java.awt.EventQueue.invokeLater(() -> {
            f.setVisible(true);
        });  
    }
}
