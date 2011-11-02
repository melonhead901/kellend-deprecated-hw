import java.util.*;
import java.io.*;

public class b {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("b.in"));			
		
		int n = scanner.nextInt();
		while(n != 0) {
			List<Integer> nums = new ArrayList<Integer>(n);
			int[] numArr = new int[n];
			for(int i = 0; i < n; i++) {
				int nextInt = scanner.nextInt();
				nums.add(nextInt);
				numArr[i] = nextInt;
			}
			System.out.println(SortAndCount(nums).a);
			//System.out.println(countInversions(numArr));
			n = scanner.nextInt();
		}
		
	}
	
	public static class Pair<A,B> {
		A a;
		B b;
		public Pair(A a , B b) {
			this.a= a;
			this.b= b;
		}
	}
	
	public static Pair<Long, List<Integer>> MergeAndCount(List<Integer> a, List<Integer>b) {
		long count = 0;
		int i = 0;
		int j = 0;
		List<Integer> out = new ArrayList<Integer>();
		while(i < a.size() && j < b.size()) {
			out.add(Math.min(a.get(i), b.get(j)));
			if(b.get(j) <= a.get(i) ) {
				count += a.size()-i;
				j++;
			} else {
				i++;
			}
		}
		while (i < a.size()) {
			out.add(a.get(i));
			i++;
		}
		while (j < b.size()) {
			out.add(b.get(j));
			j++;
		}
		return new Pair<Long, List<Integer>>(count, out);
	}
	
	public static Pair<Long, List<Integer>> SortAndCount(List<Integer>a) {		
		if(a.size() == 1) {
			return new Pair<Long, List<Integer>>(0l, a);
		}
		else {
			List<Integer> A = new ArrayList<Integer>();
			List<Integer> B = new ArrayList<Integer>();
			for(int i = 0; i < a.size()/2; i++) {
				A.add(a.get(i));
			} 
			for(int i = a.size()/2; i < a.size(); i++) {
				B.add(a.get(i));
			} 
			
			Pair<Long,List<Integer>> pa = SortAndCount(A);
			Pair<Long,List<Integer>> pb = SortAndCount(B);
			Pair<Long,List<Integer>> pc = MergeAndCount(pa.b,pb.b);
			
			return new Pair<Long, List<Integer>>(pa.a+pb.a+pc.a, pc.b);			
		}
	}
	

    public static int countInversions(int nums[])
    /*  This function will count the number of inversions in an
        array of numbers.  (Recall that an inversion is a pair
        of numbers that appear out of numerical order in the list.

        We use a modified version of the MergeSort algorithm to 
        do this, so it's a recursive function.  We split the
        list into two (almost) equal parts, recursively count
        the number of inversions in each part, and then count
        inversions caused by one element from each part of 
        the list. 

        The merging is done is a separate procedure given below,
        in order to simplify the presentation of the algorithm
        here. 

        Note:  I am assuming that the integers are distinct, but
        they need *not* be integers { 1, 2, ..., n} for some n.  
         
    */
    {  
        int mid = nums.length/2, k;
        int countLeft, countRight, countMerge;
       
      /*  If the list is small, there's nothing to do.  */ 
        if (nums.length <= 1) 
           return 0;

      /*  Otherwise, we create new arrays and split the list into 
          two (almost) equal parts.   
      */
        int left[] = new int[mid];
        int right[] = new int[nums.length - mid];

        for (k = 0; k < mid; k++)
            left[k] = nums[k];
        for (k = 0; k < nums.length - mid; k++)
            right[k] = nums[mid+k];

      /*  Recursively count the inversions in each part. 
      */
        countLeft = countInversions (left);
        countRight = countInversions (right);

      /*  Now merge the two sublists together, and count the
          inversions caused by pairs of elements, one from
          each half of the original list.  
      */ 
        int result[] = new int[nums.length];
        countMerge = mergeAndCount (left, right, result);

      /*  Finally, put the resulting list back into the original one.
          This is necessary for the recursive calls to work correctly.
      */
        for (k = 0; k < nums.length; k++)
            nums[k] = result[k];

      /*  Return the sum of the values computed to 
          get the total number of inversions for the list.
      */
        return (countLeft + countRight + countMerge);  

    }  /*  end of "countInversions" procedure  */


   public static int mergeAndCount (int left[], int right[], int result[])
   /*  This procudure will merge the two lists, and count the number of
       inversions caused by the elements in the "right" list that are 
       less than elements in the "left" list.  
   */ 
   {
       int a = 0, b = 0, count = 0, i, k=0;

       while ( ( a < left.length) && (b < right.length) )
         {
            if ( left[a] <= right[b] )
                  result [k] = left[a++];
            else       /*  You have found (a number of) inversions here.  */  
               {
                  result [k] = right[b++];
                  count += left.length - a;
               }
            k++;
         }

       if ( a == left.length )
          for ( i = b; i < right.length; i++)
              result [k++] = right[i];
       else
          for ( i = a; i < left.length; i++)
              result [k++] = left[i];

       return count;
   } 
}
