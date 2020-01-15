package in.vikk.ds.tree.heap;

import java.util.Arrays;
import java.util.Comparator;

public abstract class BinaryHeap <T extends Comparable<T>> {
    protected static int DEFAULT_CAPACITY = 10;

    protected Object[] heap;

    protected int heapSize = 0;

    protected Comparator<T> comparator = null;

    protected BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    protected BinaryHeap(int initialSize) {
        this(initialSize, null);
    }

    protected BinaryHeap(Comparator<T> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    protected BinaryHeap(int initialSize, Comparator<T> comparator) {
        heap = new Object[initialSize];
        this.comparator = comparator;
    }

    public void insert(T key) {
        if (heapSize == heap.length) {
            grow();
        }
        heap[heapSize] = key;
        fixHeapParent(heapSize++);
    }

    @SuppressWarnings("unchecked")
    public T delete() {
        T key = null;
        if (heapSize > 0) {
            key = (T) heap[0];
            heap[0] = heap[heapSize-1];
            heap[--heapSize] = null;
            fixHeapChildren(0);
        }
        return key;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    protected abstract void fixHeapParent(int keyIndex);

    protected abstract void fixHeapChildren(int index);

    protected int left(int index) {
        return (2 << index) - 1;
    }

    protected int right(int index) {
        return (2<< index);
    }

    @SuppressWarnings("unchecked")
    protected T leftChild(int index) {
        return (T)heap[left(index)];
    }

    @SuppressWarnings("unchecked")
    protected T rightChild(int index) {
        return (T)heap[right(index)];
    }

    protected int parent(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1) / 2;
    }

    protected void grow() {
        int newCapacity = heap.length + heap.length >> 1;
        Object[] newHeap = new Object[newCapacity];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    protected void swap(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @SuppressWarnings("unchecked")
    protected int comparesion(int heapIndex1, int heapIndex2) {
        int comparision= 0;
        if (comparator == null) {
            comparision = ((Comparable<T>)heap[heapIndex1]).compareTo((T)heap[heapIndex2]);
        }
        else {
            comparision = comparator.compare((T)heap[heapIndex1], (T)heap[heapIndex2]);
        }
        return comparision;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }
}
