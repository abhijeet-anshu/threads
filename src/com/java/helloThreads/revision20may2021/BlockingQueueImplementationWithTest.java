package com.java.helloThreads.revision20may2021;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class BlockingQueueImplementationWithTest {

    private static final boolean DEBUG = true;

    final static Consumer<String> printer = s ->
    {
        if (BlockingQueueImplementationWithTest.DEBUG)
            System.out.println(s);
    };

    static class BQCustom<T>  {
        final int MAX_CAPACITY;

        private final T[] array;
        private int headPosition = 0;
        private int currSize = 0;

        private final Lock queueLock = new ReentrantLock();

        private final Condition notFull = queueLock.newCondition();
        private final Condition notEmpty = queueLock.newCondition();

       BQCustom ( int capacity) {
            MAX_CAPACITY = capacity;

           array = (T[]) new Object[MAX_CAPACITY];
        }

        /**
         * Blocking method
         * add element to end of queue
         * @param element
         * @return boolean - added or not
         */
        public boolean add(T element) {
            queueLock.lock();
            try {
                while (currSize==MAX_CAPACITY) {
                    notFull.await();
                }
                int putPosition =  (headPosition + currSize)%MAX_CAPACITY;
                array[putPosition] = element;
                currSize++;
                notEmpty.signal();
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
            return false;
        }

        /**
         * Blocking method
         * @return element on the front of queue
         */
        public T remove() {
            queueLock.lock();
            try {
                while (currSize==0) {
                    notEmpty.await();
                }
                currSize--;
                T elem =  array[(headPosition)];
                headPosition = (headPosition + 1)%MAX_CAPACITY;
                notFull.signal();
                return elem;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
            return null;
        }

        /**
         * blocking method
         * @return size of queue (just a best guess)
         */
        public int size() {
            queueLock.lock();
            try {
                return currSize;
            } finally {
                queueLock.unlock();
            }
        }

    } //end of blocking queue implementation


    static Runnable supplierImplementation(BQCustom<Integer> queue, int range) {
        IntConsumer consumer = (a) -> {
            printer.accept("---------await add---------");
            queue.add(a);
            printer.accept("input-" + a);
        };
        return ()->IntStream.rangeClosed(0, range).parallel().forEach(consumer);
    }

    static Runnable consumerImplementation(BQCustom<Integer> queue, int times) {
        IntConsumer consumer = (a) -> {
            printer.accept("---------await remove---------");
            int elem=queue.remove();
            printer.accept(elem + "-out");
        };
        return () -> IntStream.rangeClosed(0, times).parallel().forEach(consumer);
    }

    public static void main(String[] args) {
        BQCustom<Integer> myBQ = new BQCustom<Integer>( 30);
        final int times = 500;
        Thread consumerImpl = new Thread(consumerImplementation(myBQ, times));
        Thread supplierImpl =  new Thread(supplierImplementation(myBQ, times));
        ArrayBlockingQueue<Integer> abc = new ArrayBlockingQueue<>(100);
        supplierImpl.start();
        consumerImpl.start();
        try {
            supplierImpl.join();
            consumerImpl.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
