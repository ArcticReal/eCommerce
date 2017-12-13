package com.skytala.eCommerce.domain.product.relations.product.control.configOptionOption;

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
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionOption.AddConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionOption.DeleteConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionOption.UpdateConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionOption.ConfigOptionProductOptionAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionOption.ConfigOptionProductOptionDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionOption.ConfigOptionProductOptionFound;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionOption.ConfigOptionProductOptionUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOptionOption.ConfigOptionProductOptionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configOptionOption.ConfigOptionProductOption;
import com.skytala.eCommerce.domain.product.relations.product.query.configOptionOption.FindConfigOptionProductOptionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/configOptionProductOptions")
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
	@GetMapping("/find")
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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
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

	@GetMapping("/{configOptionProductOptionId}")
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

	@DeleteMapping("/{configOptionProductOptionId}")
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

}
