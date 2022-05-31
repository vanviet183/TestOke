package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.BaseController;
import com.hit.product.applications.services.BannerService;
import com.hit.product.applications.services.NewsService;
import com.hit.product.domains.dtos.BannerDto;
import com.hit.product.domains.dtos.NewsDto;
import com.hit.product.domains.entities.Banner;
import com.hit.product.domains.entities.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController extends BaseController<News> {

    @Autowired
    NewsService newsService;

    @GetMapping("")
    public ResponseEntity<?> getNews() {
        return this.resListSuccess(newsService.getNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable("id") Long id) {
        return this.resSuccess(newsService.getNewsById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createNews(@RequestBody NewsDto newsDto) {
        return this.resSuccess(newsService.createNews(newsDto));
    }

    @PostMapping("/{id}/uploadImg")
    public ResponseEntity<?> uploadImg(@PathVariable("id") Long id, @RequestParam("imgNews") List<MultipartFile> multipartFiles) {
        return ResponseEntity.ok().body(newsService.uploadImgNews(id, multipartFiles));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable("id") Long id, @RequestBody NewsDto newsDto) {
        return this.resSuccess(newsService.updateNews(id, newsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(newsService.deleteNews(id));
    }

}
