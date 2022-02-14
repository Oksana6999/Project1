package Entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Student {
    private String name;
    private double averageMark;

    @JsonGetter ("name")
    public String getNameStudent() {return name; }

    @JsonSetter ("name")
    public void setNameStudent(String name) {this.name = name; }

    @JsonGetter ("averageMark")
    public double getAverageMark() {return averageMark;}

    @JsonSetter ("averageMark")
    public void setAverageMark(double averageMark) {this.averageMark = averageMark;}

    public Student (String name, double averageMark) {
        this.averageMark = averageMark;
        this.name = name;
    }

    public Student (){

    }


}
