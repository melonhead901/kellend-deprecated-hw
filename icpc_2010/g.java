// team17

import java.io.File;
import java.util.Scanner;
public class g {

  /**
   * @param args
   */
  public static void main(String[] args) throws Throwable{
    Scanner scan;
    if (System.getenv("KD_READ_STDIN") != null) {
      scan = new Scanner(new File("g.in"));
    } else {
      scan = new Scanner(System.in);
    }

    int a = scan.nextInt();
    int m = scan.nextInt();
    int s = scan.nextInt();
    scan.nextLine();
    while(scan.hasNextLine()) {
      String line = scan.nextLine();
      for(int i = 0; i < line.length(); i++) {
        int c = line.charAt(i);
        if(!(c >= 32 && c <= 126)) {
          System.out.print(c);
          continue;
        }
        c -= 32;
        double val = (s % m) / ((double) m);
        s = (a * s + 1) % m;
        int t = (int) Math.ceil(95 - (val * 95));
        c -= t;
        c = c % 95;
        if(c < 0) {
          c = 95 + c;
        }
        c += 32;
        System.out.print((char) c);
      }
      System.out.println();
    }
  }
}