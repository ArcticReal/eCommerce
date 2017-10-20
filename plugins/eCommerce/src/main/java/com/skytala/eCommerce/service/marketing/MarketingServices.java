package com.skytala.eCommerce.service.marketing;

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
@RequestMapping("/service/Marketings")
public class MarketingServices{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteMarketingCampaignRole")
	public ResponseEntity<Object> deleteMarketingCampaignRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteMarketingCampaignRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContactListParty")
	public ResponseEntity<Object> updateContactListParty(HttpSession session, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="optInVerifyCode", required=false) String optInVerifyCode, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="preferredContactMechId", required=false) String preferredContactMechId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("optInVerifyCode",optInVerifyCode);
		paramMap.put("statusId",statusId);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContactListParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSegmentGroupGeo")
	public ResponseEntity<Object> createSegmentGroupGeo(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="segmentGroupId") String segmentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSegmentGroupGeo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTrackingCode")
	public ResponseEntity<Object> createTrackingCode(HttpSession session, @RequestParam(value="trackingCodeId") String trackingCodeId, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="trackableLifetime", required=false) Long trackableLifetime, @RequestParam(value="redirectUrl", required=false) String redirectUrl, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="marketingCampaignId", required=false) String marketingCampaignId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="overrideLogo", required=false) String overrideLogo, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="description", required=false) String description, @RequestParam(value="billableLifetime", required=false) Long billableLifetime, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="subgroupId", required=false) String subgroupId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="overrideCss", required=false) String overrideCss, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("trackableLifetime",trackableLifetime);
		paramMap.put("redirectUrl",redirectUrl);
		paramMap.put("comments",comments);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("overrideLogo",overrideLogo);
		paramMap.put("groupId",groupId);
		paramMap.put("description",description);
		paramMap.put("billableLifetime",billableLifetime);
		paramMap.put("thruDate",thruDate);
		paramMap.put("subgroupId",subgroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("overrideCss",overrideCss);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTrackingCode", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendContactListPartyUnSubscribeVerifyEmail")
	public ResponseEntity<Object> sendContactListPartyUnSubscribeVerifyEmail(HttpSession session, @RequestParam(value="preferredContactMechId") String preferredContactMechId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("contactListId",contactListId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendContactListPartyUnSubscribeVerifyEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSegmentGroupRole")
	public ResponseEntity<Object> updateSegmentGroupRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="segmentGroupId") String segmentGroupId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSegmentGroupRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPromoToMarketingCampaign")
	public ResponseEntity<Object> addPromoToMarketingCampaign(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPromoToMarketingCampaign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContactListPartyNoUserLogin")
	public ResponseEntity<Object> updateContactListPartyNoUserLogin(HttpSession session, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="optInVerifyCode", required=false) String optInVerifyCode, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="preferredContactMechId", required=false) String preferredContactMechId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="email", required=false) String email, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("optInVerifyCode",optInVerifyCode);
		paramMap.put("statusId",statusId);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("email",email);
		paramMap.put("thruDate",thruDate);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContactListPartyNoUserLogin", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTrackingCodeOrderReturns")
	public ResponseEntity<Object> createTrackingCodeOrderReturns(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTrackingCodeOrderReturns", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unsubscribeContactListPartyContachMech")
	public ResponseEntity<Object> unsubscribeContactListPartyContachMech(HttpSession session, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="preferredContactMechId") String preferredContactMechId, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("unsubscribeContactListPartyContachMech", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContact")
	public ResponseEntity<Object> createContact(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="emailAddress", required=false) String emailAddress, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="quickAdd", required=false) String quickAdd) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("extension",extension);
		paramMap.put("contactListId",contactListId);
		paramMap.put("quickAdd",quickAdd);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContact", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unsubscribeContactListParty")
	public ResponseEntity<Object> unsubscribeContactListParty(HttpSession session, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="email") String email, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("email",email);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("unsubscribeContactListParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSegmentGroupGeo")
	public ResponseEntity<Object> deleteSegmentGroupGeo(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="segmentGroupId") String segmentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSegmentGroupGeo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendContactListPartySubscribeEmail")
	public ResponseEntity<Object> sendContactListPartySubscribeEmail(HttpSession session, @RequestParam(value="preferredContactMechId") String preferredContactMechId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("contactListId",contactListId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendContactListPartySubscribeEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/optOutOfListFromCommEvent")
	public ResponseEntity<Object> optOutOfListFromCommEvent(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("optOutOfListFromCommEvent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeContactList")
	public ResponseEntity<Object> removeContactList(HttpSession session, @RequestParam(value="contactListId") String contactListId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteContactListParty")
	public ResponseEntity<Object> deleteContactListParty(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contactListId",contactListId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteContactListParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPriceRuleToMarketingCampaign")
	public ResponseEntity<Object> addPriceRuleToMarketingCampaign(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="productPriceRuleId") String productPriceRuleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPriceRuleToMarketingCampaign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContactList")
	public ResponseEntity<Object> updateContactList(HttpSession session, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="verifyEmailSubject", required=false) String verifyEmailSubject, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="contactListName", required=false) String contactListName, @RequestParam(value="marketingCampaignId", required=false) String marketingCampaignId, @RequestParam(value="optOutScreen", required=false) String optOutScreen, @RequestParam(value="description", required=false) String description, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="contactListTypeId", required=false) String contactListTypeId, @RequestParam(value="verifyEmailFrom", required=false) String verifyEmailFrom, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="singleUse", required=false) String singleUse, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="verifyEmailScreen", required=false) String verifyEmailScreen, @RequestParam(value="verifyEmailWebSiteId", required=false) String verifyEmailWebSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("verifyEmailSubject",verifyEmailSubject);
		paramMap.put("comments",comments);
		paramMap.put("contactListName",contactListName);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("optOutScreen",optOutScreen);
		paramMap.put("description",description);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("contactListTypeId",contactListTypeId);
		paramMap.put("verifyEmailFrom",verifyEmailFrom);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("singleUse",singleUse);
		paramMap.put("isPublic",isPublic);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("verifyEmailScreen",verifyEmailScreen);
		paramMap.put("verifyEmailWebSiteId",verifyEmailWebSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSegmentGroupRole")
	public ResponseEntity<Object> deleteSegmentGroupRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="segmentGroupId") String segmentGroupId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSegmentGroupRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTrackingCodeOrderReturn")
	public ResponseEntity<Object> deleteTrackingCodeOrderReturn(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTrackingCodeOrderReturn", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAccount")
	public ResponseEntity<Object> createAccount(HttpSession session, @RequestParam(value="emailAddress", required=false) String emailAddress, @RequestParam(value="extension", required=false) String extension) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("extension",extension);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createMarketingCampaignNote")
	public ResponseEntity<Object> createMarketingCampaignNote(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="noteId") String noteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("noteId",noteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMarketingCampaignNote", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateTrackingCodeOrderReturn")
	public ResponseEntity<Object> updateTrackingCodeOrderReturn(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="returnId") String returnId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="trackingCodeId", required=false) String trackingCodeId, @RequestParam(value="siteId", required=false) String siteId, @RequestParam(value="hasExported", required=false) String hasExported, @RequestParam(value="affiliateReferredTimeStamp", required=false) Timestamp affiliateReferredTimeStamp, @RequestParam(value="isBillable", required=false) String isBillable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("returnId",returnId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("siteId",siteId);
		paramMap.put("hasExported",hasExported);
		paramMap.put("affiliateReferredTimeStamp",affiliateReferredTimeStamp);
		paramMap.put("isBillable",isBillable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTrackingCodeOrderReturn", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebSiteContactList")
	public ResponseEntity<Object> updateWebSiteContactList(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contactListId",contactListId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebSiteContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createMarketingCampaignRole")
	public ResponseEntity<Object> createMarketingCampaignRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMarketingCampaignRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signUpForContactList")
	public ResponseEntity<Object> signUpForContactList(HttpSession session, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="email") String email, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListId",contactListId);
		paramMap.put("email",email);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("signUpForContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSegmentGroupClassification")
	public ResponseEntity<Object> createSegmentGroupClassification(HttpSession session, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="segmentGroupId") String segmentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSegmentGroupClassification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/convertLeadToContact")
	public ResponseEntity<Object> convertLeadToContact(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="partyGroupId") String partyGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("partyGroupId",partyGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("convertLeadToContact", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createMarketingCampaign")
	public ResponseEntity<Object> createMarketingCampaign(HttpSession session, @RequestParam(value="expectedResponsePercent", required=false) BigDecimal expectedResponsePercent, @RequestParam(value="expectedRevenue", required=false) BigDecimal expectedRevenue, @RequestParam(value="convertedLeads", required=false) String convertedLeads, @RequestParam(value="campaignSummary", required=false) String campaignSummary, @RequestParam(value="numSent", required=false) Long numSent, @RequestParam(value="parentCampaignId", required=false) String parentCampaignId, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="isActive", required=false) String isActive, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="budgetedCost", required=false) BigDecimal budgetedCost, @RequestParam(value="campaignName", required=false) String campaignName, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="startDate", required=false) Timestamp startDate, @RequestParam(value="actualCost", required=false) BigDecimal actualCost) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("expectedResponsePercent",expectedResponsePercent);
		paramMap.put("expectedRevenue",expectedRevenue);
		paramMap.put("convertedLeads",convertedLeads);
		paramMap.put("campaignSummary",campaignSummary);
		paramMap.put("numSent",numSent);
		paramMap.put("parentCampaignId",parentCampaignId);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("isActive",isActive);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("budgetedCost",budgetedCost);
		paramMap.put("campaignName",campaignName);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("startDate",startDate);
		paramMap.put("actualCost",actualCost);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMarketingCampaign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removePriceRuleFromMarketingCampaign")
	public ResponseEntity<Object> removePriceRuleFromMarketingCampaign(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="productPriceRuleId") String productPriceRuleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePriceRuleFromMarketingCampaign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContactListParty")
	public ResponseEntity<Object> createContactListParty(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="baseLocation", required=false) String baseLocation, @RequestParam(value="preferredContactMechId", required=false) String preferredContactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("contactListId",contactListId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContactListParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSegmentGroupClassification")
	public ResponseEntity<Object> updateSegmentGroupClassification(HttpSession session, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="segmentGroupId") String segmentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSegmentGroupClassification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/arePartiesRelatedThroughSegmentGroup")
	public ResponseEntity<Object> arePartiesRelatedThroughSegmentGroup(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="toPartyId") String toPartyId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="toRoleTypeId", required=false) String toRoleTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("toPartyId",toPartyId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("toRoleTypeId",toRoleTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("arePartiesRelatedThroughSegmentGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTrackingCodeOrderReturn")
	public ResponseEntity<Object> createTrackingCodeOrderReturn(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="returnId") String returnId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="trackingCodeId", required=false) String trackingCodeId, @RequestParam(value="siteId", required=false) String siteId, @RequestParam(value="hasExported", required=false) String hasExported, @RequestParam(value="affiliateReferredTimeStamp", required=false) Timestamp affiliateReferredTimeStamp, @RequestParam(value="isBillable", required=false) String isBillable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("returnId",returnId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("siteId",siteId);
		paramMap.put("hasExported",hasExported);
		paramMap.put("affiliateReferredTimeStamp",affiliateReferredTimeStamp);
		paramMap.put("isBillable",isBillable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTrackingCodeOrderReturn", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContactList")
	public ResponseEntity<Object> createContactList(HttpSession session, @RequestParam(value="contactListName") String contactListName, @RequestParam(value="contactListTypeId") String contactListTypeId, @RequestParam(value="verifyEmailSubject", required=false) String verifyEmailSubject, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="marketingCampaignId", required=false) String marketingCampaignId, @RequestParam(value="optOutScreen", required=false) String optOutScreen, @RequestParam(value="description", required=false) String description, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="verifyEmailFrom", required=false) String verifyEmailFrom, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="singleUse", required=false) String singleUse, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="verifyEmailScreen", required=false) String verifyEmailScreen, @RequestParam(value="verifyEmailWebSiteId", required=false) String verifyEmailWebSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactListName",contactListName);
		paramMap.put("contactListTypeId",contactListTypeId);
		paramMap.put("verifyEmailSubject",verifyEmailSubject);
		paramMap.put("comments",comments);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("optOutScreen",optOutScreen);
		paramMap.put("description",description);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("verifyEmailFrom",verifyEmailFrom);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("singleUse",singleUse);
		paramMap.put("contactListId",contactListId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("verifyEmailScreen",verifyEmailScreen);
		paramMap.put("verifyEmailWebSiteId",verifyEmailWebSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTrackingCode")
	public ResponseEntity<Object> deleteTrackingCode(HttpSession session, @RequestParam(value="trackingCodeId") String trackingCodeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTrackingCode", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendContactListPartyVerifyEmail")
	public ResponseEntity<Object> sendContactListPartyVerifyEmail(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="partyId") String partyId, @RequestParam(value="baseLocation", required=false) String baseLocation) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contactListId",contactListId);
		paramMap.put("partyId",partyId);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendContactListPartyVerifyEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateTrackingCodeOrder")
	public ResponseEntity<Object> updateTrackingCodeOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="trackingCodeId", required=false) String trackingCodeId, @RequestParam(value="siteId", required=false) String siteId, @RequestParam(value="hasExported", required=false) String hasExported, @RequestParam(value="affiliateReferredTimeStamp", required=false) Timestamp affiliateReferredTimeStamp, @RequestParam(value="isBillable", required=false) String isBillable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("siteId",siteId);
		paramMap.put("hasExported",hasExported);
		paramMap.put("affiliateReferredTimeStamp",affiliateReferredTimeStamp);
		paramMap.put("isBillable",isBillable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTrackingCodeOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSegmentGroupType")
	public ResponseEntity<Object> updateSegmentGroupType(HttpSession session, @RequestParam(value="segmentGroupTypeId") String segmentGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("segmentGroupTypeId",segmentGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSegmentGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteMarketingCampaign")
	public ResponseEntity<Object> deleteMarketingCampaign(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteMarketingCampaign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateMarketingCampaign")
	public ResponseEntity<Object> updateMarketingCampaign(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="expectedResponsePercent", required=false) BigDecimal expectedResponsePercent, @RequestParam(value="expectedRevenue", required=false) BigDecimal expectedRevenue, @RequestParam(value="convertedLeads", required=false) String convertedLeads, @RequestParam(value="campaignSummary", required=false) String campaignSummary, @RequestParam(value="numSent", required=false) Long numSent, @RequestParam(value="parentCampaignId", required=false) String parentCampaignId, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="isActive", required=false) String isActive, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="budgetedCost", required=false) BigDecimal budgetedCost, @RequestParam(value="campaignName", required=false) String campaignName, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="startDate", required=false) Timestamp startDate, @RequestParam(value="actualCost", required=false) BigDecimal actualCost) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("expectedResponsePercent",expectedResponsePercent);
		paramMap.put("expectedRevenue",expectedRevenue);
		paramMap.put("convertedLeads",convertedLeads);
		paramMap.put("campaignSummary",campaignSummary);
		paramMap.put("numSent",numSent);
		paramMap.put("parentCampaignId",parentCampaignId);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("isActive",isActive);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("budgetedCost",budgetedCost);
		paramMap.put("campaignName",campaignName);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("startDate",startDate);
		paramMap.put("actualCost",actualCost);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateMarketingCampaign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSegmentGroupRole")
	public ResponseEntity<Object> createSegmentGroupRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="segmentGroupId") String segmentGroupId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSegmentGroupRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/marketingPermissionService")
	public ResponseEntity<Object> marketingPermissionService(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("marketingPermissionService", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTrackingCodeType")
	public ResponseEntity<Object> deleteTrackingCodeType(HttpSession session, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTrackingCodeType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSegmentGroup")
	public ResponseEntity<Object> updateSegmentGroup(HttpSession session, @RequestParam(value="segmentGroupId") String segmentGroupId, @RequestParam(value="description", required=false) String description, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="segmentGroupTypeId", required=false) String segmentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("description",description);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("segmentGroupTypeId",segmentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSegmentGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContactListCommStatus")
	public ResponseEntity<Object> updateContactListCommStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="contactMechId") String contactMechId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("contactListId",contactListId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("messageId",messageId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContactListCommStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTrackingCodeOrder")
	public ResponseEntity<Object> createTrackingCodeOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="trackingCodeId", required=false) String trackingCodeId, @RequestParam(value="siteId", required=false) String siteId, @RequestParam(value="hasExported", required=false) String hasExported, @RequestParam(value="affiliateReferredTimeStamp", required=false) Timestamp affiliateReferredTimeStamp, @RequestParam(value="isBillable", required=false) String isBillable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("siteId",siteId);
		paramMap.put("hasExported",hasExported);
		paramMap.put("affiliateReferredTimeStamp",affiliateReferredTimeStamp);
		paramMap.put("isBillable",isBillable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTrackingCodeOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTrackingCodeType")
	public ResponseEntity<Object> createTrackingCodeType(HttpSession session, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTrackingCodeType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/exportVCard")
	public ResponseEntity<Object> exportVCard(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("exportVCard", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendContactListPartyUnSubscribeEmail")
	public ResponseEntity<Object> sendContactListPartyUnSubscribeEmail(HttpSession session, @RequestParam(value="preferredContactMechId") String preferredContactMechId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("contactListId",contactListId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendContactListPartyUnSubscribeEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createLead")
	public ResponseEntity<Object> createLead(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="emailAddress", required=false) String emailAddress, @RequestParam(value="groupName", required=false) String groupName, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="numEmployees", required=false) String numEmployees, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="title", required=false) String title, @RequestParam(value="officeSiteName", required=false) String officeSiteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("groupName",groupName);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("extension",extension);
		paramMap.put("numEmployees",numEmployees);
		paramMap.put("contactListId",contactListId);
		paramMap.put("title",title);
		paramMap.put("officeSiteName",officeSiteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createLead", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/mergeContacts")
	public ResponseEntity<Object> mergeContacts(HttpSession session, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="partyId") String partyId, @RequestParam(value="emailContactMechId", required=false) String emailContactMechId, @RequestParam(value="addrContactMechId", required=false) String addrContactMechId, @RequestParam(value="useEmail2", required=false) String useEmail2, @RequestParam(value="useContactNum2", required=false) String useContactNum2, @RequestParam(value="phoneContactMechIdTo", required=false) String phoneContactMechIdTo, @RequestParam(value="emailContactMechIdTo", required=false) String emailContactMechIdTo, @RequestParam(value="addrContactMechIdTo", required=false) String addrContactMechIdTo, @RequestParam(value="phoneContactMechId", required=false) String phoneContactMechId, @RequestParam(value="useAddress2", required=false) String useAddress2) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("partyId",partyId);
		paramMap.put("emailContactMechId",emailContactMechId);
		paramMap.put("addrContactMechId",addrContactMechId);
		paramMap.put("useEmail2",useEmail2);
		paramMap.put("useContactNum2",useContactNum2);
		paramMap.put("phoneContactMechIdTo",phoneContactMechIdTo);
		paramMap.put("emailContactMechIdTo",emailContactMechIdTo);
		paramMap.put("addrContactMechIdTo",addrContactMechIdTo);
		paramMap.put("phoneContactMechId",phoneContactMechId);
		paramMap.put("useAddress2",useAddress2);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("mergeContacts", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removePromoFromMarketingCampaign")
	public ResponseEntity<Object> removePromoFromMarketingCampaign(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePromoFromMarketingCampaign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateTrackingCode")
	public ResponseEntity<Object> updateTrackingCode(HttpSession session, @RequestParam(value="trackingCodeId") String trackingCodeId, @RequestParam(value="trackableLifetime", required=false) Long trackableLifetime, @RequestParam(value="redirectUrl", required=false) String redirectUrl, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="marketingCampaignId", required=false) String marketingCampaignId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="overrideLogo", required=false) String overrideLogo, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="description", required=false) String description, @RequestParam(value="billableLifetime", required=false) Long billableLifetime, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="subgroupId", required=false) String subgroupId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="trackingCodeTypeId", required=false) String trackingCodeTypeId, @RequestParam(value="overrideCss", required=false) String overrideCss, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeId",trackingCodeId);
		paramMap.put("trackableLifetime",trackableLifetime);
		paramMap.put("redirectUrl",redirectUrl);
		paramMap.put("comments",comments);
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("overrideLogo",overrideLogo);
		paramMap.put("groupId",groupId);
		paramMap.put("description",description);
		paramMap.put("billableLifetime",billableLifetime);
		paramMap.put("thruDate",thruDate);
		paramMap.put("subgroupId",subgroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("overrideCss",overrideCss);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTrackingCode", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSegmentGroup")
	public ResponseEntity<Object> createSegmentGroup(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="segmentGroupTypeId", required=false) String segmentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("segmentGroupTypeId",segmentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSegmentGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSegmentGroupType")
	public ResponseEntity<Object> createSegmentGroupType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="segmentGroupTypeId", required=false) String segmentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("segmentGroupTypeId",segmentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSegmentGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSegmentGroupClassification")
	public ResponseEntity<Object> deleteSegmentGroupClassification(HttpSession session, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="segmentGroupId") String segmentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSegmentGroupClassification", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWebSiteContactList")
	public ResponseEntity<Object> createWebSiteContactList(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contactListId",contactListId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebSiteContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSegmentGroup")
	public ResponseEntity<Object> deleteSegmentGroup(HttpSession session, @RequestParam(value="segmentGroupId") String segmentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSegmentGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSegmentGroupType")
	public ResponseEntity<Object> deleteSegmentGroupType(HttpSession session, @RequestParam(value="segmentGroupTypeId") String segmentGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("segmentGroupTypeId",segmentGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSegmentGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommStatusFromCommEvent")
	public ResponseEntity<Object> updateCommStatusFromCommEvent(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommStatusFromCommEvent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/importVCard")
	public ResponseEntity<Object> importVCard(HttpSession session, @RequestParam(value="infile") java.nio.ByteBuffer infile, @RequestParam(value="serviceName") String serviceName, @RequestParam(value="partyType", required=false) String partyType, @RequestParam(value="serviceContext", required=false) Map serviceContext) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("infile",infile);
		paramMap.put("serviceName",serviceName);
		paramMap.put("partyType",partyType);
		paramMap.put("serviceContext",serviceContext);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("importVCard", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWebSiteContactList")
	public ResponseEntity<Object> deleteWebSiteContactList(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="webSiteId") String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contactListId",contactListId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWebSiteContactList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyEmailContactListParty")
	public ResponseEntity<Object> updatePartyEmailContactListParty(HttpSession session, @RequestParam(value="oldContactMechId") String oldContactMechId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("oldContactMechId",oldContactMechId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyEmailContactListParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteMarketingCampaignNote")
	public ResponseEntity<Object> deleteMarketingCampaignNote(HttpSession session, @RequestParam(value="marketingCampaignId") String marketingCampaignId, @RequestParam(value="noteId") String noteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketingCampaignId",marketingCampaignId);
		paramMap.put("noteId",noteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteMarketingCampaignNote", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateTrackingCodeType")
	public ResponseEntity<Object> updateTrackingCodeType(HttpSession session, @RequestParam(value="trackingCodeTypeId") String trackingCodeTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trackingCodeTypeId",trackingCodeTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTrackingCodeType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSegmentGroupGeo")
	public ResponseEntity<Object> updateSegmentGroupGeo(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="segmentGroupId") String segmentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("segmentGroupId",segmentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSegmentGroupGeo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContactListPartyStatus")
	public ResponseEntity<Object> createContactListPartyStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="contactListId") String contactListId, @RequestParam(value="preferredContactMechId") String preferredContactMechId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="optInVerifyCode", required=false) String optInVerifyCode, @RequestParam(value="baseLocation", required=false) String baseLocation) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("contactListId",contactListId);
		paramMap.put("preferredContactMechId",preferredContactMechId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("optInVerifyCode",optInVerifyCode);
		paramMap.put("baseLocation",baseLocation);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContactListPartyStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
