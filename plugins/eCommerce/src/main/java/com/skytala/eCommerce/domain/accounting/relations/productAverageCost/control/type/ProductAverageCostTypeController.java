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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/productAverageCostTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductAverageCostTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAverageCostTypesBy query = new FindProductAverageCostTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAverageCostType> productAverageCostTypes =((ProductAverageCostTypeFound) Scheduler.execute(query).data()).getProductAverageCostTypes();

		if (productAverageCostTypes.size() == 1) {
			return ResponseEntity.ok().body(productAverageCostTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductAverageCostType(HttpServletRequest request) throws Exception {

		ProductAverageCostType productAverageCostTypeToBeAdded = new ProductAverageCostType();
		try {
			productAverageCostTypeToBeAdded = ProductAverageCostTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductAverageCostType(@RequestBody ProductAverageCostType productAverageCostTypeToBeAdded) throws Exception {

		AddProductAverageCostType command = new AddProductAverageCostType(productAverageCostTypeToBeAdded);
		ProductAverageCostType productAverageCostType = ((ProductAverageCostTypeAdded) Scheduler.execute(command).data()).getAddedProductAverageCostType();
		
		if (productAverageCostType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productAverageCostType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductAverageCostType could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductAverageCostType(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ProductAverageCostType productAverageCostTypeToBeUpdated = new ProductAverageCostType();

		try {
			productAverageCostTypeToBeUpdated = ProductAverageCostTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductAverageCostType(productAverageCostTypeToBeUpdated, productAverageCostTypeToBeUpdated.getProductAverageCostTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductAverageCostType(@RequestBody ProductAverageCostType productAverageCostTypeToBeUpdated,
			@PathVariable String productAverageCostTypeId) throws Exception {

		productAverageCostTypeToBeUpdated.setProductAverageCostTypeId(productAverageCostTypeId);

		UpdateProductAverageCostType command = new UpdateProductAverageCostType(productAverageCostTypeToBeUpdated);

		try {
			if(((ProductAverageCostTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productAverageCostTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productAverageCostTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAverageCostTypeId", productAverageCostTypeId);
		try {

			Object foundProductAverageCostType = findProductAverageCostTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductAverageCostType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productAverageCostTypeId}")
	public ResponseEntity<Object> deleteProductAverageCostTypeByIdUpdated(@PathVariable String productAverageCostTypeId) throws Exception {
		DeleteProductAverageCostType command = new DeleteProductAverageCostType(productAverageCostTypeId);

		try {
			if (((ProductAverageCostTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductAverageCostType could not be deleted");

	}

}
