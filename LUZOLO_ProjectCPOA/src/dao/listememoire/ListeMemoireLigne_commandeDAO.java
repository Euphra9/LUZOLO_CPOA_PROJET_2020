package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.*;
import model.metier.*;


public class ListeMemoireLigne_commandeDAO implements IDaoLigne_commande{
	
	private static ListeMemoireLigne_commandeDAO instance;
	private List<Ligne_commande> donnees;


	public static ListeMemoireLigne_commandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireLigne_commandeDAO();
		}

		return instance;
	}

	private ListeMemoireLigne_commandeDAO() {

		this.donnees = new ArrayList<Ligne_commande>();

		this.donnees.add(new Ligne_commande(1, 3, 3, 10.92));
		this.donnees.add(new Ligne_commande(2, 4, 5, 21.04));
		this.donnees.add(new Ligne_commande(3, 4, 5, 11.00));
		this.donnees.add(new Ligne_commande(4, 4, 4, 21.04));
		this.donnees.add(new Ligne_commande(6, 4, 4, 43.02));
	}


	@Override
	public boolean create(Ligne_commande lico) {

		lico.setId_commande(6);
		// Ne fonctionne que si l'objet metier est bien fait...
		while (this.donnees.contains(lico)) {

			lico.setId_commande(lico.getId_commande() + 1);
		}
		boolean ok = this.donnees.add(lico);
		
		return ok;
	}

	@Override
	public boolean update(Ligne_commande lico) {
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(lico);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une ligne_commande inexistante");
		} else {
			
			this.donnees.set(idx, lico);
		}
		
		return true;
	}

	@Override
	public boolean delete(Ligne_commande lico) {

		Ligne_commande supprime;
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(lico);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une ligne_commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return lico.equals(supprime);
	}

	@Override
	public Ligne_commande getById(int id) {
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(new Ligne_commande(id, 2));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune ligne_commande ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Ligne_commande> findAll() {
		return (ArrayList<Ligne_commande>) this.donnees;
	}

	@Override
	public List<Ligne_commande> getAllLigne_commandes() {
		
		return this.donnees;
	}
	
	
}

