package Karmaka;
import java.util.*;
import java.io.*;
/**
 * La classe Jeu represente le jeu Karmaka, un jeu de cartes ou les joueurs
 * s'affrontent pour gagner des points d'illumination et transcender.
 * Elle gere la source de cartes, la fosse, les joueurs, le tour, et les actions du jeu.
 * 
 * @author Briffa_Halliez
 * @version 1.0
 */
public class Jeu implements Serializable {
	// Liste de cartes representant la fosse
	private ArrayList<Carte> source;
	// Liste de cartes dans la fosse
	private ArrayList<Carte> fosse;
	   
	private Joueur joueur1;
	private Joueur joueur2;
	
	private int tour = 1;
	private static final long serialVersionUID = 1L;
    /**
     * Constructeur par defaut de la classe Jeu.
     * Initialise les listes de cartes source et fosse.
     */
	public Jeu() {
		this.source = new ArrayList<Carte>();
	    this.fosse = new ArrayList<Carte>();
    }
	
    /**
     * Permet de piocher une carte dans la source.
     * Si la source est vide, elle est completee.
     * 
     * @return La carte piochee.
     */
	public Carte piocher() {
		if(this.source.size() == 0) {
			this.completerSource();
		}
		Carte carte = this.source.get(this.source.size() - 1);
		this.source.remove(this.source.size() - 1);
		return carte;
	}
	
    /**
     * Distribue les mains des deux joueurs a partir de la source.
     */
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
		
		// Distribuer les piles
		ArrayList<Carte> pile1 = new ArrayList<Carte>();
        ArrayList<Carte> pile2 = new ArrayList<Carte>();

        for(int i = 0; i < 2; i++) {
            pile1.add(this.source.get(this.source.size() - 1));
            this.source.remove(this.source.size() - 1);
        }

