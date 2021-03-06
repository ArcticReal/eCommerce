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
@RequestMapping("/service/accountingRate")
public class AccountingRateServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyRate")
	public ResponseEntity<Map<String, Object>> deletePartyRate(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="rateAmountFromDate", required=false) Timestamp rateAmountFromDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("rateAmountFromDate",rateAmountFromDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyRate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/filterRateAmountList")
	public ResponseEntity<Map<String, Object>> filterRateAmountList(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="rateTypeId", required=false) String rateTypeId, @RequestParam(value="ratesList", required=false) List ratesList, @RequestParam(value="rateAmount", required=false) BigDecimal rateAmount, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("ratesList",ratesList);
		paramMap.put("rateAmount",rateAmount);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("filterRateAmountList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getRatesAmountsFromWorkEffortId")
	public ResponseEntity<Map<String, Object>> getRatesAmountsFromWorkEffortId(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="rateTypeId", required=false) String rateTypeId, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRatesAmountsFromWorkEffortId", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getRatesAmountsFromPartyId")
	public ResponseEntity<Map<String, Object>> getRatesAmountsFromPartyId(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="rateTypeId", required=false) String rateTypeId, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRatesAmountsFromPartyId", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getRatesAmountsFromEmplPositionTypeId")
	public ResponseEntity<Map<String, Object>> getRatesAmountsFromEmplPositionTypeId(HttpSession session, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="rateTypeId", required=false) String rateTypeId, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRatesAmountsFromEmplPositionTypeId", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyRate")
	public ResponseEntity<Map<String, Object>> updatePartyRate(HttpSession session, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="periodTypeId") String periodTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="percentageUsed", required=false) BigDecimal percentageUsed, @RequestParam(value="defaultRate", required=false) String defaultRate, @RequestParam(value="rateAmount", required=false) BigDecimal rateAmount, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("percentageUsed",percentageUsed);
		paramMap.put("defaultRate",defaultRate);
		paramMap.put("rateAmount",rateAmount);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyRate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRateType")
	public ResponseEntity<Map<String, Object>> deleteRateType(HttpSession session, @RequestParam(value="rateTypeId") String rateTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateRateAmount")
	public ResponseEntity<Map<String, Object>> updateRateAmount(HttpSession session, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="rateAmount") BigDecimal rateAmount, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("rateAmount",rateAmount);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRateAmount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateRateType")
	public ResponseEntity<Map<String, Object>> updateRateType(HttpSession session, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRateAmount")
	public ResponseEntity<Map<String, Object>> deleteRateAmount(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRateAmount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getRateAmount")
	public ResponseEntity<Map<String, Object>> getRateAmount(HttpSession session, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRateAmount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createRateType")
	public ResponseEntity<Map<String, Object>> createRateType(HttpSession session, @RequestParam(value="rateTypeId", required=false) String rateTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRateType", paramMap);
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
