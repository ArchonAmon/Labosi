import java.util.ArrayList;
import java.util.Random;

public class Optimiranje2 {
	
	public double epsilon = 0.000001;
	double alfa = 1.3;
	public int pozivanjeFunkcije = 0;
	public int pozivanjeHesse = 0;
	public int pozivanjeGradijent = 0;
	public int t = 1;

	
	
	
	
	
	public double[] gradijentni(double x1, double x2, int funkcija, boolean zlatniRez) {
		
		double lambdaf1 = 0.0001;
		double lambdaf2 = 1.2/5;
		double lambdaf3 = 0.5;
		double lambdaDefault = 0.01;
		double[] x = {x1, x2};
		

		
		int iteracije = 0;
		do {
						
			double[] dF = new double[2];
			if (funkcija == 1) {
				dF[0] = - 400 * x[0] * (x[1] - Math.pow(x[0], 2)) - 2 * (1 - x[0]);
				dF[1] = 200 * x[0] * (x[1] - Math.pow(x[0], 2));
			}
			else if (funkcija == 2) {
				dF[0] = 2 * x[0] - 8;
				dF[1] = 8 * x[1] - 16;
			}
			else {
				dF[0] = 2 * x[0] - 4;
				dF[1] = 2 * x[1] + 6;
			}
		
			double duljina = Math.sqrt(dF[0] * dF[0] + dF[1] * dF[1]);
			
			double[] v = pomnoziSkalar(dF, -1./duljina);			//ide -1 jer se ide suprotno od gradijenta
			
			double lambda1 = 1;
			double lambda2 = 1;

			if (zlatniRez == true) {
				Optimiranje2 z = new Optimiranje2();
				double[] temp = {x[0]+ lambda1 * dF[0]};
				double[] d = z.hookeJeeves3(temp, funkcija);
				lambda1 = d[0];
				
				Optimiranje2 z2 = new Optimiranje2();
				double[] temp2 = {x[1] + lambda2 * dF[1]};
				double[] d2 = z2.hookeJeeves3(temp2, funkcija);
				lambda2 = d2[0];
				
//				if(funkcija == 1) lambda = lambdaf1;
//				else if (funkcija == 2) lambda = lambdaf2;
//				else lambda = lambdaf3;
			}
			
			
			x[0] = x[0] + (zlatniRez == true ? lambda1 : lambdaDefault) * v[0];
			x[1] = x[1] + (zlatniRez == true ? lambda2 : lambdaDefault) * v[1];
		
			//System.out.println(x[0] +  " " + x[1]);
			
			iteracije++;
			
		} while(f(x[0], x[1], funkcija) > epsilon && iteracije < 1000);
		
		
		return x;
		
	}
	
	
	
	
	
	
	public double[] hookeJeeves3(double[] x0, int funkcija) {
		
		double[] xp = x0.clone();
		double[] xb = x0.clone();
		double Dx = 0.5;
		do {
			double[] xn = istrazi3(xp, Dx, funkcija);
//			System.out.println(xb[0] + " " + xp[0] + " " + xn[0] + " " + f(xn[0], 0, funkcija) + " " + f(xb[0], 0, funkcija) + " " + Dx);
			if (f(xn[0], 0, funkcija) < f(xb[0], 0, funkcija)) {
				xp = oduzmi(pomnoziSkalar(xn.clone(), 2), xb.clone());
				xb = xn.clone();
			}
			else {
				Dx /= 2;
				xp = xb.clone();
			}
		} while (Dx > epsilon);
		
		return xb;
			
	}
	
	
	public double[] istrazi3(double[] xp, double Dx, int funkcija) {
		
		double[] x = xp.clone();
		for (int i = 0; i < x.length; i++) {
			double p = f(x[0], 0, funkcija);
			x[i] += Dx;
			double N = f(x[0], 0, funkcija);
			if (N > p) {
				x[i] = x[i] - 2 * Dx;
				N = f(x[0], 0, funkcija);
				if (N > p) x[i] += Dx;
			}
		}
		return x;
		
	}
	
	
	
	
	
	
	
	
	
	
	public double[] newtonRaph(double x1, double x2, int funkcija, boolean zlatniRez) {
				
		double[] x = {x1, x2};
		double lambda = 0.01;
		int iteracije = 0;
		
		do {
			
			lambda = 1;
			if (zlatniRez == true) {
				Optimiranje z = new Optimiranje();
				double[] d = {x[0], x[1]};
				d = z.zlatniRez(-5, 5, x, funkcija + 6, 0);
				lambda = (d[0] + d[1]) / 2;
				
				//if(funkcija == 1) lambda = lambdaf1;
				//else lambda = lambdaf2;
			}
			
			double[] dF = new double[2];
			double[] dH = new double[2];
			if (funkcija == 1) {
				dF[0] = - 400 * x[0] * (x[1] - Math.pow(x[0], 2)) - 2 * (1 - x[0]);
				dF[1] = 200 * x[0] * (x[1] - Math.pow(x[0], 2));
				dH[0] = 1./2;
				dH[1] = 1./8;		//change
			}
			else if (funkcija == 2) {
				dF[0] = 2 * x[0] - 8;
				dF[1] = 8 * x[1] - 16;
				dH[0] = 1./2;
				dH[1] = 1./8;
			}
			
			
		//h = f(x[0], x[1], funkcija) / f(x[0], x[1], funkcija + 4);

		x[0] = x[0] - lambda * dF[0] * dH[0];
		x[1] = x[1] - lambda * dF[1] * dH[1];
			
		//System.out.println(x[0] + " " + x[1]);
		
		iteracije++;
		
		} while (f(x[0], x[1], funkcija) > epsilon && iteracije < 1000);
		
		return x;
	}
	
	
	
	
	
	
	
	
	
	
	
