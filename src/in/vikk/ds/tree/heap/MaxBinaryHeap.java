package in.vikk.ds.tree.heap;

import java.util.Arrays;

public class MaxBinaryHeap<T extends Comparable<T>> extends BinaryHeap <T> {

    public MaxBinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MaxBinaryHeap(int intialSize) {
        super(intialSize);
    }

    @SuppressWarnings("unchecked")
    protected void fixHeapParent(int keyIndex) {
        if (keyIndex != 0) {
            int parentIndex = parent(keyIndex);
            if (((Comparable<T>)heap[parentIndex]).compareTo((T)heap[keyIndex]) < 0) {
                swap(keyIndex, parentIndex);
                fixHeapParent(parentIndex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void fixHeapChildren(int index) {
        if (index < heapSize && heap[index] != null) {
            int leftIndex = left(index);
            int rightIndex = right(index);

            int test = leftIndex;
            if (leftIndex < heapSize && rightIndex < heapSize
                    && heap[leftIndex] != null && heap[rightIndex] != null) {
                if (((Comparable) heap[leftIndex]).compareTo(heap[rightIndex]) < 0) {
                    test = rightIndex;
                }
                if (heap[test] != null
                        && ((Comparable) heap[test]).compareTo(heap[index]) > 0) {
                    swap(index, test);
                    fixHeapChildren(test);
                }
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> maxBH = new MaxBinaryHeap<>();
        maxBH.insert(8);
        maxBH.insert(9);
        maxBH.insert(5);
        maxBH.insert(6);
        maxBH.insert(3);
        maxBH.insert(1);

        System.out.println(maxBH);

        System.out.println(maxBH.delete());
        System.out.println(maxBH);
    }
}
