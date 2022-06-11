package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.DetailBillService;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.dtos.DetailBillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/detail_bills")
public class DetailBillController {

    @Autowired
    DetailBillService detailBillService;

    @GetMapping("")
    public ResponseEntity<?> getDetailBills() {
        return VsResponseUtil.ok(detailBillService.getDetailBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailBillById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(detailBillService.getDetailBillById(id));
    }

//    @PostMapping("/{idBill}/{idProduct}")
//    public ResponseEntity<?> createDetailBill(@PathVariable("idBill") Long idBill, @PathVariable("idProduct") Long idProduct, @RequestBody DetailBillDto detailBillDto) {
//        return VsResponseUtil.ok(detailBillService.createDetailBill(idBill, idProduct, detailBillDto));
//    }

    @PostMapping("/{idProduct}")
    public ResponseEntity<?> createDetailBill(@PathVariable("idProduct") Long idProduct, @RequestBody DetailBillDto detailBillDto) {
        return VsResponseUtil.ok(detailBillService.createDetailBill(idProduct, detailBillDto));
    }

//    @PostMapping("")
//    public ResponseEntity<?> createDetailBill(@RequestBody DetailBillDto detailBillDto) {
//        return VsResponseUtil.ok(detailBillService.createDetailBill(detailBillDto));
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDetailBill(@PathVariable("id") Long id, @RequestBody DetailBillDto detailBillDto) {
        return VsResponseUtil.ok(detailBillService.updateDetailBill(id, detailBillDto));
    }

    @PostMapping("/{idDetailBill}/pay")
    public ResponseEntity<?> pay(@PathVariable("idDetailBill") Long idDetailBill, @RequestBody BillDto billDto) {
        return VsResponseUtil.ok(detailBillService.pay(idDetailBill, billDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetailBill(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(detailBillService.deleteDetailBill(id));
    }

}