	public double[] box(double x1, double x2, double xd, double xg, int funkcija) {
		
		int iteracije = 0;
		int n = 2;
		if (xd > x1 || xd > x2 || x1 > xg || x2 > xg || x2 - x1 < 0 || 2 - x1 < 0) {		//x2-x1<0 i 2-x1<0 su g(x)
			System.out.println("Uvjet nije dobar, izlazim!");
			return null;
		}
		Random rand = new Random();
		ArrayList<Double[]> x = new ArrayList<>();
		Double[] xc = {x1, x2};

		
		for (int t = 0; t < 2*n; t++) {
			double R1 = rand.nextDouble();
			double R2 = rand.nextDouble();
			double tmp1 = xd + R1 * (xg - xd);
			double tmp2 = xd + R2 * (xg - xd);
			Double[] tmp = {tmp1, tmp2};
			x.add(tmp);
			int brojIteracija = 0;
			while (x.get(t)[0] - x.get(t)[1] < 0 || 2 - x.get(t)[0] < 0) {				//u zagradi x.get(t)[0] - 1 < 0
				Double[] xtmp = new Double[2];
				if (brojIteracija >= 100) {
					brojIteracija = 0;
					R1 = rand.nextDouble();
					R2 = rand.nextDouble();
					tmp1 = xd + R1 * (xg - xd);
					tmp2 = xd + R2 * (xg - xd);
					xtmp[0] = tmp1;
					xtmp[1] = tmp2;
				}
				else {
					xtmp[0] = 0.5 * (x.get(t)[0] + xc[0]);
					xtmp[1] = 0.5 * (x.get(t)[1] + xc[1]);			//pomicanje prema centroidu
				}

				x.remove(t);
				x.add(xtmp);
				//System.out.println(x.get(t)[0] + " " + x.get(t)[1]);
				brojIteracija++;
			}

			xc[0] = (double) 0;		//reset vrijednosti centroida za ponovno računanje
			xc[1] = (double) 0;
			for (int i = 0; i < t; i++) {
				xc[0] += x.get(t)[0];
				xc[1] += x.get(t)[1];
			}

			xc[0] /= x.size();	
			xc[1] /= x.size();
		}
		
		
		do {
						
			//punjenje fje
			double f[]   = new double[x.size()];
		    for (int j = 0; j < x.size(); j++) {
		      f[j] = f(x.get(j)[0], x.get(j)[1], funkcija);
		    }
		    
	  		//index najgoreg
	  		int h=0;
	  		for (int j = 0; j < x.size();j++) {
	  			if (f[j] > f[h]) {
	  				h = j;
	  			}
	  		}
			
	  		//index najboljeg
	  		int l=0;
	  		for (int j = 0; j < x.size(); j++) {
	  			if (f[j] < f[l]) {
	  				l = j;
	  			}
	  		}

	  		//index drugog najgoreg
	  		int vh=l;
	  		for (int j = 0; j < x.size(); j++) {
	  			if (f[j] > f[vh] && f[j] < f[h]) {
	  				vh = j;
	  			}
	  		}
	  		
	  		
	  		//centroid
	  		double centroid = 0;
	  		double[] centroid_uk = new double[2];
	  		for (int j = 0; j < 2; j++) {
	  			centroid = 0.0;
	  			for (int m = 0; m < x.size(); m++) {
	  				if (m != h) {
	  					centroid += x.get(m)[j];
	  				}
	  			}
	  			centroid_uk[j] = centroid/(x.size()-1);
	  		}
	  		
	  		
		    //System.out.println(f[h]);

	  		
	  		// refleksija
	  		double[] xtmp = new double[2];
	  		for (int i = 0; i < 2; i++) xtmp[i] = x.get(h)[i];
		    double xr[]  = new double[2];
		    xr = refleksija(1.3, centroid_uk, xtmp);		//alfa promijenjen u 2
	  		
			
		    
		    
		    for (int i = 0; i < 2; i++) {
		    	if (xr[i] < xd) xr[i] = xd;
		    	else if (xr[i] > xg) xr[i] = xg;
		    }
		    
		    
		    while (xr[0] - xr[1] < 0 || 2 - xr[0] < 0) {		//u zagradi xr[0] - 1 < 0
		    	xr[0] = 0.5 * (xr[0] + centroid_uk[0]);
		    	xr[1] = 0.5 * (xr[1] + centroid_uk[1]);
		    }
		    
		    if (f(xr[0], xr[1], funkcija) > f(x.get(vh)[0], x.get(vh)[1], funkcija)) {
		    	xr[0] = 0.5 * (xr[0] + centroid_uk[0]);
		    	xr[1] = 0.5 * (xr[1] + centroid_uk[1]);
		    }
		    Double[] xtmpp = {xr[0], xr[1]};
		    x.remove(h);
		    x.add(xtmpp);
		    
		    //System.out.println(f(x.get(x.size()-1)[0], x.get(x.size()-1)[1], funkcija));

		    //for (Double[] c : x)
		    //	System.out.println(f(c[0], c[1], funkcija));
		    //System.out.println(f(x.get(l)[0], x.get(l)[1], funkcija));
		    
		    
	  		double sum = 0.0;
	  		double fullsum = 0;
	  		double avg = 0;
	  		for (int j = 0; j < x.size(); j++) {
	  			fullsum += f[j];
	  		}
	  		avg = fullsum / x.size();
	  		sum = 0.0;
	  		for (int j = 0; j < x.size(); j++) {
	  			sum += Math.pow((f[j] - avg), 2.0) / x.size();
	  		}
	  		sum = Math.sqrt(sum);
	  		if (sum < epsilon || iteracije >= 10000) break;
	  		if (iteracije >= 10000) {
	  			double[] ret = {0};
	  			return ret;
	  		}
	  		iteracije++;
	  	
	  		//for(Double[] i : x) System.out.println(i[0] + " " + i[1]);
	  		//System.out.println();
		} while(true);
		
		
		
		
	  	//indeks najboljeg
	  	int l=0;
	  	for (int j = 0; j <= n; j++) {
	  		if (f(x.get(j)[0], x.get(j)[1], funkcija) < f(x.get(l)[0], x.get(l)[1], funkcija)) {
	  			l = j;
	  		}
	  	}


	  	//System.out.println(x.get(l)[0] + " " + x.get(l)[1]);
	  	double[] ret = {x.get(l)[0], x.get(l)[1]};
		return ret;
		
		
	}
	
	
	
	
	
	
	
	
	public double[] transformacija(double x1, double x2, int funkcija) {
		
		double[] x = {x1, x2};
		if (funkcija == 1 || funkcija == 2) {
			if (x2 - x1 < 0 || 2 - x1 < 0) {
				x = unutarnjaTocka(x, funkcija);
				System.out.println("Početna točka: (" + x1 + ", " + x2 + ") je izmijenjena u (" + x[0] + ", " + x[1] + ")");
			}
		}
		else if (funkcija == 4) {
			if (3 - x1 - x2 < 0 || 3 + 1.5 * x1 - x2 < 0 || x2 - 1 != 0) {
				x = unutarnjaTocka(x, funkcija);
				System.out.println("Početna točka: (" + x1 + ", " + x2 + ") je izmijenjena u (" + x[0] + ", " + x[1] + ")");
			}
		}
		double[] xs = x.clone();

		do {
			
			xs = x.clone();
			x = hookeJeeves(x.clone(), funkcija);
			t = t * 10;
			//System.out.println(x[0] + " " + x[1]);
			
		} while (Math.abs(xs[0] - x[0]) > epsilon || Math.abs(xs[1] - x[1]) > epsilon);
		
		return x;
		
	}
	
