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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/marketingCampaign/marketingCampaignNotes")
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
	@GetMapping("/find")
	public ResponseEntity<List<MarketingCampaignNote>> findMarketingCampaignNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignNotesBy query = new FindMarketingCampaignNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignNote> marketingCampaignNotes =((MarketingCampaignNoteFound) Scheduler.execute(query).data()).getMarketingCampaignNotes();

		return ResponseEntity.ok().body(marketingCampaignNotes);

	}

	/**
	 * creates a new MarketingCampaignNote entry in the ofbiz database
	 * 
	 * @param marketingCampaignNoteToBeAdded
	 *            the MarketingCampaignNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MarketingCampaignNote> createMarketingCampaignNote(@RequestBody MarketingCampaignNote marketingCampaignNoteToBeAdded) throws Exception {

		AddMarketingCampaignNote command = new AddMarketingCampaignNote(marketingCampaignNoteToBeAdded);
		MarketingCampaignNote marketingCampaignNote = ((MarketingCampaignNoteAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignNote();
		
		if (marketingCampaignNote != null) 
			return successful(marketingCampaignNote);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateMarketingCampaignNote(@RequestBody MarketingCampaignNote marketingCampaignNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketingCampaignNoteToBeUpdated.setnull(null);

		UpdateMarketingCampaignNote command = new UpdateMarketingCampaignNote(marketingCampaignNoteToBeUpdated);

		try {
			if(((MarketingCampaignNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{marketingCampaignNoteId}")
	public ResponseEntity<MarketingCampaignNote> findById(@PathVariable String marketingCampaignNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignNoteId", marketingCampaignNoteId);
		try {

			List<MarketingCampaignNote> foundMarketingCampaignNote = findMarketingCampaignNotesBy(requestParams).getBody();
			if(foundMarketingCampaignNote.size()==1){				return successful(foundMarketingCampaignNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{marketingCampaignNoteId}")
	public ResponseEntity<String> deleteMarketingCampaignNoteByIdUpdated(@PathVariable String marketingCampaignNoteId) throws Exception {
		DeleteMarketingCampaignNote command = new DeleteMarketingCampaignNote(marketingCampaignNoteId);

		try {
			if (((MarketingCampaignNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
