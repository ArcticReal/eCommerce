package com.skytala.eCommerce.domain.order.relations.quote.control.role;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.role.AddQuoteRole;
import com.skytala.eCommerce.domain.order.relations.quote.command.role.DeleteQuoteRole;
import com.skytala.eCommerce.domain.order.relations.quote.command.role.UpdateQuoteRole;
import com.skytala.eCommerce.domain.order.relations.quote.event.role.QuoteRoleAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.role.QuoteRoleDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.role.QuoteRoleFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.role.QuoteRoleUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.role.QuoteRoleMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.role.QuoteRole;
import com.skytala.eCommerce.domain.order.relations.quote.query.role.FindQuoteRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteRoles")
public class QuoteRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteRole
	 * @return a List with the QuoteRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<QuoteRole>> findQuoteRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteRolesBy query = new FindQuoteRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteRole> quoteRoles =((QuoteRoleFound) Scheduler.execute(query).data()).getQuoteRoles();

		return ResponseEntity.ok().body(quoteRoles);

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
	public ResponseEntity<QuoteRole> createQuoteRole(HttpServletRequest request) throws Exception {

		QuoteRole quoteRoleToBeAdded = new QuoteRole();
		try {
			quoteRoleToBeAdded = QuoteRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createQuoteRole(quoteRoleToBeAdded);

	}

	/**
	 * creates a new QuoteRole entry in the ofbiz database
	 * 
	 * @param quoteRoleToBeAdded
	 *            the QuoteRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteRole> createQuoteRole(@RequestBody QuoteRole quoteRoleToBeAdded) throws Exception {

		AddQuoteRole command = new AddQuoteRole(quoteRoleToBeAdded);
		QuoteRole quoteRole = ((QuoteRoleAdded) Scheduler.execute(command).data()).getAddedQuoteRole();
		
		if (quoteRole != null) 
			return successful(quoteRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the QuoteRole with the specific Id
	 * 
	 * @param quoteRoleToBeUpdated
	 *            the QuoteRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateQuoteRole(@RequestBody QuoteRole quoteRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		quoteRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateQuoteRole command = new UpdateQuoteRole(quoteRoleToBeUpdated);

		try {
			if(((QuoteRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteRoleId}")
	public ResponseEntity<QuoteRole> findById(@PathVariable String quoteRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteRoleId", quoteRoleId);
		try {

			List<QuoteRole> foundQuoteRole = findQuoteRolesBy(requestParams).getBody();
			if(foundQuoteRole.size()==1){				return successful(foundQuoteRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteRoleId}")
	public ResponseEntity<String> deleteQuoteRoleByIdUpdated(@PathVariable String quoteRoleId) throws Exception {
		DeleteQuoteRole command = new DeleteQuoteRole(quoteRoleId);

		try {
			if (((QuoteRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
