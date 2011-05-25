package vakantievibes.client.domain;

import java.util.Date;

public class Boeking {

	private Date boekingDatum;
	private boolean heeftBetaald;

	public Boeking(Date bd, Boolean hb) {
		boekingDatum = bd;
		heeftBetaald = hb;
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

}