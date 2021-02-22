package hr.fer.oop.lab4.second;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;

public class Program {
	
	public static void main (String[] args) {
		
		FileVisitor<Path> visitor = new MySecondByteReader();
		Path racuni = Paths.get("C:\\Users\\Robert Pavliš\\eclipse-workspace\\4.labos\\racuni3");
		try {
			Files.walkFileTree(racuni, visitor);
			
			 Writer bw = new BufferedWriter(
                     new OutputStreamWriter(
                             new BufferedOutputStream(new FileOutputStream("C:\\Users\\Robert Pavliš\\eclipse-workspace\\4.labos\\temp.txt"))
                             ,"UTF-8"));
			 
			 for (String st:((MySecondByteReader) visitor).getSadrzaj()) {
			 bw.write(st);
			 System.out.println(st);
			 }
			
			System.out.println(((MySecondByteReader) visitor).getStari() + " " + ((MySecondByteReader) visitor).getNovi());
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
