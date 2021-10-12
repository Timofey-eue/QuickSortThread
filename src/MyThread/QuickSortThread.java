package MyThread;

import QuickSort.QuickSort;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QuickSortThread <T extends Comparable<T>>  {
    private T[] array;
    private int threadNum = Runtime.getRuntime().availableProcessors();
    private int low;
    private int high;
    private QuickSort result;

    public QuickSortThread(T[] array, int low, int high, int threadNum) {
        this.low = low;
        this.high = high;
        this.array = array;
        this.threadNum = threadNum;
        this.result = new QuickSort(array, array.length);

    }

    public void swapArray(int i, int j) {
        T tmp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = tmp;
    }

     public QuickSortThread(T[] array, int threadNum) {
        this.array = array;
        this.threadNum = threadNum;
        this.result = new QuickSort(array, array.length);
    }

    public QuickSort parallelSort() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(this.threadNum);
        int point = low + (high - low) / 2;
        for(int i = this.low; i < array.length; i++) {
            for (int j = this.high; j > point; j--) {
                executorService.execute(new MyThread(i, j));
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        return this.result;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(this.array));
        return sb.toString();
    }

    private class MyThread implements Runnable {
        private int l;
        private int h;

        public MyThread(int i, int j) {
            this.l = i;
            this.h = j;
        }

        @Override
        public void run() {
            int middle = this.l + (this.h - this.l) / 2;
            T point = array[middle];
            int lw = this.l;
            int hg = this.h;
            while(lw <= hg) {
                while(array[lw].compareTo(point) < 0) {
                    lw++;
                }
                while (array[hg].compareTo(point) > 0) {
                    hg--;
                }
                if(lw <= hg) {
                    swapArray(lw, hg);
                    lw++;
                    hg--;
                }
            }
            if(lw < this.h) {
                result.quickSort(array, this.l, hg);
            }
            if(hg > this.l) {
                result.quickSort(array, lw, this.h);
            }
        }
    }
}


