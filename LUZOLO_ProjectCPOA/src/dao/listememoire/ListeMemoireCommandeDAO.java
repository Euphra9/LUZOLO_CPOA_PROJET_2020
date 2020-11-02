package dao.listememoire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.*;
import model.metier.*;

public class ListeMemoireCommandeDAO implements IDaoCommande {


	
	private static ListeMemoireCommandeDAO instance;
	private ArrayList<Commande> donnees;
	private DateTimeFormatter formatage=DateTimeFormatter.ofPattern("dd/MM/yyyy");



	public static ListeMemoireCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireCommandeDAO() {

		this.donnees = new ArrayList<Commande>();
		this.donnees.add(new Commande(1,  LocalDate.parse("30/09/2020",formatage), 2));
		this.donnees.add(new Commande(2,  LocalDate.parse("01/10/2020",formatage), 1));
		this.donnees.add(new Commande(3,  LocalDate.parse("01/01/2020",formatage), 6));
		this.donnees.add(new Commande(4,  LocalDate.parse("31/01/2020",formatage), 6));
		this.donnees.add(new Commande(5,  LocalDate.parse("31/01/2019",formatage), 2));

		
	}


	@Override
	public boolean create(Commande commande) {

		commande.setId_commande(2);
		// Ne fonctionne que si l'objet metier est bien fait...
		while (this.donnees.contains(commande)) {

			commande.setId_commande(commande.getId_commande() + 1);
		}
		boolean ok = this.donnees.add(commande);
		
		return ok;
	}

	@Override
	public boolean update(Commande commande) {
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(commande);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
		} else {
			
			this.donnees.set(idx, commande);
		}
		
		return true;
	}

	@Override
	public boolean delete(Commande commande) {

		Commande supprime;
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(commande);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return commande.equals(supprime);
	}

	@Override
	public Commande getById(int id) {
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(new Commande(id, LocalDate.parse("01/01/2010",formatage), 4));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune commande ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Commande> findAll() {
		return (ArrayList<Commande>) this.donnees;
	}

	@Override
	public List<Commande> getAllCommandes() {
		// TODO Auto-generated method stub
		return this.donnees;
	}
	
	
}