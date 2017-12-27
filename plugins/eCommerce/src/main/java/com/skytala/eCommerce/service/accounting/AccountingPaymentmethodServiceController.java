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
@RequestMapping("/service/accountingPaymentmethod")
public class AccountingPaymentmethodServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentMethod")
	public ResponseEntity<Map<String, Object>> deletePaymentMethod(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentMethod", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/payPalProcessInterface")
	public ResponseEntity<Map<String, Object>> payPalProcessInterface(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="payPalPaymentMethod") org.apache.ofbiz.entity.GenericValue payPalPaymentMethod, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("payPalProcessInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCreditCardAndAddress")
	public ResponseEntity<Map<String, Object>> createCreditCardAndAddress(HttpSession session, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCreditCardAndAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCreditCard")
	public ResponseEntity<Map<String, Object>> updateCreditCard(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="firstNameOnCard", required=false) String firstNameOnCard, @RequestParam(value="issueNumber", required=false) Timestamp issueNumber, @RequestParam(value="cardType", required=false) String cardType, @RequestParam(value="description", required=false) String description, @RequestParam(value="lastFailedAuthDate", required=false) Timestamp lastFailedAuthDate, @RequestParam(value="consecutiveFailedNsf", required=false) Long consecutiveFailedNsf, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="companyNameOnCard", required=false) String companyNameOnCard, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="expMonth", required=false) String expMonth, @RequestParam(value="titleOnCard", required=false) String titleOnCard, @RequestParam(value="lastNameOnCard", required=false) String lastNameOnCard, @RequestParam(value="expYear", required=false) String expYear, @RequestParam(value="middleNameOnCard", required=false) String middleNameOnCard, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="expireDate", required=false) Timestamp expireDate, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="consecutiveFailedAuths", required=false) Long consecutiveFailedAuths, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="lastFailedNsfDate", required=false) Timestamp lastFailedNsfDate, @RequestParam(value="cardNumber", required=false) String cardNumber, @RequestParam(value="suffixOnCard", required=false) String suffixOnCard) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("firstNameOnCard",firstNameOnCard);
		paramMap.put("issueNumber",issueNumber);
		paramMap.put("cardType",cardType);
		paramMap.put("description",description);
		paramMap.put("lastFailedAuthDate",lastFailedAuthDate);
		paramMap.put("consecutiveFailedNsf",consecutiveFailedNsf);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("companyNameOnCard",companyNameOnCard);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("expMonth",expMonth);
		paramMap.put("titleOnCard",titleOnCard);
		paramMap.put("lastNameOnCard",lastNameOnCard);
		paramMap.put("expYear",expYear);
		paramMap.put("middleNameOnCard",middleNameOnCard);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("expireDate",expireDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("consecutiveFailedAuths",consecutiveFailedAuths);
		paramMap.put("partyId",partyId);
		paramMap.put("lastFailedNsfDate",lastFailedNsfDate);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("suffixOnCard",suffixOnCard);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCreditCard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ccAuthInterface")
	public ResponseEntity<Map<String, Object>> ccAuthInterface(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("ccAuthInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGroupMember")
	public ResponseEntity<Map<String, Object>> createPaymentGroupMember(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGroupMember", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePayPalPaymentMethod")
	public ResponseEntity<Map<String, Object>> updatePayPalPaymentMethod(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="expressCheckoutToken", required=false) String expressCheckoutToken, @RequestParam(value="avsAddr", required=false) String avsAddr, @RequestParam(value="payerId", required=false) String payerId, @RequestParam(value="avsZip", required=false) String avsZip, @RequestParam(value="correlationId", required=false) String correlationId, @RequestParam(value="payerStatus", required=false) String payerStatus, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="transactionId", required=false) String transactionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("expressCheckoutToken",expressCheckoutToken);
		paramMap.put("avsAddr",avsAddr);
		paramMap.put("payerId",payerId);
		paramMap.put("avsZip",avsZip);
		paramMap.put("correlationId",correlationId);
		paramMap.put("payerStatus",payerStatus);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("transactionId",transactionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePayPalPaymentMethod", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCProcessorWithCapture")
	public ResponseEntity<Map<String, Object>> testCCProcessorWithCapture(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("testCCProcessorWithCapture", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearCreditCardDataAndExpire")
	public ResponseEntity<Map<String, Object>> clearCreditCardDataAndExpire(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearCreditCardDataAndExpire", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCCapture")
	public ResponseEntity<Map<String, Object>> testCCCapture(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testCCCapture", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysDeclineEFTProcessor")
	public ResponseEntity<Map<String, Object>> alwaysDeclineEFTProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="eftAccount") org.apache.ofbiz.entity.GenericValue eftAccount, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("eftAccount",eftAccount);
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
			result = dispatcher.runSync("alwaysDeclineEFTProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processCreditResult")
	public ResponseEntity<Map<String, Object>> processCreditResult(HttpSession session, @RequestParam(value="creditResult") Boolean creditResult, @RequestParam(value="creditRefNum") String creditRefNum, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditAmount") BigDecimal creditAmount, @RequestParam(value="creditMessage", required=false) String creditMessage, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="creditCode", required=false) String creditCode, @RequestParam(value="creditFlag", required=false) String creditFlag, @RequestParam(value="creditAltRefNum", required=false) String creditAltRefNum, @RequestParam(value="internalRespMsgs", required=false) List internalRespMsgs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("creditResult",creditResult);
		paramMap.put("creditRefNum",creditRefNum);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("creditAmount",creditAmount);
		paramMap.put("creditMessage",creditMessage);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("creditCode",creditCode);
		paramMap.put("creditFlag",creditFlag);
		paramMap.put("creditAltRefNum",creditAltRefNum);
		paramMap.put("internalRespMsgs",internalRespMsgs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processCreditResult", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/expirePaymentGroupMember")
	public ResponseEntity<Map<String, Object>> expirePaymentGroupMember(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expirePaymentGroupMember", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setPaymentMethodAddress")
	public ResponseEntity<Map<String, Object>> setPaymentMethodAddress(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setPaymentMethodAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysApproveGCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysApproveGCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="giftCard") org.apache.ofbiz.entity.GenericValue giftCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("giftCard",giftCard);
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
			result = dispatcher.runSync("alwaysApproveGCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCRefundFailure")
	public ResponseEntity<Map<String, Object>> testCCRefundFailure(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testCCRefundFailure", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/buildCcExpireDate")
	public ResponseEntity<Map<String, Object>> buildCcExpireDate(HttpSession session, @RequestParam(value="expMonth") String expMonth, @RequestParam(value="expYear") String expYear) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("expMonth",expMonth);
		paramMap.put("expYear",expYear);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("buildCcExpireDate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/paymentRefundInterface")
	public ResponseEntity<Map<String, Object>> paymentRefundInterface(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("paymentRefundInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/savePaymentGatewayResponse")
	public ResponseEntity<Map<String, Object>> savePaymentGatewayResponse(HttpSession session, @RequestParam(value="paymentGatewayResponse") org.apache.ofbiz.entity.GenericValue paymentGatewayResponse) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayResponse",paymentGatewayResponse);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("savePaymentGatewayResponse", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCreditCardTypeGlAccount")
	public ResponseEntity<Map<String, Object>> updateCreditCardTypeGlAccount(HttpSession session, @RequestParam(value="cardType") String cardType, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cardType",cardType);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCreditCardTypeGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/releaseOrderPayments")
	public ResponseEntity<Map<String, Object>> releaseOrderPayments(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderPaymentPreferenceId", required=false) String orderPaymentPreferenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("releaseOrderPayments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ccCreditInterface")
	public ResponseEntity<Map<String, Object>> ccCreditInterface(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="referenceCode") String referenceCode, @RequestParam(value="creditAmount") BigDecimal creditAmount, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="orderItems", required=false) List orderItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("referenceCode",referenceCode);
		paramMap.put("creditAmount",creditAmount);
		paramMap.put("creditCard",creditCard);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("cardSecurityCode",cardSecurityCode);
		paramMap.put("billToEmail",billToEmail);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("currency",currency);
		paramMap.put("billingAddress",billingAddress);
		paramMap.put("orderItems",orderItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ccCreditInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCRefund")
	public ResponseEntity<Map<String, Object>> testCCRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testCCRefund", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/captureOrderPayments")
	public ResponseEntity<Map<String, Object>> captureOrderPayments(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="billingAccountId", required=false) String billingAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("captureAmount",captureAmount);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("captureOrderPayments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processPaymentServiceError")
	public ResponseEntity<Map<String, Object>> processPaymentServiceError(HttpSession session, @RequestParam(value="paymentServiceTypeEnumId") String paymentServiceTypeEnumId, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="transCodeEnumId") String transCodeEnumId, @RequestParam(value="serviceResultMap") Map serviceResultMap) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentServiceTypeEnumId",paymentServiceTypeEnumId);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("transCodeEnumId",transCodeEnumId);
		paramMap.put("serviceResultMap",serviceResultMap);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processPaymentServiceError", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/authOrderPaymentPreference")
	public ResponseEntity<Map<String, Object>> authOrderPaymentPreference(HttpSession session, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId, @RequestParam(value="overrideAmount", required=false) BigDecimal overrideAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("overrideAmount",overrideAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("authOrderPaymentPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/retryFailedOrderAuth")
	public ResponseEntity<Map<String, Object>> retryFailedOrderAuth(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("retryFailedOrderAuth", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/retryFailedAuthNsfs")
	public ResponseEntity<Map<String, Object>> retryFailedAuthNsfs(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("retryFailedAuthNsfs", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCheckAccount")
	public ResponseEntity<Map<String, Object>> updateCheckAccount(HttpSession session, @RequestParam(value="nameOnAccount") String nameOnAccount, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="companyNameOnAccount", required=false) String companyNameOnAccount, @RequestParam(value="routingNumber", required=false) String routingNumber, @RequestParam(value="accountType", required=false) String accountType, @RequestParam(value="description", required=false) String description, @RequestParam(value="bankName", required=false) String bankName, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="accountNumber", required=false) String accountNumber, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("nameOnAccount",nameOnAccount);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("companyNameOnAccount",companyNameOnAccount);
		paramMap.put("routingNumber",routingNumber);
		paramMap.put("accountType",accountType);
		paramMap.put("description",description);
		paramMap.put("bankName",bankName);
		paramMap.put("partyId",partyId);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCheckAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testGCRelease")
	public ResponseEntity<Map<String, Object>> testGCRelease(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testGCRelease", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentMethodAddress")
	public ResponseEntity<Map<String, Object>> updatePaymentMethodAddress(HttpSession session, @RequestParam(value="oldContactMechId") String oldContactMechId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("oldContactMechId",oldContactMechId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentMethodAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysFailCCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysFailCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("alwaysFailCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGroup")
	public ResponseEntity<Map<String, Object>> updatePaymentGroup(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId, @RequestParam(value="paymentGroupName", required=false) String paymentGroupName, @RequestParam(value="paymentGroupTypeId", required=false) String paymentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("paymentGroupName",paymentGroupName);
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCreditCardAndAddress")
	public ResponseEntity<Map<String, Object>> updateCreditCardAndAddress(HttpSession session, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCreditCardAndAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysApproveWithCaptureCCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysApproveWithCaptureCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("alwaysApproveWithCaptureCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processReleaseResult")
	public ResponseEntity<Map<String, Object>> processReleaseResult(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="releaseRefNum") String releaseRefNum, @RequestParam(value="releaseResult") Boolean releaseResult, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="releaseFlag", required=false) String releaseFlag, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="releaseAltRefNum", required=false) String releaseAltRefNum, @RequestParam(value="releaseCode", required=false) String releaseCode, @RequestParam(value="releaseMessage", required=false) String releaseMessage, @RequestParam(value="internalRespMsgs", required=false) List internalRespMsgs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("releaseAmount",releaseAmount);
		paramMap.put("releaseRefNum",releaseRefNum);
		paramMap.put("releaseResult",releaseResult);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("releaseFlag",releaseFlag);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("releaseAltRefNum",releaseAltRefNum);
		paramMap.put("releaseCode",releaseCode);
		paramMap.put("releaseMessage",releaseMessage);
		paramMap.put("internalRespMsgs",internalRespMsgs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processReleaseResult", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentGroupMember")
	public ResponseEntity<Map<String, Object>> deletePaymentGroupMember(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentGroupMember", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processAuthResult")
	public ResponseEntity<Map<String, Object>> processAuthResult(HttpSession session, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="authResult") Boolean authResult, @RequestParam(value="authRefNum") String authRefNum, @RequestParam(value="scoreCode", required=false) String scoreCode, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="authCode", required=false) String authCode, @RequestParam(value="serviceTypeEnum", required=false) String serviceTypeEnum, @RequestParam(value="authMessage", required=false) String authMessage, @RequestParam(value="avsCode", required=false) String avsCode, @RequestParam(value="authFlag", required=false) String authFlag, @RequestParam(value="cvCode", required=false) String cvCode, @RequestParam(value="internalRespMsgs", required=false) List internalRespMsgs, @RequestParam(value="authAltRefNum", required=false) String authAltRefNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("authResult",authResult);
		paramMap.put("authRefNum",authRefNum);
		paramMap.put("scoreCode",scoreCode);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("authCode",authCode);
		paramMap.put("serviceTypeEnum",serviceTypeEnum);
		paramMap.put("authMessage",authMessage);
		paramMap.put("avsCode",avsCode);
		paramMap.put("authFlag",authFlag);
		paramMap.put("cvCode",cvCode);
		paramMap.put("internalRespMsgs",internalRespMsgs);
		paramMap.put("authAltRefNum",authAltRefNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processAuthResult", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEftAccount")
	public ResponseEntity<Map<String, Object>> updateEftAccount(HttpSession session, @RequestParam(value="routingNumber") String routingNumber, @RequestParam(value="nameOnAccount") String nameOnAccount, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="accountType") String accountType, @RequestParam(value="bankName") String bankName, @RequestParam(value="accountNumber") String accountNumber, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="companyNameOnAccount", required=false) String companyNameOnAccount, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("routingNumber",routingNumber);
		paramMap.put("nameOnAccount",nameOnAccount);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("accountType",accountType);
		paramMap.put("bankName",bankName);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("fromDate",fromDate);
		paramMap.put("companyNameOnAccount",companyNameOnAccount);
		paramMap.put("description",description);
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEftAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/manualForcedCcTransaction")
	public ResponseEntity<Map<String, Object>> manualForcedCcTransaction(HttpSession session, @RequestParam(value="countryGeoId") String countryGeoId, @RequestParam(value="lastName") String lastName, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="city") String city, @RequestParam(value="firstNameOnCard") String firstNameOnCard, @RequestParam(value="address1") String address1, @RequestParam(value="postalCode") String postalCode, @RequestParam(value="cardType") String cardType, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="transactionType") String transactionType, @RequestParam(value="expMonth") String expMonth, @RequestParam(value="firstName") String firstName, @RequestParam(value="infoString") String infoString, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId, @RequestParam(value="lastNameOnCard") String lastNameOnCard, @RequestParam(value="expYear") String expYear, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="titleOnCard", required=false) String titleOnCard, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="middleNameOnCard", required=false) String middleNameOnCard, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="referenceCode", required=false) String referenceCode, @RequestParam(value="suffixOnCard", required=false) String suffixOnCard, @RequestParam(value="companyNameOnCard", required=false) String companyNameOnCard) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("lastName",lastName);
		paramMap.put("amount",amount);
		paramMap.put("city",city);
		paramMap.put("firstNameOnCard",firstNameOnCard);
		paramMap.put("address1",address1);
		paramMap.put("postalCode",postalCode);
		paramMap.put("cardType",cardType);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("transactionType",transactionType);
		paramMap.put("expMonth",expMonth);
		paramMap.put("firstName",firstName);
		paramMap.put("infoString",infoString);
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("lastNameOnCard",lastNameOnCard);
		paramMap.put("expYear",expYear);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("cardSecurityCode",cardSecurityCode);
		paramMap.put("titleOnCard",titleOnCard);
		paramMap.put("address2",address2);
		paramMap.put("middleNameOnCard",middleNameOnCard);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("referenceCode",referenceCode);
		paramMap.put("suffixOnCard",suffixOnCard);
		paramMap.put("companyNameOnCard",companyNameOnCard);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("manualForcedCcTransaction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEftAccountAndAddress")
	public ResponseEntity<Map<String, Object>> createEftAccountAndAddress(HttpSession session, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEftAccountAndAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/giftCardProcessInterface")
	public ResponseEntity<Map<String, Object>> giftCardProcessInterface(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="giftCard") org.apache.ofbiz.entity.GenericValue giftCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("giftCard",giftCard);
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
			result = dispatcher.runSync("giftCardProcessInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/captureBillingAccountPayments")
	public ResponseEntity<Map<String, Object>> captureBillingAccountPayments(HttpSession session, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="orderId", required=false) String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("captureAmount",captureAmount);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("captureBillingAccountPayments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/ccCaptureInterface")
	public ResponseEntity<Map<String, Object>> ccCaptureInterface(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ccCaptureInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/eftProcessInterface")
	public ResponseEntity<Map<String, Object>> eftProcessInterface(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="eftAccount") org.apache.ofbiz.entity.GenericValue eftAccount, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("eftAccount",eftAccount);
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
			result = dispatcher.runSync("eftProcessInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processCaptureResult")
	public ResponseEntity<Map<String, Object>> processCaptureResult(HttpSession session, @RequestParam(value="captureResult") Boolean captureResult, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureRefNum") String captureRefNum, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="captureFlag", required=false) String captureFlag, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="serviceTypeEnum", required=false) String serviceTypeEnum, @RequestParam(value="captureAltRefNum", required=false) String captureAltRefNum, @RequestParam(value="captureMessage", required=false) String captureMessage, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="captureCode", required=false) String captureCode, @RequestParam(value="internalRespMsgs", required=false) List internalRespMsgs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("captureResult",captureResult);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("captureRefNum",captureRefNum);
		paramMap.put("captureAmount",captureAmount);
		paramMap.put("captureFlag",captureFlag);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("serviceTypeEnum",serviceTypeEnum);
		paramMap.put("captureAltRefNum",captureAltRefNum);
		paramMap.put("captureMessage",captureMessage);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("payToPartyId",payToPartyId);
		paramMap.put("captureCode",captureCode);
		paramMap.put("internalRespMsgs",internalRespMsgs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processCaptureResult", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processCaptureSplitPayment")
	public ResponseEntity<Map<String, Object>> processCaptureSplitPayment(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="splitAmount") BigDecimal splitAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("splitAmount",splitAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processCaptureSplitPayment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCProcessorCaptureAlwaysDecline")
	public ResponseEntity<Map<String, Object>> testCCProcessorCaptureAlwaysDecline(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testCCProcessorCaptureAlwaysDecline", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/paymentCreditInterface")
	public ResponseEntity<Map<String, Object>> paymentCreditInterface(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="referenceCode") String referenceCode, @RequestParam(value="creditAmount") BigDecimal creditAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="orderItems", required=false) List orderItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("referenceCode",referenceCode);
		paramMap.put("creditAmount",creditAmount);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("billToEmail",billToEmail);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("currency",currency);
		paramMap.put("billingAddress",billingAddress);
		paramMap.put("orderItems",orderItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("paymentCreditInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateGiftCard")
	public ResponseEntity<Map<String, Object>> updateGiftCard(HttpSession session, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="expMonth", required=false) String expMonth, @RequestParam(value="expYear", required=false) String expYear, @RequestParam(value="description", required=false) String description, @RequestParam(value="pinNumber", required=false) String pinNumber, @RequestParam(value="expireDate", required=false) String expireDate, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("fromDate",fromDate);
		paramMap.put("expMonth",expMonth);
		paramMap.put("expYear",expYear);
		paramMap.put("description",description);
		paramMap.put("pinNumber",pinNumber);
		paramMap.put("expireDate",expireDate);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGiftCard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/refundPayment")
	public ResponseEntity<Map<String, Object>> refundPayment(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("refundAmount",refundAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("refundPayment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCRelease")
	public ResponseEntity<Map<String, Object>> testCCRelease(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testCCRelease", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/retryFailedAuths")
	public ResponseEntity<Map<String, Object>> retryFailedAuths(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("retryFailedAuths", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentGroup")
	public ResponseEntity<Map<String, Object>> deletePaymentGroup(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/payPalCaptureInterface")
	public ResponseEntity<Map<String, Object>> payPalCaptureInterface(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("payPalCaptureInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEftAccountAndAddress")
	public ResponseEntity<Map<String, Object>> updateEftAccountAndAddress(HttpSession session, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEftAccountAndAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysDeclineCCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysDeclineCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("alwaysDeclineCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCCaptureWithReAuth")
	public ResponseEntity<Map<String, Object>> testCCCaptureWithReAuth(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testCCCaptureWithReAuth", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysBadCardNumberCCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysBadCardNumberCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("alwaysBadCardNumberCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/creditOrderPaymentPreference")
	public ResponseEntity<Map<String, Object>> creditOrderPaymentPreference(HttpSession session, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("creditOrderPaymentPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testCCProcessor")
	public ResponseEntity<Map<String, Object>> testCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("testCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentMethodType")
	public ResponseEntity<Map<String, Object>> createPaymentMethodType(HttpSession session, @RequestParam(value="defaultGlAccountId", required=false) String defaultGlAccountId, @RequestParam(value="description", required=false) String description, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("defaultGlAccountId",defaultGlAccountId);
		paramMap.put("description",description);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testEFTProcessor")
	public ResponseEntity<Map<String, Object>> testEFTProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="eftAccount") org.apache.ofbiz.entity.GenericValue eftAccount, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("eftAccount",eftAccount);
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
			result = dispatcher.runSync("testEFTProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCheckAccount")
	public ResponseEntity<Map<String, Object>> createCheckAccount(HttpSession session, @RequestParam(value="nameOnAccount") String nameOnAccount, @RequestParam(value="bankName") String bankName, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="companyNameOnAccount", required=false) String companyNameOnAccount, @RequestParam(value="routingNumber", required=false) String routingNumber, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="accountType", required=false) String accountType, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="accountNumber", required=false) String accountNumber, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("nameOnAccount",nameOnAccount);
		paramMap.put("bankName",bankName);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("companyNameOnAccount",companyNameOnAccount);
		paramMap.put("routingNumber",routingNumber);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("accountType",accountType);
		paramMap.put("description",description);
		paramMap.put("partyId",partyId);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCheckAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCreditCardTypeGlAccount")
	public ResponseEntity<Map<String, Object>> createCreditCardTypeGlAccount(HttpSession session, @RequestParam(value="cardType") String cardType, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cardType",cardType);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCreditCardTypeGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentMethodType")
	public ResponseEntity<Map<String, Object>> deletePaymentMethodType(HttpSession session, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/captureBillingAccountPayment")
	public ResponseEntity<Map<String, Object>> captureBillingAccountPayment(HttpSession session, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="billingAccountId") String billingAccountId, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="orderId", required=false) String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("captureAmount",captureAmount);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("captureBillingAccountPayment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCreditCardTypeGlAccount")
	public ResponseEntity<Map<String, Object>> deleteCreditCardTypeGlAccount(HttpSession session, @RequestParam(value="cardType") String cardType, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cardType",cardType);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCreditCardTypeGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysDeclineGCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysDeclineGCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="giftCard") org.apache.ofbiz.entity.GenericValue giftCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("giftCard",giftCard);
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
			result = dispatcher.runSync("alwaysDeclineGCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/verifyCreditCard")
	public ResponseEntity<Map<String, Object>> verifyCreditCard(HttpSession session, @RequestParam(value="mode") String mode, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="oldPaymentMethodId", required=false) String oldPaymentMethodId, @RequestParam(value="productStoreId", required=false) String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mode",mode);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("oldPaymentMethodId",oldPaymentMethodId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("verifyCreditCard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/manualForcedCcAuthTransaction")
	public ResponseEntity<Map<String, Object>> manualForcedCcAuthTransaction(HttpSession session, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="paymentMethodId") String paymentMethodId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="securityCode", required=false) String securityCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("securityCode",securityCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("manualForcedCcAuthTransaction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/payPalDoExpressCheckoutInterface")
	public ResponseEntity<Map<String, Object>> payPalDoExpressCheckoutInterface(HttpSession session, @RequestParam(value="orderPaymentPreference") GenericValue orderPaymentPreference) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalDoExpressCheckoutInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/savePaymentGatewayResponseAndMessages")
	public ResponseEntity<Map<String, Object>> savePaymentGatewayResponseAndMessages(HttpSession session, @RequestParam(value="paymentGatewayResponse") org.apache.ofbiz.entity.GenericValue paymentGatewayResponse, @RequestParam(value="messages") List messages) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayResponse",paymentGatewayResponse);
		paramMap.put("messages",messages);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("savePaymentGatewayResponseAndMessages", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/payPalGetExpressCheckoutInterface")
	public ResponseEntity<Map<String, Object>> payPalGetExpressCheckoutInterface(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalGetExpressCheckoutInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGroupMember")
	public ResponseEntity<Map<String, Object>> updatePaymentGroupMember(HttpSession session, @RequestParam(value="paymentGroupId") String paymentGroupId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="paymentId") String paymentId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupId",paymentGroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentId",paymentId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGroupMember", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCreditCard")
	public ResponseEntity<Map<String, Object>> createCreditCard(HttpSession session, @RequestParam(value="lastNameOnCard") String lastNameOnCard, @RequestParam(value="firstNameOnCard") String firstNameOnCard, @RequestParam(value="cardType") String cardType, @RequestParam(value="expireDate") Timestamp expireDate, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="issueNumber", required=false) Timestamp issueNumber, @RequestParam(value="description", required=false) String description, @RequestParam(value="lastFailedAuthDate", required=false) Timestamp lastFailedAuthDate, @RequestParam(value="consecutiveFailedNsf", required=false) Long consecutiveFailedNsf, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="companyNameOnCard", required=false) String companyNameOnCard, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="expMonth", required=false) String expMonth, @RequestParam(value="titleOnCard", required=false) String titleOnCard, @RequestParam(value="expYear", required=false) String expYear, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="middleNameOnCard", required=false) String middleNameOnCard, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="consecutiveFailedAuths", required=false) Long consecutiveFailedAuths, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="lastFailedNsfDate", required=false) Timestamp lastFailedNsfDate, @RequestParam(value="suffixOnCard", required=false) String suffixOnCard) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lastNameOnCard",lastNameOnCard);
		paramMap.put("firstNameOnCard",firstNameOnCard);
		paramMap.put("cardType",cardType);
		paramMap.put("expireDate",expireDate);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("issueNumber",issueNumber);
		paramMap.put("description",description);
		paramMap.put("lastFailedAuthDate",lastFailedAuthDate);
		paramMap.put("consecutiveFailedNsf",consecutiveFailedNsf);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("companyNameOnCard",companyNameOnCard);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("expMonth",expMonth);
		paramMap.put("titleOnCard",titleOnCard);
		paramMap.put("expYear",expYear);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("middleNameOnCard",middleNameOnCard);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("consecutiveFailedAuths",consecutiveFailedAuths);
		paramMap.put("partyId",partyId);
		paramMap.put("lastFailedNsfDate",lastFailedNsfDate);
		paramMap.put("suffixOnCard",suffixOnCard);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCreditCard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/capturePaymentsByInvoice")
	public ResponseEntity<Map<String, Object>> capturePaymentsByInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("capturePaymentsByInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysApproveCCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysApproveCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("alwaysApproveCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPayPalPaymentMethod")
	public ResponseEntity<Map<String, Object>> createPayPalPaymentMethod(HttpSession session, @RequestParam(value="expressCheckoutToken", required=false) String expressCheckoutToken, @RequestParam(value="avsAddr", required=false) String avsAddr, @RequestParam(value="payerId", required=false) String payerId, @RequestParam(value="description", required=false) String description, @RequestParam(value="payerStatus", required=false) String payerStatus, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="avsZip", required=false) String avsZip, @RequestParam(value="correlationId", required=false) String correlationId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("expressCheckoutToken",expressCheckoutToken);
		paramMap.put("avsAddr",avsAddr);
		paramMap.put("payerId",payerId);
		paramMap.put("description",description);
		paramMap.put("payerStatus",payerStatus);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("transactionId",transactionId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("avsZip",avsZip);
		paramMap.put("correlationId",correlationId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPayPalPaymentMethod", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/badExpireEvenCCProcessor")
	public ResponseEntity<Map<String, Object>> badExpireEvenCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("badExpireEvenCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createGiftCard")
	public ResponseEntity<Map<String, Object>> createGiftCard(HttpSession session, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="expMonth", required=false) String expMonth, @RequestParam(value="expYear", required=false) String expYear, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="description", required=false) String description, @RequestParam(value="pinNumber", required=false) String pinNumber, @RequestParam(value="expireDate", required=false) String expireDate, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("fromDate",fromDate);
		paramMap.put("expMonth",expMonth);
		paramMap.put("expYear",expYear);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("description",description);
		paramMap.put("pinNumber",pinNumber);
		paramMap.put("expireDate",expireDate);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGiftCard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processRefundResult")
	public ResponseEntity<Map<String, Object>> processRefundResult(HttpSession session, @RequestParam(value="refundResult") Boolean refundResult, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundRefNum") String refundRefNum, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="refundMessage", required=false) String refundMessage, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="serviceTypeEnum", required=false) String serviceTypeEnum, @RequestParam(value="refundFlag", required=false) String refundFlag, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="refundAltRefNum", required=false) String refundAltRefNum, @RequestParam(value="payFromPartyId", required=false) String payFromPartyId, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="refundCode", required=false) String refundCode, @RequestParam(value="internalRespMsgs", required=false) List internalRespMsgs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("refundResult",refundResult);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("refundRefNum",refundRefNum);
		paramMap.put("refundAmount",refundAmount);
		paramMap.put("refundMessage",refundMessage);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("serviceTypeEnum",serviceTypeEnum);
		paramMap.put("refundFlag",refundFlag);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("refundAltRefNum",refundAltRefNum);
		paramMap.put("payFromPartyId",payFromPartyId);
		paramMap.put("payToPartyId",payToPartyId);
		paramMap.put("refundCode",refundCode);
		paramMap.put("internalRespMsgs",internalRespMsgs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processRefundResult", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/refundOrderPaymentPreference")
	public ResponseEntity<Map<String, Object>> refundOrderPaymentPreference(HttpSession session, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("refundOrderPaymentPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/releaseOrderPaymentPreference")
	public ResponseEntity<Map<String, Object>> releaseOrderPaymentPreference(HttpSession session, @RequestParam(value="orderPaymentPreferenceId") String orderPaymentPreferenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreferenceId",orderPaymentPreferenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("releaseOrderPaymentPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysApproveEFTProcessor")
	public ResponseEntity<Map<String, Object>> alwaysApproveEFTProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="eftAccount") org.apache.ofbiz.entity.GenericValue eftAccount, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("eftAccount",eftAccount);
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
			result = dispatcher.runSync("alwaysApproveEFTProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/payPalSetExpressCheckoutInterface")
	public ResponseEntity<Map<String, Object>> payPalSetExpressCheckoutInterface(HttpSession session, @RequestParam(value="cart") org.apache.ofbiz.order.shoppingcart.ShoppingCart cart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cart",cart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("payPalSetExpressCheckoutInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/paymentReleaseInterface")
	public ResponseEntity<Map<String, Object>> paymentReleaseInterface(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("paymentReleaseInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testRandomAuthorize")
	public ResponseEntity<Map<String, Object>> testRandomAuthorize(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("testRandomAuthorize", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/authOrderPayments")
	public ResponseEntity<Map<String, Object>> authOrderPayments(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="reAuth", required=false) Boolean reAuth) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("reAuth",reAuth);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("authOrderPayments", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEftAccount")
	public ResponseEntity<Map<String, Object>> createEftAccount(HttpSession session, @RequestParam(value="routingNumber") String routingNumber, @RequestParam(value="nameOnAccount") String nameOnAccount, @RequestParam(value="accountType") String accountType, @RequestParam(value="bankName") String bankName, @RequestParam(value="accountNumber") String accountNumber, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="companyNameOnAccount", required=false) String companyNameOnAccount, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("routingNumber",routingNumber);
		paramMap.put("nameOnAccount",nameOnAccount);
		paramMap.put("accountType",accountType);
		paramMap.put("bankName",bankName);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("fromDate",fromDate);
		paramMap.put("companyNameOnAccount",companyNameOnAccount);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("description",description);
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEftAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentMethodType")
	public ResponseEntity<Map<String, Object>> updatePaymentMethodType(HttpSession session, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="defaultGlAccountId", required=false) String defaultGlAccountId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("defaultGlAccountId",defaultGlAccountId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/paymentProcessInterface")
	public ResponseEntity<Map<String, Object>> paymentProcessInterface(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
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
			result = dispatcher.runSync("paymentProcessInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/testEFTRelease")
	public ResponseEntity<Map<String, Object>> testEFTRelease(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("testEFTRelease", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGroup")
	public ResponseEntity<Map<String, Object>> createPaymentGroup(HttpSession session, @RequestParam(value="paymentGroupName") String paymentGroupName, @RequestParam(value="paymentGroupTypeId") String paymentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGroupName",paymentGroupName);
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysBadExpireCCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysBadExpireCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("alwaysBadExpireCCProcessor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/alwaysNsfCCProcessor")
	public ResponseEntity<Map<String, Object>> alwaysNsfCCProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="creditCard") org.apache.ofbiz.entity.GenericValue creditCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="cardSecurityCode", required=false) String cardSecurityCode, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("alwaysNsfCCProcessor", paramMap);
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
