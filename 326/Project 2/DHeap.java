/**
 * @author steinz
 * 4/30/09
 * CSE326 Project 2
 * ThreeHeap used for BestFS bin search
 */

import java.util.NoSuchElementException;

public class DHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {

	/**
	 * the default capacity of the heap
	 */
	private static final int DEFAULT_CAPACITY = 13;
	
	/**
	 * current number of elements in the heap
	 */
	public int currentSize;
	/**
	 * the elements in the heap
	 * the first (minimal) element is at index 0
	 */
	private E[] elements;
	/**
	 * the number of children each node has
	 */
	private int d;
	
	/**
	 * construct an empty heap
	 * @param pq the number of children each node in the heap has
	 */
	public DHeap(int d) throws Exception {
		if (d < 1){
			throw new Exception("attempt to construct a pq-heap with pq < 1");
		}
		this.d = d;
		makeEmpty();
	}
	
	/**
	 * delete and return the minimum element in the heap
	 * @throws NoSuchElementException if the heap is empty
	 * @return the minimum element in the heap
	 */
	public E deleteMin() {
		// throw an exception if the heap is empty
		if (this.isEmpty()) {
				throw new NoSuchElementException();
		}
		
		// get the minimum element in the heap
		E minElement = findMin();
		
		/* replace the minimum element in the heap with the last element in the heap,
		 * null the last element in the heap, decrement the size,
		 * and percolate the last element down
		 */
		elements[0] = elements[currentSize - 1];
		elements[currentSize - 1] = null;
		currentSize--;
		this.percolateDown(0);
		
		// return the minimum element
		return minElement;
	}
	/**
	 * get the minimum element in the heap
	 * @throws NoSuchElementException if the heap is empty
	 * @return the minimum element in the heap
	 */
	public E findMin() {
		// throw an exception if the heap is empty
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		// return the minimum element
		return elements[0];
	}
	/**
	 * insert the provided element into the heap
	 * @param x the element to insert into the heap
	 */
	public void insert(E x) {
		// grow elements if necessary
		if (isFull()) {
			this.enlargeArray();
		}
		
		elements[currentSize] = x;
		percolateUp(currentSize);
		currentSize++;
	}

	/**
	 * @return true if the heap is empty, false otherwise
	 */
	public boolean isEmpty() {
		return this.currentSize == 0;
	}
	/**
	 * empty the heap
	 */
	@SuppressWarnings("unchecked")
	public void makeEmpty() {
		// set the size to 0
		this.currentSize = 0;
		// reconstruct the elements array with the default size
		this.elements = (E[])new Comparable[DEFAULT_CAPACITY];
	}
	/**
	 * @return true if the heap is full, false otherwise
	 */
	private boolean isFull() {
		// heap is full if currentSize equals the lenth of the element array
		return currentSize == elements.length;
	}
	
	/**
	 * percolate the item at index down to ensure the heap's validity
	 * @param index the index of the heap to percolate down
	 */
	private void percolateDown(int index) {
		// get the value  at index to be percolated down 
		E target = elements[index];
		
		// while target's index is inside the heap's size
		while (index < currentSize){
			// stores the minimum element of index's children or index if index has no children
			int minChild = index;
			
			// get the children of index
			int[] children = children(index);
			
			// if the first child exists, assume it is the minimal child
			if (children[0] < currentSize){
				minChild = children[0];
			}
			
			// for ever other child, if they exist and are less than the current minimal child, they are the minimal child
			for (int i = 1; i < children.length; i++){
				if (children[i] < currentSize &&
						elements[children[i]].compareTo(elements[minChild]) < 0){
					minChild = children[i];
				}
			}
			
			/* if index has children and target is bigger than the minimal child, move the minimal child up and
			 * set index to be the index of the minimal child; else index is target's proper position, break out of the loop
			 */
			if (minChild != index &&
					target.compareTo(elements[minChild]) > 0) {
				elements[index] = elements[minChild];
				index = minChild;
			} else {
				break;
			}
		}
		
		// place target in it's proper location
		elements[index] = target;
	}
	/**
	 * percolate the item at index up to ensure the heap's validity
	 * @param index the index of the heap to percolate up
	 */
	private void percolateUp(int index) {
		// get the value at index to percolate up
		E target = elements[index];
		
		/* while the target's index is below the root and target is smaller than it's parent,
		 * move it's parent down and let index equal target's parent's index
		 */
		while (index > 0 &&
				target.compareTo(elements[parent(index)]) < 0) {
			elements[index] = elements[parent(index)];
			index = parent(index);
		}
		
		// place target in it's proper location
		elements[index] = target;
	}
	
	/**
	 * @param i the index to find the parent of
	 * @return the parent of index i
	 */
	private int parent(int i) {
		return (i - 1) / d;
	}
	/**
	 * @param index the index to find the left child of
	 * @return the left child of index i
	 */
	private int[] children(int index) {
		int[] children = new int[d];
		for (int i = 1; i <= d; i++){
			children[i-1] = index * d + i;
		}
		return children;
	}
	
	/**
	 * enlarge the element array
	 */
	@SuppressWarnings("unchecked")
	private void enlargeArray() {
		// create a new array twice as big as the current array
		E[] newElements  = (E[])new Comparable[elements.length * 2];
		
		// copy the elements from the current array to the new array
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = elements[i];
		}
		
		// replace the old array with the new one
		elements = newElements;
	}
}
