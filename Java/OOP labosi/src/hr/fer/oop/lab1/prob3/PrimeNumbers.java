package hr.fer.oop.lab1.prob3;

public class PrimeNumbers {

	public static void main (String[] args) {
		int brojac = 0;
		int index = 0;
		int broj = Integer.parseInt(args[0]);
		for (int i = 2; i < 10000000; i++) {
			brojac = 0;
			for (int j = 2; j < i / 2; j++ ) {
			if (i % j == 0) brojac++;
			}
			if (brojac == 0 && broj != 0) {
				index++;
				broj--;
				System.out.println("" + index + ". " + i);
			}
			if (broj == 0) break;
		}
	}
	
}
