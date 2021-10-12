package Main;

import QuickSort.*;
import MyThread.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        Integer[] orig = new Integer[500];

        int len = orig.length - 1;

        for (int i = 0; i < orig.length; i++) {
            orig[i] = r.nextInt(100) + 1;
        }

        long startTime = System.currentTimeMillis();
//      System.out.println(Arrays.toString(orig));
        QuickSort test = new QuickSort(orig, len);

        int low = 0;

        test.quickSort(orig, low, len);
        long estimatedTime = System.currentTimeMillis() - startTime;
//        System.out.println(Arrays.toString(orig));
        System.out.println("Время обычной сортировки: " + estimatedTime + "ms");

        int threadNum = Runtime.getRuntime().availableProcessors();
        QuickSortThread test1 = new QuickSortThread(orig, low, len, threadNum);
        long startTime1 = System.currentTimeMillis();
        test1.parallelSort();
//      System.out.println(test1);
        long estimatedTime1 = System.currentTimeMillis() - startTime1;
        System.out.println("Время с потоком: " + estimatedTime1 + "ms");

    }
}

