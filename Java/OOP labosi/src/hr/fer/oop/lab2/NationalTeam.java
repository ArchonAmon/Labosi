package hr.fer.oop.lab2;

/**
 * The national team is a team who is playing for a country (has the characteristics of a team and a country to play for)
 * 
 * @author Robert Pavliš
 */

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;

public class NationalTeam extends Team{
	
	private String country = Constants.DEFAULT_COUNTRY;
	
	/**
	 * Links the upper class of national team and that is Team
	 * 
	 * @author Robert Pavliš
	 */

    public NationalTeam(){
        super(Constants.MAX_NO_PLAYERS_NATIONAL);
    }
    
	/**
	 * Creates a national team
	 * 
	 * @author Robert Pavliš
	 */

    public NationalTeam(String name, Formation formation, String country) {
        super(name, formation, Constants.MAX_NO_PLAYERS_NATIONAL);
        if(country != null)
            this.country = country;
        else System.err.println("Drzava je null! :(");
    }

	/**
	 * Registers players for the national team
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public boolean registerPlayer(FootballPlayer player) {
        if(player==null)
            return false;
        if(!player.GetCountry().equals(country) || getRegisteredPlayers().size() >= getRegisteredPlayers().getMaxSize())
            return false;

        getRegisteredPlayers().add(player);
        return true;
    }
    
	/**
	 * Calculates the rating of the national team
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public double calculateRating() {
        return Constants.SEVENTY_PERCENT * getRegisteredPlayers().calculateEmotionSum() + Constants.THIRTY_PERCENT * getRegisteredPlayers().calculateSkillSum();
    }

	
	
}
