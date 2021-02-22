import java.util.ArrayList;
import java.util.Random;

public class GenetskiAlgoritam {

	public double epsilon = 0.0001;
	public int pozivanjeFunkcije = 0;
	public int veličinaPopulacije = 100;
	public double vjerojatnostMutacije = 0.02;
	public double vjerojatnostMutacijeCijelog = 0.2;
	public double donjaGranica = -5;
	public double gornjaGranica = 10;
	public int iteracije = 0;


	
	
	public double[] algoritam(int veličinaVektora, int brojDecimalnih, int funkcija, boolean binarniPrikaz, 
			boolean križanjeSMaskom, boolean aritmetičkoKrižanje) {	
		ArrayList<double[]> populacija = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < veličinaPopulacije; i++) {
			double[] temp = new double[veličinaVektora];
			for(int j = 0; j < veličinaVektora; j++) {
				temp[j] = r.nextDouble() * (gornjaGranica - donjaGranica) + donjaGranica;
				//temp[j] = temp[j] * Math.pow(10, brojDecimalnih);
				//int t = (int)Math.round(temp[j]);
				//temp[j] = (double) t / Math.pow(10, brojDecimalnih);
			}
			populacija.add(temp);			//temp je vektor varijabli
		}
		
		
		int brBitova = 0;

		for (int i = 0; i < 100000; i++) {
			brBitova++;
			if (Math.pow(2, i) > (gornjaGranica - donjaGranica) * Math.pow(10, brojDecimalnih)) break;
		}

