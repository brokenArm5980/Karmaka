package Karmaka;
import java.util.*;
public class Joueur {
	int anneaux;
	int transcendance;
	private ArrayList<Carte> pile;
	private ArrayList<Carte> vieFuture;
	private ArrayList<Carte> oeuvre;
	private ArrayList<Carte> saMain;
	
	// Constructeur
	public Joueur() {
		this.anneaux = 0;
		this.transcendance = 4;
		this.pile = new ArrayList<>();
		this.vieFuture = new ArrayList<>();
		this.oeuvre = new ArrayList<>();
		this.saMain = new ArrayList<>();
	}
	
	public void piocherPile(){
		Carte carte = this.pile.get(this.pile.size() - 1);
		this.pile.remove(this.pile.size() - 1);
		this.saMain.add(carte);
	}
	
	public void jouerPoint(Carte carte){
		this.saMain.remove(carte);
		this.oeuvre.add(carte);
	}
	
	public void jouerPouvoir(Carte carte, Joueur receveur){
		carte.executerPouvoir(receveur);
		this.saMain.remove(carte);
	}
	
	public void jouerFutur(Carte carte){
		this.saMain.remove(carte);
		this.vieFuture.add(carte);
	}
	
	// Permet au joueur de passer son tour
	public void passerTour(){}
	
	// Permet de vérifier si le joueur a gagné la partie
	public boolean estGagnant(){
		if (transcendance == 8) {
			return true;
		}
		else {return false;}
	}
	
	// Calculer les points accumulés par le joueur
	public int calculerPoint(){
		int totalPoint = 0;
		for(int i = 0; i < this.oeuvre.size(); i++) {
			totalPoint = totalPoint + oeuvre.get(i).getPoint();
		}
		return totalPoint;
	}
	
	public void transcender(){}
	public void nouvelleVie(){}
	public void choisirCarte(){
		for(int i = 0; i < this.saMain.size(); i++) {
			System.out.println(saMain.get(i));
		}
	}
	
	// Getters and Setters
	public ArrayList<Carte> getMain(){
		return this.saMain;
	}
	public void setMain(ArrayList<Carte> saMain){
		this.saMain = new ArrayList<Carte>(saMain);
	}
	
	public ArrayList<Carte> getPile(){
		return this.pile;
	}
	public void setPile(ArrayList<Carte> pile){
		this.pile = new ArrayList<Carte>(pile);
	}
	
	public ArrayList<Carte> getVieFuture(){
		return this.vieFuture;
	}
	public void setVieFuture(ArrayList<Carte> vieFuture){
		this.vieFuture = new ArrayList<Carte>(vieFuture);
	}
	
	public ArrayList<Carte> getOeuvre(){
		return this.oeuvre;
	}
	public void setOeuvre(ArrayList<Carte> oeuvre){
		this.oeuvre = new ArrayList<Carte>(oeuvre);
	}
	
	public int getAnneaux(){
		return anneaux;
	}
	public void setAnneaux(int anneaux){
		this.anneaux = anneaux;
	}
	
	public int getTranscendance(){
		return transcendance;
	}
	public void setTranscendance(int transcendance){
		this.transcendance = transcendance;
	}
}
