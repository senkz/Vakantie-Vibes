package vakantievibes.client.domain;

import java.util.Date;

public class Reis {

	private Date vertrekDatum, terugDatum;
	private String naam;

	public Reis(String nm, Date vDat, Date tDat){
		setNaam(nm);
		vertrekDatum = vDat;
		terugDatum = tDat;
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
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getNaam() {
		return naam;
	}

}