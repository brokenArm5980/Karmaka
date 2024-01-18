package Karmaka;
import java.util.*;
import java.io.*;
/**
 * La classe Joueur represente un joueur dans le jeu Karmaka.
 * Chaque joueur possede un nom, des anneaux, une transcendance,
 * une pile, une vie future, une oeuvre, une main, et une reference vers le jeu.
 * Les joueurs peuvent effectuer diverses actions telles que piocher, jouer des cartes,
 * transcender, etc.
 */
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
	
    /**
     * Constructeur de la classe Joueur.
     * @param jeu La reference vers le jeu auquel le joueur appartient.
     * @param nom Le nom du joueur.
     */
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
    /**
     * Methode permettant au joueur de piocher une carte depuis sa pile.
     */
	public void piocherPile(){
		int index = this.pile.size() - 1;
		Carte carte = this.pile.get(index);
		this.retirerPile(index);
		this.ajouterMain(carte);
	}
	/**
    * Methode permettant au joueur de jouer une carte de sa main dans son oeuvre.
    * @param index L'index de la carte dans la main du joueur.
    */
	public void jouerPoint(int index){
		Carte carte = this.saMain.get(index);
		this.retirerMain(index);
		this.ajouterOeuvre(carte);
	}
    /**
     * Methode permettant au joueur de jouer une carte de sa main, d'executer son pouvoir,
     * et de donner au joueur victime la possibilite de recuperer la carte dans sa vie future.
     * @param index L'index de la carte dans la main du joueur.
     * @param executeur Le joueur executant le pouvoir.
     * @param victime Le joueur subissant le pouvoir.
     */
	public void jouerPouvoir(int index, Joueur executeur, Joueur victime){
		Carte carte = this.saMain.get(index);
		this.retirerMain(index);
		
		// Le joueur victime peut choisir de recuperer la carte pour sa vie future
		System.out.println(victime.getNom() + ", voulez vous recuperer la carte " + carte.getNom() + " dans votre vie future ? oui: tapez 1, non: tapez 2");
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
			victime.ajouterVieFuture(carte); // Le joueur victime recupere la carte
		}
		else if(choix == 2) {
			this.jeu.ajouterCarteFosse(carte); // La carte est defossee
		}
		
		carte.executerPouvoir(executeur, victime);
	}
    /**
     * Methode permettant au joueur de jouer une carte de sa main dans sa vie future.
     * @param index L'index de la carte dans la main du joueur.
     */
	public void jouerFutur(int index){
		Carte carte = this.saMain.get(index);
		this.retirerMain(index);
		this.ajouterVieFuture(carte);
	}
	
    /**
     * Methode permettant de verifier si le joueur a gagne la partie.
     * @return true si le joueur a atteint la transcendance 8, sinon false.
     */
	public boolean estGagnant(){
		if (transcendance == 8) {
			return true;
		}
		else {return false;}
	}
	
    /**
     * Methode permettant de calculer les points accumules par le joueur.
     * @return Le total des points accumules.
     */
	public int calculerPoint(){
		int totalPoint = 0;
		for(int i = 0; i < this.oeuvre.size(); i++) {
			totalPoint = totalPoint + this.oeuvre.get(i).getPoint();
		}
		return totalPoint;
	}
    /**
     * Methode permettant au joueur de transcender s'il a suffisamment de points.
     */
	public void transcender(){
		if (this.calculerPoint() > this.transcendance - 1) {
			this.transcendance = this.transcendance + 1;
		}
		else if (this.calculerPoint() + this.getAnneaux() > this.transcendance) {
			this.transcendance = this.transcendance + 1;
			this.setAnneaux(this.getAnneaux() - (this.transcendance - this.calculerPoint()));
		}
		this.nouvelleVie(false);
		System.out.println("Vous avez transcende");
	}
    /**
     * Methode permettant au joueur de commencer une nouvelle vie en recuperant des anneaux,
     * en ajoutant les oeuvres dans la fosse, et en prenant les cartes de la vie future dans sa main.
     * @param gainAnneau true si le joueur doit gagner un anneau, sinon false.
     */
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
			while(true){ // On pioche jusqu'a atteindre 6 cartes (main + pile)
				Carte carte = jeu.piocher();
				this.ajouterPile(carte);
				if (this.saMain.size() + this.pile.size() > 5) {
					break;
				}
			}
		}
	}
	
	/**
     * Methode permettant d'ajouter une carte a la main du joueur.
     * @param carte La carte a ajouter a la main.
     */
	// Ajouter et Retirer
	public void ajouterMain(Carte carte) {
		this.saMain.add(carte);
	}
	/**
     * Methode permettant de retirer une carte de la main du joueur.
     * @param index L'index de la carte a retirer.
     */
	public void retirerMain(int index) {
		this.saMain.remove(index);
	}
    /**
     * Methode permettant d'ajouter une carte a la pile du joueur.
     * @param carte La carte a ajouter a la pile.
     */
	public void ajouterPile(Carte carte) {
		this.pile.add(carte);
	}
    /**
     * Methode permettant de retirer une carte de la pile du joueur.
     * @param index L'index de la carte a retirer.
     */
	public void retirerPile(int index) {
		this.pile.remove(index);
	}
    /**
     * Methode permettant d'ajouter une carte a la vie future du joueur.
     * @param carte La carte a ajouter a la vie future.
     */
	public void ajouterVieFuture(Carte carte) {
		this.vieFuture.add(carte);
	}
    /**
     * Methode permettant de retirer une carte de la vie future du joueur.
     * @param index L'index de la carte a retirer.
     */
	public void retirerVieFuture(int index) {
		this.vieFuture.remove(index);
	}
    /**
     * Methode permettant d'ajouter une carte a l'oeuvre du joueur.
     * @param carte La carte a ajouter a l'oeuvre.
     */
	public void ajouterOeuvre(Carte carte) {
		this.oeuvre.add(carte);
	}
    /**
     * Methode permettant de retirer une carte de l'oeuvre du joueur.
     * @param index L'index de la carte a retirer.
     */
	public void retirerOeuvre(int index) {
		this.oeuvre.remove(index);
	}
	
    /**
     * Getter pour la main du joueur.
     * @return La liste des cartes dans la main du joueur.
     */
	public ArrayList<Carte> getMain(){
		return this.saMain;
	}
    /**
     * Setter pour la main du joueur.
     * @param saMain La nouvelle liste de cartes dans la main.
     */
	public void setMain(ArrayList<Carte> saMain){
		this.saMain = new ArrayList<Carte>(saMain);
	}
    /**
     * Getter pour la pile du joueur.
     * @return La liste des cartes dans la pile du joueur.
     */
	public ArrayList<Carte> getPile(){
		return this.pile;
	}
	/**
     * Setter pour la pile du joueur.
     * @param pile La nouvelle liste de cartes dans la pile.
     */
	public void setPile(ArrayList<Carte> pile){
		this.pile = new ArrayList<Carte>(pile);
	}
	/**
     * Getter pour la vie future du joueur.
     * @return La liste des cartes dans la vie future du joueur.
     */
	public ArrayList<Carte> getVieFuture(){
		return this.vieFuture;
	}
    /**
     * Setter pour la vie future du joueur.
     * @param vieFuture La nouvelle liste de cartes dans la vie future.
     */
	public void setVieFuture(ArrayList<Carte> vieFuture){
		this.vieFuture = new ArrayList<Carte>(vieFuture);
	}
	 /**
     * Getter pour l'oeuvre du joueur.
     * @return La liste des cartes dans l'oeuvre du joueur.
     */
	public ArrayList<Carte> getOeuvre(){
		return this.oeuvre;
	}
    /**
     * Setter pour l'oeuvre du joueur.
     * @param oeuvre La nouvelle liste de cartes dans l'oeuvre.
     */
	public void setOeuvre(ArrayList<Carte> oeuvre){
		this.oeuvre = new ArrayList<Carte>(oeuvre);
	}
	/**
     * Getter pour le nombre d'anneaux du joueur.
     * @return Le nombre d'anneaux du joueur.
     */
	public int getAnneaux(){
		return anneaux;
	}
    /**
     * Setter pour le nombre d'anneaux du joueur.
     * @param anneaux Le nouveau nombre d'anneaux.
     */
	public void setAnneaux(int anneaux){
		this.anneaux = anneaux;
	}
    /**
     * Getter pour le niveau de transcendance du joueur.
     * @return Le niveau de transcendance du joueur.
     */
	
	public int getTranscendance(){
		return transcendance;
	}
	/**
     * Setter pour le niveau de transcendance du joueur.
     * @param transcendance Le nouveau niveau de transcendance.
     */
	public void setTranscendance(int transcendance){
		this.transcendance = transcendance;
	}
    /**
     * Getter pour le nom du joueur.
     * @return Le nom du joueur.
     */
	public String getNom() {
		return this.nom;
	}
}
