// team17

import java.util.*;
import java.io.*;
public class d {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable{
		Scanner scan = new Scanner(new File("d.in"));
		scan.nextLine();
		
		while(scan.hasNextLine()) {
			boolean doable = false;
			int min = scan.nextInt();
			int max = scan.nextInt();
			int n = scan.nextInt();
			ArrayList<Integer> insects = new ArrayList<Integer>();
			while(n > 0) {
				insects.add(scan.nextInt());
				n -= 1;
			}
			Collections.sort(insects);
			for(int i = insects.size() - 1; i > 0; i--) {
				int insect = insects.get(i);
				if(insect < min) {
					min -= insect;
					max -= insect;
					continue;
				}
				if(insect <= max) {
					doable = true;
					break;
				}
			}
			if(doable)
				System.out.println("Sallow swallow swallows.");
			else
				System.out.println("Sallow swallow wallows in dust.");
		}
	}
}