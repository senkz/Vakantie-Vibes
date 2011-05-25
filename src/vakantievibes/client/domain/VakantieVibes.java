package vakantievibes.client.domain;
import java.util.ArrayList;

public class VakantieVibes {
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Reis> reisen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private Gebruiker inlog;
	private String username,password;

	public VakantieVibes(String u, String p)	{
		inlog = Inloggen.Login(u,p);
	}
}