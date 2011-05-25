package vakantievibes.client.domain;
public class Bestemming{

	private String information;
	private double prijsPerNacht;

	public Bestemming(String info, double ppn){
		information = info;
		prijsPerNacht = ppn;
	}

	public void setPrijsPerNacht(double ppn){
		prijsPerNacht = ppn;
	}
}