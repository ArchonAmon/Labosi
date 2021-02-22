package hr.fer.oop.lab1.prob1;

import java.util.Scanner;

public class Rectangle {

	public static void main (String[] args) {
		
		double x = 0;
		double y = 0;
		String unos;
		
		if (args.length != 2 && args.length != 0) {
			System.out.println("Niste unijeli 2 podatka! Izvodenje se prekida! ");
			System.exit(1);
		}
		
		if (args.length == 2) {
		
		x = Double.parseDouble(args[0]);
		y = Double.parseDouble(args[1]);
	
		double P = x * y;
		double O = x * 2 + y * 2;
		System.out.println("Povrsina je " + P + ", a opseg je " + O);
	}
	
		else {
			Scanner sc1 = new Scanner (System.in); 
			
			while (true) {
			System.out.printf("Upisite argument 1: ");
			unos = sc1.nextLine().trim();
			x = arg1(unos);
			
			
			
			if (x != 0) break;
			} 
			
			while (true) {
				System.out.printf("Upisite argument 2: ");
				unos = sc1.nextLine().trim();
				y = arg1(unos);
				
				
				
				if (y != 0) break;
				}
			
			sc1.close();
			
			
			double P = x * y;
			double O = x * 2 + y * 2;
			System.out.println("Povrsina je " + P + ", a opseg je " + O);
		}
				
	}
	
	
	public static double arg1 (String unos) {	
	
	 if(unos.isEmpty()) {
         System.out.println("Upis ne smije biti prazan!");
         return 0;
     }
	 
	 double sc3 = Double.parseDouble(unos);
	 
	 if (sc3 <= 0) {
		 System.out.println("Upis ne smije biti negativan!");
		 return 0;
	 } 
	return sc3;
}
	
}
