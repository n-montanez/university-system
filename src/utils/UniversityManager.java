package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.course.Course;
import model.student.Student;
import model.teacher.Teacher;
import model.university.University;

public class UniversityManager {
    public static void listTeachers(University university) {
        List<Teacher> teachers = university.getTeachers();

        System.out.println("-------- TEACHERS --------");
        int counter = 1;
        for (Teacher t : teachers) {
            System.out.println(counter + ". " + t.getName() + ": $" + t.calculateSalary());
            counter++;
        }
        System.out.println("--------------------------");
    }

    public static void courseDetails(University university, Scanner scanner) {
        listCourses(university);
        List<Course> courses = university.getCourses();
        System.out.print("Enter class number to see its details (-1 to go back): ");
        int selection = InputValidator.getValidIntegerInput(scanner);

        if (selection == -1)
            return;

        while (selection < 1 || selection > courses.size()) {
            System.out.print("Invalid option. Please enter a valid class number: ");
            selection = InputValidator.getValidIntegerInput(scanner);
            if (selection == -1)
                return;
        }

        Course selectedCourse = courses.get(selection - 1);
        System.out.println("Class " + selectedCourse.getName() + " - " + selectedCourse.getClassroom());
        System.out.println("Teacher: " + selectedCourse.getTeacher().getName());
        System.out.println("Students: ");
        for (Student s : selectedCourse.getStudentList()) {
            System.out.println(s.getId() + ". " + s.getName() + " - " + s.getAge() + " y/o.");
        }
        System.out.println("---------------------------");
    }

    public static void listCourses(University university) {
        List<Course> courses = university.getCourses();
        System.out.println("-------- COURSES --------");
        int counter = 1;
        for (Course c : courses) {
            System.out.println(counter + ". " + c.getName() + " - " + c.getClassroom());
            counter++;
        }
        System.out.println("-------------------------");
    }

    public static void addStudent(University university, Scanner scanner) {
        System.out.println("------- NEW STUDENT -------");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = InputValidator.getValidIntegerInput(scanner);

        String studentId = generateUniqueId(university);

        Student newStudent = new Student(studentId, name, age);
        university.addStudent(newStudent);

        System.out.println("--------------------------");
        System.out.println("Student added successfully");
        System.out.println("--------------------------");

        System.out.print("Do you want to add student to an existing class? Y/N: ");
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("Y")) {
            addStudentToCourse(university, newStudent, scanner);
        }
    }

    public static void addStudentToCourse(University university, Student student, Scanner scanner) {
        listCourses(university);

        List<Course> courses = university.getCourses();
        System.out.print("Enter class number to add student to: ");
        int selection = InputValidator.getValidIntegerInput(scanner);

        while (selection < 1 || selection > courses.size()) {
            System.out.print("Invalid option. Please enter a valid class number: ");
            selection = InputValidator.getValidIntegerInput(scanner);
        }

        courses.get(selection - 1).enrollStudent(student);

        System.out.println("----------------");
        System.out.println("STUDENT ENROLLED");
        System.out.println("----------------");
    }

    public static String generateUniqueId(University university) {
        Random random = new Random();
        String studentId;
        studentId = String.format("%06d", random.nextInt(1000000));
        return studentId;
    }

    public static void addCourse(University university, Scanner scanner) {
        System.out.println("------- NEW COURSE -------");

        System.out.print("Enter class name: ");
        String className = scanner.nextLine();

        System.out.print("Enter classroom: ");
        String classRoom = scanner.nextLine();

        listTeachers(university);
        System.out.print("Enter teacher number: ");
        int teacherIndex = InputValidator.getValidIntegerInput(scanner);

        while (teacherIndex < 1 || teacherIndex > university.getTeachers().size()) {
            System.out.print("Invalid teacher selection. Please enter a valid teacher number: ");
            teacherIndex = InputValidator.getValidIntegerInput(scanner);
        }

        Teacher selectedTeacher = university.getTeachers().get(teacherIndex - 1);

        listStudents(university);
        System.out.print("Enter student IDs to add (-1 to finish): ");

        List<Student> selectedStudents = new ArrayList<>();
        while (true) {
            String studentId = scanner.nextLine();

            if (studentId.equals("-1")) {
                break;
            }

            Student foundStudent = null;
            for (Student student : university.getStudents()) {
                if (student.getId().equals(studentId)) {
                    foundStudent = student;
                    break;
                }
            }

            if (foundStudent != null) {
                selectedStudents.add(foundStudent);
                System.out.println("Added student: " + foundStudent.getName());
            } else {
                System.out.println("Invalid student ID: " + studentId);
            }
        }

        Course newCourse = new Course(className, classRoom, selectedStudents, selectedTeacher);
        university.addCourse(newCourse);
        System.out.println("Course added successfully.");
    }

    public static void studentDetails(University university, Scanner scanner) {
        listStudents(university);
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        Student found = null;
        for (Student s : university.getStudents()) {
            if (s.getId().equals(studentId)) {
                found = s;
                break;
            }
        }

        if (found == null) {
            System.out.println("----------");
            System.out.println("INVALID ID");
            System.out.println("----------");
            return;
        }

        System.out.println("Student: " + found.getId() + " - " + found.getName());
        System.out.println("Courses enrolled: ");
        for (Course c : university.getCourses()) {
            for (Student s : c.getStudentList()) {
                if (s.getId().equals(found.getId())) {
                    System.out.println(c.getName() + " - " + c.getClassroom());
                }
            }
        }
        System.out.println("-----------------------");
    }

    public static void listStudents(University university) {
        List<Student> students = university.getStudents();

        System.out.println("-------- STUDENTS --------");
        for (Student s : students) {
            System.out.println(s.getId() + ". " + s.getName() + " - " + s.getAge() + " y/o.");
        }
        System.out.println("--------------------------");
    }

}
