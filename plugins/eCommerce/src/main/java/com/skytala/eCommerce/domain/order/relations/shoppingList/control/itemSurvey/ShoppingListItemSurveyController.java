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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ShoppingListItemSurvey>> findShoppingListItemSurveysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListItemSurveysBy query = new FindShoppingListItemSurveysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListItemSurvey> shoppingListItemSurveys =((ShoppingListItemSurveyFound) Scheduler.execute(query).data()).getShoppingListItemSurveys();

		return ResponseEntity.ok().body(shoppingListItemSurveys);

	}

	/**
	 * creates a new ShoppingListItemSurvey entry in the ofbiz database
	 * 
	 * @param shoppingListItemSurveyToBeAdded
	 *            the ShoppingListItemSurvey thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShoppingListItemSurvey> createShoppingListItemSurvey(@RequestBody ShoppingListItemSurvey shoppingListItemSurveyToBeAdded) throws Exception {

		AddShoppingListItemSurvey command = new AddShoppingListItemSurvey(shoppingListItemSurveyToBeAdded);
		ShoppingListItemSurvey shoppingListItemSurvey = ((ShoppingListItemSurveyAdded) Scheduler.execute(command).data()).getAddedShoppingListItemSurvey();
		
		if (shoppingListItemSurvey != null) 
			return successful(shoppingListItemSurvey);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateShoppingListItemSurvey(@RequestBody ShoppingListItemSurvey shoppingListItemSurveyToBeUpdated,
			@PathVariable String shoppingListItemSeqId) throws Exception {

		shoppingListItemSurveyToBeUpdated.setShoppingListItemSeqId(shoppingListItemSeqId);

		UpdateShoppingListItemSurvey command = new UpdateShoppingListItemSurvey(shoppingListItemSurveyToBeUpdated);

		try {
			if(((ShoppingListItemSurveyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shoppingListItemSurveyId}")
	public ResponseEntity<ShoppingListItemSurvey> findById(@PathVariable String shoppingListItemSurveyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListItemSurveyId", shoppingListItemSurveyId);
		try {

			List<ShoppingListItemSurvey> foundShoppingListItemSurvey = findShoppingListItemSurveysBy(requestParams).getBody();
			if(foundShoppingListItemSurvey.size()==1){				return successful(foundShoppingListItemSurvey.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shoppingListItemSurveyId}")
	public ResponseEntity<String> deleteShoppingListItemSurveyByIdUpdated(@PathVariable String shoppingListItemSurveyId) throws Exception {
		DeleteShoppingListItemSurvey command = new DeleteShoppingListItemSurvey(shoppingListItemSurveyId);

		try {
			if (((ShoppingListItemSurveyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
