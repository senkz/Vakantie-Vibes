package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class Reizen extends VerticalPanel {
	
	public VerticalPanel hoofdPanel = new VerticalPanel(), boekReisPanel= new VerticalPanel();
	public DatePicker datum;
	public Button boek,terug;
	
	public Reizen(VakantieVibes vv, Bestemming b) {
		add(new Label("Kies een datum en een reis."));
		ArrayList<Reis> reizen = vv.getReizen();
		for(Reis r:reizen) {
			if(r.getBestemming()==b){
				VerticalPanel vp = new VerticalPanel();
				vp.add(new Label(r.getInformatie()));
				vp.setTitle(r.getTitel());
				boek = new Button("Boek reis");
				datum = new DatePicker();
				datum.setTransientEnabledOnDates(true, r.getVertrekDatum());
				datum.setTransientEnabledOnDates(false, r.getTerugDatum());
				boek.addClickHandler(new MyClickHandler(r,this));
				vp.setStyleName("reis");
				vp.add(datum);
				vp.add(boek);
				hoofdPanel.add(vp);
			}
		}
		add(hoofdPanel);
	}
		
	class MyClickHandler implements ClickHandler {
		public Reis reis;
		public Reizen pagina;
		
		public MyClickHandler(Reis r, Reizen p) {
			reis=r;
			pagina=p;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			if(reis==null){
				pagina.naarHoofdPanel();
			} else {
				pagina.boekReis(reis);
			}
		}
	};
	
	public void boekReis(Reis r) {
		remove(hoofdPanel);
		boekReisPanel.add(new Label(r.getInformatie()));
		terug = new Button("Terug naar overzicht");
		terug.addClickHandler(new MyClickHandler(null,this));
		boekReisPanel.add(terug);
		add(boekReisPanel);
	}
	
	public void naarHoofdPanel() {
		remove(boekReisPanel);
		boekReisPanel= new VerticalPanel();
		add(hoofdPanel);
	}
}