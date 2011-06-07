package vakantievibes.client.domain;

import com.google.gwt.dom.client.Element;

public class Bestemming{

	private String informatie,titel, locatie;


	public Bestemming(String loc,String t,String info){
		setInformatie(info);
		locatie=loc;
		setTitel(t);
	}

	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}

	public String getLocatie() {
		return locatie;
	}

	public void setInformatie(String informatie) {
		this.informatie = informatie;
	}

	public String getInformatie() {
		return informatie;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getTitel() {
		return titel;
	}
}