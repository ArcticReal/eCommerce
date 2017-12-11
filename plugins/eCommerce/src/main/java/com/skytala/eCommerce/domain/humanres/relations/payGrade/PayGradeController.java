package com.skytala.eCommerce.domain.humanres.relations.payGrade;

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
import com.skytala.eCommerce.domain.humanres.relations.payGrade.command.AddPayGrade;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.command.DeletePayGrade;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.command.UpdatePayGrade;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.event.PayGradeAdded;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.event.PayGradeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.event.PayGradeFound;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.event.PayGradeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.mapper.PayGradeMapper;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.model.PayGrade;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.query.FindPayGradesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/payGrades")
public class PayGradeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PayGradeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PayGrade
	 * @return a List with the PayGrades
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPayGradesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayGradesBy query = new FindPayGradesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayGrade> payGrades =((PayGradeFound) Scheduler.execute(query).data()).getPayGrades();

		if (payGrades.size() == 1) {
			return ResponseEntity.ok().body(payGrades.get(0));
		}

		return ResponseEntity.ok().body(payGrades);

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
	public ResponseEntity<Object> createPayGrade(HttpServletRequest request) throws Exception {

		PayGrade payGradeToBeAdded = new PayGrade();
		try {
			payGradeToBeAdded = PayGradeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPayGrade(payGradeToBeAdded);

	}

	/**
	 * creates a new PayGrade entry in the ofbiz database
	 * 
	 * @param payGradeToBeAdded
	 *            the PayGrade thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPayGrade(@RequestBody PayGrade payGradeToBeAdded) throws Exception {

		AddPayGrade command = new AddPayGrade(payGradeToBeAdded);
		PayGrade payGrade = ((PayGradeAdded) Scheduler.execute(command).data()).getAddedPayGrade();
		
		if (payGrade != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(payGrade);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PayGrade could not be created.");
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
	public boolean updatePayGrade(HttpServletRequest request) throws Exception {

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

		PayGrade payGradeToBeUpdated = new PayGrade();

		try {
			payGradeToBeUpdated = PayGradeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePayGrade(payGradeToBeUpdated, payGradeToBeUpdated.getPayGradeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PayGrade with the specific Id
	 * 
	 * @param payGradeToBeUpdated
	 *            the PayGrade thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{payGradeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePayGrade(@RequestBody PayGrade payGradeToBeUpdated,
			@PathVariable String payGradeId) throws Exception {

		payGradeToBeUpdated.setPayGradeId(payGradeId);

		UpdatePayGrade command = new UpdatePayGrade(payGradeToBeUpdated);

		try {
			if(((PayGradeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{payGradeId}")
	public ResponseEntity<Object> findById(@PathVariable String payGradeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payGradeId", payGradeId);
		try {

			Object foundPayGrade = findPayGradesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPayGrade);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{payGradeId}")
	public ResponseEntity<Object> deletePayGradeByIdUpdated(@PathVariable String payGradeId) throws Exception {
		DeletePayGrade command = new DeletePayGrade(payGradeId);

		try {
			if (((PayGradeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PayGrade could not be deleted");

	}

}
