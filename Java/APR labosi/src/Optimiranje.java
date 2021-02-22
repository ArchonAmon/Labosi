public class Optimiranje {
	
	public double epsilon = 0.000001;
	public double k = 0.5 * (Math.sqrt(5)-1);
	public int pozivanjeFunkcije = 0;
	public double simplexStep = 0.5;
	
	
	//postupak za zlatni rez, dobiva se a i b (unimodalni interval) kao ulaz
	public double[] zlatniRez (double a, double b, double[] tocka, int funkcija, int indexFix) {
		
		double c = b - k * (b - a);
		double d = a + k * (b - a);
		//double[] t1 = {c};
		double[] t1 = new double[tocka.length];
		for (int i = 0; i < tocka.length; i++)
			if(i == indexFix) t1[i] = c;
			else t1[i] = tocka[i];
		//double[] t2 = {d};
		double[] t2 = new double[tocka.length];
		for (int i = 0; i < tocka.length; i++)
			if(i == indexFix) t2[i] = d;
			else t2[i] = tocka[i];
		double fc = f(t1, funkcija);	
		double fd = f(t2, funkcija);
		
		while ((b - a) > epsilon) {
			if (fc < fd) {
				b = d;
				d = c;
				c = b - k * (b - a);
				fd = fc;
				t1[indexFix] = c;
				fc = f(t1, funkcija);							
				}
			
			else {
				a = c;
				c = d;
				d = a + k * (b - a);
				fc = fd;
				t2[indexFix] = d;
				fd = f(t2, funkcija);
			}
		}
		double[] ret = {a, b};
		return ret;
		
	}
	
	
	
	
	
	public double[] unimodalni(double h, double[] tocka, int funkcija, int indexFix) {
		
		double l = tocka.clone()[indexFix] - h;
		double r = tocka.clone()[indexFix] + h;
		double m = tocka.clone()[indexFix];
		int step = 1;
		
		double[] t1 = tocka.clone();
		//double[] t2 = {l};
		double[] t2 = new double[tocka.length];
		for (int i = 0; i < tocka.length; i++)
			if(i == indexFix) t2[i] = l;
			else t2[i] = tocka[i];
			
		//double[] t3 = {r};
		double[] t3 = new double[tocka.length];
		for (int i = 0; i < tocka.length; i++)
			if(i == indexFix) t3[i] = r;
			else t3[i] = tocka[i];
		double fm = f(t1, funkcija);
		double fl = f(t2, funkcija);
		double fr = f(t3, funkcija);
		
		if (fm < fr && fm < fl) {
			double[] dbl = {l, r};
			return dbl;
		}
		
		else if (fm > fr) {
			do {
				l = m;
				m = r;
				fm = fr;
				r = tocka.clone()[indexFix] + h * (step *= 2);
				t3[indexFix] = r;
				fr = f(t3, funkcija);
			} while (fm > fr);
		}
		else {
			do {
				r = m;
				m = l;
				fm = fl;
				l = tocka.clone()[indexFix] - h * (step *= 2);
				t2[indexFix] = l;
				fl = f(t2, funkcija);
			} while (fm > fl);
		}
		
		double[] ret = {l, r};
		return ret;
		
	}
	
	
	
	
	
	public double[] poKoordinatnimOsima(double[] x0, int funkcija) {
		
		double[] x = x0.clone();
		//šalji točku u petlju i iz petlje koordinate šalji u unimodalni pa u zlatni rez
		boolean gotov = true;
		do {
			
			double[] xs = x.clone();
			for (int i = 0; i < x0.length; i++) {			//ovdje ce trebati raditi caseove
				//System.out.println("fja prije promjene: " + f(x, funkcija));
				double[] lambda = unimodalni(0.5, x.clone(), funkcija, i);
				//double[] print1 = {i==0 ? (lambda[0] + lambda[1])/2 : x[0], i==1 ? (lambda[0] + lambda[1])/2 : x[1]};
				//System.out.println(lambda [0] + " " + lambda [1] + " fja unimodalnog: " + f(print1, funkcija));
				double[] lam = zlatniRez(lambda[0], lambda[1], x.clone(), funkcija, i);
				//double[] print2 = {i==0 ? (lam[0] + lam[1])/2 : x[0], i==1 ? (lam[0] + lam[1])/2 : x[1]};
				//System.out.println(lam[0] + " " + lam[1] + " fja zlatnog: " + f(print2, funkcija));
				double lamb = (lam[1] + lam[0]) / 2;
				x[i] = lamb;//x[i] + lamb;
				//System.out.println("fja na kraju: " + f(x, funkcija));
			}
			
			gotov = true;
			for (int i = 0; i < x.length; i++) {
				if (Math.abs(x[i] - xs[i]) > epsilon)
					gotov = false;
			}
			
			//System.out.println(x[0] + " " + x[1] + " " + f(x, funkcija));			//za ispis koraka maknuti komentar
			
		} while (gotov == false);
		
		return x;
			
	}
	
	
	
	public double[] hookeJeeves(double[] x0, int funkcija) {
		
		double[] xp = x0.clone();
		double[] xb = x0.clone();
		double Dx = 0.5;
		do {
			double[] xn = istrazi(xp, Dx, funkcija);
			//System.out.println(xb[0] + " " + xb[1] + " " + xp[0] + " " + xp[1] + " " + xn[0] + " " + xn[1] + " " + f(xn, funkcija) + " " + f(xb, funkcija) + " " + Dx);
			if (f(xn, funkcija) < f(xb, funkcija)) {
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
			double p = f(x, funkcija);
			x[i] += Dx;
			double N = f(x, funkcija);
			if (N > p) {
				x[i] = x[i] - 2 * Dx;
				N = f(x, funkcija);
				if (N > p) x[i] += Dx;
			}
		}
		return x;
		
	}
	
	
	
	
	  public double[] simpleks(double x0[], int funkcija) 
	  {

	    
	    int n = x0.length;
	    
	    double x[][] = new double[n+1][n];

	    	    
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n; j++) {
				x[i][j] = x0.clone()[j];
			}
			if (i < n)
			x[i][i] += simplexStep;			//povecavanje u jednom smjeru (was changed)
		}

		//punjenje fje
		double f[]   = new double[n+1];
	    for (int j = 0; j <= n; j++) {
	      f[j] = f(x[j], funkcija);
	    }
	    
	  	
	  	do {  	
	  		//index najgoreg
	  		int h=0;
	  		for (int j = 0; j <= n;j++) {
	  			if (f[j] > f[h]) {
	  				h = j;
	  			}
	  		}

	  		//index najboljeg
	  		int l=0;
	  		for (int j = 0; j <= n; j++) {
	  			if (f[j] < f[l]) {
	  				l = j;
	  			}
	  		}

	  		//index drugog najgoreg
	  		int vh=l;
	  		for (int j = 0; j <= n; j++) {
	  			if (f[j] > f[vh] && f[j] < f[h]) {
	  				vh = j;
	  			}
	  		}

	  		//centroid
	  		double centroid = 0;
	  		double[] centroid_uk = new double[n];
	  		for (int j = 0; j <= n-1; j++) {
	  			centroid=0.0;
	  			for (int m = 0; m <= n; m++) {
	  				if (m!=h) {
	  					centroid += x[m][j];
	  				}
	  			}
	  			centroid_uk[j] = centroid/n;
	  		}

	  		// refleksija
	  		double[] xtmp = new double[n];
	  		for (int i = 0; i < n; i++) xtmp[i] = x[h][i];
		    double xr[]  = new double[n];
		    xr = refleksija(1, centroid_uk, xtmp);

		    double fxr = f(xr, funkcija);
	  		if (fxr < f[vh] && fxr >= f[l]) {
	  			for (int j = 0; j <= n-1; j++) {
	  				x[h][j] = xr[j];
	  			}
	  			f[h] = fxr;
	  		}

	  		double fxe = 0;
	  		if ( fxr <  f[l]) {
	  			
	  			//ekspanzija
		  		xtmp = new double[n];
		  		for (int i = 0; i < n; i++) xtmp[i] = x[h][i];
			    double xe[]  = new double[n];
			    xe = ekspanzija(1, centroid_uk, xtmp);
			    fxe = f(xe, funkcija);
			    
	  			if (fxe < fxr) {
	  				for (int j = 0; j <= n-1; j++) {
	  					x[h][j] = xe[j];
	  				}
	  				f[h] = fxe;
	  			}
	  			else {
	  				for (int j = 0; j <= n-1; j++) {
	  					x[h][j] = xr[j];
	  				}
	  				f[h] = fxr;
	  			}
	  		}

	  		
		    double xc[]  = new double[n];
		    double fxc = 0;
	  		if (fxr >= f[vh]) {
	  			
	  			if (fxr < f[h] && fxr >= f[vh]) {
			  		xtmp = new double[n];
			  		for (int i = 0; i < n; i++) xtmp[i] = x[h][i];
				    xc = new double[n];
				    xc = ekspanzija(0.5, centroid_uk, xtmp);

	  			}
	  			else {
	  				for (int j = 0; j <= n-1; j++) {
	  					xc[j] = centroid_uk[j]-0.5*(centroid_uk[j]-x[h][j]);
	  				}

	  			}

	  			fxc = f(xc, funkcija);
	  			if (fxc < f[h]) {
	  				for (int j = 0; j <= n-1; j++) {
	  					x[h][j] = xc[j];
	  				}
	  				f[h] = fxc;
	  			}


	  			else {
	  				for (int i = 0; i <= n; i++) {
	  					if (i != l) {
	  						for (int j = 0; j <= n-1; j++) {
	  							x[i][j] = x[l][j]+(x[i][j]-x[l][j])/2.0;
	  						}
	  					}
	  				}

	  				f[h] = f(x[h], funkcija);

	  				f[vh] = f(x[vh], funkcija);
	  			}
	  		}

	  		
	    	/*for (int j = 0; j <= n;j++) {				//ispis svega
	    	  for (int i = 0; i < n; i++) {
	    		  System.out.println("Centroid: " + centroid_uk[0] + " " + centroid_uk[1] + " Funkcija u centroidu: " + f(centroid_uk, funkcija));
	  		  }
	    	}*/

	  		double sum = 0.0;
	  		double fullsum = 0;
	  		double avg = 0;
	  		for (int j = 0; j <= n; j++) {
	  			fullsum += f[j];
	  		}
	  		avg = fullsum / (n+1);
	  		sum = 0.0;
	  		for (int j = 0; j <= n;j++) {
	  			sum += Math.pow((f[j] - avg), 2.0) / (n);
	  		}
	  		sum = Math.sqrt(sum);
	  		if (sum < epsilon) break;
	  	} while (true);

	  	//indeks najboljeg
	  	int l=0;
	  	for (int j = 0; j <= n; j++) {
	  		if (f[j] < f[l]) {
	  			l = j;
	  		}
	  	}


	  	//System.out.println("Broj evaluacija: " +  pozivanjeFunkcije);
		return x[l];

	  }
	
	
	
	
	
	
	
	
	
	public double[] refleksija(double alfa, double[] xc, double[] xh) {
		
		return oduzmi(pomnoziSkalar(xc.clone(), (1 + alfa)), pomnoziSkalar(xh.clone(), alfa));
		
	}
	
	public double[] ekspanzija(double gama, double[] xc, double[] xr) {
		
		return zbroji(xc.clone(), pomnoziSkalar(oduzmi(xr.clone(), xc.clone()), gama));
		
	}
	
	public double[] kontrakcija(double beta, double[] xc, double[] xh) {
		
		return zbroji(xc.clone(), pomnoziSkalar(oduzmi(xh.clone(), xc.clone()), beta));
		
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
	
	public double[] zbroji (double[] x1, double[] x2) {
		
		double[] x11 = x1.clone();
		double[] x22 = x2.clone();
		for (int i = 0; i < x11.length; i++) {
			x11[i] += x22[i];
		}
		return x11;
	}
	
	
	//router na to koju fju koristiti
	public double f(double[] x, int funkcija) {
		
		double[] x1 = x.clone();
		switch (funkcija) {
		case 1: return f1(x1[0], x1[1]);
		case 2: return f2(x1[0], x1[1]);
		case 3: return f3(x1);
		case 4: return f4(x1[0], x1[1]);
		case 5: return f5(x1[0]);
		case 6: return f6(x1);
		case 7: return f7(x1[0], x1[1]);
		case 8: return f8(x1[0], x1[1]);
		case 9: return f9(x1[0], x1[1]);

		}
		return f1(x1[0], x1[1]);
		
	}
	
	
	
	public double f1(double x1, double x2) {
		
		pozivanjeFunkcije++;
		return 100 * Math.pow(x2 - Math.pow(x1, 2), 2) + Math.pow(1 - x1, 2);
		
	}
	
	public double f2(double x1, double x2) {
		
		pozivanjeFunkcije++;
		return Math.pow(x1 - 4, 2) + 4 * Math.pow(x2 - 2, 2);
		
	}
	
	public double f3(double[] x) {
		
		pozivanjeFunkcije++;
		double[] x1 = x.clone();
		double suma = 0;
		for (int i = 0, j = 1; i < x1.length; i++, j++) {
			suma += Math.pow(x1[i] - j, 2);
		}
		return suma;
		
	}
	
	public double f4(double x1, double x2) {
		
		pozivanjeFunkcije++;
		return Math.abs((x1 - x2) * (x1 + x2)) + Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2));
		
	}
	
	public double f5(double x1) {
		
		pozivanjeFunkcije++;
		return Math.pow(x1 - 3, 2);
		
	}
	
	public double f6(double[] x) {
		
		pozivanjeFunkcije++;
		double[] x1 = x.clone();
		double suma = 0;
		for (int i = 0; i < x1.length; i++) {
			suma += Math.pow(x1[i], 2);
		}
		return 0.5 + (Math.pow(Math.sin(Math.sqrt(suma)), 2) - 0.5) / Math.pow((1 + 0.001 * suma), 2);

		
	}
	
	
	//derivacija prve funkcije s jednom varijablom
	public double f7(double x1, double x2) {
		
		//pozivanjeFunkcije++;
		return - 400 * Math.pow(-x1, 3) + 600 * Math.pow(-x1, 2) - 202 * -x1 + 2;			//mozda nederiviranu fju ovdje staviti
		
	}
	
	//derivacija druge funkcije s jednom varijablom
	public double f8(double x1, double x2) {
		
		//pozivanjeFunkcije++;
		return 10 * x1 + 24;
		
	}
	
	//derivacija treće funkcije s jednom varijablom
	public double f9(double x1, double x2) {
		
		//pozivanjeFunkcije++;
		return 4 * x1 - 2;
		
	}
	
		

}
