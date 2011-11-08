import java.io.*;
import java.util.*;

class st implements Comparable<st> {
    int x, y, dx, dy;
    long n;

    st(int x, int y, int dx, int dy, long n) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.n = n;
    }

    st accel(int ddx, int ddy, int[][] fws, int maxx, int maxy) {
        int newdx = dx + ddx;
        int newdy = dy + ddy;

        int destx = x + newdx;
        int desty = y + newdy;

        if (hopValid(destx, desty, x, y, fws, maxx, maxy))
            return new st(destx, desty, newdx, newdy, n+1);
        return null;
    }

    st maint(int[][] fws, int maxx, int maxy) {
        int destx = x + dx;
        int desty = y + dy;
        if (dx == 0 && dy == 0) return null;
        if (hopValid(destx, desty, x, y, fws, maxx, maxy))
            return new st(destx, desty, dx, dy, n+1);
        return null;
    }

    public int compareTo(st o) {
            st oth = (st)o;
            if (n < oth.n) return -1;
            else if (oth.n < n) return 1;
            return 0;
    }
    
    public boolean equals(Object oth) {
        try {
            st o = (st)oth;
           return (x == o.x && y == o.y && dx == o.dx && dy == o.dy);
        } catch (Exception e) { return false; }
    }

    public int hashCode() {
        return (x * 7) + (y * 13) + (dx * 17) + (dy * 23);
    }
   
    static boolean hopValid(int destx, int desty, int x, int y, int[][] fws, int maxx, int maxy) {
        if (destx < 0 || destx > maxx) return false;
        if (desty < 0 || desty > maxy) return false;
        for (int[] fw : fws) {
             if (cross(destx, desty, x, y, fw)) return false;
             if (destx == fw[0] && desty == fw[1]) return false;
             if (destx == fw[2] && desty == fw[3]) return false;
        }
        return true;
    }

    static boolean cross(int x1, int y1, int x0, int y0, int[] fw) {
        if (x1 == x0 && y1 == y0) return false;
        if (x0 == x1 && fw[0] == fw[2]) return false; // parallel vertical lines
        if (x0 == x1) { // hop is vert
            double slope = ((double)fw[3] - fw[1]) / (fw[2] - fw[0]);
            double newy = slope * (x0 - fw[0]) + fw[1];

            int ymin = y0;
            int ymax = y1;
            if (ymin > ymax) { ymax = y0; ymin = y1; }

            if (newy <= ymax && newy >= ymin && fw[0] <= x0 && fw[2] >= x0) return true;
            return false;
        }
        // x0 < x1
        if (x0 > x1) return cross(x0, y0, x1, y1, fw);
        if (fw[0] == fw[2]) { // wall is vert
            double slope = ((double)y1 - y0) / (x1 - x0);
            double newy = slope * (fw[0] - x0) + y0;

            int ymin = fw[0];
            int ymax = fw[1];
            if (ymin > ymax) { ymax = fw[0]; ymin = fw[1]; }
            if (newy <= ymax && newy >= ymin) return true;
            return false;
        }

        // at this point, neither is vertical and x0 < x1; fw[0] < fw[2].
        double s0 = ((double)y1 - y0) / (x1 - x0);
        double s1 = ((double)fw[3] - fw[1]) / (fw[2] - fw[0]);

        if (Math.abs(s0 - s1) < 0.000000001) return false; // parallel

        double b0 = y0 - s0 * x0;
        double b1 = fw[1] - s1 * fw[0];

        double nnnx = (b1 - b0) / (s0 - s1);

        if (nnnx <= x1 && nnnx >= x0 &&
            nnnx <= fw[2] && nnnx >= fw[0]) return true;
        return false;
    }
}


class e {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
			String line = s.nextLine();
            System.out.println(game(new Scanner(line)));
		}
	}

    static long game(Scanner line) {
        int maxx = line.nextInt() - 1;
        int maxy = line.nextInt() - 1;
        int xs = line.nextInt();
        int ys = line.nextInt();
        int xf = line.nextInt();
        int yf = line.nextInt();
        int nfw = line.nextInt();

        int[][] fws = new int[nfw][];
        for (int i = 0; i < fws.length; i++) {
            int x0 = line.nextInt();
            int y0 = line.nextInt();
            int x1 = line.nextInt();
            int y1 = line.nextInt();

            if (x0 > x1) {
                fws[i] = new int[] { x1, y1, x0, y0 };
            } else {
                fws[i] = new int[] { x0, y0, x1, y1 };
            }
        }

        PriorityQueue<st> q = new PriorityQueue<st>();
        q.add(new st(xs, ys, 0, 0, 0));
        
        HashSet<st> visited = new HashSet<st>();

        while (true) {
            st hd = q.poll();
            if (hd.x == xf && hd.y == yf && hd.dx == 0 && hd.dy == 0) return hd.n; // stopped on finish?
            
            visited.add(hd);

            st n1 = hd.accel(1, 0, fws, maxx, maxy),
               n2 = hd.accel(-1, 0, fws, maxx, maxy),
               n3 = hd.accel(0, 1, fws, maxx, maxy),
               n4 = hd.accel(0, -1, fws, maxx, maxy),
               n5 = hd.maint(fws, maxx, maxy);

            if (n1 != null && !visited.contains(n1)) q.add(n1);
            if (n2 != null && !visited.contains(n2)) q.add(n2);
            if (n3 != null && !visited.contains(n3)) q.add(n3);
            if (n4 != null && !visited.contains(n4)) q.add(n4);
            if (n5 != null && !visited.contains(n5)) q.add(n5);
        }
    } // game()
}
				
