package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import jxl.write.WriteException;

public class Main {

	public static void transformUzivatele() throws WriteException,
			IOException {

		String inputFile = "files/slozky.csv";
		String outputFile = "files/slozky_output.csv";
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

		String inputLine = "";
		while ((inputLine = in.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(inputLine,";");
			String username = "";
			if (tokenizer.hasMoreElements())
				username = tokenizer.nextToken();
			while (tokenizer.hasMoreElements()) {
				String token = "";
				if ((token = tokenizer.nextToken()) != "") {
					inputLine = username + ";" +token;
					out.write(inputLine + "\r\n");
					inputLine = "";
				}
			}
			

		}
		in.close();
		out.close();

	}

	public static void main(String[] args) throws WriteException, IOException {
		transformUzivatele();
	}
}
