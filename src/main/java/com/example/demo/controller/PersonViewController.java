package com.example.demo.controller;


import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonViewController {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("li", personRepo.findAll());
        return "index";
    }

    @GetMapping("/addnew")
    public String addNewPerson(Model model) {
        Person p1 = new Person();
        model.addAttribute("person", p1);
        return "new";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") Person person) {
        personRepo.save(person);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") int id, Model model) {
        Person person = personRepo.getById(id);
        model.addAttribute("person", person);
        return "update";
    }

    @GetMapping("/deletePerson/{id}")
    public String deleteThroughId(@PathVariable(value = "id") int id) {
        personRepo.deleteById(id);
        return "redirect:/";

    }
}
