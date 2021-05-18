package com.example.sqliteexamination;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sqliteexamination.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static final int REQUEST_ADD_CODE = 10000;
    private FloatingActionButton fab;
    private ArrayList<Student> listStudent = new ArrayList<>();
    private StudentAdapter studentAdapter = new StudentAdapter(this);
    private SQLiteStudentHelper sqLiteStudentHelper = new SQLiteStudentHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_student);
        fab = findViewById(R.id.fab_add);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listStudent = sqLiteStudentHelper.getAllStudent();
        studentAdapter.setListStudent(listStudent);
        recyclerView.setAdapter(studentAdapter);

        //Student(id, name, age, mark, gender, dateCreated)
//        listStudent.add(new Student(01, "Quang Anh", 22, 5, true, "05/16/2021"));
//        listStudent.add(new Student(02, "ABC", 21, 6, true, "05/17/2021"));
//        listStudent.add(new Student(03, "XYZ", 20, 7, false, "05/18/2021"));
//
//        StudentAdapter studentAdapter = new StudentAdapter(listStudent);
//        recyclerView.setAdapter(studentAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, REQUEST_ADD_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Student student = (Student) data.getSerializableExtra("add_student");
//                    System.out.println("\n " + student.getAge() + " " + student.getMark());
                    listStudent = sqLiteStudentHelper.getAllStudent();
                    studentAdapter.setListStudent(listStudent);
                    recyclerView.setAdapter(studentAdapter);
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Ban da huy them sinh vien", Toast.LENGTH_SHORT).show();
            }
        }
        recyclerView.setAdapter(studentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Student> students = sqLiteStudentHelper.getStudentByName(newText);
                studentAdapter.setListStudent(students);
                recyclerView.setAdapter(studentAdapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onResume() {
        super.onResume();
        listStudent = sqLiteStudentHelper.getAllStudent();
        studentAdapter.setListStudent(listStudent);
        recyclerView.setAdapter(studentAdapter);
    }
}