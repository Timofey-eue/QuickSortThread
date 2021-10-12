package QuickSort;

import MyThread.*;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private Object[] array;
    private int size;
    private int element;

    public QuickSort(Object[] array, int size) {
        this.element = 0;
        this.size = size;
        this.array = array;
    }

    public void addVal(int val) {
        this.array[element] = val;
        this.element++;
        this.size++;
    }

    public void swapArray(int i, int j) {
        Object tmp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = tmp;
    }

    public <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (array.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;
        T point = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i].compareTo(point) < 0) {
                i++;
            }
            while (array[j].compareTo(point) > 0) {
                j--;
            }
            if (i <= j) {
                swapArray(j, i);
                i++;
                j--;
            }
            if (i < high) {
                quickSort(array, low, j);
            }
            if (j > low) {
                quickSort(array, i, high);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.array);
        return sb.toString();
    }

}