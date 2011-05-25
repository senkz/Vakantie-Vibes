package vakantievibes.client.pages;

import vakantievibes.client.domain.Adres;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;


public class RegistrerenGebruiker extends FormPanel{

	private TextBox tbnaam, tbanaam, tbstraat, tbhn, tbemail, tbgb, tbpostcode, tbtelefoon, tbplaats, tbland;
	private Label lbnaam, lbanaam, lbstraat, lbhn, lbemail, lbww, lbgb, lbpostcode, lbtelefoon, lbplaats, lbland;
	private Button bpost, breset;
	private PasswordTextBox tbww;
	private VakantieVibes serviceImpl;
		
	public RegistrerenGebruiker(VakantieVibes sI){
		serviceImpl = sI;
		
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
		
		bpost.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
					Gebruiker g = new Gebruiker(tbgb.getText(), tbww.getText(), tbnaam.getText(), tbanaam.getText(), tbemail.getText());
					Adres a = new Adres(tbland.getText(), tbplaats.getText(),tbstraat.getText(), tbhn.getText(), tbpostcode.getText(), Integer.parseInt(tbtelefoon.getText()));
					
					g.setAdres(a);
					serviceImpl.
			}});
		breset.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				tbnaam.setText(""); tbanaam.setText(""); tbstraat.setText(""); tbhn.setText(""); tbemail.setText(""); tbgb.setText(""); tbpostcode.setText(""); tbtelefoon.setText(""); tbplaats.setText(""); tbland.setText("");
				
			}
			
		});
	}
}
