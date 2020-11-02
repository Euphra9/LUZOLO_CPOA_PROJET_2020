package Test.metier;

import org.junit.Test;

import model.metier.Commande;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommandeTest {
	@Test
	public void TConstructeur3Parametres() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("01/01/2018", formatage);
		Commande c = new Commande(1, date, 1);
		assertNotNull(c.getId_commande());
		assertNotNull(c.getDate());
		assertNotNull(c.getId_client());
	}

	@Test
	public void TConstructeur1Parametre() {
		Commande c = new Commande(1);
		assertNotNull(c.getId_commande());
		assertNull(c.getDate());
		assertEquals(0, c.getId_client());// pour un objet qui contient un attribut de type int,
		// la creation de cet objet va mettre automatiquement 0 pour cet attribut,
		// donc on compare id_client avec 0

	}

	@Test
	public void TgetId_commande() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("01/01/2018", formatage);
		Commande c = new Commande(1, date, 1);
		assertEquals(1, c.getId_commande());
	}

	@Test
	public void TsetId_commande() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("01/01/2018", formatage);
		Commande c = new Commande(1, date, 1);
		c.setId_commande(2);
		assertEquals(2, c.getId_commande());
	}

	@Test
	public void TgetDate() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("01/01/2018", formatage);
		Commande c = new Commande(1, date, 1);
		assertEquals(date, c.getDate());
	}

	@Test
	public void TsetDate() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("01/01/2018", formatage);
		Commande c = new Commande(1, date, 1);
		LocalDate date2 = LocalDate.parse("02/02/2018", formatage);
		c.setDate(date2);
		assertEquals(date2, c.getDate());
	}

	@Test
	public void TgetId_client() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("01/01/2018", formatage);
		Commande c = new Commande(1, date, 1);
		assertEquals(1, c.getId_client());
	}

	@Test
	public void TsetId_client() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("01/01/2018", formatage);
		Commande c = new Commande(1, date, 1);
		c.setId_client(2);
		assertEquals(2, c.getId_client());
	}

}