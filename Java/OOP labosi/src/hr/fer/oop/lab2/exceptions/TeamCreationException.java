package hr.fer.oop.lab2.exceptions;

@SuppressWarnings("serial")
public class TeamCreationException extends FootballException{
	
	TeamCreationException(String message) {
        super(message);
	}
	
	TeamCreationException() {
		super();
	}

}
