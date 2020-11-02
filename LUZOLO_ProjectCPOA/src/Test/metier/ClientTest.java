package Test.metier;

import org.junit.Test;

import model.metier.Client;

import static org.junit.Assert.*;

public class ClientTest {

	@Test
	public void TConstructeur3Parametres() {
		Client c = new Client(1, "nom1", "prenom1");
		assertNotNull(c.getId_client());
		assertNotNull(c.getNom());
		assertNotNull(c.getPrenom());
	}

	@Test
	public void TConstructeur1Parametre() {
		Client c = new Client(1);
		assertNotNull(c.getId_client());
		assertNull(c.getNom());
		assertNull(c.getPrenom());
	}

	@Test
	public void TgetId_client() {
		Client c1 = new Client(1, "nom1", "prenom1");
		assertEquals(1, c1.getId_client());
	}

	@Test
	public void TsetId_client() {
		Client c = new Client(1, "nom1", "prenom1");
		c.setId_client(2);
		assertEquals(2, c.getId_client());
	}

	@Test
	public void TgetNom() {
		Client c = new Client(1, "nom1", "prenom1");
		assertEquals("nom1", c.getNom());
	}

	@Test
	public void TsetNom() {
		Client c = new Client(1, "nom1", "prenom1");
		c.setNom("nom2");
		assertEquals("nom2", c.getNom());
	}

	@Test
	public void TgetPrenom() {
		Client c = new Client(1, "nom1", "prenom1");
		assertEquals("prenom1", c.getPrenom());
	}

	@Test
	public void TsetPrenom() {
		Client c = new Client(1, "nom1", "prenom1");
		c.setPrenom("prenom2");
		assertEquals("prenom2", c.getPrenom());
	}

}
