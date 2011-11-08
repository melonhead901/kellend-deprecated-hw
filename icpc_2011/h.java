import java.util.*;
import java.io.*;


public class h {

	static double a, b, c, d, t, m;
	/**
	 * @param args
	 */	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		//Scanner scanner = new Scanner( new File("h.in"));
		while (scanner.hasNext()) {
			processCase(scanner);
		}		
	}

	private static void processCase(Scanner scanner) {
		a = scanner.nextDouble();
		b = scanner.nextDouble();
		c = scanner.nextDouble();
		d = scanner.nextDouble();
		m = scanner.nextDouble();
		t = scanner.nextDouble();
		double v = 0;
		double result = eval(0);
		boolean growing = true;
		double growth = 1;
		while (Math.abs(result - t) > 0.000001) {
			if (t > result) {
				// Too small
				v += growth;
				if (growing) {
					growth *= 2;
				}
			} else {
				// Too large
				v -= growth;
				if (growing) {
					growing = false;
				}
				growth /= 2;
			}
			//System.out.println(v + " " + result + " should be " + t);
			result = eval(v);
		}
		
		while (result > t) {
			v -= .00001;
			result = eval(v);
		}
		
		v *= 100;
		v = Math.floor(v);
		v /= 100;
		
		System.out.printf("%.02f\n", v);
	}
	
	public static double eval(double v) {
		return m * (a*v*v*v + b*v*v + v*c + d);
	}

}
