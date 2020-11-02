package model.metier;

import java.util.List;

/**
 * Pojo classe metier Client
 * 
 * @author ȫ���
 *
 */
public class Client {
	private int id_client;
	private String nom;
	private String prenom;

	// un Client realise 0 ou plusieurs commandes...
	private List<Commande> commandes;

	public Client(int id_client, String nom, String prenom) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Client(int id_client) {
		this.id_client = id_client;
	}

	public int getId_client() {
		return id_client;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_client;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Client other = (Client) obj;
		
		if (id_client != other.id_client)
			return false;
		
		return true;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Client other = (Client) obj;

		if (id_client != other.id_client)
			return false;

		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;

		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}*/

	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

}
