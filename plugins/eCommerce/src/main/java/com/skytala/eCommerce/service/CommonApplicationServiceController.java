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

@Deprecated
@RestController
@RequestMapping("/service/CommonApplicationController")
public class CommonApplicationServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateNote")
	public ResponseEntity<Object> updateNote(HttpSession session, @RequestParam(value="noteId") String noteId, @RequestParam(value="noteName", required=false) String noteName, @RequestParam(value="noteDateTime", required=false) Timestamp noteDateTime, @RequestParam(value="noteInfo", required=false) String noteInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("noteId",noteId);
		paramMap.put("noteName",noteName);
		paramMap.put("noteDateTime",noteDateTime);
		paramMap.put("noteInfo",noteInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateNote", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ftpInterface")
	public ResponseEntity<Object> ftpInterface(HttpSession session, @RequestParam(value="hostname") String hostname, @RequestParam(value="password") String password, @RequestParam(value="remoteFilename") String remoteFilename, @RequestParam(value="localFilename") String localFilename, @RequestParam(value="username") String username, @RequestParam(value="defaultTimeout", required=false) Integer defaultTimeout, @RequestParam(value="binaryTransfer", required=false) Boolean binaryTransfer, @RequestParam(value="passiveMode", required=false) Boolean passiveMode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("hostname",hostname);
		paramMap.put("password",password);
		paramMap.put("remoteFilename",remoteFilename);
		paramMap.put("localFilename",localFilename);
		paramMap.put("username",username);
		paramMap.put("defaultTimeout",defaultTimeout);
		paramMap.put("binaryTransfer",binaryTransfer);
		paramMap.put("passiveMode",passiveMode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ftpInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDataSourceType")
	public ResponseEntity<Object> createDataSourceType(HttpSession session, @RequestParam(value="dataSourceTypeId") String dataSourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceTypeId",dataSourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataSourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGeoType")
	public ResponseEntity<Object> deleteGeoType(HttpSession session, @RequestParam(value="geoTypeId") String geoTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoTypeId",geoTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGeoType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/performFind")
	public ResponseEntity<Object> performFind(HttpSession session, @RequestParam(value="inputFields") java.util.Map inputFields, @RequestParam(value="entityName") String entityName, @RequestParam(value="viewSize", required=false) Integer viewSize, @RequestParam(value="filterByDate", required=false) String filterByDate, @RequestParam(value="filterByDateValue", required=false) Timestamp filterByDateValue, @RequestParam(value="thruDateName", required=false) String thruDateName, @RequestParam(value="orderBy", required=false) String orderBy, @RequestParam(value="noConditionFind", required=false) String noConditionFind, @RequestParam(value="distinct", required=false) String distinct, @RequestParam(value="viewIndex", required=false) Integer viewIndex, @RequestParam(value="fieldList", required=false) java.util.List fieldList, @RequestParam(value="fromDateName", required=false) String fromDateName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputFields",inputFields);
		paramMap.put("entityName",entityName);
		paramMap.put("viewSize",viewSize);
		paramMap.put("filterByDate",filterByDate);
		paramMap.put("filterByDateValue",filterByDateValue);
		paramMap.put("thruDateName",thruDateName);
		paramMap.put("orderBy",orderBy);
		paramMap.put("noConditionFind",noConditionFind);
		paramMap.put("distinct",distinct);
		paramMap.put("viewIndex",viewIndex);
		paramMap.put("fieldList",fieldList);
		paramMap.put("fromDateName",fromDateName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("performFind", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPortalPagePortlet")
	public ResponseEntity<Object> createPortalPagePortlet(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="portalPortletId") String portalPortletId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="columnSeqId", required=false) String columnSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("portalPortletId",portalPortletId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("columnSeqId",columnSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPortalPagePortlet", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPeriodType")
	public ResponseEntity<Object> createPeriodType(HttpSession session, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="periodLength", required=false) Long periodLength, @RequestParam(value="uomId", required=false) String uomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("description",description);
		paramMap.put("periodLength",periodLength);
		paramMap.put("uomId",uomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPeriodType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/preferenceGetSetPermission")
	public ResponseEntity<Object> preferenceGetSetPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="userPrefLoginId", required=false) String userPrefLoginId, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("userPrefLoginId",userPrefLoginId);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("preferenceGetSetPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/movePortletToPortalPage")
	public ResponseEntity<Object> movePortletToPortalPage(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="portletSeqId") String portletSeqId, @RequestParam(value="newPortalPageId") String newPortalPageId, @RequestParam(value="portalPortletId") String portalPortletId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("portletSeqId",portletSeqId);
		paramMap.put("newPortalPageId",newPortalPageId);
		paramMap.put("portalPortletId",portalPortletId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("movePortletToPortalPage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getServerTimestampAsLong")
	public ResponseEntity<Object> getServerTimestampAsLong(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getServerTimestampAsLong", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/performFindItem")
	public ResponseEntity<Object> performFindItem(HttpSession session, @RequestParam(value="inputFields") java.util.Map inputFields, @RequestParam(value="entityName") String entityName, @RequestParam(value="filterByDate", required=false) String filterByDate, @RequestParam(value="filterByDateValue", required=false) Timestamp filterByDateValue, @RequestParam(value="orderBy", required=false) String orderBy) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputFields",inputFields);
		paramMap.put("entityName",entityName);
		paramMap.put("filterByDate",filterByDate);
		paramMap.put("filterByDateValue",filterByDateValue);
		paramMap.put("orderBy",orderBy);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("performFindItem", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/genericBasePermissionCheck")
	public ResponseEntity<Object> genericBasePermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("genericBasePermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUserLogin")
	public ResponseEntity<Object> createUserLogin(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="currentPasswordVerify") String currentPasswordVerify, @RequestParam(value="currentPassword") String currentPassword, @RequestParam(value="passwordHint", required=false) String passwordHint, @RequestParam(value="securityQuestion", required=false) String securityQuestion, @RequestParam(value="securityAnswer", required=false) String securityAnswer, @RequestParam(value="externalAuthId", required=false) String externalAuthId, @RequestParam(value="requirePasswordChange", required=false) String requirePasswordChange, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="enabled", required=false) String enabled) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("currentPasswordVerify",currentPasswordVerify);
		paramMap.put("currentPassword",currentPassword);
		paramMap.put("passwordHint",passwordHint);
		paramMap.put("securityQuestion",securityQuestion);
		paramMap.put("securityAnswer",securityAnswer);
		paramMap.put("externalAuthId",externalAuthId);
		paramMap.put("requirePasswordChange",requirePasswordChange);
		paramMap.put("partyId",partyId);
		paramMap.put("enabled",enabled);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUserLogin", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceDataSource")
	public ResponseEntity<Object> interfaceDataSource(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId, @RequestParam(value="dataSourceTypeId") String dataSourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("dataSourceTypeId",dataSourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceDataSource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGeo")
	public ResponseEntity<Object> updateGeo(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="geoName", required=false) String geoName, @RequestParam(value="geoTypeId", required=false) String geoTypeId, @RequestParam(value="geoCode", required=false) String geoCode, @RequestParam(value="abbreviation", required=false) String abbreviation, @RequestParam(value="geoSecCode", required=false) String geoSecCode, @RequestParam(value="wellKnownText", required=false) String wellKnownText) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("geoName",geoName);
		paramMap.put("geoTypeId",geoTypeId);
		paramMap.put("geoCode",geoCode);
		paramMap.put("abbreviation",abbreviation);
		paramMap.put("geoSecCode",geoSecCode);
		paramMap.put("wellKnownText",wellKnownText);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGeo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePortalPagePortlet")
	public ResponseEntity<Object> deletePortalPagePortlet(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="portletSeqId") String portletSeqId, @RequestParam(value="portalPortletId") String portalPortletId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("portletSeqId",portletSeqId);
		paramMap.put("portalPortletId",portalPortletId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePortalPagePortlet", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGeo")
	public ResponseEntity<Object> createGeo(HttpSession session, @RequestParam(value="geoName") String geoName, @RequestParam(value="geoTypeId") String geoTypeId, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="geoCode", required=false) String geoCode, @RequestParam(value="abbreviation", required=false) String abbreviation, @RequestParam(value="geoSecCode", required=false) String geoSecCode, @RequestParam(value="wellKnownText", required=false) String wellKnownText) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoName",geoName);
		paramMap.put("geoTypeId",geoTypeId);
		paramMap.put("geoId",geoId);
		paramMap.put("geoCode",geoCode);
		paramMap.put("abbreviation",abbreviation);
		paramMap.put("geoSecCode",geoSecCode);
		paramMap.put("wellKnownText",wellKnownText);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGeo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/convertUomCustom")
	public ResponseEntity<Object> convertUomCustom(HttpSession session, @RequestParam(value="originalValue") BigDecimal originalValue, @RequestParam(value="uomId") String uomId, @RequestParam(value="uomConversion") Map uomConversion, @RequestParam(value="uomIdTo") String uomIdTo, @RequestParam(value="conversionParameters", required=false) Map conversionParameters) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("originalValue",originalValue);
		paramMap.put("uomId",uomId);
		paramMap.put("uomConversion",uomConversion);
		paramMap.put("uomIdTo",uomIdTo);
		paramMap.put("conversionParameters",conversionParameters);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("convertUomCustom", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePeriodType")
	public ResponseEntity<Object> updatePeriodType(HttpSession session, @RequestParam(value="periodTypeId") String periodTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="periodLength", required=false) Long periodLength, @RequestParam(value="uomId", required=false) String uomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("description",description);
		paramMap.put("periodLength",periodLength);
		paramMap.put("uomId",uomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePeriodType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUserPrefGroupType")
	public ResponseEntity<Object> deleteUserPrefGroupType(HttpSession session, @RequestParam(value="userPrefGroupTypeId") String userPrefGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteUserPrefGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDataSource")
	public ResponseEntity<Object> createDataSource(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId, @RequestParam(value="dataSourceTypeId") String dataSourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("dataSourceTypeId",dataSourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataSource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEnumeration")
	public ResponseEntity<Object> deleteEnumeration(HttpSession session, @RequestParam(value="enumId") String enumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("enumId",enumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEnumeration", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePortalPagePortlet")
	public ResponseEntity<Object> updatePortalPagePortlet(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="portletSeqId") String portletSeqId, @RequestParam(value="portalPortletId") String portalPortletId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="columnSeqId", required=false) String columnSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("portletSeqId",portletSeqId);
		paramMap.put("portalPortletId",portalPortletId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("columnSeqId",columnSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePortalPagePortlet", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustomTimePeriod")
	public ResponseEntity<Object> deleteCustomTimePeriod(HttpSession session, @RequestParam(value="customTimePeriodId") String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustomTimePeriod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGeoType")
	public ResponseEntity<Object> updateGeoType(HttpSession session, @RequestParam(value="geoTypeId") String geoTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoTypeId",geoTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGeoType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDataSourceType")
	public ResponseEntity<Object> deleteDataSourceType(HttpSession session, @RequestParam(value="dataSourceTypeId") String dataSourceTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceTypeId",dataSourceTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDataSourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGeo")
	public ResponseEntity<Object> deleteGeo(HttpSession session, @RequestParam(value="geoId") String geoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGeo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getStatusItems")
	public ResponseEntity<Object> getStatusItems(HttpSession session, @RequestParam(value="statusTypeIds") List statusTypeIds) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusTypeIds",statusTypeIds);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getStatusItems", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPortletAttribute")
	public ResponseEntity<Object> createPortletAttribute(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="portletSeqId") String portletSeqId, @RequestParam(value="portalPortletId") String portalPortletId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue, @RequestParam(value="attrType", required=false) Long attrType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("portletSeqId",portletSeqId);
		paramMap.put("portalPortletId",portalPortletId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("attrType",attrType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPortletAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCustomTimePeriod")
	public ResponseEntity<Object> createCustomTimePeriod(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="periodTypeId") String periodTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="thruDate") Timestamp thruDate, @RequestParam(value="periodNum", required=false) Long periodNum, @RequestParam(value="isClosed", required=false) String isClosed, @RequestParam(value="periodName", required=false) String periodName, @RequestParam(value="parentPeriodId", required=false) String parentPeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("periodNum",periodNum);
		paramMap.put("isClosed",isClosed);
		paramMap.put("periodName",periodName);
		paramMap.put("parentPeriodId",parentPeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustomTimePeriod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/resetMetric")
	public ResponseEntity<Object> resetMetric(HttpSession session, @RequestParam(value="name") String name) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name",name);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resetMetric", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateStatusValidChange")
	public ResponseEntity<Object> updateStatusValidChange(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="statusIdTo") String statusIdTo, @RequestParam(value="conditionExpression", required=false) String conditionExpression, @RequestParam(value="transitionName", required=false) String transitionName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("statusIdTo",statusIdTo);
		paramMap.put("conditionExpression",conditionExpression);
		paramMap.put("transitionName",transitionName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateStatusValidChange", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDataSource")
	public ResponseEntity<Object> deleteDataSource(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDataSource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateTemporalExpression")
	public ResponseEntity<Object> updateTemporalExpression(HttpSession session, @RequestParam(value="tempExprId") String tempExprId, @RequestParam(value="string1", required=false) String string1, @RequestParam(value="string2", required=false) String string2, @RequestParam(value="description", required=false) String description, @RequestParam(value="integer1", required=false) Long integer1, @RequestParam(value="date2", required=false) Timestamp date2, @RequestParam(value="date1", required=false) Timestamp date1, @RequestParam(value="tempExprTypeId", required=false) String tempExprTypeId, @RequestParam(value="integer2", required=false) Long integer2) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("string1",string1);
		paramMap.put("string2",string2);
		paramMap.put("description",description);
		paramMap.put("integer1",integer1);
		paramMap.put("date2",date2);
		paramMap.put("date1",date1);
		paramMap.put("tempExprTypeId",tempExprTypeId);
		paramMap.put("integer2",integer2);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTemporalExpression", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/adjustDebugLevels")
	public ResponseEntity<Object> adjustDebugLevels(HttpSession session, @RequestParam(value="important", required=false) String important, @RequestParam(value="timing", required=false) String timing, @RequestParam(value="warning", required=false) String warning, @RequestParam(value="error", required=false) String error, @RequestParam(value="fatal", required=false) String fatal, @RequestParam(value="info", required=false) String info, @RequestParam(value="verbose", required=false) String verbose) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("important",important);
		paramMap.put("timing",timing);
		paramMap.put("warning",warning);
		paramMap.put("error",error);
		paramMap.put("fatal",fatal);
		paramMap.put("info",info);
		paramMap.put("verbose",verbose);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("adjustDebugLevels", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePortalPage")
	public ResponseEntity<Object> updatePortalPage(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="originalPortalPageId", required=false) String originalPortalPageId, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="parentPortalPageId", required=false) String parentPortalPageId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="helpContentId", required=false) String helpContentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="ownerUserLoginId", required=false) String ownerUserLoginId, @RequestParam(value="portalPageName", required=false) String portalPageName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("originalPortalPageId",originalPortalPageId);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("parentPortalPageId",parentPortalPageId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("helpContentId",helpContentId);
		paramMap.put("description",description);
		paramMap.put("ownerUserLoginId",ownerUserLoginId);
		paramMap.put("portalPageName",portalPageName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePortalPage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUomType")
	public ResponseEntity<Object> updateUomType(HttpSession session, @RequestParam(value="uomTypeId") String uomTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomTypeId",uomTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUomType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/tempExprPermissionCheck")
	public ResponseEntity<Object> tempExprPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("tempExprPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGeoAssoc")
	public ResponseEntity<Object> deleteGeoAssoc(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="geoIdTo") String geoIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("geoIdTo",geoIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGeoAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceDataSourceType")
	public ResponseEntity<Object> interfaceDataSourceType(HttpSession session, @RequestParam(value="dataSourceTypeId") String dataSourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceTypeId",dataSourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceDataSourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createVisualThemeResource")
	public ResponseEntity<Object> createVisualThemeResource(HttpSession session, @RequestParam(value="resourceTypeEnumId") String resourceTypeEnumId, @RequestParam(value="visualThemeId") String visualThemeId, @RequestParam(value="resourceValue", required=false) Long resourceValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resourceTypeEnumId",resourceTypeEnumId);
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("resourceValue",resourceValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createVisualThemeResource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEnumeration")
	public ResponseEntity<Object> createEnumeration(HttpSession session, @RequestParam(value="enumTypeId") String enumTypeId, @RequestParam(value="description") String description, @RequestParam(value="enumCode", required=false) String enumCode, @RequestParam(value="sequenceId", required=false) String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("enumTypeId",enumTypeId);
		paramMap.put("description",description);
		paramMap.put("enumCode",enumCode);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEnumeration", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEnumeration")
	public ResponseEntity<Object> updateEnumeration(HttpSession session, @RequestParam(value="enumTypeId") String enumTypeId, @RequestParam(value="enumId") String enumId, @RequestParam(value="description") String description, @RequestParam(value="enumCode", required=false) String enumCode, @RequestParam(value="sequenceId", required=false) String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("enumTypeId",enumTypeId);
		paramMap.put("enumId",enumId);
		paramMap.put("description",description);
		paramMap.put("enumCode",enumCode);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEnumeration", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUomType")
	public ResponseEntity<Object> deleteUomType(HttpSession session, @RequestParam(value="uomTypeId") String uomTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomTypeId",uomTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteUomType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/executeFind")
	public ResponseEntity<Object> executeFind(HttpSession session, @RequestParam(value="entityName") String entityName, @RequestParam(value="orderByList", required=false) java.util.List orderByList, @RequestParam(value="maxRows", required=false) Integer maxRows, @RequestParam(value="entityConditionList", required=false) org.apache.ofbiz.entity.condition.EntityConditionList entityConditionList, @RequestParam(value="noConditionFind", required=false) String noConditionFind, @RequestParam(value="distinct", required=false) String distinct, @RequestParam(value="fieldList", required=false) java.util.List fieldList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityName",entityName);
		paramMap.put("orderByList",orderByList);
		paramMap.put("maxRows",maxRows);
		paramMap.put("entityConditionList",entityConditionList);
		paramMap.put("noConditionFind",noConditionFind);
		paramMap.put("distinct",distinct);
		paramMap.put("fieldList",fieldList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("executeFind", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataSource")
	public ResponseEntity<Object> updateDataSource(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId, @RequestParam(value="dataSourceTypeId") String dataSourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("dataSourceTypeId",dataSourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataSource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUom")
	public ResponseEntity<Object> updateUom(HttpSession session, @RequestParam(value="uomId") String uomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="abbreviation", required=false) String abbreviation, @RequestParam(value="uomTypeId", required=false) String uomTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomId",uomId);
		paramMap.put("description",description);
		paramMap.put("abbreviation",abbreviation);
		paramMap.put("uomTypeId",uomTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUom", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/echoService")
	public ResponseEntity<Object> echoService(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("echoService", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createVisualTheme")
	public ResponseEntity<Object> createVisualTheme(HttpSession session, @RequestParam(value="visualThemeId") String visualThemeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="visualThemeSetId", required=false) String visualThemeSetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("description",description);
		paramMap.put("visualThemeSetId",visualThemeSetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createVisualTheme", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkUomConversion")
	public ResponseEntity<Object> checkUomConversion(HttpSession session, @RequestParam(value="uomId") String uomId, @RequestParam(value="uomIdTo") String uomIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomId",uomId);
		paramMap.put("uomIdTo",uomIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkUomConversion", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rssFeedInterface")
	public ResponseEntity<Object> rssFeedInterface(HttpSession session, @RequestParam(value="entryLink") String entryLink, @RequestParam(value="feedType") String feedType, @RequestParam(value="mainLink") String mainLink) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entryLink",entryLink);
		paramMap.put("feedType",feedType);
		paramMap.put("mainLink",mainLink);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("rssFeedInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getUserPreferenceGroup")
	public ResponseEntity<Object> getUserPreferenceGroup(HttpSession session, @RequestParam(value="userPrefGroupTypeId") String userPrefGroupTypeId, @RequestParam(value="userPrefLoginId", required=false) String userPrefLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("userPrefLoginId",userPrefLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getUserPreferenceGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPortalPageColumn")
	public ResponseEntity<Object> addPortalPageColumn(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="columnWidthPixels", required=false) Long columnWidthPixels, @RequestParam(value="columnWidthPercentage", required=false) Long columnWidthPercentage, @RequestParam(value="columnSeqId", required=false) String columnSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("columnWidthPixels",columnWidthPixels);
		paramMap.put("columnWidthPercentage",columnWidthPercentage);
		paramMap.put("columnSeqId",columnSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPortalPageColumn", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createStatusValidChange")
	public ResponseEntity<Object> createStatusValidChange(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="statusIdTo") String statusIdTo, @RequestParam(value="conditionExpression", required=false) String conditionExpression, @RequestParam(value="transitionName", required=false) String transitionName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("statusIdTo",statusIdTo);
		paramMap.put("conditionExpression",conditionExpression);
		paramMap.put("transitionName",transitionName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createStatusValidChange", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePortalPageSeq")
	public ResponseEntity<Object> updatePortalPageSeq(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="mode") String mode, @RequestParam(value="originalPortalPageId", required=false) String originalPortalPageId, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="parentPortalPageId", required=false) String parentPortalPageId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="helpContentId", required=false) String helpContentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="ownerUserLoginId", required=false) String ownerUserLoginId, @RequestParam(value="portalPageName", required=false) String portalPageName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("mode",mode);
		paramMap.put("originalPortalPageId",originalPortalPageId);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("parentPortalPageId",parentPortalPageId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("helpContentId",helpContentId);
		paramMap.put("description",description);
		paramMap.put("ownerUserLoginId",ownerUserLoginId);
		paramMap.put("portalPageName",portalPageName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePortalPageSeq", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/userLogin")
	public ResponseEntity<Object> userLogin(HttpSession session, @RequestParam(value="login.username") String loginusername, @RequestParam(value="login.password") String loginpassword, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="isServiceAuth", required=false) Boolean isServiceAuth) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("login.username",loginusername);
		paramMap.put("login.password",loginpassword);
		paramMap.put("visitId",visitId);
		paramMap.put("isServiceAuth",isServiceAuth);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("userLogin", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getUserPreference")
	public ResponseEntity<Object> getUserPreference(HttpSession session, @RequestParam(value="userPrefTypeId") String userPrefTypeId, @RequestParam(value="userPrefGroupTypeId", required=false) String userPrefGroupTypeId, @RequestParam(value="userPrefLoginId", required=false) String userPrefLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefTypeId",userPrefTypeId);
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("userPrefLoginId",userPrefLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getUserPreference", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/forceGarbageCollection")
	public ResponseEntity<Object> forceGarbageCollection(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("forceGarbageCollection", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGeoAssocType")
	public ResponseEntity<Object> deleteGeoAssocType(HttpSession session, @RequestParam(value="geoAssocTypeId") String geoAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoAssocTypeId",geoAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGeoAssocType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTemporalExpression")
	public ResponseEntity<Object> createTemporalExpression(HttpSession session, @RequestParam(value="string1", required=false) String string1, @RequestParam(value="string2", required=false) String string2, @RequestParam(value="description", required=false) String description, @RequestParam(value="integer1", required=false) Long integer1, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="date2", required=false) Timestamp date2, @RequestParam(value="date1", required=false) Timestamp date1, @RequestParam(value="tempExprTypeId", required=false) String tempExprTypeId, @RequestParam(value="integer2", required=false) Long integer2) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("string1",string1);
		paramMap.put("string2",string2);
		paramMap.put("description",description);
		paramMap.put("integer1",integer1);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("date2",date2);
		paramMap.put("date1",date1);
		paramMap.put("tempExprTypeId",tempExprTypeId);
		paramMap.put("integer2",integer2);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTemporalExpression", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/visualThemePermissionCheck")
	public ResponseEntity<Object> visualThemePermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("visualThemePermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateVisualTheme")
	public ResponseEntity<Object> updateVisualTheme(HttpSession session, @RequestParam(value="visualThemeId") String visualThemeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="visualThemeSetId", required=false) String visualThemeSetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("description",description);
		paramMap.put("visualThemeSetId",visualThemeSetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateVisualTheme", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePortalPage")
	public ResponseEntity<Object> deletePortalPage(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="originalPortalPageId", required=false) String originalPortalPageId, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="parentPortalPageId", required=false) String parentPortalPageId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="helpContentId", required=false) String helpContentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="ownerUserLoginId", required=false) String ownerUserLoginId, @RequestParam(value="portalPageName", required=false) String portalPageName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("originalPortalPageId",originalPortalPageId);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("parentPortalPageId",parentPortalPageId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("helpContentId",helpContentId);
		paramMap.put("description",description);
		paramMap.put("ownerUserLoginId",ownerUserLoginId);
		paramMap.put("portalPageName",portalPageName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePortalPage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/linkGeos")
	public ResponseEntity<Object> linkGeos(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="geoAssocTypeId") String geoAssocTypeId, @RequestParam(value="geoIds", required=false) List geoIds) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("geoAssocTypeId",geoAssocTypeId);
		paramMap.put("geoIds",geoIds);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("linkGeos", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserPrefGroupType")
	public ResponseEntity<Object> updateUserPrefGroupType(HttpSession session, @RequestParam(value="userPrefGroupTypeId") String userPrefGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUserPrefGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createJsLanguageFileMapping")
	public ResponseEntity<Object> createJsLanguageFileMapping(HttpSession session, @RequestParam(value="encoding", required=false) String encoding) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("encoding",encoding);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createJsLanguageFileMapping", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/performFindList")
	public ResponseEntity<Object> performFindList(HttpSession session, @RequestParam(value="inputFields") java.util.Map inputFields, @RequestParam(value="entityName") String entityName, @RequestParam(value="viewSize", required=false) Integer viewSize, @RequestParam(value="filterByDate", required=false) String filterByDate, @RequestParam(value="filterByDateValue", required=false) Timestamp filterByDateValue, @RequestParam(value="orderBy", required=false) String orderBy, @RequestParam(value="noConditionFind", required=false) String noConditionFind, @RequestParam(value="viewIndex", required=false) Integer viewIndex) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputFields",inputFields);
		paramMap.put("entityName",entityName);
		paramMap.put("viewSize",viewSize);
		paramMap.put("filterByDate",filterByDate);
		paramMap.put("filterByDateValue",filterByDateValue);
		paramMap.put("orderBy",orderBy);
		paramMap.put("noConditionFind",noConditionFind);
		paramMap.put("viewIndex",viewIndex);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("performFindList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setUserPreferenceGroup")
	public ResponseEntity<Object> setUserPreferenceGroup(HttpSession session, @RequestParam(value="userPrefGroupTypeId") String userPrefGroupTypeId, @RequestParam(value="userPrefMap") Map userPrefMap, @RequestParam(value="userPrefLoginId", required=false) String userPrefLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("userPrefMap",userPrefMap);
		paramMap.put("userPrefLoginId",userPrefLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setUserPreferenceGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/preferenceCopyPermission")
	public ResponseEntity<Object> preferenceCopyPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("preferenceCopyPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getServerTimestamp")
	public ResponseEntity<Object> getServerTimestamp(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getServerTimestamp", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteKeywordThesaurus")
	public ResponseEntity<Object> deleteKeywordThesaurus(HttpSession session, @RequestParam(value="enteredKeyword") String enteredKeyword, @RequestParam(value="alternateKeyword", required=false) String alternateKeyword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("enteredKeyword",enteredKeyword);
		paramMap.put("alternateKeyword",alternateKeyword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteKeywordThesaurus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkUomConversionDated")
	public ResponseEntity<Object> checkUomConversionDated(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="uomId") String uomId, @RequestParam(value="uomIdTo") String uomIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("uomId",uomId);
		paramMap.put("uomIdTo",uomIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkUomConversionDated", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/commonGenericPermission")
	public ResponseEntity<Object> commonGenericPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("commonGenericPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUomGroup")
	public ResponseEntity<Object> deleteUomGroup(HttpSession session, @RequestParam(value="uomGroupId") String uomGroupId, @RequestParam(value="uomId") String uomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomGroupId",uomGroupId);
		paramMap.put("uomId",uomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteUomGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setUserPreference")
	public ResponseEntity<Object> setUserPreference(HttpSession session, @RequestParam(value="userPrefValue") String userPrefValue, @RequestParam(value="userPrefTypeId") String userPrefTypeId, @RequestParam(value="userPrefGroupTypeId", required=false) String userPrefGroupTypeId, @RequestParam(value="userPrefLoginId", required=false) String userPrefLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefValue",userPrefValue);
		paramMap.put("userPrefTypeId",userPrefTypeId);
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("userPrefLoginId",userPrefLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setUserPreference", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTemporalExpressionAssoc")
	public ResponseEntity<Object> createTemporalExpressionAssoc(HttpSession session, @RequestParam(value="fromTempExprId") String fromTempExprId, @RequestParam(value="toTempExprId") String toTempExprId, @RequestParam(value="exprAssocType", required=false) String exprAssocType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromTempExprId",fromTempExprId);
		paramMap.put("toTempExprId",toTempExprId);
		paramMap.put("exprAssocType",exprAssocType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTemporalExpressionAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ftpPutFile")
	public ResponseEntity<Object> ftpPutFile(HttpSession session, @RequestParam(value="hostname") String hostname, @RequestParam(value="password") String password, @RequestParam(value="remoteFilename") String remoteFilename, @RequestParam(value="localFilename") String localFilename, @RequestParam(value="username") String username, @RequestParam(value="defaultTimeout", required=false) Integer defaultTimeout, @RequestParam(value="binaryTransfer", required=false) Boolean binaryTransfer, @RequestParam(value="siteCommands", required=false) List siteCommands, @RequestParam(value="passiveMode", required=false) Boolean passiveMode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("hostname",hostname);
		paramMap.put("password",password);
		paramMap.put("remoteFilename",remoteFilename);
		paramMap.put("localFilename",localFilename);
		paramMap.put("username",username);
		paramMap.put("defaultTimeout",defaultTimeout);
		paramMap.put("binaryTransfer",binaryTransfer);
		paramMap.put("siteCommands",siteCommands);
		paramMap.put("passiveMode",passiveMode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ftpPutFile", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUomGroup")
	public ResponseEntity<Object> createUomGroup(HttpSession session, @RequestParam(value="uomGroupId") String uomGroupId, @RequestParam(value="uomId") String uomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomGroupId",uomGroupId);
		paramMap.put("uomId",uomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUomGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createNote")
	public ResponseEntity<Object> createNote(HttpSession session, @RequestParam(value="note") String note, @RequestParam(value="noteName", required=false) String noteName, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("note",note);
		paramMap.put("noteName",noteName);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createNote", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePortletSeqDragDrop")
	public ResponseEntity<Object> updatePortletSeqDragDrop(HttpSession session, @RequestParam(value="mode") String mode, @RequestParam(value="o_portalPortletId") String o_portalPortletId, @RequestParam(value="o_portalPageId") String o_portalPageId, @RequestParam(value="o_portletSeqId") String o_portletSeqId, @RequestParam(value="d_portalPortletId", required=false) String d_portalPortletId, @RequestParam(value="d_portalPageId", required=false) String d_portalPageId, @RequestParam(value="d_portletSeqId", required=false) String d_portletSeqId, @RequestParam(value="destinationColumn", required=false) String destinationColumn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mode",mode);
		paramMap.put("o_portalPortletId",o_portalPortletId);
		paramMap.put("o_portalPageId",o_portalPageId);
		paramMap.put("o_portletSeqId",o_portletSeqId);
		paramMap.put("d_portalPortletId",d_portalPortletId);
		paramMap.put("d_portalPageId",d_portalPageId);
		paramMap.put("d_portletSeqId",d_portletSeqId);
		paramMap.put("destinationColumn",destinationColumn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePortletSeqDragDrop", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/returnErrorService")
	public ResponseEntity<Object> returnErrorService(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("returnErrorService", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getFileUploadProgressStatus")
	public ResponseEntity<Object> getFileUploadProgressStatus(HttpSession session, @RequestParam(value="uploadProgressListener", required=false) org.apache.ofbiz.webapp.event.FileUploadProgressListener uploadProgressListener) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uploadProgressListener",uploadProgressListener);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getFileUploadProgressStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGeoAssocType")
	public ResponseEntity<Object> updateGeoAssocType(HttpSession session, @RequestParam(value="geoAssocTypeId") String geoAssocTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoAssocTypeId",geoAssocTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGeoAssocType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getServerTimeZone")
	public ResponseEntity<Object> getServerTimeZone(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getServerTimeZone", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGeoPoint")
	public ResponseEntity<Object> deleteGeoPoint(HttpSession session, @RequestParam(value="geoPointId") String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGeoPoint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/commonGetAllCrudPermissions")
	public ResponseEntity<Object> commonGetAllCrudPermissions(HttpSession session, @RequestParam(value="primaryPermission") String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("commonGetAllCrudPermissions", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataSourceType")
	public ResponseEntity<Object> updateDataSourceType(HttpSession session, @RequestParam(value="dataSourceTypeId") String dataSourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceTypeId",dataSourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataSourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/convertUom")
	public ResponseEntity<Object> convertUom(HttpSession session, @RequestParam(value="originalValue") BigDecimal originalValue, @RequestParam(value="uomId") String uomId, @RequestParam(value="uomIdTo") String uomIdTo, @RequestParam(value="defaultRoundingMode", required=false) String defaultRoundingMode, @RequestParam(value="purposeEnumId", required=false) String purposeEnumId, @RequestParam(value="conversionParameters", required=false) Map conversionParameters, @RequestParam(value="defaultDecimalScale", required=false) Long defaultDecimalScale, @RequestParam(value="asOfDate", required=false) Timestamp asOfDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("originalValue",originalValue);
		paramMap.put("uomId",uomId);
		paramMap.put("uomIdTo",uomIdTo);
		paramMap.put("defaultRoundingMode",defaultRoundingMode);
		paramMap.put("purposeEnumId",purposeEnumId);
		paramMap.put("conversionParameters",conversionParameters);
		paramMap.put("defaultDecimalScale",defaultDecimalScale);
		paramMap.put("asOfDate",asOfDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("convertUom", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGeoType")
	public ResponseEntity<Object> createGeoType(HttpSession session, @RequestParam(value="geoTypeId", required=false) String geoTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoTypeId",geoTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGeoType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGeoPoint")
	public ResponseEntity<Object> createGeoPoint(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId, @RequestParam(value="latitude") String latitude, @RequestParam(value="longitude") String longitude, @RequestParam(value="elevation", required=false) BigDecimal elevation, @RequestParam(value="elevationUomId", required=false) String elevationUomId, @RequestParam(value="geoPointTypeEnumId", required=false) String geoPointTypeEnumId, @RequestParam(value="description", required=false) String description, @RequestParam(value="information", required=false) String information) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("latitude",latitude);
		paramMap.put("longitude",longitude);
		paramMap.put("elevation",elevation);
		paramMap.put("elevationUomId",elevationUomId);
		paramMap.put("geoPointTypeEnumId",geoPointTypeEnumId);
		paramMap.put("description",description);
		paramMap.put("information",information);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGeoPoint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createKeywordThesaurus")
	public ResponseEntity<Object> createKeywordThesaurus(HttpSession session, @RequestParam(value="enteredKeyword") String enteredKeyword, @RequestParam(value="alternateKeyword") String alternateKeyword, @RequestParam(value="relationshipEnumId", required=false) String relationshipEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("enteredKeyword",enteredKeyword);
		paramMap.put("alternateKeyword",alternateKeyword);
		paramMap.put("relationshipEnumId",relationshipEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createKeywordThesaurus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteVisualThemeResource")
	public ResponseEntity<Object> deleteVisualThemeResource(HttpSession session, @RequestParam(value="resourceTypeEnumId") String resourceTypeEnumId, @RequestParam(value="visualThemeId") String visualThemeId, @RequestParam(value="sequenceId") String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resourceTypeEnumId",resourceTypeEnumId);
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteVisualThemeResource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ftpGetFile")
	public ResponseEntity<Object> ftpGetFile(HttpSession session, @RequestParam(value="hostname") String hostname, @RequestParam(value="password") String password, @RequestParam(value="remoteFilename") String remoteFilename, @RequestParam(value="localFilename") String localFilename, @RequestParam(value="username") String username, @RequestParam(value="defaultTimeout", required=false) Integer defaultTimeout, @RequestParam(value="binaryTransfer", required=false) Boolean binaryTransfer, @RequestParam(value="passiveMode", required=false) Boolean passiveMode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("hostname",hostname);
		paramMap.put("password",password);
		paramMap.put("remoteFilename",remoteFilename);
		paramMap.put("localFilename",localFilename);
		paramMap.put("username",username);
		paramMap.put("defaultTimeout",defaultTimeout);
		paramMap.put("binaryTransfer",binaryTransfer);
		paramMap.put("passiveMode",passiveMode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("ftpGetFile", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTemporalExpressionAssoc")
	public ResponseEntity<Object> deleteTemporalExpressionAssoc(HttpSession session, @RequestParam(value="fromTempExprId") String fromTempExprId, @RequestParam(value="toTempExprId") String toTempExprId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromTempExprId",fromTempExprId);
		paramMap.put("toTempExprId",toTempExprId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTemporalExpressionAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeUserPreference")
	public ResponseEntity<Object> removeUserPreference(HttpSession session, @RequestParam(value="userPrefTypeId") String userPrefTypeId, @RequestParam(value="userPrefLoginId", required=false) String userPrefLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefTypeId",userPrefTypeId);
		paramMap.put("userPrefLoginId",userPrefLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeUserPreference", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePortalPageColumn")
	public ResponseEntity<Object> updatePortalPageColumn(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="columnSeqId") String columnSeqId, @RequestParam(value="columnWidthPixels", required=false) Long columnWidthPixels, @RequestParam(value="columnWidthPercentage", required=false) Long columnWidthPercentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("columnSeqId",columnSeqId);
		paramMap.put("columnWidthPixels",columnWidthPixels);
		paramMap.put("columnWidthPercentage",columnWidthPercentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePortalPageColumn", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/copyUserPrefGroup")
	public ResponseEntity<Object> copyUserPrefGroup(HttpSession session, @RequestParam(value="userPrefGroupTypeId") String userPrefGroupTypeId, @RequestParam(value="fromUserLoginId") String fromUserLoginId, @RequestParam(value="userPrefLoginId", required=false) String userPrefLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("fromUserLoginId",fromUserLoginId);
		paramMap.put("userPrefLoginId",userPrefLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyUserPrefGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getStatusValidChangeToDetails")
	public ResponseEntity<Object> getStatusValidChangeToDetails(HttpSession session, @RequestParam(value="statusId") String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getStatusValidChangeToDetails", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getVisualThemeResources")
	public ResponseEntity<Object> getVisualThemeResources(HttpSession session, @RequestParam(value="themeResources", required=false) Map themeResources, @RequestParam(value="visualThemeId", required=false) String visualThemeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("themeResources",themeResources);
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getVisualThemeResources", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUserPrefGroupType")
	public ResponseEntity<Object> createUserPrefGroupType(HttpSession session, @RequestParam(value="userPrefGroupTypeId", required=false) String userPrefGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userPrefGroupTypeId",userPrefGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUserPrefGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPortletAttributes")
	public ResponseEntity<Object> getPortletAttributes(HttpSession session, @RequestParam(value="portalPortletId") String portalPortletId, @RequestParam(value="portalPageId", required=false) String portalPageId, @RequestParam(value="portletSeqId", required=false) String portletSeqId, @RequestParam(value="ownerUserLoginId", required=false) String ownerUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPortletId",portalPortletId);
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("portletSeqId",portletSeqId);
		paramMap.put("ownerUserLoginId",ownerUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPortletAttributes", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getCountryList")
	public ResponseEntity<Object> getCountryList(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getCountryList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePortalPageColumn")
	public ResponseEntity<Object> deletePortalPageColumn(HttpSession session, @RequestParam(value="portalPageId") String portalPageId, @RequestParam(value="columnSeqId") String columnSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("columnSeqId",columnSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePortalPageColumn", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserLoginSecurity")
	public ResponseEntity<Object> updateUserLoginSecurity(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="enabled") String enabled, @RequestParam(value="disabledDateTime", required=false) java.sql.Timestamp disabledDateTime, @RequestParam(value="disabledBy", required=false) String disabledBy, @RequestParam(value="successiveFailedLogins", required=false) Long successiveFailedLogins, @RequestParam(value="userLdapDn", required=false) String userLdapDn, @RequestParam(value="externalAuthId", required=false) String externalAuthId, @RequestParam(value="requirePasswordChange", required=false) String requirePasswordChange) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("enabled",enabled);
		paramMap.put("disabledDateTime",disabledDateTime);
		paramMap.put("disabledBy",disabledBy);
		paramMap.put("successiveFailedLogins",successiveFailedLogins);
		paramMap.put("userLdapDn",userLdapDn);
		paramMap.put("externalAuthId",externalAuthId);
		paramMap.put("requirePasswordChange",requirePasswordChange);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUserLoginSecurity", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/prepareFind")
	public ResponseEntity<Object> prepareFind(HttpSession session, @RequestParam(value="inputFields") java.util.Map inputFields, @RequestParam(value="entityName") String entityName, @RequestParam(value="filterByDate", required=false) String filterByDate, @RequestParam(value="filterByDateValue", required=false) Timestamp filterByDateValue, @RequestParam(value="thruDateName", required=false) String thruDateName, @RequestParam(value="orderBy", required=false) String orderBy, @RequestParam(value="noConditionFind", required=false) String noConditionFind, @RequestParam(value="fromDateName", required=false) String fromDateName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputFields",inputFields);
		paramMap.put("entityName",entityName);
		paramMap.put("filterByDate",filterByDate);
		paramMap.put("filterByDateValue",filterByDateValue);
		paramMap.put("thruDateName",thruDateName);
		paramMap.put("orderBy",orderBy);
		paramMap.put("noConditionFind",noConditionFind);
		paramMap.put("fromDateName",fromDateName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("prepareFind", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustomTimePeriod")
	public ResponseEntity<Object> updateCustomTimePeriod(HttpSession session, @RequestParam(value="customTimePeriodId") String customTimePeriodId, @RequestParam(value="periodNum", required=false) Long periodNum, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="isClosed", required=false) String isClosed, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="periodName", required=false) String periodName, @RequestParam(value="parentPeriodId", required=false) String parentPeriodId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("periodNum",periodNum);
		paramMap.put("fromDate",fromDate);
		paramMap.put("isClosed",isClosed);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("periodName",periodName);
		paramMap.put("parentPeriodId",parentPeriodId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustomTimePeriod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUom")
	public ResponseEntity<Object> createUom(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="abbreviation", required=false) String abbreviation, @RequestParam(value="uomTypeId", required=false) String uomTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("abbreviation",abbreviation);
		paramMap.put("uomTypeId",uomTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUom", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteVisualTheme")
	public ResponseEntity<Object> deleteVisualTheme(HttpSession session, @RequestParam(value="visualThemeId") String visualThemeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteVisualTheme", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteStatusValidChange")
	public ResponseEntity<Object> deleteStatusValidChange(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="statusIdTo") String statusIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("statusIdTo",statusIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteStatusValidChange", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUomConversionDated")
	public ResponseEntity<Object> createUomConversionDated(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="uomId") String uomId, @RequestParam(value="uomIdTo") String uomIdTo, @RequestParam(value="roundingMode", required=false) String roundingMode, @RequestParam(value="purposeEnumId", required=false) String purposeEnumId, @RequestParam(value="conversionFactor", required=false) BigDecimal conversionFactor, @RequestParam(value="decimalScale", required=false) Long decimalScale, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("uomId",uomId);
		paramMap.put("uomIdTo",uomIdTo);
		paramMap.put("roundingMode",roundingMode);
		paramMap.put("purposeEnumId",purposeEnumId);
		paramMap.put("conversionFactor",conversionFactor);
		paramMap.put("decimalScale",decimalScale);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUomConversionDated", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePassword")
	public ResponseEntity<Object> updatePassword(HttpSession session, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="newPasswordVerify", required=false) String newPasswordVerify, @RequestParam(value="passwordHint", required=false) String passwordHint, @RequestParam(value="newPassword", required=false) String newPassword, @RequestParam(value="currentPassword", required=false) String currentPassword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("newPasswordVerify",newPasswordVerify);
		paramMap.put("passwordHint",passwordHint);
		paramMap.put("newPassword",newPassword);
		paramMap.put("currentPassword",currentPassword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePassword", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAssociatedStateList")
	public ResponseEntity<Object> getAssociatedStateList(HttpSession session, @RequestParam(value="countryGeoId") String countryGeoId, @RequestParam(value="listOrderBy", required=false) String listOrderBy) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("listOrderBy",listOrderBy);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAssociatedStateList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUomType")
	public ResponseEntity<Object> createUomType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomTypeId", required=false) String uomTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("uomTypeId",uomTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUomType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPortalPage")
	public ResponseEntity<Object> createPortalPage(HttpSession session, @RequestParam(value="portalPageId", required=false) String portalPageId, @RequestParam(value="originalPortalPageId", required=false) String originalPortalPageId, @RequestParam(value="securityGroupId", required=false) String securityGroupId, @RequestParam(value="parentPortalPageId", required=false) String parentPortalPageId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="helpContentId", required=false) String helpContentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="ownerUserLoginId", required=false) String ownerUserLoginId, @RequestParam(value="portalPageName", required=false) String portalPageName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("portalPageId",portalPageId);
		paramMap.put("originalPortalPageId",originalPortalPageId);
		paramMap.put("securityGroupId",securityGroupId);
		paramMap.put("parentPortalPageId",parentPortalPageId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("helpContentId",helpContentId);
		paramMap.put("description",description);
		paramMap.put("ownerUserLoginId",ownerUserLoginId);
		paramMap.put("portalPageName",portalPageName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPortalPage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePeriodType")
	public ResponseEntity<Object> deletePeriodType(HttpSession session, @RequestParam(value="periodTypeId") String periodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePeriodType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserLoginId")
	public ResponseEntity<Object> updateUserLoginId(HttpSession session, @RequestParam(value="userLoginId") String userLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUserLoginId", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateVisualThemeResource")
	public ResponseEntity<Object> updateVisualThemeResource(HttpSession session, @RequestParam(value="resourceTypeEnumId") String resourceTypeEnumId, @RequestParam(value="visualThemeId") String visualThemeId, @RequestParam(value="sequenceId") String sequenceId, @RequestParam(value="resourceValue", required=false) Long resourceValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resourceTypeEnumId",resourceTypeEnumId);
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("resourceValue",resourceValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateVisualThemeResource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateKeywordThesaurus")
	public ResponseEntity<Object> updateKeywordThesaurus(HttpSession session, @RequestParam(value="enteredKeyword") String enteredKeyword, @RequestParam(value="alternateKeyword") String alternateKeyword, @RequestParam(value="relationshipEnumId", required=false) String relationshipEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("enteredKeyword",enteredKeyword);
		paramMap.put("alternateKeyword",alternateKeyword);
		paramMap.put("relationshipEnumId",relationshipEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateKeywordThesaurus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getRelatedGeos")
	public ResponseEntity<Object> getRelatedGeos(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="geoAssocTypeId") String geoAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("geoAssocTypeId",geoAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRelatedGeos", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUom")
	public ResponseEntity<Object> deleteUom(HttpSession session, @RequestParam(value="uomId") String uomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uomId",uomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteUom", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGeoPoint")
	public ResponseEntity<Object> updateGeoPoint(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId, @RequestParam(value="latitude") String latitude, @RequestParam(value="geoPointId") String geoPointId, @RequestParam(value="longitude") String longitude, @RequestParam(value="elevation", required=false) BigDecimal elevation, @RequestParam(value="elevationUomId", required=false) String elevationUomId, @RequestParam(value="geoPointTypeEnumId", required=false) String geoPointTypeEnumId, @RequestParam(value="description", required=false) String description, @RequestParam(value="information", required=false) String information) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("latitude",latitude);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("longitude",longitude);
		paramMap.put("elevation",elevation);
		paramMap.put("elevationUomId",elevationUomId);
		paramMap.put("geoPointTypeEnumId",geoPointTypeEnumId);
		paramMap.put("description",description);
		paramMap.put("information",information);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGeoPoint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGeoAssocType")
	public ResponseEntity<Object> createGeoAssocType(HttpSession session, @RequestParam(value="geoAssocTypeId", required=false) String geoAssocTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoAssocTypeId",geoAssocTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGeoAssocType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAllMetrics")
	public ResponseEntity<Object> getAllMetrics(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAllMetrics", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/displayXaDebugInfo")
	public ResponseEntity<Object> displayXaDebugInfo(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("displayXaDebugInfo", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(value = (" * "))
	public ResponseEntity<Object> returnErrorPage(HttpSession session) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested service does not exist. JSESSIONID=" + session.getId());
	}

}
