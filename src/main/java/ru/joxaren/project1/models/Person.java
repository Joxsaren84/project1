package ru.joxaren.project1.models;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {

    private int personId;
    @NotEmpty(message = "Name can't be empty")
    private String personName;
    @Min(value = 1921, message = "Person can't be born early 1921")
    private int personBornYear;

    public Person(int personId, String personName, int personBornYear) {
        this.personId = personId;
        this.personName = personName;
        this.personBornYear = personBornYear;
    }

    public Person(){}

    public int getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public int getPersonBornYear() {
        return personBornYear;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonBornYear(int personBornYear) {
        this.personBornYear = personBornYear;
    }
}
