package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import model.metier.Categorie;
import model.metier.Client;
import model.metier.Commande;
import model.metier.Ligne_commande;
import model.metier.Produit;


public class Menu {
	public static DaoFactory daof;
	private static DateTimeFormatter formatage=DateTimeFormatter.ofPattern("dd/mm/yyyy");
	public static Scanner sc=new Scanner(System.in);
	public static int choice;
	public static String typebdd;
	
	public Menu() {
		MenuBDD();
	}
	
	public static void MenuBDD(){
		System.out.println("Bienvenue ! Voulez-vous acc��der �� la BDD"
				+ "MySQL (1) ou ListeM��moire (2)? (Quitter 0)");
		choice=0;
		try{
			sc=new Scanner(System.in);
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("Erreur de saisie, veuillez saisir correctement");
			System.out.println(e.getMessage());
			MenuBDD();//en cas d'erreur, on rapelle MenuPrincipal()
		}
		switch (choice) 
		{
		case 1: typebdd="MySQL";
				System.out.println("MySQL connexion...");
				new Connexion();
				daof=DaoFactory.getDaoFactory(Persistance.MYSQL);
				MenuPrincipal();break;
		case 2: typebdd="ListeMemoire";
				daof=DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
				MenuPrincipal();break;
		case 0: System.out.println("Au revoir");
				System.exit(0);//arreter le programme normalement
				break;
		default: System.out.println("Erreur"); 
		}
		
	}
	
	
	/*------------------------------MAIN PAGE------------------------------------*/
	public static void MenuPrincipal() {
		System.out.println(typebdd+": Voulez-vous voir TVA (1), Client (2), Produit (3),"
				+ "\nFacture (4) ou Ligne_facture (5) ? (Quitter 0)");
		choice=0;
		try{
			sc=new Scanner(System.in);
			choice=sc.nextInt();
			switch (choice) 
			{
				case 1: SousMenuCategorie();break;
				case 2: SousMenuClient();break;
				case 3: SousMenuProduit();break;
				case 4: SousMenuCommande();break;
				case 5: SousMenuLigne_commande();break;
				case 0: Connexion.Deconnection();
						MenuBDD();
						break;
				default: System.out.println("Erreur"); 
						MenuPrincipal();
			}
		}
		catch(Exception e) {
			System.out.println("Erreur de saisie, veuillez saisir correctement");
			System.out.println(e.getMessage());
			MenuPrincipal();//en cas d'erreur, on rapelle MenuPrincipal()
		}
	}
	
