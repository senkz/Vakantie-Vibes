package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Adres;
import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminPage extends FormPanel implements ClickHandler {
	
	private VakantieVibes serviceImpl;
	private VerticalPanel mainvp, hrvp, hbvp;
	private HorizontalPanel menuhp;
	private Button breis, bbestem, edit, delete,bbestemtoe,breistoe;
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
		breistoe = new Button("toevoegen");							breistoe.addClickHandler(this);
		
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
	    	hrvp.add(lrb);//hrvp.add();
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
	    	refreshPanelBestem();
	    	hplist.clear(); hbvp.clear();
	    	hbvp.add(lbnm);hbvp.add(tbbnm);hbvp.add(lbinfo); hbvp.add(tbbinfo); 
	    	  hbvp.add(lbloc);hbvp.add(tbloc);
	    	  hbvp.add(bbestemtoe);
	    	hrvp.setVisible(false);
	    	hbvp.setVisible(true);
			System.out.println("printbbestem");
			for(Bestemming b : bestemmingen){
					TextBox tbbed = new TextBox();
					HorizontalPanel hp = new HorizontalPanel();
					hp.add(new Label(b.getTitel()));
					//String s = b.getInformatie();
					String s = "kaas";
					tbbed.setText(s);
					hp.add(tbbed);
					
					edit = new Button("edit") ;
					delete = new Button("X");
					hp.add(delete);
					hp.add(edit);
					hplist.add(hp);
					delete.addClickHandler(new Mydelete2(b));
					edit.addClickHandler(new Myedit2(b, tbbed.getText(), b.getTitel()));

			}
			for(HorizontalPanel hp : hplist)
				hbvp.add(hp);
	    }
	    if (sender == bbestemtoe){
	    	Bestemming b = new Bestemming(tbloc.getText(),tbbnm.getText(),tbbinfo.getText());
	    	serviceImpl.addBestemming(b);
	    	refreshPanelBestem();
	    }
	    if (sender == breistoe){
	    	ListBox lb = new ListBox();
			for(Bestemming b : bestemmingen){
				lb.addItem(b.getTitel());
			/*	DateFormat myDateFormat = new SimpleDateFormat(tbrvdat.getText());
				Date myDate = null;
				try {
				     myDate = myDateFormat.parse(tbrvdat.getText());
				}  catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DateFormat anotherDateFormat = new SimpleDateFormat(tbrtdat.getText());
				Date myDate2 = null;
				try {
				     myDate2 = myDateFormat.parse(tbrtdat.getText());
				}  catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			}
			
			
				lb.setVisibleItemCount(bestemmingen.size());
				//String bestemmingtoev = lb.getValue(lb.getSelectedIndex());
				//double tp = Double.parseDouble(tbrtp.getText());
				//String d = lb.getItemText(lb.getSelectedIndex());
				//Adres a = new Adres(tbrl.getText(), tbrs.getText(), tbrst.getText(), tbrhn.getText(), tbrpc.getText(), lrtf.getText());
				//Reis reizen = new Reis(tbrvdat.getText(), tbrtdat.getText(), tbrnm.getText(), tbrinfo.getText(), bestemmingtoev, a, tp);
				//serviceImpl.addAdres(a);	
				//serviceImpl.addReis(reizen);
				refreshpanelreis();
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
		public String bestem, titel;
		public Bestemming bs;
		
		
		public Myedit2(Bestemming b, String s,String tl) {
			bs = b;
			bestem = s;
			titel =tl;
		}
		@Override
		public void onClick(ClickEvent event) {
			serviceImpl.changeBestemming(bs, bestem,titel);
			refreshPanelBestem();
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
    	hbvp.add(lbnm);hbvp.add(tbbnm);hbvp.add(lbinfo); hbvp.add(tbbinfo); 
  	  	hbvp.add(lbloc);hbvp.add(tbloc);
  	  	hbvp.add(bbestemtoe);
    	hrvp.setVisible(false);
    	hbvp.setVisible(true);
		System.out.println("printbbestem");
		for(Bestemming b : bestemmingen){
				TextBox tbbed = new TextBox();
				HorizontalPanel hp = new HorizontalPanel();
				hp.add(new Label(b.getTitel()));
				//String s = b.getInformatie();
				//tbbed.setText();
				hp.add(tbbed);
				edit = new Button("edit") ;
				delete = new Button("X");
				hp.add(delete);
				hp.add(edit);
				hplist.add(hp);
				delete.addClickHandler(new Mydelete2(b));
				edit.addClickHandler(new Myedit2(b, tbbed.getText(), b.getTitel()));

		}
		for(HorizontalPanel hp : hplist)
			hbvp.add(hp);
	}
}