import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

class e {
  public static void main(String[] args)  throws Exception {
    Scanner s = new Scanner(new File("e.in"));
    int N = s.nextInt();
    String blank = s.nextLine();

    HashMap<String, Double> tzs = new HashMap<String, Double>();
    tzs.put("UTC", 0.);
    tzs.put("GMT", 0.);
    tzs.put("BST", 1.);
    tzs.put("IST", 1.);
    tzs.put("WET", 0.);
    tzs.put("WEST", 1.);
    tzs.put("CET", 1.);
    tzs.put("CEST", 2.);
    tzs.put("EET", 2.);
    tzs.put("EEST", 3.);
    tzs.put("MSK", 3.);
    tzs.put("MSD", 4.);
    tzs.put("AST", -4.);
    tzs.put("ADT", -3.);
    tzs.put("NST", -3.5);
    tzs.put("NDT", -2.5);
    tzs.put("EST", -5.);
    tzs.put("EDT", -4.);
    tzs.put("CST", -6.);
    tzs.put("CDT", -5.);
    tzs.put("MST", -7.);
    tzs.put("MDT", -6.);
    tzs.put("PST", -8.);
    tzs.put("PDT", -7.);
    tzs.put("HST", -10.);
    tzs.put("AKST", -9.);
    tzs.put("AKDT", -8.);
    tzs.put("AEST", 10.);
    tzs.put("AEDT", 11.);
    tzs.put("ACST", 9.5);
    tzs.put("ACDT", 10.5);
    tzs.put("AWST", 8.);

    for (int i = 0; i < N; i++) {
      String[] line = s.nextLine().split(" ");
      String time = line[0];
      String starttz = line[1], endtz = line[2];
      int h, m;
      if (line.length > 3) {
        // 12:00 p.m.
        String[] hhmm = time.split(":");
        h = Integer.parseInt(hhmm[0]);
        m = Integer.parseInt(hhmm[1]);
        if (line[1].equals("p.m.") && h != 12) h += 12;
        else if (line[1].equals("a.m.") && h == 12) h = 0;
        starttz = line[2];
        endtz = line[3];
      } else {
        if (time.equals("noon")) { h = 12; m = 0; }
        else { h = 0 ; m = 0; }
      }

      double hours = h + (double)m/60.0;

      hours += -tzs.get(starttz);
      hours += tzs.get(endtz);

      while (hours < 0.0) hours += 24.0;
      while (hours >= 24.0) hours -= 24.0;

      int hnew = (int)hours;
      int mnew = (int)Math.round(((hours - hnew) * 60));

      while (mnew >= 60) {
        hnew += 1;
        mnew -= 60;
      }

      while (mnew < 0) {
        hnew -= 1;
        mnew += 60;
      }

      while (hnew >= 24) {
        hnew -= 24;
      }

      while (hnew < 0) {
        hnew += 24;
      }

      if (hnew == 0 && mnew == 0) {
        System.out.println("midnight");
      } else if (hnew < 12) {
        if (hnew == 0) hnew = 12;
        System.out.printf("%d:%02d a.m.\n", hnew, mnew);
      } else if (hnew == 12 && mnew == 0) {
        System.out.println("noon");
      } else if (hnew == 12) {
          System.out.printf("%d:%02d p.m.\n", 12, mnew);    	  
      } else {
        System.out.printf("%d:%02d p.m.\n", hnew - 12, mnew);
      }
    }
  }
}
