package hr.fer.oop.lab2.demo;

import hr.fer.oop.lab2.Coach;
import hr.fer.oop.lab2.FootballPlayer;
import hr.fer.oop.lab2.NationalTeam;
import hr.fer.oop.lab2.welcomepack.*;

public class CoachTest {


	public static final String[] PLAYER_NAMES = { "Pilkotesa", "R Vokac", "Smitac", "D Misic", "Dolso", "Narij",
			"Nastic", "Prekinoski", "N Vokac", "Kuser", "Kobsic" };
	public static final PlayingPosition[] PLAYER_POSITIONS = { PlayingPosition.GK, PlayingPosition.DF,
			PlayingPosition.DF, PlayingPosition.DF, PlayingPosition.MF, PlayingPosition.MF, PlayingPosition.MF,
			PlayingPosition.MF, PlayingPosition.MF, PlayingPosition.FW, PlayingPosition.FW };
	public static final int PLAYERS_SIZE = 11;
	public static final int DEF_EMOTION = 80;
	public static final int DEF_SKILL = 85;

	public static final String DEF_COUNTRY = "Croatia";

	public static void main(String[] args) {

		Coach defaultCoach = new Coach();
		Coach mainCoach = new Coach("Main", Constants.DEFAULT_COUNTRY, 1, 1, Formation.F352);
		Coach helpCoach = new Coach();

		if (defaultCoach.equals(null)) {
			System.err.println("TEST FAILED: Trener ne bi smio biti null");
		}

		if (mainCoach.equals(defaultCoach)) {
			System.err.println("TEST FAILED: Dva trenera bi trebala biti drugacija");
		}

		helpCoach.setCoachingSkill(10);
		helpCoach.SetEmotion(10);
		if (!defaultCoach.equals(helpCoach)) {
			System.err.println("TEST FAILED: Prema uputama iz laboratorijske vjezbe, treneri bi trebali biti jednaki.");
		}

		Coach wrongCoach = new Coach(null, null, -5, 150, Formation.F541);
		if(wrongCoach.GetName()==null ||
				wrongCoach.GetCountry() == null ||
				wrongCoach.GetEmotion() <0 ||
				wrongCoach.getCoachingSkill() > 100){
			System.err.println("TEST FAILED: Vrijednosti trenera nisu dobre!");
		}

		FootballPlayer notEligiblePlayer = new FootballPlayer("Butatista", "Argentina", 65, 83, PlayingPosition.FW);

		Manager nationalManager = new Coach("Ban Vasten", "Dutch", 66, 70, Formation.F442);

		NationalTeam croatia = new NationalTeam("Croatia", Formation.F352, "Croatia");
		nationalManager.setManagingTeam(croatia);

		// stvaranje i registracija igraca:
		for (int i = 0; i < PLAYERS_SIZE; i++) {
			croatia.registerPlayer(new FootballPlayer(PLAYER_NAMES[i], DEF_COUNTRY, DEF_EMOTION, DEF_SKILL, PLAYER_POSITIONS[i]));
		}
		// dodavanje igraèa koji nije dobar za repku
		croatia.registerPlayer(notEligiblePlayer);


		SimpleFootballPlayerCollection croatianSquad = croatia.getRegisteredPlayers();
		System.out.println("Ispis repke, not eligible player ne bi smio biti na popisu... Butatista ne bi trebao biti na popisu...");
		printPlayerNames(croatianSquad);

		nationalManager.pickStartingEleven();
		System.out.println("Ispis pocetnih 11:");
		printPlayerNames(croatia.getStartingEleven());


		System.out.println("Manager forsira svoju 442 formaciju.");
		nationalManager.forceMyFormation();
		nationalManager.pickStartingEleven();
		System.out.println(
				"Sad mu nedostaje jedan branic, broj pocetne jedanaestorice: " + croatia.getStartingEleven().size());



		System.out.println("Ako nemate 'TEST FAILED' poruke onda je ovaj osnovni test prosao.\nSada napisite neke svoje testove kako biste isprobali druge funkcionalnosti.");

	}

	public static void printPlayerNames(SimpleFootballPlayerCollection players) {
		String output = "";
		for (FootballPlayer player : players.getPlayers()) {
			if(player!=null)
			output += player.GetName() + "\n";
		}
		System.out.println(output);
	}
}
