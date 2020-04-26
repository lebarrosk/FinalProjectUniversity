package com.university.utils;

import com.university.classes.Classroom;
import com.university.classes.Course;
import com.university.classes.Student;
import com.university.staff.FullTimeTeacher;
import com.university.staff.PartTimeTeacher;
import com.university.staff.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Initializer {
    /*
     * Crear minimo dos profesores de cada tipo
     * inicializar minimo 6 estudiantes
     * inicializar minimo 4 cursos diferentes con sus estudiantes, su profesor y demas datos
     *
     * */

    public static List<Teacher> initTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new FullTimeTeacher(1, "Lic. Ana Maria Perez", 1500, 15));
        teacherList.add(new FullTimeTeacher(2, "Lic. Bernanrdo Garcia", 1000, 5));
        teacherList.add(new FullTimeTeacher(3, "Lic. Caterine Jones", 900, 4));
        teacherList.add(new PartTimeTeacher(4, "Lic. Antonio Rodriguez", 700, 35));
        teacherList.add(new PartTimeTeacher(5, "Lic. Beatriz Rojas", 700, 20));
        teacherList.add(new PartTimeTeacher(6, "Lic. Carla Gonzalez", 700, 18));

        return teacherList;
    }

    public static List<Student> initStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Maria Roa", 17));
        studentList.add(new Student(2, "Luisa Gomez", 22));
        studentList.add(new Student(3, "Pedro Rojas", 19));
        studentList.add(new Student(4, "Roberto Zea", 21));
        studentList.add(new Student(5, "Alicia Luyo", 27));
        studentList.add(new Student(6, "John Perez", 16));
        studentList.add(new Student(7, "Wendy Salazar", 20));
        studentList.add(new Student(8, "Oscar Gonzalez", 22));
        studentList.add(new Student(9, "Luis Gomez", 30));
        studentList.add(new Student(10, "Alejandra Avendano", 27));
        studentList.add(new Student(11, "Javier Saldana", 18));
        studentList.add(new Student(12, "Rosa Alvarez", 19));
        studentList.add(new Student(13, "Patricia Perez", 26));

        return studentList;
    }

    public static List<Classroom> initClassRoom() {
        List<Classroom> classroomList = new ArrayList<>();

        classroomList.add(new Classroom("A1", 35));
        classroomList.add(new Classroom("B1", 15));
        classroomList.add(new Classroom("A2", 20));
        classroomList.add(new Classroom("C1", 30));
        classroomList.add(new Classroom("D1", 10));

        return classroomList;
    }

    public static List<Course> initClass() {
        List<Course> courseList = new ArrayList<>();
        List<Teacher> teacherList = initTeachers();
        List<Student> studentList = initStudents();
        List<Classroom> classroomList = initClassRoom();

        courseList.add(new Course(Helper.getClassRoomByName("B1", classroomList)
                , Helper.getTeacherByID(3, teacherList)
                , Helper.getFirstXStudents(6, studentList)));

        courseList.add(new Course(Helper.getClassRoomByName("C1", classroomList)
                , Helper.getTeacherByID(2, teacherList)
                , Helper.getLastXStudents(5, studentList)));

        courseList.add(new Course(Helper.getClassRoomByName("A1", classroomList)
                , Helper.getTeacherByID(4, teacherList)
                , Helper.getLastXStudents(7, studentList)));

        courseList.add(new Course(Helper.getClassRoomByName("A2", classroomList)
                , Helper.getTeacherByID(5, teacherList)
                , Helper.getFirstXStudents(4, studentList)));

        courseList.add(new Course(Helper.getClassRoomByName("D1", classroomList)
                , Helper.getTeacherByID(1, teacherList)
                , Helper.getFirstXStudents(7, studentList)));
        return courseList;
    }
}
