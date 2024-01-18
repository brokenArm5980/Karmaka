package Karmaka;

import java.util.*;

import java.io.*;
/**
 * La classe Carte represente une carte du jeu Karmaka.
 * Chaque carte possede un nom, une couleur, un nombre de points, 
 * et une reference vers le jeu auquel elle appartient.
 * Les cartes peuvent avoir differents pouvoirs definis dans la methode executerPouvoir.
 */

public class Carte implements Serializable {
	private static final long serialVersionUID = 4L;
	private String nom;
	private Couleur couleur;
	private int point;
	private Jeu jeu;	
	
    /**
     * Constructeur de la classe Carte.
     * @param nom Le nom de la carte.
     * @param couleur La couleur de la carte.
     * @param point Le nombre de points associes a la carte.
     * @param jeu La reference vers le jeu auquel la carte appartient.
     */
	
	public Carte(String nom, Couleur couleur, int point, Jeu jeu) {
		this.nom = nom;
		this.couleur = couleur;
		this.point = point;
		this.jeu = jeu;
	}
	
    /**
     * Methode permettant d'executer le pouvoir de la carte en fonction de son nom.
     * @param executeur Le joueur executant le pouvoir.
     * @param victime Le joueur subissant le pouvoir.
     */
	public void executerPouvoir(Joueur executeur, Joueur victime) {
		
		if(this.nom=="Transmigration") { // Placez dans votre Main n’importe quelle carte de votre Vie Future.
			
			if (executeur.getVieFuture().size() > 0) {
				System.out.println("quelle carte voulez vous prendre ?");
				for (int i = 0; i < executeur.getVieFuture().size(); i++) {
					System.out.println(executeur.getVieFuture().get(i).getNom()+ ": entrez "+ i);
				}
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * executeur.getVieFuture().size();
					choix = (int) al;
					System.out.println("Le bot a choisi : " + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				}
		        Carte carteVieFuture = executeur.getVieFuture().get(choix); // Prenez la carte de la Vie Future
		        executeur.getMain().add(carteVieFuture); // Ajoutez cette carte a la Main du joueur
		        executeur.getVieFuture().remove(choix); // Retirez la carte de la Vie Future
		    }
		}
		
		if(this.nom=="Coup d Oeil") { // Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.
			
			if (victime.getMain().size() > 0) { 
				System.out.println("Voici les cartes de votre adversaire:");
				for (int i=0;i<victime.getMain().size();i++) {
					System.out.println(victime.getMain().get(i).getNom());
				}
			}
	
			// Le joueur peut rejouer
			this.jeu.changerTour();
			
		}
		
		if(this.nom=="Destinee") { // Regardez les 3 premieres cartes de la Source ; ajoutez-en jusqu’a 2 a votre Vie Future. Replacez le reste dans l'ordre souhaite.
			System.out.println("Voici les 3 premieres cartes de la source :");
			ArrayList<Carte> source = this.jeu.getSource();
			ArrayList<Carte> listeTroisCartes = new ArrayList<Carte>();
			int nbCarte;
			if(source.size() == 0) {
				this.jeu.completerSource();
				source = this.jeu.getSource();
			}
			if(source.size() < 3) {
				for(int i=0; i<source.size(); i++) {
					
					listeTroisCartes.add(source.get(i));
				}
			}
			else {
				for(int i=0; i<3; i++) {
					listeTroisCartes.add(source.get(source.size() - 1 - i));
				}
			}
			nbCarte = listeTroisCartes.size();
			for(int i=0; i < nbCarte; i++) {
				System.out.println(listeTroisCartes.get(i).getNom());
			}
			if(nbCarte == 1) {
				System.out.println("Voulez vous mettre la carte dans votre vie future ? oui: tapez 1, non: tapez 2");
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 2 + 1;
					choix = (int) al;
					System.out.println("Le bot a choisi : " + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				}
				Carte carte = listeTroisCartes.get(0);
				if(choix == 1) { // On met la carte dans la vie future
					executeur.ajouterVieFuture(carte);
				}
				else if(choix == 2) { // On met la carte dans la vie future
					this.jeu.ajouterCarteFosse(carte);
				}
			}
			else if(nbCarte == 2) {
				System.out.println("Quelles cartes voulez-vous placer dans votre vie future ? carte1: tapez 1, carte2: tapez 2, les deux: tapez 3, auncune: tapez 4");
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 4 + 1;
					choix = (int) al;
					System.out.println("Le bot a choisi :" + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				}
				if(choix == 1) { // On met la carte dans la vie future
					executeur.ajouterVieFuture(listeTroisCartes.get(0));
					this.jeu.ajouterCarteFosse(listeTroisCartes.get(1));
				}
				else if(choix == 2) { // On met la carte dans la vie future
					executeur.ajouterVieFuture(listeTroisCartes.get(1));
					this.jeu.ajouterCarteFosse(listeTroisCartes.get(0));
				}
				else if(choix == 3) { // On met la carte dans la vie future
					executeur.ajouterVieFuture(listeTroisCartes.get(0));
					executeur.ajouterVieFuture(listeTroisCartes.get(1));
				}
				else if(choix == 4) { // On met la carte dans la vie future
					this.jeu.ajouterCarteFosse(listeTroisCartes.get(0));
					this.jeu.ajouterCarteFosse(listeTroisCartes.get(1));
				}
			}
			else if(nbCarte == 3) {
				System.out.println("Quelles cartes voulez-vous placer dans votre vie future ? carte1: tapez 1, carte2: tapez 2, carte3: tapez 3, passer: tapez 4");
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 4 + 1;
					choix = (int) al;
					System.out.println("Le bot a choisi :" + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				}
				if(choix == 1) { // On met la carte dans la vie future
					executeur.ajouterVieFuture(listeTroisCartes.get(0));
					System.out.println("Quelle carte voulez-vous placer dans votre vie future ? carte2: tapez 2, carte3: tapez 3, passer: tapez 4");
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * 3 + 2;
						choix = (int) al;
						System.out.println("Le bot a choisi :" + choix);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						choix = scanner.nextInt();
					}
					if(choix == 2) {
						executeur.ajouterVieFuture(listeTroisCartes.get(1));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(2));
					}
					else if(choix == 3) {
						executeur.ajouterVieFuture(listeTroisCartes.get(2));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(1));
					}
					else if(choix == 4) {
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(1));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(2));
					}
				}
				else if(choix == 2) { // On met la carte dans la vie future
					executeur.ajouterVieFuture(listeTroisCartes.get(1));
					System.out.println("Quelle carte voulez-vous placer dans votre vie future ? carte1: tapez 1, carte3: tapez 3, passer: tapez 4");
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * 3 + 2;
						choix = (int) al;
						if(choix == 2) {choix = 1;}
						System.out.println("Le bot a choisi :" + choix);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						choix = scanner.nextInt();
					}
					if(choix == 1) {
						executeur.ajouterVieFuture(listeTroisCartes.get(0));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(2));
					}
					else if(choix == 3) {
						executeur.ajouterVieFuture(listeTroisCartes.get(2));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(0));
					}
					else if(choix == 4) {
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(0));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(2));
					}
				}
				else if(choix == 3) { // On met la carte dans la vie future
					executeur.ajouterVieFuture(listeTroisCartes.get(2));
					System.out.println("Quelle carte voulez-vous placer dans votre vie future ? carte1: tapez 1, carte2: tapez 2, passer: tapez 4");
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * 3 + 1;
						choix = (int) al;
						if(choix == 3) {choix = 4;}
						System.out.println("Le bot a choisi :" + choix);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						choix = scanner.nextInt();
					}
					if(choix == 2) {
						executeur.ajouterVieFuture(listeTroisCartes.get(0));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(1));
					}
					else if(choix == 3) {
						executeur.ajouterVieFuture(listeTroisCartes.get(1));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(0));
					}
					else if(choix == 4) {
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(0));
						this.jeu.ajouterCarteFosse(listeTroisCartes.get(1));
					}
				}
				else if(choix == 4) { // On met les carte dans la fosse
					this.jeu.ajouterCarteFosse(listeTroisCartes.get(0));
					this.jeu.ajouterCarteFosse(listeTroisCartes.get(1));
					this.jeu.ajouterCarteFosse(listeTroisCartes.get(2));
				}
			}
		}
		
		if(this.nom=="Reves Brises") { // Placez la premiere carte de la Vie Future d'un rival sur la vôtre.
			if(victime.getVieFuture().size() > 0) {
				int index = victime.getVieFuture().size() - 1;
				Carte carte = victime.getVieFuture().get(index);
				victime.retirerVieFuture(index);
				executeur.ajouterVieFuture(carte);
			}
		}
		
		if(this.nom=="Deni") { // Defaussez une carte de votre Main. Copiez le pouvoir de cette carte.
			while(true) {
				System.out.println("Choisissez une carte a jouer avec Deni :");
				ArrayList<Carte> main = executeur.getMain();
				for(int i=0; i<main.size(); i++) {
					System.out.println(main.get(i).getNom() + ": tapez " + i);
				}
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * main.size();
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				Carte carte = main.get(index);
				executeur.retirerMain(index);
				this.jeu.ajouterCarteFosse(carte);
				carte.executerPouvoir(executeur, victime);
			}
		}
		
		if(this.nom=="Duperie") { // Regardez 3 cartes de la Main d’un rival ; ajoutez-en une a votre Main.
			ArrayList<Carte> main = victime.getMain();
			int tailleMain = main.size();
			System.out.println("Votre adversaire a " + tailleMain + " cartes");
			if(tailleMain > 3) {
				System.out.println("Choisissez les numero des cartes que vous voulez voir allant de 1 a " + tailleMain);
				System.out.println("Premier numero :");
				int num1 = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * tailleMain + 1;
					num1 = (int) al;
					System.out.println("Le bot a choisi : " + num1);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					num1 = scanner.nextInt();
				}
				System.out.println("Deuxieme numero :");
				int num2 = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * tailleMain + 1;
					num2 = (int) al;
					System.out.println("Le bot a choisi : " + num2);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					num2 = scanner.nextInt();
				}
				System.out.println("Troisieme numero :");
				int num3 = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * tailleMain + 1;
					num3 = (int) al;
					System.out.println("Le bot a choisi : " + num3);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					num3 = scanner.nextInt();
				}
				System.out.println("Voici les cartes que vous avez choisies :");
				System.out.println(main.get(num1).getNom());
				System.out.println(main.get(num2).getNom());
				System.out.println(main.get(num3).getNom());
				System.out.println("Ajouter une carte a votre main. Carte 1: tapez 1, Carte 2: tapez 2, Carte 3: tapez 3");
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 3 + 1;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				if(index == 1) {
					executeur.ajouterMain(main.get(num1));
					victime.retirerMain(num1);
				}
				else if(index == 2) {
					executeur.ajouterMain(main.get(num2));
					victime.retirerMain(num2);
				}
				else if(index == 3) {
					executeur.ajouterMain(main.get(num3));
					victime.retirerMain(num3);
				}
			}
			else if(tailleMain == 3) {
				System.out.println("Voici les cartes dont dispose votre adversaire :");
				System.out.println(main.get(0).getNom());
				System.out.println(main.get(1).getNom());
				System.out.println(main.get(2).getNom());
				System.out.println("Ajouter une carte a votre main. Carte 1: tapez 1, Carte 2: tapez 2, Carte 3: tapez 3");
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 3 + 1;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				if(index == 1) {
					executeur.ajouterMain(main.get(0));
					victime.retirerMain(0);
				}
				else if(index == 2) {
					executeur.ajouterMain(main.get(1));
					victime.retirerMain(1);
				}
				else if(index == 3) {
					executeur.ajouterMain(main.get(2));
					victime.retirerMain(2);
				}
			}
			else if(tailleMain == 2) {
				System.out.println("Voici les cartes dont dispose votre adversaire :");
				System.out.println(main.get(0).getNom());
				System.out.println(main.get(1).getNom());
				System.out.println("Ajouter une carte a votre main. Carte 1: tapez 1, Carte 2: tapez 2");
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 2 + 1;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				if(index == 1) {
					executeur.ajouterMain(main.get(0));
					victime.retirerMain(0);
				}
				else if(index == 2) {
					executeur.ajouterMain(main.get(1));
					victime.retirerMain(1);
				}
			}
			else if(tailleMain == 1) {
				System.out.println("Voici les cartes dont dispose votre adversaire :");
				System.out.println(main.get(0).getNom());
				System.out.println("La carte a ete ajoutee a votre main.");
				executeur.ajouterMain(main.get(0));
				victime.retirerMain(0);
			}
			else if(tailleMain == 0){
				System.out.println("Votre adversaire n'a pas de carte en main");
			}
		}
		
		if(this.nom=="Vol") { // Placez dans votre Main l’Oeuvre Exposee d'un rival.
			ArrayList<Carte> oeuvre = victime.getOeuvre();
			int taille = oeuvre.size();
			
			if(taille == 0) {
				System.out.println("Votre adversaire n'a pas de carte dans ses oeuvres");
			}
			else {
				int index = taille - 1;
				Carte carte = oeuvre.get(index);
				executeur.ajouterMain(carte);
				victime.retirerOeuvre(index);
			}
		}
		
		if(this.nom=="Lendemain") { // Puisez une carte a la Source. Vous pouvez ensuite jouer une autre carte.
			ArrayList<Carte> source = this.jeu.getSource();
			int taille = source.size();
			
			if(taille == 0) {
				this.jeu.completerSource();
				source = this.jeu.getSource();
				taille = source.size();
			}
			
			int index = taille - 1;
			
			Carte carte = source.get(index);
			executeur.ajouterMain(carte);
			this.jeu.retirerCarteSource(index);
			System.out.println("La carte: " + carte.getNom() + " a ete ajoute a votre main");
			
			// Le joueur peut rejouer
			this.jeu.changerTour();
		}
		
		if(this.nom=="Recyclage") { // Ajoutez a votre Vie Future une des 3 dernieres cartes de la Fosse.
			ArrayList<Carte> fosse = this.jeu.getFosse();
			int taille = fosse.size();
			
			Carte carte;
			
			if(taille == 0) {
				System.out.println("La fosse est vide.");
			}
			else if(taille == 1) {
				System.out.println("Il y a une seule carte dans la fosse");
				carte = fosse.get(0);
				executeur.ajouterVieFuture(carte);
				this.jeu.retirerCarteFosse(0);
				System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre vie future");
			}
			else if(taille == 2) {
				System.out.println("Il y a deux cartes dans la fosse :");
				
				System.out.println(fosse.get(0).getNom());
				System.out.println(fosse.get(1).getNom());
				
				System.out.println("Choisissez une carte a ajouter a votre vie future: carte 1 (tapez 1) ou carte 2 (tapez 2)");
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 2 + 1;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				if(index == 1) {
					carte = fosse.get(0);
					this.jeu.retirerCarteFosse(0);
					executeur.ajouterVieFuture(carte);
					System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre vie future");
				}
				else if(index == 2) {
					carte = fosse.get(1);
					this.jeu.retirerCarteFosse(1);
					executeur.ajouterVieFuture(carte);
					System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre vie future");
				}
			}
			else if(taille > 2) {
				System.out.println("Voici les trois dernieres cartes de la fosse :");
				
				for(int i=0; i<3; i++) {
					System.out.println(fosse.get(taille - 1 - i).getNom());
				}
				
				System.out.println("Choisissez une carte a ajouter a votre vie future: carte 1 (tapez 1) ou carte 2 (tapez 2) ou carte 3 (tapez 3)");
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 3 + 1;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				int index2 = 0;
				
				if(index == 1) {
					index2 = taille - 1;
				}
				else if(index == 2) {
					index2 = taille - 2;
				}
				else if(index == 3) {
					index2 = taille - 3;
				}
				carte = fosse.get(index2);
				this.jeu.retirerCarteFosse(index2);
				executeur.ajouterVieFuture(carte);
				System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre vie future");
			}
			
		}
		if(this.nom=="Sauvetage") { // Ajoutez a votre Main une des 3 dernieres cartes de la Fosse.
			ArrayList<Carte> fosse = this.jeu.getFosse();
			int taille = fosse.size();
			
			Carte carte;
			
			if(taille == 0) {
				System.out.println("La fosse est vide.");
			}
			else if(taille == 1) {
				System.out.println("Il y a une seule carte dans la fosse");
				carte = fosse.get(0);
				executeur.ajouterMain(carte);
				this.jeu.retirerCarteFosse(0);
				System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre main");
			}
			else if(taille == 2) {
				System.out.println("Il y a deux cartes dans la fosse :");
				
				System.out.println(fosse.get(0).getNom());
				System.out.println(fosse.get(1).getNom());
				
				System.out.println("Choisissez une carte a ajouter a votre main: carte 1 (tapez 1) ou carte 2 (tapez 2)");
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 2 + 1;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				if(index == 1) {
					carte = fosse.get(0);
					this.jeu.retirerCarteFosse(0);
					executeur.ajouterMain(carte);
					System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre main");
				}
				else if(index == 2) {
					carte = fosse.get(1);
					this.jeu.retirerCarteFosse(1);
					executeur.ajouterMain(carte);
					System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre vie future");
				}
			}
			else if(taille > 2) {
				System.out.println("Voici les trois dernieres cartes de la fosse :");
				
				for(int i=0; i<3; i++) {
					System.out.println(fosse.get(taille - 1 - i).getNom());
				}
				
				System.out.println("Choisissez une carte a ajouter a votre main: carte 1 (tapez 1) ou carte 2 (tapez 2) ou carte 3 (tapez 3)");
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 3 + 1;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				int index2 = 0;
				
				if(index == 1) {
					index2 = taille - 1;
				}
				else if(index == 2) {
					index2 = taille - 2;
				}
				else if(index == 3) {
					index2 = taille - 3;
				}
				carte = fosse.get(index2);
				this.jeu.retirerCarteFosse(index2);
				executeur.ajouterMain(carte);
				System.out.println("La carte " + carte.getNom() + " a ete ajoute a votre vie future");
			}
		}
		
		if(this.nom=="Longevite") { // Placez 2 cartes puisees a la Source sur la Pile d'un joueur.
			ArrayList<Carte> source = this.jeu.getSource();
			int taille = source.size();
			
			if(taille < 2) {
				this.jeu.completerSource();
				source = this.jeu.getSource();
				taille = source.size();
			}
			
			Carte carte1 = source.get(taille - 1);
			Carte carte2 = source.get(taille - 2);
			
			// Ajout des carte a la pile du joueur victime
			victime.ajouterPile(carte1);
			victime.ajouterPile(carte2);
			
			// On retire les cartes de la source
			this.jeu.retirerCarteSource(this.jeu.getSource().size() - 1);
			this.jeu.retirerCarteSource(this.jeu.getSource().size() - 1);
		}
		
		if(this.nom=="Semis") { // Puisez 2 cartes a la Source, puis placez sur votre Vie Future 2 cartes de votre Main.
			ArrayList<Carte> source = this.jeu.getSource();
			int taille_source = source.size();
			ArrayList<Carte> main = executeur.getMain();
			int taille_main = main.size();
			
			if(taille_source < 2) {
				this.jeu.completerSource();
				source = this.jeu.getSource();
				taille_source = source.size();
			}
			
			Carte carte1 = source.get(taille_source - 1);
			Carte carte2 = source.get(taille_source - 2);
			
			executeur.ajouterMain(carte1);
			executeur.ajouterMain(carte2);
			
			main = executeur.getMain();
			taille_main = main.size();
			
			this.jeu.retirerCarteSource(taille_source - 1);
			this.jeu.retirerCarteSource(taille_source - 1);
			source = this.jeu.getSource();
			taille_source = source.size();
			
			if(taille_main == 2) {
				System.out.println("Les deux cartes de votre main ont ete place dans votre vie future");
				executeur.ajouterVieFuture(main.get(0));
				executeur.retirerMain(0);
				executeur.ajouterVieFuture(main.get(0));
				executeur.retirerMain(0);
			}
			else if(taille_main > 1) {
				System.out.println("Choisissez une premiere carte a placer dans votre vie future :");
				for(int i=0; i<taille_main; i++) {
					System.out.println(main.get(i) + ": tapez " + i);
				}
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * taille_main;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				executeur.ajouterVieFuture(main.get(index));
				executeur.retirerMain(index);
				
				main = executeur.getMain();
				taille_main = main.size();
				
				System.out.println("Choisissez une seconde carte a placer dans votre vie future :");
				for(int i=0; i<taille_main; i++) {
					System.out.println(main.get(i) + ": tapez " + i);
				}
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * taille_main;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				executeur.ajouterVieFuture(main.get(index));
				executeur.retirerMain(index);
			}
		}
		
		if(this.nom=="Voyage") { // Puisez 3 cartes a la Source. Vous pouvez ensuite jouer une autre carte.
			ArrayList<Carte> source = this.jeu.getSource();
			int taille = source.size();
			
			if(taille < 3) {
				this.jeu.completerSource();
				source = this.jeu.getSource();
				taille = source.size();
			}
			
			for(int i=0; i<3; i++) {
				executeur.ajouterMain(source.get(this.jeu.getSource().size() - 1));
				this.jeu.retirerCarteSource(this.jeu.getSource().size() - 1);
			}
			
			// Le joueur peut rejouer
			this.jeu.changerTour();			
		}
		
		if(this.nom=="Jubile") { // Placez jusqu’a 2 cartes de votre Main sur vos Oeuvres.
			ArrayList<Carte> main = executeur.getMain();
			int taille = main.size();
			
			if(taille == 0) {
				System.out.println("Votre Main est vide.");
			}
			else if(taille == 1) {
				System.out.println("Votre Main contient une seule carte :" + main.get(0));
				System.out.println("Voulez-vous la placer dans vos oeuvres ? oui: tapez 1, non: tapez 2");
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 2 + 1;
					choix = (int) al;
					System.out.println("Le bot a choisi : " + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				}
				
				if(choix == 1) {
					executeur.ajouterOeuvre(main.get(0));
					executeur.retirerMain(0);
				}
			}
			else if(taille > 1) {
				System.out.println("Voici les cartes de votre main :");
				for(int i=0; i<taille; i++) {
					System.out.println(main.get(i).getNom());
				}
				System.out.println("Vous pouvez placer dans vos oeuvres 1 carte (tapez 1), 2 carte (tapez 2) ou aucune carte (tapez 3)");
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 3 + 1;
					choix = (int) al;
					System.out.println("Le bot a choisi : " + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				}
				
				if(choix == 1) {
					for(int i=0; i<taille; i++) {
						System.out.println(main.get(i).getNom() + ": tapez " + i);
					}
					int index = 0;
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * taille;
						index = (int) al;
						System.out.println("Le bot a choisi : " + index);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						index = scanner.nextInt();
					}
					
					executeur.ajouterOeuvre(main.get(index));
					executeur.retirerMain(index);
				}
				else if(choix == 2) {
					System.out.println("Premiere carte :");
					for(int i=0; i<taille; i++) {
						System.out.println(main.get(i).getNom() + ": tapez " + i);
					}
					int index = 0;
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * taille;
						index = (int) al;
						System.out.println("Le bot a choisi : " + index);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						index = scanner.nextInt();
					}
					
					executeur.ajouterOeuvre(main.get(index));
					executeur.retirerMain(index);
					
					main = executeur.getMain();
					taille = main.size();
					
					System.out.println("Seconde carte :");
					for(int i=0; i<taille; i++) {
						System.out.println(main.get(i).getNom() + ": tapez " + i);
					}
					
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * taille;
						index = (int) al;
						System.out.println("Le bot a choisi : " + index);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						index = scanner.nextInt();
					}
					
					executeur.ajouterOeuvre(main.get(index));
					executeur.retirerMain(index);
					
				}
				else if(choix == 3) {
					System.out.println("Aucune carte n'a ete place dans vos oeuvres");
				}
			}
		}
		
		if(this.nom=="Panique") { // Defaussez la premiere carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.
			ArrayList<Carte> pile = victime.getPile();
			int taille = pile.size();
			
			if(taille == 0) {
				System.out.println("Votre adversaire n'a pas de carte dans sa pile");
			}
			else {
				System.out.println("Choisissez une carte de la pile de votre adversaire a defausser :");
				for(int i=0; i<taille; i++) {
					System.out.print(pile.get(i).getNom() + ": tapez " + i);
				}
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * taille;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				this.jeu.ajouterCarteFosse(pile.get(index));
				victime.retirerPile(index);
			}
			
			// Le joueur peut jouer une autre carte
			this.jeu.changerTour();
		}
		
		if(this.nom=="Dernier Souffle") { // Le joueur de votre choix defausse une carte de sa Main.
			ArrayList<Carte> main = victime.getMain();
			int taille = main.size();
			
			if(taille == 0) {
				System.out.println("La main du joueur " + victime.getNom() + " est vide.");
			}
			else if(taille > 0) {
				System.out.println("Joueur " + victime.getNom() + ", vous devez defosser une carte de votre main parmis :");
				for(int i=0; i<taille; i++) {
					System.out.println(main.get(i).getNom() + ": tapez " + i);
				}
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * taille;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				this.jeu.ajouterCarteFosse(main.get(index));
				victime.retirerMain(index);
			}
		}
		
		if(this.nom=="Crise") { // Le rival de votre choix defausse une de ses Oeuvres.
			ArrayList<Carte> oeuvre = victime.getOeuvre();
			int taille = oeuvre.size();
			
			if(taille == 0) {
				System.out.println("Le joueur " + victime.getNom() + "n'a pas d'oeuvre");
			}
			else if(taille > 0) {
				System.out.println("Joueur " + victime.getNom() + ", choisissez une carte parmi vos oeuvres :");
				for (int i=0; i<taille; i++) {
					System.out.println(oeuvre.get(i) + ": tapez " + i);
				}
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * taille;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				this.jeu.ajouterCarteFosse(oeuvre.get(index));
				victime.retirerOeuvre(index);
			}
		}
		
		if(this.nom=="Roulette") { // Defaussez jusqu’a 2 cartes de votre Main. Vous pouvez ensuite puiser a la Source autant de carte(s) + 1.			
			int totalAPiocher = 1;
			
			if(executeur.getMain().size() == 0) {
				System.out.println("Vous n'avez pas de carte dans votre main");
				totalAPiocher = 1;
			}
			else if(executeur.getMain().size() == 1) {
				System.out.println("Vous pouvez defausser votre carte : " + executeur.getMain().get(0).getNom() + ", oui: tapez 1, non: tapez 2");
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 2 + 1;
					choix = (int) al;
					System.out.println("Le bot a choisi : " + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				}
				
				if(choix == 1) {
					this.jeu.ajouterCarteFosse(executeur.getMain().get(0));
					executeur.retirerMain(0);
					totalAPiocher = 2;
				}
				else if(choix == 2) {
					System.out.println("Aucune carte n'a ete defaussee");
					totalAPiocher = 1;
				}
			}
			else if(executeur.getMain().size() > 1) {
				System.out.println("Vous avez " + executeur.getMain().size() + " cartes dans votre main, voulez-vous en defausser ? (oui: tapez 1, non: tapez 2)");
				int choix = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * 2 + 1;
					choix = (int) al;
					System.out.println("Le bot a choisi : " + choix);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					choix = scanner.nextInt();
				};
				
				if(choix == 2) {
					System.out.println("Aucune carte n'a ete defaussee");
					totalAPiocher = 1;
				}
				else if(choix == 1) {
					System.out.println("Choisissez une carte a defausser :");
					for(int i=0; i<executeur.getMain().size(); i++) {
						System.out.println(executeur.getMain().get(i).getNom() + ": tapez " + i);
					}
					int index = 0;
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * executeur.getMain().size();
						index = (int) al;
						System.out.println("Le bot a choisi : " + index);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						index = scanner.nextInt();
					}
					
					this.jeu.ajouterCarteFosse(executeur.getMain().get(index));
					executeur.retirerMain(index);
					
					totalAPiocher = totalAPiocher + 1;
					
					System.out.println("Voulez-vous defausser une autre carte ? (oui: tapez 1, non: tapez 2)");
					int choix2 = 0;
					if(executeur.getNom() == "bot1") {
						double al = Math.random() * 2 + 1;
						choix2 = (int) al;
						System.out.println("Le bot a choisi : " + choix2);
					}
					else {
						Scanner scanner = new Scanner(System.in);
						choix2 = scanner.nextInt();
					};
					
					if(choix2 == 2) {
						System.out.println("Une seule carte a ete defaussee");
					}
					else if(choix == 1) {
						System.out.println("Choisissez une carte a defausser :");
						for(int i=0; i<executeur.getMain().size(); i++) {
							System.out.println(executeur.getMain().get(i).getNom() + ": tapez " + i);
						}
						if(executeur.getNom() == "bot1") {
							double al = Math.random() * executeur.getMain().size();
							index = (int) al;
							System.out.println("Le bot a choisi : " + index);
						}
						else {
							Scanner scanner = new Scanner(System.in);
							index = scanner.nextInt();
						}
						
						this.jeu.ajouterCarteFosse(executeur.getMain().get(index));
						executeur.retirerMain(index);
						
						totalAPiocher = totalAPiocher + 1;
					}
				}
				
				// Pioche des cartes
				
				if(this.jeu.getSource().size() < totalAPiocher) {
					this.jeu.completerSource();
				}
				
				for(int i=0; i<totalAPiocher; i++) {
					Carte carte = this.jeu.getSource().get(this.jeu.getSource().size() - 1);
					executeur.ajouterMain(carte);
					this.jeu.retirerCarteSource(this.jeu.getSource().size());
					System.out.println("Carte piochee : " + carte.getNom());
					if(i++ < totalAPiocher) {
						System.out.println("Voulez vous piocher une autre carte ? 1: oui, 2: non");
						int choix2 = 0;
						if(executeur.getNom() == "bot1") {
							double al = Math.random() * 2 + 1;
							choix2 = (int) al;
							System.out.println("Le bot a choisi : " + choix2);
						}
						else {
							Scanner scanner2 = new Scanner(System.in);
							choix2 = scanner2.nextInt();
						}
						if(choix2 == 2) {
							break;
						}
					}
				}
			}
			
		}
		
		if(this.nom=="Fournaise") { // Defaussez les 2 premieres cartes de la Vie Future d'un rival.
			int taille = victime.getVieFuture().size();
			
			if(taille == 0) {
				System.out.println("Le joueur " + victime.getNom() + " n'a pas de carte dans sa vie future");
			}
			else if(taille == 1) {
				System.out.println("Votre adversaire n'a qu'une seule carte dans sa vie future, elle a ete defossee");
				Carte carte = victime.getVieFuture().get(0);
				victime.retirerVieFuture(0);
				this.jeu.ajouterCarteFosse(carte);
			}
			else if(taille>1) {
				System.out.println("Les deux premieres cartes de la vie future de votre adversaire ont ete defossees");
				for(int i=0; i<2; i++) {
					Carte carte = victime.getVieFuture().get(victime.getVieFuture().size() - 1);
					victime.retirerVieFuture(victime.getVieFuture().size() - 1);
					this.jeu.ajouterCarteFosse(carte);
				}
			}
		}
		
		if(this.nom=="Vengeance") { // Defaussez l’Oeuvre Exposee d’un rival.
			int taille = victime.getOeuvre().size();
			
			if(taille==0) {
				System.out.println("Le joueur " + victime.getNom() + " n'a pas de carte dans ses oeuvres");
			}
			else if(taille > 0) {
				Carte carte = victime.getOeuvre().get(taille - 1);
				this.jeu.ajouterCarteFosse(carte);
				victime.retirerOeuvre(taille - 1);
				System.out.println("L'oeuvre exposee du joueur " + victime.getNom() + " a ete defaussee");
			}
		}
		
		if(this.nom=="Bassesse") { // Defaussez au hasard 2 cartes de la Main d’un rival.
			int taille = victime.getMain().size();
			
			if (taille == 0) {
				System.out.println("Votre adversaire n'a pas de carte en main");
			}
			if(taille == 1) {
				System.out.println("Votre adversaire avait une carte en main, elle a ete defaussee");
				Carte carte = victime.getMain().get(0);
				this.jeu.ajouterCarteFosse(carte);
				victime.retirerMain(0);
			}
			if(taille == 2) {
				System.out.println("Votre adversaire avait deux cartes en main, elles ont ete defaussees");
				for(int i=0; i<2; i++) {
					Carte carte = victime.getMain().get(0);
					this.jeu.ajouterCarteFosse(carte);
					victime.retirerMain(0);
				}
			}
			if(taille > 2) {
				System.out.println("Votre adversaire avait " + taille + " cartes en main, 2 ont ete defaussees au hasard");
				for(int i=0; i<2; i++) {
					int nombreAleatoire = (int) Math.random() * (victime.getMain().size() - 1);
					Carte carte = victime.getMain().get(nombreAleatoire);
					this.jeu.ajouterCarteFosse(carte);
					victime.retirerMain(nombreAleatoire);
				}
			}
		}
		
		if(this.nom=="Incarnation") { // Choisissez une de vos Oeuvres. Copiez son pouvoir.
			int taille = executeur.getOeuvre().size();
			
			if(taille == 0) {
				System.out.println("Vous n'avez pas d'oeuvre");
			}
			else if(taille > 0) {
				System.out.println("Choisissez une de vos oeuvres :");
				for(int i=0; i<taille; i++) {
					System.out.println(executeur.getOeuvre().get(i) + ": tapez " + i);
				}
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * taille;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				executeur.getOeuvre().get(index).executerPouvoir(executeur, victime);
			}
		}
		
		if(this.nom=="Mimetisme") { // Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposee.
			int taille = victime.getOeuvre().size();
			
			if(taille == 0) {
				System.out.println("Votre rival n'a pas d'oeuvre");
			}
			else if(taille > 0) {
				System.out.println("Choisissez une de ses oeuvres :");
				for(int i=0; i<taille; i++) {
					System.out.println(victime.getOeuvre().get(i) + ": tapez " + i);
				}
				int index = 0;
				if(executeur.getNom() == "bot1") {
					double al = Math.random() * taille;
					index = (int) al;
					System.out.println("Le bot a choisi : " + index);
				}
				else {
					Scanner scanner = new Scanner(System.in);
					index = scanner.nextInt();
				}
				
				victime.getOeuvre().get(index).executerPouvoir(executeur, victime);
			}
		}
	}
    /**
     * Getter pour le nom de la carte.
     * @return Le nom de la carte.
     */
	public String getNom() {
		return nom;
	}
    /**
     * Setter pour le nom de la carte.
     * @param nom Le nouveau nom de la carte.
     */
	public void setNom(String nom) {
		this.nom = nom;
	}
    /**
     * Getter pour la couleur de la carte.
     * @return La couleur de la carte.
     */
	public Couleur getCouleur() {
		return couleur;
	}
    /**
     * Setter pour la couleur de la carte.
     * @param couleur La nouvelle couleur de la carte.
     */

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

    /**
     * Getter pour le nombre de points de la carte.
     * @return Le nombre de points de la carte.
     */
	public int getPoint() {
		return point;
	}
	   /**
     * Setter pour le nombre de points de la carte.
     * @param point Le nouveau nombre de points de la carte.
     */
	public void setPoint(int point) {
		this.point = point;
	}
	  /**
     * Methode permettant d'obtenir une representation textuelle de la carte.
     * @return Une chaîne de caracteres representant la carte.
     */
	public String toString() {
        return "Carte{" +
                "nom='" + this.nom + '\'' +
                ", couleur=" + this.couleur +
                ", point=" + this.point +
                '}';
    }
	
}
