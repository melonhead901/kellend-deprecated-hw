//team17

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class f {

  /**
   * @param args
   */
  public static void main(String[] args) throws Throwable {
    Scanner scan;
    if (System.getenv("KD_READ_STDIN") != null) {
      scan = new Scanner(new File("f.in"));
    } else {
      scan = new Scanner(System.in);
    }

    while (scan.hasNext()) {
      long n = scan.nextLong();
      if (n == 0) {
        break;
      }
      System.out.print(n + ": ");
      if (isVamp(n)) {
        System.out.println("yes");
      } else {
        System.out.println("no");
      }
    }

  }

  private static boolean isVamp(long n) {
    if (String.valueOf(n).length() % 2 != 0) {
      return false;
    }

    String str = String.valueOf(n);
    int minLength = str.length() / 2;
    long min = (long) Math.pow(10, minLength - 1);

    for (long i = (long) Math.sqrt(n); i > min; i--) {
      if (n % i == 0) {
        if (comp(i, n / i, n)) {
          return true;
        }
      }
    }

    return false;
  }

  private static boolean comp(long i, long n2, long n3) {
    String s1 = String.valueOf(i);
    String s2 = String.valueOf(n2);
    String s3 = String.valueOf(n3);

    if (s1.length() + s2.length() != s3.length()) {
      return false;
    }

    HashMap<Character, Integer> hash1 = buildHash(s1 + s2);
    HashMap<Character, Integer> hash2 = buildHash(s3);

    return compHash(hash1, hash2);
  }

  private static boolean compHash(HashMap<Character, Integer> hash1,
      HashMap<Character, Integer> hash2) {
    for (char c : hash1.keySet()) {
      if (!hash2.containsKey(c)) {
        return false;
      } else if (hash1.get(c) != hash2.get(c)) {
        return false;
      }
    }
    return true;
  }

  private static HashMap<Character, Integer> buildHash(String string) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for (int i = 0; i < string.length(); i++) {
      if (map.containsKey(string.charAt(i))) {
        map.put(string.charAt(i), map.get(string.charAt(i)) + 1);
      } else {
        map.put(string.charAt(i), 1);
      }
    }
    return map;
  }

}
