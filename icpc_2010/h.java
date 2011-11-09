//team 17

import java.util.*;
import java.io.*;

public class h {

	private static class wrap {
		public boolean value;
		public String rest;
		
		public wrap(boolean v, String r) {
			value = v;
			rest = r;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable {
		
		Scanner scan = new Scanner(new File("h.in"));
		int lineCount = scan.nextInt(); scan.nextLine();
		
		//for (int curLine = 0; curLine < lineCount; curLine++) {
		int curLine = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] split = line.split(" = ");
			
			boolean expected;
			if (split[1].equals("t")) {
				expected = true;
			} else {
				expected = false;
			}

			boolean actual = parse_e(split[0]).value;
				
			if (actual == expected) {
				System.out.println((curLine + 1) + ": Good brain");
			} else {
				System.out.println((curLine + 1) + ": Bad brain");
			}
			curLine++;
		}
	}
	
	private static wrap parse_e(String expr) {
		boolean negate = false;
		while (expr.substring(0,1).equals("!")) {
			negate = !negate;
			expr = expr.substring(1);
		}
		
		expr = expr.substring(1); // (
		
		wrap w = parse_e_or_v(expr);
		boolean L = w.value;
		String rest = w.rest;
		
		String op = rest.substring(0,1);
		rest = rest.substring(1);
		
		w = parse_e_or_v(rest);
		boolean R = w.value;
		rest = w.rest;

		rest = rest.substring(1); // )

		boolean retVal = false;
		if (op.equals("&")) {
			retVal = L && R;
		} else {
			retVal = L || R;
		}
		return new wrap(negate ? !retVal : retVal, rest);
	}
	
	private static wrap parse_v(String expr) {
		if (expr.substring(0,1).equals("t")) {
			return new wrap(true, expr.substring(1));
		} else {
			return new wrap(false, expr.substring(1));
		}
	}
	
	private static wrap parse_e_or_v(String expr) {
		if (expr.substring(0,1).equals("(")) {
			return parse_e(expr);
		} else if (expr.substring(0,1).equals("t") ||
				expr.substring(0,1).equals("f")) {
			return parse_v(expr);
		} else {
			boolean negate = false;
			if (expr.substring(0,1).equals("!")) {
				negate = true;
				expr = expr.substring(1);
			}
			wrap res = parse_e_or_v(expr);
			return new wrap(negate ? !res.value : res.value, res.rest);
		}
	}
}
