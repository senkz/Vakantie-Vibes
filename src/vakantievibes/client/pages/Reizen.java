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
	public Button reizen,bestemmingen;
	public VakantieVibes vv;
	public TextBox ap;
	public Label paginaInfo = new Label();
	
	public Reizen(VakantieVibes vv, Bestemming b) {
		paginaInfo.setText("Kies een datum en een reis.");
		add(paginaInfo);
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
		public Vervoer vervoer;
		
		public PageClickHandler(Reis r, Reizen p, int i) {
			reis=r;
			pagina=p;
			soortPagina=i;
		}
		
		public PageClickHandler(Reis r, Reizen p, int i, Vervoer v) {
			reis=r;
			pagina=p;
			soortPagina=i;
			vervoer=v;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			switch(soortPagina) {
			case 0:
				pagina.bevestigVervoer(reis);
				pagina.paginaInfo.setText("Bevestig je vervoers opgave");
				break;
			case 1:
				pagina.zoekVervoer(reis);
				pagina.paginaInfo.setText("Kies een vervoer hieronder uit");
				break;
			case 2:
				pagina.naarHoofdPanel();
				pagina.paginaInfo.setText("Kies een datum en een reis.");
				break;
			case 3:
				if(ap.getValue().matches("^[0-9]+$")) {
					pagina.biedtVervoerAan(reis);
					pagina.paginaInfo.setText("Uitslag van je bevestiging:");
				} else {
					Window.alert("Geen numerieke waarde opgegeven");
				}				
				break;
			case 4:
				pagina.reisMeeMetVervoer(reis,vervoer);
				pagina.paginaInfo.setText("Bevestig de Boeking.");
				break;
			}
		}
	};
	
	public void bevestigVervoer(Reis r) {
		remove(hoofdPanel);
		boekReisPanel.add(new Label(r.getInformatie()));
		Label aantalPlaatsenInfo=new Label();
		ap=new TextBox();
		aantalPlaatsenInfo.setText("Hoeveel vervoersplekken biedt u aan?");
		Button bevestig = new Button("Bevestig boeking");
		bevestig.addClickHandler(new PageClickHandler(r,this,3));
		reizen = new Button("Terug naar reis overzicht");
		reizen.addClickHandler(new PageClickHandler(r,this,2));
		boekReisPanel.setStyleName("reis");		
		boekReisPanel.add(aantalPlaatsenInfo);
		boekReisPanel.add(ap);
		boekReisPanel.add(bevestig);
		add(boekReisPanel);
		add(reizen);
	}
	
	public void zoekVervoer(Reis r) {
		remove(hoofdPanel);
		boekReisPanel.add(new Label(r.getInformatie()));
		Label vervoerInfo=new Label();
		ArrayList<Vervoer> vervoer = vv.getVervoer(r);
		if(vervoer==null){
			vervoerInfo.setText("Er is helaas geen vervoer aangeboden voor deze reis.");
			boekReisPanel.add(vervoerInfo);
		} else {
			vervoerInfo.setText("Er worden vervoers mogelijkheden aangeboden.");
			boekReisPanel.add(vervoerInfo);
			for (Vervoer v:vervoer) {
				if(v.getZitplaatsen()>0){
					HorizontalPanel temppane = new HorizontalPanel();
					Button tempb = new Button("Reis met deze aanbieder");
					tempb.addClickHandler(new PageClickHandler(r,this,4,v));
					temppane.add(new Label("Aanbieder: "+v.getAanbieder().getAchterNaam()+", "+v.getAanbieder().getVoorNaam()+" heeft nog "+v.getZitplaatsen()+" zitplaatsen over."));
					temppane.add(tempb);
					boekReisPanel.add(temppane);
				}
			}
		}
		reizen = new Button("Terug naar overzicht");
		reizen.addClickHandler(new PageClickHandler(null,this,2));
		boekReisPanel.setStyleName("reis");		
		add(boekReisPanel);
		add(reizen);
	}
	
	public void naarHoofdPanel() {
		remove(boekReisPanel);
		remove(reizen);
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
		System.out.println(r.getTitel());
		boekReisPanel = new VerticalPanel();
		if(vv.addVervoer(v)&&vv.addBoeking(b,v)) {
			boekReisPanel.add(new Label("U heeft succesvol geboekt"));
		} else {
			boekReisPanel.add(new Label("De boeking faalde"));
		}
		add(boekReisPanel);
	}
	
	public void reisMeeMetVervoer(Reis r,Vervoer v) {
		remove(boekReisPanel);
		Gebruiker g = vv.getLoginUser();
		if(g==null) {
			boekReisPanel= new VerticalPanel();
			boekReisPanel.add(new Label("U bent niet ingelogd"));
			add(boekReisPanel);
			return;
		}
		v.addMeerijder(g);
		Boeking b = new Boeking(new Date(), false,g,r); 
		System.out.println(r.getTitel());
		boekReisPanel = new VerticalPanel();
		if(vv.addBoeking(b,v)) {
			boekReisPanel.add(new Label("U heeft succesvol geboekt"));
		} else {
			boekReisPanel.add(new Label("De boeking faalde"));
		}
		add(boekReisPanel);
	}
}