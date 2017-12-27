package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.auth;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.auth.AddFinAccountAuth;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.auth.DeleteFinAccountAuth;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.auth.UpdateFinAccountAuth;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth.FinAccountAuthAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth.FinAccountAuthDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth.FinAccountAuthFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth.FinAccountAuthUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.auth.FinAccountAuthMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.auth.FinAccountAuth;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.auth.FindFinAccountAuthsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountAuths")
public class FinAccountAuthController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountAuthController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountAuth
	 * @return a List with the FinAccountAuths
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountAuth>> findFinAccountAuthsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountAuthsBy query = new FindFinAccountAuthsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountAuth> finAccountAuths =((FinAccountAuthFound) Scheduler.execute(query).data()).getFinAccountAuths();

		return ResponseEntity.ok().body(finAccountAuths);

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
	public ResponseEntity<FinAccountAuth> createFinAccountAuth(HttpServletRequest request) throws Exception {

		FinAccountAuth finAccountAuthToBeAdded = new FinAccountAuth();
		try {
			finAccountAuthToBeAdded = FinAccountAuthMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFinAccountAuth(finAccountAuthToBeAdded);

	}

	/**
	 * creates a new FinAccountAuth entry in the ofbiz database
	 * 
	 * @param finAccountAuthToBeAdded
	 *            the FinAccountAuth thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountAuth> createFinAccountAuth(@RequestBody FinAccountAuth finAccountAuthToBeAdded) throws Exception {

		AddFinAccountAuth command = new AddFinAccountAuth(finAccountAuthToBeAdded);
		FinAccountAuth finAccountAuth = ((FinAccountAuthAdded) Scheduler.execute(command).data()).getAddedFinAccountAuth();
		
		if (finAccountAuth != null) 
			return successful(finAccountAuth);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountAuth with the specific Id
	 * 
	 * @param finAccountAuthToBeUpdated
	 *            the FinAccountAuth thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{finAccountAuthId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountAuth(@RequestBody FinAccountAuth finAccountAuthToBeUpdated,
			@PathVariable String finAccountAuthId) throws Exception {

		finAccountAuthToBeUpdated.setFinAccountAuthId(finAccountAuthId);

		UpdateFinAccountAuth command = new UpdateFinAccountAuth(finAccountAuthToBeUpdated);

		try {
			if(((FinAccountAuthUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountAuthId}")
	public ResponseEntity<FinAccountAuth> findById(@PathVariable String finAccountAuthId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountAuthId", finAccountAuthId);
		try {

			List<FinAccountAuth> foundFinAccountAuth = findFinAccountAuthsBy(requestParams).getBody();
			if(foundFinAccountAuth.size()==1){				return successful(foundFinAccountAuth.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountAuthId}")
	public ResponseEntity<String> deleteFinAccountAuthByIdUpdated(@PathVariable String finAccountAuthId) throws Exception {
		DeleteFinAccountAuth command = new DeleteFinAccountAuth(finAccountAuthId);

		try {
			if (((FinAccountAuthDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
