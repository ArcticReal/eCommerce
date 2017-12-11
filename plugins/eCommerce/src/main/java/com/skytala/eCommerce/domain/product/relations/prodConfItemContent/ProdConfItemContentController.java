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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProdConfItemContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdConfItemContentsBy query = new FindProdConfItemContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdConfItemContent> prodConfItemContents =((ProdConfItemContentFound) Scheduler.execute(query).data()).getProdConfItemContents();

		if (prodConfItemContents.size() == 1) {
			return ResponseEntity.ok().body(prodConfItemContents.get(0));
		}

		return ResponseEntity.ok().body(prodConfItemContents);

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
	public ResponseEntity<Object> createProdConfItemContent(HttpServletRequest request) throws Exception {

		ProdConfItemContent prodConfItemContentToBeAdded = new ProdConfItemContent();
		try {
			prodConfItemContentToBeAdded = ProdConfItemContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProdConfItemContent(prodConfItemContentToBeAdded);

	}

	/**
	 * creates a new ProdConfItemContent entry in the ofbiz database
	 * 
	 * @param prodConfItemContentToBeAdded
	 *            the ProdConfItemContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProdConfItemContent(@RequestBody ProdConfItemContent prodConfItemContentToBeAdded) throws Exception {

		AddProdConfItemContent command = new AddProdConfItemContent(prodConfItemContentToBeAdded);
		ProdConfItemContent prodConfItemContent = ((ProdConfItemContentAdded) Scheduler.execute(command).data()).getAddedProdConfItemContent();
		
		if (prodConfItemContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(prodConfItemContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProdConfItemContent could not be created.");
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
	public boolean updateProdConfItemContent(HttpServletRequest request) throws Exception {

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

		ProdConfItemContent prodConfItemContentToBeUpdated = new ProdConfItemContent();

		try {
			prodConfItemContentToBeUpdated = ProdConfItemContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProdConfItemContent(prodConfItemContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProdConfItemContent(@RequestBody ProdConfItemContent prodConfItemContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		prodConfItemContentToBeUpdated.setnull(null);

		UpdateProdConfItemContent command = new UpdateProdConfItemContent(prodConfItemContentToBeUpdated);

		try {
			if(((ProdConfItemContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{prodConfItemContentId}")
	public ResponseEntity<Object> findById(@PathVariable String prodConfItemContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodConfItemContentId", prodConfItemContentId);
		try {

			Object foundProdConfItemContent = findProdConfItemContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProdConfItemContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{prodConfItemContentId}")
	public ResponseEntity<Object> deleteProdConfItemContentByIdUpdated(@PathVariable String prodConfItemContentId) throws Exception {
		DeleteProdConfItemContent command = new DeleteProdConfItemContent(prodConfItemContentId);

		try {
			if (((ProdConfItemContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProdConfItemContent could not be deleted");

	}

}
