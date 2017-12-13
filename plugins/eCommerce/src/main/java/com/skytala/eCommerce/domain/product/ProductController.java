package com.skytala.eCommerce.domain.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.common.base.Function;
import com.skytala.eCommerce.domain.product.dto.ProductDetailsDTO;
import com.skytala.eCommerce.domain.product.dto.ProductListItemDTO;
import com.skytala.eCommerce.domain.product.relations.product.control.attribute.ProductAttributeController;
import com.skytala.eCommerce.domain.product.relations.product.control.categoryMember.ProductCategoryMemberController;
import com.skytala.eCommerce.domain.product.relations.product.control.price.ProductPriceController;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryMember.ProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.domain.product.relations.product.query.category.FindProductCategorysBy;
import com.skytala.eCommerce.domain.product.util.ProductAttributes;
import com.skytala.eCommerce.domain.product.util.ProductDTOValidation;
import com.skytala.eCommerce.service.login.util.SecurityGroups;
import com.skytala.eCommerce.service.product.ProductViewServiceController;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.regexp.RE;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.command.AddProduct;
import com.skytala.eCommerce.domain.product.command.DeleteProduct;
import com.skytala.eCommerce.domain.product.command.UpdateProduct;
import com.skytala.eCommerce.domain.product.event.ProductAdded;
import com.skytala.eCommerce.domain.product.event.ProductDeleted;
import com.skytala.eCommerce.domain.product.event.ProductFound;
import com.skytala.eCommerce.domain.product.event.ProductUpdated;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.query.FindProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.AUTHENTICATED;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.HAS_ADMIN_AUTHORITY;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.PERMIT_ALL;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	@Resource
	ProductPriceController priceController;

	@Resource
	ProductAttributeController attributeController;

	@Resource
	ProductCategoryMemberController productCategoryMemberController;


	public ProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Product
	 * @return a List with the Products
	 * @throws Exception 
	 */
	@GetMapping("/find")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<List<Product>> findProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductsBy query = new FindProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Product> products =((ProductFound) Scheduler.execute(query).data()).getProducts();


		return successful(generateListItemsFromProducts(products));

	}

	/**
	 * creates a new Product entry in the ofbiz database
	 * 
	 * @param productToBeAdded
	 *            the Product thats to be added
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity<Product> createProduct(@RequestBody Product productToBeAdded) throws Exception {

		AddProduct command = new AddProduct(productToBeAdded);
		Product product = ((ProductAdded) Scheduler.execute(command).data()).getAddedProduct();
		
		return product != null ?  created(product)
				               :  conflict();
	}


	/**
	 * Updates the Product with the specific Id
	 * 
	 * @param productToBeUpdated
	 *            the Product thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping("/{productId}")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity<Object> updateProduct(@RequestBody Product productToBeUpdated,
			@PathVariable String productId) throws Exception {

		productToBeUpdated.setProductId(productId);

		UpdateProduct command = new UpdateProduct(productToBeUpdated);

		try {
			if(((ProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(CONFLICT).body(null);
	}

	@GetMapping("/{productId}")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<Product> findById(@PathVariable String productId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productId", productId);
		try {

			List<Product> foundProducts = findProductsBy(requestParams).getBody();
			if(foundProducts.size()!=1)
				return notFound();
			return ResponseEntity.status(HttpStatus.OK).body(foundProducts.get(0));
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productId}")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity<Object> deleteProductByIdUpdated(@PathVariable String productId) throws Exception {
		DeleteProduct command = new DeleteProduct(productId);

		try {
			if (((ProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(CONFLICT).body("Product could not be deleted");

	}

	@GetMapping("/{productId}/details")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<ProductDetailsDTO> findByIdWithDetails(@PathVariable String productId) throws Exception {

		ResponseEntity<Product> response = findById(productId);
		if(response.getStatusCode().equals(HttpStatus.NOT_FOUND))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		Product product = response.getBody();

		Map<String, String> filter = UtilMisc.toMap("productId", productId,
													"productPricePurposeId", "PURCHASE",
													"productPriceTypeId", "DEFAULT_PRICE");

		final List<ProductPrice> foundProductPrices = priceController.findProductPricesBy(filter)
															    .getBody();

		List<ProductPrice> price = new LinkedList<>();

		price.add(priceController.getNewestPrice(foundProductPrices));
		filter.clear();


		final List<ProductAttribute> attributes = attributeController.findProductAttributesBy(filter)
																     .getBody();


		List<String> categoryIds = getAllCategoriesFromProduct(productId);

		return successful(ProductDetailsDTO.create(product, price, attributes, categoryIds));
	}

	private List<String> getAllCategoriesFromProduct(String productId) throws Exception {

		return productCategoryMemberController
				.findProductCategoryMembersBy(UtilMisc.toMap("productId", productId))
				.getBody()
				.stream()
				.map(productCategoryMember -> {return productCategoryMember.getProductCategoryId();})
				.collect(Collectors.toList())
				.stream()
				.distinct()
				.collect(Collectors.toList());

	}

	@GetMapping("/productList")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<List<Product>> getAllProductListItems() throws Exception {

		List<Product> products = findProductsBy(new HashMap<>()).getBody();



		return successful(generateListItemsFromProducts(products));

	}

	private List<Product> generateListItemsFromProducts(List<Product> products) throws Exception {

		Map<String, String> filter = UtilMisc.toMap("productPricePurposeId", "PURCHASE",
													"productPriceTypeId", "DEFAULT_PRICE");

		final List<ProductPrice> productPrices = priceController.getNewestPrices(priceController.findProductPricesBy(filter)
				.getBody());

		filter.clear();

		final List<ProductAttribute> attributes = attributeController.findProductAttributesBy(filter)
				.getBody();



		final List<String> results = products.stream()
				.map((Product prod) -> ProductListItemDTO.create(prod, productPrices, attributes))
				.filter(dto -> {
					if (dto.getPrice()==null)
						return false;
					else
						return true;})
				.map(dto -> {
					return dto.getProductId();
				})
				.collect(Collectors.toList());


		return products.stream().filter(product -> {
					if(results.contains(product.getProductId()))
						return true;
					else
						return false;
				})
				.collect(Collectors.toList());

	}

	@GetMapping("/productListFromCategory/{categoryId}")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<List<Product>> getAllProductListItemsFromCategory(HttpSession session,
																					   @PathVariable("categoryId") String categoryId) throws Exception {
		ProductCategory category =
				new ProductCategory(categoryId,
									new FindProductCategorysBy(UtilMisc.toMap("productCategoryId", categoryId)));

		List<Product> products = category.getProductCategoryMembers(session)
				.stream()
				.map(this::getProductFromCategoryMember)
				.filter(product -> {
					if(product == null)
						return false;
					else
						return true;
				})
				.distinct()
				.collect(Collectors.toList());

		return successful(generateListItemsFromProducts(products));

	}

	private Product getProductFromCategoryMember(ProductCategoryMember member) {
		String productId = member.getProductId();
		try{
			Product product = findById(productId).getBody();
			return product;
		}catch(Exception e){
			return null;
		}

	}

	@PostMapping("/addDetailed")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity addProductWithDetails(@RequestBody ProductDetailsDTO dto) throws Exception {


		String valid = validate(dto);
		if(!valid.equals(ProductDTOValidation.VALID))
			return badRequest(valid);

		ResponseEntity<Product> productResponse;
		ResponseEntity<ProductPrice> priceResponse;
		ResponseEntity<ProductAttribute> attributeResponse;

		if((productResponse = createProduct(dto.extractProduct())).getStatusCode().equals(CREATED)){
			dto.setProductId(productResponse.getBody().getProductId());

			if(!(priceResponse = priceController.createProductPrice(dto.extractProductPrice())).getStatusCode().equals(CREATED))
				return priceResponse;

			for(ProductAttribute attribute : dto.extractAllAttributes()){
				if(!(attributeResponse = attributeController.createProductAttribute(attribute)).getStatusCode().equals(CREATED))
					return attributeResponse;
			}

		}else
			return productResponse;

		return created(dto);

	}

	private String validate(ProductDetailsDTO dto) {

		//no rating should be
		dto.setProductRating(BigDecimal.ZERO);

		if(dto.getProductName()==null||dto.getProductName().equals(""))
			return ProductDTOValidation.INVALID_NAME;

		if(dto.getAuthor()==null||dto.getAuthor().equals(""))
			return ProductDTOValidation.INVALID_AUTHOR;

		if(dto.getPrice()==null)
			return ProductDTOValidation.INVALID_PRICE;

		if(dto.getPublisher()==null||dto.getPublisher().equals(""))
			return ProductDTOValidation.INVALID_PUBLISHER;

		if (dto.getIsbn()==null)
			return ProductDTOValidation.INVALID_ISBN;


		return validateSyntax(dto);

	}

	private String validateSyntax(ProductDetailsDTO dto){



		if(dto.getPrice()!=null){

			if(dto.getPrice().compareTo(BigDecimal.ZERO) < 0)
				return ProductDTOValidation.INVALID_PRICE;

			if(dto.getPrice().scale()>2)
				return ProductDTOValidation.INVALID_PRICE;
			else
				dto.setPrice(dto.getPrice().setScale(2));

		}

		String ISBN = dto.getIsbn();
		if(ISBN!=null&&ISBN.equals(""))
			ISBN = null;

		if(ISBN!=null){

			ISBN = ISBN.replace("-", "");
			ISBN = ISBN.replace(" ", "");

			ISBN = ISBNValidator.getInstance().validate(ISBN);
			if(ISBN == null)
				return ProductDTOValidation.INVALID_ISBN;
			else
				dto.setIsbn(ISBN);


		}

		if(dto.getPublishingDate()!=null) {
			dto.setPublishingDate(dto.getPublishingDate().split("T")[0]);
			if (!DateValidator.getInstance().isValid(dto.getPublishingDate(), "yyyy-mm-dd"))
				return ProductDTOValidation.INVALID_PUBLISHING_DATE;
		}
		return ProductDTOValidation.VALID;
	}


	@PutMapping("/{productId}/details")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity updateProductWithDetails(@PathVariable String productId, @RequestBody ProductDetailsDTO dto) throws Exception {

		dto.setProductId(productId);
		if(dto.getProductId()==null)
			return badRequest();

		String valid = validateSyntax(dto);
		if(!valid.equals(ProductDTOValidation.VALID))
			return badRequest(valid);

		ResponseEntity response;


		if(!(response = updateProduct(dto.extractProduct(), productId)).getStatusCode().equals(HttpStatus.NO_CONTENT))
			return response;


		if(dto.getPrice()!=null)
			if(!(response = priceController.updateProductPrice(dto.extractProductPrice(), productId)).getStatusCode().equals(HttpStatus.NO_CONTENT))
				return response;


		for(ProductAttribute attribute : dto.extractAllAttributes()){
			if(!(response = attributeController.updateProductAttribute(attribute, productId)).getStatusCode().equals(HttpStatus.NO_CONTENT))
				return response;
		}

		return successful();
	}

	@PutMapping("/{productId}/updateCategories")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity updateCategories(HttpSession session,
										   @PathVariable("productId") String productId,
										   @RequestBody List<String> newCategoryIds) throws Exception {


		List<String> oldCategoryIds = getAllCategoriesFromProduct(productId);

		List<String> filteredOldIds = oldCategoryIds.stream()
				.filter(oldId -> {return !newCategoryIds.contains(oldId);})
				.collect(Collectors.toList());

		List<String> filteredNewIds = newCategoryIds.stream()
				.filter(newId -> {return !oldCategoryIds.contains(newId);})
				.collect(Collectors.toList());

		Product product = new Product(productId, new FindProductsBy(UtilMisc.toMap("productId", productId)));

		for(String categoryId : filteredOldIds){
			Map<String, String> filter = UtilMisc.toMap("productId", productId,
														"productCategoryId", categoryId);

			List<ProductCategoryMember> members = productCategoryMemberController.findProductCategoryMembersBy(filter).getBody();

			for(ProductCategoryMember member : members){

				ProductCategory category = getProductCategoryFromId(member.getProductCategoryId());
				product.removeFromCategory(session, category, member.getFromDate());

			}

		}

		List<ProductCategory> categories = filteredNewIds.stream().map(categoryId -> {
			return getProductCategoryFromId(categoryId);
		}).collect(Collectors.toList());

		product.addToCategories(session, categories);

		return successful();
	}

	private ProductCategory getProductCategoryFromId(String categoryId){
		return new ProductCategory(categoryId, new FindProductCategorysBy(UtilMisc.toMap("productCategoryId", categoryId)));

	}

}
