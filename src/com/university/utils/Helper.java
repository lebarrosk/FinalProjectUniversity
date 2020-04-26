package com.university.utils;

import com.university.classes.Classroom;
import com.university.classes.Student;
import com.university.staff.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {
    public static Teacher getTeacherByID(Integer identification, List<Teacher> teacherList) {
        Teacher teacher = teacherList.stream()
                .filter(teach -> identification.equals(teach.getIdentification()))
                .findAny()
                .orElse(null);
        return teacher;
    }

    public static Student getStudentByID(Integer identification, List<Student> studentList) {
        Student student = studentList.stream()
                .filter(stud -> identification.equals(stud.getId()))
                .findAny()
                .orElse(null);
        return student;
    }

    public static Classroom getClassRoomByName(String name, List<Classroom> classroomList) {
        Classroom classRoom = classroomList.stream()
                .filter(cr -> name.equals(cr.getName()))
                .findAny()
                .orElse(null);
        return classRoom;
    }

    public static List<Student> getFirstXStudents(int quantity, List<Student> studentList) {
        List<Student> resultList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            resultList.add(studentList.get(i));
        }

        return resultList;
    }

    public static List<Student> getLastXStudents(int quantity, List<Student> studentList) {
        List<Student> resultList = new ArrayList<>();
        int size = studentList.size() - 1;
        int stopNum = size - quantity;

        for (int i = size; i > stopNum; i--) {
            resultList.add(studentList.get(i));
        }

        return resultList;
    }

    public static List<Student> getRandomXStudents(int quantity, List<Student> studentList) {
        List<Student> resultList = new ArrayList<>();
        List<Integer> nonRepeat = new ArrayList<>();
        Integer field;
        Random rand = new Random();

        for (int i = 0; i < quantity; i++) {
            field = rand.nextInt(quantity - 1);

            while (nonRepeat.contains(field)) {
                field = rand.nextInt(quantity - 1);
            }

            resultList.add(studentList.get(field.intValue()));
        }

        return resultList;
    }


}
