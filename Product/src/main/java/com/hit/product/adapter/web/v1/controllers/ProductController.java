package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.BaseController;
import com.hit.product.applications.services.ProductService;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends BaseController<Product> {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        return this.resListSuccess(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return this.resSuccess(productService.getProductById(id));
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getProductImages(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.getImageByProductId(id));
    }

    @PostMapping("/{idCategory}")
    public ResponseEntity<?> createProduct(@PathVariable("idCategory") Long idCategory, @RequestBody ProductDto productDto) {
        return this.resSuccess(productService.createProduct(idCategory, productDto));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam("nameProduct") String nameProduct) {
        return this.resListSuccess(productService.searchProducts(nameProduct));
    }

    @PostMapping("/{id}/uploadImg")
    public ResponseEntity<?> uploadProductImage(@PathVariable("id") Long id, @RequestParam("imgProduct") List<MultipartFile> multipartFiles) {
        return ResponseEntity.ok().body(productService.uploadProductImages(id, multipartFiles));
    }

    @PatchMapping("/{idCategory}/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("idCategory") Long idCategory, @PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return this.resSuccess(productService.updateProduct(idCategory, id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }

}
