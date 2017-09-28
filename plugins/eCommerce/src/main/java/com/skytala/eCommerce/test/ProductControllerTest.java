package com.skytala.eCommerce.test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.skytala.eCommerce.config.WebAppConfig;
import com.skytala.eCommerce.control.ProductController;
import com.skytala.eCommerce.entity.Product;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppConfig.class })
public class ProductControllerTest extends TestCase {

	// Product Attributes
	private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
	private static final String EXISTING_PRODUCT_ID_NON_DELETABLE = "EXISTING_ID";
	private static final String EXISTING_PRODUCT_ID_DELETABLE = "EXISTING_ID_DELABLE";
	private static final String NON_EXISTING_PRODUCT_ID = "NON_EXISTING_XYZAABC";

	private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
	private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";
	private static final String EXISTING_PRODUCT_NAME = "EXISTING_PRODUCT";

	private static final String INVALID_FACILITY_ID = "NON_EXISTING_XYZAABBC";

	ObjectMapper objectMapper;

	MockMvc mockMvc;

	@Override
	@Before
	public void setUp() throws Exception {

		objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();

		Delegator delegator = DelegatorFactory.getDelegator("test");

		Product product = new Product();
		product.setProductId(EXISTING_PRODUCT_ID_NON_DELETABLE);
		product.setProductName(EXISTING_PRODUCT_NAME);
		product.setAutoCreateKeywords(true);

		try {
			delegator.createOrStore(delegator.makeValue("Product", product.mapAttributeField()));
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		product = new Product();
		product.setProductId(EXISTING_PRODUCT_ID_DELETABLE);
		product.setProductName("testProductDel");
		product.setAutoCreateKeywords(false);

		try {
			delegator.createOrStore(delegator.makeValue("Product", product.mapAttributeField()));
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		super.setUp();
	}

	/**
	 * Create test
	 *
	 * TestCase 1: create Product that doesn't exist
	 * 
	 * Given data: ID is not used in DB, data is valid (no FK Constraints are hurt)
	 * 
	 * Expected behavior: Product created, given ID overwritten, new ID
	 * automatically generated
	 * 
	 * Expected status code: 201 CREATED
	 * 
	 * 
	 */
	@Test
	@Transactional
	@Rollback
	public void testCreateProductThatDoesntExist() throws JsonProcessingException, Exception {

		Product product = new Product();
		product.setProductId(DEFAULT_PRODUCT_ID);
		product.setProductName(DEFAULT_PRODUCT_NAME);

		String contentExpected = mockMvc
				.perform(post("/products/add").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		Product createdProduct = objectMapper.readValue(contentExpected, Product.class);

		String contentIs = mockMvc.perform(get("/products/" + createdProduct.getProductId())).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		assertEquals(contentIs, contentExpected);
		
	}

	/**
	 * Create test
	 * 
	 * TestCase 2: create Product that exists
	 * 
	 * Given data: ID is used in DB, data is valid (no FK Constraints are hurt)
	 * 
	 * Expected behavior: Product created, given ID overwritten, new ID
	 * automatically generated (NO UPDATE!)
	 * 
	 * Expected status code: 201 CREATED
	 * 
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void testCreateProductThatExists() throws JsonProcessingException, Exception {

		Product product = new Product();
		product.setProductId(EXISTING_PRODUCT_ID_DELETABLE);
		product.setProductName(DEFAULT_PRODUCT_NAME);

		mockMvc.perform(post("/products/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isCreated());

		mockMvc.perform(get("/products/" + DEFAULT_PRODUCT_ID)).andExpect(status().isNotFound());

	}

	/**
	 * create test
	 * 
	 * TestCase 3: create Product with invalid data
	 * 
	 * Given data: Data is invalid (at least FK Constraint is hurt)
	 * 
	 * Expected behavior: Product not created
	 * 
	 * Expected status code: 409 CONFLICT
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 * 
	 */
	@Test
	public void testCreateProductWithInvalidData() throws JsonProcessingException, Exception {

		Product product = new Product();
		product.setProductId(DEFAULT_PRODUCT_ID);
		product.setFacilityId(INVALID_FACILITY_ID);

		mockMvc.perform(post("/products/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isConflict());

		mockMvc.perform(get("/products/" + DEFAULT_PRODUCT_ID)).andExpect(status().isNotFound());

	}

	/**
	 * update test
	 * 
	 * TestCase 1: update existing Product with valid data
	 * 
	 * Given data: ID is used, data is valid
	 * 
	 * Expected behavior: Product updated
	 * 
	 * Expected status code: 204 NO_CONTENT
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateExistingProductWithValidData() throws JsonProcessingException, Exception {

		Product product = new Product();
		product.setProductId(EXISTING_PRODUCT_ID_NON_DELETABLE);
		product.setProductName(UPDATED_PRODUCT_NAME);

		mockMvc.perform(put("/products/" + EXISTING_PRODUCT_ID_NON_DELETABLE)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/products/" + EXISTING_PRODUCT_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productName").value(UPDATED_PRODUCT_NAME));

	}

	/**
	 * update test
	 * 
	 * TestCase 2: update non-existing Product
	 * 
	 * Given data: ID is not used
	 * 
	 * Expected behavior: No Product updated or created
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateNonExistingProductWithValidData() throws JsonProcessingException, Exception {
		Product product = new Product();
		product.setProductId(NON_EXISTING_PRODUCT_ID);
		product.setProductName(UPDATED_PRODUCT_NAME);

		mockMvc.perform(put("/products/" + NON_EXISTING_PRODUCT_ID).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isNotFound());

		mockMvc.perform(get("/products/" + NON_EXISTING_PRODUCT_ID)).andExpect(status().isNotFound());

	}

	/**
	 * update test
	 * 
	 * TestCase 3: update existing Product with invalid data
	 * 
	 * Given data: ID is used, Data is invalid (at least FK Constraint is hurt)
	 * 
	 * Expected behavior: Product not updated
	 * 
	 * Expected status code: 409 CONFLICT
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateExistingProductWithInvalidData() throws JsonProcessingException, Exception {
		Product product = new Product();
		product.setProductId(EXISTING_PRODUCT_ID_NON_DELETABLE);
		product.setProductName(UPDATED_PRODUCT_NAME);
		product.setFacilityId(INVALID_FACILITY_ID);

		mockMvc.perform(put("/products/" + EXISTING_PRODUCT_ID_NON_DELETABLE)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isConflict());

		mockMvc.perform(get("/products/" + EXISTING_PRODUCT_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productName").value(EXISTING_PRODUCT_NAME));
	}

	/**
	 * update test
	 * 
	 * TestCase 4: update non-existing Product with invalid data
	 * 
	 * Given data: ID is not used, Data is invalid (at least one FK Constraint is
	 * hurt)
	 * 
	 * Expected behavior: No Product updated or created
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateNonExistingProductWithInvalidData() throws JsonProcessingException, Exception {
		Product product = new Product();
		product.setProductId(NON_EXISTING_PRODUCT_ID);
		product.setProductName(UPDATED_PRODUCT_NAME);
		product.setFacilityId(INVALID_FACILITY_ID);

		mockMvc.perform(put("/products/" + NON_EXISTING_PRODUCT_ID).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isNotFound());

		mockMvc.perform(get("/products/" + NON_EXISTING_PRODUCT_ID)).andExpect(status().isNotFound());

	}

	/**
	 * get test
	 *
	 * TestCase 1: get existing Product
	 * 
	 * Given data: ID is used
	 * 
	 * Expected behavior: Gets data of Product with given ID from DB
	 * 
	 * Expected status code: 200 OK
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testGetExistingProduct() throws Exception {
		mockMvc.perform(get("/products/" + EXISTING_PRODUCT_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId", containsString(EXISTING_PRODUCT_ID_NON_DELETABLE)));

	}

	/**
	 * get test
	 * 
	 * TestCase 2: get non-existing Product
	 * 
	 * Given data: ID not used
	 * 
	 * Expected behavior: No data gotten from DB
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetNonExistingProduct() throws Exception {
		mockMvc.perform(get("/products/" + NON_EXISTING_PRODUCT_ID)).andExpect(status().isNotFound());

	}

	/**
	 * delete test
	 * 
	 * TestCase 1: delete existing deletable Product
	 * 
	 * Given data: ID is used, tuple deletable
	 * 
	 * Expected behavior: tuple deleted
	 * 
	 * Expected status code: 204 NO_CONTENT
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testDeleteExistingDeletableProduct() throws Exception {
		String result = mockMvc.perform(delete("/products/" + EXISTING_PRODUCT_ID_DELETABLE))
				.andExpect(status().isNoContent()).andReturn().getResponse().getContentAsString();

		assertEquals(result, "");

		mockMvc.perform(get("/products/" + EXISTING_PRODUCT_ID_DELETABLE)).andExpect(status().isNotFound());

	}

	/**
	 * delete test
	 * 
	 * TestCase 2: delete existing non-deletable Product
	 * 
	 * Given data: ID is used, tuple not deletable (FK Constraint)
	 * 
	 * Expected behavior: tuple not deleted
	 * 
	 * Expected status code: 409 CONFLICT
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testDeleteExistingNonDeletableProduct() throws Exception {

		mockMvc.perform(delete("/products/" + EXISTING_PRODUCT_ID_NON_DELETABLE)).andExpect(status().isConflict())
				.andExpect(jsonPath("$").value("Product could not be deleted"));

		mockMvc.perform(get("/products/" + EXISTING_PRODUCT_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(EXISTING_PRODUCT_ID_NON_DELETABLE));

	}

	/**
	 * delete test
	 * 
	 * TestCase 3: delete non-existing Product
	 * 
	 * Given data: ID is not used
	 * 
	 * Expected behavior: no Product deleted
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	@Test
	public void testDeleteNonExistingProduct() throws Exception {

		int countProductsBeforeDelete = 0;

		String responseStr = mockMvc.perform(get("/products/find")).andReturn().getResponse().getContentAsString();

		List<?> products = JsonPath.read(responseStr, "$");
		countProductsBeforeDelete = products.size();

		mockMvc.perform(delete("/products/" + NON_EXISTING_PRODUCT_ID)).andExpect(status().isNotFound());

		mockMvc.perform(get("/products/find")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(countProductsBeforeDelete)));

	}

}
