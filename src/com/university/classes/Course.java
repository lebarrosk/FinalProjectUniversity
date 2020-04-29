package com.university.classes;

import com.university.staff.Teacher;

import java.util.List;

public class Course {
    private int id;
    private String name;
    private Classroom classroom;
    private Teacher teacher;
    private List<Student> studentList;

    public Course(int id, String name, Classroom classroom, Teacher teacher, List<Student> studentList) {
        this.id = id;
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.studentList = studentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        final String[] course = {"Id: " + id + "; Class Name: " + name + "; Classroom: " + classroom.getName() + "; Teacher: " + teacher.getName() + System.lineSeparator()};
        course[0] += " Students: ";
        for (Student student : studentList) {
            course[0] += System.lineSeparator() + student.toString();
        }
        return course[0];
    }

    public String getCourseWithoutStudents(){
        return "Id: " + id + "; Class Name: " + name;
    }

}
