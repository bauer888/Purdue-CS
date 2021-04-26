/**
 * A Patient queue implementation using a dynamically-sized circular array.
 * 
 * @author Jack Bauer
 */
public class MyPatientQueue{
	// instance variables
	// TODO ATTENTION: CODE NEEDED HERE
	private Patient[] array;
	private int head;
	private int tail;
	private int currentSize;
	private int maxSize;
	// declare instance variables
	// -----

	// constructor
	public MyPatientQueue() {
		// TODO ATTENTION: CODE NEEDED HERE
		this.maxSize = 7;
		this.array = new Patient[maxSize];
		this.head = 0;
		this.tail = 0;
		this.currentSize = 0;
		// initialize instance variables
		// -----
	}

	// functions
	/**
	 * @return the number of patients in the queue
	 */
	public int size() {
		// TODO ATTENTION: CODE NEEDED HERE
		/*if (head < tail) {
			currentSize = tail - head;
		} else if (head == tail) {
			currentSize = 0;
		} else {
			currentSize = tail + (array.length - head);
		}*/
		return currentSize;
		// return the number of patients in the queue
		// -----
	}

	/**
	 * add patient to end of queue.
	 * @param p - Patient to add to queue
	 */
	public void enqueue(Patient p) {
		// TODO ATTENTION: CODE NEEDED HERE
		if (currentSize == array.length) {
			maxSize *= 2;
			Patient[] newArray = new Patient[maxSize];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[(head + i) % array.length];
			}
			array = newArray;
			tail = currentSize;
			head = 0;
			array[tail] = p;
			tail = (tail + 1) % array.length;
			currentSize++;
		} else {
			array[tail] = p;
			tail = (tail + 1) % array.length;
			currentSize++;
		}

		if (head == tail) {
			maxSize *= 2;
			Patient[] newArray = new Patient[maxSize];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[(head + i) % array.length];
			}
			array = newArray;
			tail = currentSize;
			head = 0;
		}

		// add patient to end of queue
		// resize array, if needed
		// -----
	}

	/**
	 * remove and return next patient from the queue
	 * @return patient at front of queue, null if queue is empty
	 */
	public Patient dequeue() {
		//return array[head];
		if (head == tail) {
			return null;
		}
		Patient dElement;
		dElement = array[head];
		array[head] = null;
		head = (head + 1) % array.length;
		currentSize--;
		if (currentSize < (array.length / 4) && !(array.length < 7)) {
			maxSize /= 2;
			Patient[] newArray = new Patient[maxSize];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = array[(head + i) % array.length];
			}
			array = newArray;
			head = 0;
			tail = currentSize;
		}
		if (head == tail) {
			head = 0;
			tail = 0;
			currentSize = 0;
		}
		// TODO ATTENTION: CODE NEEDED HERE
		// remove and return the patient at the head of the queue
		// resize array, if needed
		return dElement;
		// -----
	}

	/**
	 * return, but do not remove, the patient at index i
	 * @param i - index of patient to return
	 * @return patient at index i, or null if no such element
	 */
	public Patient get(int i) {
		// TODO ATTENTION: CODE NEEDED HERE
		// return, but do not remove, the patient at index i
		return array[(head + i) % array.length];
		// -----
	}

	/**
	 * add patient to front of queue
	 * @param p - patient being added to queue
	 */
	public void push(Patient p) {
		if (currentSize == array.length) {
			maxSize *= 2;
			Patient[] newArray = new Patient[maxSize];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = array[(head + i) % array.length];
			}
			array = newArray;
			tail = currentSize;
			head = 0;
			//int i = tail - 1;
			/*while (i >= 0) {
				array[i+1] = array[i];
				i--;
			}*/
			array[(head - 1) % array.length] = p;
			head = (head - 1) % array.length;
			currentSize++;
		} else {
			/*int i = tail - 1;
			while (i >= 0) {
				array[i + 1] = array[i];
				i--;
			}*/
			if (head != 0) {
				array[(head - 1) % array.length] = p;
				head = (head - 1) % array.length;
				currentSize++;
			} else {
				array[array.length - 1] = p;
				head = array.length - 1;
				//tail = (tail - 1) % array.length;
				currentSize++;
			}
		}
		if (head == tail) {
			maxSize *= 2;
			Patient[] newArray = new Patient[maxSize];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[(head + i) % array.length];
			}
			array = newArray;
			tail = currentSize;
			head = 0;
		}
		// TODO ATTENTION: CODE NEEDED HERE
		// add Patient p to front of queue
		// resize array, if needed
		// -----
	}

	/**
	 * remove and return patient at index i from queue
	 * @param i - index of patient to remove
	 * @return patient at index i, null if no such element
	 */
	public Patient dequeue(int i) {
		// TODO ATTENTION: CODE NEEDED HERE
		if (i < 0 || i >= currentSize) {
			return null;
		}
		if (head == tail) {
			return null;
		}
		Patient p = array[(head + i) % array.length];
		for (int j = (head + i) % array.length; j != tail ; j = (j + 1) % array.length) {
			array[j] = array[(j + 1) % array.length];
		}
		if (tail == 0) {
			tail = array.length - 1;
		} else {
			tail = (tail - 1) % array.length;
		}
		array[tail] = null;
		if (head == tail) {
			head = 0;
			tail = 0;
		}
		// remove and return Patient at index i from queue
		// shift patients down to fill hole left by removed patient
		// resize array, if needed
		currentSize--;
		return p;

		// -----
	}
}

