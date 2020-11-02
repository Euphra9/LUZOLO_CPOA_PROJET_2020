package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import dao.interfaces.IDaoCategorie;
import dao.interfaces.IDaoCategorie.*;
import main.Connexion;
import model.metier.*;


public class MySQLDaoCategorie implements IDaoCategorie {//il faut implements IDaoCategorie ou IDao<Categorie>
	
	private static MySQLDaoCategorie instance;
	public static ArrayList<Categorie> al = new ArrayList<Categorie>();
	public static PreparedStatement requete;
	int nbLignes;
	
	private  MySQLDaoCategorie() {		
		
	}
	
	
	public static MySQLDaoCategorie getInstance() {
		if (instance==null) {
			
			instance = new MySQLDaoCategorie();
		}
		return instance;
	}
	
	@Override
	public Categorie getById(int id_categorie) {
		String str1="select * from categorie where id_categorie=?";
		Categorie cate=null;
		//al.clear();//vider ArrayList
		try {
			//partie SQL
			requete=Connexion.maConnexion.prepareStatement(str1);
			requete.setInt(1, id_categorie);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_categorie");
				String res2=resset.getString("titre");
				String res3=resset.getString("visuel");
				cate=new Categorie(res1, res2, res3);
			}
			return cate;
		}
		catch(SQLException sqle) {
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());	
		}
		catch(Exception sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
	
		return cate;
	}
	
	@Override
	public boolean create(Categorie categorie) {
		String str2="insert into categorie values(?,?,?)";
		try {
			requete=Connexion.maConnexion.prepareStatement(str2);
			requete.setInt(1, categorie.getId_categorie());
			requete.setString(2, categorie.getTitre());
			requete.setString(3, categorie.getVisuel());
			//requete.executeUpdate();
			System.out.println("Successfully added");
			nbLignes = requete.executeUpdate();
		}catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			System.out.println(ime.getMessage());	
		}
		catch(SQLException sqle) {
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		
		return nbLignes==1;
		
	}
	
	@Override
	public boolean update(Categorie categorie) {
		String str3 ="Update categorie set titre=?, visuel=?, where id_categorie=?";
		try {
			requete=Connexion.maConnexion.prepareStatement(str3);
			requete.setInt(1, categorie.getId_categorie());
			requete.setString(2, categorie.getTitre());
			requete.setString(3, categorie.getVisuel());
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
	public boolean delete(Categorie categorie) {
		String str4="delete from categorie where id_categorie=?";
		try {
			requete=Connexion.maConnexion.prepareStatement(str4);
			requete.setInt(1, categorie.getId_categorie());
			//requete.executeUpdate();
			System.out.println("Successfully deleted");
			nbLignes = requete.executeUpdate();
		}catch(InputMismatchException ime) {
			System.out.println("Erreur de saisie. Veuillez saisir correctement.");
			System.out.println(ime.getMessage());	
		}
		catch(SQLException sqle) {
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		
		return nbLignes==1;
	}
	
	@Override
	public ArrayList<Categorie> findAll() {
		String str5 ="select*from categorie";
		al.clear();//vider ArrayList
		try {
			requete=Connexion.maConnexion.prepareStatement(str5);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_categorie");
				String res2=resset.getString("titre");
				String res3=resset.getString("visuel");
				al.add(new Categorie(res1, res2, res3));
			}
			for(Categorie x: al) {//afficher tous les elements de ArrayList
				System.out.println(x);
				
			}
		}
		catch(SQLException sqle) {
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());	
		}
		return al;
	}


	@Override
	public List<Categorie> getAllCategories() {
		return al;
	}
		
	
	
	
}
