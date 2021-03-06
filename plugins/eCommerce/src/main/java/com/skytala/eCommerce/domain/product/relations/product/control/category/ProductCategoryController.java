package com.skytala.eCommerce.domain.product.relations.product.control.category;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.control.categoryMember.ProductCategoryMemberController;
import com.skytala.eCommerce.domain.product.relations.product.dto.category.CategoryListItemDTO;
import com.skytala.eCommerce.domain.product.relations.product.util.ProductCategoryTypes;
import com.skytala.eCommerce.framework.util.AuthorizeMethods;
import com.skytala.eCommerce.service.login.util.SecurityGroups;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.product.command.category.AddProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.category.DeleteProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.category.UpdateProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryFound;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.category.ProductCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.query.category.FindProductCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.notFound;
import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.successful;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.HAS_ADMIN_AUTHORITY;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.HAS_USER_AUTHORITY;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.PERMIT_ALL;

@RestController
@RequestMapping("/product/product/productCategorys")
@PreAuthorize(HAS_ADMIN_AUTHORITY)
public class ProductCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	@Resource
	private ProductCategoryMemberController categoryMemberController;

	public ProductCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}



	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategory
	 * @return a List with the ProductCategorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<List<ProductCategory>> findProductCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategorysBy query = new FindProductCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategory> productCategorys =((ProductCategoryFound) Scheduler.execute(query).data()).getProductCategorys();

		return ResponseEntity.ok().body(productCategorys);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ProductCategory> createProductCategory(HttpServletRequest request) throws Exception {

		ProductCategory productCategoryToBeAdded = new ProductCategory();
		try {
			productCategoryToBeAdded = ProductCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return this.createProductCategory(productCategoryToBeAdded);

	}

	/**
	 * creates a new ProductCategory entry in the ofbiz database
	 * 
	 * @param productCategoryToBeAdded
	 *            the ProductCategory thats to be added
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasAuthority('"+SecurityGroups.ADMIN+"')")
	public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategoryToBeAdded) throws Exception {

		productCategoryToBeAdded.setProductCategoryTypeId(ProductCategoryTypes.USAGE_CATEGORY);
		AddProductCategory command = new AddProductCategory(productCategoryToBeAdded);
		ProductCategory productCategory = ((ProductCategoryAdded) Scheduler.execute(command).data()).getAddedProductCategory();
		
		if (productCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
	}

	/**
	 * Updates the ProductCategory with the specific Id
	 * 
	 * @param productCategoryToBeUpdated
	 *            the ProductCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/{productCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategoryToBeUpdated,
			@PathVariable String productCategoryId) throws Exception {

		productCategoryToBeUpdated.setProductCategoryId(productCategoryId);

		UpdateProductCategory command = new UpdateProductCategory(productCategoryToBeUpdated);

		try {
			if(((ProductCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productCategoryId}")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<ProductCategory> findById(@PathVariable String productCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryId", productCategoryId);
		try {

			List<ProductCategory> foundProductCategory = findProductCategorysBy(requestParams).getBody();
			if(foundProductCategory.size()==1)
				return ResponseEntity.status(HttpStatus.OK).body(foundProductCategory.get(0));
			else
				return notFound();
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryId}")
	public ResponseEntity<Object> deleteProductCategoryById(@PathVariable String productCategoryId) throws Exception {

		categoryMemberController.deleteProductCategoryMemberByCategoryId(productCategoryId);
		DeleteProductCategory command = new DeleteProductCategory(productCategoryId);

		try {
			if (((ProductCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategory could not be deleted");

	}

	@GetMapping("/listAll")
	@PreAuthorize(PERMIT_ALL)
	public ResponseEntity<List<CategoryListItemDTO>> listAllProductCategories() throws Exception {
		List<ProductCategory> categories = findProductCategorysBy(new HashMap<>()).getBody();

		return successful(categories.stream()
				  					.map(CategoryListItemDTO::new)
				  					.collect(Collectors.toList()));

	}


}
