package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.BaseController;
import com.hit.product.applications.services.BannerService;
import com.hit.product.applications.services.ProductService;
import com.hit.product.domains.dtos.BannerDto;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.Banner;
import com.hit.product.domains.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/banners")
public class BannerController extends BaseController<Banner> {

    @Autowired
    BannerService bannerService;

    @GetMapping("")
    public ResponseEntity<?> getBanners() {
        return this.resListSuccess(bannerService.getBanners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBanner(@PathVariable("id") Long id) {
        return this.resSuccess(bannerService.getBannerById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createBanner(@RequestBody BannerDto bannerDto) {
        return this.resSuccess(bannerService.createBanner(bannerDto));
    }

    @PostMapping("/{id}/uploadImg")
    public ResponseEntity<?> uploadImg(@PathVariable("id") Long id, @RequestParam("imgBanner") MultipartFile multipartFile) {
        return this.resSuccess(bannerService.uploadImgBanner(id, multipartFile));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBanner(@PathVariable("id") Long id, @RequestBody BannerDto bannerDto) {
        return this.resSuccess(bannerService.updateBanner(id, bannerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(bannerService.deleteBanner(id));
    }

}
