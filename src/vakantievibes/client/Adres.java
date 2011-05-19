public class Adres {
	private String land, stad, straat, huisnummer, postcode;
	private int telefoon;

	public Adres(String l, String s, String st, String hn, String pc ,int tf) {
		land = l;
		stad = s;
		straat = st;
		huisnummer = hs;
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

	public int getTelefoon() {
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

	public void setTelefoon(int telefoon) {
		this.telefoon = telefoon;
	}
}