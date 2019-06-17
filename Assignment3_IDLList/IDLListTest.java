package com.company;

public class IDLListTest {

    public static void main(String[] args) {

        // create an empty indexed double linked list, data type is Integer
        IDLList<Integer> idlList = new IDLList();

        // add element 10 at the head, 10 becomes the first element
        // result: [10]
        idlList.add(10);
        System.out.println("The first element of the list is: " + idlList.toString() + "\n");

        // add element 5 at the head, 5 becomes the first element
        // result: [5, 10]
        idlList.add(0,5);
        System.out.println("After add 5 at the head, the list becomes " + idlList.toString() + "\n");

        // add element 8 at index 1
        // result: [5, 8, 10]
        idlList.add(1,8);
        System.out.println("After add 8 at index 1, the list becomes " + idlList.toString() + "\n");


        // append element 30 at the end of the list
        // result: [5, 8, 10, 30]
        idlList.append(30);
        System.out.println("After append, the list is : " + idlList.toString() + "\n");

        // returns the object at position index 1 from the head
        // result: 8
        System.out.println("Current list is: " + idlList.toString());
        System.out.println("Get element of position 1 in list : " + idlList.get(1) + "\n");

        // return the object at the head
        // result: 5
        System.out.println("Current list is: " + idlList.toString());
        System.out.println("Get head object in list: " + idlList.getHead() + "\n");

        // return the object at the tail
        // result: 30
        System.out.println("Current list is: " + idlList.toString());
        System.out.println("Get tail object in list: " + idlList.getLast() + "\n");

        // return the list size
        System.out.println("The current list size is: " + idlList.size() + "\n");

        // remove and return element at the head
        System.out.println("Now the list is: " + idlList.toString());
        System.out.println("The removed head element is: " + idlList.remove());
        System.out.println("After remove, the current list is: " + idlList.toString() + "\n");

        // remove and return the element at the tail
        System.out.println("Now the list is: " + idlList.toString());
        System.out.println("The removed tail element is: " + idlList.removeLast());
        System.out.println("After remove, the current list is: " + idlList.toString() + "\n");

        // add two elements 15
        // current list: [8, 10 ,15, 15]
        idlList.append(15);
        idlList.add(2, 15);
        System.out.println("Now the list is: " + idlList.toString());

        // remove the first occurrence of 15
        // result: [8, 10, 15]
        idlList.remove(15);
        System.out.println("After removed the first occurrence of 15, now the list is: "
                + idlList.toString());

    }

}
