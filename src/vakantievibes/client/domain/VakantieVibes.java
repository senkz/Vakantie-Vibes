package vakantievibes.client.domain;
import java.util.ArrayList;

import com.google.gwt.user.client.Window;

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
	public void removeBestemming(Bestemming b){
		bestemmingen.remove(b);
		Window.alert("uw heeft "+ b.getTitel() + " verwijderd");
	}
	
	public void addReis(Reis r) {
		reizen.add(r);
	}

	public boolean addVervoer(Vervoer vervoer) {
		ArrayList<Vervoer> ver = loginUser.getVervoer();
		for (Vervoer v : ver) {
			if (v.getReis().equals(vervoer.getReis())) {
				return false;
			}
		}
		this.vervoer.add(vervoer);
		loginUser.addVervoer(vervoer);
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
		System.out.println(svervoer.size()); 
		if(svervoer.size()==0) {return null;}
		return svervoer;
	}

	public boolean addBoeking(Boeking boeking,Vervoer v) {
		ArrayList<Boeking> boe = loginUser.getBoeking();
		for (Boeking b : boe) {
			if (b.getReis().equals(boeking.getReis())) {
				return false;
			}
		}
		this.boeking.add(boeking);
		loginUser.addBoeking(boeking);
		loginUser.addVervoer(v);
		return true;
	}

	public ArrayList<Boeking> getBoeking() {
		return boeking;
	}
	
	public void removeReis(Reis r){
		reizen.remove(r);
		Window.alert("uw heeft " + r.getTitel() + " verwijderd");
	}

	public void changeBestemming(Bestemming bs, String bestem, String tl) {
			bs.setInformatie(bestem);
			System.out.println("informatie leeg");
			

	}
}