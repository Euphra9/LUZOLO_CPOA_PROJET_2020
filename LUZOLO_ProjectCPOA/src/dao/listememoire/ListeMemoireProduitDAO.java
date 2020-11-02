package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.*;
import model.metier.*;

public class ListeMemoireProduitDAO implements IDaoProduit {
	
	private static ListeMemoireProduitDAO instance;
	private List<Produit> donnees;
	
	
	public static ListeMemoireProduitDAO getInstance() {
		
		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}
		return instance;
	}
	
	private ListeMemoireProduitDAO() {
		this.donnees = new ArrayList<Produit>();
		
		this.donnees.add(new Produit(3, "iphone", "rose",899.30,"iphone.png",1));
		this.donnees.add(new Produit(4, "airpods", "black",99.97,"airpods.png",3));
		this.donnees.add(new Produit(5,"ipad","it is white",800.35,"ipad.png",2));
		this.donnees.add(new Produit(6,"samsung","galaxy...",1012.34,"samsung.png",5));
		
	}
	
	
	@Override
	public boolean create(Produit produit) {
		produit.setId_produit(6);
		//Ne fonctionne que si l'objet metier est bien fait...
		while (this.donnees.contains(produit)) {
			produit.setId_produit(produit.getId_produit()+1);
		}
		boolean ok = this.donnees.add(produit);
		
		return ok;
	}
	
	@Override
	public boolean update(Produit objet) {
		int idx = this.donnees.indexOf(objet);
		if(idx==-1) {
			throw new IllegalArgumentException("Tentative de modification d'un produit innexistante");
		}else {
			this.donnees.set(idx, objet);
		}
		return true;
	}
	
	@Override
	public boolean delete(Produit objet) {
		Produit supprime;
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if(idx==-1) {
			throw new IllegalArgumentException("Tentative de suppression d'un produit innexistante");
		}else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}

	@Override
	public Produit getById(int id) {
		//Ne fonctionne que si l'objet metier est bien fait...
		//int idx = this.donnees.indexOf(new Produit(id,"ipad","it is white",800.35,"ipad.png",2));//sais pas pourquoi?
		int idx = this.donnees.indexOf(new Produit(id));
		if(idx==-1) {
			throw new IllegalArgumentException("Aucune produit ne possede cet identifiant");
		}else {
			return this.donnees.get(idx);
		}
	}
	
	@Override
	public ArrayList<Produit> findAll(){
		return (ArrayList<Produit>) this.donnees;
	}

	
	
	@Override
    public List<Produit> getAllProduits() {
        return this.donnees;
    }
	
	
}	
