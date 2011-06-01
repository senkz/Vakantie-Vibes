package vakantievibes.client.domain;
import java.util.ArrayList;

public class VakantieVibes {
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Reis> reizen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private ArrayList<Vervoer> vervoer = new ArrayList<Vervoer>();
	private ArrayList<Boeking> boeking = new ArrayList<Boeking>();
	private Gebruiker loginUser = null;

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
	
	public void addReis(Reis r) {
		reizen.add(r);
	}

	public boolean addVervoer(Vervoer vervoer) {
		ArrayList<Vervoer> ver = loginUser.getVervoer();
		for(Vervoer v:ver) {
			if(v.getReis()==vervoer.getReis()) return false;
		}
		this.vervoer.add(vervoer);
		return true;
	}

	public ArrayList<Vervoer> getVervoer() {
		return vervoer;
	}
	
	public ArrayList<Vervoer> getVervoer(Reis r) {
		ArrayList<Vervoer> svervoer=new ArrayList<Vervoer>();
		for(Vervoer v:vervoer) {
			if(v.getReis()==r) {
				svervoer.add(v);
			}
		}
		if(svervoer.size()==0) return null;
		return svervoer;
	}

	public boolean addBoeking(Boeking boeking) {
		ArrayList<Boeking> boe = loginUser.getBoeking();
		for(Boeking b:boe) {
			if(b.getReis()==boeking.getReis()) return false;
		}
		this.boeking.add(boeking);
		loginUser.addBoeking(boeking);
		return true;
	}

	public ArrayList<Boeking> getBoeking() {
		return boeking;
	}
}