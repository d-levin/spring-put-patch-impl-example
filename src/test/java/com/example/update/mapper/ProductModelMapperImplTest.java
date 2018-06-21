package com.example.update.mapper;

import com.example.update.dto.ProductCreateDto;
import com.example.update.dto.ProductReadDto;
import com.example.update.dto.ProductUpdateDto;
import com.example.update.dto.ProductUpdatePartialDto;
import com.example.update.entity.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductModelMapperImplTest {

  private ProductModelMapperImpl productModelMapperImpl;

  @Before
  public void setUp() {
    this.productModelMapperImpl = new ProductModelMapperImpl();
  }

  @Test
  public void convertToProduct_shouldConvertAllFields() {
    final ProductCreateDto productCreateDto = new ProductCreateDto();
    productCreateDto.setName("name");
    productCreateDto.setDescription("description");

    final Product product = this.productModelMapperImpl.convertToProduct(productCreateDto);

    assertEquals(product.getName(), "name");
    assertEquals(product.getDescription(), "description");
  }

  @Test
  public void convertToProductReadDto_shouldConvertAllFields() {
    final Product product = new Product();
    product.setId(1L);
    product.setName("name");
    product.setDescription("description");

    final ProductReadDto productReadDto = this.productModelMapperImpl.convertToProductReadDto(product);

    assertEquals(productReadDto.getId(), (Long) 1L);
    assertEquals(productReadDto.getName(), "name");
    assertEquals(productReadDto.getDescription(), "description");
  }

  @Test
  public void mergeIntoProduct_shouldMergeProductUpdateDtoIntoProduct() {
    final Product product = new Product();
    product.setName("name");
    product.setDescription("description");

    final ProductUpdateDto productUpdateDto = new ProductUpdateDto();
    productUpdateDto.setName(null);
    productUpdateDto.setDescription("updatedDescription");

    this.productModelMapperImpl.mergeIntoProduct(product, productUpdateDto);

    assertNull(product.getName());
    assertEquals(product.getDescription(), "updatedDescription");
  }

  @Test
  public void mergeIntoProduct_shouldMergeProductUpdatePartialDtoIntoProduct() {
    final Product product = new Product();
    product.setName("name");
    product.setDescription("description");

    final ProductUpdatePartialDto productUpdatePartialDto = new ProductUpdatePartialDto();
    productUpdatePartialDto.setName("updatedName");

    this.productModelMapperImpl.mergeIntoProduct(product, productUpdatePartialDto);

    assertEquals(product.getName(), "updatedName");
    assertEquals(product.getDescription(), "description");
  }

}
