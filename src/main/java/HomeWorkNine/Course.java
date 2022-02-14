package HomeWorkNine;

import java.util.Objects;

public class Course {
    private final String name;

    public Course (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        Course course = (Course) o;
        if (this == o) return true;
        if (o == null )return false;
        if (getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
