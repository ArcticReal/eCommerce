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

import org.apache.ofbiz.base.util.UtilMisc;
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
@RequestMapping("/party/emailAddressVerifications")
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
	public ResponseEntity<List<EmailAddressVerification>> findEmailAddressVerificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmailAddressVerificationsBy query = new FindEmailAddressVerificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmailAddressVerification> emailAddressVerifications =((EmailAddressVerificationFound) Scheduler.execute(query).data()).getEmailAddressVerifications();

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
	public ResponseEntity<EmailAddressVerification> createEmailAddressVerification(HttpServletRequest request) throws Exception {

		EmailAddressVerification emailAddressVerificationToBeAdded = new EmailAddressVerification();
		try {
			emailAddressVerificationToBeAdded = EmailAddressVerificationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
	public ResponseEntity<EmailAddressVerification> createEmailAddressVerification(@RequestBody EmailAddressVerification emailAddressVerificationToBeAdded) throws Exception {

		AddEmailAddressVerification command = new AddEmailAddressVerification(emailAddressVerificationToBeAdded);
		EmailAddressVerification emailAddressVerification = ((EmailAddressVerificationAdded) Scheduler.execute(command).data()).getAddedEmailAddressVerification();
		
		if (emailAddressVerification != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emailAddressVerification);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{hash}")
	public ResponseEntity<Object> deleteEmailAddressVerificationByHash(@PathVariable String hash) throws Exception {
		DeleteEmailAddressVerification command = new DeleteEmailAddressVerification(hash);

		try {
			if (((EmailAddressVerificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmailAddressVerification could not be deleted");

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{verifyHash}")
	public ResponseEntity<EmailAddressVerification> findByHash(@PathVariable String verifyHash) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("verifyHash", verifyHash);
		try {

			List<EmailAddressVerification> foundEmailAddressVerification = findEmailAddressVerificationsBy(requestParams).getBody();
			if(foundEmailAddressVerification.size()==1)
				return ResponseEntity.status(HttpStatus.OK).body(foundEmailAddressVerification.get(0));
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

}
