import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class AtleetManager {
// Assignment 1: Maak een lijst van 50 atleten.
// Vul ze steeds met andere sporten, dus bijv een aantal boksers, voetballers etc ec
// Maak deze ook aan met een variabel geboortejaar (tussen 1990-2005))

//  - zoek naar alle atleten die zwemmen en druk deze dan af
//  - zoek naar alle atleten die jair heten en zwemmen en druk deze dan af
// vul de 
//  (*) zoek naar alle atleten die jonger zijn dan 20 jaar

	/*
	 * Assignment:
	 * 
	 * 1) Zorg ervoor dat de geboortedatum willekeurig is tussen 2 data bijv atleten
	 * moeten geboren zijn tussen 1990 - 2003
	 * 
	 * 2) Zoek naar alle individuele sporters
	 * 
	 * 3) Druk af hoeveel teamsporters er in de lijst zitten
	 * 
	 * 4) Druk af hoeveel sporters er zijn die jarig zijn in Maart
	 *
	 * 5) Druk af hoeveel sporters er zijn die geboren zijn in 2000-2002
	 * 
	 * 6) Druk de geboortedatum af in de Europese notatie 1 January 2002 (en niet de
	 * Amerikaanse 2002-01-01)
	 */

	public enum Sport {
		BASKETBAL(false), ZWEMMEN(true), VOLLEYBAL(false), KOGELSTOTEN(true), WIELRENNEN(true), BOKSEN(true),
		JUDO(true), CROSSFIT(true), MMA(true);

		private boolean individueel;

		private Sport(boolean individueel) {
			this.individueel = individueel;
		}

		public boolean isIndividueel() {
			return individueel;
		}
	};

	private String voorNaam;
	private String achterNaam;
	private LocalDate geboorteDatum;
	public Sport sportCategorie;

	public Sport[] sportLijst = { Sport.BASKETBAL, Sport.ZWEMMEN, Sport.VOLLEYBAL, Sport.KOGELSTOTEN, Sport.WIELRENNEN,
			Sport.BOKSEN, Sport.JUDO, Sport.CROSSFIT, Sport.MMA };

	public AtleetManager(String voorNaam, String achterNaam, LocalDate geboorteDatum, Sport sportCategorie) {

		this.voorNaam = voorNaam;
		this.achterNaam = achterNaam;
		this.geboorteDatum = geboorteDatum;
		this.sportCategorie = sportCategorie;
	}

	private AtleetManager[] lijst = new AtleetManager[50];

	private AtleetManager[] recordGenerator() {
		Random random = new Random();
		String[] voorNamen = { "Bryan", "John", "Tim", "Alicia", "Elon", "Serena", "Conor", "Kobe", "Jair" };
		String[] achterNamen = { "Doorson", "Cena", "Duncan", "Keys", "Musk", "Williams", "Megreggor", "Bryant",
				"Tjon en Fa" };

		lijst = new AtleetManager[50];

		int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2005, 12, 31).toEpochDay();

		for (int i = 0; i < 50; i++) {
			int randomVoorNaamIndex = random.nextInt(voorNamen.length);
			int randomAchterNaamIndex = random.nextInt(achterNamen.length);
			int randomSportTypeIndex = random.nextInt(sportLijst.length);
			long randomDay = minDay + random.nextInt(maxDay - minDay);

			String randomVoorNaam = voorNamen[randomVoorNaamIndex];
			String randomAchterNaam = achterNamen[randomAchterNaamIndex];
			LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
			Sport randomSportType = sportLijst[randomSportTypeIndex];

			lijst[i] = new AtleetManager(randomVoorNaam, randomAchterNaam, randomBirthDate, randomSportType);

		}
		return lijst;
	}

	private int minLeefTijd;
	private int maxLeefTijd;
	private Month kiesMaand;
	private int jaar1;
	private int jaar2;

	private void generator() {

		LocalDate today = LocalDate.now();
		LocalDate gekozenLeeftijd = today.minusYears(maxLeefTijd);
		LocalDate gekozenLeeftijd2 = today.minusYears(minLeefTijd);

		LocalDate eersteJaar = LocalDate.of(jaar1, 1, 1);
		LocalDate tweedeJaar = LocalDate.of(jaar2, 12, 31);

		for (int i = 0; i < recordGenerator().length; i++) {
			if (voorNaam == null && sportCategorie == null && maxLeefTijd == 0 && kiesMaand == null && minLeefTijd == 0
					&& jaar1 == 0 && jaar2 == 0
					|| (lijst[i].voorNaam.equals(voorNaam) || lijst[i].sportCategorie.equals(sportCategorie)
							|| lijst[i].geboorteDatum.isAfter(gekozenLeeftijd)
							|| lijst[i].geboorteDatum.getMonth() == kiesMaand
							|| (lijst[i].geboorteDatum.isAfter(gekozenLeeftijd)
									&& (lijst[i].geboorteDatum.isBefore(gekozenLeeftijd2)))
							|| (lijst[i].geboorteDatum.isAfter(eersteJaar)
									&& lijst[i].geboorteDatum.isBefore(tweedeJaar)))) {

				System.out.println("Voornaam	: " + lijst[i].voorNaam);
				System.out.println("Achernaam	: " + lijst[i].achterNaam);
				System.out.println("GeboorteDatum	: " + lijst[i].geboorteDatum.getDayOfMonth() + " "
						+ lijst[i].geboorteDatum.getMonth() + " " + lijst[i].geboorteDatum.getYear());
				System.out.println("Sport Categorie	: " + lijst[i].sportCategorie);
				System.out.println();
			}

		}

	}

	public void zoek(String zoekVoorNaam) {
		this.voorNaam = zoekVoorNaam;
		generator();
	}

	public void zoek(AtleetManager.Sport sportCategorie) {
		this.sportCategorie = sportCategorie;
		generator();
	}

	public void zoek(int maxLeefTijd) {
		this.maxLeefTijd = maxLeefTijd;
		generator();
	}

	public void zoek(Month kiesMaand) {
		this.kiesMaand = kiesMaand;
		generator();
	}

//	public void zoek(int minLeefTijd, int maxleeftijd) {
//		this.minLeefTijd = minLeefTijd;
//		this.maxLeefTijd = maxleeftijd;
//		generator();
//	}

	public void zoek(int jaar1, int jaar2) {
		this.jaar1 = jaar1;
		this.jaar2 = jaar2;
		generator();
	}

	public void zoek() {
		generator();
	}
}