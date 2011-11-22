/*
 * 100726948 - Jason Recillo - Section B
 * --------- - John Vincent Adame - Section A
 */
package jason_johnvincent_2080fall2011assign2;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jason_JohnVincent_2080Fall2011Assign2 {

    static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        //ReverseString
        Stack pstack = new Stack();
        System.out.print("Enter a palindrome: ");
        String line = null;
        try {
            line = keyboard.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        pstack = reverseString(line);
        //EndReverseString
    }

    public static Stack reverseString(String str) {
        Stack pstack = new Stack();
        char[] line = str.toCharArray();
        for (int i = 0; i < line.length; i++) {
            pstack.addElement(line[i]);
        }

        int stackSize = pstack.size();
        for (int i = 0; i < stackSize; i++) {
            System.out.print(pstack.pop().toString() + " ");
        }
        return pstack;
    }
}