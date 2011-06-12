package vakantievibes.client.pages;

import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Inloggen;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class InloggenPage extends FormPanel implements ClickHandler {
	private TextBox tbgbi = new TextBox();
	private PasswordTextBox tbwwi = new PasswordTextBox();
	private Label lnaam = new Label("gebruiker"), lww = new Label("wachtwoord"), luser = new Label();
	private Button binlog, buitlog;
	private VakantieVibes serviceImpl;
	private final VerticalPanel vp = new VerticalPanel(), loggedin = new VerticalPanel(), vvp = new VerticalPanel();
	
	public InloggenPage(VakantieVibes sI){
		serviceImpl = sI;
		
		
		vp.add(lnaam); vp.add(tbgbi);
		vp.add(lww); vp.add(tbwwi);
		binlog = new Button("inloggen");  binlog.addClickHandler(this);
		vp.add(binlog);
		vvp.add(vp);						
		buitlog = new Button("uitloggen"); buitlog.addClickHandler(this);
		
		loggedin.add(luser);
		loggedin.add(buitlog);
		
		add(vvp);
	}
		
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();
		if(sender == binlog){
				Inloggen i = new Inloggen(serviceImpl);
				final Gebruiker g = i.Login(tbwwi.getText(), tbgbi.getText());
				
				if(g == null) {
					Window.alert("Foute invoer!");
					tbwwi.setText(""); tbgbi.setText("");
				} 
				else{
					serviceImpl.setLoginUser(g);
					tbwwi.setText(""); tbgbi.setText("");
					vp.setVisible(false);
					luser.setText(g.getGebruikersNaam());
					vvp.add(loggedin);
					loggedin.setVisible(true);
				}
			}
		if (sender == buitlog){
			vp.setVisible(true);
			loggedin.setVisible(false);
			Gebruiker k = serviceImpl.getLoginUser();
			System.out.println("k is: " + k.getGebruikersNaam() + k.getWachtWoord());
			serviceImpl.setLoginUser(null);
			
		}
	}
	
	
}
