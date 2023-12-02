package Karmaka;

public class Carte {
	private String nom;
	private Couleur couleur;
	private int point;
	
	public Carte(String nom, Couleur couleur, int point) {
		this.nom = nom;
		this.couleur = couleur;
		this.point = point;
	}

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
