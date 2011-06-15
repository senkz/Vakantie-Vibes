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
	private Label ltext1 = new Label("text1"), ltext2 = new Label("text2"), ltext3 = new Label("text3");
	private Label lemail = new Label("email"), lnaam = new Label("naam"), lonwp = new Label("onderwerp"), luv = new Label("uw vraag?");
	private TextBox tbe = new TextBox(), tbn = new TextBox(), tbo = new TextBox(), tbuv = new TextBox();
	
	public ContactPage(){
		
		vp = new VerticalPanel();
		vpfield = new VerticalPanel();
		hpe.add(lemail);hpe.add(tbe);
		hpn.add(lnaam);hpn.add(tbn);
		hpo.add(lonwp);hpo.add(tbo);
		hpuv.add(luv);hpuv.add(tbuv);
		vpfield.add(hpe);vpfield.add(hpn);vpfield.add(hpo);vpfield.add(hpuv);vpfield.add(b);
		vp.add(ltext1);vp.add(ltext2);vp.add(ltext3);vp.add(vpfield);
		add(vp);
	}

}
