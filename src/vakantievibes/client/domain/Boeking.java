package vakantievibes.client.domain;

import java.util.Calendar;

public class Boeking {

	private Calendar boekingDatum;
	private boolean heeftBetaald;

	public Boeking(Calendar bd, Boolean hb) {
		boekingDatum = bd;
		heeftBetaald = hb;
	}

	public boolean getHeeftBetaald() {
		return heeftBetaald;
	}

	public void setHeeftBetaald(boolean hb) {
		heeftBetaald = hb;
	}

	public Calendar getBoekingDatum() {
		return boekingDatum;
	}

}