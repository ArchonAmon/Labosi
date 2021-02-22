import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {
	
	static int broj = 0;
	static String ulaz = null;
	boolean rezervirano = false;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String s = reader.readLine();
		ulaz = s;

		System.out.print("S");
		 S();
		 if (broj == ulaz.length()) {
			 System.out.println("\nDA");
		 }
		 else System.out.println("\nNE");
	}
	
	static void S() {
		if (broj < ulaz.length() && ulaz.charAt(broj) == 'a') {
		broj++;
			System.out.print("A");
			A();
			System.out.print("B");
			B();
		}
		else if (broj < ulaz.length() && ulaz.charAt(broj) == 'b') {
			broj++;
			System.out.print("B");
			B();
			System.out.print("A");
			A();
		}
		
		else {
			System.out.println("\nNE");
			System.exit(0);
		}
		
	}
	
	static void A() {
		if (broj < ulaz.length() && ulaz.charAt(broj) == 'b') {
			broj++;
			System.out.print("C");
			C();
		}
		else if (broj < ulaz.length() && ulaz.charAt(broj) == 'a') {
			broj++;
		}
		
		else {
			System.out.println("\nNE");
			System.exit(0);
		}
		
	}
	
	static void B() {
		if (broj < ulaz.length() && ulaz.charAt(broj) == 'c') {
			broj++;
			if (broj < ulaz.length() && ulaz.charAt(broj) == 'c') {
				broj++;
				System.out.print("S");
				S();
				if (broj < ulaz.length() && ulaz.charAt(broj) == 'b') {
					broj++;
					if (broj < ulaz.length() && ulaz.charAt(broj) == 'c') {
						broj++;
					}
					else if (broj == ulaz.length()-1) {
						System.out.println("\nDA");
						System.exit(0);
					}
					else {
						System.out.println("\nNE");
						System.exit(0);
					}
				}
				else if (broj == ulaz.length()-1) {
					System.out.println("\nDA");
					System.exit(0);
				}
			}
		}
	}
	
	static void C() {
		System.out.print("A");
		A();
		System.out.print("A");
		A();
	}
	
}
