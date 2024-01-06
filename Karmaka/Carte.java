package Karmaka;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	public void executerPouvoir(Joueur executeur, Joueur victime, Jeu jeu) {
		
		if(this.nom=="Transmigration") {
			
			if (!executeur.getVieFuture().isEmpty()) {
				System.out.println("quelle carte voulez vous prendre ?");
				for (int i=0;i<executeur.getVieFuture().size();i++) {
					System.out.println(executeur.getVieFuture().get(i).nom+ " entrez"+ i);
				}
				Scanner scanner = new Scanner(System.in);
				int choix = scanner.nextInt();
		        Carte carteVieFuture = executeur.getVieFuture().get(choix); // Prenez la carte de la Vie Future
		        executeur.getMain().add(carteVieFuture); // Ajoutez cette carte à la Main du joueur
		        executeur.getVieFuture().remove(choix); // Retirez la carte de la Vie Future
		    }
		}
		if(this.nom=="Coup d Oeil") {
			
			if (!victime.getMain().isEmpty()) {
				System.out.println("Voici les cartes de votre adversaire:");
				for (int i=0;i<victime.getMain().size();i++) {
					System.out.println(victime.getMain().get(i).nom);
				}
			}
	
			// à coder : rejouer une carte
			
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
	public String toString() {
        return "Carte{" +
                "nom='" + this.nom + '\'' +
                ", couleur=" + this.couleur +
                ", point=" + this.point +
                '}';
    }
	
}
