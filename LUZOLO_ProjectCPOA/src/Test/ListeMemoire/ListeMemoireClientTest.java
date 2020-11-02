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
import dao.interfaces.IDaoClient;
import model.metier.Client;

public class ListeMemoireClientTest {
	private Client client,clientRead;
	private IDaoClient dao;
	private static int id_update =5;
	private static int id_read =7;
	private static int id_delete =5;


	@Before
	public void setUp1() {
		
		DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		dao = daoF.getDaoClient();
		client = daoF.getDaoClient().getById(1);
		clientRead= daoF.getDaoClient().getById(4);

	}

	@Test
	public void testNotEquals() {

		DaoFactory daoF2 = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		Client client2 = daoF2.getDaoClient().getById(2);

		System.out.println(client.toString());
		System.out.println(client2.toString());
		assertNotEquals(client, client2);
	}

	@Test
	public void setUp() {
		// recuperation de la dao par la factory

		dao = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE).getDaoClient();
		assertNotNull(dao);
		assertNotNull(dao.getAllClients());

	}

		@Test
	public void testCreateProduit() {
		// Nombre de clients avant insertion :

		List<Client> totalcl = dao.getAllClients();
		int size = totalcl.size();

		client = new Client(6,"MICHEL","Lain");
		client.setId_client(6);

		assertTrue(dao.create(client));

		// Vérification que nous avons un client en plus dans la liste :
		assertEquals(size + 1, dao.getAllClients().size());
	}

	
	  @Test 
	  public void testUpdateClient() { 
	  Client client= new Client(4,"ROBERT","Ines");
	  client.setId_client(id_update);
	  //client.setPrenom("Simon");
	  
	  // On ajoute un nouveau client dans la base des données
	  assertTrue(dao.create(client));
	  
	  //On vérifie que le client existe bien dans la base des données (a été bien ajouté) "Client"
	  clientRead= dao.getById(client.getId_client());
	  assertEquals(client,clientRead);
	  
	  //on modifie les champs du client
	  clientRead.setPrenom("françois");
	  assertTrue(dao.update(client));
	  
	  //verification en base de donnees
	 
	  assertEquals(clientRead,dao.getById(id_update));
	  
	  
	 }
	 

		 @Test
	public void testGetByID() {

		assertNotNull(dao.getById(id_read));

	}

	//verifier que l'exception saute
		 @Test(expected = IllegalArgumentException.class)
	public void testDelesteClient() {

		// On lit le client
		Client client = dao.getById(id_delete);

		// On supprime le client
		dao.delete(client);

		// on verifie que le client n'existe plus
		assertNull(dao.getById(id_delete));
	}

}
