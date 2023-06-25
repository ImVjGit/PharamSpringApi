package com.example.pharma.controller;

import com.example.pharma.entity.PharmaProduct;
import com.example.pharma.model.PharmaProductDto;
import com.example.pharma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/PharmaHome")
public class ProductController {

    @Autowired private ProductService productService;

    //save
    @PostMapping("/save")
    ResponseEntity<PharmaProductDto> saveProduct(@RequestBody PharmaProductDto productDto){
        return ResponseEntity.ok(productService.saveProduct(productDto));
    }

    //showall
    @GetMapping("/showall")
    ResponseEntity<List<PharmaProductDto>> showAll(){
        return ResponseEntity.ok(productService.showAll());
    }
/*
    @GetMapping("/showallwithpage")
    ResponseEntity<List<PharmaProductDto>> showAllWithPage(
            @RequestParam( value = "pageSize",defaultValue = "1",required = false) Integer pageSize,
            @RequestParam(value = "pageNumber",defaultValue = "5",required = false) Integer pageNumber){

       // return ResponseEntity.ok(productService.showAllWithPage(pageSize,pageNumber));
        return new ResponseEntity<List<PharmaProductDto>>(HttpStatus.OK);
    }*/

    //only sorting
    @GetMapping("/showall/{anyfield}")
    ResponseEntity<List<PharmaProductDto>>showAllWithSort(@PathVariable("anyfield") String anyfield){
        return ResponseEntity.ok(productService.showAllWithSort(anyfield));
    }

    @GetMapping("/showallpage")
    ResponseEntity<List<PharmaProductDto>>showAllWithPage(
            @RequestParam( value = "pagenumber",defaultValue = "1",required = false) int pagenumber,
            @RequestParam( value = "pagesize",defaultValue = "1",required = false) int pagesize){
        List<PharmaProductDto> pharmaProductDtoList=  productService.showAllWithPage(pagenumber,pagesize);
        return new ResponseEntity<List<PharmaProductDto>>(pharmaProductDtoList,HttpStatus.OK);
    }

    //onlypagination
//    @GetMapping("/showwithpage")
//   ResponseEntity<List<PharmaProductDto>>showAllWithPagination(@RequestParam("pagesize") int pagesize,
//                                                               @RequestParam("pagenumber") int pagenumber){
//        List<PharmaProductDto> PharmaProductDto= productService.showAllwithPage(pagesize,pagenumber);
//        return new ResponseEntity<List<PharmaProductDto>>(PharmaProductDto,HttpStatus.OK);
//    }

    //delete
    @DeleteMapping("/delete")
    ResponseEntity<String>deleteProductById(@RequestParam("id") Long productId){
        productService.deleteProductById(productId);
        return  ResponseEntity.ok("Deleted the product with id: "+productId);
    }

    //update
    @PutMapping("/update")
    ResponseEntity<PharmaProductDto>updateProductById(@RequestBody PharmaProductDto productDto,
                      @RequestParam("id") Long productId ){
        return ResponseEntity.ok(productService.updateProductById(productDto,productId));
    }

    @GetMapping("/checkexpiration/{id}")
    ResponseEntity<String>checkExpiryDate(@PathVariable("id") Long productId){
              return ResponseEntity.ok(productService.checkExpiryDate(productId));

    }
}
