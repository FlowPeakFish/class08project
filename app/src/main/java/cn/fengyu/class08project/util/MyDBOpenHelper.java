package cn.fengyu.class08project.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    public MyDBOpenHelper(@Nullable Context context) {
        // super(context, "student.db", null, 1);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table stu_info (id integer primary key autoincrement, name varchar(20), department varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
