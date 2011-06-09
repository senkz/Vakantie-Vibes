package vakantievibes.client.pages;

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
	private Label lnaam = new Label("gebruiker"), lww = new Label("wachtwoord"), luser = new Label();
	private Button binlog = new Button("Inloggen"),  buitlog = new Button("uitloggen");
	private VakantieVibes serviceImpl;
	private final VerticalPanel vp = new VerticalPanel(), loggedin = new VerticalPanel();
	
	public InloggenPage(VakantieVibes sI){
		serviceImpl = sI;
		
		add(vp);
		vp.add(lnaam); vp.add(tbgbi);
		vp.add(lww); vp.add(tbwwi);
		vp.add(binlog);
		
		loggedin.add(luser);
		loggedin.add(buitlog);
		
		binlog.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Inloggen i = new Inloggen(serviceImpl);
				Boolean b = i.Login(tbwwi.getText(), tbgbi.getText());
				
				if(b) {
					tbwwi.setText(""); tbgbi.setText("");
					remove(vp);
					luser.setText(serviceImpl.getLoginUser().getGebruikersNaam());
					add(loggedin);
				} else {
					Window.alert("Foute invoer!");
					tbwwi.setText(""); tbgbi.setText("");
				}
			}
			
		});
		buitlog.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				serviceImpl.setLoginUser(null);
				remove(loggedin);
				add(vp);
			}
		});
	}
}