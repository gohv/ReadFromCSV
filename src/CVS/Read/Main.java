package CVS.Read;

import java.util.*;
import java.nio.file.*;


public class Main {

	public static void main(String[] args) {

		try {
			List<String> read = Files.readAllLines(Paths.get("CVS/animals.lostandfound.csv"));
			for (String readLine : read ){
				readLine = readLine.replace(" ", " ");
		System.out.println(readLine);
				}
		} catch (Exception e) {
			System.err.println("No file Found!");
		}

	}

}
