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
@RequestMapping("/service/accountingPaymentgateway")
public class AccountingPaymentgatewayServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentGatewayConfigSagePay")
	public ResponseEntity<Object> deletePaymentGatewayConfigSagePay(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentGatewayConfigSagePay", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGatewayConfig")
	public ResponseEntity<Object> createPaymentGatewayConfig(HttpSession session, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="paymentGatewayConfigTypeId", required=false) String paymentGatewayConfigTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("paymentGatewayConfigTypeId",paymentGatewayConfigTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGatewayConfig", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigWorldPay")
	public ResponseEntity<Object> updatePaymentGatewayConfigWorldPay(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="authMode", required=false) String authMode, @RequestParam(value="instId", required=false) Long instId, @RequestParam(value="redirectUrl", required=false) Long redirectUrl, @RequestParam(value="hideContact", required=false) String hideContact, @RequestParam(value="testMode", required=false) Long testMode, @RequestParam(value="fixContact", required=false) String fixContact, @RequestParam(value="langId", required=false) String langId, @RequestParam(value="hideCurrency", required=false) String hideCurrency, @RequestParam(value="noLanguageMenu", required=false) String noLanguageMenu, @RequestParam(value="withDelivery", required=false) String withDelivery) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("authMode",authMode);
		paramMap.put("instId",instId);
		paramMap.put("redirectUrl",redirectUrl);
		paramMap.put("hideContact",hideContact);
		paramMap.put("testMode",testMode);
		paramMap.put("fixContact",fixContact);
		paramMap.put("langId",langId);
		paramMap.put("hideCurrency",hideCurrency);
		paramMap.put("noLanguageMenu",noLanguageMenu);
		paramMap.put("withDelivery",withDelivery);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigWorldPay", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentGatewayConfigAuthorizeNet")
	public ResponseEntity<Object> deletePaymentGatewayConfigAuthorizeNet(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentGatewayConfigAuthorizeNet", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigPayflowPro")
	public ResponseEntity<Object> updatePaymentGatewayConfigPayflowPro(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="redirectUrl", required=false) Long redirectUrl, @RequestParam(value="proxyPassword", required=false) Long proxyPassword, @RequestParam(value="cancelReturnUrl", required=false) Long cancelReturnUrl, @RequestParam(value="enableTransmit", required=false) Long enableTransmit, @RequestParam(value="checkAvs", required=false) String checkAvs, @RequestParam(value="stackTraceOn", required=false) String stackTraceOn, @RequestParam(value="userId", required=false) String userId, @RequestParam(value="checkCvv2", required=false) String checkCvv2, @RequestParam(value="timeout", required=false) Long timeout, @RequestParam(value="proxyPort", required=false) Long proxyPort, @RequestParam(value="proxyLogon", required=false) Long proxyLogon, @RequestParam(value="certsPath", required=false) Long certsPath, @RequestParam(value="partner", required=false) String partner, @RequestParam(value="vendor", required=false) String vendor, @RequestParam(value="logFileName", required=false) Long logFileName, @RequestParam(value="hostPort", required=false) Long hostPort, @RequestParam(value="hostAddress", required=false) Long hostAddress, @RequestParam(value="preAuth", required=false) String preAuth, @RequestParam(value="proxyAddress", required=false) Long proxyAddress, @RequestParam(value="pwd", required=false) Long pwd, @RequestParam(value="returnUrl", required=false) Long returnUrl, @RequestParam(value="loggingLevel", required=false) Long loggingLevel, @RequestParam(value="maxLogFileSize", required=false) Long maxLogFileSize) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("redirectUrl",redirectUrl);
		paramMap.put("proxyPassword",proxyPassword);
		paramMap.put("cancelReturnUrl",cancelReturnUrl);
		paramMap.put("enableTransmit",enableTransmit);
		paramMap.put("checkAvs",checkAvs);
		paramMap.put("stackTraceOn",stackTraceOn);
		paramMap.put("userId",userId);
		paramMap.put("checkCvv2",checkCvv2);
		paramMap.put("timeout",timeout);
		paramMap.put("proxyPort",proxyPort);
		paramMap.put("proxyLogon",proxyLogon);
		paramMap.put("certsPath",certsPath);
		paramMap.put("partner",partner);
		paramMap.put("vendor",vendor);
		paramMap.put("logFileName",logFileName);
		paramMap.put("hostPort",hostPort);
		paramMap.put("hostAddress",hostAddress);
		paramMap.put("preAuth",preAuth);
		paramMap.put("proxyAddress",proxyAddress);
		paramMap.put("pwd",pwd);
		paramMap.put("returnUrl",returnUrl);
		paramMap.put("loggingLevel",loggingLevel);
		paramMap.put("maxLogFileSize",maxLogFileSize);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigPayflowPro", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfig")
	public ResponseEntity<Object> updatePaymentGatewayConfig(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="paymentGatewayConfigTypeId", required=false) String paymentGatewayConfigTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("paymentGatewayConfigTypeId",paymentGatewayConfigTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfig", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGatewayConfigClearCommerce")
	public ResponseEntity<Object> createPaymentGatewayConfigClearCommerce(HttpSession session, @RequestParam(value="sourceId", required=false) String sourceId, @RequestParam(value="effectiveAlias", required=false) String effectiveAlias, @RequestParam(value="clientId", required=false) String clientId, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="userAlias", required=false) String userAlias, @RequestParam(value="serverURL", required=false) Long serverURL, @RequestParam(value="pwd", required=false) Long pwd, @RequestParam(value="processMode", required=false) String processMode, @RequestParam(value="enableCVM", required=false) String enableCVM, @RequestParam(value="username", required=false) String username) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sourceId",sourceId);
		paramMap.put("effectiveAlias",effectiveAlias);
		paramMap.put("clientId",clientId);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("groupId",groupId);
		paramMap.put("userAlias",userAlias);
		paramMap.put("serverURL",serverURL);
		paramMap.put("pwd",pwd);
		paramMap.put("processMode",processMode);
		paramMap.put("enableCVM",enableCVM);
		paramMap.put("username",username);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGatewayConfigClearCommerce", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigSagePay")
	public ResponseEntity<Object> updatePaymentGatewayConfigSagePay(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="authenticationTransType", required=false) String authenticationTransType, @RequestParam(value="voidUrl", required=false) String voidUrl, @RequestParam(value="testingHost", required=false) String testingHost, @RequestParam(value="sagePayMode", required=false) String sagePayMode, @RequestParam(value="releaseTransType", required=false) String releaseTransType, @RequestParam(value="authoriseTransType", required=false) String authoriseTransType, @RequestParam(value="vendor", required=false) String vendor, @RequestParam(value="productionHost", required=false) String productionHost, @RequestParam(value="refundUrl", required=false) String refundUrl, @RequestParam(value="protocolVersion", required=false) String protocolVersion, @RequestParam(value="releaseUrl", required=false) String releaseUrl, @RequestParam(value="authenticationUrl", required=false) String authenticationUrl, @RequestParam(value="authoriseUrl", required=false) String authoriseUrl) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("authenticationTransType",authenticationTransType);
		paramMap.put("voidUrl",voidUrl);
		paramMap.put("testingHost",testingHost);
		paramMap.put("sagePayMode",sagePayMode);
		paramMap.put("releaseTransType",releaseTransType);
		paramMap.put("authoriseTransType",authoriseTransType);
		paramMap.put("vendor",vendor);
		paramMap.put("productionHost",productionHost);
		paramMap.put("refundUrl",refundUrl);
		paramMap.put("protocolVersion",protocolVersion);
		paramMap.put("releaseUrl",releaseUrl);
		paramMap.put("authenticationUrl",authenticationUrl);
		paramMap.put("authoriseUrl",authoriseUrl);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigSagePay", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigType")
	public ResponseEntity<Object> updatePaymentGatewayConfigType(HttpSession session, @RequestParam(value="paymentGatewayConfigTypeId") String paymentGatewayConfigTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigTypeId",paymentGatewayConfigTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigCyberSource")
	public ResponseEntity<Object> updatePaymentGatewayConfigCyberSource(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="fraudScore", required=false) String fraudScore, @RequestParam(value="production", required=false) String production, @RequestParam(value="keysDir", required=false) Long keysDir, @RequestParam(value="merchantContact", required=false) Long merchantContact, @RequestParam(value="autoBill", required=false) String autoBill, @RequestParam(value="enableDav", required=false) String enableDav, @RequestParam(value="apiVersion", required=false) String apiVersion, @RequestParam(value="keysFile", required=false) Long keysFile, @RequestParam(value="merchantDescr", required=false) Long merchantDescr, @RequestParam(value="logEnabled", required=false) String logEnabled, @RequestParam(value="merchantId", required=false) Long merchantId, @RequestParam(value="logFile", required=false) Long logFile, @RequestParam(value="logSize", required=false) Long logSize, @RequestParam(value="ignoreAvs", required=false) String ignoreAvs, @RequestParam(value="disableBillAvs", required=false) String disableBillAvs, @RequestParam(value="logDir", required=false) Long logDir, @RequestParam(value="avsDeclineCodes", required=false) Long avsDeclineCodes) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("fraudScore",fraudScore);
		paramMap.put("production",production);
		paramMap.put("keysDir",keysDir);
		paramMap.put("merchantContact",merchantContact);
		paramMap.put("autoBill",autoBill);
		paramMap.put("enableDav",enableDav);
		paramMap.put("apiVersion",apiVersion);
		paramMap.put("keysFile",keysFile);
		paramMap.put("merchantDescr",merchantDescr);
		paramMap.put("logEnabled",logEnabled);
		paramMap.put("merchantId",merchantId);
		paramMap.put("logFile",logFile);
		paramMap.put("logSize",logSize);
		paramMap.put("ignoreAvs",ignoreAvs);
		paramMap.put("disableBillAvs",disableBillAvs);
		paramMap.put("logDir",logDir);
		paramMap.put("avsDeclineCodes",avsDeclineCodes);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigCyberSource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigAuthorizeNet")
	public ResponseEntity<Object> updatePaymentGatewayConfigAuthorizeNet(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="cpVersion", required=false) String cpVersion, @RequestParam(value="method", required=false) String method, @RequestParam(value="transDescription", required=false) Long transDescription, @RequestParam(value="cpMarketType", required=false) String cpMarketType, @RequestParam(value="certificateAlias", required=false) Long certificateAlias, @RequestParam(value="delimiterChar", required=false) String delimiterChar, @RequestParam(value="emailCustomer", required=false) String emailCustomer, @RequestParam(value="transactionUrl", required=false) Long transactionUrl, @RequestParam(value="userId", required=false) Long userId, @RequestParam(value="delimitedData", required=false) String delimitedData, @RequestParam(value="tranKey", required=false) Long tranKey, @RequestParam(value="apiVersion", required=false) String apiVersion, @RequestParam(value="emailMerchant", required=false) String emailMerchant, @RequestParam(value="testMode", required=false) String testMode, @RequestParam(value="relayResponse", required=false) String relayResponse, @RequestParam(value="pwd", required=false) Long pwd, @RequestParam(value="duplicateWindow", required=false) Long duplicateWindow, @RequestParam(value="cpDeviceType", required=false) String cpDeviceType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("cpVersion",cpVersion);
		paramMap.put("method",method);
		paramMap.put("transDescription",transDescription);
		paramMap.put("cpMarketType",cpMarketType);
		paramMap.put("certificateAlias",certificateAlias);
		paramMap.put("delimiterChar",delimiterChar);
		paramMap.put("emailCustomer",emailCustomer);
		paramMap.put("transactionUrl",transactionUrl);
		paramMap.put("userId",userId);
		paramMap.put("delimitedData",delimitedData);
		paramMap.put("tranKey",tranKey);
		paramMap.put("apiVersion",apiVersion);
		paramMap.put("emailMerchant",emailMerchant);
		paramMap.put("testMode",testMode);
		paramMap.put("relayResponse",relayResponse);
		paramMap.put("pwd",pwd);
		paramMap.put("duplicateWindow",duplicateWindow);
		paramMap.put("cpDeviceType",cpDeviceType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigAuthorizeNet", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigEway")
	public ResponseEntity<Object> updatePaymentGatewayConfigEway(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="enableCvn", required=false) String enableCvn, @RequestParam(value="testMode", required=false) String testMode, @RequestParam(value="enableBeagle", required=false) String enableBeagle, @RequestParam(value="customerId", required=false) Long customerId, @RequestParam(value="refundPwd", required=false) Long refundPwd) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("enableCvn",enableCvn);
		paramMap.put("testMode",testMode);
		paramMap.put("enableBeagle",enableBeagle);
		paramMap.put("customerId",customerId);
		paramMap.put("refundPwd",refundPwd);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigEway", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigSecurePay")
	public ResponseEntity<Object> updatePaymentGatewayConfigSecurePay(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="merchantId", required=false) Long merchantId, @RequestParam(value="serverURL", required=false) Long serverURL, @RequestParam(value="pwd", required=false) Long pwd, @RequestParam(value="processTimeout", required=false) Long processTimeout, @RequestParam(value="enableAmountRound", required=false) String enableAmountRound) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("merchantId",merchantId);
		paramMap.put("serverURL",serverURL);
		paramMap.put("pwd",pwd);
		paramMap.put("processTimeout",processTimeout);
		paramMap.put("enableAmountRound",enableAmountRound);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigSecurePay", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigiDEAL")
	public ResponseEntity<Object> updatePaymentGatewayConfigiDEAL(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="expirationPeriod", required=false) Long expirationPeriod, @RequestParam(value="merchantReturnURL", required=false) Long merchantReturnURL, @RequestParam(value="merchantId", required=false) Long merchantId, @RequestParam(value="acquirerKeyStoreFilename", required=false) Long acquirerKeyStoreFilename, @RequestParam(value="merchantSubId", required=false) Long merchantSubId, @RequestParam(value="merchantKeyStorePassword", required=false) Long merchantKeyStorePassword, @RequestParam(value="acquirerURL", required=false) Long acquirerURL, @RequestParam(value="acquirerTimeout", required=false) Long acquirerTimeout, @RequestParam(value="privateCert", required=false) Long privateCert, @RequestParam(value="acquirerKeyStorePassword", required=false) Long acquirerKeyStorePassword, @RequestParam(value="merchantKeyStoreFilename", required=false) Long merchantKeyStoreFilename) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("expirationPeriod",expirationPeriod);
		paramMap.put("merchantReturnURL",merchantReturnURL);
		paramMap.put("merchantId",merchantId);
		paramMap.put("acquirerKeyStoreFilename",acquirerKeyStoreFilename);
		paramMap.put("merchantSubId",merchantSubId);
		paramMap.put("merchantKeyStorePassword",merchantKeyStorePassword);
		paramMap.put("acquirerURL",acquirerURL);
		paramMap.put("acquirerTimeout",acquirerTimeout);
		paramMap.put("privateCert",privateCert);
		paramMap.put("acquirerKeyStorePassword",acquirerKeyStorePassword);
		paramMap.put("merchantKeyStoreFilename",merchantKeyStoreFilename);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigiDEAL", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigClearCommerce")
	public ResponseEntity<Object> updatePaymentGatewayConfigClearCommerce(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="sourceId", required=false) String sourceId, @RequestParam(value="effectiveAlias", required=false) String effectiveAlias, @RequestParam(value="clientId", required=false) String clientId, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="userAlias", required=false) String userAlias, @RequestParam(value="serverURL", required=false) Long serverURL, @RequestParam(value="pwd", required=false) Long pwd, @RequestParam(value="processMode", required=false) String processMode, @RequestParam(value="enableCVM", required=false) String enableCVM, @RequestParam(value="username", required=false) String username) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("sourceId",sourceId);
		paramMap.put("effectiveAlias",effectiveAlias);
		paramMap.put("clientId",clientId);
		paramMap.put("groupId",groupId);
		paramMap.put("userAlias",userAlias);
		paramMap.put("serverURL",serverURL);
		paramMap.put("pwd",pwd);
		paramMap.put("processMode",processMode);
		paramMap.put("enableCVM",enableCVM);
		paramMap.put("username",username);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigClearCommerce", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGatewayConfigSagePay")
	public ResponseEntity<Object> createPaymentGatewayConfigSagePay(HttpSession session, @RequestParam(value="authenticationTransType", required=false) String authenticationTransType, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="voidUrl", required=false) String voidUrl, @RequestParam(value="testingHost", required=false) String testingHost, @RequestParam(value="sagePayMode", required=false) String sagePayMode, @RequestParam(value="releaseTransType", required=false) String releaseTransType, @RequestParam(value="authoriseTransType", required=false) String authoriseTransType, @RequestParam(value="vendor", required=false) String vendor, @RequestParam(value="productionHost", required=false) String productionHost, @RequestParam(value="refundUrl", required=false) String refundUrl, @RequestParam(value="protocolVersion", required=false) String protocolVersion, @RequestParam(value="releaseUrl", required=false) String releaseUrl, @RequestParam(value="authenticationUrl", required=false) String authenticationUrl, @RequestParam(value="authoriseUrl", required=false) String authoriseUrl) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("authenticationTransType",authenticationTransType);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("voidUrl",voidUrl);
		paramMap.put("testingHost",testingHost);
		paramMap.put("sagePayMode",sagePayMode);
		paramMap.put("releaseTransType",releaseTransType);
		paramMap.put("authoriseTransType",authoriseTransType);
		paramMap.put("vendor",vendor);
		paramMap.put("productionHost",productionHost);
		paramMap.put("refundUrl",refundUrl);
		paramMap.put("protocolVersion",protocolVersion);
		paramMap.put("releaseUrl",releaseUrl);
		paramMap.put("authenticationUrl",authenticationUrl);
		paramMap.put("authoriseUrl",authoriseUrl);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGatewayConfigSagePay", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentGatewayConfigPayPal")
	public ResponseEntity<Object> updatePaymentGatewayConfigPayPal(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId, @RequestParam(value="businessEmail", required=false) Long businessEmail, @RequestParam(value="apiUserName", required=false) String apiUserName, @RequestParam(value="redirectUrl", required=false) Long redirectUrl, @RequestParam(value="confirmTemplate", required=false) Long confirmTemplate, @RequestParam(value="cancelReturnUrl", required=false) Long cancelReturnUrl, @RequestParam(value="apiPassword", required=false) String apiPassword, @RequestParam(value="apiSignature", required=false) String apiSignature, @RequestParam(value="imageUrl", required=false) Long imageUrl, @RequestParam(value="requireConfirmedShipping", required=false) String requireConfirmedShipping, @RequestParam(value="notifyUrl", required=false) Long notifyUrl, @RequestParam(value="apiEnvironment", required=false) String apiEnvironment, @RequestParam(value="confirmUrl", required=false) Long confirmUrl, @RequestParam(value="returnUrl", required=false) Long returnUrl, @RequestParam(value="shippingCallbackUrl", required=false) String shippingCallbackUrl) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("businessEmail",businessEmail);
		paramMap.put("apiUserName",apiUserName);
		paramMap.put("redirectUrl",redirectUrl);
		paramMap.put("confirmTemplate",confirmTemplate);
		paramMap.put("cancelReturnUrl",cancelReturnUrl);
		paramMap.put("apiPassword",apiPassword);
		paramMap.put("apiSignature",apiSignature);
		paramMap.put("imageUrl",imageUrl);
		paramMap.put("requireConfirmedShipping",requireConfirmedShipping);
		paramMap.put("notifyUrl",notifyUrl);
		paramMap.put("apiEnvironment",apiEnvironment);
		paramMap.put("confirmUrl",confirmUrl);
		paramMap.put("returnUrl",returnUrl);
		paramMap.put("shippingCallbackUrl",shippingCallbackUrl);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePaymentGatewayConfigPayPal", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentGatewayConfig")
	public ResponseEntity<Object> deletePaymentGatewayConfig(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentGatewayConfig", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPaymentGatewayConfigAuthorizeNet")
	public ResponseEntity<Object> createPaymentGatewayConfigAuthorizeNet(HttpSession session, @RequestParam(value="cpVersion", required=false) String cpVersion, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="method", required=false) String method, @RequestParam(value="transDescription", required=false) Long transDescription, @RequestParam(value="cpMarketType", required=false) String cpMarketType, @RequestParam(value="certificateAlias", required=false) Long certificateAlias, @RequestParam(value="delimiterChar", required=false) String delimiterChar, @RequestParam(value="emailCustomer", required=false) String emailCustomer, @RequestParam(value="transactionUrl", required=false) Long transactionUrl, @RequestParam(value="userId", required=false) Long userId, @RequestParam(value="delimitedData", required=false) String delimitedData, @RequestParam(value="tranKey", required=false) Long tranKey, @RequestParam(value="apiVersion", required=false) String apiVersion, @RequestParam(value="emailMerchant", required=false) String emailMerchant, @RequestParam(value="testMode", required=false) String testMode, @RequestParam(value="relayResponse", required=false) String relayResponse, @RequestParam(value="pwd", required=false) Long pwd, @RequestParam(value="duplicateWindow", required=false) Long duplicateWindow, @RequestParam(value="cpDeviceType", required=false) String cpDeviceType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cpVersion",cpVersion);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("method",method);
		paramMap.put("transDescription",transDescription);
		paramMap.put("cpMarketType",cpMarketType);
		paramMap.put("certificateAlias",certificateAlias);
		paramMap.put("delimiterChar",delimiterChar);
		paramMap.put("emailCustomer",emailCustomer);
		paramMap.put("transactionUrl",transactionUrl);
		paramMap.put("userId",userId);
		paramMap.put("delimitedData",delimitedData);
		paramMap.put("tranKey",tranKey);
		paramMap.put("apiVersion",apiVersion);
		paramMap.put("emailMerchant",emailMerchant);
		paramMap.put("testMode",testMode);
		paramMap.put("relayResponse",relayResponse);
		paramMap.put("pwd",pwd);
		paramMap.put("duplicateWindow",duplicateWindow);
		paramMap.put("cpDeviceType",cpDeviceType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPaymentGatewayConfigAuthorizeNet", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePaymentGatewayConfigClearCommerce")
	public ResponseEntity<Object> deletePaymentGatewayConfigClearCommerce(HttpSession session, @RequestParam(value="paymentGatewayConfigId") String paymentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePaymentGatewayConfigClearCommerce", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
