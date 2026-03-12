package HW191.app;

import java.util.Arrays;

public class ArrayUtils {
    public static void main(String[] args) {
        int[] arr = {5, 12, 47, 15, 6, 33, 2, 85, 93, 54};
        System.out.println("Вхідний масив: " + Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("Відсортований масив: " + Arrays.toString(arr));

        int target = 33;
        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("Елемент " + target + " знайдено за індексом " + index);
        } else {
            System.out.println("Елемент " + target + " не знайдено у масиві");
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }
}