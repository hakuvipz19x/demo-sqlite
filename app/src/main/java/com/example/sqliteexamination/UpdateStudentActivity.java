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
import android.widget.Toast;

import com.example.sqliteexamination.model.Student;

import java.util.Calendar;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText edtName, edtAge, edtMark, edtDate;
    RadioButton rbMale, rbFemale;
    MainActivity mainActivity;
    Button btnGetDate, btnEdit, btnDelete, btnCancel;
    int mDay, mMonth, mYear;
    SQLiteStudentHelper sqLiteStudentHelper = new SQLiteStudentHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        initView();
        Intent getIntent = getIntent();
        Student student = (Student) getIntent.getSerializableExtra("update_student");
        edtName.setText(student.getName());
        edtAge.setText(student.getAge() + "");
        edtMark.setText(student.getMark() + "");
        edtDate.setText(student.getDateCreated());
        if(student.isGender() == true) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(UpdateStudentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                            }
                        }, mYear, mMonth, mDay);
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ban da huy cap nhat thong tin", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int deleteRows = sqLiteStudentHelper.deleteStudent(student.getId());
                if(deleteRows > 0) {
                    Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                int age = Integer.parseInt(edtAge.getText().toString());
                double mark = Double.parseDouble(edtMark.getText().toString());
                boolean gender;
                if(rbMale.isChecked()) {
                    gender = true;
                } else {
                    gender = false;
                }
                String dateCreated = edtDate.getText().toString();
                int changedRows = sqLiteStudentHelper.updateStudent(new Student(
                        student.getId(), name, age, mark, gender, dateCreated
                ));

                if(changedRows > 0) {
                    Toast.makeText(getApplicationContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Cap nhat that bai", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void initView() {
        edtName = findViewById(R.id.edt_update_name);
        edtAge = findViewById(R.id.edt_update_age);
        edtMark = findViewById(R.id.edt_update_mark);
        edtDate = findViewById(R.id.edt_update_date);
        rbMale = findViewById(R.id.rb_update_male);
        rbFemale = findViewById(R.id.rb_update_female);
        btnGetDate = findViewById(R.id.btn_update_getDate);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
        btnCancel = findViewById(R.id.btn_update_cancel);
    }
}