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
        String option = "";
        int opt = 0;

        while (opt != 6) {
            System.out.println("----------------------------\nCOMP 2080 Assignment 2\n----------------------------\nSelect an option:");
            System.out.println("1. Display Palindromes");
            System.out.println("2. Add Palindrome");
            System.out.println("3. Find Palindrome");
            System.out.println("4. Reverse String");
            System.out.println("5. Extra: Check Anagram");
            System.out.println("6. Exit Program");

            System.out.print("Enter an option: ");
            try {
                option = keyboard.readLine();
                opt = Integer.parseInt(option);
                System.out.println("----------------------------");

                if (opt < 1 || opt > 6) {
                    throw new InvalidOptionException();
                }

                switch (opt) {
                    case 1:
                        funcDisplayPalindromes();
                        break;
                    case 2:
                        funcAddPalindromes();
                        break;
                    case 3:
                        funcFindPalindromes();
                        break;
                    case 4:
                        funcReverseString();
                        break;
                    case 5:
                        funcAnagram();
                        break;
                    case 6:
                        System.exit(0);
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (InvalidOptionException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    /*------------------------*/
    /* PROGRAM MAIN FUNCTIONS */
    /*------------------------*/

    public static void funcDisplayPalindromes() {
        Queue plist = new LinkedList();

        plist = getPalindromes();

        System.out.println("Palindrome list:");
        while (plist.peek() != null) {
            System.out.print(plist.poll().toString() + " ");
        }
        System.out.print("\n");
        enterToContinue();
    }

    public static void funcAddPalindromes() {
        System.out.print("Enter a palindrome: ");
        try {
            String palindrome = keyboard.readLine();
            if (isPalindrome(palindrome)) {
                FileWriter fwriter = new FileWriter("palindrome.txt", true);
                BufferedWriter output = new BufferedWriter(fwriter);
                output.write(palindrome); // write to file
                output.close();
                System.out.println("The input \"" + palindrome + "\" is a palindrome, so it was added to the palindrome list.");
            } else {
                System.out.println("The input \"" + palindrome + "\" is not a palindrome, so it was not added to the palindrome list.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        enterToContinue();
    }

    public static void funcFindPalindromes() {
        LinkedList p = new LinkedList();
        try {
            FileInputStream fstream = new FileInputStream("palindrome.txt");
            DataInputStream input = new DataInputStream(fstream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            if (p.isEmpty()) {
                while ((line = reader.readLine()) != null) {
                    p.add(line);
                }
                input.close();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Sort the list
        p = sortList(p);

        // Search the list
        try {
            System.out.print("Enter a palindrome to search for: ");
            String query = keyboard.readLine();

            if (isPalindrome(query)) {
                if (searchList(p, query)) {
                    System.out.println("The palindrome \"" + query + "\" was found in the list.");
                } else {
                    System.out.println("The palindrome \"" + query + "\" was not found.");
                }
            } else {
                System.out.println("The query is not a palindrome.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        enterToContinue();
    }

    public static void funcReverseString() {
        Stack pstack = new Stack();
        System.out.print("Enter a string to reverse: ");
        String line = null;
        try {
            line = keyboard.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        pstack = reverseString(line);

        System.out.print("\n");
        enterToContinue();
    }

    public static void funcAnagram() {
        try {
            System.out.print("Enter the first string to test: ");
            String first = keyboard.readLine();
            System.out.print("Enter the second string to test: ");
            String second = keyboard.readLine();
            if (isAnagram(first, second)) {
                System.out.println("\""+second + "\" is an anagram of \"" + first + "\".");
            } else {
                System.out.println("\""+second + "\" is not an anagram of \"" + first + "\".");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        enterToContinue();
    }

    public static boolean isAnagram(String p, String q) {
        p = p.replaceAll("\\s", "");
        p = p.toLowerCase();
        char[] pcharArray = p.toCharArray();
        Arrays.sort(pcharArray);
        String s1 = new String(pcharArray);

        q = q.replaceAll("\\s", "");
        q = q.toLowerCase();
        char[] qcharArray = q.toCharArray();
        Arrays.sort(qcharArray);
        String s2 = new String(qcharArray);

        if (s1.equals(s2)) {
            return true;
        } else {
            return false;
        }
    }
    /*-------------------------*/
    /* SUPPLEMENTARY FUNCTIONS */
    /*-------------------------*/

    public static void enterToContinue() {
        System.out.print("----------------------------\nPress <ENTER> to continue.");
        try {
            keyboard.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
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
        System.out.print("Your input, reversed: ");
        for (int i = 0; i < line.length; i++) {
            pstack.addElement(line[i]);
        }

        int stackSize = pstack.size();
        for (int i = 0; i < stackSize; i++) {
            System.out.print(pstack.pop().toString() + " ");
        }
        return pstack;
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

    public static LinkedList sortList(LinkedList l) {
//        LinkedList q = new LinkedList();
//        
//        for (int i = 0; i < l.size(); i++) {
//            if (q.size() == 0) {
//                q.add(0, l.removeFirst());
//            } else if (q.get(0) >) {
//                
//            }
//        }
        Object[] asdf = l.toArray();
        Arrays.sort(asdf);

        LinkedList q = new LinkedList();
        q.addAll(Arrays.asList(asdf));

        return q;
    }

    private static boolean searchList(LinkedList p, String q) {
        int first, last, mid = 0;
        boolean found = false;
        first = 0;
        last = p.size() - 1;
        while ((!found) && (first <= last)) {
            mid = first + (last - first) / 2;
            //if (p.get(mid) == q) {
            if (q.compareTo(p.get(mid).toString()) == 0) {
                found = true;
            } else if (q.compareTo(p.get(mid).toString()) < 0) {
                last = mid - 1;
            } else if (q.compareTo(p.get(mid).toString()) > 0) {
                first = mid + 1;
            }
        }
        return found;
    }
}

class InvalidOptionException extends Exception {

    public InvalidOptionException() {
        super("Invalid option chosen.");
    }

    public InvalidOptionException(String str) {
        super(str);
    }
}
