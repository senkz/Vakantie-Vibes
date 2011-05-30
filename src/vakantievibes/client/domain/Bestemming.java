package vakantievibes.client.domain;
public class Bestemming{

	private String information,naam;
	private double prijsPerNacht;

	public Bestemming(String nm,String info, double ppn){
		information = info;
		setNaam(nm);
		prijsPerNacht = ppn;
	}

	public void setPrijsPerNacht(double ppn){
		prijsPerNacht = ppn;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}
}