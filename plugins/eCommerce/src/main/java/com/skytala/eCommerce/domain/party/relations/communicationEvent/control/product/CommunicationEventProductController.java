package com.skytala.eCommerce.domain.party.relations.communicationEvent.control.product;

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
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.product.AddCommunicationEventProduct;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.product.DeleteCommunicationEventProduct;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.product.UpdateCommunicationEventProduct;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product.CommunicationEventProductAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product.CommunicationEventProductDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product.CommunicationEventProductFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product.CommunicationEventProductUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.product.CommunicationEventProductMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.product.CommunicationEventProduct;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.query.product.FindCommunicationEventProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/communicationEvent/communicationEventProducts")
public class CommunicationEventProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventProduct
	 * @return a List with the CommunicationEventProducts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventProductsBy query = new FindCommunicationEventProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventProduct> communicationEventProducts =((CommunicationEventProductFound) Scheduler.execute(query).data()).getCommunicationEventProducts();

		if (communicationEventProducts.size() == 1) {
			return ResponseEntity.ok().body(communicationEventProducts.get(0));
		}

		return ResponseEntity.ok().body(communicationEventProducts);

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
	public ResponseEntity<Object> createCommunicationEventProduct(HttpServletRequest request) throws Exception {

		CommunicationEventProduct communicationEventProductToBeAdded = new CommunicationEventProduct();
		try {
			communicationEventProductToBeAdded = CommunicationEventProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEventProduct(communicationEventProductToBeAdded);

	}

	/**
	 * creates a new CommunicationEventProduct entry in the ofbiz database
	 * 
	 * @param communicationEventProductToBeAdded
	 *            the CommunicationEventProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEventProduct(@RequestBody CommunicationEventProduct communicationEventProductToBeAdded) throws Exception {

		AddCommunicationEventProduct command = new AddCommunicationEventProduct(communicationEventProductToBeAdded);
		CommunicationEventProduct communicationEventProduct = ((CommunicationEventProductAdded) Scheduler.execute(command).data()).getAddedCommunicationEventProduct();
		
		if (communicationEventProduct != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEventProduct);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEventProduct could not be created.");
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
	public boolean updateCommunicationEventProduct(HttpServletRequest request) throws Exception {

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

		CommunicationEventProduct communicationEventProductToBeUpdated = new CommunicationEventProduct();

		try {
			communicationEventProductToBeUpdated = CommunicationEventProductMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEventProduct(communicationEventProductToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommunicationEventProduct with the specific Id
	 * 
	 * @param communicationEventProductToBeUpdated
	 *            the CommunicationEventProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommunicationEventProduct(@RequestBody CommunicationEventProduct communicationEventProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventProductToBeUpdated.setnull(null);

		UpdateCommunicationEventProduct command = new UpdateCommunicationEventProduct(communicationEventProductToBeUpdated);

		try {
			if(((CommunicationEventProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventProductId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventProductId", communicationEventProductId);
		try {

			Object foundCommunicationEventProduct = findCommunicationEventProductsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEventProduct);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventProductId}")
	public ResponseEntity<Object> deleteCommunicationEventProductByIdUpdated(@PathVariable String communicationEventProductId) throws Exception {
		DeleteCommunicationEventProduct command = new DeleteCommunicationEventProduct(communicationEventProductId);

		try {
			if (((CommunicationEventProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEventProduct could not be deleted");

	}

}
