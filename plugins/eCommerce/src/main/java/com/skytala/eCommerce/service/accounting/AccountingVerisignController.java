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
@RequestMapping("/service/accountingVerisign")
public class AccountingVerisignController{

	@RequestMapping(method = RequestMethod.POST, value = "/payflowPayPalProcessor")
	public ResponseEntity<Object> payflowPayPalProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="payPalPaymentMethod") org.apache.ofbiz.entity.GenericValue payPalPaymentMethod, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("payflowPayPalProcessor", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowPayPalRefund")
	public ResponseEntity<Object> payflowPayPalRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("payflowPayPalRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowCCRefund")
	public ResponseEntity<Object> payflowCCRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("payflowCCRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowPayPalVoid")
	public ResponseEntity<Object> payflowPayPalVoid(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("payflowPayPalVoid", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowGetExpressCheckout")
	public ResponseEntity<Object> payflowGetExpressCheckout(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payflowGetExpressCheckout", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowCCVoid")
	public ResponseEntity<Object> payflowCCVoid(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("payflowCCVoid", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowPayPalCapture")
	public ResponseEntity<Object> payflowPayPalCapture(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("payflowPayPalCapture", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowCCProcessor")
	public ResponseEntity<Object> payflowCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("creditCard",creditCard);
		paramMap.put("orderItems",orderItems);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("cardSecurityCode",cardSecurityCode);
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
			result = dispatcher.runSync("payflowCCProcessor", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowCCCapture")
	public ResponseEntity<Object> payflowCCCapture(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("payflowCCCapture", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowSetExpressCheckout")
	public ResponseEntity<Object> payflowSetExpressCheckout(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payflowSetExpressCheckout", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/payflowDoExpressCheckout")
	public ResponseEntity<Object> payflowDoExpressCheckout(HttpSession session, @RequestParam(value="orderPaymentPreference") GenericValue orderPaymentPreference) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payflowDoExpressCheckout", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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