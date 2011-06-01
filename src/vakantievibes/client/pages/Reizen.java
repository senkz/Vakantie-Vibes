package vakantievibes.client.pages;

import java.util.ArrayList;
import java.util.Date;

import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Boeking;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.domain.Vervoer;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class Reizen extends VerticalPanel {
	
	public VerticalPanel hoofdPanel = new VerticalPanel(), boekReisPanel= new VerticalPanel();
	public DatePicker datum;
	public Button terug;
	public VakantieVibes vv;
	public TextBox ap;
	
	public Reizen(VakantieVibes vv, Bestemming b) {
		add(new Label("Kies een datum en een reis."));
		ArrayList<Reis> reizen = vv.getReizen();
		this.vv=vv;
		for(Reis r:reizen) {
			if(r.getBestemming()==b){
				VerticalPanel vp = new VerticalPanel();
				HorizontalPanel buttonHolder = new HorizontalPanel();
				Button zoekVervoer=new Button("Zoek vervoer"), biedtAan=new Button("Biedt vervoer aan");
				biedtAan.addClickHandler(new PageClickHandler(r,this,0)); zoekVervoer.addClickHandler(new PageClickHandler(r,this,1));
				datum = new DatePicker();
				datum.setTransientEnabledOnDates(true, r.getVertrekDatum());
				datum.setTransientEnabledOnDates(false, r.getTerugDatum());
				buttonHolder.add(zoekVervoer); buttonHolder.add(biedtAan);
				vp.add(new Label(r.getInformatie()));
				vp.setTitle(r.getTitel());
				vp.setStyleName("reis");
				vp.add(datum);
				vp.add(buttonHolder);
				hoofdPanel.add(vp);
			}
		}
		add(hoofdPanel);
	}
		
	class PageClickHandler implements ClickHandler {
		public Reis reis;
		public Reizen pagina;
		public int soortPagina;
		
		public PageClickHandler(Reis r, Reizen p, int i) {
			reis=r;
			pagina=p;
			soortPagina=i;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			switch(soortPagina) {
			case 0:
				pagina.biedVervoerAan(reis);
				break;
			case 1:
				pagina.zoekVervoer(reis);
				break;
			case 2:
				pagina.naarHoofdPanel();
				break;
			case 3:
				if(ap.getValue().matches("^[0-9]+$")) {
					pagina.biedtVervoerAan(reis);
				} else {
					Window.alert("Geen numerieke waarde opgegeven");
				}				
				break;
			}
		}
	};
	
	public void biedVervoerAan(Reis r) {
		remove(hoofdPanel);
		boekReisPanel.add(new Label(r.getInformatie()));
		Label aantalPlaatsenInfo=new Label();
		ap=new TextBox();
		aantalPlaatsenInfo.setText("Hoeveel vervoersplekken biedt u aan?");
		Button bevestig = new Button("Bevestig boeking");
		bevestig.addClickHandler(new PageClickHandler(null,this,3));
		terug = new Button("Terug naar overzicht");
		terug.addClickHandler(new PageClickHandler(null,this,2));
		boekReisPanel.setStyleName("reis");		
		boekReisPanel.add(aantalPlaatsenInfo);
		boekReisPanel.add(ap);
		boekReisPanel.add(bevestig);
		boekReisPanel.add(terug);
		add(boekReisPanel);
	}
	
	public void zoekVervoer(Reis r) {
		remove(hoofdPanel);
		boekReisPanel.add(new Label(r.getInformatie()));
		Label vervoerInfo=new Label();
		ArrayList<Vervoer> vervoer = vv.getVervoer(r);
		if(vervoer==null){
			vervoerInfo.setText("Er is helaas geen vervoer aangeboden voor deze reis.");
		} else {
			vervoerInfo.setText("Er word(en) "+vervoer.size()+" vervoers mogelijkheden aangeboden.");
		}
		Button bevestig = new Button("Bevestig boeking");
		terug = new Button("Terug naar overzicht");
		terug.addClickHandler(new PageClickHandler(null,this,2));
		boekReisPanel.add(vervoerInfo);
		boekReisPanel.setStyleName("reis");		
		boekReisPanel.add(bevestig);
		boekReisPanel.add(terug);
		add(boekReisPanel);
	}
	
	public void naarHoofdPanel() {
		remove(boekReisPanel);
		boekReisPanel= new VerticalPanel();
		add(hoofdPanel);
	}
	
	public void biedtVervoerAan(Reis r) {
		remove(boekReisPanel);
		Gebruiker g = vv.getLoginUser();
		if(g==null) {
			boekReisPanel= new VerticalPanel();
			boekReisPanel.add(new Label("U bent niet ingelogd"));
			add(boekReisPanel);
			return;
		}
		Vervoer v = new Vervoer(Integer.parseInt(ap.getValue()),g,r);
		Boeking b = new Boeking(new Date(), false,g,r); 
		
		boekReisPanel= new VerticalPanel();
		if(vv.addVervoer(v)&&vv.addBoeking(b)) {
			boekReisPanel.add(new Label("U heeft succesvol geboekt"));
		} else {
			boekReisPanel.add(new Label("De boeking faalde"));
		}
		add(boekReisPanel);
	}
}