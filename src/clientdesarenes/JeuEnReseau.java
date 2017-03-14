package clientdesarenes;

import gui.FenetreDeJeu;
import reseau.ClientReseau;


public class JeuEnReseau {

    public static void main(String[] args) {
     
    	ClientReseau cli = new ClientReseau(new Bot("Salameche", "cli5_PASS5"));
    	new FenetreDeJeu( cli).setVisible(true);
    	while( cli.donneEtat() != ClientReseau.ETAT_QUITTER) {
	        if ( cli.donneEtat() == ClientReseau.ETAT_NON_CONNECTE) {
	            System.out.println("Client "+cli.donneNomJoueur()+" : Essai de connexion au serveur...");
	            cli.connect("127.0.0.1", 10000);
	            try { Thread.sleep(4000); } catch (InterruptedException ex) { }
	        }
	        try { Thread.sleep(1000); } catch (InterruptedException ex) { }
	    }
	    System.out.println("Client "+cli.donneNomJoueur()+" : Au revoir !");      
    }  
}
