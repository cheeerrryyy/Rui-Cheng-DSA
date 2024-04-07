1.The hash function is like 
private int hashCode(K key) {
        int hash = 0;
        String keyString = key.toString();
        for (int i = 0; i < keyString.length(); i++) {
            hash = 31 * hash + keyString.charAt(i);
        }
        return hash;
    }

2.To get the top100, I first get the sorted array through TreeToArrayVisitor's method and quick sort. Then I return the sorted array to the implementations.

3.There is currently no content regarding the correctness of the implementation。

4.Time complexity is commonly used to describe how the execution time of an algorithm increases with the amount of input data. For the implementation of word counting and sorting, the time complexity mainly depends on two main parts: counting and sorting. Using a hash table can effectively read and manage words and their counts. No matter how the number of words increases, the operation time remains relatively stable. This makes the code very efficient when dealing with a large number of words. The efficiency of obtaining the top 100 list is efficient: extracting the top 100 elements can usually be done directly in the sorted list, with a time complexity of O (1), as this is simply accessing the top 100 elements in the list. Fast sorting was used with a time complexity of O (n log n).

5.I found the most difficult task to find the appropriate algorithm and meet the requirements of the test file.

6.I have learned a lot through writing exercises. For example, the time complexity of algorithms, when they are most suitable for use, Java throwing, the process of resolving exceptions, and their data structures.