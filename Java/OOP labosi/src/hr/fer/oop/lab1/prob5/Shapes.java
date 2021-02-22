package hr.fer.oop.lab1.prob5;

public class Shapes {

	public static void main (String[] args) {
		
		plusminplus();
		bsl_slsh_kratki();
		crta_gore();
		crta_dolje();
		slsh_bsl_kratki();
		slsh_bsl_dugi();
		plusminplus();
		
		crta_dolje();
		slsh_bsl_kratki();
		slsh_bsl_dugi();
		bsl_slsh_kratki();
		crta_gore();
		
		System.out.println();
		bsl_slsh_kratki();
		crta_gore();
		plusminplus();
		
		crta_dolje();
		slsh_bsl_kratki();
		slsh_bsl_dugi();
		plusminplus();
	}
	
	public static void plusminplus() {
		System.out.println(" +--------+");
	}
	public static void bsl_slsh_kratki() {
		System.out.println(" \\        /");
	}
	public static void bsl_slsh_dugi() {
		System.out.println(" \\      /");
	}
	public static void crta_dolje() {
		System.out.println("   ______");
	}
	public static void slsh_bsl_kratki() {
		System.out.println("  /      \\");
	}
	public static void slsh_bsl_dugi() {
	System.out.println(" /        \\");
	}
	public static void crta_gore() {
		System.out.println("  \\______/");
	}
	public static void crta_gore_duga() {
		System.out.println(" /________\\");
	}
	
}
