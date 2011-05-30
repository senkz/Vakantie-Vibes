package vakantievibes.client.domain;

import java.util.ArrayList;

public class Vervoer {
	private int zitplaatsen;
	private ArrayList<Gebruiker> meerijder = new ArrayList<Gebruiker>();
	private Gebruiker aanbieder;

	public Vervoer(int zp, Gebruiker g) {
		zitplaatsen = zp;
		aanbieder = g;
	}
	
	public Gebruiker getAanbieder() {
		return aanbieder;
	}

	public int getZitplaatsen()	{
		return zitplaatsen;
	}

	public void setZitplaatsen(int zp) {
		zitplaatsen = zp;
	}
	
	public Gebruiker getMeerijder(int i) {
		return meerijder.get(i);
	}
	
	public void setMeerijder(Gebruiker g) {
		if(!checkMeerijder(g)) {
			meerijder.add(g);
		}
	}
	
	public boolean checkMeerijder(Gebruiker g) {
		for(Gebruiker h:meerijder) {
			if(h.getGebruikersNaam().equals(g.getGebruikersNaam())) return true;
		}
		return false;
	}
	
	public void removeMeerijder(Gebruiker g) {
		meerijder.remove(g);
	}
	
	public int getAantalMeerijder() {
		return meerijder.size();
	}
}