package com.skytala.eCommerce.service.accounting;

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
@RequestMapping("/service/accountingBilling")
public class AccountingBillingServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/removeBillingAccountTerm")
	public ResponseEntity<Map<String, Object>> removeBillingAccountTerm(HttpSession session, @RequestParam(value="billingAccountTermId") String billingAccountTermId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountTermId",billingAccountTermId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBillingAccountTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBillingAccount")
	public ResponseEntity<Map<String, Object>> createBillingAccount(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="accountLimit", required=false) BigDecimal accountLimit, @RequestParam(value="externalAccountId", required=false) String externalAccountId, @RequestParam(value="accountCurrencyUomId", required=false) String accountCurrencyUomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("accountLimit",accountLimit);
		paramMap.put("externalAccountId",externalAccountId);
		paramMap.put("accountCurrencyUomId",accountCurrencyUomId);
		paramMap.put("description",description);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBillingAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBillingAccount")
	public ResponseEntity<Map<String, Object>> updateBillingAccount(HttpSession session, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="accountLimit", required=false) BigDecimal accountLimit, @RequestParam(value="externalAccountId", required=false) String externalAccountId, @RequestParam(value="accountCurrencyUomId", required=false) String accountCurrencyUomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("accountLimit",accountLimit);
		paramMap.put("externalAccountId",externalAccountId);
		paramMap.put("accountCurrencyUomId",accountCurrencyUomId);
		paramMap.put("description",description);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBillingAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBillingAccountRole")
	public ResponseEntity<Map<String, Object>> updateBillingAccountRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBillingAccountRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBillingAccountAndRole")
	public ResponseEntity<Map<String, Object>> createBillingAccountAndRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="accountLimit", required=false) BigDecimal accountLimit, @RequestParam(value="externalAccountId", required=false) String externalAccountId, @RequestParam(value="accountCurrencyUomId", required=false) String accountCurrencyUomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("accountLimit",accountLimit);
		paramMap.put("externalAccountId",externalAccountId);
		paramMap.put("accountCurrencyUomId",accountCurrencyUomId);
		paramMap.put("description",description);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBillingAccountAndRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBillingAccountTerm")
	public ResponseEntity<Map<String, Object>> updateBillingAccountTerm(HttpSession session, @RequestParam(value="billingAccountTermId") String billingAccountTermId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) BigDecimal termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountTermId",billingAccountTermId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("termDays",termDays);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBillingAccountTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcBillingAccountBalance")
	public ResponseEntity<Map<String, Object>> calcBillingAccountBalance(HttpSession session, @RequestParam(value="billingAccountId") String billingAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calcBillingAccountBalance", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBillingAccountTerm")
	public ResponseEntity<Map<String, Object>> createBillingAccountTerm(HttpSession session, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) BigDecimal termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("termDays",termDays);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBillingAccountTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBillingAccountRole")
	public ResponseEntity<Map<String, Object>> createBillingAccountRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBillingAccountRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeBillingAccountRole")
	public ResponseEntity<Map<String, Object>> removeBillingAccountRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBillingAccountRole", paramMap);
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
