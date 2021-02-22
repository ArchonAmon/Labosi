package hr.fer.oop.lab2;

/**
 * This class makes an array of players who can be used in the club/national team
 * 
 * @author Robert Pavliš
 */

import hr.fer.oop.lab2.welcomepack.SimpleFootballPlayerCollection;

public class SimpleFootballPlayerCollectionImpl implements SimpleFootballPlayerCollection {

	private FootballPlayer[] player;
	
	/**
	 * Set the size of the array of football players
	 * 
	 * @author Robert Pavliš
	 */
	
    public SimpleFootballPlayerCollectionImpl(int size) {
        this.player = new FootballPlayer[size];
    }
    
	/**
	 * Adds the football player to the array
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public boolean add(FootballPlayer player) {
        if (!contains(player)) {
            for (int i = 0; i < this.player.length; i++) {
                if (this.player[i] == null) {
                    this.player[i] = player;
                    return true;
                }
            }
        }
        return false;
    }
    
	/**
	 * Calculates the emotion of all players in the array
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public int calculateEmotionSum() {
        int emotionSum = 0;
        for (FootballPlayer p : this.player) {
            if (p != null)
                emotionSum += p.GetEmotion();
        }
        return emotionSum;
    }
	
	/**
	 * Calculates the skill of all players in the array
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public int calculateSkillSum() {
        int skillSum = 0;
        for (FootballPlayer p : player) {
            if (p != null)
                skillSum += p.getPlayingSkill();
        }
        return skillSum;
    }
    
	/**
	 * Clears a player from the array
	 *  
	 * @author Robert Pavliš
	 */
    
    @Override
    public void clear () {
    	for (int i = 0;  i < player.length; i++) {
    		player[i] = null;
    	}
    }
    
	/**
	 * Checks if the player is contained within the array
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public boolean contains (FootballPlayer player) {
    	if (player == null)
            return false;
        for (FootballPlayer p : this.player) {
            if (p != null) {
                if (player.equals(p))
                    return true;
            }
    	}
    	return false;
    }
    
	/**
	 * Getter for the maximum size of the array
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public int getMaxSize () {
    	return this.player.length;
    }
    
	/**
	 * Getter for the players of the array
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
	public FootballPlayer[] getPlayers () {
		return player;
	}
    
	/**
	 * Returns the size of the array
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public int size() {
        int size = 0;
        for (FootballPlayer player : this.player) {
            if (player != null) {
                size++;
            }
        }
        return size;
    }
	
}
