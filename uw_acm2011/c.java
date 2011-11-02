import java.util.*;
import java.io.*;

public class c {
	static Scanner scanner;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		scanner = new Scanner(new File("c.in"));
		int N = scanner.nextInt();
		for(int i = 0; i < N; i++) {
			processTestCase();
		}

	}
	
	private static void processTestCase() {
		int cap = scanner.nextInt();
		int trav = scanner.nextInt();
		int totalCars = scanner.nextInt();
		boolean ferryLeft = true;
		Queue<Car> left = new LinkedList<Car>();
		Queue<Car> right = new LinkedList<Car>();
		List<Car> results = new ArrayList<Car>();
		int maxTime = 0;
		for (int j = 0; j < totalCars; j++) {
			int t = scanner.nextInt();
			if (scanner.next().equals("left")) {
				left.add(new Car(t));
			} else  {
				right.add(new Car(t));
			}
			maxTime = t;
		}
		
		int currTime = 0;
		while(totalCars > 0) {
			int numCars = 0;
			if(ferryLeft && carsReady(currTime, left)) {
				while (numCars < cap && carsReady(currTime, left)) {
					numCars++;
					Car c = left.remove();
					c.doneTime = currTime + trav;
					results.add(c);
				}
				currTime += trav;
				totalCars -= numCars;
				ferryLeft = false;
			} else if (!ferryLeft && carsReady(currTime, right)) {
				while (numCars < cap && carsReady(currTime, right)) {
					numCars++;
					Car c = right.remove();
					c.doneTime = currTime + trav;
					results.add(c);
				}
				currTime += trav;
				totalCars -= numCars;
				ferryLeft = true;
			} else {
				if (ferryLeft && carsReady(currTime, right)) {
					currTime += trav;
					ferryLeft= false;
				} else if (!ferryLeft && carsReady(currTime, left)) {
					currTime += trav;
					ferryLeft= true;
				} else {
					currTime++;
				}
			}
		}
		Collections.sort(results);
		for(Car c : results) {
			System.out.println(c.doneTime);
		}
		System.out.println();
	}
	
	private static class Car implements Comparable<Car> {
		int startTime;
		int doneTime;
		
		public Car(int time) {
			this.startTime = time;
		}
		@Override
		public int compareTo(Car o) {
		   if (this.startTime < o.startTime) {
			   return -1;
		   } else if (this.startTime == o.startTime) {
			   return 0;
		   } else return 1;
		}
		
		
	}

	private static boolean carsReady(int currTime, Queue<Car> left) {
		 return !left.isEmpty() && left.peek().startTime <= currTime;
	}

}
