package vakantievibes.client.domain;
import java.util.ArrayList;

public class VakantieVibes {
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Reis> reizen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private Gebruiker loginUser;

	public VakantieVibes()	{
	}
	
	public void addGebruiker(Gebruiker g) {
		gebruikers.add(g);
	}
	
	public Gebruiker zoekGebruiker(String gnaam) {
		for(Gebruiker z : gebruikers) {
			if(z.getGebruikersNaam().equals(gnaam))
				return z;
		}
		return null;
	}
	
	public void setLoginUser(Gebruiker g) {
		loginUser = g;
	}
	
	public ArrayList<Gebruiker> listGebruikers() {
		return gebruikers;
	}

	public Gebruiker getLoginUser() {
		return loginUser;
	}
}