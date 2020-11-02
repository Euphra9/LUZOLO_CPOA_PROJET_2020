package Test.metier;


import org.junit.Test;

import model.metier.Ligne_commande;

import static org.junit.Assert.*;

public class Ligne_commandeTest {

	@Test
	public void TConstructeur4Parametres() {
		Ligne_commande p = new Ligne_commande(1, 2, 3, 40.99);
		assertNotNull(p.getId_commande());
		assertNotNull(p.getId_produit());
		assertNotNull(p.getQuantite());
		assertNotNull(p.getTarif_unitaire());
	}

	@Test
	public void TConstructeur2Parametre() {
		Ligne_commande lc = new Ligne_commande(1, 2);
		assertNotNull(lc.getId_commande());
		assertNotNull(lc.getId_produit());
		assertEquals(0, lc.getQuantite());// pour un objet qui contient un attribut de type int,
		// la creation de cet objet va mettre automatiquement 0 pour cet attribut,
		// donc on compare quantite avec 0
		assertEquals(0.0, lc.getTarif_unitaire(), 0.001);// la creation de cet objet va mettre automatiquement 0.0 pour
															// cet attribut,
		// donc on compare prix_produit avec 0.0
		// le 3eme parametre est l'exactitude

	}

	@Test
	public void TgetId_commande() {
		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		assertEquals(1, c.getId_commande());
	}

	@Test
	public void TsetId_commande() {

		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		c.setId_commande(2);
		assertEquals(2, c.getId_commande());
	}

	@Test
	public void TgetId_produit() {
		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		assertEquals(2, c.getId_produit());
	}

	@Test
	public void TsetId_produit() {

		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		c.setId_produit(2);
		assertEquals(2, c.getId_produit());
	}

	@Test
	public void TgetQuantite() {

		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		assertEquals(3, c.getQuantite());
	}

	@Test
	public void TsetQuantite() {

		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		c.setQuantite(11);
		assertEquals(11, c.getQuantite());
	}

	@Test
	public void TgetTarif_unitaire() {
		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		assertEquals(40.99, c.getTarif_unitaire(), 0.001);
	}

	@Test
	public void TsetTarif_unitaire() {
		Ligne_commande c = new Ligne_commande(1, 2, 3, 40.99);
		c.setTarif_unitaire(20.78);
		assertEquals(20.78, c.getTarif_unitaire(), 0.001);
	}
}
