package Test.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.SQLException;
import org.junit.Test;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.sql.MySQLDaoProduit;
import main.Connexion;
import model.metier.Produit;



public class MySQLDaoProduitTest {

	public DaoFactory daoF = DaoFactory.getDaoFactory(Persistance.MYSQL);
	public Produit p1 = new Produit(10,"airpods","white",90.89,"airpods.php",02);
	public Produit p2 = new Produit(11,"iphone","black",800.99,"iphone.png",03);
	
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
		MySQLDaoProduit m = MySQLDaoProduit.getInstance();
		assertNotNull(m);
	}
	
	@Test
	public void testGetById() throws Exception {
		daoF.getDaoProduit().create(p1);
		assertEquals(p1,daoF.getDaoProduit().getById(p1.getId_produit()));
		daoF.getDaoProduit().delete(p1);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		daoF.getDaoProduit().create(p1);
		Produit but=daoF.getDaoProduit().getById(p1.getId_produit());
		assertEquals(p1, but);	//comparer si les 2 objets sont egaux
//		assertSame(c1, but);//comparer si les adresses des 2 objets sont egaux
		daoF.getDaoProduit().delete(p1);		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		daoF.getDaoProduit().create(p1);
		Produit cnew=new Produit(p1.getId_produit(),"produit modifier","description modifier",90.89,"airpods.php",02);
		daoF.getDaoProduit().update(cnew);
		assertEquals(cnew, daoF.getDaoProduit().getById(p1.getId_produit()));
		daoF.getDaoProduit().delete(p1);
	}
	
	@Test
	public void testDelete() throws SQLException {
		daoF.getDaoProduit().create(p1);
		daoF.getDaoProduit().delete(p1);
		assertNull(daoF.getDaoProduit().getById(p1.getId_produit()));
	}
	
	@Test
	public void testFindAll() throws SQLException {
		daoF.getDaoProduit().create(p1);
		daoF.getDaoProduit().create(p2);
		assertNotNull(daoF.getDaoProduit().findAll());
		daoF.getDaoProduit().delete(p1);
		daoF.getDaoProduit().delete(p2);
	}
	
	
}
