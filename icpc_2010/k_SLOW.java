// team17

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class k_SLOW {

  public static class point implements Comparable {

    public double x;
    public double y;

    public point(double ix, double iy) {
      x = ix;
      y = iy;
    }

    @Override
    public int compareTo(Object o) {
      point other = (point)o;
      if (other.x == this.x && other.y == this.y) {
        return 0;
      } else if (other.x < this.x) {
        return -1;
      } else {
        return 1;
      }
    }

  }

  /**
   * @param args
   */
  public static void main(String[] args) throws Throwable {
    Scanner scan = new Scanner(new File("k.in"));

    int boards = scan.nextInt();
    scan.nextLine();

    for (int runs = 0; runs < boards; runs++) {
      int w = scan.nextInt();
      int h = scan.nextInt();
      scan.nextLine();

      Set<point> mines = new HashSet<point>();
      Set<point> zombies = new HashSet<point>();

      for (int heights = 0; heights < h; heights++) {
        String line = scan.nextLine();
        for (int index = 0; index < line.length(); index++) {
          if (line.substring(index, index + 1).equals("M")) {
            mines.add(new point(heights, index));
          } else if (line.substring(index, index + 1).equals("Z")) {
            zombies.add(new point(heights, index));
          }
        }
      }

      double maxOveral = Double.MIN_VALUE;

      for (point z: zombies) {
        double closest = Double.MAX_VALUE;
        for (point m : mines) {
          double dist = Math.sqrt((m.x - z.x)*(m.x - z.x)+(m.y - z.y)*(m.y - z.y));
          if (dist < closest) {
            closest = dist;
          }
        }
        if (closest > maxOveral) {
          maxOveral = closest;
        }
      }

      System.out.println(maxOveral);


      /*double[] distances = new double[zombies.size()];
			int dc = 0;
			for (point z : zombies) {
				double closest = Double.MAX_VALUE;
				for (point m : mines) {
					double dist = Math.sqrt((m.x - z.x)*(m.x - z.x)+(m.y - z.y)*(m.y - z.y));
					if (dist < closest) {
						closest = dist;
					}
				}
				distances[dc] = closest;
				dc++;
			}

			double max = Double.MIN_NORMAL;
			for (double d : distances) {
				if (d > max) {
					max = d;
				}
			}
			System.out.println(max);*/
    }
  }

}
