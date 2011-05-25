package vakantievibes.client.domain;
import java.util.ArrayList;

public class VakantieVibes {
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Reis> reisen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private Gebruiker inlog;

	public VakantieVibes()	{
	}
	
	private void addGebruiker(Gebruiker g) {
		gebruikers.add(g);
	}
}