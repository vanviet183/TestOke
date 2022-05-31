package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.BaseController;
import com.hit.product.applications.services.ProductSizeService;
import com.hit.product.domains.dtos.ProductSizeDto;
import com.hit.product.domains.entities.ProductSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productSizes")
public class ProductSizeController extends BaseController<ProductSize> {

    @Autowired
    ProductSizeService productSizeService;

    @GetMapping("")
    public ResponseEntity<?> getProductColors() {
        return this.resListSuccess(productSizeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductColor(@PathVariable("id") Long id) {
        return this.resSuccess(productSizeService.getProductSizeById(id));
    }

    @PostMapping("/{idProduct}")
    public ResponseEntity<?> createProductColor(@PathVariable("idProduct") Long idProduct, @RequestBody List<ProductSizeDto> productSizeDtos) {
        return this.resListSuccess(productSizeService.createListProductSize(idProduct, productSizeDtos));
    }

    @PatchMapping("/{idProduct}/{id}")
    public ResponseEntity<?> updateProductC(@PathVariable("idProduct") Long idProduct, @PathVariable("id") Long id, @RequestBody ProductSizeDto productSizeDto) {
        return this.resSuccess(productSizeService.updateProductSize(idProduct, id, productSizeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productSizeService.deleteProductSize(id));
    }
}
