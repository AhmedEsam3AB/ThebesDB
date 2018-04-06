package com.a3abcarinho.ahmed.thebesdb;

/**
 * Created by ahmed on 07/10/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
public class SqliteDatabase extends SQLiteOpenHelper {
    private    static final int DATABASE_VERSION =    6;
    private    static final String    DATABASE_NAME = "student";
    private    static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ID = "_id";
    private static final   String COLUMN_STUDENTNAME = "studentname";
    private static final String COLUMN_CODE = "code";
    private static final   String COLUMN_GRADE = "grade";
    private static final String COLUMN_GPA = "gpa";
    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE    TABLE " + TABLE_STUDENTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_STUDENTNAME + " TEXT," + COLUMN_CODE + " INTEGER," + COLUMN_GRADE + " TEXT," + COLUMN_GPA + " REAL" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);


    }
    public List<student> listStudents() {
        String sql = "select * from " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        List<student> storestudents = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int code = Integer.parseInt(cursor.getString(2));
                 String grade = cursor.getString(3);
                double gpa = Double.parseDouble(cursor.getString(4));
                storestudents.add(new student(id, name, code, grade , gpa));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storestudents;
    }

    public void addStudent(student student){
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENTNAME, student.getName());
        values.put(COLUMN_CODE, student.getCode());
        values.put(COLUMN_GRADE, student.getGrade());
        values.put(COLUMN_GPA, student.getGpa());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENTS, null, values);
    }
    public void updateStudent(student student){
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENTNAME, student.getName());
        values.put(COLUMN_CODE, student.getCode());
        values.put(COLUMN_GRADE, student.getGrade());
        values.put(COLUMN_GPA, student.getGpa());;
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_STUDENTS, values, COLUMN_ID    + "    = ?", new String[] { String.valueOf(student.getId())});
    }


    public void deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, COLUMN_ID    + "    = ?", new String[] { String.valueOf(id)});
    }


}