// team17

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * e
 * 
 * @author Kellen
 * 
 */
public class e {
  static HashMap<Integer, Long> fibo;

  static String[] factors = { "0", "1", "1",
    " 2",
    " 3",
    " 5",
    " 2 2 2",
    " 13",
    " 3 7",
    " 2 17",
    " 5 11",
    " 89",
    " 2 2 2 2 3 3",
    " 233",
    " 13 29",
    " 2 5 61",
    " 3 7 47",
    " 1597",
    " 2 2 2 17 19",
    " 37 113",
    " 3 5 11 41",
    " 2 13 421",
    " 89 199",
    " 28657",
    " 2 2 2 2 2 3 3 7 23",
    " 5 5 3001",
    " 233 521",
    " 2 17 53 109",
    " 3 13 29 281",
    " 514229",
    " 2 2 2 5 11 31 61",
    " 557 2417",
    " 3 7 47 2207",
    " 2 89 19801",
    " 1597 3571",
    " 5 13 141961",
    " 2 2 2 2 3 3 3 17 19 107",
    " 73 149 2221",
    " 37 113 9349",
    " 2 233 135721",
    " 3 5 7 11 41 2161",
    " 2789 59369",
    " 2 2 2 13 29 211 421",
    " 433494437",
    " 3 43 89 199 307",
    " 2 5 17 61 109441",
    " 139 461 28657",
    " 2971215073",
    " 2 2 2 2 2 2 3 3 7 23 47 1103",
    " 13 97 6168709",
    " 5 5 11 101 151 3001",
    " 2 1597 6376021",
    " 3 233 521 90481",
    " 953 55945741",
    " 2 2 2 17 19 53 109 5779",
    " 5 89 661 474541",
    " 3 7 7 13 29 281 14503",
    " 2 37 113 797 54833",
    " 59 19489 514229",
    " 353 2710260697",
    " 2 2 2 2 3 3 5 11 31 41 61 2521",
    " 4513 555003497",
    " 557 2417 3010349",
    " 2 13 17 421 35239681",
    " 3 7 47 1087 2207 4481",
    " 5 233 14736206161",
    " 2 2 2 89 199 9901 19801",
    " 269 116849 1429913",
    " 3 67 1597 3571 63443",
    " 2 137 829 18077 28657",
    " 5 11 13 29 71 911 141961",
    " 6673 46165371073",
    " 2 2 2 2 2 3 3 3 7 17 19 23 107 103681",
    " 9375829 86020717",
    " 73 149 2221 54018521",
    " 2 5 5 61 3001 230686501",
    " 3 37 113 9349 29134601",
    " 13 89 988681 4832521",
    " 2 2 2 79 233 521 859 135721",
    " 157 92180471494753",
    " 3 5 7 11 41 47 1601 2161 3041",
    " 2 17 53 109 2269 4373 19441",
    " 2789 59369 370248451",
    " 99194853094755497",
    " 2 2 2 2 3 3 13 29 83 211 281 421 1427",
    " 5 1597 9521 3415914041",
    " 6709 144481 433494437",
    " 2 173 514229 3821263937",
    " 3 7 43 89 199 263 307 881 967",
    " 1069 1665088321800481",
    " 2 2 2 5 11 17 19 31 61 181 541 109441",
    " 13 13 233 741469 159607993",
  " 3 139 461 4969 28657 275449" };

  /**
   * @param args
   */
  public static void main(String[] args) throws Throwable {
    fibo = new HashMap<Integer, Long>();
    Scanner scan;

    if (System.getenv("KD_READ_STDIN") != null) {
      scan = new Scanner(new File("e.in"));
    } else {
      scan = new Scanner(System.in);
    }

    fibo.put(0, (long) 0);
    long a = 0;
    long b = 1;
    int i = 1;
    for (i = 1; b < Math.pow(2, 62) * 1.5; i++) {
      fibo.put(i, b);
      long c = a;
      a = b;
      b = a + c;
      // System.out.print(b + ": ");
      // printPrimeFactors(b);
    }
    // System.exit(0);
    fibo.put(i, b);

    while (scan.hasNext()) {
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
      for (i = 0; fibo.get(i) != null && fibo.get(i) <= hi; i++) {
        if (fibo.get(i) < lo) {
          continue;
        }
        if (i == 0) {
          System.out.println("Fib(0) = 0, lg does not exist");
          System.out.println("No prime factors");
          continue;
        }
        if (i == 1 || i == 2) {
          System.out.println("Fib(" + i + ") = " + 1 + ", lg is 0.000000");
          System.out.println("No prime factors");
          continue;
        }

        hasAny = true;
        System.out.print(String.format("Fib(%d) = %d, lg", i, fibo.get(i)));
        System.out.printf(" is %.6f\n", (Math.log(fibo.get(i)) / Math.log(2)));

        if (i > 1) {
          StringBuilder builder = new StringBuilder();
          builder.append("Prime factors:");
          builder.append(factors[i]);
          System.out.println(builder.toString());
        } else {
          System.out.println("No prime factors");
        }
      }
      if (!hasAny) {
        System.out.println("No Fibonacci numbers in the range");
      }
      System.out.println();
    }
  }

  /**
   * @param i
   * @return
   */
  private static void printPrimeFactors(long i) {
    // System.out.printf("\"");
    List<Long> factors = primeFactors(i);
    for (long l : factors) {
      System.out.printf(" %s", l);
    }
    System.out.printf("\n");

    // System.out.printf("\",\n");
  }

  public static List<Long> primeFactors(long n) {
    List<Long> factors = new ArrayList<Long>();
    if (n <= 1) {
      return factors;
    }
    BigInteger rem = new BigInteger(String.valueOf(n));
    BigInteger guess = new BigInteger("2");
    while (!rem.isProbablePrime(100)) {
      if (rem.mod(guess) == BigInteger.ZERO) {
        factors.add(guess.longValue());
        rem = rem.divide(guess);
      } else {
        guess = guess.nextProbablePrime();
      }
    }
    factors.add(rem.longValue());
    return factors;
  }

}
