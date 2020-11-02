package Test.metier;

import org.junit.Test;

import model.metier.Produit;

import static org.junit.Assert.*;

public class ProduitTest {

	@Test
	public void TConstructeur6Parametres() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		
		assertNotNull(p.getId_produit());
		assertNotNull(p.getNom());
		assertNotNull(p.getDescription());
		assertNotNull(p.getTarif());
		assertNotNull(p.getVisuel());
		assertNotNull(p.getId_categorie());
	}


	public void TConstructeur1Parametre() {
		Produit p = new Produit(1);
		assertNotNull(p.getId_produit());
		assertNull(p.getNom());
		assertNotNull(p.getDescription());
		assertEquals(22.89, p.getTarif(), 0.001);
		assertNotNull(p.getVisuel());
		assertEquals(0, p.getId_categorie());//pour un objet qui contient un attribut de type int, 
		//la creation de cet objet va mettre automatiquement 0 pour cet attribut,
		//donc on compare id_categorie avec 0
	}

	@Test
	public void TgetId_produit() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		assertEquals(1, p.getId_produit());
	}

	@Test
	public void TsetId_produit() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		p.setId_produit(2);
		assertEquals(2, p.getId_produit());
	}

	@Test
	public void TgetNom() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		assertEquals("prod1", p.getNom());
	}

	@Test
	public void TsetNom() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		p.setNom("prod2");
		assertEquals("prod2", p.getNom());
	}

	@Test
	public void TgetDescription() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		assertEquals("description1", p.getDescription());
	}

	@Test
	public void TsetDescription() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		p.setDescription("description2");
		assertEquals("description2", p.getDescription());
	}

	@Test
	public void TgetTarif() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		assertEquals(22.89, p.getTarif(), 0.001);
	}

	@Test
	public void TsetTarif() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		p.setTarif(20.78);
		assertEquals(20.78, p.getTarif(), 0.001);
	}

	
	@Test
	public void TgetVisuel() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		assertEquals("prod1.png", p.getVisuel());
	}

	@Test
	public void TsetVisuel() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		p.setVisuel("prod2.png");
		assertEquals("prod2.png", p.getVisuel());
	}
	
	@Test
	public void TgetId_categorie() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		assertEquals(1, p.getId_categorie());
	}

	@Test
	public void TsetId_tva() {
		Produit p = new Produit(1, "prod1", "description1", 22.89, "prod1.png", 1);
		p.setId_categorie(2);
		assertEquals(2, p.getId_categorie());
	}
}
