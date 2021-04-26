import java.util.Arrays;

/**
 * This class imitates some of the functionality of an Arraylist.
 *
 * @param <T>
 * @author Jack Bauer
 * @version 07/29/2019
 */

public class GrowableArray<T> {

    private Object[] values; //Array
    private int size; //Integer

    /**
     * Initializes the size and values fields
     */
    public GrowableArray() {
        size = 0;
        values = new Object[10];
    }

    /**
     * Adds a T value to the specified index in values
     *
     * @param index an integer
     * @param value an integer
     * @throws IndexOutOfBoundsException if index < 0 or size < index
     */
    public void add(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        } else if (size() >= values.length) {
            Object[] temp = new Object[values.length * 2];
            for (int i = 0; i < size(); i++) {
                temp[i] = values[i];
            }
            values = temp;
            for (int i = size() - 1; i >= index; i--) {
                values[i + 1] = values[i];
            }
            size += 1;
            values[index] = value;
        } else {
            for (int i = size() - 1; i >= index; i--) {
                values[i + 1] = values[i];
            }
            size += 1;
            values[index] = value;
        }
    }

    /**
     * Clears the values array
     */
    public void clear() {
        Arrays.fill(values, null);
        size = 0;
    }

    public boolean equals(Object object) {
        if (object instanceof GrowableArray) {
            if (size == ((GrowableArray) object).size) {
                for (int i = 0; i < size; i++) {
                    if (values[i] != ((GrowableArray) object).values[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the T value at the given index of the values array
     *
     * @param index an integer
     * @return a T value
     * @throws IndexOutOfBoundsException when the index < 0 and size <= index
     */
    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return (T) values[index];
        }
    }

    /**
     * Returns the index of the given object in the values array
     *
     * @param object an object
     * @return an integer
     */
    public int indexOf(Object object) {
        for (int i = 0; i < size(); i++) {
            if (values[i] == object) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the values is empty, false if not.
     *
     * @return a boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the value at the given index of the values array
     *
     * @param index an integer
     * @return a T value
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Object[] n = values;
            T variable = (T) n[index];
            int tempSize = size() - 1;
            if (index < tempSize) {
                System.arraycopy(n, index + 1, n, index, tempSize - index);
            }
            size = tempSize;
            n[size] = null;

            return variable;
        }
    }

    /**
     * Changes the T value at the given index of the values array to the given T value
     *
     * @param index an integer
     * @param value a T value
     * @return a T value
     * @throws IndexOutOfBoundsException when index < 0 or size < index
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Object boi = values[index];
            values[index] = value;
            return (T) boi;
        }
    }

    /**
     * Returns the size of the values array
     *
     * @return an integer
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns a string representation of the values array
     *
     * @return a String
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < size(); i++) {
            if (i == size() - 1) {
                s += values[i];
            } else {
                s += values[i] + ", ";
            }
        }
        return "{" + s + "}";
    }
}
