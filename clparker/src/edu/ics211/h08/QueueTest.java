/**
 * Test case for PacketQueue.
 * 
 * @author Cory Parker
 */

package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Case for PacketQueue.
 * 
 * @author Cory Parker.
 *
 */
class QueueTest {
  public PacketQueue queue;

  /**
   * Setup for test queue.
   * 
   * @author Cory Parker.
   * 
   * @throws java.lang.Exception for exceptions.
   */
  @BeforeEach
  void setUp() throws Exception {
    queue = new PacketQueue();
  }


  public void addEight() {
    for (int i = 2; i < 10; i++) {
      this.queue.add(new Packet(i));
    }
  }


  public void offerEight() {
    for (int i = 2; i < 10; i++) {
      this.queue.offer(new Packet(i));
    }
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#size()}.
   */
  @Test
  void testSize() {
    // test empty queue
    assertEquals(queue.size(), 0);
    // test queue with one element
    this.queue.add(new Packet(1));
    assertEquals(queue.size(), 1);
    // queue with one empty space
    addEight();
    assertEquals(queue.size(), 9);
    // queue that is full
    this.queue.add(new Packet(10));
    assertEquals(queue.size(), 10);
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#PacketQueue()}.
   */
  @Test
  void testPacketQueue() {
    PacketQueue tstQueue = new PacketQueue();
    assertNotNull(tstQueue);
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#offer(edu.ics211.h08.Packet)}.
   */
  @Test
  void testOffer() {
    // test empty queue
    assertEquals(this.queue.offer(new Packet(0)), true);
    assertEquals(this.queue.size(), 1);
    // test queue with one element
    // queue with one empty space
    offerEight();
    assertEquals(this.queue.size(), 9);
    // queue that is full
    assertEquals(this.queue.offer(new Packet(10)), true);
    this.queue.offer(new Packet(11));
    assertEquals(this.queue.size(), 10);

  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#add(edu.ics211.h08.Packet)}.
   */
  @Test
  void testAddPacket() {
    // test empty queue
    assertEquals(this.queue.add(new Packet(1)), true);
    assertEquals(this.queue.size(), 1);
    // test queue with one element
    // queue with one empty space
    addEight();
    assertEquals(this.queue.size(), 9);
    // queue that is full
    assertEquals(this.queue.add(new Packet(10)), true);

    try {
      this.queue.add(new Packet(11));
      fail("should throw error on full");
    } catch (IllegalStateException ise) {
      assert true;
    }
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#poll()}.
   */
  @Test
  void testPoll() {
    // test empty queue
    assertEquals(this.queue.poll(), null);
    // test queue with one element
    this.queue.add(new Packet(1));
    assertEquals(this.queue.poll().toString(), new Packet(1).toString());
    // queue with one empty space
    addEight();
    assertEquals(this.queue.poll().toString(), new Packet(2).toString());
    // queue that is full
    this.queue.add(new Packet(8));
    this.queue.add(new Packet(9));
    this.queue.add(new Packet(10));
    assertEquals(this.queue.poll().toString(), new Packet(3).toString());

  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#remove()}.
   */
  @Test
  void testRemove() {
    // test empty queue
    try {
      this.queue.remove();
    } catch (NullPointerException npe) {
      assert true;
    }
    // test queue with one element
    this.queue.add(new Packet(1));
    assertEquals(this.queue.remove().toString(), new Packet(1).toString());
    // queue with one empty space
    addEight();
    assertEquals(this.queue.remove().toString(), new Packet(2).toString());
    // queue that is full
    this.queue.add(new Packet(8));
    this.queue.add(new Packet(9));
    this.queue.add(new Packet(10));
    assertEquals(this.queue.remove().toString(), new Packet(3).toString());
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#peek()}.
   */
  @Test
  void testPeek() {
    // test empty queue
    assertEquals(this.queue.peek(), null);
    // test queue with one element
    this.queue.add(new Packet(1));
    assertEquals(this.queue.peek().toString(), new Packet(1).toString());
    assertEquals(this.queue.size(), 1);
    // queue with one empty space
    addEight();
    assertEquals(this.queue.peek().toString(), new Packet(1).toString());
    // queue that is full
    this.queue.add(new Packet(10));
    assertEquals(this.queue.peek().toString(), new Packet(1).toString());
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#element()}.
   */
  @Test
  void testElement() {
    // test empty queue
    try {
      this.queue.element();
    } catch (NullPointerException npe) {
      assert true;
    }
    // test queue with one element
    this.queue.add(new Packet(1));
    assertEquals(this.queue.element().toString(), new Packet(1).toString());
    assertEquals(this.queue.size(), 1);
    // queue with one empty space
    addEight();
    assertEquals(this.queue.element().toString(), new Packet(1).toString());
    // queue that is full
    this.queue.add(new Packet(10));
    assertEquals(this.queue.element().toString(), new Packet(1).toString());
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#iterator()}.
   */
  @Test
  void testIterator() {
    Iterator<Packet> itr = queue.iterator();
    // test empty queue
    assertEquals(itr.hasNext(), false);
    // test queue with one element
    this.queue.add(new Packet(1));
    assertEquals(itr.hasNext(), true);
    itr.next();
    assertEquals(itr.hasNext(), false);
    // queue with one empty space
    addEight();
    assertEquals(itr.hasNext(), true);
    // queue that is full
    this.queue.add(new Packet(1));
    assertEquals(itr.hasNext(), true);
  }


  /**
   * Test method for {@link edu.ics211.h08.PacketQueue#toString()}.
   */
  @Test
  void testToString() {
    this.queue.add(new Packet(1));
    assertEquals(this.queue.peek().toString(), new Packet(1).toString());
  }

}
