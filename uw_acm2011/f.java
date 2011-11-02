import java.io.*;
import java.util.*;
import java.math.*;

class f {
  public static void main(String[] args)  throws Exception {
    Scanner s = new Scanner(new File("f.in"));
    int N = s.nextInt();
    String blank = s.nextLine();

    for (int gameno = 0; gameno < N; gameno++) {
      List<Set<Integer>> runs = new ArrayList<Set<Integer>>(12);
      Map<Integer, Set<Integer>> nums = new HashMap<Integer, Set<Integer>>();

      for (int j = 0; j < 12; j++) {
        runs.add(new HashSet<Integer>());
      }

      runs.get(2).add(-1);
      runs.get(7).add(-1);
      runs.get(10).add(-1);
      runs.get(11).add(-1);

      for (int j = 1; j < 76; j++)
        nums.put(j, new HashSet<Integer>());
      nums.put(-1, new HashSet<Integer>());

      String[][] lines = new String[5][];
      lines[0] = s.nextLine().split(" ");
      lines[1] = s.nextLine().split(" ");
      lines[2] = s.nextLine().split(" ");
      lines[3] = s.nextLine().split(" ");
      lines[4] = s.nextLine().split(" ");

      String[] midline = new String[5];
      midline[0] = lines[2][0];
      midline[1] = lines[2][1];
      midline[2] = "-1";
      midline[3] = lines[2][2];
      midline[4] = lines[2][3];

      lines[2] = midline;

      for (int col = 0; col < 5; col++) {
        for (int row = 0; row < 5; row++) {
          int aaaa = Integer.parseInt(lines[row][col]);
          nums.get(aaaa).add(col);
          nums.get(aaaa).add(row + 5);
        }
      }

      nums.get(Integer.parseInt(lines[0][0])).add(11);
      nums.get(Integer.parseInt(lines[1][1])).add(11);
      nums.get(Integer.parseInt(lines[2][2])).add(11);
      nums.get(Integer.parseInt(lines[3][3])).add(11);
      nums.get(Integer.parseInt(lines[4][4])).add(11);

      nums.get(Integer.parseInt(lines[0][4])).add(10);
      nums.get(Integer.parseInt(lines[1][3])).add(10);
      nums.get(Integer.parseInt(lines[2][2])).add(10);
      nums.get(Integer.parseInt(lines[3][1])).add(10);
      nums.get(Integer.parseInt(lines[4][0])).add(10);

      int nseen = 0;
      int winpoint = -1;
      while (nseen < 75) {
        String[] numbers = s.nextLine().split(" ");
        for (String nnn : numbers) {
          int aa = Integer.parseInt(nnn);
          nseen++;
          if (winpoint != -1) continue;
          for (Integer runset : nums.get(aa)) {
            runs.get(runset).add(aa);
            if (runs.get(runset).size() == 5) winpoint = nseen;
          }
        }
      }

      System.out.printf("BINGO after %d numbers announced\n", winpoint);

    }
  }
}
