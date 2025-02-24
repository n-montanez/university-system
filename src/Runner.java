import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.course.Course;
import model.student.Student;
import model.teacher.Teacher;
import model.university.University;
import utils.Initializer;

public class Runner {
    public static void main(String[] args) {
        University university = Initializer.initializeUniversity();

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("Welcome to The " + university.getName());
        System.out.println("--------------------------------------------");

        do {
            printMenu();

            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    listTeachers(university);
                    break;
                case 2:
                    courseDetails(university, scanner);
                    break;
                case 3:
                    addStudent(university, scanner);
                    break;
                case 4:
                    addCourse(university);
                    break;
                case 5:
                    studentDetails(university, scanner);
                    break;
                case 6:
                    System.out.println("---------------------");
                    System.out.println("Quitting... Thank you");
                    System.out.println("---------------------");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("--------------");
                    System.out.println("Invalid option");
                    System.out.println("--------------");
                    break;
            }

        } while (true);
    }

    private static void listTeachers(University university) {
        List<Teacher> teachers = university.getTeachers();

        System.out.println("-------- TEACHERS --------");
        int counter = 1;
        for (Teacher t : teachers) {
            System.out.println(counter + ". " + t.getName() + ": $" + t.calculateSalary());
            counter++;
        }
        System.out.println("--------------------------");
        return;
    }

    private static void courseDetails(University university, Scanner scanner) {
        listCourses(university);
        List<Course> courses = university.getCourses();
        System.out.print("Enter class number to see its details (-1 to go back): ");
        int selection = scanner.nextInt();

        if (selection == -1)
            return;

        if (selection < 0 || selection > courses.size() + 1) {
            System.out.println("--------------");
            System.out.println("INVALID OPTION");
            System.out.println("--------------");
        }

        Course selectedCourse = courses.get(selection - 1);
        System.out.println("Class " + selectedCourse.getName() + " - " + selectedCourse.getClassroom());
        System.out.println("Teacher: " + selectedCourse.getTeacher().getName());
        System.out.println("Students: ");
        for (Student s : selectedCourse.getStudentList()) {
            System.out.println(
                    s.getId() + ". " +
                            s.getName() + " - " +
                            s.getAge() + " y/o."

            );
        }
        System.out.println("---------------------------");
    }

    private static void listCourses(University university) {
        List<Course> courses = university.getCourses();
        System.out.println("-------- COURSES --------");
        int counter = 1;
        for (Course c : courses) {
            System.out.println(
                    counter + ". " +
                            c.getName() + " - " +
                            c.getClassroom());
            counter++;
        }
        System.out.println("-------------------------");
    }

    private static void addStudent(University university, Scanner scanner) {
        System.out.println("------- NEW STUDENT -------");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        String studentId = generateUniqueId(university);

        Student newStudent = new Student(studentId, name, age);
        university.addStudent(newStudent);

        System.out.println("--------------------------");
        System.out.println("Student added successfully");
        System.out.println("--------------------------");

        System.out.print("Do you want to add student to an existing class? Y/N: ");
        String option = scanner.nextLine();

        if (!option.equals("Y") || !option.equals("y")) {
            return;
        }

        addStudentToCourse(university, newStudent, scanner);
    }

    private static void addStudentToCourse(University university, Student student, Scanner scanner) {
        listCourses(university);

        List<Course> courses = university.getCourses();
        System.out.print("Enter class number to add student to: ");
        int selection = scanner.nextInt();

        if (selection == -1)
            return;

        if (selection < 0 || selection > courses.size() + 1) {
            System.out.println("--------------");
            System.out.println("INVALID OPTION");
            System.out.println("--------------");
        }

        courses.get(selection - 1).enrollStudent(student);

        System.out.println("----------------");
        System.out.println("STUDENT ENROLLED");
        System.out.println("----------------");
    }

    private static String generateUniqueId(University university) {
        Random random = new Random();
        String studentId;
        studentId = String.format("%06d", random.nextInt(1000000));
        return studentId;
    }

    private static void addCourse(University university) {

    }

    private static void studentDetails(University university, Scanner scanner) {
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
        return;
    }

    private static void listStudents(University university) {
        List<Student> students = university.getStudents();

        System.out.println("-------- STUDENTS --------");
        for (Student s : students) {
            System.out.println(
                    s.getId() + ". " +
                            s.getName() + " - " +
                            s.getAge() + " y/o."

            );
        }
        System.out.println("--------------------------");
        return;
    }

    private static void printMenu() {
        System.out.println("-----------------");
        System.out.println("Menu:");
        System.out.println("1. See available teachers");
        System.out.println("2. See all offered courses");
        System.out.println("3. Enroll new student");
        System.out.println("4. Create a new course");
        System.out.println("5. See student information");
        System.out.println("6. Exit");
    }
}
