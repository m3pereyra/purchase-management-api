
package com.marcelo.purchase.repository;

import com.marcelo.purchase.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCode(String code);

}
