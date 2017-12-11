package com.skytala.eCommerce.domain.humanres.relations.partyQual;

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
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.AddPartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.DeletePartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.UpdatePartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualDeleted;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualFound;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.PartyQualMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.query.FindPartyQualsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/partyQuals")
public class PartyQualController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyQualController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyQual
	 * @return a List with the PartyQuals
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyQualsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyQualsBy query = new FindPartyQualsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyQual> partyQuals =((PartyQualFound) Scheduler.execute(query).data()).getPartyQuals();

		if (partyQuals.size() == 1) {
			return ResponseEntity.ok().body(partyQuals.get(0));
		}

		return ResponseEntity.ok().body(partyQuals);

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
	public ResponseEntity<Object> createPartyQual(HttpServletRequest request) throws Exception {

		PartyQual partyQualToBeAdded = new PartyQual();
		try {
			partyQualToBeAdded = PartyQualMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyQual(partyQualToBeAdded);

	}

	/**
	 * creates a new PartyQual entry in the ofbiz database
	 * 
	 * @param partyQualToBeAdded
	 *            the PartyQual thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyQual(@RequestBody PartyQual partyQualToBeAdded) throws Exception {

		AddPartyQual command = new AddPartyQual(partyQualToBeAdded);
		PartyQual partyQual = ((PartyQualAdded) Scheduler.execute(command).data()).getAddedPartyQual();
		
		if (partyQual != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyQual);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyQual could not be created.");
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
	public boolean updatePartyQual(HttpServletRequest request) throws Exception {

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

		PartyQual partyQualToBeUpdated = new PartyQual();

		try {
			partyQualToBeUpdated = PartyQualMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyQual(partyQualToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyQual with the specific Id
	 * 
	 * @param partyQualToBeUpdated
	 *            the PartyQual thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyQual(@RequestBody PartyQual partyQualToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyQualToBeUpdated.setnull(null);

		UpdatePartyQual command = new UpdatePartyQual(partyQualToBeUpdated);

		try {
			if(((PartyQualUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyQualId}")
	public ResponseEntity<Object> findById(@PathVariable String partyQualId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyQualId", partyQualId);
		try {

			Object foundPartyQual = findPartyQualsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyQual);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyQualId}")
	public ResponseEntity<Object> deletePartyQualByIdUpdated(@PathVariable String partyQualId) throws Exception {
		DeletePartyQual command = new DeletePartyQual(partyQualId);

		try {
			if (((PartyQualDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyQual could not be deleted");

	}

}
