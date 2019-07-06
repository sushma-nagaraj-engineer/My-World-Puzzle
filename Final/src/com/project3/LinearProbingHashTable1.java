package com.project3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// QuadraticProbing Hash table class has been manipulated to suit linear probing

// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items

public class LinearProbingHashTable1<AnyType> {

	/**
	 * Construct the hash table.
	 */
	public LinearProbingHashTable1() {
		this(DEFAULT_TABLE_SIZE);
	}

	/**
	 * Construct the hash table.
	 * 
	 * @param size
	 *            the approximate initial size.
	 */
	public LinearProbingHashTable1(int size) {
		allocateArray(size);
		doClear();
	}

	/**
	 * Insert into the hash table. If the item is already present, do nothing.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public boolean insert(AnyType x, boolean j) {

		int currentPos = findPos(x);
		if (isActive(currentPos))
			return false;

		if (array[currentPos] == null)
			++occupied;

		array[currentPos] = new HashEntry<>(x, true, j);
		theSize++;

		if (occupied > array.length / 2)
			rehash();

		return true;
	}

	/**
	 * Expand the hash table.
	 */
	private void rehash() {
		HashEntry<AnyType>[] oldArray = array;

		// Create a new double-sized, empty table
		allocateArray(2 * oldArray.length);
		occupied = 0;
		theSize = 0;

		// Copy table over
		for (HashEntry<AnyType> entry : oldArray)
			if (entry != null && entry.isActive && entry.isPrefix)
				insert(entry.element, true);
			else if (entry != null && entry.isActive && !entry.isPrefix)
				insert(entry.element, false);
	}

	/**
	 * Method that performs quadratic probing resolution.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the position where the search terminates.
	 */
	private int findPos(AnyType x) {
		int offset = 1;
		int currentPos = myhash(x);

		while (array[currentPos] != null
				&& !array[currentPos].element.equals(x)) {
			currentPos += offset;
			// The code for Quadratic probing has been changed here by deleting
			// offset+=2; Because in linear probing if collision occurs we look
			// for next immediately available empty cell.
			if (currentPos >= array.length)
				currentPos -= array.length;

		}

		return currentPos;
	}

	/**
	 * Remove from the hash table.
	 * 
	 * @param x
	 *            the item to remove.
	 * @return true if item removed
	 */
	public boolean remove(AnyType x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;

			theSize--;
			return true;
		} else
			return false;
	}

	/**
	 * Get current size.
	 * 
	 * @return the size.
	 */
	public int size() {
		return theSize;
	}

	/**
	 * Get length of internal table.
	 * 
	 * @return the size.
	 */
	public int capacity() {
		return array.length;
	}

	/**
	 * Find an item in the hash table.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the matching item.
	 */
	public boolean contains(AnyType x) {
		int currentPos = findPos(x);
		return isActive(currentPos);
	}

	public boolean isPrefix(AnyType x) {
		int currentPos = findPos(x);
		return array[currentPos] != null && array[currentPos].isActive
				&& array[currentPos].isPrefix;
	}

	/**
	 * Return true if currentPos exists and is active.
	 * 
	 * @param currentPos
	 *            the result of a call to findPos.
	 * @return true if currentPos is active.
	 */
	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}

	/**
	 * Make the hash table logically empty.
	 */
	public void makeEmpty() {
		doClear();
	}

	private void doClear() {
		occupied = 0;
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}

	private int myhash(AnyType x) {
		int hashVal = x.hashCode();
		hashVal %= array.length;
		if (hashVal < 0)
			hashVal += array.length;

		return hashVal;
	}

	private static class HashEntry<AnyType> {
		public AnyType element;
		public boolean isActive;// this indicates if the element is deleted or
								// not
		public boolean isPrefix;

		public HashEntry(AnyType e) {
			this(e, true, true);
		}

		public HashEntry(AnyType e, boolean i, boolean j) {
			element = e;
			isActive = i;
			isPrefix = j;
		}
	}

	private static final int DEFAULT_TABLE_SIZE = 101;

	private HashEntry<AnyType>[] array; // The array of elements
	private int occupied; // The number of occupied cells
	private int theSize; // Current size

	/**
	 * Internal method to allocate array.
	 * 
	 * @param arraySize
	 *            the size of the array.
	 */
	private void allocateArray(int arraySize) {
		array = new HashEntry[nextPrime(arraySize)];
	}

	/**
	 * Internal method to find a prime number at least as large as n.
	 * 
	 * @param n
	 *            the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * @param n
	 *            the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

	// Simple main
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		// First we begin with demonstration of enhanced mode which considers
		// prefixes for searching word in dictionary
		boolean enhance = true;
		System.out.println("Enter the number of rows: ");
		int r = sc.nextInt();

		System.out.println("Enter the number of columns: ");
		int c = sc.nextInt();

		LinearProbingHashTable1<String> L = new LinearProbingHashTable1<>();
		FindWord s = new FindWord();
		try {
			// IF the file is in the same folders need not specify the full path
			// else need to specify the complete path
			BufferedReader rd = new BufferedReader(new FileReader(
					"dictionary.txt"));
			String line;

			int i = 0;
			while ((line = rd.readLine()) != null) {
				line = line.trim(); // used to remove white spaces
				// if variable enhance is false then we do not use the prefixes
				// the whole word in the dictionary is considered
				if (enhance == false) {
					L.insert(line, false);

				}
				// for the prefix mode we use string builder and append to
				// consider the words in the form apple as a, ap, app,appl,apple
				else {
					StringBuilder buildword = new StringBuilder();
					for (int j = 0; j < line.length(); j++) {
						buildword.append(line.charAt(j));
						if (j == ((line.length()) - 1)) { // we are passing
															// false as it is a
															// word
							L.insert(buildword.toString(), false);

						} else { // passing true as it is not a word
									// This is done for inserting into same hash
									// table for both with and without prefix
									// case
							L.insert(buildword.toString(), true);

						}
					}

				}
				i++;
			}
			rd.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Finally block can be remove but included just to show generation of
		// grid of random characters irrespective of dictionary file present in
		// desired location
		finally {

			Random random = new Random();

			// characters to be inserted in to randomly generated grid
			char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
					'v', 'w', 'x', 'y', 'z' };
			// a matrix is created based on user input
			char[][] matrix = new char[r][c];

			// used for random character grid generation
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					int randNum = random.nextInt(25);
					matrix[i][j] = alphabet[randNum];
				}
			}

			System.out.println("Randomly genertaed grid is :");

			// printing the grid
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("Enhanced Mode Result");
			long startTime1 = System.currentTimeMillis();
			// using a counter to keep a track of number of words detected
			int count = s.wordCheck(matrix, L, enhance);
			System.out.println("count is " + count);
			long endTime1 = System.currentTimeMillis();

			System.out.println("Elapsed time with enhancement: "
					+ (endTime1 - startTime1) + "ms");
			System.out.println("Without Enhancement the Results are ");

			long startTime2 = System.currentTimeMillis();
			count = 0;
			count = s.wordCheck(matrix, L, false);
			System.out.println("count is " + count);
			long endTime2 = System.currentTimeMillis();

			System.out.println("Elapsed time without enhancement: "
					+ (endTime2 - startTime2) + "ms");

		}

	}
}
