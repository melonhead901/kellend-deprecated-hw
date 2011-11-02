import java.io.*;
import java.util.*;

public class h {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(new File("h.in"));
		int n = input.nextInt();
		for (int i = 0; i < n; i++) {
			int a = input.nextInt();
			int b = input.nextInt();
			int sum = a + b;
			if (sum % 2 == 1) {
				System.out.println("impossible");
			} else {
				int x = (int) ((a + b) / 2.0);
				int y = a - x;
				if (y < 0) {
					System.out.println("impossible");
				} else {
					System.out.println(x + " " + y);
				}
			}

		}

	}
}
