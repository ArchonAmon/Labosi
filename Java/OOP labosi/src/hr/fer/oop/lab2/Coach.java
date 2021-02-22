package hr.fer.oop.lab2;

/**
 * A person who manages the team. Has his formation, coaching skills and manages a specific team.
 * 
 * @author Robert Pavliš
 */

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;
import hr.fer.oop.lab2.welcomepack.ManageableTeam;
import hr.fer.oop.lab2.welcomepack.Manager;


public class Coach extends Person implements Manager {

	int CoachingSkill = Constants.DEFAULT_COACHING_SKILL;
	Formation Formation = Constants.DEFAULT_FORMATION;
	ManageableTeam ManagingTeam;


	
	/**
	 * Sets the coaching skill of a coach
	 * 
	 * @author Robert Pavliš
	 */
	
    public Coach(String name, String country, int emotion, int coachingSkill, Formation formation) {
        super(name, country, emotion);
        setCoachingSkill(coachingSkill);
        setFormation(formation);
    }
    
	/**
	 * Sets the coaching skill of a coach
	 * 
	 * @author Robert Pavliš
	 */
    
    public void setCoachingSkill(int coachingSkill) {
        if (coachingSkill >= Constants.MIN_COACHING_SKILL && coachingSkill <= Constants.MAX_COACHING_SKILL)
            this.CoachingSkill = coachingSkill;
        else System.err.println("Vjestina trenera je lose unesena");
    }
		
	/**
	 * Sets the coache's formation
	 * 
	 * @author Robert Pavliš
	 */
	
	public void setFormation(Formation formation) {
        if (formation != null)
    	    this.Formation = formation;
        else System.err.println("Formacija je null");
	    }
	
	/**
	 * Getter for the coaching skill of a coach
	 * 
	 * @author Robert Pavliš
	 */
	
	public int getCoachingSkill() {
        return CoachingSkill;
    }
	
	/**
	 * Getter for the formation currently enforced
	 * 
	 * @author Robert Pavliš
	 */

    public Formation getFormation() {
        return Formation;
    }
    
	/**
	 * Calls the super class of the coach (Person)
	 * 
	 * @author Robert Pavliš
	 */
    
    public Coach(){
        super();
    }
    
	/**
	 * Picks the 11 players who will start the game
	 * 
	 * @author Robert Pavliš
	 */
	
	@Override
	public void pickStartingEleven() {
		int df = Formation.getNoDF();
		int mf = Formation.getNoMF();
		int fw = Formation.getNoFW();
		int gk = Formation.getNoGK();
		
		for (FootballPlayer player : ManagingTeam.getRegisteredPlayers().getPlayers()) {
			if (player != null && ManagingTeam.getStartingEleven().size() != ManagingTeam.getStartingEleven().getMaxSize()) {
				 switch (player.getPlayingPosition()){
                 case GK:
                     if(gk>0){
                         ManagingTeam.addPlayerToStartingEleven(player);
                         gk--;
                     }
                     break;
                 case DF:
                     if(df>0){
                         ManagingTeam.addPlayerToStartingEleven(player);
                         df--;
                     }
                     break;
                 case MF:
                     if(mf>0){
                         ManagingTeam.addPlayerToStartingEleven(player);
                         mf--;
                     }
                     break;
                 case FW:
                     if(fw>0){
                         ManagingTeam.addPlayerToStartingEleven(player);
                         fw--;
                     }
                     break;
                 default:
                     break;
             }
				
			}
		}
	}
	
	/**
	 * Forces the formation of the coach
	 * 
	 * @author Robert Pavliš
	 */
	
	@Override
	public void forceMyFormation() {
		ManagingTeam.setFormation(Formation);
	}
	
	/**
	 * Sets the team the coach is managing
	 * 
	 * @author Robert Pavliš
	 */
	
	@Override
	public void setManagingTeam(ManageableTeam team) {
		if (team != null)
		ManagingTeam = team;
		else System.err.println("Team ne smije biti null");
	}
	
}
