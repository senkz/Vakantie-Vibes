package vakantievibes.client.pages;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ContactPage extends FormPanel {
	
	private VerticalPanel vp, vpfield;
	private HorizontalPanel hpe = new HorizontalPanel(),hpn = new HorizontalPanel(), hpo = new HorizontalPanel(), hpuv = new HorizontalPanel();
	private Button b = new Button("verzenden");
	private Label ltext1, ltext2, ltext3,ltext4, ltext6,ltext8;
	private Label lemail = new Label("email"), lnaam = new Label("naam"), lonwp = new Label("onderwerp"), luv = new Label("uw vraag?");
	private TextBox tbe = new TextBox(), tbn = new TextBox(), tbo = new TextBox(), tbuv = new TextBox();
	
	public ContactPage(){
		
		ltext1 = new Label("U kunt ons bellen. Houdt u uw bestellingsnummer gereed als u een vraag heeft over uw vakantie. ");
		ltext4 = new Label("Wij zullen u dan zo snel mogelijk proberen te helpen.");
		ltext2 = new Label("Telefoon: 0800 - 1234");
		ltext6 = new Label("Bereikbaar van maandag tot en met vrijdag van 8.00 tot 20.00 uur.");
		ltext3 = new Label("Kijk op Vragen om te kijken of uw vraag ertussen staat.");
		ltext8 = new Label("We zijn ook te bereiken via de e-mail.");
		
		vp = new VerticalPanel();
		vpfield = new VerticalPanel();
		hpe.add(lemail);hpe.add(tbe);
		hpn.add(lnaam);hpn.add(tbn);
		hpo.add(lonwp);hpo.add(tbo);
		hpuv.add(luv);hpuv.add(tbuv);
		vpfield.add(hpe);vpfield.add(hpn);vpfield.add(hpo);vpfield.add(hpuv);vpfield.add(b);
		vp.add(ltext1);vp.add(ltext4);vp.add(ltext2);vp.add(ltext6);vp.add(ltext3);
		vp.add(ltext8);
		vp.add(vpfield);
		add(vp);
	}

}
