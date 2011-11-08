import java.io.*;
import java.util.*;

class a {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		long N = s.nextLong();
		s.nextLine(); // blank
		for( long i = 0 ; i < N ; i++) { 
			String line = s.nextLine();
			long gs = 0;
			long bs = 0;
			for (int ii = 0; ii < line.length(); ii++) {
				if (Character.toLowerCase(line.charAt(ii)) == 'g') gs++;
				if (Character.toLowerCase(line.charAt(ii)) == 'b') bs++;
			}
			System.out.print(line + " is ");
			if (gs > bs) System.out.println("GOOD");
			else if (gs < bs) System.out.println("A BADDY");
			else System.out.println("NEUTRAL");
		}
	}
}
				
