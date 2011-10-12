/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 18, 2009
 * CSE 326 A
 * Project 3 - RunCorrelators.java
 */

/**
 * Runs a Correlator between each pair of texts.
 * 
 * @author Zach
 * 
 */
public class RunCorrelators {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] texts = new String[9];

		texts[0] = "bacon_advancement_of_learning.txt";
		texts[1] = "ebacn10.txt";
		texts[2] = "the-new-atlantis.txt";
		texts[3] = "hamlet.txt";
		texts[4] = "macbeth";
		texts[5] = "merchant-of-venice";
		texts[6] = "romeo-and-juliet";
		texts[7] = "origin_of_species";
		texts[8] = "Marlowe_Edward_II.txt";

		for (int i = 0; i < texts.length; i++) {
			for (int j = i; j < texts.length; j++) {
				String[] a = new String[3];

				a[0] = "-h";
				a[1] = "texts/" + texts[i];
				a[2] = "texts/" + texts[j];

				System.out.println();
				System.out.println(texts[i] + " and " + texts[j]);
				Correlator.main(a);
				System.out.println();
			}
		}
	}
}
