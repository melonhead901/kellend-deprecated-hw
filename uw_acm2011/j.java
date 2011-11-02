import java.util.*;
import java.io.*;

public class j {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("j.in"));
		int numP = s.nextInt();
		while (numP != 0) {
			int numG = s.nextInt() * numP * (numP - 1) / 2;
			int[] wins = new int[numP];
			int[] plays = new int[numP];
			for (int i = 0; i < numG; i++) {
				int p1 = s.nextInt() - 1;
				String m1 = s.next();
				int p2 = s.nextInt() - 1;
				String m2 = s.next();
				plays[p1]++;
				plays[p2]++;
				if ((m1.equals("rock") && m2.equals("rock"))
						|| (m1.equals("scissors") && m2.equals("scissors"))
						|| (m1.equals("paper") && m2.equals("paper"))) {
					plays[p1]--;
					plays[p2]--;
				} else if ((m1.equals("rock") && m2.equals("scissors"))
						|| (m1.equals("scissors") && m2.equals("paper"))
						|| (m1.equals("paper") && m2.equals("rock"))) {
					wins[p1]++;
				} else if ((m1.equals("rock") && m2.equals("paper"))
						|| (m1.equals("scissors") && m2.equals("rock"))
						|| (m1.equals("paper") && m2.equals("scissors"))) {
					wins[p2]++;
				}
			}
			
			for (int i = 0; i < numP; i++) {
				if (plays[i] == 0) {
					System.out.println("-");
				} else {
					System.out.printf("%.03f\n", (float)wins[i] / plays[i]);
				}
			}

			numP = s.nextInt();
			if (numP != 0) {
				System.out.println();
			}
		}
	}
}
