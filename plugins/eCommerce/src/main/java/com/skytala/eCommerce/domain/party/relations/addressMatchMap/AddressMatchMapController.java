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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<AddressMatchMap>> findAddressMatchMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAddressMatchMapsBy query = new FindAddressMatchMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AddressMatchMap> addressMatchMaps =((AddressMatchMapFound) Scheduler.execute(query).data()).getAddressMatchMaps();

		return ResponseEntity.ok().body(addressMatchMaps);

	}

	/**
	 * creates a new AddressMatchMap entry in the ofbiz database
	 * 
	 * @param addressMatchMapToBeAdded
	 *            the AddressMatchMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AddressMatchMap> createAddressMatchMap(@RequestBody AddressMatchMap addressMatchMapToBeAdded) throws Exception {

		AddAddressMatchMap command = new AddAddressMatchMap(addressMatchMapToBeAdded);
		AddressMatchMap addressMatchMap = ((AddressMatchMapAdded) Scheduler.execute(command).data()).getAddedAddressMatchMap();
		
		if (addressMatchMap != null) 
			return successful(addressMatchMap);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAddressMatchMap(@RequestBody AddressMatchMap addressMatchMapToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		addressMatchMapToBeUpdated.setnull(null);

		UpdateAddressMatchMap command = new UpdateAddressMatchMap(addressMatchMapToBeUpdated);

		try {
			if(((AddressMatchMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{addressMatchMapId}")
	public ResponseEntity<AddressMatchMap> findById(@PathVariable String addressMatchMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("addressMatchMapId", addressMatchMapId);
		try {

			List<AddressMatchMap> foundAddressMatchMap = findAddressMatchMapsBy(requestParams).getBody();
			if(foundAddressMatchMap.size()==1){				return successful(foundAddressMatchMap.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{addressMatchMapId}")
	public ResponseEntity<String> deleteAddressMatchMapByIdUpdated(@PathVariable String addressMatchMapId) throws Exception {
		DeleteAddressMatchMap command = new DeleteAddressMatchMap(addressMatchMapId);

		try {
			if (((AddressMatchMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
