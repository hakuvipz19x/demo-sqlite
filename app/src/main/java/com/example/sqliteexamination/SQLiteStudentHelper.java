package com.example.sqliteexamination;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqliteexamination.model.Student;

import java.util.ArrayList;

public class SQLiteStudentHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final int DATABASE_VERSION = 1;
    public SQLiteStudentHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "name text, " +
                "age int," +
                "mark real," +
                "gender boolean," +
                "dateCreated text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("age", student.getAge());
        values.put("mark", student.getMark());
        values.put("gender", student.isGender());
        values.put("dateCreated", student.getDateCreated());
//        System.out.println(student.getAge() + " " + student.getMark());
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("student", null, values);
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> listStudent = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", null, null, null,
                null, null, null);

        if (cursor != null & cursor.moveToFirst()) {
            do {
                int studentId = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                Double mark = cursor.getDouble(3);
                boolean gender = cursor.getInt(4) == 1;
                String dateCreated = cursor.getString(5);
                Student student = new Student(studentId, name, age, mark, gender, dateCreated);
                System.out.println(age + " " + mark + "\n");
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        return listStudent;
    }

//    public Student getStudent(int id) {
//        String whereClause = "id = ?";
//        String[] whereArgs = {Integer.toString(id)};
//        SQLiteDatabase database = getReadableDatabase();
//        Cursor cursor = database.query("student", null, whereClause, whereArgs,
//                null, null, null);
//
//        if(cursor != null && cursor.moveToFirst()) {
//            int studentId = cursor.getInt(0);
//            String studentName = cursor.getString(1);
//            int studentAge = cursor.getInt(2);
//            double studentMark = cursor.getDouble(3);
//            boolean studentGender = cursor.getInt(4) == 1;
//            String studentDateCreated = cursor.getString(5);
//            cursor.close();
//            return new Student(studentId, studentName, studentAge, studentMark, studentGender,
//                    studentDateCreated);
//        }
//        return null;
//    }

    public ArrayList<Student> getStudentByName(String name) {
        ArrayList<Student> list = new ArrayList<>();
        String whereClause = "name LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("student", null, whereClause,
                whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int studentId = cursor.getInt(0);
            String studentName = cursor.getString(1);
            int studentAge = cursor.getInt(2);
            double studentMark = cursor.getDouble(3);
            boolean studentGender = cursor.getInt(4) == 1;
            String studentDateCreated = cursor.getString(5);
            list.add(new Student(studentId, studentName, studentAge,
                    studentMark, studentGender, studentDateCreated));
        }
        return list;
    }
    public int updateStudent(Student student) {
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(student.getId())};
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("age", student.getAge());
        values.put("mark", student.getMark());
        values.put("gender", student.isGender());
        values.put("dateCreated", student.getDateCreated());
        SQLiteDatabase database = getWritableDatabase();
        return database.update("student", values, whereClause, whereArgs);
    }

    public int deleteStudent(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase database = getWritableDatabase();
        return database.delete("student", whereClause, whereArgs);
    }
}
