package com.example.pharma.repository;

import com.example.pharma.entity.PharmaProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<PharmaProduct,Long> {
}
