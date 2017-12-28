package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision;

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
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.command.AddX509IssuerProvision;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.command.DeleteX509IssuerProvision;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.command.UpdateX509IssuerProvision;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event.X509IssuerProvisionAdded;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event.X509IssuerProvisionDeleted;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event.X509IssuerProvisionFound;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event.X509IssuerProvisionUpdated;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.mapper.X509IssuerProvisionMapper;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.query.FindX509IssuerProvisionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/login/x509IssuerProvision/x509IssuerProvisions")
public class X509IssuerProvisionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public X509IssuerProvisionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a X509IssuerProvision
	 * @return a List with the X509IssuerProvisions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<X509IssuerProvision>> findX509IssuerProvisionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindX509IssuerProvisionsBy query = new FindX509IssuerProvisionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<X509IssuerProvision> x509IssuerProvisions =((X509IssuerProvisionFound) Scheduler.execute(query).data()).getX509IssuerProvisions();

		return ResponseEntity.ok().body(x509IssuerProvisions);

	}

	/**
	 * creates a new X509IssuerProvision entry in the ofbiz database
	 * 
	 * @param x509IssuerProvisionToBeAdded
	 *            the X509IssuerProvision thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<X509IssuerProvision> createX509IssuerProvision(@RequestBody X509IssuerProvision x509IssuerProvisionToBeAdded) throws Exception {

		AddX509IssuerProvision command = new AddX509IssuerProvision(x509IssuerProvisionToBeAdded);
		X509IssuerProvision x509IssuerProvision = ((X509IssuerProvisionAdded) Scheduler.execute(command).data()).getAddedX509IssuerProvision();
		
		if (x509IssuerProvision != null) 
			return successful(x509IssuerProvision);
		else 
			return conflict(null);
	}

	/**
	 * Updates the X509IssuerProvision with the specific Id
	 * 
	 * @param x509IssuerProvisionToBeUpdated
	 *            the X509IssuerProvision thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{certProvisionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateX509IssuerProvision(@RequestBody X509IssuerProvision x509IssuerProvisionToBeUpdated,
			@PathVariable String certProvisionId) throws Exception {

		x509IssuerProvisionToBeUpdated.setCertProvisionId(certProvisionId);

		UpdateX509IssuerProvision command = new UpdateX509IssuerProvision(x509IssuerProvisionToBeUpdated);

		try {
			if(((X509IssuerProvisionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{x509IssuerProvisionId}")
	public ResponseEntity<X509IssuerProvision> findById(@PathVariable String x509IssuerProvisionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("x509IssuerProvisionId", x509IssuerProvisionId);
		try {

			List<X509IssuerProvision> foundX509IssuerProvision = findX509IssuerProvisionsBy(requestParams).getBody();
			if(foundX509IssuerProvision.size()==1){				return successful(foundX509IssuerProvision.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{x509IssuerProvisionId}")
	public ResponseEntity<String> deleteX509IssuerProvisionByIdUpdated(@PathVariable String x509IssuerProvisionId) throws Exception {
		DeleteX509IssuerProvision command = new DeleteX509IssuerProvision(x509IssuerProvisionId);

		try {
			if (((X509IssuerProvisionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
