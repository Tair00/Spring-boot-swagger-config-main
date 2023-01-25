package com.techinterface.controller;

import com.techinterface.model.Category;
import com.techinterface.model.Child;
import com.techinterface.repository.CategoryRepository;
import com.techinterface.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    private CategoryRepository repo;
    @GetMapping("/categories")
    public String listCategories(Model model){
        List<Category> listCategories = repo.findAll();
        model.addAttribute("listCategories",listCategories);
        return "categories";
    }

    @GetMapping("/categories/new")
    public String showCategoryNewForm(Model model){
        model.addAttribute("category",new Category());
        return "category_form";

    }

    @GetMapping("/categories/save")
    public String saveCategory(Category category){
        repo.save(category);
        return "redirect:/categories";

    }
    @GetMapping("category/edit/{id}")
    public String showEditChild(@PathVariable("id") Integer id, Model model){

        Category category =repo.findById(id).get();

        model.addAttribute("child",category);

        return "category_form";
    }
}
