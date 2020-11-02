package model.metier;

import java.util.List;

/*
 * Pojo (Plain Old Java Object) correspondant a la classe metier "categorie".
 */
public class Categorie {
	private int id_categorie;
	private String titre;
	private String visuel;
	//une categorie appartient a 0 ou plusieurs produits
	private List<Produit> produits;
	
	public Categorie(int id_categorie, String titre, String visuel) {
		super();
		this.id_categorie=id_categorie;
		this.titre=titre;
		this.visuel=visuel;
	
	} 
	
	public Categorie (int id_categorie) {
		this.id_categorie=id_categorie;
	}
	
	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}


	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	
	
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result + id_categorie;
		result = prime*result + ((titre==null) ? 0 : titre.hashCode());
		result = prime*result + ((visuel==null) ? 0 : visuel.hashCode());
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
		
		Categorie other = (Categorie) obj;
		
		if (id_categorie != other.id_categorie)
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
		
		Categorie other = (Categorie) obj;
		
		if (id_categorie != other.id_categorie)
			return false;
		
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		
		if (visuel == null) {
			if (other.visuel != null)
				return false;
		} else if (!visuel.equals(other.visuel))
			return false;
		return true;
		
	}*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_categorie;
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
		Categorie other = (Categorie) obj;
		if (id_categorie != other.id_categorie)
			return false;
		return true;
	}

	@Override
	public String toString() {
		//return "categorie [id_categorie=" + id_categorie + ", titre=" + titre + ", visuel=" + visuel + "]";
		return titre;
	}	
}
