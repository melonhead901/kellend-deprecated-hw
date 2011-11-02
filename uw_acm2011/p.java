import java.io.*;
import java.util.*;

class p {
  public static void main(String[] args)  throws Exception {
    Scanner s = new Scanner(new File("p.in"));
    int N = s.nextInt();
    for (int i = 0; i < N ; i++) {
      int M = s.nextInt();
      System.out.println(Integer.toString(M, 8));
    }
  }
}
