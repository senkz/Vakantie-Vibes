package vakantievibes.client.domain;

import junit.framework.TestCase;

import org.junit.Test;

public class InloggenTest extends TestCase {
	
	@Test
	public final void testHashWachtwoord() {
		String p = "Hello World";
		
		assertFalse(p.equals(Inloggen.hashWachtwoord(p)));	

		System.out.println("Raw tekst:\t\t" + p);
		System.out.println("Hashed result:\t\t" + Inloggen.hashWachtwoord(p));
	}

	@Test
	public final void testLogin() {
		VakantieVibes vv = new VakantieVibes();
		Inloggen i = new Inloggen(vv);
		
		vv.addGebruiker(new Gebruiker("Tester", "geheim1", "Henk", "de Vries", "test@example.com"));
		
		assertNotNull(i.Login("geheim1", "Tester"));
		assertNull(i.Login("geheim2", "Tester"));
	}
}
