package com.example.pharma.service;

import com.example.pharma.converter.Converter;
import com.example.pharma.entity.PharmaProduct;
import com.example.pharma.exception.CannotUpdateDate;
import com.example.pharma.exception.ResourceNotFoundException;
import com.example.pharma.model.PharmaProductDto;
import com.example.pharma.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    //private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
    @Autowired
    private Converter converter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public PharmaProductDto saveProduct(PharmaProductDto productDto) {
        PharmaProduct product = converter.pharmaDtoToEntity(productDto);

        Date newMgfDate = product.getMgfDate();
        Date currentDate = new Date(System.currentTimeMillis());

        if (newMgfDate.after(currentDate)) {

            throw new CannotUpdateDate("manufactured date Cannot be greater than " + currentDate);
        } else {
            product.setMgfDate(product.getMgfDate());
        }
        PharmaProduct product1 = productRepository.save(product);
        PharmaProductDto productDto1 = converter.pharmaEntityToDto(product1);

        // Or
        //PharmaProduct product1=productRepository.save(converter.pharmaDtoToEntity(productDto));

        return productDto1;
    }

    public PharmaProductDto pharmaEntityToDto(PharmaProduct product) {
        return modelMapper.map(product, PharmaProductDto.class);
    }

    public PharmaProduct pharmaDtoToEntity(PharmaProductDto productDto) {
        return modelMapper.map(productDto, PharmaProduct.class);
    }

    @Override
    public List<PharmaProductDto> showAll() {
        List<PharmaProduct> allProduct = productRepository.findAll();
        List<PharmaProductDto> allProductDto = allProduct.stream()
                .map(converter::pharmaEntityToDto).collect(Collectors.toList());

        // above method -------- OR------ below method
        // .map((allProductDto1)->this.modelMapper.map(allProduct,PharmaProductDto.class))
        // .collect(Collectors.toList());

        return allProductDto;
    }

    @Override
    public List<PharmaProductDto> showAllWithSort(String anyfield) {
        List<PharmaProduct> allProduct = productRepository.findAll(Sort.by(Sort.Direction.ASC, anyfield));
        List<PharmaProductDto> allProductDto = allProduct
                .stream()
                .map(converter::pharmaEntityToDto)
                .collect(Collectors.toList());
        return allProductDto;
    }

    @Override
    public List<PharmaProductDto> showAllWithPage( int pagenumber,int pagesize) {
        Pageable p=PageRequest.of(pagesize, pagenumber);

        Page<PharmaProduct> allPageProduct = productRepository.findAll(p);
        List<PharmaProduct> pageToListProduct=allPageProduct.getContent();
        List<PharmaProductDto> allProductDto = pageToListProduct
                .stream()
                .map(converter::pharmaEntityToDto)
                .collect(Collectors.toList());
        return allProductDto;
    }

   /* @Override
    public List<PharmaProductDto> showAllwithPage(int pagesize, int pagenumber) {

        Pageable p=PageRequest.of(pagesize,pagenumber);

        Page<PharmaProduct> pharmaProductsPage=productRepository.findAll(p);
        List<PharmaProduct> pharmaProductsList=pharmaProductsPage.getContent();

        List<PharmaProductDto> pharmaProductDtoList=pharmaProductsList.stream()
                .map((pharmaProducts1)->this.modelMapper.map(pharmaProductsPage, PharmaProductDto.class))
                .collect(Collectors.toList());

        return pharmaProductDtoList;
    }*/



   /* @Override
    public List<PharmaProductDto> showAllWithPage(Integer pageSize, Integer pageNumber) {
        Pageable p= (Pageable) PageRequest.of(pageSize,pageNumber);

       Page<PharmaProduct> allPageProduct=productRepository.findAll((org.springframework.data.domain.Pageable) p);
       List<PharmaProduct >listPageProduct=allPageProduct.getContent();

       List<PharmaProductDto> listPharmaDto=listPageProduct
               .stream()
               .map((pharmaProduct)->this.modelMapper.map(pharmaProduct,PharmaProductDto.class))
               .collect(Collectors.toList());

        return listPharmaDto;
    }*/

    @Override
    public void deleteProductById(Long productId) {
        PharmaProduct pharmaProduct = productRepository.getReferenceById(productId);
        productRepository.deleteById(productId);
    }

    @Override
    public PharmaProductDto updateProductById(PharmaProductDto productDto, Long productId) {

        PharmaProduct pharmaProduct = converter.pharmaDtoToEntity(productDto);
        PharmaProduct dBPharmaProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("no element found with id" + productId));

        dBPharmaProduct.setProductName(pharmaProduct.getProductName());
        dBPharmaProduct.setProductDescription(pharmaProduct.getProductDescription());
        dBPharmaProduct.setProductPrice(pharmaProduct.getProductPrice());
        dBPharmaProduct.setProductCategory(pharmaProduct.getProductCategory());
        dBPharmaProduct.setProductQuantity(pharmaProduct.getProductQuantity());
        //setting mgf date limit
        Date newMgfDate = pharmaProduct.getMgfDate();
        Date currentDate = new Date(System.currentTimeMillis());

        if (newMgfDate.after(currentDate)) {
            throw new CannotUpdateDate("manufactured date Cannot be greater than " + currentDate);
        } else {
            dBPharmaProduct.setMgfDate(pharmaProduct.getMgfDate());
        }

        dBPharmaProduct.setMgfDate(pharmaProduct.getMgfDate());
        dBPharmaProduct.setExpDate(pharmaProduct.getExpDate());

        PharmaProduct pharmaProduct1 = productRepository.save(dBPharmaProduct);
        return converter.pharmaEntityToDto(pharmaProduct1);
    }

    @Override
    public String checkExpiryDate(Long productId) {
        PharmaProduct dBPharmaProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("no element found with id: " + productId));

        Date expiaryDate = dBPharmaProduct.getExpDate();
        //java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Date currentDate = new Date(System.currentTimeMillis());

        if (expiaryDate.before(currentDate)) return "product is expired";
        return "product is not expired";

    }

}
