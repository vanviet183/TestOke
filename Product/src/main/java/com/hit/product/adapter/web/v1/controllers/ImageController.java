package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.ImageService;
import com.hit.product.domains.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("")
    public ResponseEntity<?> getImages() {
        return VsResponseUtil.ok(imageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(imageService.getImageById(id));
    }

//    @PatchMapping("/{idUser}")
//    public ResponseEntity<?> changeAvatar(@PathVariable("idUser") Long idUser, @RequestParam("image") MultipartFile multipartFile) {
//        return ResponseEntity.ok().body(imageService.createAvatarImage(idUser, multipartFile));
//    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile multipartFile) {
        return VsResponseUtil.ok(imageService.updateImage(id, multipartFile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(imageService.deleteImage(id));
    }


}
