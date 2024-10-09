package com.example.th3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> students;

    private List<Student> filteredStudents;

    public StudentAdapter(List<Student> students) {
        this.students = students;
        this.filteredStudents = new ArrayList<>(students);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = students.get(position);
        if (student.getGender().equals("Nam")) {
            holder.imgGender.setImageResource(R.drawable.male);
        } else {
            holder.imgGender.setImageResource(R.drawable.female);
        }
        holder.txtID.setText(student.getId());
        holder.txtFullName.setText(student.getFullName().toString());
        holder.txtGPA.setText(student.getGpa() + "");

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), Detail.class);
            intent.putExtra("student_id", student.getId());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtID, txtFullName, txtGPA;
        ImageView imgGender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGender = itemView.findViewById(R.id.imgGender);
            txtID = itemView.findViewById(R.id.txtID);
            txtFullName = itemView.findViewById(R.id.txtFullName);
            txtGPA = itemView.findViewById(R.id.txtGPA);
        }
    }


}

