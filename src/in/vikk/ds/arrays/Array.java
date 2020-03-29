package in.vikk.ds.arrays;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {
    
    private static final int DEFAULT_CAPACITY = 10;

    private T[] dataArray;

    private int length;

    private int capacity;

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public Array(final int capacity) {
        this.dataArray = (T[])new Object[capacity];
        this.capacity = capacity;
    }

    public void add(final T data) {
        increaseCapacity();
        this.dataArray[this.length++] = data;
    }

    public void add(final int index, final T data) {
        if(index < 0 || index > this.length) {
            throw new IllegalArgumentException("Index out of bound.");
        }

        if (index == this.length) {
            add(data);
        }

        increaseCapacity();
        T current = data;
        T previous = null;
        this.length += 1;
        for (int dataIndex = index; dataIndex < this.length; dataIndex++) {
            previous = this.dataArray[dataIndex];
            this.dataArray[dataIndex] = current;
            current = previous;
        }
    }

    public int remove(final T data) {
        final int pos = indexOf(data);
        final T removedData = remove(pos);
        // If data is null.
        assert(removedData == data || removedData.equals(data));
        return pos;
    }

    public T remove(final int pos) {
        if (pos < 0 || pos >= this.length) {
            throw new IllegalArgumentException("Index out of bound");
        }
        
        final T data = this.dataArray[pos];
        for (int index = pos+1; index < this.length; index++) {
            this.dataArray[index - 1] = this.dataArray[index];
        }
        this.dataArray[this.length--] = null;
        return data;
    }

    public int indexOf(final T data) {
        int pos = -1;
        for (int index = 0; index < this.length; index++) {
            final T currentData = this.dataArray[index];
            if (currentData == data || currentData.equals(data)) {
                pos = index;
                break;
            }
        }
        return pos;
    }

    public T get(final int index) {
        if (index < 0 || index > this.length) {
            throw new IllegalArgumentException("Index out of bound");
        }
        return this.dataArray[index];
    }

    public int size() {
        return this.length;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("[");
        for (int index = 0; index < this.length; index++) {
            sb.append(this.dataArray[index]).append(',');
        }
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }

    private void increaseCapacity() {
        if (this.length == this.capacity) {
            final int newCapacity = this.capacity * 2;
            // Create a copy of the dataArray
            final T[] newDataArray = (T[]) new Object[newCapacity];
            for (int index = 0; index < this.length; index++) {
                newDataArray[index] = dataArray[index];
            }
            this.dataArray = newDataArray;
            this.capacity = newCapacity;
        }
    }


    @Override
    public Iterator<T> iterator() {
        class IteratorImplementation implements Iterator<T> {

            private int currentIndex = 0;

            private int count = size();

            @Override
            public boolean hasNext() {
                return size() != 0 && currentIndex < size();
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                concurrentCheck();
                return get(currentIndex++);
            }

            private void concurrentCheck() {
                if (count != size()) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        return new IteratorImplementation();
    }

    public static void main(final String[] args) {
        final Array<Integer> ia = new Array<>(3);
        ia.add(1);
        ia.add(2);
        ia.add(3);
        ia.add(4);
        ia.add(5);
        ia.add(6);
        ia.add(7);
        ia.add(8);
        ia.add(9);
        ia.add(9, 10);
        ia.add(10, 11);
        ia.add(1, 12);
        ia.add(5, 13);
        ia.add(0, 14);
        
        System.out.println(ia);

        ia.remove(0);
        ia.remove(ia.size()-1);
        ia.remove(1);
        ia.remove(new Integer(6));

        System.out.println(ia);
        // System.out.println(ia.get(0));
        for (Integer i : ia) {
            // ia.add(100);
            System.out.println(i);
        }
    }

}