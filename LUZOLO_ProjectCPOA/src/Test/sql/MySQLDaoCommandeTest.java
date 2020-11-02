package Test.sql;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.sql.MySQLDaoCommande;
import main.Connexion;
import model.metier.Commande;


public class MySQLDaoCommandeTest {

	
	public DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.MYSQL);
	private DateTimeFormatter formatage=DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public Commande c1 = new Commande(1,  LocalDate.parse("30/09/2020",formatage), 2);
	public Commande c2 = new Commande(2,  LocalDate.parse("01/10/2020",formatage), 3);
	
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
		MySQLDaoCommande m = MySQLDaoCommande.getInstance();
		assertNotNull(m);
	}
	
	
	
	
	@Test
	public void testGetById() throws Exception {
		daoF.getDaoCommande().create(c1);
		assertEquals(c1,daoF.getDaoCommande().getById(c1.getId_commande()));
		daoF.getDaoCommande().delete(c1);
	}
	
	
	
	@Test
	public void testCreate() throws SQLException {
		daoF.getDaoCommande().create(c1);
		Commande but=daoF.getDaoCommande().getById(c1.getId_commande());
		assertEquals(c1, but);	//comparer si les 2 objets sont egaux
		//assertSame(c1, but);//comparer si les adresses des 2 objets sont egaux
		daoF.getDaoCommande().delete(c1);		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		daoF.getDaoCommande().create(c1);
		Commande cnew=new Commande(c1.getId_commande(), LocalDate.parse("03/03/2013",formatage), 4);
		daoF.getDaoCommande().update(cnew);
		assertEquals(cnew, daoF.getDaoCommande().getById(c1.getId_commande()));
		daoF.getDaoCommande().delete(c1);
	}
	
	@Test
	public void testDelete() throws SQLException {
		daoF.getDaoCommande().create(c1);
		daoF.getDaoCommande().delete(c1);
		assertNull(daoF.getDaoCommande().getById(c1.getId_commande()));
	}
	
	@Test
	public void testFindAll() throws SQLException {
		daoF.getDaoCommande().create(c1);
		daoF.getDaoCommande().create(c2);
		assertNotNull(daoF.getDaoCommande().findAll());
		daoF.getDaoCommande().delete(c1);
		daoF.getDaoCommande().delete(c2);
	}

	
	
}
