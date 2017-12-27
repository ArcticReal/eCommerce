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
@RequestMapping("/service/orderShoppinglist")
public class OrderShoppinglistServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateShoppingListItem")
	public ResponseEntity<Map<String, Object>> updateShoppingListItem(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="shoppingListItemSeqId") String shoppingListItemSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="quantityPurchased", required=false) BigDecimal quantityPurchased, @RequestParam(value="modifiedPrice", required=false) BigDecimal modifiedPrice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("shoppingListItemSeqId",shoppingListItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("quantityPurchased",quantityPurchased);
		paramMap.put("modifiedPrice",modifiedPrice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShoppingListItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShoppingListType")
	public ResponseEntity<Map<String, Object>> deleteShoppingListType(HttpSession session, @RequestParam(value="shoppingListTypeId") String shoppingListTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShoppingListType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingListItemInterface")
	public ResponseEntity<Map<String, Object>> shoppingListItemInterface(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="quantityPurchased", required=false) BigDecimal quantityPurchased, @RequestParam(value="modifiedPrice", required=false) BigDecimal modifiedPrice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("quantityPurchased",quantityPurchased);
		paramMap.put("modifiedPrice",modifiedPrice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("shoppingListItemInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShoppingList")
	public ResponseEntity<Map<String, Object>> updateShoppingList(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="intervalNumber", required=false) Integer intervalNumber, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="parentShoppingListId", required=false) String parentShoppingListId, @RequestParam(value="productPromoCodeId", required=false) String productPromoCodeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="endDateTime", required=false) Timestamp endDateTime, @RequestParam(value="isActive", required=false) String isActive, @RequestParam(value="shoppingListTypeId", required=false) String shoppingListTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="frequency", required=false) Integer frequency, @RequestParam(value="shippingMethodString", required=false) String shippingMethodString, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="startDateTime", required=false) Timestamp startDateTime, @RequestParam(value="currencyUom", required=false) String currencyUom, @RequestParam(value="lastOrderedDate", required=false) Timestamp lastOrderedDate, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="listName", required=false) String listName, @RequestParam(value="lastAdminModified", required=false) Timestamp lastAdminModified, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="visitorId", required=false) String visitorId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("intervalNumber",intervalNumber);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("parentShoppingListId",parentShoppingListId);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("description",description);
		paramMap.put("endDateTime",endDateTime);
		paramMap.put("isActive",isActive);
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("frequency",frequency);
		paramMap.put("shippingMethodString",shippingMethodString);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("startDateTime",startDateTime);
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("lastOrderedDate",lastOrderedDate);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("listName",listName);
		paramMap.put("lastAdminModified",lastAdminModified);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("visitorId",visitorId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShoppingList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingListItemSurvey")
	public ResponseEntity<Map<String, Object>> createShoppingListItemSurvey(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="shoppingListItemSeqId") String shoppingListItemSeqId, @RequestParam(value="surveyResponseId") String surveyResponseId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("shoppingListItemSeqId",shoppingListItemSeqId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShoppingListItemSurvey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingListType")
	public ResponseEntity<Map<String, Object>> createShoppingListType(HttpSession session, @RequestParam(value="shoppingListTypeId") String shoppingListTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShoppingListType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/splitShipmentMethodString")
	public ResponseEntity<Map<String, Object>> splitShipmentMethodString(HttpSession session, @RequestParam(value="shippingMethodString", required=false) String shippingMethodString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shippingMethodString",shippingMethodString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("splitShipmentMethodString", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingListInterface")
	public ResponseEntity<Map<String, Object>> shoppingListInterface(HttpSession session, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="parentShoppingListId", required=false) String parentShoppingListId, @RequestParam(value="productPromoCodeId", required=false) String productPromoCodeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="isActive", required=false) String isActive, @RequestParam(value="shoppingListTypeId", required=false) String shoppingListTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="shippingMethodString", required=false) String shippingMethodString, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="currencyUom", required=false) String currencyUom, @RequestParam(value="lastOrderedDate", required=false) Timestamp lastOrderedDate, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="listName", required=false) String listName, @RequestParam(value="lastAdminModified", required=false) Timestamp lastAdminModified, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="visitorId", required=false) String visitorId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("parentShoppingListId",parentShoppingListId);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("description",description);
		paramMap.put("isActive",isActive);
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("shippingMethodString",shippingMethodString);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("lastOrderedDate",lastOrderedDate);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("listName",listName);
		paramMap.put("lastAdminModified",lastAdminModified);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("visitorId",visitorId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("shoppingListInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeShoppingList")
	public ResponseEntity<Map<String, Object>> removeShoppingList(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeShoppingList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/makeShoppingListFromOrder")
	public ResponseEntity<Map<String, Object>> makeShoppingListFromOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="intervalNumber", required=false) Integer intervalNumber, @RequestParam(value="startDateTime", required=false) Timestamp startDateTime, @RequestParam(value="shoppingListId", required=false) String shoppingListId, @RequestParam(value="endDateTime", required=false) Timestamp endDateTime, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="shoppingListTypeId", required=false) String shoppingListTypeId, @RequestParam(value="frequency", required=false) Integer frequency) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("intervalNumber",intervalNumber);
		paramMap.put("startDateTime",startDateTime);
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("endDateTime",endDateTime);
		paramMap.put("partyId",partyId);
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("frequency",frequency);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("makeShoppingListFromOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShoppingListType")
	public ResponseEntity<Map<String, Object>> updateShoppingListType(HttpSession session, @RequestParam(value="shoppingListTypeId") String shoppingListTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShoppingListType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShoppingListItemSurvey")
	public ResponseEntity<Map<String, Object>> deleteShoppingListItemSurvey(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="shoppingListItemSeqId") String shoppingListItemSeqId, @RequestParam(value="surveyResponseId") String surveyResponseId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("shoppingListItemSeqId",shoppingListItemSeqId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShoppingListItemSurvey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingList")
	public ResponseEntity<Map<String, Object>> createShoppingList(HttpSession session, @RequestParam(value="intervalNumber", required=false) Integer intervalNumber, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="parentShoppingListId", required=false) String parentShoppingListId, @RequestParam(value="productPromoCodeId", required=false) String productPromoCodeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="endDateTime", required=false) Timestamp endDateTime, @RequestParam(value="isActive", required=false) String isActive, @RequestParam(value="shoppingListTypeId", required=false) String shoppingListTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="frequency", required=false) Integer frequency, @RequestParam(value="shippingMethodString", required=false) String shippingMethodString, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="startDateTime", required=false) Timestamp startDateTime, @RequestParam(value="currencyUom", required=false) String currencyUom, @RequestParam(value="lastOrderedDate", required=false) Timestamp lastOrderedDate, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="listName", required=false) String listName, @RequestParam(value="lastAdminModified", required=false) Timestamp lastAdminModified, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="visitorId", required=false) String visitorId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("intervalNumber",intervalNumber);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("parentShoppingListId",parentShoppingListId);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("description",description);
		paramMap.put("endDateTime",endDateTime);
		paramMap.put("isActive",isActive);
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("frequency",frequency);
		paramMap.put("shippingMethodString",shippingMethodString);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("startDateTime",startDateTime);
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("lastOrderedDate",lastOrderedDate);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("listName",listName);
		paramMap.put("lastAdminModified",lastAdminModified);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("visitorId",visitorId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShoppingList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingListRecurrence")
	public ResponseEntity<Map<String, Object>> createShoppingListRecurrence(HttpSession session, @RequestParam(value="intervalNumber", required=false) Integer intervalNumber, @RequestParam(value="startDateTime", required=false) Timestamp startDateTime, @RequestParam(value="endDateTime", required=false) Timestamp endDateTime, @RequestParam(value="frequency", required=false) Integer frequency) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("intervalNumber",intervalNumber);
		paramMap.put("startDateTime",startDateTime);
		paramMap.put("endDateTime",endDateTime);
		paramMap.put("frequency",frequency);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShoppingListRecurrence", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/runShoppingListAutoReorder")
	public ResponseEntity<Map<String, Object>> runShoppingListAutoReorder(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("runShoppingListAutoReorder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/autoDeleteAutoSaveShoppingList")
	public ResponseEntity<Map<String, Object>> autoDeleteAutoSaveShoppingList(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoDeleteAutoSaveShoppingList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeShoppingListItem")
	public ResponseEntity<Map<String, Object>> removeShoppingListItem(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="shoppingListItemSeqId") String shoppingListItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("shoppingListItemSeqId",shoppingListItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeShoppingListItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addSuggestionsToShoppingList")
	public ResponseEntity<Map<String, Object>> addSuggestionsToShoppingList(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addSuggestionsToShoppingList", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calculateShoppingListDeepTotalPrice")
	public ResponseEntity<Map<String, Object>> calculateShoppingListDeepTotalPrice(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="autoUserLogin", required=false) org.apache.ofbiz.entity.GenericValue autoUserLogin, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="productStoreGroupId", required=false) String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("autoUserLogin",autoUserLogin);
		paramMap.put("quantity",quantity);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateShoppingListDeepTotalPrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingListItem")
	public ResponseEntity<Map<String, Object>> createShoppingListItem(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="quantityPurchased", required=false) BigDecimal quantityPurchased, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="modifiedPrice", required=false) BigDecimal modifiedPrice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("quantityPurchased",quantityPurchased);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("modifiedPrice",modifiedPrice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShoppingListItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addDistinctShoppingListItem")
	public ResponseEntity<Map<String, Object>> addDistinctShoppingListItem(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="quantityPurchased", required=false) BigDecimal quantityPurchased, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="modifiedPrice", required=false) BigDecimal modifiedPrice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("quantityPurchased",quantityPurchased);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("modifiedPrice",modifiedPrice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addDistinctShoppingListItem", paramMap);
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
