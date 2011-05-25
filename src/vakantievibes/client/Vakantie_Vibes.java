package vakantievibes.client;


import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Vakantie_Vibes implements EntryPoint {

		private TabBar myTabBar;
		
		@Override
		public void onModuleLoad() {
			
			
			TabPanel myTabPanel = new TabPanel();
			myTabBar = new TabBar();
			myTabBar = myTabPanel.getTabBar();
			
			myTabPanel.setSize("150px", "100px");
			myTabPanel.setAnimationEnabled(true);
			myTabPanel.add(x, "Tab 1");
			myTabPanel.selectTab(0);
			
			RootPanel.get("content").add(myTabPanel);
		}
	
}
