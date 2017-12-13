package com.skytala.eCommerce.domain.product.relations.prodCatalog.control.invFacility;

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
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.invFacility.AddProdCatalogInvFacility;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.invFacility.DeleteProdCatalogInvFacility;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.invFacility.UpdateProdCatalogInvFacility;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility.ProdCatalogInvFacilityAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility.ProdCatalogInvFacilityDeleted;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility.ProdCatalogInvFacilityFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility.ProdCatalogInvFacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.invFacility.ProdCatalogInvFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.invFacility.ProdCatalogInvFacility;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.query.invFacility.FindProdCatalogInvFacilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/prodCatalog/prodCatalogInvFacilitys")
public class ProdCatalogInvFacilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdCatalogInvFacilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdCatalogInvFacility
	 * @return a List with the ProdCatalogInvFacilitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProdCatalogInvFacilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogInvFacilitysBy query = new FindProdCatalogInvFacilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalogInvFacility> prodCatalogInvFacilitys =((ProdCatalogInvFacilityFound) Scheduler.execute(query).data()).getProdCatalogInvFacilitys();

		if (prodCatalogInvFacilitys.size() == 1) {
			return ResponseEntity.ok().body(prodCatalogInvFacilitys.get(0));
		}

		return ResponseEntity.ok().body(prodCatalogInvFacilitys);

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
	public ResponseEntity<Object> createProdCatalogInvFacility(HttpServletRequest request) throws Exception {

		ProdCatalogInvFacility prodCatalogInvFacilityToBeAdded = new ProdCatalogInvFacility();
		try {
			prodCatalogInvFacilityToBeAdded = ProdCatalogInvFacilityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProdCatalogInvFacility(prodCatalogInvFacilityToBeAdded);

	}

	/**
	 * creates a new ProdCatalogInvFacility entry in the ofbiz database
	 * 
	 * @param prodCatalogInvFacilityToBeAdded
	 *            the ProdCatalogInvFacility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProdCatalogInvFacility(@RequestBody ProdCatalogInvFacility prodCatalogInvFacilityToBeAdded) throws Exception {

		AddProdCatalogInvFacility command = new AddProdCatalogInvFacility(prodCatalogInvFacilityToBeAdded);
		ProdCatalogInvFacility prodCatalogInvFacility = ((ProdCatalogInvFacilityAdded) Scheduler.execute(command).data()).getAddedProdCatalogInvFacility();
		
		if (prodCatalogInvFacility != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(prodCatalogInvFacility);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProdCatalogInvFacility could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProdCatalogInvFacility(HttpServletRequest request) throws Exception {

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

		ProdCatalogInvFacility prodCatalogInvFacilityToBeUpdated = new ProdCatalogInvFacility();

		try {
			prodCatalogInvFacilityToBeUpdated = ProdCatalogInvFacilityMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProdCatalogInvFacility(prodCatalogInvFacilityToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProdCatalogInvFacility with the specific Id
	 * 
	 * @param prodCatalogInvFacilityToBeUpdated
	 *            the ProdCatalogInvFacility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProdCatalogInvFacility(@RequestBody ProdCatalogInvFacility prodCatalogInvFacilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		prodCatalogInvFacilityToBeUpdated.setnull(null);

		UpdateProdCatalogInvFacility command = new UpdateProdCatalogInvFacility(prodCatalogInvFacilityToBeUpdated);

		try {
			if(((ProdCatalogInvFacilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{prodCatalogInvFacilityId}")
	public ResponseEntity<Object> findById(@PathVariable String prodCatalogInvFacilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogInvFacilityId", prodCatalogInvFacilityId);
		try {

			Object foundProdCatalogInvFacility = findProdCatalogInvFacilitysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProdCatalogInvFacility);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{prodCatalogInvFacilityId}")
	public ResponseEntity<Object> deleteProdCatalogInvFacilityByIdUpdated(@PathVariable String prodCatalogInvFacilityId) throws Exception {
		DeleteProdCatalogInvFacility command = new DeleteProdCatalogInvFacility(prodCatalogInvFacilityId);

		try {
			if (((ProdCatalogInvFacilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProdCatalogInvFacility could not be deleted");

	}

}
