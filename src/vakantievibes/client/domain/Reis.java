package vakantievibes.client.domain;
public class Reis {

	private Calendar vertrekDatum, terugDatum;

	public Reis(Calendar vDat, Calendar tDat){
		vertrekDatum = vDat;
		terugDatum = tDat;
	}
	public Calendar getVertrekDatum() {
		return vertrekDatum;
	}

	public Calendar getTerugDatum(){
		return terugDatum;
	}

	public void setVertrekDatum(Calendar vDat){
			vertrekDatum = vDat.getInstance();
	}

	public void setTerugDatum(Calendar tDat){
			terugDatum = tDat.getInstance();
	}

}