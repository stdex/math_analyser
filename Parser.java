package analyser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Parser {

	private List<Token> tokens;
	private Iterator<Token> iterator;
	private Token token;

	private Map<String, Integer> map = new HashMap<String, Integer>();

	private String name;

	// expr VAR ASSIGN INT|VAR (OP INT|VAR)*
	public void expr(List<Token> tokens) {

		this.tokens = tokens;
		this.iterator = this.tokens.iterator();
		try {
			var();
			assign();
			int value = value();

			map.put(name, value);
			
		} catch (ParseException e) {
			System.err.println("Parse error: " + e.getMessage());
		}
	}

	private int value() throws ParseException {
		int res;
		res = intvar();

		res += tale();
		return res;
	}

	private int tale() throws ParseException{
		if (!iterator.hasNext()) 
			return 0;
		boolean isPlus = operator();
		int val = intvar();
		val = (isPlus)? val: val*(-1);
		
		val += tale();
		
		return val;
	}

	private boolean operator() throws ParseException {
		if (iterator.hasNext()){
			token = iterator.next();
			if (token.name == TokenType.OPP){
				if (token.value.equals("+")) return true;
				return false;
			}
		}
		throw new ParseException("no operator");
	}

	private int intvar() throws ParseException {
		if (iterator.hasNext()) {
			token = iterator.next();
			if (token.name == TokenType.VAR) {
				if (!map.containsKey(token.value))
					throw new ParseException("No such variable in the map: "
							+ token.value);
				return map.get(token.value);
				
			} else if (token.name == TokenType.INT) {
				return Integer.valueOf(token.value);
			} else {
				throw new ParseException("token is not a variable or an integer: " + token);
			}
		}
		throw new ParseException("no token when should be int or var ");
		

	}

	private void var() throws ParseException {
		if (iterator.hasNext()) {
			token = iterator.next();
			if (token.name == TokenType.VAR) {
				name = token.value;
				return;
			} else {
				throw new ParseException("not a variable");
			}
		}
		throw new ParseException("no variable");
	}

	private void assign() throws ParseException {
		if (iterator.hasNext()) {
			Token token = iterator.next();
			if (token.name == TokenType.ASSIGN) {
				return;
			}
		}
		throw new ParseException("assignment error");
	}

	public void print() {
		for (Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
	}

}
