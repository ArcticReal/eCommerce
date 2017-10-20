package com.skytala.eCommerce.service.party;

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
@RequestMapping("/service/Partys")
public class PartyServices{

	@RequestMapping(method = RequestMethod.POST, value = "/sendContactUsEmailToCompany")
	public ResponseEntity<Object> sendContactUsEmailToCompany(HttpSession session, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="note", required=false) String note, @RequestParam(value="lastName", required=false) String lastName, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="ccString", required=false) String ccString, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="communicationEventTypeId", required=false) String communicationEventTypeId, @RequestParam(value="content", required=false) String content, @RequestParam(value="emailAddress", required=false) String emailAddress, @RequestParam(value="contentMimeTypeId", required=false) String contentMimeTypeId, @RequestParam(value="datetimeStarted", required=false) Timestamp datetimeStarted, @RequestParam(value="emailType", required=false) String emailType, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="action", required=false) String action, @RequestParam(value="contactMechIdFrom", required=false) String contactMechIdFrom, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="parentCommEventId", required=false) String parentCommEventId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="datetimeEnded", required=false) Timestamp datetimeEnded, @RequestParam(value="origCommEventId", required=false) String origCommEventId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="contactMechIdTo", required=false) String contactMechIdTo, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="headerString", required=false) String headerString, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="bccString", required=false) String bccString, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="toString", required=false) String toString, @RequestParam(value="fromString", required=false) String fromString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("note",note);
		paramMap.put("lastName",lastName);
		paramMap.put("orderId",orderId);
		paramMap.put("subject",subject);
		paramMap.put("ccString",ccString);
		paramMap.put("postalCode",postalCode);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("communicationEventTypeId",communicationEventTypeId);
		paramMap.put("content",content);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("contentMimeTypeId",contentMimeTypeId);
		paramMap.put("datetimeStarted",datetimeStarted);
		paramMap.put("emailType",emailType);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactListId",contactListId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("action",action);
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("productId",productId);
		paramMap.put("parentCommEventId",parentCommEventId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("datetimeEnded",datetimeEnded);
		paramMap.put("origCommEventId",origCommEventId);
		paramMap.put("messageId",messageId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("firstName",firstName);
		paramMap.put("headerString",headerString);
		paramMap.put("statusId",statusId);
		paramMap.put("bccString",bccString);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("toString",toString);
		paramMap.put("fromString",fromString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendContactUsEmailToCompany", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelPartyInvitation")
	public ResponseEntity<Object> cancelPartyInvitation(HttpSession session, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelPartyInvitation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPostalAddressAndPurposes")
	public ResponseEntity<Object> createPostalAddressAndPurposes(HttpSession session, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="city", required=false) String city, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="setShippingPurpose", required=false) String setShippingPurpose, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="address1", required=false) String address1, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="setBillingPurpose", required=false) String setBillingPurpose, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("city",city);
		paramMap.put("postalCode",postalCode);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attnName",attnName);
		paramMap.put("setShippingPurpose",setShippingPurpose);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("address1",address1);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("setBillingPurpose",setBillingPurpose);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("directions",directions);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPostalAddressAndPurposes", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContactMechAttribute")
	public ResponseEntity<Object> updateContactMechAttribute(HttpSession session, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContactMechAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendEmailDated")
	public ResponseEntity<Object> sendEmailDated(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendEmailDated", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyPostalAddress")
	public ResponseEntity<Object> updatePartyPostalAddress(HttpSession session, @RequestParam(value="city") String city, @RequestParam(value="address1") String address1, @RequestParam(value="postalCode") String postalCode, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("city",city);
		paramMap.put("address1",address1);
		paramMap.put("postalCode",postalCode);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attnName",attnName);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("partyId",partyId);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("directions",directions);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyPostalAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAffiliate")
	public ResponseEntity<Object> updateAffiliate(HttpSession session, @RequestParam(value="affiliateName") String affiliateName, @RequestParam(value="yearEstablished", required=false) String yearEstablished, @RequestParam(value="affiliateDescription", required=false) String affiliateDescription, @RequestParam(value="sitePageViews", required=false) String sitePageViews, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="siteVisitors", required=false) String siteVisitors, @RequestParam(value="siteType", required=false) String siteType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("affiliateName",affiliateName);
		paramMap.put("yearEstablished",yearEstablished);
		paramMap.put("affiliateDescription",affiliateDescription);
		paramMap.put("sitePageViews",sitePageViews);
		paramMap.put("partyId",partyId);
		paramMap.put("siteVisitors",siteVisitors);
		paramMap.put("siteType",siteType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAffiliate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findPartyFromTelephone")
	public ResponseEntity<Object> findPartyFromTelephone(HttpSession session, @RequestParam(value="telno") String telno) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("telno",telno);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findPartyFromTelephone", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyClassificationGroup")
	public ResponseEntity<Object> deletePartyClassificationGroup(HttpSession session, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyClassificationGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/logIncomingMessage")
	public ResponseEntity<Object> logIncomingMessage(HttpSession session, @RequestParam(value="messageWrapper") org.apache.ofbiz.service.mail.MimeMessageWrapper messageWrapper) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("messageWrapper",messageWrapper);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("logIncomingMessage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyClassificationGroup")
	public ResponseEntity<Object> updatePartyClassificationGroup(HttpSession session, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="parentGroupId", required=false) String parentGroupId, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyClassificationTypeId", required=false) String partyClassificationTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("parentGroupId",parentGroupId);
		paramMap.put("description",description);
		paramMap.put("partyClassificationTypeId",partyClassificationTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyClassificationGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/savePartyNameChange")
	public ResponseEntity<Object> savePartyNameChange(HttpSession session, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="lastName", required=false) String lastName, @RequestParam(value="groupName", required=false) String groupName, @RequestParam(value="middleName", required=false) String middleName, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="personalTitle", required=false) String personalTitle, @RequestParam(value="suffix", required=false) String suffix) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName",firstName);
		paramMap.put("lastName",lastName);
		paramMap.put("groupName",groupName);
		paramMap.put("middleName",middleName);
		paramMap.put("partyId",partyId);
		paramMap.put("personalTitle",personalTitle);
		paramMap.put("suffix",suffix);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("savePartyNameChange", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findPartyFromTelephoneComplete")
	public ResponseEntity<Object> findPartyFromTelephoneComplete(HttpSession session, @RequestParam(value="telno") String telno) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("telno",telno);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findPartyFromTelephoneComplete", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyTelecomNumber")
	public ResponseEntity<Object> createPartyTelecomNumber(HttpSession session, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("extension",extension);
		paramMap.put("askForName",askForName);
		paramMap.put("comments",comments);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("areaCode",areaCode);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyTelecomNumber", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommEventFromEmail")
	public ResponseEntity<Object> createCommEventFromEmail(HttpSession session, @RequestParam(value="sendTo") String sendTo, @RequestParam(value="subject") String subject, @RequestParam(value="sendFrom") String sendFrom, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sendTo",sendTo);
		paramMap.put("subject",subject);
		paramMap.put("sendFrom",sendFrom);
		paramMap.put("statusId",statusId);
		paramMap.put("orderId",orderId);
		paramMap.put("partyId",partyId);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommEventFromEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyCarrierAccount")
	public ResponseEntity<Object> updatePartyCarrierAccount(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="partyId") String partyId, @RequestParam(value="accountNumber", required=false) String accountNumber, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("partyId",partyId);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyCarrierAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setPartyStatus")
	public ResponseEntity<Object> setPartyStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="partyId") String partyId, @RequestParam(value="statusDate", required=false) Timestamp statusDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("partyId",partyId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setPartyStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyClassificationGroup")
	public ResponseEntity<Object> createPartyClassificationGroup(HttpSession session, @RequestParam(value="parentGroupId", required=false) String parentGroupId, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyClassificationTypeId", required=false) String partyClassificationTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentGroupId",parentGroupId);
		paramMap.put("description",description);
		paramMap.put("partyClassificationTypeId",partyClassificationTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyClassificationGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ensurePartyRoleTo")
	public ResponseEntity<Object> ensurePartyRoleTo(HttpSession session, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ensurePartyRoleTo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyDatasourcePermissionCheck")
	public ResponseEntity<Object> partyDatasourcePermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyDatasourcePermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendEmailToContactList")
	public ResponseEntity<Object> sendEmailToContactList(HttpSession session, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendEmailToContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findPartyFromEmailAddress")
	public ResponseEntity<Object> findPartyFromEmailAddress(HttpSession session, @RequestParam(value="address") String address, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="caseInsensitive", required=false) String caseInsensitive, @RequestParam(value="personal", required=false) String personal) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("address",address);
		paramMap.put("fromDate",fromDate);
		paramMap.put("caseInsensitive",caseInsensitive);
		paramMap.put("personal",personal);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findPartyFromEmailAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/linkPartyRecord")
	public ResponseEntity<Object> linkPartyRecord(HttpSession session, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("linkPartyRecord", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyClassification")
	public ResponseEntity<Object> updatePartyClassification(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyClassification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyClassification")
	public ResponseEntity<Object> createPartyClassification(HttpSession session, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyClassification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyContactMechPermissionCheck")
	public ResponseEntity<Object> partyContactMechPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("partyId",partyId);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyContactMechPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyRelationship")
	public ResponseEntity<Object> deletePartyRelationship(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyRelationship", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyPostalAddress")
	public ResponseEntity<Object> getPartyPostalAddress(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyPostalAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/clearAddressMatchMap")
	public ResponseEntity<Object> clearAddressMatchMap(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearAddressMatchMap", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdatePerson")
	public ResponseEntity<Object> createUpdatePerson(HttpSession session, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="yearsWithEmployer", required=false) Long yearsWithEmployer, @RequestParam(value="occupation", required=false) String occupation, @RequestParam(value="gender", required=false) String gender, @RequestParam(value="employmentStatusEnumId", required=false) String employmentStatusEnumId, @RequestParam(value="socialSecurityNumber", required=false) String socialSecurityNumber, @RequestParam(value="suffix", required=false) String suffix, @RequestParam(value="mothersMaidenName", required=false) String mothersMaidenName, @RequestParam(value="middleNameLocal", required=false) String middleNameLocal, @RequestParam(value="existingCustomer", required=false) String existingCustomer, @RequestParam(value="residenceStatusEnumId", required=false) String residenceStatusEnumId, @RequestParam(value="nickname", required=false) String nickname, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="memberId", required=false) String memberId, @RequestParam(value="height", required=false) BigDecimal height, @RequestParam(value="passportNumber", required=false) String passportNumber, @RequestParam(value="lastNameLocal", required=false) String lastNameLocal, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="monthsWithEmployer", required=false) Long monthsWithEmployer, @RequestParam(value="weight", required=false) BigDecimal weight, @RequestParam(value="birthDate", required=false) Timestamp birthDate, @RequestParam(value="otherLocal", required=false) String otherLocal, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="cardId", required=false) String cardId, @RequestParam(value="middleName", required=false) String middleName, @RequestParam(value="firstNameLocal", required=false) String firstNameLocal, @RequestParam(value="passportExpireDate", required=false) Timestamp passportExpireDate, @RequestParam(value="salutation", required=false) String salutation, @RequestParam(value="personalTitle", required=false) String personalTitle, @RequestParam(value="deceasedDate", required=false) Timestamp deceasedDate, @RequestParam(value="totalYearsWorkExperience", required=false) BigDecimal totalYearsWorkExperience, @RequestParam(value="maritalStatus", required=false) String maritalStatus) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName",firstName);
		paramMap.put("lastName",lastName);
		paramMap.put("yearsWithEmployer",yearsWithEmployer);
		paramMap.put("occupation",occupation);
		paramMap.put("gender",gender);
		paramMap.put("employmentStatusEnumId",employmentStatusEnumId);
		paramMap.put("socialSecurityNumber",socialSecurityNumber);
		paramMap.put("suffix",suffix);
		paramMap.put("mothersMaidenName",mothersMaidenName);
		paramMap.put("middleNameLocal",middleNameLocal);
		paramMap.put("existingCustomer",existingCustomer);
		paramMap.put("residenceStatusEnumId",residenceStatusEnumId);
		paramMap.put("nickname",nickname);
		paramMap.put("partyId",partyId);
		paramMap.put("memberId",memberId);
		paramMap.put("height",height);
		paramMap.put("passportNumber",passportNumber);
		paramMap.put("lastNameLocal",lastNameLocal);
		paramMap.put("comments",comments);
		paramMap.put("monthsWithEmployer",monthsWithEmployer);
		paramMap.put("weight",weight);
		paramMap.put("birthDate",birthDate);
		paramMap.put("otherLocal",otherLocal);
		paramMap.put("userLogin",userLogin);
		paramMap.put("cardId",cardId);
		paramMap.put("middleName",middleName);
		paramMap.put("firstNameLocal",firstNameLocal);
		paramMap.put("passportExpireDate",passportExpireDate);
		paramMap.put("salutation",salutation);
		paramMap.put("personalTitle",personalTitle);
		paramMap.put("deceasedDate",deceasedDate);
		paramMap.put("totalYearsWorkExperience",totalYearsWorkExperience);
		paramMap.put("maritalStatus",maritalStatus);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdatePerson", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uploadPartyContentFile")
	public ResponseEntity<Object> uploadPartyContentFile(HttpSession session, @RequestParam(value="partyContentTypeId") String partyContentTypeId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyContentTypeId",partyContentTypeId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("uploadPartyContentFile", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRoleType")
	public ResponseEntity<Object> deleteRoleType(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRoleType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAgreementItemTypeAttr")
	public ResponseEntity<Object> updateAgreementItemTypeAttr(HttpSession session, @RequestParam(value="agreementItemTypeId") String agreementItemTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("agreementItemTypeId",agreementItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAgreementItemTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteVendor")
	public ResponseEntity<Object> deleteVendor(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteVendor", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContactMech")
	public ResponseEntity<Object> createContactMech(HttpSession session, @RequestParam(value="contactMechTypeId") String contactMechTypeId, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("infoString",infoString);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContactMech", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyCarrierAccount")
	public ResponseEntity<Object> createPartyCarrierAccount(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="partyId") String partyId, @RequestParam(value="accountNumber", required=false) String accountNumber, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("partyId",partyId);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyCarrierAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyEmail")
	public ResponseEntity<Object> getPartyEmail(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAgreementAttribute")
	public ResponseEntity<Object> deleteAgreementAttribute(HttpSession session, @RequestParam(value="agreementId") String agreementId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("agreementId",agreementId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAgreementAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removePartyContent")
	public ResponseEntity<Object> removePartyContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyContentTypeId") String partyContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyContentTypeId",partyContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePartyContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEmailAddress")
	public ResponseEntity<Object> createEmailAddress(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("infoString",infoString);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmailAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContactMechLink")
	public ResponseEntity<Object> createContactMechLink(HttpSession session, @RequestParam(value="contactMechIdFrom") String contactMechIdFrom, @RequestParam(value="contactMechIdTo") String contactMechIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContactMechLink", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommunicationEvent")
	public ResponseEntity<Object> updateCommunicationEvent(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="note", required=false) String note, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="ccString", required=false) String ccString, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="communicationEventTypeId", required=false) String communicationEventTypeId, @RequestParam(value="content", required=false) String content, @RequestParam(value="contentMimeTypeId", required=false) String contentMimeTypeId, @RequestParam(value="datetimeStarted", required=false) Timestamp datetimeStarted, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="contactMechIdFrom", required=false) String contactMechIdFrom, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="parentCommEventId", required=false) String parentCommEventId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="datetimeEnded", required=false) Timestamp datetimeEnded, @RequestParam(value="origCommEventId", required=false) String origCommEventId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="contactMechIdTo", required=false) String contactMechIdTo, @RequestParam(value="headerString", required=false) String headerString, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contactMechPurposeTypeIdFrom", required=false) String contactMechPurposeTypeIdFrom, @RequestParam(value="bccString", required=false) String bccString, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="toString", required=false) String toString, @RequestParam(value="fromString", required=false) String fromString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("note",note);
		paramMap.put("subject",subject);
		paramMap.put("ccString",ccString);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("communicationEventTypeId",communicationEventTypeId);
		paramMap.put("content",content);
		paramMap.put("contentMimeTypeId",contentMimeTypeId);
		paramMap.put("datetimeStarted",datetimeStarted);
		paramMap.put("contactListId",contactListId);
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("parentCommEventId",parentCommEventId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("datetimeEnded",datetimeEnded);
		paramMap.put("origCommEventId",origCommEventId);
		paramMap.put("messageId",messageId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("headerString",headerString);
		paramMap.put("statusId",statusId);
		paramMap.put("contactMechPurposeTypeIdFrom",contactMechPurposeTypeIdFrom);
		paramMap.put("bccString",bccString);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("toString",toString);
		paramMap.put("fromString",fromString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommunicationEvent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEmailAddressVerification")
	public ResponseEntity<Object> createEmailAddressVerification(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="expireDate", required=false) String expireDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("expireDate",expireDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmailAddressVerification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyRole")
	public ResponseEntity<Object> deletePartyRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setCommEventRoleToRead")
	public ResponseEntity<Object> setCommEventRoleToRead(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCommEventRoleToRead", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyStatusPermissionCheck")
	public ResponseEntity<Object> partyStatusPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("partyId",partyId);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyStatusPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyDataSource")
	public ResponseEntity<Object> createPartyDataSource(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyDataSource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/declinePartyInvitation")
	public ResponseEntity<Object> declinePartyInvitation(HttpSession session, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("declinePartyInvitation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRoleType")
	public ResponseEntity<Object> updateRoleType(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRoleType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyGroup")
	public ResponseEntity<Object> createPartyGroup(HttpSession session, @RequestParam(value="groupName") String groupName, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="description", required=false) String description, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="tickerSymbol", required=false) String tickerSymbol, @RequestParam(value="logoImageUrl", required=false) String logoImageUrl, @RequestParam(value="partyTypeId", required=false) String partyTypeId, @RequestParam(value="groupNameLocal", required=false) String groupNameLocal, @RequestParam(value="numEmployees", required=false) Long numEmployees, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="preferredCurrencyUomId", required=false) String preferredCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="officeSiteName", required=false) String officeSiteName, @RequestParam(value="annualRevenue", required=false) BigDecimal annualRevenue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupName",groupName);
		paramMap.put("comments",comments);
		paramMap.put("description",description);
		paramMap.put("externalId",externalId);
		paramMap.put("tickerSymbol",tickerSymbol);
		paramMap.put("logoImageUrl",logoImageUrl);
		paramMap.put("partyTypeId",partyTypeId);
		paramMap.put("groupNameLocal",groupNameLocal);
		paramMap.put("numEmployees",numEmployees);
		paramMap.put("statusId",statusId);
		paramMap.put("preferredCurrencyUomId",preferredCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("officeSiteName",officeSiteName);
		paramMap.put("annualRevenue",annualRevenue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyInvitationRoleAssoc")
	public ResponseEntity<Object> deletePartyInvitationRoleAssoc(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyInvitationRoleAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyIdentification")
	public ResponseEntity<Object> deletePartyIdentification(HttpSession session, @RequestParam(value="partyIdentificationTypeId") String partyIdentificationTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdentificationTypeId",partyIdentificationTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyIdentification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustRequestCommEvent")
	public ResponseEntity<Object> deleteCustRequestCommEvent(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustRequestCommEvent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdatePartyEmailAddress")
	public ResponseEntity<Object> createUpdatePartyEmailAddress(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("userLogin",userLogin);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechId",contactMechId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdatePartyEmailAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyContactMechPurpose")
	public ResponseEntity<Object> deletePartyContactMechPurpose(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyContactMechPurpose", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateVendor")
	public ResponseEntity<Object> updateVendor(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="manifestCompanyTitle", required=false) String manifestCompanyTitle, @RequestParam(value="manifestPolicies", required=false) String manifestPolicies, @RequestParam(value="manifestLogoUrl", required=false) String manifestLogoUrl, @RequestParam(value="manifestCompanyName", required=false) String manifestCompanyName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("manifestCompanyTitle",manifestCompanyTitle);
		paramMap.put("manifestPolicies",manifestPolicies);
		paramMap.put("manifestLogoUrl",manifestLogoUrl);
		paramMap.put("manifestCompanyName",manifestCompanyName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateVendor", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/quickCreateCustomer")
	public ResponseEntity<Object> quickCreateCustomer(HttpSession session, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="subscribeContactList", required=false) String subscribeContactList, @RequestParam(value="contactListId", required=false) String contactListId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName",firstName);
		paramMap.put("lastName",lastName);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("subscribeContactList",subscribeContactList);
		paramMap.put("contactListId",contactListId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickCreateCustomer", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyAttribute")
	public ResponseEntity<Object> updatePartyAttribute(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelPartyInvitationPermissionCheck")
	public ResponseEntity<Object> cancelPartyInvitationPermissionCheck(HttpSession session, @RequestParam(value="partyInvitationId") String partyInvitationId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelPartyInvitationPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyInvitation")
	public ResponseEntity<Object> createPartyInvitation(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="emailAddress", required=false) String emailAddress, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="lastInviteDate", required=false) Timestamp lastInviteDate, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("statusId",statusId);
		paramMap.put("toName",toName);
		paramMap.put("lastInviteDate",lastInviteDate);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyInvitation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTelecomNumber")
	public ResponseEntity<Object> createTelecomNumber(HttpSession session, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("areaCode",areaCode);
		paramMap.put("askForName",askForName);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTelecomNumber", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyGroup")
	public ResponseEntity<Object> updatePartyGroup(HttpSession session, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="description", required=false) String description, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="tickerSymbol", required=false) String tickerSymbol, @RequestParam(value="logoImageUrl", required=false) String logoImageUrl, @RequestParam(value="partyTypeId", required=false) String partyTypeId, @RequestParam(value="groupName", required=false) String groupName, @RequestParam(value="groupNameLocal", required=false) String groupNameLocal, @RequestParam(value="numEmployees", required=false) Long numEmployees, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="preferredCurrencyUomId", required=false) String preferredCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="officeSiteName", required=false) String officeSiteName, @RequestParam(value="annualRevenue", required=false) BigDecimal annualRevenue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comments",comments);
		paramMap.put("description",description);
		paramMap.put("externalId",externalId);
		paramMap.put("tickerSymbol",tickerSymbol);
		paramMap.put("logoImageUrl",logoImageUrl);
		paramMap.put("partyTypeId",partyTypeId);
		paramMap.put("groupName",groupName);
		paramMap.put("groupNameLocal",groupNameLocal);
		paramMap.put("numEmployees",numEmployees);
		paramMap.put("statusId",statusId);
		paramMap.put("preferredCurrencyUomId",preferredCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("officeSiteName",officeSiteName);
		paramMap.put("annualRevenue",annualRevenue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findPartiesById")
	public ResponseEntity<Object> findPartiesById(HttpSession session, @RequestParam(value="idToFind") String idToFind, @RequestParam(value="searchAllId", required=false) String searchAllId, @RequestParam(value="partyIdentificationTypeId", required=false) String partyIdentificationTypeId, @RequestParam(value="searchPartyFirst", required=false) String searchPartyFirst) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("idToFind",idToFind);
		paramMap.put("searchAllId",searchAllId);
		paramMap.put("partyIdentificationTypeId",partyIdentificationTypeId);
		paramMap.put("searchPartyFirst",searchPartyFirst);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findPartiesById", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyRelationshipType")
	public ResponseEntity<Object> createPartyRelationshipType(HttpSession session, @RequestParam(value="partyRelationshipName") String partyRelationshipName, @RequestParam(value="partyRelationshipTypeId") String partyRelationshipTypeId, @RequestParam(value="roleTypeIdValidTo", required=false) String roleTypeIdValidTo, @RequestParam(value="roleTypeIdValidFrom", required=false) String roleTypeIdValidFrom, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyRelationshipName",partyRelationshipName);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("roleTypeIdValidTo",roleTypeIdValidTo);
		paramMap.put("roleTypeIdValidFrom",roleTypeIdValidFrom);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyRelationshipType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmailAddress")
	public ResponseEntity<Object> updateEmailAddress(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmailAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ensureNaPartyRole")
	public ResponseEntity<Object> ensureNaPartyRole(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ensureNaPartyRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/importAddressMatchMapCsv")
	public ResponseEntity<Object> importAddressMatchMapCsv(HttpSession session, @RequestParam(value="_uploadedFile_contentType") String _uploadedFile_contentType, @RequestParam(value="uploadedFile") java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName") String _uploadedFile_fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("importAddressMatchMapCsv", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPersonAndUserLogin")
	public ResponseEntity<Object> createPersonAndUserLogin(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="currentPasswordVerify") String currentPasswordVerify, @RequestParam(value="currentPassword") String currentPassword, @RequestParam(value="occupation", required=false) String occupation, @RequestParam(value="passwordHint", required=false) String passwordHint, @RequestParam(value="suffix", required=false) String suffix, @RequestParam(value="mothersMaidenName", required=false) String mothersMaidenName, @RequestParam(value="existingCustomer", required=false) String existingCustomer, @RequestParam(value="partyTypeId", required=false) String partyTypeId, @RequestParam(value="preferredCurrencyUomId", required=false) String preferredCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="memberId", required=false) String memberId, @RequestParam(value="height", required=false) BigDecimal height, @RequestParam(value="passportNumber", required=false) String passportNumber, @RequestParam(value="lastNameLocal", required=false) String lastNameLocal, @RequestParam(value="monthsWithEmployer", required=false) Long monthsWithEmployer, @RequestParam(value="weight", required=false) BigDecimal weight, @RequestParam(value="otherLocal", required=false) String otherLocal, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="cardId", required=false) String cardId, @RequestParam(value="requirePasswordChange", required=false) String requirePasswordChange, @RequestParam(value="salutation", required=false) String salutation, @RequestParam(value="totalYearsWorkExperience", required=false) BigDecimal totalYearsWorkExperience, @RequestParam(value="maritalStatus", required=false) String maritalStatus, @RequestParam(value="yearsWithEmployer", required=false) Long yearsWithEmployer, @RequestParam(value="lastName", required=false) String lastName, @RequestParam(value="gender", required=false) String gender, @RequestParam(value="employmentStatusEnumId", required=false) String employmentStatusEnumId, @RequestParam(value="securityAnswer", required=false) String securityAnswer, @RequestParam(value="socialSecurityNumber", required=false) String socialSecurityNumber, @RequestParam(value="externalAuthId", required=false) String externalAuthId, @RequestParam(value="description", required=false) String description, @RequestParam(value="enabled", required=false) String enabled, @RequestParam(value="middleNameLocal", required=false) String middleNameLocal, @RequestParam(value="residenceStatusEnumId", required=false) String residenceStatusEnumId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="nickname", required=false) String nickname, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="securityQuestion", required=false) String securityQuestion, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="birthDate", required=false) Timestamp birthDate, @RequestParam(value="isUnread", required=false) String isUnread, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="middleName", required=false) String middleName, @RequestParam(value="firstNameLocal", required=false) String firstNameLocal, @RequestParam(value="passportExpireDate", required=false) Timestamp passportExpireDate, @RequestParam(value="personalTitle", required=false) String personalTitle, @RequestParam(value="deceasedDate", required=false) Timestamp deceasedDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("currentPasswordVerify",currentPasswordVerify);
		paramMap.put("currentPassword",currentPassword);
		paramMap.put("occupation",occupation);
		paramMap.put("passwordHint",passwordHint);
		paramMap.put("suffix",suffix);
		paramMap.put("mothersMaidenName",mothersMaidenName);
		paramMap.put("existingCustomer",existingCustomer);
		paramMap.put("partyTypeId",partyTypeId);
		paramMap.put("preferredCurrencyUomId",preferredCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("memberId",memberId);
		paramMap.put("height",height);
		paramMap.put("passportNumber",passportNumber);
		paramMap.put("lastNameLocal",lastNameLocal);
		paramMap.put("monthsWithEmployer",monthsWithEmployer);
		paramMap.put("weight",weight);
		paramMap.put("otherLocal",otherLocal);
		paramMap.put("firstName",firstName);
		paramMap.put("statusId",statusId);
		paramMap.put("cardId",cardId);
		paramMap.put("requirePasswordChange",requirePasswordChange);
		paramMap.put("salutation",salutation);
		paramMap.put("totalYearsWorkExperience",totalYearsWorkExperience);
		paramMap.put("maritalStatus",maritalStatus);
		paramMap.put("yearsWithEmployer",yearsWithEmployer);
		paramMap.put("lastName",lastName);
		paramMap.put("gender",gender);
		paramMap.put("employmentStatusEnumId",employmentStatusEnumId);
		paramMap.put("securityAnswer",securityAnswer);
		paramMap.put("socialSecurityNumber",socialSecurityNumber);
		paramMap.put("externalAuthId",externalAuthId);
		paramMap.put("description",description);
		paramMap.put("enabled",enabled);
		paramMap.put("middleNameLocal",middleNameLocal);
		paramMap.put("residenceStatusEnumId",residenceStatusEnumId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("nickname",nickname);
		paramMap.put("comments",comments);
		paramMap.put("securityQuestion",securityQuestion);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("externalId",externalId);
		paramMap.put("birthDate",birthDate);
		paramMap.put("isUnread",isUnread);
		paramMap.put("createdDate",createdDate);
		paramMap.put("middleName",middleName);
		paramMap.put("firstNameLocal",firstNameLocal);
		paramMap.put("passportExpireDate",passportExpireDate);
		paramMap.put("personalTitle",personalTitle);
		paramMap.put("deceasedDate",deceasedDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPersonAndUserLogin", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ensurePartyRole")
	public ResponseEntity<Object> ensurePartyRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ensurePartyRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyRolePermissionCheck")
	public ResponseEntity<Object> partyRolePermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("partyId",partyId);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyRolePermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ensurePartyRoleFrom")
	public ResponseEntity<Object> ensurePartyRoleFrom(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ensurePartyRoleFrom", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyNote")
	public ResponseEntity<Object> createPartyNote(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="note", required=false) String note, @RequestParam(value="noteName", required=false) String noteName, @RequestParam(value="noteId", required=false) String noteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("note",note);
		paramMap.put("noteName",noteName);
		paramMap.put("noteId",noteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyNote", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendVerifyEmailAddressNotification")
	public ResponseEntity<Object> sendVerifyEmailAddressNotification(HttpSession session, @RequestParam(value="emailAddress") String emailAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendVerifyEmailAddressNotification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAgreementAttribute")
	public ResponseEntity<Object> updateAgreementAttribute(HttpSession session, @RequestParam(value="agreementId") String agreementId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("agreementId",agreementId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAgreementAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyTelephone")
	public ResponseEntity<Object> getPartyTelephone(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyTelephone", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/accAndDecPartyInvitationPermissionCheck")
	public ResponseEntity<Object> accAndDecPartyInvitationPermissionCheck(HttpSession session, @RequestParam(value="partyInvitationId") String partyInvitationId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("accAndDecPartyInvitationPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyRelationship")
	public ResponseEntity<Object> createPartyRelationship(HttpSession session, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="positionTitle", required=false) String positionTitle, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="priorityTypeId", required=false) String priorityTypeId, @RequestParam(value="permissionsEnumId", required=false) String permissionsEnumId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="relationshipName", required=false) String relationshipName, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("positionTitle",positionTitle);
		paramMap.put("comments",comments);
		paramMap.put("priorityTypeId",priorityTypeId);
		paramMap.put("permissionsEnumId",permissionsEnumId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("relationshipName",relationshipName);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyRelationship", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePerson")
	public ResponseEntity<Object> updatePerson(HttpSession session, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="yearsWithEmployer", required=false) Long yearsWithEmployer, @RequestParam(value="occupation", required=false) String occupation, @RequestParam(value="gender", required=false) String gender, @RequestParam(value="employmentStatusEnumId", required=false) String employmentStatusEnumId, @RequestParam(value="socialSecurityNumber", required=false) String socialSecurityNumber, @RequestParam(value="description", required=false) String description, @RequestParam(value="suffix", required=false) String suffix, @RequestParam(value="mothersMaidenName", required=false) String mothersMaidenName, @RequestParam(value="middleNameLocal", required=false) String middleNameLocal, @RequestParam(value="existingCustomer", required=false) String existingCustomer, @RequestParam(value="residenceStatusEnumId", required=false) String residenceStatusEnumId, @RequestParam(value="nickname", required=false) String nickname, @RequestParam(value="preferredCurrencyUomId", required=false) String preferredCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="memberId", required=false) String memberId, @RequestParam(value="height", required=false) BigDecimal height, @RequestParam(value="passportNumber", required=false) String passportNumber, @RequestParam(value="lastNameLocal", required=false) String lastNameLocal, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="monthsWithEmployer", required=false) Long monthsWithEmployer, @RequestParam(value="weight", required=false) BigDecimal weight, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="birthDate", required=false) Timestamp birthDate, @RequestParam(value="otherLocal", required=false) String otherLocal, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="cardId", required=false) String cardId, @RequestParam(value="middleName", required=false) String middleName, @RequestParam(value="firstNameLocal", required=false) String firstNameLocal, @RequestParam(value="passportExpireDate", required=false) Timestamp passportExpireDate, @RequestParam(value="salutation", required=false) String salutation, @RequestParam(value="personalTitle", required=false) String personalTitle, @RequestParam(value="deceasedDate", required=false) Timestamp deceasedDate, @RequestParam(value="totalYearsWorkExperience", required=false) BigDecimal totalYearsWorkExperience, @RequestParam(value="maritalStatus", required=false) String maritalStatus) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName",firstName);
		paramMap.put("lastName",lastName);
		paramMap.put("yearsWithEmployer",yearsWithEmployer);
		paramMap.put("occupation",occupation);
		paramMap.put("gender",gender);
		paramMap.put("employmentStatusEnumId",employmentStatusEnumId);
		paramMap.put("socialSecurityNumber",socialSecurityNumber);
		paramMap.put("description",description);
		paramMap.put("suffix",suffix);
		paramMap.put("mothersMaidenName",mothersMaidenName);
		paramMap.put("middleNameLocal",middleNameLocal);
		paramMap.put("existingCustomer",existingCustomer);
		paramMap.put("residenceStatusEnumId",residenceStatusEnumId);
		paramMap.put("nickname",nickname);
		paramMap.put("preferredCurrencyUomId",preferredCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("memberId",memberId);
		paramMap.put("height",height);
		paramMap.put("passportNumber",passportNumber);
		paramMap.put("lastNameLocal",lastNameLocal);
		paramMap.put("comments",comments);
		paramMap.put("monthsWithEmployer",monthsWithEmployer);
		paramMap.put("weight",weight);
		paramMap.put("externalId",externalId);
		paramMap.put("birthDate",birthDate);
		paramMap.put("otherLocal",otherLocal);
		paramMap.put("statusId",statusId);
		paramMap.put("cardId",cardId);
		paramMap.put("middleName",middleName);
		paramMap.put("firstNameLocal",firstNameLocal);
		paramMap.put("passportExpireDate",passportExpireDate);
		paramMap.put("salutation",salutation);
		paramMap.put("personalTitle",personalTitle);
		paramMap.put("deceasedDate",deceasedDate);
		paramMap.put("totalYearsWorkExperience",totalYearsWorkExperience);
		paramMap.put("maritalStatus",maritalStatus);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePerson", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventWithoutPermission")
	public ResponseEntity<Object> createCommunicationEventWithoutPermission(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="note", required=false) String note, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="ccString", required=false) String ccString, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="communicationEventTypeId", required=false) String communicationEventTypeId, @RequestParam(value="content", required=false) String content, @RequestParam(value="contentMimeTypeId", required=false) String contentMimeTypeId, @RequestParam(value="datetimeStarted", required=false) Timestamp datetimeStarted, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="action", required=false) String action, @RequestParam(value="contactMechIdFrom", required=false) String contactMechIdFrom, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="parentCommEventId", required=false) String parentCommEventId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="datetimeEnded", required=false) Timestamp datetimeEnded, @RequestParam(value="origCommEventId", required=false) String origCommEventId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="contactMechIdTo", required=false) String contactMechIdTo, @RequestParam(value="headerString", required=false) String headerString, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="bccString", required=false) String bccString, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="toString", required=false) String toString, @RequestParam(value="fromString", required=false) String fromString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("note",note);
		paramMap.put("orderId",orderId);
		paramMap.put("subject",subject);
		paramMap.put("ccString",ccString);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("communicationEventTypeId",communicationEventTypeId);
		paramMap.put("content",content);
		paramMap.put("contentMimeTypeId",contentMimeTypeId);
		paramMap.put("datetimeStarted",datetimeStarted);
		paramMap.put("contactListId",contactListId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("action",action);
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("productId",productId);
		paramMap.put("parentCommEventId",parentCommEventId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("datetimeEnded",datetimeEnded);
		paramMap.put("origCommEventId",origCommEventId);
		paramMap.put("messageId",messageId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("headerString",headerString);
		paramMap.put("statusId",statusId);
		paramMap.put("bccString",bccString);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("toString",toString);
		paramMap.put("fromString",fromString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventWithoutPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContactMechAndPurposes")
	public ResponseEntity<Object> updateContactMechAndPurposes(HttpSession session, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="city", required=false) String city, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="setShippingPurpose", required=false) String setShippingPurpose, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="phoneContactMechId", required=false) String phoneContactMechId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="address1", required=false) String address1, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="setBillingPurpose", required=false) String setBillingPurpose, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("askForName",askForName);
		paramMap.put("city",city);
		paramMap.put("postalCode",postalCode);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attnName",attnName);
		paramMap.put("setShippingPurpose",setShippingPurpose);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("phoneContactMechId",phoneContactMechId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("address1",address1);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("setBillingPurpose",setBillingPurpose);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("areaCode",areaCode);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("directions",directions);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContactMechAndPurposes", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteNeedType")
	public ResponseEntity<Object> deleteNeedType(HttpSession session, @RequestParam(value="needTypeId") String needTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("needTypeId",needTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteNeedType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyEmailAddress")
	public ResponseEntity<Object> updatePartyEmailAddress(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("extension",extension);
		paramMap.put("comments",comments);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyEmailAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendCommEventAsEmail")
	public ResponseEntity<Object> sendCommEventAsEmail(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendCommEventAsEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRoleTypeAttr")
	public ResponseEntity<Object> createRoleTypeAttr(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRoleTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventPrpTyp")
	public ResponseEntity<Object> createCommunicationEventPrpTyp(HttpSession session, @RequestParam(value="communicationEventPrpTypId", required=false) String communicationEventPrpTypId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventPrpTypId",communicationEventPrpTypId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventPrpTyp", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setPartyProfileDefaults")
	public ResponseEntity<Object> setPartyProfileDefaults(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="defaultPayMeth", required=false) String defaultPayMeth, @RequestParam(value="defaultShipAddr", required=false) String defaultShipAddr, @RequestParam(value="defaultShipMeth", required=false) String defaultShipMeth, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="defaultBillAddr", required=false) String defaultBillAddr) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("defaultPayMeth",defaultPayMeth);
		paramMap.put("defaultShipAddr",defaultShipAddr);
		paramMap.put("defaultShipMeth",defaultShipMeth);
		paramMap.put("partyId",partyId);
		paramMap.put("defaultBillAddr",defaultBillAddr);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setPartyProfileDefaults", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeAddressMatchMap")
	public ResponseEntity<Object> removeAddressMatchMap(HttpSession session, @RequestParam(value="mapValue") String mapValue, @RequestParam(value="mapKey") String mapKey) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mapValue",mapValue);
		paramMap.put("mapKey",mapKey);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeAddressMatchMap", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/lookupParty")
	public ResponseEntity<Object> lookupParty(HttpSession session, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="lastName", required=false) String lastName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName",firstName);
		paramMap.put("lastName",lastName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("lookupParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyGroupPermissionCheck")
	public ResponseEntity<Object> partyGroupPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("partyId",partyId);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyGroupPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removePartyAttribute")
	public ResponseEntity<Object> removePartyAttribute(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePartyAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEvent")
	public ResponseEntity<Object> createCommunicationEvent(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="note", required=false) String note, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="ccString", required=false) String ccString, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="communicationEventTypeId", required=false) String communicationEventTypeId, @RequestParam(value="content", required=false) String content, @RequestParam(value="contentMimeTypeId", required=false) String contentMimeTypeId, @RequestParam(value="datetimeStarted", required=false) Timestamp datetimeStarted, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="action", required=false) String action, @RequestParam(value="contactMechIdFrom", required=false) String contactMechIdFrom, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="parentCommEventId", required=false) String parentCommEventId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="datetimeEnded", required=false) Timestamp datetimeEnded, @RequestParam(value="origCommEventId", required=false) String origCommEventId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="contactMechIdTo", required=false) String contactMechIdTo, @RequestParam(value="headerString", required=false) String headerString, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="bccString", required=false) String bccString, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="toString", required=false) String toString, @RequestParam(value="fromString", required=false) String fromString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("note",note);
		paramMap.put("orderId",orderId);
		paramMap.put("subject",subject);
		paramMap.put("ccString",ccString);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("communicationEventTypeId",communicationEventTypeId);
		paramMap.put("content",content);
		paramMap.put("contentMimeTypeId",contentMimeTypeId);
		paramMap.put("datetimeStarted",datetimeStarted);
		paramMap.put("contactListId",contactListId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("action",action);
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("productId",productId);
		paramMap.put("parentCommEventId",parentCommEventId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("datetimeEnded",datetimeEnded);
		paramMap.put("origCommEventId",origCommEventId);
		paramMap.put("messageId",messageId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("headerString",headerString);
		paramMap.put("statusId",statusId);
		paramMap.put("bccString",bccString);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("toString",toString);
		paramMap.put("fromString",fromString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEvent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePostalAddressBoundary")
	public ResponseEntity<Object> deletePostalAddressBoundary(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePostalAddressBoundary", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/processBouncedMessage")
	public ResponseEntity<Object> processBouncedMessage(HttpSession session, @RequestParam(value="messageWrapper") org.apache.ofbiz.service.mail.MimeMessageWrapper messageWrapper) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("messageWrapper",messageWrapper);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processBouncedMessage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventPurpose")
	public ResponseEntity<Object> createCommunicationEventPurpose(HttpSession session, @RequestParam(value="communicationEventPrpTypId") String communicationEventPrpTypId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventPrpTypId",communicationEventPrpTypId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventPurpose", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommEventWorkEffort")
	public ResponseEntity<Object> createCommEventWorkEffort(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="currentStatusId") String currentStatusId, @RequestParam(value="workEffortName") String workEffortName, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="revisionNumber", required=false) Long revisionNumber, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="lastStatusUpdate", required=false) Timestamp lastStatusUpdate, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("revisionNumber",revisionNumber);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("lastStatusUpdate",lastStatusUpdate);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("createdDate",createdDate);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommEventWorkEffort", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyContent")
	public ResponseEntity<Object> updatePartyContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyContentTypeId") String partyContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyContentTypeId",partyContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRoleTypeAttr")
	public ResponseEntity<Object> updateRoleTypeAttr(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRoleTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContactMechAttribute")
	public ResponseEntity<Object> createContactMechAttribute(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyId") String partyId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContactMechAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventInterface")
	public ResponseEntity<Object> createCommunicationEventInterface(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="note", required=false) String note, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="ccString", required=false) String ccString, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="communicationEventTypeId", required=false) String communicationEventTypeId, @RequestParam(value="content", required=false) String content, @RequestParam(value="contentMimeTypeId", required=false) String contentMimeTypeId, @RequestParam(value="datetimeStarted", required=false) Timestamp datetimeStarted, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="action", required=false) String action, @RequestParam(value="contactMechIdFrom", required=false) String contactMechIdFrom, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="parentCommEventId", required=false) String parentCommEventId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="datetimeEnded", required=false) Timestamp datetimeEnded, @RequestParam(value="origCommEventId", required=false) String origCommEventId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="contactMechIdTo", required=false) String contactMechIdTo, @RequestParam(value="headerString", required=false) String headerString, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="bccString", required=false) String bccString, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="toString", required=false) String toString, @RequestParam(value="fromString", required=false) String fromString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("note",note);
		paramMap.put("orderId",orderId);
		paramMap.put("subject",subject);
		paramMap.put("ccString",ccString);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("communicationEventTypeId",communicationEventTypeId);
		paramMap.put("content",content);
		paramMap.put("contentMimeTypeId",contentMimeTypeId);
		paramMap.put("datetimeStarted",datetimeStarted);
		paramMap.put("contactListId",contactListId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("action",action);
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("productId",productId);
		paramMap.put("parentCommEventId",parentCommEventId);
		paramMap.put("entryDate",entryDate);
		paramMap.put("datetimeEnded",datetimeEnded);
		paramMap.put("origCommEventId",origCommEventId);
		paramMap.put("messageId",messageId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("headerString",headerString);
		paramMap.put("statusId",statusId);
		paramMap.put("bccString",bccString);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("toString",toString);
		paramMap.put("fromString",fromString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdatePartyRelationshipAndRoles")
	public ResponseEntity<Object> createUpdatePartyRelationshipAndRoles(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="relationshipName", required=false) String relationshipName, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="positionTitle", required=false) String positionTitle, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="priorityTypeId", required=false) String priorityTypeId, @RequestParam(value="permissionsEnumId", required=false) String permissionsEnumId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("relationshipName",relationshipName);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("positionTitle",positionTitle);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("priorityTypeId",priorityTypeId);
		paramMap.put("permissionsEnumId",permissionsEnumId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdatePartyRelationshipAndRoles", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyContactMech")
	public ResponseEntity<Object> createPartyContactMech(HttpSession session, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("extension",extension);
		paramMap.put("comments",comments);
		paramMap.put("verified",verified);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("infoString",infoString);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyContactMech", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePostalAddress")
	public ResponseEntity<Object> updatePostalAddress(HttpSession session, @RequestParam(value="city") String city, @RequestParam(value="address1") String address1, @RequestParam(value="postalCode") String postalCode, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("city",city);
		paramMap.put("address1",address1);
		paramMap.put("postalCode",postalCode);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("address2",address2);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("attnName",attnName);
		paramMap.put("directions",directions);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePostalAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyTelecomNumber")
	public ResponseEntity<Object> updatePartyTelecomNumber(HttpSession session, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("extension",extension);
		paramMap.put("askForName",askForName);
		paramMap.put("comments",comments);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("areaCode",areaCode);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyTelecomNumber", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createNeedType")
	public ResponseEntity<Object> createNeedType(HttpSession session, @RequestParam(value="needTypeId", required=false) String needTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("needTypeId",needTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createNeedType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyNameForDate")
	public ResponseEntity<Object> getPartyNameForDate(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="compareDate", required=false) Timestamp compareDate, @RequestParam(value="lastNameFirst", required=false) String lastNameFirst) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("compareDate",compareDate);
		paramMap.put("lastNameFirst",lastNameFirst);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyNameForDate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyClassification")
	public ResponseEntity<Object> deletePartyClassification(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyClassification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyBasePermissionCheck")
	public ResponseEntity<Object> partyBasePermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyBasePermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyContactMech")
	public ResponseEntity<Object> deletePartyContactMech(HttpSession session, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyContactMech", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventRoleInterface")
	public ResponseEntity<Object> createCommunicationEventRoleInterface(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyId") String partyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyId",partyId);
		paramMap.put("statusId",statusId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventRoleInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeCommunicationEventRole")
	public ResponseEntity<Object> removeCommunicationEventRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyId") String partyId, @RequestParam(value="deleteCommEventIfLast", required=false) String deleteCommEventIfLast, @RequestParam(value="delContentDataResource", required=false) String delContentDataResource) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyId",partyId);
		paramMap.put("deleteCommEventIfLast",deleteCommEventIfLast);
		paramMap.put("delContentDataResource",delContentDataResource);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCommunicationEventRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAgreementItemTypeAttr")
	public ResponseEntity<Object> createAgreementItemTypeAttr(HttpSession session, @RequestParam(value="agreementItemTypeId") String agreementItemTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("agreementItemTypeId",agreementItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAgreementItemTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdatePartyTelecomNumber")
	public ResponseEntity<Object> createUpdatePartyTelecomNumber(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("extension",extension);
		paramMap.put("askForName",askForName);
		paramMap.put("comments",comments);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLogin",userLogin);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("areaCode",areaCode);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdatePartyTelecomNumber", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyPostalAddress")
	public ResponseEntity<Object> createPartyPostalAddress(HttpSession session, @RequestParam(value="city") String city, @RequestParam(value="address1") String address1, @RequestParam(value="postalCode") String postalCode, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("city",city);
		paramMap.put("address1",address1);
		paramMap.put("postalCode",postalCode);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attnName",attnName);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("partyId",partyId);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("directions",directions);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyPostalAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/copyPartyContactMechs")
	public ResponseEntity<Object> copyPartyContactMechs(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyPartyContactMechs", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeCommunicationEventPurpose")
	public ResponseEntity<Object> removeCommunicationEventPurpose(HttpSession session, @RequestParam(value="communicationEventPrpTypId") String communicationEventPrpTypId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventPrpTypId",communicationEventPrpTypId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCommunicationEventPurpose", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/verifyEmailAddress")
	public ResponseEntity<Object> verifyEmailAddress(HttpSession session, @RequestParam(value="verifyHash") String verifyHash) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("verifyHash",verifyHash);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("verifyEmailAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyInvitationRoleAssoc")
	public ResponseEntity<Object> createPartyInvitationRoleAssoc(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyInvitationRoleAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/storeIncomingEmail")
	public ResponseEntity<Object> storeIncomingEmail(HttpSession session, @RequestParam(value="messageWrapper") org.apache.ofbiz.service.mail.MimeMessageWrapper messageWrapper) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("messageWrapper",messageWrapper);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("storeIncomingEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendCreatePartyEmailNotification")
	public ResponseEntity<Object> sendCreatePartyEmailNotification(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendCreatePartyEmailNotification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAddressMatchMap")
	public ResponseEntity<Object> createAddressMatchMap(HttpSession session, @RequestParam(value="mapValue") String mapValue, @RequestParam(value="mapKey") String mapKey, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mapValue",mapValue);
		paramMap.put("mapKey",mapKey);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAddressMatchMap", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyRelationshipAndRole")
	public ResponseEntity<Object> createPartyRelationshipAndRole(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="relationshipName", required=false) String relationshipName, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="positionTitle", required=false) String positionTitle, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="priorityTypeId", required=false) String priorityTypeId, @RequestParam(value="permissionsEnumId", required=false) String permissionsEnumId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("relationshipName",relationshipName);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("positionTitle",positionTitle);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("priorityTypeId",priorityTypeId);
		paramMap.put("permissionsEnumId",permissionsEnumId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyRelationshipAndRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateNeedType")
	public ResponseEntity<Object> updateNeedType(HttpSession session, @RequestParam(value="needTypeId") String needTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("needTypeId",needTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateNeedType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPostalAddressBoundary")
	public ResponseEntity<Object> createPostalAddressBoundary(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPostalAddressBoundary", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyIdentifications")
	public ResponseEntity<Object> createPartyIdentifications(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="identifications") Map identifications) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("identifications",identifications);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyIdentifications", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setCommunicationEventStatus")
	public ResponseEntity<Object> setCommunicationEventStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="setRoleStatusToComplete") String setRoleStatusToComplete, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("setRoleStatusToComplete",setRoleStatusToComplete);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCommunicationEventStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommEventAfterEmail")
	public ResponseEntity<Object> updateCommEventAfterEmail(HttpSession session, @RequestParam(value="messageWrapper") org.apache.ofbiz.service.mail.MimeMessageWrapper messageWrapper, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("messageWrapper",messageWrapper);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommEventAfterEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setCommEventComplete")
	public ResponseEntity<Object> setCommEventComplete(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCommEventComplete", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createVendor")
	public ResponseEntity<Object> createVendor(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="manifestCompanyTitle", required=false) String manifestCompanyTitle, @RequestParam(value="manifestPolicies", required=false) String manifestPolicies, @RequestParam(value="manifestLogoUrl", required=false) String manifestLogoUrl, @RequestParam(value="manifestCompanyName", required=false) String manifestCompanyName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("manifestCompanyTitle",manifestCompanyTitle);
		paramMap.put("manifestPolicies",manifestPolicies);
		paramMap.put("manifestLogoUrl",manifestLogoUrl);
		paramMap.put("manifestCompanyName",manifestCompanyName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createVendor", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventRole")
	public ResponseEntity<Object> createCommunicationEventRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyId") String partyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyId",partyId);
		paramMap.put("statusId",statusId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyInvitation")
	public ResponseEntity<Object> updatePartyInvitation(HttpSession session, @RequestParam(value="partyInvitationId") String partyInvitationId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="emailAddress", required=false) String emailAddress, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="lastInviteDate", required=false) Timestamp lastInviteDate, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("statusId",statusId);
		paramMap.put("toName",toName);
		paramMap.put("lastInviteDate",lastInviteDate);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyInvitation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommunicationEventPrpTyp")
	public ResponseEntity<Object> updateCommunicationEventPrpTyp(HttpSession session, @RequestParam(value="communicationEventPrpTypId") String communicationEventPrpTypId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventPrpTypId",communicationEventPrpTypId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommunicationEventPrpTyp", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteParty")
	public ResponseEntity<Object> deleteParty(HttpSession session, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyEmailAddress")
	public ResponseEntity<Object> createPartyEmailAddress(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("extension",extension);
		paramMap.put("comments",comments);
		paramMap.put("verified",verified);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("infoString",infoString);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyEmailAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyRelationshipContactAccount")
	public ResponseEntity<Object> createPartyRelationshipContactAccount(HttpSession session, @RequestParam(value="contactPartyId") String contactPartyId, @RequestParam(value="accountPartyId") String accountPartyId, @RequestParam(value="comments", required=false) String comments) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactPartyId",contactPartyId);
		paramMap.put("accountPartyId",accountPartyId);
		paramMap.put("comments",comments);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyRelationshipContactAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommunicationEventRole")
	public ResponseEntity<Object> updateCommunicationEventRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyId") String partyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyId",partyId);
		paramMap.put("statusId",statusId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommunicationEventRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdatePartyPostalAddress")
	public ResponseEntity<Object> createUpdatePartyPostalAddress(HttpSession session, @RequestParam(value="city") String city, @RequestParam(value="address1") String address1, @RequestParam(value="postalCode") String postalCode, @RequestParam(value="partyId") String partyId, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("city",city);
		paramMap.put("address1",address1);
		paramMap.put("postalCode",postalCode);
		paramMap.put("partyId",partyId);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attnName",attnName);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLogin",userLogin);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("directions",directions);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdatePartyPostalAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPostalAddress")
	public ResponseEntity<Object> createPostalAddress(HttpSession session, @RequestParam(value="city") String city, @RequestParam(value="address1") String address1, @RequestParam(value="postalCode") String postalCode, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("city",city);
		paramMap.put("address1",address1);
		paramMap.put("postalCode",postalCode);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("address2",address2);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attnName",attnName);
		paramMap.put("directions",directions);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPostalAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContactMech")
	public ResponseEntity<Object> updateContactMech(HttpSession session, @RequestParam(value="contactMechTypeId") String contactMechTypeId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="infoString", required=false) String infoString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("infoString",infoString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContactMech", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCustRequestCommEvent")
	public ResponseEntity<Object> createCustRequestCommEvent(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustRequestCommEvent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAgreementAttribute")
	public ResponseEntity<Object> createAgreementAttribute(HttpSession session, @RequestParam(value="agreementId") String agreementId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("agreementId",agreementId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAgreementAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyMainRole")
	public ResponseEntity<Object> getPartyMainRole(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyMainRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateTelecomNumber")
	public ResponseEntity<Object> updateTelecomNumber(HttpSession session, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("areaCode",areaCode);
		paramMap.put("askForName",askForName);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTelecomNumber", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyInvitationGroupAssoc")
	public ResponseEntity<Object> createPartyInvitationGroupAssoc(HttpSession session, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyInvitationGroupAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyRole")
	public ResponseEntity<Object> createPartyRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyCommunicationEventPermissionCheck")
	public ResponseEntity<Object> partyCommunicationEventPermissionCheck(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyCommunicationEventPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyAttribute")
	public ResponseEntity<Object> createPartyAttribute(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyIdentification")
	public ResponseEntity<Object> createPartyIdentification(HttpSession session, @RequestParam(value="partyIdentificationTypeId") String partyIdentificationTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="idValue", required=false) String idValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdentificationTypeId",partyIdentificationTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("idValue",idValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyIdentification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyIdentification")
	public ResponseEntity<Object> updatePartyIdentification(HttpSession session, @RequestParam(value="partyIdentificationTypeId") String partyIdentificationTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="idValue", required=false) String idValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdentificationTypeId",partyIdentificationTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("idValue",idValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyIdentification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyRelationship")
	public ResponseEntity<Object> updatePartyRelationship(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="relationshipName", required=false) String relationshipName, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="positionTitle", required=false) String positionTitle, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="priorityTypeId", required=false) String priorityTypeId, @RequestParam(value="permissionsEnumId", required=false) String permissionsEnumId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("relationshipName",relationshipName);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("positionTitle",positionTitle);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("priorityTypeId",priorityTypeId);
		paramMap.put("permissionsEnumId",permissionsEnumId);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyRelationship", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyTextContent")
	public ResponseEntity<Object> createPartyTextContent(HttpSession session, @RequestParam(value="partyContentTypeId") String partyContentTypeId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyContentTypeId",partyContentTypeId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyTextContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setCommunicationEventRoleStatus")
	public ResponseEntity<Object> setCommunicationEventRoleStatus(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="statusId") String statusId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCommunicationEventRoleStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyInvitation")
	public ResponseEntity<Object> deletePartyInvitation(HttpSession session, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyInvitation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventRoleWithoutPermission")
	public ResponseEntity<Object> createCommunicationEventRoleWithoutPermission(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="partyId") String partyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("partyId",partyId);
		paramMap.put("statusId",statusId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventRoleWithoutPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendAccountActivatedEmailNotification")
	public ResponseEntity<Object> sendAccountActivatedEmailNotification(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="productStoreId") String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendAccountActivatedEmailNotification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendUpdatePersonalInfoEmailNotification")
	public ResponseEntity<Object> sendUpdatePersonalInfoEmailNotification(HttpSession session, @RequestParam(value="updatedUserLogin", required=false) org.apache.ofbiz.entity.GenericValue updatedUserLogin, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("updatedUserLogin",updatedUserLogin);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendUpdatePersonalInfoEmailNotification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPerson")
	public ResponseEntity<Object> createPerson(HttpSession session, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="yearsWithEmployer", required=false) Long yearsWithEmployer, @RequestParam(value="occupation", required=false) String occupation, @RequestParam(value="gender", required=false) String gender, @RequestParam(value="employmentStatusEnumId", required=false) String employmentStatusEnumId, @RequestParam(value="socialSecurityNumber", required=false) String socialSecurityNumber, @RequestParam(value="description", required=false) String description, @RequestParam(value="suffix", required=false) String suffix, @RequestParam(value="mothersMaidenName", required=false) String mothersMaidenName, @RequestParam(value="middleNameLocal", required=false) String middleNameLocal, @RequestParam(value="existingCustomer", required=false) String existingCustomer, @RequestParam(value="residenceStatusEnumId", required=false) String residenceStatusEnumId, @RequestParam(value="nickname", required=false) String nickname, @RequestParam(value="preferredCurrencyUomId", required=false) String preferredCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="memberId", required=false) String memberId, @RequestParam(value="height", required=false) BigDecimal height, @RequestParam(value="passportNumber", required=false) String passportNumber, @RequestParam(value="lastNameLocal", required=false) String lastNameLocal, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="monthsWithEmployer", required=false) Long monthsWithEmployer, @RequestParam(value="weight", required=false) BigDecimal weight, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="birthDate", required=false) Timestamp birthDate, @RequestParam(value="otherLocal", required=false) String otherLocal, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="cardId", required=false) String cardId, @RequestParam(value="middleName", required=false) String middleName, @RequestParam(value="firstNameLocal", required=false) String firstNameLocal, @RequestParam(value="passportExpireDate", required=false) Timestamp passportExpireDate, @RequestParam(value="salutation", required=false) String salutation, @RequestParam(value="personalTitle", required=false) String personalTitle, @RequestParam(value="deceasedDate", required=false) Timestamp deceasedDate, @RequestParam(value="totalYearsWorkExperience", required=false) BigDecimal totalYearsWorkExperience, @RequestParam(value="maritalStatus", required=false) String maritalStatus) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName",firstName);
		paramMap.put("lastName",lastName);
		paramMap.put("yearsWithEmployer",yearsWithEmployer);
		paramMap.put("occupation",occupation);
		paramMap.put("gender",gender);
		paramMap.put("employmentStatusEnumId",employmentStatusEnumId);
		paramMap.put("socialSecurityNumber",socialSecurityNumber);
		paramMap.put("description",description);
		paramMap.put("suffix",suffix);
		paramMap.put("mothersMaidenName",mothersMaidenName);
		paramMap.put("middleNameLocal",middleNameLocal);
		paramMap.put("existingCustomer",existingCustomer);
		paramMap.put("residenceStatusEnumId",residenceStatusEnumId);
		paramMap.put("nickname",nickname);
		paramMap.put("preferredCurrencyUomId",preferredCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("memberId",memberId);
		paramMap.put("height",height);
		paramMap.put("passportNumber",passportNumber);
		paramMap.put("lastNameLocal",lastNameLocal);
		paramMap.put("comments",comments);
		paramMap.put("monthsWithEmployer",monthsWithEmployer);
		paramMap.put("weight",weight);
		paramMap.put("externalId",externalId);
		paramMap.put("birthDate",birthDate);
		paramMap.put("otherLocal",otherLocal);
		paramMap.put("statusId",statusId);
		paramMap.put("cardId",cardId);
		paramMap.put("middleName",middleName);
		paramMap.put("firstNameLocal",firstNameLocal);
		paramMap.put("passportExpireDate",passportExpireDate);
		paramMap.put("salutation",salutation);
		paramMap.put("personalTitle",personalTitle);
		paramMap.put("deceasedDate",deceasedDate);
		paramMap.put("totalYearsWorkExperience",totalYearsWorkExperience);
		paramMap.put("maritalStatus",maritalStatus);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPerson", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyContactMechPurpose")
	public ResponseEntity<Object> createPartyContactMechPurpose(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyContactMechPurpose", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAffiliate")
	public ResponseEntity<Object> createAffiliate(HttpSession session, @RequestParam(value="affiliateName") String affiliateName, @RequestParam(value="partyId") String partyId, @RequestParam(value="yearEstablished", required=false) String yearEstablished, @RequestParam(value="affiliateDescription", required=false) String affiliateDescription, @RequestParam(value="sitePageViews", required=false) String sitePageViews, @RequestParam(value="siteVisitors", required=false) String siteVisitors, @RequestParam(value="siteType", required=false) String siteType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("affiliateName",affiliateName);
		paramMap.put("partyId",partyId);
		paramMap.put("yearEstablished",yearEstablished);
		paramMap.put("affiliateDescription",affiliateDescription);
		paramMap.put("sitePageViews",sitePageViews);
		paramMap.put("siteVisitors",siteVisitors);
		paramMap.put("siteType",siteType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAffiliate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyRelationshipPermissionCheck")
	public ResponseEntity<Object> partyRelationshipPermissionCheck(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyRelationshipPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyInvitationGroupAssoc")
	public ResponseEntity<Object> deletePartyInvitationGroupAssoc(HttpSession session, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyInvitationGroupAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partyIdPermissionCheck")
	public ResponseEntity<Object> partyIdPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("partyId",partyId);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("partyIdPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/importParty")
	public ResponseEntity<Object> importParty(HttpSession session, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("importParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteContactMechLink")
	public ResponseEntity<Object> deleteContactMechLink(HttpSession session, @RequestParam(value="contactMechIdFrom") String contactMechIdFrom, @RequestParam(value="contactMechIdTo") String contactMechIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechIdFrom",contactMechIdFrom);
		paramMap.put("contactMechIdTo",contactMechIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteContactMechLink", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommunicationEventPurpose")
	public ResponseEntity<Object> updateCommunicationEventPurpose(HttpSession session, @RequestParam(value="communicationEventPrpTypId") String communicationEventPrpTypId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventPrpTypId",communicationEventPrpTypId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommunicationEventPurpose", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRoleType")
	public ResponseEntity<Object> createRoleType(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRoleType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/acceptPartyInvitation")
	public ResponseEntity<Object> acceptPartyInvitation(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="partyInvitationId") String partyInvitationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("partyInvitationId",partyInvitationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("acceptPartyInvitation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAgreementItemTypeAttr")
	public ResponseEntity<Object> deleteAgreementItemTypeAttr(HttpSession session, @RequestParam(value="agreementItemTypeId") String agreementItemTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("agreementItemTypeId",agreementItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAgreementItemTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCommunicationEventWorkEffort")
	public ResponseEntity<Object> deleteCommunicationEventWorkEffort(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="delContentDataResource", required=false) String delContentDataResource) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("delContentDataResource",delContentDataResource);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCommunicationEventWorkEffort", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRoleTypeAttr")
	public ResponseEntity<Object> deleteRoleTypeAttr(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRoleTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyContactMech")
	public ResponseEntity<Object> updatePartyContactMech(HttpSession session, @RequestParam(value="contactMechTypeId") String contactMechTypeId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="newContactMechId", required=false) String newContactMechId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("extension",extension);
		paramMap.put("comments",comments);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("infoString",infoString);
		paramMap.put("newContactMechId",newContactMechId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyContactMech", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyContactMechPurposeIfExists")
	public ResponseEntity<Object> deletePartyContactMechPurposeIfExists(HttpSession session, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyContactMechPurposeIfExists", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCommunicationEventPrpTyp")
	public ResponseEntity<Object> deleteCommunicationEventPrpTyp(HttpSession session, @RequestParam(value="communicationEventPrpTypId") String communicationEventPrpTypId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventPrpTypId",communicationEventPrpTypId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCommunicationEventPrpTyp", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeContactMechAttribute")
	public ResponseEntity<Object> removeContactMechAttribute(HttpSession session, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContactMechAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePostalAddressAndPurposes")
	public ResponseEntity<Object> updatePostalAddressAndPurposes(HttpSession session, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="city", required=false) String city, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="setShippingPurpose", required=false) String setShippingPurpose, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="address1", required=false) String address1, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="setBillingPurpose", required=false) String setBillingPurpose, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("city",city);
		paramMap.put("postalCode",postalCode);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("attnName",attnName);
		paramMap.put("setShippingPurpose",setShippingPurpose);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("address1",address1);
		paramMap.put("verified",verified);
		paramMap.put("monthsWithContactMech",monthsWithContactMech);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("setBillingPurpose",setBillingPurpose);
		paramMap.put("allowSolicitation",allowSolicitation);
		paramMap.put("yearsWithContactMech",yearsWithContactMech);
		paramMap.put("directions",directions);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePostalAddressAndPurposes", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCommunicationEvent")
	public ResponseEntity<Object> deleteCommunicationEvent(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="delContentDataResource", required=false) String delContentDataResource) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("delContentDataResource",delContentDataResource);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCommunicationEvent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyContent")
	public ResponseEntity<Object> createPartyContent(HttpSession session, @RequestParam(value="partyContentTypeId") String partyContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyContentTypeId",partyContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
