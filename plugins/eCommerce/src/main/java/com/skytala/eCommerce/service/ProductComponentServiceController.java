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

@RestController
@RequestMapping("/service/ProductComponentController")
public class ProductComponentServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/getInventoryAvailableByContainer")
	public ResponseEntity<Object> getInventoryAvailableByContainer(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="containerId") String containerId, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("containerId",containerId);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInventoryAvailableByContainer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkProductFacilityRelatedPermission")
	public ResponseEntity<Object> checkProductFacilityRelatedPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkProductFacilityRelatedPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reserveProductInventoryByContainer")
	public ResponseEntity<Object> reserveProductInventoryByContainer(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="orderId") String orderId, @RequestParam(value="requireInventory") String requireInventory, @RequestParam(value="containerId") String containerId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="reservedDatetime", required=false) Timestamp reservedDatetime, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="sequenceId", required=false) Long sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("containerId",containerId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("reservedDatetime",reservedDatetime);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reserveProductInventoryByContainer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPhysicalInventoryAndVariance")
	public ResponseEntity<Object> createPhysicalInventoryAndVariance(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="varianceReasonId", required=false) String varianceReasonId, @RequestParam(value="physicalInventoryDate", required=false) Timestamp physicalInventoryDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="quantityOnHandVar", required=false) BigDecimal quantityOnHandVar, @RequestParam(value="generalComments", required=false) String generalComments, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="availableToPromiseVar", required=false) BigDecimal availableToPromiseVar) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("varianceReasonId",varianceReasonId);
		paramMap.put("physicalInventoryDate",physicalInventoryDate);
		paramMap.put("comments",comments);
		paramMap.put("quantityOnHandVar",quantityOnHandVar);
		paramMap.put("generalComments",generalComments);
		paramMap.put("partyId",partyId);
		paramMap.put("availableToPromiseVar",availableToPromiseVar);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPhysicalInventoryAndVariance", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityLocation")
	public ResponseEntity<Object> deleteFacilityLocation(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="locationSeqId") String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityLocation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContainerType")
	public ResponseEntity<Object> updateContainerType(HttpSession session, @RequestParam(value="containerTypeId") String containerTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("containerTypeId",containerTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContainerType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/prepareInventoryTransfer")
	public ResponseEntity<Object> prepareInventoryTransfer(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="xferQty") BigDecimal xferQty) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("xferQty",xferQty);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("prepareInventoryTransfer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reassignInventoryReservations")
	public ResponseEntity<Object> reassignInventoryReservations(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="priority", required=false) String priority) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("priority",priority);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reassignInventoryReservations", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reserveProductInventory")
	public ResponseEntity<Object> reserveProductInventory(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="orderId") String orderId, @RequestParam(value="requireInventory") String requireInventory, @RequestParam(value="reserveOrderEnumId") String reserveOrderEnumId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="reservedDatetime", required=false) Timestamp reservedDatetime, @RequestParam(value="priority", required=false) String priority, @RequestParam(value="sequenceId", required=false) Long sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("lotId",lotId);
		paramMap.put("reservedDatetime",reservedDatetime);
		paramMap.put("priority",priority);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reserveProductInventory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeFacilityGroupFromGroup")
	public ResponseEntity<Object> removeFacilityGroupFromGroup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="parentFacilityGroupId") String parentFacilityGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("parentFacilityGroupId",parentFacilityGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeFacilityGroupFromGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContactMechFromFacility")
	public ResponseEntity<Object> removeContactMechFromFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContactMechFromFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductFacilityLocation")
	public ResponseEntity<Object> updateProductFacilityLocation(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="locationSeqId") String locationSeqId, @RequestParam(value="moveQuantity", required=false) BigDecimal moveQuantity, @RequestParam(value="minimumStock", required=false) BigDecimal minimumStock) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("moveQuantity",moveQuantity);
		paramMap.put("minimumStock",minimumStock);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductFacilityLocation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityContactMech")
	public ResponseEntity<Object> updateFacilityContactMech(HttpSession session, @RequestParam(value="contactMechTypeId") String contactMechTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="newContactMechId", required=false) String newContactMechId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("extension",extension);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("infoString",infoString);
		paramMap.put("newContactMechId",newContactMechId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setOrderReservationPriority")
	public ResponseEntity<Object> setOrderReservationPriority(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="priority", required=false) String priority) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("priority",priority);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setOrderReservationPriority", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityContactMech")
	public ResponseEntity<Object> createFacilityContactMech(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("extension",extension);
		paramMap.put("infoString",infoString);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemLabelAppl")
	public ResponseEntity<Object> createInventoryItemLabelAppl(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="inventoryItemLabelId") String inventoryItemLabelId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("inventoryItemLabelId",inventoryItemLabelId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemLabelAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityToGroup")
	public ResponseEntity<Object> updateFacilityToGroup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityToGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdateFacilityGeoPoint")
	public ResponseEntity<Object> createUpdateFacilityGeoPoint(HttpSession session, @RequestParam(value="dataSourceId") String dataSourceId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="latitude") String latitude, @RequestParam(value="longitude") String longitude, @RequestParam(value="elevation", required=false) BigDecimal elevation, @RequestParam(value="elevationUomId", required=false) String elevationUomId, @RequestParam(value="geoPointTypeEnumId", required=false) String geoPointTypeEnumId, @RequestParam(value="description", required=false) String description, @RequestParam(value="information", required=false) String information, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("latitude",latitude);
		paramMap.put("longitude",longitude);
		paramMap.put("elevation",elevation);
		paramMap.put("elevationUomId",elevationUomId);
		paramMap.put("geoPointTypeEnumId",geoPointTypeEnumId);
		paramMap.put("description",description);
		paramMap.put("information",information);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdateFacilityGeoPoint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemCheckSetAtpQoh")
	public ResponseEntity<Object> createInventoryItemCheckSetAtpQoh(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantityOnHandTotal", required=false) BigDecimal quantityOnHandTotal, @RequestParam(value="availableToPromiseTotal", required=false) BigDecimal availableToPromiseTotal) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantityOnHandTotal",quantityOnHandTotal);
		paramMap.put("availableToPromiseTotal",availableToPromiseTotal);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemCheckSetAtpQoh", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacility")
	public ResponseEntity<Object> updateFacility(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="defaultInventoryItemTypeId", required=false) String defaultInventoryItemTypeId, @RequestParam(value="parentFacilityId", required=false) String parentFacilityId, @RequestParam(value="description", required=false) String description, @RequestParam(value="defaultWeightUomId", required=false) String defaultWeightUomId, @RequestParam(value="primaryFacilityGroupId", required=false) String primaryFacilityGroupId, @RequestParam(value="openedDate", required=false) Timestamp openedDate, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="facilitySize", required=false) BigDecimal facilitySize, @RequestParam(value="facilitySizeUomId", required=false) String facilitySizeUomId, @RequestParam(value="closedDate", required=false) Timestamp closedDate, @RequestParam(value="facilityTypeId", required=false) String facilityTypeId, @RequestParam(value="defaultDaysToShip", required=false) Long defaultDaysToShip, @RequestParam(value="defaultDimensionUomId", required=false) String defaultDimensionUomId, @RequestParam(value="facilityName", required=false) String facilityName, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="geoPointId", required=false) String geoPointId, @RequestParam(value="oldSquareFootage", required=false) Long oldSquareFootage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("defaultInventoryItemTypeId",defaultInventoryItemTypeId);
		paramMap.put("parentFacilityId",parentFacilityId);
		paramMap.put("description",description);
		paramMap.put("defaultWeightUomId",defaultWeightUomId);
		paramMap.put("primaryFacilityGroupId",primaryFacilityGroupId);
		paramMap.put("openedDate",openedDate);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("facilitySize",facilitySize);
		paramMap.put("facilitySizeUomId",facilitySizeUomId);
		paramMap.put("closedDate",closedDate);
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("defaultDaysToShip",defaultDaysToShip);
		paramMap.put("defaultDimensionUomId",defaultDimensionUomId);
		paramMap.put("facilityName",facilityName);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("oldSquareFootage",oldSquareFootage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityContent")
	public ResponseEntity<Object> deleteFacilityContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteContainer")
	public ResponseEntity<Object> deleteContainer(HttpSession session, @RequestParam(value="containerId") String containerId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("containerId",containerId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteContainer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductFacility")
	public ResponseEntity<Object> deleteProductFacility(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getInventoryAvailableByFacility")
	public ResponseEntity<Object> getInventoryAvailableByFacility(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="useCache", required=false) Boolean useCache, @RequestParam(value="lotId", required=false) String lotId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("useCache",useCache);
		paramMap.put("lotId",lotId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInventoryAvailableByFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getInventoryAvailableByLocation")
	public ResponseEntity<Object> getInventoryAvailableByLocation(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="locationSeqId") String locationSeqId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="useCache", required=false) Boolean useCache) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("statusId",statusId);
		paramMap.put("useCache",useCache);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInventoryAvailableByLocation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/completeInventoryTransfer")
	public ResponseEntity<Object> completeInventoryTransfer(HttpSession session, @RequestParam(value="inventoryTransferId") String inventoryTransferId, @RequestParam(value="receiveDate", required=false) Timestamp receiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryTransferId",inventoryTransferId);
		paramMap.put("receiveDate",receiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("completeInventoryTransfer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemType")
	public ResponseEntity<Object> createInventoryItemType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityGroupType")
	public ResponseEntity<Object> updateFacilityGroupType(HttpSession session, @RequestParam(value="facilityGroupTypeId") String facilityGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityGroupTypeId",facilityGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityGroupType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeFacilityFromGroup")
	public ResponseEntity<Object> removeFacilityFromGroup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="facilityGroupId") String facilityGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeFacilityFromGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityTelecomNumber")
	public ResponseEntity<Object> updateFacilityTelecomNumber(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("extension",extension);
		paramMap.put("areaCode",areaCode);
		paramMap.put("askForName",askForName);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityTelecomNumber", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPhysicalInventory")
	public ResponseEntity<Object> createPhysicalInventory(HttpSession session, @RequestParam(value="physicalInventoryId") String physicalInventoryId, @RequestParam(value="physicalInventoryDate", required=false) Timestamp physicalInventoryDate, @RequestParam(value="generalComments", required=false) String generalComments, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("physicalInventoryDate",physicalInventoryDate);
		paramMap.put("generalComments",generalComments);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPhysicalInventory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityGroup")
	public ResponseEntity<Object> updateFacilityGroup(HttpSession session, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="facilityGroupTypeId", required=false) String facilityGroupTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="facilityGroupName", required=false) String facilityGroupName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("primaryParentGroupId",primaryParentGroupId);
		paramMap.put("facilityGroupTypeId",facilityGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("facilityGroupName",facilityGroupName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityContactMechPurpose")
	public ResponseEntity<Object> createFacilityContactMechPurpose(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityContactMechPurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addPartyToFacility")
	public ResponseEntity<Object> addPartyToFacility(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPartyToFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductInventoryAvailable")
	public ResponseEntity<Object> getProductInventoryAvailable(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="useCache", required=false) Boolean useCache) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("useCache",useCache);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductInventoryAvailable", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityTelecomNumber")
	public ResponseEntity<Object> createFacilityTelecomNumber(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="areaCode", required=false) String areaCode, @RequestParam(value="askForName", required=false) String askForName, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="countryCode", required=false) String countryCode, @RequestParam(value="contactNumber", required=false) String contactNumber, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("extension",extension);
		paramMap.put("areaCode",areaCode);
		paramMap.put("askForName",askForName);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("countryCode",countryCode);
		paramMap.put("contactNumber",contactNumber);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityTelecomNumber", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityAttribute")
	public ResponseEntity<Object> deleteFacilityAttribute(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityType")
	public ResponseEntity<Object> createFacilityType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="facilityTypeId", required=false) String facilityTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItem")
	public ResponseEntity<Object> createInventoryItem(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="isReturned") String isReturned, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="inventoryItemTypeId") String inventoryItemTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="serialNumber", required=false) Long serialNumber, @RequestParam(value="softIdentifier", required=false) Long softIdentifier, @RequestParam(value="binNumber", required=false) String binNumber, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="accountingQuantityTotal", required=false) BigDecimal accountingQuantityTotal, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="oldAvailableToPromise", required=false) BigDecimal oldAvailableToPromise, @RequestParam(value="activationValidThru", required=false) Timestamp activationValidThru, @RequestParam(value="activationNumber", required=false) Long activationNumber, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="unitCost", required=false) BigDecimal unitCost, @RequestParam(value="datetimeManufactured", required=false) Timestamp datetimeManufactured, @RequestParam(value="datetimeReceived", required=false) Timestamp datetimeReceived, @RequestParam(value="expireDate", required=false) Timestamp expireDate, @RequestParam(value="oldQuantityOnHand", required=false) BigDecimal oldQuantityOnHand, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="containerId", required=false) String containerId, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("isReturned",isReturned);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("comments",comments);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("softIdentifier",softIdentifier);
		paramMap.put("binNumber",binNumber);
		paramMap.put("lotId",lotId);
		paramMap.put("uomId",uomId);
		paramMap.put("accountingQuantityTotal",accountingQuantityTotal);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("oldAvailableToPromise",oldAvailableToPromise);
		paramMap.put("activationValidThru",activationValidThru);
		paramMap.put("activationNumber",activationNumber);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("datetimeManufactured",datetimeManufactured);
		paramMap.put("datetimeReceived",datetimeReceived);
		paramMap.put("expireDate",expireDate);
		paramMap.put("oldQuantityOnHand",oldQuantityOnHand);
		paramMap.put("partyId",partyId);
		paramMap.put("containerId",containerId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceInventoryTransfer")
	public ResponseEntity<Object> interfaceInventoryTransfer(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="statusId") String statusId, @RequestParam(value="containerIdTo", required=false) String containerIdTo, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendDate", required=false) Timestamp sendDate, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="receiveDate", required=false) Timestamp receiveDate, @RequestParam(value="facilityIdTo", required=false) String facilityIdTo, @RequestParam(value="containerId", required=false) String containerId, @RequestParam(value="locationSeqIdTo", required=false) String locationSeqIdTo, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("statusId",statusId);
		paramMap.put("containerIdTo",containerIdTo);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("sendDate",sendDate);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("receiveDate",receiveDate);
		paramMap.put("facilityIdTo",facilityIdTo);
		paramMap.put("containerId",containerId);
		paramMap.put("locationSeqIdTo",locationSeqIdTo);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceInventoryTransfer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityAttribute")
	public ResponseEntity<Object> updateFacilityAttribute(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addContactMechToFacility")
	public ResponseEntity<Object> addContactMechToFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addContactMechToFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInventoryItemLabel")
	public ResponseEntity<Object> deleteInventoryItemLabel(HttpSession session, @RequestParam(value="inventoryItemLabelId") String inventoryItemLabelId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemLabelId",inventoryItemLabelId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInventoryItemLabel", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductFacilityLocation")
	public ResponseEntity<Object> createProductFacilityLocation(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="locationSeqId") String locationSeqId, @RequestParam(value="moveQuantity", required=false) BigDecimal moveQuantity, @RequestParam(value="minimumStock", required=false) BigDecimal minimumStock) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("moveQuantity",moveQuantity);
		paramMap.put("minimumStock",minimumStock);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductFacilityLocation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInventoryItemLabelType")
	public ResponseEntity<Object> deleteInventoryItemLabelType(HttpSession session, @RequestParam(value="inventoryItemLabelTypeId") String inventoryItemLabelTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemLabelTypeId",inventoryItemLabelTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInventoryItemLabelType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContainerType")
	public ResponseEntity<Object> createContainerType(HttpSession session, @RequestParam(value="containerTypeId", required=false) String containerTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("containerTypeId",containerTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContainerType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reserveAnInventoryItem")
	public ResponseEntity<Object> reserveAnInventoryItem(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="orderId") String orderId, @RequestParam(value="promisedDatetime") Timestamp promisedDatetime, @RequestParam(value="requireInventory") String requireInventory, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="reservedDatetime", required=false) Timestamp reservedDatetime, @RequestParam(value="priority", required=false) String priority, @RequestParam(value="sequenceId", required=false) Long sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("promisedDatetime",promisedDatetime);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("reservedDatetime",reservedDatetime);
		paramMap.put("priority",priority);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reserveAnInventoryItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductFacilityLocation")
	public ResponseEntity<Object> deleteProductFacilityLocation(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="locationSeqId") String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductFacilityLocation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkProductInventoryDiscontinuation")
	public ResponseEntity<Object> checkProductInventoryDiscontinuation(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkProductInventoryDiscontinuation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityPostalAddress")
	public ResponseEntity<Object> updateFacilityPostalAddress(HttpSession session, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="city", required=false) String city, @RequestParam(value="address1", required=false) String address1, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("city",city);
		paramMap.put("address1",address1);
		paramMap.put("postalCode",postalCode);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
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
			result = dispatcher.runSync("updateFacilityPostalAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueImmediatelyFulfilledOrder")
	public ResponseEntity<Object> issueImmediatelyFulfilledOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueImmediatelyFulfilledOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/facilityGenericPermission")
	public ResponseEntity<Object> facilityGenericPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("facilityGenericPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getMktgPackagesAvailable")
	public ResponseEntity<Object> getMktgPackagesAvailable(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getMktgPackagesAvailable", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/balanceOrderItemsWithNegativeReservations")
	public ResponseEntity<Object> balanceOrderItemsWithNegativeReservations(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("balanceOrderItemsWithNegativeReservations", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityLocation")
	public ResponseEntity<Object> updateFacilityLocation(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="locationSeqId") String locationSeqId, @RequestParam(value="areaId", required=false) String areaId, @RequestParam(value="positionId", required=false) String positionId, @RequestParam(value="levelId", required=false) String levelId, @RequestParam(value="sectionId", required=false) String sectionId, @RequestParam(value="geoPointId", required=false) String geoPointId, @RequestParam(value="locationTypeEnumId", required=false) String locationTypeEnumId, @RequestParam(value="aisleId", required=false) String aisleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("areaId",areaId);
		paramMap.put("positionId",positionId);
		paramMap.put("levelId",levelId);
		paramMap.put("sectionId",sectionId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("locationTypeEnumId",locationTypeEnumId);
		paramMap.put("aisleId",aisleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityLocation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityGroupType")
	public ResponseEntity<Object> createFacilityGroupType(HttpSession session, @RequestParam(value="facilityGroupTypeId", required=false) String facilityGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityGroupTypeId",facilityGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityGroupType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacility")
	public ResponseEntity<Object> createFacility(HttpSession session, @RequestParam(value="ownerPartyId") String ownerPartyId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="facilityTypeId") String facilityTypeId, @RequestParam(value="facilityName") String facilityName, @RequestParam(value="defaultInventoryItemTypeId", required=false) String defaultInventoryItemTypeId, @RequestParam(value="parentFacilityId", required=false) String parentFacilityId, @RequestParam(value="description", required=false) String description, @RequestParam(value="defaultWeightUomId", required=false) String defaultWeightUomId, @RequestParam(value="primaryFacilityGroupId", required=false) String primaryFacilityGroupId, @RequestParam(value="openedDate", required=false) Timestamp openedDate, @RequestParam(value="facilitySize", required=false) BigDecimal facilitySize, @RequestParam(value="facilitySizeUomId", required=false) String facilitySizeUomId, @RequestParam(value="closedDate", required=false) Timestamp closedDate, @RequestParam(value="defaultDaysToShip", required=false) Long defaultDaysToShip, @RequestParam(value="defaultDimensionUomId", required=false) String defaultDimensionUomId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="geoPointId", required=false) String geoPointId, @RequestParam(value="oldSquareFootage", required=false) Long oldSquareFootage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("facilityName",facilityName);
		paramMap.put("defaultInventoryItemTypeId",defaultInventoryItemTypeId);
		paramMap.put("parentFacilityId",parentFacilityId);
		paramMap.put("description",description);
		paramMap.put("defaultWeightUomId",defaultWeightUomId);
		paramMap.put("primaryFacilityGroupId",primaryFacilityGroupId);
		paramMap.put("openedDate",openedDate);
		paramMap.put("facilitySize",facilitySize);
		paramMap.put("facilitySizeUomId",facilitySizeUomId);
		paramMap.put("closedDate",closedDate);
		paramMap.put("defaultDaysToShip",defaultDaysToShip);
		paramMap.put("defaultDimensionUomId",defaultDimensionUomId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("oldSquareFootage",oldSquareFootage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getInventoryAvailableByItem")
	public ResponseEntity<Object> getInventoryAvailableByItem(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="useCache", required=false) Boolean useCache) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("useCache",useCache);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInventoryAvailableByItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setLastInventoryCount")
	public ResponseEntity<Object> setLastInventoryCount(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setLastInventoryCount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryTransfer")
	public ResponseEntity<Object> updateInventoryTransfer(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="inventoryTransferId") String inventoryTransferId, @RequestParam(value="statusId") String statusId, @RequestParam(value="containerIdTo", required=false) String containerIdTo, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendDate", required=false) Timestamp sendDate, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="receiveDate", required=false) Timestamp receiveDate, @RequestParam(value="facilityIdTo", required=false) String facilityIdTo, @RequestParam(value="containerId", required=false) String containerId, @RequestParam(value="locationSeqIdTo", required=false) String locationSeqIdTo, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("inventoryTransferId",inventoryTransferId);
		paramMap.put("statusId",statusId);
		paramMap.put("containerIdTo",containerIdTo);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("sendDate",sendDate);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("receiveDate",receiveDate);
		paramMap.put("facilityIdTo",facilityIdTo);
		paramMap.put("containerId",containerId);
		paramMap.put("locationSeqIdTo",locationSeqIdTo);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryTransfer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemLabelType")
	public ResponseEntity<Object> createInventoryItemLabelType(HttpSession session, @RequestParam(value="inventoryItemLabelTypeId") String inventoryItemLabelTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemLabelTypeId",inventoryItemLabelTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemLabelType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemDetail")
	public ResponseEntity<Object> createInventoryItemDetail(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="description", required=false) String description, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="accountingQuantityDiff", required=false) BigDecimal accountingQuantityDiff, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="availableToPromiseDiff", required=false) BigDecimal availableToPromiseDiff, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="unitCost", required=false) BigDecimal unitCost, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="quantityOnHandDiff", required=false) BigDecimal quantityOnHandDiff) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("orderId",orderId);
		paramMap.put("description",description);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("accountingQuantityDiff",accountingQuantityDiff);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("availableToPromiseDiff",availableToPromiseDiff);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("quantityOnHandDiff",quantityOnHandDiff);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemDetail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteContainerType")
	public ResponseEntity<Object> deleteContainerType(HttpSession session, @RequestParam(value="containerTypeId") String containerTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("containerTypeId",containerTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteContainerType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemType")
	public ResponseEntity<Object> updateInventoryItemType(HttpSession session, @RequestParam(value="inventoryItemTypeId") String inventoryItemTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityPostalAddress")
	public ResponseEntity<Object> createFacilityPostalAddress(HttpSession session, @RequestParam(value="city") String city, @RequestParam(value="address1") String address1, @RequestParam(value="countryGeoId", required=false) String countryGeoId, @RequestParam(value="houseNumberExt", required=false) String houseNumberExt, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="address2", required=false) String address2, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="postalCodeGeoId", required=false) String postalCodeGeoId, @RequestParam(value="houseNumber", required=false) Long houseNumber, @RequestParam(value="postalCodeExt", required=false) String postalCodeExt, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="attnName", required=false) String attnName, @RequestParam(value="directions", required=false) String directions, @RequestParam(value="countyGeoId", required=false) String countyGeoId, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="toName", required=false) String toName, @RequestParam(value="cityGeoId", required=false) String cityGeoId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="municipalityGeoId", required=false) String municipalityGeoId, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId, @RequestParam(value="geoPointId", required=false) String geoPointId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("city",city);
		paramMap.put("address1",address1);
		paramMap.put("countryGeoId",countryGeoId);
		paramMap.put("houseNumberExt",houseNumberExt);
		paramMap.put("extension",extension);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("address2",address2);
		paramMap.put("postalCode",postalCode);
		paramMap.put("postalCodeGeoId",postalCodeGeoId);
		paramMap.put("houseNumber",houseNumber);
		paramMap.put("postalCodeExt",postalCodeExt);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("attnName",attnName);
		paramMap.put("directions",directions);
		paramMap.put("countyGeoId",countyGeoId);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("toName",toName);
		paramMap.put("cityGeoId",cityGeoId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("municipalityGeoId",municipalityGeoId);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityPostalAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityContactMechPurpose")
	public ResponseEntity<Object> deleteFacilityContactMechPurpose(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="contactMechPurposeTypeId") String contactMechPurposeTypeId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityContactMechPurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityContent")
	public ResponseEntity<Object> createFacilityContent(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="contentId") String contentId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("contentId",contentId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInventoryItemLabelAppl")
	public ResponseEntity<Object> deleteInventoryItemLabelAppl(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="inventoryItemLabelTypeId") String inventoryItemLabelTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("inventoryItemLabelTypeId",inventoryItemLabelTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInventoryItemLabelAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemVariance")
	public ResponseEntity<Object> createInventoryItemVariance(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="physicalInventoryId") String physicalInventoryId, @RequestParam(value="varianceReasonId", required=false) String varianceReasonId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="quantityOnHandVar", required=false) BigDecimal quantityOnHandVar, @RequestParam(value="availableToPromiseVar", required=false) BigDecimal availableToPromiseVar) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("varianceReasonId",varianceReasonId);
		paramMap.put("comments",comments);
		paramMap.put("quantityOnHandVar",quantityOnHandVar);
		paramMap.put("availableToPromiseVar",availableToPromiseVar);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemVariance", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrderInventoryReservation")
	public ResponseEntity<Object> cancelOrderInventoryReservation(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelOrderInventoryReservation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemStatus")
	public ResponseEntity<Object> createInventoryItemStatus(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="statusId") String statusId, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="statusDatetime", required=false) Timestamp statusDatetime, @RequestParam(value="changeByUserLoginId", required=false) String changeByUserLoginId, @RequestParam(value="statusEndDatetime", required=false) Timestamp statusEndDatetime) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("statusId",statusId);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("productId",productId);
		paramMap.put("statusDatetime",statusDatetime);
		paramMap.put("changeByUserLoginId",changeByUserLoginId);
		paramMap.put("statusEndDatetime",statusEndDatetime);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/inventoryItemCheckSetDefaultValues")
	public ResponseEntity<Object> inventoryItemCheckSetDefaultValues(HttpSession session, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="inventoryItem", required=false) Map inventoryItem) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("inventoryItem",inventoryItem);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("inventoryItemCheckSetDefaultValues", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductFacility")
	public ResponseEntity<Object> createProductFacility(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="lastInventoryCount", required=false) BigDecimal lastInventoryCount, @RequestParam(value="minimumStock", required=false) BigDecimal minimumStock, @RequestParam(value="reorderQuantity", required=false) BigDecimal reorderQuantity, @RequestParam(value="daysToShip", required=false) Long daysToShip) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("lastInventoryCount",lastInventoryCount);
		paramMap.put("minimumStock",minimumStock);
		paramMap.put("reorderQuantity",reorderQuantity);
		paramMap.put("daysToShip",daysToShip);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemLabelType")
	public ResponseEntity<Object> updateInventoryItemLabelType(HttpSession session, @RequestParam(value="inventoryItemLabelTypeId") String inventoryItemLabelTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemLabelTypeId",inventoryItemLabelTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemLabelType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityCarrierShipment")
	public ResponseEntity<Object> createFacilityCarrierShipment(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityCarrierShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueImmediatelyFulfilledOrderItem")
	public ResponseEntity<Object> issueImmediatelyFulfilledOrderItem(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItem", required=false) org.apache.ofbiz.entity.GenericValue orderItem, @RequestParam(value="orderHeader", required=false) org.apache.ofbiz.entity.GenericValue orderHeader, @RequestParam(value="productStore", required=false) org.apache.ofbiz.entity.GenericValue productStore) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("orderItem",orderItem);
		paramMap.put("orderHeader",orderHeader);
		paramMap.put("productStore",productStore);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueImmediatelyFulfilledOrderItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityContactMech")
	public ResponseEntity<Object> deleteFacilityContactMech(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/changeOwnerUponIssuance")
	public ResponseEntity<Object> changeOwnerUponIssuance(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("changeOwnerUponIssuance", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findStockMovesNeeded")
	public ResponseEntity<Object> findStockMovesNeeded(HttpSession session, @RequestParam(value="facilityId") String facilityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findStockMovesNeeded", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductInventoryAvailableBySupplier")
	public ResponseEntity<Object> getProductInventoryAvailableBySupplier(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="partyId") String partyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="useCache", required=false) Boolean useCache) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("partyId",partyId);
		paramMap.put("statusId",statusId);
		paramMap.put("useCache",useCache);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductInventoryAvailableBySupplier", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityGroupToGroup")
	public ResponseEntity<Object> updateFacilityGroupToGroup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="parentFacilityGroupId") String parentFacilityGroupId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("parentFacilityGroupId",parentFacilityGroupId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityGroupToGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addFacilityToGroup")
	public ResponseEntity<Object> addFacilityToGroup(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addFacilityToGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityLocationGeoPoint")
	public ResponseEntity<Object> createFacilityLocationGeoPoint(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="geoPointId") String geoPointId, @RequestParam(value="locationSeqId") String locationSeqId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityLocationGeoPoint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addPartyToFacilityGroup")
	public ResponseEntity<Object> addPartyToFacilityGroup(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPartyToFacilityGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removePartyFromFacility")
	public ResponseEntity<Object> removePartyFromFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePartyFromFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemLabel")
	public ResponseEntity<Object> createInventoryItemLabel(HttpSession session, @RequestParam(value="inventoryItemLabelId") String inventoryItemLabelId, @RequestParam(value="inventoryItemLabelTypeId") String inventoryItemLabelTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemLabelId",inventoryItemLabelId);
		paramMap.put("inventoryItemLabelTypeId",inventoryItemLabelTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemLabel", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/expireFacilityLocationGeoPoint")
	public ResponseEntity<Object> expireFacilityLocationGeoPoint(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="geoPointId") String geoPointId, @RequestParam(value="locationSeqId") String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireFacilityLocationGeoPoint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityCarrierShipment")
	public ResponseEntity<Object> deleteFacilityCarrierShipment(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityCarrierShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityGroup")
	public ResponseEntity<Object> deleteFacilityGroup(HttpSession session, @RequestParam(value="facilityGroupId") String facilityGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItem")
	public ResponseEntity<Object> updateInventoryItem(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="serialNumber", required=false) Long serialNumber, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="softIdentifier", required=false) Long softIdentifier, @RequestParam(value="binNumber", required=false) String binNumber, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="accountingQuantityTotal", required=false) BigDecimal accountingQuantityTotal, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="oldAvailableToPromise", required=false) BigDecimal oldAvailableToPromise, @RequestParam(value="activationValidThru", required=false) Timestamp activationValidThru, @RequestParam(value="activationNumber", required=false) Long activationNumber, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="unitCost", required=false) BigDecimal unitCost, @RequestParam(value="datetimeManufactured", required=false) Timestamp datetimeManufactured, @RequestParam(value="datetimeReceived", required=false) Timestamp datetimeReceived, @RequestParam(value="expireDate", required=false) Timestamp expireDate, @RequestParam(value="oldQuantityOnHand", required=false) BigDecimal oldQuantityOnHand, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="containerId", required=false) String containerId, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("productId",productId);
		paramMap.put("softIdentifier",softIdentifier);
		paramMap.put("binNumber",binNumber);
		paramMap.put("lotId",lotId);
		paramMap.put("uomId",uomId);
		paramMap.put("accountingQuantityTotal",accountingQuantityTotal);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("oldAvailableToPromise",oldAvailableToPromise);
		paramMap.put("activationValidThru",activationValidThru);
		paramMap.put("activationNumber",activationNumber);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("datetimeManufactured",datetimeManufactured);
		paramMap.put("datetimeReceived",datetimeReceived);
		paramMap.put("expireDate",expireDate);
		paramMap.put("oldQuantityOnHand",oldQuantityOnHand);
		paramMap.put("partyId",partyId);
		paramMap.put("containerId",containerId);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemFromDetail")
	public ResponseEntity<Object> updateInventoryItemFromDetail(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemFromDetail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityEmailAddress")
	public ResponseEntity<Object> createFacilityEmailAddress(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("fromDate",fromDate);
		paramMap.put("extension",extension);
		paramMap.put("infoString",infoString);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("contactMechTypeId",contactMechTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityEmailAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrderItemShipGrpInvRes")
	public ResponseEntity<Object> cancelOrderItemShipGrpInvRes(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelOrderItemShipGrpInvRes", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityTypeAttr")
	public ResponseEntity<Object> updateFacilityTypeAttr(HttpSession session, @RequestParam(value="facilityTypeId") String facilityTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reserveProductInventoryByFacility")
	public ResponseEntity<Object> reserveProductInventoryByFacility(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="orderId") String orderId, @RequestParam(value="requireInventory") String requireInventory, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="reservedDatetime", required=false) Timestamp reservedDatetime, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="priority", required=false) String priority, @RequestParam(value="sequenceId", required=false) Long sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("lotId",lotId);
		paramMap.put("reservedDatetime",reservedDatetime);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("priority",priority);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reserveProductInventoryByFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findStockMovesRecommended")
	public ResponseEntity<Object> findStockMovesRecommended(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="stockMoveHandled", required=false) Map stockMoveHandled) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("stockMoveHandled",stockMoveHandled);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findStockMovesRecommended", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addFacilityGroupToGroup")
	public ResponseEntity<Object> addFacilityGroupToGroup(HttpSession session, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="parentFacilityGroupId") String parentFacilityGroupId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("parentFacilityGroupId",parentFacilityGroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addFacilityGroupToGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSerializedInventoryTotals")
	public ResponseEntity<Object> updateSerializedInventoryTotals(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSerializedInventoryTotals", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemLabel")
	public ResponseEntity<Object> updateInventoryItemLabel(HttpSession session, @RequestParam(value="inventoryItemLabelId") String inventoryItemLabelId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemLabelId",inventoryItemLabelId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemLabel", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrderItemInvResQty")
	public ResponseEntity<Object> cancelOrderItemInvResQty(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelOrderItemInvResQty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContainer")
	public ResponseEntity<Object> createContainer(HttpSession session, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="containerTypeId", required=false) String containerTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="containerId", required=false) String containerId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("containerTypeId",containerTypeId);
		paramMap.put("description",description);
		paramMap.put("containerId",containerId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContainer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductInventoryAndFacilitySummary")
	public ResponseEntity<Object> getProductInventoryAndFacilitySummary(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="checkTime", required=false) Timestamp checkTime, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="minimumStock", required=false) BigDecimal minimumStock) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("checkTime",checkTime);
		paramMap.put("statusId",statusId);
		paramMap.put("minimumStock",minimumStock);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductInventoryAndFacilitySummary", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkInventoryAvailability")
	public ResponseEntity<Object> checkInventoryAvailability(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkInventoryAvailability", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductInventorySummaryForItems")
	public ResponseEntity<Object> getProductInventorySummaryForItems(HttpSession session, @RequestParam(value="orderItems") List orderItems, @RequestParam(value="facilityId", required=false) String facilityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItems",orderItems);
		paramMap.put("facilityId",facilityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductInventorySummaryForItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityGroupType")
	public ResponseEntity<Object> deleteFacilityGroupType(HttpSession session, @RequestParam(value="facilityGroupTypeId") String facilityGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityGroupTypeId",facilityGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityGroupType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryTransfer")
	public ResponseEntity<Object> createInventoryTransfer(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="statusId") String statusId, @RequestParam(value="xferQty") BigDecimal xferQty, @RequestParam(value="containerIdTo", required=false) String containerIdTo, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sendDate", required=false) Timestamp sendDate, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="receiveDate", required=false) Timestamp receiveDate, @RequestParam(value="facilityIdTo", required=false) String facilityIdTo, @RequestParam(value="containerId", required=false) String containerId, @RequestParam(value="locationSeqIdTo", required=false) String locationSeqIdTo, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("statusId",statusId);
		paramMap.put("xferQty",xferQty);
		paramMap.put("containerIdTo",containerIdTo);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("sendDate",sendDate);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("receiveDate",receiveDate);
		paramMap.put("facilityIdTo",facilityIdTo);
		paramMap.put("containerId",containerId);
		paramMap.put("locationSeqIdTo",locationSeqIdTo);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryTransfer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findProductInventorylocations")
	public ResponseEntity<Object> findProductInventorylocations(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findProductInventorylocations", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/balanceInventoryItems")
	public ResponseEntity<Object> balanceInventoryItems(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="priorityOrderId", required=false) String priorityOrderId, @RequestParam(value="priorityOrderItemSeqId", required=false) String priorityOrderItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("priorityOrderId",priorityOrderId);
		paramMap.put("priorityOrderItemSeqId",priorityOrderItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("balanceInventoryItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityType")
	public ResponseEntity<Object> updateFacilityType(HttpSession session, @RequestParam(value="facilityTypeId") String facilityTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reserveOrderItemInventory")
	public ResponseEntity<Object> reserveOrderItemInventory(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="promisedDatetime", required=false) Timestamp promisedDatetime, @RequestParam(value="quantityNotAvailable", required=false) BigDecimal quantityNotAvailable, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="reservedDatetime", required=false) Timestamp reservedDatetime, @RequestParam(value="priority", required=false) Boolean priority, @RequestParam(value="sequenceId", required=false) Long sequenceId, @RequestParam(value="oldPickStartDate", required=false) Timestamp oldPickStartDate, @RequestParam(value="currentPromisedDate", required=false) Timestamp currentPromisedDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("promisedDatetime",promisedDatetime);
		paramMap.put("quantityNotAvailable",quantityNotAvailable);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("reservedDatetime",reservedDatetime);
		paramMap.put("priority",priority);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("oldPickStartDate",oldPickStartDate);
		paramMap.put("currentPromisedDate",currentPromisedDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reserveOrderItemInventory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/countProductInventoryShippedForSales")
	public ResponseEntity<Object> countProductInventoryShippedForSales(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("countProductInventoryShippedForSales", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processPhysicalStockMove")
	public ResponseEntity<Object> processPhysicalStockMove(HttpSession session, @RequestParam(value="quantityMoved") BigDecimal quantityMoved, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="targetLocationSeqId") String targetLocationSeqId, @RequestParam(value="locationSeqId") String locationSeqId, @RequestParam(value="warningMessageList", required=false) List warningMessageList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantityMoved",quantityMoved);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("targetLocationSeqId",targetLocationSeqId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("warningMessageList",warningMessageList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processPhysicalStockMove", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContainer")
	public ResponseEntity<Object> updateContainer(HttpSession session, @RequestParam(value="containerId") String containerId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="containerTypeId", required=false) String containerTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("containerId",containerId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("containerTypeId",containerTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContainer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/countProductInventoryOnHand")
	public ResponseEntity<Object> countProductInventoryOnHand(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="inventoryCountDate") Timestamp inventoryCountDate, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("inventoryCountDate",inventoryCountDate);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("countProductInventoryOnHand", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeFacilityTypeAttr")
	public ResponseEntity<Object> removeFacilityTypeAttr(HttpSession session, @RequestParam(value="facilityTypeId") String facilityTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeFacilityTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductFacility")
	public ResponseEntity<Object> updateProductFacility(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="lastInventoryCount", required=false) BigDecimal lastInventoryCount, @RequestParam(value="minimumStock", required=false) BigDecimal minimumStock, @RequestParam(value="reorderQuantity", required=false) BigDecimal reorderQuantity, @RequestParam(value="daysToShip", required=false) Long daysToShip) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("lastInventoryCount",lastInventoryCount);
		paramMap.put("minimumStock",minimumStock);
		paramMap.put("reorderQuantity",reorderQuantity);
		paramMap.put("daysToShip",daysToShip);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityGroup")
	public ResponseEntity<Object> createFacilityGroup(HttpSession session, @RequestParam(value="facilityGroupTypeId") String facilityGroupTypeId, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="facilityGroupName") String facilityGroupName, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityGroupTypeId",facilityGroupTypeId);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("facilityGroupName",facilityGroupName);
		paramMap.put("primaryParentGroupId",primaryParentGroupId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInventoryItemType")
	public ResponseEntity<Object> deleteInventoryItemType(HttpSession session, @RequestParam(value="inventoryItemTypeId") String inventoryItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInventoryItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelInventoryTransfer")
	public ResponseEntity<Object> cancelInventoryTransfer(HttpSession session, @RequestParam(value="inventoryTransferId") String inventoryTransferId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryTransferId",inventoryTransferId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelInventoryTransfer", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacility")
	public ResponseEntity<Object> deleteFacility(HttpSession session, @RequestParam(value="facilityId") String facilityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removePartyFromFacilityGroup")
	public ResponseEntity<Object> removePartyFromFacilityGroup(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="facilityGroupId") String facilityGroupId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePartyFromFacilityGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityTypeAttr")
	public ResponseEntity<Object> createFacilityTypeAttr(HttpSession session, @RequestParam(value="facilityTypeId") String facilityTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFacilityType")
	public ResponseEntity<Object> deleteFacilityType(HttpSession session, @RequestParam(value="facilityTypeId") String facilityTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityTypeId",facilityTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFacilityType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityLocation")
	public ResponseEntity<Object> createFacilityLocation(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="areaId", required=false) String areaId, @RequestParam(value="positionId", required=false) String positionId, @RequestParam(value="levelId", required=false) String levelId, @RequestParam(value="sectionId", required=false) String sectionId, @RequestParam(value="geoPointId", required=false) String geoPointId, @RequestParam(value="locationTypeEnumId", required=false) String locationTypeEnumId, @RequestParam(value="aisleId", required=false) String aisleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("areaId",areaId);
		paramMap.put("positionId",positionId);
		paramMap.put("levelId",levelId);
		paramMap.put("sectionId",sectionId);
		paramMap.put("geoPointId",geoPointId);
		paramMap.put("locationTypeEnumId",locationTypeEnumId);
		paramMap.put("aisleId",aisleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityLocation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemLabelAppl")
	public ResponseEntity<Object> updateInventoryItemLabelAppl(HttpSession session, @RequestParam(value="inventoryItemLabelTypeId") String inventoryItemLabelTypeId, @RequestParam(value="inventoryItemLabelId", required=false) String inventoryItemLabelId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemLabelTypeId",inventoryItemLabelTypeId);
		paramMap.put("inventoryItemLabelId",inventoryItemLabelId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemLabelAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFacilityAttribute")
	public ResponseEntity<Object> createFacilityAttribute(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFacilityAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFacilityEmailAddress")
	public ResponseEntity<Object> updateFacilityEmailAddress(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("fromDate",fromDate);
		paramMap.put("extension",extension);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFacilityEmailAddress", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductInventoryAvailableFromAssocProducts")
	public ResponseEntity<Object> getProductInventoryAvailableFromAssocProducts(HttpSession session, @RequestParam(value="assocProducts") List assocProducts, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("assocProducts",assocProducts);
		paramMap.put("facilityId",facilityId);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductInventoryAvailableFromAssocProducts", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryTransfersForProduct")
	public ResponseEntity<Object> createInventoryTransfersForProduct(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="facilityIdTo") String facilityIdTo, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="sendDate", required=false) Timestamp sendDate, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="containerId", required=false) String containerId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("facilityIdTo",facilityIdTo);
		paramMap.put("statusId",statusId);
		paramMap.put("sendDate",sendDate);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("containerId",containerId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryTransfersForProduct", paramMap);
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
