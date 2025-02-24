package utils;

import model.course.Course;
import model.student.Student;
import model.teacher.FullTimeTeacher;
import model.teacher.PartTimeTeacher;
import model.teacher.Teacher;
import model.university.University;

import java.util.ArrayList;
import java.util.List;

public class Initializer {
    public static University initializeUniversity() {
        List<Student> students = initializeStudents();
        List<Teacher> teachers = initializeTeachers();
        List<Course> courses = initializeCourses(teachers, students);

        return new University("Globant University", students, teachers, courses);
    }

    private static List<Teacher> initializeTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new FullTimeTeacher("John Locke", 2800, 5));
        teachers.add(new FullTimeTeacher("Virginia Wolf", 3200, 2));

        teachers.add(new PartTimeTeacher("Immanuel Kant", 2000, 16));
        teachers.add(new PartTimeTeacher("Jane Austen", 2100, 20));
        return teachers;
    }

    private static List<Student> initializeStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("204603", "Charles Dickens", 22));
        students.add(new Student("204607", "William Shakespeare", 21));
        students.add(new Student("205701", "Lewis Carroll", 24));
        students.add(new Student("205802", "J. K. Rowling", 20));
        students.add(new Student("204806", "Louisa May Alcott", 26));
        students.add(new Student("204806", "Isabel Allende", 25));

        return students;
    }

    private static List<Course> initializeCourses(List<Teacher> teachers, List<Student> students) {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Literature 101", "Room A", students.subList(0, 3), teachers.get(0)));
        courses.add(new Course("Philosophy 201", "Room B", students.subList(1, 4), teachers.get(2)));
        courses.add(new Course("Creative Writing", "Room C", students.subList(2, 5), teachers.get(1)));
        courses.add(new Course("Modern Literature", "Room D", students.subList(0, 4), teachers.get(3)));
        return courses;
    }
}
