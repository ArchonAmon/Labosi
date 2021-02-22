package hr.fer.oop.lab2.welcomepack;

import hr.fer.oop.lab2.FootballPlayer;

public interface Reporter {
	
	public void startMatch();
	
	public void skip(int minutes);
	
	public void goalScored(FootballPlayer scorer, FootballPlayer assistent, String comment);
	
	public void foul (FootballPlayer whoMadeFaul, FootballPlayer whoSuffered, String comment);
	
	public void printFullMatchReport();

}
