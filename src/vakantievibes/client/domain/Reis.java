package vakantievibes.client.domain;

import java.util.Date;

public class Reis {

	private Date vertrekDatum, terugDatum;

	public Reis(Date vDat, Date tDat){
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

}