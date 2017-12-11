package com.skytala.eCommerce.domain.party.relations.addressMatchMap;

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
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.command.AddAddressMatchMap;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.command.DeleteAddressMatchMap;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.command.UpdateAddressMatchMap;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.event.AddressMatchMapAdded;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.event.AddressMatchMapDeleted;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.event.AddressMatchMapFound;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.event.AddressMatchMapUpdated;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.mapper.AddressMatchMapMapper;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.query.FindAddressMatchMapsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/addressMatchMaps")
public class AddressMatchMapController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AddressMatchMapController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AddressMatchMap
	 * @return a List with the AddressMatchMaps
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAddressMatchMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAddressMatchMapsBy query = new FindAddressMatchMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AddressMatchMap> addressMatchMaps =((AddressMatchMapFound) Scheduler.execute(query).data()).getAddressMatchMaps();

		if (addressMatchMaps.size() == 1) {
			return ResponseEntity.ok().body(addressMatchMaps.get(0));
		}

		return ResponseEntity.ok().body(addressMatchMaps);

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
	public ResponseEntity<Object> createAddressMatchMap(HttpServletRequest request) throws Exception {

		AddressMatchMap addressMatchMapToBeAdded = new AddressMatchMap();
		try {
			addressMatchMapToBeAdded = AddressMatchMapMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAddressMatchMap(addressMatchMapToBeAdded);

	}

	/**
	 * creates a new AddressMatchMap entry in the ofbiz database
	 * 
	 * @param addressMatchMapToBeAdded
	 *            the AddressMatchMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAddressMatchMap(@RequestBody AddressMatchMap addressMatchMapToBeAdded) throws Exception {

		AddAddressMatchMap command = new AddAddressMatchMap(addressMatchMapToBeAdded);
		AddressMatchMap addressMatchMap = ((AddressMatchMapAdded) Scheduler.execute(command).data()).getAddedAddressMatchMap();
		
		if (addressMatchMap != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(addressMatchMap);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AddressMatchMap could not be created.");
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
	public boolean updateAddressMatchMap(HttpServletRequest request) throws Exception {

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

		AddressMatchMap addressMatchMapToBeUpdated = new AddressMatchMap();

		try {
			addressMatchMapToBeUpdated = AddressMatchMapMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAddressMatchMap(addressMatchMapToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AddressMatchMap with the specific Id
	 * 
	 * @param addressMatchMapToBeUpdated
	 *            the AddressMatchMap thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAddressMatchMap(@RequestBody AddressMatchMap addressMatchMapToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		addressMatchMapToBeUpdated.setnull(null);

		UpdateAddressMatchMap command = new UpdateAddressMatchMap(addressMatchMapToBeUpdated);

		try {
			if(((AddressMatchMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{addressMatchMapId}")
	public ResponseEntity<Object> findById(@PathVariable String addressMatchMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("addressMatchMapId", addressMatchMapId);
		try {

			Object foundAddressMatchMap = findAddressMatchMapsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAddressMatchMap);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{addressMatchMapId}")
	public ResponseEntity<Object> deleteAddressMatchMapByIdUpdated(@PathVariable String addressMatchMapId) throws Exception {
		DeleteAddressMatchMap command = new DeleteAddressMatchMap(addressMatchMapId);

		try {
			if (((AddressMatchMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AddressMatchMap could not be deleted");

	}

}
