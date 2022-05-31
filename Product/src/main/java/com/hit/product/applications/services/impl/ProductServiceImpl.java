package com.hit.product.applications.services.impl;

import com.github.slugify.Slugify;
import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.exceptions.NotFoundException;
import com.hit.product.applications.repositories.CategoryRepository;
import com.hit.product.applications.repositories.ImageRepository;
import com.hit.product.applications.repositories.ProductRepository;
import com.hit.product.applications.services.ProductService;
import com.hit.product.applications.utils.UploadFile;
import com.hit.product.domains.dtos.CategoryDto;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.Category;
import com.hit.product.domains.entities.Image;
import com.hit.product.domains.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UploadFile uploadFile;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);
        return product.get();
    }

    @Override
    public List<Image> getImageByProductId(Long id) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);

        return imageRepository.findByProduct_Id(id);
    }

    @Override
    public Product createProduct(Long idCategory, ProductDto productDto) {
        return createOrUpdate(idCategory, new Product(), productDto);
    }

    @Override
    public Product updateProduct(Long idCategory, Long id, ProductDto productDto) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);
        return createOrUpdate(idCategory, product.get(), productDto);
    }

    private Product createOrUpdate(Long idCategory, Product product, ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        checkCategoryException(category);

        modelMapper.map(productDto, product);

        Slugify slug = new Slugify();
        String result = slug.slugify(productDto.getTitle());
        product.setSlug(result);

        product.setCategory(category.get());

        return productRepository.save(product);
    }

    @Override
    public TrueFalseResponse deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);
        productRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    @Override
    public List<Product> searchProducts(String nameProduct) {
        Slugify slug = new Slugify();
        String result = slug.slugify(nameProduct);

        return productRepository.findBySlugContaining(result);
    }

    @Override
    @Transactional
    public List<Image> uploadProductImages(Long id, List<MultipartFile> multipartFiles) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);

        List<Image> imageList = new ArrayList<>();
        multipartFiles.forEach(multipartFile -> {
            imageList.add(createImgProduct(product.get(), new Image(), multipartFile));
        });
        return imageList;
    }

    public Image createImgProduct(Product product, Image image, MultipartFile multipartFile) {
        image.setImageUrl(uploadFile.getUrlFromFile(multipartFile));
        image.setProduct(product);
        return imageRepository.save(image);
    }

    private void checkCategoryException(Optional<Category> category) {
        if(category.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkProductException(Optional<Product> product) {
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
