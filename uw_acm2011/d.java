import java.io.*;
import java.util.*;
import java.math.*;

class d {
  public static void main(String[] args)  throws Exception {
    Scanner s = new Scanner(new File("d.in"));
    int C = s.nextInt();
    for (int i = 0; i < C ; i++) {
      int N = s.nextInt();
      double avg = 0.0;
      List<Integer> grades = new ArrayList<Integer>();
      for (int k = 0; k < N ; k++) {
        int kk =s.nextInt();
        grades.add(kk);
        avg += kk;
      }
      avg /= N;
      double above = 0.0;
      for (int k = 0; k < N ; k++) {
        if (grades.get(k) > avg) above += 1.0;
      }
      above = above * 100.0 / N;
      System.out.printf("%.03f%%\n", above);

    }

  }
}
