package com.example.swapnilgupta.sharechat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by swapnilgupta on 03/09/2017.
 */

public class Utils {

    final static SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    public static final String getDDMMYYYYDate(long time) {
        Date d = new Date(time);
        return DD_MM_YYYY.format(d);
    }
}
