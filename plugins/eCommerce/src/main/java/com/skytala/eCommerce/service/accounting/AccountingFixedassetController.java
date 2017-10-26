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
@RequestMapping("/service/accountingFixedasset")
public class AccountingFixedassetController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetGeoPoint")
	public ResponseEntity<Object> updateFixedAssetGeoPoint(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="geoPointId") String geoPointId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetGeoPoint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createMaintsFromTimeInterval")
	public ResponseEntity<Object> createMaintsFromTimeInterval(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMaintsFromTimeInterval", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyFixedAssetAssignment")
	public ResponseEntity<Object> createPartyFixedAssetAssignment(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="allocatedDate", required=false) Timestamp allocatedDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("comments",comments);
		paramMap.put("allocatedDate",allocatedDate);
		paramMap.put("statusId",statusId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyFixedAssetAssignment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetMaint")
	public ResponseEntity<Object> createFixedAssetMaint(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="intervalUomId", required=false) String intervalUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="maintTemplateWorkEffortId", required=false) String maintTemplateWorkEffortId, @RequestParam(value="purchaseOrderId", required=false) String purchaseOrderId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="scheduleWorkEffortId", required=false) String scheduleWorkEffortId, @RequestParam(value="intervalQuantity", required=false) BigDecimal intervalQuantity, @RequestParam(value="productMaintTypeId", required=false) String productMaintTypeId, @RequestParam(value="intervalMeterTypeId", required=false) String intervalMeterTypeId, @RequestParam(value="productMaintSeqId", required=false) String productMaintSeqId, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("intervalUomId",intervalUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("maintTemplateWorkEffortId",maintTemplateWorkEffortId);
		paramMap.put("purchaseOrderId",purchaseOrderId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("scheduleWorkEffortId",scheduleWorkEffortId);
		paramMap.put("intervalQuantity",intervalQuantity);
		paramMap.put("productMaintTypeId",productMaintTypeId);
		paramMap.put("intervalMeterTypeId",intervalMeterTypeId);
		paramMap.put("productMaintSeqId",productMaintSeqId);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetMaint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetTypeAttr")
	public ResponseEntity<Object> createFixedAssetTypeAttr(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetIdent")
	public ResponseEntity<Object> updateFixedAssetIdent(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fixedAssetIdentTypeId") String fixedAssetIdentTypeId, @RequestParam(value="idValue", required=false) String idValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fixedAssetIdentTypeId",fixedAssetIdentTypeId);
		paramMap.put("idValue",idValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetIdent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetIdentType")
	public ResponseEntity<Object> deleteFixedAssetIdentType(HttpSession session, @RequestParam(value="fixedAssetIdentTypeId") String fixedAssetIdentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetIdentTypeId",fixedAssetIdentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetIdentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetIdentType")
	public ResponseEntity<Object> updateFixedAssetIdentType(HttpSession session, @RequestParam(value="fixedAssetIdentTypeId") String fixedAssetIdentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetIdentTypeId",fixedAssetIdentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetIdentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAccommodationMap")
	public ResponseEntity<Object> updateAccommodationMap(HttpSession session, @RequestParam(value="accommodationMapId") String accommodationMapId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="numberOfSpaces", required=false) Long numberOfSpaces, @RequestParam(value="accommodationClassId", required=false) String accommodationClassId, @RequestParam(value="accommodationMapTypeId", required=false) String accommodationMapTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("numberOfSpaces",numberOfSpaces);
		paramMap.put("accommodationClassId",accommodationClassId);
		paramMap.put("accommodationMapTypeId",accommodationMapTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAccommodationMap", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAsset")
	public ResponseEntity<Object> createFixedAsset(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="dateNextService", required=false) Timestamp dateNextService, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dateAcquired", required=false) Timestamp dateAcquired, @RequestParam(value="purchaseCost", required=false) BigDecimal purchaseCost, @RequestParam(value="purchaseCostUomId", required=false) String purchaseCostUomId, @RequestParam(value="productionCapacity", required=false) BigDecimal productionCapacity, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="classEnumId", required=false) String classEnumId, @RequestParam(value="fixedAssetName", required=false) String fixedAssetName, @RequestParam(value="acquireOrderId", required=false) String acquireOrderId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="locatedAtLocationSeqId", required=false) String locatedAtLocationSeqId, @RequestParam(value="actualEndOfLife", required=false) Timestamp actualEndOfLife, @RequestParam(value="dateLastServiced", required=false) Timestamp dateLastServiced, @RequestParam(value="acquireOrderItemSeqId", required=false) String acquireOrderItemSeqId, @RequestParam(value="calendarId", required=false) String calendarId, @RequestParam(value="salvageValue", required=false) BigDecimal salvageValue, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="parentFixedAssetId", required=false) String parentFixedAssetId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="expectedEndOfLife", required=false) Timestamp expectedEndOfLife, @RequestParam(value="locatedAtFacilityId", required=false) String locatedAtFacilityId, @RequestParam(value="instanceOfProductId", required=false) String instanceOfProductId, @RequestParam(value="depreciation", required=false) BigDecimal depreciation) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("dateNextService",dateNextService);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dateAcquired",dateAcquired);
		paramMap.put("purchaseCost",purchaseCost);
		paramMap.put("purchaseCostUomId",purchaseCostUomId);
		paramMap.put("productionCapacity",productionCapacity);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("classEnumId",classEnumId);
		paramMap.put("fixedAssetName",fixedAssetName);
		paramMap.put("acquireOrderId",acquireOrderId);
		paramMap.put("uomId",uomId);
		paramMap.put("locatedAtLocationSeqId",locatedAtLocationSeqId);
		paramMap.put("actualEndOfLife",actualEndOfLife);
		paramMap.put("dateLastServiced",dateLastServiced);
		paramMap.put("acquireOrderItemSeqId",acquireOrderItemSeqId);
		paramMap.put("calendarId",calendarId);
		paramMap.put("salvageValue",salvageValue);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("parentFixedAssetId",parentFixedAssetId);
		paramMap.put("partyId",partyId);
		paramMap.put("expectedEndOfLife",expectedEndOfLife);
		paramMap.put("locatedAtFacilityId",locatedAtFacilityId);
		paramMap.put("instanceOfProductId",instanceOfProductId);
		paramMap.put("depreciation",depreciation);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAsset", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAccommodationClass")
	public ResponseEntity<Object> createAccommodationClass(HttpSession session, @RequestParam(value="parentClassId", required=false) String parentClassId, @RequestParam(value="description", required=false) String description, @RequestParam(value="accommodationClassId", required=false) String accommodationClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentClassId",parentClassId);
		paramMap.put("description",description);
		paramMap.put("accommodationClassId",accommodationClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAccommodationClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetTypeGlAccount")
	public ResponseEntity<Object> createFixedAssetTypeGlAccount(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="depGlAccountId", required=false) String depGlAccountId, @RequestParam(value="fixedAssetTypeId", required=false) String fixedAssetTypeId, @RequestParam(value="profitGlAccountId", required=false) String profitGlAccountId, @RequestParam(value="lossGlAccountId", required=false) String lossGlAccountId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="accDepGlAccountId", required=false) String accDepGlAccountId, @RequestParam(value="assetGlAccountId", required=false) String assetGlAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("depGlAccountId",depGlAccountId);
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("profitGlAccountId",profitGlAccountId);
		paramMap.put("lossGlAccountId",lossGlAccountId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("accDepGlAccountId",accDepGlAccountId);
		paramMap.put("assetGlAccountId",assetGlAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetTypeGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAccommodationMap")
	public ResponseEntity<Object> deleteAccommodationMap(HttpSession session, @RequestParam(value="accommodationMapId") String accommodationMapId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAccommodationMap", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetMeter")
	public ResponseEntity<Object> updateFixedAssetMeter(HttpSession session, @RequestParam(value="productMeterTypeId") String productMeterTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="readingDate") Timestamp readingDate, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="readingReasonEnumId", required=false) String readingReasonEnumId, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="meterValue", required=false) BigDecimal meterValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("readingDate",readingDate);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("readingReasonEnumId",readingReasonEnumId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("meterValue",meterValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetMeter", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetTypeGlAccount")
	public ResponseEntity<Object> updateFixedAssetTypeGlAccount(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetTypeGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addFixedAssetProduct")
	public ResponseEntity<Object> addFixedAssetProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fixedAssetProductTypeId") String fixedAssetProductTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fixedAssetProductTypeId",fixedAssetProductTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("comments",comments);
		paramMap.put("quantity",quantity);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addFixedAssetProduct", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeFixedAssetIdent")
	public ResponseEntity<Object> removeFixedAssetIdent(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fixedAssetIdentTypeId") String fixedAssetIdentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fixedAssetIdentTypeId",fixedAssetIdentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeFixedAssetIdent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetMaintOrder")
	public ResponseEntity<Object> deleteFixedAssetMaintOrder(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="maintHistSeqId") String maintHistSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetMaintOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetAttribute")
	public ResponseEntity<Object> deleteFixedAssetAttribute(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAccommodationClass")
	public ResponseEntity<Object> deleteAccommodationClass(HttpSession session, @RequestParam(value="accommodationClassId") String accommodationClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationClassId",accommodationClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAccommodationClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAsset")
	public ResponseEntity<Object> updateFixedAsset(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="dateNextService", required=false) Timestamp dateNextService, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dateAcquired", required=false) Timestamp dateAcquired, @RequestParam(value="purchaseCost", required=false) BigDecimal purchaseCost, @RequestParam(value="purchaseCostUomId", required=false) String purchaseCostUomId, @RequestParam(value="productionCapacity", required=false) BigDecimal productionCapacity, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="classEnumId", required=false) String classEnumId, @RequestParam(value="fixedAssetName", required=false) String fixedAssetName, @RequestParam(value="acquireOrderId", required=false) String acquireOrderId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="locatedAtLocationSeqId", required=false) String locatedAtLocationSeqId, @RequestParam(value="actualEndOfLife", required=false) Timestamp actualEndOfLife, @RequestParam(value="dateLastServiced", required=false) Timestamp dateLastServiced, @RequestParam(value="acquireOrderItemSeqId", required=false) String acquireOrderItemSeqId, @RequestParam(value="calendarId", required=false) String calendarId, @RequestParam(value="salvageValue", required=false) BigDecimal salvageValue, @RequestParam(value="parentFixedAssetId", required=false) String parentFixedAssetId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="expectedEndOfLife", required=false) Timestamp expectedEndOfLife, @RequestParam(value="locatedAtFacilityId", required=false) String locatedAtFacilityId, @RequestParam(value="instanceOfProductId", required=false) String instanceOfProductId, @RequestParam(value="depreciation", required=false) BigDecimal depreciation) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("dateNextService",dateNextService);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dateAcquired",dateAcquired);
		paramMap.put("purchaseCost",purchaseCost);
		paramMap.put("purchaseCostUomId",purchaseCostUomId);
		paramMap.put("productionCapacity",productionCapacity);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("classEnumId",classEnumId);
		paramMap.put("fixedAssetName",fixedAssetName);
		paramMap.put("acquireOrderId",acquireOrderId);
		paramMap.put("uomId",uomId);
		paramMap.put("locatedAtLocationSeqId",locatedAtLocationSeqId);
		paramMap.put("actualEndOfLife",actualEndOfLife);
		paramMap.put("dateLastServiced",dateLastServiced);
		paramMap.put("acquireOrderItemSeqId",acquireOrderItemSeqId);
		paramMap.put("calendarId",calendarId);
		paramMap.put("salvageValue",salvageValue);
		paramMap.put("parentFixedAssetId",parentFixedAssetId);
		paramMap.put("partyId",partyId);
		paramMap.put("expectedEndOfLife",expectedEndOfLife);
		paramMap.put("locatedAtFacilityId",locatedAtFacilityId);
		paramMap.put("instanceOfProductId",instanceOfProductId);
		paramMap.put("depreciation",depreciation);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAsset", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetType")
	public ResponseEntity<Object> createFixedAssetType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="fixedAssetTypeId", required=false) String fixedAssetTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetRegistration")
	public ResponseEntity<Object> deleteFixedAssetRegistration(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetId") String fixedAssetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetRegistration", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetMaint")
	public ResponseEntity<Object> deleteFixedAssetMaint(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="maintHistSeqId") String maintHistSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetMaint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkUpdateFixedAssetDepreciation")
	public ResponseEntity<Object> checkUpdateFixedAssetDepreciation(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkUpdateFixedAssetDepreciation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetStdCost")
	public ResponseEntity<Object> updateFixedAssetStdCost(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetStdCostTypeId") String fixedAssetStdCostTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="amountUomId", required=false) String amountUomId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetStdCostTypeId",fixedAssetStdCostTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("amountUomId",amountUomId);
		paramMap.put("amount",amount);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetStdCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyFixedAssetAssignment")
	public ResponseEntity<Object> deletePartyFixedAssetAssignment(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyFixedAssetAssignment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetDepMethod")
	public ResponseEntity<Object> deleteFixedAssetDepMethod(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="depreciationCustomMethodId") String depreciationCustomMethodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("depreciationCustomMethodId",depreciationCustomMethodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetDepMethod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetIdentType")
	public ResponseEntity<Object> createFixedAssetIdentType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="fixedAssetIdentTypeId", required=false) String fixedAssetIdentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("fixedAssetIdentTypeId",fixedAssetIdentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetIdentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetType")
	public ResponseEntity<Object> updateFixedAssetType(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetMeter")
	public ResponseEntity<Object> createFixedAssetMeter(HttpSession session, @RequestParam(value="productMeterTypeId") String productMeterTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="readingDate") Timestamp readingDate, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="readingReasonEnumId", required=false) String readingReasonEnumId, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="meterValue", required=false) BigDecimal meterValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("readingDate",readingDate);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("readingReasonEnumId",readingReasonEnumId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("meterValue",meterValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetMeter", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetTypeAttr")
	public ResponseEntity<Object> deleteFixedAssetTypeAttr(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAccommodationMapType")
	public ResponseEntity<Object> updateAccommodationMapType(HttpSession session, @RequestParam(value="accommodationMapTypeId") String accommodationMapTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationMapTypeId",accommodationMapTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAccommodationMapType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetProductType")
	public ResponseEntity<Object> updateFixedAssetProductType(HttpSession session, @RequestParam(value="fixedAssetProductTypeId") String fixedAssetProductTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetProductTypeId",fixedAssetProductTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetProductType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetStdCostType")
	public ResponseEntity<Object> deleteFixedAssetStdCostType(HttpSession session, @RequestParam(value="fixedAssetStdCostTypeId") String fixedAssetStdCostTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetStdCostTypeId",fixedAssetStdCostTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetStdCostType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetDepMethod")
	public ResponseEntity<Object> createFixedAssetDepMethod(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="depreciationCustomMethodId") String depreciationCustomMethodId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("depreciationCustomMethodId",depreciationCustomMethodId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetDepMethod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetIdent")
	public ResponseEntity<Object> createFixedAssetIdent(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fixedAssetIdentTypeId") String fixedAssetIdentTypeId, @RequestParam(value="idValue", required=false) String idValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fixedAssetIdentTypeId",fixedAssetIdentTypeId);
		paramMap.put("idValue",idValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetIdent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetStdCostType")
	public ResponseEntity<Object> updateFixedAssetStdCostType(HttpSession session, @RequestParam(value="fixedAssetStdCostTypeId") String fixedAssetStdCostTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetStdCostTypeId",fixedAssetStdCostTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetStdCostType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAccommodationMapType")
	public ResponseEntity<Object> createAccommodationMapType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="accommodationMapTypeId", required=false) String accommodationMapTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("accommodationMapTypeId",accommodationMapTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAccommodationMapType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAccommodationMap")
	public ResponseEntity<Object> createAccommodationMap(HttpSession session, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="numberOfSpaces", required=false) Long numberOfSpaces, @RequestParam(value="accommodationClassId", required=false) String accommodationClassId, @RequestParam(value="accommodationMapTypeId", required=false) String accommodationMapTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("numberOfSpaces",numberOfSpaces);
		paramMap.put("accommodationClassId",accommodationClassId);
		paramMap.put("accommodationMapTypeId",accommodationMapTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAccommodationMap", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetMaint")
	public ResponseEntity<Object> updateFixedAssetMaint(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="maintHistSeqId") String maintHistSeqId, @RequestParam(value="intervalUomId", required=false) String intervalUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="purchaseOrderId", required=false) String purchaseOrderId, @RequestParam(value="scheduleWorkEffortId", required=false) String scheduleWorkEffortId, @RequestParam(value="intervalQuantity", required=false) BigDecimal intervalQuantity, @RequestParam(value="productMaintTypeId", required=false) String productMaintTypeId, @RequestParam(value="intervalMeterTypeId", required=false) String intervalMeterTypeId, @RequestParam(value="productMaintSeqId", required=false) String productMaintSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("intervalUomId",intervalUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("purchaseOrderId",purchaseOrderId);
		paramMap.put("scheduleWorkEffortId",scheduleWorkEffortId);
		paramMap.put("intervalQuantity",intervalQuantity);
		paramMap.put("productMaintTypeId",productMaintTypeId);
		paramMap.put("intervalMeterTypeId",intervalMeterTypeId);
		paramMap.put("productMaintSeqId",productMaintSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetMaint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/doubleDecliningBalanceDepreciation")
	public ResponseEntity<Object> doubleDecliningBalanceDepreciation(HttpSession session, @RequestParam(value="purchaseCost") BigDecimal purchaseCost, @RequestParam(value="assetAcquiredYear") Integer assetAcquiredYear, @RequestParam(value="usageYears") Integer usageYears, @RequestParam(value="salvageValue") BigDecimal salvageValue, @RequestParam(value="expEndOfLifeYear") Integer expEndOfLifeYear, @RequestParam(value="fixedAssetId") String fixedAssetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("purchaseCost",purchaseCost);
		paramMap.put("assetAcquiredYear",assetAcquiredYear);
		paramMap.put("usageYears",usageYears);
		paramMap.put("salvageValue",salvageValue);
		paramMap.put("expEndOfLifeYear",expEndOfLifeYear);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("doubleDecliningBalanceDepreciation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetAttribute")
	public ResponseEntity<Object> createFixedAssetAttribute(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAccommodationClass")
	public ResponseEntity<Object> updateAccommodationClass(HttpSession session, @RequestParam(value="accommodationClassId") String accommodationClassId, @RequestParam(value="parentClassId", required=false) String parentClassId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationClassId",accommodationClassId);
		paramMap.put("parentClassId",parentClassId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAccommodationClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetStdCostType")
	public ResponseEntity<Object> createFixedAssetStdCostType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="fixedAssetStdCostTypeId", required=false) String fixedAssetStdCostTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("fixedAssetStdCostTypeId",fixedAssetStdCostTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetStdCostType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetTypeGlAccount")
	public ResponseEntity<Object> deleteFixedAssetTypeGlAccount(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetTypeGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetType")
	public ResponseEntity<Object> deleteFixedAssetType(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetRegistration")
	public ResponseEntity<Object> createFixedAssetRegistration(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="registrationNumber", required=false) String registrationNumber, @RequestParam(value="registrationDate", required=false) Timestamp registrationDate, @RequestParam(value="licenseNumber", required=false) String licenseNumber, @RequestParam(value="govAgencyPartyId", required=false) String govAgencyPartyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("registrationNumber",registrationNumber);
		paramMap.put("registrationDate",registrationDate);
		paramMap.put("licenseNumber",licenseNumber);
		paramMap.put("govAgencyPartyId",govAgencyPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetRegistration", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/fixedAssetDepCalcInterface")
	public ResponseEntity<Object> fixedAssetDepCalcInterface(HttpSession session, @RequestParam(value="purchaseCost") BigDecimal purchaseCost, @RequestParam(value="assetAcquiredYear") Integer assetAcquiredYear, @RequestParam(value="usageYears") Integer usageYears, @RequestParam(value="salvageValue") BigDecimal salvageValue, @RequestParam(value="expEndOfLifeYear") Integer expEndOfLifeYear) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("purchaseCost",purchaseCost);
		paramMap.put("assetAcquiredYear",assetAcquiredYear);
		paramMap.put("usageYears",usageYears);
		paramMap.put("salvageValue",salvageValue);
		paramMap.put("expEndOfLifeYear",expEndOfLifeYear);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("fixedAssetDepCalcInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetProductType")
	public ResponseEntity<Object> createFixedAssetProductType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="fixedAssetProductTypeId", required=false) String fixedAssetProductTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("fixedAssetProductTypeId",fixedAssetProductTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetProductType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetProduct")
	public ResponseEntity<Object> updateFixedAssetProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fixedAssetProductTypeId") String fixedAssetProductTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fixedAssetProductTypeId",fixedAssetProductTypeId);
		paramMap.put("comments",comments);
		paramMap.put("quantity",quantity);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetProduct", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeFixedAssetProduct")
	public ResponseEntity<Object> removeFixedAssetProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fixedAssetProductTypeId") String fixedAssetProductTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fixedAssetProductTypeId",fixedAssetProductTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeFixedAssetProduct", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetRegistration")
	public ResponseEntity<Object> updateFixedAssetRegistration(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="registrationNumber", required=false) String registrationNumber, @RequestParam(value="registrationDate", required=false) Timestamp registrationDate, @RequestParam(value="licenseNumber", required=false) String licenseNumber, @RequestParam(value="govAgencyPartyId", required=false) String govAgencyPartyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("registrationNumber",registrationNumber);
		paramMap.put("registrationDate",registrationDate);
		paramMap.put("licenseNumber",licenseNumber);
		paramMap.put("govAgencyPartyId",govAgencyPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetRegistration", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyFixedAssetAssignment")
	public ResponseEntity<Object> updatePartyFixedAssetAssignment(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="partyId") String partyId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="allocatedDate", required=false) Timestamp allocatedDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("partyId",partyId);
		paramMap.put("comments",comments);
		paramMap.put("allocatedDate",allocatedDate);
		paramMap.put("statusId",statusId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyFixedAssetAssignment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetDepMethod")
	public ResponseEntity<Object> updateFixedAssetDepMethod(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="depreciationCustomMethodId") String depreciationCustomMethodId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("depreciationCustomMethodId",depreciationCustomMethodId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetDepMethod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetGeoPoint")
	public ResponseEntity<Object> createFixedAssetGeoPoint(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="geoPointId") String geoPointId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetGeoPoint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelFixedAssetStdCost")
	public ResponseEntity<Object> cancelFixedAssetStdCost(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetStdCostTypeId") String fixedAssetStdCostTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="amountUomId", required=false) String amountUomId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetStdCostTypeId",fixedAssetStdCostTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("amountUomId",amountUomId);
		paramMap.put("amount",amount);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelFixedAssetStdCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetProductType")
	public ResponseEntity<Object> deleteFixedAssetProductType(HttpSession session, @RequestParam(value="fixedAssetProductTypeId") String fixedAssetProductTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetProductTypeId",fixedAssetProductTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetProductType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetTypeAttr")
	public ResponseEntity<Object> updateFixedAssetTypeAttr(HttpSession session, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/calculateFixedAssetDepreciation")
	public ResponseEntity<Object> calculateFixedAssetDepreciation(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateFixedAssetDepreciation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAccommodationMapType")
	public ResponseEntity<Object> deleteAccommodationMapType(HttpSession session, @RequestParam(value="accommodationMapTypeId") String accommodationMapTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationMapTypeId",accommodationMapTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAccommodationMapType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFixedAssetAttribute")
	public ResponseEntity<Object> updateFixedAssetAttribute(HttpSession session, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFixedAssetAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetMaintOrder")
	public ResponseEntity<Object> createFixedAssetMaintOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="maintHistSeqId") String maintHistSeqId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetMaintOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetStdCost")
	public ResponseEntity<Object> createFixedAssetStdCost(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetStdCostTypeId") String fixedAssetStdCostTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="amountUomId", required=false) String amountUomId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetStdCostTypeId",fixedAssetStdCostTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("amountUomId",amountUomId);
		paramMap.put("amount",amount);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetStdCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetMeter")
	public ResponseEntity<Object> deleteFixedAssetMeter(HttpSession session, @RequestParam(value="productMeterTypeId") String productMeterTypeId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="readingDate") Timestamp readingDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("readingDate",readingDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetMeter", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/straightLineDepreciation")
	public ResponseEntity<Object> straightLineDepreciation(HttpSession session, @RequestParam(value="purchaseCost") BigDecimal purchaseCost, @RequestParam(value="assetAcquiredYear") Integer assetAcquiredYear, @RequestParam(value="usageYears") Integer usageYears, @RequestParam(value="salvageValue") BigDecimal salvageValue, @RequestParam(value="expEndOfLifeYear") Integer expEndOfLifeYear, @RequestParam(value="fixedAssetId") String fixedAssetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("purchaseCost",purchaseCost);
		paramMap.put("assetAcquiredYear",assetAcquiredYear);
		paramMap.put("usageYears",usageYears);
		paramMap.put("salvageValue",salvageValue);
		paramMap.put("expEndOfLifeYear",expEndOfLifeYear);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("straightLineDepreciation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFixedAssetGeoPoint")
	public ResponseEntity<Object> deleteFixedAssetGeoPoint(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="geoPointId") String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFixedAssetGeoPoint", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
