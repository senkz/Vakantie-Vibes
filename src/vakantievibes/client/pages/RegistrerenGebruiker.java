package vakantievibes.client.pages;

import vakantievibes.client.domain.Adres;
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


public class RegistrerenGebruiker extends FormPanel{

	private TextBox tbnaam = new TextBox(), tbanaam = new TextBox(), tbstraat = new TextBox(), tbhn = new TextBox(), tbemail = new TextBox(), tbgb = new TextBox(), tbpostcode = new TextBox(), tbtelefoon = new TextBox(), tbplaats = new TextBox(), tbland = new TextBox();
	private Label lbnaam, lbanaam, lbstraat, lbhn, lbemail, lbww, lbgb, lbpostcode, lbtelefoon, lbplaats, lbland;
	private Button bpost = new Button("Verzenden"), breset = new Button("reset");
	private PasswordTextBox tbww = new PasswordTextBox();
	private VakantieVibes serviceImpl;
		
	public RegistrerenGebruiker(VakantieVibes sI){
		serviceImpl = sI;
		setStyleName("reis");
		
		VerticalPanel vp = new VerticalPanel();
		this.add(vp);
		
		lbnaam 		= new Label("Voornaam");		vp.add(lbnaam);			vp.add(tbnaam);
		lbanaam 	= new Label("Achternaam");		vp.add(lbanaam);		vp.add(tbanaam);
		lbstraat 	= new Label("Straat");			vp.add(lbstraat);		vp.add(tbstraat);
		lbhn 		= new Label("Huisnummer");		vp.add(lbhn);			vp.add(tbhn);
		lbemail 	= new Label("e-mail");			vp.add(lbemail);		vp.add(tbemail);
		lbww 		= new Label("Wachtwoord");		vp.add(lbww);			vp.add(tbww);
		lbgb 		= new Label("Gebruikersnaam");	vp.add(lbgb);			vp.add(tbgb);
		lbpostcode	= new Label("Postcode");		vp.add(lbpostcode);		vp.add(tbpostcode);
		lbtelefoon 	= new Label("Telefoonnr");		vp.add(lbtelefoon);		vp.add(tbtelefoon);
		lbplaats 	= new Label("Plaats");			vp.add(lbplaats);		vp.add(tbplaats);
		lbland 		= new Label("Land");			vp.add(lbland);			vp.add(tbland);
		
		vp.add(bpost);		vp.add(breset);
		
		
		bpost.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				String regex = "[A-z].*";
				String regmail = "^[\\w-]+\\.*[\\w-]+@([\\w-]+\\.)+[\\w-]+";
				String regpost = "[0-9]{4}[A-z]{2}";
				String regtel = "[0-9]{10}";
				String reghn = "[0-9].*[\\w]*";
				String p = "";
				if (!tbnaam.getText().matches(regex)) {
					//Window.alert("geen valide voornaam");
					p = "geen valide voornaam\n";
				}
				if (!tbanaam.getText().matches(regex)) {
					//Window.alert("geen valide voornaam");
					p += "geen valide achternaam\n";
				}
				if (!tbhn.getText().matches(reghn))
				{
					p += "geen valide huisnummer, begin met een nummer\n";
				}
				if(!tbemail.getText().matches(regmail))
				{
					//Window.alert("geen valide email");
					p += "geen valide email\n";
				}

				if(!tbpostcode.getText().matches(regpost))
				{
					//Window.alert("geen valide postcode");
					p += "geen valide postcode\n";
				}

				if(!tbtelefoon.getText().matches(regtel))
				{
					//Window.alert("geen valide telefoonnummer");
					p += "geen valide telefoonnummer\n";
				}

				if (!tbstraat.getText().matches(regex))
				{
					//Window.alert("geen valide straatnaam");
					p += "geen valide straatnaam\n";
				}

				if (!tbplaats.getText().matches(regex))
				{
					//Window.alert("geen valide plaatsnaam");
					p += "geen valide plaatsnaam\n";
				}
			
				if (!tbland.getText().matches(regex))
				{
					//Window.alert("hier moet een woord geen nummers");
					p += "geen valide landnaam\n";
				}
				if (tbww.getText().equals("")) 
				{
					p += "geen wachtwoord ingevuld\n";
				}
				if (tbgb.getText().equals(""))
				{
					p += "geen gebruikersnaam ingevuld\n";
				}
				if(p != "")
				{
					Window.alert(p);	
				}
				else{
					Gebruiker g = new Gebruiker(tbgb.getText(), Inloggen.hashWachtwoord(tbww.getText()), tbnaam.getText(), tbanaam.getText(), tbemail.getText());
					Adres a = new Adres(tbland.getText(), tbplaats.getText(),tbstraat.getText(), tbhn.getText(), tbpostcode.getText(), tbtelefoon.getText());
					g.setAdres(a);
					serviceImpl.addGebruiker(g);
					Window.alert("Gebruiker is geregistreerd");
				}
			}});
		breset.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				tbnaam.setText(""); tbanaam.setText(""); tbstraat.setText(""); tbww.setText(""); tbhn.setText(""); tbemail.setText(""); tbgb.setText(""); tbpostcode.setText(""); tbtelefoon.setText(""); tbplaats.setText(""); tbland.setText("");
				
			}
			
		});
	}
}
