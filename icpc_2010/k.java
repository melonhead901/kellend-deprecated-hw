// team17

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class k {

  public static class Node {
    public double x,y;
    public Node L,R;
    public Node(double ix, double iy) {
      x = ix; y = iy; L = null; R = null;
    }
  }

  public static class point{
    public double x, y;
    public point(double ix, double iy) {
      x = ix;	y = iy;
    }
  }

  public static double closest = Double.MAX_VALUE;

  public static void main(String[] args) throws Throwable {
    Scanner scan = new Scanner(new File("k.in"));

    int boards = scan.nextInt();
    scan.nextLine();

    for (int runs = 0; runs < boards; runs++) {
      int w = scan.nextInt();
      int h = scan.nextInt();
      scan.nextLine();

      Node m_tree = null;
      Set<point> zombies = new HashSet<point>();

      for (int heights = 0; heights < h; heights++) {
        String line = scan.nextLine();
        for (int index = 0; index < line.length(); index++) {
          if (line.substring(index, index + 1).equals("M")) {
            m_tree = insert(heights, index, m_tree);
          } else if (line.substring(index, index + 1).equals("Z")) {
            zombies.add(new point(heights, index));
          }
        }
      }

      double maxOverall = Double.MIN_VALUE;

      for (point z: zombies) {
        closest = Double.MAX_VALUE;
        get_closest(z.x,z.y,m_tree);
        if (closest > maxOverall) {
          maxOverall = closest;
        }
      }

      System.out.println(maxOverall);

    }
  }

  public static Node insert(double x, double y, Node root) {
    return insert(x,y,root,true);
  }

  public static Node insert(double x, double y, Node t, boolean x_lvl) {
    if (t == null) {
      return new Node(x,y);
    }

    if (x_lvl) {
      if (x < t.x) {
        t.L = insert(x,y,t.L,!x_lvl);
      } else {
        t.R = insert(x,y,t.R,!x_lvl);
      }
    } else {
      if (y < t.y) {
        t.L = insert(x,y,t.L,!x_lvl);
      } else {
        t.R = insert(x,y,t.R,!x_lvl);
      }
    }

    return t;
  }

  public static void get_closest(double x, double y, Node root) {
    get_closest(x,y,root,true);
  }

  public static void get_closest(double x, double y, Node t, boolean x_lvl) {
    if (t == null) {
      return;
    }
    closest = Math.min(closest, Math.sqrt((t.x - x)*(t.x - x) + (t.y - y)*(t.y - y)));
    Node[] children = new Node[2];
    children[0] = t.L;
    children[1] = t.R;
    for (Node c : children){
      if (c != null && Math.abs(c.x - x) < closest && Math.abs(c.y - y) < closest) {
        get_closest(x,y,c,!x_lvl);
      }
      /*if (c != null && !x_lvl && Math.abs(c.x - x) < closest) {
				get_closest(x,y,c,!x_lvl);
			} else if (c != null && x_lvl && Math.abs(c.y - y) < closest) {
				get_closest(x,y,c,!x_lvl);
			}*/
    }
  }
}
