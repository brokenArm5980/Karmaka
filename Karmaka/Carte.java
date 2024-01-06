package Karmaka;

import java.util.ArrayList;

public class Carte {
	private String nom;
	private Couleur couleur;
	private int point;
	
	// Constructor
	public Carte(String nom, Couleur couleur, int point) {
		this.nom = nom;
		this.couleur = couleur;
		this.point = point;
	}
	
	public void executerPouvoir(Joueur joueur) {
		
		if(this.nom=="Transmigration") {
			
			if (!joueur.getVieFuture().isEmpty()) {
				System.out.println("quelle carte voulez vous prendre ?");
				ArrayList<Carte> tempVie = new ArrayList<Carte>();
				tempVie = joueur.getVieFuture();
				for (int i=0;i<joueur.vieFuture.size();i++) {
					System.out.println(tempVie.get(i).nom+ " entrez"+ i);
				}
		        Carte carteVieFuture = joueur.getVieFuture().get(0); // Prenez la première carte de la Vie Future
		        joueur.getMain().add(carteVieFuture); // Ajoutez cette carte à la Main du joueur
		        joueur.getVieFuture().remove(0); // Retirez la carte de la Vie Future
		    }
		}
		if(this.nom=="Coup d Oeil") {
			
		}
		if(this.nom=="Destinee") {
			
		}
		if(this.nom=="Reves Brises") {

		}
		if(this.nom=="Deni") {
			
		}
		if(this.nom=="Duperie") {
			
		}
		if(this.nom=="Vol") {
			
		}
		if(this.nom=="Lendemain") {
			
		}
		if(this.nom=="Recyclage") {
			
		}
		if(this.nom=="Sauvetage") {
			
		}
		if(this.nom=="Longevite") {
			
		}
		if(this.nom=="Semis") {
			
		}
		if(this.nom=="Voyage") {
			
		}
		if(this.nom=="Jubile") {
			
		}
		if(this.nom=="Panique") {
			
		}
		if(this.nom=="Dernier Souffle") {
			
		}
		if(this.nom=="Crise") {
			
		}
		if(this.nom=="Roulette") {
			
		}
		if(this.nom=="Fournaise") {
			
		}
		if(this.nom=="Vengeance") {
			
		}
		if(this.nom=="Bassesse") {
			
		}
		if(this.nom=="Incarnation") {
			
		}
		if(this.nom=="Mimetisme") {
			
		}
	}
	//Getters and Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
}
