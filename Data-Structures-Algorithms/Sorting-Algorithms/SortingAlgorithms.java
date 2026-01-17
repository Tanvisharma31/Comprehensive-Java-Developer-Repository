package Sorting_Algorithms;

import java.util.Arrays;

/**
 * Sorting Algorithms in Java
 * 
 * Demonstrates various sorting algorithms with complexity analysis
 */

public class SortingAlgorithms {
    
    /**
     * Bubble Sort
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Stable: Yes
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * Selection Sort
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Stable: No
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
    
    /**
     * Insertion Sort
     * Time Complexity: O(n²) worst, O(n) best
     * Space Complexity: O(1)
     * Stable: Yes
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    /**
     * Merge Sort
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * Stable: Yes
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    /**
     * Quick Sort
     * Time Complexity: O(n log n) average, O(n²) worst
     * Space Complexity: O(log n)
     * Stable: No
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    /**
     * Heap Sort
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     * Stable: No
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            
            heapify(arr, n, largest);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sorting Algorithms Demo ===\n");
        
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr1));
        
        // Bubble Sort
        int[] arr2 = arr1.clone();
        bubbleSort(arr2);
        System.out.println("Bubble Sort:    " + Arrays.toString(arr2));
        
        // Selection Sort
        int[] arr3 = arr1.clone();
        selectionSort(arr3);
        System.out.println("Selection Sort: " + Arrays.toString(arr3));
        
        // Insertion Sort
        int[] arr4 = arr1.clone();
        insertionSort(arr4);
        System.out.println("Insertion Sort: " + Arrays.toString(arr4));
        
        // Merge Sort
        int[] arr5 = arr1.clone();
        mergeSort(arr5, 0, arr5.length - 1);
        System.out.println("Merge Sort:     " + Arrays.toString(arr5));
        
        // Quick Sort
        int[] arr6 = arr1.clone();
        quickSort(arr6, 0, arr6.length - 1);
        System.out.println("Quick Sort:     " + Arrays.toString(arr6));
        
        // Heap Sort
        int[] arr7 = arr1.clone();
        heapSort(arr7);
        System.out.println("Heap Sort:      " + Arrays.toString(arr7));
        
        System.out.println("\n=== Complexity Summary ===");
        System.out.println("Bubble Sort:    O(n²) time, O(1) space");
        System.out.println("Selection Sort: O(n²) time, O(1) space");
        System.out.println("Insertion Sort: O(n²) time, O(1) space");
        System.out.println("Merge Sort:     O(n log n) time, O(n) space");
        System.out.println("Quick Sort:     O(n log n) avg, O(n²) worst, O(log n) space");
        System.out.println("Heap Sort:      O(n log n) time, O(1) space");
    }
}
