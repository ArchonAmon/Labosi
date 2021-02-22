package Labos2;

import hr.fer.oop.lab2.Reporter_class;
import hr.fer.oop.lab2.FootballPlayer;
import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.PlayingPosition;


public class Main {
	
	public static void main (String[] args) {
		
		FootballPlayer suker = new FootballPlayer("suker", Constants.DEFAULT_COUNTRY, 50, 50,
				PlayingPosition.FW);

		FootballPlayer jozic = new FootballPlayer("jozic", Constants.DEFAULT_COUNTRY, 50, 50,
				PlayingPosition.FW);
		
		Reporter_class rep = new Reporter_class();
		
		rep.startMatch();
		
		rep.skip(45);
		
		rep.goalScored(suker, jozic, null);
		
		rep.skip(10);
		
		rep.foul(suker, jozic, null);
		
		rep.skip(50);
		
		rep.printFullMatchReport();
		
	
	
	
	}


}
