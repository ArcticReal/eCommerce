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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ConfigOptionProductOption>> findConfigOptionProductOptionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindConfigOptionProductOptionsBy query = new FindConfigOptionProductOptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ConfigOptionProductOption> configOptionProductOptions =((ConfigOptionProductOptionFound) Scheduler.execute(query).data()).getConfigOptionProductOptions();

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
	public ResponseEntity<ConfigOptionProductOption> createConfigOptionProductOption(HttpServletRequest request) throws Exception {

		ConfigOptionProductOption configOptionProductOptionToBeAdded = new ConfigOptionProductOption();
		try {
			configOptionProductOptionToBeAdded = ConfigOptionProductOptionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ConfigOptionProductOption> createConfigOptionProductOption(@RequestBody ConfigOptionProductOption configOptionProductOptionToBeAdded) throws Exception {

		AddConfigOptionProductOption command = new AddConfigOptionProductOption(configOptionProductOptionToBeAdded);
		ConfigOptionProductOption configOptionProductOption = ((ConfigOptionProductOptionAdded) Scheduler.execute(command).data()).getAddedConfigOptionProductOption();
		
		if (configOptionProductOption != null) 
			return successful(configOptionProductOption);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateConfigOptionProductOption(@RequestBody ConfigOptionProductOption configOptionProductOptionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		configOptionProductOptionToBeUpdated.setnull(null);

		UpdateConfigOptionProductOption command = new UpdateConfigOptionProductOption(configOptionProductOptionToBeUpdated);

		try {
			if(((ConfigOptionProductOptionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{configOptionProductOptionId}")
	public ResponseEntity<ConfigOptionProductOption> findById(@PathVariable String configOptionProductOptionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("configOptionProductOptionId", configOptionProductOptionId);
		try {

			List<ConfigOptionProductOption> foundConfigOptionProductOption = findConfigOptionProductOptionsBy(requestParams).getBody();
			if(foundConfigOptionProductOption.size()==1){				return successful(foundConfigOptionProductOption.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{configOptionProductOptionId}")
	public ResponseEntity<String> deleteConfigOptionProductOptionByIdUpdated(@PathVariable String configOptionProductOptionId) throws Exception {
		DeleteConfigOptionProductOption command = new DeleteConfigOptionProductOption(configOptionProductOptionId);

		try {
			if (((ConfigOptionProductOptionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
