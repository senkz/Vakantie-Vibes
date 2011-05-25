package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class Reizen extends VerticalPanel {
	
	public ArrayList<VerticalPanel> reisInfo = new ArrayList<VerticalPanel>();
	public VerticalPanel hoofdPanel = new VerticalPanel(), boekReisPanel= new VerticalPanel();
	public DatePicker datum;
	public Button boek,terug;
	
	public Reizen(VakantieVibes vv) {
		String s="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		add(new Label("Kies een datum en een reis."));
		for(int i=0;i<3;i++) {
			reisInfo.add(new VerticalPanel());
			reisInfo.get(i).add(new Label(s));
			reisInfo.get(i).setTitle("Reis "+i);
		}
		for(VerticalPanel vp : reisInfo) {
			boek = new Button("Boek reis");
			datum = new DatePicker();
			boek.addClickHandler(new MyClickHandler(vp.getTitle(),this));
			vp.setStyleName("reis");
			vp.add(datum);
			vp.add(boek);
			hoofdPanel.add(vp);
		}
		add(hoofdPanel);
	}
		
	class MyClickHandler implements ClickHandler {
		public String reis;
		public Reizen pagina;
		
		public MyClickHandler(String r, Reizen p) {
			reis=r;
			pagina=p;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			if(reis.equals("terug")){
				pagina.naarHoofdPanel();
			} else {
				pagina.boekReis(reis);
			}
		}
	};
	
	public void boekReis(String r) {
		remove(hoofdPanel);
		boekReisPanel.add(new Label(r));
		terug = new Button("Terug naar overzicht");
		terug.addClickHandler(new MyClickHandler("terug",this));
		boekReisPanel.add(terug);
		add(boekReisPanel);
	}
	
	public void naarHoofdPanel() {
		remove(boekReisPanel);
		boekReisPanel= new VerticalPanel();
		add(hoofdPanel);
	}
}