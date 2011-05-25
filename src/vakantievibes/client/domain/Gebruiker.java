package vakantievibes.client.domain;
public class Gebruiker{

	private String gebruikersNaam, wachtWoord, voorNaam, achterNaam, email;
	private int rechten;

	public Gebruiker(String gb, String ww, String vn, String an, String em) {
		gebruikersNaam = gb;
		wachtWoord = ww;
		voorNaam = vn;
		achterNaam = an;
		email = em;

	}
	public String getAchterNaam() {
		return achterNaam;
	}

	public String getEmail() {
		return email;
	}

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public String getVoorNaam() {
		return voorNaam;
	}

	public String getWachtWoord() {
		return wachtWoord;
	}

	public void setAchterNaam(String achterNaam) {
		this.achterNaam = achterNaam;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}
	public void setVoorNaam(String voorNaam) {
		this.voorNaam = voorNaam;
	}

	public void setWachtWoord(String wachtWoord) {
		this.wachtWoord = wachtWoord;
	}

	public int getRechten() {
		return rechten;
	}

	public void setRechten(int rechten) {
		this.rechten = rechten;
	}
}