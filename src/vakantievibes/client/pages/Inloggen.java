package vakantievibes.client.pages;

import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class Inloggen extends FormPanel {
	private TextBox tbgbi = new TextBox();
	private PasswordTextBox tbwwi = new PasswordTextBox();
	private Label lnaam = new Label("gebruikerswacht"); Label lww = new Label("wachtwoord");
	private Button binlog = new Button("Inloggen");
	
	public Inloggen(){
		
		
	}
	
	
	
}