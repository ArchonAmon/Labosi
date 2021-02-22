package hr.fer.oop.lab2;

/**
 * A person is a somebody with a name, country and emotion
 * 
 * @author Robert Pavliš
 */

import hr.fer.oop.lab2.welcomepack.Constants;

public class Person {

	String PersonName = Constants.DEFAULT_PLAYER_NAME;
	String Country = Constants.DEFAULT_COUNTRY;
	int Emotion = Constants.DEFAULT_EMOTION;
	
	/**
	 * Creates a person
	 * 
	 * @author Robert Pavliš
	 */
	
	public Person(String name, String country, int emotion) {
		SetCountry (country);
		SetPersonName (name);
		SetEmotion (emotion);
	}
	
	/**
	 * Indicates that a person is the super class of other classes
	 * 
	 * @author Robert Pavliš
	 */
	
	public Person() {}
	
	/**
	 * Sets the country a person is from
	 * 
	 * @author Robert Pavliš
	 */
	
	public final void SetCountry (String country) {
		if (country != null)
		this.Country = country;
		else System.err.println("Lose unesena drzava");
	}
	
	/**
	 * Sets a person's name
	 * 
	 * @author Robert Pavliš
	 */
	
	public final void SetPersonName (String name) {
		if (name != null)
		this.PersonName = name;
		else System.err.println("Lose uneseno ime");
	}
	
	/**
	 * Sets a person's emotion
	 * 
	 * @author Robert Pavliš
	 */
	
	public void SetEmotion (int emot) {
		if (emot >= Constants.MIN_EMOTION && emot <= Constants.MAX_EMOTION)
		this.Emotion = emot;
		else System.err.println("Lose unesena emocija");
	}
	
	/**
	 * Getter for the country of a person
	 * 
	 * @author Robert Pavliš
	 */
	
	public String GetCountry () {
		return this.Country;
	}
	
	/**
	 * Getter for the name of a person
	 * 
	 * @author Robert Pavliš
	 */
	
	public String GetName () {
		return this.PersonName;
	}
	
	/**
	 * Getter for the emotion of a player
	 * 
	 * @author Robert Pavliš
	 */
	
	public int GetEmotion () {
		return this.Emotion;
	}
	
	/**
	 * Redefines when a person equals another
	 * 
	 * @author Robert Pavliš
	 */
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (PersonName != null ? !PersonName.equals(person.PersonName) : person.PersonName != null) return false;
        return !(Country != null ? !Country.equals(person.Country) : person.Country != null);

    }
    
	/**
	 * Returns what a person's hashcode is
	 * 
	 * @author Robert Pavliš
	 */
    
    @Override
    public int hashCode() {
        int result = PersonName != null ? PersonName.hashCode() : 0;
        result = 31 * result + (Country != null ? Country.hashCode() : 0);
        return result;
    }

	
}