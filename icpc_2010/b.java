import java.util.*;
import java.io.*;

public class b {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("b.in"));
		
		int n = scan.nextInt();
		while (n != 0) {
			double x1 = scan.nextDouble();
			double y1 = scan.nextDouble();
			double x2 = scan.nextDouble();
			double y2 = scan.nextDouble();
			double x3 = scan.nextDouble();
			double y3 = scan.nextDouble();
			
			double xsum = 0;
			double ysum = 0;
			for (int i = 0; i < n; i++) {
				xsum += scan.nextDouble();
				ysum += scan.nextDouble();
			}
			
			System.out.println((xsum / n) + " " + (ysum / n));
			
			n = scan.nextInt();
		}
	}
}
