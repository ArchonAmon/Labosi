import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Matrica {
	
    public int M;             // broj redaka
    public int N;             // broj stupaca
    public double[][] podaci;   // matrica
    public double epsilon = 0.000000000001;			//10 na -12 granica
    public int brojPermutacija = 0;			//broj permutacija za racunanje determinante
    
    //konstruktor za stvaranje prazne matrice, dobiva broj redaka i stupaca matrice
    public Matrica(int M, int N) {
    	
    	this.M = M;
    	this.N = N;
    	podaci = new double[M][N];
    	
    }

    //konstruktor za stvaranje pune matrice na osnovu tekstualne datoteke, dobiva put do datoteke
    public Matrica(String path) throws IOException {
    	
    	  File file = new File(path); 
    	  
    	  BufferedReader br = new BufferedReader(new FileReader(file)); 
    	  
    	  String st;
    	  boolean prviRed = true;
    	  ArrayList<String> lista = new ArrayList<>();
    	  
    	  while ((st = br.readLine()) != null) {
    	    if (prviRed == true) {
    	    	N = st.split("[ \t]").length;
    	    	prviRed = false;
    	    }
    	  	
    	    	lista.add(st); //lista za podatke matrice    	  	

    	  }
    	  
       	  	 M = lista.size();

    	  
        	 podaci = new double[M][N];
	  	
  	    	 for (int i = 0; i < M; i++) {
  	    		for (int j = 0; j < N; j++) {
  	  			podaci[i][j] = Double.parseDouble(lista.get(i).split("[ \t]")[j]);
  	    		}
  	    	}
    	  
    	}
    
    
    //defaultni konstruktor za klasu
    public Matrica() {
    	
    }
    
    
    
    
    //ispis matrice u datoteku, dobiva put do datoteke
    public void ispisDatoteka(String path) throws IOException {

    	double [][] p = podaci;
        FileWriter myWriter = new FileWriter(path);
        for (int i = 0; i < M; i++) {
        	ArrayList<String> lista = new ArrayList<>();
        	for (int j = 0; j < N; j++) {
        		lista.add(Double.toString(p[i][j]));
        	}
            myWriter.write(String.join(" ", lista));
            if(i < M - 1)
            myWriter.write("\n");
        }

        myWriter.close();

    }
    
    
    
    //ispis matrice na ekran
    public void ispisEkran() {
    	
    	double[][] p = podaci;
        for (int i = 0; i < M; i++) {
        	ArrayList<String> lista = new ArrayList<>();
        	for (int j = 0; j < N; j++) {
        		lista.add(Double.toString(p[i][j]));
        	}
            System.out.println(String.join(" ", lista));
        }
    	
    }
    
    
    
    //zbrajanje 2 matrice, dobiva matricu sa kojom se zbraja
    public Matrica zbroji(Matrica B) {
    	
    	Matrica A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Zbrajati se mogu samo matrice s istim dimenzijama");
        Matrica C = new Matrica(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.podaci[i][j] = A.podaci[i][j] + B.podaci[i][j];
        return C;

    }
    
    
    
    //oduzimanje 2 matrice, dobiva matricu s kojom se oduzima
    public Matrica oduzmi(Matrica B) {
    	
    	Matrica A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Oduzimati se mogu samo matrice s istim dimenzijama");
        Matrica C = new Matrica(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.podaci[i][j] = A.podaci[i][j] - B.podaci[i][j];
        return C;

    }
    
    
    
    //mnozenje 2 matrice, dobiva matricu s kojom se množi
    public Matrica pomnoži(Matrica B) {
    	
        Matrica A = this;
        if (A.N != B.M) throw new RuntimeException("Množiti se mogu samo matrice gdje prva matrica ima isti broj stupaca kao druga redova");
        Matrica C = new Matrica(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.podaci[i][j] += (A.podaci[i][k] * B.podaci[k][j]);
        return C;
        
    }
    
    //mnozenje matrice i skalara, dobiva broj s kojim se množi
    public Matrica pomnožiSkalar(double d) {
    	
    	Matrica A = this;
    	Matrica B = new Matrica(M, N);
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			B.podaci[i][j] = A.podaci[i][j] * d;
    		}
    	}
    	return B;
    	
    }
    
    
    
    //transponirana matrica
    public Matrica transponiraj() {
    	
        Matrica A = new Matrica(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.podaci[j][i] = this.podaci[i][j];
        return A;
        
    }
    
    
    
    
    @Override
    //metoda za usporedbu matrica
    public boolean equals(Object o) {
    	
    	if (o == null) return false;
    	Matrica B = (Matrica) o;
        Matrica A = this;
        if (B.M != A.M || B.N != A.N) return false;
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.podaci[i][j] != B.podaci[i][j]) return false;    	
    	return true;
    	
    }
    
    
    //metoda i algoritam za supstituciju unaprijed, dobiva vektor
    public Matrica supstUnaprijed(Matrica B) {
    	
    	Matrica A = this;
    	if (A.M != A.N) throw new RuntimeException("Ulazna matrica mora biti kvadratna");
    	if (B.N != 1 || B.M == 0) throw new RuntimeException("Vektor mora imat jedan stupac");
    	for (int i = 0; i <= M; i++) {
    		for (int j = i + 1; j < N; j++) {
    			B.podaci[j][0] -= A.podaci[j][i] * B.podaci[i][0];
    		}
    	}
    	return B;
    	
    }
    
    
    //metoda i algoritam za supstituciju unatrag, dobiva vektor
    public Matrica supstUnatrag(Matrica B) {
    	
    	Matrica A = this;
    	if (A.M != A.N) throw new RuntimeException("Ulazna matrica mora biti kvadratna");
    	if (B.N != 1 || B.M == 0) throw new RuntimeException("Vektor mora imat jedan stupac");
    	for (int i = N - 1; i >= 0; i--) {
    		if (Math.abs(A.podaci[i][i]) < epsilon) throw new RuntimeException("Dijeljenje s nulom");		//greska datektirana 21.10. nakon popravka linije s epsilonom gore, ista stvar i tu
    		B.podaci[i][0] /= A.podaci[i][i];
    		for (int j = 0; j < i; j++) {
    			B.podaci[j][0] -= A.podaci[j][i] * B.podaci[i][0];
    		}
    	}
    	return B;
    	
    }
    
    
    
    //metoda i algoritam za LU dekompoziciju
    public void LUdekompozicija() {
    	
    	Matrica A = this;
    	if (A.M != A.N) throw new RuntimeException("Ulazna matrica mora biti kvadratna");
    	for (int i = 0; i < N - 1; i++) {
    		for (int j = i + 1; j < N; j++) {
    			if (Math.abs(A.podaci[i][i]) < epsilon) throw new RuntimeException("Dijeljenje s nulom");    //21.10. sam u provjeri koda shvatio da nedosta abs vrijednost pa sam ju dodao pa sad 4. zadatak radi
    			A.podaci[j][i] /= A.podaci[i][i];
    			for (int k = i + 1; k < N; k++) {
        			A.podaci[j][k] -= A.podaci[j][i] * A.podaci[i][k];
    			}
    		}
    		//A.ispisEkran();			//maknuti komentar za medukorake u racunanju matrica
    	}
    	
    	
    	//return A;
    	
    }
    
    
    
    
    
    //metoda i algoritam za LUP dekompoziciju
    public Matrica LUPdekompozicija() {
    	
    	Matrica A = this;
    	Matrica p = new Matrica(M, N);		//spremanje zamjena redova
    	if (A.M != A.N) throw new RuntimeException("Ulazna matrica mora biti kvadratna");
    	brojPermutacija = 0;
    	//ispunjavanje pom matrice
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			if (j == i) p.podaci[i][j] = 1;
    		}
    	}
    	
    	for (int i = 0; i < N; i++) {
    		int redZaZamjenu = i;
    		double trenutniNajveci = Math.abs(A.podaci[i][i]);
    		for (int t = i; t < N; t++) {
    			if (Math.abs(A.podaci[t][i]) > trenutniNajveci) {
    				redZaZamjenu = t;
    				trenutniNajveci = A.podaci[t][i];
    			}
    		}
    		
    		if (redZaZamjenu != i) {		//ako treba biti zamjene
    			
    			brojPermutacija++;
    		
    		//zamjena redova
    			for (int t = 0; t < N; t++) {
    				double tmp = A.podaci[i][t];
    				A.podaci[i][t] = A.podaci[redZaZamjenu][t];
    				A.podaci[redZaZamjenu][t] = tmp;
    			}
    		
    		//zamjena redova pom Matrice
    			for (int t = 0; t < N; t++) {
    				double tmp = p.podaci[i][t];
    				p.podaci[i][t] = p.podaci[redZaZamjenu][t];
    				p.podaci[redZaZamjenu][t] = tmp;
    			}
    			
    		}
    		
    		if (Math.abs(A.podaci[i][i]) < epsilon) throw new RuntimeException("Neizbježno dijeljenje s nulom");
    		
    		for (int j = i + 1; j < N; j++) {
    			A.podaci[j][i] /= A.podaci[i][i];
    			for (int k = i + 1; k < N; k++) {
        			A.podaci[j][k] -= A.podaci[j][i] * A.podaci[i][k];
    			}
    		}
    		//A.ispisEkran();			//maknuti komentar za medukorake u racunanju matrica
    	}
    	return p;
    	
    }
    
    
    
    //metoda za racunanje L matrice
    public Matrica LMatrica() {
    	
    	Matrica A = this;
    	if (A.M != A.N) throw new RuntimeException("Ulazna matrica mora biti kvadratna");
    	Matrica B = new Matrica(M, N);
    	for (int i = 0; i < M; i++) {
    		for (int j = i; j < N; j++) {
    			if (i == j) B.podaci[j][i] = 1;
    			else B.podaci[j][i] = A.podaci[j][i];
    		}
    	}
    	return B;
    	
    }
    
    
    //metoda za racunanje U matrice
    public Matrica UMatrica() {
    	
    	Matrica A = this;
    	if (A.M != A.N) throw new RuntimeException("Ulazna matrica mora biti kvadratna");
    	Matrica B = new Matrica(M, N);
    	for (int i = 0; i < M; i++) {
    		for (int j = i; j < N; j++) {
    			B.podaci[i][j] = A.podaci[i][j];
    		}
    	}
    	return B;
    	
    }
    
    
    
    //racunanje inverzije matrice
    public Matrica inverzija() {
    	
    	Matrica A = this;
    	Matrica B = new Matrica(M,N);
    	Matrica pivotiranje = A.LUPdekompozicija();
    	Matrica pom = new Matrica(M,N);
    	//ispunjavanje pom matrice
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			if (j == i) pom.podaci[i][j] = 1;
    		}
    	}
    	
    	//algoritam za inverziju
    	for (int i = 0; i < M; i++) {
    		Matrica p = new Matrica(M, 1);
    		for (int j = 0; j < M; j++) p.podaci[j][0] = pom.podaci[j][i];
    		p = pivotiranje.pomnoži(p);
    		Matrica pp = A.LMatrica().supstUnaprijed(p);
    		pp = A.UMatrica().supstUnatrag(pp);
    		
    		for (int j = 0; j < N; j++) {
    			
    			B.podaci[j][i] = pp.podaci[j][0];
    			
    		}
    	}
    	
    	return B;
    	
    }
    
    
    
    //racunanje determinante matrice
    public double determinanta() {
    	
    	Matrica A = this;
    	Matrica pivotiranje = A.LUPdekompozicija();
    	double determinanta = 0;
    	double l = 1;
    	double u = 1;
    	double predznak = Math.pow(-1, brojPermutacija);
    	
    	//ispunjavanje dijagonala l i u matrice
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			if (j == i) {
    				l *= A.LMatrica().podaci[i][j];
    				u *= A.UMatrica().podaci[i][j];
    			}
    		}
    	}
    	
    	determinanta = predznak * l * u;
    			
    	return determinanta;
    	
    }
    
    
    

    //dobivanje pojedinog elementa
	public double getElement(int red, int stupac) {
		return podaci[red][stupac];
	}

	//namjestanje pojedinog elementa matrice
	public void setElement(int red, int stupac, double element) {
		podaci[red][stupac] = element;
	}

	//dobivanje broja redaka matrice
	public int getRedovi() {
		return M;
	}

	//namjestanje broja redaka matrice
	public void setRedovi(int m) {
		M = m;
	}

	//dobivanje broja stupaca matrice
	public int getStupci() {
		return N;
	}

	//namjestanje broja stupaca matrice
	public void setStupci(int n) {
		N = n;
	}   
    
    	
}
	
