package model.course;

import model.student.Student;
import model.teacher.Teacher;

import java.util.List;

public class Course {
    private String name;
    private String classroom;
    private List<Student> studentList;
    private Teacher teacher;

    public Course() {
    }

    public Course(String name, String classroom, List<Student> studentList, Teacher teacher) {
        this.name = name;
        this.classroom = classroom;
        this.studentList = studentList;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void enrollStudent(Student student) {
        this.studentList.add(student);
    }
}
