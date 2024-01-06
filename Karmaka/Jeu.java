package Karmaka;
import java.util.*;
public class Jeu {
	// Liste de cartes représentant la fosse
	private ArrayList<Carte> source;

	// Liste de cartes dans la fosse
	private ArrayList<Carte> fosse;
	   
	private Joueur joueur1;
	private Joueur joueur2;

	public Jeu() {
		this.source = new ArrayList<>();
	    this.fosse = new ArrayList<>();
	    this.joueur1 = new Joueur();
	    this.joueur2 = new Joueur();

	    // initialisation de la source
	    initialiserSource();
	    
	    // distribution
	    distribuer();
    }
	
	// Distribuer les mains des deux joueurs
	public void distribuer() {
		ArrayList<Carte> saMain = new ArrayList<Carte>();
		
		// Distribution joueur2
		for(int i = 0; i < 4; i++) {
			saMain.add(this.source.get(this.source.size() - 1));
			this.source.remove(this.source.size() - 1);
		}
		joueur1.setMain(saMain);
		
		saMain.clear();
		
		// Distribution joueur2
		for(int i = 0; i < 4; i++) {
			saMain.add(this.source.get(this.source.size() - 1));
			this.source.remove(this.source.size() - 1);
		}
		joueur2.setMain(saMain);
	}

	public void ajouterCarteSource(Carte carte) {
	        this.source.add(carte);
	    }
	public void ajouterCarteFosse(Carte carte) {
	        this.fosse.add(carte);
	    }
	
	// initialisation de la source
	public void initialiserSource() {
		Carte carte;
	    
	    // Transmigration
	    carte = new Carte("Transmigration", Couleur.BLEU, 1);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	    // Coup d Oeil
	    carte = new Carte("Coup d Oeil", Couleur.BLEU, 1);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	    // Destinee
	    carte = new Carte("Destinee", Couleur.BLEU, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Reves Brises
	    carte = new Carte("Reves Brises", Couleur.BLEU, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Deni
	    carte = new Carte("Deni", Couleur.BLEU, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Duperie
	    carte = new Carte("Duperie", Couleur.BLEU, 3);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Vol
	    carte = new Carte("Vol", Couleur.BLEU, 3);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Lendemain
	    carte = new Carte("Lendemain", Couleur.VERT, 1);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Recyclage
	    carte = new Carte("Recyclage", Couleur.VERT, 1);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Sauvetage
	    carte = new Carte("Sauvetage", Couleur.VERT, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Longevite
	    carte = new Carte("Longevite", Couleur.VERT, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Semis
	    carte = new Carte("Semis", Couleur.VERT, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Voyage
	    carte = new Carte("Voyage", Couleur.VERT, 3);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Jubile
	    carte = new Carte("Jubile", Couleur.VERT, 3);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	    // Panique
	    carte = new Carte("Panique", Couleur.ROUGE, 1);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Dernier Souffle
	    carte = new Carte("Dernier Souffle", Couleur.ROUGE, 1);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Crise
	    carte = new Carte("Crise", Couleur.ROUGE, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Roulette
	    carte = new Carte("Roulette", Couleur.ROUGE, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Fournaise
	    carte = new Carte("Fournaise", Couleur.ROUGE, 2);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Vengeance
	    carte = new Carte("Vengeance", Couleur.ROUGE, 3);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Bassesse
	    carte = new Carte("Bassesse", Couleur.ROUGE, 3);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Incarnation
	    carte = new Carte("Incarnation", Couleur.MOSAIQUE, 1);
	    for(int i = 0; i < 5; i++) {
	    	this.source.add(carte);
	    }
	 	// Mimetisme
	    carte = new Carte("Mimetisme", Couleur.MOSAIQUE, 2);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	    
	    // Mélanger la source
	    Collections.shuffle(source);
	}
	
	public static void main(String[] args) {
	        Jeu monJeu = new Jeu();
	    }

}