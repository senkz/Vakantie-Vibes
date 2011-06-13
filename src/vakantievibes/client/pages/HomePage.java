package vakantievibes.client.pages;

import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HomePage extends FormPanel {
	
	private VakantieVibes serviceImpl;
	private VerticalPanel vp = new VerticalPanel();
	private Label lkop = new Label("Welkom bij VakantieVibes"), ltext = new Label("Op deze site kan je een vakantie boeken, die stukken goedkoper uitvalt dan alle andere reis organisaties. Zon zee strand vakanties? Of wil je liever skiën in de bergen? Wil je een city trip of ga je liever ronddolen in oude kastelen wij hebben het allemaal");
	
	public HomePage(VakantieVibes sI){
		serviceImpl = sI;
		
		vp.add(lkop);vp.add(ltext);
		add(vp);
	}

}
