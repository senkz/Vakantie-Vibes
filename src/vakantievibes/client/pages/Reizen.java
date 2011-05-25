package vakantievibes.client.pages;

import java.util.ArrayList;

import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Reizen extends VerticalPanel {
	
	public ArrayList<Label> reisInfo = new ArrayList<Label>();
	
	public Reizen(VakantieVibes vv) {
		String s="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		for(int i=0;i<3;i++) {
			reisInfo.add(new Label(s));
		}
		for(Label l : reisInfo) {
			add(l);
		}
	}
}