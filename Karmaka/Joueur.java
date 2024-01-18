package Karmaka;
import java.util.*;
import java.io.*;
public class Joueur implements Serializable {
	private static final long serialVersionUID = 2L;
	private String nom;
	private int anneaux;
	private int transcendance;
	private ArrayList<Carte> pile;
	private ArrayList<Carte> vieFuture;
	private ArrayList<Carte> oeuvre;
	private ArrayList<Carte> saMain;
	private Jeu jeu;
	
	// Constructeur
	public Joueur(Jeu jeu, String nom) {
		this.anneaux = 0;
		this.transcendance = 4;
		this.pile = new ArrayList<>();
		this.vieFuture = new ArrayList<>();
		this.oeuvre = new ArrayList<>();
		this.saMain = new ArrayList<>();
		this.jeu = jeu;
		this.nom = nom;
	}
	
	public void piocherPile(){
		int index = this.pile.size() - 1;
		Carte carte = this.pile.get(index);
		this.retirerPile(index);
		this.ajouterMain(carte);
	}
	
	public void jouerPoint(int index){
		Carte carte = this.saMain.get(index);
		this.retirerMain(index);
		this.ajouterOeuvre(carte);
	}
	
	public void jouerPouvoir(int index, Joueur executeur, Joueur victime){
		Carte carte = this.saMain.get(index);
		this.retirerMain(index);
		
		// Le joueur victime peut choisir de récupérer la carte pour sa vie future
		System.out.println(victime.getNom() + ", voulez vous récupérer la carte " + carte.getNom() + " dans votre vie future ? oui: tapez 1, non: tapez 2");
		int choix = 0;
		if(victime.nom == "bot1") {
			double al = Math.random() * 2 + 1;
			choix = (int) al;
			System.out.println("Le bot a choisi : " + choix);
		}
		else {
			Scanner scanner = new Scanner(System.in);
			choix = scanner.nextInt();
		}
		if(choix == 1) {
			victime.ajouterVieFuture(carte); // Le joueur victime récupère la carte
		}
		else if(choix == 2) {
			this.jeu.ajouterCarteFosse(carte); // La carte est défossée
		}
		
		carte.executerPouvoir(executeur, victime);
	}
	
	public void jouerFutur(int index){
		Carte carte = this.saMain.get(index);
		this.retirerMain(index);
		this.ajouterVieFuture(carte);
	}
	
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
			totalPoint = totalPoint + this.oeuvre.get(i).getPoint();
		}
		return totalPoint;
	}
	
	public void transcender(){
		if (this.calculerPoint() > this.transcendance - 1) {
			this.transcendance = this.transcendance + 1;
		}
		else if (this.calculerPoint() + this.getAnneaux() > this.transcendance) {
			this.transcendance = this.transcendance + 1;
			this.setAnneaux(this.getAnneaux() - (this.transcendance - this.calculerPoint()));
		}
		this.nouvelleVie(false);
		System.out.println("Vous avez transcendé");
	}
	
	public void nouvelleVie(boolean gainAnneau){
		if (gainAnneau == true) {
			this.anneaux = this.anneaux + 1;
		}
		
		// Ajouter les oeuvres dans la fosse
		for(int i = 0; i < this.oeuvre.size(); i++) {
			Carte carte = this.oeuvre.get(i);
			this.retirerOeuvre(i);
			this.jeu.ajouterCarteFosse(carte);
		}
		
		// Prendre les cartes de la vie future
		for (int i = 0; i < this.vieFuture.size(); i++) {
			Carte carte = this.vieFuture.get(i);
			this.retirerVieFuture(i);
			this.ajouterMain(carte);
		}
		
		// Si la main contient moins de 6 cartes
		if (this.saMain.size() < 6) {
			while(true){ // On pioche jusqu'à atteindre 6 cartes (main + pile)
				Carte carte = jeu.piocher();
				this.ajouterPile(carte);
				if (this.saMain.size() + this.pile.size() > 5) {
					break;
				}
			}
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
	
	public String getNom() {
		return this.nom;
	}
}
