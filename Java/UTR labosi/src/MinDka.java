import java.io.*;
import java.util.*;

public class MinDka {
	
	static LinkedHashMap<String, LinkedList<String>> temp = new LinkedHashMap<>();
	static LinkedHashMap<Integer, ArrayList<String>> grupe2;
	static LinkedHashMap<Integer, ArrayList<String>> grupe = new LinkedHashMap<>();
	static String[] stanja = new String[100];
	static String[] simboli = new String[100];
	static int kojagrupa;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int brojac = 0;
	
//		String[] Lulaz = new String[101];
		List<String> Lprijelazi = new ArrayList<String>();
//		List<String> Lstanja = new ArrayList<String>();
//		List<String> Labeceda = new ArrayList<String>();
//		List<String> Lprihvatljiva = new ArrayList<String>();

		boolean imaprihvatljivih = true;
		String[] prihvatljiva = new String[100];
		String pocetno = null;
        String s = reader.readLine();
    	
    	
        while (s != null) {			//ovdje staviti s != null

        	if (brojac == 0) {
        		stanja = s.split("\\,");
        	}
        	if (brojac == 1) {
        		simboli = s.split("\\,");
        	}
        	if (brojac == 2 ) {
        		prihvatljiva = s.split("\\,");
        		if(s.isEmpty())imaprihvatljivih = false;
        	}
        	if (brojac == 3 && !s.isEmpty()) pocetno = s;
        	if (brojac > 3 && s != null) Lprijelazi.add(s);			//ovdje mozda dodati uvjet s != null
        	brojac++;
        	s = reader.readLine();
//       	if (brojac > 4 && s.isEmpty()) break;						//ovo bi trebalo moci maknuti 
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
        
        //nedohvatljiva
        TreeSet<String> set = new TreeSet<>();
        TreeSet<String> tempset = new TreeSet<>();
        set.add(pocetno);
        for (int i = 0; i < stanja.length; i++) {
        	TreeSet<String> set2 = new TreeSet<>();
        	set.addAll(tempset);
        	set2 = set;
        	for (int j = 0; j < Lprijelazilijevo.size(); j++) {
        		for (String st : set2) {
        			if (Lprijelazilijevo.get(j).get(0).equals(st)) {
        				tempset.add(Lprijelazidesno.get(j).get(0));
        			}
        		}
        	}
        }
        
        stanja = set.toArray(new String[set.size()]);
        
        //podijeli ih prvo na 2 dijela (prihvatljive i neprihvatljive) pa usporeduj svako stanje sa svakim i kad 
        //dodemo do nekog stanje koje ne odgovara stavljamo ga u novu skupinu (listu lista) kojih mora biti n
        //stanja su ista ako za sljedece ulazne nizove ulaze u iste grupe
        
       if (imaprihvatljivih) {
        for (int i = 0; i < stanja.length; i++) {
        	for (int j = 0; j < prihvatljiva.length; j++) {
        	if (stanja[i].equals(prihvatljiva[j])) {
        		ArrayList<String> li = new ArrayList<>();
        		if (grupe.containsKey(0)) {
            		li.addAll(grupe.get(0));
            		li.add(stanja[i]);
            		grupe.put(0, li);
            		}
            		else {
            			li.add(stanja[i]);
            			grupe.put(0, li);
            		}
        		break;
        	}
        	}
        		ArrayList<String> li = new ArrayList<>();
        		if (!grupe.containsKey(0) || !grupe.get(0).contains(stanja[i]))
        		if (grupe.containsKey(1)) {
        		li.addAll(grupe.get(1));
        		li.add(stanja[i]);
        		grupe.put(1, li);
        		}
        		else {
        			li.add(stanja[i]);
        			grupe.put(1, li);
        		}
        	
        	}
       }
       else {
    	   for (int i = 0; i < stanja.length; i++) {
       		ArrayList<String> li = new ArrayList<>();
       		if (!grupe.containsKey(0) || !grupe.get(0).contains(stanja[i]))
       		if (grupe.containsKey(0)) {
       		li.addAll(grupe.get(0));
       		li.add(stanja[i]);
       		grupe.put(0, li);
       		}
       		else {
       			li.add(stanja[i]);
       			grupe.put(0, li);
       		}
    	   }
       }
       
       
       //ako su samo prihvatljiva
       if (grupe.size() == 1 && grupe.containsKey(1) && grupe.get(1).contains(pocetno)) {
    	   grupe.clear();
    	   ArrayList<String> p = new ArrayList<>();
    	   p.add(pocetno);
    	   grupe.put(0, p);
       }
        
        
        //mapiranje
        	for (int j = 0; j < stanja.length; j++)	
        			for (int i = 0; i < Lprijelazilijevo.size(); i++) 
        					if (Lprijelazilijevo.get(i).get(0).equals(stanja[j])) {
        						if (temp.containsKey(stanja[j])) {
        						LinkedList<String> l = new LinkedList<>();
        						l.addAll(temp.get(stanja[j]));
        						l.add(Lprijelazidesno.get(i).get(0));
        						temp.put(stanja[j], l);
        						}
        						else {
        							LinkedList<String> l = new LinkedList<>();
            						l.add(Lprijelazidesno.get(i).get(0));
            						temp.put(stanja[j], l);
        						}
        					}
        	
        	//ovdje bi trebalo usporediti dohvatljiva stanja sa mapiranim i maknuti ona koja su nedohvatljiva
        	int brojac6 = 0;
        	boolean promjena = false;
        	do {
        		if (brojac6 > 50) break;
        		promjena = false;
        		grupe2 = grupe;
        		for (int j = 0; j < stanja.length-1; j++) {	//prvo stanje
        			for (int k = j+1; k < stanja.length; k++) {	//stanje s kojim usporedujemo
        				if (k >= stanja.length) break;
        				for (int i = 0; i < grupe2.size(); i++) {//grupa
//        					if (grupe.get(0).contains(stanja[j]) && i == 1 && grupe.size() < 2) i = 2; 
        					if (grupe2.get(i).contains(stanja[k]) 
        							&& grupe2.get(i).contains(stanja[j])) //ako su 2 stanja u istoj grupi
        						for (int c = 0; c < simboli.length; c++) {	//ide se po simbolima
        							for (int u = 0; u < grupe2.size(); u++) {	//ide se po grupama u koje idu stanja
        								if (!grupe2.get(u).contains(temp.get(stanja[j]).get(c)) && grupe2.get(u).contains(temp.get(stanja[k]).get(c)) ||
        									grupe2.get(u).contains(temp.get(stanja[j]).get(c)) && !grupe2.get(u).contains(temp.get(stanja[k]).get(c))) {		//ne pripadaju istoj grupi
        									//ako ne pripada grupi u kojoj je, pogledati pripada li nekoj od postojucih grupa, ako ne, napraviti novu
//        									System.out.println(stanja[j] + " "+ stanja[k]);
        									if (provjeri(stanja[j], j, i)) {		//ovo provjerava mogu li stanje staviti u neku drugu grupu
        									ArrayList<String> li = new ArrayList<>();
        									li.add(stanja[j]);
        									grupe.get(i).remove(stanja[j]);
        									grupe.put(grupe2.size(), li);
//        									System.out.println(grupe + ", if");
        									}
        									else {
        										ArrayList<String> li = new ArrayList<>();
        										li.add(stanja[j]);
        										li.addAll(grupe.get(kojagrupa));
        										if (!grupe.get(kojagrupa).contains(stanja[j]))
        										grupe.put(kojagrupa, li);
        										grupe.get(i).remove(stanja[j]);
//        										System.out.println(grupe + ", else");
        									}
        									promjena = true;
        									brojac6++;
        									break;
        								}
        							}
        							if (promjena) break;
        						}
        					if (promjena) break;
        				}
        				if (promjena) break;
        			}
//        			if (promjena) break;
        		}
        		
        	}	while(promjena == true); 
        	
        //ispis
        TreeSet<String> tmp = new TreeSet<>();
        for (int i = 0; i < grupe.size(); i++) {
        	TreeSet<String> istovjetni = new TreeSet<>();
        	for (String st : grupe.get(i))
        		istovjetni.add(st);
        	 tmp.add(istovjetni.first());
        }
        
        for (int i = 0; i < grupe.size(); i++) grupe.get(i).sort((s1,s2)->s1.compareTo(s2));
        
        int brojac5 = 0;
        for (String st : tmp) {
        	brojac5++;
        	if (brojac5 == tmp.size()) System.out.print(st);
        	else System.out.print(st+",");
        }
        System.out.println();
        brojac5 = 0;
        for (int i = 0; i < simboli.length; i++) {
        	brojac5++;
        	if (brojac5 == simboli.length) System.out.print(simboli[i]);
        	else System.out.print(simboli[i]+",");
        }
        System.out.println();
        //sad idu prihvatljiva stanja koja nisam eliminirao
        brojac5 = 0;
        TreeSet<String> tmp2 = new TreeSet<>();
        for (int i = 0; i < prihvatljiva.length; i++) {
        	for (String str : tmp) {
        		if (str.equals(prihvatljiva[i])) tmp2.add(str);
        	}
        }
        for (String str : tmp2) {
        	brojac5++;
        	if (brojac5 == tmp2.size()) System.out.print(str);
        	else System.out.print(str+",");
        }
        System.out.println();
        for (int i = 0; i < grupe.size(); i++) {
        	if (grupe.get(i).contains(pocetno)) {
        		System.out.println(grupe.get(i).get(0));
        		break;
        	}
        }
//        System.out.println(pocetno);
        //sad navesti sva stanja koja jos uvijek vrijede
        brojac5 = 0;
       
        Map<Integer, ArrayList<String>> Lprijelazilijevoispis = new LinkedHashMap<Integer, ArrayList<String>>();
        Map<Integer, ArrayList<String>> Lprijelazidesnoispis = new LinkedHashMap<Integer, ArrayList<String>>();
        for (int i = 0; i < Lprijelazilijevo.size(); i++) {
            ArrayList<String> templis = new ArrayList<>();
        	if (tmp.contains(Lprijelazilijevo.get(i).get(0))) {
        		Lprijelazilijevoispis.put(i, Lprijelazilijevo.get(i));
        		for (int j = 0; j < grupe.size(); j++)
        			for (String ss : grupe.get(j))
        				if (Lprijelazidesno.get(i).get(0).equals(ss))
        					templis.add(grupe.get(j).get(0));
        		Lprijelazidesnoispis.put(i, templis);
        	}
        }
        
        
        for (int i = 0; i < 101; i++) {
        	if (!Lprijelazilijevoispis.containsKey(i)) continue;
        	System.out.println(Lprijelazilijevoispis.get(i).get(0)+","+Lprijelazilijevoispis.get(i).get(1)+"->"+Lprijelazidesnoispis.get(i).get(0)); 
        }
        	
        	
     
        
        
        
        
//        System.out.println("\ngrupe " + grupe);
//        System.out.println("prijelazi " + temp);
	}

	static boolean provjeri(String string, int rbr, int brojgrupe) {		//ako ima grupe kojoj vec pripada promjeniti varijablu kojagrupa i returnati false
		if (brojgrupe+1 >= grupe2.size()) return true;
		if (rbr+1 < stanja.length)
		for (int k = 0; k < stanja.length; k++) {	//stanje s kojim usporedujemo
			if (k != rbr)
			for (int i = brojgrupe+1; i < grupe2.size(); i++) {		//grupa
//				if (grupe2.get(i).contains(stanja[k]) && 
//						grupe2.get(i).contains(string)) {
					int brojac4 = 0;
					for (int c = 0; c < simboli.length; c++) {
//						brojac4 = 0;
						for (int u = 0; u < grupe2.size(); u++) {
							if (grupe2.get(u).contains(temp.get(string).get(c)) && grupe2.get(u).contains(temp.get(stanja[k]).get(c))) {		//ne pripadaju istoj grupi
								brojac4++;	
								if (brojac4 == simboli.length && i != brojgrupe) {
//									if (i == brojgrupe) return true;
									kojagrupa = i;
									return false;
								}
								break;
							}
						}
						}
						}
		}
		return true;
	}
}
