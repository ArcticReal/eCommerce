package com.skytala.eCommerce.service.order;

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
@RequestMapping("/service/orderReturn")
public class OrderReturnServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/getReturnAmountByOrder")
	public ResponseEntity<Object> getReturnAmountByOrder(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getReturnAmountByOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItem")
	public ResponseEntity<Object> createReturnItem(HttpSession session, @RequestParam(value="returnQuantity") BigDecimal returnQuantity, @RequestParam(value="returnItemTypeId") String returnItemTypeId, @RequestParam(value="orderId") String orderId, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnTypeId") String returnTypeId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="includeAdjustments", required=false) String includeAdjustments, @RequestParam(value="returnItemResponseId", required=false) String returnItemResponseId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="returnReasonId", required=false) String returnReasonId, @RequestParam(value="receivedQuantity", required=false) BigDecimal receivedQuantity, @RequestParam(value="description", required=false) String description, @RequestParam(value="expectedItemStatus", required=false) String expectedItemStatus, @RequestParam(value="returnPrice", required=false) BigDecimal returnPrice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnQuantity",returnQuantity);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("orderId",orderId);
		paramMap.put("returnId",returnId);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("includeAdjustments",includeAdjustments);
		paramMap.put("returnItemResponseId",returnItemResponseId);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("receivedQuantity",receivedQuantity);
		paramMap.put("description",description);
		paramMap.put("expectedItemStatus",expectedItemStatus);
		paramMap.put("returnPrice",returnPrice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnAdjustment")
	public ResponseEntity<Object> updateReturnAdjustment(HttpSession session, @RequestParam(value="returnAdjustmentId") String returnAdjustmentId, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="returnAdjustmentTypeId", required=false) String returnAdjustmentTypeId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="originalReturnQuantity", required=false) BigDecimal originalReturnQuantity, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="orderAdjustmentId", required=false) String orderAdjustmentId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId, @RequestParam(value="originalReturnPrice", required=false) BigDecimal originalReturnPrice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnAdjustmentId",returnAdjustmentId);
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("returnId",returnId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("originalReturnQuantity",originalReturnQuantity);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("originalReturnPrice",originalReturnPrice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnType")
	public ResponseEntity<Object> updateReturnType(HttpSession session, @RequestParam(value="returnTypeId") String returnTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="sequenceId", required=false) String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("description",description);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReturnItemTypeMap")
	public ResponseEntity<Object> deleteReturnItemTypeMap(HttpSession session, @RequestParam(value="returnItemMapKey") String returnItemMapKey, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnItemMapKey",returnItemMapKey);
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReturnItemTypeMap", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReturnReason")
	public ResponseEntity<Object> deleteReturnReason(HttpSession session, @RequestParam(value="returnReasonId") String returnReasonId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReturnReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/autoCancelReplacementOrders")
	public ResponseEntity<Object> autoCancelReplacementOrders(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoCancelReplacementOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processRefundReturnForReplacement")
	public ResponseEntity<Object> processRefundReturnForReplacement(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processRefundReturnForReplacement", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReturnContactMech")
	public ResponseEntity<Object> deleteReturnContactMech(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="returnId") String returnId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("returnId",returnId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReturnContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnHeaderType")
	public ResponseEntity<Object> updateReturnHeaderType(HttpSession session, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnHeaderType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnItemType")
	public ResponseEntity<Object> updateReturnItemType(HttpSession session, @RequestParam(value="returnItemTypeId") String returnItemTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnAndItemOrAdjustment")
	public ResponseEntity<Object> createReturnAndItemOrAdjustment(HttpSession session, @RequestParam(value="returnQuantity", required=false) BigDecimal returnQuantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="returnHeaderTypeId", required=false) String returnHeaderTypeId, @RequestParam(value="returnAdjustmentTypeId", required=false) String returnAdjustmentTypeId, @RequestParam(value="returnItemResponseId", required=false) String returnItemResponseId, @RequestParam(value="expectedItemStatus", required=false) String expectedItemStatus, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="receivedQuantity", required=false) BigDecimal receivedQuantity, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage, @RequestParam(value="toPartyId", required=false) String toPartyId, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="fromPartyId", required=false) String fromPartyId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="description", required=false) String description, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="returnReasonId", required=false) String returnReasonId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="destinationFacilityId", required=false) String destinationFacilityId, @RequestParam(value="orderAdjustmentId", required=false) String orderAdjustmentId, @RequestParam(value="supplierRmaId", required=false) String supplierRmaId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="returnPrice", required=false) BigDecimal returnPrice, @RequestParam(value="returnAdjustmentId", required=false) String returnAdjustmentId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="returnItemTypeId", required=false) String returnItemTypeId, @RequestParam(value="needsInventoryReceive", required=false) String needsInventoryReceive, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnQuantity",returnQuantity);
		paramMap.put("orderId",orderId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("returnItemResponseId",returnItemResponseId);
		paramMap.put("expectedItemStatus",expectedItemStatus);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("productId",productId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("statusId",statusId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("receivedQuantity",receivedQuantity);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("toPartyId",toPartyId);
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("fromPartyId",fromPartyId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("description",description);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("returnId",returnId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("destinationFacilityId",destinationFacilityId);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("supplierRmaId",supplierRmaId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("returnPrice",returnPrice);
		paramMap.put("returnAdjustmentId",returnAdjustmentId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("createdBy",createdBy);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("needsInventoryReceive",needsInventoryReceive);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnAndItemOrAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnHeader")
	public ResponseEntity<Object> updateReturnHeader(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="fromPartyId", required=false) String fromPartyId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="returnHeaderTypeId", required=false) String returnHeaderTypeId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="needsInventoryReceive", required=false) String needsInventoryReceive, @RequestParam(value="toPartyId", required=false) String toPartyId, @RequestParam(value="destinationFacilityId", required=false) String destinationFacilityId, @RequestParam(value="supplierRmaId", required=false) String supplierRmaId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("fromPartyId",fromPartyId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("createdBy",createdBy);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("needsInventoryReceive",needsInventoryReceive);
		paramMap.put("toPartyId",toPartyId);
		paramMap.put("destinationFacilityId",destinationFacilityId);
		paramMap.put("supplierRmaId",supplierRmaId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnHeader", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItemTypeMap")
	public ResponseEntity<Object> createReturnItemTypeMap(HttpSession session, @RequestParam(value="returnItemMapKey") String returnItemMapKey, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId, @RequestParam(value="returnItemTypeId", required=false) String returnItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnItemMapKey",returnItemMapKey);
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItemTypeMap", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processCreditReturn")
	public ResponseEntity<Object> processCreditReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processCreditReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnHeader")
	public ResponseEntity<Object> createReturnHeader(HttpSession session, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId, @RequestParam(value="fromPartyId", required=false) String fromPartyId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="needsInventoryReceive", required=false) String needsInventoryReceive, @RequestParam(value="toPartyId", required=false) String toPartyId, @RequestParam(value="destinationFacilityId", required=false) String destinationFacilityId, @RequestParam(value="supplierRmaId", required=false) String supplierRmaId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("fromPartyId",fromPartyId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("createdBy",createdBy);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("needsInventoryReceive",needsInventoryReceive);
		paramMap.put("toPartyId",toPartyId);
		paramMap.put("destinationFacilityId",destinationFacilityId);
		paramMap.put("supplierRmaId",supplierRmaId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnHeader", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReturnType")
	public ResponseEntity<Object> deleteReturnType(HttpSession session, @RequestParam(value="returnTypeId") String returnTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReturnType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processRefundOnlyReturn")
	public ResponseEntity<Object> processRefundOnlyReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processRefundOnlyReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendReturnCompleteNotification")
	public ResponseEntity<Object> sendReturnCompleteNotification(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendReturnCompleteNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnReason")
	public ResponseEntity<Object> updateReturnReason(HttpSession session, @RequestParam(value="returnReasonId") String returnReasonId, @RequestParam(value="description", required=false) String description, @RequestParam(value="sequenceId", required=false) String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("description",description);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnType")
	public ResponseEntity<Object> createReturnType(HttpSession session, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="sequenceId", required=false) String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("description",description);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItemShipment")
	public ResponseEntity<Object> createReturnItemShipment(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId") String returnItemSeqId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItemShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnItem")
	public ResponseEntity<Object> updateReturnItem(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId") String returnItemSeqId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="returnQuantity", required=false) BigDecimal returnQuantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="returnPrice", required=false) BigDecimal returnPrice, @RequestParam(value="returnItemResponseId", required=false) String returnItemResponseId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="returnReasonId", required=false) String returnReasonId, @RequestParam(value="returnItemTypeId", required=false) String returnItemTypeId, @RequestParam(value="receivedQuantity", required=false) BigDecimal receivedQuantity, @RequestParam(value="expectedItemStatus", required=false) String expectedItemStatus) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("returnQuantity",returnQuantity);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("description",description);
		paramMap.put("returnPrice",returnPrice);
		paramMap.put("returnItemResponseId",returnItemResponseId);
		paramMap.put("statusId",statusId);
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("receivedQuantity",receivedQuantity);
		paramMap.put("expectedItemStatus",expectedItemStatus);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItemType")
	public ResponseEntity<Object> createReturnItemType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="returnItemTypeId", required=false) String returnItemTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnItemOrAdjustment")
	public ResponseEntity<Object> updateReturnItemOrAdjustment(HttpSession session, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="returnQuantity", required=false) BigDecimal returnQuantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="returnAdjustmentTypeId", required=false) String returnAdjustmentTypeId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="returnItemResponseId", required=false) String returnItemResponseId, @RequestParam(value="returnReasonId", required=false) String returnReasonId, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="expectedItemStatus", required=false) String expectedItemStatus, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="orderAdjustmentId", required=false) String orderAdjustmentId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="returnPrice", required=false) BigDecimal returnPrice, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="returnAdjustmentId", required=false) String returnAdjustmentId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="returnItemTypeId", required=false) String returnItemTypeId, @RequestParam(value="receivedQuantity", required=false) BigDecimal receivedQuantity, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("returnQuantity",returnQuantity);
		paramMap.put("orderId",orderId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("returnItemResponseId",returnItemResponseId);
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("returnId",returnId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("expectedItemStatus",expectedItemStatus);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("productId",productId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("returnPrice",returnPrice);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("returnAdjustmentId",returnAdjustmentId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("receivedQuantity",receivedQuantity);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnItemOrAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickReturnOrder")
	public ResponseEntity<Object> quickReturnOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId, @RequestParam(value="returnReasonId", required=false) String returnReasonId, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="receiveReturn", required=false) Boolean receiveReturn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("receiveReturn",receiveReturn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickReturnOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processRefundImmediatelyReturn")
	public ResponseEntity<Object> processRefundImmediatelyReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processRefundImmediatelyReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentApplicationsFromReturnItemResponse")
	public ResponseEntity<Object> createPaymentApplicationsFromReturnItemResponse(HttpSession session, @RequestParam(value="returnItemResponseId") String returnItemResponseId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnItemResponseId",returnItemResponseId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentApplicationsFromReturnItemResponse", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/refundBillingAccountPayment")
	public ResponseEntity<Object> refundBillingAccountPayment(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("refundAmount",refundAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("refundBillingAccountPayment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createExchangeOrderAssoc")
	public ResponseEntity<Object> createExchangeOrderAssoc(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="originOrderId") String originOrderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("originOrderId",originOrderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createExchangeOrderAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getReturnItemInitialCost")
	public ResponseEntity<Object> getReturnItemInitialCost(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId") String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getReturnItemInitialCost", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnContactMech")
	public ResponseEntity<Object> createReturnContactMech(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId") String returnItemSeqId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnItemTypeMap")
	public ResponseEntity<Object> updateReturnItemTypeMap(HttpSession session, @RequestParam(value="returnItemMapKey") String returnItemMapKey, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId, @RequestParam(value="returnItemTypeId", required=false) String returnItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnItemMapKey",returnItemMapKey);
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnItemTypeMap", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getReturnableItems")
	public ResponseEntity<Object> getReturnableItems(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getReturnableItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeReturnAdjustment")
	public ResponseEntity<Object> removeReturnAdjustment(HttpSession session, @RequestParam(value="returnAdjustmentId") String returnAdjustmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnAdjustmentId",returnAdjustmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeReturnAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnAdjustment")
	public ResponseEntity<Object> createReturnAdjustment(HttpSession session, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="returnAdjustmentTypeId", required=false) String returnAdjustmentTypeId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="orderAdjustmentId", required=false) String orderAdjustmentId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("returnId",returnId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getReturnableQuantity")
	public ResponseEntity<Object> getReturnableQuantity(HttpSession session, @RequestParam(value="orderItem") org.apache.ofbiz.entity.GenericValue orderItem) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItem",orderItem);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getReturnableQuantity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processReplacementReturn")
	public ResponseEntity<Object> processReplacementReturn(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnTypeId") String returnTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processReplacementReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnAdjustmentType")
	public ResponseEntity<Object> createReturnAdjustmentType(HttpSession session, @RequestParam(value="returnAdjustmentTypeId", required=false) String returnAdjustmentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnAdjustmentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnHeaderType")
	public ResponseEntity<Object> createReturnHeaderType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="returnHeaderTypeId", required=false) String returnHeaderTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnHeaderType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReturnAdjustmentType")
	public ResponseEntity<Object> deleteReturnAdjustmentType(HttpSession session, @RequestParam(value="returnAdjustmentTypeId") String returnAdjustmentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReturnAdjustmentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnReason")
	public ResponseEntity<Object> createReturnReason(HttpSession session, @RequestParam(value="returnReasonId", required=false) String returnReasonId, @RequestParam(value="description", required=false) String description, @RequestParam(value="sequenceId", required=false) String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("description",description);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getStatusItemsForReturn")
	public ResponseEntity<Object> getStatusItemsForReturn(HttpSession session, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getStatusItemsForReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnItemsStatus")
	public ResponseEntity<Object> updateReturnItemsStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnItemsStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processReplaceImmediatelyReturn")
	public ResponseEntity<Object> processReplaceImmediatelyReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processReplaceImmediatelyReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelReturnItems")
	public ResponseEntity<Object> cancelReturnItems(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelReturnItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addProductsBackToCategory")
	public ResponseEntity<Object> addProductsBackToCategory(HttpSession session, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="returnId", required=false) String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProductsBackToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendReturnAcceptNotification")
	public ResponseEntity<Object> sendReturnAcceptNotification(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendReturnAcceptNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processWaitReplacementReservedReturn")
	public ResponseEntity<Object> processWaitReplacementReservedReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processWaitReplacementReservedReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReturnItemType")
	public ResponseEntity<Object> deleteReturnItemType(HttpSession session, @RequestParam(value="returnItemTypeId") String returnItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReturnItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkPaymentAmountForRefund")
	public ResponseEntity<Object> checkPaymentAmountForRefund(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkPaymentAmountForRefund", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnStatus")
	public ResponseEntity<Object> createReturnStatus(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItemBilling")
	public ResponseEntity<Object> createReturnItemBilling(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId") String returnItemSeqId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="shipmentReceiptId", required=false) String shipmentReceiptId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("shipmentReceiptId",shipmentReceiptId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItemBilling", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnAdjustmentType")
	public ResponseEntity<Object> updateReturnAdjustmentType(HttpSession session, @RequestParam(value="returnAdjustmentTypeId") String returnAdjustmentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnAdjustmentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnContactMech")
	public ResponseEntity<Object> updateReturnContactMech(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="returnId") String returnId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="oldContactMechId", required=false) String oldContactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("returnId",returnId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("oldContactMechId",oldContactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processSubscriptionReturn")
	public ResponseEntity<Object> processSubscriptionReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processSubscriptionReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processWaitReplacementReturn")
	public ResponseEntity<Object> processWaitReplacementReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processWaitReplacementReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeReturnItem")
	public ResponseEntity<Object> removeReturnItem(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId") String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeReturnItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendReturnCancelNotification")
	public ResponseEntity<Object> sendReturnCancelNotification(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendReturnCancelNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processCrossShipReplacementReturn")
	public ResponseEntity<Object> processCrossShipReplacementReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processCrossShipReplacementReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getOrderAvailableReturnedTotal")
	public ResponseEntity<Object> getOrderAvailableReturnedTotal(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="countNewReturnItems", required=false) Boolean countNewReturnItems, @RequestParam(value="adjustment", required=false) BigDecimal adjustment) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("countNewReturnItems",countNewReturnItems);
		paramMap.put("adjustment",adjustment);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOrderAvailableReturnedTotal", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReturnHeaderType")
	public ResponseEntity<Object> deleteReturnHeaderType(HttpSession session, @RequestParam(value="returnHeaderTypeId") String returnHeaderTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnHeaderTypeId",returnHeaderTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReturnHeaderType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReturnStatusFromReceipt")
	public ResponseEntity<Object> updateReturnStatusFromReceipt(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReturnStatusFromReceipt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItemOrAdjustment")
	public ResponseEntity<Object> createReturnItemOrAdjustment(HttpSession session, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="returnQuantity", required=false) BigDecimal returnQuantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="returnAdjustmentTypeId", required=false) String returnAdjustmentTypeId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="returnItemResponseId", required=false) String returnItemResponseId, @RequestParam(value="returnReasonId", required=false) String returnReasonId, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="expectedItemStatus", required=false) String expectedItemStatus, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="orderAdjustmentId", required=false) String orderAdjustmentId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="returnPrice", required=false) BigDecimal returnPrice, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="returnAdjustmentId", required=false) String returnAdjustmentId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="returnItemTypeId", required=false) String returnItemTypeId, @RequestParam(value="receivedQuantity", required=false) BigDecimal receivedQuantity, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("returnQuantity",returnQuantity);
		paramMap.put("orderId",orderId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("returnItemResponseId",returnItemResponseId);
		paramMap.put("returnReasonId",returnReasonId);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("returnId",returnId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("expectedItemStatus",expectedItemStatus);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("productId",productId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("returnPrice",returnPrice);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("returnAdjustmentId",returnAdjustmentId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("returnItemTypeId",returnItemTypeId);
		paramMap.put("receivedQuantity",receivedQuantity);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItemOrAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkReturnComplete")
	public ResponseEntity<Object> checkReturnComplete(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkReturnComplete", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processRepairReplacementReturn")
	public ResponseEntity<Object> processRepairReplacementReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processRepairReplacementReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItemForRental")
	public ResponseEntity<Object> createReturnItemForRental(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItemForRental", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processRefundReturn")
	public ResponseEntity<Object> processRefundReturn(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnTypeId") String returnTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processRefundReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelReplacementOrderItems")
	public ResponseEntity<Object> cancelReplacementOrderItems(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="returnItemSeqId") String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelReplacementOrderItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReturnItemResponse")
	public ResponseEntity<Object> createReturnItemResponse(HttpSession session, @RequestParam(value="replacementOrderId", required=false) String replacementOrderId, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="orderPaymentPreferenceId", required=false) String orderPaymentPreferenceId, @RequestParam(value="responseAmount", required=false) BigDecimal responseAmount, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="responseDate", required=false) Timestamp responseDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("replacementOrderId",replacementOrderId);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("responseAmount",responseAmount);
		paramMap.put("paymentId",paymentId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("responseDate",responseDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReturnItemResponse", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/returnAdjustmentInterface")
	public ResponseEntity<Object> returnAdjustmentInterface(HttpSession session, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="returnTypeId", required=false) String returnTypeId, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="returnAdjustmentTypeId", required=false) String returnAdjustmentTypeId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="orderAdjustmentId", required=false) String orderAdjustmentId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="returnAdjustmentId", required=false) String returnAdjustmentId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("returnTypeId",returnTypeId);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("returnAdjustmentTypeId",returnAdjustmentTypeId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("returnId",returnId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("returnAdjustmentId",returnAdjustmentId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("returnAdjustmentInterface", paramMap);
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



}
