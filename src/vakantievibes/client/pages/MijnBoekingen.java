package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Boeking;
import vakantievibes.client.domain.Gebruiker;
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

public class MijnBoekingen extends VerticalPanel {
	
	private VakantieVibes vv;
	private Gebruiker gebruiker;
	private ArrayList<Boeking> boekingen = new ArrayList<Boeking>();
	private VerticalPanel hoofdPanel=new VerticalPanel(),wijzigPanel=new VerticalPanel();
	private TextBox vrijplekken;
	private HorizontalPanel refBoekingsPanel;
	
	public MijnBoekingen(VakantieVibes sI){
		vv=sI;
		gebruiker = vv.getLoginUser();
		if(gebruiker==null) {
			add(new Label("U bent niet ingelogd"));
			return;
		}
		Label templ=new Label("Uw boekingen:");
		templ.setStyleName("labelextra");
		hoofdPanel.add(templ);
		boekingen=vv.getBoekingenGebruiker(gebruiker);
		if(boekingen.isEmpty()) {
			HorizontalPanel tempp=new HorizontalPanel();
			tempp.add(new Label("Geen boekingen op dit moment"));
			tempp.setStyleName("reis2");
			hoofdPanel.add(tempp);
		} else{
			for (Boeking b:boekingen) {
				HorizontalPanel boeking=new HorizontalPanel();
				Button tempb = new Button("Pas gegevens aan.");
				tempb.addClickHandler(new PageClickHandler(b,0,boeking));
				boeking.add(new Label("Reis: "+b.getReis().getTitel()+", Betaald: "+b.getHeeftBetaald()+", datum: "+b.getBoekingDatum()));
				boeking.add(tempb);
				boeking.setStyleName("reis2");
				hoofdPanel.add(boeking);
			}	
		}
		add(hoofdPanel);
	}
	
	public void naarHoofdPanel() {
		remove(wijzigPanel);
		wijzigPanel= new VerticalPanel();
		add(hoofdPanel);
	}
	
	public void naarHoofdPanel(HorizontalPanel hp) {
		remove(wijzigPanel);
		wijzigPanel= new VerticalPanel();
		hp.clear();
		hp.removeStyleName("reis2");
		add(hoofdPanel);
	}
	
	public void wijzigBoeking(Boeking b) {
		remove(hoofdPanel);
		wijzigPanel.setStyleName("reis2");
		wijzigPanel.add(new Label("Reis: "+b.getReis().getTitel()));
		wijzigPanel.add(new Label("Betaald: "+b.getHeeftBetaald()));
		wijzigPanel.add(new Label("Vertrek Datum: "+b.getReis().getVertrekDatum()));
		wijzigPanel.add(new Label("Terugkeer Datum: "+b.getReis().getTerugDatum()));
		if(gebruiker.vervoersStatus(b.getReis()).equals("aanbieder")) {
			wijzigPanel.add(new Label("U bent: Aanbieder"));
			wijzigPanel.add(new Label("Meerijders lijst:"));
			if(gebruiker.getMeeRijders(b.getReis())==null) {
				wijzigPanel.add(new Label("(Nog) Geen meerijders bekend bij deze boeking"));
			} else {
				ArrayList<Gebruiker> meerijders=gebruiker.getMeeRijders(b.getReis());
				if(meerijders==null){
					wijzigPanel.add(new Label("Geen meerijders op dit moment."));
				} else {
					for(Gebruiker g:meerijders) {
						HorizontalPanel meerijder=new HorizontalPanel();
						Button tempb = new Button("Verwijder van lijst");
						tempb.addClickHandler(new removeGebruikerVanLijst(g.getVervoerBijReis(b.getReis()),g,meerijder));
						meerijder.add(new Label("Voornaam: "+g.getVoorNaam()+", Achternaam: "+g.getAchterNaam()+", Email: "+g.getEmail()));
						meerijder.add(tempb);
						wijzigPanel.add(meerijder);
					}	
				}
			}
			wijzigPanel.add(new Label("Aantal zitplaatsen over:"));
			vrijplekken = new TextBox();
			vrijplekken.setValue(gebruiker.getVervoerBijReis(b.getReis()).getZitplaatsen()+"");
			wijzigPanel.add(vrijplekken);
			Button wijzig = new Button("Wijzig gegevens");
			wijzig.addClickHandler(new wijzigVrijePlekken(gebruiker.getVervoerBijReis(b.getReis()),vrijplekken));
			wijzigPanel.add(wijzig);
			Button annuleer = new Button("Annuleer uw Boeking");
			annuleer.addClickHandler(new annuleerBoeking(gebruiker.getVervoerBijReis(b.getReis()),b,0));
			wijzigPanel.add(annuleer);
			Button terug = new Button("Terug naar mijn boekingen");
			terug.addClickHandler(new PageClickHandler(b,1));
			wijzigPanel.add(terug);		
		} else if(gebruiker.vervoersStatus(b.getReis()).equals("meerijder")) {
			wijzigPanel.add(new Label("U bent: Meerijder"));	
			wijzigPanel.add(new Label("U rijd mee met: "+gebruiker.getVervoerBijReis(b.getReis()).getAanbieder().getVoorNaam()+", "+gebruiker.getVervoerBijReis(b.getReis()).getAanbieder().getAchterNaam()));	
			Button annuleer = new Button("Annuleer uw Boeking");
			annuleer.addClickHandler(new annuleerBoeking(gebruiker.getVervoerBijReis(b.getReis()),b,1));
			wijzigPanel.add(annuleer);
			Button terug = new Button("Terug naar mijn boekingen");
			terug.addClickHandler(new PageClickHandler(b,1));
			wijzigPanel.add(terug);	
		} else if(gebruiker.vervoersStatus(b.getReis()).equals("nietoplijst")){
			wijzigPanel.add(new Label("De aanbieder van uw vervoer heeft u van de lijst gehaald, u moet een nieuwe boeking maken of contact opnemen met VakantieVibes."));
			Button annuleer = new Button("Annuleer uw Boeking");
			annuleer.addClickHandler(new annuleerBoeking(gebruiker.getVervoerBijReis(b.getReis()),b,1));
			wijzigPanel.add(annuleer);
			Button terug = new Button("Terug naar mijn boekingen");
			terug.addClickHandler(new PageClickHandler(b,1));
			wijzigPanel.add(terug);	
		} else if(gebruiker.vervoersStatus(b.getReis()).equals("null")){
			wijzigPanel.add(new Label("Er is geen vervoer bekend voor deze reis, waarschijnlijk heeft de aanbieder van vervoer zijn boeking geannuleerd. Maak een nieuwe boeking of neem contact op met VakantieVibes."));
			Button annuleer = new Button("Annuleer uw Boeking");
			annuleer.addClickHandler(new annuleerBoeking(gebruiker.getVervoerBijReis(b.getReis()),b,2));
			wijzigPanel.add(annuleer);
			Button terug = new Button("Terug naar mijn boekingen");
			terug.addClickHandler(new PageClickHandler(b,1));
			wijzigPanel.add(terug);	
		} else {
			wijzigPanel.add(new Label("Er is een onbekende fout opgetreden, neem contact op met VakantieVibes"));
			Button annuleer = new Button("Annuleer uw Boeking");
			annuleer.addClickHandler(new annuleerBoeking(gebruiker.getVervoerBijReis(b.getReis()),b,2));
			wijzigPanel.add(annuleer);
			Button terug = new Button("Terug naar mijn boekingen");
			terug.addClickHandler(new PageClickHandler(b,1));
			wijzigPanel.add(terug);	
		}
		add(wijzigPanel);
	}
	
