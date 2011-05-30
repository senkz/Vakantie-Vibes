package vakantievibes.client.domain;
public class Bestemming{

	private String information;
	private String locatie;
	private double prijsPerNacht;

	public Bestemming(String loc,String info, double ppn){
		setInformation(info);
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

	public void setInformation(String information) {
		this.information = information;
	}

	public String getInformation() {
		return information;
	}
}