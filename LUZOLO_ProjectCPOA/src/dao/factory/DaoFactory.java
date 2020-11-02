package dao.factory;

import dao.interfaces.IDaoCategorie;
import dao.interfaces.IDaoClient;
import dao.interfaces.IDaoCommande;
import dao.interfaces.IDaoLigne_commande;
import dao.interfaces.IDaoProduit;

public abstract class DaoFactory {
	public enum Persistance{
		MYSQL,LISTEMEMOIRE;
	}
	
	public static DaoFactory getDaoFactory(Persistance cible) {
		DaoFactory daoF = null;
		switch (cible) {
		case MYSQL:
			daoF = new MySQLDaoFactory();
			break;
		case LISTEMEMOIRE:
			daoF = new ListeMemoireDaoFactory();
			break;
		}
		
		return daoF;
	}
	
	public abstract IDaoCategorie getDaoCategorie();
	public abstract IDaoProduit getDaoProduit();
	public abstract IDaoClient getDaoClient();
	public abstract IDaoCommande getDaoCommande();
	public abstract IDaoLigne_commande getDaoLigne_commande();
	
}
