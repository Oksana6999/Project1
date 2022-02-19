package HomeWorkNine;

import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Student> students = Data.getStudentsList();
        Course randomCourse = Data.getRandomCourse();
        System.out.println(getUniqCourses(students));
        System.out.println(getGoodStudent(students));
        System.out.println(getSudentsFromCourses(students, randomCourse));

    }

    public static List<Course> getUniqCourses(List<Student> students) {
        System.out.println("Курсы, которые выбрали наши студенты: ");
        return students.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Student> getGoodStudent(List<Student> students) {
        System.out.println("Список самых активных и любознательных студентов: ");
        return students.stream()
                .sorted((s1, s2) -> (s2.getAllCourses().size()) - (s1.getAllCourses().size()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Student> getSudentsFromCourses(List<Student> students, Course randomCourse) {
        System.out.println("Студенты, которые проходят курс " + randomCourse + " : ");
        return  students.stream()
                .filter(student -> student.getAllCourses().contains(randomCourse))
                .collect(Collectors.toList());
    }
}
