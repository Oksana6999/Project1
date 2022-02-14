package HomeWorkNine;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Data {
    public static List<Student> getStudentsList() {
        Course course1 = new Course("Гит");
        Course course2 = new Course("Тестирование ПО");
        Course course3 = new Course("Программирование");
        Course course4 = new Course("Докер");
        Course course5 = new Course("Дизайн");
        Course course6 = new Course("Фронтенд");
        Course course7 = new Course("Менджмент");
        Course course8 = new Course("Бэкенд");
        Course course9 = new Course("Управление");
        Course course10 = new Course("Психология");
        Course course11 = new Course("Развитие");
        Course course12 = new Course("Линукс");

        Student student1 = new Student("Алексей Алексеев", Arrays.asList(course8, course9, course12));
        Student student2 = new Student("Иван Иванов", Arrays.asList(course1, course5));
        Student student3 = new Student("Петр Петров", Arrays.asList(course1, course2, course6, course4, course10));
        Student student4 = new Student("Василий Васильев", Arrays.asList(course12, course10, course5, course9));
        Student student5 = new Student("Максим Максимов", Arrays.asList(course1));
        Student student6 = new Student("Евгений Евгеньев", Arrays.asList(course7, course6, course2, course3, course9, course12));
        Student student7 = new Student("Сергей Сергеев", Arrays.asList(course2, course6, course5));
        Student student8 = new Student("Владимир Владимиров", Arrays.asList(course6, course1, course2, course11));
        Student student9 = new Student("Александр Александров", Arrays.asList(course8, course9));
        Student student10 = new Student("Владлен Владленов", Arrays.asList(course8));

        return Arrays.asList(student1, student2, student3, student4, student5, student6, student7, student8, student9, student10);
    }

    public static Course getRandomCourse() {
        Random random = new Random();
        List<Course> courses = getListCourses();
        return courses.get(random.nextInt(courses.size()));
    }

    private static List<Course> getListCourses() {
        return Arrays.asList(new Course("Тестирование ПО"),
                new Course("Дизайн"),
                new Course("Программирование"),
                new Course("Психология"),
                new Course("Менеджмент"),
                new Course("Управление"),
                new Course("Развитие"),
                new Course("Фронтенд"),
                new Course("Бэкенд"),
                new Course("Линукс"),
                new Course("Докер"),
                new Course("Гит"));
    }


}
