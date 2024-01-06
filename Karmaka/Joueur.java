package Karmaka;
import java.util.ArrayList;
import java.util.List;
public class Joueur {
	int anneaux;
	int transcendance;
	private List<Carte> pile;
	private List<Carte> vieFuture;
	private List<Carte> oeuvre;
	private List<Carte> saMain;
	
	
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
	public List<Carte> getMain(){
		return this.saMain;
	}
	public void setMain(){}
	public List<Carte> getPile(){
		return this.pile;
	}
	public void setPile(){}
	public List<Carte> getVieFuture(){
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
