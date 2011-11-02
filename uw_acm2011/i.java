import java.io.*;
import java.util.*;
import java.math.*;

class rat {
  int a, b;

  rat(rat c) {
    a = c.a;
    b = c.b;
  }

  rat() {
    a = 0;
    b = 1;
  }

  static int gcd(int a, int b) {
    while (b != 0) {
      int rem = a % b;
      a = b;
      b = rem;
    }
    return a;
  }

  void add(rat oth) {
    int d = b * oth.b;
    int n = a * oth.b + oth.a * b;
    int c = gcd(n, d);

    a = n / c;
    b = d / c;
  }

  rat(int k) {
    a = k;
    b = 1;
  }

  void sub(rat oth) {
    rat kkk = new rat();
    kkk.a = -oth.a;
    kkk.b = oth.b;
    
    add(kkk);
  }

  void times(rat oth) {
    a *= oth.a;
    b *= oth.b;

    int c = gcd(a, b);
    a /= c;
    b /= c;
  }

  void divide(rat oth) {
    rat kkk = new rat();
    kkk.a = oth.b;
    kkk.b = oth.a;

    times(kkk);
  }

  boolean iszero() {
    return a == 0;
  }

  rat neg() {
    rat kkk = new rat();
    kkk.a = -a;
    kkk.b = b;
    return kkk;
  }

  public String toString() {
    if (b < 0) { b *= -1; a *= -1; }
    if (b == 1) return "" + a;
    int c = gcd(a,b);
    a/=c;
    b/=c;
    if (b < 0) { b *= -1; a *= -1; }
    return "" + a + "/" + b;
  }
}

class i {
  static void printRow(rat[] row) {
    System.out.print("[");
    System.out.print(row[0].toString());
    System.out.print(" ");
    System.out.print(row[1].toString());
    System.out.print(" ");
    System.out.print(row[2].toString());
    System.out.print("]");
    System.out.println();
  }

  static void addrow(rat[] dst, rat[] src, rat konst) {
    for (int i = 0 ; i < dst.length; i++) {
      rat srccopy = new rat(src[i]);
      srccopy.times(konst);
      dst[i].add(srccopy);
    }
  }

  static boolean subtract(rat[] dst, rat[] src, int idx) {
    if (src[idx].iszero()) return false;
    rat konst = dst[idx].neg();
    konst.divide(src[idx]);
    addrow(dst, src, konst);
    return true;
  }

  static void giveup(rat[] r1, rat[] r2) {
    boolean f = false;

    if (r1[0].iszero() && r1[1].iszero() && !r1[2].iszero()) {
      System.out.println("don't know");
      System.out.println("don't know");
    }
    else if (r2[0].iszero() && r2[1].iszero() && !r2[2].iszero()) {
      System.out.println("don't know");
      System.out.println("don't know");
    }
    else if (r1[1].iszero() && !r1[0].iszero()) {
      r1[2].divide(r1[0]);
      System.out.println(r1[2].toString());
      System.out.println("don't know");
      f = true;
    }
    else if (r2[0].iszero() && !r2[1].iszero()) {
      r2[2].divide(r2[1]);
      System.out.println("don't know");
      System.out.println(r2[2].toString());
      f  =true;
    }
    else if (r2[1].iszero() && !r2[0].iszero()) {
      r2[2].divide(r2[0]);
      System.out.println(r2[2].toString());
      System.out.println("don't know");
      f = true;
    }
    else if (r1[0].iszero() && !r1[1].iszero()) {
      r1[2].divide(r1[1]);
      System.out.println("don't know");
      System.out.println(r1[2].toString());
      f  =true;
    }
    else {
      System.out.println("don't know");
      System.out.println("don't know");
    }
  }

  static rat[] parse(String eq) {
    rat[] res = new rat[3];
    res[0] = new rat(0); //x
    res[1] = new rat(0); //y
    res[2] = new rat(0); // k

    Scanner s = new Scanner(eq);
    boolean pastequals = false;
    boolean prevplus = true;

    while (s.hasNext()) {
      String tok = s.next();

      switch (tok.charAt(tok.length()-1)) {
        case '-':
          prevplus =false;
          break;
        case '+':
          prevplus = true;
          break;
        case '=':
          pastequals = true;
          prevplus = true;
          break;
        case 'x':
          int v1;
          if ("x".equals(tok)) v1 = 1;
          else if ("-x".equals(tok)) v1 = -1;
          else v1 = Integer.parseInt(tok.substring(0, tok.length()-1));
          if (!prevplus) v1 *= -1;
          if (pastequals) v1 *= -1;
          res[0].add(new rat(v1));
          break;
        case 'y':
          int v2;
          if ("y".equals(tok)) v2 = 1;
          else if ("-y".equals(tok)) v2 = -1;
          else v2 = Integer.parseInt(tok.substring(0, tok.length()-1));
          if (!prevplus) v2 *= -1;
          if (pastequals) v2 *= -1;
          res[1].add(new rat(v2));
          break;
        default:
          int v3 = Integer.parseInt(tok);
          if (pastequals) v3 *= -1;
          if (!prevplus) v3 *= -1;
          res[2].add(new rat(v3));
      }
    }

    res[2] = res[2].neg();

    return res;
  }

  public static void main(String[] args)  throws Exception {
    Scanner s = new Scanner(new File("i.in"));
    int N = s.nextInt();
    for (int cas = 0; cas < N; cas++) {
      String blank = s.nextLine();
      String eq1 = s.nextLine();
      String eq2 = s.nextLine();

      rat[] eq1p = parse(eq1);
      rat[] eq2p = parse(eq2);

      if ((eq1p[0].iszero() && eq2p[0].iszero()) ||
          (eq1p[1].iszero() && eq2p[1].iszero())) {
        giveup(eq1p, eq2p);
        if (cas + 1 < N) System.out.println();
        continue;
          }

      if (eq1p[0].iszero()) {
        rat[] t = eq1p;
        eq1p = eq2p;
        eq2p = t;
      } else {
        if (!subtract(eq2p, eq1p, 0)) {
        giveup(eq1p, eq2p);
        if (cas + 1 < N) System.out.println();
        continue;
        }
      }

      if ((eq1p[0].iszero() && eq2p[0].iszero()) ||
          (eq1p[1].iszero() && eq2p[1].iszero())) {
        giveup(eq1p, eq2p);
        if (cas + 1 < N) System.out.println();
        continue;
          }

      if (!eq1p[1].iszero()) {
        if (!subtract(eq1p, eq2p, 1)) {
        giveup(eq1p, eq2p);
        if (cas + 1 < N) System.out.println();
        continue;
        }
      }

      if ((eq1p[0].iszero() && eq2p[0].iszero()) ||
          (eq1p[1].iszero() && eq2p[1].iszero())) {
        giveup(eq1p, eq2p);
        if (cas + 1 < N) System.out.println();
        continue;
          }

      eq1p[2].divide(eq1p[0]);
      eq2p[2].divide(eq2p[1]);

      System.out.println(eq1p[2].toString());
      System.out.println(eq2p[2].toString());
      if (cas + 1 < N) System.out.println();
    }
  }
}
