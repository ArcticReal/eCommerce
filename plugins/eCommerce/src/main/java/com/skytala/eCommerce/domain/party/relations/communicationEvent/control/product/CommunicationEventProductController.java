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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEventProduct>> findCommunicationEventProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventProductsBy query = new FindCommunicationEventProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventProduct> communicationEventProducts =((CommunicationEventProductFound) Scheduler.execute(query).data()).getCommunicationEventProducts();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<CommunicationEventProduct> createCommunicationEventProduct(HttpServletRequest request) throws Exception {

		CommunicationEventProduct communicationEventProductToBeAdded = new CommunicationEventProduct();
		try {
			communicationEventProductToBeAdded = CommunicationEventProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<CommunicationEventProduct> createCommunicationEventProduct(@RequestBody CommunicationEventProduct communicationEventProductToBeAdded) throws Exception {

		AddCommunicationEventProduct command = new AddCommunicationEventProduct(communicationEventProductToBeAdded);
		CommunicationEventProduct communicationEventProduct = ((CommunicationEventProductAdded) Scheduler.execute(command).data()).getAddedCommunicationEventProduct();
		
		if (communicationEventProduct != null) 
			return successful(communicationEventProduct);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCommunicationEventProduct(@RequestBody CommunicationEventProduct communicationEventProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventProductToBeUpdated.setnull(null);

		UpdateCommunicationEventProduct command = new UpdateCommunicationEventProduct(communicationEventProductToBeUpdated);

		try {
			if(((CommunicationEventProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventProductId}")
	public ResponseEntity<CommunicationEventProduct> findById(@PathVariable String communicationEventProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventProductId", communicationEventProductId);
		try {

			List<CommunicationEventProduct> foundCommunicationEventProduct = findCommunicationEventProductsBy(requestParams).getBody();
			if(foundCommunicationEventProduct.size()==1){				return successful(foundCommunicationEventProduct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventProductId}")
	public ResponseEntity<String> deleteCommunicationEventProductByIdUpdated(@PathVariable String communicationEventProductId) throws Exception {
		DeleteCommunicationEventProduct command = new DeleteCommunicationEventProduct(communicationEventProductId);

		try {
			if (((CommunicationEventProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
