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
    public static String messageStudentOK = "";
    public static String messageStudentError = "";
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
            System.out.println("1: Print all Teachers");
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
                    System.out.println("*** ENTER THE CLASS ID TO SEE DETAILS ***");
                    action = scanner.nextInt();

                    showCourse(action);
                    break;
                case 3:
                    System.out.println("*** CREATE A NEW STUDENT AND ASSOCIATE TO A CLASS ***");
                    createNewStudentAssociateToClass();
                    break;
                case 4:
                    System.out.println("*** CREATE A NEW CLASS ***");
                    createClassWithAllAssociated();
                    break;
                case 5:
                    printStudents();
                    System.out.println("*** PLEASE WRITE STUDENT ID TO SEE THE ALL THE CLASSES RELATED ***");
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
            System.out.println("1: IF YOU WISH TO CONTINUE");
            System.out.println("ANY OTHER NUMBER KEY TO FINISH YOUR VISIT");

            whileAction = scanner.nextInt();
            if (whileAction == 1) {
                System.out.println("WHAT DO YOU WANT TO DO?: ");
            } else {
                System.out.println("GOOD BYE!!!!");
            }
        }
    }

    private static void printTeachers() {
        System.out.println("University Teachers: ");
        teacherList.forEach(teacher -> {
            System.out.println(teacher.toString());
        });
    }

    private static void printCourses() {
        System.out.println("University Classes: ");
        coursesList.forEach(course -> {
            System.out.println(course.getCourseWithoutStudents());
        });
    }

    private static void printStudents() {
        System.out.println("University Students: ");
        System.out.println("{");
        studentList.forEach(student -> {
            System.out.println(student.toString());
        });
        System.out.println("}");
    }

    private static void printClassroom() {
        System.out.println("University Classrooms: ");
        classroomList.forEach(classroom -> {
            System.out.println(classroom.toString());
        });
    }

    private static void showCourse(int id) {
        System.out.println("Class Details: ");
        Course course = Helper.getClassById(id, coursesList);

        if (course != null) {
            System.out.println("{ ");
            System.out.println(course.toString());
            System.out.println("}");
        } else {
            System.out.println("There is no class with the specified Id");
        }
    }

    private static void showClassesOfStudent(int id) {
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

    private static void createNewStudentAssociateToClass() {
        printStudents();

        int id = 0;
        boolean sw = true;
        Student student = null;
        String[] name = new String[2];

        //validate that the Student doesn't exist
        while (sw) {
            System.out.println("Please enter the Id:");
            id = scanner.nextInt();
            student = Helper.getStudentByID(id, studentList);

            if (student != null) {
                System.out.println("WARNING: Student Id already exist! Try again");
            } else {
                sw = false;
            }
        }
        System.out.println("Please enter the Name and Last Name, like this: Name%LastName");
        String nameLastName = scanner.next();
        name = nameLastName.split("%");

        if (name.length < 2 || name.length > 3) {
            System.out.println("WARNING: You must enter one Student's Last Name. Try again:");
        }

        System.out.println("Please enter the Student's age");

        action = scanner.nextInt();

        //Create Student and added to the University List
        Student stdn = new Student(id, name[0] + " " + name[1], action);
        studentList.add(stdn);

        printCourses();
        System.out.println("** Enter the class id you want to associate the student **");
        action = scanner.nextInt();

        //Add the new Student to a specified class
        Course course = Helper.getClassById(action, coursesList);
        if (course == null) {
            System.out.println("WARNING: The id class doesn't exist!");
        } else {

            for (int i = 0; i < coursesList.size(); i++) {
                //Update the class student list
                if (coursesList.get(i).getId() == course.getId()) {
                    List<Student> courseStudents = coursesList.get(i).getStudentList();

                    courseStudents.add(student);

                    coursesList.get(i).setStudentList(courseStudents);

                    System.out.println("Student added successfully to the class!!");
                    i = coursesList.size();
                }
            }
        }
    }

    private static void createClassWithAllAssociated() {
        int id;
        String className;
        Classroom classroom;
        Teacher teacher;
        List<Student> classStudentList;

        System.out.println("Please enter the following information:");
        printCourses();
        //Class Id
        id = getNonExistingClassId();

        System.out.println("** Enter the New Class Name:");
        System.out.println("Note: if the name has space, please replace space for % Example: Differential%Calculus:");
        System.out.println("Note: Otherwise it will take only the first word");
        className = scanner.next();

        String[] clsNames = className.split("%");
        className = "";

        if (clsNames.length > 1) {
            for (String cls : clsNames) {
                className += cls + " ";
            }
            className = className.trim();
        }

        //Classroom by name
        classroom = getExistingClassroomName();

        //Teacher by Id
        teacher = getExistingTeacher();

        //Students of the class
        classStudentList = getStudentList();

        Course course = new Course(id, className, classroom, teacher, classStudentList);
        coursesList.add(course);

        System.out.println(messageStudentOK + "}");
        System.out.println(messageStudentError + "}");
        System.out.println("");

        System.out.println("New Class Details:  ");
        System.out.println(course.toString());

        System.out.println("Class successfully added!!");
    }

    private static int getNonExistingClassId() {
        boolean sw = true;

        while (sw) {
            System.out.println("** Enter the New Class Id:");
            action = scanner.nextInt();
            Course course = Helper.getClassById(action, coursesList);

            if (course != null) {
                System.out.println("ERROR: Class Id already exist! Try again:");
            } else {
                sw = false;
            }
        }
        return action;
    }

    private static Classroom getExistingClassroomName() {
        boolean sw = true;
        String name = "";
        Classroom classroom = null;

        System.out.println("** Please choose a Classroom by the name:");
        System.out.println("{");
        printClassroom();
        System.out.println("}");

        while (sw) {
            name = scanner.next();
            classroom = Helper.getClassRoomByName(name, classroomList);
            if (classroom == null) {
                System.out.println("** ERROR: Classroom name doesn't exist! Try again:");
            } else {
                sw = false;
            }
        }
        return classroom;
    }

    private static Teacher getExistingTeacher() {
        boolean sw = true;
        Teacher teacher = null;

        System.out.println("** Please choose a Teacher by the Id:");
        System.out.println("{");
        printTeachers();
        System.out.println("}");

        while (sw) {
            action = scanner.nextInt();
            teacher = Helper.getTeacherByID(action, teacherList);

            if (teacher == null) {
                System.out.println("ERROR: Teacher ID doesn't exist! Try again:");
            } else {
                sw = false;
            }
        }

        return teacher;
    }

    private static List<Student> getStudentList() {
        boolean sw = true;
        Student student;
        String ids;
        messageStudentOK = "The following Student Ids were created: {";
        messageStudentError = "The following Student Ids were not created, because they don't exist: {";
        List<Student> students = new ArrayList<>();

        printStudents();
        System.out.println("** Please choose the Students of the class:");
        System.out.println("** Please enter the IDs separated by comma: StudentID1,StudentID2...");
        ids = scanner.next();

        String[] listIds = ids.split(",");

        for (String id : listIds) {
            student = Helper.getStudentByID(Integer.parseInt(id), studentList);

            if (student == null) {
                messageStudentError += id + ", ";
            } else {
                boolean repeated = students.contains(student);
                if (!repeated) {
                    students.add(student);
                    messageStudentOK += id + ", ";
                }
            }
        }

        return students;
    }

}
