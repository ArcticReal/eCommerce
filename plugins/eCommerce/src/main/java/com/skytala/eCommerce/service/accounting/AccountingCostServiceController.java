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

@RestController
@RequestMapping("/service/accountingCost")
public class AccountingCostServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/removeWorkEffortCostCalc")
	public ResponseEntity<Object> removeWorkEffortCostCalc(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="costComponentTypeId") String costComponentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWorkEffortCostCalc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductAverageCost")
	public ResponseEntity<Object> deleteProductAverageCost(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="productAverageCostTypeId") String productAverageCostTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("productAverageCostTypeId",productAverageCostTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductAverageCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getProductAverageCost")
	public ResponseEntity<Object> getProductAverageCost(HttpSession session, @RequestParam(value="inventoryItem", required=false) org.apache.ofbiz.entity.GenericValue inventoryItem) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItem",inventoryItem);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductAverageCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCostComponentCalc")
	public ResponseEntity<Object> createCostComponentCalc(HttpSession session, @RequestParam(value="costCustomMethodId", required=false) String costCustomMethodId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="variableCost", required=false) BigDecimal variableCost, @RequestParam(value="costGlAccountTypeId", required=false) String costGlAccountTypeId, @RequestParam(value="fixedCost", required=false) BigDecimal fixedCost, @RequestParam(value="description", required=false) String description, @RequestParam(value="offsettingGlAccountTypeId", required=false) String offsettingGlAccountTypeId, @RequestParam(value="perMilliSecond", required=false) Long perMilliSecond) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costCustomMethodId",costCustomMethodId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("variableCost",variableCost);
		paramMap.put("costGlAccountTypeId",costGlAccountTypeId);
		paramMap.put("fixedCost",fixedCost);
		paramMap.put("description",description);
		paramMap.put("offsettingGlAccountTypeId",offsettingGlAccountTypeId);
		paramMap.put("perMilliSecond",perMilliSecond);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCostComponentCalc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductAverageCostOnReceiveInventory")
	public ResponseEntity<Object> updateProductAverageCostOnReceiveInventory(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="quantityAccepted") BigDecimal quantityAccepted) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductAverageCostOnReceiveInventory", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductAverageCost")
	public ResponseEntity<Object> updateProductAverageCost(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="productAverageCostTypeId") String productAverageCostTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="averageCost", required=false) BigDecimal averageCost, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("productAverageCostTypeId",productAverageCostTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("averageCost",averageCost);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductAverageCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductAverageCost")
	public ResponseEntity<Object> createProductAverageCost(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="productAverageCostTypeId") String productAverageCostTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="averageCost") BigDecimal averageCost, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("productAverageCostTypeId",productAverageCostTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("averageCost",averageCost);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductAverageCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortCostCalc")
	public ResponseEntity<Object> createWorkEffortCostCalc(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="costComponentCalcId", required=false) String costComponentCalcId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortCostCalc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCostComponentCalc")
	public ResponseEntity<Object> updateCostComponentCalc(HttpSession session, @RequestParam(value="costComponentCalcId") String costComponentCalcId, @RequestParam(value="costCustomMethodId", required=false) String costCustomMethodId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="variableCost", required=false) BigDecimal variableCost, @RequestParam(value="costGlAccountTypeId", required=false) String costGlAccountTypeId, @RequestParam(value="fixedCost", required=false) BigDecimal fixedCost, @RequestParam(value="description", required=false) String description, @RequestParam(value="offsettingGlAccountTypeId", required=false) String offsettingGlAccountTypeId, @RequestParam(value="perMilliSecond", required=false) Long perMilliSecond) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("costCustomMethodId",costCustomMethodId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("variableCost",variableCost);
		paramMap.put("costGlAccountTypeId",costGlAccountTypeId);
		paramMap.put("fixedCost",fixedCost);
		paramMap.put("description",description);
		paramMap.put("offsettingGlAccountTypeId",offsettingGlAccountTypeId);
		paramMap.put("perMilliSecond",perMilliSecond);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCostComponentCalc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeCostComponentCalc")
	public ResponseEntity<Object> removeCostComponentCalc(HttpSession session, @RequestParam(value="costComponentCalcId") String costComponentCalcId, @RequestParam(value="costCustomMethodId", required=false) String costCustomMethodId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="variableCost", required=false) BigDecimal variableCost, @RequestParam(value="costGlAccountTypeId", required=false) String costGlAccountTypeId, @RequestParam(value="fixedCost", required=false) BigDecimal fixedCost, @RequestParam(value="description", required=false) String description, @RequestParam(value="offsettingGlAccountTypeId", required=false) String offsettingGlAccountTypeId, @RequestParam(value="perMilliSecond", required=false) Long perMilliSecond) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("costCustomMethodId",costCustomMethodId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("variableCost",variableCost);
		paramMap.put("costGlAccountTypeId",costGlAccountTypeId);
		paramMap.put("fixedCost",fixedCost);
		paramMap.put("description",description);
		paramMap.put("offsettingGlAccountTypeId",offsettingGlAccountTypeId);
		paramMap.put("perMilliSecond",perMilliSecond);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCostComponentCalc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(value = (" * "))
	public ResponseEntity<Object> returnErrorPage(HttpSession session) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested service does not exist. JSESSIONID=" + session.getId());
	}

}
