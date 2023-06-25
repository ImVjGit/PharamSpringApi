package com.example.pharma.service;

import com.example.pharma.entity.PharmaProduct;
import com.example.pharma.model.PharmaProductDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;

public interface ProductService {
    PharmaProductDto  saveProduct(PharmaProductDto productDto);

    List<PharmaProductDto> showAll();


   /* List<PharmaProductDto>showAllWithPage(Integer pageSize, Integer pageNumber);*/

    void deleteProductById(Long productId);

    PharmaProductDto updateProductById(PharmaProductDto productDto, Long productId);

    String checkExpiryDate(Long productId);

    List<PharmaProductDto> showAllWithSort(String anyfield);

    List<PharmaProductDto> showAllWithPage( int pagenumber,int pagesize);


//    List <PharmaProductDto>showAllwithPage(int pagesize, int pagenumber);
}
