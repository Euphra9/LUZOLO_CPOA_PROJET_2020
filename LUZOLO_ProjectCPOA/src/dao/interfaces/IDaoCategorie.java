package dao.interfaces;

import java.util.List;

import model.metier.*;

public interface IDaoCategorie extends IDao<Categorie>{
	List<Categorie> getAllCategories();
}
