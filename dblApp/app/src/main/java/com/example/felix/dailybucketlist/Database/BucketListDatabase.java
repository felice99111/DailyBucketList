package com.example.felix.dailybucketlist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.Goals.Goal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BucketListDatabase extends SQLiteOpenHelper {

    public static BucketListDatabase instance = null;

    private BucketListDatabase(Context context) {
        super(context, Config.DB_NAME, null, Config.VERSION);
    }

    //mit Hilfe der Methode ist keine konkrete Instanz der BucketListDatabase nötig
    public static BucketListDatabase getInstance(Context context) {
        if(instance == null) {
            instance = new BucketListDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Database kann keinen boolean speichern, deshalb integer mit 0 (false) und 1 (true)
        String createQuery = "create table " + Config.TABLE_NAME + " (" + Config.ID_COLUMN + " integer primary key autoincrement, " + Config.NAME_COLUMN + " text not null, " + Config.GOAL_REACHED_COLUMN + " integer default null, " + Config.DATE_COLUMN + " integer default null);";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Methode vermutlich nicht zielführend! Versionsprobleme + aktuell wird die bestehende Datenbank jeweils gelöscht und neu erstellt -> effizienter gestalten!
        String dropTable = "DROP TABLE IF EXISTS " + Config.TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public Goal createGoal(Goal goal) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Config.NAME_COLUMN, goal.getName());
        values.put(Config.GOAL_REACHED_COLUMN, goal.isReached() ? 1 : 0);
        values.put(Config.DATE_COLUMN, goal.getDate() == null ? null : goal.getDate().getTimeInMillis()/1000);

        long newID = database.insert(Config.TABLE_NAME, null, values);

        database.close();

        return readGoal(newID);
    }

    public Goal readGoal(long id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(Config.TABLE_NAME, new String[]{Config.ID_COLUMN, Config.NAME_COLUMN, Config.GOAL_REACHED_COLUMN, Config.DATE_COLUMN}, Config.ID_COLUMN + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        //SELECT ID, NAME, GOAL_REACHED, DATE WHERE ID = ?
        Goal goal = null;
        //wenn Cursor erstellt wurde und Daten vorhanden sind
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            goal = new Goal(cursor.getString(cursor.getColumnIndex(Config.NAME_COLUMN)));
            goal.setId(cursor.getLong(cursor.getColumnIndex(Config.ID_COLUMN)));

            Calendar calendar = null;

            if(!cursor.isNull(cursor.getColumnIndex(Config.DATE_COLUMN))) {
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(Config.DATE_COLUMN)) * 1000);
            }

            goal.setDate(calendar);
        }
        database.close();
        return goal;
    }

    public List<Goal> readAllGoals() {
        List<Goal> goals = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + Config.TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do{
                Goal goal = readGoal(cursor.getLong(cursor.getColumnIndex(Config.ID_COLUMN)));
                if(goal != null) {
                    goals.add(goal);
                }
            }while(cursor.moveToNext());
        }
        database.close();
        return goals;
    }

    public Goal updateGoal(Goal goal) {
        SQLiteDatabase database = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Config.NAME_COLUMN, goal.getName());
        values.put(Config.GOAL_REACHED_COLUMN, goal.isReached() ? 1 : 0);
        values.put(Config.DATE_COLUMN, goal.getDate() == null ? null : goal.getDate().getTimeInMillis()/1000);

        database.update(Config.TABLE_NAME, values, Config.ID_COLUMN + " = ?", new String[]{String.valueOf(goal.getId())});
        database.close();

        return this.readGoal(goal.getId());
    }

    public void deleteGoal(Goal goal) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Config.TABLE_NAME, Config.ID_COLUMN + " = ?", new String[]{String.valueOf(goal.getId())});
        database.close();
    }

    public void deleteAllGoals() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + Config.TABLE_NAME);
        database.close();
    }
}
