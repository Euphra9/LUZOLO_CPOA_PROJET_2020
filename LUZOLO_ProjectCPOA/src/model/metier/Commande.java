package model.metier;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * representation metier d'une 'commande'
 * une commande est realisee une date par un client
 * et contient plusieurs produits
 */
public class Commande {
	private int id_commande;
	private LocalDate date;
	//une commande est realisee par un client
	private int id_client;
	
	
	//ligne de commande:liste des produits.
	//une commande contient 1 ou n produits.
	private Map<Produit, Integer> produits;
	

	public Commande(int id_commande, LocalDate date, int id_client) {
		this.id_commande = id_commande;
		this.date = date;
		this.id_client = id_client;
	}


	public Commande(int id_commande) {
		this.id_commande = id_commande;
	}
	
	
	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getId_client() {
		return id_client;
	}


	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	
	/*return le montant total du panier(ligne de commande)*/
	public Double getMontantTotal() {
		//parcours de la table des produits...ligne de commande.
		Double resultat = 0d;
		for(Entry<Produit, Integer> entry : produits.entrySet()) {
			//pour chaque ligne de commande je recupere la cle(produit> et la valeur(quantite)
			Produit produit = entry.getKey();
			Integer quantite = entry.getValue();
			resultat = resultat + (produit.getTarif()*quantite);
		}
		return resultat;
	}
	
	
	
	/*Methode qui permet d'ajouter un produit à la ligne de commande.*/
	public void addProduit(Produit produit, int quantite) {
		if(produits == null) {
			produits = new HashMap<>();
		}
		produits.put(produit, quantite);
	}

	

	public Map<Produit, Integer> getProduits() {
		return produits;
	}

	// ��ʦûдsetProduits
	public void setProduits(Map<Produit, Integer> produits) {
		this.produits = produits;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id_client;
		result = prime * result + id_commande;
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
		Commande other = (Commande) obj;
		
		if (id_commande != other.id_commande)
			return false;
		return true;
	}	
	
	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		
		if (id_client != other.id_client)
			return false;
		
		if (id_commande != other.id_commande)
			return false;
		return true;
	}	*/
	
	@Override
	public String toString() {
		return "Commande [id_commande=" + id_commande + ", date_commande=" + date +", id_client=" + id_client+ "]"; 
				
	}
	
	
}
