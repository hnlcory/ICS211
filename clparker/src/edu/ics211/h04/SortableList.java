/**
 * 
 */
package edu.ics211.h04;

import java.util.Arrays;
import java.util.Comparator;

import edu.ics211.h03.SortRecord;

/**
 * @author Cory Parker
 *
 */
public class SortableList<E> implements IList211<E>, ISortableList<E>{
  private E[] data;
  private int size;
  private int swaps;
  private int comps;
  private double sortTime;
  
  public SortableList() {
    //init member variable
    this.data = (E[]) new Object[13];
  }

  @Override
  public void insertionSort(Comparator<E> compare) {
    swaps=0;
    comps=0;
    //set swaps, comps to 0
    //Dont loop to data.length-1, loop from 1 to size-1(nothing at size, we start at 0)
    //dont have to pull data from sort record?, compare bc they are just E
    
    int n = data.length;
    for (int fill = 0; fill < n - 1; fill++) {
      int posMin = fill;
      for (int next = fill + 1; next < n; next++) {
        comps++;
        int result = compare.compare(data[next], data[posMin]);

        if (result < 0) {
          swaps++;
          posMin = next;
        }
      }

      E temp = data[fill];
      data[fill] = data[posMin];
      data[posMin] = temp;

    }

  }

  @Override
  public void bubbleSort(Comparator<E> compare) {
    swaps=0;
    comps=0;
    boolean exchange = false;
    int pass = 0;
    
    do { // do while loop
      exchange = false; // set we havent changed
      for (int i = 1; i < data.length - pass; i++) {

        comps++;// count compare

        int result = compare.compare(data[i], data[i - 1]);

        if (result < 0) {
          swaps++;// count swap
          E temp = data[i];
          data[i] = data[i - 1];
          data[i - 1] = temp;
          exchange = true;// change exchange to true
        }
      }
      pass++;
    } while (exchange);
  }

  @Override
  public void selectionSort(Comparator<E> compare) {
    swaps=0;
    comps=0;
   
    for (int i = 0; i < data.length - 1; i++) {
      E posMin = data[i];// guess smallest is at [i]

      for (int j = i + 1; j < data.length; j++) {
        comps++;
        if (compare.compare(data[j], posMin) < 0) {
          posMin = data[j];
          swaps++;
        }
      }
      E temp = posMin;
      posMin = data[i];
      data[i] = temp;
    }
  }

  @Override
  public int getNumberOfSwaps() {
   return swaps;
  }

  @Override
  public int getNumberOfComparisons() {
   return comps;
  }

  @Override
  public double getSortTime() {
    return sortTime;
  }

  
  

  @Override
  public E get(int index) {
   checkIndex(index);
   return data[index];
  }
  
  private void checkIndex(int index) {
    if (index<0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public E set(int index, E element) {
    checkIndex(index);
    E temp = data[index]; //remmeber value @ index
   data[index]=element;// set value at index to element
    return temp;//return remembered
  }

  @Override
  public int indexOf(Object obj) {// ***may need to change to E type
    for (int i=0; i<size-1; i++) {//loop 0 to size -1      *** may be weird bc i=0
      if (obj.equals(data[i])){//  if obj.equals(item at index)
        return i; //return index
      }    
    }
    return -1;//not found
  }

  @Override
  public int size() {
   return size;
  }

  @Override
  public boolean add(E e) {
    add(size, e);
    return true;
  }

  @Override
  public void add(int index, E element) {
    if (index < 0 || index > size) { // if index is bad, throw exception, but size is good.
      throw new IndexOutOfBoundsException();
    }
    if (size == data.length) {// if data is full, grow
      data = Arrays.copyOf(data, 2 * data.length);//grow array *2 to be efficient
    }
    for (int i=size-1 ;i<index ;i++) {//(loop size-1 to index) **** possible wrong
      
    }
  
    
        
    // shift all items from index to index+1 (loop size-1 to index)
    //insert element into array at index
    size++;//increment size
  }

  @Override
  public E remove(int index) {
    checkIndex(index);
    E temp = data[index];//remember item at index
    
    //shift items from index +1 to size of index
    
    
    size= size-1;//decrement size
    return temp;//return remembered
    
  }

}