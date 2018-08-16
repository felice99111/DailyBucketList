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
}