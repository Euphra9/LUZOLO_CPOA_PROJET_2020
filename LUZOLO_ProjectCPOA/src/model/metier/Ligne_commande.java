package model.metier;

public class Ligne_commande {
	private int id_commande;
	private int id_produit;
	private int quantite;
	private double tarif_unitaire;
	
	
	public Ligne_commande(int id_commande, int id_produit, int quantite, double tarif_unitaire) {
		this.id_commande = id_commande;
		this.id_produit = id_produit;
		this.quantite = quantite;
		this.tarif_unitaire = tarif_unitaire;
	}
	
	public Ligne_commande(int id_commande, int id_produit) {
		this.id_commande = id_commande;
		this.id_produit = id_produit;
	}
	

	public int getId_commande() {
		return id_commande;
	}
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getTarif_unitaire() {
		return tarif_unitaire;
	}
	public void setTarif_unitaire(double tarif_unitaire) {
		this.tarif_unitaire = tarif_unitaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_commande;
		result = prime * result + id_produit;
		result = prime * result + quantite;
		long temp;
		temp = Double.doubleToLongBits(tarif_unitaire);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Ligne_commande other = (Ligne_commande) obj;
		if (id_commande != other.id_commande)
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
		Ligne_commande other = (Ligne_commande) obj;
		if (id_commande != other.id_commande)
			return false;
		if (id_produit != other.id_produit)
			return false;
		if (quantite != other.quantite)
			return false;
		if (Double.doubleToLongBits(tarif_unitaire) != Double.doubleToLongBits(other.tarif_unitaire))
			return false;
		return true;
	}*/
	
	
	
}
