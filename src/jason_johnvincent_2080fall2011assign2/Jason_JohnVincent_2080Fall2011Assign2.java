/*
 * 100726948 - Jason Recillo - Section B
 * --------- - John Vincent Adame - Section A
 */
package jason_johnvincent_2080fall2011assign2;

import java.util.*;
import java.io.*;

public class Jason_JohnVincent_2080Fall2011Assign2 {

    static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        //DisplayPalindromes
        Queue plist = new LinkedList();

        plist = getPalindromes();

        System.out.println("Palindrome list:");
        while (plist.peek() != null) {
            System.out.print(plist.poll().toString() + " ");
        }
        //EndDisplayPalindromes
        
        //ReverseString
        Stack pstack = new Stack();
        System.out.print("\n\nEnter a string to reverse: ");
        String line = null;
        try {
            line = keyboard.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        pstack = reverseString(line);
        //EndReverseString
    }

    public static Queue getPalindromes() {
        Queue plist = new LinkedList();
        try {
            FileInputStream fstream = new FileInputStream("palindrome.txt");
            DataInputStream input = new DataInputStream(fstream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;

            if (plist.isEmpty()) {
                while ((line = reader.readLine()) != null) {
                    plist.add(line);
                }
                input.close();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return plist;

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
