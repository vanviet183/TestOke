package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.applications.services.RoleService;
import com.hit.product.domains.dtos.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> getAllRole() {
        return ResponseEntity.ok(roleService.getAllRole());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.findRoleById(id));
    }

    @PostMapping("")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.createRole(roleDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(roleService.deleteRoleById(id));
    }
}
