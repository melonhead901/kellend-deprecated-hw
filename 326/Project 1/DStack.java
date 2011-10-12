/**
 * Interface for a stack of primitive doubles.
 * @version CSE326 Sp09
 *
 * NOTE: The comments for this interface are horrible! You will
 *       need to write something better for your implementations.
 */
interface DStack {

    boolean isEmpty();


    void push(double d);


    double pop();


    double peek();
}
