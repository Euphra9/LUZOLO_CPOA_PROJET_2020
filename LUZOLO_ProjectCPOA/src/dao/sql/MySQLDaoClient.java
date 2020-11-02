package dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import dao.interfaces.IDaoClient;
import main.Connexion;
import model.metier.Client;



public class MySQLDaoClient implements IDaoClient{
	private static MySQLDaoClient instance;
	public static ArrayList<Client> al = new ArrayList<Client>();
	public static PreparedStatement requete;
	int nbLignes;
	
	private MySQLDaoClient(){
		
	}
	
	public static MySQLDaoClient getInstance(){
		if(instance==null){
			instance=new MySQLDaoClient();
		}
		return instance;
	}
	
	@Override
	public Client getById(int id_client) {
		
		Client c1=null;
		String str1="select * from Client where id_client=?";
		al.clear();//vider ArrayList
		try {
			//partie SQL
			requete=Connexion.maConnexion.prepareStatement(str1);
			requete.setInt(1, id_client);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_client");
				String res2=resset.getString("nom");
				String res3=resset.getString("prenom");
				System.out.println(res1+ " "+res2+" "+res3);
				c1=new Client(res1, res2, res3);
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
	public boolean create(Client client) {
		String str2="insert into Client values(?,?,?,0,0,0,0,0,0,0)";
		try{
			requete=Connexion.maConnexion.prepareStatement(str2);
			requete.setInt(1, client.getId_client());
			requete.setString(2, client.getNom());
			requete.setString(3, client.getPrenom());
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
	public boolean update(Client client) {
		String str3="Update client set nom=?, prenom=? where id_client=?";
		try{
			requete=Connexion.maConnexion.prepareStatement(str3);
			requete.setString(1, client.getNom());
			requete.setString(2, client.getPrenom());
			requete.setInt(3, client.getId_client());
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
	public boolean delete(Client client) {
		String str4="delete from client where id_client=?";
		try {
			requete=Connexion.maConnexion.prepareStatement(str4);
			requete.setInt(1, client.getId_client());
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
	public ArrayList<Client> findAll() {
		String str5="select * from client";
		al.clear();//vider ArrayList
		try {
			requete=Connexion.maConnexion.prepareStatement(str5);
			ResultSet resset=requete.executeQuery();
			while(resset.next()) {
				int res1=resset.getInt("id_client");
				String res2=resset.getString("nom");				
				String res3=resset.getString("prenom");
				al.add(new Client(res1, res2, res3));
			}
			for(Client x: al) {//afficher tous les elements de ArrayList
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
	public List<Client> getAllClients() {
		
		return al;
	}
}
