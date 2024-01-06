package Karmaka;
import java.util.*;
public class Joueur {
	int anneaux;
	int transcendance;
	private ArrayList<Carte> pile;
	private ArrayList<Carte> vieFuture;
	private ArrayList<Carte> oeuvre;
	private ArrayList<Carte> saMain;
	private Jeu jeu;
	
	// Constructeur
	public Joueur(Jeu jeu) {
		this.anneaux = 0;
		this.transcendance = 4;
		this.pile = new ArrayList<>();
		this.vieFuture = new ArrayList<>();
		this.oeuvre = new ArrayList<>();
		this.saMain = new ArrayList<>();
		this.jeu = jeu;
	}
	
	public void piocherPile(){
		int index = this.pile.size() - 1;
		Carte carte = this.pile.get(index);
		retirerPile(index);
		ajouterMain(carte);
	}
	
	public void jouerPoint(int index){
		Carte carte = this.saMain.get(index);
		retirerMain(index);
		ajouterOeuvre(carte);
	}
	
	public void jouerPouvoir(int index, Joueur executeur, Joueur victime){
		Carte carte = this.saMain.get(index);
		carte.executerPouvoir(executeur, victime);
		retirerMain(index);
	}
	
	public void jouerFutur(int index){
		Carte carte = this.saMain.get(index);
		retirerMain(index);
		ajouterVieFuture(carte);
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
	
	public void transcender(){
		this.transcendance = this.transcendance + 1;
		nouvelleVie(false);
	}
	
	public void nouvelleVie(boolean gainAnneau){
		if (gainAnneau == true) {
			this.anneaux = this.anneaux + 1;
		}
		
		// Ajouter les oeuvres dans la fosse
		for(int i = 0; i < this.oeuvre.size(); i++) {
			Carte carte = this.oeuvre.get(i);
			retirerOeuvre(i);
			this.jeu.ajouterCarteFosse(carte);
		}
		
		// Prendre les cartes de la vie future
		for (int i = 0; i < this.vieFuture.size(); i++) {
			Carte carte = this.vieFuture.get(i);
			retirerVieFuture(i);
			ajouterMain(carte);
		}
	}
	
	public void choisirCarte(){
		for(int i = 0; i < this.saMain.size(); i++) {
			System.out.println(saMain.get(i));
		}
	}
	
	// Ajouter et Retirer
	public void ajouterMain(Carte carte) {
		this.saMain.add(carte);
	}
	public void retirerMain(int index) {
		this.saMain.remove(index);
	}
	
	public void ajouterPile(Carte carte) {
		this.pile.add(carte);
	}
	public void retirerPile(int index) {
		this.pile.remove(index);
	}
	
	public void ajouterVieFuture(Carte carte) {
		this.vieFuture.add(carte);
	}
	public void retirerVieFuture(int index) {
		this.vieFuture.remove(index);
	}
	
	public void ajouterOeuvre(Carte carte) {
		this.oeuvre.add(carte);
	}
	public void retirerOeuvre(int index) {
		this.oeuvre.remove(index);
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
