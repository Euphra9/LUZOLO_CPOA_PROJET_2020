package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import dao.interfaces.IDaoProduit;
import main.Connexion;
import model.metier.Produit;




public class MySQLDaoProduit implements  IDaoProduit{
	private static MySQLDaoProduit instance;
	public static ArrayList<Produit> al=new ArrayList<Produit>();
	public static PreparedStatement requete;
	int nbLignes;
	
	private MySQLDaoProduit(){

	}
	public static MySQLDaoProduit getInstance(){
		if(instance==null){
			instance=new MySQLDaoProduit();
		}
		return instance;
	}
	@Override
	public Produit getById(int id_produit) {
		String str1="select * from produit where id_produit=?";
		Produit p=null;
		//al.clear();//vider ArrayList
		try {
			//partie SQL
			requete=Connexion.maConnexion.prepareStatement(str1);
			requete.setInt(1, id_produit);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_produit");
				String res2=resset.getString("nom");
				String res3=resset.getString("description");
				double res4=resset.getDouble("tarif");
				String res5=resset.getString("visuel");
				int res6=resset.getInt("id_categorie");
				p= new Produit(res1,res2,res3,res4,res5,res6);
				
			}
			return p;
		}
		catch(SQLException sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		catch(Exception sqle){
			System.out.println("SQL Syntaxe Erreur.");
			System.out.println(sqle.getMessage());
		}
		return p;
	}
	
	@Override
	public boolean create(Produit produit) {
		String str2="insert into produit values(?,?,?,?,?,?)";
		try{
			requete=Connexion.maConnexion.prepareStatement(str2);
			requete.setInt(1, produit.getId_produit());
			requete.setString(2, produit.getNom());
			requete.setString(3, produit.getDescription());
			requete.setDouble(4, produit.getTarif());
			requete.setString(5, produit.getVisuel());
			requete.setInt(6, produit.getId_categorie());
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
	public boolean update(Produit produit) {
		String str3="Update produit set nom=?, description=?, tarif=?, visuel=?"
					+ ", id_categorie=? where id_produit=?";
		try{
			requete=Connexion.maConnexion.prepareStatement(str3);
			requete.setInt(1, produit.getId_produit());
			requete.setString(2, produit.getNom());
			requete.setString(3, produit.getDescription());
			requete.setDouble(4, produit.getTarif());
			requete.setString(5, produit.getVisuel());
			requete.setInt(6, produit.getId_categorie());
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
	public boolean delete(Produit produit) {
		String str4="delete from produit where id_produit=?";
		try {
			requete=Connexion.maConnexion.prepareStatement(str4);
			requete.setInt(1, produit.getId_produit());
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
	public ArrayList<Produit> findAll() {
		String str5="select * from produit";
		al.clear();//vider ArrayList
		try {
			requete=Connexion.maConnexion.prepareStatement(str5);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_produit");
				String res2=resset.getString("nom");
				String res3=resset.getString("description");
				double res4=resset.getDouble("tarif");
				String res5=resset.getString("visuel");
				int res6=resset.getInt("id_categorie");
				al.add(new Produit(res1, res2, res3, res4, res5, res6));
				
			}
			for(Produit x: al) {//afficher tous les elements de ArrayList
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
	public List<Produit> getAllProduits() {
		// TODO Auto-generated method stub
		return al;
	}
}
