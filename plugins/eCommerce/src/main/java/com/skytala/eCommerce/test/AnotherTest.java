package com.skytala.eCommerce.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytala.eCommerce.config.WebAppConfig;
import com.skytala.eCommerce.control.ProductController;
import com.skytala.eCommerce.entity.Product;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppConfig.class })
public class AnotherTest extends TestCase {

	// Product Attributes
	private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";

	private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
	private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

	@Autowired
	ObjectMapper objectMapper;

	MockMvc mockMvc;

	@Override
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();

	}

	@Test
	public void testCreateProductThatDoesntExist() throws JsonProcessingException, Exception {

		Product product = new Product();
		product.setProductId(DEFAULT_PRODUCT_ID);
		product.setProductName(DEFAULT_PRODUCT_NAME);

		mockMvc.perform(post("/products/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(product)))
			.andDo(print())
			.andExpect(status().isCreated());

	}

	@Test
	public void testCreateProductThatExists() throws JsonProcessingException, Exception {

		Product product = new Product();
		product.setProductId(DEFAULT_PRODUCT_ID);
		product.setProductName(DEFAULT_PRODUCT_NAME);

		mockMvc.perform(post("/products/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(product)))
			.andDo(print())
			.andExpect(status().isConflict());
	
	
	}

	@Test
	public void testThat() throws Exception {

		mockMvc.perform(get("/products/find")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testAnotherThing() throws Exception {

		mockMvc.perform(get("/products/10020")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].productId", containsString("10020")));

	}

}
