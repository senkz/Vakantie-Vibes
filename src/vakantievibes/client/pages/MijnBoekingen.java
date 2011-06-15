package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Boeking;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.domain.Vervoer;
import vakantievibes.client.pages.Reizen.PageClickHandler;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MijnBoekingen extends VerticalPanel {
	
	private VakantieVibes vv;
	private ArrayList<Boeking> boekingen = new ArrayList<Boeking>();
	
	public MijnBoekingen(VakantieVibes sI){
		vv=sI;
		Gebruiker g = vv.getLoginUser();
		if(g==null) {
			add(new Label("U bent niet ingelogd"));
			return;
		}
		add(new Label("Uw boekingen:"));
		boekingen=vv.getBoekingenGebruiker(g);
		for (Boeking b:boekingen) {
			HorizontalPanel temppane = new HorizontalPanel();
			Button tempb = new Button("Pas gegevens aan.");
			//tempb.addClickHandler(new PageClickHandler(r,this,4,v));
			temppane.add(new Label("Reis: "+b.getReis().getTitel()+", Betaald: "+b.getHeeftBetaald()+", datum: "+b.getBoekingDatum()));
			temppane.add(tempb);
			add(temppane);
		}
	}

}
