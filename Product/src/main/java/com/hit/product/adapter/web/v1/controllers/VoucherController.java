package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.VoucherService;
import com.hit.product.domains.dtos.VoucherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vouchers")
public class VoucherController {
    
    @Autowired
    VoucherService voucherService;

    @GetMapping("")
    public ResponseEntity<?> getVouchers() {
        return VsResponseUtil.ok(voucherService.getVouchers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVoucherById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(voucherService.getVoucherById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDto VoucherDto) {
        return VsResponseUtil.ok(voucherService.createVoucher(VoucherDto));
    }

    @PostMapping("/{idUser}/{idVoucher}")
    public ResponseEntity<?> addVoucherToUser(@PathVariable("idUser") Long idUser, @PathVariable("idVoucher") Long idVoucher) {
        return VsResponseUtil.ok(voucherService.addVoucherToUser(idUser, idVoucher));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateVoucher(@PathVariable("id") Long id, @RequestBody VoucherDto VoucherDto) {
        return VsResponseUtil.ok(voucherService.updateVoucher(id, VoucherDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(voucherService.deleteVoucher(id));
    }
}
