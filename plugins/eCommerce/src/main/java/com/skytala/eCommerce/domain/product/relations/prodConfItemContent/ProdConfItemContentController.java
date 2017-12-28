package com.skytala.eCommerce.domain.product.relations.prodConfItemContent;

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
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command.AddProdConfItemContent;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command.DeleteProdConfItemContent;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command.UpdateProdConfItemContent;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.ProdConfItemContentAdded;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.ProdConfItemContentDeleted;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.ProdConfItemContentFound;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.ProdConfItemContentUpdated;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.ProdConfItemContentMapper;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.query.FindProdConfItemContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/prodConfItemContents")
public class ProdConfItemContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdConfItemContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdConfItemContent
	 * @return a List with the ProdConfItemContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProdConfItemContent>> findProdConfItemContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdConfItemContentsBy query = new FindProdConfItemContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdConfItemContent> prodConfItemContents =((ProdConfItemContentFound) Scheduler.execute(query).data()).getProdConfItemContents();

		return ResponseEntity.ok().body(prodConfItemContents);

	}

	/**
	 * creates a new ProdConfItemContent entry in the ofbiz database
	 * 
	 * @param prodConfItemContentToBeAdded
	 *            the ProdConfItemContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProdConfItemContent> createProdConfItemContent(@RequestBody ProdConfItemContent prodConfItemContentToBeAdded) throws Exception {

		AddProdConfItemContent command = new AddProdConfItemContent(prodConfItemContentToBeAdded);
		ProdConfItemContent prodConfItemContent = ((ProdConfItemContentAdded) Scheduler.execute(command).data()).getAddedProdConfItemContent();
		
		if (prodConfItemContent != null) 
			return successful(prodConfItemContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProdConfItemContent with the specific Id
	 * 
	 * @param prodConfItemContentToBeUpdated
	 *            the ProdConfItemContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProdConfItemContent(@RequestBody ProdConfItemContent prodConfItemContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		prodConfItemContentToBeUpdated.setnull(null);

		UpdateProdConfItemContent command = new UpdateProdConfItemContent(prodConfItemContentToBeUpdated);

		try {
			if(((ProdConfItemContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{prodConfItemContentId}")
	public ResponseEntity<ProdConfItemContent> findById(@PathVariable String prodConfItemContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodConfItemContentId", prodConfItemContentId);
		try {

			List<ProdConfItemContent> foundProdConfItemContent = findProdConfItemContentsBy(requestParams).getBody();
			if(foundProdConfItemContent.size()==1){				return successful(foundProdConfItemContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{prodConfItemContentId}")
	public ResponseEntity<String> deleteProdConfItemContentByIdUpdated(@PathVariable String prodConfItemContentId) throws Exception {
		DeleteProdConfItemContent command = new DeleteProdConfItemContent(prodConfItemContentId);

		try {
			if (((ProdConfItemContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
