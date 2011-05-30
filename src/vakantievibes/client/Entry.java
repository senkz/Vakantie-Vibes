package vakantievibes.client;


import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.pages.AanpassenGebruiker;
import vakantievibes.client.pages.InloggenPage;
import vakantievibes.client.pages.RegistrerenGebruiker;
import vakantievibes.client.pages.Reizen;

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
							p.add(new Reizen(serviceImpl));
							break;
						case 2:
							p.add(new InloggenPage(serviceImpl));
							break;
						case 3:
							p.add(new AanpassenGebruiker(serviceImpl));
							break;
					}
				}
			});
			
			myTabPanel.add(new HorizontalPanel(), "Reg. gebruiker");
			myTabPanel.add(new HorizontalPanel(), "Boek Reis");
			myTabPanel.add(new HorizontalPanel(), "Inloggen");
			myTabPanel.add(new HorizontalPanel(), "Aanpassen");
			myTabPanel.selectTab(0);
			
			RootPanel.get("content").add(myTabPanel);
		}
	
}
