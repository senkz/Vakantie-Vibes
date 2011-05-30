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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminPage extends FormPanel implements ClickHandler {
	private VakantieVibes serviceImpl;
	private Button breis, bbestem, delete,edit,breisdel,bbestemdel, breistoe, bbestemtoe;
	private TextBox tbbnm, tbbinfo, tbrnm, tbr
	private ArrayList<Reis> reizen = new ArrayList<Reis>();
	private ArrayList<Bestemming> bestemmingen = new ArrayList<Bestemming>();
	private ArrayList<VerticalPanel> vp	= new ArrayList<VerticalPanel>();
	private VerticalPanel vp2, vpreis, vpbestem;
	private Reis reis;
	private Bestemming bestemming;
	
	public AdminPage()	{
		
		breis = new Button("reis");
		bbestem = new Button("bestemming");
		breistoe = new Button("add reis");
		bbestemtoe = new Button("add bestemming");
		breisdel = new Button("delete reis");
		bbestemdel = new Button("delete bestem");
		HorizontalPanel hp = new HorizontalPanel();
		vpreis = new VerticalPanel();
		vpbestem = new VerticalPanel();
		vp2 = new VerticalPanel();
		
		breis.addClickHandler(this);
		bbestem.addClickHandler(this);
		breistoe.addClickHandler(this);
		bbestemtoe.addClickHandler(this);
		breisdel.addClickHandler(this);
		bbestemdel.addClickHandler(this);
		
		add(hp);
		hp.add(breis);hp.add(bbestem);
		vpreis.add(breistoe);vpreis.add(breisdel);vpreis.setVisible(false);
		vpbestem.add(bbestemtoe);vpbestem.add(bbestemdel);vpbestem.setVisible(false);
		add(vpreis);
		add(vpbestem);
	}
		 public void onClick(ClickEvent event) {
			    // note that in general, events can have sources that are not Widgets.
			    Widget sender = (Widget) event.getSource();

			    if (sender == breis) {
			    	vpreis.setVisible(true);
					vpbestem.setVisible(false);
			    }  
			    if (sender == bbestem) {
					vpreis.setVisible(false);
					vpbestem.setVisible(true);
			    }
			    if (sender == breistoe){
			    	
			  }
			    if (sender == bbestemtoe){
			    }
			    if (sender == breisdel){
			    	for(Reis r : reizen){
						String s = reis.getNaam();
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
						String s = bestemming.getNaam();
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
			
			
			public Myedit(String r, Reizen p) {
				reis=r;
				
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

}
