package com.skytala.eCommerce.domain.fixedAsset;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.fixedAsset.command.AddFixedAsset;
import com.skytala.eCommerce.domain.fixedAsset.command.DeleteFixedAsset;
import com.skytala.eCommerce.domain.fixedAsset.command.UpdateFixedAsset;
import com.skytala.eCommerce.domain.fixedAsset.event.FixedAssetAdded;
import com.skytala.eCommerce.domain.fixedAsset.event.FixedAssetDeleted;
import com.skytala.eCommerce.domain.fixedAsset.event.FixedAssetFound;
import com.skytala.eCommerce.domain.fixedAsset.event.FixedAssetUpdated;
import com.skytala.eCommerce.domain.fixedAsset.mapper.FixedAssetMapper;
import com.skytala.eCommerce.domain.fixedAsset.model.FixedAsset;
import com.skytala.eCommerce.domain.fixedAsset.query.FindFixedAssetsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssets")
public class FixedAssetController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAsset
	 * @return a List with the FixedAssets
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetsBy query = new FindFixedAssetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAsset> fixedAssets =((FixedAssetFound) Scheduler.execute(query).data()).getFixedAssets();

		if (fixedAssets.size() == 1) {
			return ResponseEntity.ok().body(fixedAssets.get(0));
		}

		return ResponseEntity.ok().body(fixedAssets);

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
	public ResponseEntity<Object> createFixedAsset(HttpServletRequest request) throws Exception {

		FixedAsset fixedAssetToBeAdded = new FixedAsset();
		try {
			fixedAssetToBeAdded = FixedAssetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAsset(fixedAssetToBeAdded);

	}

	/**
	 * creates a new FixedAsset entry in the ofbiz database
	 * 
	 * @param fixedAssetToBeAdded
	 *            the FixedAsset thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAsset(@RequestBody FixedAsset fixedAssetToBeAdded) throws Exception {

		AddFixedAsset command = new AddFixedAsset(fixedAssetToBeAdded);
		FixedAsset fixedAsset = ((FixedAssetAdded) Scheduler.execute(command).data()).getAddedFixedAsset();
		
		if (fixedAsset != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAsset);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAsset could not be created.");
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
	public boolean updateFixedAsset(HttpServletRequest request) throws Exception {

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

		FixedAsset fixedAssetToBeUpdated = new FixedAsset();

		try {
			fixedAssetToBeUpdated = FixedAssetMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAsset(fixedAssetToBeUpdated, fixedAssetToBeUpdated.getFixedAssetId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAsset with the specific Id
	 * 
	 * @param fixedAssetToBeUpdated
	 *            the FixedAsset thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAsset(@RequestBody FixedAsset fixedAssetToBeUpdated,
			@PathVariable String fixedAssetId) throws Exception {

		fixedAssetToBeUpdated.setFixedAssetId(fixedAssetId);

		UpdateFixedAsset command = new UpdateFixedAsset(fixedAssetToBeUpdated);

		try {
			if(((FixedAssetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetId", fixedAssetId);
		try {

			Object foundFixedAsset = findFixedAssetsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAsset);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetId}")
	public ResponseEntity<Object> deleteFixedAssetByIdUpdated(@PathVariable String fixedAssetId) throws Exception {
		DeleteFixedAsset command = new DeleteFixedAsset(fixedAssetId);

		try {
			if (((FixedAssetDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAsset could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fixedAsset/\" plus one of the following: "
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