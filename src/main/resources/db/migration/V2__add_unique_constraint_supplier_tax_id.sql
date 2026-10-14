ALTER TABLE suppliers
    ADD CONSTRAINT uk_suppliers_tax_id UNIQUE (tax_id);