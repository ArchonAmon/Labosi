package hr.fer.oop.lab2.exceptions;

@SuppressWarnings("serial")
public class FootballPlayerCreationException extends FootballException {
	
	FootballPlayerCreationException(String message) {
        super(message);
	}
	
	FootballPlayerCreationException() {
		super();
	}

}