	public double F(double[] x, int funkcija) {
		
		double F = 0;
		if (funkcija == 1) {
			F = f1(x[0], x[1]);
			if (x[1] - x[0] < 0) return 9999999;		//prva implicitna
			else F = F - 1./t * Math.log(x[1] - x[0]);
			if (2 - x[0] < 0) return 9999999;		//druga implicitna
			else F = F - 1./t * Math.log(2 - x[0]);
		}
		else if (funkcija == 2) {
			F = f2(x[0], x[1]);
			if (x[1] - x[0] < 0) return 9999999;		//prva implicitna
			else F = F - 1./t * Math.log(x[1] - x[0]);
			if (2 - x[0] < 0) return 9999999;		//druga implicitna
			else F = F - 1./t * Math.log(2 - x[0]);
		}
		else {
			F = f4(x[0], x[1]);
			if (3 - x[0] - x[1] < 0) return 9999999;		//prva implicitna
			else F = F - 1./t * Math.log(3 - x[0] - x[1]);
			if (3 + 1.5 * x[0] - x[1] < 0) return 9999999;		//druga implicitna
			else F = F - 1./t * Math.log(3 + 1.5 * x[0] - x[1]);
			F = F + t * Math.pow((x[1] - 1), 2);				//jednadžba sa = 0
		}
		return F;
		
	}
	
	
	public double[] hookeJeeves(double[] x0, int funkcija) {
		
		double[] xp = x0.clone();
		double[] xb = x0.clone();
		double Dx = 0.5;
		do {
			double[] xn = istrazi(xp, Dx, funkcija);
			//System.out.println(xb[0] + " " + xb[1] + " " + xp[0] + " " + xp[1] + " " + xn[0] + " " + xn[1] + " " + F(xn, funkcija) + " " + F(xb, funkcija) + " " + Dx);
			if (F(xn, funkcija) < F(xb, funkcija)) {
				xp = oduzmi(pomnoziSkalar(xn.clone(), 2), xb.clone());
				xb = xn.clone();
			}
			else {
				Dx /= 2;
				xp = xb.clone();
			}
		} while (Dx > epsilon);
		
		return xb;
			
	}
	
	
	public double[] istrazi(double[] xp, double Dx, int funkcija) {
		
		double[] x = xp.clone();
		for (int i = 0; i < x.length; i++) {
			double p = F(x, funkcija);
			x[i] += Dx;
			double N = F(x, funkcija);
			if (N > p) {
				x[i] = x[i] - 2 * Dx;
				N = F(x, funkcija);
				if (N > p) x[i] += Dx;
			}
		}
		return x;
		
	}
	
	
	public double[] unutarnjaTocka(double[] x0, int funkcija) {
		
		double[] xs = x0.clone();
		double[] x = x0.clone();
		do {
			
			xs = x.clone();
			x = hookeJeevesUnutarnja(x.clone(), funkcija);
			
		} while (Math.abs(xs[0] - x[0]) > epsilon || Math.abs(xs[1] - x[1]) > epsilon);
		return x;
		
	}
	
