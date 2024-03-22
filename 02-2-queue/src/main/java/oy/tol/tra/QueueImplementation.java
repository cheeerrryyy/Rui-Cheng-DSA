package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

   private Object[] itemArray;
   private int capacity;
   private int front;
   private int rear;
   private static final int DEFAULT_STACK_SIZE = 10;

   public QueueImplementation() throws QueueAllocationException {
      this.capacity = DEFAULT_STACK_SIZE;
      itemArray = new Object[DEFAULT_STACK_SIZE];
      front = 0;
      rear = 0;
   }

   public QueueImplementation(int capacity) throws QueueAllocationException {
      this.capacity = capacity;
      itemArray = new Object[capacity];
      front = 0;
      rear = 0;
   }

   @Override
   public int capacity() {
      return capacity;
   }

   @Override
   public void enqueue(E element) throws QueueAllocationException{
      if (element == null) {
         throw new NullPointerException();
      } else if ((rear + 1) % capacity == front) {
         int newCapacity = capacity + 1;
         Object[] newArray = new Object[newCapacity];
         int size = size();
         for (int i = 0; i < size; i++) {
            newArray[i] = itemArray[(front + i) % capacity];
         }
         newArray[size] = element;  
         capacity = newCapacity;
         itemArray = newArray;
         rear = size;
         front = 0;
         rear = (rear + 1) % capacity;
      } else {
         itemArray[rear] = element;
         rear = (rear + 1) % capacity;
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public E dequeue() throws QueueIsEmptyException {
      if (front == rear) {
         throw new QueueIsEmptyException("It is null");
      } else {
         E value = (E) itemArray[front];
         front = (front + 1) % capacity;
         return value;
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public E element() throws QueueIsEmptyException {
      if (front == rear) {
         throw new QueueIsEmptyException("It is null");
      } else {
         return (E) itemArray[front];
      }
   }

   @Override
   public int size() {
      if (front <= rear) {  
         return (rear - front + capacity) % capacity;  
     } else {  
         return (capacity - front + rear) % capacity;  
     }  
   }

   @Override
   public void clear() {
      for (int i = 0; i < itemArray.length; i++) {
         itemArray[i] = null;
      }
      front = rear = 0;
   }

   @Override
   public boolean isEmpty() {
      if (front == rear) {
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      int current = front;
      for (var index = 0; index < (rear + capacity - front) % capacity; index++) {
         builder.append(itemArray[current]);
         if (index < (rear + capacity - front) % capacity - 1) {
            builder.append(", ");
         }
         current = (current + 1) % itemArray.length;
      }
      builder.append("]");
      return builder.toString();
   }
}
