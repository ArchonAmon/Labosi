import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SintaksniAnalizator {
	
	static String trenutni = null;
	static int trenRazina = 0;
	static ArrayList<String> string = new ArrayList<>();
	static ArrayList<Integer> razina = new ArrayList<>();
	static ArrayList<String> zadatak = new ArrayList<>();
	static int indexTrenutni = 0;
	static boolean trenutniNull = false;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String s = reader.readLine();
				//traziti po listi gdje nesto fali i ispisati samo zadnju stvar koja fali u error
		 while (s != null) {				//promijeni
			 zadatak.add(s);
			 
			 
			 s = reader.readLine();
//			 if (s.isEmpty()) break;
		 }

			 if (zadatak.isEmpty()) {
				 trenutniNull = true;
				 
			 }
			 else {
				 trenutni = zadatak.get(indexTrenutni);
			 }
			 
				string.add("<program>");
				razina.add(trenRazina);
			 
			 for (int i = 0; i < 1; i++) {				//i < zadnjiRed, zasto radi kad je 1?
			 program();
			 trenRazina = 0;
			 if (indexTrenutni < zadatak.size())
			 trenutni = zadatak.get(indexTrenutni);
			 }
			
//			 trenutniNull = true;
//			 program();													//mora biti jedan slucaj za prazan red
			 
			 
			 for (int i = 0; i < string.size(); i++) {
				 for (int j = 0; j < razina.get(i); j++) {			//razina.get(i) jer i oznacava redni broj na listi
					 System.out.print(" ");
				 }
				 System.out.print(string.get(i));
				 System.out.println();
			 }
			 
			 //izgraditi listu ovdje
			 
			 

		 
		
	}

	private static void program() {
		
		if (trenutniNull || trenutni.startsWith("IDN") || trenutni.startsWith("KR_ZA")) {
			
			trenRazina++;
			lista_naredbi();
			trenRazina--;
			
			
		}
		
		 else {
			 System.out.println("err " + trenutni);
			 System.exit(0);
		 }
		
	}

	private static void lista_naredbi() {
		string.add("<lista_naredbi>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("IDN") || trenutni.startsWith("KR_ZA")) {
			
			trenRazina++;
			naredba();
			trenRazina--;
			
			trenRazina++;
			lista_naredbi();
			trenRazina--;
			
		}
		
		else {	// if (trenutni.startsWith("KR_AZ") || trenutniNull) {
			
			trenRazina++;
			string.add("$");
			razina.add(trenRazina);
			trenRazina--;
			
		}
			
		
	}

	private static void naredba() {
		string.add("<naredba>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("IDN")) {
			
			trenRazina++;
			naredba_pridruzivanja();
		    trenRazina--;
			
		}
		
		else if (trenutni.startsWith("KR_ZA")) {
			
			trenRazina++;
			za_petlja();
		    trenRazina--;
			
		}
		
		
	}

	private static void za_petlja() {
		string.add("<za_petlja>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("KR_ZA")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni).startsWith("IDN")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti IDN
			 }
			 else {
				 System.out.println("err " + trenutni);
				 System.exit(0);
			 }
			
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni).startsWith("KR_OD")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti KR_OD
			 }
			 else {
				 System.out.println("err " + trenutni);
				 System.exit(0);
			 }
			
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			trenutni = zadatak.get(indexTrenutni);
			
			trenRazina++;
			E();
			trenRazina--;
			
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			
			indexTrenutni++;
			if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("KR_DO")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti KR_DO
			 }
			 else {
				 System.out.println("err " + trenutni);
				 System.exit(0);
			 }
			

			
			trenRazina++;
			E();
			trenRazina--;
			
			trenRazina++;
			lista_naredbi();
			trenRazina--;
			
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("KR_AZ")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti KR_AZ
			 }
			else if (zadatak.get(indexTrenutni-1).startsWith("KR_AZ")) {

			}
			 else {
				 System.out.println("err " + trenutni);
				 System.exit(0);
			 }
			
		}
	}

	private static void naredba_pridruzivanja() {
		string.add("<naredba_pridruzivanja>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("IDN")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni).startsWith("OP_PRIDRUZI")) {
				 trenutni = zadatak.get(indexTrenutni);															//idemo na novi znak, mora biti =	 
				 }
			 else {
				 System.out.println("err " + trenutni);
				 System.exit(0);
			 }
			 
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);			
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size()) {
				 trenutni = zadatak.get(indexTrenutni);													//da ne bude oob exception	 
				 }
			 else {
				 System.out.println("err kraj");
				 System.exit(0);
			 }
			
			
			trenRazina++;
			E();
			trenRazina--;
			 
		}

		
	}

	private static void E() {
		string.add("<E>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("IDN") || trenutni.startsWith("BROJ") || trenutni.startsWith("OP_PLUS") || trenutni.startsWith("OP_MINUS") || trenutni.startsWith("L_ZAGRADA")) {
			
			trenRazina++;
			T();
			trenRazina--;
			
			trenRazina++;
			E_lista();
			trenRazina--;
			
			
		}
		
	}
	
	private static void E_lista() {
		string.add("<E_lista>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("OP_PLUS")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("OP_PLUS")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti +
			 }
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }
			
				trenRazina++;
				E();
				trenRazina--;
			
		}
		
		else if (trenutni.startsWith("OP_MINUS")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("OP_MINUS")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti +
			 }
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }
			
				trenRazina++;
				E();
				trenRazina--;
			
		}
		
