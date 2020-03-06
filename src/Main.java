import entity.Course;
import entity.Student;
import services.StudentService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Course> courseList1 = Arrays.asList(
            new Course("Технология Java Servlets", 16),
            new Course("Struts Framework", 24),
            new Course("Spring Framework", 48),
            new Course("Hibernate", 20)
        );
        List<Integer> marks1 = Arrays.asList(3, 4, 2, 5, 3, 3);
        Student student1 = new Student("Ivanov Ivan", "J2EE Developer", new GregorianCalendar(2020, Calendar.MARCH, 5), courseList1, marks1);

        List<Course> courseList2 = Arrays.asList(
                new Course("Обзор технологий Java", 8),
                new Course("Библиотека JFC/Swing", 16),
                new Course("Технология JDBC", 16),
                new Course("Технология JAX", 52),
                new Course("Библиотеки commons", 44)
        );
        List<Integer> marks2 = Arrays.asList(4, 5, 3, 5, 3, 3, 5, 5);
        Student student2 = new Student("Petrov Petr", "Java Developer", new GregorianCalendar(2020, Calendar.MARCH, 5), courseList2, marks2);

        List<Student> studentList = Arrays.asList(student1, student2);

        new StudentService().studentsResult(studentList);
    }
}
