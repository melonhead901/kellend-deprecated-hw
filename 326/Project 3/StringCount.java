/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 12, 2009
 * CSE 326 A
 * Project 3 - StringCounter.java
 */

/**
 * An extension of DataCount<String> except handles only a string type and
 * implements two hash function
 * 
 * @author Kellen
 */
public class StringCount extends DataCount<String> {

	int BASE1 = 37;
	int BASE2 = 51;

	StringCount(String data, int count) {
		super(data, count);
	}

	/**
	 * Gets the primary hash code of this object
	 * 
	 * @return Hash code
	 */
	public int hash1() {
		long sum = 0;
		for (int i = 0; i < data.length(); i++) {
			sum *= BASE1;
			sum += (int) data.charAt(i);
		}
		int result = Math.abs((int) sum);

		return result;

	}

	/**
	 * Returns the alternate hash code of this object
	 * 
	 * @return Hash code
	 */
	public int hash2() {
		long sum = 0;
		for (int i = data.length() - 1; i >= 0; i--) {
			sum *= BASE2;
			sum += (int) data.charAt(i);
		}
		int result = Math.abs((int) sum);
		return result;
	}

	@Override
	public int hashCode() {
		return this.hash1();
	}

	@Override
	public String toString() {
		return this.data + " - " + this.count;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj.getClass() == this.getClass())) {
			throw new ClassCastException("Comparison of two incompatible types");
		}

		StringCount scObj = (StringCount) obj;

		return this.data.equals(scObj.data);
	}

}
