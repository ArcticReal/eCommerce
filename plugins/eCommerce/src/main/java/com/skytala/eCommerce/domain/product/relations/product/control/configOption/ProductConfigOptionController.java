package com.skytala.eCommerce.domain.product.relations.product.control.configOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.product.command.configOption.AddProductConfigOption;
import com.skytala.eCommerce.domain.product.relations.product.command.configOption.DeleteProductConfigOption;
import com.skytala.eCommerce.domain.product.relations.product.command.configOption.UpdateProductConfigOption;
import com.skytala.eCommerce.domain.product.relations.product.event.configOption.ProductConfigOptionAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.configOption.ProductConfigOptionDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.configOption.ProductConfigOptionFound;
import com.skytala.eCommerce.domain.product.relations.product.event.configOption.ProductConfigOptionUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOption.ProductConfigOptionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configOption.ProductConfigOption;
import com.skytala.eCommerce.domain.product.relations.product.query.configOption.FindProductConfigOptionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productConfigOptions")
public class ProductConfigOptionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigOptionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigOption
	 * @return a List with the ProductConfigOptions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductConfigOption>> findProductConfigOptionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigOptionsBy query = new FindProductConfigOptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigOption> productConfigOptions =((ProductConfigOptionFound) Scheduler.execute(query).data()).getProductConfigOptions();

		return ResponseEntity.ok().body(productConfigOptions);

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
	public ResponseEntity<ProductConfigOption> createProductConfigOption(HttpServletRequest request) throws Exception {

		ProductConfigOption productConfigOptionToBeAdded = new ProductConfigOption();
		try {
			productConfigOptionToBeAdded = ProductConfigOptionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductConfigOption(productConfigOptionToBeAdded);

	}

	/**
	 * creates a new ProductConfigOption entry in the ofbiz database
	 * 
	 * @param productConfigOptionToBeAdded
	 *            the ProductConfigOption thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductConfigOption> createProductConfigOption(@RequestBody ProductConfigOption productConfigOptionToBeAdded) throws Exception {

		AddProductConfigOption command = new AddProductConfigOption(productConfigOptionToBeAdded);
		ProductConfigOption productConfigOption = ((ProductConfigOptionAdded) Scheduler.execute(command).data()).getAddedProductConfigOption();
		
		if (productConfigOption != null) 
			return successful(productConfigOption);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductConfigOption with the specific Id
	 * 
	 * @param productConfigOptionToBeUpdated
	 *            the ProductConfigOption thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{configOptionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductConfigOption(@RequestBody ProductConfigOption productConfigOptionToBeUpdated,
			@PathVariable String configOptionId) throws Exception {

		productConfigOptionToBeUpdated.setConfigOptionId(configOptionId);

		UpdateProductConfigOption command = new UpdateProductConfigOption(productConfigOptionToBeUpdated);

		try {
			if(((ProductConfigOptionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productConfigOptionId}")
	public ResponseEntity<ProductConfigOption> findById(@PathVariable String productConfigOptionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigOptionId", productConfigOptionId);
		try {

			List<ProductConfigOption> foundProductConfigOption = findProductConfigOptionsBy(requestParams).getBody();
			if(foundProductConfigOption.size()==1){				return successful(foundProductConfigOption.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productConfigOptionId}")
	public ResponseEntity<String> deleteProductConfigOptionByIdUpdated(@PathVariable String productConfigOptionId) throws Exception {
		DeleteProductConfigOption command = new DeleteProductConfigOption(productConfigOptionId);

		try {
			if (((ProductConfigOptionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
