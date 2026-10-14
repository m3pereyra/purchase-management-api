package com.marcelo.purchase.service;

import com.marcelo.purchase.entity.Supplier;
import com.marcelo.purchase.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import com.marcelo.purchase.exception.DuplicateTaxIdException;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier save(Supplier supplier) {

        if (supplierRepository.existsByTaxId(supplier.getTaxId())) {
            throw new DuplicateTaxIdException(
                    "Ya existe un proveedor con el Tax ID " + supplier.getTaxId()
            );
        }

        return supplierRepository.save(supplier);
    }

    public boolean deleteById(Long id) {

        if (!supplierRepository.existsById(id)) {
            return false;
        }

        supplierRepository.deleteById(id);
        return true;
    }

    public Optional<Supplier> update(Long id, Supplier supplierDetails) {

        return supplierRepository.findById(id)
                .map(existingSupplier -> {

                    if (supplierRepository.existsByTaxIdAndIdNot(
                            supplierDetails.getTaxId(), id)) {

                        throw new DuplicateTaxIdException(
                                "Ya existe un proveedor con el Tax ID " + supplierDetails.getTaxId()
                        );
                    }

                    existingSupplier.setName(supplierDetails.getName());
                    existingSupplier.setTaxId(supplierDetails.getTaxId());
                    existingSupplier.setEmail(supplierDetails.getEmail());
                    existingSupplier.setPhone(supplierDetails.getPhone());

                    return supplierRepository.save(existingSupplier);
                });
    }
}