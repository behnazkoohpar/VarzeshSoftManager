package com.eram.manager.utils;

public class Luhn {
    /**
     * validate the given card-number with the luhn mod10 algorithm
     */
    public static boolean luhn(String card) {
        int total = 0; // sum of all digits
        int v; // variable to hold the numeric value of the "current" char
        int i = card.length(); // current position in the String
		/*
		 [action] represents what we're currently doing with the chars we read
		------
		 action=true: odd character
		 action=false: even character
		*/
        boolean action = false;

        while(0 < i--) {
            // get the numeric value of the char
            v = (int)(card.charAt(i) - 48);
            // if we're an even number of steps into this loop
            if ( action ) {
                v *= 2; // double the value of [v]
                // and if [v] is now a double-digit number
                if (v > 9) {
                    // reduce it by 9, effectively adding the two digits
                    v -= 9;
                }
            }

            // increase [total] by
            total += v;
            // toggle the value of [action]
            action = !action;
        }

		/*
		 return whether [total] is divisible by 10
		*/
        return ((total % 10) == 0);
    }
}