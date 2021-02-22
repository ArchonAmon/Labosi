package hr.fer.oop.lab4.first;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SingleFileProgram {
	
	public static void main (String[] args) {
		
		Path p = Paths.get("C:\\Users\\Robert Pavliš\\eclipse-workspace\\4.labos\\racuni3\\2015\\1\\Racun_0.txt");
		InputStream file;
		try {
			file = Files.newInputStream(p);
		
		Path p2 = Paths.get("C:\\Users\\Robert Pavliš\\eclipse-workspace\\4.labos\\singleout.txt");
		MyByteWriter reader = new MyByteWriter(file, p2);
//		reader.run();
		reader.run2();
//		reader.run3();
		}
		catch (IOException e) {}
	}


}
