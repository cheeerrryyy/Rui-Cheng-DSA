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

   public T[] getArray() {
      return array;
   }

}