	/*------------------------------CHOICE = CATEGORY ----------------------------------*/
	public static void SousMenuCategorie() {
		int id_categorie;
		String titre;
		String visuel;
		System.out.println("Voici la table de categorie : ");
		System.out.println("id_categorie titre visuel");
		//afficher toutes les donn��es de la table
		daof.getDaoCategorie().findAll();// modifier
		int choix=0;
		System.out.println("Voulez-vous Ajouter (1), Modifier (2), Supprimer (3) "
				+ "categorie ou \n(R��)Afficher (4) tous les categories, getById(5)? (Retourner 0)");
		try{
			sc=new Scanner(System.in);
			choix=sc.nextInt();	//il peut entrainer des erreurs, chaine possible
			switch (choix) 
			{
				case 1: //Ajouter
						System.out.println("Ajouter: Entrer un id_categorie:");
						id_categorie=sc.nextInt();
						System.out.println("Ajouter: Entrer un titre:");
						sc.nextLine();//vider la memoire \n
						titre=sc.nextLine();
						System.out.println("Ajouter: Entrer un visuel:");
						visuel=sc.nextLine();
						daof.getDaoCategorie().create(new Categorie(id_categorie, titre, visuel));
						break;
				case 2: //Modifier
						System.out.println("Modifier: Entrer un id_categorie :");
						id_categorie=sc.nextInt();
						System.out.println("Modifier: Entrer un titre:");
						sc.nextLine();//vider la memoire \n
						titre=sc.nextLine();
						System.out.println("Modifier: Entrer un visuel:");
						visuel=sc.nextLine();
						daof.getDaoCategorie().update(new Categorie(id_categorie, titre, visuel));
						break;
				case 3: //Supprimer
						System.out.println("Supprimer : Entrer un id_categorie:");
						id_categorie=sc.nextInt();
						daof.getDaoCategorie().delete(new Categorie(id_categorie));
						break;
				case 4: //Afficher tout
						SousMenuCategorie();
//						afin d'afficher toutes les donn��es, on rapelle SousMenu
//						pour utiliser les deux lignes
//						System.out.println("Voici la table de categorie : ");
//						daof.getDaoCategorie().FindAll();
						break;
				case 5: //getById
						System.out.println("getById: Entrer un id_categorie");
						id_categorie=sc.nextInt();
						daof.getDaoCategorie().getById(id_categorie);
						break;
				case 0: MenuPrincipal();
						break;
				default: System.out.println("Erreur"); 
						 SousMenuCategorie();//en cas d'erreur, on reappeler SousMenu()
			}
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			SousMenuCategorie();
		}
	}
	
	
	/*-------------------------------CUSTOMER----------------------------------*/
	public static void SousMenuClient() {
		int id_client;
		String nom;
		String prenom;
		System.out.println("Voici la table de Client : ");
		System.out.println("id_client nom prenom");
		
		//afficher toutes les donnees de la table
		
		daof.getDaoClient().findAll();// modifier
		int choix=0;
		System.out.println("Voulez-vous Ajouter (1), Modifier (2), Supprimer (3) "
				+ "un Client ou \n(R��)Afficher (4) tous les Clients, getById(5)? (Retourner 0)");
		try{
			choix=sc.nextInt();	//il peut entrainer des erreurs, chaine possible
			switch (choix) 
			{
				case 1: //Ajouter
						System.out.println("Ajouter: Entrer un id_client:");
						id_client=sc.nextInt();
						System.out.println("Ajouter: Entrer un nom:");
						sc.nextLine();//vider la memoire \n
						nom=sc.nextLine();
						System.out.println("Ajouter: Entrer un prenom:");
//						pas besoin de vider la memoire, car sc.nextLine() vide automatiquement
						prenom=sc.nextLine();
						daof.getDaoClient().create(new Client(id_client, nom, prenom));
						break;
				case 2: //Modifier
						System.out.println("Modifier: Entrer un id_client :");
						id_client=sc.nextInt();
						System.out.println("Modifier: Entrer un nom:");
						sc.nextLine();//vider la memoire \n
						nom=sc.nextLine();
						System.out.println("Modifier: Entrer un prenom:");
						//pas besoin de vider la memoire, car sc.nextLine() vide automatiquement
						prenom=sc.nextLine();
						daof.getDaoClient().update(new Client(id_client, nom, prenom));
						break;
				case 3: //Supprimer
						System.out.println("Supprimer : Entrer un id_client:");
						id_client=sc.nextInt();
						daof.getDaoClient().delete(new Client(id_client));
						break;
				case 4: //Afficher tout
						SousMenuClient();
//						afin d'afficher toutes les donn��es, on rapelle SousMenu
//						pour utiliser les deux lignes
//						System.out.println("Voici la table de Client : ");
//						daof.getDaoClient().findAll();
						break;
				case 5: //getById
						System.out.println("getById: Entrer un id_Client");
						id_client=sc.nextInt();
						daof.getDaoClient().getById(id_client);
						break;
				case 0: MenuPrincipal();
						break;
				default: System.out.println("Erreur"); 
						 SousMenuClient();//en cas d'erreur, on reappeler SousMenu()
			}
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			SousMenuClient();
		}
	}
	
	
	/*-------------------------------PRODUCT-------------------------------------------*/
	public static void SousMenuProduit() {
		int id_produit;
		String nom;
		String description;
		double tarif;
		String visuel;
		int id_categorie;
		
		System.out.println("Voici la table de Produit : ");
		System.out.println("id_produit nom description tarif visuel id_categorie");
		//afficher toutes les donn��es de la table
		daof.getDaoProduit().findAll();
		int choix=0;
		System.out.println("Voulez-vous Ajouter (1), Modifier (2), Supprimer (3) "
				+ "un Produit ou \n(R��)Afficher (4) tous les Produits, getById(5)? (Retourner 0)");
		try{
			choix=sc.nextInt();	//il peut entrainer des erreurs, chaine possible
			switch (choix) 
			{
				case 1: //Ajouter
						System.out.println("Ajouter: Entrer un id_produit:");
						id_produit=sc.nextInt();
						System.out.println("Ajouter: Entrer un nom:");
						sc.nextLine();//vider la memoire \n
						nom=sc.nextLine();
						System.out.println("Ajouter: Entrer la description:");
						sc.nextLine();//vider la memoire \n
						description=sc.nextLine();
						System.out.println("Ajouter: Entrer un tarif:");
						tarif=sc.nextDouble();
						System.out.println("Ajouter: Entrer un visuel:");
						sc.nextLine();//vider la memoire \n
						visuel=sc.nextLine();
						System.out.println("Ajouter: Entrer un id_categorie:");
						id_categorie=sc.nextInt();
						daof.getDaoProduit().create(new Produit (id_produit, nom, description, tarif, visuel, id_categorie));
						break;
						
				case 2: //Modifier
					System.out.println("Modifier: Entrer un id_produit:");
					id_produit=sc.nextInt();
					System.out.println("Modifier: Entrer un nom:");
					sc.nextLine();//vider la memoire \n
					nom=sc.nextLine();
					System.out.println("Modifier: Entrer la description:");
					sc.nextLine();//vider la memoire \n
					description=sc.nextLine();
					System.out.println("Modifier: Entrer un tarif:");
					tarif=sc.nextDouble();
					System.out.println("Modifier: Entrer un visuel:");
					sc.nextLine();//vider la memoire \n
					visuel=sc.nextLine();
					System.out.println("Modifier: Entrer un id_categorie:");
					id_categorie=sc.nextInt();
					daof.getDaoProduit().update(new Produit (id_produit, nom, description, tarif, visuel, id_categorie));
					break;
				case 3: //Supprimer
						System.out.println("Supprimer : Entrer un id_produit:");
						id_produit=sc.nextInt();
						daof.getDaoProduit().delete(new Produit(id_produit));
						break;
				case 4: //Afficher tout
						SousMenuProduit();
//						afin d'afficher toutes les donn��es, on rapelle SousMenu
//						pour utiliser les deux lignes
//						System.out.println("Voici la table de Produit : ");
//						daof.getDaoProduit().findAll();
						break;
				case 5: //getById
						System.out.println("getById: Entrer un id_produit");
						id_produit=sc.nextInt();
						daof.getDaoProduit().getById(id_produit);
						break;
				case 0: MenuPrincipal();
						break;
				default: System.out.println("Erreur"); 
						 SousMenuProduit();//en cas d'erreur, on reappeler SousMenu()
			}
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			SousMenuProduit();
		}
	}
	
	
	/*-------------------------------ORDER-------------------------------------------*/
	public static void SousMenuCommande() {
		int id_commande, id_client;
		LocalDate date_commande;
		System.out.println("Voici la table de Commande : ");
		System.out.println("id_facture id_client date_commande");
		//afficher toutes les donn��es de la table
		daof.getDaoCommande().findAll();
		int choix=0;
		System.out.println("Voulez-vous Ajouter (1), Modifier (2), Supprimer (3) "
				+ "une Facture ou \n(R��)Afficher (4) toutes les Commandes, getById(5)? (Retourner 0)");
		try{
			choix=sc.nextInt();	//il peut entrainer des erreurs, chaine possible
			switch (choix) 
			{
				case 1: //Ajouter
						System.out.println("Ajouter: Entrer un id_commande:");
						id_commande=sc.nextInt();
						System.out.println("Ajouter: Entrer une date sous forme (dd/MM/yyyy):");
						sc.nextLine();//vider la memoire \n
						date_commande=LocalDate.parse(sc.nextLine(), formatage);
						System.out.println("Ajouter: Entrer un id_client:");
						id_client=sc.nextInt();
						daof.getDaoCommande().create(new Commande(id_commande, date_commande, id_client));
						break;
				case 2: //Modifier
						System.out.println("Modifier: Entrer un id_commande:");
						id_commande=sc.nextInt();
						System.out.println("Modifier: Entrer une date sous forme (dd/MM/yyyy):");
						sc.nextLine();//vider la memoire \n
						date_commande=LocalDate.parse(sc.nextLine(), formatage);
						System.out.println("Modifier: Entrer un id_client:");
						id_client=sc.nextInt();
						daof.getDaoCommande().update(new Commande(id_commande, date_commande, id_client));
						break;
				case 3: //Supprimer
						System.out.println("Supprimer : Entrer un id_commande:");
						id_commande=sc.nextInt();
						daof.getDaoCommande().delete(new Commande(id_commande));
						break;
				case 4: //Afficher tout
						SousMenuCommande ();
//						afin d'afficher toutes les donn��es, on rapelle SousMenuFacture
//						pour utiliser les deux lignes
//						System.out.println("Voici la table de Commande : ");
//						daof.getDaoCommande().findAll();
						break;
				case 5: //getById
						System.out.println("Entrer un id_commande");
						id_commande=sc.nextInt();
						daof.getDaoCommande().getById(id_commande);
						break;
				case 0: MenuPrincipal();
						break;
				default: System.out.println("Erreur"); 
						 SousMenuCommande();//en cas d'erreur, on reappeler SousMenu()
			}
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			SousMenuCommande();
		}
	}
	
	
	/*-------------------------------COMMAND LINE-------------------------------------------*/
	public static void SousMenuLigne_commande() {
		int id_commande;
		int id_produit;
		int quantite;
		double tarif_unitaire;
		
		
		System.out.println("Voici la table de Ligne_commande : ");
		System.out.println("id_commande id_produit quantite tarif_unitaire");
		//afficher toutes les donn��es de la table
		daof.getDaoLigne_commande().findAll();
		int choix=0;
		System.out.println("Voulez-vous Ajouter (1), Modifier (2), Supprimer (3) une Ligne_facture"
				+ " ou \n(R��)Afficher (4) toutes les Lignes_facture, getById(5)? (Retourner 0)");
		try{
			choix=sc.nextInt();	//il peut entrainer des erreurs, chaine possible
			switch (choix) 
			{
				case 1: //Ajouter
						System.out.println("Ajouter: Entrer un id_commande:");
						id_commande=sc.nextInt();
						System.out.println("Ajouter: Entrer un id_produit:");
						id_produit=sc.nextInt();
						System.out.println("Ajouter: Entrer un quantite:");
						quantite=sc.nextInt();
						System.out.println("Ajouter: Entrer un tarif_unitaire:");
						tarif_unitaire=sc.nextDouble();
						daof.getDaoLigne_commande().create(new Ligne_commande(id_commande, id_produit
																, quantite, tarif_unitaire));
						break;
				case 2: //Modifier
						System.out.println("Modifier: Entrer un id_commande:");
						id_commande=sc.nextInt();
						System.out.println("Modifier: Entrer un id_produit:");
						id_produit=sc.nextInt();
						System.out.println("Modifier: Entrer un quantite:");
						quantite=sc.nextInt();
						System.out.println("Modifier: Entrer un tarif_unitaire:");
						tarif_unitaire=sc.nextDouble();
						daof.getDaoLigne_commande().update(new Ligne_commande(id_commande, id_produit
															, quantite, tarif_unitaire));
						break;
				case 3: //Supprimer
						System.out.println("Supprimer : Entrer un id_commande:");
						id_commande=sc.nextInt();
						System.out.println("Supprimer : Entrer un id_produit:");
						id_produit=sc.nextInt();
						daof.getDaoLigne_commande().delete(new Ligne_commande(id_commande, id_produit));
						break;
				case 4: //Afficher tout
						SousMenuLigne_commande();
//						afin d'afficher toutes les donn��es, on rapelle SousMenu
//						pour utiliser les deux lignes
//						System.out.println("Voici la table de Ligne_commande : ");
//						daof.getDaoLigne_commande().findAll();
						break;
				case 5: //getById
						System.out.println("getById: Entrer un id_commande");
						id_commande=sc.nextInt();
//						System.out.println("getById: Entrer un id_produit");
//						id_produit=sc.nextInt();
						daof.getDaoLigne_commande().getById(id_commande);
						break;
				case 0: MenuPrincipal();
						break;
				default: System.out.println("Erreur"); 
						 SousMenuLigne_commande();//en cas d'erreur, on reappeler SousMenu()
			}
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			SousMenuLigne_commande();
		}
	}
}

