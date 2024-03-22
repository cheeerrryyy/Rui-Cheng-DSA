package oy.tol.tra;

/**
 * A simple array of student grades to be used in testing
 * misbehaving algorithm for reversing the array.
 */
public class Grades {
   
   private Integer [] grades = null;

   /**
    * A constructor for building IntArrays.
    * @param grades the plain Java integer array with numbers to add.
    */
   public Grades(Integer [] grades) {
      this.grades = new Integer [grades.length];
      for (int counter = 0; counter < grades.length; counter++) {
         this.grades[counter] = grades[counter];
      }
   }

   /**
    * The method to reverse the internal Java int array.
    */
   public void reverse() {
      for (int i = 0;i < grades.length/2;i++) {
         int temp = grades[i];
         grades[i] = grades[grades.length-i-1];
         grades[grades.length-i-1] = temp;
     }
   }

   /**
    * Sorts the array to ascending order.
    */
   public void sort() {
      for (int i = 0;i < grades.length-1;i++) {
         for (int j = 0; j < grades.length-1-i;j++){
         if (grades[j] > grades[j+1]) {
            int tmp = grades[j];
            grades[j] = grades[j+1];
            grades[j+1] = tmp;
           }
         }
      }
   }

   /**
    * Returns the plain Java int [] array for investigation.
    * @return The int array.
    */
   public Integer [] getArray() {
      return grades;
   }
}
