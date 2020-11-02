package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.*;
import model.metier.*;

public class ListeMemoireClientDAO implements IDaoClient{

	
	private static ListeMemoireClientDAO instance;
	private List<Client> donnees;


	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}

		return instance;
	}

	private ListeMemoireClientDAO() {

		this.donnees = new ArrayList<Client>();
		this.donnees.add(new Client(1,"LUZOLO","Euphra"));
		this.donnees.add(new Client(2,"ALEX","Ines"));
		this.donnees.add(new Client(4,"ROBERT","Ines"));
		this.donnees.add(new Client(5,"ROBERT","Jennifer"));
		this.donnees.add(new Client(7,"Meline","Simon"));
	
		
	}


	@Override
	public boolean create(Client client) {

		client.setId_client(10);
		// Ne fonctionne que si l'objet metier est bien fait...
		while (this.donnees.contains(client)) {

			client.setId_client(client.getId_client() + 1);
		}
		boolean ok = this.donnees.add(client);
		
		return ok;
	}

	@Override
	public boolean update(Client client) {
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(client);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un client inexistante");
		} else {
			
			this.donnees.set(idx, client);
		}
		
		return true;
	}

	@Override
	public boolean delete(Client client) {

		Client supprime;
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(client);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un client inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return client.equals(supprime);
	}

	@Override
	public Client getById(int id) {
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(new Client(id, "nom", "prenom"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun client ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.donnees;
	}

	
	@Override
	public List<Client> getAllClients() {
		
		return this.donnees;
	}
	
	
}