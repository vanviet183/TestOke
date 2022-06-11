package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.CategoryService;
import com.hit.product.domains.dtos.CategoryDto;
import com.hit.product.domains.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        return VsResponseUtil.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return VsResponseUtil.ok(categoryService.createCategory(categoryDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        return VsResponseUtil.ok(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(categoryService.deleteCategory(id));
    }

}
