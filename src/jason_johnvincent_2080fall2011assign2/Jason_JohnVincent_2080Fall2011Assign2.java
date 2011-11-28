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
        ProgramStart:
        System.out.println("COMP 2080 Assignment 2\n----------------------------\nSelect an option:");
        System.out.println("1. Display Palindromes");
        System.out.println("2. Add Palindrome");
        System.out.println("3. Find Palindrome");
        System.out.println("4. Reverse String");
        System.out.println("5. Extra");

        System.out.print("Enter an option: ");
        try {
            //String option = keyboard.readLine();
        }
//        //DisplayPalindromes
//        Queue plist = new LinkedList();
//
//        plist = getPalindromes();
//
//        System.out.println("Palindrome list:");
//        while (plist.peek() != null) {
//            System.out.print(plist.poll().toString() + " ");
//        }
//        //EndDisplayPalindromes
//        
//        //ReverseString
//        Stack pstack = new Stack();
//        System.out.print("\n\nEnter a string to reverse: ");
//        String line = null;
//        try {
//            line = keyboard.readLine();
//        } catch (IOException e) {
//            System.err.println("Error: " + e.getMessage());
//        }
//        pstack = reverseString(line);
//        //EndReverseString

        //AddPalindrome
        addPalindrome();
        //EndAddPalindrome
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

    public static void addPalindrome() {
        String palindrome = enterPalindrome();
        try {
            FileWriter fwriter = new FileWriter("palindrome.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String enterPalindrome() {
        System.out.print("Enter a palindrome: ");
        String palindrome = "";
        try {
            palindrome = keyboard.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            palindrome = enterPalindrome();
        }
        if (isPalindrome(palindrome)) {
            System.out.println(palindrome + " is a palindrome.");
        } else {
            System.out.println(palindrome + " is not a palinrome.");
        }

        return palindrome;
    }

    public static boolean isPalindrome(String p) {
        if (p.length() == 0 || p.length() == 1) {
            return true;
        }
        if (p.charAt(0) == p.charAt(p.length() - 1)) {
            return isPalindrome(p.substring(1, p.length() - 1));
        }
        return false;
    }
}
