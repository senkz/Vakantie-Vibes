package vakantievibes.client.pages;

import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Inloggen;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
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
		FlexTable t=new FlexTable();
		t.setCellSpacing(5);
		t.setWidget(0, 0, lnaam);	t.setWidget(0, 1, tbgbi);
		t.setWidget(1, 0, lww);	t.setWidget(1, 1, tbwwi);
		vp.add(t);
		serviceImpl = sI;
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
				final Gebruiker g = i.Login(tbgbi.getText(), tbwwi.getText());
				
				if(g == null) {
					Window.alert("Foute invoer!");
					tbwwi.setText(""); tbgbi.setText("");
				} 
				else{
					tbwwi.setText(""); tbgbi.setText("");
					vp.setVisible(false);
					luser.setText("Ingelogged als: "+g.getGebruikersNaam());
					vvp.add(loggedin);
					loggedin.setVisible(true);

					serviceImpl.changeTab(g);
					
					if(g.getRechten() == 2)
						RootPanel.get("admin").add(new AdminPage(serviceImpl));
					else
						RootPanel.get("admin").clear();
				}
			}
		if (sender == buitlog){
			vp.setVisible(true);
			loggedin.setVisible(false);
			serviceImpl.setLoginUser(null);
			serviceImpl.goHome();
		}
	}
	
	
}
