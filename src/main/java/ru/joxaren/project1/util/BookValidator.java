package ru.joxaren.project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.joxaren.project1.dao.BookDAO;
import ru.joxaren.project1.models.Book;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDao;

    @Autowired
    public BookValidator(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if(bookDao.show(book.getBookName()) != null)
            errors.rejectValue("bookName", "", "This book name is already exist");
    }
}
