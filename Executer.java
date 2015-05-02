package analyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Executer {

	
	public static void main(String[] args) {
				
		File f = new File("C:/Users/Rostunov_Sergey/workspace/analyser/bin/analyser/input.txt");
		if (!f.exists())
			throw new IllegalArgumentException("File input.txt doesn't exist");
		
		Parser p = new Parser();
		Lexer l = new Lexer();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String str;
			while((str = br.readLine()) != null) {
				List<Token> tokens = l.read(str);
				p.expr(tokens);
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Can't read file");;
		} 
		
		p.print();
	}
	
}
