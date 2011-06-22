package vakantievibes.client.domain;
import java.util.ArrayList;

import vakantievibes.client.Entry;

import com.google.gwt.user.client.Window;

public class VakantieVibes {
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Reis> reizen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private ArrayList<Vervoer> vervoer = new ArrayList<Vervoer>();
	private ArrayList<Boeking> boeking = new ArrayList<Boeking>();
	private ArrayList<Adres> adressen = new ArrayList<Adres>();
	private Gebruiker loginUser = null;
	private Entry entry;

	public VakantieVibes(Entry e)	{
		this.entry = e;
	}
	
	public void goHome() {
		this.entry.myTabBar.selectTab(0);
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
	
	public void changeTab(Gebruiker g) {
		this.entry.changeTab(g);
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

	public void changeBestemming(Bestemming b, String bestem) {
		for (Bestemming bs: bestemmingen) {
			if (bs.getTitel().equals(b.getTitel())) {
				if(bs.getLocatie().equals(b.getLocatie()))
					bs.setInformatie(bestem);
			}
		}
	}


	public void changeReis(Reis r, String info, double totpr) {

		for(Reis rd: reizen)
		{
			if(rd.getTitel().equals(r.getTitel()))
			{
				r.setTotaalPrijs(totpr);
				r.setInformatie(info);
			}
		}

	}
	public void addReizen(Reis reisjes, Bestemming best, Adres ad)
	{

		if(best.equals(reisjes.getBestemming()))
		{
			System.out.println("bestem is : " + best.getLocatie());

			reizen.add(reisjes);
			if(best.getTitel().equals(reisjes.getTitel())){

				reisjes.setAdres(ad);
				reisjes.setBestemming(best);
				System.out.println("bestem reisjes is : " + reisjes.getLocatie());

			}

		}
	}

	public ArrayList<Boeking> getBoekingenGebruiker(Gebruiker g) {
		ArrayList<Boeking> gebruikerboeking = new ArrayList<Boeking>();
		for(Boeking b:boeking) {
			if(b.getBoeker()==g) {
				gebruikerboeking.add(b);
			}
		}
		return gebruikerboeking;
	}
	
	public void removeBoekingVanAanbieder(Boeking b,Vervoer v) {
		v.removeMeerijders();
		boeking.remove(b);
		vervoer.remove(v);
		loginUser.removeBoeking(b);
		loginUser.removeVervoer(v);
	}
	
	public void removeBoekingMeerijder(Boeking b,Vervoer v) {
		boeking.remove(b);
		v.removeMeerijder(loginUser);
		loginUser.removeBoeking(b);
		loginUser.removeVervoer(v);
	}
	
	public void removeBoekingMeerijder(Boeking b) {
		boeking.remove(b);
		loginUser.removeBoeking(b);
	}
}