package com.skytala.eCommerce.domain.party.relations.party.control.dataSource;

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
import com.skytala.eCommerce.domain.party.relations.party.command.dataSource.AddPartyDataSource;
import com.skytala.eCommerce.domain.party.relations.party.command.dataSource.DeletePartyDataSource;
import com.skytala.eCommerce.domain.party.relations.party.command.dataSource.UpdatePartyDataSource;
import com.skytala.eCommerce.domain.party.relations.party.event.dataSource.PartyDataSourceAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.dataSource.PartyDataSourceDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.dataSource.PartyDataSourceFound;
import com.skytala.eCommerce.domain.party.relations.party.event.dataSource.PartyDataSourceUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.dataSource.PartyDataSourceMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.dataSource.PartyDataSource;
import com.skytala.eCommerce.domain.party.relations.party.query.dataSource.FindPartyDataSourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyDataSources")
public class PartyDataSourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyDataSourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyDataSource
	 * @return a List with the PartyDataSources
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyDataSource>> findPartyDataSourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyDataSourcesBy query = new FindPartyDataSourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyDataSource> partyDataSources =((PartyDataSourceFound) Scheduler.execute(query).data()).getPartyDataSources();

		return ResponseEntity.ok().body(partyDataSources);

	}

	/**
	 * creates a new PartyDataSource entry in the ofbiz database
	 * 
	 * @param partyDataSourceToBeAdded
	 *            the PartyDataSource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyDataSource> createPartyDataSource(@RequestBody PartyDataSource partyDataSourceToBeAdded) throws Exception {

		AddPartyDataSource command = new AddPartyDataSource(partyDataSourceToBeAdded);
		PartyDataSource partyDataSource = ((PartyDataSourceAdded) Scheduler.execute(command).data()).getAddedPartyDataSource();
		
		if (partyDataSource != null) 
			return successful(partyDataSource);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyDataSource with the specific Id
	 * 
	 * @param partyDataSourceToBeUpdated
	 *            the PartyDataSource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyDataSource(@RequestBody PartyDataSource partyDataSourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyDataSourceToBeUpdated.setnull(null);

		UpdatePartyDataSource command = new UpdatePartyDataSource(partyDataSourceToBeUpdated);

		try {
			if(((PartyDataSourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyDataSourceId}")
	public ResponseEntity<PartyDataSource> findById(@PathVariable String partyDataSourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyDataSourceId", partyDataSourceId);
		try {

			List<PartyDataSource> foundPartyDataSource = findPartyDataSourcesBy(requestParams).getBody();
			if(foundPartyDataSource.size()==1){				return successful(foundPartyDataSource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyDataSourceId}")
	public ResponseEntity<String> deletePartyDataSourceByIdUpdated(@PathVariable String partyDataSourceId) throws Exception {
		DeletePartyDataSource command = new DeletePartyDataSource(partyDataSourceId);

		try {
			if (((PartyDataSourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
