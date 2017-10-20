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
@RequestMapping("/service/PartyView")
public class PartyView{

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyFromUserLogin")
	public ResponseEntity<Object> getPartyFromUserLogin(HttpSession session, @RequestParam(value="userLoginId") String userLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyFromUserLogin", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyFromName")
	public ResponseEntity<Object> getPartyFromName(HttpSession session, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="lastName", required=false) String lastName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName",firstName);
		paramMap.put("lastName",lastName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyFromName", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartiesByRelationship")
	public ResponseEntity<Object> getPartiesByRelationship(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="positionTitle", required=false) String positionTitle, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="priorityTypeId", required=false) String priorityTypeId, @RequestParam(value="permissionsEnumId", required=false) String permissionsEnumId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="relationshipName", required=false) String relationshipName, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="partyIdTo", required=false) String partyIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
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
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartiesByRelationship", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyFromEmail")
	public ResponseEntity<Object> getPartyFromEmail(HttpSession session, @RequestParam(value="email") String email) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email",email);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyFromEmail", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findParty")
	public ResponseEntity<Object> findParty(HttpSession session, @RequestParam(value="lastName", required=false) String lastName, @RequestParam(value="VIEW_SIZE", required=false) String VIEW_SIZE, @RequestParam(value="city", required=false) String city, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="softIdentifier", required=false) String softIdentifier, @RequestParam(value="extInfo", required=false) String extInfo, @RequestParam(value="partyTypeId", required=false) String partyTypeId, @RequestParam(value="showAll", required=false) String showAll, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="sortField", required=false) String sortField, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="ownerPartyIds", required=false) List ownerPartyIds, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="address1", required=false) String address1, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="lookupFlag", required=false) String lookupFlag, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="groupName", required=false) String groupName, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="VIEW_INDEX", required=false) String VIEW_INDEX) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lastName",lastName);
		paramMap.put("VIEW_SIZE",VIEW_SIZE);
		paramMap.put("city",city);
		paramMap.put("postalCode",postalCode);
		paramMap.put("softIdentifier",softIdentifier);
		paramMap.put("extInfo",extInfo);
		paramMap.put("partyTypeId",partyTypeId);
		paramMap.put("showAll",showAll);
		paramMap.put("infoString",infoString);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("sortField",sortField);
		paramMap.put("partyId",partyId);
		paramMap.put("ownerPartyIds",ownerPartyIds);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("address2",address2);
		paramMap.put("address1",address1);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("lookupFlag",lookupFlag);
		paramMap.put("firstName",firstName);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("groupName",groupName);
		paramMap.put("areaCode",areaCode);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("VIEW_INDEX",VIEW_INDEX);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPerson")
	public ResponseEntity<Object> getPerson(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPerson", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getParentOrganizations")
	public ResponseEntity<Object> getParentOrganizations(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="getParentsOfParents", required=false) String getParentsOfParents) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("getParentsOfParents",getParentsOfParents);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getParentOrganizations", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPostalAddressBoundary")
	public ResponseEntity<Object> getPostalAddressBoundary(HttpSession session, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPostalAddressBoundary", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyContactMechValueMaps")
	public ResponseEntity<Object> getPartyContactMechValueMaps(HttpSession session, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="showOld", required=false) Boolean showOld, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin",userLogin);
		paramMap.put("showOld",showOld);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("partyId",partyId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyContactMechValueMaps", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getRelatedParties")
	public ResponseEntity<Object> getRelatedParties(HttpSession session, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="includeFromToSwitched", required=false) String includeFromToSwitched, @RequestParam(value="roleTypeIdToIncludeAllChildTypes", required=false) String roleTypeIdToIncludeAllChildTypes, @RequestParam(value="recurse", required=false) String recurse, @RequestParam(value="useCache", required=false) String useCache, @RequestParam(value="roleTypeIdFromInclueAllChildTypes", required=false) String roleTypeIdFromInclueAllChildTypes, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("includeFromToSwitched",includeFromToSwitched);
		paramMap.put("roleTypeIdToIncludeAllChildTypes",roleTypeIdToIncludeAllChildTypes);
		paramMap.put("recurse",recurse);
		paramMap.put("useCache",useCache);
		paramMap.put("roleTypeIdFromInclueAllChildTypes",roleTypeIdFromInclueAllChildTypes);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRelatedParties", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/performFindParty")
	public ResponseEntity<Object> performFindParty(HttpSession session, @RequestParam(value="lastName", required=false) String lastName, @RequestParam(value="city", required=false) String city, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="softIdentifier", required=false) String softIdentifier, @RequestParam(value="extInfo", required=false) String extInfo, @RequestParam(value="partyTypeId", required=false) String partyTypeId, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="partyIdentificationTypeId", required=false) String partyIdentificationTypeId, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="sortField", required=false) String sortField, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="ownerPartyIds", required=false) List ownerPartyIds, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="address1", required=false) String address1, @RequestParam(value="noConditionFind", required=false) String noConditionFind, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="idValue", required=false) String idValue, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="partyRelationshipTypeId", required=false) String partyRelationshipTypeId, @RequestParam(value="groupName", required=false) String groupName, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="extCond", required=false) org.apache.ofbiz.entity.condition.EntityCondition extCond) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lastName",lastName);
		paramMap.put("city",city);
		paramMap.put("postalCode",postalCode);
		paramMap.put("softIdentifier",softIdentifier);
		paramMap.put("extInfo",extInfo);
		paramMap.put("partyTypeId",partyTypeId);
		paramMap.put("infoString",infoString);
		paramMap.put("countryCode",countryCode);
		paramMap.put("partyIdentificationTypeId",partyIdentificationTypeId);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("sortField",sortField);
		paramMap.put("partyId",partyId);
		paramMap.put("ownerPartyIds",ownerPartyIds);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("address2",address2);
		paramMap.put("address1",address1);
		paramMap.put("noConditionFind",noConditionFind);
		paramMap.put("externalId",externalId);
		paramMap.put("idValue",idValue);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("firstName",firstName);
		paramMap.put("partyRelationshipTypeId",partyRelationshipTypeId);
		paramMap.put("groupName",groupName);
		paramMap.put("areaCode",areaCode);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("extCond",extCond);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("performFindParty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyFromGroupName")
	public ResponseEntity<Object> getPartyFromGroupName(HttpSession session, @RequestParam(value="groupName") String groupName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupName",groupName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyFromGroupName", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyFromExternalId")
	public ResponseEntity<Object> getPartyFromExternalId(HttpSession session, @RequestParam(value="externalId") String externalId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("externalId",externalId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyFromExternalId", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getChildRoleTypes")
	public ResponseEntity<Object> getChildRoleTypes(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getChildRoleTypes", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
