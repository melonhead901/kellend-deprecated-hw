// team17


import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class e {	
	static HashMap<Long, Long> fibo;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable{
		
		fibo = new HashMap<Long, Long>();
		Scanner scan = new Scanner(new File("e.in"));

		fibo.put((long)0, (long)0);
		long a = 0;
		long b = 1;
		for(long i = 2; b < Math.pow(2, 62)-1; i++) {
			fibo.put(a+b, i);
			long c = a;
			a = b;
			b = a+c;
		}
		
		while(scan.hasNext()) {
			String str = scan.nextLine();
			str = str.replaceAll("x", "0");
			Scanner stringScanner = new Scanner(str);
			long lo = stringScanner.nextLong(16);
			long hi = stringScanner.nextLong(16);
			
			if (lo >= hi) {
				return;
			}
						
			System.out.println(String.format("Range %d to %d:", lo, hi));
			boolean hasAny = false;
			for(long i = lo; i <= hi; i++) {
				if(i == 1) {
					System.out.println("Fib(1) = 1, lg is 0.000000");
					System.out.println("No prime factors");
				}
				if(fibo.containsKey(i)) {
					hasAny = true;
					System.out.print(String.format("Fib(%d) = %d, lg", fibo.get(i), i));
					if(i == 0) {
						System.out.println(" does not exist");
					} else {
						double log = (Math.log(i) / Math.log(2));
						System.out.println(String.format(" is %.7g", log));
					}
					if(i > 1) {
						StringBuilder builder = new StringBuilder();
						builder.append("Prime factors:");
						List<Long> factors = primeFactors(i);
						Collections.sort(factors);
						for(long l : factors) {
							builder.append(" ");
							builder.append(l);
						}
						System.out.println(builder.toString());
					} else {
						System.out.println("No prime factors");
					}
					
				}
			}
			if (!hasAny) {
				System.out.println("No Fibonacci numbers in the range");
			}
			System.out.println();
		}
		
	}
	
	public static List<Long> primeFactors(long n) {
		List<Long> factors = new ArrayList<Long>();
		long rem = n; //new BigInteger(String.valueOf(n));
		while(!isPrime(rem)) {
			for(long i = 2; i < rem; i++) {
				if(rem % i == 0) {
					rem/= i;
					factors.add(i);
				}
			}
		}
		factors.add(rem);
		return factors;
	}
	
	public static boolean isPrime(long n) {
		if( n <= 2)
			return true;
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) 
				return false;
		}
		
		return true;
	}
}
