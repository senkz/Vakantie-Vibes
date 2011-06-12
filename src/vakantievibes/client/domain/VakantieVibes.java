package vakantievibes.client.domain;
import java.util.ArrayList;
import java.util.Date;

import vakantievibes.client.pages.AdminPage;
import vakantievibes.client.pages.InloggenPage;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class VakantieVibes {
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Reis> reizen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private ArrayList<Vervoer> vervoer = new ArrayList<Vervoer>();
	private ArrayList<Boeking> boeking = new ArrayList<Boeking>();
	private ArrayList<Adres> adressen = new ArrayList<Adres>();
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
		if(g.getGebruikersNaam().equals("admin1")){
			if(g.getWachtWoord().equals("admin"))
			{
				g.setRechten(2);
				if(g.getRechten() == 2){

					RootPanel.get("admin").add(new AdminPage(this));
				}
			}

		}
		else{
			loginUser = g;
			System.out.println("logged in is nu: " + loginUser.getGebruikersNaam() + loginUser.getWachtWoord());
			
		}
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

	public boolean addBoeking(Boeking boeking) {
		ArrayList<Boeking> boe = loginUser.getBoeking();
		for (Boeking b : boe) {
			if (b.getReis().equals(boeking.getReis())) {
				return false;
			}
		}
		this.boeking.add(boeking);
		loginUser.addBoeking(boeking);
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
		//System.out.println("bs: "+ bs.getInformatie()+ bs.getTitel());
		//System.out.println("bestem : " + bestem);
		for (Bestemming bs: bestemmingen)
		{
			if(bs.getInformatie().equals(bestem))
			{
				System.out.println("zelfde info");

			}
			if (bs.getTitel().equals(b.getTitel()))
			{
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
}