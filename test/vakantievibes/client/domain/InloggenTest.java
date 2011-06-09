package vakantievibes.client.domain;

import junit.framework.TestCase;

import org.junit.Test;

public class InloggenTest extends TestCase {
	
	@Test
	public final void testHashing() {
		String p = "Hello World";
		assertFalse(p.equals(Inloggen.hashWachtwoord(p)));	
	}

	@Test
	public final void testLoginCorrectPassword() {
		VakantieVibes vv = new VakantieVibes();
		Inloggen i = new Inloggen(vv);
		
		vv.addGebruiker(new Gebruiker("Tester", "geheim1", "Henk", "de Vries", "test@example.com"));
		
		assertTrue(i.Login("geheim1", "Tester"));
	}

	@Test
	public final void testLoginWrongPassword() {
		VakantieVibes vv = new VakantieVibes();
		Inloggen i = new Inloggen(vv);
		
		vv.addGebruiker(new Gebruiker("Tester", "geheim1", "Henk", "de Vries", "test@example.com"));
		
		assertFalse(i.Login("geheim2", "Tester"));
	}
}
