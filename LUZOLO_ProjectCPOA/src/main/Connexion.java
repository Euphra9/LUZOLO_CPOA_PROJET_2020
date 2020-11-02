package main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion {
	
	

	public static Connection maConnexion = null;
	
	
	
	
	
	public Connection creeConnexion() {
		
		String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/luzolo1u_ProjetCPOA"; 
		url += "?serverTimezone=Europe/Paris";
		String login = "luzolo1u_appli";
		String pwd = "31903438";

	
		try {
			
			maConnexion = DriverManager.getConnection(url, login, pwd);
			if (maConnexion!= null )
			{
				System .out .println ( " Connexion réussie à la base de données MySQL" ); 
				}

			} catch (SQLException sqle) {
			System.out.println("Erreur de connexion \n" + sqle.getMessage()); }
			return maConnexion; }

	
	public static void Deconnection() {
		try {
			if(maConnexion!=null) {
				maConnexion.close();
				System.out.println("deconnexion reussie de la base de données MySQL");	
			}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				System.out.println(sqle.getMessage());
			}
		}
	//---------------------REQUETE CATEGORIE---------------------------------
	public PreparedStatement createCategorieStatement() throws SQLException {
		String sql="insert Categorie (titre,visuel) values(?,?)";
		return maConnexion.prepareStatement(sql);
	}



	

	
	//---------------------REQUETE CLIENT---------------------------------

	
	public PreparedStatement viewClientStatement() throws SQLException {
		
		String sql="SELECT id_client,nom,prenom FROM Client";
		return maConnexion.prepareStatement(sql);
		
	}
	public PreparedStatement createClientStatement() throws SQLException {
		// TODO Auto-generated method stub
		String sql="insert into Client (nom,prenom,identifiant,mot_de_passe,"
				+ " adr_numero,adr_voie,adr_code_postal,adr_ville, adr_pays) values(?,?,?,?,?,?,?,?,?)";
		return maConnexion.prepareStatement(sql);
	}
	
	public PreparedStatement updateClStatement() throws SQLException {
		// TODO Auto-generated method stub
		String sql="UPDATE Client SET nom=?,prenom=?,identifiant=?,mot_de_passe=?"
				+ "adr_numero=?,adr_voie=?,adr_code_postal=?,adr_ville=?,adr_pays=? "
				+ "WHERE id_client=?";
		
		return maConnexion.prepareStatement(sql);
	}
	
	/*public PreparedStatement deleteClientStatement() throws SQLException {
		String sql="DELETE FROM Client WHERE id_client="+id;
		return maConnexion.prepareStatement(sql);
	}*/
	
	

	}

	

