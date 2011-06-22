package vakantievibes.client.domain;

import java.util.ArrayList;

public class Vervoer {
	private int zitplaatsen;
	private ArrayList<Gebruiker> meerijder = new ArrayList<Gebruiker>();
	private Gebruiker aanbieder;
	private Reis reis;

	public Vervoer(int zp, Gebruiker g, Reis r) {
		zitplaatsen = zp;
		aanbieder = g;
		reis = r;
	}
	
	public Reis getReis() {
		return reis;
	}
	
	public Gebruiker getAanbieder() {
		return aanbieder;
	}

	public int getZitplaatsen()	{
		return zitplaatsen - meerijder.size();
	}

	public void setZitplaatsen(int zp) {
		zitplaatsen = zp;
	}
	
	public ArrayList<Gebruiker> getMeerijders() {
		if(meerijder.isEmpty()){
			return null;
		} else {
			return meerijder;	
		}
	}
	
	public Gebruiker getMeerijder(int i) {
		return meerijder.get(i);
	}
	
	public boolean addMeerijder(Gebruiker g) {
		if(!checkMeerijder(g) && zitplaatsen > meerijder.size()) {
			meerijder.add(g);
			return true;
		} else
			return false;
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
	
	public String toString() {
		return reis.getTitel();
	}
	
	public String gebruikerStatus(Gebruiker g) {
		if(g==aanbieder) {
			return "aanbieder";
		} else {
			for(Gebruiker mr:meerijder) {
				if(g==mr) {
					return "meerijder";
				}
			}
		}
		return "nietoplijst";
	}

	public void removeMeerijders() {
		for(Gebruiker g:meerijder) {
			g.removeVervoer(this);
		}
	}
}