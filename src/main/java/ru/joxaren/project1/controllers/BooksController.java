package ru.joxaren.project1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.joxaren.project1.dao.BookDao;
import ru.joxaren.project1.dao.PersonDao;
import ru.joxaren.project1.models.Book;
import ru.joxaren.project1.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
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
    public String createBook(@ModelAttribute("book") Book book){
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

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book, Model model){
        bookDao.updateBook(id, book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/reset-reader")
    public String resetReader(@PathVariable("id") int id){
        bookDao.setReader(id, null);
        return "redirect:/books/" + id;
    }
}
