import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
public class a {

  /**
   * @param args
   */
  public static void main(String[] args) throws Throwable{
    Scanner scan;

    if (System.getenv("KD_READ_STDIN") != null) {
      scan = new Scanner(new File("a.in"));
    } else {
      scan = new Scanner(System.in);
    }
    HashMap<Integer, String> map = new HashMap<Integer, String>();

    int numRules = scan.nextInt();
    scan.nextLine();
    int i = 1;
    while(i<=numRules) {
      String line = scan.nextLine();
      map.put(i, line);
      i++;
    }
    scan.nextLine();
    while(scan.hasNext()) {
      int num = scan.nextInt();
      System.out.print("Rule " + num + ": ");
      if(map.containsKey(num)) {
        System.out.println(map.get(num));
      } else {
        System.out.println("No such rule");
      }
    }
  }
}
