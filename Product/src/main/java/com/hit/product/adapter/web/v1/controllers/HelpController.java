package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.HelpService;
import com.hit.product.domains.dtos.HelpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/helps")
public class HelpController {

    @Autowired
    HelpService helpService;

    @GetMapping("")
    public ResponseEntity<?> getHelps() {
        return VsResponseUtil.ok(helpService.getHelps());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHelpById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(helpService.getHelpById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createHelp(@RequestBody HelpDto helpDto) {
        return VsResponseUtil.ok(helpService.createHelp(helpDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateHelp(@PathVariable("id") Long id, @RequestBody HelpDto helpDto) {
        return VsResponseUtil.ok(helpService.updateHelp(id, helpDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHelp(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(helpService.deleteHelp(id));
    }

}