	public double G(double[] x, int funkcija) {
		
		double G = 0;
		if (funkcija == 1 || funkcija == 2) {
			if (x[1] - x[0] < 0) G = G - (x[1] - x[0]);
			if (2 - x[0] < 0) G = G - (2 - x[0]);
		}
		else {     //zadatak je 5
			if (3 - x[0] - x[1] < 0) G = G - (3 - x[0] - x[1]);
			if (3 + 1.5 * x[0] - x[1] < 0) G = G - (3 + 1.5 * x[0] - x[1]);
			//if (x[1] - 1 != 0) G = G - (x[1] - 1);
		}
		return G;
		
	}
	
	
	
	public double[] hookeJeevesUnutarnja(double[] x0, int funkcija) {
		
		double[] xp = x0.clone();
		double[] xb = x0.clone();
		double Dx = 0.5;
		do {
			double[] xn = istraziUnutarnja(xp, Dx, funkcija);
			//System.out.println(xb[0] + " " + xb[1] + " " + xp[0] + " " + xp[1] + " " + xn[0] + " " + xn[1] + " " + f(xn, funkcija) + " " + f(xb, funkcija) + " " + Dx);
			if (G(xn, funkcija) < G(xb, funkcija)) {
				xp = oduzmi(pomnoziSkalar(xn.clone(), 2), xb.clone());
				xb = xn.clone();
			}
			else {
				Dx /= 2;
				xp = xb.clone();
			}
		} while (Dx > epsilon);
		
		return xb;
			
	}
	
	
	public double[] istraziUnutarnja(double[] xp, double Dx, int funkcija) {
		
		double[] x = xp.clone();
		for (int i = 0; i < x.length; i++) {
			double p = G(x, funkcija);
			x[i] += Dx;
			double N = G(x, funkcija);
			if (N > p) {
				x[i] = x[i] - 2 * Dx;
				N = G(x, funkcija);
				if (N > p) x[i] += Dx;
			}
		}
		return x;
		
	}
	
	
	
	
	public double[] refleksija(double alfa, double[] xc, double[] xh) {
		
		return oduzmi(pomnoziSkalar(xc.clone(), (1 + alfa)), pomnoziSkalar(xh.clone(), alfa));
		
	}
	
	
	public double[] pomnoziSkalar(double[] x, double skalar) {
		
		double[] x1 = x.clone();
		for (int i = 0; i < x1.length; i++) {
			x1[i] *= skalar;
		}
		return x1;
		
	}
	
	
	
