package vakantievibes.client.domain;
import java.util.ArrayList;

import com.google.gwt.user.client.Window;

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
	
	public ArrayList<Reis> getReizen() {
		return reizen;
	}
	public ArrayList<Bestemming> getBestemming(){
		return bestemmingen;
	}
	
	public void addBestemming(Bestemming b) {
		bestemmingen.add(b);
	}
	public void removeBestemming(Bestemming b){
		bestemmingen.remove(b);
		Window.alert("uw heeft "+ b.getTitel() + " verwijderd");
	}
	
	public void addReis(Reis r) {
		reizen.add(r);
	}
	public void removeReis(Reis r){
		reizen.remove(r);
		Window.alert("uw heeft " + r.getTitel() + " verwijderd");
	}
}