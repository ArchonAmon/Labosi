package hr.fer.oop.lab1.prob4;

public class PrimeFactorization {

	public static void main (String[] args) {
		int broj = Integer.parseInt(args[0]);
		int index = 0;
		for (int i = 2; i < broj + 1; i++) {
		
			if (broj % i == 0) {
				broj = broj / i;
				index++;
				System.out.println("" + index + ". " + i);
				i--;
			}
			
		}
	}
	
}
