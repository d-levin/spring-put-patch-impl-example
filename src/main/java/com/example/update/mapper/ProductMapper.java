package com.example.update.mapper;

import com.example.update.dto.ProductCreateDto;
import com.example.update.dto.ProductReadDto;
import com.example.update.dto.ProductUpdateDto;
import com.example.update.dto.ProductUpdatePartialDto;
import com.example.update.entity.Product;

public interface ProductMapper {

  Product convertToProduct(ProductCreateDto productCreateDto);

  ProductReadDto convertToProductReadDto(Product product);

  void mergeIntoProduct(Product product, ProductUpdateDto productUpdateDto);

  void mergeIntoProduct(Product product, ProductUpdatePartialDto productUpdatePartialDto);
}
