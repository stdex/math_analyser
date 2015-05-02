package analyser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

	
	public List<Token> read(String str) {
		List<Token> tokens = new ArrayList<Token>();
		str = str.trim();
		
		StringBuffer tokenPatternsBuffer = new StringBuffer();
	    for (TokenType tokenType : TokenType.values())
	        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
	    Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

	 // Begin matching tokens
	    Matcher matcher = tokenPatterns.matcher(str);
	    while (matcher.find()) {
	        if (matcher.group(TokenType.VAR.name()) != null) {
	            tokens.add(new Token(TokenType.VAR, matcher.group(TokenType.VAR.name())));
	            continue;
	        } else if (matcher.group(TokenType.ASSIGN.name()) != null) {
	            tokens.add(new Token(TokenType.ASSIGN, matcher.group(TokenType.ASSIGN.name())));
	            continue;
	        } else if (matcher.group(TokenType.INT.name()) != null) {
	            tokens.add(new Token(TokenType.INT, matcher.group(TokenType.INT.name())));
	            continue;
	        } else if (matcher.group(TokenType.OPP.name()) != null){
	        	tokens.add(new Token(TokenType.OPP, matcher.group(TokenType.OPP.name())));
	        	continue;
	        }
	    }
	        
		return tokens;
	}

}
