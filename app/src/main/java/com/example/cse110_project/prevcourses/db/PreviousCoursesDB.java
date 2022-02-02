package com.example.cse110_project.prevcourses.db;

import java.util.HashMap;

public class PreviousCoursesDB {
    private HashMap<String, String> myMap;

    public PreviousCoursesDB() {
        this.myMap = new HashMap<String, String>();
    }

    /**
     * [...]
     *
     * @param yQC is an abbreviation for year-Quarter-Course, which represents the keys in myMap
     * @param courseNumber is the value to which each key maps to in the myMap
     * */
    public void addPrevCourse(String yQC, String courseNumber) {
        myMap.put(yQC, courseNumber);
    }

    public String getPrevCourse(String yQC) {
        return myMap.get(yQC);
    }

    public int numOfPrevCourses() {
        return this.myMap.size();
    }
}
