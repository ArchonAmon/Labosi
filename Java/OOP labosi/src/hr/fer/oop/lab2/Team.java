package hr.fer.oop.lab2;

/**
 * Abstract class that is implemented by the club and national team.
 * Has a name, formation, registered players and the starting eleven players
 * 
 * @author Robert Pavliš
 */

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;
import hr.fer.oop.lab2.welcomepack.ManageableTeam;
import hr.fer.oop.lab2.welcomepack.SimpleFootballPlayerCollection;

public abstract class Team implements ManageableTeam {

	private String name = Constants.DEFAULT_TEAM_NAME;
	private Formation Formation = Constants.DEFAULT_FORMATION;
	private SimpleFootballPlayerCollection registeredPlayers;
    private SimpleFootballPlayerCollection startingEleven = new SimpleFootballPlayerCollectionImpl(Constants.STARTING_ELEVEN_SIZE);
	
	/**
	 * Creates a new team
	 * 
	 * @author Robert Pavliš
	 */
	
    public Team(String name, Formation formation, int registeredPlayersSize) {
        this.registeredPlayers = new SimpleFootballPlayerCollectionImpl(registeredPlayersSize);

        if(name != null){
            this.setName(name);
        } else System.err.println("Ime je null");

        setFormation(formation);
    }

	/**
	 * Creates an array with the registered players
	 * 
	 * @author Robert Pavliš
	 */
    
    public Team(int registeredPlayersSize){
        this.registeredPlayers = new SimpleFootballPlayerCollectionImpl(registeredPlayersSize);
    }

	/**
	 * Adds a player to the starting eleven
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public boolean addPlayerToStartingEleven(FootballPlayer player) {
        if(registeredPlayers.contains(player) && !startingEleven.contains(player) && startingEleven.size() < Constants.STARTING_ELEVEN_SIZE){
            startingEleven.add(player);
            return true;
        }
        return false;
    }
    
	/**
	 * Clears a player from the starting eleven
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public void clearStartingEleven() {
        startingEleven.clear();
    }
    
	/**
	 * Getter for the formation enforced
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public Formation getFormation() {
        return Formation;
    }
    
	/**
	 * Getter for the registered players of a team
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public SimpleFootballPlayerCollection getRegisteredPlayers() {
        return registeredPlayers;
    }
    
	/**
	 * Getter for the starting eleven of the team
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public SimpleFootballPlayerCollection getStartingEleven() {
        return startingEleven;
    }
    
	/**
	 * Checks if a player is registered in a team
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public boolean isPlayerRegistered(FootballPlayer player) {
        return registeredPlayers.contains(player);
    }
    
	/**
	 * Sets the formation a team will use
	 * 
	 * @author Robert Pavliš
	 */

    @Override
    public void setFormation(Formation formation) {
        if(formation!=null)
            this.Formation = formation;
        else System.err.println("Formacija je null! :(");
    }
    
	/**
	 * Getter for the name of a team
	 * 
	 * @author Robert Pavliš
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of a team
	 * 
	 * @author Robert Pavliš
	 */

	public void setName(String name) {
		this.name = name;
	}
	
}
