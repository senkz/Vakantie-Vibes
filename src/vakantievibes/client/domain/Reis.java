package vakantievibes.client.domain;

import java.util.Date;

public class Reis {

	private Date vertrekDatum, terugDatum;
	private String informatie,titel;
	private double totaalprijs;
	private Bestemming bestemming;
	private Adres adres;
	

	public Reis(Date vDat, Date tDat,String t, Bestemming b, Adres a, double tp){
		vertrekDatum = vDat;
		terugDatum = tDat;
		setTitel(t);
		setInformatie(b.getInformatie());
		setBestemming(b);
		setAdres(a);
		setTotaalPrijs(tp);
	}

	public Reis(Date vDat, Date tDat,String t,String i, Bestemming b, Adres a, double tp){
		vertrekDatum = vDat;
		terugDatum = tDat;
		setTitel(t);
		setBestemming(b);
		setInformatie(i);
		setAdres(a);
		setTotaalPrijs(tp);
		System.out.println("alles is nu " + vertrekDatum + "" + terugDatum + "" +titel + " " +informatie + " " +adres.getHuisnummer() + " " +adres.getStad() +" " + tp + " " + bestemming.getTitel());
	}
	
	public Reis(Date myDate, Date myDate2, String t, String i, double tp) {
		vertrekDatum = myDate;
		terugDatum = myDate2;
		setInformatie(t);
		setInformatie(i);
		setTotaalPrijs(tp);
	}
	
	public void setTotaalPrijs(double tp) {
		totaalprijs = tp;	
	}
	
	public double getTotaalPrijs(){
		return totaalprijs;
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
	
	public boolean equals(Reis r) {
		if(this.titel.equals(r.getTitel())&&this.informatie.equals(r.getInformatie())) {
			return true;
		}
		return false;
	}

	public void setVertrekDatum(String vertdat) {
		// TODO Auto-generated method stub
		
	}



}