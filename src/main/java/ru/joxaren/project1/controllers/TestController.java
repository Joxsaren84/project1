package ru.joxaren.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.joxaren.project1.dao.TestDao;

@Controller
public class TestController {

    private final TestDao testDao;

    @Autowired
    public TestController(TestDao testDao) {
        this.testDao = testDao;
    }

    @GetMapping("/test")
    public String testPage(Model model) {

        String name = "first id has: " + testDao.testDataBase();

        model.addAttribute("name", name);

        return "test/test";
    }
}
