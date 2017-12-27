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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PayGrade>> findPayGradesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayGradesBy query = new FindPayGradesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayGrade> payGrades =((PayGradeFound) Scheduler.execute(query).data()).getPayGrades();

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
	public ResponseEntity<PayGrade> createPayGrade(HttpServletRequest request) throws Exception {

		PayGrade payGradeToBeAdded = new PayGrade();
		try {
			payGradeToBeAdded = PayGradeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PayGrade> createPayGrade(@RequestBody PayGrade payGradeToBeAdded) throws Exception {

		AddPayGrade command = new AddPayGrade(payGradeToBeAdded);
		PayGrade payGrade = ((PayGradeAdded) Scheduler.execute(command).data()).getAddedPayGrade();
		
		if (payGrade != null) 
			return successful(payGrade);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePayGrade(@RequestBody PayGrade payGradeToBeUpdated,
			@PathVariable String payGradeId) throws Exception {

		payGradeToBeUpdated.setPayGradeId(payGradeId);

		UpdatePayGrade command = new UpdatePayGrade(payGradeToBeUpdated);

		try {
			if(((PayGradeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{payGradeId}")
	public ResponseEntity<PayGrade> findById(@PathVariable String payGradeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payGradeId", payGradeId);
		try {

			List<PayGrade> foundPayGrade = findPayGradesBy(requestParams).getBody();
			if(foundPayGrade.size()==1){				return successful(foundPayGrade.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{payGradeId}")
	public ResponseEntity<String> deletePayGradeByIdUpdated(@PathVariable String payGradeId) throws Exception {
		DeletePayGrade command = new DeletePayGrade(payGradeId);

		try {
			if (((PayGradeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
