package vakantievibes.client.domain;

import junit.framework.TestCase;

import org.junit.Test;


public class VervoerTest extends TestCase {
	
	@Test
	public final void testRemoveVervoerMeerijders() {
		Gebruiker g = new Gebruiker("Jen", Inloggen.hashWachtwoord("geheim"), "Henk", "de Vries", "test@example.com");
		Gebruiker g2 = new Gebruiker("Piet", Inloggen.hashWachtwoord("geheim"), "Piet", "de Vries", "test@example.com");
		
		Vervoer v = new Vervoer(5, g, null);
		v.addMeerijder(g2);
		
		assertEquals(1, g.getVervoer().size());
		assertEquals(1, g2.getVervoer().size());
		
		v.removeMeerijder(g2);
		assertEquals(0, g2.getVervoer().size());

		v.addMeerijder(g2);
		g.removeVervoer(v);
		assertEquals(0, g.getVervoer().size());
		assertEquals(0, g2.getVervoer().size());
	}
}
