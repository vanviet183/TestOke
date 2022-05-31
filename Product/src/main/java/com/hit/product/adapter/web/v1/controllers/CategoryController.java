package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.BaseController;
import com.hit.product.applications.events.RegistrationCompleteEvent;
import com.hit.product.applications.services.CategoryService;
import com.hit.product.domains.dtos.CategoryDto;
import com.hit.product.domains.dtos.UserDto;
import com.hit.product.domains.entities.Category;
import com.hit.product.domains.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController<Category> {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        return this.resListSuccess(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Long id) {
        return this.resSuccess(categoryService.getCategoryById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return this.resSuccess(categoryService.createCategory(categoryDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        return this.resSuccess(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

}
