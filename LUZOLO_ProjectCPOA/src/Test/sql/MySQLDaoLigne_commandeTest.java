package Test.sql;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.SQLException;
import org.junit.Test;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.sql.MySQLDaoLigne_commande;
import main.Connexion;
import model.metier.Ligne_commande;


public class MySQLDaoLigne_commandeTest {

	public DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.MYSQL);
	public Ligne_commande c1 = new Ligne_commande(40, 3, 3, 10.92);
	public Ligne_commande c2 = new Ligne_commande(50, 4, 5, 21.04);
	
	@BeforeClass//etre execute qu'une fois, quand on lance cette classe
	public static void connection() {
		try {
			new Connexion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@AfterClass//etre execute qu'une fois, quand on termine cette classe
	public static void deconnection() {
		try {
			Connexion.maConnexion.close();
			System.out.println("Deconnecter");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetInstance() throws Exception{
		MySQLDaoLigne_commande m = MySQLDaoLigne_commande.getInstance();
		assertNotNull(m);
	}
	
	
	
	
	@Test
	public void testGetById() throws Exception {
		daoF.getDaoLigne_commande().create(c1);
		assertEquals(c1,daoF.getDaoLigne_commande().getById(c1.getId_commande()));
		daoF.getDaoLigne_commande().delete(c1);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		daoF.getDaoLigne_commande().create(c1);
		Ligne_commande but=daoF.getDaoLigne_commande().getById(c1.getId_commande());
		assertEquals(c1, but);	//comparer si les 2 objets sont egaux
		//assertSame(c1, but);//comparer si les adresses des 2 objets sont egaux
		daoF.getDaoLigne_commande().delete(c1);		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		daoF.getDaoLigne_commande().create(c1);
		Ligne_commande cnew=new Ligne_commande(c1.getId_commande(), 4, 5, 21.99);
		daoF.getDaoLigne_commande().update(cnew);
		assertEquals(cnew, daoF.getDaoLigne_commande().getById(c1.getId_commande()));
		daoF.getDaoLigne_commande().delete(c1);
	}
	
	@Test
	public void testDelete() throws SQLException {
		daoF.getDaoLigne_commande().create(c1);
		daoF.getDaoLigne_commande().delete(c1);
		assertNull(daoF.getDaoLigne_commande().getById(c1.getId_commande()));
	}
	
	@Test
	public void testFindAll() throws SQLException {
		daoF.getDaoLigne_commande().create(c1);
		daoF.getDaoLigne_commande().create(c2);
		assertNotNull(daoF.getDaoLigne_commande().findAll());
		daoF.getDaoLigne_commande().delete(c1);
		daoF.getDaoLigne_commande().delete(c2);
	}
	
	
}
