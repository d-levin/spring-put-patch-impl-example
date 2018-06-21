package com.example.update.restcontroller;

import com.example.update.dto.ProductCreateDto;
import com.example.update.dto.ProductReadDto;
import com.example.update.dto.ProductUpdateDto;
import com.example.update.dto.ProductUpdatePartialDto;
import com.example.update.entity.Product;
import com.example.update.mapper.ProductModelMapper;
import com.example.update.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductRestController {

  private final ProductRepository productRepository;

  private final ProductModelMapper productModelMapper;

  @Autowired
  public ProductRestController(final ProductRepository productRepository,
                               final ProductModelMapper productModelMapper) {
    this.productRepository = productRepository;
    this.productModelMapper = productModelMapper;
  }

  @GetMapping
  public List<ProductReadDto> getProducts() {
    log.debug("Returning all products");
    return this.productRepository.findAll().stream()
            .map(this.productModelMapper::convertToProductReadDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/{productId}")
  public ProductReadDto getProductById(@PathVariable final Long productId) {
    log.debug("Returning product with ID [{}]", productId);
    return this.productModelMapper.convertToProductReadDto(this.findProductById(productId));
  }

  @PostMapping
  public ProductReadDto createProduct(@RequestBody final ProductCreateDto productCreateDto) {
    log.debug("POST {}", productCreateDto);
    final Product product = this.productModelMapper.convertToProduct(productCreateDto);
    return this.productModelMapper.convertToProductReadDto(this.productRepository.save(product));
  }

  @PatchMapping("/{productId}")
  public ProductReadDto partialUpdateProduct(@PathVariable final Long productId,
                                             @RequestBody final ProductUpdatePartialDto productUpdatePartialDto) {
    log.debug("PATCH product [{}]", productId);
    final Product product = this.findProductById(productId);
    this.productModelMapper.mergeIntoProduct(product, productUpdatePartialDto);
    return this.productModelMapper.convertToProductReadDto(this.productRepository.save(product));
  }

  @PutMapping("/{productId}")
  public ProductReadDto updateProduct(@PathVariable final Long productId,
                                      @RequestBody final ProductUpdateDto productUpdateDto) {
    log.debug("PUT product [{}]", productId);
    final Product product = this.findProductById(productId);
    this.productModelMapper.mergeIntoProduct(product, productUpdateDto);
    return this.productModelMapper.convertToProductReadDto(this.productRepository.save(product));
  }

  @DeleteMapping("/{productId}")
  public void deleteProduct(@PathVariable final Long productId) {
    log.debug("DELETE product [{}]", productId);
    this.productRepository.deleteById(productId);
  }

  private Product findProductById(final Long productId) {
    return this.productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product with ID [" + productId + "] not found"));
  }

}
