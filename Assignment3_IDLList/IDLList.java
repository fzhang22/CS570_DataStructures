package com.company;

import java.util.ArrayList;

public class IDLList<E> {

    /**
     * inner class Node<E> should be declared
     * @param <E>
     */
    private static class Node<E> {

        // data fields for private inner class Node<E>
        private E data;
        private Node<E> next;
        private Node<E> prev;

        // a constructor holds "elem"
        private Node(E elem) {
            this.data = elem;
        }

        // a constructor that creates a node holding "elem", with "next" as next
        // and "prev" as prev
        public Node(E elem, Node<E> prev, Node<E> next) {
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    // data fields for IDLList<E>
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    /**
     * create an empty double-linked list
     */
    public IDLList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<>();
    }

    /**
     * add "elem" at position "index"
     */
    public boolean add(int index, E elem) {

        // if index is out of bound of arrayList indices, then an exception
        // will be thrown
        if (index < 0 || index >= indices.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(index));

        }

        if (index == 0) {
            // add element at head
            add(elem);

            return true;

        } else {
            // get ith element in arrayList by using get(i)
            Node<E> node = indices.get(index);

            // create a new item that will be added into LinkedList
            Node<E> newItem = new Node<>(elem);

            // insert newItem into a double-linked list, the position is in front
            // of node's ith position
            newItem.next = node;
            newItem.prev = node.prev;
            node.prev.next = newItem;
            node.prev = newItem;
            size++;

            // modify the arrayList corresponding to the modified LinkedList
            indices.add(index, newItem);

            return true;


        }

    }

    // add "elem" at the head, it becomes the first element of the list
    public boolean add(E elem) {

        if (head == null) {
            head = new Node<>(elem);
            tail = head;
            size++;

            // add the head onto indices accordingly
            indices.add(0,head);

            return true;

        } else {

            // if head != null, then insert "firstElem" before "head" in the list
            Node<E> firstElem = new Node<>(elem);

            firstElem.next = head;
            head.prev = firstElem;
            head = firstElem;
            size++;

            // add "firstElem" onto first position 0 in indices
            indices.add(0, firstElem);

            return true;

        }
    }

    /*
    add "elem" as the new last element of the list
     */
    public boolean append(E elem) {

        // if head == null, head and tail all point to new created head
        if (head == null) {
            add(elem);
            return true;
        }

        // if head != null, then lastElem is created and is inserted after the tail
        Node<E> lastElem = new Node<>(elem);

        tail.next = lastElem;
        lastElem.prev = tail;
        tail = lastElem;
        size++;

        // add lastElem onto indices accordingly
        indices.add(lastElem);
        return true;
    }

    /*
    return the object at position index from the head
     */
    public E get(int index) {

        return indices.get(index).data;

    }

    /*
    return the object at the head
     */
    public E getHead () {

        return head.data;
    }

    /*
    return the object at the tail
     */
    public E getLast() {

        return tail.data;
    }

    /*
    return the list size
     */
    public int size() {

        return indices.size();
    }

    /*
    remove the element at the head
     */
    public E remove() {

        if (head == null) return null;

        // head.data is stored at temp
        Node<E> temp = head;

        // head points to the next element
        head = head.next;
        head.prev = null;
        size--;
        indices.remove(temp);

        return temp.data;

    }

    /*
    removes and returns the element at the tail
     */
    public E removeLast() {

        if (tail == null) return null;

        Node<E> temp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        indices.remove(temp);

        return temp.data;

    }

    /*
    remove and return the element at the index
     */
    public E removeAt(int index) {

        // if index is out of bound of arrayList indices, then an exception
        // will be thrown
        if (index < 0 || index >= indices.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(index));

        }

        if (index == 0) return remove();
        if (index == indices.size() - 1) return removeLast();

        // removeNode is Node at index position in indices
        Node<E> removeNode = indices.get(index);
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        size--;

        // remove the Node in indices accordingly
        indices.remove(removeNode);

        return removeNode.data;

    }

    /*
    removes the first occurrence of elem in the list and
    returns true. Return false if elem was not in the list
     */
    public boolean remove(E elem) {

        for (int i = 0; i < indices.size(); i++) {

            if (indices.get(i).data == elem) {
                removeAt(i);
                return true;
            }


        }

        return false;

    }

    /*
    print list in String format
    1 -> 3 -> 2
    " 1 3 2"
     */
    public String toString() {

        // loop through the linkedlist and return the print of the element
        Node cur = head;
        String res = "";
        while (cur != null) {
            res += " " + cur.data.toString();
            cur = cur.next;
        }

        return res;
    }


}
