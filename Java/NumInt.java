import java.util.ArrayList;

public class NumInt {
	
	public ArrayList<double[]> euler(double[] xd, double[] x0, double period, double maxPeriod, int zadatak) {
		
		double x1 = x0[0];
		double x2 = x0[1];
		
		double rez1[] = new double[(int) (1/period * maxPeriod)];
		double rez2[] = new double[(int) (1/period * maxPeriod)];
	
		
		rez1[0] = x1;
		rez2[0] = x2;
		
		for (double i = 0 + period, counter = 2; i < maxPeriod; i += period, counter += 2) {
			
			double x1Novi = 0;
			double x2Novi = 0;
			
			if(zadatak == 1 || zadatak == 2) {
			
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2);
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2);
			
			}
			
			else if (zadatak == 3) {
				
				x1Novi = x1 + period * ((xd[0] * x1 + xd[1] * x2) + 2);
				x2Novi = x2 + period * ((xd[2] * x1 + xd[3] * x2) + 3);
				
			}
			
			else {
				
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2) + 5 * i;
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2) + 3 * i;
				
			}
					
			x1 = x1Novi;
			x2 = x2Novi;
			
			rez1[(int) (i * 1/period)] = x1;
			rez2[(int) (i * 1/period)] = x2;
			
			
		}
			
		
		ArrayList <double[]> rjesenje = new ArrayList<>();
		
		rjesenje.add(rez1);
		rjesenje.add(rez2);
		
		return rjesenje;
		
		
		
	}
	
	
	
	
	public ArrayList<double[]> obrnutiEuler(double[] xd, double[] x0, double period, double maxPeriod, int zadatak) {
		
		double x1 = x0[0];
		double x2 = x0[1];
		
		double rez1[] = new double[(int) (1/period * maxPeriod)];
		double rez2[] = new double[(int) (1/period * maxPeriod)];
		
		rez1[0] = x1;
		rez2[0] = x2;
		
		for (double i = 0 + period; i < maxPeriod; i += period) {
						
			//double x1Novi = x1 + period * (xd[0] * x1novi + xd[1] * x2novi);
			//double x2Novi = x2 + period * (xd[2] * x1novi + xd[3] * x2novi);
			
			/*Matrica A2copy = new Matrica(2, 2);
			A2copy.setElement(0, 0, 1 - period * xd[0]);
			A2copy.setElement(0, 1, -period * xd[1]);
			A2copy.setElement(1, 0, 1 - period * xd[3]);
			A2copy.setElement(1, 1, -period * xd[2]);
			
			Matrica B2copy = new Matrica(2, 1);
			B2copy.setElement(0, 0, x1);
			B2copy.setElement(1, 0, x2);
			
			Matrica A2pivotiranje = A2copy.LUPdekompozicija();
			Matrica LMat = A2copy.LMatrica();
			Matrica UMat = A2copy.UMatrica();
			Matrica D2 = LMat.supstUnaprijed(A2pivotiranje.pomnoži(B2copy));
			
			Matrica izlaz = new Matrica(2, 1);
			izlaz = UMat.supstUnatrag(D2);
			izlaz.ispisEkran();
			
			double x1Novi = izlaz.getElement(0, 0);
			double x2Novi = izlaz.getElement(1, 0);*/

			
			//x1Novi = x1 + period * xd[0] * x1Novi + period * xd[1] * x2Novi;
			//x1Novi - period * xd[0] * x1Novi = x1 + period * xd[1] * x2Novi;
			//x1Novi = x1 + period * xd[1] * x2Novi / (1 - period * xd[0]);
			
			//x2Novi = x2 + period * xd[2] * (x1 + period * xd[1] * x2Novi / (1 - period * xd[0] * x1Novi)) + period * xd[3] * x2Novi;
			//x2Novi = x2 + period * xd[2] * x1 + period * xd[2] * period * xd[1] * x2Novi / (1 - period * xd[0]) + period * xd[3] * x2Novi;
			//x2Novi - period * xd[2] * period * xd[1] * x2Novi / (1 - period * xd[0]) - period * xd[3] * x2Novi = x2 + period * xd[2] * x1;
			//x2Novi * (1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]) = x2 + period * xd[2] * x1;
			
			double x2Novi = 0;
			double x1Novi = 0;
			
			if (zadatak == 1 || zadatak == 2) {
			
				x2Novi = (x2 + period * xd[2] * x1) / ((1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]));
				x1Novi = x1 + period * xd[1] * x2Novi / (1 - period * xd[0]);
			
			}
			
			else if (zadatak == 3) {
				
				x2Novi = (x2 + 3 + period * xd[2] * (x1 + 2)) / ((1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]));
				x1Novi = x1 + 2 + period * xd[1] * x2Novi / (1 - period * xd[0]);				
				
			}
			
			else {
				
				x2Novi = (x2 + 5 * i + period * xd[2] * (x1 + 3 * i)) / ((1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]));
				x1Novi = x1 + 3 * i + period * xd[1] * x2Novi / (1 - period * xd[0]);				
				
			}
			
			x1 = x1Novi;
			x2 = x2Novi;
			
			rez1[(int) (i * 1/period)] = x1;
			rez2[(int) (i * 1/period)] = x2;
			
		}
		
		ArrayList <double[]> rjesenje = new ArrayList<>();
		
		rjesenje.add(rez1);
		rjesenje.add(rez2);
		
		return rjesenje;
		
		
		
	}
	
	
	
	public ArrayList<double[]> trapezni(double[] xd, double[] x0, double period, double maxPeriod, int zadatak) {
		
		double x1 = x0[0];
		double x2 = x0[1];
		
		double rez1[] = new double[(int) (1/period * maxPeriod)];
		double rez2[] = new double[(int) (1/period * maxPeriod)];
		
		rez1[0] = x1;
		rez2[0] = x2;
		
		for (double i = 0 + period; i < maxPeriod; i += period) {
						
			//double x1Novi = x1 + period/2 * (xd[0] * x1 + xd[1] * x2 + xd[0] * x1Novi + xd[1] * x2Novi);
			//double x2Novi = x2 + period/2 * (xd[2] * x1 + xd[3] * x2 + xd[2] * x1Novi + xd[3] * x2Novi);
			
			/*Matrica A2copy = new Matrica(2, 2);
			A2copy.setElement(0, 0, 1 - period * xd[0]);
			A2copy.setElement(0, 1, -period * xd[1]);
			A2copy.setElement(1, 0, 1 - period * xd[3]);
			A2copy.setElement(1, 1, -period * xd[2]);
			
			Matrica B2copy = new Matrica(2, 1);
			B2copy.setElement(0, 0, x1);
			B2copy.setElement(1, 0, x2);
			
			Matrica A2pivotiranje = A2copy.LUPdekompozicija();
			Matrica LMat = A2copy.LMatrica();
			Matrica UMat = A2copy.UMatrica();
			Matrica D2 = LMat.supstUnaprijed(A2pivotiranje.pomnoži(B2copy));
			
			Matrica izlaz = new Matrica(2, 1);
			izlaz = UMat.supstUnatrag(D2);
			izlaz.ispisEkran();
			
			double x1Novi = izlaz.getElement(0, 0);
			double x2Novi = izlaz.getElement(1, 0);*/

			double x1Euler = period/2 * (xd[0] * x1 + xd[1] * x2);
			double x2Euler = period/2 * (xd[2] * x1 + xd[3] * x2);

			//x1Novi = x1 + period/2 * (xd[0] * x1 + xd[1] * x2 + xd[0] * x1Novi + xd[1] * x2Novi);
			//x1Novi - period/2 * xd[0] * x1Novi = x1 + x1Euler + period/2 * xd[1] * x2Novi;
			//x1Novi = x1 + x1Euler + period/2 * xd[1] * x2Novi / (1 - period/2 * xd[0]);
			
			//x2Novi = x2 + period/2 * (xd[2] * x1 + xd[3] * x2 + xd[2] * x1Novi + xd[3] * x2Novi);
			//x2Novi = x2 + x2Euler + period/2 * xd[2] * x1 + x1Euler + period/2 * xd[1] * x2Novi / (1 - period/2 * xd[0]) * xd[3] * x2Novi;
			//x2Novi - period/2 * xd[2] * period/2 * xd[1] * x2Novi / (1 - period/2 * xd[0]) - period/2 * xd[3] * x2Novi = x2 + x2Euler + period/2 * xd[2] * x1;
			//x2Novi * (1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]) = x2 + period * xd[2] * x1;
			
			double x2Novi = 0;
			double x1Novi = 0;
			
			if (zadatak == 1 || zadatak == 2) {
			
			x2Novi = (x2 + x2Euler + period/2 * xd[2] * (x1 + x1Euler)) / ((1 - period/2 * xd[2] * period/2 * xd[1] / (1 - period/2 * xd[0]) - period/2 * xd[3]));
			x1Novi = x1 + x1Euler + period/2 * xd[1] * x2Novi / (1 - period/2 * xd[0]);
			
			}
			
			else if (zadatak == 3) {
				
			x2Novi = (x2 + x2Euler + 3 + period/2 * xd[2] * (x1 + x1Euler + 2)) / ((1 - period/2 * xd[2] * period/2 * xd[1] / (1 - period/2 * xd[0]) - period/2 * xd[3]));
			x1Novi = x1 + x1Euler + 2 + period/2 * xd[1] * x2Novi / (1 - period/2 * xd[0]);
			
			}
			
			else {
				
			x2Novi = (x2 + x2Euler + 3 * i + period/2 * xd[2] * (x1 + x1Euler + 5 * i)) / ((1 - period/2 * xd[2] * period/2 * xd[1] / (1 - period/2 * xd[0]) - period/2 * xd[3]));
			x1Novi = x1 + x1Euler + 5 * i + period/2 * xd[1] * x2Novi / (1 - period/2 * xd[0]);
			
			}
			
			x1 = x1Novi;
			x2 = x2Novi;
			
			rez1[(int) (i * 1/period)] = x1;
			rez2[(int) (i * 1/period)] = x2;
			
		}
		
		ArrayList <double[]> rjesenje = new ArrayList<>();
		
		rjesenje.add(rez1);
		rjesenje.add(rez2);
		
		return rjesenje;
		
		
		
	}
	
	
	
	
	
	
	public ArrayList<double[]> rungeKutta(double[] xd, double[] x0, double period, double maxPeriod, int zadatak) {
		
		double x1 = x0[0];
		double x2 = x0[1];
		
		double rez1[] = new double[(int) (1/period * maxPeriod)];
		double rez2[] = new double[(int) (1/period * maxPeriod)];
		
		rez1[0] = x1;
		rez2[0] = x2;
		
		for (double i = 0 + period; i < maxPeriod; i += period) {
			
			if (zadatak == 1 || zadatak == 2) {
			
			double m1x1 = xd[0] * x1 + xd[1] * x2;
			double m2x1 = xd[0] * (x1 + period/2*m1x1) + xd[1] * (x2 + period/2*m1x1);
			double m3x1 = xd[0] * (x1 + period/2*m2x1) + xd[1] * (x2 + period/2*m2x1);
			double m4x1 = xd[0] * (x1 + period*m3x1) + xd[1] * (x2 + period*m3x1);

			double x1Novi = x1 + period/6 * (m1x1 + 2*m2x1 + 2*m3x1 + m4x1);
			
			double m1x2 = xd[2] * x1 + xd[3] * x2;
			double m2x2 = xd[2] * (x1 + period/2*m1x2) + xd[3] * (x2 + period/2*m1x2);
			double m3x2 = xd[2] * (x1 + period/2*m2x2) + xd[3] * (x2 + period/2*m2x2);
			double m4x2 = xd[2] * (x1 + period*m3x2) + xd[3] * (x2 + period*m3x2);
			
			double x2Novi = x2 + period/6 * (m1x2 + 2*m2x2 + 2*m3x2 + m4x2);
					
			x1 = x1Novi;
			x2 = x2Novi;
			
			}

			else if (zadatak == 3) {
				
			double m1x1 = xd[0] * x1 + xd[1] * x2 + 2;
			double m2x1 = xd[0] * (x1 + period/2*m1x1) + xd[1] * (x2 + period/2*m1x1) + 2;
			double m3x1 = xd[0] * (x1 + period/2*m2x1) + xd[1] * (x2 + period/2*m2x1) + 2;
			double m4x1 = xd[0] * (x1 + period*m3x1) + xd[1] * (x2 + period*m3x1) + 2;

			double x1Novi = x1 + period/6 * (m1x1 + 2*m2x1 + 2*m3x1 + m4x1);
			
			double m1x2 = xd[2] * x1 + xd[3] * x2 + 3;
			double m2x2 = xd[2] * (x1 + period/2*m1x2) + xd[3] * (x2 + period/2*m1x2) + 3;
			double m3x2 = xd[2] * (x1 + period/2*m2x2) + xd[3] * (x2 + period/2*m2x2) + 3;
			double m4x2 = xd[2] * (x1 + period*m3x2) + xd[3] * (x2 + period*m3x2) + 3;
			
			double x2Novi = x2 + period/6 * (m1x2 + 2*m2x2 + 2*m3x2 + m4x2);
					
			x1 = x1Novi;
			x2 = x2Novi;
			
			}
			
			else {
				
			double m1x1 = xd[0] * x1 + xd[1] * x2 + 5 * i;
			double m2x1 = xd[0] * (x1 + period/2*m1x1) + xd[1] * (x2 + period/2*m1x1) + 5 * i;
			double m3x1 = xd[0] * (x1 + period/2*m2x1) + xd[1] * (x2 + period/2*m2x1) + 5 * i;
			double m4x1 = xd[0] * (x1 + period*m3x1) + xd[1] * (x2 + period*m3x1) + 5 * i;

			double x1Novi = x1 + period/6 * (m1x1 + 2*m2x1 + 2*m3x1 + m4x1);
			
			double m1x2 = xd[2] * x1 + xd[3] * x2 + 3 * i;
			double m2x2 = xd[2] * (x1 + period/2*m1x2) + xd[3] * (x2 + period/2*m1x2) + 3 * i;
			double m3x2 = xd[2] * (x1 + period/2*m2x2) + xd[3] * (x2 + period/2*m2x2) + 3 * i;
			double m4x2 = xd[2] * (x1 + period*m3x2) + xd[3] * (x2 + period*m3x2) + 3 * i;
			
			double x2Novi = x2 + period/6 * (m1x2 + 2*m2x2 + 2*m3x2 + m4x2);
					
			x1 = x1Novi;
			x2 = x2Novi;
			
			}
			
			rez1[(int) (i * 1/period)] = x1;
			rez2[(int) (i * 1/period)] = x2;
			
		}
		
		ArrayList <double[]> rjesenje = new ArrayList<>();
		
		rjesenje.add(rez1);
		rjesenje.add(rez2);
		
		return rjesenje;
		
		
		
	}
	
	
	
	
	public ArrayList<double[]> PECE2(double[] xd, double[] x0, double period, double maxPeriod, int zadatak) {
		
		//euler + obrnuti euler
		
		double x1 = x0[0];
		double x2 = x0[1];
		
		double rez1[] = new double[(int) (1/period * maxPeriod)];
		double rez2[] = new double[(int) (1/period * maxPeriod)];
		
		rez1[0] = x1;
		rez2[0] = x2;
		
		for (double i = 0 + period; i < maxPeriod; i += period) {
			
			double x1Novi = 0;
			double x2Novi = 0;
			
			if(zadatak == 1 || zadatak == 2) {
			
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2);
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2);
			
			}
			
			else if (zadatak == 3) {
				
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2) + 2;
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2) + 3;
				
			}
			
			else {
				
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2) + 5 * i;
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2) + 3 * i;
				
			}
					
			double x1knext = x1Novi;
			double x2knext = x2Novi;
			
			double x1knextTemp = 0;
			double x2knextTemp = 0;
			
			for (int j = 0; j < 2; j++) {
			
			if (zadatak == 1 || zadatak == 2) {
			
				x1knextTemp = x1 + period * (xd[0] * x1knext + xd[1] * x2knext);
				x2knextTemp = x2 + period * (xd[2] * x1knext + xd[3] * x2knext);
			
			}
			
			else if (zadatak == 3) {
				
				x2knextTemp = (x2 + 3 + period * xd[2] * (x1 + 2)) / ((1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]));
				x1knextTemp = x1 + 2 + period * xd[1] * x2Novi / (1 - period * xd[0]);				
				
			}
			
			else {
				
				x2knextTemp = (x2 + 5 * i + period * xd[2] * (x1 + 3 * i)) / ((1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]));
				x1knextTemp = x1 + 3 * i + period * xd[1] * x2Novi / (1 - period * xd[0]);				
				
			}
			
			x1knext = x1knextTemp;
			x2knext = x2knextTemp;

			}
			
			
			x1 = x1knextTemp;
			x2 = x2knextTemp;
			
			rez1[(int) (i * 1/period)] = x1;
			rez2[(int) (i * 1/period)] = x2;
			
			
		}
		
		ArrayList <double[]> rjesenje = new ArrayList<>();
		
		rjesenje.add(rez1);
		rjesenje.add(rez2);
		
		return rjesenje;
		
		
		
	}
	
	
	
	
	
	public ArrayList<double[]> PECE(double[] xd, double[] x0, double period, double maxPeriod, int zadatak) {
		
		//euler + trapezni
		
		double x1 = x0[0];
		double x2 = x0[1];
		
		double rez1[] = new double[(int) (1/period * maxPeriod)];
		double rez2[] = new double[(int) (1/period * maxPeriod)];
		
		rez1[0] = x1;
		rez2[0] = x2;
		
		for (double i = 0 + period; i < maxPeriod; i += period) {
			
			double x1Novi = 0;
			double x2Novi = 0;
			
			if(zadatak == 1 || zadatak == 2) {
			
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2);
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2);
			
			}
			
			else if (zadatak == 3) {
				
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2) + 2;
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2) + 3;
				
			}
			
			else {
				
				x1Novi = x1 + period * (xd[0] * x1 + xd[1] * x2) + 5 * i;
				x2Novi = x2 + period * (xd[2] * x1 + xd[3] * x2) + 3 * i;
				
			}
					
			double x1knext = x1Novi;
			double x2knext = x2Novi;
			
			double x1knextTemp = 0;
			double x2knextTemp = 0;
			
			for (int j = 0; j < 1; j++) {
			
			if (zadatak == 1 || zadatak == 2) {
			
				x1knextTemp = x1 + period/2 * (xd[0] * x1 + xd[1] * x2 + xd[0] * x1knext + xd[1] * x2knext);
				x2knextTemp = x2 + period/2 * (xd[2] * x1 + xd[3] * x2 + xd[2] * x1knext + xd[3] * x2knext);
			
			}
			
			else if (zadatak == 3) {
				
				x2knextTemp = (x2 + 3 + period * xd[2] * (x1 + 2)) / ((1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]));
				x1knextTemp = x1 + 2 + period * xd[1] * x2Novi / (1 - period * xd[0]);				
				
			}
			
			else {
				
				x2knextTemp = (x2 + 5 * i + period * xd[2] * (x1 + 3 * i)) / ((1 - period * xd[2] * period * xd[1] / (1 - period * xd[0]) - period * xd[3]));
				x1knextTemp = x1 + 3 * i + period * xd[1] * x2Novi / (1 - period * xd[0]);				
				
			}
			
			x1knext = x1knextTemp;
			x2knext = x2knextTemp;

			}
			
			
			x1 = x1knextTemp;
			x2 = x2knextTemp;
			
			rez1[(int) (i * 1/period)] = x1;
			rez2[(int) (i * 1/period)] = x2;
			
			
		}
		
		ArrayList <double[]> rjesenje = new ArrayList<>();
		
		rjesenje.add(rez1);
		rjesenje.add(rez2);
		
		return rjesenje;
		
		
		
	}
	
	

}
