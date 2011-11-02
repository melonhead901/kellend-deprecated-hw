import java.util.*;
import java.io.*;

public class CopyOfb {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("b.in"));
		
		int size = s.nextInt();
		while (size != 0) {
			
			int[] a = new int[size];
			Integer[] d = new Integer[size];
			Integer[] jump = new Integer[size];
			
			for (int i = 0; i < size; i++) {
				a[i] = s.nextInt();
			}
			
			int sln = 0;
			for (int i = size - 1; i >= 0; i--) {
				int ltCount = 0;
				Integer indexFirstGreater = null;
				for (int j = i + 1; j < size; j++) {
					if (d[j] != null && a[j] < a[i]) {
						ltCount += d[j] + 1;
						if (jump[j] == null) {
							break;
						} else {
							j = jump[j] - 1;
						}
					} else if (a[j] < a[i]) {
						ltCount++;
					} else {
						if (indexFirstGreater == null) {
							indexFirstGreater = j;
						}
					}
				}
				d[i] = ltCount;
				jump[i] = indexFirstGreater;
				sln += ltCount;
			}
			
			System.out.println(sln);
			
			size = s.nextInt();
		}
		
		/*
		int n = scanner.nextInt();
		while(n != 0) {
			int[] nums = new int[n];
			for(int i = 0; i < n; i++) {
				nums[i] = scanner.nextInt();
			}
			int numInversions = 0;
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					if (nums[j] < nums[i]) {
						int temp = nums[j];
						nums[j] = nums[i];
						nums[i] = temp;
						numInversions++;
					}
				}
			}
			System.out.println(numInversions);
			n = scanner.nextInt();
		}
		*/
	}

}
