package vakantievibes.client.domain;

import junit.framework.TestCase;

import org.junit.Test;

import vakantievibes.client.Entry;


public class BoekingTest extends TestCase {
	
	@Test
	public final void testAddBoeking() {
		VakantieVibes vv = new VakantieVibes(new Entry());
		Gebruiker g = new Gebruiker("Jen", Inloggen.hashWachtwoord("geheim"), "Henk", "de Vries", "test@example.com");
		vv.setLoginUser(g);
		
		Boeking b = new Boeking(null, true, g, null);
		Vervoer v = new Vervoer(0, g, null);
		
		vv.addBoeking(b, v);
		
		assertEquals(1, g.getBoeking().size());
	}

	@Test
	public final void testRemoveBoeking() {
		VakantieVibes vv = new VakantieVibes(new Entry());
		Gebruiker g = new Gebruiker("Jen", Inloggen.hashWachtwoord("geheim"), "Henk", "de Vries", "test@example.com");
		vv.setLoginUser(g);
		
		Boeking b = new Boeking(null, true, g, null);
		Vervoer v = new Vervoer(0, g, null);
		
		vv.addBoeking(b, v);
		
		vv.removeBoekingVanAanbieder(b, v);
		
		assertEquals(0, g.getBoeking().size());
	}
}
