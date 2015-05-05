package com.dct.server.dao.impl.util;

/**
 * Created by stukolov_m on 05.05.2015.
 */
public class SearchUtil {
    public static String advLuceneEscape(String str) {
        return str.trim().toLowerCase().replaceAll("\\*","\\\\*");
    }

}
