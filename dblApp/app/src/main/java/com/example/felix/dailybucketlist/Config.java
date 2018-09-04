package com.example.felix.dailybucketlist;

public class Config {

    //Klasse zur Speicherung aller Konstanten

    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    //Constants for Database

    public static final String DB_NAME = "bucket_list_db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "bucket_list_table";
    public static final String ID_COLUMN = "ID";
    public static final String NAME_COLUMN = "NAME";
    public static final String GOAL_REACHED_COLUMN = "GOAL_REACHED";
    public static final String DATE_COLUMN = "DATE";

    //Constants for Notification

    public static final String CHANNEL_ID = "personal_notification";
    public static final CharSequence CHANNEL_NAME = "enter_goal_reminder";
    public static final String CHANNEL_DESCRIPTION = "";
    public static final int NOTIFICATION_ID = 1;
    public static final String ALARM_PREFERENCES = "alarm_preferences";
    public static final String ALARM_PREFERENCES_KEY = "alarm_key";
    public static final boolean ALARM_SET = true;
    public static final boolean ALARM_NOT_SET = false;

    //Constants for Tutorial

    public static final String TUTORIAL_PICTURE_01 = "tut1";
    public static final String TUTORIAL_PICTURE_02 = "tut2";
    public static final String TUTORIAL_PICTURE_03 = "tut3";
    public static final String TUTORIAL_PICTURE_04 = "tut4";
    public static final String TUTORIAL_TEXT_01 = "Organisiere deine Aufgaben und behalte den Überblick. Per Swipe wechselst du zwischen den Wochen.";
    public static final String TUTORIAL_TEXT_02 = "Über das '+' Symbol erstellst du neue Aufgaben.";
    public static final String TUTORIAL_TEXT_03 = "Wähle eine Aufgabe aus und markiere sie als 'erfüllt'.";
    public static final String TUTORIAL_TEXT_04 = "Lasse dir eine Notification zur gewünschten Uhrzeit senden.";
    public static final String TUTORIAL_EXTRA_NAME = "calling-activity";
    public static final String TUTORIAL_EXTRA_VALUE = "tabbing";

}