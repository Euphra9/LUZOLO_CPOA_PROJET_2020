package Test.ListeMemoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.interfaces.IDaoProduit;
import model.metier.Produit;


public class ListeMemoireProduitDaoTest {

	private Produit produit,produitRead;
	private IDaoProduit dao;
	private static int id_update = 8;
	private static int id_read = 6;

	@Before
	public void setUp1() {
		
		DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		dao = daoF.getDaoProduit();
		produit = daoF.getDaoProduit().getById(4);
		produitRead= daoF.getDaoProduit().getById(4);

	}

	@Test
	public void testNotEquals() {

		DaoFactory daoF2 = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		Produit produit2 = daoF2.getDaoProduit().getById(5);

		System.out.println(produit.toString());
		System.out.println(produit2.toString());
		assertNotEquals(produit, produit2);
	}

	@Test
	public void setUp() {
		// recuperation de la dao par la factory

		dao = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE).getDaoProduit();
		assertNotNull(dao);
		assertNotNull(dao.getAllProduits());

	}

	@Test
	public void testCreateProduit() {
		// Nombre de produits avant insertion :

		List<Produit> totalprod = dao.getAllProduits();
		int size = totalprod.size();

		produit = new Produit(6, "livre", "livre d'histoire", 2.2, "livre.jpg", 4);
		produit.setId_produit(6);

		assertTrue(dao.create(produit));

		// Vérification que nous avons un produit en plus dans la liste :
		assertEquals(size + 1, dao.getAllProduits().size());
	}

	
	  @Test 
	  public void testUpdateProduit() { 
	  Produit produit= new Produit(6,"roman", "histoire epouvante", 14.0, null, 0);
	  produit.setId_categorie(id_update);
	  produit.setDescription("description");
	  
	  // On crée un produit dans la base des données
	  assertTrue(dao.create(produit));
	  
	  //On vérifie que le produit existe bien dans la base des données Produit
	  produitRead= dao.getById(produit.getId_categorie());
	  assertEquals(produit,produitRead);
	  
	  //on modifie le prodiit
	  produitRead.setDescription("deucieme description");
	  assertTrue(dao.update(produit));
	  
	  //on verifie en DB
	 
	  assertEquals(produitRead,dao.getById(id_update));
	  
	  
	 }
	 

	@Test
	public void testGetByID() {

		assertNotNull(dao.getById(id_read));

	}

	//verifier que l'exception saute
	@Test(expected = IllegalArgumentException.class)
	public void testDelesteProduit() {

		// On lit le produit
		Produit produit = dao.getById(id_read);

		// On supprime le produit
		dao.delete(produit);

		// on verifie que le produit n'existe plus
		assertNull(dao.getById(id_read));
	}

}
