package com.eram.manager.utils;

/**
 * Created by Shahab on 6/24/2017.
 */

public class NumberFormatter {


    private static final String[] persianNumbers = { "۰" , "۱" , "۲" , "۳" , "۴" , "۵" ,"۶" ,"۷" , "۸", "۹" };
    private static int digitCount = 0;

    public static String format(long number){
        digitCount = 0 ;
        String result = "" ;
        while(number != 0){
            result = persianNumbers[(int) (number%10)] + result;
            number /= 10 ;
            digitCount++ ;
        }
        result = separator(result);
        return result;
    }

    public static String convertDigitsToPersian(String str){
        String result = str ;
        result = result.replace('0' , '۰');
        result = result.replace('1' , '۱');
        result = result.replace('2' , '۲');
        result = result.replace('3' , '۳');
        result = result.replace('4' , '۴');
        result = result.replace('5' , '۵');
        result = result.replace('6' , '۶');
        result = result.replace('7' , '۷');
        result = result.replace('8' , '۸');
        result = result.replace('9' , '۹');
        return result ;
    }

    public static String convertDigitsToPersianPrice(String str){
        String result = convertDigitsToPersian(str);
        result = separator(result);
        return result ;
    }

    public static String convertPriceToNumber(String str){
        String result = str.replace("," , "") ;
        result = result.replace('۰' , '0');
        result = result.replace('۱' , '1');
        result = result.replace('۲' , '2');
        result = result.replace('۳' , '3');
        result = result.replace('۴' , '4');
        result = result.replace('۵' , '5');
        result = result.replace('۶' , '6');
        result = result.replace('۷' , '7');
        result = result.replace('۸' , '8');
        result = result.replace('۹' , '9');
        return result ;
    }

    private static String separator(String number) {
        StringBuilder result = new StringBuilder(number) ;
        int count = 0 ;
        for(int i = number.length()-1 ; i > 0 ; i--){
            count++ ;
            if(count % 3 == 0) {
                result.insert(i , ",");
            }
        }
        return result.toString() ;
    }

}
