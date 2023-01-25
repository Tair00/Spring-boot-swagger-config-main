package com.techinterface.controller;

import com.techinterface.model.Category;
import com.techinterface.model.Child;
import com.techinterface.repository.CategoryRepository;
import com.techinterface.repository.ChildRepository;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/child/")
public class ChildController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ChildRepository childRepository;

    @GetMapping("/child/new")
    public String showNewChildForm(Model model){
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("child",new Child());
        model.addAttribute("listCategories",listCategories);
        return "child_form";
    }
    @PostMapping("/child/save")
    public String saveChild (Child categoryChild){
        childRepository.save(categoryChild);

        return "redirect:/";
    }


    @GetMapping("/child")
    public String listChild(Model model){
        List<Child> listChild = childRepository.findAll();
        model.addAttribute("listChild",listChild);
        return "child";
    }
    @GetMapping("child/edit/{id}")
    public String showEditChild(@PathVariable("id") Integer id, Model model){

        Child child =childRepository.findById(id).get();

        model.addAttribute("child",child);
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("listCategory",categoryList);
        return "child_form";
    }

    @GetMapping("child/delete/{id}")
    public String deleteChild(@PathVariable("id") Integer id, Model model){
        childRepository.deleteById(id);
        return "redirect:/child";
    }
}
