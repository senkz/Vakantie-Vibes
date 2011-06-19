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
import com.google.gwt.user.client.ui.FlexTable;
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
		paginaInfo.setStyleName("labelextra");
		add(paginaInfo);
		ArrayList<Reis> reizen2 = vv.getReizen();
		this.vv=vv;
		for(Reis r:reizen2) {
			if(r.getBestemming()==b){
				VerticalPanel vp = new VerticalPanel();
				HorizontalPanel buttonHolder = new HorizontalPanel();
				Button zoekVervoer=new Button("Zoek vervoer"), biedtAan=new Button("Biedt vervoer aan");
				biedtAan.addClickHandler(new PageClickHandler(r,this,0)); zoekVervoer.addClickHandler(new PageClickHandler(r,this,1));
				if(vv.getLoginUser()==null) {
					buttonHolder.add(new Label("U moet eerst inloggen voordat u een boeking kunt plaatsen."));
				} else {
					if(vv.getLoginUser().heeftReis(r)) {
						buttonHolder.add(new Label("U heeft al geboekt voor deze reis, de boeking kunt u aanpassen in 'Mijn Boekingen'."));
					} else {
						buttonHolder.add(zoekVervoer); buttonHolder.add(biedtAan);
					}
				}
				FlexTable t=new FlexTable();
				t.setCellSpacing(5);
				t.setText(0, 0,"Titel: ");				t.setText(0, 1, r.getTitel());
				t.setText(1, 0, "Stad: ");				t.setText(1, 1, r.getAdres().getStad());
				t.setText(2, 0, "Informatie: ");		t.setText(2, 1, r.getInformatie());
				t.setText(3, 0, "Vertrek datum: ");		t.setText(3, 1, r.getVertrekDatum()+"");
				t.setText(4, 0, "Terugkeer datum: ");	t.setText(4, 1, r.getTerugDatum()+"");
				t.setText(5, 0, "Prijs: ");				t.setText(5, 1, r.getTotaalPrijs()+"");
				vp.add(t);
				vp.setStyleName("reis2");
				vp.add(buttonHolder);
				hoofdPanel.add(vp);
			}
		}
		reizen = new Button("Terug naar reis overzicht");
		reizen.addStyleName("specialeButton");
		reizen.addClickHandler(new PageClickHandler(2));
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
		
		public PageClickHandler(int i) {
			soortPagina=i;
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
				naarHoofdPanel();
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
		boekReisPanel.setStyleName("reis2");		
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
		boekReisPanel.setStyleName("reis2");		
		add(boekReisPanel);
		add(reizen);
	}
	
	public void naarHoofdPanel() {
		remove(boekReisPanel);
		remove(reizen);
		boekReisPanel = new VerticalPanel();
		add(hoofdPanel);
	}
	
	public void biedtVervoerAan(Reis r) {
		boekReisPanel.clear();
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
		if(vv.addVervoer(v)&&vv.addBoeking(b,v)) {
			boekReisPanel.add(new Label("U heeft succesvol geboekt"));
		} else {
			boekReisPanel.add(new Label("De boeking faalde"));
		}
	}
	
	public void reisMeeMetVervoer(Reis r,Vervoer v) {
		boekReisPanel.clear();
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
		boekReisPanel.clear();
		if(vv.addBoeking(b,v)) {
			boekReisPanel.add(new Label("U heeft succesvol geboekt"));
		} else {
			boekReisPanel.add(new Label("De boeking faalde"));
		}
	}
}