package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import dao.interfaces.IDaoCommande;
import main.Connexion;
import model.metier.Commande;


public class MySQLDaoCommande implements IDaoCommande{
	private static MySQLDaoCommande instance;
	public static ArrayList<Commande> al =new ArrayList<Commande>();
	public  static PreparedStatement requete;
	int nbLignes;
	
	private MySQLDaoCommande() {
		
	}
	
	public static MySQLDaoCommande getInstance() {
		if(instance==null) {
			instance=new MySQLDaoCommande();
		}
		return instance;
	}
	
	@Override
	public Commande getById(int id_commande) {
		String str1="select * from commande where id_commande=?";
		Commande c1=null;
		//al.clear();//vider ArrayList
		try {
			//partie SQL
			requete=Connexion.maConnexion.prepareStatement(str1);
			requete.setInt(1, id_commande);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_commande");
				LocalDate res2=resset.getDate("date_commande").toLocalDate();
				int res3=resset.getInt("id_client");
				c1=new Commande(res1, res2, res3);
			}
			return c1;
		}
		catch(SQLException sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		catch(Exception sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		return c1;
	}
	
	@Override
	public boolean create(Commande commande) {
		String str2="insert into commande values(?,?,?)";
		try{
			requete=Connexion.maConnexion.prepareStatement(str2);
			requete.setInt(1, commande.getId_commande());
			requete.setDate(2, java.sql.Date.valueOf(commande.getDate()));
			requete.setInt(3, commande.getId_client());
			//requete.executeUpdate();
			System.out.println("Successfully added");
			nbLignes = requete.executeUpdate();
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			System.out.println(ime.getMessage());
		}
		catch(SQLException sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		return nbLignes==1;
	}
	
	@Override
	public boolean update(Commande commande) {
		String str3="Update commande set id_client=?, date_commande=? where id_commande=?";
		try{
			requete=Connexion.maConnexion.prepareStatement(str3);
			requete.setInt(1, commande.getId_commande());
			requete.setDate(2, java.sql.Date.valueOf(commande.getDate()));
			requete.setInt(3, commande.getId_client());
			//requete.executeUpdate();
			System.out.println("Successfully modified");
			nbLignes = requete.executeUpdate();
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			System.out.println(ime.getMessage());
		}
		catch(SQLException sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		return nbLignes==1;
	}
	
	@Override
	public boolean delete(Commande commande) {
		String str4="delete from commande where id_commande=?";
		try {
			requete=Connexion.maConnexion.prepareStatement(str4);
			requete.setInt(1, commande.getId_commande());
			//requete.executeUpdate();
			System.out.println("Successfully deleted");
			nbLignes = requete.executeUpdate();
		}
		catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			System.out.println(ime.getMessage());
		}
		catch(SQLException sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		return nbLignes==1;
	}
	
	@Override
	public ArrayList<Commande> findAll() {
		String str5="select * from facture";
		al.clear();//vider ArrayList
		try {
			requete=Connexion.maConnexion.prepareStatement(str5);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_commande");
				LocalDate res2=resset.getDate("date_commande").toLocalDate();//de SQLDate a LocalDate
				//DateTimeFormatter formatage=DateTimeFormatter.ofPattern("dd/MM/yyyy");
				int res3=resset.getInt("id_client");	
				al.add(new Commande(res1, res2, res3));
			}
			for(Commande x: al) {//afficher tous les elements de ArrayList
				System.out.println(x);
			}
		}
		catch(SQLException sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		return al;
	}

	@Override
	public List<Commande> getAllCommandes() {
		
		return al;
	}
 
}
