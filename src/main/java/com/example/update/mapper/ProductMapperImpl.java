package com.example.update.mapper;

import com.example.update.dto.ProductCreateDto;
import com.example.update.dto.ProductReadDto;
import com.example.update.dto.ProductUpdateDto;
import com.example.update.dto.ProductUpdatePartialDto;
import com.example.update.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

  private final ModelMapper modelMapper;

  public ProductMapperImpl() {
    this.modelMapper = new ModelMapper();
  }

  @Override
  public Product convertToProduct(final ProductCreateDto productCreateDto) {
    return this.modelMapper.map(productCreateDto, Product.class);
  }

  @Override
  public ProductReadDto convertToProductReadDto(final Product product) {
    return this.modelMapper.map(product, ProductReadDto.class);
  }

  @Override
  public void mergeIntoProduct(final Product product, final ProductUpdateDto productUpdateDto) {
    product.setName(productUpdateDto.getName());
    product.setDescription(productUpdateDto.getDescription());
  }

  @Override
  public void mergeIntoProduct(final Product product, final ProductUpdatePartialDto productUpdatePartialDto) {
    if (productUpdatePartialDto.containsName()) {
      product.setName(productUpdatePartialDto.getName());
    }

    if (productUpdatePartialDto.containsDescription()) {
      product.setDescription(productUpdatePartialDto.getDescription());
    }
  }

}
