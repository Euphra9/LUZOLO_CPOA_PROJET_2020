package dao.interfaces;

import java.util.List;

import model.metier.*;

public interface IDaoCommande extends IDao<Commande>{
	List<Commande> getAllCommandes();
}
