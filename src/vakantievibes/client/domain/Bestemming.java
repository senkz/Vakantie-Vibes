package vakantievibes.client.domain;
public class Bestemming{

	private String informatie,locatie;
	private double prijsPerNacht;

	public Bestemming(String loc,String info, double ppn){
		setInformatie(info);
		prijsPerNacht = ppn;
		locatie=loc;
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
}