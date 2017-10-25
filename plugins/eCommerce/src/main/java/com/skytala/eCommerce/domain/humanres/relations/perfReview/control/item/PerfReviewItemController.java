package com.skytala.eCommerce.domain.humanres.relations.perfReview.control.item;

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
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item.AddPerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item.DeletePerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item.UpdatePerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemDeleted;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemFound;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.item.PerfReviewItemMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.item.PerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.query.item.FindPerfReviewItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/perfReviewItems")
public class PerfReviewItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PerfReviewItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PerfReviewItem
	 * @return a List with the PerfReviewItems
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPerfReviewItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerfReviewItemsBy query = new FindPerfReviewItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerfReviewItem> perfReviewItems =((PerfReviewItemFound) Scheduler.execute(query).data()).getPerfReviewItems();

		if (perfReviewItems.size() == 1) {
			return ResponseEntity.ok().body(perfReviewItems.get(0));
		}

		return ResponseEntity.ok().body(perfReviewItems);

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
	public ResponseEntity<Object> createPerfReviewItem(HttpServletRequest request) throws Exception {

		PerfReviewItem perfReviewItemToBeAdded = new PerfReviewItem();
		try {
			perfReviewItemToBeAdded = PerfReviewItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPerfReviewItem(perfReviewItemToBeAdded);

	}

	/**
	 * creates a new PerfReviewItem entry in the ofbiz database
	 * 
	 * @param perfReviewItemToBeAdded
	 *            the PerfReviewItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPerfReviewItem(@RequestBody PerfReviewItem perfReviewItemToBeAdded) throws Exception {

		AddPerfReviewItem command = new AddPerfReviewItem(perfReviewItemToBeAdded);
		PerfReviewItem perfReviewItem = ((PerfReviewItemAdded) Scheduler.execute(command).data()).getAddedPerfReviewItem();
		
		if (perfReviewItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(perfReviewItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PerfReviewItem could not be created.");
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
	public boolean updatePerfReviewItem(HttpServletRequest request) throws Exception {

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

		PerfReviewItem perfReviewItemToBeUpdated = new PerfReviewItem();

		try {
			perfReviewItemToBeUpdated = PerfReviewItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePerfReviewItem(perfReviewItemToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PerfReviewItem with the specific Id
	 * 
	 * @param perfReviewItemToBeUpdated
	 *            the PerfReviewItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePerfReviewItem(@RequestBody PerfReviewItem perfReviewItemToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		perfReviewItemToBeUpdated.setnull(null);

		UpdatePerfReviewItem command = new UpdatePerfReviewItem(perfReviewItemToBeUpdated);

		try {
			if(((PerfReviewItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{perfReviewItemId}")
	public ResponseEntity<Object> findById(@PathVariable String perfReviewItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("perfReviewItemId", perfReviewItemId);
		try {

			Object foundPerfReviewItem = findPerfReviewItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPerfReviewItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{perfReviewItemId}")
	public ResponseEntity<Object> deletePerfReviewItemByIdUpdated(@PathVariable String perfReviewItemId) throws Exception {
		DeletePerfReviewItem command = new DeletePerfReviewItem(perfReviewItemId);

		try {
			if (((PerfReviewItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PerfReviewItem could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/perfReviewItem/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