	class PageClickHandler implements ClickHandler {
		public Boeking boeking;
		public int soortPagina;
		public HorizontalPanel hp;
		
		public PageClickHandler(Boeking b,int i) {
			boeking=b;
			soortPagina=i;
		}
		
		public PageClickHandler(Boeking b,int i,HorizontalPanel hp) {
			boeking=b;
			soortPagina=i;
			this.hp=hp;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			switch(soortPagina) {
			case 0:
				wijzigBoeking(boeking);
				refBoekingsPanel=hp;
				break;
			case 1:
				naarHoofdPanel();
				break;
			}
		}
	};
	
	class removeGebruikerVanLijst implements ClickHandler {
		public Vervoer v;			
		public Gebruiker g;
		public HorizontalPanel hp;
		public removeGebruikerVanLijst(Vervoer v,Gebruiker g,HorizontalPanel hp) {
			this.v=v;
			this.g=g;
			this.hp=hp;
		}
		@Override
		public void onClick(ClickEvent event) {
			v.removeMeerijder(g);	
			hp.clear();
			vrijplekken.setValue((Integer.parseInt(vrijplekken.getValue())+1)+"");
		}
	};
	
	class wijzigVrijePlekken implements ClickHandler {
		public Vervoer v;			
		public TextBox vp;
		public wijzigVrijePlekken(Vervoer v,TextBox vp) {
			this.v=v;
			this.vp=vp;
		}
		@Override
		public void onClick(ClickEvent event) {
			if(vp.getValue().matches("^[0-9]+$")) {
				v.setZitplaatsen(Integer.parseInt(vp.getValue()));
				Window.alert("Boeking succesvol gewijzigd");
			} else {
				Window.alert("Geen numerieke waarde opgegeven");
			}	
		}
	};
	
	class annuleerBoeking implements ClickHandler {
		public Vervoer v;			
		public Boeking b;
		public int soortBoeking;
		public annuleerBoeking(Vervoer v,Boeking b,int i) {
			this.v=v;
			this.b=b;
			soortBoeking=i;
		}
		@Override
		public void onClick(ClickEvent event) {
			switch(soortBoeking) {
			case 0:
				vv.removeBoekingVanAanbieder(b, v);
				naarHoofdPanel(refBoekingsPanel);
				break;
			case 1:
				vv.removeBoekingMeerijder(b, v);
				naarHoofdPanel(refBoekingsPanel);
				break;
			case 2:
				vv.removeBoekingMeerijder(b);
				naarHoofdPanel(refBoekingsPanel);
				break;
			}
		}
	};
}
