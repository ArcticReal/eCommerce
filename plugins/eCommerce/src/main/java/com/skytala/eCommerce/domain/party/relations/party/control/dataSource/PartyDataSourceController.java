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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findPartyDataSourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyDataSourcesBy query = new FindPartyDataSourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyDataSource> partyDataSources =((PartyDataSourceFound) Scheduler.execute(query).data()).getPartyDataSources();

		if (partyDataSources.size() == 1) {
			return ResponseEntity.ok().body(partyDataSources.get(0));
		}

		return ResponseEntity.ok().body(partyDataSources);

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
	public ResponseEntity<Object> createPartyDataSource(HttpServletRequest request) throws Exception {

		PartyDataSource partyDataSourceToBeAdded = new PartyDataSource();
		try {
			partyDataSourceToBeAdded = PartyDataSourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyDataSource(partyDataSourceToBeAdded);

	}

	/**
	 * creates a new PartyDataSource entry in the ofbiz database
	 * 
	 * @param partyDataSourceToBeAdded
	 *            the PartyDataSource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyDataSource(@RequestBody PartyDataSource partyDataSourceToBeAdded) throws Exception {

		AddPartyDataSource command = new AddPartyDataSource(partyDataSourceToBeAdded);
		PartyDataSource partyDataSource = ((PartyDataSourceAdded) Scheduler.execute(command).data()).getAddedPartyDataSource();
		
		if (partyDataSource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyDataSource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyDataSource could not be created.");
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
	public boolean updatePartyDataSource(HttpServletRequest request) throws Exception {

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

		PartyDataSource partyDataSourceToBeUpdated = new PartyDataSource();

		try {
			partyDataSourceToBeUpdated = PartyDataSourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyDataSource(partyDataSourceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyDataSource(@RequestBody PartyDataSource partyDataSourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyDataSourceToBeUpdated.setnull(null);

		UpdatePartyDataSource command = new UpdatePartyDataSource(partyDataSourceToBeUpdated);

		try {
			if(((PartyDataSourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyDataSourceId}")
	public ResponseEntity<Object> findById(@PathVariable String partyDataSourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyDataSourceId", partyDataSourceId);
		try {

			Object foundPartyDataSource = findPartyDataSourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyDataSource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyDataSourceId}")
	public ResponseEntity<Object> deletePartyDataSourceByIdUpdated(@PathVariable String partyDataSourceId) throws Exception {
		DeletePartyDataSource command = new DeletePartyDataSource(partyDataSourceId);

		try {
			if (((PartyDataSourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyDataSource could not be deleted");

	}

}
