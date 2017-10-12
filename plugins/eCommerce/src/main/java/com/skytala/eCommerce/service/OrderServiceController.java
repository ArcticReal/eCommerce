package com.skytala.eCommerce.service;

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
@RequestMapping("/service/OrderController")
public class OrderServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteQuoteWorkEffort")
	public ResponseEntity<Object> deleteQuoteWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quoteId") String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteQuoteWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteFromCustRequest")
	public ResponseEntity<Object> createQuoteFromCustRequest(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteFromCustRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyQuoteItem")
	public ResponseEntity<Object> copyQuoteItem(HttpSession session, @RequestParam(value="quoteItemSeqId") String quoteItemSeqId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="quoteIdTo", required=false) String quoteIdTo, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="quoteItemSeqIdTo", required=false) String quoteItemSeqIdTo, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="quoteUnitPrice", required=false) BigDecimal quoteUnitPrice, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="isPromo", required=false) Boolean isPromo, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="copyQuoteAdjustments", required=false) String copyQuoteAdjustments, @RequestParam(value="skillTypeId", required=false) String skillTypeId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="leadTimeDays", required=false) Long leadTimeDays, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("comments",comments);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("quoteIdTo",quoteIdTo);
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("quoteItemSeqIdTo",quoteItemSeqIdTo);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("quoteUnitPrice",quoteUnitPrice);
		paramMap.put("uomId",uomId);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("isPromo",isPromo);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("copyQuoteAdjustments",copyQuoteAdjustments);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("leadTimeDays",leadTimeDays);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyQuoteItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteAndQuoteItemForRequest")
	public ResponseEntity<Object> createQuoteAndQuoteItemForRequest(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="quoteUnitPrice", required=false) BigDecimal quoteUnitPrice, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="isPromo", required=false) Boolean isPromo, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="skillTypeId", required=false) String skillTypeId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="leadTimeDays", required=false) Long leadTimeDays, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("comments",comments);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("quoteUnitPrice",quoteUnitPrice);
		paramMap.put("uomId",uomId);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("isPromo",isPromo);
		paramMap.put("quoteId",quoteId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("leadTimeDays",leadTimeDays);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteAndQuoteItemForRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteAttribute")
	public ResponseEntity<Object> updateQuoteAttribute(HttpSession session, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="attrName", required=false) String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("quoteId",quoteId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteTermAttribute")
	public ResponseEntity<Object> createQuoteTermAttribute(HttpSession session, @RequestParam(value="quoteItemSeqId") String quoteItemSeqId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteTermAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteTypeAttr")
	public ResponseEntity<Object> updateQuoteTypeAttr(HttpSession session, @RequestParam(value="quoteTypeId") String quoteTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeQuoteCoefficient")
	public ResponseEntity<Object> removeQuoteCoefficient(HttpSession session, @RequestParam(value="coeffValue", required=false) BigDecimal coeffValue, @RequestParam(value="coeffName", required=false) String coeffName, @RequestParam(value="quoteId", required=false) String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("coeffValue",coeffValue);
		paramMap.put("coeffName",coeffName);
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeQuoteCoefficient", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteAdjustment")
	public ResponseEntity<Object> updateQuoteAdjustment(HttpSession session, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="includeInShipping", required=false) Boolean includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="quoteAdjustmentId", required=false) String quoteAdjustmentId, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="includeInTax", required=false) Boolean includeInTax, @RequestParam(value="quoteAdjustmentTypeId", required=false) String quoteAdjustmentTypeId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("quoteAdjustmentId",quoteAdjustmentId);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("quoteAdjustmentTypeId",quoteAdjustmentTypeId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getNextQuoteId")
	public ResponseEntity<Object> getNextQuoteId(HttpSession session, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="issueDate", required=false) Timestamp issueDate, @RequestParam(value="validThruDate", required=false) Timestamp validThruDate, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="quoteName", required=false) String quoteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("statusId",statusId);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("description",description);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("issueDate",issueDate);
		paramMap.put("validThruDate",validThruDate);
		paramMap.put("quoteId",quoteId);
		paramMap.put("quoteName",quoteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getNextQuoteId", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteQuoteType")
	public ResponseEntity<Object> deleteQuoteType(HttpSession session, @RequestParam(value="quoteTypeId") String quoteTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteQuoteType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteItem")
	public ResponseEntity<Object> createQuoteItem(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="quoteUnitPrice", required=false) BigDecimal quoteUnitPrice, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="isPromo", required=false) Boolean isPromo, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="skillTypeId", required=false) String skillTypeId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="leadTimeDays", required=false) Long leadTimeDays, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("comments",comments);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("quoteUnitPrice",quoteUnitPrice);
		paramMap.put("uomId",uomId);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("isPromo",isPromo);
		paramMap.put("quoteId",quoteId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("leadTimeDays",leadTimeDays);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/autoCreateQuoteAdjustments")
	public ResponseEntity<Object> autoCreateQuoteAdjustments(HttpSession session, @RequestParam(value="quoteId") String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoCreateQuoteAdjustments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteCoefficient")
	public ResponseEntity<Object> createQuoteCoefficient(HttpSession session, @RequestParam(value="coeffName") String coeffName, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="coeffValue", required=false) BigDecimal coeffValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("coeffName",coeffName);
		paramMap.put("quoteId",quoteId);
		paramMap.put("coeffValue",coeffValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteCoefficient", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteAdjustment")
	public ResponseEntity<Object> createQuoteAdjustment(HttpSession session, @RequestParam(value="quoteAdjustmentTypeId") String quoteAdjustmentTypeId, @RequestParam(value="quoteAdjustmentId") String quoteAdjustmentId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="includeInShipping", required=false) Boolean includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="includeInTax", required=false) Boolean includeInTax, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteAdjustmentTypeId",quoteAdjustmentTypeId);
		paramMap.put("quoteAdjustmentId",quoteAdjustmentId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("amount",amount);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("comments",comments);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteItem")
	public ResponseEntity<Object> updateQuoteItem(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="quoteUnitPrice", required=false) BigDecimal quoteUnitPrice, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="isPromo", required=false) Boolean isPromo, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="skillTypeId", required=false) String skillTypeId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="leadTimeDays", required=false) Long leadTimeDays, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("comments",comments);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("quoteUnitPrice",quoteUnitPrice);
		paramMap.put("uomId",uomId);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("isPromo",isPromo);
		paramMap.put("quoteId",quoteId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("leadTimeDays",leadTimeDays);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteTermAttribute")
	public ResponseEntity<Object> updateQuoteTermAttribute(HttpSession session, @RequestParam(value="quoteItemSeqId") String quoteItemSeqId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteTermAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeQuoteRole")
	public ResponseEntity<Object> removeQuoteRole(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeQuoteRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteFromShoppingList")
	public ResponseEntity<Object> createQuoteFromShoppingList(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="applyStorePromotions", required=false) String applyStorePromotions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("applyStorePromotions",applyStorePromotions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteFromShoppingList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteQuoteTermAttribute")
	public ResponseEntity<Object> deleteQuoteTermAttribute(HttpSession session, @RequestParam(value="quoteItemSeqId") String quoteItemSeqId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteQuoteTermAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteWorkEffort")
	public ResponseEntity<Object> createQuoteWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="revisionNumber", required=false) Long revisionNumber, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sendNotificationEmail", required=false) Boolean sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="currentStatusId", required=false) String currentStatusId, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="workEffortName", required=false) String workEffortName, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="lastStatusUpdate", required=false) Timestamp lastStatusUpdate, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("revisionNumber",revisionNumber);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("lastStatusUpdate",lastStatusUpdate);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("createdDate",createdDate);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quoteSequenceEnforced")
	public ResponseEntity<Object> quoteSequenceEnforced(HttpSession session, @RequestParam(value="partyAcctgPreference") org.apache.ofbiz.entity.GenericValue partyAcctgPreference, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="issueDate", required=false) Timestamp issueDate, @RequestParam(value="validThruDate", required=false) Timestamp validThruDate, @RequestParam(value="quoteName", required=false) String quoteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyAcctgPreference",partyAcctgPreference);
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("statusId",statusId);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("description",description);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("issueDate",issueDate);
		paramMap.put("validThruDate",validThruDate);
		paramMap.put("quoteName",quoteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quoteSequenceEnforced", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeQuoteAttribute")
	public ResponseEntity<Object> removeQuoteAttribute(HttpSession session, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="attrName", required=false) String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("quoteId",quoteId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeQuoteAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteTypeAttr")
	public ResponseEntity<Object> createQuoteTypeAttr(HttpSession session, @RequestParam(value="quoteTypeId") String quoteTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteFromCart")
	public ResponseEntity<Object> createQuoteFromCart(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart, @RequestParam(value="applyStorePromotions", required=false) String applyStorePromotions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("applyStorePromotions",applyStorePromotions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteFromCart", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteAttribute")
	public ResponseEntity<Object> createQuoteAttribute(HttpSession session, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteQuoteTypeAttr")
	public ResponseEntity<Object> deleteQuoteTypeAttr(HttpSession session, @RequestParam(value="quoteTypeId") String quoteTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteQuoteTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuote")
	public ResponseEntity<Object> createQuote(HttpSession session, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="issueDate", required=false) Timestamp issueDate, @RequestParam(value="validThruDate", required=false) Timestamp validThruDate, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="quoteName", required=false) String quoteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("statusId",statusId);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("description",description);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("issueDate",issueDate);
		paramMap.put("validThruDate",validThruDate);
		paramMap.put("quoteId",quoteId);
		paramMap.put("quoteName",quoteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendQuoteReportMail")
	public ResponseEntity<Object> sendQuoteReportMail(HttpSession session, @RequestParam(value="sendTo") String sendTo, @RequestParam(value="emailType") String emailType, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="note", required=false) String note, @RequestParam(value="sendCc", required=false) String sendCc) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sendTo",sendTo);
		paramMap.put("emailType",emailType);
		paramMap.put("quoteId",quoteId);
		paramMap.put("note",note);
		paramMap.put("sendCc",sendCc);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendQuoteReportMail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteRole")
	public ResponseEntity<Object> createQuoteRole(HttpSession session, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="quoteId", required=false) String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/autoUpdateQuotePrice")
	public ResponseEntity<Object> autoUpdateQuotePrice(HttpSession session, @RequestParam(value="quoteItemSeqId") String quoteItemSeqId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="defaultQuoteUnitPrice", required=false) BigDecimal defaultQuoteUnitPrice, @RequestParam(value="manualQuoteUnitPrice", required=false) BigDecimal manualQuoteUnitPrice, @RequestParam(value="averageCost", required=false) BigDecimal averageCost, @RequestParam(value="costToPriceMult", required=false) BigDecimal costToPriceMult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("defaultQuoteUnitPrice",defaultQuoteUnitPrice);
		paramMap.put("manualQuoteUnitPrice",manualQuoteUnitPrice);
		paramMap.put("averageCost",averageCost);
		paramMap.put("costToPriceMult",costToPriceMult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoUpdateQuotePrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/storeQuote")
	public ResponseEntity<Object> storeQuote(HttpSession session, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="quoteTerms", required=false) List quoteTerms, @RequestParam(value="quoteWorkEfforts", required=false) List quoteWorkEfforts, @RequestParam(value="description", required=false) String description, @RequestParam(value="quoteAttributes", required=false) List quoteAttributes, @RequestParam(value="validThruDate", required=false) Timestamp validThruDate, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="quoteAdjustments", required=false) List quoteAdjustments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="quoteCoefficients", required=false) List quoteCoefficients, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="quoteRoles", required=false) List quoteRoles, @RequestParam(value="quoteTermAttributes", required=false) List quoteTermAttributes, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="issueDate", required=false) Timestamp issueDate, @RequestParam(value="quoteName", required=false) String quoteName, @RequestParam(value="quoteItems", required=false) List quoteItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("quoteTerms",quoteTerms);
		paramMap.put("quoteWorkEfforts",quoteWorkEfforts);
		paramMap.put("description",description);
		paramMap.put("quoteAttributes",quoteAttributes);
		paramMap.put("validThruDate",validThruDate);
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("quoteAdjustments",quoteAdjustments);
		paramMap.put("statusId",statusId);
		paramMap.put("quoteCoefficients",quoteCoefficients);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("quoteRoles",quoteRoles);
		paramMap.put("quoteTermAttributes",quoteTermAttributes);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("issueDate",issueDate);
		paramMap.put("quoteName",quoteName);
		paramMap.put("quoteItems",quoteItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("storeQuote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteNote")
	public ResponseEntity<Object> createQuoteNote(HttpSession session, @RequestParam(value="noteInfo") String noteInfo, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="noteName", required=false) String noteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("noteInfo",noteInfo);
		paramMap.put("quoteId",quoteId);
		paramMap.put("noteName",noteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteCoefficient")
	public ResponseEntity<Object> updateQuoteCoefficient(HttpSession session, @RequestParam(value="coeffValue", required=false) BigDecimal coeffValue, @RequestParam(value="coeffName", required=false) String coeffName, @RequestParam(value="quoteId", required=false) String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("coeffValue",coeffValue);
		paramMap.put("coeffName",coeffName);
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteCoefficient", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeQuoteItem")
	public ResponseEntity<Object> removeQuoteItem(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="quoteUnitPrice", required=false) BigDecimal quoteUnitPrice, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="isPromo", required=false) Boolean isPromo, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="skillTypeId", required=false) String skillTypeId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="leadTimeDays", required=false) Long leadTimeDays, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("comments",comments);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("quoteUnitPrice",quoteUnitPrice);
		paramMap.put("uomId",uomId);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("isPromo",isPromo);
		paramMap.put("quoteId",quoteId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("leadTimeDays",leadTimeDays);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeQuoteItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkUpdateQuoteStatus")
	public ResponseEntity<Object> checkUpdateQuoteStatus(HttpSession session, @RequestParam(value="quoteId") String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkUpdateQuoteStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteType")
	public ResponseEntity<Object> updateQuoteType(HttpSession session, @RequestParam(value="quoteTypeId") String quoteTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeQuoteAdjustment")
	public ResponseEntity<Object> removeQuoteAdjustment(HttpSession session, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="includeInShipping", required=false) Boolean includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="quoteAdjustmentId", required=false) String quoteAdjustmentId, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="includeInTax", required=false) Boolean includeInTax, @RequestParam(value="quoteAdjustmentTypeId", required=false) String quoteAdjustmentTypeId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("quoteAdjustmentId",quoteAdjustmentId);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("quoteAdjustmentTypeId",quoteAdjustmentTypeId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeQuoteAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyQuote")
	public ResponseEntity<Object> copyQuote(HttpSession session, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="copyQuoteAttributes", required=false) String copyQuoteAttributes, @RequestParam(value="copyQuoteCoefficients", required=false) String copyQuoteCoefficients, @RequestParam(value="copyQuoteTerms", required=false) String copyQuoteTerms, @RequestParam(value="copyQuoteRoles", required=false) String copyQuoteRoles, @RequestParam(value="copyQuoteItems", required=false) String copyQuoteItems, @RequestParam(value="copyQuoteAdjustments", required=false) String copyQuoteAdjustments) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("copyQuoteAttributes",copyQuoteAttributes);
		paramMap.put("copyQuoteCoefficients",copyQuoteCoefficients);
		paramMap.put("copyQuoteTerms",copyQuoteTerms);
		paramMap.put("copyQuoteRoles",copyQuoteRoles);
		paramMap.put("copyQuoteItems",copyQuoteItems);
		paramMap.put("copyQuoteAdjustments",copyQuoteAdjustments);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyQuote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteType")
	public ResponseEntity<Object> createQuoteType(HttpSession session, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuote")
	public ResponseEntity<Object> updateQuote(HttpSession session, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="issueDate", required=false) Timestamp issueDate, @RequestParam(value="validThruDate", required=false) Timestamp validThruDate, @RequestParam(value="quoteName", required=false) String quoteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("statusId",statusId);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("description",description);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("issueDate",issueDate);
		paramMap.put("validThruDate",validThruDate);
		paramMap.put("quoteName",quoteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuote", paramMap);
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
