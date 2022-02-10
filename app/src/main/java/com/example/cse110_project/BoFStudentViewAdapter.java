package com.example.cse110_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.BoFStudent;

import java.util.List;

public class BoFStudentViewAdapter extends RecyclerView.Adapter<BoFStudentViewAdapter.ViewHolder> {
    private final List<BoFStudent> students;

    public BoFStudentViewAdapter(List<BoFStudent> students) {
        super();
        this.students = students;
    }

    @NonNull
    @Override
    public BoFStudentViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.student_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoFStudentViewAdapter.ViewHolder holder, int position) {
        holder.setStudent(students.get(position));
    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView studentNameView;
        private BoFStudent student;

        ViewHolder(View itemView) {
            super(itemView);
            this.studentNameView = itemView.findViewById(R.id.student_row_name);
            itemView.setOnClickListener(this);
        }

        public void setStudent(BoFStudent student) {
            this.student = student;
            this.studentNameView.setText(student.getName());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra("bof_student_id", this.student.getStudentId());

            context.startActivity(intent);
        }
    }
}
