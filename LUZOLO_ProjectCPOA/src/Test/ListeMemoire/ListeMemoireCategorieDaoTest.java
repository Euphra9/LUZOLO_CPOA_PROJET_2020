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
import dao.interfaces.IDaoCategorie;
import model.metier.Categorie;



public class ListeMemoireCategorieDaoTest {
	private Categorie categorie,categorieRead;
	private IDaoCategorie dao;
	private static int id_update = 8;
	private static int id_read =6;
	private static int id_delete =7;


	@Before
	public void setUp1() {
		
		DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		dao = daoF.getDaoCategorie();
		categorie = daoF.getDaoCategorie().getById(2);
		categorieRead= daoF.getDaoCategorie().getById(3);

	}

	@Test
	public void testNotEquals() {

		DaoFactory daoF2 = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		Categorie categorie2 = daoF2.getDaoCategorie().getById(1);

		System.out.println(categorie.toString());
		System.out.println(categorie2.toString());
		assertNotEquals(categorie, categorie2);
	}

	@Test
	public void setUp() {
		// recuperation de la dao par la factory

		dao = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE).getDaoCategorie();
		assertNotNull(dao);
		assertNotNull(dao.getAllCategories());

	}

	@Test
	public void testCreateCategorie() {
		// Nombre de categorie avant insertion :

		List<Categorie> totalcateg = dao.getAllCategories();
		int size = totalcateg.size();

		categorie = new Categorie(5,"polo","polo.jpg");
		categorie.setId_categorie(5);

		assertTrue(dao.create(categorie));

		// Verification que nous avons une categorie en plus dans la liste :
		assertEquals(size + 1, dao.getAllCategories().size());
	}

	
	 @Test 
	  public void testUpdateCategorie() { 
	  Categorie categorie= new Categorie(3,"ventilateur", null);
	  categorie.setId_categorie(id_update);  
	  //categorie.setVisuel("ventilateur.jpg");
	  
	  // On cree une nouvelle categorie dans la base des donnees
	  assertTrue(dao.create(categorie));
	  
	  //On verifie que la categorie existe bien dans la base des donnees "Catégorie"
	  categorieRead= dao.getById(categorie.getId_categorie());
	  assertEquals(categorie,categorieRead);
	  
	  //on modifie la categorie
	  categorieRead.setVisuel("climatisateur.jpg");
	  assertTrue(dao.update(categorie));
	  
	  //on verifie en DB
	  assertEquals(categorieRead,dao.getById(id_update));
	 
	 }
	 

	@Test
	public void testGetByID() {

		assertNotNull(dao.getById(id_read));

	}

	//verifier que l'exception saute
	@Test(expected = IllegalArgumentException.class)
	
	public void testDelesteCategorie() 
	{

		// On lit la catégorie
		Categorie categorie = dao.getById(id_delete);

		// On supprime la catégorie
		dao.delete(categorie);

		// on verifie que la catégorie n'existe plus
		assertNull(dao.getById(id_delete));
	}


}