		double najbolji = 9999;
		double[] točkaNajboljeg = new double[veličinaVektora];
		
		
		do {			
			double ispisSuma = 0;
			for (double[] p : populacija) {
				double suma = f(p, funkcija);
				ispisSuma += suma;
				if (suma < najbolji) {
					najbolji = suma;			//traženje najboljeg
					točkaNajboljeg = p;
				}
			}

			//if (iteracije % 100000 == 0) {
			//	System.out.println(ispisSuma/veličinaPopulacije + " " + najbolji);
			//}

			int indexPrvi = r.nextInt(veličinaPopulacije); 			//bilo je r.nextInt() % veličinaPopulacije;
			int indexDrugi;
			do {
				indexDrugi = r.nextInt(veličinaPopulacije);
			} while (indexDrugi == indexPrvi);
			int indexTreci;												//odaberi 3 nasumična broja
			do {
				indexTreci = r.nextInt(veličinaPopulacije);
			} while (indexTreci == indexPrvi || indexTreci == indexDrugi);
			
			
			double fjaPrvi = f(populacija.get(indexPrvi), funkcija);
			double fjaDrugi = f(populacija.get(indexDrugi), funkcija);
			double fjaTreci = f(populacija.get(indexTreci), funkcija);

			double[] prvi = new double[veličinaPopulacije];
			double[] drugi = new double[veličinaPopulacije];
			
			//izbacivanje najgoreg
			if (fjaPrvi > fjaDrugi && fjaPrvi > fjaTreci) {			//prvi je najgori
				if (fjaDrugi > fjaTreci) {					//namještanje da je drugi bolji od prvoga
					prvi = populacija.get(indexDrugi);
					drugi = populacija.get(indexTreci);
				}
				else {
					prvi = populacija.get(indexTreci);
					drugi = populacija.get(indexDrugi);
				}
				populacija.remove(indexPrvi);		
			}
			else if (fjaDrugi > fjaPrvi && fjaDrugi > fjaTreci) {		//drugi je najgori
				if (fjaPrvi > fjaTreci) {
				prvi = populacija.get(indexPrvi);
				drugi = populacija.get(indexTreci);
				}
				else {
				prvi = populacija.get(indexTreci);
				drugi = populacija.get(indexPrvi);
				}
				populacija.remove(indexDrugi);		
			}
			else {												//treci je najgori
				if (fjaPrvi > fjaDrugi) {
				prvi = populacija.get(indexPrvi);
				drugi = populacija.get(indexDrugi);
				}
				else {
				prvi = populacija.get(indexDrugi);
				drugi = populacija.get(indexPrvi);
				}
				populacija.remove(indexTreci);		
			}
			
			if (binarniPrikaz == true) {

			int[] binarniBrojeviPrvi = new int[veličinaVektora];
			int[] binarniBrojeviDrugi = new int[veličinaVektora];

			for (int i = 0; i < veličinaVektora; i++) {
			binarniBrojeviPrvi[i] = (int) Math.round((prvi[i] - donjaGranica) * (Math.pow(2, brBitova) - 1) / (gornjaGranica - donjaGranica));		//binarni prikaz u broju

			binarniBrojeviDrugi[i] = (int) Math.round((drugi[i] - donjaGranica) * (Math.pow(2, brBitova) - 1) / (gornjaGranica - donjaGranica));		//binarni prikaz u broju
			}

			
			//pretvori ih u binarne
			int[][] prviBinarno = new int[veličinaVektora][brBitova];
			for (int j = 0; j < veličinaVektora; j++) {
			int[] binaryNumPrvi = new int[brBitova];
			
			for (int i = brBitova - 1; i >= 0; i--) {
				int k = binarniBrojeviPrvi[j] >> i;
				binaryNumPrvi[i] = (k & 1) == 1 ? 1 : 0;
			}
			
			prviBinarno[j] = binaryNumPrvi;
			}

			
			
			int[][] drugiBinarno = new int[veličinaVektora][brBitova];
			for (int j = 0; j < veličinaVektora; j++) {
			int[] binaryNumDrugi = new int[brBitova];
			
			for (int i = brBitova - 1; i >= 0; i--) {
				int k = binarniBrojeviDrugi[j] >> i;
				binaryNumDrugi[i] = (k & 1) == 1 ? 1 : 0;
			}
			
			drugiBinarno[j] = binaryNumDrugi;
			}

			
			//krizanje i mutacija
			int[][] binaryNumNovi = new int[veličinaVektora][brBitova];

			for (int j = 0; j < veličinaVektora; j++) {
				
				//generiranje maske
				int[] maska = new int[brBitova];
				for (int z = 0; z < brBitova; z++)
				maska[z] = r.nextInt(1);		//između 0 i 1
				
				int tockaKrizanja = r.nextInt(brBitova);
				
			for (int i = 0; i < brBitova; i++) {
				
				if (križanjeSMaskom == false)
				binaryNumNovi[j][i] = i < tockaKrizanja ? prviBinarno[j][i] : drugiBinarno[j][i];
				else binaryNumNovi[j][i] = (prviBinarno[j][i] * drugiBinarno[j][i]) == 1 ? 1 : (maska[i] * ((prviBinarno[j][i] == drugiBinarno[j][i]) ? 0 : 1));
				
				double pMutacija = r.nextDouble(); 
				if (pMutacija > 0 && pMutacija < vjerojatnostMutacije && binaryNumNovi[j][i] == 1) binaryNumNovi[j][i] = 0;
				else if (pMutacija > 0 && pMutacija < vjerojatnostMutacije && binaryNumNovi[j][i] == 0) binaryNumNovi[j][i] = 1;
				
			}
			}

			
			

			int noveTočkeBinarni[] = new int[veličinaVektora];
			for (int j = 0; j < veličinaVektora; j++) {			//ovo daje vektor točaka
				int counter = 0;
				int noviBrojBinarni = 0;
			for (int i = brBitova - 1; i >= 0; i--) {			//ovo daje 1 broj
				noviBrojBinarni += binaryNumNovi[j][counter] * Math.pow(2, i);		//1. po bitovima, onda po vektoru točaka
				counter++;
			}
			noveTočkeBinarni[j] = noviBrojBinarni;
			}


			double[] novi = new double[veličinaVektora];
			for (int i = 0; i < veličinaVektora; i++)
			novi[i] = noveTočkeBinarni[i] * (gornjaGranica - donjaGranica) / (Math.pow(2, brBitova) - 1) + donjaGranica;			//obrnuta formula za pretvorbu u binarni

			populacija.add(novi);

			double sumaNovog = f(novi, funkcija);
			if (sumaNovog < najbolji) {				//provjera je li novi najbolji
				najbolji = sumaNovog;
				točkaNajboljeg = novi;
			}
			
			}		//kraj binarnong
			
			else {	//početak realnog
				//imamo prvi i drugi u double
				
				double a = r.nextDouble();
				double[] novi = new double[veličinaVektora];
				
				//križanje i mutacija
				for (int i = 0; i < veličinaVektora; i++) {
					if (aritmetičkoKrižanje == true) novi[i] = a * prvi[i] + (1 - a) * drugi[i];
					else novi[i] = a * (drugi[i] - prvi[i]) + drugi[i];
				}
				
				//double pMutacija = 1 - Math.pow((1 - r.nextDouble()), brBitova);
				double temp = r.nextDouble();
				if (temp < vjerojatnostMutacijeCijelog) {
					for (int i = 0; i < veličinaVektora; i++) {
						novi[i] = r.nextDouble() * (gornjaGranica - donjaGranica) + donjaGranica;
					}
				}
				
				populacija.add(novi);
				
				double sumaNovog = f(novi, funkcija);
				if (sumaNovog < najbolji) {				//provjera je li novi najbolji
					najbolji = sumaNovog;
					točkaNajboljeg = novi;
				}
				
			}


			iteracije++;
		} while (najbolji > epsilon && iteracije < 100000);//iteracije < 1000);

		
		//System.out.println(najbolji);

		return točkaNajboljeg;
		
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
		case 7: return f7(x1);
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
	
	public double f7(double[] x) {
		
		pozivanjeFunkcije++;
		double[] x1 = x.clone();
		double suma = 0;
		for (int i = 0; i < x1.length; i++) {
			suma += Math.pow(x1[i], 2);
		}
		return Math.pow(suma, 0.25) * (1 + Math.pow(Math.sin(50 * Math.pow(suma, 0.1)), 2));

	}
	
}



