package Lesson7;

import Entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Example1 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = new Student("Alexei", 4.5);
        String jsonStudent = objectMapper.writeValueAsString(student);
        System.out.println(jsonStudent);

    }
}
