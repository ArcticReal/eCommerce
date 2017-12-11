package com.skytala.eCommerce.domain.order.relations.shoppingList.control.itemSurvey;

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
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.itemSurvey.AddShoppingListItemSurvey;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.itemSurvey.DeleteShoppingListItemSurvey;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.itemSurvey.UpdateShoppingListItemSurvey;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey.ShoppingListItemSurveyAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey.ShoppingListItemSurveyDeleted;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey.ShoppingListItemSurveyFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey.ShoppingListItemSurveyUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.itemSurvey.ShoppingListItemSurveyMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.itemSurvey.ShoppingListItemSurvey;
import com.skytala.eCommerce.domain.order.relations.shoppingList.query.itemSurvey.FindShoppingListItemSurveysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/shoppingList/shoppingListItemSurveys")
public class ShoppingListItemSurveyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShoppingListItemSurveyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShoppingListItemSurvey
	 * @return a List with the ShoppingListItemSurveys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findShoppingListItemSurveysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListItemSurveysBy query = new FindShoppingListItemSurveysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListItemSurvey> shoppingListItemSurveys =((ShoppingListItemSurveyFound) Scheduler.execute(query).data()).getShoppingListItemSurveys();

		if (shoppingListItemSurveys.size() == 1) {
			return ResponseEntity.ok().body(shoppingListItemSurveys.get(0));
		}

		return ResponseEntity.ok().body(shoppingListItemSurveys);

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
	public ResponseEntity<Object> createShoppingListItemSurvey(HttpServletRequest request) throws Exception {

		ShoppingListItemSurvey shoppingListItemSurveyToBeAdded = new ShoppingListItemSurvey();
		try {
			shoppingListItemSurveyToBeAdded = ShoppingListItemSurveyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShoppingListItemSurvey(shoppingListItemSurveyToBeAdded);

	}

	/**
	 * creates a new ShoppingListItemSurvey entry in the ofbiz database
	 * 
	 * @param shoppingListItemSurveyToBeAdded
	 *            the ShoppingListItemSurvey thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShoppingListItemSurvey(@RequestBody ShoppingListItemSurvey shoppingListItemSurveyToBeAdded) throws Exception {

		AddShoppingListItemSurvey command = new AddShoppingListItemSurvey(shoppingListItemSurveyToBeAdded);
		ShoppingListItemSurvey shoppingListItemSurvey = ((ShoppingListItemSurveyAdded) Scheduler.execute(command).data()).getAddedShoppingListItemSurvey();
		
		if (shoppingListItemSurvey != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shoppingListItemSurvey);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShoppingListItemSurvey could not be created.");
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
	public boolean updateShoppingListItemSurvey(HttpServletRequest request) throws Exception {

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

		ShoppingListItemSurvey shoppingListItemSurveyToBeUpdated = new ShoppingListItemSurvey();

		try {
			shoppingListItemSurveyToBeUpdated = ShoppingListItemSurveyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShoppingListItemSurvey(shoppingListItemSurveyToBeUpdated, shoppingListItemSurveyToBeUpdated.getShoppingListItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShoppingListItemSurvey with the specific Id
	 * 
	 * @param shoppingListItemSurveyToBeUpdated
	 *            the ShoppingListItemSurvey thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shoppingListItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShoppingListItemSurvey(@RequestBody ShoppingListItemSurvey shoppingListItemSurveyToBeUpdated,
			@PathVariable String shoppingListItemSeqId) throws Exception {

		shoppingListItemSurveyToBeUpdated.setShoppingListItemSeqId(shoppingListItemSeqId);

		UpdateShoppingListItemSurvey command = new UpdateShoppingListItemSurvey(shoppingListItemSurveyToBeUpdated);

		try {
			if(((ShoppingListItemSurveyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{shoppingListItemSurveyId}")
	public ResponseEntity<Object> findById(@PathVariable String shoppingListItemSurveyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListItemSurveyId", shoppingListItemSurveyId);
		try {

			Object foundShoppingListItemSurvey = findShoppingListItemSurveysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShoppingListItemSurvey);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{shoppingListItemSurveyId}")
	public ResponseEntity<Object> deleteShoppingListItemSurveyByIdUpdated(@PathVariable String shoppingListItemSurveyId) throws Exception {
		DeleteShoppingListItemSurvey command = new DeleteShoppingListItemSurvey(shoppingListItemSurveyId);

		try {
			if (((ShoppingListItemSurveyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShoppingListItemSurvey could not be deleted");

	}

}
