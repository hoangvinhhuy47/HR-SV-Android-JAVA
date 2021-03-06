package com.example.myapplication.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GobalUtils {
    public static String convertDateToString(Date date, String format) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    public static String convertDateTime(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm");
        return simpleDateFormat.format(date);
    }

    public static String convertDate(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(date);
    }
    public static String convertDateShowScreen(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }
    public static String convertDateShowScreen1(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm");
        return simpleDateFormat.format(date);
    }
    public static String convertDateShowScreen2(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        return simpleDateFormat.format(date);
    }
    public static String convertTimeShowScreen(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }


    public static Date convertStringToDate(String dateString) {
        if (dateString == null)
            return null;

        if (dateString.length() >= 18)
            dateString = dateString.substring(0, 18);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String coverStringToDateOfWeek(Date date){
        DateFormat format = new SimpleDateFormat("EEEE",Locale.ENGLISH);
        return format.format(date);
    }
    public  static  String DateEtoDateVN(String daylst){
        if (daylst.equals("Monday")==true){
            return "T2";
        }
        else if (daylst.equals("Tuesday")==true){
            return "T3";
        }
        else if (daylst.equals("Wednesday")==true){
            return "T4";
        }
        else if (daylst.equals("Thursday")==true){
            return "T5";
        }
        else if (daylst.equals("Friday")==true){
            return  "T6";
        }
        else if (daylst.equals("Sunday")==true){
            return "CN";
        }
        else
            return "T7";

    }
    public  static  String MaritalStatus(int stt){
        if (stt == 1){
            return "?????c Th??n";
        }
        else if (stt == 2){
            return "K???t H??n";
        }
        else if (stt == 3){
            return "???? ly d???";
        }
        else
            return "Ch??a r??";

    }

    public static String convertStatusLeaveAplication(int stt){
        if (stt==1) {
            return "Ch??? G???i";
        }
        else if (stt==2){
            return "Ch??? Duy???t";
        }
        else if (stt==3){
            return "Cho Ph??p";
        }
        return "T???  Ch???i";
    }
    public static int converStringToStatus(String stt){
        if (stt.equals("Ngh??? Ph??p")==true) {
            return 1;
        }
        else if (stt.equals("??i Tr???")==true){
            return 2;
        }
        else if (stt.equals("V??? S???m")==true){
            return 3;
        }
        return 0;
    }
    public static String converStatustoString(int stt){
        if (stt==1) {
            return "Ngh??? Ph??p";
        }
        else if (stt==2){
            return "??i Tr???";
        }
        else if (stt==3){
            return "V??? S???m";
        }
        else if (stt==4){
            return "OT";
        }
        return null;
    }
//    public static String getCovertDateZoneToString (Date date){
//       val
//
//    }
    public static String ChangeSTTLeave(String text){
        if (text.equals("Ch??? Duy???t")== true){
            return "CD";
        }
        else if(text.equals("Ch??? G???i")==true){
            return "CG";
        }
        else if (text.equals("T??? Ch???i")==true){
            return "TC";
        }
        else if (text.equals("Ch???p nh???n")==true){
            return "CN";
        }
        return "";

    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    public static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }
    public static String ConverTaskStatus(int taskStatusID){
        if (taskStatusID == 1) {
            return  "Ch??? Ch???p Nh???n";
        } else if (taskStatusID == 2) {
            return  "Ch???p Nh???n";
        } else if (taskStatusID == 3) {
           return  "T??? Ch???i";
        }
        return "";
    }
    public  static  String ConverSttEquitment(int stt){
        if (stt==1){
            return "??ang Ch???";
        }
        else if (stt ==2){
            return "???? Nh???n";
        }
        else if (stt == 3){
            return "T??? ch???i";
        }
        else {
            return "";
        }
    }
    public static String ConverTaskPriority(int taskPriorityID){
        if (taskPriorityID == 1) {
            return   "??u Ti??n:Cao Nh???t";
        } else if (taskPriorityID == 2) {
            return  "??u Ti??n:Cao";
        } else if (taskPriorityID == 3) {
            return  "??u Ti??n:Trung B??nh";
        } else if (taskPriorityID== 4) {
           return  "??u Ti??n:Th???p";
        } else if (taskPriorityID== 5) {
            return  "??u Ti??n:Th???p Nh???t";
        }
        return "";
    }
    public static int ChangeToTime(int hours, int minuter, int second){
        return  ((((hours*60)  + minuter)*60) + second)*1000;
    }

//    public void hideTopBar() {
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }
//    public void hideTopBar() {
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }
//
//    private Window getWindow() {
//        return getWindow();
//    }
}
