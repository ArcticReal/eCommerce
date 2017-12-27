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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/orderRequest")
public class OrderRequestServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestItem")
	public ResponseEntity<Map<String, Object>> createCustRequestItem(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestResolutionId", required=false) String custRequestResolutionId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="maximumAmount", required=false) BigDecimal maximumAmount, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount, @RequestParam(value="story", required=false) String story) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("maximumAmount",maximumAmount);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("description",description);
		paramMap.put("priority",priority);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("statusId",statusId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("story",story);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestItem")
	public ResponseEntity<Map<String, Object>> updateCustRequestItem(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId, @RequestParam(value="custRequestResolutionId", required=false) String custRequestResolutionId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="maximumAmount", required=false) BigDecimal maximumAmount, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount, @RequestParam(value="story", required=false) String story) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("maximumAmount",maximumAmount);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("description",description);
		paramMap.put("priority",priority);
		paramMap.put("statusId",statusId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("story",story);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestFromShoppingList")
	public ResponseEntity<Map<String, Object>> createCustRequestFromShoppingList(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestFromShoppingList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequestType")
	public ResponseEntity<Map<String, Object>> deleteCustRequestType(HttpSession session, @RequestParam(value="custRequestTypeId") String custRequestTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequestType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/custRequestPermissionCheck")
	public ResponseEntity<Map<String, Object>> custRequestPermissionCheck(HttpSession session, @RequestParam(value="fromPartyId", required=false) String fromPartyId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromPartyId",fromPartyId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("custRequestPermissionCheck", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestAttribute")
	public ResponseEntity<Map<String, Object>> updateCustRequestAttribute(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="attrValue") String attrValue, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("attrValue",attrValue);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyCustRequestItem")
	public ResponseEntity<Map<String, Object>> copyCustRequestItem(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId, @RequestParam(value="custRequestResolutionId", required=false) String custRequestResolutionId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="custRequestItemSeqIdTo", required=false) String custRequestItemSeqIdTo, @RequestParam(value="maximumAmount", required=false) BigDecimal maximumAmount, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="custRequestIdTo", required=false) String custRequestIdTo, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="copyLinkedQuotes", required=false) String copyLinkedQuotes, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount, @RequestParam(value="story", required=false) String story) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("custRequestItemSeqIdTo",custRequestItemSeqIdTo);
		paramMap.put("maximumAmount",maximumAmount);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("description",description);
		paramMap.put("priority",priority);
		paramMap.put("custRequestIdTo",custRequestIdTo);
		paramMap.put("statusId",statusId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("copyLinkedQuotes",copyLinkedQuotes);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("story",story);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyCustRequestItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestParty")
	public ResponseEntity<Map<String, Object>> createCustRequestParty(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestResolution")
	public ResponseEntity<Map<String, Object>> createCustRequestResolution(HttpSession session, @RequestParam(value="custRequestResolutionId", required=false) String custRequestResolutionId, @RequestParam(value="description", required=false) String description, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("description",description);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestResolution", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestFromCommEvent")
	public ResponseEntity<Map<String, Object>> createCustRequestFromCommEvent(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="note", required=false) String note, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="ccString", required=false) String ccString, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="communicationEventTypeId", required=false) String communicationEventTypeId, @RequestParam(value="content", required=false) String content, @RequestParam(value="contentMimeTypeId", required=false) String contentMimeTypeId, @RequestParam(value="datetimeStarted", required=false) Timestamp datetimeStarted, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="contactMechIdFrom", required=false) String contactMechIdFrom, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="custRequestName", required=false) String custRequestName, @RequestParam(value="parentCommEventId", required=false) String parentCommEventId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="datetimeEnded", required=false) Timestamp datetimeEnded, @RequestParam(value="origCommEventId", required=false) String origCommEventId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="contactMechIdTo", required=false) String contactMechIdTo, @RequestParam(value="headerString", required=false) String headerString, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="bccString", required=false) String bccString, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="toString", required=false) String toString, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId, @RequestParam(value="fromString", required=false) String fromString, @RequestParam(value="story", required=false) String story) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("note",note);
		paramMap.put("subject",subject);
		paramMap.put("ccString",ccString);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("communicationEventTypeId",communicationEventTypeId);
		paramMap.put("content",content);
		paramMap.put("contentMimeTypeId",contentMimeTypeId);
		paramMap.put("datetimeStarted",datetimeStarted);
		paramMap.put("contactListId",contactListId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("custRequestName",custRequestName);
		paramMap.put("parentCommEventId",parentCommEventId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("datetimeEnded",datetimeEnded);
		paramMap.put("origCommEventId",origCommEventId);
		paramMap.put("messageId",messageId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("headerString",headerString);
		paramMap.put("statusId",statusId);
		paramMap.put("bccString",bccString);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("toString",toString);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("fromString",fromString);
		paramMap.put("story",story);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestFromCommEvent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequestCategory")
	public ResponseEntity<Map<String, Object>> deleteCustRequestCategory(HttpSession session, @RequestParam(value="custRequestCategoryId") String custRequestCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestCategoryId",custRequestCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequestCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestResolution")
	public ResponseEntity<Map<String, Object>> updateCustRequestResolution(HttpSession session, @RequestParam(value="custRequestResolutionId") String custRequestResolutionId, @RequestParam(value="description", required=false) String description, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("description",description);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestResolution", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequest")
	public ResponseEntity<Map<String, Object>> createCustRequest(HttpSession session, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="fromPartyId", required=false) String fromPartyId, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="fulfillContactMechId", required=false) String fulfillContactMechId, @RequestParam(value="maximumAmount", required=false) BigDecimal maximumAmount, @RequestParam(value="description", required=false) String description, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="custRequestDate", required=false) Timestamp custRequestDate, @RequestParam(value="internalComment", required=false) String internalComment, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="closedDateTime", required=false) Timestamp closedDateTime, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="custRequestName", required=false) String custRequestName, @RequestParam(value="custRequestResolutionId", required=false) String custRequestResolutionId, @RequestParam(value="responseRequiredDate", required=false) Timestamp responseRequiredDate, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="maximumAmountUomId", required=false) String maximumAmountUomId, @RequestParam(value="openDateTime", required=false) Timestamp openDateTime, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId, @RequestParam(value="custRequestCategoryId", required=false) String custRequestCategoryId, @RequestParam(value="story", required=false) String story) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("reason",reason);
		paramMap.put("fromPartyId",fromPartyId);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("fulfillContactMechId",fulfillContactMechId);
		paramMap.put("maximumAmount",maximumAmount);
		paramMap.put("description",description);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("custRequestDate",custRequestDate);
		paramMap.put("internalComment",internalComment);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("closedDateTime",closedDateTime);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("custRequestName",custRequestName);
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("responseRequiredDate",responseRequiredDate);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("productId",productId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("priority",priority);
		paramMap.put("maximumAmountUomId",maximumAmountUomId);
		paramMap.put("openDateTime",openDateTime);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("custRequestCategoryId",custRequestCategoryId);
		paramMap.put("story",story);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequest")
	public ResponseEntity<Map<String, Object>> updateCustRequest(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="fromPartyId", required=false) String fromPartyId, @RequestParam(value="custRequestName", required=false) String custRequestName, @RequestParam(value="responseRequiredDate", required=false) Timestamp responseRequiredDate, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="fulfillContactMechId", required=false) String fulfillContactMechId, @RequestParam(value="description", required=false) String description, @RequestParam(value="custRequestDate", required=false) Timestamp custRequestDate, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="maximumAmountUomId", required=false) String maximumAmountUomId, @RequestParam(value="openDateTime", required=false) Timestamp openDateTime, @RequestParam(value="internalComment", required=false) String internalComment, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="custRequestCategoryId", required=false) String custRequestCategoryId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="closedDateTime", required=false) Timestamp closedDateTime, @RequestParam(value="story", required=false) String story, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("reason",reason);
		paramMap.put("fromPartyId",fromPartyId);
		paramMap.put("custRequestName",custRequestName);
		paramMap.put("responseRequiredDate",responseRequiredDate);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("fulfillContactMechId",fulfillContactMechId);
		paramMap.put("description",description);
		paramMap.put("custRequestDate",custRequestDate);
		paramMap.put("priority",priority);
		paramMap.put("maximumAmountUomId",maximumAmountUomId);
		paramMap.put("openDateTime",openDateTime);
		paramMap.put("internalComment",internalComment);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("custRequestCategoryId",custRequestCategoryId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("closedDateTime",closedDateTime);
		paramMap.put("story",story);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequestParty")
	public ResponseEntity<Map<String, Object>> deleteCustRequestParty(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequestParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequestResolution")
	public ResponseEntity<Map<String, Object>> deleteCustRequestResolution(HttpSession session, @RequestParam(value="custRequestResolutionId") String custRequestResolutionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequestResolution", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestCategory")
	public ResponseEntity<Map<String, Object>> updateCustRequestCategory(HttpSession session, @RequestParam(value="custRequestCategoryId") String custRequestCategoryId, @RequestParam(value="description", required=false) String description, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestCategoryId",custRequestCategoryId);
		paramMap.put("description",description);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestAttribute")
	public ResponseEntity<Map<String, Object>> createCustRequestAttribute(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="attrValue") String attrValue, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("attrValue",attrValue);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getCustRequestsByRole")
	public ResponseEntity<Map<String, Object>> getCustRequestsByRole(HttpSession session, @RequestParam(value="roleTypeId", required=false) String roleTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getCustRequestsByRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestType")
	public ResponseEntity<Map<String, Object>> createCustRequestType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestItemNote")
	public ResponseEntity<Map<String, Object>> createCustRequestItemNote(HttpSession session, @RequestParam(value="note") String note, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("note",note);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestItemNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestStatus")
	public ResponseEntity<Map<String, Object>> createCustRequestStatus(HttpSession session, @RequestParam(value="statusDate", required=false) Timestamp statusDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="changeByUserLoginId", required=false) String changeByUserLoginId, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusDate",statusDate);
		paramMap.put("statusId",statusId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("changeByUserLoginId",changeByUserLoginId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestType")
	public ResponseEntity<Map<String, Object>> updateCustRequestType(HttpSession session, @RequestParam(value="custRequestTypeId") String custRequestTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestNote")
	public ResponseEntity<Map<String, Object>> createCustRequestNote(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="noteInfo") String noteInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("noteInfo",noteInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestCategory")
	public ResponseEntity<Map<String, Object>> createCustRequestCategory(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId, @RequestParam(value="custRequestCategoryId", required=false) String custRequestCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("custRequestCategoryId",custRequestCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestNote")
	public ResponseEntity<Map<String, Object>> updateCustRequestNote(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="noteId") String noteId, @RequestParam(value="noteInfo", required=false) String noteInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("noteId",noteId);
		paramMap.put("noteInfo",noteInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestContent")
	public ResponseEntity<Map<String, Object>> createCustRequestContent(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestParty")
	public ResponseEntity<Map<String, Object>> updateCustRequestParty(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateRespondingParty")
	public ResponseEntity<Map<String, Object>> updateRespondingParty(HttpSession session, @RequestParam(value="respondingPartySeqId") String respondingPartySeqId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="partyId") String partyId, @RequestParam(value="dateSent", required=false) Timestamp dateSent, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("respondingPartySeqId",respondingPartySeqId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("dateSent",dateSent);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRespondingParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRespondingParty")
	public ResponseEntity<Map<String, Object>> deleteRespondingParty(HttpSession session, @RequestParam(value="respondingPartySeqId") String respondingPartySeqId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("respondingPartySeqId",respondingPartySeqId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRespondingParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustRequestTypeAttr")
	public ResponseEntity<Map<String, Object>> updateCustRequestTypeAttr(HttpSession session, @RequestParam(value="custRequestTypeId") String custRequestTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustRequestTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createRespondingParty")
	public ResponseEntity<Map<String, Object>> createRespondingParty(HttpSession session, @RequestParam(value="respondingPartySeqId") String respondingPartySeqId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="partyId") String partyId, @RequestParam(value="dateSent", required=false) Timestamp dateSent, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("respondingPartySeqId",respondingPartySeqId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("dateSent",dateSent);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRespondingParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequestContent")
	public ResponseEntity<Map<String, Object>> deleteCustRequestContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequestContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequest")
	public ResponseEntity<Map<String, Object>> deleteCustRequest(HttpSession session, @RequestParam(value="custRequestId") String custRequestId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestFromCart")
	public ResponseEntity<Map<String, Object>> createCustRequestFromCart(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart, @RequestParam(value="custRequestName", required=false) String custRequestName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("custRequestName",custRequestName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestFromCart", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequestTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteCustRequestTypeAttr(HttpSession session, @RequestParam(value="custRequestTypeId") String custRequestTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequestTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestTypeAttr")
	public ResponseEntity<Map<String, Object>> createCustRequestTypeAttr(HttpSession session, @RequestParam(value="custRequestTypeId") String custRequestTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setCustRequestStatus")
	public ResponseEntity<Map<String, Object>> setCustRequestStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("reason",reason);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCustRequestStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/expireCustRequestParty")
	public ResponseEntity<Map<String, Object>> expireCustRequestParty(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireCustRequestParty", paramMap);
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
