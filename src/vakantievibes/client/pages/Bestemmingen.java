package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Bestemmingen extends VerticalPanel {
	
	public VerticalPanel hoofdPanel = new VerticalPanel(), reisPanel= new VerticalPanel();
	public Button bestemming,terug;
	public VakantieVibes vv;
	
	public Bestemmingen(VakantieVibes vv) {
		this.vv = vv;
		ArrayList<Bestemming> bestemmingen = vv.getBestemming();
		for(Bestemming b:bestemmingen) {
			VerticalPanel vp = new VerticalPanel();
			vp.add(new Label("Titel: "+b.getTitel()));
			vp.add(new Label("Locatie: "+b.getLocatie()));
			vp.add(new Label("Informatie: "+b.getInformatie()));
			bestemming = new Button("Zie de reizen");
			bestemming.addClickHandler(new MyClickHandler(b,this));
			vp.setStyleName("reis2");
			vp.add(bestemming);
			hoofdPanel.add(vp);
		}
		add(hoofdPanel);
	}
		
	class MyClickHandler implements ClickHandler {
		public Bestemming bestemming;
		public Bestemmingen pagina;
		
		public MyClickHandler(Bestemming b, Bestemmingen p) {
			bestemming=b;
			pagina=p;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			if(bestemming==null){
				pagina.naarHoofdPanel();
			} else {
				pagina.naarReis(bestemming);
			}
		}
	};
	
	public void naarReis(Bestemming b) {
		remove(hoofdPanel);
		reisPanel.add(new Reizen(vv, b));
		terug = new Button("Terug naar Bestemmingen");
		terug.addClickHandler(new MyClickHandler(null,this));
		terug.addStyleName("specialeButton");
		reisPanel.add(terug);
		add(reisPanel);
	}
	
	public void naarHoofdPanel() {
		remove(reisPanel);
		reisPanel= new VerticalPanel();
		add(hoofdPanel);
	}
}