package services;

import entity.Course;
import entity.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class StudentService {
    public Integer countSummaryCoursesDuration(Student student) {
        List<Course> courseList = student.getCourses();
        int sum = 0;
        for (Course course : courseList) {
            sum += course.getDuration();
        }
        return sum;
    }

    public double averageMark(List<Integer> marks) {
        double sum = 0;
        for (int temp : marks) {
            sum += temp;
        }
        return sum / marks.size();
    }

    public void studyResult(Student student) {
        String name = student.getName();
        String curriculum = student.getCurriculum();
        int timeLeft = countSummaryCoursesDuration(student) - student.getMarks().size() * 8;
        double average = averageMark(student.getMarks());
        double bestResult = 0;
        if (timeLeft / 8 > 0) bestResult = (average + 5) / 2;
        String verdict = (bestResult >= 4.5) ? "Может продолжать обучение" : "Отчислить";
        System.out.println(name + " - До окончания обучения по программе " +
                curriculum + " осталось " +
                timeLeft + " ч. Средний балл " +
                average + ". " +
                verdict
        );
    }

    public void studentsResult(List<Student> students) {
        System.out.println("Output by average mark: ");
        students.sort(Comparator.comparingDouble(a -> averageMark(a.getMarks())));
        students.forEach(this::studyResult);
        System.out.println("Output by time left: ");
        students.sort(Comparator.comparingInt(a -> countSummaryCoursesDuration(a) - a.getMarks().size() * 8));
        students.forEach(this::studyResult);
        System.out.println("Students that may complete: ");
        students.stream().filter(student -> {
            double average = averageMark(student.getMarks());
            if (countSummaryCoursesDuration(student) - student.getMarks().size() * 8 / 8 > 0) average = (average + 5) / 2;
            return average >= 4.5;
        })
                .forEach(this::studyResult);
    }
}
