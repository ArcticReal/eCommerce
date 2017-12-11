package com.skytala.eCommerce.domain.content.relations.webSitePathAlias;

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
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.command.AddWebSitePathAlias;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.command.DeleteWebSitePathAlias;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.command.UpdateWebSitePathAlias;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event.WebSitePathAliasAdded;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event.WebSitePathAliasDeleted;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event.WebSitePathAliasFound;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event.WebSitePathAliasUpdated;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.mapper.WebSitePathAliasMapper;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.query.FindWebSitePathAliassBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/webSitePathAliass")
public class WebSitePathAliasController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebSitePathAliasController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebSitePathAlias
	 * @return a List with the WebSitePathAliass
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWebSitePathAliassBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSitePathAliassBy query = new FindWebSitePathAliassBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSitePathAlias> webSitePathAliass =((WebSitePathAliasFound) Scheduler.execute(query).data()).getWebSitePathAliass();

		if (webSitePathAliass.size() == 1) {
			return ResponseEntity.ok().body(webSitePathAliass.get(0));
		}

		return ResponseEntity.ok().body(webSitePathAliass);

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
	public ResponseEntity<Object> createWebSitePathAlias(HttpServletRequest request) throws Exception {

		WebSitePathAlias webSitePathAliasToBeAdded = new WebSitePathAlias();
		try {
			webSitePathAliasToBeAdded = WebSitePathAliasMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWebSitePathAlias(webSitePathAliasToBeAdded);

	}

	/**
	 * creates a new WebSitePathAlias entry in the ofbiz database
	 * 
	 * @param webSitePathAliasToBeAdded
	 *            the WebSitePathAlias thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWebSitePathAlias(@RequestBody WebSitePathAlias webSitePathAliasToBeAdded) throws Exception {

		AddWebSitePathAlias command = new AddWebSitePathAlias(webSitePathAliasToBeAdded);
		WebSitePathAlias webSitePathAlias = ((WebSitePathAliasAdded) Scheduler.execute(command).data()).getAddedWebSitePathAlias();
		
		if (webSitePathAlias != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(webSitePathAlias);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WebSitePathAlias could not be created.");
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
	public boolean updateWebSitePathAlias(HttpServletRequest request) throws Exception {

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

		WebSitePathAlias webSitePathAliasToBeUpdated = new WebSitePathAlias();

		try {
			webSitePathAliasToBeUpdated = WebSitePathAliasMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWebSitePathAlias(webSitePathAliasToBeUpdated, webSitePathAliasToBeUpdated.getPathAlias()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WebSitePathAlias with the specific Id
	 * 
	 * @param webSitePathAliasToBeUpdated
	 *            the WebSitePathAlias thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{pathAlias}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWebSitePathAlias(@RequestBody WebSitePathAlias webSitePathAliasToBeUpdated,
			@PathVariable String pathAlias) throws Exception {

		webSitePathAliasToBeUpdated.setPathAlias(pathAlias);

		UpdateWebSitePathAlias command = new UpdateWebSitePathAlias(webSitePathAliasToBeUpdated);

		try {
			if(((WebSitePathAliasUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{webSitePathAliasId}")
	public ResponseEntity<Object> findById(@PathVariable String webSitePathAliasId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSitePathAliasId", webSitePathAliasId);
		try {

			Object foundWebSitePathAlias = findWebSitePathAliassBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWebSitePathAlias);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{webSitePathAliasId}")
	public ResponseEntity<Object> deleteWebSitePathAliasByIdUpdated(@PathVariable String webSitePathAliasId) throws Exception {
		DeleteWebSitePathAlias command = new DeleteWebSitePathAlias(webSitePathAliasId);

		try {
			if (((WebSitePathAliasDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WebSitePathAlias could not be deleted");

	}

}
