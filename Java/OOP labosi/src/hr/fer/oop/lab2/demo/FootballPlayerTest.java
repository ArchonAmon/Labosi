package hr.fer.oop.lab2.demo;

import hr.fer.oop.lab2.FootballPlayer;
import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.PlayingPosition;

public class FootballPlayerTest {

	public static void main(String[] args) {

		FootballPlayer defaultPlayer = new FootballPlayer();

		if (defaultPlayer.equals(null)) {
			System.err.println("TEST FAILED: Igrac ne bi smio biti jednak null.");
		}
		
		FootballPlayer unemotional = new FootballPlayer("Player", Constants.DEFAULT_COUNTRY, -10, 110,
					PlayingPosition.FW);
		if(unemotional.GetEmotion() < 0 || unemotional.getPlayingSkill() > 100){
			System.err.println("TEST FAILED: Emocija mora biti unutar raspona!");
		}
		
		FootballPlayer emotionalPlayer = new FootballPlayer("Player", Constants.DEFAULT_COUNTRY, 110, -10,
					PlayingPosition.GK);
		if(emotionalPlayer.GetEmotion() > 100 || emotionalPlayer.getPlayingSkill() < 0){
			System.err.println("TEST FAILED: Emocija mora biti unutar raspona!!");
		}
		
		FootballPlayer waterBoy = new FootballPlayer("Player", Constants.DEFAULT_COUNTRY, 100, 100, null);
		if(waterBoy.getPlayingPosition() == null){
			System.err.println("TEST FAILED: Vrijednosti igraca moraju biti u rasponu!");
		}


		System.out.println("Ako nemate 'TEST FAILED' poruke onda je ovaj osnovni test prosao.\nSada napisite neke svoje testove kako biste isprobali druge funkcionalnosti.");

	}
}
