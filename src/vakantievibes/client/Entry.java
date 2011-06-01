package vakantievibes.client;


import java.util.Date;

import vakantievibes.client.domain.Adres;
import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.pages.AanpassenGebruiker;
import vakantievibes.client.pages.AdminPage;
import vakantievibes.client.pages.Bestemmingen;
import vakantievibes.client.pages.InloggenPage;
import vakantievibes.client.pages.RegistrerenGebruiker;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Entry implements EntryPoint {
		protected VakantieVibes serviceImpl;

		@Override
		public void onModuleLoad() {
			serviceImpl = new VakantieVibes();
			Gebruiker admin1 = new Gebruiker("admin1", "admin", "henk", "klaas", "test@test.test");
			serviceImpl.addGebruiker(admin1);
			admin1.setRechten(1);
			
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
							p.add(new RegistrerenGebruiker(serviceImpl));
							break;
						case 1:
							p.add(new Bestemmingen(serviceImpl));
							break;
						case 2:
							p.add(new InloggenPage(serviceImpl));
							break;
						case 3:
							p.add(new AanpassenGebruiker(serviceImpl));
							break;
						case 4:
							p.add(new AdminPage(serviceImpl));
					}
				}
			});
			
			myTabPanel.add(new HorizontalPanel(), "Reg. gebruiker");
			myTabPanel.add(new HorizontalPanel(), "Overzicht Bestemmingen");
			myTabPanel.add(new HorizontalPanel(), "Inloggen");
			myTabPanel.add(new HorizontalPanel(), "Aanpassen");
			myTabPanel.add(new HorizontalPanel(), "admin");
			myTabPanel.selectTab(0);
			
			RootPanel.get("content").add(myTabPanel);
			
			Bestemming b = new Bestemming("Nederland","nl","Dit dbrvbrfvgbrg info",50);
			Bestemming b1 = new Bestemming("Nederland","nl","Dit is de freaking info",50);
			serviceImpl.addBestemming(b);serviceImpl.addBestemming(b1);
			serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+500000), "Naar de wallen", "Lekker batsen", b, new Adres("Nederland", "Amsterdam", "de wallen", "69", "1337SX" , "09005858")));
			serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+500000), "Naar dgrn", "Lekgvbrewgvrgv", b1, new Adres("Nederland", "Amsterdam", "de wallen", "69", "1337SX" , "09005858")));
			serviceImpl.addGebruiker(new Gebruiker("test", "test", "voornaam", "achternaam", "email@email.com"));
		}
	
}
