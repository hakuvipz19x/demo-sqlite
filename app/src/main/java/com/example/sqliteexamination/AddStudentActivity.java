package com.example.sqliteexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.sqliteexamination.model.Student;

import java.util.Calendar;

public class AddStudentActivity extends AppCompatActivity {

    private EditText edtName, edtAge, edtMark, edtDate;
    private RadioButton rbMale;
    private Button btnGetDate, btnAddStudent, btnCancel;
    int mDay, mMonth, mYear;
    SQLiteStudentHelper sqLiteStudentHelper = new SQLiteStudentHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        initView();
        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(AddStudentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(mDay + "/" + (month + 1) + "/" + mYear);
                            }
                        }, mYear, mMonth, mDay);
                dialog.show();
            }
        });

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                int age = Integer.parseInt(edtAge.getText().toString());
                boolean gender;
                if(rbMale.isChecked()) {
                    gender = true;
                } else {
                    gender = false;
                }
                Double mark = Double.valueOf((edtMark.getText().toString()));
                String date = edtDate.getText().toString();
                Student student = new Student(name, age, mark, gender, date);
//                System.out.println(age + " " + mark);
                sqLiteStudentHelper.addStudent(student);
                Intent intent = new Intent();
                intent.putExtra("add_student", student);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });
    }

    private void initView() {
        edtName = findViewById(R.id.edt_add_name);
        edtAge = findViewById(R.id.edt_add_age);
        edtMark = findViewById(R.id.edt_add_mark);
        edtDate = findViewById(R.id.edt_add_date);
        rbMale = findViewById(R.id.rb_add_male);
        btnGetDate = findViewById(R.id.btn_add_getDate);
        btnAddStudent = findViewById(R.id.btn_add_student);
        btnCancel = findViewById(R.id.btn_cancel_addStudent);
    }
}