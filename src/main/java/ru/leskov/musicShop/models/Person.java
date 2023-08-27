package ru.leskov.musicShop.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person {

    private int id_person;
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100")
    private String fullName;
    @Min(value = 1900, message = "Years must be greatest then 1900")
    private int yearsOfBirthday;

    public Person() {
    }

    public Person(String name, int yearsOfBirthday) {
        this.fullName = name;
        this.yearsOfBirthday = yearsOfBirthday;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearsOfBirthday() {
        return yearsOfBirthday;
    }

    public void setYearsOfBirthday(int yearsOfBirthday) {
        this.yearsOfBirthday = yearsOfBirthday;
    }
}
