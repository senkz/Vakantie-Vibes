package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminPage extends FormPanel implements ClickHandler {
	
	private VakantieVibes serviceImpl;
	private VerticalPanel mainvp, hrvp, hbvp;
	private HorizontalPanel menuhp;
	private Button breis, bbestem, edit, delete;
	private ArrayList<VerticalPanel> vp	= new ArrayList<VerticalPanel>();
	private ArrayList<Bestemming> bestemmingen;
	private ArrayList<Reis> reizen;
	
	public AdminPage(VakantieVibes sI){
		
		serviceImpl = sI;
		bestemmingen = serviceImpl.getBestemming();
		reizen = serviceImpl.getReizen();
		mainvp  = new VerticalPanel();
		hrvp  = new VerticalPanel();		mainvp.add(hrvp);	hrvp.setVisible(false);
		hbvp  = new VerticalPanel();		mainvp.add(hbvp);	hbvp.setVisible(false);
		menuhp = new HorizontalPanel();		mainvp.add(menuhp); 
		
		breis = new Button("reis");			menuhp.add(breis);		breis.addClickHandler(this);
		bbestem = new Button("bestemming");	menuhp.add(bbestem);	bbestem.addClickHandler(this);
		
		add(mainvp);
		
		
		
	}
	
	public void onClick(ClickEvent event) {
	    // note that in general, events can have sources that are not Widgets.
	    Widget sender = (Widget) event.getSource();
	    
	    if (sender == breis) {
	    	hrvp.setVisible(true);
	    	hbvp.setVisible(false);
			System.out.println("printbreis");
	    }  
	    if (sender == bbestem) {
	    	hrvp.setVisible(false);
	    	hbvp.setVisible(true);
			System.out.println("printbbestem");
			for(Bestemming b : bestemmingen){
				for(int i=0;i<bestemmingen.size();i++){
					vp.add(new VerticalPanel());
					vp.get(i).add(new Label(b.getTitel()));
					}
				for(VerticalPanel vpp : vp){
					edit = new Button("edit") ;
					delete = new Button("X");
					vpp.add(delete);
					vpp.add(edit);
					//delete.addClickHandler(new Mydelete2(b));
					//edit.addClickHandler(new Myedit2());
					}
			}
	    }
	    
	}
}