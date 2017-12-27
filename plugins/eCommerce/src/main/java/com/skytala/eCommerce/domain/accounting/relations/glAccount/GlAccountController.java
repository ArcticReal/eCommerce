package com.skytala.eCommerce.domain.accounting.relations.glAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.AddGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.DeleteGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.UpdateGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.GlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.GlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.GlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.GlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.GlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.GlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.FindGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccounts")
public class GlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccount
	 * @return a List with the GlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccount>> findGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountsBy query = new FindGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccount> glAccounts =((GlAccountFound) Scheduler.execute(query).data()).getGlAccounts();

		return ResponseEntity.ok().body(glAccounts);

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
	public ResponseEntity<GlAccount> createGlAccount(HttpServletRequest request) throws Exception {

		GlAccount glAccountToBeAdded = new GlAccount();
		try {
			glAccountToBeAdded = GlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createGlAccount(glAccountToBeAdded);

	}

	/**
	 * creates a new GlAccount entry in the ofbiz database
	 * 
	 * @param glAccountToBeAdded
	 *            the GlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccount> createGlAccount(@RequestBody GlAccount glAccountToBeAdded) throws Exception {

		AddGlAccount command = new AddGlAccount(glAccountToBeAdded);
		GlAccount glAccount = ((GlAccountAdded) Scheduler.execute(command).data()).getAddedGlAccount();
		
		if (glAccount != null) 
			return successful(glAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccount with the specific Id
	 * 
	 * @param glAccountToBeUpdated
	 *            the GlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccount(@RequestBody GlAccount glAccountToBeUpdated,
			@PathVariable String glAccountId) throws Exception {

		glAccountToBeUpdated.setGlAccountId(glAccountId);

		UpdateGlAccount command = new UpdateGlAccount(glAccountToBeUpdated);

		try {
			if(((GlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountId}")
	public ResponseEntity<GlAccount> findById(@PathVariable String glAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountId", glAccountId);
		try {

			List<GlAccount> foundGlAccount = findGlAccountsBy(requestParams).getBody();
			if(foundGlAccount.size()==1){				return successful(foundGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountId}")
	public ResponseEntity<String> deleteGlAccountByIdUpdated(@PathVariable String glAccountId) throws Exception {
		DeleteGlAccount command = new DeleteGlAccount(glAccountId);

		try {
			if (((GlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
