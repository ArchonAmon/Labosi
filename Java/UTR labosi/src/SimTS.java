import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimTS {

	static LinkedHashMap<String, LinkedList<String>> temp = new LinkedHashMap<>();
	static LinkedHashMap<Integer, ArrayList<String>> grupe2;
	static LinkedHashMap<Integer, ArrayList<String>> grupe = new LinkedHashMap<>();
	static String ulaz = null;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int brojac = 0;
	

		List<String> Lprijelazi = new ArrayList<String>();


		String[] prihvatljiva = new String[100];
		String pocetno = null;
		String pocetno_traka = null;
        String s = reader.readLine();
    	
    	
        while (s != null) {			//ovdje staviti s != null

        	if (brojac == 4) {
        		ulaz = s;
        	}
        	if (brojac == 5 ) {
        		prihvatljiva = s.split("\\,");
        	}
        	if (brojac == 6) pocetno = s;
        	if (brojac == 7) pocetno_traka = s;
        	if (brojac > 7 && s != null) Lprijelazi.add(s);			//ovdje mozda dodati uvjet s != null
        	brojac++;
        	s = reader.readLine();
//        	if (brojac > 7 && s.isEmpty()) break;						//ovo bi trebalo moci maknuti 
        }
        
        int brojac2 = 0;
        Map<Integer, ArrayList<String>> Lprijelazilijevo = new LinkedHashMap<Integer, ArrayList<String>>();
        Map<Integer, ArrayList<String>> Lprijelazidesno = new LinkedHashMap<Integer, ArrayList<String>>();
        
        for (String str : Lprijelazi) {
            ArrayList<String> lista1 = new ArrayList<>();
            ArrayList<String> lista2 = new ArrayList<>();
        	String[] string = str.split("->");
        	String[] string2 = string[0].split("\\,");
        	String[] string3 = string[1].split("\\,");
        	for (int i = 0; i < string2.length; i++)
        		lista1.add(string2[i]);
        	for (int i = 0; i < string3.length; i++)
        		lista2.add(string3[i]);
        	
        	Lprijelazilijevo.put(brojac2, lista1);
        	Lprijelazidesno.put(brojac2, lista2);
        	
        	brojac2++;
        }
        
        boolean imadalje = false;
        boolean prihvaca = true;
        char[] traka = new char[70];
        
        for (int i = 0; i < 70; i++) 
        traka[i] = ulaz.charAt(i);
        
        String sljedece_stanje = pocetno;
        
        int pozicija = Integer.parseInt(pocetno_traka);
        char sljedeci_traka = traka[pozicija];
        
        do {
        	imadalje = false;
        	for (int i = 0; i < Lprijelazilijevo.size(); i++) {
        		if (Lprijelazilijevo.get(i).get(0).equals(sljedece_stanje) &&
        				Lprijelazilijevo.get(i).get(1).charAt(0) == sljedeci_traka) {

        				imadalje = true;
        				traka[pozicija] = Lprijelazidesno.get(i).get(1).charAt(0);
        				sljedece_stanje = Lprijelazidesno.get(i).get(0);

            			if (pozicija == 0 && Lprijelazidesno.get(i).get(2).equals("L")) {
            				prihvaca = false;
            				break;
            			}
            			if (pozicija == 69 && Lprijelazidesno.get(i).get(2).equals("R")) {
            				prihvaca = false;
            				break;
            			}
            			if (Lprijelazidesno.get(i).get(2).equals("L")) pozicija--;
            			else pozicija++;
        				sljedeci_traka = traka[pozicija];
            			break;
        		}
        	}
        } while (imadalje && prihvaca != false);
        	System.out.print(sljedece_stanje+"|"+pozicija+"|");
        	for (int i = 0; i < traka.length; i++) System.out.print(traka[i]);
        	for (int i = 0; i < prihvatljiva.length; i++) 
        		if (prihvaca == true && sljedece_stanje.equals(prihvatljiva[i])) {
        			System.out.print("|1");
        			System.exit(0);
        		}
        	System.out.print("|0");
        
	}
	
}
