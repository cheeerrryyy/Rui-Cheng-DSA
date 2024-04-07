package oy.tol.tira.books;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class HashTableBookImplementation implements Book{

    private static final int MAX_WORDS = 100000;

    private static final int MAX_WORD_LEN = 100;

    KeyValueHashTable<String, Integer> words = null;

    Pair<String,Integer>[] sorted;

    private String bookFile = null;
    private String wordsToIgnoreFile = null;

    private WordFilter filter = null;

    private int uniqueWordCount = 0;
    private int totalWordCount = 0;
    private int ignoredWordsTotal = 0;
    private long loopCount = 0;

    @Override
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {
        boolean success = false;
        if (checkFile(fileName)) {
            bookFile = fileName;
            if (checkFile(ignoreWordsFile)) {
                wordsToIgnoreFile = ignoreWordsFile;
                success = true;
            }
        }
        if (!success) {
            throw new FileNotFoundException("Cannot find the specified files");
        }
    }

    @Override
    public void countUniqueWords() throws IOException, OutOfMemoryError {
        if (bookFile == null || wordsToIgnoreFile == null) {
            throw new IOException("No file(s) specified");
        }
        uniqueWordCount = 0;
        totalWordCount = 0;
        loopCount = 0;
        ignoredWordsTotal = 0;
        words =  new KeyValueHashTable<>(100);
        filter = new WordFilter();
        filter.readFile(wordsToIgnoreFile);

        FileReader reader = new FileReader(bookFile, StandardCharsets.UTF_8);
        int c;
        int[] array = new int[MAX_WORD_LEN];
        int currentIndex = 0;
        while ((c = reader.read()) != -1) {
            if (Character.isLetter(c)) {
                array[currentIndex] = c;
                currentIndex++;
            } else {
                if (currentIndex > 0) {
                    String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
                    currentIndex = 0;
                    addToWords(word);

                }
            }
        }
      
        if (currentIndex > 1) {
            String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
            addToWords(word);
        }
        reader.close();

    }

    private void addToWords(String word) {
        if (word.length() > 1) {
            word = word.toLowerCase(Locale.ROOT);
            if (!filter.shouldFilter(word)) {
                Integer currentCount = 0;
                if (words.find(word) == null) {
                    currentCount = 1;
                    uniqueWordCount += 1;
                } else {
                    currentCount = words.find(word) + 1;
                }
                words.add(word, currentCount);
                totalWordCount += 1;
            } else {
                ignoredWordsTotal++;
            }
        }

    }

    @Override
    public void report() {
        if (words.size() == 0) {
            System.out.println("*** No words to report! ***");
            return;
        }
        System.out.println("Listing words from a file: " + bookFile);
        System.out.println("Ignoring words from a file: " + wordsToIgnoreFile);
        System.out.println("Sorting the results...");
        sorted = words.toSortedArray();
        Algorithms.reverse(sorted);
        System.out.println("...sorted.");

        for (int index = 0; index < 100 && index<sorted.length; index++) {
            String word = String.format("%-20s",sorted[index].getKey()).replace(' ', '.');
            System.out.format("%4d. %s %6d%n", index + 1, word, sorted[index].getValue());
        }
        System.out.println("Count of words in total: " + totalWordCount);
        System.out.println("Count of unique words:    " + uniqueWordCount);
        System.out.println("Count of words to ignore:    " + filter.ignoreWordCount());
        System.out.println("Ignored words count:      " + ignoredWordsTotal);
        System.out.println("How many times the inner loop rolled: " + loopCount);
        System.out.println("\nInformation for hashTable implementation");
        System.out.println(words.getStatus());
    }

    @Override
    public void close() {
        bookFile = null;
        wordsToIgnoreFile = null;
        words = null;
        if (filter != null) {
            filter.close();
        }
        filter = null;
    }

    @Override
    public int getUniqueWordCount() {
        return uniqueWordCount;
    }

    @Override
    public int getTotalWordCount() {
        return totalWordCount;
    }

    @Override
    public String getWordInListAt(int position) {
        if (sorted != null && position >= 0 && position < uniqueWordCount) {
            return sorted[position].getKey();
        }
        return null;
    }

    @Override
    public int getWordCountInListAt(int position) {
        if (sorted != null && position >= 0 && position < uniqueWordCount) {
            return sorted[position].getValue();
        }
        return -1;
    }

    private boolean checkFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

}