package vakantievibes.client.domain;

import java.util.ArrayList;

public class Vervoer {
	private int zitplaatsen;
	private ArrayList<Gebruiker> meerijder = new ArrayList<Gebruiker>();
	private String type;

	public Vervoer(int zp, String tp) {
		zitplaatsen = zp;
		type = tp;
	}

	public int getZitplaatsen()	{
		return zitplaatsen;
	}

	public void setZitplaatsen(int zp) {
		zitplaatsen = zp;
	}

	public String getType()	{
		return type;
	}

	public void setType(String tp) {
		type = tp;
	}
}