package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.control.note;

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
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.note.AddMarketingCampaignNote;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.note.DeleteMarketingCampaignNote;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.note.UpdateMarketingCampaignNote;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note.MarketingCampaignNoteAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note.MarketingCampaignNoteDeleted;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note.MarketingCampaignNoteFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note.MarketingCampaignNoteUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.note.MarketingCampaignNoteMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.note.MarketingCampaignNote;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query.note.FindMarketingCampaignNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketingCampaignNotes")
public class MarketingCampaignNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MarketingCampaignNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MarketingCampaignNote
	 * @return a List with the MarketingCampaignNotes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMarketingCampaignNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignNotesBy query = new FindMarketingCampaignNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignNote> marketingCampaignNotes =((MarketingCampaignNoteFound) Scheduler.execute(query).data()).getMarketingCampaignNotes();

		if (marketingCampaignNotes.size() == 1) {
			return ResponseEntity.ok().body(marketingCampaignNotes.get(0));
		}

		return ResponseEntity.ok().body(marketingCampaignNotes);

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
	public ResponseEntity<Object> createMarketingCampaignNote(HttpServletRequest request) throws Exception {

		MarketingCampaignNote marketingCampaignNoteToBeAdded = new MarketingCampaignNote();
		try {
			marketingCampaignNoteToBeAdded = MarketingCampaignNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMarketingCampaignNote(marketingCampaignNoteToBeAdded);

	}

	/**
	 * creates a new MarketingCampaignNote entry in the ofbiz database
	 * 
	 * @param marketingCampaignNoteToBeAdded
	 *            the MarketingCampaignNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMarketingCampaignNote(@RequestBody MarketingCampaignNote marketingCampaignNoteToBeAdded) throws Exception {

		AddMarketingCampaignNote command = new AddMarketingCampaignNote(marketingCampaignNoteToBeAdded);
		MarketingCampaignNote marketingCampaignNote = ((MarketingCampaignNoteAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignNote();
		
		if (marketingCampaignNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(marketingCampaignNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MarketingCampaignNote could not be created.");
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
	public boolean updateMarketingCampaignNote(HttpServletRequest request) throws Exception {

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

		MarketingCampaignNote marketingCampaignNoteToBeUpdated = new MarketingCampaignNote();

		try {
			marketingCampaignNoteToBeUpdated = MarketingCampaignNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMarketingCampaignNote(marketingCampaignNoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the MarketingCampaignNote with the specific Id
	 * 
	 * @param marketingCampaignNoteToBeUpdated
	 *            the MarketingCampaignNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateMarketingCampaignNote(@RequestBody MarketingCampaignNote marketingCampaignNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketingCampaignNoteToBeUpdated.setnull(null);

		UpdateMarketingCampaignNote command = new UpdateMarketingCampaignNote(marketingCampaignNoteToBeUpdated);

		try {
			if(((MarketingCampaignNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{marketingCampaignNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String marketingCampaignNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignNoteId", marketingCampaignNoteId);
		try {

			Object foundMarketingCampaignNote = findMarketingCampaignNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMarketingCampaignNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{marketingCampaignNoteId}")
	public ResponseEntity<Object> deleteMarketingCampaignNoteByIdUpdated(@PathVariable String marketingCampaignNoteId) throws Exception {
		DeleteMarketingCampaignNote command = new DeleteMarketingCampaignNote(marketingCampaignNoteId);

		try {
			if (((MarketingCampaignNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MarketingCampaignNote could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/marketingCampaignNote/\" plus one of the following: "
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
