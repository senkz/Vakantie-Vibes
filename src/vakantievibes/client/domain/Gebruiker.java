package vakantievibes.client.domain;

import java.util.ArrayList;

public class Gebruiker{

	private String gebruikersNaam, wachtWoord, voorNaam, achterNaam, email;
	private int rechten;
	private Adres a;
	private ArrayList<Boeking> boeking = new ArrayList<Boeking>();
	private ArrayList<Vervoer> vervoer = new ArrayList<Vervoer>();

	public Gebruiker(String gb, String ww, String vn, String an, String em) {
		gebruikersNaam = gb;
		wachtWoord = ww;
		voorNaam = vn;
		achterNaam = an;
		email = em;

	}
	public String getAchterNaam() {
		return achterNaam;
	}

	public String getEmail() {
		return email;
	}

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public String getVoorNaam() {
		return voorNaam;
	}

	public String getWachtWoord() {
		return wachtWoord;
	}

	public void setAchterNaam(String achterNaam) {
		this.achterNaam = achterNaam;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}
	public void setVoorNaam(String voorNaam) {
		this.voorNaam = voorNaam;
	}

	public void setWachtWoord(String wachtWoord) {
		this.wachtWoord = wachtWoord;
	}

	public int getRechten() {
		return rechten;
	}

	public void setRechten(int rechten) {
		this.rechten = rechten;
	}
	public void setAdres(Adres a) {
		this.a = a;
	}
	public Adres getAdres() {
		return a;
	}
	public void addBoeking(Boeking boeking) {
		this.boeking.add(boeking);
	}
	public ArrayList<Boeking> getBoeking() {
		return boeking;
	}
	public void addVervoer(Vervoer vervoer) {
		this.vervoer.add(vervoer);
	}
	public ArrayList<Vervoer> getVervoer() {
		return vervoer;
	}
	
	public Boolean heeftReis(Reis r) {
		for(Boeking b:boeking) {
			if(b.getReis()==r) {
				return true;
			}
		}
		return false;
	}
	
	public Vervoer getVervoerBijReis(Reis r) {
		for(Vervoer v:vervoer) {
			if(v.getReis()==r) {
				return v;
			}
		}
		return null;
	}
	
	public String vervoersStatus(Reis r) {
		for(Vervoer v:vervoer) {
			if(v.getReis()==r) {
				return v.gebruikerStatus(this);
			}
		}
		return "null";
	}
	
	public ArrayList<Gebruiker> getMeeRijders(Reis r) {
		for(Vervoer v:vervoer) {
			if(v.getReis()==r) {
				return v.getMeerijders();
			}
		}
		return null;
	}
	
	public void removeVervoer(Vervoer v) {
		vervoer.remove(v);
		for (Vervoer v2:vervoer) {
			System.out.println(v2.getReis().getTitel());
		}
	}
	
	public void removeBoeking(Boeking b) {
		boeking.remove(b);
	}
}