package Test.metier;

import org.junit.Test;

import model.metier.Categorie;

import static org.junit.Assert.*;

public class CategorieTest {

	@Test
	public void TConstructeur3Parametres() {
		Categorie c = new Categorie(1, "nom1", "nom1.png");
		assertNotNull(c.getId_categorie());
		assertNotNull(c.getTitre());
		assertNotNull(c.getVisuel());
	}

	@Test
	public void TConstructeur1Parametre() {
		Categorie c = new Categorie(1);
		assertNotNull(c.getId_categorie());
		// assertNull(c.getTitre());
		// assertNull(c.getVisuel());
	}

	@Test
	public void TgetId_categorie() {
		Categorie c = new Categorie(1, "nom1", "nom1.png");
		assertEquals(1, c.getId_categorie());
	}

	@Test
	public void TsetId_categorie() {
		Categorie c = new Categorie(1, "nom1", "nom1.png");
		c.setId_categorie(2);
		assertEquals(2, c.getId_categorie());
	}

	@Test
	public void TgetTitre() {
		Categorie c = new Categorie(1, "nom1", "nom1.png");
		assertEquals("nom1", c.getTitre());
	}

	@Test
	public void TsetTitre() {
		Categorie c = new Categorie(1, "nom1", "nom1.png");
		c.setTitre("nom2");
		assertEquals("nom2", c.getTitre());
	}

	@Test
	public void TgetVisuel() {
		Categorie c = new Categorie(1, "nom1", "nom1.png");
		assertEquals("nom1.png", c.getVisuel());
	}

	@Test
	public void TsetVisuel() {
		Categorie c = new Categorie(1, "nom1", "nom1.png");
		c.setVisuel("nom2.png");
		assertEquals("nom2.png", c.getVisuel());
	}

}