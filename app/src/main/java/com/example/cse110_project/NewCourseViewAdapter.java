package com.example.cse110_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.cse110_project.prevcourses.db.NewCourse;

public class NewCourseViewAdapter extends RecyclerView.Adapter<NewCourseViewAdapter.ViewHolder> {
    private final List<NewCourse> courses;

    public NewCourseViewAdapter(List<NewCourse> courses) {
        super();
        this.courses = courses;
    }

    @NonNull
    @Override
    public NewCourseViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.courses_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCourseViewAdapter.ViewHolder holder, int position) {
        holder.setCourse(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return this.courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTextView;
        private NewCourse course;

        ViewHolder(View itemView) {
            super(itemView);
            this.courseTextView = itemView.findViewById(R.id.courses_row_text);
        }

        public void setCourse(NewCourse course) {
            this.course = course;
            this.courseTextView.setText(course.course);
        }
    }
}
