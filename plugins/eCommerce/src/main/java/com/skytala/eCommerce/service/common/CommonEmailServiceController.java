package com.skytala.eCommerce.service.common;

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
@RequestMapping("/service/commonEmail")
public class CommonEmailServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmailTemplateSetting")
	public ResponseEntity<Object> deleteEmailTemplateSetting(HttpSession session, @RequestParam(value="emailTemplateSettingId") String emailTemplateSettingId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailTemplateSettingId",emailTemplateSettingId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmailTemplateSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailHiddenInLogFromScreen")
	public ResponseEntity<Object> sendMailHiddenInLogFromScreen(HttpSession session, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="bodyText", required=false) String bodyText, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="bodyParameters", required=false) Map bodyParameters, @RequestParam(value="xslfoAttachScreenLocationList", required=false) List xslfoAttachScreenLocationList, @RequestParam(value="port", required=false) String port, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="attachmentNameList", required=false) List attachmentNameList, @RequestParam(value="attachmentName", required=false) String attachmentName, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="bodyScreenUri", required=false) String bodyScreenUri) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("xslfoAttachScreenLocation",xslfoAttachScreenLocation);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("authUser",authUser);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("bodyText",bodyText);
		paramMap.put("emailType",emailType);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("bodyParameters",bodyParameters);
		paramMap.put("xslfoAttachScreenLocationList",xslfoAttachScreenLocationList);
		paramMap.put("port",port);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendType",sendType);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("attachmentNameList",attachmentNameList);
		paramMap.put("attachmentName",attachmentName);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("bodyScreenUri",bodyScreenUri);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailHiddenInLogFromScreen", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMail")
	public ResponseEntity<Object> sendMail(HttpSession session, @RequestParam(value="body") String body, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("body",body);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendNotificationInterface")
	public ResponseEntity<Object> sendNotificationInterface(HttpSession session, @RequestParam(value="templateName") String templateName, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="templateData", required=false) Map templateData, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="body", required=false) String body, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="baseUrl", required=false) String baseUrl, @RequestParam(value="port", required=false) String port, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("templateName",templateName);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("templateData",templateData);
		paramMap.put("authUser",authUser);
		paramMap.put("body",body);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("emailType",emailType);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("baseUrl",baseUrl);
		paramMap.put("port",port);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendType",sendType);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendNotificationInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailMultiPartHiddenInLog")
	public ResponseEntity<Object> sendMailMultiPartHiddenInLog(HttpSession session, @RequestParam(value="bodyParts") java.util.List bodyParts, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bodyParts",bodyParts);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailMultiPartHiddenInLog", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/prepareNotificationInterface")
	public ResponseEntity<Object> prepareNotificationInterface(HttpSession session, @RequestParam(value="templateName") String templateName, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="templateData", required=false) Map templateData, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="body", required=false) String body, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="baseUrl", required=false) String baseUrl, @RequestParam(value="port", required=false) String port, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("templateName",templateName);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("templateData",templateData);
		paramMap.put("authUser",authUser);
		paramMap.put("body",body);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("emailType",emailType);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("baseUrl",baseUrl);
		paramMap.put("port",port);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendType",sendType);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("prepareNotificationInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailMultiPart")
	public ResponseEntity<Object> sendMailMultiPart(HttpSession session, @RequestParam(value="bodyParts") java.util.List bodyParts, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bodyParts",bodyParts);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailMultiPart", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendGenericNotificationEmail")
	public ResponseEntity<Object> sendGenericNotificationEmail(HttpSession session, @RequestParam(value="templateName") String templateName, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="templateData", required=false) Map templateData, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="body", required=false) String body, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="baseUrl", required=false) String baseUrl, @RequestParam(value="port", required=false) String port, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("templateName",templateName);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("templateData",templateData);
		paramMap.put("authUser",authUser);
		paramMap.put("body",body);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("emailType",emailType);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("baseUrl",baseUrl);
		paramMap.put("port",port);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendType",sendType);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendGenericNotificationEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailMultiPartInterface")
	public ResponseEntity<Object> sendMailMultiPartInterface(HttpSession session, @RequestParam(value="bodyParts") java.util.List bodyParts, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bodyParts",bodyParts);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailMultiPartInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailFromScreenInterface")
	public ResponseEntity<Object> sendMailFromScreenInterface(HttpSession session, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="bodyText", required=false) String bodyText, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="bodyParameters", required=false) Map bodyParameters, @RequestParam(value="xslfoAttachScreenLocationList", required=false) List xslfoAttachScreenLocationList, @RequestParam(value="port", required=false) String port, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="attachmentNameList", required=false) List attachmentNameList, @RequestParam(value="attachmentName", required=false) String attachmentName, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="bodyScreenUri", required=false) String bodyScreenUri) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("xslfoAttachScreenLocation",xslfoAttachScreenLocation);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("authUser",authUser);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("bodyText",bodyText);
		paramMap.put("emailType",emailType);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("bodyParameters",bodyParameters);
		paramMap.put("xslfoAttachScreenLocationList",xslfoAttachScreenLocationList);
		paramMap.put("port",port);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendType",sendType);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("attachmentNameList",attachmentNameList);
		paramMap.put("attachmentName",attachmentName);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("bodyScreenUri",bodyScreenUri);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailFromScreenInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailFromScreen")
	public ResponseEntity<Object> sendMailFromScreen(HttpSession session, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="bodyText", required=false) String bodyText, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="hideInLog", required=false) Boolean hideInLog, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="bodyParameters", required=false) Map bodyParameters, @RequestParam(value="xslfoAttachScreenLocationList", required=false) List xslfoAttachScreenLocationList, @RequestParam(value="port", required=false) String port, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="attachmentNameList", required=false) List attachmentNameList, @RequestParam(value="attachmentName", required=false) String attachmentName, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="bodyScreenUri", required=false) String bodyScreenUri) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("xslfoAttachScreenLocation",xslfoAttachScreenLocation);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("authUser",authUser);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("bodyText",bodyText);
		paramMap.put("emailType",emailType);
		paramMap.put("hideInLog",hideInLog);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("bodyParameters",bodyParameters);
		paramMap.put("xslfoAttachScreenLocationList",xslfoAttachScreenLocationList);
		paramMap.put("port",port);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendType",sendType);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("attachmentNameList",attachmentNameList);
		paramMap.put("attachmentName",attachmentName);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("bodyScreenUri",bodyScreenUri);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailFromScreen", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailInterface")
	public ResponseEntity<Object> sendMailInterface(HttpSession session, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/storeForwardedEmail")
	public ResponseEntity<Object> storeForwardedEmail(HttpSession session, @RequestParam(value="messageWrapper") org.apache.ofbiz.service.mail.MimeMessageWrapper messageWrapper) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("messageWrapper",messageWrapper);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("storeForwardedEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailOnePartInterface")
	public ResponseEntity<Object> sendMailOnePartInterface(HttpSession session, @RequestParam(value="sendTo") String sendTo, @RequestParam(value="subject") String subject, @RequestParam(value="body") String body, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sendTo",sendTo);
		paramMap.put("subject",subject);
		paramMap.put("body",body);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailOnePartInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmailTemplateSetting")
	public ResponseEntity<Object> updateEmailTemplateSetting(HttpSession session, @RequestParam(value="emailTemplateSettingId") String emailTemplateSettingId, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="bodyScreenLocation", required=false) String bodyScreenLocation, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="description", required=false) String description, @RequestParam(value="fromAddress", required=false) String fromAddress, @RequestParam(value="bccAddress", required=false) String bccAddress, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="ccAddress", required=false) String ccAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailTemplateSettingId",emailTemplateSettingId);
		paramMap.put("xslfoAttachScreenLocation",xslfoAttachScreenLocation);
		paramMap.put("emailType",emailType);
		paramMap.put("bodyScreenLocation",bodyScreenLocation);
		paramMap.put("subject",subject);
		paramMap.put("description",description);
		paramMap.put("fromAddress",fromAddress);
		paramMap.put("bccAddress",bccAddress);
		paramMap.put("contentType",contentType);
		paramMap.put("ccAddress",ccAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmailTemplateSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEmailTemplateSetting")
	public ResponseEntity<Object> createEmailTemplateSetting(HttpSession session, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="bodyScreenLocation", required=false) String bodyScreenLocation, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="description", required=false) String description, @RequestParam(value="fromAddress", required=false) String fromAddress, @RequestParam(value="bccAddress", required=false) String bccAddress, @RequestParam(value="emailTemplateSettingId", required=false) String emailTemplateSettingId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="ccAddress", required=false) String ccAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("xslfoAttachScreenLocation",xslfoAttachScreenLocation);
		paramMap.put("emailType",emailType);
		paramMap.put("bodyScreenLocation",bodyScreenLocation);
		paramMap.put("subject",subject);
		paramMap.put("description",description);
		paramMap.put("fromAddress",fromAddress);
		paramMap.put("bccAddress",bccAddress);
		paramMap.put("emailTemplateSettingId",emailTemplateSettingId);
		paramMap.put("contentType",contentType);
		paramMap.put("ccAddress",ccAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmailTemplateSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailHiddenInLog")
	public ResponseEntity<Object> sendMailHiddenInLog(HttpSession session, @RequestParam(value="body") String body, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="hideInLog", required=false) Boolean hideInLog, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("body",body);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("hideInLog",hideInLog);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailHiddenInLog", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailFromUrl")
	public ResponseEntity<Object> sendMailFromUrl(HttpSession session, @RequestParam(value="bodyUrl") String bodyUrl, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="bodyUrlParameters", required=false) Map bodyUrlParameters, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="port", required=false) String port, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bodyUrl",bodyUrl);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("subject",subject);
		paramMap.put("bodyUrlParameters",bodyUrlParameters);
		paramMap.put("sendCc",sendCc);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("authUser",authUser);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("port",port);
		paramMap.put("emailType",emailType);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("sendType",sendType);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailFromUrl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendMailFromTemplateSetting")
	public ResponseEntity<Object> sendMailFromTemplateSetting(HttpSession session, @RequestParam(value="emailTemplateSettingId") String emailTemplateSettingId, @RequestParam(value="authPass", required=false) String authPass, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="sendFrom", required=false) String sendFrom, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="sendCc", required=false) String sendCc, @RequestParam(value="authUser", required=false) String authUser, @RequestParam(value="sendPartial", required=false) Boolean sendPartial, @RequestParam(value="bodyText", required=false) String bodyText, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="sendBcc", required=false) String sendBcc, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="sendVia", required=false) String sendVia, @RequestParam(value="socketFactoryFallback", required=false) String socketFactoryFallback, @RequestParam(value="messageId", required=false) String messageId, @RequestParam(value="socketFactoryPort", required=false) String socketFactoryPort, @RequestParam(value="startTLSEnabled", required=false) Boolean startTLSEnabled, @RequestParam(value="bodyParameters", required=false) Map bodyParameters, @RequestParam(value="port", required=false) String port, @RequestParam(value="socketFactoryClass", required=false) String socketFactoryClass, @RequestParam(value="sendType", required=false) String sendType, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="attachmentName", required=false) String attachmentName, @RequestParam(value="sendFailureNotification", required=false) Boolean sendFailureNotification) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailTemplateSettingId",emailTemplateSettingId);
		paramMap.put("authPass",authPass);
		paramMap.put("orderId",orderId);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("subject",subject);
		paramMap.put("sendCc",sendCc);
		paramMap.put("authUser",authUser);
		paramMap.put("sendPartial",sendPartial);
		paramMap.put("bodyText",bodyText);
		paramMap.put("emailType",emailType);
		paramMap.put("sendBcc",sendBcc);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("sendVia",sendVia);
		paramMap.put("socketFactoryFallback",socketFactoryFallback);
		paramMap.put("messageId",messageId);
		paramMap.put("socketFactoryPort",socketFactoryPort);
		paramMap.put("startTLSEnabled",startTLSEnabled);
		paramMap.put("bodyParameters",bodyParameters);
		paramMap.put("port",port);
		paramMap.put("socketFactoryClass",socketFactoryClass);
		paramMap.put("sendType",sendType);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("attachmentName",attachmentName);
		paramMap.put("sendFailureNotification",sendFailureNotification);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendMailFromTemplateSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
