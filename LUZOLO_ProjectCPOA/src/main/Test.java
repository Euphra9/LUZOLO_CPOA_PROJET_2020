package main;

import java.sql.SQLException;


public class Test {

	public static void main(String[] args) throws SQLException{
		Connexion co =new Connexion();
		co.creeConnexion();
		new Menu();
		
	}
}
