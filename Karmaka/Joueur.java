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
	public void jouerPoint(Carte c){}
	public void jouerPouvoir(Carte c){}
	public void jouerFutur(Carte c){}
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
		this.saMain = saMain;
	}
	public ArrayList<Carte> getPile(){
		return this.pile;
	}
	public void setPile(){}
	public ArrayList<Carte> getVieFuture(){
		return this.vieFuture;
	}
	public void setVieFuture(){}
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
