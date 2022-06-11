package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.ProductSizeService;
import com.hit.product.domains.dtos.ProductSizeDto;
import com.hit.product.domains.entities.ProductSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productSizes")
public class ProductSizeController {

    @Autowired
    ProductSizeService productSizeService;

    @GetMapping("")
    public ResponseEntity<?> getProductColors() {
        return VsResponseUtil.ok(productSizeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductColor(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productSizeService.getProductSizeById(id));
    }

    @PostMapping("/{idProduct}")
    public ResponseEntity<?> createProductColor(@PathVariable("idProduct") Long idProduct, @RequestBody List<ProductSizeDto> productSizeDtos) {
        return VsResponseUtil.ok(productSizeService.createListProductSize(idProduct, productSizeDtos));
    }

    @PatchMapping("/{idProduct}/{id}")
    public ResponseEntity<?> updateProductC(@PathVariable("idProduct") Long idProduct, @PathVariable("id") Long id, @RequestBody ProductSizeDto productSizeDto) {
        return VsResponseUtil.ok(productSizeService.updateProductSize(idProduct, id, productSizeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productSizeService.deleteProductSize(id));
    }
}
