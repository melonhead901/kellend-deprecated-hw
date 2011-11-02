import java.io.*;
import java.util.*;

class a {
  public static void main(String[] args)  throws Exception {
    Scanner s = new Scanner(new File("a.in"));
    int numCoords = s.nextInt();
    while (numCoords != 0) {
    	int originIndex = numCoords / 2;
    	int originX = 0, originY = 0;
    	List<Integer> xs = new ArrayList<Integer>(numCoords);
    	List<Integer> ys = new ArrayList<Integer>(numCoords);
    	for (int i = 0; i < numCoords; i++) {
    		if (i == originIndex) {
    			originX = s.nextInt();
    			originY = s.nextInt();
    		} else {
    			xs.add(s.nextInt());
    			ys.add(s.nextInt());
    		}
    	}
    	int stan = 0, ollie = 0;
    	for (int i = 0; i < numCoords - 1; i++) {
    		int x = xs.get(i);
    		int y = ys.get(i);
    		if ((x > originX && y > originY) || (x < originX && y < originY)) {
    			stan++;
    		} else if ((x < originX && y > originY) || (x > originX && y < originY)) {
    			ollie++;
    		}
    	}
    	System.out.println(stan + " " + ollie);    	
    	numCoords = s.nextInt();
    }
    
  }
}
