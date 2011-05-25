package vakantievibes.client.pages;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;


public class RegisterenGebruiker extends FormPanel{

	private TextBox tbnaam, tbanaam, tbstraat, tbhn, tbemail, tbgb, tbpostcode, tbtelefoon, tbplaats, tbland;
	private Label lbnaam, lbanaam, lbstraat, lbhn, lbemail, lbww, lbgb, lbpostcode, lbtelefoon, lbplaats, lbland;
	private Button bpost, breset;
	private PasswordTextBox tbww;
	
	public RegisterenGebruiker(){
		add(lbnaam);   add(tbnaam);
		add(lbanaam);   add(tbanaam);
		add(lbstraat);   add(tbstraat);
		add(lbhn);   add(tbhn);
		add(lbemail);   add(tbemail);
		add(lbww);   add(tbww);
		add(lbgb);   add(tbgb);
		add(lbpostcode);   add(tbpostcode);
		add(lbtelefoon);   add(tbtelefoon);
		add(lbplaats);   add(tbplaats);
		add(lbland);   add(tbland);
		add(bpost);		add(breset);
		
		
		
	}
}
