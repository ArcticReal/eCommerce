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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/accountingRita")
public class AccountingRitaServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCVoidRefund")
	public ResponseEntity<Map<String, Object>> ritaCCVoidRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCVoidRefund", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCReleaseRemote")
	public ResponseEntity<Map<String, Object>> ritaCCReleaseRemote(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCReleaseRemote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCAuth")
	public ResponseEntity<Map<String, Object>> ritaCCAuth(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("ritaCCAuth", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCRefundRemote")
	public ResponseEntity<Map<String, Object>> ritaCCRefundRemote(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCRefundRemote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCCaptureRemote")
	public ResponseEntity<Map<String, Object>> ritaCCCaptureRemote(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCCaptureRemote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCRefund")
	public ResponseEntity<Map<String, Object>> ritaCCRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCRefund", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCCapture")
	public ResponseEntity<Map<String, Object>> ritaCCCapture(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCCapture", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCAuthRemote")
	public ResponseEntity<Map<String, Object>> ritaCCAuthRemote(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("ritaCCAuthRemote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCRelease")
	public ResponseEntity<Map<String, Object>> ritaCCRelease(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCRelease", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ritaCCCreditRefund")
	public ResponseEntity<Map<String, Object>> ritaCCCreditRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ritaCCCreditRefund", paramMap);
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
