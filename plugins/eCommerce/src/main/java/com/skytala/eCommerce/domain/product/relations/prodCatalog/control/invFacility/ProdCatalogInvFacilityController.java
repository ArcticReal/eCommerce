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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProdCatalogInvFacility>> findProdCatalogInvFacilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogInvFacilitysBy query = new FindProdCatalogInvFacilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalogInvFacility> prodCatalogInvFacilitys =((ProdCatalogInvFacilityFound) Scheduler.execute(query).data()).getProdCatalogInvFacilitys();

		return ResponseEntity.ok().body(prodCatalogInvFacilitys);

	}

	/**
	 * creates a new ProdCatalogInvFacility entry in the ofbiz database
	 * 
	 * @param prodCatalogInvFacilityToBeAdded
	 *            the ProdCatalogInvFacility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProdCatalogInvFacility> createProdCatalogInvFacility(@RequestBody ProdCatalogInvFacility prodCatalogInvFacilityToBeAdded) throws Exception {

		AddProdCatalogInvFacility command = new AddProdCatalogInvFacility(prodCatalogInvFacilityToBeAdded);
		ProdCatalogInvFacility prodCatalogInvFacility = ((ProdCatalogInvFacilityAdded) Scheduler.execute(command).data()).getAddedProdCatalogInvFacility();
		
		if (prodCatalogInvFacility != null) 
			return successful(prodCatalogInvFacility);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProdCatalogInvFacility(@RequestBody ProdCatalogInvFacility prodCatalogInvFacilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		prodCatalogInvFacilityToBeUpdated.setnull(null);

		UpdateProdCatalogInvFacility command = new UpdateProdCatalogInvFacility(prodCatalogInvFacilityToBeUpdated);

		try {
			if(((ProdCatalogInvFacilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{prodCatalogInvFacilityId}")
	public ResponseEntity<ProdCatalogInvFacility> findById(@PathVariable String prodCatalogInvFacilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogInvFacilityId", prodCatalogInvFacilityId);
		try {

			List<ProdCatalogInvFacility> foundProdCatalogInvFacility = findProdCatalogInvFacilitysBy(requestParams).getBody();
			if(foundProdCatalogInvFacility.size()==1){				return successful(foundProdCatalogInvFacility.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{prodCatalogInvFacilityId}")
	public ResponseEntity<String> deleteProdCatalogInvFacilityByIdUpdated(@PathVariable String prodCatalogInvFacilityId) throws Exception {
		DeleteProdCatalogInvFacility command = new DeleteProdCatalogInvFacility(prodCatalogInvFacilityId);

		try {
			if (((ProdCatalogInvFacilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
