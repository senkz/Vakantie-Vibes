package vakantievibes.client.pages;

import java.util.ArrayList;

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

public class InloggenPage extends FormPanel {
	private TextBox tbgbi = new TextBox();
	private PasswordTextBox tbwwi = new PasswordTextBox();
	private Label lnaam = new Label("gebruiker"); Label lww = new Label("wachtwoord");
	private Button binlog = new Button("Inloggen"), show = new Button("Toon gebruikers");
	private VakantieVibes serviceImpl;
	private final VerticalPanel vp = new VerticalPanel();
	
	public InloggenPage(VakantieVibes sI){
		serviceImpl = sI;
		
		add(vp);
		vp.add(lnaam); vp.add(tbgbi);
		vp.add(lww); vp.add(tbwwi);
		vp.add(binlog);
		vp.add(show);
		
		binlog.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Inloggen i = new Inloggen(serviceImpl);
				Gebruiker g = i.Login(tbwwi.getText(), tbgbi.getText());
				
				if(g == null) {
					Window.alert("Foute invoer!");
				} else {
					serviceImpl.setLoginUser(g);
					Window.alert("Goed gedaan! " + g.getVoorNaam());
				}
			}
			
		});
		
		show.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				ArrayList<Gebruiker> gebruikers = serviceImpl.listGebruikers();
				String s = "Gebruikersnamen:\n";
				for(Gebruiker b : gebruikers) {
					s += b.getGebruikersNaam() + "\n";
				}
				Window.alert(s);
			}
		});
	}
}