package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.BaseController;
import com.hit.product.applications.services.ImageService;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController extends BaseController<Image> {

    @Autowired
    ImageService imageService;

    @GetMapping("")
    public ResponseEntity<?> getImages() {
        return this.resListSuccess(imageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") Long id) {
        return this.resSuccess(imageService.getImageById(id));
    }

//    @PatchMapping("/{idUser}")
//    public ResponseEntity<?> changeAvatar(@PathVariable("idUser") Long idUser, @RequestParam("image") MultipartFile multipartFile) {
//        return ResponseEntity.ok().body(imageService.createAvatarImage(idUser, multipartFile));
//    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile multipartFile) {
        return this.resSuccess(imageService.updateImage(id, multipartFile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(imageService.deleteImage(id));
    }


}
