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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<WebSitePathAlias>> findWebSitePathAliassBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSitePathAliassBy query = new FindWebSitePathAliassBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSitePathAlias> webSitePathAliass =((WebSitePathAliasFound) Scheduler.execute(query).data()).getWebSitePathAliass();

		return ResponseEntity.ok().body(webSitePathAliass);

	}

	/**
	 * creates a new WebSitePathAlias entry in the ofbiz database
	 * 
	 * @param webSitePathAliasToBeAdded
	 *            the WebSitePathAlias thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WebSitePathAlias> createWebSitePathAlias(@RequestBody WebSitePathAlias webSitePathAliasToBeAdded) throws Exception {

		AddWebSitePathAlias command = new AddWebSitePathAlias(webSitePathAliasToBeAdded);
		WebSitePathAlias webSitePathAlias = ((WebSitePathAliasAdded) Scheduler.execute(command).data()).getAddedWebSitePathAlias();
		
		if (webSitePathAlias != null) 
			return successful(webSitePathAlias);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWebSitePathAlias(@RequestBody WebSitePathAlias webSitePathAliasToBeUpdated,
			@PathVariable String pathAlias) throws Exception {

		webSitePathAliasToBeUpdated.setPathAlias(pathAlias);

		UpdateWebSitePathAlias command = new UpdateWebSitePathAlias(webSitePathAliasToBeUpdated);

		try {
			if(((WebSitePathAliasUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webSitePathAliasId}")
	public ResponseEntity<WebSitePathAlias> findById(@PathVariable String webSitePathAliasId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSitePathAliasId", webSitePathAliasId);
		try {

			List<WebSitePathAlias> foundWebSitePathAlias = findWebSitePathAliassBy(requestParams).getBody();
			if(foundWebSitePathAlias.size()==1){				return successful(foundWebSitePathAlias.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webSitePathAliasId}")
	public ResponseEntity<String> deleteWebSitePathAliasByIdUpdated(@PathVariable String webSitePathAliasId) throws Exception {
		DeleteWebSitePathAlias command = new DeleteWebSitePathAlias(webSitePathAliasId);

		try {
			if (((WebSitePathAliasDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
