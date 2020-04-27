package com.main;

import com.university.classes.Classroom;
import com.university.classes.Course;
import com.university.classes.Student;
import com.university.staff.Teacher;
import com.university.utils.Helper;
import com.university.utils.Initializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class University {
    public static List<Student> studentList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();
    public static List<Course> coursesList = new ArrayList<>();
    public static List<Classroom> classroomList = new ArrayList<>();
    public static int whileAction;
    public static Scanner scanner = new Scanner(System.in);
    public static int action;

    public static void main(String[] args) {
        whileAction = 1;
        teacherList = Initializer.initTeachers();
        studentList = Initializer.initStudents();
        classroomList = Initializer.initClassRoom();
        coursesList = Initializer.initClass(teacherList, studentList, classroomList);

        System.out.println("========= WELCOME TO UNIVERSITY=========");

        while (whileAction == 1) {
            System.out.println("1: Print all University Teachers");
            System.out.println("2: Print all Classes");
            System.out.println("3: Create new Student");
            System.out.println("4: Create new Class");
            System.out.println("5: Print all Classes of a given Student");
            System.out.println("EXTRAS: ");
            System.out.println("6: Print all University Students");
            System.out.println("7: Print all University Classrooms");
            System.out.println("Any other number key to exit");
            action = scanner.nextInt();

            switch (action) {
                case 1:
                    printTeachers();
                    break;
                case 2:
                    printCourses();
                    System.out.println("Enter the Class Id to see details: ");
                    action = scanner.nextInt();

                    showCourse(action);
                    break;
                case 3:
                    System.out.println("*** CREATE A NEW STUDENT AND ASSOCIATE TO A CLASS ***");
                    createNewStudentAssociateToClass();
                    break;
                case 4:
                    //CREATE A CLASS WITH TEACHER, STUDENTS AND CLASSROOM
                    // ID TEACHER, ID STUDENTS, SHOW LIST OF CLASSROOMS AND ADD 1
                    System.out.println("*** CREATE A NEW CLASS***");
                    System.out.println("");
                    break;
                case 5:
                    printStudents();
                    System.out.println("Please enter Student Id: ");
                    action = scanner.nextInt();

                    showClassesOfStudent(action);
                    break;
                case 6:
                    printStudents();
                    break;
                case 7:
                    printClassroom();
                    break;
            }
            System.out.println(" ");
            System.out.println("1: If you wish to continue");
            System.out.println("Any other number key to finish");

            whileAction = scanner.nextInt();
            if (whileAction == 1) {
                System.out.println("What do you want to do?: ");
            } else {
                System.out.println("Good Bye!!");
            }
        }
    }

    public static void printTeachers() {
        System.out.println("<*** UNIVERSITY TEACHERS ***>");
        teacherList.forEach(teacher -> {
            System.out.println(teacher.toString());
        });
    }

    public static void printCourses() {
        System.out.println("<*** UNIVERSITY CLASSES ***>");
        coursesList.forEach(course -> {
            System.out.println(course.toString());
        });
    }

    public static void printStudents() {
        System.out.println("<*** UNIVERSITY STUDENTS ***> ");
        System.out.println("{");
        studentList.forEach(student -> {
            System.out.println(student.toString());
        });
        System.out.println("}");
    }

    public static void printClassroom(){
        System.out.println("<*** UNIVERSITY CLASSROOMS ***>");
        classroomList.forEach(classroom -> {
            System.out.println(classroom.toString());
        });
    }

    public static void showCourse(int id) {
        System.out.println("*** CLASS DETAILS ***");
        Course course = Helper.getClassById(id, coursesList);

        if (course != null) {
            System.out.println("{ ");
            System.out.println(course.toString());
            System.out.println("}");
        } else {
            System.out.println("There is no class with the specified Id");
        }
    }

    public static void showClassesOfStudent(int id) {
        List<Course> courses = Helper.getStudentClasses(id, coursesList);
        Student student = Helper.getStudentByID(id, studentList);

        if (student != null) {
            if (courses.size() > 0) {
                System.out.println("The student: " + student.getName() + ", has registered the following classes: ");
                for (Course course : courses) {
                    System.out.println("Id: " + course.getId()
                            + "; Name: " + course.getName()
                            + "; Classroom: " + course.getClassroom().getName()
                            + "; Teacher: " + course.getTeacher());
                }
            } else {
                System.out.println("The student doesn't have any class registered");
            }
        } else {
            System.out.println("Id student not valid!");
        }
    }

    public static void createNewStudentAssociateToClass() {
        String[] students = new String[3];
        System.out.println("Please enter the student data like that: ID-Name%LastName-Age");
        String data = scanner.next();
        students = data.split("-");
        String[] name = students[1].split("%");

        Student student = Helper.getStudentByID(Integer.parseInt(students[0]), studentList);

        if (student != null) {
            System.out.println("ERROR: Student Id already exist!");
        } else {
            //Create Student and added to the University List
            Student stdn = new Student(Integer.parseInt(students[0]), name[0] + " " + name[1], Integer.parseInt(students[2]));
            studentList.add(stdn);

            System.out.println("** Enter the class id you want to associate the student **");
            action = scanner.nextInt();

            //Add the new Student to a specified class
            Course course = Helper.getClassById(action, coursesList);
            if (course == null) {
                System.out.println("ERROR: The id class doesn't exist!");
            } else {

                for (int i = 0; i < coursesList.size(); i++) {
                    //Update the class student list
                    if (coursesList.get(i).getId() == course.getId()) {
                        List<Student> courseStudents = coursesList.get(i).getStudentList();
                        courseStudents.add(student);
                        coursesList.get(i).setStudentList(courseStudents);

                        System.out.println("Student added to the class!!");
                        i = coursesList.size();
                    }
                }
            }
        }
    }

}
