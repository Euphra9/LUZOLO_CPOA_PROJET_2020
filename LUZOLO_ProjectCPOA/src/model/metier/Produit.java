package model.metier;


/*
 *Pojo(Plain Old Java Object) correspondant a la classe metier 'Produit'. 
 */
public class Produit {

	private int id_produit;
	private String nom;
	private String description;
	private Double tarif;
//lien vers le fichier image...
	private String visuel;
//un produit est lie a une categorie
	private int id_categorie;
	//private Categorie categorie;

	

	public Produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public Produit(int id_produit, String nom, String description, Double tarif, String visuel, int id_categorie) {
		this.id_produit = id_produit;
		this.nom = nom;
		this.description = description;
		this.tarif = tarif;
		this.visuel = visuel;
		this.id_categorie = id_categorie;
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTarif() {
		return tarif;
	}

	public void setTarif(Double tarif) {
		this.tarif = tarif;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id_categorie;
		result = prime * result + id_produit;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((tarif == null) ? 0 : tarif.hashCode());
		result = prime * result + ((visuel == null) ? 0 : visuel.hashCode());
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
		Produit other = (Produit) obj;

		if (id_produit != other.id_produit)
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Produit [id_produit=" + id_produit + ", nom=" + nom + ", description=" + description + ", tarif="
				+ tarif + ", visuel=" + visuel + ", id_categorie=" + id_categorie + "]";
	}

	
}
