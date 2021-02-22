package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Reporter;

public class Reporter_class implements Reporter {
	
	public int brojMinutaPrvi;;
	public String[] dogadaji;

	public void startMatch() {
		setMinute();
		this.dogadaji = new String[10000];
		System.out.println("Utakmica je pocela");
	}

	private void setMinute() {
		this.brojMinutaPrvi = 0;
	}

	
	public void skip(int minutes) {
		
		int minute = getMinute();
		if (minute + minutes == 45) {
			System.out.println("Prvo poluvrijeme je gotovo");
		}
		if ((minute + minutes == 90)) System.out.println("Utakmica je gotova");
			this.brojMinutaPrvi = this.brojMinutaPrvi + minutes;
		}
	

	private int getMinute() {
		return this.brojMinutaPrvi;
	}
	

	
	public void goalScored(FootballPlayer scorer, FootballPlayer assistent, String comment) {
		
		String string1 = (" je zabio pogodak (min " + this.brojMinutaPrvi + ")" );
		String string2 = (" je asistirao");	
		int i;
		
		 i = dogadaji.length + 1;
			dogadaji[i] = scorer.toString() + string1;
			if (assistent != null) dogadaji[i+1] = assistent.toString() + string2;
			if (comment != null && assistent != null) dogadaji[i+2] = comment;
			if (comment != null && assistent == null) dogadaji[i+1] = comment;
		
		
	}

	
	public void foul(FootballPlayer whoMadeFaul, FootballPlayer whoSuffered, String comment) {
		
		String string1 = (" je fauliran (min " + this.brojMinutaPrvi + ")");
		String string2 = (" je uzrokovao faul");
		int i;
		
		 i = dogadaji.length + 1;
			dogadaji[i] = whoMadeFaul.toString() + string1;
			dogadaji[i+1] = whoSuffered.toString() + string2;
			if (comment != null) dogadaji[i+2] = comment;
		
	}

	
	public void printFullMatchReport() {
		for (int i = 0; i < dogadaji.length; i++) {
		System.out.println(dogadaji[i]);
		}
		
	}
	

}
