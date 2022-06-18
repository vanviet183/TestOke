package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.Image;
import com.hit.product.domains.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAll();

    Product getProductById(Long id);

    List<Image> getImageByProductId(Long id);

    Product createProduct(Long categoryId, ProductDto productDto);

    Product updateProduct(Long idCategory, Long id, ProductDto productDto);

    TrueFalseResponse deleteProduct(Long id);

    List<Product> searchProducts(String nameProduct);

    List<Image> uploadProductImages(Long id, List<MultipartFile> multipartFiles);

    List<Product> getProductsSort(Long numb);

    List<Product> getProductsBySize(Integer value);

    List<Product> getProductsByColor(String color);

    List<Product> getProductsNewest();

    List<Product> getProductsBestSeller();
}
