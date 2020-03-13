/*
 * Copyright (c) 2017 - 2018 ICORE Software Development LLP
 * http://icode.kz
 */
package org.test.springsandbox.test.anything;

import java.util.HashMap;
import java.util.Map;

public class Transliteration {

    public static void main(String[] args) {
        System.out.println(transliterate(" во поле берЁза стояла, люли сырые колымаги стояла"));
    }

    private static String transliterate(String message){
        char[] abcCyr =   {'-', ' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц', 'ч', 'ш','щ',   'ъ','ы','ь','э', 'ю','я', 'А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ',   'Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',  'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {"_", "_","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","y", "","e","ju","ja","а","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "", "y", "","e","ju","ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        Map<Character, String> mapper = new HashMap<>();
        for (int i = 0; i < abcCyr.length; i++) {
            mapper.put(abcCyr[i], abcLat[i]);
        }

        String trimmed = message.trim();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < trimmed.length(); i++) {
            String letter = mapper.getOrDefault(trimmed.charAt(i), "");
            builder.append(letter);
        }

        return builder.toString();
    }
}
