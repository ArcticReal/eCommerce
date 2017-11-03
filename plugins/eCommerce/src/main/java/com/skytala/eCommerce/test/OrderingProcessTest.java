package com.skytala.eCommerce.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytala.eCommerce.config.WebAppConfig;
import com.skytala.eCommerce.domain.product.ProductController;
import com.skytala.eCommerce.domain.product.dto.ProductDetailsDTO;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppConfig.class })
public class OrderingProcessTest extends TestCase {


    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @Override
    @Before
    public void setUp() throws Exception {

        objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();
        MockitoAnnotations.initMocks(this);

        super.setUp();


    }


    @Test
    @Transactional
    @Rollback
    public void createProductWithDetails() throws Exception {

        Product productToBeAdded = new Product();
        productToBeAdded.setProductName("testProduct");

        ProductDetailsDTO dto = new ProductDetailsDTO(productToBeAdded);
        dto.setPrice(new BigDecimal(10));
        dto.setPublisher("publisherTest");
        dto.setMediumImageUrl("/some/URL");

        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();
        mockMvc.perform(post("/products/addDetailed")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

    }

    @Test
    @Transactional
    @Rollback
    public void updateProductWithDetails() throws Exception {

        Product productToBeUpdated = new Product();
        ProductDetailsDTO dto = new ProductDetailsDTO(productToBeUpdated);
        dto.setPrice(new BigDecimal(10));
        dto.setPublisher("updatedPublisherTest");
        dto.setMediumImageUrl("/some/updated/URL");

        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();
        mockMvc.perform(put("/products/addDetailed")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNoContent());



    }




}
