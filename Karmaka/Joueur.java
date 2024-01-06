package Karmaka;
import java.util.*;
public class Joueur {
	int anneaux;
	int transcendance;
	private ArrayList<Carte> pile;
	private ArrayList<Carte> vieFuture;
	private ArrayList<Carte> oeuvre;
	private ArrayList<Carte> saMain;
	
	
	public Joueur() {
		this.anneaux = 0;
		this.transcendance = 4;
		this.pile = new ArrayList<>();
		this.vieFuture = new ArrayList<>();
		this.oeuvre = new ArrayList<>();
		this.saMain = new ArrayList<>();
	}
	
	public void piocherPile(){}
	public void jouerPoint(Carte carte){}
	public void jouerPouvoir(Carte carte){}
	public void jouerFutur(Carte carte){
		this.saMain.remove(carte);
		this.vieFuture.add(carte);
	}
	public void passerTour(){}
	public boolean estGagnant(){
		if (transcendance ==8) {
			return true;
		}
		else {return false;}
	}
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
	public void setAnneaux(){}
	public int getTranscendance(){
		return transcendance;
	}
	public void setTranscendance(){}
	public void calculerPoint() {}
	public void transcender(){}
	public void nouvelleVie(){}
	public void choisirCarte(){}
	
	
	

}
