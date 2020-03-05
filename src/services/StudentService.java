package services;

import entity.Course;
import entity.Student;

import java.util.List;

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

    public String studyResult(Student student) {
        String name = student.getName();
        String curriculum = student.getCurriculum();
        int timeLeft = countSummaryCoursesDuration(student) - student.getMarks().size() * 8;
        double average = averageMark(student.getMarks());
        String verdict = (average >= 4.5) ? "Может продолжать обучение" : "Отчислить";
        return name + " - До окончания обучения по программе " +
                curriculum + " осталось " +
                timeLeft + " ч. Средний балл " +
                average + ". " +
                verdict;
    }
}
