import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		//System.out.println("1. zadatak");
		try {
		
		NumInt g = new NumInt();
		ArrayList<double[]> d1 = new ArrayList<>();
		ArrayList<double[]> d2 = new ArrayList<>();
		ArrayList<double[]> d3 = new ArrayList<>();
		ArrayList<double[]> d4 = new ArrayList<>();
		ArrayList<double[]> d5 = new ArrayList<>();
		ArrayList<double[]> d6 = new ArrayList<>();

		int zadatak = 1;
		double[] xd = {0, 1, -1, 0};	
		double[] x0 = {1, 1};
		double period = 0.01;
		double maxPeriod = 10;
		d1 = g.euler(xd, x0, period, maxPeriod, zadatak);
		d2 = g.obrnutiEuler(xd, x0, period, maxPeriod, zadatak);
		d3 = g.trapezni(xd, x0, period, maxPeriod, zadatak);
		d4 = g.rungeKutta(xd, x0, period, maxPeriod, zadatak);
		d5 = g.PECE2(xd, x0, period, maxPeriod, zadatak);
		d6 = g.PECE(xd, x0, period, maxPeriod, zadatak);

		
		double x1 = 1;
		double x2 = 1;
		
		double greška1 = 0;
		double greška2 = 0;
		double greška3 = 0;
		double greška4 = 0;
		double greška5 = 0;
		double greška6 = 0;

		
		for (int i = 0; i < d1.get(0).length; i++) {
			//System.out.println(d1.get(0)[i] + " " + d3.get(0)[i] + " --- " + d1.get(1)[i] + " " + d3.get(1)[i]);
			//String s = String.format("%.5g", d1.get(0)[i]);
			//System.out.println(s);
		}
		
		for (double i = 0; i < maxPeriod; i += period) {
			
			double x1Pravi = x1 * Math.cos(i) + x2 * Math.sin(i);
			double x2Pravi = x2 * Math.cos(i) - x1 * Math.sin(i);
			
			//x1 = x1Pravi;
			//x2 = x2Pravi;
			
			greška1 += Math.abs(x1Pravi - d1.get(0)[(int) (i * 1/period)]);
			greška1 += Math.abs(x2Pravi - d1.get(1)[(int) (i * 1/period)]);

			greška2 += Math.abs(x1Pravi - d2.get(0)[(int) (i * 1/period)]);
			greška2 += Math.abs(x2Pravi - d2.get(1)[(int) (i * 1/period)]);
			
			greška3 += Math.abs(x1Pravi - d3.get(0)[(int) (i * 1/period)]);
			greška3 += Math.abs(x2Pravi - d3.get(1)[(int) (i * 1/period)]);
			
			greška4 += Math.abs(x1Pravi - d4.get(0)[(int) (i * 1/period)]);
			greška4 += Math.abs(x2Pravi - d4.get(1)[(int) (i * 1/period)]);
			
			greška5 += Math.abs(x1Pravi - d5.get(0)[(int) (i * 1/period)]);
			greška5 += Math.abs(x2Pravi - d5.get(1)[(int) (i * 1/period)]);
			
			greška6 += Math.abs(x1Pravi - d6.get(0)[(int) (i * 1/period)]);
			greška6 += Math.abs(x2Pravi - d6.get(1)[(int) (i * 1/period)]);
			
			//System.out.println("Greška euler = " + greška1);
			//System.out.println("Greška obrnuti euler = " + greška2);
			//System.out.println("Greška trapezni = " + greška3);
			//System.out.println("Greška Runge-Kutta = " + greška4);
			//System.out.println("Greška PECE2 = " + greška5);
			//System.out.println("Greška PECE = " + greška6);
			
			String s = String.format("%.5g", x2Pravi);
			//System.out.println(s);
			
			
		}
		
		System.out.println("Greška euler = " + greška1);
		System.out.println("Greška obrnuti euler = " + greška2);
		System.out.println("Greška trapezni = " + greška3);
		System.out.println("Greška Runge-Kutta = " + greška4);
		System.out.println("Greška PECE2 = " + greška5);
		System.out.println("Greška PECE = " + greška6);

		} catch(Exception e) {}
		
		
		
	}

}
