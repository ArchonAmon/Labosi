package hr.fer.oop.lab2.exceptions;

@SuppressWarnings("serial")
public class FootballPlayerPlayingSkillException extends FootballPlayerCreationException{

	public FootballPlayerPlayingSkillException(String message) {
        super(message);
	}
	
	FootballPlayerPlayingSkillException() {
		super();
	}
	
}
