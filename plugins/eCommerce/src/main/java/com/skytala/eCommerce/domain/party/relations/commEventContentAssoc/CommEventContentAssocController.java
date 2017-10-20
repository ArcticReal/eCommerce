package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc;

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
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.command.AddCommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.command.DeleteCommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.command.UpdateCommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocAdded;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocDeleted;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocFound;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocUpdated;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.mapper.CommEventContentAssocMapper;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.query.FindCommEventContentAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/commEventContentAssocs")
public class CommEventContentAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommEventContentAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommEventContentAssoc
	 * @return a List with the CommEventContentAssocs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommEventContentAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommEventContentAssocsBy query = new FindCommEventContentAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommEventContentAssoc> commEventContentAssocs =((CommEventContentAssocFound) Scheduler.execute(query).data()).getCommEventContentAssocs();

		if (commEventContentAssocs.size() == 1) {
			return ResponseEntity.ok().body(commEventContentAssocs.get(0));
		}

		return ResponseEntity.ok().body(commEventContentAssocs);

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
	public ResponseEntity<Object> createCommEventContentAssoc(HttpServletRequest request) throws Exception {

		CommEventContentAssoc commEventContentAssocToBeAdded = new CommEventContentAssoc();
		try {
			commEventContentAssocToBeAdded = CommEventContentAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommEventContentAssoc(commEventContentAssocToBeAdded);

	}

	/**
	 * creates a new CommEventContentAssoc entry in the ofbiz database
	 * 
	 * @param commEventContentAssocToBeAdded
	 *            the CommEventContentAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommEventContentAssoc(@RequestBody CommEventContentAssoc commEventContentAssocToBeAdded) throws Exception {

		AddCommEventContentAssoc command = new AddCommEventContentAssoc(commEventContentAssocToBeAdded);
		CommEventContentAssoc commEventContentAssoc = ((CommEventContentAssocAdded) Scheduler.execute(command).data()).getAddedCommEventContentAssoc();
		
		if (commEventContentAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(commEventContentAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommEventContentAssoc could not be created.");
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
	public boolean updateCommEventContentAssoc(HttpServletRequest request) throws Exception {

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

		CommEventContentAssoc commEventContentAssocToBeUpdated = new CommEventContentAssoc();

		try {
			commEventContentAssocToBeUpdated = CommEventContentAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommEventContentAssoc(commEventContentAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommEventContentAssoc with the specific Id
	 * 
	 * @param commEventContentAssocToBeUpdated
	 *            the CommEventContentAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommEventContentAssoc(@RequestBody CommEventContentAssoc commEventContentAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		commEventContentAssocToBeUpdated.setnull(null);

		UpdateCommEventContentAssoc command = new UpdateCommEventContentAssoc(commEventContentAssocToBeUpdated);

		try {
			if(((CommEventContentAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{commEventContentAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String commEventContentAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("commEventContentAssocId", commEventContentAssocId);
		try {

			Object foundCommEventContentAssoc = findCommEventContentAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommEventContentAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{commEventContentAssocId}")
	public ResponseEntity<Object> deleteCommEventContentAssocByIdUpdated(@PathVariable String commEventContentAssocId) throws Exception {
		DeleteCommEventContentAssoc command = new DeleteCommEventContentAssoc(commEventContentAssocId);

		try {
			if (((CommEventContentAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommEventContentAssoc could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/commEventContentAssoc/\" plus one of the following: "
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
