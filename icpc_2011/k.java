import java.io.*;
import java.util.*;

class k {
    static long[] pre = new long[1429];
    static long computed = 2;
    static long bil = 1000000000L;
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
        pre[(int)0] = 0;
        pre[(int)1] = 1;
        pre[(int)2] = 2;
        while (s.hasNext()) {
            long N = s.nextLong();
            System.out.println("XXX  " + N);
            long res = compute(N) % bil;
            System.out.println("XXX  " + res);
            System.out.printf("%09d\n", res);
        }
	}

    static long compute(long N) {
    	long d = (long) Math.floor(Math.log(N) / Math.log(2.0));
    	long f = (long) (Math.pow(2, d) -1);
    	long ans = ncr(N-f, (f+1));
    	return ans;
    }

    private static long ncr(long r, long n) {
		return factorial(n)/ (1*factorial(r));
    	
	}
    
    private static long factorial(long f) {
    	if (f == 1 || f == 0) {
    		return 1;
    	} else {
    		return f * factorial(f-1);
    	}
    }

	static long minh(long num) {
        return (long)Math.floor(Math.log(num) / Math.log(2.0));
    }

    static long times(long a, long b)  {
        long r = a * b;
        r = r % bil;
        return r;
    }
}
				
