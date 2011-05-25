package vakantievibes.client;


import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.pages.RegistrerenGebruiker;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Entry implements EntryPoint {
		static protected VakantieVibes serviceImpl;
		private TabBar myTabBar;
		
		@Override
		public void onModuleLoad() {
			
			
			TabPanel myTabPanel = new TabPanel();
			myTabBar = new TabBar();
			myTabBar = myTabPanel.getTabBar();
			
			myTabPanel.setSize("150px", "100px");
			myTabPanel.setAnimationEnabled(true);
			myTabPanel.add(new RegistrerenGebruiker(), "Reg. gebruiker");
			myTabPanel.selectTab(0);
			
			RootPanel.get("content").add(myTabPanel);
		}
	
}
