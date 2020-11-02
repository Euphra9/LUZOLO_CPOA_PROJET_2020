package dao.factory;

import dao.interfaces.IDaoCategorie;
import dao.interfaces.IDaoClient;
import dao.interfaces.IDaoCommande;
import dao.interfaces.IDaoLigne_commande;
import dao.interfaces.IDaoProduit;
import dao.sql.MySQLDaoCategorie;
import dao.sql.MySQLDaoClient;
import dao.sql.MySQLDaoCommande;
import dao.sql.MySQLDaoLigne_commande;
import dao.sql.MySQLDaoProduit;

public class MySQLDaoFactory extends DaoFactory {
	
	@Override
	public IDaoCategorie getDaoCategorie() {
		return MySQLDaoCategorie.getInstance();
		
	}
	
	@Override
	public IDaoProduit getDaoProduit() {
		return MySQLDaoProduit.getInstance();
	}
	
	@Override
	public IDaoClient getDaoClient() {
		return MySQLDaoClient.getInstance();
	}
	
	@Override
	public IDaoCommande getDaoCommande() {
		return MySQLDaoCommande.getInstance();
	}
	
	@Override
	public IDaoLigne_commande getDaoLigne_commande() {
		return MySQLDaoLigne_commande.getInstance();
	}
	
}
