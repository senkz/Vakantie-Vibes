package vakantievibes.client;


import java.util.Date;

import vakantievibes.client.domain.Adres;
import vakantievibes.client.domain.Bestemming;
import vakantievibes.client.domain.Gebruiker;
import vakantievibes.client.domain.Reis;
import vakantievibes.client.domain.VakantieVibes;
import vakantievibes.client.pages.AanpassenGebruiker;
import vakantievibes.client.pages.Bestemmingen;
import vakantievibes.client.pages.ContactPage;
import vakantievibes.client.pages.HomePage;
import vakantievibes.client.pages.InloggenPage;
import vakantievibes.client.pages.MijnBoekingen;
import vakantievibes.client.pages.RegistrerenGebruiker;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Entry implements EntryPoint {
	protected VakantieVibes serviceImpl;
	public TabBar myTabBar;

	@Override
	public void onModuleLoad() {
		serviceImpl = new VakantieVibes(this);

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
					p.add(new HomePage());
					break;
				case 1:
					if(serviceImpl.getLoginUser() == null)
						p.add(new RegistrerenGebruiker(serviceImpl));
					else
						p.add(new AanpassenGebruiker(serviceImpl));
					break;
				case 2:
					p.add(new Bestemmingen(serviceImpl));
					break;
				case 3:
					p.add(new MijnBoekingen(serviceImpl));
					break;
				case 4:
					p.add(new ContactPage());
					break;
				}
			}
		});

		myTabBar = new TabBar();
		myTabBar = myTabPanel.getTabBar();

		myTabPanel.add(new HorizontalPanel(), "Home");
		myTabPanel.add(new HorizontalPanel(), "Registreren");
		myTabPanel.add(new HorizontalPanel(), "Bestemmingen");
		myTabPanel.add(new HorizontalPanel(), "Mijn Boekingen");
		myTabPanel.add(new HorizontalPanel(), "Contact");
		
		myTabPanel.selectTab(0);

		myTabBar.setTabEnabled(3, false);

		RootPanel.get("login").add(new InloggenPage(serviceImpl));
		RootPanel.get("content").add(myTabPanel.getDeckPanel());
		RootPanel.get("header").add(myTabPanel.getTabBar());

		Gebruiker admin1 = new Gebruiker("admin", "admin", "henk", "klaas", "text@text.nl");
		serviceImpl.addGebruiker(admin1);
		admin1.setRechten(2);
		Bestemming b = new Bestemming("Egypte","Het prachtige land Egypte","Egypte, officieel de Arabische Republiek Egypte, is een staat in het noordoosten van Afrika. Het land omvat ook het schiereiland Sinaï, dat geografisch bij Azië wordt gerekend. Egypte grenst aan de Middellandse Zee in het noorden, de Gazastrook en Israël in het noordoosten, de Rode Zee in het oosten, Soedan in het zuiden en Libië in het westen. Egypte heeft een oppervlakte van 1.001.450 km² en ruim 83 miljoen inwoners. Van de bevolking woont een groot deel in de vruchtbare omgeving van de Nijl, die ook wel de 'levensader van Egypte' wordt genoemd. Hoewel de Nijlvallei en -delta slechts ongeveer 5,5 procent van de totale oppervlakte omvatten, woont hier bijna de gehele bevolking en bevinden zich hier de grote steden, zoals Caïro, de hoofdstad en grootste stad, en Alexandrië. Grote delen van de rest van het land worden bedekt door woestijnen als de Sahara.");
		Bestemming b1 = new Bestemming("Frankrijk","Croissant bij het ontbijt, wijn bij het avondmaal","Frankrijk, officieel de Franse Republiek (Frans: République française), is een land in West-Europa en qua oppervlakte het op twee na grootste Europese land. Frankrijk ligt tussen het Kanaal, de Atlantische Oceaan en de Golf van Biskaje (in het westen), België en Luxemburg (in het noorden), Duitsland, Zwitserland en Italië (in het oosten) en Spanje, Andorra, de Middellandse Zee en Monaco in het zuiden. Ook het eiland Corsica in de Middellandse Zee, behoort tot Frankrijk alsook vele overzeese gebieden. Inclusief overzeese gebieden heeft het land een oppervlakte van 674.843 km² en een bevolkingscijfer van 65,4 miljoen (januari 2010).[5] Frankrijk, vanwege de zeshoekige vorm ook wel l'Hexagone genoemd, is naar oppervlakte het grootste land binnen de Europese Unie. De hoofdstad is Parijs, dat ook verreweg de grootste stad van het land is.");
		Bestemming b2 = new Bestemming("Duitsland","Braadworst en Bier, vind je hier","De Duitsers zijn natuurlijk bekend om hun stevige maaltijden. Stampotten met bradwurst, of een flinke portie knudeln gaat er altijd wel in! Maar ook gerechten als Schwartenbraten, geroosterd varkensvlees met zuurkool en broodknudeln of Sauerbraten, in azijn gemarineerde rosbief dat vaak geserveerd wordt met appelmoes en aardappelknudeln, zijn heerlijk. Proeft u ook eens de heerlijke Kartoffelsalat, een aardappelsalade die gemaakt wordt met gemarineerde komkommer, ui en mayonaise. En natuurlijk zijn de Berliner, berliner bollen, een lekker tussendoortje! De nationale drank van de Duitsers is natuurlijk het bier. Het land produceert ook enkele wijnen.");
		serviceImpl.addBestemming(b);serviceImpl.addBestemming(b1);serviceImpl.addBestemming(b2);
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Piamiden expditie in Egypte", "In een hotel verblijven met de beroemde piramides van Gizeh op loopafstand, dat klinkt toch als een droom. Voor u is het werkelijkheid. Bekijk deze wereldwonderen uit de oudheid op eigen gelegenheid of met een excursie. Na een dag vol ervaringen is het heerlijk uitrusten op het balkon of terras van uw kamer terwijl de vogels in de tuin het hoogste lied fluiten.", b, new Adres("Egypte", "Movenpick Pyramids", "Egyptian street1", "69", "1337SX" , "09005858"), 100.00));
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Leuke reis om Egypte goed te verkennen", "Uiterst eenvoudig budgethotel voor zij die een zeer centrale ligging tegen een scherpe prijs verkiezen boven kwaliteit. Dit hotel is met name geschikt voor jongeren. Gelegen aan een drukke straat in het hart van Caïro.", b, new Adres("Egypte", "Lotus Cairo", "Egyptian street2", "70", "1334BG" , "0975458"), 100.00));
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Met deze reis leer de stad Parijs goed kennen", "Dit verfijnde hotel ligt in het hart van Parijs, tussen de Opera Garnier en het Place de la Madeleine. Het heeft een bar met glazen dak en de stijlvolle, geluiddichte kamers.", b1, new Adres("Frankrijk", "Astra Opera", "French street", "69", "1354BG" , "0964358"), 70.00));
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Zeer mooie reis voor een eerste bezoek aan Frankrijk", "Dit hotel is gelegen aan de Rue de Buci, in de wijk Saint Germain in Parijs. Het biedt comfortabele kamers met gratis WiFi. Dit boetiekhotel heeft 24 kamers en er hangt een gezellige sfeer.", b1, new Adres("Frankrijk", "Hotel De Buci", "French street2", "63", "1434BG" , "09532358"), 70.00));
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Leuke reis om Berlijn te ontdekken", "Eurostars Berlin ligt aan de beroemde winkelstraat Friedrichstra, en op 10 minuten lopen van de Brandenburger Tor. Het biedt gratis WiFi, een spa met een zwembad en een regionale keuken.", b2, new Adres("Duitsland", "Eurostars Berlin", "German street", "23", "3234BG" , "043255358"), 50.00));
		serviceImpl.addReis(new Reis(new Date(System.currentTimeMillis()+5000000), new Date(System.currentTimeMillis()+50000000), "Historische momenten leef je bij deze reis", "Dit designhotel ligt in het centrum van Berlijn, op 2 minuten lopen van het historische Checkpoint Charlie en op 3 minuten van het metrostation Stadtmitte.", b2, new Adres("Duitsland", "Hotel Gat Point Charlie", "German street2", "532", "3434BG" , "04324325358"), 50.00));
		Gebruiker g1 = new Gebruiker("wim", "isslim", "Wim", "Frederiks", "wim@frederiks.com");
		Adres a1 = new Adres("Nederland","utrecht","nijenoord","2e","3432ED","9393939339");
		g1.setAdres(a1);
		Gebruiker g2 = new Gebruiker("michiel", "isslim", "Michiel", "Borkent", "michiel@borkent.com");
		Adres a2 = new Adres("Nederland", "Amsterdam","straatweg","10","3451EO", "7435643320");
		g2.setAdres(a2);
		serviceImpl.addGebruiker(g1);
		serviceImpl.addGebruiker(g2);
	}

	public void changeTab(Gebruiker g) {
		if(g == null) {
			myTabBar.setTabEnabled(3, false);
			myTabBar.setTabText(1, "Registreren");
		} else {
			myTabBar.setTabEnabled(3, true);
			myTabBar.setTabText(1, "Instellingen");
		}
	}
}
