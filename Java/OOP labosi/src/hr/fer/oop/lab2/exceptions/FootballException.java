package hr.fer.oop.lab2.exceptions;

@SuppressWarnings("serial")
public class FootballException extends RuntimeException{
	
	FootballException(String message) {
        super(message);
	}
	
	FootballException() {
		super();
	}

}
