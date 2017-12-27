package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command.type.AddProductAverageCostType;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command.type.DeleteProductAverageCostType;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command.type.UpdateProductAverageCostType;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type.ProductAverageCostTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type.ProductAverageCostTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type.ProductAverageCostTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type.ProductAverageCostTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.mapper.type.ProductAverageCostTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.type.ProductAverageCostType;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.query.type.FindProductAverageCostTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/productAverageCost/productAverageCostTypes")
public class ProductAverageCostTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductAverageCostTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductAverageCostType
	 * @return a List with the ProductAverageCostTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductAverageCostType>> findProductAverageCostTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAverageCostTypesBy query = new FindProductAverageCostTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAverageCostType> productAverageCostTypes =((ProductAverageCostTypeFound) Scheduler.execute(query).data()).getProductAverageCostTypes();

		return ResponseEntity.ok().body(productAverageCostTypes);

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
	public ResponseEntity<ProductAverageCostType> createProductAverageCostType(HttpServletRequest request) throws Exception {

		ProductAverageCostType productAverageCostTypeToBeAdded = new ProductAverageCostType();
		try {
			productAverageCostTypeToBeAdded = ProductAverageCostTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductAverageCostType(productAverageCostTypeToBeAdded);

	}

	/**
	 * creates a new ProductAverageCostType entry in the ofbiz database
	 * 
	 * @param productAverageCostTypeToBeAdded
	 *            the ProductAverageCostType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductAverageCostType> createProductAverageCostType(@RequestBody ProductAverageCostType productAverageCostTypeToBeAdded) throws Exception {

		AddProductAverageCostType command = new AddProductAverageCostType(productAverageCostTypeToBeAdded);
		ProductAverageCostType productAverageCostType = ((ProductAverageCostTypeAdded) Scheduler.execute(command).data()).getAddedProductAverageCostType();
		
		if (productAverageCostType != null) 
			return successful(productAverageCostType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductAverageCostType with the specific Id
	 * 
	 * @param productAverageCostTypeToBeUpdated
	 *            the ProductAverageCostType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productAverageCostTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductAverageCostType(@RequestBody ProductAverageCostType productAverageCostTypeToBeUpdated,
			@PathVariable String productAverageCostTypeId) throws Exception {

		productAverageCostTypeToBeUpdated.setProductAverageCostTypeId(productAverageCostTypeId);

		UpdateProductAverageCostType command = new UpdateProductAverageCostType(productAverageCostTypeToBeUpdated);

		try {
			if(((ProductAverageCostTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productAverageCostTypeId}")
	public ResponseEntity<ProductAverageCostType> findById(@PathVariable String productAverageCostTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAverageCostTypeId", productAverageCostTypeId);
		try {

			List<ProductAverageCostType> foundProductAverageCostType = findProductAverageCostTypesBy(requestParams).getBody();
			if(foundProductAverageCostType.size()==1){				return successful(foundProductAverageCostType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productAverageCostTypeId}")
	public ResponseEntity<String> deleteProductAverageCostTypeByIdUpdated(@PathVariable String productAverageCostTypeId) throws Exception {
		DeleteProductAverageCostType command = new DeleteProductAverageCostType(productAverageCostTypeId);

		try {
			if (((ProductAverageCostTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
