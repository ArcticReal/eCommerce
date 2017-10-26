package com.skytala.eCommerce.service.manufacturing;

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
@RequestMapping("/service/ManufacturingProductionRunController")
public class ManufacturingProductionRunController{

	@RequestMapping(method = RequestMethod.POST, value = "/issueProductionRunTask")
	public ResponseEntity<Object> issueProductionRunTask(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="failIfItemsAreNotOnHand", required=false) String failIfItemsAreNotOnHand, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="failIfItemsAreNotAvailable", required=false) String failIfItemsAreNotAvailable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("failIfItemsAreNotOnHand",failIfItemsAreNotOnHand);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("failIfItemsAreNotAvailable",failIfItemsAreNotAvailable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueProductionRunTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunPartyAssign")
	public ResponseEntity<Object> createProductionRunPartyAssign(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) java.sql.Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunPartyAssign", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunForMktgPkg")
	public ResponseEntity<Object> createProductionRunForMktgPkg(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunForMktgPkg", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductionRunTask")
	public ResponseEntity<Object> updateProductionRunTask(HttpSession session, @RequestParam(value="productionRunTaskId") String productionRunTaskId, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="addSetupTime", required=false) BigDecimal addSetupTime, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="issueRequiredComponents", required=false) Boolean issueRequiredComponents, @RequestParam(value="componentsLocationMap", required=false) Map componentsLocationMap, @RequestParam(value="addQuantityRejected", required=false) BigDecimal addQuantityRejected, @RequestParam(value="addTaskTime", required=false) BigDecimal addTaskTime, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="addQuantityProduced", required=false) BigDecimal addQuantityProduced, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunTaskId",productionRunTaskId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("addSetupTime",addSetupTime);
		paramMap.put("comments",comments);
		paramMap.put("issueRequiredComponents",issueRequiredComponents);
		paramMap.put("componentsLocationMap",componentsLocationMap);
		paramMap.put("addQuantityRejected",addQuantityRejected);
		paramMap.put("addTaskTime",addTaskTime);
		paramMap.put("partyId",partyId);
		paramMap.put("addQuantityProduced",addQuantityProduced);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductionRunTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/productionRunTaskProduce")
	public ResponseEntity<Object> productionRunTaskProduce(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="isReturned", required=false) String isReturned, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="unitCost", required=false) BigDecimal unitCost, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="locationSeqId", required=false) String locationSeqId, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("isReturned",isReturned);
		paramMap.put("facilityId",facilityId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("lotId",lotId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productionRunTaskProduce", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/productionRunProduce")
	public ResponseEntity<Object> productionRunProduce(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="createLotIfNeeded", required=false) Boolean createLotIfNeeded, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="autoCreateLot", required=false) Boolean autoCreateLot, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("createLotIfNeeded",createLotIfNeeded);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("lotId",lotId);
		paramMap.put("autoCreateLot",autoCreateLot);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productionRunProduce", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/productionRunTaskReturnMaterial")
	public ResponseEntity<Object> productionRunTaskReturnMaterial(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="productId") String productId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("productId",productId);
		paramMap.put("quantity",quantity);
		paramMap.put("lotId",lotId);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productionRunTaskReturnMaterial", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductionRun")
	public ResponseEntity<Object> updateProductionRun(HttpSession session, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="workEffortName", required=false) String workEffortName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("quantity",quantity);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("description",description);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductionRun", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductionRunRoutingTask")
	public ResponseEntity<Object> deleteProductionRunRoutingTask(HttpSession session, @RequestParam(value="routingTaskId") String routingTaskId, @RequestParam(value="productionRunId") String productionRunId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("routingTaskId",routingTaskId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductionRunRoutingTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/quickStartAllProductionRunTasks")
	public ResponseEntity<Object> quickStartAllProductionRunTasks(HttpSession session, @RequestParam(value="productionRunId") String productionRunId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickStartAllProductionRunTasks", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/issueProductionRunTaskComponent")
	public ResponseEntity<Object> issueProductionRunTaskComponent(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="productId") String productId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="secondaryLocationSeqId", required=false) String secondaryLocationSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="failIfItemsAreNotOnHand", required=false) String failIfItemsAreNotOnHand, @RequestParam(value="description", required=false) String description, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="failIfItemsAreNotAvailable", required=false) String failIfItemsAreNotAvailable, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("productId",productId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("secondaryLocationSeqId",secondaryLocationSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("failIfItemsAreNotOnHand",failIfItemsAreNotOnHand);
		paramMap.put("description",description);
		paramMap.put("lotId",lotId);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("failIfItemsAreNotAvailable",failIfItemsAreNotAvailable);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueProductionRunTaskComponent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkDecomposeInventoryItem")
	public ResponseEntity<Object> checkDecomposeInventoryItem(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantityAccepted", required=false) BigDecimal quantityAccepted) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkDecomposeInventoryItem", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/productionRunDeclareAndProduce")
	public ResponseEntity<Object> productionRunDeclareAndProduce(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="componentsLocationMap", required=false) Map componentsLocationMap, @RequestParam(value="createLotIfNeeded", required=false) Boolean createLotIfNeeded, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="autoCreateLot", required=false) Boolean autoCreateLot, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="locationSeqId", required=false) String locationSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantity",quantity);
		paramMap.put("componentsLocationMap",componentsLocationMap);
		paramMap.put("createLotIfNeeded",createLotIfNeeded);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("lotId",lotId);
		paramMap.put("autoCreateLot",autoCreateLot);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productionRunDeclareAndProduce", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRun")
	public ResponseEntity<Object> createProductionRun(HttpSession session, @RequestParam(value="pRQuantity") BigDecimal pRQuantity, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="startDate") java.sql.Timestamp startDate, @RequestParam(value="routingId", required=false) String routingId, @RequestParam(value="description", required=false) String description, @RequestParam(value="workEffortName", required=false) String workEffortName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pRQuantity",pRQuantity);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("startDate",startDate);
		paramMap.put("routingId",routingId);
		paramMap.put("description",description);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRun", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changeProductionRunStatus")
	public ResponseEntity<Object> changeProductionRunStatus(HttpSession session, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("changeProductionRunStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/quickRunAllProductionRunTasks")
	public ResponseEntity<Object> quickRunAllProductionRunTasks(HttpSession session, @RequestParam(value="productionRunId") String productionRunId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickRunAllProductionRunTasks", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/issueInventoryItemToWorkEffort")
	public ResponseEntity<Object> issueInventoryItemToWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="inventoryItem") org.apache.ofbiz.entity.GenericValue inventoryItem, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("inventoryItem",inventoryItem);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueInventoryItemToWorkEffort", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getProductionRunTotResQty")
	public ResponseEntity<Object> getProductionRunTotResQty(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="startDate", required=false) Timestamp startDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("startDate",startDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductionRunTotResQty", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunTaskCosts")
	public ResponseEntity<Object> createProductionRunTaskCosts(HttpSession session, @RequestParam(value="productionRunTaskId") String productionRunTaskId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunTaskId",productionRunTaskId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunTaskCosts", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addProductionRunComponent")
	public ResponseEntity<Object> addProductionRunComponent(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="estimatedQuantity") BigDecimal estimatedQuantity, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="workEffortId", required=false) String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("estimatedQuantity",estimatedQuantity);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProductionRunComponent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getProductionRunCost")
	public ResponseEntity<Object> getProductionRunCost(HttpSession session, @RequestParam(value="workEffortId") String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductionRunCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunsForProductBom")
	public ResponseEntity<Object> createProductionRunsForProductBom(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="startDate") java.sql.Timestamp startDate, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="routingId", required=false) String routingId, @RequestParam(value="description", required=false) String description, @RequestParam(value="workEffortName", required=false) String workEffortName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("startDate",startDate);
		paramMap.put("quantity",quantity);
		paramMap.put("routingId",routingId);
		paramMap.put("description",description);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunsForProductBom", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunsForOrder")
	public ResponseEntity<Object> createProductionRunsForOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("quantity",quantity);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunsForOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/quickRunProductionRunTask")
	public ResponseEntity<Object> quickRunProductionRunTask(HttpSession session, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="taskId") String taskId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("taskId",taskId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickRunProductionRunTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changeProductionRunTaskStatus")
	public ResponseEntity<Object> changeProductionRunTaskStatus(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="issueAllComponents", required=false) Boolean issueAllComponents) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("statusId",statusId);
		paramMap.put("issueAllComponents",issueAllComponents);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("changeProductionRunTaskStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/decomposeInventoryItem")
	public ResponseEntity<Object> decomposeInventoryItem(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("decomposeInventoryItem", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkUpdatePrunRoutingTask")
	public ResponseEntity<Object> checkUpdatePrunRoutingTask(HttpSession session, @RequestParam(value="routingTaskId") String routingTaskId, @RequestParam(value="estimatedSetupMillis") BigDecimal estimatedSetupMillis, @RequestParam(value="estimatedStartDate") Timestamp estimatedStartDate, @RequestParam(value="priority") Long priority, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="estimatedMilliSeconds") BigDecimal estimatedMilliSeconds) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("routingTaskId",routingTaskId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("priority",priority);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkUpdatePrunRoutingTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunFromConfiguration")
	public ResponseEntity<Object> createProductionRunFromConfiguration(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="config", required=false) org.apache.ofbiz.product.config.ProductConfigWrapper config) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("configId",configId);
		paramMap.put("config",config);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunFromConfiguration", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addProductionRunRoutingTask")
	public ResponseEntity<Object> addProductionRunRoutingTask(HttpSession session, @RequestParam(value="routingTaskId") String routingTaskId, @RequestParam(value="priority") Long priority, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="workEffortName", required=false) String workEffortName, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("routingTaskId",routingTaskId);
		paramMap.put("priority",priority);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("description",description);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProductionRunRoutingTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunAssoc")
	public ResponseEntity<Object> createProductionRunAssoc(HttpSession session, @RequestParam(value="productionRunIdTo") String productionRunIdTo, @RequestParam(value="workFlowSequenceTypeId") String workFlowSequenceTypeId, @RequestParam(value="productionRunId") String productionRunId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunIdTo",productionRunIdTo);
		paramMap.put("workFlowSequenceTypeId",workFlowSequenceTypeId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductionRunFromRequirement")
	public ResponseEntity<Object> createProductionRunFromRequirement(HttpSession session, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductionRunFromRequirement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductionRunComponent")
	public ResponseEntity<Object> updateProductionRunComponent(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productionRunId") String productionRunId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="estimatedQuantity", required=false) BigDecimal estimatedQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("estimatedQuantity",estimatedQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductionRunComponent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/quickChangeProductionRunStatus")
	public ResponseEntity<Object> quickChangeProductionRunStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="productionRunId") String productionRunId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickChangeProductionRunStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductionRunComponent")
	public ResponseEntity<Object> deleteProductionRunComponent(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productionRunId") String productionRunId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductionRunComponent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelProductionRun")
	public ResponseEntity<Object> cancelProductionRun(HttpSession session, @RequestParam(value="productionRunId") String productionRunId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productionRunId",productionRunId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelProductionRun", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortCosts")
	public ResponseEntity<Object> getWorkEffortCosts(HttpSession session, @RequestParam(value="workEffortId") String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortCosts", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
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
