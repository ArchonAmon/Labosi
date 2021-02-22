package hr.fer.oop.lab2.demo;

import hr.fer.oop.lab2.FootballPlayer;
import hr.fer.oop.lab2.NationalTeam;
import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.PlayingPosition;

public class NationalTeamTest {

	public static void main(String[] args) {

		final double DELTA = 0.001;
		NationalTeam defaultTeam = new NationalTeam();

		FootballPlayer player = new FootballPlayer();
		defaultTeam.registerPlayer(player);
		if (!defaultTeam.isPlayerRegistered(player)) {
			System.err.println("TEST FAILED: Igrac bi trebao biti registriran!");
		}

		FootballPlayer foreignPlayer = new FootballPlayer("Foreigner", "Sjeverna Koreja", 1, 1, PlayingPosition.DF);
		defaultTeam.registerPlayer(foreignPlayer);
		if (defaultTeam.isPlayerRegistered(foreignPlayer)) {
			System.err.println("TEST FAILED: Drzave nisu iste, provjeriti registracijska pravila.");
		}

		NationalTeam ratingTeam = new NationalTeam();
		for (int i = 0; i < 20; i++) {
			FootballPlayer sequentialPlayer = new FootballPlayer("NAME" + i, Constants.DEFAULT_COUNTRY, 10, 100,
					PlayingPosition.GK);
			ratingTeam.registerPlayer(sequentialPlayer);
		}
		double expected = (10 * Constants.SEVENTY_PERCENT + 100 * Constants.THIRTY_PERCENT) * 20;
		if (Math.abs(ratingTeam.calculateRating() - expected) > DELTA) {
			System.err.println("TEST FAILED: Izracun tima nije dobar");
		}


		System.out.println("Ako nemate 'TEST FAILED' poruke onda je ovaj osnovni test prosao.\nSada napisite neke svoje testove kako biste isprobali druge funkcionalnosti.");

	}

}
