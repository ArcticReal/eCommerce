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
@RequestMapping("/service/orders")
public class OrderServicesController{

	@RequestMapping(method = RequestMethod.POST, value = "/setShippingInstructions")
	public ResponseEntity<Object> setShippingInstructions(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="shippingInstructions", required=false) String shippingInstructions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("shippingInstructions",shippingInstructions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setShippingInstructions", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteOrderItemShipGroup")
	public ResponseEntity<Object> deleteOrderItemShipGroup(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteOrderItemShipGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelAllBackOrders")
	public ResponseEntity<Object> cancelAllBackOrders(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelAllBackOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrderItem")
	public ResponseEntity<Object> cancelOrderItem(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="itemReasonMap", required=false) Map itemReasonMap, @RequestParam(value="itemCommentMap", required=false) Map itemCommentMap, @RequestParam(value="itemQtyMap", required=false) Map itemQtyMap, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("itemReasonMap",itemReasonMap);
		paramMap.put("itemCommentMap",itemCommentMap);
		paramMap.put("itemQtyMap",itemQtyMap);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelOrderItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/expireOrderContent")
	public ResponseEntity<Object> expireOrderContent(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="orderId") String orderId, @RequestParam(value="contentId") String contentId, @RequestParam(value="orderContentTypeId") String orderContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("orderId",orderId);
		paramMap.put("contentId",contentId);
		paramMap.put("orderContentTypeId",orderContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireOrderContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getOrderItemShipGroupEstimatedShipDate")
	public ResponseEntity<Object> getOrderItemShipGroupEstimatedShipDate(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOrderItemShipGroupEstimatedShipDate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderItemShipGroupAssoc")
	public ResponseEntity<Object> updateOrderItemShipGroupAssoc(HttpSession session, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="totalQuantity", required=false) BigDecimal totalQuantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="rowCount", required=false) Integer rowCount, @RequestParam(value="rowNumber", required=false) Integer rowNumber, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("totalQuantity",totalQuantity);
		paramMap.put("orderId",orderId);
		paramMap.put("rowCount",rowCount);
		paramMap.put("rowNumber",rowNumber);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderItemShipGroupAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderAdjustmentTypeAttr")
	public ResponseEntity<Object> createOrderAdjustmentTypeAttr(HttpSession session, @RequestParam(value="orderAdjustmentTypeId") String orderAdjustmentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderAdjustmentTypeId",orderAdjustmentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderAdjustmentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/recalcShippingTotal")
	public ResponseEntity<Object> recalcShippingTotal(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("recalcShippingTotal", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderPaymentPreference")
	public ResponseEntity<Object> createOrderPaymentPreference(HttpSession session, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="track2", required=false) String track2, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="manualRefNum", required=false) String manualRefNum, @RequestParam(value="swipedFlag", required=false) String swipedFlag, @RequestParam(value="manualAuthCode", required=false) String manualAuthCode, @RequestParam(value="securityCode", required=false) String securityCode, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="processAttempt", required=false) Long processAttempt, @RequestParam(value="needsNsfRetry", required=false) String needsNsfRetry, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="billingPostalCode", required=false) String billingPostalCode, @RequestParam(value="overflowFlag", required=false) String overflowFlag, @RequestParam(value="productPricePurposeId", required=false) String productPricePurposeId, @RequestParam(value="maxAmount", required=false) BigDecimal maxAmount, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="presentFlag", required=false) String presentFlag) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("track2",track2);
		paramMap.put("orderId",orderId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("manualRefNum",manualRefNum);
		paramMap.put("swipedFlag",swipedFlag);
		paramMap.put("manualAuthCode",manualAuthCode);
		paramMap.put("securityCode",securityCode);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("processAttempt",processAttempt);
		paramMap.put("needsNsfRetry",needsNsfRetry);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("billingPostalCode",billingPostalCode);
		paramMap.put("overflowFlag",overflowFlag);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("maxAmount",maxAmount);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("presentFlag",presentFlag);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderPaymentPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrderItemNoActions")
	public ResponseEntity<Object> cancelOrderItemNoActions(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelOrderItemNoActions", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderFromShoppingCart")
	public ResponseEntity<Object> createOrderFromShoppingCart(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderFromShoppingCart", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderShipment")
	public ResponseEntity<Object> createOrderShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderStatusFromReceipt")
	public ResponseEntity<Object> updateOrderStatusFromReceipt(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderStatusFromReceipt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setAllowOrderSplit")
	public ResponseEntity<Object> setAllowOrderSplit(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setAllowOrderSplit", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massQuickShipOrders")
	public ResponseEntity<Object> massQuickShipOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massQuickShipOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderItemChange")
	public ResponseEntity<Object> createOrderItemChange(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="changeTypeEnumId") String changeTypeEnumId, @RequestParam(value="orderId") String orderId, @RequestParam(value="unitPrice", required=false) BigDecimal unitPrice, @RequestParam(value="changeUserLogin", required=false) String changeUserLogin, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="changeComments", required=false) String changeComments, @RequestParam(value="itemDescription", required=false) String itemDescription, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity, @RequestParam(value="changeDatetime", required=false) Timestamp changeDatetime) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("changeTypeEnumId",changeTypeEnumId);
		paramMap.put("orderId",orderId);
		paramMap.put("unitPrice",unitPrice);
		paramMap.put("changeUserLogin",changeUserLogin);
		paramMap.put("quantity",quantity);
		paramMap.put("changeComments",changeComments);
		paramMap.put("itemDescription",itemDescription);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("changeDatetime",changeDatetime);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderItemChange", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderNote")
	public ResponseEntity<Object> updateOrderNote(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="internalNote") String internalNote, @RequestParam(value="noteId") String noteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("internalNote",internalNote);
		paramMap.put("noteId",noteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getNextOrderId")
	public ResponseEntity<Object> getNextOrderId(HttpSession session, @RequestParam(value="currencyUom") String currencyUom, @RequestParam(value="orderTypeId") String orderTypeId, @RequestParam(value="orderAdjustments") List orderAdjustments, @RequestParam(value="partyId") String partyId, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="orderItemGroups", required=false) List orderItemGroups, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="billToCustomerPartyId", required=false) String billToCustomerPartyId, @RequestParam(value="orderAttributes", required=false) List orderAttributes, @RequestParam(value="orderProductPromoUses", required=false) List orderProductPromoUses, @RequestParam(value="shipFromVendorPartyId", required=false) String shipFromVendorPartyId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="orderItemContactMechs", required=false) List orderItemContactMechs, @RequestParam(value="orderProductPromoCodes", required=false) Set orderProductPromoCodes, @RequestParam(value="orderItemSurveyResponses", required=false) List orderItemSurveyResponses, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="trackingCodeOrders", required=false) List trackingCodeOrders, @RequestParam(value="orderInternalNotes", required=false) List orderInternalNotes, @RequestParam(value="orderItemShipGroupInfo", required=false) List orderItemShipGroupInfo, @RequestParam(value="orderContactMechs", required=false) List orderContactMechs, @RequestParam(value="workEfforts", required=false) List workEfforts, @RequestParam(value="shippingAmount", required=false) BigDecimal shippingAmount, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="supplierAgentPartyId", required=false) String supplierAgentPartyId, @RequestParam(value="orderPaymentInfo", required=false) List orderPaymentInfo, @RequestParam(value="originOrderId", required=false) String originOrderId, @RequestParam(value="orderName", required=false) String orderName, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="orderItemAssociations", required=false) List orderItemAssociations, @RequestParam(value="billFromVendorPartyId", required=false) String billFromVendorPartyId, @RequestParam(value="grandTotal", required=false) BigDecimal grandTotal, @RequestParam(value="distributorId", required=false) String distributorId, @RequestParam(value="autoOrderShoppingListId", required=false) String autoOrderShoppingListId, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="endUserCustomerPartyId", required=false) String endUserCustomerPartyId, @RequestParam(value="affiliateId", required=false) String affiliateId, @RequestParam(value="orderTerms", required=false) List orderTerms, @RequestParam(value="placingCustomerPartyId", required=false) String placingCustomerPartyId, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="firstAttemptOrderId", required=false) String firstAttemptOrderId, @RequestParam(value="orderAdditionalPartyRoleMap", required=false) Map orderAdditionalPartyRoleMap, @RequestParam(value="orderItemPriceInfos", required=false) List orderItemPriceInfos, @RequestParam(value="orderNotes", required=false) List orderNotes, @RequestParam(value="shipToCustomerPartyId", required=false) String shipToCustomerPartyId, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="taxAmount", required=false) BigDecimal taxAmount, @RequestParam(value="orderDate", required=false) Timestamp orderDate, @RequestParam(value="internalCode", required=false) String internalCode, @RequestParam(value="orderItemAttributes", required=false) List orderItemAttributes) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("orderAdjustments",orderAdjustments);
		paramMap.put("partyId",partyId);
		paramMap.put("orderItems",orderItems);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("orderItemGroups",orderItemGroups);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("orderId",orderId);
		paramMap.put("billToCustomerPartyId",billToCustomerPartyId);
		paramMap.put("orderAttributes",orderAttributes);
		paramMap.put("orderProductPromoUses",orderProductPromoUses);
		paramMap.put("shipFromVendorPartyId",shipFromVendorPartyId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("terminalId",terminalId);
		paramMap.put("orderItemContactMechs",orderItemContactMechs);
		paramMap.put("orderProductPromoCodes",orderProductPromoCodes);
		paramMap.put("orderItemSurveyResponses",orderItemSurveyResponses);
		paramMap.put("visitId",visitId);
		paramMap.put("trackingCodeOrders",trackingCodeOrders);
		paramMap.put("orderInternalNotes",orderInternalNotes);
		paramMap.put("orderItemShipGroupInfo",orderItemShipGroupInfo);
		paramMap.put("orderContactMechs",orderContactMechs);
		paramMap.put("workEfforts",workEfforts);
		paramMap.put("shippingAmount",shippingAmount);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("supplierAgentPartyId",supplierAgentPartyId);
		paramMap.put("orderPaymentInfo",orderPaymentInfo);
		paramMap.put("originOrderId",originOrderId);
		paramMap.put("orderName",orderName);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("orderItemAssociations",orderItemAssociations);
		paramMap.put("billFromVendorPartyId",billFromVendorPartyId);
		paramMap.put("grandTotal",grandTotal);
		paramMap.put("distributorId",distributorId);
		paramMap.put("autoOrderShoppingListId",autoOrderShoppingListId);
		paramMap.put("externalId",externalId);
		paramMap.put("endUserCustomerPartyId",endUserCustomerPartyId);
		paramMap.put("affiliateId",affiliateId);
		paramMap.put("orderTerms",orderTerms);
		paramMap.put("placingCustomerPartyId",placingCustomerPartyId);
		paramMap.put("transactionId",transactionId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("firstAttemptOrderId",firstAttemptOrderId);
		paramMap.put("orderAdditionalPartyRoleMap",orderAdditionalPartyRoleMap);
		paramMap.put("orderItemPriceInfos",orderItemPriceInfos);
		paramMap.put("orderNotes",orderNotes);
		paramMap.put("shipToCustomerPartyId",shipToCustomerPartyId);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("taxAmount",taxAmount);
		paramMap.put("orderDate",orderDate);
		paramMap.put("internalCode",internalCode);
		paramMap.put("orderItemAttributes",orderItemAttributes);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getNextOrderId", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventOrder")
	public ResponseEntity<Object> createCommunicationEventOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderContactMech")
	public ResponseEntity<Object> updateOrderContactMech(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="orderId") String orderId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="oldContactMechId", required=false) String oldContactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("orderId",orderId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("oldContactMechId",oldContactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/appendOrderItem")
	public ResponseEntity<Object> appendOrderItem(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="itemAttributesMap", required=false) Map itemAttributesMap, @RequestParam(value="overridePrice", required=false) String overridePrice, @RequestParam(value="changeComments", required=false) String changeComments, @RequestParam(value="calcTax", required=false) Boolean calcTax, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="itemDesiredDeliveryDate", required=false) Timestamp itemDesiredDeliveryDate, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId, @RequestParam(value="basePrice", required=false) BigDecimal basePrice, @RequestParam(value="orderItemTypeId", required=false) String orderItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("amount",amount);
		paramMap.put("itemAttributesMap",itemAttributesMap);
		paramMap.put("overridePrice",overridePrice);
		paramMap.put("changeComments",changeComments);
		paramMap.put("calcTax",calcTax);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("itemDesiredDeliveryDate",itemDesiredDeliveryDate);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("basePrice",basePrice);
		paramMap.put("orderItemTypeId",orderItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("appendOrderItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderContent")
	public ResponseEntity<Object> createOrderContent(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="orderId") String orderId, @RequestParam(value="contentId") String contentId, @RequestParam(value="orderContentTypeId") String orderContentTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("orderId",orderId);
		paramMap.put("contentId",contentId);
		paramMap.put("orderContentTypeId",orderContentTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderItemShipGroup")
	public ResponseEntity<Object> updateOrderItemShipGroup(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="giftMessage", required=false) String giftMessage, @RequestParam(value="shippingInstructions", required=false) String shippingInstructions, @RequestParam(value="maySplit", required=false) String maySplit, @RequestParam(value="shipByDate", required=false) Timestamp shipByDate, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="vendorPartyId", required=false) String vendorPartyId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="telecomContactMechId", required=false) String telecomContactMechId, @RequestParam(value="shipAfterDate", required=false) Timestamp shipAfterDate, @RequestParam(value="shipmentMethod", required=false) String shipmentMethod, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="isGift", required=false) String isGift, @RequestParam(value="estimatedShipDate", required=false) Timestamp estimatedShipDate, @RequestParam(value="oldContactMechId", required=false) String oldContactMechId, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="trackingNumber", required=false) String trackingNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("giftMessage",giftMessage);
		paramMap.put("shippingInstructions",shippingInstructions);
		paramMap.put("maySplit",maySplit);
		paramMap.put("shipByDate",shipByDate);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("telecomContactMechId",telecomContactMechId);
		paramMap.put("shipAfterDate",shipAfterDate);
		paramMap.put("shipmentMethod",shipmentMethod);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("isGift",isGift);
		paramMap.put("estimatedShipDate",estimatedShipDate);
		paramMap.put("oldContactMechId",oldContactMechId);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("trackingNumber",trackingNumber);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderItemShipGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderShipment")
	public ResponseEntity<Object> updateOrderShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderAdjustment")
	public ResponseEntity<Object> updateOrderAdjustment(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderAdjustmentId") String orderAdjustmentId, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="oldPercentage", required=false) BigDecimal oldPercentage, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="originalAdjustmentId", required=false) String originalAdjustmentId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="amountAlreadyIncluded", required=false) BigDecimal amountAlreadyIncluded, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="recurringAmount", required=false) BigDecimal recurringAmount, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="orderAdjustmentTypeId", required=false) String orderAdjustmentTypeId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="isManual", required=false) String isManual, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="oldAmountPerQuantity", required=false) BigDecimal oldAmountPerQuantity, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("oldPercentage",oldPercentage);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("originalAdjustmentId",originalAdjustmentId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("amountAlreadyIncluded",amountAlreadyIncluded);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("recurringAmount",recurringAmount);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("orderAdjustmentTypeId",orderAdjustmentTypeId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("isManual",isManual);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("oldAmountPerQuantity",oldAmountPerQuantity);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/resetGrandTotal")
	public ResponseEntity<Object> resetGrandTotal(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resetGrandTotal", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteOrderItemAttribute")
	public ResponseEntity<Object> deleteOrderItemAttribute(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteOrderItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuoteTerm")
	public ResponseEntity<Object> createQuoteTerm(HttpSession session, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="textValue", required=false) String textValue, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="quoteItemSeqId", required=false) String quoteItemSeqId, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) Long termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("textValue",textValue);
		paramMap.put("termDays",termDays);
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuoteTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addOrderRole")
	public ResponseEntity<Object> addOrderRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="orderId") String orderId, @RequestParam(value="partyId") String partyId, @RequestParam(value="removeOld", required=false) Boolean removeOld) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("orderId",orderId);
		paramMap.put("partyId",partyId);
		paramMap.put("removeOld",removeOld);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addOrderRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massCancelOrders")
	public ResponseEntity<Object> massCancelOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massCancelOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderDeliverySchedule")
	public ResponseEntity<Object> updateOrderDeliverySchedule(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="skidsPallets", required=false) Long skidsPallets, @RequestParam(value="unitsPieces", required=false) BigDecimal unitsPieces, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="totalWeight", required=false) BigDecimal totalWeight, @RequestParam(value="totalCubicSize", required=false) BigDecimal totalCubicSize, @RequestParam(value="totalWeightUomId", required=false) String totalWeightUomId, @RequestParam(value="estimatedReadyDate", required=false) Timestamp estimatedReadyDate, @RequestParam(value="totalCubicUomId", required=false) String totalCubicUomId, @RequestParam(value="cartons", required=false) Long cartons) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("skidsPallets",skidsPallets);
		paramMap.put("unitsPieces",unitsPieces);
		paramMap.put("statusId",statusId);
		paramMap.put("totalWeight",totalWeight);
		paramMap.put("totalCubicSize",totalCubicSize);
		paramMap.put("totalWeightUomId",totalWeightUomId);
		paramMap.put("estimatedReadyDate",estimatedReadyDate);
		paramMap.put("totalCubicUomId",totalCubicUomId);
		paramMap.put("cartons",cartons);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderDeliverySchedule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/productAvailabalityByFacility")
	public ResponseEntity<Object> productAvailabalityByFacility(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productAvailabalityByFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeOrderTerm")
	public ResponseEntity<Object> removeOrderTerm(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="termTypeId") String termTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeOrderTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkSupplierRelatedOrderPermission")
	public ResponseEntity<Object> checkSupplierRelatedOrderPermission(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="checkAction", required=false) String checkAction, @RequestParam(value="callingMethodName", required=false) String callingMethodName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("checkAction",checkAction);
		paramMap.put("callingMethodName",callingMethodName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkSupplierRelatedOrderPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkOrderItemStatus")
	public ResponseEntity<Object> checkOrderItemStatus(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkOrderItemStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentFromPreference")
	public ResponseEntity<Object> createPaymentFromPreference(HttpSession session, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="paymentRefNum", required=false) String paymentRefNum, @RequestParam(value="paymentFromId", required=false) String paymentFromId, @RequestParam(value="eventDate", required=false) Timestamp eventDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("comments",comments);
		paramMap.put("paymentRefNum",paymentRefNum);
		paramMap.put("paymentFromId",paymentFromId);
		paramMap.put("eventDate",eventDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentFromPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getOrderShippingAmount")
	public ResponseEntity<Object> getOrderShippingAmount(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOrderShippingAmount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setGiftMessage")
	public ResponseEntity<Object> setGiftMessage(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="giftMessage", required=false) String giftMessage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("giftMessage",giftMessage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setGiftMessage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTrackingNumber")
	public ResponseEntity<Object> updateTrackingNumber(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="trackingNumber") String trackingNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("trackingNumber",trackingNumber);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTrackingNumber", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeOrderContactMech")
	public ResponseEntity<Object> removeOrderContactMech(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="orderId") String orderId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("orderId",orderId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeOrderContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeOrderRole")
	public ResponseEntity<Object> removeOrderRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="orderId") String orderId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("orderId",orderId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeOrderRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdateBillingAddress")
	public ResponseEntity<Object> createUpdateBillingAddress(HttpSession session, @RequestParam(value="billToAttnName", required=false) String billToAttnName, @RequestParam(value="billToPostalCode", required=false) String billToPostalCode, @RequestParam(value="shipToContactMechId", required=false) String shipToContactMechId, @RequestParam(value="setDefaultBilling", required=false) String setDefaultBilling, @RequestParam(value="billToName", required=false) String billToName, @RequestParam(value="billToAddress2", required=false) String billToAddress2, @RequestParam(value="billToCountryGeoId", required=false) String billToCountryGeoId, @RequestParam(value="billToCity", required=false) String billToCity, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="keepAddressBook", required=false) String keepAddressBook, @RequestParam(value="billToAddress1", required=false) String billToAddress1, @RequestParam(value="billToContactMechId", required=false) String billToContactMechId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="useShippingAddressForBilling", required=false) String useShippingAddressForBilling, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="billToStateProvinceGeoId", required=false) String billToStateProvinceGeoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToAttnName",billToAttnName);
		paramMap.put("billToPostalCode",billToPostalCode);
		paramMap.put("shipToContactMechId",shipToContactMechId);
		paramMap.put("setDefaultBilling",setDefaultBilling);
		paramMap.put("billToName",billToName);
		paramMap.put("billToAddress2",billToAddress2);
		paramMap.put("billToCountryGeoId",billToCountryGeoId);
		paramMap.put("billToCity",billToCity);
		paramMap.put("userLogin",userLogin);
		paramMap.put("keepAddressBook",keepAddressBook);
		paramMap.put("billToAddress1",billToAddress1);
		paramMap.put("billToContactMechId",billToContactMechId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("useShippingAddressForBilling",useShippingAddressForBilling);
		paramMap.put("partyId",partyId);
		paramMap.put("billToStateProvinceGeoId",billToStateProvinceGeoId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdateBillingAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setEmptyGrandTotals")
	public ResponseEntity<Object> setEmptyGrandTotals(HttpSession session, @RequestParam(value="forceAll", required=false) Boolean forceAll) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("forceAll",forceAll);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setEmptyGrandTotals", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/invoiceServiceItems")
	public ResponseEntity<Object> invoiceServiceItems(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("invoiceServiceItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuoteTerm")
	public ResponseEntity<Object> updateQuoteTerm(HttpSession session, @RequestParam(value="quoteItemSeqId") String quoteItemSeqId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="textValue", required=false) String textValue, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) Long termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("textValue",textValue);
		paramMap.put("termDays",termDays);
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuoteTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massPickOrders")
	public ResponseEntity<Object> massPickOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massPickOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShoppingListQuantitiesFromOrder")
	public ResponseEntity<Object> updateShoppingListQuantitiesFromOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShoppingListQuantitiesFromOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/MoveItemBetweenShipGroups")
	public ResponseEntity<Object> MoveItemBetweenShipGroups(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="orderId") String orderId, @RequestParam(value="fromGroupIndex") String fromGroupIndex, @RequestParam(value="toGroupIndex") String toGroupIndex) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("fromGroupIndex",fromGroupIndex);
		paramMap.put("toGroupIndex",toGroupIndex);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("MoveItemBetweenShipGroups", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteOrderItemShipGroupAssoc")
	public ResponseEntity<Object> deleteOrderItemShipGroupAssoc(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteOrderItemShipGroupAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesOrderItemFact")
	public ResponseEntity<Object> createSalesOrderItemFact(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesOrderItemFact", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massCancelRemainingPurchaseOrderItems")
	public ResponseEntity<Object> massCancelRemainingPurchaseOrderItems(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massCancelRemainingPurchaseOrderItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getOrderHeaderInformation")
	public ResponseEntity<Object> getOrderHeaderInformation(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOrderHeaderInformation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderHeader")
	public ResponseEntity<Object> updateOrderHeader(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="invoicePerShipment", required=false) String invoicePerShipment, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="orderTypeId", required=false) String orderTypeId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="isViewed", required=false) String isViewed, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="remainingSubTotal", required=false) BigDecimal remainingSubTotal, @RequestParam(value="orderName", required=false) String orderName, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="isRushOrder", required=false) String isRushOrder, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="grandTotal", required=false) BigDecimal grandTotal, @RequestParam(value="autoOrderShoppingListId", required=false) String autoOrderShoppingListId, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="priority", required=false) String priority, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="firstAttemptOrderId", required=false) String firstAttemptOrderId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="currencyUom", required=false) String currencyUom, @RequestParam(value="syncStatusId", required=false) String syncStatusId, @RequestParam(value="pickSheetPrintedDate", required=false) Timestamp pickSheetPrintedDate, @RequestParam(value="needsInventoryIssuance", required=false) String needsInventoryIssuance, @RequestParam(value="orderDate", required=false) Timestamp orderDate, @RequestParam(value="internalCode", required=false) String internalCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("invoicePerShipment",invoicePerShipment);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("terminalId",terminalId);
		paramMap.put("isViewed",isViewed);
		paramMap.put("visitId",visitId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("remainingSubTotal",remainingSubTotal);
		paramMap.put("orderName",orderName);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("isRushOrder",isRushOrder);
		paramMap.put("entryDate",entryDate);
		paramMap.put("grandTotal",grandTotal);
		paramMap.put("autoOrderShoppingListId",autoOrderShoppingListId);
		paramMap.put("externalId",externalId);
		paramMap.put("priority",priority);
		paramMap.put("transactionId",transactionId);
		paramMap.put("firstAttemptOrderId",firstAttemptOrderId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("statusId",statusId);
		paramMap.put("createdBy",createdBy);
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("syncStatusId",syncStatusId);
		paramMap.put("pickSheetPrintedDate",pickSheetPrintedDate);
		paramMap.put("needsInventoryIssuance",needsInventoryIssuance);
		paramMap.put("orderDate",orderDate);
		paramMap.put("internalCode",internalCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderHeader", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkCreateDropShipPurchaseOrders")
	public ResponseEntity<Object> checkCreateDropShipPurchaseOrders(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCreateDropShipPurchaseOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getOrderStatus")
	public ResponseEntity<Object> getOrderStatus(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOrderStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingCartTest")
	public ResponseEntity<Object> shoppingCartTest(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("shoppingCartTest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShippingMethodAndCharges")
	public ResponseEntity<Object> updateShippingMethodAndCharges(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="oldContactMechId") String oldContactMechId, @RequestParam(value="shippingAmount") String shippingAmount, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="shipmentMethodAndAmount") String shipmentMethodAndAmount, @RequestParam(value="orderAdjustmentId") String orderAdjustmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("oldContactMechId",oldContactMechId);
		paramMap.put("shippingAmount",shippingAmount);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("shipmentMethodAndAmount",shipmentMethodAndAmount);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShippingMethodAndCharges", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderItemBilling")
	public ResponseEntity<Object> createOrderItemBilling(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="shipmentReceiptId", required=false) String shipmentReceiptId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("shipmentReceiptId",shipmentReceiptId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderItemBilling", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getOrderedSummaryInformation")
	public ResponseEntity<Object> getOrderedSummaryInformation(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="orderTypeId", required=false) String orderTypeId, @RequestParam(value="monthsToInclude", required=false) Integer monthsToInclude, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("monthsToInclude",monthsToInclude);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOrderedSummaryInformation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createAlsoBoughtProductAssocs")
	public ResponseEntity<Object> createAlsoBoughtProductAssocs(HttpSession session, @RequestParam(value="processAllOrders", required=false) Boolean processAllOrders, @RequestParam(value="orderEntryFromDateTime", required=false) Timestamp orderEntryFromDateTime) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("processAllOrders",processAllOrders);
		paramMap.put("orderEntryFromDateTime",orderEntryFromDateTime);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAlsoBoughtProductAssocs", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendOrderConfirmation")
	public ResponseEntity<Object> sendOrderConfirmation(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="note", required=false) String note, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="screenUri", required=false) String screenUri, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="temporaryAnonymousUserLogin", required=false) org.apache.ofbiz.entity.GenericValue temporaryAnonymousUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("note",note);
		paramMap.put("comments",comments);
		paramMap.put("sendTo",sendTo);
		paramMap.put("screenUri",screenUri);
		paramMap.put("sendCc",sendCc);
		paramMap.put("temporaryAnonymousUserLogin",temporaryAnonymousUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendOrderConfirmation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTestOrderRentalProduct")
	public ResponseEntity<Object> createTestOrderRentalProduct(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTestOrderRentalProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/orderAdjustmentPermissionCheck")
	public ResponseEntity<Object> orderAdjustmentPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("orderAdjustmentPermissionCheck", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdateShippingAddress")
	public ResponseEntity<Object> createUpdateShippingAddress(HttpSession session, @RequestParam(value="shipToCity") String shipToCity, @RequestParam(value="shipToAddress1") String shipToAddress1, @RequestParam(value="shipToPostalCode") String shipToPostalCode, @RequestParam(value="shipToCountryGeoId") String shipToCountryGeoId, @RequestParam(value="shipToStateProvinceGeoId") String shipToStateProvinceGeoId, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="keepAddressBook", required=false) String keepAddressBook, @RequestParam(value="setDefaultShipping", required=false) String setDefaultShipping, @RequestParam(value="shipToAddress2", required=false) String shipToAddress2, @RequestParam(value="billToContactMechId", required=false) String billToContactMechId, @RequestParam(value="shipToContactMechId", required=false) String shipToContactMechId, @RequestParam(value="shipToName", required=false) String shipToName, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="shipToAttnName", required=false) String shipToAttnName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipToCity",shipToCity);
		paramMap.put("shipToAddress1",shipToAddress1);
		paramMap.put("shipToPostalCode",shipToPostalCode);
		paramMap.put("shipToCountryGeoId",shipToCountryGeoId);
		paramMap.put("shipToStateProvinceGeoId",shipToStateProvinceGeoId);
		paramMap.put("userLogin",userLogin);
		paramMap.put("keepAddressBook",keepAddressBook);
		paramMap.put("setDefaultShipping",setDefaultShipping);
		paramMap.put("shipToAddress2",shipToAddress2);
		paramMap.put("billToContactMechId",billToContactMechId);
		paramMap.put("shipToContactMechId",shipToContactMechId);
		paramMap.put("shipToName",shipToName);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipToAttnName",shipToAttnName);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdateShippingAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkOrderIsOnBackOrder")
	public ResponseEntity<Object> checkOrderIsOnBackOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkOrderIsOnBackOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addOrderItemShipGroupAssoc")
	public ResponseEntity<Object> addOrderItemShipGroupAssoc(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="giftMessage", required=false) String giftMessage, @RequestParam(value="shippingInstructions", required=false) String shippingInstructions, @RequestParam(value="maySplit", required=false) String maySplit, @RequestParam(value="shipByDate", required=false) Timestamp shipByDate, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="vendorPartyId", required=false) String vendorPartyId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity, @RequestParam(value="telecomContactMechId", required=false) String telecomContactMechId, @RequestParam(value="shipAfterDate", required=false) Timestamp shipAfterDate, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="isGift", required=false) String isGift, @RequestParam(value="estimatedShipDate", required=false) Timestamp estimatedShipDate, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="trackingNumber", required=false) String trackingNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("quantity",quantity);
		paramMap.put("giftMessage",giftMessage);
		paramMap.put("shippingInstructions",shippingInstructions);
		paramMap.put("maySplit",maySplit);
		paramMap.put("shipByDate",shipByDate);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("telecomContactMechId",telecomContactMechId);
		paramMap.put("shipAfterDate",shipAfterDate);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("isGift",isGift);
		paramMap.put("estimatedShipDate",estimatedShipDate);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("trackingNumber",trackingNumber);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addOrderItemShipGroupAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderTerm")
	public ResponseEntity<Object> createOrderTerm(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="textValue", required=false) String textValue, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) BigDecimal termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("textValue",textValue);
		paramMap.put("termDays",termDays);
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderDeliverySchedule")
	public ResponseEntity<Object> createOrderDeliverySchedule(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="skidsPallets", required=false) Long skidsPallets, @RequestParam(value="unitsPieces", required=false) BigDecimal unitsPieces, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="totalWeight", required=false) BigDecimal totalWeight, @RequestParam(value="totalCubicSize", required=false) BigDecimal totalCubicSize, @RequestParam(value="totalWeightUomId", required=false) String totalWeightUomId, @RequestParam(value="estimatedReadyDate", required=false) Timestamp estimatedReadyDate, @RequestParam(value="totalCubicUomId", required=false) String totalCubicUomId, @RequestParam(value="cartons", required=false) Long cartons) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("skidsPallets",skidsPallets);
		paramMap.put("unitsPieces",unitsPieces);
		paramMap.put("statusId",statusId);
		paramMap.put("totalWeight",totalWeight);
		paramMap.put("totalCubicSize",totalCubicSize);
		paramMap.put("totalWeightUomId",totalWeightUomId);
		paramMap.put("estimatedReadyDate",estimatedReadyDate);
		paramMap.put("totalCubicUomId",totalCubicUomId);
		paramMap.put("cartons",cartons);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderDeliverySchedule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTestSalesOrderSingle")
	public ResponseEntity<Object> createTestSalesOrderSingle(HttpSession session, @RequestParam(value="shipOrder", required=false) Boolean shipOrder, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="numberOfProductsPerOrder", required=false) Integer numberOfProductsPerOrder, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="salesChannel", required=false) String salesChannel) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipOrder",shipOrder);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("numberOfProductsPerOrder",numberOfProductsPerOrder);
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("salesChannel",salesChannel);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTestSalesOrderSingle", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderAdjustmentTypeAttr")
	public ResponseEntity<Object> updateOrderAdjustmentTypeAttr(HttpSession session, @RequestParam(value="orderAdjustmentTypeId") String orderAdjustmentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderAdjustmentTypeId",orderAdjustmentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderAdjustmentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalesOrderItemFact")
	public ResponseEntity<Object> updateSalesOrderItemFact(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalesOrderItemFact", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderContactMech")
	public ResponseEntity<Object> createOrderContactMech(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="orderId") String orderId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("orderId",orderId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderHeader")
	public ResponseEntity<Object> createOrderHeader(HttpSession session, @RequestParam(value="invoicePerShipment", required=false) String invoicePerShipment, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="orderTypeId", required=false) String orderTypeId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="isViewed", required=false) String isViewed, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="remainingSubTotal", required=false) BigDecimal remainingSubTotal, @RequestParam(value="orderName", required=false) String orderName, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="isRushOrder", required=false) String isRushOrder, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="grandTotal", required=false) BigDecimal grandTotal, @RequestParam(value="autoOrderShoppingListId", required=false) String autoOrderShoppingListId, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="priority", required=false) String priority, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="firstAttemptOrderId", required=false) String firstAttemptOrderId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="currencyUom", required=false) String currencyUom, @RequestParam(value="syncStatusId", required=false) String syncStatusId, @RequestParam(value="pickSheetPrintedDate", required=false) Timestamp pickSheetPrintedDate, @RequestParam(value="needsInventoryIssuance", required=false) String needsInventoryIssuance, @RequestParam(value="orderDate", required=false) Timestamp orderDate, @RequestParam(value="internalCode", required=false) String internalCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoicePerShipment",invoicePerShipment);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("orderId",orderId);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("terminalId",terminalId);
		paramMap.put("isViewed",isViewed);
		paramMap.put("visitId",visitId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("remainingSubTotal",remainingSubTotal);
		paramMap.put("orderName",orderName);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("isRushOrder",isRushOrder);
		paramMap.put("entryDate",entryDate);
		paramMap.put("grandTotal",grandTotal);
		paramMap.put("autoOrderShoppingListId",autoOrderShoppingListId);
		paramMap.put("externalId",externalId);
		paramMap.put("priority",priority);
		paramMap.put("transactionId",transactionId);
		paramMap.put("firstAttemptOrderId",firstAttemptOrderId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("statusId",statusId);
		paramMap.put("createdBy",createdBy);
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("syncStatusId",syncStatusId);
		paramMap.put("pickSheetPrintedDate",pickSheetPrintedDate);
		paramMap.put("needsInventoryIssuance",needsInventoryIssuance);
		paramMap.put("orderDate",orderDate);
		paramMap.put("internalCode",internalCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderHeader", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingCartRemoteTest")
	public ResponseEntity<Object> shoppingCartRemoteTest(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("shoppingCartRemoteTest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderPaymentApplication")
	public ResponseEntity<Object> createOrderPaymentApplication(HttpSession session, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderPaymentApplication", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderItemAttribute")
	public ResponseEntity<Object> updateOrderItemAttribute(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massChangeOrderApproved")
	public ResponseEntity<Object> massChangeOrderApproved(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massChangeOrderApproved", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getOrderItemInvoicedAmountAndQuantity")
	public ResponseEntity<Object> getOrderItemInvoicedAmountAndQuantity(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOrderItemInvoicedAmountAndQuantity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/recalcTaxTotal")
	public ResponseEntity<Object> recalcTaxTotal(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("recalcTaxTotal", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipGroupShipInfo")
	public ResponseEntity<Object> updateShipGroupShipInfo(HttpSession session, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="shipmentMethod", required=false) String shipmentMethod, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="oldContactMechId", required=false) String oldContactMechId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("shipmentMethod",shipmentMethod);
		paramMap.put("orderId",orderId);
		paramMap.put("oldContactMechId",oldContactMechId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipGroupShipInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/countProductQuantityOrdered")
	public ResponseEntity<Object> countProductQuantityOrdered(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("countProductQuantityOrdered", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSimpleNonProductSalesOrder")
	public ResponseEntity<Object> createSimpleNonProductSalesOrder(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="currency") String currency, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="itemMap") Map itemMap, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("currency",currency);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("itemMap",itemMap);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSimpleNonProductSalesOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderNotificationLog")
	public ResponseEntity<Object> createOrderNotificationLog(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="emailType") String emailType, @RequestParam(value="comments", required=false) String comments) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("emailType",emailType);
		paramMap.put("comments",comments);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderNotificationLog", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massCreateFileForOrders")
	public ResponseEntity<Object> massCreateFileForOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList, @RequestParam(value="screenLocation") String screenLocation) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("screenLocation",screenLocation);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massCreateFileForOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/orderSequence_enforced")
	public ResponseEntity<Object> orderSequence_enforced(HttpSession session, @RequestParam(value="currencyUom") String currencyUom, @RequestParam(value="orderTypeId") String orderTypeId, @RequestParam(value="orderAdjustments") List orderAdjustments, @RequestParam(value="partyId") String partyId, @RequestParam(value="partyAcctgPreference") org.apache.ofbiz.entity.GenericValue partyAcctgPreference, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="orderItemGroups", required=false) List orderItemGroups, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="billToCustomerPartyId", required=false) String billToCustomerPartyId, @RequestParam(value="orderAttributes", required=false) List orderAttributes, @RequestParam(value="orderProductPromoUses", required=false) List orderProductPromoUses, @RequestParam(value="shipFromVendorPartyId", required=false) String shipFromVendorPartyId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="orderItemContactMechs", required=false) List orderItemContactMechs, @RequestParam(value="orderProductPromoCodes", required=false) Set orderProductPromoCodes, @RequestParam(value="orderItemSurveyResponses", required=false) List orderItemSurveyResponses, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="trackingCodeOrders", required=false) List trackingCodeOrders, @RequestParam(value="orderInternalNotes", required=false) List orderInternalNotes, @RequestParam(value="orderItemShipGroupInfo", required=false) List orderItemShipGroupInfo, @RequestParam(value="orderContactMechs", required=false) List orderContactMechs, @RequestParam(value="workEfforts", required=false) List workEfforts, @RequestParam(value="shippingAmount", required=false) BigDecimal shippingAmount, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="supplierAgentPartyId", required=false) String supplierAgentPartyId, @RequestParam(value="orderPaymentInfo", required=false) List orderPaymentInfo, @RequestParam(value="originOrderId", required=false) String originOrderId, @RequestParam(value="orderName", required=false) String orderName, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="orderItemAssociations", required=false) List orderItemAssociations, @RequestParam(value="billFromVendorPartyId", required=false) String billFromVendorPartyId, @RequestParam(value="grandTotal", required=false) BigDecimal grandTotal, @RequestParam(value="distributorId", required=false) String distributorId, @RequestParam(value="autoOrderShoppingListId", required=false) String autoOrderShoppingListId, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="endUserCustomerPartyId", required=false) String endUserCustomerPartyId, @RequestParam(value="affiliateId", required=false) String affiliateId, @RequestParam(value="orderTerms", required=false) List orderTerms, @RequestParam(value="placingCustomerPartyId", required=false) String placingCustomerPartyId, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="firstAttemptOrderId", required=false) String firstAttemptOrderId, @RequestParam(value="orderAdditionalPartyRoleMap", required=false) Map orderAdditionalPartyRoleMap, @RequestParam(value="orderItemPriceInfos", required=false) List orderItemPriceInfos, @RequestParam(value="orderNotes", required=false) List orderNotes, @RequestParam(value="shipToCustomerPartyId", required=false) String shipToCustomerPartyId, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="taxAmount", required=false) BigDecimal taxAmount, @RequestParam(value="orderDate", required=false) Timestamp orderDate, @RequestParam(value="internalCode", required=false) String internalCode, @RequestParam(value="orderItemAttributes", required=false) List orderItemAttributes) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("orderAdjustments",orderAdjustments);
		paramMap.put("partyId",partyId);
		paramMap.put("partyAcctgPreference",partyAcctgPreference);
		paramMap.put("orderItems",orderItems);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("orderItemGroups",orderItemGroups);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("billToCustomerPartyId",billToCustomerPartyId);
		paramMap.put("orderAttributes",orderAttributes);
		paramMap.put("orderProductPromoUses",orderProductPromoUses);
		paramMap.put("shipFromVendorPartyId",shipFromVendorPartyId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("terminalId",terminalId);
		paramMap.put("orderItemContactMechs",orderItemContactMechs);
		paramMap.put("orderProductPromoCodes",orderProductPromoCodes);
		paramMap.put("orderItemSurveyResponses",orderItemSurveyResponses);
		paramMap.put("visitId",visitId);
		paramMap.put("trackingCodeOrders",trackingCodeOrders);
		paramMap.put("orderInternalNotes",orderInternalNotes);
		paramMap.put("orderItemShipGroupInfo",orderItemShipGroupInfo);
		paramMap.put("orderContactMechs",orderContactMechs);
		paramMap.put("workEfforts",workEfforts);
		paramMap.put("shippingAmount",shippingAmount);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("supplierAgentPartyId",supplierAgentPartyId);
		paramMap.put("orderPaymentInfo",orderPaymentInfo);
		paramMap.put("originOrderId",originOrderId);
		paramMap.put("orderName",orderName);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("orderItemAssociations",orderItemAssociations);
		paramMap.put("billFromVendorPartyId",billFromVendorPartyId);
		paramMap.put("grandTotal",grandTotal);
		paramMap.put("distributorId",distributorId);
		paramMap.put("autoOrderShoppingListId",autoOrderShoppingListId);
		paramMap.put("externalId",externalId);
		paramMap.put("endUserCustomerPartyId",endUserCustomerPartyId);
		paramMap.put("affiliateId",affiliateId);
		paramMap.put("orderTerms",orderTerms);
		paramMap.put("placingCustomerPartyId",placingCustomerPartyId);
		paramMap.put("transactionId",transactionId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("firstAttemptOrderId",firstAttemptOrderId);
		paramMap.put("orderAdditionalPartyRoleMap",orderAdditionalPartyRoleMap);
		paramMap.put("orderItemPriceInfos",orderItemPriceInfos);
		paramMap.put("orderNotes",orderNotes);
		paramMap.put("shipToCustomerPartyId",shipToCustomerPartyId);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("taxAmount",taxAmount);
		paramMap.put("orderDate",orderDate);
		paramMap.put("internalCode",internalCode);
		paramMap.put("orderItemAttributes",orderItemAttributes);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("orderSequence_enforced", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/autoCancelOrderItems")
	public ResponseEntity<Object> autoCancelOrderItems(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoCancelOrderItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkDigitalItemFulfillment")
	public ResponseEntity<Object> checkDigitalItemFulfillment(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkDigitalItemFulfillment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massRejectOrders")
	public ResponseEntity<Object> massRejectOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massRejectOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/itemFulfillmentInterface")
	public ResponseEntity<Object> itemFulfillmentInterface(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="orderItem") org.apache.ofbiz.entity.GenericValue orderItem, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("orderItem",orderItem);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("itemFulfillmentInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createAlsoBoughtProductAssocsForOrder")
	public ResponseEntity<Object> createAlsoBoughtProductAssocsForOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAlsoBoughtProductAssocsForOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductOrderItem")
	public ResponseEntity<Object> createProductOrderItem(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="engagementItemSeqId") String engagementItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="engagementId") String engagementId, @RequestParam(value="productId", required=false) String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("engagementItemSeqId",engagementItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("engagementId",engagementId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductOrderItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderAdjustmentBilling")
	public ResponseEntity<Object> createOrderAdjustmentBilling(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="orderAdjustmentId") String orderAdjustmentId, @RequestParam(value="amount", required=false) BigDecimal amount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("amount",amount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderAdjustmentBilling", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderTerm")
	public ResponseEntity<Object> updateOrderTerm(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="textValue", required=false) String textValue, @RequestParam(value="termDays", required=false) Long termDays, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termValue", required=false) BigDecimal termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("textValue",textValue);
		paramMap.put("termDays",termDays);
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setUnitPriceAsLastPrice")
	public ResponseEntity<Object> setUnitPriceAsLastPrice(HttpSession session, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="unitCost", required=false) String unitCost, @RequestParam(value="itemPriceMap", required=false) Map itemPriceMap, @RequestParam(value="overridePriceMap", required=false) Map overridePriceMap, @RequestParam(value="orderCurrencyUnitPrice", required=false) String orderCurrencyUnitPrice, @RequestParam(value="orderTypeId", required=false) String orderTypeId, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="orderItems", required=false) List orderItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("itemPriceMap",itemPriceMap);
		paramMap.put("overridePriceMap",overridePriceMap);
		paramMap.put("orderCurrencyUnitPrice",orderCurrencyUnitPrice);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("orderItems",orderItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setUnitPriceAsLastPrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderNote")
	public ResponseEntity<Object> createOrderNote(HttpSession session, @RequestParam(value="note") String note, @RequestParam(value="orderId") String orderId, @RequestParam(value="internalNote") String internalNote, @RequestParam(value="noteName", required=false) String noteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("note",note);
		paramMap.put("orderId",orderId);
		paramMap.put("internalNote",internalNote);
		paramMap.put("noteName",noteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelRemainingPurchaseOrderItems")
	public ResponseEntity<Object> cancelRemainingPurchaseOrderItems(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelRemainingPurchaseOrderItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processOrderPayments")
	public ResponseEntity<Object> processOrderPayments(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processOrderPayments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdateCreditCard")
	public ResponseEntity<Object> createUpdateCreditCard(HttpSession session, @RequestParam(value="expMonth") String expMonth, @RequestParam(value="expYear") String expYear, @RequestParam(value="lastNameOnCard") String lastNameOnCard, @RequestParam(value="firstNameOnCard") String firstNameOnCard, @RequestParam(value="cardType") String cardType, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="titleOnCard", required=false) String titleOnCard, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="middleNameOnCard", required=false) String middleNameOnCard, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="suffixOnCard", required=false) String suffixOnCard, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="companyNameOnCard", required=false) String companyNameOnCard) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("expMonth",expMonth);
		paramMap.put("expYear",expYear);
		paramMap.put("lastNameOnCard",lastNameOnCard);
		paramMap.put("firstNameOnCard",firstNameOnCard);
		paramMap.put("cardType",cardType);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("titleOnCard",titleOnCard);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("middleNameOnCard",middleNameOnCard);
		paramMap.put("partyId",partyId);
		paramMap.put("suffixOnCard",suffixOnCard);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("companyNameOnCard",companyNameOnCard);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdateCreditCard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/changeOrderItemStatus")
	public ResponseEntity<Object> changeOrderItemStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="changeReason", required=false) String changeReason, @RequestParam(value="statusDateTime", required=false) Timestamp statusDateTime, @RequestParam(value="fromStatusId", required=false) String fromStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("changeReason",changeReason);
		paramMap.put("statusDateTime",statusDateTime);
		paramMap.put("fromStatusId",fromStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("changeOrderItemStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderItemShipGroup")
	public ResponseEntity<Object> createOrderItemShipGroup(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="giftMessage", required=false) String giftMessage, @RequestParam(value="shippingInstructions", required=false) String shippingInstructions, @RequestParam(value="maySplit", required=false) String maySplit, @RequestParam(value="shipByDate", required=false) Timestamp shipByDate, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="vendorPartyId", required=false) String vendorPartyId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="telecomContactMechId", required=false) String telecomContactMechId, @RequestParam(value="shipAfterDate", required=false) Timestamp shipAfterDate, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="isGift", required=false) String isGift, @RequestParam(value="estimatedShipDate", required=false) Timestamp estimatedShipDate, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="trackingNumber", required=false) String trackingNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("giftMessage",giftMessage);
		paramMap.put("shippingInstructions",shippingInstructions);
		paramMap.put("maySplit",maySplit);
		paramMap.put("shipByDate",shipByDate);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("telecomContactMechId",telecomContactMechId);
		paramMap.put("shipAfterDate",shipAfterDate);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("isGift",isGift);
		paramMap.put("estimatedShipDate",estimatedShipDate);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("trackingNumber",trackingNumber);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderItemShipGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendOrderBackorderNotification")
	public ResponseEntity<Object> sendOrderBackorderNotification(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="note", required=false) String note, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="screenUri", required=false) String screenUri, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="temporaryAnonymousUserLogin", required=false) org.apache.ofbiz.entity.GenericValue temporaryAnonymousUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("note",note);
		paramMap.put("comments",comments);
		paramMap.put("sendTo",sendTo);
		paramMap.put("screenUri",screenUri);
		paramMap.put("sendCc",sendCc);
		paramMap.put("temporaryAnonymousUserLogin",temporaryAnonymousUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendOrderBackorderNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendOrderCompleteNotification")
	public ResponseEntity<Object> sendOrderCompleteNotification(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="note", required=false) String note, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="screenUri", required=false) String screenUri, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="temporaryAnonymousUserLogin", required=false) org.apache.ofbiz.entity.GenericValue temporaryAnonymousUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("note",note);
		paramMap.put("comments",comments);
		paramMap.put("sendTo",sendTo);
		paramMap.put("screenUri",screenUri);
		paramMap.put("sendCc",sendCc);
		paramMap.put("temporaryAnonymousUserLogin",temporaryAnonymousUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendOrderCompleteNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massOrderChangeInterface")
	public ResponseEntity<Object> massOrderChangeInterface(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massOrderChangeInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteQuoteTerm")
	public ResponseEntity<Object> deleteQuoteTerm(HttpSession session, @RequestParam(value="quoteItemSeqId") String quoteItemSeqId, @RequestParam(value="termTypeId") String termTypeId, @RequestParam(value="quoteId") String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteItemSeqId",quoteItemSeqId);
		paramMap.put("termTypeId",termTypeId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteQuoteTerm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductOrderItem")
	public ResponseEntity<Object> updateProductOrderItem(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="engagementItemSeqId") String engagementItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="engagementId") String engagementId, @RequestParam(value="productId", required=false) String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("engagementItemSeqId",engagementItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("engagementId",engagementId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductOrderItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCommunicationEventOrder")
	public ResponseEntity<Object> removeCommunicationEventOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCommunicationEventOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/generateReqsFromCancelledPOItems")
	public ResponseEntity<Object> generateReqsFromCancelledPOItems(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("generateReqsFromCancelledPOItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderItemAttribute")
	public ResponseEntity<Object> createOrderItemAttribute(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderItemGroupOrder")
	public ResponseEntity<Object> createOrderItemGroupOrder(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="groupOrderId") String groupOrderId, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("groupOrderId",groupOrderId);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderItemGroupOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findOrders")
	public ResponseEntity<Object> findOrders(HttpSession session, @RequestParam(value="viewSize", required=false) Integer viewSize, @RequestParam(value="minDate", required=false) String minDate, @RequestParam(value="salesChannelEnumId", required=false) List salesChannelEnumId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="useEntryDate", required=false) String useEntryDate, @RequestParam(value="softIdentifier", required=false) String softIdentifier, @RequestParam(value="orderTypeId", required=false) List orderTypeId, @RequestParam(value="filterInventoryProblems", required=false) String filterInventoryProblems, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="paymentStatusId", required=false) String paymentStatusId, @RequestParam(value="isViewed", required=false) String isViewed, @RequestParam(value="shipmentMethod", required=false) String shipmentMethod, @RequestParam(value="hasBackOrders", required=false) String hasBackOrders, @RequestParam(value="showAll", required=false) String showAll, @RequestParam(value="filterPOsWithRejectedItems", required=false) String filterPOsWithRejectedItems, @RequestParam(value="productStoreId", required=false) List productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="orderName", required=false) String orderName, @RequestParam(value="gatewayScoreResult", required=false) String gatewayScoreResult, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="roleTypeId", required=false) List roleTypeId, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="goodIdentificationTypeId", required=false) String goodIdentificationTypeId, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="viewIndex", required=false) Integer viewIndex, @RequestParam(value="budgetId", required=false) String budgetId, @RequestParam(value="includeCountry", required=false) String includeCountry, @RequestParam(value="accountNumber", required=false) String accountNumber, @RequestParam(value="correspondingPoId", required=false) String correspondingPoId, @RequestParam(value="goodIdentificationIdValue", required=false) String goodIdentificationIdValue, @RequestParam(value="filterPartiallyReceivedPOs", required=false) String filterPartiallyReceivedPOs, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="quoteId", required=false) String quoteId, @RequestParam(value="filterPOsOpenPastTheirETA", required=false) String filterPOsOpenPastTheirETA, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="orderStatusId", required=false) List orderStatusId, @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="gatewayAvsResult", required=false) String gatewayAvsResult, @RequestParam(value="orderWebSiteId", required=false) List orderWebSiteId, @RequestParam(value="maxDate", required=false) String maxDate, @RequestParam(value="subscriptionId", required=false) String subscriptionId, @RequestParam(value="internalCode", required=false) String internalCode, @RequestParam(value="cardNumber", required=false) String cardNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("viewSize",viewSize);
		paramMap.put("minDate",minDate);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("orderId",orderId);
		paramMap.put("useEntryDate",useEntryDate);
		paramMap.put("softIdentifier",softIdentifier);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("filterInventoryProblems",filterInventoryProblems);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("terminalId",terminalId);
		paramMap.put("paymentStatusId",paymentStatusId);
		paramMap.put("isViewed",isViewed);
		paramMap.put("shipmentMethod",shipmentMethod);
		paramMap.put("hasBackOrders",hasBackOrders);
		paramMap.put("showAll",showAll);
		paramMap.put("filterPOsWithRejectedItems",filterPOsWithRejectedItems);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("orderName",orderName);
		paramMap.put("gatewayScoreResult",gatewayScoreResult);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("productId",productId);
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("externalId",externalId);
		paramMap.put("viewIndex",viewIndex);
		paramMap.put("budgetId",budgetId);
		paramMap.put("includeCountry",includeCountry);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("correspondingPoId",correspondingPoId);
		paramMap.put("goodIdentificationIdValue",goodIdentificationIdValue);
		paramMap.put("filterPartiallyReceivedPOs",filterPartiallyReceivedPOs);
		paramMap.put("transactionId",transactionId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("filterPOsOpenPastTheirETA",filterPOsOpenPastTheirETA);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("orderStatusId",orderStatusId);
		paramMap.put("createdBy",createdBy);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("gatewayAvsResult",gatewayAvsResult);
		paramMap.put("orderWebSiteId",orderWebSiteId);
		paramMap.put("maxDate",maxDate);
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("internalCode",internalCode);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteOrderShipment")
	public ResponseEntity<Object> deleteOrderShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteOrderShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/fulfillDigitalItems")
	public ResponseEntity<Object> fulfillDigitalItems(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItems") java.util.List orderItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItems",orderItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("fulfillDigitalItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massHoldOrders")
	public ResponseEntity<Object> massHoldOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massHoldOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/completePurchaseOrder")
	public ResponseEntity<Object> completePurchaseOrder(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("completePurchaseOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/loadCartForUpdate")
	public ResponseEntity<Object> loadCartForUpdate(HttpSession session, @RequestParam(value="userLogin") String userLogin, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin",userLogin);
		paramMap.put("orderId",orderId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("loadCartForUpdate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderAdjustment")
	public ResponseEntity<Object> createOrderAdjustment(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderAdjustmentTypeId") String orderAdjustmentTypeId, @RequestParam(value="customerReferenceId", required=false) String customerReferenceId, @RequestParam(value="correspondingProductId", required=false) String correspondingProductId, @RequestParam(value="includeInShipping", required=false) String includeInShipping, @RequestParam(value="description", required=false) String description, @RequestParam(value="exemptAmount", required=false) BigDecimal exemptAmount, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="oldPercentage", required=false) BigDecimal oldPercentage, @RequestParam(value="primaryGeoId", required=false) String primaryGeoId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="originalAdjustmentId", required=false) String originalAdjustmentId, @RequestParam(value="secondaryGeoId", required=false) String secondaryGeoId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="amountAlreadyIncluded", required=false) BigDecimal amountAlreadyIncluded, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="recurringAmount", required=false) BigDecimal recurringAmount, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="productPromoRuleId", required=false) String productPromoRuleId, @RequestParam(value="isManual", required=false) String isManual, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="taxAuthorityRateSeqId", required=false) String taxAuthorityRateSeqId, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="oldAmountPerQuantity", required=false) BigDecimal oldAmountPerQuantity, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="includeInTax", required=false) String includeInTax, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="productPromoActionSeqId", required=false) String productPromoActionSeqId, @RequestParam(value="sourcePercentage", required=false) BigDecimal sourcePercentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderAdjustmentTypeId",orderAdjustmentTypeId);
		paramMap.put("customerReferenceId",customerReferenceId);
		paramMap.put("correspondingProductId",correspondingProductId);
		paramMap.put("includeInShipping",includeInShipping);
		paramMap.put("description",description);
		paramMap.put("exemptAmount",exemptAmount);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("oldPercentage",oldPercentage);
		paramMap.put("primaryGeoId",primaryGeoId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("originalAdjustmentId",originalAdjustmentId);
		paramMap.put("secondaryGeoId",secondaryGeoId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("amountAlreadyIncluded",amountAlreadyIncluded);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("recurringAmount",recurringAmount);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("isManual",isManual);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("oldAmountPerQuantity",oldAmountPerQuantity);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("includeInTax",includeInTax);
		paramMap.put("createdDate",createdDate);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("sourcePercentage",sourcePercentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderAdjustment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendProcessNotification")
	public ResponseEntity<Object> sendProcessNotification(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="adminEmailList") String adminEmailList, @RequestParam(value="assignedRoleTypeId", required=false) String assignedRoleTypeId, @RequestParam(value="assignedPartyId", required=false) String assignedPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("adminEmailList",adminEmailList);
		paramMap.put("assignedRoleTypeId",assignedRoleTypeId);
		paramMap.put("assignedPartyId",assignedPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendProcessNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addOrderItemShipGroup")
	public ResponseEntity<Object> addOrderItemShipGroup(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="giftMessage", required=false) String giftMessage, @RequestParam(value="shippingInstructions", required=false) String shippingInstructions, @RequestParam(value="maySplit", required=false) String maySplit, @RequestParam(value="shipByDate", required=false) Timestamp shipByDate, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="estimatedDeliveryDate", required=false) Timestamp estimatedDeliveryDate, @RequestParam(value="vendorPartyId", required=false) String vendorPartyId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="telecomContactMechId", required=false) String telecomContactMechId, @RequestParam(value="shipAfterDate", required=false) Timestamp shipAfterDate, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="isGift", required=false) String isGift, @RequestParam(value="estimatedShipDate", required=false) Timestamp estimatedShipDate, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="trackingNumber", required=false) String trackingNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("giftMessage",giftMessage);
		paramMap.put("shippingInstructions",shippingInstructions);
		paramMap.put("maySplit",maySplit);
		paramMap.put("shipByDate",shipByDate);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("estimatedDeliveryDate",estimatedDeliveryDate);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("telecomContactMechId",telecomContactMechId);
		paramMap.put("shipAfterDate",shipAfterDate);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("isGift",isGift);
		paramMap.put("estimatedShipDate",estimatedShipDate);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("trackingNumber",trackingNumber);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addOrderItemShipGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderPaymentPreference")
	public ResponseEntity<Object> updateOrderPaymentPreference(HttpSession session, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="track2", required=false) String track2, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="manualRefNum", required=false) String manualRefNum, @RequestParam(value="swipedFlag", required=false) String swipedFlag, @RequestParam(value="manualAuthCode", required=false) String manualAuthCode, @RequestParam(value="securityCode", required=false) String securityCode, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="checkOutPaymentId", required=false) String checkOutPaymentId, @RequestParam(value="processAttempt", required=false) Long processAttempt, @RequestParam(value="needsNsfRetry", required=false) String needsNsfRetry, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="billingPostalCode", required=false) String billingPostalCode, @RequestParam(value="overflowFlag", required=false) String overflowFlag, @RequestParam(value="productPricePurposeId", required=false) String productPricePurposeId, @RequestParam(value="maxAmount", required=false) BigDecimal maxAmount, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="presentFlag", required=false) String presentFlag) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("track2",track2);
		paramMap.put("orderId",orderId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("manualRefNum",manualRefNum);
		paramMap.put("swipedFlag",swipedFlag);
		paramMap.put("manualAuthCode",manualAuthCode);
		paramMap.put("securityCode",securityCode);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("checkOutPaymentId",checkOutPaymentId);
		paramMap.put("processAttempt",processAttempt);
		paramMap.put("needsNsfRetry",needsNsfRetry);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("billingPostalCode",billingPostalCode);
		paramMap.put("overflowFlag",overflowFlag);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("maxAmount",maxAmount);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("presentFlag",presentFlag);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderPaymentPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendOrderChangeNotification")
	public ResponseEntity<Object> sendOrderChangeNotification(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="note", required=false) String note, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="screenUri", required=false) String screenUri, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="temporaryAnonymousUserLogin", required=false) org.apache.ofbiz.entity.GenericValue temporaryAnonymousUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("note",note);
		paramMap.put("comments",comments);
		paramMap.put("sendTo",sendTo);
		paramMap.put("screenUri",screenUri);
		paramMap.put("sendCc",sendCc);
		paramMap.put("temporaryAnonymousUserLogin",temporaryAnonymousUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendOrderChangeNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductOrderItem")
	public ResponseEntity<Object> deleteProductOrderItem(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="engagementItemSeqId") String engagementItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="engagementId") String engagementId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("engagementItemSeqId",engagementItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("engagementId",engagementId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductOrderItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/recreateOrderAdjustments")
	public ResponseEntity<Object> recreateOrderAdjustments(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("recreateOrderAdjustments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/changeOrderStatus")
	public ResponseEntity<Object> changeOrderStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="orderId") String orderId, @RequestParam(value="setItemStatus", required=false) String setItemStatus, @RequestParam(value="changeReason", required=false) String changeReason) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("orderId",orderId);
		paramMap.put("setItemStatus",setItemStatus);
		paramMap.put("changeReason",changeReason);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("changeOrderStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalesOrderItemFact")
	public ResponseEntity<Object> deleteSalesOrderItemFact(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalesOrderItemFact", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/runSubscriptionAutoReorders")
	public ResponseEntity<Object> runSubscriptionAutoReorders(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("runSubscriptionAutoReorders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCreateShoppinCartAndOrder")
	public ResponseEntity<Object> testCreateShoppinCartAndOrder(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testCreateShoppinCartAndOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendOrderDeliveryScheduleNotification")
	public ResponseEntity<Object> sendOrderDeliveryScheduleNotification(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendOrderDeliveryScheduleNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massProcessOrders")
	public ResponseEntity<Object> massProcessOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massProcessOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/changeOrderPaymentStatus")
	public ResponseEntity<Object> changeOrderPaymentStatus(HttpSession session, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId, @RequestParam(value="changeReason", required=false) String changeReason) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("changeReason",changeReason);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("changeOrderPaymentStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderItems")
	public ResponseEntity<Object> updateOrderItems(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="itemPriceMap") Map itemPriceMap, @RequestParam(value="overridePriceMap") Map overridePriceMap, @RequestParam(value="itemQtyMap") Map itemQtyMap, @RequestParam(value="itemReasonMap", required=false) Map itemReasonMap, @RequestParam(value="itemAttributesMap", required=false) Map itemAttributesMap, @RequestParam(value="itemDeliveryDateMap", required=false) Map itemDeliveryDateMap, @RequestParam(value="itemCommentMap", required=false) Map itemCommentMap, @RequestParam(value="orderTypeId", required=false) String orderTypeId, @RequestParam(value="calcTax", required=false) Boolean calcTax, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="itemDescriptionMap", required=false) Map itemDescriptionMap, @RequestParam(value="itemShipDateMap", required=false) Map itemShipDateMap) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("itemPriceMap",itemPriceMap);
		paramMap.put("overridePriceMap",overridePriceMap);
		paramMap.put("itemQtyMap",itemQtyMap);
		paramMap.put("itemReasonMap",itemReasonMap);
		paramMap.put("itemAttributesMap",itemAttributesMap);
		paramMap.put("itemDeliveryDateMap",itemDeliveryDateMap);
		paramMap.put("itemCommentMap",itemCommentMap);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("calcTax",calcTax);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("itemDescriptionMap",itemDescriptionMap);
		paramMap.put("itemShipDateMap",itemShipDateMap);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrderItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/orderNotificationInterface")
	public ResponseEntity<Object> orderNotificationInterface(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="note", required=false) String note, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="screenUri", required=false) String screenUri, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="temporaryAnonymousUserLogin", required=false) org.apache.ofbiz.entity.GenericValue temporaryAnonymousUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("note",note);
		paramMap.put("comments",comments);
		paramMap.put("sendTo",sendTo);
		paramMap.put("screenUri",screenUri);
		paramMap.put("sendCc",sendCc);
		paramMap.put("temporaryAnonymousUserLogin",temporaryAnonymousUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("orderNotificationInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/massPrintOrders")
	public ResponseEntity<Object> massPrintOrders(HttpSession session, @RequestParam(value="orderIdList") List orderIdList, @RequestParam(value="screenLocation") String screenLocation, @RequestParam(value="printerName", required=false) String printerName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderIdList",orderIdList);
		paramMap.put("screenLocation",screenLocation);
		paramMap.put("printerName",printerName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("massPrintOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendOrderPayRetryNotification")
	public ResponseEntity<Object> sendOrderPayRetryNotification(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="note", required=false) String note, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="screenUri", required=false) String screenUri, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="temporaryAnonymousUserLogin", required=false) org.apache.ofbiz.entity.GenericValue temporaryAnonymousUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("note",note);
		paramMap.put("comments",comments);
		paramMap.put("sendTo",sendTo);
		paramMap.put("screenUri",screenUri);
		paramMap.put("sendCc",sendCc);
		paramMap.put("temporaryAnonymousUserLogin",temporaryAnonymousUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendOrderPayRetryNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addPaymentMethodToOrder")
	public ResponseEntity<Object> addPaymentMethodToOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="maxAmount") BigDecimal maxAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("maxAmount",maxAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPaymentMethodToOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteOrderAdjustmentTypeAttr")
	public ResponseEntity<Object> deleteOrderAdjustmentTypeAttr(HttpSession session, @RequestParam(value="orderAdjustmentTypeId") String orderAdjustmentTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderAdjustmentTypeId",orderAdjustmentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteOrderAdjustmentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTestSalesOrders")
	public ResponseEntity<Object> createTestSalesOrders(HttpSession session, @RequestParam(value="shipOrder", required=false) Boolean shipOrder, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="numberOfProductsPerOrder", required=false) Integer numberOfProductsPerOrder, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="numberOfOrders", required=false) Integer numberOfOrders, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="salesChannel", required=false) String salesChannel) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipOrder",shipOrder);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("numberOfProductsPerOrder",numberOfProductsPerOrder);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("numberOfOrders",numberOfOrders);
		paramMap.put("partyId",partyId);
		paramMap.put("salesChannel",salesChannel);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTestSalesOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/saveUpdatedCartToOrder")
	public ResponseEntity<Object> saveUpdatedCartToOrder(HttpSession session, @RequestParam(value="changeMap") Map changeMap, @RequestParam(value="orderId") String orderId, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="calcTax", required=false) Boolean calcTax, @RequestParam(value="deleteItems", required=false) Boolean deleteItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("changeMap",changeMap);
		paramMap.put("orderId",orderId);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("calcTax",calcTax);
		paramMap.put("deleteItems",deleteItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("saveUpdatedCartToOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/callProcessOrderPayments")
	public ResponseEntity<Object> callProcessOrderPayments(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="manualHold", required=false) Boolean manualHold) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("manualHold",manualHold);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("callProcessOrderPayments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/storeOrder")
	public ResponseEntity<Object> storeOrder(HttpSession session, @RequestParam(value="currencyUom") String currencyUom, @RequestParam(value="orderTypeId") String orderTypeId, @RequestParam(value="orderAdjustments") List orderAdjustments, @RequestParam(value="partyId") String partyId, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="orderItemGroups", required=false) List orderItemGroups, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="billToCustomerPartyId", required=false) String billToCustomerPartyId, @RequestParam(value="orderAttributes", required=false) List orderAttributes, @RequestParam(value="orderProductPromoUses", required=false) List orderProductPromoUses, @RequestParam(value="shipFromVendorPartyId", required=false) String shipFromVendorPartyId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="orderItemContactMechs", required=false) List orderItemContactMechs, @RequestParam(value="orderProductPromoCodes", required=false) Set orderProductPromoCodes, @RequestParam(value="orderItemSurveyResponses", required=false) List orderItemSurveyResponses, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="trackingCodeOrders", required=false) List trackingCodeOrders, @RequestParam(value="orderInternalNotes", required=false) List orderInternalNotes, @RequestParam(value="orderItemShipGroupInfo", required=false) List orderItemShipGroupInfo, @RequestParam(value="orderContactMechs", required=false) List orderContactMechs, @RequestParam(value="workEfforts", required=false) List workEfforts, @RequestParam(value="shippingAmount", required=false) BigDecimal shippingAmount, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="supplierAgentPartyId", required=false) String supplierAgentPartyId, @RequestParam(value="orderPaymentInfo", required=false) List orderPaymentInfo, @RequestParam(value="originOrderId", required=false) String originOrderId, @RequestParam(value="orderName", required=false) String orderName, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="orderItemAssociations", required=false) List orderItemAssociations, @RequestParam(value="billFromVendorPartyId", required=false) String billFromVendorPartyId, @RequestParam(value="grandTotal", required=false) BigDecimal grandTotal, @RequestParam(value="distributorId", required=false) String distributorId, @RequestParam(value="autoOrderShoppingListId", required=false) String autoOrderShoppingListId, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="endUserCustomerPartyId", required=false) String endUserCustomerPartyId, @RequestParam(value="affiliateId", required=false) String affiliateId, @RequestParam(value="orderTerms", required=false) List orderTerms, @RequestParam(value="placingCustomerPartyId", required=false) String placingCustomerPartyId, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="firstAttemptOrderId", required=false) String firstAttemptOrderId, @RequestParam(value="orderAdditionalPartyRoleMap", required=false) Map orderAdditionalPartyRoleMap, @RequestParam(value="orderItemPriceInfos", required=false) List orderItemPriceInfos, @RequestParam(value="orderNotes", required=false) List orderNotes, @RequestParam(value="shipToCustomerPartyId", required=false) String shipToCustomerPartyId, @RequestParam(value="supplierPartyId", required=false) String supplierPartyId, @RequestParam(value="taxAmount", required=false) BigDecimal taxAmount, @RequestParam(value="orderDate", required=false) Timestamp orderDate, @RequestParam(value="internalCode", required=false) String internalCode, @RequestParam(value="orderItemAttributes", required=false) List orderItemAttributes) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("orderAdjustments",orderAdjustments);
		paramMap.put("partyId",partyId);
		paramMap.put("orderItems",orderItems);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("orderItemGroups",orderItemGroups);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("orderId",orderId);
		paramMap.put("billToCustomerPartyId",billToCustomerPartyId);
		paramMap.put("orderAttributes",orderAttributes);
		paramMap.put("orderProductPromoUses",orderProductPromoUses);
		paramMap.put("shipFromVendorPartyId",shipFromVendorPartyId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("terminalId",terminalId);
		paramMap.put("orderItemContactMechs",orderItemContactMechs);
		paramMap.put("orderProductPromoCodes",orderProductPromoCodes);
		paramMap.put("orderItemSurveyResponses",orderItemSurveyResponses);
		paramMap.put("visitId",visitId);
		paramMap.put("trackingCodeOrders",trackingCodeOrders);
		paramMap.put("orderInternalNotes",orderInternalNotes);
		paramMap.put("orderItemShipGroupInfo",orderItemShipGroupInfo);
		paramMap.put("orderContactMechs",orderContactMechs);
		paramMap.put("workEfforts",workEfforts);
		paramMap.put("shippingAmount",shippingAmount);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("supplierAgentPartyId",supplierAgentPartyId);
		paramMap.put("orderPaymentInfo",orderPaymentInfo);
		paramMap.put("originOrderId",originOrderId);
		paramMap.put("orderName",orderName);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("orderItemAssociations",orderItemAssociations);
		paramMap.put("billFromVendorPartyId",billFromVendorPartyId);
		paramMap.put("grandTotal",grandTotal);
		paramMap.put("distributorId",distributorId);
		paramMap.put("autoOrderShoppingListId",autoOrderShoppingListId);
		paramMap.put("externalId",externalId);
		paramMap.put("endUserCustomerPartyId",endUserCustomerPartyId);
		paramMap.put("affiliateId",affiliateId);
		paramMap.put("orderTerms",orderTerms);
		paramMap.put("placingCustomerPartyId",placingCustomerPartyId);
		paramMap.put("transactionId",transactionId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("firstAttemptOrderId",firstAttemptOrderId);
		paramMap.put("orderAdditionalPartyRoleMap",orderAdditionalPartyRoleMap);
		paramMap.put("orderItemPriceInfos",orderItemPriceInfos);
		paramMap.put("orderNotes",orderNotes);
		paramMap.put("shipToCustomerPartyId",shipToCustomerPartyId);
		paramMap.put("supplierPartyId",supplierPartyId);
		paramMap.put("taxAmount",taxAmount);
		paramMap.put("orderDate",orderDate);
		paramMap.put("internalCode",internalCode);
		paramMap.put("orderItemAttributes",orderItemAttributes);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("storeOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteOrderAdjustment")
	public ResponseEntity<Object> deleteOrderAdjustment(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderAdjustmentId") String orderAdjustmentId, @RequestParam(value="productPromoCodeId", required=false) String productPromoCodeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderAdjustmentId",orderAdjustmentId);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteOrderAdjustment", paramMap);
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
