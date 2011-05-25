package vakantievibes.client.domain;
public class Inloggen {

	static String salt,wachtwoord,gebruikersNaam;
	static VakantieVibes vb;

	public static Gebruiker Login(String ww, String gb) {
		wachtwoord = ww;
		gebruikersNaam = gb;
		return null;
	}
	
}