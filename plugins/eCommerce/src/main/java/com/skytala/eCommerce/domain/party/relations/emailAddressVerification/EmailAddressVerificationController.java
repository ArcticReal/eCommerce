package com.skytala.eCommerce.domain.party.relations.emailAddressVerification;

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
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.command.AddEmailAddressVerification;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.command.DeleteEmailAddressVerification;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.command.UpdateEmailAddressVerification;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event.EmailAddressVerificationAdded;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event.EmailAddressVerificationDeleted;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event.EmailAddressVerificationFound;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event.EmailAddressVerificationUpdated;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.mapper.EmailAddressVerificationMapper;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.query.FindEmailAddressVerificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/emailAddressVerifications")
public class EmailAddressVerificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmailAddressVerificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmailAddressVerification
	 * @return a List with the EmailAddressVerifications
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmailAddressVerificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmailAddressVerificationsBy query = new FindEmailAddressVerificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmailAddressVerification> emailAddressVerifications =((EmailAddressVerificationFound) Scheduler.execute(query).data()).getEmailAddressVerifications();

		if (emailAddressVerifications.size() == 1) {
			return ResponseEntity.ok().body(emailAddressVerifications.get(0));
		}

		return ResponseEntity.ok().body(emailAddressVerifications);

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
	public ResponseEntity<Object> createEmailAddressVerification(HttpServletRequest request) throws Exception {

		EmailAddressVerification emailAddressVerificationToBeAdded = new EmailAddressVerification();
		try {
			emailAddressVerificationToBeAdded = EmailAddressVerificationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmailAddressVerification(emailAddressVerificationToBeAdded);

	}

	/**
	 * creates a new EmailAddressVerification entry in the ofbiz database
	 * 
	 * @param emailAddressVerificationToBeAdded
	 *            the EmailAddressVerification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmailAddressVerification(@RequestBody EmailAddressVerification emailAddressVerificationToBeAdded) throws Exception {

		AddEmailAddressVerification command = new AddEmailAddressVerification(emailAddressVerificationToBeAdded);
		EmailAddressVerification emailAddressVerification = ((EmailAddressVerificationAdded) Scheduler.execute(command).data()).getAddedEmailAddressVerification();
		
		if (emailAddressVerification != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emailAddressVerification);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmailAddressVerification could not be created.");
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
	public boolean updateEmailAddressVerification(HttpServletRequest request) throws Exception {

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

		EmailAddressVerification emailAddressVerificationToBeUpdated = new EmailAddressVerification();

		try {
			emailAddressVerificationToBeUpdated = EmailAddressVerificationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmailAddressVerification(emailAddressVerificationToBeUpdated, emailAddressVerificationToBeUpdated.getEmailAddress()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmailAddressVerification with the specific Id
	 * 
	 * @param emailAddressVerificationToBeUpdated
	 *            the EmailAddressVerification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emailAddress}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmailAddressVerification(@RequestBody EmailAddressVerification emailAddressVerificationToBeUpdated,
			@PathVariable String emailAddress) throws Exception {

		emailAddressVerificationToBeUpdated.setEmailAddress(emailAddress);

		UpdateEmailAddressVerification command = new UpdateEmailAddressVerification(emailAddressVerificationToBeUpdated);

		try {
			if(((EmailAddressVerificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{emailAddressVerificationId}")
	public ResponseEntity<Object> findById(@PathVariable String emailAddressVerificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emailAddressVerificationId", emailAddressVerificationId);
		try {

			Object foundEmailAddressVerification = findEmailAddressVerificationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmailAddressVerification);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{emailAddressVerificationId}")
	public ResponseEntity<Object> deleteEmailAddressVerificationByIdUpdated(@PathVariable String emailAddressVerificationId) throws Exception {
		DeleteEmailAddressVerification command = new DeleteEmailAddressVerification(emailAddressVerificationId);

		try {
			if (((EmailAddressVerificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmailAddressVerification could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/emailAddressVerification/\" plus one of the following: "
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
