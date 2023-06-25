package com.example.pharma.converter;

import com.example.pharma.entity.PharmaProduct;
import com.example.pharma.model.PharmaProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    @Autowired private ModelMapper modelMapper;

    public PharmaProductDto  pharmaEntityToDto(PharmaProduct product){
        return modelMapper.map(product, PharmaProductDto.class);
    }
    public PharmaProduct pharmaDtoToEntity(PharmaProductDto productDto){
        return modelMapper.map(productDto, PharmaProduct.class);
    }
}
