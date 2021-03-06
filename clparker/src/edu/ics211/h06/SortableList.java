/**
 * Sortable list of generic.
 * @author Cory Parker.
 */

package edu.ics211.h06;

import edu.ics211.h04.IList211;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * Creates a sortable list of type E.
 * 
 * @author Cory Parker.
 * @param <E> generic type.
 */
public class SortableList<E> implements IList211<E>, Iterable<E>, List<E> {
  private DLinkedNode tail;
  private int size;
  int swaps = 0;// imports of sort
  int comps = 0;
  long sortTime = 0;

  /**
   * Init variables.
   * 
   * @author Cory Parker
   */
  public SortableList() {
    tail = null;
    size = 0;
  }


  /**
   * Bubble sort to sort data.
   * 
   * @author Cory Parker.
   * @param compare compares data.
   */
  public void bubbleSort(Comparator<E> compare) {
    long startTime = System.nanoTime();
    boolean exchange = false;
    int pass = 0;

    do { // do while loop
      exchange = false; // set changed
      for (int i = 1; i < size - pass; i++) {

        if (get(i) == null) {
          break;
        }
        comps++;// count compare

        int result = compare.compare(get(i), get(i - 1));

        if (result < 0) {
          swaps++;// count swap
          E temp = get(i);
          set(i, get(i - 1));
          set(i - 1, temp);
          exchange = true;// change exchange to true
        }
      }
      pass++;
    } while (exchange);
    long endTime = System.nanoTime(); // get ending time from nanoTime
    this.sortTime = endTime - startTime;// subtract ending from starting to stortTime
  }


  @Override
  public Iterator<E> iterator() {
    return new MyListIterator();
  }


