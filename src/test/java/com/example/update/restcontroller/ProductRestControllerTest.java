package com.example.update.restcontroller;

import com.example.update.dto.ProductReadDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductRestController productRestController;

  @Test
  public void getProducts_shouldGetAllProducts() throws Exception {
    final ProductReadDto productReadDto = new ProductReadDto();
    productReadDto.setName("name");

    final List<ProductReadDto> products = Collections.singletonList(productReadDto);

    given(this.productRestController.getProducts()).willReturn(products);

    this.mockMvc.perform(get("/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is(productReadDto.getName())));
  }

  @Test
  public void getProductById_shouldGetProductById() throws Exception {
    final ProductReadDto productReadDto = new ProductReadDto();
    productReadDto.setId(1L);

    given(this.productRestController.getProductById(productReadDto.getId())).willReturn(productReadDto);

    this.mockMvc.perform(get("/products/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id", is(1)));
  }

}
