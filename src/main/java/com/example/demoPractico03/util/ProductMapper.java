package com.example.demoPractico03.util;

import ch.qos.logback.core.model.ComponentModel;
import com.example.demoPractico03.dto.ProductDTO;
import com.example.demoPractico03.repository.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCIA = Mappers.getMapper(ProductMapper.class);

    @Mapping(source="nombre",target = "nombre")
    @Mapping(source="precio",target = "precio")
    ProductDTO productToProductDTO(Product p);

    @Mapping(source="nombre", target = "nombre")
    @Mapping(source="precio", target = "precio")
    Product productDtoToProduct(ProductDTO p);
}
