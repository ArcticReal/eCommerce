package com.skytala.eCommerce.service.marketing;

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
@RequestMapping("/service/MarketingOpportunity")
public class MarketingOpportunity{

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalesOpportunity")
	public ResponseEntity<Object> updateSalesOpportunity(HttpSession session, @RequestParam(value="salesOpportunityId") String salesOpportunityId, @RequestParam(value="estimatedProbability", required=false) BigDecimal estimatedProbability, @RequestParam(value="opportunityName", required=false) String opportunityName, @RequestParam(value="marketingCampaignId", required=false) String marketingCampaignId, @RequestParam(value="description", required=false) String description, @RequestParam(value="opportunityStageId", required=false) String opportunityStageId, @RequestParam(value="typeEnumId", required=false) String typeEnumId, @RequestParam(value="leadPartyId", required=false) String leadPartyId, @RequestParam(value="estimatedCloseDate", required=false) Timestamp estimatedCloseDate, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="accountPartyId", required=false) String accountPartyId, @RequestParam(value="nextStep", required=false) String nextStep, @RequestParam(value="nextStepDate", required=false) Timestamp nextStepDate, @RequestParam(value="estimatedAmount", required=false) BigDecimal estimatedAmount, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("estimatedProbability",estimatedProbability);
		paramMap.put("opportunityName",opportunityName);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("description",description);
		paramMap.put("opportunityStageId",opportunityStageId);
		paramMap.put("typeEnumId",typeEnumId);
		paramMap.put("leadPartyId",leadPartyId);
		paramMap.put("estimatedCloseDate",estimatedCloseDate);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("accountPartyId",accountPartyId);
		paramMap.put("nextStep",nextStep);
		paramMap.put("nextStepDate",nextStepDate);
		paramMap.put("estimatedAmount",estimatedAmount);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalesOpportunity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findPartyInSalesOpportunityRole")
	public ResponseEntity<Object> findPartyInSalesOpportunityRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="salesOpportunityId", required=false) String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findPartyInSalesOpportunityRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesOpportunityQuote")
	public ResponseEntity<Object> deleteSalesOpportunityQuote(HttpSession session, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesOpportunityQuote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesForecastDetail")
	public ResponseEntity<Object> createSalesForecastDetail(HttpSession session, @RequestParam(value="salesForecastId") String salesForecastId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="quantityUomId", required=false) String quantityUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("salesForecastId",salesForecastId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("productId",productId);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesForecastDetail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityStage")
	public ResponseEntity<Object> createSalesOpportunityStage(HttpSession session, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="defaultProbability", required=false) BigDecimal defaultProbability, @RequestParam(value="description", required=false) String description, @RequestParam(value="opportunityStageId", required=false) String opportunityStageId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("defaultProbability",defaultProbability);
		paramMap.put("description",description);
		paramMap.put("opportunityStageId",opportunityStageId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityStage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityLeadRole")
	public ResponseEntity<Object> createSalesOpportunityLeadRole(HttpSession session, @RequestParam(value="leadPartyId") String leadPartyId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("leadPartyId",leadPartyId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityLeadRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityAccountRole")
	public ResponseEntity<Object> createSalesOpportunityAccountRole(HttpSession session, @RequestParam(value="accountPartyId") String accountPartyId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accountPartyId",accountPartyId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityAccountRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityTrckCode")
	public ResponseEntity<Object> createSalesOpportunityTrckCode(HttpSession session, @RequestParam(value="trackingCodeId") String trackingCodeId, @RequestParam(value="salesOpportunityId") String salesOpportunityId, @RequestParam(value="receivedDate", required=false) Timestamp receivedDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("receivedDate",receivedDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityTrckCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalesOpportunityTrckCode")
	public ResponseEntity<Object> updateSalesOpportunityTrckCode(HttpSession session, @RequestParam(value="trackingCodeId") String trackingCodeId, @RequestParam(value="salesOpportunityId") String salesOpportunityId, @RequestParam(value="receivedDate", required=false) Timestamp receivedDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("receivedDate",receivedDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalesOpportunityTrckCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityWorkEffort")
	public ResponseEntity<Object> createSalesOpportunityWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesOpportunityWorkEffort")
	public ResponseEntity<Object> deleteSalesOpportunityWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesOpportunityWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesOpportunityTrckCode")
	public ResponseEntity<Object> deleteSalesOpportunityTrckCode(HttpSession session, @RequestParam(value="trackingCodeId") String trackingCodeId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesOpportunityTrckCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesOpportunityRole")
	public ResponseEntity<Object> deleteSalesOpportunityRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesOpportunityRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalesForecastDetail")
	public ResponseEntity<Object> updateSalesForecastDetail(HttpSession session, @RequestParam(value="salesForecastId") String salesForecastId, @RequestParam(value="salesForecastDetailId") String salesForecastDetailId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="quantityUomId", required=false) String quantityUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("salesForecastId",salesForecastId);
		paramMap.put("salesForecastDetailId",salesForecastDetailId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("productId",productId);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalesForecastDetail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesOpportunityStage")
	public ResponseEntity<Object> deleteSalesOpportunityStage(HttpSession session, @RequestParam(value="opportunityStageId") String opportunityStageId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("opportunityStageId",opportunityStageId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesOpportunityStage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesForecastDetail")
	public ResponseEntity<Object> deleteSalesForecastDetail(HttpSession session, @RequestParam(value="salesForecastId") String salesForecastId, @RequestParam(value="salesForecastDetailId") String salesForecastDetailId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("salesForecastId",salesForecastId);
		paramMap.put("salesForecastDetailId",salesForecastDetailId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesForecastDetail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityRole")
	public ResponseEntity<Object> createSalesOpportunityRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesOpportunityCompetitor")
	public ResponseEntity<Object> deleteSalesOpportunityCompetitor(HttpSession session, @RequestParam(value="competitorPartyId") String competitorPartyId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("competitorPartyId",competitorPartyId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesOpportunityCompetitor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesForecast")
	public ResponseEntity<Object> createSalesForecast(HttpSession session, @RequestParam(value="forecastAmount", required=false) BigDecimal forecastAmount, @RequestParam(value="pipelineAmount", required=false) BigDecimal pipelineAmount, @RequestParam(value="modifiedByUserLoginId", required=false) String modifiedByUserLoginId, @RequestParam(value="quotaAmount", required=false) BigDecimal quotaAmount, @RequestParam(value="bestCaseAmount", required=false) BigDecimal bestCaseAmount, @RequestParam(value="createdByUserLoginId", required=false) String createdByUserLoginId, @RequestParam(value="internalPartyId", required=false) String internalPartyId, @RequestParam(value="closedAmount", required=false) BigDecimal closedAmount, @RequestParam(value="percentOfQuotaForecast", required=false) BigDecimal percentOfQuotaForecast, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="parentSalesForecastId", required=false) String parentSalesForecastId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="percentOfQuotaClosed", required=false) BigDecimal percentOfQuotaClosed, @RequestParam(value="customTimePeriodId", required=false) String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("forecastAmount",forecastAmount);
		paramMap.put("pipelineAmount",pipelineAmount);
		paramMap.put("modifiedByUserLoginId",modifiedByUserLoginId);
		paramMap.put("quotaAmount",quotaAmount);
		paramMap.put("bestCaseAmount",bestCaseAmount);
		paramMap.put("createdByUserLoginId",createdByUserLoginId);
		paramMap.put("internalPartyId",internalPartyId);
		paramMap.put("closedAmount",closedAmount);
		paramMap.put("percentOfQuotaForecast",percentOfQuotaForecast);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("parentSalesForecastId",parentSalesForecastId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("percentOfQuotaClosed",percentOfQuotaClosed);
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesForecast", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityCompetitor")
	public ResponseEntity<Object> createSalesOpportunityCompetitor(HttpSession session, @RequestParam(value="competitorPartyId") String competitorPartyId, @RequestParam(value="salesOpportunityId") String salesOpportunityId, @RequestParam(value="strengths", required=false) String strengths, @RequestParam(value="weaknesses", required=false) String weaknesses, @RequestParam(value="positionEnumId", required=false) String positionEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("competitorPartyId",competitorPartyId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("strengths",strengths);
		paramMap.put("weaknesses",weaknesses);
		paramMap.put("positionEnumId",positionEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityCompetitor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalesOpportunityStage")
	public ResponseEntity<Object> updateSalesOpportunityStage(HttpSession session, @RequestParam(value="opportunityStageId") String opportunityStageId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="defaultProbability", required=false) BigDecimal defaultProbability, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("opportunityStageId",opportunityStageId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("defaultProbability",defaultProbability);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalesOpportunityStage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunityQuote")
	public ResponseEntity<Object> createSalesOpportunityQuote(HttpSession session, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="salesOpportunityId") String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunityQuote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalesForecast")
	public ResponseEntity<Object> updateSalesForecast(HttpSession session, @RequestParam(value="salesForecastId") String salesForecastId, @RequestParam(value="forecastAmount", required=false) BigDecimal forecastAmount, @RequestParam(value="pipelineAmount", required=false) BigDecimal pipelineAmount, @RequestParam(value="modifiedByUserLoginId", required=false) String modifiedByUserLoginId, @RequestParam(value="quotaAmount", required=false) BigDecimal quotaAmount, @RequestParam(value="bestCaseAmount", required=false) BigDecimal bestCaseAmount, @RequestParam(value="createdByUserLoginId", required=false) String createdByUserLoginId, @RequestParam(value="changeNote", required=false) String changeNote, @RequestParam(value="internalPartyId", required=false) String internalPartyId, @RequestParam(value="closedAmount", required=false) BigDecimal closedAmount, @RequestParam(value="percentOfQuotaForecast", required=false) BigDecimal percentOfQuotaForecast, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="parentSalesForecastId", required=false) String parentSalesForecastId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="percentOfQuotaClosed", required=false) BigDecimal percentOfQuotaClosed, @RequestParam(value="customTimePeriodId", required=false) String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("salesForecastId",salesForecastId);
		paramMap.put("forecastAmount",forecastAmount);
		paramMap.put("pipelineAmount",pipelineAmount);
		paramMap.put("modifiedByUserLoginId",modifiedByUserLoginId);
		paramMap.put("quotaAmount",quotaAmount);
		paramMap.put("bestCaseAmount",bestCaseAmount);
		paramMap.put("createdByUserLoginId",createdByUserLoginId);
		paramMap.put("changeNote",changeNote);
		paramMap.put("internalPartyId",internalPartyId);
		paramMap.put("closedAmount",closedAmount);
		paramMap.put("percentOfQuotaForecast",percentOfQuotaForecast);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("parentSalesForecastId",parentSalesForecastId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("percentOfQuotaClosed",percentOfQuotaClosed);
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalesForecast", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalesOpportunityCompetitor")
	public ResponseEntity<Object> updateSalesOpportunityCompetitor(HttpSession session, @RequestParam(value="competitorPartyId") String competitorPartyId, @RequestParam(value="salesOpportunityId") String salesOpportunityId, @RequestParam(value="strengths", required=false) String strengths, @RequestParam(value="weaknesses", required=false) String weaknesses, @RequestParam(value="positionEnumId", required=false) String positionEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("competitorPartyId",competitorPartyId);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("strengths",strengths);
		paramMap.put("weaknesses",weaknesses);
		paramMap.put("positionEnumId",positionEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalesOpportunityCompetitor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOpportunity")
	public ResponseEntity<Object> createSalesOpportunity(HttpSession session, @RequestParam(value="estimatedProbability", required=false) BigDecimal estimatedProbability, @RequestParam(value="opportunityName", required=false) String opportunityName, @RequestParam(value="marketingCampaignId", required=false) String marketingCampaignId, @RequestParam(value="description", required=false) String description, @RequestParam(value="opportunityStageId", required=false) String opportunityStageId, @RequestParam(value="typeEnumId", required=false) String typeEnumId, @RequestParam(value="leadPartyId", required=false) String leadPartyId, @RequestParam(value="estimatedCloseDate", required=false) Timestamp estimatedCloseDate, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="accountPartyId", required=false) String accountPartyId, @RequestParam(value="nextStep", required=false) String nextStep, @RequestParam(value="nextStepDate", required=false) Timestamp nextStepDate, @RequestParam(value="estimatedAmount", required=false) BigDecimal estimatedAmount, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="salesOpportunityId", required=false) String salesOpportunityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("estimatedProbability",estimatedProbability);
		paramMap.put("opportunityName",opportunityName);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("description",description);
		paramMap.put("opportunityStageId",opportunityStageId);
		paramMap.put("typeEnumId",typeEnumId);
		paramMap.put("leadPartyId",leadPartyId);
		paramMap.put("estimatedCloseDate",estimatedCloseDate);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("accountPartyId",accountPartyId);
		paramMap.put("nextStep",nextStep);
		paramMap.put("nextStepDate",nextStepDate);
		paramMap.put("estimatedAmount",estimatedAmount);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("salesOpportunityId",salesOpportunityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOpportunity", paramMap);
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
