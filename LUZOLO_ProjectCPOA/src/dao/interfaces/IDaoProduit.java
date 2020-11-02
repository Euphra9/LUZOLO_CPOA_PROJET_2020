package dao.interfaces;

import java.util.List;

import model.metier.*;

/*Interface DAO produit qui etend l'interface principale CRUD;*/
public interface IDaoProduit extends IDao<Produit>{
	List<Produit> getAllProduits();
}
