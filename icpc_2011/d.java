import java.io.*;
import java.util.*;

class d {
	static class Triangle {
		long x1;
		long y1;
		long x2;
		long y2;
		long x3;
		long y3;

		Triangle(long a, long b, long c, long d, long e, long f) {
			x1 = a;
			y1 = b;
			x2 = c;
			y2 = d;
			x3 = e;
			y3 = f;
		}

		@Override
		public String toString() {
			return "(" + x1 + "," + y1 + ")" + " (" + x2 + "," + y2 + ")"
					+ " (" + x3 + "," + y3 + ")";
		}

		public boolean isAbove(Triangle o) {
			boolean a = h(o, x1, x2, y3, y2, y1, x3);
			boolean b = h(o, x2, x3, y1, y3, y2, x1);
			boolean c = h(o, x3, x1, y2, y1, y3, x2);
			return a || b || c;
		}

		public boolean h(Triangle o, long x1, long x2, long y3, long y2, long y1, long x3) {
			if (x2 == x1) { return false; }
			boolean thirdAbove = y3 > (x3 - x1) * (y2 - y1) / (x2 - x1) + y1;
			if (x3 == x1) { thirdAbove = y3 > y1; }
			if (x3 == x2) { thirdAbove = y3 > y2; }
			boolean a = x1 == o.x2 && x2 == o.x1 && y1 == o.y2 && y2 == o.y1 && thirdAbove;
			boolean b = x1 == o.x3 && x2 == o.x2 && y1 == o.y3 && y2 == o.y2 && thirdAbove;
			boolean c = x1 == o.x1 && x2 == o.x3 && y1 == o.y1 && y2 == o.y3 && thirdAbove;
			return a || b || c;
		}
	}

	static class Node {
		long num;
		List<Node> children;
		long parents;

		Node(long n) {
			num = n;
			children = new ArrayList<Node>();
			parents = 0;
		}

		@Override
		public String toString() {
			return num + ":" + parents;
		}
	}

	public static void main(String[] args) throws Exception {
		//Scanner s = new Scanner(new File("d.in"));
		Scanner s = new Scanner(System.in);
		long trials = s.nextLong();
		for (long trial = 0; trial < trials; trial++) {
			long triangles = s.nextLong();
			List<Node> nodes = new ArrayList<Node>((int) triangles);
			for (int i = 0; i < triangles; i++) {
				nodes.add(i, new Node(i));
			}

			List<Triangle> tris = new ArrayList<Triangle>((int) triangles);
			for (long triangle = 0; triangle < triangles; triangle++) {
				long x1 = s.nextLong();
				long y1 = s.nextLong();
				long x2 = s.nextLong();
				long y2 = s.nextLong();
				long x3 = s.nextLong();
				long y3 = s.nextLong();
				tris.add((int) triangle, new Triangle(x1, y1, x2, y2, x3, y3));
			}

			for (int i = 0; i < triangles; i++) {
				for (int j = 0; j < triangles; j++) {
					if (i != j) {
						Triangle t1 = tris.get(i);
						Triangle t2 = tris.get(j);
						if (t1.isAbove(t2)) {
							//System.out.println((i + " above " + j));
							Node n1 = nodes.get(i);
							Node n2 = nodes.get(j);
							n2.children.add(n1);
							n1.parents++;
						}
					}
				}
			}

			List<Long> printList = new ArrayList<Long>((int) triangles);

			while (nodes.size() > 0) {
				for (int i = 0; i < nodes.size(); i++) {
					Node n = nodes.get(i);
					if (n.parents == 0) {
						for (Node c : n.children) {
							c.parents--;
						}
						printList.add(n.num);
						nodes.remove(i);
					}
				}
			}

			for (int i = 0; i < triangles - 1; i++) {
				System.out.print((printList.get(i) + 1) + " ");
			}

			System.out.println(printList.get((int) (triangles - 1)) + 1);
		}
	}
}
