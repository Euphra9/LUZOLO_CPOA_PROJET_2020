package Test.ListeMemoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.interfaces.IDaoCommande;
import model.metier.Commande;


public class ListeMemoireCommandeDaoTest {
	private Commande commande,commandeRead;
	private IDaoCommande dao;
	private static int id_update =7;
	private static int id_read =4;
	private static int id_delete =5;
	private DateTimeFormatter formatage=DateTimeFormatter.ofPattern("dd/MM/yyyy");



	@Before
	public void setUp1() {
		
		DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		dao = daoF.getDaoCommande();
		commande = daoF.getDaoCommande().getById(2);
		commandeRead= daoF.getDaoCommande().getById(3);

	}

	@Test
	public void testNotEquals() {

		DaoFactory daoF2 = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		Commande commande2 = daoF2.getDaoCommande().getById(3);

		System.out.println(commande.toString());
		System.out.println(commande2.toString());
		assertNotEquals(commande, commande2);
	}

	@Test
	public void setUp() {
		// recuperation de la dao par la factory

		dao = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE).getDaoCommande();
		assertNotNull(dao);
		assertNotNull(dao.getAllCommandes());

	}

		@Test
	public void testCreateCategorie() {
		// Nombre de commande avant insertion :

		List<Commande> totalcom = dao.getAllCommandes();
		int size = totalcom.size();

		commande = new Commande(5,LocalDate.parse("30/09/2020",formatage),4);
		commande.setId_commande(5);

		assertTrue(dao.create(commande));

		// Vérification que nous avons une commande en plus dans la liste :
		assertEquals(size + 1, dao.getAllCommandes().size());
	}

	
		 @Test 
	  public void testUpdateCommande() { 
	  Commande commande= new Commande(3,null,6);
	  commande.setId_commande(id_update);  
	  commande.setDate(LocalDate.parse("30/04/2020",formatage));
	  
	 // On crée une commande dans la base des données
	  assertTrue(dao.create(commande));
	  
	   //On vérifie que la commande existe bien dans la base des données "Commande"
	  commandeRead= dao.getById(commande.getId_commande());
	  assertEquals(commande,commandeRead);
	  
	   //on modifie la commande
	  commandeRead.setDate(LocalDate.parse("30/09/2020",formatage));
	  assertTrue(dao.update(commande));
	  
	  //on verifie en DB
	 
	//  assertEquals(commandeRead,dao.getById(id_update));
	  
	  
	 }
	 

	@Test
	public void testGetByID() {

		assertNotNull(dao.getById(id_read));

	}

	//verifier que l'exception saute
	@Test(expected = IllegalArgumentException.class)
	
	public void testDelesteCategorie() 
	{

		// On lit la commande
		Commande commande = dao.getById(id_delete);

		// On supprime la commande
		dao.delete(commande);

		// on verifie que la commande n'existe plus
		assertNull(dao.getById(id_delete));
	}

}
