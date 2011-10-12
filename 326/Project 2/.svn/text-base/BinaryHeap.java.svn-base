/**
 * @author steinz
 * 4/29/09
 * CSE326 Project 2
 * BinaryHeap used for BestFS bin search
 */

import java.util.NoSuchElementException;

public class BinaryHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {

	/**
	 * the default capacity of the heap
	 */
	private static final int DEFAULT_CAPACITY = 16;
	
	/**
	 * current number of elements in the heap
	 */
	public int currentSize;
	/**
	 * the elements in the heap
	 * the first (minimal) element is at index 1
	 */
	private E[] elements;
	
	/**
	 * construct an empty heap
	 */
	public BinaryHeap() {
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
		elements[1] = elements[currentSize];
		elements[currentSize] = null;
		currentSize--;
		this.percolateDown(1);
		
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
		return elements[1];
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
		
		elements[currentSize + 1] = x;
		percolateUp(currentSize + 1);
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
		// index 0 is unused, so heap is full if currentSize equals the lenth of the element array - 1
		return currentSize == elements.length - 1;
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
			
			// if left child exists, assume it is the minimal child
			if (leftChild(index) <= currentSize){
				minChild = leftChild(index);
			}
			
			// if right child exists and is less than right child, it is the minimal child
			if (rightChild(index) <= currentSize &&
					elements[rightChild(index)].compareTo(elements[leftChild(index)]) < 0) {
				minChild = rightChild(index);
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
		while (index > 1 &&
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
		return i / 2;
	}
	/**
	 * @param i the index to find the left child of
	 * @return the left child of index i
	 */
	private int leftChild(int i) {
		return i * 2;
	}
	/**
	 * @param i the index to find the right child of
	 * @return the right child of index i
	 */
	private int rightChild(int i) {
		return i * 2 + 1;
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
