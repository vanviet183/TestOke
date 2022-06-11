package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.BillService;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.entities.Bill;
import com.hit.product.domains.entities.DetailBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("")
    public ResponseEntity<?> getBills() {
        return VsResponseUtil.ok(billService.getBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBillById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(billService.getBillById(id));
    }

//    @PostMapping("/{idUser}")
//    public ResponseEntity<?> createBill(@PathVariable("idUser") Long idUser, @RequestBody BillDto billDto) {
//        return VsResponseUtil.ok(billService.createBill(idUser, billDto));
//    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createBill(@PathVariable("idUser") Long idUser, @RequestBody BillDto billDto, @RequestBody List<DetailBill> detailBills) {
        return VsResponseUtil.ok(billService.createBill(idUser, billDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(billService.deleteBill(id));
    }

}
