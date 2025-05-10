package ru.joxaren.project1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.joxaren.project1.dao.BookDAO;
import ru.joxaren.project1.dao.PersonDAO;
import ru.joxaren.project1.models.Book;
import ru.joxaren.project1.models.Person;
import ru.joxaren.project1.util.BookValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDao;
    private final PersonDAO personDao;

    private final BookValidator bookValidator;
    @Autowired
    public BooksController(BookDAO bookDao, PersonDAO personDao, BookValidator bookValidator) {
        this.bookDao = bookDao;
        this.personDao = personDao;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){

        bookValidator.validate(book, bindingResult);

        if(bindingResult.hasErrors())
            return "books/new";

        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        Book book = bookDao.show(id);
        model.addAttribute("book", book);
        model.addAttribute("people", personDao.index());
        if(book.getBookReaderId() == null) {
            return "books/show-free";
        }else {
            person = bookDao.getReader(id);
            model.addAttribute("reader", person);
            return "books/show-not-free";
        }
    }

    @PatchMapping("/set-reader")
    public String setReader(Model model, @ModelAttribute("person") Person person, @ModelAttribute("book")
                            Book book){
        bookDao.setReader(book.getBookId(), person.getPersonId());
        return "redirect:/books/" + book.getBookId();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDao.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            book.setBookId(id);
            return "books/edit";
        }

        bookDao.updateBook(id, book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/reset-reader")
    public String resetReader(@PathVariable("id") int id){
        bookDao.setReader(id, null);
        return "redirect:/books/" + id;
    }
}
