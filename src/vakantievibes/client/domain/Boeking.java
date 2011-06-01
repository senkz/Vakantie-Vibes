package vakantievibes.client.domain;

import java.util.Date;

public class Boeking {

	private Date boekingDatum;
	private boolean heeftBetaald;
	private Gebruiker boeker;
	private Reis reis;

	public Boeking(Date bd, Boolean hb, Gebruiker g, Reis r) {
		boekingDatum = bd;
		heeftBetaald = hb;
		boeker =  g;
		reis = r;
	}

	public boolean getHeeftBetaald() {
		return heeftBetaald;
	}

	public void setHeeftBetaald(boolean hb) {
		heeftBetaald = hb;
	}

	public Date getBoekingDatum() {
		return boekingDatum;
	}

	public Gebruiker getBoeker() {
		return boeker;
	}

	public Reis getReis() {
		return reis;
	}

}