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
@RequestMapping("/service/AccountingController19")
public class AccountingServiceController19{

	@RequestMapping(method = RequestMethod.POST, value = "/sagepayCCCapture")
	public ResponseEntity<Object> sagepayCCCapture(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("sagepayCCCapture", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sagepayCCRelease")
	public ResponseEntity<Object> sagepayCCRelease(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("sagepayCCRelease", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sagepayCCAuth")
	public ResponseEntity<Object> sagepayCCAuth(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("sagepayCCAuth", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SagePayPaymentRelease")
	public ResponseEntity<Object> SagePayPaymentRelease(HttpSession session, @RequestParam(value="releaseAmount") String releaseAmount, @RequestParam(value="vpsTxId") String vpsTxId, @RequestParam(value="vendorTxCode") String vendorTxCode, @RequestParam(value="securityKey") String securityKey, @RequestParam(value="txAuthNo") String txAuthNo, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("releaseAmount",releaseAmount);
		paramMap.put("vpsTxId",vpsTxId);
		paramMap.put("vendorTxCode",vendorTxCode);
		paramMap.put("securityKey",securityKey);
		paramMap.put("txAuthNo",txAuthNo);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("SagePayPaymentRelease", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SagePayPaymentRefund")
	public ResponseEntity<Object> SagePayPaymentRefund(HttpSession session, @RequestParam(value="relatedVendorTxCode") String relatedVendorTxCode, @RequestParam(value="amount") String amount, @RequestParam(value="vendorTxCode") String vendorTxCode, @RequestParam(value="description") String description, @RequestParam(value="currency") String currency, @RequestParam(value="relatedTxAuthNo") String relatedTxAuthNo, @RequestParam(value="relatedVPSTxId") String relatedVPSTxId, @RequestParam(value="relatedSecurityKey") String relatedSecurityKey, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("relatedVendorTxCode",relatedVendorTxCode);
		paramMap.put("amount",amount);
		paramMap.put("vendorTxCode",vendorTxCode);
		paramMap.put("description",description);
		paramMap.put("currency",currency);
		paramMap.put("relatedTxAuthNo",relatedTxAuthNo);
		paramMap.put("relatedVPSTxId",relatedVPSTxId);
		paramMap.put("relatedSecurityKey",relatedSecurityKey);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("SagePayPaymentRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sagepayCCRefund")
	public ResponseEntity<Object> sagepayCCRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("sagepayCCRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SagePayPaymentAuthentication")
	public ResponseEntity<Object> SagePayPaymentAuthentication(HttpSession session, @RequestParam(value="expiryDate") String expiryDate, @RequestParam(value="amount") String amount, @RequestParam(value="vendorTxCode") String vendorTxCode, @RequestParam(value="cardType") String cardType, @RequestParam(value="cardHolder") String cardHolder, @RequestParam(value="description") String description, @RequestParam(value="currency") String currency, @RequestParam(value="billingAddress") String billingAddress, @RequestParam(value="billingPostCode") String billingPostCode, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="deliveryPhone", required=false) String deliveryPhone, @RequestParam(value="deliveryCity", required=false) String deliveryCity, @RequestParam(value="basket", required=false) String basket, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="issueNumber", required=false) String issueNumber, @RequestParam(value="deliveryAddress2", required=false) String deliveryAddress2, @RequestParam(value="billingFirstnames", required=false) String billingFirstnames, @RequestParam(value="billingState", required=false) String billingState, @RequestParam(value="deliverySurname", required=false) String deliverySurname, @RequestParam(value="billingAddress2", required=false) String billingAddress2, @RequestParam(value="deliveryPostCode", required=false) String deliveryPostCode, @RequestParam(value="deliveryAddress", required=false) String deliveryAddress, @RequestParam(value="deliveryCountry", required=false) String deliveryCountry, @RequestParam(value="deliveryState", required=false) String deliveryState, @RequestParam(value="billingSurname", required=false) String billingSurname, @RequestParam(value="billingCountry", required=false) String billingCountry, @RequestParam(value="isBillingSameAsDelivery", required=false) Boolean isBillingSameAsDelivery, @RequestParam(value="deliveryFirstnames", required=false) String deliveryFirstnames, @RequestParam(value="billingCity", required=false) String billingCity, @RequestParam(value="billingPhone", required=false) String billingPhone, @RequestParam(value="cv2", required=false) String cv2, @RequestParam(value="startDate", required=false) String startDate, @RequestParam(value="clientIPAddress", required=false) String clientIPAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("expiryDate",expiryDate);
		paramMap.put("amount",amount);
		paramMap.put("vendorTxCode",vendorTxCode);
		paramMap.put("cardType",cardType);
		paramMap.put("cardHolder",cardHolder);
		paramMap.put("description",description);
		paramMap.put("currency",currency);
		paramMap.put("billingAddress",billingAddress);
		paramMap.put("billingPostCode",billingPostCode);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("deliveryPhone",deliveryPhone);
		paramMap.put("deliveryCity",deliveryCity);
		paramMap.put("basket",basket);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("issueNumber",issueNumber);
		paramMap.put("deliveryAddress2",deliveryAddress2);
		paramMap.put("billingFirstnames",billingFirstnames);
		paramMap.put("billingState",billingState);
		paramMap.put("deliverySurname",deliverySurname);
		paramMap.put("billingAddress2",billingAddress2);
		paramMap.put("deliveryPostCode",deliveryPostCode);
		paramMap.put("deliveryAddress",deliveryAddress);
		paramMap.put("deliveryCountry",deliveryCountry);
		paramMap.put("deliveryState",deliveryState);
		paramMap.put("billingSurname",billingSurname);
		paramMap.put("billingCountry",billingCountry);
		paramMap.put("isBillingSameAsDelivery",isBillingSameAsDelivery);
		paramMap.put("deliveryFirstnames",deliveryFirstnames);
		paramMap.put("billingCity",billingCity);
		paramMap.put("billingPhone",billingPhone);
		paramMap.put("cv2",cv2);
		paramMap.put("startDate",startDate);
		paramMap.put("clientIPAddress",clientIPAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("SagePayPaymentAuthentication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SagePayPaymentVoid")
	public ResponseEntity<Object> SagePayPaymentVoid(HttpSession session, @RequestParam(value="vpsTxId") String vpsTxId, @RequestParam(value="vendorTxCode") String vendorTxCode, @RequestParam(value="securityKey") String securityKey, @RequestParam(value="txAuthNo") String txAuthNo, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vpsTxId",vpsTxId);
		paramMap.put("vendorTxCode",vendorTxCode);
		paramMap.put("securityKey",securityKey);
		paramMap.put("txAuthNo",txAuthNo);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("SagePayPaymentVoid", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SagePayPaymentAuthorisation")
	public ResponseEntity<Object> SagePayPaymentAuthorisation(HttpSession session, @RequestParam(value="amount") String amount, @RequestParam(value="vendorTxCode") String vendorTxCode, @RequestParam(value="vpsTxId", required=false) String vpsTxId, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="securityKey", required=false) String securityKey, @RequestParam(value="txAuthNo", required=false) String txAuthNo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("vendorTxCode",vendorTxCode);
		paramMap.put("vpsTxId",vpsTxId);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("securityKey",securityKey);
		paramMap.put("txAuthNo",txAuthNo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("SagePayPaymentAuthorisation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
