package vakantievibes.client.domain;
public class Bestemming{

	private String informatie,titel, locatie;
	private double prijsPerNacht;


	public Bestemming(String loc,String t,String info, double ppn){
		setInformatie(info);
		prijsPerNacht = ppn;
		locatie=loc;
		setTitel(t);
	}

	public void setPrijsPerNacht(double ppn){
		prijsPerNacht = ppn;
	}
	
	public double getPrijsPerNacht(){
		return prijsPerNacht;
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