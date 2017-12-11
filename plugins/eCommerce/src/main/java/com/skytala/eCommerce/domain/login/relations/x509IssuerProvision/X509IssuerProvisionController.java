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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findX509IssuerProvisionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindX509IssuerProvisionsBy query = new FindX509IssuerProvisionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<X509IssuerProvision> x509IssuerProvisions =((X509IssuerProvisionFound) Scheduler.execute(query).data()).getX509IssuerProvisions();

		if (x509IssuerProvisions.size() == 1) {
			return ResponseEntity.ok().body(x509IssuerProvisions.get(0));
		}

		return ResponseEntity.ok().body(x509IssuerProvisions);

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
	public ResponseEntity<Object> createX509IssuerProvision(HttpServletRequest request) throws Exception {

		X509IssuerProvision x509IssuerProvisionToBeAdded = new X509IssuerProvision();
		try {
			x509IssuerProvisionToBeAdded = X509IssuerProvisionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createX509IssuerProvision(x509IssuerProvisionToBeAdded);

	}

	/**
	 * creates a new X509IssuerProvision entry in the ofbiz database
	 * 
	 * @param x509IssuerProvisionToBeAdded
	 *            the X509IssuerProvision thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createX509IssuerProvision(@RequestBody X509IssuerProvision x509IssuerProvisionToBeAdded) throws Exception {

		AddX509IssuerProvision command = new AddX509IssuerProvision(x509IssuerProvisionToBeAdded);
		X509IssuerProvision x509IssuerProvision = ((X509IssuerProvisionAdded) Scheduler.execute(command).data()).getAddedX509IssuerProvision();
		
		if (x509IssuerProvision != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(x509IssuerProvision);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("X509IssuerProvision could not be created.");
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
	public boolean updateX509IssuerProvision(HttpServletRequest request) throws Exception {

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

		X509IssuerProvision x509IssuerProvisionToBeUpdated = new X509IssuerProvision();

		try {
			x509IssuerProvisionToBeUpdated = X509IssuerProvisionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateX509IssuerProvision(x509IssuerProvisionToBeUpdated, x509IssuerProvisionToBeUpdated.getCertProvisionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateX509IssuerProvision(@RequestBody X509IssuerProvision x509IssuerProvisionToBeUpdated,
			@PathVariable String certProvisionId) throws Exception {

		x509IssuerProvisionToBeUpdated.setCertProvisionId(certProvisionId);

		UpdateX509IssuerProvision command = new UpdateX509IssuerProvision(x509IssuerProvisionToBeUpdated);

		try {
			if(((X509IssuerProvisionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{x509IssuerProvisionId}")
	public ResponseEntity<Object> findById(@PathVariable String x509IssuerProvisionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("x509IssuerProvisionId", x509IssuerProvisionId);
		try {

			Object foundX509IssuerProvision = findX509IssuerProvisionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundX509IssuerProvision);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{x509IssuerProvisionId}")
	public ResponseEntity<Object> deleteX509IssuerProvisionByIdUpdated(@PathVariable String x509IssuerProvisionId) throws Exception {
		DeleteX509IssuerProvision command = new DeleteX509IssuerProvision(x509IssuerProvisionId);

		try {
			if (((X509IssuerProvisionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("X509IssuerProvision could not be deleted");

	}

}
