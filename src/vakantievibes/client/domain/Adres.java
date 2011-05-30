package vakantievibes.client.domain;
public class Adres {
	private String land, stad, straat, huisnummer, postcode, telefoon;

	public Adres(String l, String s, String st, String hn, String pc ,String tf) {
		land = l;
		stad = s;
		straat = st;
		huisnummer = hn;
		postcode = pc;
		telefoon = tf;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public String getLand() {
		return land;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getStad() {
		return stad;
	}

	public String getStraat() {
		return straat;
	}

	public String getTelefoon() {
		return telefoon;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setStad(String stad) {
		this.stad = stad;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public void setTelefoon(String telefoon) {
		this.telefoon = telefoon;
	}
}