//		else if (trenutni.startsWith("IDN") || trenutni.startsWith("KR_ZA") || trenutni.startsWith("KR_DO") || trenutni.startsWith("KR_AZ") || trenutni.startsWith("D_ZAGRADA") || trenutni.equals(null)){					//ovdje mozda dodati jos znak za kraj ulaza
		else {	
			trenRazina++;
			string.add("$");
			razina.add(trenRazina);
			trenRazina--;
			
		}
		
//		 else {
//		 System.out.println(trenutni);
//		 System.exit(0);
//	 }
		
	}

	private static void T() {
		string.add("<T>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("IDN") || trenutni.startsWith("BROJ") || trenutni.startsWith("OP_PLUS") || trenutni.startsWith("OP_MINUS") || trenutni.startsWith("L_ZAGRADA")) {
			
			trenRazina++;
			P();
			trenRazina--;
			
			trenRazina++;
			T_lista();
			trenRazina--;
			
			
		}
		
	}
	
	private static void T_lista() {
		string.add("<T_lista>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("OP_PUTA")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("OP_PUTA")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti *
			 }
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }
			
				trenRazina++;
				T();
				trenRazina--;
			
		}
		
		else if (trenutni.startsWith("OP_DIJELI")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("OP_DIJELI")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti /
			 }
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }
			
				trenRazina++;
				T();
				trenRazina--;
			
		}
		
//		else if (trenutni.startsWith("IDN") || trenutni.startsWith("KR_ZA") || trenutni.startsWith("KR_DO") || trenutni.startsWith("KR_AZ") || trenutni.startsWith("OP_PLUS") || trenutni.startsWith("OP_MINUS") || trenutni.startsWith("D_ZAGRADA") || trenutni.equals(null)) {								//ovdje mozda dodati jos kraj ulaza
		else {			
			trenRazina++;
			string.add("$");
			razina.add(trenRazina);
			trenRazina--;
			
		}
		
//		 else {
//		 System.out.println("err " + trenutni);
//		 System.exit(0);
//	 }
		
	}
	
	
	private static void P() {
		string.add("<P>");
		razina.add(trenRazina);
		
		if (trenutni.startsWith("OP_PLUS")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("OP_PLUS")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti +
			 }
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }
			
				trenRazina++;
				P();
				trenRazina--;
			
		}
		
		else if (trenutni.startsWith("OP_MINUS")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("OP_MINUS")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti -
			 }
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }
			
				trenRazina++;
				P();
				trenRazina--;
			
		}
		
		else if (trenutni.startsWith("L_ZAGRADA")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("L_ZAGRADA")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti (
			 }
			 
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }
			
				trenRazina++;
				E();
				trenRazina--;
				
				trenRazina++;
				string.add(trenutni);
				razina.add(trenRazina);
				trenRazina--;
				
				
				indexTrenutni++;
				 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("D_ZAGRADA")) {
					 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti )
				 }
					else if (zadatak.get(indexTrenutni-1).startsWith("D_ZAGRADA")) {

					}
				 else {
					 System.out.println("err " + trenutni);
					 System.exit(0);
				 }
			
		}
		
		else if (trenutni.startsWith("IDN")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("IDN")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti IDN
			 }
				else if (trenutni.startsWith("IDN")) {
					trenutni = "!";
				}
//			 else {
//				 System.out.println(trenutni);
//				 System.exit(0);
//			 }

		}
		
		else if (trenutni.startsWith("BROJ")) {
			trenRazina++;
			string.add(trenutni);
			razina.add(trenRazina);
			trenRazina--;
			
			indexTrenutni++;
			 if (indexTrenutni < zadatak.size() && zadatak.get(indexTrenutni-1).startsWith("BROJ")) {
				 trenutni = zadatak.get(indexTrenutni);							//idemo na novi znak, mora biti BROJ
			 }
//			 else {
//				 System.out.println("err" + trenutni);
//				 System.exit(0);
//			 }

		}
		
		 else {
		 System.out.println("err " + trenutni);
		 System.exit(0);
	 }
		
	}
	
	
	
}
