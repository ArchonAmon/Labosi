import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeksickiAnalizator {
	
	static String ulaz = null;
	static String red = null;
	static int brRed = 0;
	static boolean zastava = false;

	public static void main(String[] args) throws IOException {
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String s = reader.readLine();
	        while (s != null) {
	        	
	        zastava = false;
			ulaz = s;
			brRed++;
			 if (ulaz.length() == 0 || ulaz.equals("/n")) {

			 }
			 else {

				 for (int i = 0; i < ulaz.length(); i++) {
					 if (ulaz.charAt(i) == '/' && ulaz.length() > i+1 && ulaz.charAt(i+1) == '/') break;
					 else if (ulaz.charAt(i) == ' ' || ulaz.charAt(i) == '\t' || ulaz.charAt(i) == '\n') continue;
					 else if (ulaz.charAt(i) == '=') System.out.println("OP_PRIDRUZI" + " " + brRed + " " + "=");
					 else if (ulaz.charAt(i) == '+') System.out.println("OP_PLUS" + " " + brRed + " " + "+");
					 else if (ulaz.charAt(i) == '-') System.out.println("OP_MINUS" + " " + brRed + " " + "-");
					 else if (ulaz.charAt(i) == '*') System.out.println("OP_PUTA" + " " + brRed + " " + "*");
					 else if (ulaz.charAt(i) == '(') System.out.println("L_ZAGRADA" + " " + brRed + " " + "(");
					 else if (ulaz.charAt(i) == ')') System.out.println("D_ZAGRADA" + " " + brRed + " " + ")");
					 else if (ulaz.charAt(i) == '/') System.out.println("OP_DIJELI" + " " + brRed + " " + "/");
					 else if (ulaz.charAt(i) == 'z' && ulaz.length() > i+1 && ulaz.charAt(i+1) == 'a' && (ulaz.length() > i+2 && 
							 (ulaz.charAt(i+2) == '\n' || ulaz.charAt(i+2) == ' ' || ulaz.charAt(i+2) == '\t') || ulaz.length() <= i+2)) {
						 System.out.println("KR_ZA" + " " + brRed + " " + "za");
						 i++;
						 continue;
					 }
					 else if (ulaz.charAt(i) == 'o' && ulaz.length() > i+1 && ulaz.charAt(i+1) == 'd' && (ulaz.length() > i+2 && 
							 (ulaz.charAt(i+2) == '\n' || ulaz.charAt(i+2) == ' ' || ulaz.charAt(i+2) == '\t') || ulaz.length() <= i+2)) {
						 System.out.println("KR_OD" + " " + brRed + " " + "od");
						 i++;
						 continue;
					 }
					 else if (ulaz.charAt(i) == 'd' && ulaz.length() > i+1 && ulaz.charAt(i+1) == 'o' && (ulaz.length() > i+2 && 
							 (ulaz.charAt(i+2) == '\n' || ulaz.charAt(i+2) == ' ' || ulaz.charAt(i+2) == '\t') || ulaz.length() <= i+2)) {
						 System.out.println("KR_DO" + " " + brRed + " " + "do");
						 i++;
						 continue;
					 }
					 else if (ulaz.charAt(i) == 'a' && ulaz.length() > i+1 && ulaz.charAt(i+1) == 'z' && (ulaz.length() > i+2 && 
							 (ulaz.charAt(i+2) == '\n' || ulaz.charAt(i+2) == ' ' || ulaz.charAt(i+2) == '\t') || ulaz.length() <= i+2)) {
						 System.out.println("KR_AZ" + " " + brRed + " " + "az");
						 i++;
						 continue;
					 }
					 else if (ulaz.charAt(i) == '0' || ulaz.charAt(i) == '1' || ulaz.charAt(i) == '2' || ulaz.charAt(i) == '3' || ulaz.charAt(i) == '4'
							 || ulaz.charAt(i) == '5' || ulaz.charAt(i) == '6' || ulaz.charAt(i) == '7' || ulaz.charAt(i) == '8' || ulaz.charAt(i) == '9') {
						 System.out.print("BROJ" + " " + brRed + " " + "");
						 System.out.print(ulaz.charAt(i));
							 
						 while (true) {
							 if (ulaz.length() > i + 1 && (ulaz.charAt(i+1) == '0' || ulaz.charAt(i+1) == '1' || ulaz.charAt(i+1) == '2' || ulaz.charAt(i+1) == '3' || ulaz.charAt(i+1) == '4'
									 || ulaz.charAt(i+1) == '5' || ulaz.charAt(i+1) == '6' || ulaz.charAt(i+1) == '7' || ulaz.charAt(i+1) == '8' || ulaz.charAt(i+1) == '9')) {
								 i++;
								 System.out.print(ulaz.charAt(i));
							 }

							 else break;
						 }
						 
						 if (s != null) System.out.println();
						 }

					 
					 else {
						 System.out.print("IDN" + " " + brRed + " " + "");
						 while (ulaz.charAt(i) != ' ' && ulaz.charAt(i) != '\n' && ulaz.charAt(i) != '\t' && ulaz.charAt(i) != '(' && ulaz.charAt(i) != ')'
								&& ulaz.charAt(i) != '/' && ulaz.charAt(i) != '*' && ulaz.charAt(i) != '+' && ulaz.charAt(i) != '-' && ulaz.charAt(i) != '=') {
							 System.out.print(ulaz.charAt(i));
							 if (ulaz.length() > i + 1) i++;			//ako postoji sljedeci
							 else { 
								 break;
							 }
						 }
						 if (ulaz.length() > i + 1 && (ulaz.charAt(i) == '(' || ulaz.charAt(i) == ')' || ulaz.charAt(i) == '+' || ulaz.charAt(i) == '-' ||
								 ulaz.charAt(i) == '*' || ulaz.charAt(i) == '/' || ulaz.charAt(i) == '=')) i--;
						 
						 if (s != null) System.out.println();
					 }
				 }
			 }
			 
		
	            s = reader.readLine();
	}
	}
}
