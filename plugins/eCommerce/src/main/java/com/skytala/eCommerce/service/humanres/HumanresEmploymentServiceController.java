package com.skytala.eCommerce.service.humanres;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/humanresEmployment")
public class HumanresEmploymentServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateBenefitType")
	public ResponseEntity<Map<String, Object>> updateBenefitType(HttpSession session, @RequestParam(value="benefitTypeId") String benefitTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="employerPaidPercentage", required=false) BigDecimal employerPaidPercentage, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="benefitName", required=false) String benefitName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("benefitTypeId",benefitTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("employerPaidPercentage",employerPaidPercentage);
		paramMap.put("hasTable",hasTable);
		paramMap.put("benefitName",benefitName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBenefitType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmploymentAppSourceType")
	public ResponseEntity<Map<String, Object>> updateEmploymentAppSourceType(HttpSession session, @RequestParam(value="employmentAppSourceTypeId") String employmentAppSourceTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmploymentAppSourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createBenefitType")
	public ResponseEntity<Map<String, Object>> createBenefitType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="employerPaidPercentage", required=false) BigDecimal employerPaidPercentage, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="benefitName", required=false) String benefitName, @RequestParam(value="benefitTypeId", required=false) String benefitTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("employerPaidPercentage",employerPaidPercentage);
		paramMap.put("hasTable",hasTable);
		paramMap.put("benefitName",benefitName);
		paramMap.put("benefitTypeId",benefitTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBenefitType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEmploymentAppSourceType")
	public ResponseEntity<Map<String, Object>> createEmploymentAppSourceType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="employmentAppSourceTypeId", required=false) String employmentAppSourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmploymentAppSourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmploymentAppSourceType")
	public ResponseEntity<Map<String, Object>> deleteEmploymentAppSourceType(HttpSession session, @RequestParam(value="employmentAppSourceTypeId") String employmentAppSourceTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmploymentAppSourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBenefitType")
	public ResponseEntity<Map<String, Object>> deleteBenefitType(HttpSession session, @RequestParam(value="benefitTypeId") String benefitTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("benefitTypeId",benefitTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBenefitType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}



}
