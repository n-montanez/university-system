package model.university;

import model.course.Course;
import model.student.Student;
import model.teacher.Teacher;

import java.util.List;

public class University {
    private String name;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;

    public University() {
    }

    public University(String name, List<Student> students, List<Teacher> teachers, List<Course> courses) {
        this.name = name;
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