  /**
   * Iterator for going over list.
   * 
   * @author Cory Parker.
   * @param c for item being compared.
   * @return sorted item.
   */
  public Iterator<E> iterator(Comparator<E> c) {
    return new MySortedIterator(c);
  }


  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }


  @Override
  public E get(int index) {
    checkIndex(index);
    DLinkedNode temp = tail;
    for (int i = size - 1; i > index; i--) {
      temp = temp.prev;

    }

    return temp.item;
    // traverse to index form tail (tail = size-1)
    // return node.item

  }


  @Override
  public E set(int index, E element) {
    checkIndex(index);
    DLinkedNode temp = tail;
    for (int i = size - 1; i > index; i--) {
      temp = temp.prev;
    }
    E data = temp.item;
    temp.item = element;
    return data;
    // check index
    // traverse from index to tail
    // remmebr item in node
    // set item in node to element
    // return remembered
  }


  @Override
  public int indexOf(Object obj) {
    DLinkedNode temp = tail;
    for (int index = size - 1; index >= 0; index--) {
      if (temp.item.equals(obj)) {
        return index;
      }
      temp = temp.prev;
    }
    return -1;
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
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }

    if (size == 0) { // if size is 0, point to new node (element,null,null
      tail = new DLinkedNode(element, null, null);
    } else if (index == size) { // else if index is side, adding to the end
      DLinkedNode temp = new DLinkedNode(element, null, tail);
      if (tail != null) {
        tail.next = temp;// tail.next = new node
        tail = temp;
      }
    } else {
      DLinkedNode temp = tail;

      for (int i = size - 1; i > index; i--) {

        temp = temp.prev;
        // System.out.println(temp.item);
      }
      DLinkedNode e = new DLinkedNode(element, temp, temp.prev);
      if (temp.prev != null) {
        temp.prev.next = e;
      }
      temp.prev = e;

    }
    size++;
  }


  @Override
  public E remove(int index) {
    checkIndex(index);

    DLinkedNode temp = tail;
    for (int i = size - 1; i > index; i--) {
      temp = temp.prev;
    }

    if (index == size - 1) {
      if (temp.prev != null) {
        temp.prev.next = temp.next;
      }
      // make the list not point to the node
      // if temp.prev is not null
      // temp.prev.next = temp.next
      // update tail
    } else {

      if (temp.prev != null) {
        temp.prev.next = temp.next;
      }
      if (temp.next != null) {
        temp.next.prev = temp.prev;
      }
    }
    size--;
    return temp.item;
  }

  private class DLinkedNode {
    E item;
    DLinkedNode next;
    DLinkedNode prev;

    public DLinkedNode(E item, DLinkedNode next, DLinkedNode prev) {
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }

  private class MyListIterator implements ListIterator<E> {
    private DLinkedNode nextNode;
    private int nextIndex;

    public MyListIterator() {
      nextIndex = 0;
      // nextIndex=0
      nextNode = traverse(0);
    }


    private DLinkedNode traverse(int num) {
      DLinkedNode temp = tail;
      while (temp.prev != null) {
        temp = temp.prev;
      }
      // System.out.println(temp.item);
      return temp;
    }


    @Override
    public boolean hasNext() {
      if (nextNode != null) {
        return true;
      }
      return false;
    }


    @Override
    public E next() {
      if (hasNext() == true) {
        E data = nextNode.item;
        nextNode = nextNode.next;// update nextNode?
        nextIndex = nextIndex + 1;// update nextIndex?
        return data;
      } else {
        throw new NoSuchElementException();
      }
    }


    @Override
    public boolean hasPrevious() {
      if (nextNode == null || nextNode.prev != null) {
        return true;
      }
      return false;
    }


    @Override
    public E previous() {
      if (hasPrevious() == true) {
        E data = nextNode.prev.item;
        nextNode = nextNode.prev;// update nextNode?
        nextIndex = nextIndex - 1;// update nextIndex?
        return data;
      } else {
        throw new NoSuchElementException();
      }
    }


    @Override
    public int nextIndex() {
      return nextIndex;
    }


    @Override
    public int previousIndex() {
      return nextIndex - 1;
    }


    @Override
    public void remove() {
      throw new UnsupportedOperationException();

    }


    @Override
    public void set(E e) {
      throw new UnsupportedOperationException();

    }


    @Override
    public void add(E e) {
      throw new UnsupportedOperationException();

    }

  }

  private class MySortedIterator implements Iterator<E> {
    private SortableList<E> sorted;
    private int nextIndex;

    public MySortedIterator(Comparator<E> c) {
      sorted = new SortableList<E>();// init variables?
      for (int i = 0; i < size; i++) {

        sorted.add(get(i));

      }
      sorted.bubbleSort(c);// add in bubble sort or call?
      nextIndex = 0;
      // copy all items from this into sorted(for int i=0; i<size ; i++) sorted.add(get[i])
      // sort sorted (bubble sort)
      // nextIndex=0

    }


    @Override
    public boolean hasNext() {
      return nextIndex < size;
    }


    @Override
    public E next() {
      if (hasNext() == true) {
        return sorted.get(nextIndex++);
      } else {
        throw new NoSuchElementException();
      }
      // if hasNext
      // return sorted.get[nextIndex++]
      // else throw exception
    }

  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean contains(Object o) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public <T> T[] toArray(T[] a) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean remove(Object o) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean containsAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean addAll(Collection<? extends E> c) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean removeAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean retainAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public void clear() {
    // TODO Auto-generated method stub
    
  }


  @Override
  public int lastIndexOf(Object o) {
    // TODO Auto-generated method stub
    return 0;
  }


  @Override
  public ListIterator<E> listIterator() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public ListIterator<E> listIterator(int index) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    // TODO Auto-generated method stub
    return null;
  }

}

// private class MyListIterator implemnts ListIterator<E>{
// private dlinkednode nextNode;
// private int nextIndex;

// public MyListIterator(){
// nextIndex=0
// nextNode = traverse[0];
// }

// has next{
// nextNode is not null
// }

// E next(){
// if hasnext
// remmebr item at nextNode
// update nextnode
// update nextIndex
// return item
// if no next, throw nosuchelementexception
// }

// hasprevious{
// if there is a prev of nextNode or nextNode is null
// }

// E prevoius(){
// if hasPrevoius
// remembr item at nextNode.prev
// update nextNode
// update nextIndex
// return item
// else throw exception
// }

// prevoiusindex{
// return nextIndex-1
// }

// get set remove throws new unsupported

// private class MySortedIterator implements Iterator<E>{
// private sortablelist<E> sorted;
// private int nextIndex;

// public MySortedIterator(Comparator<E> c){
// init sorted
// copy all items from this into sorted(for int i=0; i<size ; i++) sorted.add(get[i])
// sort sorted (bubble sort)
// nextIndex =0
// }

// hasNext{
// return nextIndex< size
// }

// E next(){
// if hasNext
// return sorted.get[nextIndex++]
// else throw exception
// }

// }

// }
