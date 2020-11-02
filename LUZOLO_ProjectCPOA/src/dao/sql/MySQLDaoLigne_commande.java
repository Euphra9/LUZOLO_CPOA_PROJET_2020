package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import dao.interfaces.IDaoLigne_commande;
import main.Connexion;
import model.metier.Ligne_commande;


public class MySQLDaoLigne_commande implements  IDaoLigne_commande {

	private static MySQLDaoLigne_commande instance;
	public static ArrayList<Ligne_commande> al=new ArrayList<Ligne_commande>();
	public static PreparedStatement requete;
	int nbLignes;
	
	private MySQLDaoLigne_commande(){

	}
	public static MySQLDaoLigne_commande getInstance(){
		if(instance==null){
			instance=new MySQLDaoLigne_commande();
		}
		return instance;
	}
	
	@Override
	public Ligne_commande getById(int id_commande) {
		String str1="select * from ligne_commande where id_commande=?";
		Ligne_commande lc=null;
		//al.clear();//vider ArrayList
		try {
			//partie SQL
			requete=Connexion.maConnexion.prepareStatement(str1);
			requete.setInt(1, id_commande);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_commande");
				int res2=resset.getInt("id_produit");
				int res3=resset.getInt("quantite");
				double res4=resset.getDouble("tarif_unitaire");
				lc=new Ligne_commande(res1, res2, res3, res4);
			}
			return lc;
		}
		catch(SQLException sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		catch(Exception sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		return lc;
	}
	
	@Override
	public boolean create(Ligne_commande lico) {
		String str2="insert into ligne_commande values(?,?,?,?)";
		try{
			requete=Connexion.maConnexion.prepareStatement(str2);
			requete.setInt(1, lico.getId_commande());
			requete.setInt(2, lico.getId_produit());
			requete.setInt(3, lico.getQuantite());
			requete.setDouble(4, lico.getTarif_unitaire());
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
	public boolean update(Ligne_commande lico) {
		String str3="Update ligne_commande set quantite=?, tarif_unitaire=?"
					+ " where id_commande=? and id_produit=?";
		try{
			requete=Connexion.maConnexion.prepareStatement(str3);
			requete.setInt(1, lico.getId_commande());
			requete.setInt(2, lico.getId_produit());
			requete.setInt(3, lico.getQuantite());
			requete.setDouble(4, lico.getTarif_unitaire());
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
	public boolean delete(Ligne_commande lico) {
		String str4="delete from ligne_commande where id_commande=? and id_produit=?";
		try {
			requete=Connexion.maConnexion.prepareStatement(str4);
			requete.setInt(1, lico.getId_commande());
			requete.setInt(2, lico.getId_produit());
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
	public ArrayList<Ligne_commande> findAll() {
		String str5="select * from ligne_commande";
		al.clear();//vider ArrayList
		try {
			requete=Connexion.maConnexion.prepareStatement(str5);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_commande");
				int res2=resset.getInt("id_produit");
				int res3=resset.getInt("quantite");
				double res4=resset.getDouble("tarif_unitaire");
				al.add(new Ligne_commande(res1, res2, res3, res4));
			}
			for(Ligne_commande x: al) {//afficher tous les elements de ArrayList
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
	public List<Ligne_commande> getAllLigne_commandes() {
		
		return al;
	}
	
}