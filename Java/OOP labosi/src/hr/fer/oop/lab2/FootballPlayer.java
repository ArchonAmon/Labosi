package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.PlayingPosition;

/**
 * The class of FootballPlayer has the characteristics of a person and playingskill and position
 * 
 * @author Robert Pavliš
 */

public class FootballPlayer extends Person {

	int PlayingSkill = Constants.DEFAULT_PLAYING_SKILL;
    private PlayingPosition Position = Constants.DEFAULT_PLAYING_POSITION;
    
	/**
	 * Calls the upper class of FootballPlayer (person)
	 * 
	 * @author Robert Pavliš
	 */
	
	public FootballPlayer (){
        super();
    }
	
	/**
	 * Creates a football player
	 * 
	 * @author Robert Pavliš
	 */
	
	public FootballPlayer (String name, String country, int emotion, int skill, PlayingPosition pos) {
		super(name, country, emotion);
		setPlayingPosition (pos);
		setPlayingSkill (skill);
	}
	
	/**
	 * Sets the playing skill of a player
	 * 
	 * @author Robert Pavliš
	 */
	
	public void setPlayingSkill(int skill) {
		if (skill >= Constants.MIN_PLAYING_SKILL && skill <= Constants.MAX_PLAYING_SKILL) 
			PlayingSkill = skill;
		else System.err.println("Lose postavljen playing skill");
	}
	
	/**
	 * Getter for the playing skill of a player
	 * 
	 * @author Robert Pavliš
	 */
	
	public int getPlayingSkill () {
		return PlayingSkill;
	}
	
	/**
	 * Getter for the playing position of the player
	 * 
	 * @author Robert Pavliš
	 */
	
	public PlayingPosition getPlayingPosition () {
		return Position;
	}
	
	/**
	 * Sets the playing position of the player
	 * 
	 * @author Robert Pavliš
	 */
	
    public void setPlayingPosition(PlayingPosition playingPosition) {
        if(playingPosition!=null)
            this.Position = playingPosition;
        else System.err.println("Lose upisana pozicija");
    }
	
}
