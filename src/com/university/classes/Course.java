package com.university.classes;

import com.university.staff.Teacher;

import java.util.List;

public class Course {
    private Classroom classroom;
    private Teacher teacher;
    private List<Student> studentList;

    public Course(Classroom classroom, Teacher teacher, List<Student> studentList) {
        this.classroom = classroom;
        this.teacher = teacher;
        this.studentList = studentList;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
