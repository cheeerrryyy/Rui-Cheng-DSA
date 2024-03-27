package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      this.capacity = DEFAULT_STACK_SIZE;
      itemArray = new Object[DEFAULT_STACK_SIZE];
   }

   /**
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      try {  
         if (capacity < 2) {  
             throw new StackAllocationException("Size cannot be less than 2.");  
         }  
         this.capacity = capacity;  
         itemArray = new Object[capacity];  
         this.currentIndex = -1;  
     } catch (OutOfMemoryError e) {  
         throw new StackAllocationException("Failed to allocate memory for the stack.", e);  
     }  
 }  
   

   @Override
   public int capacity() {
      return capacity;
      
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if (element == null) {
         throw new NullPointerException();
      } else if (this.capacity - 1 == currentIndex) {
         this.capacity++;
         Object[] newArray = new Object[this.capacity];
         for (int i = 0; i < itemArray.length; i++) {
            newArray[i] = itemArray[i];
         }
         itemArray = newArray;
         itemArray[++currentIndex] = element;
      } else {
         itemArray[++currentIndex] = element;
      }         
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {  
      if (currentIndex == -1) {  
          throw new StackIsEmptyException("Stack is empty");  
      } else {  
          E item = (E) itemArray[currentIndex];  
          itemArray[currentIndex--] = null; 
          return item;  
      }  
  }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Null");
      } else {
         return (E) itemArray[currentIndex];
      }
   }

   @Override
   public int size() {
      return currentIndex+1;
   }

   @Override
   public void clear() {
      for (int i = 0; i < itemArray.length; i++) {
         itemArray[i]=null;
      }
      this.currentIndex=-1;
   }

   @Override
   public boolean isEmpty() {
      if (currentIndex==-1){
         return true;
      }else {
         return false;
      }
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}
