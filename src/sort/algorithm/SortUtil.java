package sort.algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;

/***
 * 排序工具类
 * SortUtil: SortUtil.java.
 * <br>==========================
 * <br> 创建时间：2018年2月28日
 * <br> 修改时间：2018年2月28日
 * <br> 版本：1.0
 * <br> JDK版本：1.8
 * <br>==========================
 */
public class SortUtil {

    /** 冒泡排序 */
    public static void bubbleSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                }
            }
        }

    }

    /** 选择排序：和冒泡的差别就是每一轮比较记住极值，每一轮结束后再交换 */
    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int max = 0;
            for (int j = 0; j < list.length - i; j++) {
                if (list[j] > list[max]) {
                    max = j;
                }
            }
            int tmp = list[list.length - i - 1];
            list[list.length - i - 1] = list[max];
            list[max] = tmp;

        }

    }

    /** 插入排序：类似抓扑克牌，将要排序的节点向左比较，遇到比该节点大的元素后，将比该节点大的元素往yo移动 */
    public static void insertionSort(int[] list) {

        for (int i = 1; i < list.length; i++) {
            int now = list[i];
            int j = i - 1;
            /** 大于当前值就往右边挪，否则 */
            while (j >= 0 && list[j] > now) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = now;
        }
    }

    /** 快速排序 */
    public static void quickSort(int left, int right, int[] list) {
        if (left > right) {
            return;
        }
        /** 左哨兵 */
        int i = left;
        /** 右哨兵 */
        int j = right;
        /** 基准数 */
        int base = list[left];
        int temp;
        while (i != j) {
            /** 右哨兵寻找比基准数小的元素 */
            while (base <= list[j] && i < j) {
                j--;
            }
            /** 左哨兵寻找比基准数大的元素 */
            while (base >= list[i] && i < j) {
                i++;
            }
            /** 交换 */
            if (i < j) {
                temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
        /** 定位到i==j的位置是基准数的位置,交换 */
        temp = list[left];
        list[left] = list[i];
        list[i] = temp;
        /** 继续处理基准数左边的元素 */
        quickSort(left, i - 1, list);
        /** 继续处理基准数右边的元素 */
        quickSort(i + 1, right, list);
    }

    public static void quickSort(int[] list) {
        quickSort(0, list.length - 1, list);
    }

    /** 归并排序 */
    private static void mergeSort(int left, int right, int[] list) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(left, mid, list);
        mergeSort(mid + 1, right, list);
        merge(left, mid, right, list);
    }

    private static void merge(int left, int mid, int right, int[] list) {
        int len = right - left + 1;
        int[] temp = new int[len];
        int i = left;
        int j = mid + 1;
        int tmpIndex = 0;
        /** 共同长度比较 */
        while (i <= mid && j <= right) {
            if (list[i] <= list[j]) {
                temp[tmpIndex++] = list[i++];
            } else {
                temp[tmpIndex++] = list[j++];
            }
        }
        /** 左边超出长度 */
        while (i <= mid) {
            temp[tmpIndex++] = list[i++];
        }
        /** 右边超出长度 */
        while (j <= right) {
            temp[tmpIndex++] = list[j++];
        }
        System.arraycopy(temp, 0, list, left, temp.length);
    }

    public static void mergeSort(int[] list) {
        mergeSort(0, list.length - 1, list);
    }

    public static void main(String[] args) {
        //int[] array = { 1, 8, 7,0,-1};
        long start = System.currentTimeMillis();
        int[] array = IntStream.generate(() -> (int) (Math.random() * 10)).limit(10).toArray();
        int[] array1 = new int[array.length];
        System.arraycopy(array, 0, array1, 0, array.length);
        System.out.println("generate int array lenght:" + array.length);
        long end1 = System.currentTimeMillis();
        System.out.println("generate took time:" + ((float) (end1 - start) / 1000) + "s");
        quickSort(5, array1.length - 1, array);
        //bubbleSort(array1);
        //selectionSort(array1);
        //insertionSort(array1);
        mergeSort(5, array1.length - 1, array1);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(array1));
        long end2 = System.currentTimeMillis();
        System.out.println("quick sort took time:" + ((float) (end2 - end1) / 1000) + "s");
    }
}
