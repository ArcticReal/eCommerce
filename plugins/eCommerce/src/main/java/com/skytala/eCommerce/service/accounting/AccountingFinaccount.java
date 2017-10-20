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
@RequestMapping("/service/AccountingFinaccount")
public class AccountingFinaccount{

	@RequestMapping(method = RequestMethod.POST, value = "/getFinAccountTransRunningTotalAndBalances")
	public ResponseEntity<Object> getFinAccountTransRunningTotalAndBalances(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="runningTotal", required=false) BigDecimal runningTotal, @RequestParam(value="reconciledBalance", required=false) BigDecimal reconciledBalance, @RequestParam(value="numberOfTransactions", required=false) Long numberOfTransactions, @RequestParam(value="openingBalance", required=false) BigDecimal openingBalance) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("runningTotal",runningTotal);
		paramMap.put("reconciledBalance",reconciledBalance);
		paramMap.put("numberOfTransactions",numberOfTransactions);
		paramMap.put("openingBalance",openingBalance);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getFinAccountTransRunningTotalAndBalances", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbFaRefund")
	public ResponseEntity<Object> ofbFaRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("refundAmount",refundAmount);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("currency",currency);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ofbFaRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountStatus")
	public ResponseEntity<Object> createFinAccountStatus(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="statusId") String statusId, @RequestParam(value="statusDate", required=false) Timestamp statusDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("statusId",statusId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/finAccountRefund")
	public ResponseEntity<Object> finAccountRefund(HttpSession session, @RequestParam(value="finAccountId") String finAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("finAccountRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountBalancesFromTrans")
	public ResponseEntity<Object> updateFinAccountBalancesFromTrans(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountBalancesFromTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/redeemGiftCertificate")
	public ResponseEntity<Object> redeemGiftCertificate(HttpSession session, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="pinNumber") String pinNumber, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("pinNumber",pinNumber);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("currency",currency);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("redeemGiftCertificate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbFaCapture")
	public ResponseEntity<Object> ofbFaCapture(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="captureAmount") BigDecimal captureAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ofbFaCapture", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/depositWithdrawPayments")
	public ResponseEntity<Object> depositWithdrawPayments(HttpSession session, @RequestParam(value="paymentIds") List paymentIds, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="groupInOneTransaction", required=false) String groupInOneTransaction, @RequestParam(value="paymentGroupName", required=false) String paymentGroupName, @RequestParam(value="paymentGroupTypeId", required=false) String paymentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentIds",paymentIds);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("groupInOneTransaction",groupInOneTransaction);
		paramMap.put("paymentGroupName",paymentGroupName);
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("depositWithdrawPayments", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getTransactionTotalByGlReconcileId")
	public ResponseEntity<Object> getTransactionTotalByGlReconcileId(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getTransactionTotalByGlReconcileId", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbGcAuthorize")
	public ResponseEntity<Object> ofbGcAuthorize(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="giftCard") org.apache.ofbiz.entity.GenericValue giftCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("ofbGcAuthorize", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountTypeAttr")
	public ResponseEntity<Object> deleteFinAccountTypeAttr(HttpSession session, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountTypeAttr")
	public ResponseEntity<Object> updateFinAccountTypeAttr(HttpSession session, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description, @RequestParam(value="attrValue", required=false) String attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/expireFinAccountAuth")
	public ResponseEntity<Object> expireFinAccountAuth(HttpSession session, @RequestParam(value="finAccountAuthId") String finAccountAuthId, @RequestParam(value="expireDateTime", required=false) Timestamp expireDateTime) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountAuthId",finAccountAuthId);
		paramMap.put("expireDateTime",expireDateTime);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireFinAccountAuth", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/refundGcPurchase")
	public ResponseEntity<Object> refundGcPurchase(HttpSession session, @RequestParam(value="orderItem") org.apache.ofbiz.entity.GenericValue orderItem, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItem",orderItem);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("refundGcPurchase", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/finAccountDeposit")
	public ResponseEntity<Object> finAccountDeposit(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="isRefund", required=false) Boolean isRefund, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("amount",amount);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("isRefund",isRefund);
		paramMap.put("currency",currency);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("finAccountDeposit", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reconcileWithdrawalFinAcctgTrans")
	public ResponseEntity<Object> reconcileWithdrawalFinAcctgTrans(HttpSession session, @RequestParam(value="finAccountTrans") org.apache.ofbiz.entity.GenericValue finAccountTrans) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTrans",finAccountTrans);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reconcileWithdrawalFinAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentAndFinAccountTrans")
	public ResponseEntity<Object> createPaymentAndFinAccountTrans(HttpSession session, @RequestParam(value="finAccountTransTypeId") String finAccountTransTypeId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="paymentPreferenceId", required=false) String paymentPreferenceId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="actualCurrencyAmount", required=false) BigDecimal actualCurrencyAmount, @RequestParam(value="overrideGlAccountId", required=false) String overrideGlAccountId, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="paymentGroupTypeId", required=false) String paymentGroupTypeId, @RequestParam(value="actualCurrencyUomId", required=false) String actualCurrencyUomId, @RequestParam(value="isDepositWithDrawPayment", required=false) String isDepositWithDrawPayment, @RequestParam(value="paymentTypeId", required=false) String paymentTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="paymentGatewayResponseId", required=false) String paymentGatewayResponseId, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="paymentRefNum", required=false) String paymentRefNum, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="effectiveDate", required=false) Timestamp effectiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("paymentPreferenceId",paymentPreferenceId);
		paramMap.put("comments",comments);
		paramMap.put("actualCurrencyAmount",actualCurrencyAmount);
		paramMap.put("overrideGlAccountId",overrideGlAccountId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("paymentGroupTypeId",paymentGroupTypeId);
		paramMap.put("actualCurrencyUomId",actualCurrencyUomId);
		paramMap.put("isDepositWithDrawPayment",isDepositWithDrawPayment);
		paramMap.put("paymentTypeId",paymentTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("paymentGatewayResponseId",paymentGatewayResponseId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("paymentRefNum",paymentRefNum);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("effectiveDate",effectiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentAndFinAccountTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountRole")
	public ResponseEntity<Object> createFinAccountRole(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbFaAuthorize")
	public ResponseEntity<Object> ofbFaAuthorize(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="finAccountPin", required=false) String finAccountPin, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress, @RequestParam(value="finAccountCode", required=false) String finAccountCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("billToParty",billToParty);
		paramMap.put("orderId",orderId);
		paramMap.put("processAmount",processAmount);
		paramMap.put("orderPaymentPreference",orderPaymentPreference);
		paramMap.put("orderItems",orderItems);
		paramMap.put("paymentConfig",paymentConfig);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("billToEmail",billToEmail);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("finAccountPin",finAccountPin);
		paramMap.put("shippingAddress",shippingAddress);
		paramMap.put("currency",currency);
		paramMap.put("billingAddress",billingAddress);
		paramMap.put("customerIpAddress",customerIpAddress);
		paramMap.put("finAccountCode",finAccountCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ofbFaAuthorize", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGiftCertificate")
	public ResponseEntity<Object> createGiftCertificate(HttpSession session, @RequestParam(value="initialAmount") BigDecimal initialAmount, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("initialAmount",initialAmount);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("currency",currency);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGiftCertificate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reconcileAdjustmentFinAcctgTrans")
	public ResponseEntity<Object> reconcileAdjustmentFinAcctgTrans(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="finAccountTrans") org.apache.ofbiz.entity.GenericValue finAccountTrans) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("finAccountTrans",finAccountTrans);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reconcileAdjustmentFinAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountTransTypeAttr")
	public ResponseEntity<Object> updateFinAccountTransTypeAttr(HttpSession session, @RequestParam(value="finAccountTransTypeId") String finAccountTransTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountTransTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountRole")
	public ResponseEntity<Object> updateFinAccountRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbFaRelease")
	public ResponseEntity<Object> ofbFaRelease(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ofbFaRelease", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGcFulFillmentRecord")
	public ResponseEntity<Object> createGcFulFillmentRecord(HttpSession session, @RequestParam(value="typeEnumId") String typeEnumId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="referenceNum", required=false) String referenceNum, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="authCode", required=false) String authCode, @RequestParam(value="merchantId", required=false) String merchantId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="pinNumber", required=false) String pinNumber, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="cardNumber", required=false) String cardNumber, @RequestParam(value="responseCode", required=false) String responseCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("typeEnumId",typeEnumId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("referenceNum",referenceNum);
		paramMap.put("amount",amount);
		paramMap.put("authCode",authCode);
		paramMap.put("merchantId",merchantId);
		paramMap.put("orderId",orderId);
		paramMap.put("pinNumber",pinNumber);
		paramMap.put("partyId",partyId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("responseCode",responseCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGcFulFillmentRecord", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentOnFinAccTransStatusSetToCancel")
	public ResponseEntity<Object> updatePaymentOnFinAccTransStatusSetToCancel(HttpSession session, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentOnFinAccTransStatusSetToCancel", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountRole")
	public ResponseEntity<Object> deleteFinAccountRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountTransTypeAttr")
	public ResponseEntity<Object> deleteFinAccountTransTypeAttr(HttpSession session, @RequestParam(value="finAccountTransTypeId") String finAccountTransTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountTransTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountAndCredit")
	public ResponseEntity<Object> createFinAccountAndCredit(HttpSession session, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="partyId") String partyId, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="finAccountName", required=false) String finAccountName, @RequestParam(value="replenishLevel", required=false) BigDecimal replenishLevel, @RequestParam(value="replenishPaymentId", required=false) String replenishPaymentId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="productStoreId", required=false) String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("partyId",partyId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("comments",comments);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("finAccountName",finAccountName);
		paramMap.put("replenishLevel",replenishLevel);
		paramMap.put("replenishPaymentId",replenishPaymentId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountAndCredit", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addFundsToGiftCertificate")
	public ResponseEntity<Object> addFundsToGiftCertificate(HttpSession session, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="pinNumber", required=false) String pinNumber, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("pinNumber",pinNumber);
		paramMap.put("currency",currency);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addFundsToGiftCertificate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setFinAccountTransStatus")
	public ResponseEntity<Object> setFinAccountTransStatus(HttpSession session, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setFinAccountTransStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyFinAccountFromPurchase")
	public ResponseEntity<Object> createPartyFinAccountFromPurchase(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="orderItem") org.apache.ofbiz.entity.GenericValue orderItem, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId) {
		
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
			result = dispatcher.runSync("createPartyFinAccountFromPurchase", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountTransAttribute")
	public ResponseEntity<Object> createFinAccountTransAttribute(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountTransAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountTrans")
	public ResponseEntity<Object> createFinAccountTrans(HttpSession session, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="glReconciliationId", required=false) String glReconciliationId, @RequestParam(value="glAccountId", required=false) String glAccountId, @RequestParam(value="transactionDate", required=false) Timestamp transactionDate, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="finAccountTransTypeId", required=false) String finAccountTransTypeId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("entryDate",entryDate);
		paramMap.put("orderId",orderId);
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("transactionDate",transactionDate);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getReconciliationClosingBalance")
	public ResponseEntity<Object> getReconciliationClosingBalance(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getReconciliationClosingBalance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbGcRefund")
	public ResponseEntity<Object> ofbGcRefund(HttpSession session, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="refundAmount") BigDecimal refundAmount, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ofbGcRefund", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelBankReconciliation")
	public ResponseEntity<Object> cancelBankReconciliation(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelBankReconciliation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reconcileFinAccountTrans")
	public ResponseEntity<Object> reconcileFinAccountTrans(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="debitCreditFlag", required=false) String debitCreditFlag, @RequestParam(value="glAccountId", required=false) String glAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("debitCreditFlag",debitCreditFlag);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reconcileFinAccountTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/finAccountWithdraw")
	public ResponseEntity<Object> finAccountWithdraw(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="requireBalance", required=false) Boolean requireBalance, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("amount",amount);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("requireBalance",requireBalance);
		paramMap.put("orderId",orderId);
		paramMap.put("currency",currency);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("finAccountWithdraw", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountAttribute")
	public ResponseEntity<Object> deleteFinAccountAttribute(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountAuth")
	public ResponseEntity<Object> createFinAccountAuth(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="authorizationDate", required=false) Timestamp authorizationDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("amount",amount);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("authorizationDate",authorizationDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountAuth", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/assignGlRecToFinAccTrans")
	public ResponseEntity<Object> assignGlRecToFinAccTrans(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId, @RequestParam(value="glReconciliationId") String glReconciliationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("assignGlRecToFinAccTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeFinAccountTransFromReconciliation")
	public ResponseEntity<Object> removeFinAccountTransFromReconciliation(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeFinAccountTransFromReconciliation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/postFinAccountTransToGl")
	public ResponseEntity<Object> postFinAccountTransToGl(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId, @RequestParam(value="glAccountId") String glAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("postFinAccountTransToGl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkFinAccountBalance")
	public ResponseEntity<Object> checkFinAccountBalance(HttpSession session, @RequestParam(value="finAccountId") String finAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkFinAccountBalance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAssociatedAcctgTransEntriesWithFinAccountTrans")
	public ResponseEntity<Object> getAssociatedAcctgTransEntriesWithFinAccountTrans(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAssociatedAcctgTransEntriesWithFinAccountTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountTransAttribute")
	public ResponseEntity<Object> updateFinAccountTransAttribute(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountTransAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountTransType")
	public ResponseEntity<Object> updateFinAccountTransType(HttpSession session, @RequestParam(value="finAccountTransTypeId") String finAccountTransTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountTransType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/expirePaymentAssociationsOnFinAccountTransCancel")
	public ResponseEntity<Object> expirePaymentAssociationsOnFinAccountTransCancel(HttpSession session, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expirePaymentAssociationsOnFinAccountTransCancel", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbGcProcessor")
	public ResponseEntity<Object> ofbGcProcessor(HttpSession session, @RequestParam(value="billToParty") org.apache.ofbiz.entity.GenericValue billToParty, @RequestParam(value="orderId") String orderId, @RequestParam(value="processAmount") BigDecimal processAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="giftCard") org.apache.ofbiz.entity.GenericValue giftCard, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="billToEmail", required=false) org.apache.ofbiz.entity.GenericValue billToEmail, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="currency", required=false) String currency, @RequestParam(value="billingAddress", required=false) org.apache.ofbiz.entity.GenericValue billingAddress, @RequestParam(value="customerIpAddress", required=false) String customerIpAddress) {
		
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
			result = dispatcher.runSync("ofbGcProcessor", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccount")
	public ResponseEntity<Object> createFinAccount(HttpSession session, @RequestParam(value="finAccountTypeId", required=false) String finAccountTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="isRefundable", required=false) String isRefundable, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="postToGlAccountId", required=false) String postToGlAccountId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="finAccountName", required=false) String finAccountName, @RequestParam(value="finAccountPin", required=false) String finAccountPin, @RequestParam(value="replenishLevel", required=false) BigDecimal replenishLevel, @RequestParam(value="replenishPaymentId", required=false) String replenishPaymentId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="finAccountCode", required=false) String finAccountCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("isRefundable",isRefundable);
		paramMap.put("fromDate",fromDate);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("postToGlAccountId",postToGlAccountId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("finAccountName",finAccountName);
		paramMap.put("finAccountPin",finAccountPin);
		paramMap.put("replenishLevel",replenishLevel);
		paramMap.put("replenishPaymentId",replenishPaymentId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("finAccountCode",finAccountCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountTransType")
	public ResponseEntity<Object> deleteFinAccountTransType(HttpSession session, @RequestParam(value="finAccountTransTypeId") String finAccountTransTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountTransType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/finAccountReplenish")
	public ResponseEntity<Object> finAccountReplenish(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="productStoreId", required=false) String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("finAccountReplenish", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountTypeAttr")
	public ResponseEntity<Object> createFinAccountTypeAttr(HttpSession session, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description, @RequestParam(value="attrValue", required=false) String attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountType")
	public ResponseEntity<Object> createFinAccountType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="replenishEnumId", required=false) String replenishEnumId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="finAccountTypeId", required=false) String finAccountTypeId, @RequestParam(value="isRefundable", required=false) String isRefundable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("replenishEnumId",replenishEnumId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("isRefundable",isRefundable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccount")
	public ResponseEntity<Object> updateFinAccount(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="finAccountTypeId", required=false) String finAccountTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="isRefundable", required=false) String isRefundable, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="postToGlAccountId", required=false) String postToGlAccountId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="finAccountName", required=false) String finAccountName, @RequestParam(value="finAccountPin", required=false) String finAccountPin, @RequestParam(value="replenishLevel", required=false) BigDecimal replenishLevel, @RequestParam(value="replenishPaymentId", required=false) String replenishPaymentId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="finAccountCode", required=false) String finAccountCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("isRefundable",isRefundable);
		paramMap.put("fromDate",fromDate);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("postToGlAccountId",postToGlAccountId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("finAccountName",finAccountName);
		paramMap.put("finAccountPin",finAccountPin);
		paramMap.put("replenishLevel",replenishLevel);
		paramMap.put("replenishPaymentId",replenishPaymentId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("finAccountCode",finAccountCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccount")
	public ResponseEntity<Object> deleteFinAccount(HttpSession session, @RequestParam(value="finAccountId") String finAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkFinAccountStatus")
	public ResponseEntity<Object> checkFinAccountStatus(HttpSession session, @RequestParam(value="finAccountAuthId", required=false) String finAccountAuthId, @RequestParam(value="finAccountId", required=false) String finAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountAuthId",finAccountAuthId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkFinAccountStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountForStore")
	public ResponseEntity<Object> createFinAccountForStore(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="isRefundable", required=false) String isRefundable, @RequestParam(value="availableBalance", required=false) BigDecimal availableBalance, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="postToGlAccountId", required=false) String postToGlAccountId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="finAccountName", required=false) String finAccountName, @RequestParam(value="finAccountPin", required=false) String finAccountPin, @RequestParam(value="actualBalance", required=false) BigDecimal actualBalance, @RequestParam(value="replenishLevel", required=false) BigDecimal replenishLevel, @RequestParam(value="replenishPaymentId", required=false) String replenishPaymentId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="finAccountCode", required=false) String finAccountCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("isRefundable",isRefundable);
		paramMap.put("availableBalance",availableBalance);
		paramMap.put("fromDate",fromDate);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("postToGlAccountId",postToGlAccountId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("finAccountName",finAccountName);
		paramMap.put("finAccountPin",finAccountPin);
		paramMap.put("actualBalance",actualBalance);
		paramMap.put("replenishLevel",replenishLevel);
		paramMap.put("replenishPaymentId",replenishPaymentId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("finAccountCode",finAccountCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountForStore", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountAttribute")
	public ResponseEntity<Object> updateFinAccountAttribute(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountBalancesFromAuth")
	public ResponseEntity<Object> updateFinAccountBalancesFromAuth(HttpSession session, @RequestParam(value="finAccountAuthId") String finAccountAuthId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountAuthId",finAccountAuthId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountBalancesFromAuth", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountAttribute")
	public ResponseEntity<Object> createFinAccountAttribute(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountType")
	public ResponseEntity<Object> updateFinAccountType(HttpSession session, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="replenishEnumId", required=false) String replenishEnumId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="isRefundable", required=false) String isRefundable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("replenishEnumId",replenishEnumId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("isRefundable",isRefundable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountTransType")
	public ResponseEntity<Object> createFinAccountTransType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="finAccountTransTypeId", required=false) String finAccountTransTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountTransType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbGcReload")
	public ResponseEntity<Object> ofbGcReload(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="orderItem") org.apache.ofbiz.entity.GenericValue orderItem, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId) {
		
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
			result = dispatcher.runSync("ofbGcReload", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createServiceCredit")
	public ResponseEntity<Object> createServiceCredit(HttpSession session, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="partyId") String partyId, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="finAccountId", required=false) String finAccountId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="finAccountName", required=false) String finAccountName, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="productStoreId", required=false) String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("partyId",partyId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("comments",comments);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("finAccountName",finAccountName);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createServiceCredit", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbGcRelease")
	public ResponseEntity<Object> ofbGcRelease(HttpSession session, @RequestParam(value="releaseAmount") BigDecimal releaseAmount, @RequestParam(value="orderPaymentPreference") org.apache.ofbiz.entity.GenericValue orderPaymentPreference, @RequestParam(value="paymentConfig", required=false) String paymentConfig, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="authTrans", required=false) org.apache.ofbiz.entity.GenericValue authTrans, @RequestParam(value="currency", required=false) String currency) {
		
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
			result = dispatcher.runSync("ofbGcRelease", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountType")
	public ResponseEntity<Object> deleteFinAccountType(HttpSession session, @RequestParam(value="finAccountTypeId") String finAccountTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reconcileDepositFinAcctgTrans")
	public ResponseEntity<Object> reconcileDepositFinAcctgTrans(HttpSession session, @RequestParam(value="finAccountTrans") org.apache.ofbiz.entity.GenericValue finAccountTrans) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTrans",finAccountTrans);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reconcileDepositFinAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ofbGcPurchase")
	public ResponseEntity<Object> ofbGcPurchase(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="orderItem") org.apache.ofbiz.entity.GenericValue orderItem, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId) {
		
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
			result = dispatcher.runSync("ofbGcPurchase", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/isGlReconciliationReconciled")
	public ResponseEntity<Object> isGlReconciliationReconciled(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("isGlReconciliationReconciled", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountTransTypeAttr")
	public ResponseEntity<Object> createFinAccountTransTypeAttr(HttpSession session, @RequestParam(value="finAccountTransTypeId") String finAccountTransTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountTransTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkGiftCertificateBalance")
	public ResponseEntity<Object> checkGiftCertificateBalance(HttpSession session, @RequestParam(value="pinNumber") String pinNumber, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="currency", required=false) String currency) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pinNumber",pinNumber);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("currency",currency);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkGiftCertificateBalance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/autoFinAccountReconciliation")
	public ResponseEntity<Object> autoFinAccountReconciliation(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoFinAccountReconciliation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getFinAccountTransListAndTotals")
	public ResponseEntity<Object> getFinAccountTransListAndTotals(HttpSession session, @RequestParam(value="finAccountId") String finAccountId, @RequestParam(value="fromEntryDate", required=false) Timestamp fromEntryDate, @RequestParam(value="finAccountTransTypeId", required=false) String finAccountTransTypeId, @RequestParam(value="thruEntryDate", required=false) Timestamp thruEntryDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="glReconciliationId", required=false) String glReconciliationId, @RequestParam(value="fromTransactionDate", required=false) Timestamp fromTransactionDate, @RequestParam(value="openingBalance", required=false) BigDecimal openingBalance, @RequestParam(value="thruTransactionDate", required=false) Timestamp thruTransactionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountId",finAccountId);
		paramMap.put("fromEntryDate",fromEntryDate);
		paramMap.put("finAccountTransTypeId",finAccountTransTypeId);
		paramMap.put("thruEntryDate",thruEntryDate);
		paramMap.put("statusId",statusId);
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("fromTransactionDate",fromTransactionDate);
		paramMap.put("openingBalance",openingBalance);
		paramMap.put("thruTransactionDate",thruTransactionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getFinAccountTransListAndTotals", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountTransAttribute")
	public ResponseEntity<Object> deleteFinAccountTransAttribute(HttpSession session, @RequestParam(value="finAccountTransId") String finAccountTransId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountTransAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
