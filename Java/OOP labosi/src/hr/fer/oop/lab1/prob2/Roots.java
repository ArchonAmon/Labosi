package hr.fer.oop.lab1.prob2;

import java.lang.Math;

public class Roots {

	public static void main (String[] args) {
		
		double real = Double.parseDouble(args[0]);
		double im = Double.parseDouble(args[1]);
		double korijen = Integer.parseInt(args[2]);;
		
		
		if (real == 0) {
			System.exit(1);	
		}
		
		double fi = Math.atan2(im,real);
		double rad = Math.sqrt(Math.pow(real, 2) + Math.pow(im, 2));
		rad = Math.pow(rad, 1/korijen);
		
		for (int i = 0; i < korijen;) {
			real = rad * Math.cos((fi + 2 * Math.PI * i) / korijen);
			im = rad * Math.sin((fi + 2* Math.PI * i) / korijen);
			i++;
			
			if (im >= 0) System.out.format("%d %.2f + %.2f\n", i, real, im);
			else {
				im = Math.abs(im);
				System.out.format("%d %.2f - %.2f\n", i, real, im);
			}
		}
		
	}
		
}
