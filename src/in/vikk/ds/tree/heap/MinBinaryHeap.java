package in.vikk.ds.tree.heap;

import java.util.Comparator;

public class MinBinaryHeap<T extends Comparable<T>> extends BinaryHeap<T> {

    public MinBinaryHeap() {
        super(DEFAULT_CAPACITY);
    }

    public MinBinaryHeap(int intialSize) {
        super(intialSize);
    }

    public MinBinaryHeap(Comparator<T> comparator) {
        super(comparator);
    }

    public MinBinaryHeap(int initialSize, Comparator<T> comparator) {
        super(initialSize, comparator);
    }

    protected void fixHeapParent(int keyIndex) {
        if (keyIndex != 0) {
            int parentIndex = parent(keyIndex);
            int comparision= comparesion(parentIndex, keyIndex);
            if (comparision > 0) {
                swap(keyIndex, parentIndex);
                fixHeapParent(parentIndex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void fixHeapChildren(int index) {
        if (heap[index] != null) {
            int leftIndex = left(index);
            int rightIndex = right(index);

            int test = leftIndex;
            if (leftIndex < heapSize && rightIndex < heapSize
                    && heap[leftIndex] != null && heap[rightIndex] != null) {
                if (comparesion(leftIndex, rightIndex) > 0) {
                    test = rightIndex;
                }

                if (heap[test] != null
                        && comparesion(index, test) > 0) {
                    swap(index, test);
                    fixHeapChildren(test);
                }
            }
        }
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> minBH = new MinBinaryHeap<>();
        minBH.insert(8);
        minBH.insert(9);
        minBH.insert(5);
        minBH.insert(6);
        minBH.insert(3);
        minBH.insert(3);
        minBH.insert(1);

        System.out.println(minBH);

        System.out.println(minBH.delete());
        System.out.println(minBH);

        System.out.println(minBH.delete());
        System.out.println(minBH);
    }
}
