package com.marcelo.purchase.controller;

import com.marcelo.purchase.entity.Supplier;
import com.marcelo.purchase.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.marcelo.purchase.dto.SupplierRequestDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findById(@PathVariable Long id) {
        return supplierService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Supplier> create(
            @Valid @RequestBody SupplierRequestDTO request) {

        Supplier supplier = Supplier.builder()
                .name(request.getName())
                .taxId(request.getTaxId())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        Supplier createdSupplier = supplierService.save(supplier);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!supplierService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequestDTO request) {

        Supplier supplier = Supplier.builder()
                .name(request.getName())
                .taxId(request.getTaxId())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        return supplierService.update(id, supplier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}