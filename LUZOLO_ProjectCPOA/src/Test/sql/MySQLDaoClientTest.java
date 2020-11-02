package Test.sql;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.SQLException;
import org.junit.Test;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.sql.MySQLDaoClient;
import main.Connexion;
import model.metier.Client;




public class MySQLDaoClientTest {

	
	public DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.MYSQL);
	public Client c1 = new Client(21,"nom1","prenom1");
	public Client c2 = new Client(11,"nom2","prenom2");
	
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
		MySQLDaoClient m = MySQLDaoClient.getInstance();
		assertNotNull(m);
	}
	
	
	@Test
	public void testGetById() throws Exception {
		daoF.getDaoClient().create(c1);
		assertEquals(c1,daoF.getDaoClient().getById(c1.getId_client()));
		daoF.getDaoClient().delete(c1);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		daoF.getDaoClient().create(c1);
		Client but=daoF.getDaoClient().getById(c1.getId_client());
		assertEquals(c1, but);	//comparer si les 2 objets sont egaux
		//assertSame(c1, but);//comparer si les adresses des 2 objets sont egaux
		daoF.getDaoClient().delete(c1);		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		daoF.getDaoClient().create(c1);
		Client cnew=new Client(c1.getId_client(), "nom modifie", "prenom modifie");
		daoF.getDaoClient().update(cnew);
		assertEquals(cnew, daoF.getDaoClient().getById(c1.getId_client()));
		daoF.getDaoClient().delete(c1);
	}
	
	@Test
	public void testDelete() throws SQLException {
		daoF.getDaoClient().create(c1);
		daoF.getDaoClient().delete(c1);
		assertNull(daoF.getDaoClient().getById(c1.getId_client()));
	}
	
	@Test
	public void testFindAll() throws SQLException {
		daoF.getDaoClient().create(c1);
		daoF.getDaoClient().create(c2);
		assertNotNull(daoF.getDaoClient().findAll());
		daoF.getDaoClient().delete(c1);
		daoF.getDaoClient().delete(c2);
	}
	
	
}
