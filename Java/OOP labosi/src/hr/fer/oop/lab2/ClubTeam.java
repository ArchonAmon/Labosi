package hr.fer.oop.lab2;

/**
 * Class ClubTeam determines should a player belong to a team depending on his skills
 * 
 * @author Robert Pavliš
 */

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;


public class ClubTeam extends Team{
	
	int reputation = Constants.DEFAULT_REPUTATION;
	
	/**
	 * Calls the upper class of Team
	 * 
	 * @author Robert Pavliš
	 */
	
    public ClubTeam(){
        super(Constants.MAX_NO_PLAYERS_CLUB);
    }

	/**
	 * Creates a Club with a name, formation and reputation
	 * 
	 * @author Robert Pavliš
	 */
    
    public ClubTeam(String name, Formation formation, int reputation) {
        super(name, formation, Constants.MAX_NO_PLAYERS_CLUB);
        setReputation(reputation);
    }
    
	/**
	 * Sets the reputation of a team
	 * 
	 * @author Robert Pavliš
	 */

	public void setReputation(int reputation) {
		if (reputation >= Constants.MIN_REPUTATION && reputation <= Constants.MAX_REPUTATION)
		this.reputation = reputation;
		else System.err.println("Lose upisana reputacija");
	}
	
	/**
	 * Getter for team reputaton
	 * 
	 * @author Robert Pavliš
	 */
	
    public int getReputation() {
        return reputation;
    }
    
	/**
	 * Registers a player into a club
	 * 
	 * @author Robert Pavliš
	 */

	@Override
    public boolean registerPlayer(FootballPlayer player) {
        if (player == null)
            return false;
        if (player.getPlayingSkill() < reputation || getRegisteredPlayers().size() >= getRegisteredPlayers().getMaxSize())
            return false;

        getRegisteredPlayers().add(player);
        return true;
    }
	
	/**
	 * Rating calculation of a club
	 * 
	 * @author Robert Pavliš
	 */

	@Override
	public double calculateRating() {
		return Constants.THIRTY_PERCENT * getRegisteredPlayers().calculateEmotionSum() + Constants.SEVENTY_PERCENT * getRegisteredPlayers().calculateSkillSum();
	}
	
	
	
}
