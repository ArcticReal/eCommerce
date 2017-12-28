package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.control.type;

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
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command.type.AddProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command.type.DeleteProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command.type.UpdateProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type.ProdConfItemContentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type.ProdConfItemContentTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type.ProdConfItemContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type.ProdConfItemContentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.type.ProdConfItemContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.type.ProdConfItemContentType;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.query.type.FindProdConfItemContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/prodConfItemContent/prodConfItemContentTypes")
public class ProdConfItemContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdConfItemContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdConfItemContentType
	 * @return a List with the ProdConfItemContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProdConfItemContentType>> findProdConfItemContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdConfItemContentTypesBy query = new FindProdConfItemContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdConfItemContentType> prodConfItemContentTypes =((ProdConfItemContentTypeFound) Scheduler.execute(query).data()).getProdConfItemContentTypes();

		return ResponseEntity.ok().body(prodConfItemContentTypes);

	}

	/**
	 * creates a new ProdConfItemContentType entry in the ofbiz database
	 * 
	 * @param prodConfItemContentTypeToBeAdded
	 *            the ProdConfItemContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProdConfItemContentType> createProdConfItemContentType(@RequestBody ProdConfItemContentType prodConfItemContentTypeToBeAdded) throws Exception {

		AddProdConfItemContentType command = new AddProdConfItemContentType(prodConfItemContentTypeToBeAdded);
		ProdConfItemContentType prodConfItemContentType = ((ProdConfItemContentTypeAdded) Scheduler.execute(command).data()).getAddedProdConfItemContentType();
		
		if (prodConfItemContentType != null) 
			return successful(prodConfItemContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProdConfItemContentType with the specific Id
	 * 
	 * @param prodConfItemContentTypeToBeUpdated
	 *            the ProdConfItemContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{confItemContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProdConfItemContentType(@RequestBody ProdConfItemContentType prodConfItemContentTypeToBeUpdated,
			@PathVariable String confItemContentTypeId) throws Exception {

		prodConfItemContentTypeToBeUpdated.setConfItemContentTypeId(confItemContentTypeId);

		UpdateProdConfItemContentType command = new UpdateProdConfItemContentType(prodConfItemContentTypeToBeUpdated);

		try {
			if(((ProdConfItemContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{prodConfItemContentTypeId}")
	public ResponseEntity<ProdConfItemContentType> findById(@PathVariable String prodConfItemContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodConfItemContentTypeId", prodConfItemContentTypeId);
		try {

			List<ProdConfItemContentType> foundProdConfItemContentType = findProdConfItemContentTypesBy(requestParams).getBody();
			if(foundProdConfItemContentType.size()==1){				return successful(foundProdConfItemContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{prodConfItemContentTypeId}")
	public ResponseEntity<String> deleteProdConfItemContentTypeByIdUpdated(@PathVariable String prodConfItemContentTypeId) throws Exception {
		DeleteProdConfItemContentType command = new DeleteProdConfItemContentType(prodConfItemContentTypeId);

		try {
			if (((ProdConfItemContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
