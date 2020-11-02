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
import dao.interfaces.IDaoLigne_commande;
import model.metier.Ligne_commande;


public class ListeMemoireLigne_commandeTest {
	private Ligne_commande ligne_commande,ligne_commandeRead;
	private IDaoLigne_commande dao;
	private static int id_update =6;
	private static int id_read =4;
	private static int id_delete =5;


	@Before
	public void setUp1() {
		
		DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		dao = daoF.getDaoLigne_commande();
		ligne_commande = daoF.getDaoLigne_commande().getById(2);
		ligne_commandeRead= daoF.getDaoLigne_commande().getById(4);

	}

	@Test
	public void testNotEquals() {

		DaoFactory daoF2 = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		Ligne_commande ligne_commande2 = daoF2.getDaoLigne_commande().getById(1);

		System.out.println(ligne_commande.toString());
		System.out.println(ligne_commande2.toString());
		assertNotEquals(ligne_commande, ligne_commande2);
	}

	@Test
	public void setUp() {
		// recuperation de la dao par la factory

		dao = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE).getDaoLigne_commande();
		assertNotNull(dao);
		assertNotNull(dao.getAllLigne_commandes());

	}

	@Test
	public void testCreateCategorie() {
		// Nombre de ligne_com avant insertion :

		List<Ligne_commande> totalligne_com = dao.getAllLigne_commandes();
		int size = totalligne_com.size();

		ligne_commande = new Ligne_commande(5,2,4,2.2);
		ligne_commande.setQuantite(3);

		assertTrue(dao.create(ligne_commande));

		// Vérification que nous avons une ligne_com en plus dans la liste :
		assertEquals(size + 1, dao.getAllLigne_commandes().size());
	}

	
	 @Test 
	  public void testUpdateCategorie() { 
	  Ligne_commande ligne_commande= new Ligne_commande(4,2,3,3.3);
	  ligne_commande.setId_commande(id_update);
	  ligne_commande.setQuantite(2);
	  
	  // On crée une ligne_com dans la base des données
	  assertTrue(dao.create(ligne_commande));
	  
	   //On vérifie que la ligne_com existe bien dans la base des données "Ligne commande"
	  ligne_commandeRead= dao.getById(ligne_commande.getId_commande());
	  assertEquals(ligne_commande,ligne_commandeRead);
	  
	  //on modifie la ligne_com
	  ligne_commandeRead.setQuantite(3);
	  assertTrue(dao.update(ligne_commande));
	  
	   //on verifie en DB
	 
	  assertEquals(ligne_commandeRead,dao.getById(id_update)); 
	  
	  
	 }
	 

	@Test
	public void testGetByID() {

		assertNotNull(dao.getById(id_read));

	}

	//verifier que l'exception saute
	 @Test(expected = IllegalArgumentException.class)
	
	public void testDelesteCategorie() 
	{

		// On lit la ligne_com
		Ligne_commande ligne_commande = dao.getById(id_delete);

		// On supprime la ligne_com
		dao.delete(ligne_commande);

		// on verifie que la ligne_com n'existe plus
		assertNull(dao.getById(id_delete));
	}
	
}
