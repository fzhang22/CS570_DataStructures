package com.company;

// Student Name: Fang Zhang   CWID: 10433041

import java.util.Arrays;

public class BinaryNumber {

    // define the field: array
    private int data[];
    // define the field: overflow
    private boolean overflow;


    public BinaryNumber(int length) {
        data = new int[length];
        // the numbers of zero equals to length of array
        for (int i=0; i< length; i++) {
            data[i] = 0;
        }
    }

    public BinaryNumber(String str) {
        // define length of binary number equals
        // to the length of input String
        int length = str.length();
        data = new int[length];

        // return each character in Str to new
        // charArray based on its index in Str
        // Then covert each char into int type
        for (int i =0; i < length; i++) {
            char c = str.charAt(i);
            data[i] = Character.getNumericValue(c);
        }
    }

    public int getLength() {
        return data.length;
    }

    // if input of index is out of range, the warning
    // message will show up!
    public int getDigit(int index) {
        if (index < data.length && index >= 0) {
            return data[index];
        } else {
            System.out.println("The length of binary number is " +
                    data.length + ", index " + index + " is out of bound!");
            return -1;
        }
    }

    public void shiftR(int amount) {

        // Use "array.CopyOf" method to append amount of zero to the right side of data[]
        // e.g. when int amount = 3, [1,0,1,1] → [1,0,1,1,0,0,0]
        int[] shiftArray = Arrays.copyOf(data, data.length + amount);

        // shift original elements(those elements in data[]) to the right in shiftArray
        // e.g. original elements are [1,0,1,1], [1,0,1,1,0,0,0] → [1,0,1,1,0,1,1]
        // the shift increment is 3, shiftArray[i + 3】 = shiftArray[i]
        for (int i = data.length - 1; i >= 0; i--) {
            shiftArray[i + amount] = shiftArray[i];
        }

        for (int i = 0; i < amount; i++){
            shiftArray[i] = 0;
        }

        System.out.println("The shifted array is " + Arrays.toString(shiftArray));

    }

    public void add(BinaryNumber aBinaryNumber){
        int carry = 0;
        int index = data.length - 1;
        int sumOfDigit = 0;
        int decimal = 0;

        // check if the length of both arrays are equal or not
        if (data.length != aBinaryNumber.getLength()) {
            System.out.println("the lengths of binary numbers don't match!");
        } else {
            while (index >= 0) {
                sumOfDigit = data[index] + aBinaryNumber.getDigit(index) + carry;
                data[index] = sumOfDigit % 2;
                carry = sumOfDigit / 2;
                index--;
                }

            // calculate the result of addition
            for (int i = 0; i< data.length; i++) {
                decimal += data[i] * Math.pow(2, i);
            }

            if (carry == 1) {
                overflow = true;
            } else {
                overflow = false;
            }

            }
        System.out.println("After addition, the new array becomes: " +
                Arrays.toString(data));
        System.out.println("The addition result of new array is: " + decimal);

    }

    public String toString() {

        // append each element in data[] array to string in sequence
        String string = "";
        for (int i = 0; i < data.length; i++) {
            string += data[i];
        }

        // check if overflow is true, then return "overflow", otherwise return string
        if (overflow == true) {
            return "Overflow";
        } else {
            return string;
        }
    }

    // each element of decimal = array[index] * (2 ** index)
    // Then sum all of the elements
    public int toDecimal() {
        int decimal = 0;
        for (int i = 0; i< data.length; i++) {
            decimal += data[i] * Math.pow(2, i);
        }

        return decimal;
    }

    // set overflow equals to false
    public void clearOverflow() {
        overflow = false;
        System.out.println("Now overflow set to false!");
    }

}






