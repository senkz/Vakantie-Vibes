package vakantievibes.client.domain;

import java.util.Date;

public class Reis {

	private Date vertrekDatum, terugDatum;
	private String informatie,titel;
	private Bestemming bestemming;
	private Adres adres;
	

	public Reis(Date vDat, Date tDat,String t, Bestemming b, Adres a){
		vertrekDatum = vDat;
		terugDatum = tDat;
		setTitel(t);
		setInformatie(b.getInformatie());
		setBestemming(b);
		setAdres(a);
	}
	
	public Reis(Date vDat, Date tDat,String t,String i, Bestemming b, Adres a){
		vertrekDatum = vDat;
		terugDatum = tDat;
		setTitel(t);
		setBestemming(b);
		setInformatie(i);
		setAdres(a);
	}
	
	public Date getVertrekDatum() {
		return vertrekDatum;
	}

	public Date getTerugDatum(){
		return terugDatum;
	}

	public void setVertrekDatum(Date vDat){
			vertrekDatum = vDat;
	}

	public void setTerugDatum(Date tDat){
			terugDatum = tDat;
	}
	public void setBestemming(Bestemming bestemming) {
		this.bestemming = bestemming;
	}
	public Bestemming getBestemming() {
		return bestemming;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getTitel() {
		return titel;
	}

	public void setInformatie(String informatie) {
		this.informatie = informatie;
	}

	public String getInformatie() {
		return informatie;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public Adres getAdres() {
		return adres;
	}

}