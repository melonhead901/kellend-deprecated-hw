import java.util.*;
import java.io.*;

public class i {

	static enum Edge {
		a, b, c, d, e, f, g, h
	};

	static int caseNumber = 1;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		//Scanner scanner = new Scanner(new File("i.in"));
		while (scanner.hasNext()) {
			processCase(scanner);
			caseNumber++;
			System.out.println();
		}
	}

	static int row, col;
	static Board[][] boards;

	private static void processCase(Scanner scanner) {
		row = scanner.nextInt();
		col = scanner.nextInt();
		if (row == 0 && col == 0) {
			System.exit(0);
		}
		boards = new Board[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				boards[i][j] = new Board(new Coord(i, j));
			}
		}

		populateBoard(scanner);
		System.out.println("Board " + caseNumber + ":");

		scanner.nextLine();
		Scanner lineScanner = new Scanner(scanner.nextLine());
		while (lineScanner.hasNext()) {
			String nextMove = lineScanner.next();
			Board b = getBoard(Integer.parseInt(nextMove.substring(0, nextMove.length()-1)));
			Edge e = getEdge(nextMove.charAt(nextMove.length()-1));
			Position pos = new Position(b, e);
			start = nextMove;
			pos.edge = pos.board.connectedEdges.get(pos.edge);
			while (!isEdge(pos)) {
				pos = advance(pos);
				pos.edge = pos.board.connectedEdges.get(pos.edge);
			}
			System.out.println(start + " is connected to "
					+ convertCoordToNum(pos.board.position)
					+ pos.edge.toString().toUpperCase());
		}

		scanner.nextLine();
	}

	public static String convertCoordToNum(Coord c) {
		return String.valueOf(c.r * col + c.c + 1);
	}

	static String start;

	private static Position advance(Position pos) {
		switch (pos.edge) {
		case a:
			return new Position(up(pos.board), Edge.f);
		case b:
			return new Position(up(pos.board), Edge.e);
		case c:
			return new Position(right(pos.board), Edge.h);
		case d:
			return new Position(right(pos.board), Edge.g);
		case e:
			return new Position(down(pos.board), Edge.b);
		case f:
			return new Position(down(pos.board), Edge.a);
		case g:
			return new Position(left(pos.board), Edge.d);
		case h:
			return new Position(left(pos.board), Edge.c);
		}
		return null;
	}

	private static Board left(Board board) {
		return getBoard(new Coord(board.position.r, board.position.c - 1));
	}

	private static Board down(Board board) {
		return getBoard(new Coord(board.position.r + 1, board.position.c));
	}

	private static Board right(Board board) {
		return getBoard(new Coord(board.position.r, board.position.c + 1));
	}

	private static Board up(Board board) {
		return getBoard(new Coord(board.position.r - 1, board.position.c));
	}

	private static void populateBoard(Scanner scanner) {
		int boardNum = scanner.nextInt();
		while (boardNum != 0) {
			Board b = getBoard(convertNumToCoord(boardNum));
			Scanner lineScanner = new Scanner(scanner.nextLine());
			while (lineScanner.hasNext()) {
				String nextConnection = lineScanner.next();
				Edge start = getEdge(nextConnection.charAt(0));
				Edge end = getEdge(nextConnection.charAt(1));
				b.connectedEdges.put(start, end);
				b.connectedEdges.put(end, start);
			}
			boardNum = scanner.nextInt();
		}
	}

	private static Edge getEdge(char c) {
		return Edge.valueOf(String.valueOf(Character.toLowerCase(c)));
	}

	private static Board getBoard(int n) {
		Coord c = convertNumToCoord(n);
		return boards[c.r][c.c];
	}

	private static Board getBoard(Coord c) {
		return boards[c.r][c.c];
	}

	private static Coord convertNumToCoord(int n) {
		int r, c;
		r = (n - 1) / col;
		c = (n - 1) % col;
		return new Coord(r, c);
	}

	private static boolean isEdge(Position p) {
		boolean top = p.board.position.r == 0
				&& (p.edge == Edge.a || p.edge == Edge.b);
		boolean left = p.board.position.c == 0
				&& (p.edge == Edge.g || p.edge == Edge.h);
		boolean right = p.board.position.c == col - 1
				&& (p.edge == Edge.c || p.edge == Edge.d);
		boolean bottom = p.board.position.r == row - 1
				&& (p.edge == Edge.f || p.edge == Edge.e);
		return top || left || right || bottom;
	}

	private static class Position {
		Board board;
		Edge edge;

		public Position(Board b, Edge e) {
			this.board = b;
			this.edge = e;
		}

		public String toString() {
			return board.toString() + " " + edge.toString();
		}
	}

	private static class Board {
		Coord position;
		Map<Edge, Edge> connectedEdges;

		public Board(Coord c) {
			this.position = c;
			this.connectedEdges = new HashMap<i.Edge, i.Edge>();
		}

		public String toString() {
			return "Board at " + position.toString();

		}
	}

	private static class Coord {
		int r, c;

		public Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public String toString() {
			return this.r + "," + this.c;
		}
	}
}
