//team17

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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

  private static boolean eval(List<Long> digits, String aSoFar, String bSoFar, long target) {
    if (digits.size() == 0) {
      // System.out.println(aSoFar + " " + bSoFar);
      return Long.valueOf(aSoFar) * Long.valueOf(bSoFar) == target;
    }
    Stack<Object[]> possibilites = new Stack<Object[]>();
    for (int i = 0; i < digits.size(); i++) {
      for (int j = 0; j < digits.size(); j++) {
        if (i == j) {
          continue;
        }
        Long iDigit = digits.get(i);
        Long jDigit = digits.get(j);
        String newA = String.valueOf(iDigit) + aSoFar;
        String newB = String.valueOf(jDigit) + bSoFar;
        if (newA.startsWith("00") || newB.startsWith("00")) {
          continue;
        }
        if (success(newA, newB, target)) {
          possibilites.push(new Object[] { i, j, newA, newB });
        }
      }
    }
    // System.out.println(possibilites.size());
    while (!possibilites.empty()) {
      Object[] obj = possibilites.pop();
      if (processPossibility(digits, target, Integer.valueOf(obj[0].toString()),
          Integer.valueOf(obj[1].toString()), (obj[2].toString()), (obj[3].toString()))) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param digits
   * @param target
   * @param i
   * @param j
   * @param newA
   * @param newB
   */
  private static boolean processPossibility(List<Long> digits, long target, int i, int j,
      String newA,
      String newB) {
    List<Long> subList = new ArrayList<Long>();
    subList.addAll(digits);
    subList.remove(Math.max(i, j));
    subList.remove(Math.min(i, j));
    if (eval(subList, newA, newB, target)) {
      return true;
    }
    return false;
  }

  private static boolean success(String a, String b, long target) {
    long product = Long.valueOf(a) * Long.valueOf(b);
    int length = Math.min(String.valueOf(product).length(), a.length());
    return ((target - Long.valueOf(a) * Long.valueOf(b)) % Math.pow(10, length)) == 0;

    // String newResult = String.valueOf(Long.valueOf(a) * Long.valueOf(b));
    // if (newResult.length() == 1) {
    // return String.valueOf(target).endsWith(newResult);
    // }
    //
    // if (newResult.length() == 2) {
    // return String.valueOf(target).endsWith(newResult.charAt(newResult.length()-1) + "");
    // }
    //
    // else {
    // if (newResult.length() - a.length() == -1) {
    // return false;
    // }
    // return String.valueOf(target).endsWith(newResult.substring(newResult.length() - 1 -
    // a.length()));
    // }
  }

  private static boolean isVamp(long n) {
    if (String.valueOf(n).length() % 2 != 0) {
      return false;
    }
    List<Long> digits = new ArrayList<Long>();
    for (Character c : String.valueOf(n).toCharArray()) {
      digits.add(Long.valueOf("0" + c));
    }
    return eval(digits, "", "", n);
  }

  // private static boolean comp(long i, long n2, long n3) {
  // String s1 = String.valueOf(i);
  // String s2 = String.valueOf(n2);
  // String s3 = String.valueOf(n3);
  //
  // if (s1.length() + s2.length() != s3.length()) {
  // return false;
  // }
  //
  // HashMap<Character, Integer> hash1 = buildHash(s1 + s2);
  // HashMap<Character, Integer> hash2 = buildHash(s3);
  //
  // return compHash(hash1, hash2);
  // }
  //
  // private static boolean compHash(HashMap<Character, Integer> hash1,
  // HashMap<Character, Integer> hash2) {
  // for (char c : hash1.keySet()) {
  // if (!hash2.containsKey(c)) {
  // return false;
  // } else if (hash1.get(c) != hash2.get(c)) {
  // return false;
  // }
  // }
  // return true;
  // }
  //
  // private static HashMap<Character, Integer> buildHash(String string) {
  // HashMap<Character, Integer> map = new HashMap<Character, Integer>();
  // for (int i = 0; i < string.length(); i++) {
  // if (map.containsKey(string.charAt(i))) {
  // map.put(string.charAt(i), map.get(string.charAt(i)) + 1);
  // } else {
  // map.put(string.charAt(i), 1);
  // }
  // }
  // return map;
  // }
}
