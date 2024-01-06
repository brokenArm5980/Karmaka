package Karmaka;
import java.util.*;
public class Jeu {
	
	private List<Carte> source;

	    // Liste de cartes dans la fosse
	private List<Carte> fosse;
	   
	private Joueur joueur1;
	private Joueur joueur2;

	public Jeu() {
		this.source = new ArrayList<>();
	    this.fosse = new ArrayList<>();
	    this.joueur1 = new Joueur();
	    this.joueur2 = new Joueur();
	    }

	public void ajouterCarteSource(Carte carte) {
	        this.source.add(carte);
	    }
	public void ajouterCarteFosse(Carte carte) {
	        this.fosse.add(carte);
	    }
	
	public static void main(String[] args) {
	        Jeu monJeu = new Jeu();

	    }

}
