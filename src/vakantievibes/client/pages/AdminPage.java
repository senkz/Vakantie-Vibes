package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminPage extends FormPanel implements ClickHandler {
	
	private VakantieVibes serviceImpl;
	private VerticalPanel mainvp, hrvp, hbvp;
	private HorizontalPanel menuhp;
	private Button breis, bbestem, edit, delete,bbestemtoe;
	private TextBox tbbnm, tbbinfo,tbloc, tbrnm, tbrvdat, tbrtdat,tbrinfo, tbrtp;
	private Label lbloc, lbnm, lbinfo, lrnm, lrvdat, lrtdat,lrinfo, lrb, lrtp;
	private TextBox tbrl, tbrs, tbrst, tbrhn, tbrpc, tbrtf;
	private Label lrl, lrs, lrst, lrhn, lrpc, lrtf;
	private ArrayList<HorizontalPanel> hplist = new ArrayList<HorizontalPanel>();
	private ArrayList<HorizontalPanel> hplist2 = new ArrayList<HorizontalPanel>();
	private ArrayList<Bestemming> bestemmingen;
	private ArrayList<Reis> reizen;
	
	public AdminPage(VakantieVibes sI){
		
		serviceImpl = sI;
		bestemmingen = serviceImpl.getBestemming();
		reizen = serviceImpl.getReizen();
		
		tbbnm = new TextBox();		lbnm = new Label("titel bestemming");
		tbbinfo = new TextBox();	lbinfo = new Label("info");
		tbloc = new TextBox();		lbloc = new Label("locatie");
		
		tbrnm = new TextBox();		lrnm = new Label("titel reis");
		tbrvdat = new TextBox();	lrvdat = new Label("vertrek datum");
		tbrtdat = new TextBox();	lrtdat = new Label("terugkomst datum");
		tbrinfo	= new TextBox();	lrinfo = new Label("informatie");
		tbrtp = new TextBox();		lrtp = new Label("totaal prijs");
		tbrl = new TextBox();		lrl = new Label("land");
		tbrs = new TextBox();		lrs = new Label("stad");
		tbrst = new TextBox();		lrst = new Label("straat");
		tbrhn = new TextBox();		lrhn = new Label("huisnummer");
		tbrpc = new TextBox();		lrpc = new Label("postcode");
		tbrtf = new TextBox();		lrtf = new Label("telefoonnr");
									lrb = new Label("bestemming");

		mainvp  = new VerticalPanel();
		menuhp = new HorizontalPanel();		mainvp.add(menuhp); 
		hrvp  = new VerticalPanel();		mainvp.add(hrvp);	hrvp.setVisible(false);
		hbvp  = new VerticalPanel();		mainvp.add(hbvp);	hbvp.setVisible(false);
		
		
		breis = new Button("reis");			menuhp.add(breis);		breis.addClickHandler(this);
		bbestem = new Button("bestemming");	menuhp.add(bbestem);	bbestem.addClickHandler(this);
		bbestemtoe = new Button("toevoegen"); 						bbestemtoe.addClickHandler(this);
		
		add(mainvp);
    	
		
		
	}
	
	public void onClick(ClickEvent event) {
	    // note that in general, events can have sources that are not Widgets.
	    Widget sender = (Widget) event.getSource();
	    
	    if (sender == breis) {
	    	hplist2.clear(); hrvp.clear();
	    	hrvp.add(lrnm);hrvp.add(tbrnm);hrvp.add(lrvdat);hrvp.add(tbrvdat);
	    	hrvp.add(lrtdat);hrvp.add(tbrtdat);hrvp.add(lrinfo);hrvp.add(tbrinfo);
	    	hrvp.add(lrtp);hrvp.add(tbrtp);hrvp.add(lrl);hrvp.add(tbrl);hrvp.add(lrs);
	    	hrvp.add(tbrs);hrvp.add(lrst);hrvp.add(tbrst);hrvp.add(lrhn);hrvp.add(tbrhn);
	    	hrvp.add(lrpc);hrvp.add(tbrpc);hrvp.add(lrtf);hrvp.add(tbrtf);
	    	hrvp.add(lrb);hrvp.add();
	    	hrvp.setVisible(true);
	    	hbvp.setVisible(false);
			System.out.println("printbreis");
			for(Reis r : reizen){
				HorizontalPanel hp2 = new HorizontalPanel();
				hp2.add(new Label(r.getTitel()));
				edit = new Button("edit") ;
				delete = new Button("X");
				hp2.add(delete);
				hp2.add(edit);
				hplist2.add(hp2);
				delete.addClickHandler(new Mydelete(r));
				edit.addClickHandler(new Myedit());
			}
			for(HorizontalPanel hp2 : hplist2)
				hrvp.add(hp2);
	    }  
	    if (sender == bbestem) {
	    	hplist.clear(); hbvp.clear();
	    	hbvp.add(lbnm);hbvp.add(tbbnm);hbvp.add(lbinfo); hbvp.add(tbbinfo); 
	    	  hbvp.add(lbloc);hbvp.add(tbloc);
	    	  hbvp.add(bbestemtoe);
	    	hrvp.setVisible(false);
	    	hbvp.setVisible(true);
			System.out.println("printbbestem");
			for(Bestemming b : bestemmingen){
					HorizontalPanel hp = new HorizontalPanel();
					hp.add(new Label(b.getTitel()));
					edit = new Button("edit") ;
					delete = new Button("X");
					hp.add(delete);
					hp.add(edit);
					hplist.add(hp);
					delete.addClickHandler(new Mydelete2(b));
					edit.addClickHandler(new Myedit2());

			}
			for(HorizontalPanel hp : hplist)
				hbvp.add(hp);
	    }
	    if (sender == bbestemtoe){
	    	Bestemming b = new Bestemming(tbloc.getText(),tbbnm.getText(),tbbinfo.getText());
	    	serviceImpl.addBestemming(b);
	    	refreshPanelBestem();
	    }
	    
	}
	
	class Mydelete implements ClickHandler {
		private Reis reisjes;
		
		public Mydelete(Reis r) {
			reisjes = r;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			serviceImpl.removeReis(reisjes);
			refreshpanelreis();
		}
	};
	class Myedit implements ClickHandler {
		public String reis;
		
		
		public Myedit() {
			
			
		}
		
		@Override
		public void onClick(ClickEvent event) {

		}
	};	
	
	class Mydelete2 implements ClickHandler {
		private Bestemming bestem;
		
		public Mydelete2(Bestemming b) {
			bestem = b;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			serviceImpl.removeBestemming(bestem);
			refreshPanelBestem();
		}
	};
	class Myedit2 implements ClickHandler {
		public String reis;
		
		
		public Myedit2() {
			
			
		}
		
		@Override
		public void onClick(ClickEvent event) {

		}
	};
	public void refreshpanelreis(){
		hplist2.clear(); hrvp.clear();
    	hrvp.setVisible(true);
    	hbvp.setVisible(false);
		System.out.println("printbreis");
		for(Reis r : reizen){
			HorizontalPanel hp2 = new HorizontalPanel();
			hp2.add(new Label(r.getTitel()));
			edit = new Button("edit") ;
			delete = new Button("X");
			hp2.add(delete);
			hp2.add(edit);
			hplist2.add(hp2);
			delete.addClickHandler(new Mydelete(r));
			edit.addClickHandler(new Myedit());
		}
		for(HorizontalPanel hp2 : hplist2)
			hrvp.add(hp2);
	}
	public void refreshPanelBestem(){
		hplist.clear(); hbvp.clear();
    	hrvp.setVisible(false);
    	hbvp.setVisible(true);
		System.out.println("printbbestem");
		for(Bestemming b : bestemmingen){
				HorizontalPanel hp = new HorizontalPanel();
				hp.add(new Label(b.getTitel()));
				edit = new Button("edit") ;
				delete = new Button("X");
				hp.add(delete);
				hp.add(edit);
				hplist.add(hp);
				delete.addClickHandler(new Mydelete2(b));
				edit.addClickHandler(new Myedit2());

		}
		for(HorizontalPanel hp : hplist)
			hbvp.add(hp);
	}
}