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
@RequestMapping("/service/PayPalController")
public class PayPalServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/payPalCapture")
	public ResponseEntity<Object> payPalCapture(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("captureAmount",captureAmount);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("authTrans",authTrans);
		paramMap.put("currency",currency);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalCapture", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payPalProcessor")
	public ResponseEntity<Object> payPalProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="payPalPaymentMethod") org.apache.ofbiz.entity.GenericValue payPalPaymentMethod, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("payPalPaymentMethod",payPalPaymentMethod);
		paramMap.put("orderItems",orderItems);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("billToEmail",billToEmail);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("shippingAddress",shippingAddress);
		paramMap.put("currency",currency);
		paramMap.put("billingAddress",billingAddress);
		paramMap.put("customerIpAddress",customerIpAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalProcessor", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payPalDoExpressCheckout")
	public ResponseEntity<Object> payPalDoExpressCheckout(HttpSession session, @RequestParam(value="orderPaymentPreference") GenericValue orderPaymentPreference) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalDoExpressCheckout", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payPalRefund")
	public ResponseEntity<Object> payPalRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("refundAmount",refundAmount);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("currency",currency);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payPalGetExpressCheckout")
	public ResponseEntity<Object> payPalGetExpressCheckout(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalGetExpressCheckout", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payPalVoid")
	public ResponseEntity<Object> payPalVoid(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("releaseAmount",releaseAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("authTrans",authTrans);
		paramMap.put("currency",currency);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalVoid", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payPalSetExpressCheckout")
	public ResponseEntity<Object> payPalSetExpressCheckout(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalSetExpressCheckout", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payPalCheckoutUpdate")
	public ResponseEntity<Object> payPalCheckoutUpdate(HttpSession session, @RequestParam(value="request") javax.servlet.http.HttpServletRequest request, @RequestParam(value="response") javax.servlet.http.HttpServletResponse response) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("request",request);
		paramMap.put("response",response);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalCheckoutUpdate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
