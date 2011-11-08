import java.io.*;
import java.util.*;

class f {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		long N = s.nextLong();
		s.nextLine(); // blank
		for( long i = 0 ; i < N ; i++) { 
            int M = s.nextInt();
            long[] pivots = new long[M];
            boolean alllllz = true;
            for (int j = 0; j < M; j++) {
                pivots[j] = s.nextLong();
                if (pivots[j] != 0) alllllz = false;
            }
            if (alllllz) {
                System.out.println("ap!");
                continue;
            }

            long ncycles = 0;
            boolean cont = false;
            if (M == 0) { System.out.println("ap!"); continue; }
            if (M == 1) {
                if (pivots[0] == 0) {
                    System.out.println("ap!");
                    continue;
                }
                else if (pivots[0] < 0) {
                    System.out.println("*bunny*");
                    continue;
                } else {
                    System.out.println("*fizzle*");
                    continue;
                }
            }
            for (int j = 1; j < M; j++) {
                if (pivots.length == 1) {
                    if (pivots[0] == 0) {
                        for (int kk=0; kk< j-1; kk++) System.out.print("z");
                        System.out.println("ap!");
                        cont = true;
                        break;
                    }
                    else if (pivots[0] < 0) {
                        System.out.println("*bunny*");
                        cont  = true;
                        break;
                    } else { System.out.println("*fizzle*"); cont  =true;
                        break;
                    }
                }

                long[] newpivs = new long[pivots.length-1];
                boolean allzero = true;
                for (int k = 0; k < newpivs.length; k++) {
                    long prev = pivots[k+1];
                    long prev_prec = pivots[k];
                    newpivs[k] = prev - prev_prec;
                    if (newpivs[k] != 0) allzero = false;
                }
                if (allzero) {
                    cont = true;
                    for (int kk=0; kk< j; kk++) System.out.print("z");
                    System.out.println("ap!");
                    break;
                }
                pivots = newpivs;
                if (pivots.length == 1) {
                    if (pivots[0] == 0) {
                        for (int kk=0; kk< j; kk++) System.out.print("z");
                        System.out.println("ap!");
                        cont = true;
                        break;
                    }
                    else if (pivots[0] < 0) {
                        System.out.println("*bunny*");
                        cont  = true;
                        break;
                    } else { System.out.println("*fizzle*"); cont  =true;
                        break;
                    }
                }

            }
            if (cont) continue;
		}
	}
}
				
