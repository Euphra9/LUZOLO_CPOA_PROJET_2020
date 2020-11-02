package Test.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.sql.MySQLDaoCategorie;
import main.Connexion;
import model.metier.Categorie;


public class MySQLDaoCategorieTest {

	public DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.MYSQL);
	public Categorie cate1 = new Categorie(10, "fruit", "fruit.png");
	public Categorie cate2 = new Categorie(11, "legume", "legume.png");

	@BeforeClass // etre execute qu'une fois, quand on lance cette classe
	public static void connection() {
		try {
			new Connexion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterClass // etre execute qu'une fois, quand on termine cette classe
	public static void deconnection() {
		try {
			Connexion.maConnexion.close();
			System.out.println("Deconnecter");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetInstance() throws Exception {
		MySQLDaoCategorie m = MySQLDaoCategorie.getInstance();
		assertNotNull(m);
	}

	@Test
	public void testGetById() throws Exception {
		daoF.getDaoCategorie().create(cate1);
		assertEquals(cate1, daoF.getDaoCategorie().getById(cate1.getId_categorie()));
		daoF.getDaoCategorie().delete(cate1);
	}

	@Test
	public void testCreate() throws SQLException {
		daoF.getDaoCategorie().create(cate1);
		Categorie but = daoF.getDaoCategorie().getById(cate1.getId_categorie());
		assertEquals(cate1, but); // comparer si les 2 objets sont egaux
//		assertSame(c1, but);//comparer si les adresses des 2 objets sont egaux
		daoF.getDaoCategorie().delete(cate1);
	}

	@Test
	public void testUpdate() throws SQLException {
		daoF.getDaoCategorie().create(cate1);
		Categorie cnew = new Categorie(cate1.getId_categorie(), "viande", "viande.png");
		daoF.getDaoCategorie().update(cnew);
		assertEquals(cnew, daoF.getDaoCategorie().getById(cate1.getId_categorie()));
		daoF.getDaoCategorie().delete(cate1);
	}

	@Test
	public void testDelete() throws SQLException {
		daoF.getDaoCategorie().create(cate1);
		daoF.getDaoCategorie().delete(cate1);
		assertNull(daoF.getDaoCategorie().getById(cate1.getId_categorie()));
	}

	@Test
	public void testFindAll() throws SQLException {
		daoF.getDaoCategorie().create(cate1);
		daoF.getDaoCategorie().create(cate2);
		assertNotNull(daoF.getDaoCategorie().findAll());
		daoF.getDaoCategorie().delete(cate1);
		daoF.getDaoCategorie().delete(cate2);
	}

	
}