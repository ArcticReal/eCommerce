package com.skytala.eCommerce.domain.product.relations.configOptionProductOption;

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
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.command.AddConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.command.DeleteConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.command.UpdateConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event.ConfigOptionProductOptionAdded;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event.ConfigOptionProductOptionDeleted;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event.ConfigOptionProductOptionFound;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event.ConfigOptionProductOptionUpdated;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.mapper.ConfigOptionProductOptionMapper;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.model.ConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.query.FindConfigOptionProductOptionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/configOptionProductOptions")
public class ConfigOptionProductOptionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ConfigOptionProductOptionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ConfigOptionProductOption
	 * @return a List with the ConfigOptionProductOptions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findConfigOptionProductOptionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindConfigOptionProductOptionsBy query = new FindConfigOptionProductOptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ConfigOptionProductOption> configOptionProductOptions =((ConfigOptionProductOptionFound) Scheduler.execute(query).data()).getConfigOptionProductOptions();

		if (configOptionProductOptions.size() == 1) {
			return ResponseEntity.ok().body(configOptionProductOptions.get(0));
		}

		return ResponseEntity.ok().body(configOptionProductOptions);

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
	public ResponseEntity<Object> createConfigOptionProductOption(HttpServletRequest request) throws Exception {

		ConfigOptionProductOption configOptionProductOptionToBeAdded = new ConfigOptionProductOption();
		try {
			configOptionProductOptionToBeAdded = ConfigOptionProductOptionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createConfigOptionProductOption(configOptionProductOptionToBeAdded);

	}

	/**
	 * creates a new ConfigOptionProductOption entry in the ofbiz database
	 * 
	 * @param configOptionProductOptionToBeAdded
	 *            the ConfigOptionProductOption thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createConfigOptionProductOption(@RequestBody ConfigOptionProductOption configOptionProductOptionToBeAdded) throws Exception {

		AddConfigOptionProductOption command = new AddConfigOptionProductOption(configOptionProductOptionToBeAdded);
		ConfigOptionProductOption configOptionProductOption = ((ConfigOptionProductOptionAdded) Scheduler.execute(command).data()).getAddedConfigOptionProductOption();
		
		if (configOptionProductOption != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(configOptionProductOption);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ConfigOptionProductOption could not be created.");
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
	public boolean updateConfigOptionProductOption(HttpServletRequest request) throws Exception {

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

		ConfigOptionProductOption configOptionProductOptionToBeUpdated = new ConfigOptionProductOption();

		try {
			configOptionProductOptionToBeUpdated = ConfigOptionProductOptionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateConfigOptionProductOption(configOptionProductOptionToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ConfigOptionProductOption with the specific Id
	 * 
	 * @param configOptionProductOptionToBeUpdated
	 *            the ConfigOptionProductOption thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateConfigOptionProductOption(@RequestBody ConfigOptionProductOption configOptionProductOptionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		configOptionProductOptionToBeUpdated.setnull(null);

		UpdateConfigOptionProductOption command = new UpdateConfigOptionProductOption(configOptionProductOptionToBeUpdated);

		try {
			if(((ConfigOptionProductOptionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{configOptionProductOptionId}")
	public ResponseEntity<Object> findById(@PathVariable String configOptionProductOptionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("configOptionProductOptionId", configOptionProductOptionId);
		try {

			Object foundConfigOptionProductOption = findConfigOptionProductOptionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundConfigOptionProductOption);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{configOptionProductOptionId}")
	public ResponseEntity<Object> deleteConfigOptionProductOptionByIdUpdated(@PathVariable String configOptionProductOptionId) throws Exception {
		DeleteConfigOptionProductOption command = new DeleteConfigOptionProductOption(configOptionProductOptionId);

		try {
			if (((ConfigOptionProductOptionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ConfigOptionProductOption could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/configOptionProductOption/\" plus one of the following: "
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
