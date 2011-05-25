package vakantievibes.client;


import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.pages.RegistrerenGebruiker;
import vakantievibes.client.pages.Reizen;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Entry implements EntryPoint {
		protected VakantieVibes serviceImpl;

		@Override
		public void onModuleLoad() {
			serviceImpl = new VakantieVibes();
			
			TabPanel myTabPanel = new TabPanel();
			myTabBar = myTabPanel.getTabBar();
			
			myTabPanel.setSize("800px", "100px");
			myTabPanel.setAnimationEnabled(true);
			myTabPanel.add(new RegistrerenGebruiker(serviceImpl), "Reg. gebruiker");
			myTabPanel.add(new Reizen(serviceImpl), "Boek Reis");
			myTabPanel.add(new InloggenPage(serviceImpl), "Inloggen");
			myTabPanel.selectTab(0);
			
			RootPanel.get("content").add(myTabPanel);
		}
	
}
