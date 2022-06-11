package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
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
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        return VsResponseUtil.ok(productService.getAll());
    }

    @GetMapping("/sort/{numb}")
    public ResponseEntity<?> getProductsSort(@PathVariable("numb") Long numb) {
        return VsResponseUtil.ok(productService.getProductsSort(numb));
    }

    @GetMapping("/size/{value}")
    public ResponseEntity<?> getProductsBySize(@PathVariable("value") Integer value) {
        return VsResponseUtil.ok(productService.getProductsBySize(value));
    }

    @GetMapping("/colors")
    public ResponseEntity<?> getProductsByColor(@RequestParam("color") String color) {
        return VsResponseUtil.ok(productService.getProductsByColor(color));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productService.getProductById(id));
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getProductImages(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productService.getImageByProductId(id));
    }

    @PostMapping("/{idCategory}")
    public ResponseEntity<?> createProduct(@PathVariable("idCategory") Long idCategory, @RequestBody ProductDto productDto) {
        return VsResponseUtil.ok(productService.createProduct(idCategory, productDto));
    }

//    @PostMapping("/{idCategory}")
//    public ResponseEntity<?> addProductToBill(@PathVariable("idCategory") Long idCategory, @RequestBody ProductDto productDto) {
//        return VsResponseUtil.ok(productService.createProduct(idCategory, productDto));
//    }

    @PostMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam("nameProduct") String nameProduct) {
        return VsResponseUtil.ok(productService.searchProducts(nameProduct));
    }

    @PostMapping("/{id}/uploadImg")
    public ResponseEntity<?> uploadProductImage(@PathVariable("id") Long id, @RequestParam("imgProduct") List<MultipartFile> multipartFiles) {
        return VsResponseUtil.ok(productService.uploadProductImages(id, multipartFiles));
    }

    @PatchMapping("/{idCategory}/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("idCategory") Long idCategory, @PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return VsResponseUtil.ok(productService.updateProduct(idCategory, id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productService.deleteProduct(id));
    }

}
