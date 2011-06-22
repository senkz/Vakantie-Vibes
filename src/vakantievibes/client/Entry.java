package vakantievibes.client;


import java.util.Date;

import vakantievibes.client.domain.Adres;
import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.pages.AanpassenGebruiker;
import vakantievibes.client.pages.Bestemmingen;
import vakantievibes.client.pages.ContactPage;
import vakantievibes.client.pages.HomePage;
import vakantievibes.client.pages.InloggenPage;
import vakantievibes.client.pages.MijnBoekingen;
import vakantievibes.client.pages.RegistrerenGebruiker;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Entry implements EntryPoint {
	protected VakantieVibes serviceImpl;
	public TabBar myTabBar;

	@Override
	public void onModuleLoad() {
		serviceImpl = new VakantieVibes(this);

		final TabPanel myTabPanel = new TabPanel();

		myTabPanel.setSize("800px", "100px");
		myTabPanel.setAnimationEnabled(true);

		myTabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				Panel p = (Panel) myTabPanel.getWidget(event.getSelectedItem());
				p.clear();
				switch(event.getSelectedItem()) {
				default:
				case 0:
					p.add(new HomePage());
					break;
				case 1:
					if(serviceImpl.getLoginUser() == null)
						p.add(new RegistrerenGebruiker(serviceImpl));
					else
						p.add(new AanpassenGebruiker(serviceImpl));
					break;
				case 2:
					p.add(new Bestemmingen(serviceImpl));
					break;
				case 3:
					p.add(new MijnBoekingen(serviceImpl));
					break;
				case 4:
					p.add(new ContactPage());
					break;
				}
			}
		});

		myTabBar = new TabBar();
		myTabBar = myTabPanel.getTabBar();

		myTabPanel.add(new HorizontalPanel(), "Home");
		myTabPanel.add(new HorizontalPanel(), "Registreren");
		myTabPanel.add(new HorizontalPanel(), "Bestemmingen");
		myTabPanel.add(new HorizontalPanel(), "Mijn Boekingen");
		myTabPanel.add(new HorizontalPanel(), "Contact");
		
		myTabPanel.selectTab(0);

		myTabBar.setTabEnabled(3, false);

		RootPanel.get("login").add(new InloggenPage(serviceImpl));
		RootPanel.get("content").add(myTabPanel.getDeckPanel());
		RootPanel.get("header").add(myTabPanel.getTabBar());


		Gebruiker admin1 = new Gebruiker("admin1", "admin", "henk", "klaas", "text@text.nl");
		Adres aadmin = new Adres("Nederland","hensweert","kaasstraat","2e","5687HI","7854963210");
		admin1.setAdres(aadmin);
		serviceImpl.addGebruiker(admin1);
		admin1.setRechten(2);
		Bestemming b = new Bestemming("Belgie","klimmen","weekje door de bergen klimmen");
		Bestemming b1 = new Bestemming("Nederland","relaxen","een weekje rustig aan in centerparcs");
		serviceImpl.addBestemming(b);serviceImpl.addBestemming(b1);
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Ardenen", "klimmen", b, new Adres("Belgie", "Foy", "dorpstraat", "69", "1337SX" , "09005858"), 150.00));
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Emmen", "relaxen", b1, new Adres("Nederland", "Emmen", "boerenweg", "9", "1389AD" , "09008958"), 65.00));
		Gebruiker g1 = new Gebruiker("test", "test", "voornaam", "achternaam", "email@email.com");
		Adres a1 = new Adres("Nederland","kaas","kaas","2e","3432ED","9393939339");
		g1.setAdres(a1);
		Gebruiker g2 = new Gebruiker("test1", "test1", "voornaam1", "achternaam1", "email1@email.com");
		Adres a2 = new Adres("Nederland", "kasen","kase","3e","3421SO", "9030000320");
		g2.setAdres(a2);
		serviceImpl.addGebruiker(g1);
		serviceImpl.addGebruiker(g2);
	}

	public void changeTab(Gebruiker g) {
		if(g == null) {
			myTabBar.setTabEnabled(3, false);
			myTabBar.setTabText(1, "Registreren");
		} else {
			myTabBar.setTabEnabled(3, true);
			myTabBar.setTabText(1, "Instellingen");
		}
	}
}
