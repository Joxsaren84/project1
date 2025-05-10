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
import ru.joxaren.project1.util.PersonValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDao;
    private final BookDAO bookDao;

    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDao, BookDAO bookDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.bookDao = bookDao;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()){
            return "people/new";
        }

        personDao.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, @ModelAttribute("book") Book book, Model model){
        Person person = personDao.getPerson(id);
        model.addAttribute("person", person);

        List<Book> bookList = bookDao.getReadersBooks(id);

        if(bookList.isEmpty()){
            return "/people/show-without-books";
        }else{
            model.addAttribute("bookList", bookList);
            return "/people/show-with-books";
        }
    }

    @GetMapping("/{id}/edit")
    public String personEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDao.getPerson(id));
        return "/people/edit";
    }

    @PatchMapping("{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            person.setPersonId(id);
            return "/people/edit";
        }

        personDao.personUpdate(id, person);
        return "redirect:/people/" + id;
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") int id){
        bookDao.setNull(id);
        personDao.deletePerson(id);
        return "redirect:/people";
    }
}
