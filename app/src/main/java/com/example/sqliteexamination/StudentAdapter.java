package com.example.sqliteexamination;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteexamination.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {


    ArrayList<Student> listStudent = new ArrayList<>();
    Context context;
    public StudentAdapter(Context context) {
        this.context = context;
    }

    public StudentAdapter(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }

    public void setListStudent(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }
    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_cardview, null);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student s = listStudent.get(position);
        holder.txtIDName.setText(s.getName() + " - " + s.getId());
        if(s.isGender() == true) {
            holder.imgGender.setImageResource(R.drawable.male_icon);
        } else {
            holder.imgGender.setImageResource(R.drawable.female_icon);
        }

        holder.txtAge.setText("Age: " + s.getAge());
        holder.txtMark.setText("Mark: " + s.getMark());
        holder.txtDateCreated.setText("Date: " + s.getDateCreated());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateStudentActivity.class);
                intent.putExtra("update_student", s);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listStudent != null) {
            return listStudent.size();
        }

        return 0;
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        TextView txtIDName, txtAge, txtMark, txtDateCreated;
        ImageView imgGender;
        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            txtIDName = itemView.findViewById(R.id.txt_idname);
            txtAge = itemView.findViewById(R.id.txt_age);
            txtMark = itemView.findViewById(R.id.txt_mark);
            txtDateCreated = itemView.findViewById(R.id.txt_dateCreated);
            imgGender = itemView.findViewById(R.id.img_gender);
        }
    }
}
