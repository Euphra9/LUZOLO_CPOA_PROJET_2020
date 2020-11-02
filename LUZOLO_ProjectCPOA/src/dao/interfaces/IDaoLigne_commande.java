package dao.interfaces;

import java.util.List;

import model.metier.*;


public interface IDaoLigne_commande extends IDao<Ligne_commande>{
	List<Ligne_commande> getAllLigne_commandes();

}
