package vakantievibes.client.pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vakantievibes.client.domain.Adres;
import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminPage extends FormPanel implements ClickHandler 
{

	private VakantieVibes serviceImpl;
	private VerticalPanel mainvp, hrvp, hbvp;
	private HorizontalPanel menuhp;
	private Button breis, bbestem, edit, delete,bbestemtoe,breistoe;
	private TextBox tbbnm, tbbinfo,tbloc, tbrnm, tbrvdat, tbrtdat,tbrinfo, tbrtp, tbrb;
	private Label lbloc, lbnm, lbinfo, lrnm, lrvdat, lrtdat,lrinfo, lrb, lrtp;
	private TextBox tbrl, tbrs, tbrst, tbrhn, tbrpc, tbrtf;
	private Label lrl, lrs, lrst, lrhn, lrpc, lrtf, lab;
	private ListBox lb;
	private ArrayList<HorizontalPanel> hplist = new ArrayList<HorizontalPanel>();
	private ArrayList<HorizontalPanel> hplist2 = new ArrayList<HorizontalPanel>();
	private ArrayList<Bestemming> bestemmingen;
	private ArrayList<Reis> reizen;

	public AdminPage(VakantieVibes sI)
	{

		serviceImpl = sI;
		bestemmingen = serviceImpl.getBestemming();
		reizen = serviceImpl.getReizen();



		tbbnm = new TextBox();		lbnm = new Label("titel bestemming");
		tbbinfo = new TextBox();	lbinfo = new Label("info");
		tbloc = new TextBox();		lbloc = new Label("locatie");

		tbrnm = new TextBox();		lrnm = new Label("titel reis");
		tbrvdat = new TextBox();	lrvdat = new Label("vertrek datum");
		tbrtdat = new TextBox();	lrtdat = new Label("terugkomst datum");
		tbrinfo	= new TextBox();	lrinfo = new Label("informatie");
		tbrtp = new TextBox();		lrtp = new Label("totaal prijs");
		tbrl = new TextBox();		lrl = new Label("land");
		tbrs = new TextBox();		lrs = new Label("stad");
		tbrst = new TextBox();		lrst = new Label("straat");
		tbrhn = new TextBox();		lrhn = new Label("huisnummer");
		tbrpc = new TextBox();		lrpc = new Label("postcode");
		tbrtf = new TextBox();		lrtf = new Label("telefoonnr");
		tbrb = new TextBox();		lrb = new Label("bestemming");							

		mainvp  = new VerticalPanel();
		menuhp = new HorizontalPanel();		mainvp.add(menuhp); 
		hrvp  = new VerticalPanel();		mainvp.add(hrvp);	hrvp.setVisible(false);
		hbvp  = new VerticalPanel();		mainvp.add(hbvp);	hbvp.setVisible(false);


		breis = new Button("reis");			menuhp.add(breis);		breis.addClickHandler(this);
		bbestem = new Button("bestemming");	menuhp.add(bbestem);	bbestem.addClickHandler(this);
		bbestemtoe = new Button("toevoegen"); 						bbestemtoe.addClickHandler(this);
		breistoe = new Button("toevoegen");							breistoe.addClickHandler(this);
		
		add(mainvp);

	}

	public void onClick(ClickEvent event) 
	{
		Widget sender = (Widget) event.getSource();

		if (sender == breis) 
		{
			hplist2.clear(); hrvp.clear();
			lb = new ListBox(); 
			for(Bestemming b : bestemmingen){
				lb.addItem(b.getTitel());

			}
			lb.setVisibleItemCount(3);

			hrvp.add(lrnm);hrvp.add(tbrnm);hrvp.add(lrvdat);hrvp.add(tbrvdat);
			hrvp.add(lrtdat);hrvp.add(tbrtdat);hrvp.add(lrinfo);hrvp.add(tbrinfo);
			hrvp.add(lrtp);hrvp.add(tbrtp);hrvp.add(lrl);hrvp.add(tbrl);hrvp.add(lrs);
			hrvp.add(tbrs);hrvp.add(lrst);hrvp.add(tbrst);hrvp.add(lrhn);hrvp.add(tbrhn);
			hrvp.add(lrpc);hrvp.add(tbrpc);hrvp.add(lrtf);hrvp.add(tbrtf);
			hrvp.add(lrb);hrvp.add(lb);hrvp.add(breistoe);
			hrvp.setVisible(true);
			hbvp.setVisible(false);


			for(Reis r : reizen)
			{
				HorizontalPanel hp2 = new HorizontalPanel();
				hp2.add(new Label(r.getTitel()));
				edit = new Button("edit") ;
				delete = new Button("X");
				hp2.add(delete);
				hp2.add(edit);
				hplist2.add(hp2);
				delete.addClickHandler(new Mydelete(r));
				edit.addClickHandler(new Myedit(r));
			}
			for(HorizontalPanel hp2 : hplist2)
			{
				hrvp.add(hp2); 
			} 
		}
		if (sender == bbestem) 
		{
			hplist.clear(); hbvp.clear();
			hbvp.add(lbnm);hbvp.add(tbbnm);hbvp.add(lbinfo); hbvp.add(tbbinfo); 
			hbvp.add(lbloc);hbvp.add(tbloc);
			hbvp.add(bbestemtoe);
			hrvp.setVisible(false);
			hbvp.setVisible(true);
			System.out.println("printbbestem");
			for(Bestemming b : bestemmingen)
			{
				HorizontalPanel hp = new HorizontalPanel();
				hp.add(new Label(b.getTitel()));
				lab = new Label(b.getInformatie());

				hp.add(lab);

				edit = new Button("edit") ;
				delete = new Button("X");
				hp.add(delete);
				hp.add(edit);
				hplist.add(hp);
				delete.addClickHandler(new Mydelete2(b));
				edit.addClickHandler(new Myedit2(b));

			}
			for(HorizontalPanel hp : hplist)
			{
				hbvp.add(hp);   
			}
		}
		if (sender == bbestemtoe)
		{
			Bestemming b = new Bestemming(tbloc.getText(),tbbnm.getText(),tbbinfo.getText());
			serviceImpl.addBestemming(b);
			refreshPanelBestem();
		}
		if (sender == breistoe)
		{
			int teller = 0;
			String bestemtoev = lb.getValue(lb.getSelectedIndex());
			DateFormat myDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date myDate = null;
			Date myDate2 = null;
			try {
			     myDate = myDateFormat.parse(tbrvdat.getText());
			     myDate2 = myDateFormat.parse(tbrtdat.getText());
			}  catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double tp = Double.parseDouble(tbrtp.getText());
		
			for(Bestemming b: bestemmingen) {
				if(bestemtoev.equals(b.getTitel())){
								
					Adres ad = new Adres(tbrl.getText(), tbrs.getText(), tbrst.getText(), tbrhn.getText(), tbrpc.getText(), lrtf.getText());
					Bestemming best = b;
					Reis reisjes = new Reis(myDate, myDate2, tbrnm.getText(), tbrinfo.getText(),b, ad, tp);	
					serviceImpl.addReizen(reisjes, best, ad);
					teller++;
					if(teller == 1)
					{
						System.out.println("teller is " + teller);
						refreshpanelreis();
						teller--;
					}
					
				}
			}

			
		}
	}
	class Mydelete implements ClickHandler 
	{
		private Reis reisjes;

		public Mydelete(Reis r) 
		{
			reisjes = r;
		}

		@Override
		public void onClick(ClickEvent event) 
		{
			int teller = 0;
			serviceImpl.removeReis(reisjes);
			teller++;
			if(teller == 1)
			{
				refreshpanelreis();
			}			
		}
	};

	class Myedit implements ClickHandler 
	{
		public Reis reis;


		public Myedit(Reis r)
		{
			reis = r;
		}

		@Override
		public void onClick(ClickEvent event) {
			showEditAdresMenu(reis);
		}
	};

	private void showEditAdresMenu(Reis reis) 
	{
		HorizontalPanel hp3 = new HorizontalPanel();
		HorizontalPanel hp4 = new HorizontalPanel();
		final TextBox tfinfo =  new TextBox();
		final TextBox tftpr =  new TextBox();

		Button bt = new Button("voer in");
		hp4.add(new Label("informatie")); hp4.add(new Label("totaalprijs"));  
		hp3.add(tfinfo); hp3.add(tftpr);
		hp3.add(bt);
		hp3.setVisible(true);
		hrvp.add(hp4);
		hrvp.add(hp3);

		final Reis r = reis;
		bt.addClickHandler(new ClickHandler() 
		{

			@Override
			public void onClick(ClickEvent event) 
			{
				int teller = 0;
				String info = tfinfo.getText();
				double totpr = Double.parseDouble(tftpr.getText());

				serviceImpl.changeReis(r, info, totpr);
				teller++;
				if(teller == 1) 
				{
					refreshpanelreis();
					teller--;
				}

			}

		});
	}

	class Mydelete2 implements ClickHandler {
		private Bestemming bestem;

		public Mydelete2(Bestemming b) 
		{
			bestem = b;
		}

		@Override
		public void onClick(ClickEvent event) 
		{
			serviceImpl.removeBestemming(bestem);
			refreshPanelBestem();
		}
	};
	class Myedit2 implements ClickHandler 
	{
		public Bestemming bs;


		public Myedit2(Bestemming b) 
		{
			bs = b;
		}

		@Override
		public void onClick(ClickEvent event) 
		{
			showeditmenu(bs);
		}
	};

	public void showeditmenu(Bestemming bs) 
	{
		HorizontalPanel hp2 = new HorizontalPanel();
		final TextBox tf =  new TextBox();
		Button bt = new Button("voer in");
		hp2.add(tf); hp2.add(bt);
		hp2.setVisible(true);
		hbvp.add(hp2);


		final Bestemming b = bs;
		bt.addClickHandler(new ClickHandler() 
		{

			@Override
			public void onClick(ClickEvent event) 
			{
				int teller = 0;
				String bestem = tf.getText();
				serviceImpl.changeBestemming(b, bestem);
				teller++;
				if(teller == 1) 
				{
					refreshPanelBestem();
					teller--;
				}

			}
		});
	}
	public void refreshpanelreis()
	{
		hplist2.clear(); hrvp.clear();
		lb = new ListBox(); 
		for(Bestemming b : bestemmingen){
			lb.addItem(b.getTitel());

		}
		lb.setVisibleItemCount(3);

		hrvp.add(lrnm);hrvp.add(tbrnm);hrvp.add(lrvdat);hrvp.add(tbrvdat);
		hrvp.add(lrtdat);hrvp.add(tbrtdat);hrvp.add(lrinfo);hrvp.add(tbrinfo);
		hrvp.add(lrtp);hrvp.add(tbrtp);hrvp.add(lrl);hrvp.add(tbrl);hrvp.add(lrs);
		hrvp.add(tbrs);hrvp.add(lrst);hrvp.add(tbrst);hrvp.add(lrhn);hrvp.add(tbrhn);
		hrvp.add(lrpc);hrvp.add(tbrpc);hrvp.add(lrtf);hrvp.add(tbrtf);
		hrvp.add(lrb);hrvp.add(lb);hrvp.add(breistoe);
		hrvp.setVisible(true);
		hbvp.setVisible(false);


		for(Reis r : reizen)
		{
			HorizontalPanel hp2 = new HorizontalPanel();
			hp2.add(new Label(r.getTitel()));
			edit = new Button("edit") ;
			delete = new Button("X");
			hp2.add(delete);
			hp2.add(edit);
			hplist2.add(hp2);
			delete.addClickHandler(new Mydelete(r));
			edit.addClickHandler(new Myedit(r));
		}
		for(HorizontalPanel hp2 : hplist2)
		{
			hrvp.add(hp2); 
		} 
	}
	public void refreshPanelBestem()
	{
		hplist.clear(); hbvp.clear();
		hbvp.add(lbnm);hbvp.add(tbbnm);hbvp.add(lbinfo); hbvp.add(tbbinfo); 
		hbvp.add(lbloc);hbvp.add(tbloc);
		hbvp.add(bbestemtoe);
		hrvp.setVisible(false);
		hbvp.setVisible(true);
		//System.out.println("printbbestem");
		for(Bestemming b : bestemmingen)
		{
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(new Label(b.getTitel()));
			lab = new Label(b.getInformatie());

			hp.add(lab);

			edit = new Button("edit") ;
			delete = new Button("X");
			hp.add(delete);
			hp.add(edit);
			hplist.add(hp);
			delete.addClickHandler(new Mydelete2(b));
			edit.addClickHandler(new Myedit2(b));

		}
		for(HorizontalPanel hp : hplist)
		{
			hbvp.add(hp);
		}
	}
}

