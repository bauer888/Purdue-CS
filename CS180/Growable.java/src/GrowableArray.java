import java.util.Arrays;

/**
 * @author Jack Bauer, bauer88
 *
 * @version 4/27/2019
 *
 * @param <T>
 */
public class GrowableArray<T> {

    private Object[] values;
    private int size;

    public GrowableArray() {
        values = new Object[10];
        size = 0;
    }

    public void add(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        } else if (size < values.length) {
            size += 1;
            values[index] = value;
        } else {
            values = new Object[values.length * 2];
            size += 1;
            values[index] = value;
        }
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return (T) values[index];
        }
    }

    public T set(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Object object = values[index];
            values[index] = value;
            return (T) object;
        }
    }

    public int indexOf(Object object) {
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == object) {
                index = i;
                break;
            } else {
                index = -1;
            }
        }
        return index;
    }

    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            for (int i = index; i < size - 1; i++) {
                values[i] = values[i + 1];
                values[size - 1] = null;
            }
            size -= 1;
        }
        return (T) values[index];
    }

    public void clear() {
        Arrays.fill(values, null);
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public boolean equals(Object object) {
        return object instanceof GrowableArray && ((GrowableArray) object).values == this.values;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                result += values[i];
            } else {
                result += values[i] + ", ";
            }
        }
        return "{" + result + "}";
    }

    public static void main(String[] args) {
        String x = "world";
        String y = x;
        x = "hello";
        System.out.printf("%s %s", x, y);
    }
}
