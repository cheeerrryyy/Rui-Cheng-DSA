package oy.tol.tra;

public class Algorithms<T> {

   private T[] array = null;

   public void Array(T[] array) {
      this.array = array;
      for (int counter = 0; counter < array.length; counter++) {
         this.array[counter] = array[counter];
      }
   }

   public static <T extends Comparable<T>> void sort(T[] array) {
      for (int i = 0; i < array.length - 1; i++) {
         for (int j = 0; j < array.length - 1 - i; j++) {
            if (array[j].compareTo(array[j + 1]) > 0) {
               T tmp = array[j];
               array[j] = array[j + 1];
               array[j + 1] = tmp;
            }
         }
      }
   }

   public static <T> void reverse(T[] array) {
      for (int i = 0; i < array.length / 2; i++) {
         T temp = array[i];
         array[i] = array[array.length - i - 1];
         array[array.length - i - 1] = temp;
      }
   }

   public static <T> void swap(T[] array, int first, int second) {
      T tem = array[first];
      array[first] = array[second];
      array[second] = tem;
   }
   
   public static <E extends Comparable<E>> void fastSort(E [] array, int begin, int end) {
      quickSort(array, 0, array.length - 1);
   }

   public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
      if (begin < end) {  
         int pivot = partition(array, begin, end);  
         quickSort(array, begin, pivot - 1);  
         quickSort(array, pivot + 1, end);  
     }  
   }

   private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
      E temp = array[end];
      int i = begin - 1;
      for (int j = begin; j < end; j++) {  
          if (array[j].compareTo(temp) < 0) {  
              i++;  
              swap(array, i, j);  
          }  
      }  
      swap(array, i + 1, end);  
      return i + 1; 
   }

   public T[] getArray() {
      return array;
   }

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      if (fromIndex > toIndex) {  
         return -1; 
     }  
     int mid = fromIndex + (toIndex - fromIndex) / 2; 
     int comparison = aValue.compareTo(fromArray[mid]);  
     if (comparison == 0) {  
         return mid; 
     } else if (comparison < 0) {  
         return binarySearch(aValue, fromArray, fromIndex, mid - 1);  
     } else {  
         return binarySearch(aValue, fromArray, mid + 1, toIndex);  
     }  
   }
}


