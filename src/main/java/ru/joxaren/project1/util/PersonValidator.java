package ru.joxaren.project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.joxaren.project1.dao.PersonDAO;
import ru.joxaren.project1.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDao;

    @Autowired
    public PersonValidator(PersonDAO personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDao.getPerson(person.getPersonName()) != null) {
            errors.rejectValue("personName", "", "This person is already exist");
        }

    }
}
