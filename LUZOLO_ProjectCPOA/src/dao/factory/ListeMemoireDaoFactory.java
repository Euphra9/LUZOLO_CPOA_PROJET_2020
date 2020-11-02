package dao.factory;

import dao.interfaces.IDaoCategorie;
import dao.interfaces.IDaoClient;
import dao.interfaces.IDaoCommande;
import dao.interfaces.IDaoLigne_commande;
import dao.interfaces.IDaoProduit;
import dao.listememoire.*;


public class ListeMemoireDaoFactory extends DaoFactory{
	
	@Override
	public IDaoCategorie getDaoCategorie() {
		return ListeMemoireCategorieDAO.getInstance();
	}
	
	@Override
	public IDaoClient getDaoClient() {
		return ListeMemoireClientDAO.getInstance();
	}
	
	@Override
	public IDaoProduit getDaoProduit() {
		return ListeMemoireProduitDAO.getInstance();
	}
	
	@Override
	public IDaoCommande getDaoCommande() {
		return ListeMemoireCommandeDAO.getInstance();
	}
	
	@Override
	public IDaoLigne_commande getDaoLigne_commande() {
		return ListeMemoireLigne_commandeDAO.getInstance();
	}
	
	
}
