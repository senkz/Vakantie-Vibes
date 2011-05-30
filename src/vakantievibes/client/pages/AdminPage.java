package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Adres;
import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Inloggen;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.pages.Reizen.MyClickHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminPage extends FormPanel implements ClickHandler {
	private VakantieVibes serviceImpl;
	private Button breis, bbestem, delete,edit,breisdel,bbestemdel, breistoe, bbestemtoe;
	private TextBox tbbnm, tbbinfo, tbrnm, tbrvdat, tbrtdat;
	private TextBox tbrnm1, tbrvdat1, tbrtdat1;
	private Label lbnm, lbinfo, lrnm, lrvdat, lrtdat;
	private Label lrnm1, lrvdat1, lrtdat1;
	private ArrayList<Reis> reizen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private ArrayList<VerticalPanel> vp	= new ArrayList<VerticalPanel>();
	private VerticalPanel vp2, vpreis, vpbestem, vpreistoe, vpbestemtoe,vpreisedit ;
	private HorizontalPanel hp, hbp1, hbp2, hbp3, hbp4, hbp5, hbp6, hbp7, hbp8;
	private Reis reis;
	private Bestemming bestemming;
	
	public AdminPage()	{
		
		//buttons hoofdpagina adminmenubalk aan horizontalpanel hp
		breis = new Button("reis"); 					breis.addClickHandler(this);
		bbestem = new Button("bestemming");				bbestem.addClickHandler(this);
		//buttons vervolg pagina's toegevoegt aan vpreis en vpbestemtoe
		breistoe = new Button("add reis");				breistoe.addClickHandler(this);
		bbestemtoe = new Button("add bestemming");		bbestemtoe.addClickHandler(this);
		breisdel = new Button("delete reis");			breisdel.addClickHandler(this);
		bbestemdel = new Button("delete bestem");		bbestemdel.addClickHandler(this);
		// textbox en labels voor vpreistoe en vpbestemtoe
		tbbnm = new TextBox();		lbnm = new Label("naam bestemming");
		tbbinfo = new TextBox();	lbinfo = new Label("info");
		tbrnm = new TextBox();		lrnm = new Label("naam reis");
		tbrvdat = new TextBox();	lrvdat = new Label("vertrek datum");
		tbrtdat = new TextBox();	lrtdat = new Label("terugkomst datum");
		// textbox en labels voor vpreisedit
		tbrnm1 = new TextBox();		lrnm1 = new Label("naam reis");
		tbrvdat1 = new TextBox();	lrvdat1 = new Label("vertrek datum");
		tbrtdat1 = new TextBox();	lrtdat1 = new Label("terugkomst datum");
		
		// aanmaken panels
		hp = new HorizontalPanel();
		vpreis = new VerticalPanel();
		vpbestem = new VerticalPanel();
		vpreistoe = new VerticalPanel();
		vpbestemtoe = new VerticalPanel();
		vpreisedit  = new VerticalPanel();
		hbp1 = new HorizontalPanel();
		hbp2 = new HorizontalPanel();
		hbp3 = new HorizontalPanel();
		hbp4 = new HorizontalPanel();
		hbp5 = new HorizontalPanel();
		hbp6 = new HorizontalPanel();
		hbp7 = new HorizontalPanel();
		hbp8 = new HorizontalPanel();
		vp2 = new VerticalPanel();
		
		// toevoegen panels en buttons
		add(hp);
		hp.add(breis);hp.add(bbestem);
		vpreis.add(breistoe);vpreis.add(breisdel);vpreis.setVisible(false);
		vpbestem.add(bbestemtoe);vpbestem.add(bbestemdel);vpbestem.setVisible(false);
		add(vpreis);
		add(vpbestem);
		
		hbp1.add(lbnm);hbp1.add(tbbnm);hbp2.add(lbinfo);hbp2.add(tbbinfo);
		hbp3.add(lrnm);hbp3.add(tbrnm);hbp4.add(lrvdat);hbp4.add(tbrvdat);hbp5.add(lrtdat);hbp5.add(tbrtdat);
		vpreistoe.add(hbp3);vpreistoe.add(hbp4);vpreistoe.add(hbp5); vpreistoe.setVisible(false);
		vpbestemtoe.add(hbp1);vpbestemtoe.add(hbp2);vpbestemtoe.setVisible(false);
		add(vpreistoe);
		add(vpbestemtoe);
		
		hbp6.add(lrnm);hbp6.add(tbrnm);hbp7.add(lrvdat);hbp7.add(tbrvdat);hbp8.add(lrtdat);hbp8.add(tbrtdat);
		vpreisedit.add(hbp3);vpreisedit.add(hbp4);vpreisedit.add(hbp5); vpreisedit.setVisible(false);
		add(vpreisedit);
		
	}
		 public void onClick(ClickEvent event) {
			    // note that in general, events can have sources that are not Widgets.
			    Widget sender = (Widget) event.getSource();

			    if (sender == breis) {
			    	vpreis.setVisible(true);
					vpbestem.setVisible(false);
					vpbestemtoe.setVisible(false);
					vpreistoe.setVisible(false);
			    }  
			    if (sender == bbestem) {
					vpreis.setVisible(false);
					vpbestem.setVisible(true);
					vpbestemtoe.setVisible(false);
					vpreistoe.setVisible(false);
			    }
			    if (sender == breistoe){
			    	vpreis.setVisible(false);
					vpbestem.setVisible(false);
			    	vpbestemtoe.setVisible(false);
			    	vpreistoe.setVisible(true);
			  }
			    if (sender == bbestemtoe){
			    	vpreis.setVisible(false);
					vpbestem.setVisible(false);
			    	vpbestemtoe.setVisible(true);
			    	vpreistoe.setVisible(false);
			    }
			    if (sender == breisdel){
			    	for(Reis r : reizen){
						String s = reis.getTitel();
						for(int i=0;i<reizen.size();i++){
							vp.add(new VerticalPanel());
							vp.get(i).add(new Label(s));}
						for(VerticalPanel vpp : vp){
							edit = new Button("edit") ;
							delete = new Button("X");
							vpp.add(delete);
							vpp.add(edit);
							delete.addClickHandler(new Mydelete(r));
							edit.addClickHandler(new Myedit());
							}
					}
			    }
			    if (sender == bbestemdel){
			    	for(Bestemming b : bestemmingen){
						String s = bestemming.getTitel();
						for(int i=0;i<bestemmingen.size();i++){
							vp.add(new VerticalPanel());
							vp.get(i).add(new Label(s));}
						for(VerticalPanel vpp : vp){
							edit = new Button("edit") ;
							delete = new Button("X");
							vpp.add(delete);
							vpp.add(edit);
							delete.addClickHandler(new Mydelete2(b));
							edit.addClickHandler(new Myedit2());
							}
					}					
			    }
		 }
		
		class Mydelete implements ClickHandler {
			public Reis reis;
			public Reizen pagina;
			
			public Mydelete(Reis r) {
				reis=r;
			}
			
			@Override
			public void onClick(ClickEvent event) {
				 reizen.remove(reis);
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
			public Bestemming bestemming;
			
			
			public Mydelete2(Bestemming b) {
				bestemming = b;
			}
			
			@Override
			public void onClick(ClickEvent event) {
				 bestemmingen.remove(bestemming);
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

}