        joueur1.setPile(pile1);
        for(int i = 0; i < 2; i++) {
            pile2.add(this.source.get(this.source.size() - 1));
            this.source.remove(this.source.size() - 1);
        }
        joueur2.setPile(pile2);
	}
    /**
     * Ajoute une carte a la source.
     * 
     * @param carte La carte a ajouter.
     */
	public void ajouterCarteSource(Carte carte) {
	        this.source.add(carte);
	    }
    /**
     * Ajoute une carte a la fosse.
     * 
     * @param carte La carte a ajouter.
     */
	public void ajouterCarteFosse(Carte carte) {
	        this.fosse.add(carte);
	    }
    /**
     * Retire une carte de la source a l'index specifie.
     * 
     * @param index L'index de la carte a retirer.
     */
	public void retirerCarteSource(int index) {
        this.source.remove(index);
    }
    /**
     * Retire une carte de la fosse a l'index specifie.
     * 
     * @param index L'index de la carte a retirer.
     */
	public void retirerCarteFosse(int index) {
        this.fosse.remove(index);
    }
	
    /**
     * Initialise la source avec toutes les cartes du jeu Karmaka et la melange.
     */
	public void initialiserSource() {
		Carte carte;
	    
	    // Transmigration
	    carte = new Carte("Transmigration", Couleur.BLEU, 1, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	    // Coup d Oeil
	    carte = new Carte("Coup d Oeil", Couleur.BLEU, 1, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	    // Destinee
	    carte = new Carte("Destinee", Couleur.BLEU, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Reves Brises
	    carte = new Carte("Reves Brises", Couleur.BLEU, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Deni
	    carte = new Carte("Deni", Couleur.BLEU, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Duperie
	    carte = new Carte("Duperie", Couleur.BLEU, 3, this);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Vol
	    carte = new Carte("Vol", Couleur.BLEU, 3, this);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Lendemain
	    carte = new Carte("Lendemain", Couleur.VERT, 1, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Recyclage
	    carte = new Carte("Recyclage", Couleur.VERT, 1, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Sauvetage
	    carte = new Carte("Sauvetage", Couleur.VERT, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Longevite
	    carte = new Carte("Longevite", Couleur.VERT, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Semis
	    carte = new Carte("Semis", Couleur.VERT, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Voyage
	    carte = new Carte("Voyage", Couleur.VERT, 3, this);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Jubile
	    carte = new Carte("Jubile", Couleur.VERT, 3, this);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	    // Panique
	    carte = new Carte("Panique", Couleur.ROUGE, 1, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Dernier Souffle
	    carte = new Carte("Dernier Souffle", Couleur.ROUGE, 1, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Crise
	    carte = new Carte("Crise", Couleur.ROUGE, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Roulette
	    carte = new Carte("Roulette", Couleur.ROUGE, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Fournaise
	    carte = new Carte("Fournaise", Couleur.ROUGE, 2, this);
	    for(int i = 0; i < 3; i++) {
	    	this.source.add(carte);
	    }
	 	// Vengeance
	    carte = new Carte("Vengeance", Couleur.ROUGE, 3, this);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Bassesse
	    carte = new Carte("Bassesse", Couleur.ROUGE, 3, this);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	 	// Incarnation
	    carte = new Carte("Incarnation", Couleur.MOSAIQUE, 1, this);
	    for(int i = 0; i < 5; i++) {
	    	this.source.add(carte);
	    }
	 	// Mimetisme
	    carte = new Carte("Mimetisme", Couleur.MOSAIQUE, 2, this);
	    for(int i = 0; i < 2; i++) {
	    	this.source.add(carte);
	    }
	    
	    // Melanger la source
	    Collections.shuffle(this.source);
	}
	
    /**
     * Change le joueur actif pour le tour suivant.
     */
	public void changerTour()
	{
		if(tour == 1) {
			tour = 2;
		}
		else if(tour == 2) {
			tour = 1;
		}
	}
	
    /**
     * Retourne le joueur actuel qui doit jouer.
     * 
     * @return Le joueur actuel.
     */
	public Joueur selectionnerJoueurActuel()
	{
		if(this.tour == 1) {
			return this.joueur1;
		}
		return this.joueur2;
	}
	
    /**
     * Retourne le joueur qui ne doit pas jouer pendant ce tour, celui qui subit les attaques.
     * 
     * @return Le joueur qui ne joue pas.
     */
	public Joueur selectionnerJoueurVictime()
	{
		if(this.tour == 1) {
			return this.joueur2;
		}
		return this.joueur1;
	}
	
    /**
     * Deroulement d'un tour du joueur humain.
     * 
     * @param joueurActuel Le joueur actuel.
     * @param joueur2 Le joueur adverse.
     */
	public void jouer(Joueur joueurActuel, Joueur joueur2)
	{
		// Le joueur piocher une carte de sa pile
		if(joueurActuel.getPile().size() > 0) {
	        Carte carte = joueurActuel.getPile().get(joueurActuel.getPile().size() - 1); // Prendre la carte de la Pile
	        joueurActuel.ajouterMain(carte); // Ajouter cette carte a la Main du joueur
	        joueurActuel.retirerPile(joueurActuel.getPile().size() - 1); // Retirer la carte de la Pile
	        int a = 0;
	        for (int i=0; i<joueurActuel.getPile().size(); i++) {
	        	a = a + 1;
	        }
	        System.out.println(joueurActuel.getNom() + " : Vous avez pioche une carte de votre pile: " + carte.getNom());
		}
		
		System.out.println("Voici votre main :");
		for(int i=0; i<joueurActuel.getMain().size(); i++) {
			System.out.println(joueurActuel.getMain().get(i).getNom());
		}
		
		// Le joueur joue une carte ou passe
		System.out.println("Vous pouvez :");
		System.out.println("- jouer une carte pour ses points (tapez 1)");
		System.out.println("- jouer une carte pour son pouvoir (tapez 2)");
		System.out.println("- jouer une carte pour votre vie future (tapez 3)");
		if(joueurActuel.getPile().size() > 0) {
			System.out.println("- passer votre tour (tapez 4)");
		}
		
		Scanner scanner = new Scanner(System.in);
		int choix = scanner.nextInt();
		
		
		if(choix == 1) {
			System.out.println("Choisissez une carte a jouer pour ses points :");
			
			ArrayList<Carte> main = joueurActuel.getMain();
			
			for(int i=0; i<main.size(); i++) {
				System.out.println(main.get(i).getNom() + ": tapez " + i);
			}
			scanner = new Scanner(System.in);
			int index = scanner.nextInt();
	        joueurActuel.jouerPoint(index); // Jouer la carte
		}
		else if(choix == 2) {
			System.out.println("Choisissez une carte a jouer pour son pouvoir :");
			
			ArrayList<Carte> main = joueurActuel.getMain();
			
			for(int i=0; i<main.size(); i++) {
				System.out.println(main.get(i).getNom() + ": tapez " + i);
			}
			scanner = new Scanner(System.in);
			int index = scanner.nextInt();
			joueurActuel.jouerPouvoir(index, joueurActuel, joueur2); // Jouer la carte
		}
		else if(choix == 3) {
			System.out.println("Choisissez une carte a jouer pour votre vie future :");
			
			ArrayList<Carte> main = joueurActuel.getMain();
			
			for(int i=0; i<main.size(); i++) {
				System.out.println(main.get(i).getNom() + ": tapez " + i);
			}
			scanner = new Scanner(System.in);
			int index = scanner.nextInt();
			joueurActuel.jouerFutur(index); // Jouer la carte
		}
	}
	
    /**
     * Deroulement d'un tour du bot (joueur automatique).
     * 
     * @param joueurActuel Le joueur actuel (bot).
     * @param joueur2 Le joueur adverse.
     */
		public void jouerBot(Joueur joueurActuel, Joueur joueur2)
		{
			// Le joueur piocher une carte de sa pile
			ArrayList<Carte> pile = joueurActuel.getPile();
			if(pile.size() > 0) {
		        Carte carte = pile.get(pile.size() - 1); // Prendre la carte de la Pile
		        joueurActuel.ajouterMain(carte); // Ajouter cette carte a la Main du joueur
		        joueurActuel.retirerPile(pile.size() - 1); // Retirer la carte de la Pile
		        System.out.println(joueurActuel.getNom() + " : Vous avez pioche une carte de votre pile: " + carte.getNom());
			}
			
			System.out.println("Voici votre main :");
			for(int i=0; i<joueurActuel.getMain().size(); i++) {
				System.out.println(joueurActuel.getMain().get(i).getNom());
			}
			
			// Le joueur joue une carte ou passe
			System.out.println("Vous pouvez :");
			System.out.println("- jouer une carte pour ses points (tapez 1)");
			System.out.println("- jouer une carte pour son pouvoir (tapez 2)");
			System.out.println("- jouer une carte pour votre vie future (tapez 3)");
			if(joueurActuel.getPile().size() > 0) {
				System.out.println("- passer votre tour (tapez 4)");
			}
			
			double al = Math.random() * 4 + 1;
			int choix = (int) al;
			System.out.println("Le bot a choisi : " + choix);
			
			
			if(choix == 1) {
				System.out.println("Choisissez une carte a jouer pour ses points :");
				
				ArrayList<Carte> main = joueurActuel.getMain();
				
				for(int i=0; i<main.size(); i++) {
					System.out.println(main.get(i).getNom() + ": tapez " + i);
				}
				al = Math.random() * main.size();
				int index = (int) al;
				System.out.println("Le bot a choisi : " + index);
		        joueurActuel.jouerPoint(index); // Jouer la carte
			}
			else if(choix == 2) {
				System.out.println("Choisissez une carte a jouer pour son pouvoir :");
				
				ArrayList<Carte> main = joueurActuel.getMain();
				
				for(int i=0; i<main.size(); i++) {
					System.out.println(main.get(i).getNom() + ": tapez " + i);
				}
				al = Math.random() * main.size();
				int index = (int) al;
				System.out.println("Le bot a choisi : " + index);
				joueurActuel.jouerPouvoir(index, joueurActuel, joueur2); // Jouer la carte
			}
			else if(choix == 3) {
				System.out.println("Choisissez une carte a jouer pour votre vie future :");
				
				ArrayList<Carte> main = joueurActuel.getMain();
				
				for(int i=0; i<main.size(); i++) {
					System.out.println(main.get(i).getNom() + ": tapez " + i);
				}
				al = Math.random() * main.size();
				int index = (int) al;
				System.out.println("Le bot a choisi : " + index);
				joueurActuel.jouerFutur(index); // Jouer la carte
			}
		}
	    /**
	     * Complete la source avec une partie de la fosse.
	     */
	public void completerSource() {
		ArrayList<Carte> temp = new ArrayList<Carte>();
		for(int i=0; i<this.fosse.size() - 3; i++) {
			temp.add(this.fosse.get(i));
			this.fosse.remove(i);
		}
		Collections.shuffle(temp);
		for(int i=0; i<temp.size(); i++) {
			this.source.add(temp.get(i));
		}
	}
	
    /**
     * Retourne la liste de cartes de la source.
     * 
     * @return La liste de cartes source.
     */
	public ArrayList<Carte> getSource(){
		return this.source;
	}
    /**
     * Definit la liste de cartes source.
     * 
     * @param source La nouvelle liste de cartes source.
     */
	public void setSource(ArrayList<Carte> source) {
		this.source = source;
	}
    /**
     * Retourne la liste de cartes de la fosse.
     * 
     * @return La liste de cartes fosse.
     */
	public ArrayList<Carte> getFosse(){
		return this.fosse;
	}
    /**
     * Definit la liste de cartes fosse.
     * 
     * @param fosse La nouvelle liste de cartes fosse.
     */
	public void setFosse(ArrayList<Carte> fosse) {
		this.fosse = fosse;
	}
    /**
     * Sauvegarde l'etat actuel du jeu dans un fichier objet.
     * 
     * @param jeu Le jeu a sauvegarder.
     * @param nom Le nom du fichier de sauvegarde.
     */
	public static void sauvegarder(Jeu jeu, String nom)
	{
		try {
			FileOutputStream fos = new FileOutputStream(nom + ".obj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(jeu);
			fos.close();
		} catch (IOException e) {e.printStackTrace();}
	}
    /**
     * Charge un jeu a partir d'un fichier objet.
     * 
     * @param nom Le nom du fichier a charger.
     * @return Le jeu charge.
     */
	public static Jeu charger(String nom)
	{
		Jeu jeu = new Jeu();
		try {
			FileInputStream fis = new FileInputStream(nom + ".obj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			jeu = (Jeu)ois.readObject();
			fis.close();
		}
		catch (IOException e) {e.printStackTrace();}
		catch (ClassNotFoundException e1) {e1.printStackTrace();}
		
		return jeu;
	}
    /**
     * Fonction principale du jeu.
     * Permet de creer un nouveau jeu, de jouer contre un humain ou un bot,
     * de charger une partie existante, et gere le deroulement du jeu.
     * 
     * @param args Les arguments de la ligne de commande.
     */
	public static void main(String[] args) {
	        
			// Creation d'un nouveau jeu
        	Jeu monJeu = new Jeu();
        	
        	// Jouer contre un bot ou un humain
        	System.out.println("Voulez-vous jouer contre un humain ou contre un bot ? (humain: 1, bot: 2)");
        	Scanner scanner = new Scanner(System.in);
			int choix = scanner.nextInt();
			if(choix == 1) {
				// creation des deux joueurs
				monJeu.joueur1 = new Joueur(monJeu, "joueur1");
			    monJeu.joueur2 = new Joueur(monJeu, "joueur2");
			}
			else {
				monJeu.joueur1 = new Joueur(monJeu, "joueur1");
				monJeu.joueur2 = new Joueur(monJeu, "bot1");
			}
		    
		    // initialisation de la source
		    monJeu.initialiserSource();
		    
		    // distribution
		    monJeu.distribuer();
		    
		    // charger une partie
		    System.out.println("Charger une partie ? oui:1, non: 2");
		    scanner = new Scanner(System.in);
			choix = scanner.nextInt();
			if(choix == 1) {
				System.out.println("Entrez le nom de la partie :");
			    scanner = new Scanner(System.in);
			    String nom;
				nom = scanner.next();
				monJeu = Jeu.charger(nom);
			}
			
		    // jeu
		    while(true) {
		    	// On selectionne le joueur qui doit jouer
		    	Joueur joueurActuel = monJeu.selectionnerJoueurActuel();
		    	// On selectionne le joueur qui ne doit pas jouer
		    	Joueur joueurVictime = monJeu.selectionnerJoueurVictime();
		    	
		    	// On check si le joueur actuel doit recommencer une nouvelle vie
		    	if(joueurActuel.getMain().size() == 0 && joueurActuel.getPile().size() == 0) {
		    		int points = joueurActuel.calculerPoint();
		    		if(points + joueurActuel.getAnneaux() > joueurActuel.getTranscendance() - 1) {
		    			if(joueurActuel.getTranscendance() == 7) { // Si le joueur actuel a gagne
		    				System.out.println("Joueur " + joueurActuel.getNom() + ", vous avez gagne !");
		    				break;
		    			}
		    			
		    			joueurActuel.transcender();
		    		}
		    		else {
		    			joueurActuel.nouvelleVie(true);
		    		}
		    	}
		    	
		    	// Le joueur joue
		    	if(joueurActuel.getNom() == "bot1") {
		    		monJeu.jouerBot(joueurActuel, joueurVictime);
		    	}
		    	else {
		    		monJeu.jouer(joueurActuel, joueurVictime);
		    	}
		    	
		    	// On change de joueur
		    	monJeu.changerTour();
		    	
		    	// Possibilite de sauvegarder la partie
		    	System.out.println("Voulez-vous sauvegarder ? oui: 1, non: 2");
		    	scanner = new Scanner(System.in);
				choix = scanner.nextInt();
				if(choix == 1) {
					System.out.println("Entrez le nom de la sauvegarde :");
			    	scanner = new Scanner(System.in);
			    	String nom;
			    	nom = scanner.next();
					Jeu.sauvegarder(monJeu, nom);
					System.out.println("Partie sauvegardee");
				}
		    }
	    }
}