	public double[] oduzmi (double[] x1, double[] x2) {
		
		double[] x11 = x1.clone();
		double[] x22 = x2.clone();
		for (int i = 0; i < x11.length; i++) {
			x11[i] -= x22[i];
		}
		return x11;
		
	}
	
	
	
	
	public double f(double x1, double x2, int funkcija) {
		
		switch (funkcija) {
		case 1: return f1(x1, x2);
		case 2: return f2(x1, x2);
		case 3: return f3(x1, x2);
		case 4: return f4(x1, x2);
		case 5: return f5(x1, x2);
		case 6: return f6(x1, x2);
		case 7: return f7(x1, x2);
		}
		return f1(x1, x2);
		
	}
	
	
	
	
	
public double f1(double x1, double x2) {
		
		pozivanjeFunkcije++;
		return 100 * Math.pow(x2 - Math.pow(x1, 2), 2) + Math.pow(1 - x1, 2);
		
	}
	
	public double f2(double x1, double x2) {
		
		pozivanjeFunkcije++;
		return Math.pow(x1 - 4, 2) + 4 * Math.pow(x2 - 2, 2);
		
	}
	
	public double f3(double x1, double x2) {
		
		pozivanjeFunkcije++;
		return Math.pow(x1 - 2, 2) + Math.pow(x2 + 3, 2);
		
	}
	
	public double f4(double x1, double x2) {
		
		pozivanjeFunkcije++;
		return Math.pow(x1 - 3, 2) + Math.pow(x2, 2);
		
	}
	
	//derivacija prve funkcije s jednom varijablom
	public double f5(double x1, double x2) {
		
		//pozivanjeFunkcije++;
		return 400 * Math.pow(x1, 3) - 600 * Math.pow(x1, 2) + 202 * x1 - 2;			//mozda nederiviranu fju ovdje staviti
		
	}
	
	//derivacija druge funkcije s jednom varijablom
	public double f6(double x1, double x2) {
		
		//pozivanjeFunkcije++;
		return 10 * x1 - 24;
		
	}
	
	//derivacija treće funkcije s jednom varijablom
	public double f7(double x1, double x2) {
		
		//pozivanjeFunkcije++;
		return 4 * x1 + 2;
		
	}

}
