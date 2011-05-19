public class VakantieVibes {
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Reis> reisen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private Inlogggen inlog;

	public VakantieVibes()	{
		inlog = Inloggen.getInstance();
	}
}