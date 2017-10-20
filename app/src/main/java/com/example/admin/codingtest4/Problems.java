package com.example.admin.codingtest4;

import java.util.Stack;

/**
 * Created by Admin on 10/20/2017.
 */

public class Problems {

    public static boolean checkOrder( String string ) {
        Stack<Character> stack  = new Stack<>();

        for(int i = 0; i < string.length(); i++) {
            char c = string.charAt( i );

            //check for an opening character. If so, push onto stack. if not, check if it's a closing
            //char, and if it matches the last opener on the stack.
            if(c == '[' || c == '(' || c == '{' ) {
                stack.push(c);
            } else if ( c == ')' ) {
                if( stack.isEmpty() || stack.pop() != '(' ) {
                    return false;
                }
            } else if ( c == '}' ) {
                if( stack.isEmpty() || stack.pop() != '{' ) {
                    return false;
                }
            } else if ( c == ']' ) {
                if( stack.isEmpty() || stack.pop() != '[' ) {
                    return false;
                }
            }
        }

        //make sure there aren't any left over chars in the stack.
        return stack.isEmpty();
    }

    public static boolean checkSub( String string, String sub, int i ) {
        if( i == 0 )
            return true;
        else if( string.indexOf( sub ) >= 0 ) {
            //check if any part of the sub string recurs
            int j = sub.indexOf( sub.charAt(0), 1);

            if( j >= 0 ) {
                //trim string leaving any recurring parts of sub so we can detect overlap.
                String nextString = string.substring(string.indexOf(sub) + j);
                return checkSub(nextString, sub, i - 1);
            } else
                //cut away everything up to the end of the substring.
                return checkSub( string.substring( string.indexOf( sub ) + sub.length()), sub, i-1);
        } else
            return false;
    }

    public static void main(String[] args) {
        System.out.println( checkOrder( "({})[]" ));
        System.out.println( checkOrder( "[]{]}[" ));
        System.out.println( checkOrder( "{a,b}(a,b,c)" ));
        System.out.println();
        System.out.println( checkSub( "catcowcat", "cat", 2 ));
        System.out.println( checkSub( "catcowcat", "cow", 2 ));
        System.out.println( checkSub( "catcowcat", "cow", 1 ));
        System.out.println( checkSub( "xxyxxyxxyxx", "xxyxx", 3 ));
        System.out.println( checkSub( "abxabxabxab", "abxab", 3 ));
    }
}
