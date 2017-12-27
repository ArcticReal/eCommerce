package com.skytala.eCommerce.service.order;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/orderRequirement")
public class OrderRequirementServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateRequirement")
	public ResponseEntity<Map<String, Object>> updateRequirement(HttpSession session, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="estimatedBudget", required=false) BigDecimal estimatedBudget, @RequestParam(value="description", required=false) String description, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="requirementStartDate", required=false) Timestamp requirementStartDate, @RequestParam(value="requirementTypeId", required=false) String requirementTypeId, @RequestParam(value="useCase", required=false) String useCase, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="deliverableId", required=false) String deliverableId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("reason",reason);
		paramMap.put("facilityId",facilityId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("estimatedBudget",estimatedBudget);
		paramMap.put("description",description);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("requirementStartDate",requirementStartDate);
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("useCase",useCase);
		paramMap.put("statusId",statusId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("deliverableId",deliverableId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRequirement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRequirementAttribute")
	public ResponseEntity<Map<String, Object>> deleteRequirementAttribute(HttpSession session, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRequirementAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRequirementType")
	public ResponseEntity<Map<String, Object>> updateRequirementType(HttpSession session, @RequestParam(value="requirementTypeId") String requirementTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRequirementType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkCreateStockRequirementQoh")
	public ResponseEntity<Map<String, Object>> checkCreateStockRequirementQoh(HttpSession session, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="issuedByUserLoginId", required=false) String issuedByUserLoginId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("issuedByUserLoginId",issuedByUserLoginId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCreateStockRequirementQoh", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirementType")
	public ResponseEntity<Map<String, Object>> createRequirementType(HttpSession session, @RequestParam(value="requirementTypeId", required=false) String requirementTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirementType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRequirementRole")
	public ResponseEntity<Map<String, Object>> updateRequirementRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRequirementRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/autoAssignRequirementToSupplier")
	public ResponseEntity<Map<String, Object>> autoAssignRequirementToSupplier(HttpSession session, @RequestParam(value="requirementId") String requirementId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoAssignRequirementToSupplier", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRequirementTypeAttr")
	public ResponseEntity<Map<String, Object>> updateRequirementTypeAttr(HttpSession session, @RequestParam(value="requirementTypeId") String requirementTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRequirementTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkCreateProductRequirementForFacility")
	public ResponseEntity<Map<String, Object>> checkCreateProductRequirementForFacility(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="defaultRequirementMethodId", required=false) String defaultRequirementMethodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("defaultRequirementMethodId",defaultRequirementMethodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCreateProductRequirementForFacility", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createATPRequirementsForOrder")
	public ResponseEntity<Map<String, Object>> createATPRequirementsForOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createATPRequirementsForOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkReqFulfType")
	public ResponseEntity<Map<String, Object>> deleteWorkReqFulfType(HttpSession session, @RequestParam(value="workReqFulfTypeId") String workReqFulfTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workReqFulfTypeId",workReqFulfTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkReqFulfType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirementStatus")
	public ResponseEntity<Map<String, Object>> createRequirementStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="statusDate", required=false) Timestamp statusDate, @RequestParam(value="changeByUserLoginId", required=false) String changeByUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("changeByUserLoginId",changeByUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirementStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRequirementAttribute")
	public ResponseEntity<Map<String, Object>> updateRequirementAttribute(HttpSession session, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRequirementAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeRequirementRole")
	public ResponseEntity<Map<String, Object>> removeRequirementRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeRequirementRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRequirementsToOrdered")
	public ResponseEntity<Map<String, Object>> updateRequirementsToOrdered(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRequirementsToOrdered", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/requirementInterface")
	public ResponseEntity<Map<String, Object>> requirementInterface(HttpSession session, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="estimatedBudget", required=false) BigDecimal estimatedBudget, @RequestParam(value="description", required=false) String description, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="requirementStartDate", required=false) Timestamp requirementStartDate, @RequestParam(value="requirementTypeId", required=false) String requirementTypeId, @RequestParam(value="useCase", required=false) String useCase, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="deliverableId", required=false) String deliverableId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("reason",reason);
		paramMap.put("facilityId",facilityId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("estimatedBudget",estimatedBudget);
		paramMap.put("description",description);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("requirementStartDate",requirementStartDate);
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("useCase",useCase);
		paramMap.put("statusId",statusId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("deliverableId",deliverableId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("requirementInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRequirement")
	public ResponseEntity<Map<String, Object>> deleteRequirement(HttpSession session, @RequestParam(value="requirementId") String requirementId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRequirement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirementAttribute")
	public ResponseEntity<Map<String, Object>> createRequirementAttribute(HttpSession session, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirementAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateRequirementBudgetAllocation")
	public ResponseEntity<Map<String, Object>> updateRequirementBudgetAllocation(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="amount", required=false) BigDecimal amount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("amount",amount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRequirementBudgetAllocation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDesiredFeature")
	public ResponseEntity<Map<String, Object>> updateDesiredFeature(HttpSession session, @RequestParam(value="desiredFeatureId") String desiredFeatureId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="optionalInd", required=false) String optionalInd, @RequestParam(value="productFeatureId", required=false) String productFeatureId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("desiredFeatureId",desiredFeatureId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("optionalInd",optionalInd);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDesiredFeature", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDesiredFeature")
	public ResponseEntity<Map<String, Object>> createDesiredFeature(HttpSession session, @RequestParam(value="desiredFeatureId") String desiredFeatureId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="optionalInd", required=false) String optionalInd, @RequestParam(value="productFeatureId", required=false) String productFeatureId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("desiredFeatureId",desiredFeatureId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("optionalInd",optionalInd);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDesiredFeature", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRequirementCustRequest")
	public ResponseEntity<Map<String, Object>> deleteRequirementCustRequest(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRequirementCustRequest", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirement")
	public ResponseEntity<Map<String, Object>> createRequirement(HttpSession session, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="estimatedBudget", required=false) BigDecimal estimatedBudget, @RequestParam(value="description", required=false) String description, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="requirementStartDate", required=false) Timestamp requirementStartDate, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId, @RequestParam(value="requirementTypeId", required=false) String requirementTypeId, @RequestParam(value="useCase", required=false) String useCase, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="requirementId", required=false) String requirementId, @RequestParam(value="deliverableId", required=false) String deliverableId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("reason",reason);
		paramMap.put("facilityId",facilityId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("estimatedBudget",estimatedBudget);
		paramMap.put("description",description);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("requirementStartDate",requirementStartDate);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("useCase",useCase);
		paramMap.put("statusId",statusId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("deliverableId",deliverableId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/approveRequirement")
	public ResponseEntity<Map<String, Object>> approveRequirement(HttpSession session, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementId",requirementId);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("approveRequirement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRequirementType")
	public ResponseEntity<Map<String, Object>> deleteRequirementType(HttpSession session, @RequestParam(value="requirementTypeId") String requirementTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRequirementType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDesiredFeature")
	public ResponseEntity<Map<String, Object>> deleteDesiredFeature(HttpSession session, @RequestParam(value="desiredFeatureId") String desiredFeatureId, @RequestParam(value="requirementId") String requirementId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("desiredFeatureId",desiredFeatureId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDesiredFeature", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirementTypeAttr")
	public ResponseEntity<Map<String, Object>> createRequirementTypeAttr(HttpSession session, @RequestParam(value="requirementTypeId") String requirementTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirementTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addRequirementTask")
	public ResponseEntity<Map<String, Object>> addRequirementTask(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="workReqFulfTypeId", required=false) String workReqFulfTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("workReqFulfTypeId",workReqFulfTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addRequirementTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRequirementBudgetAllocation")
	public ResponseEntity<Map<String, Object>> deleteRequirementBudgetAllocation(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="requirementId") String requirementId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRequirementBudgetAllocation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getRequirementsForSupplier")
	public ResponseEntity<Map<String, Object>> getRequirementsForSupplier(HttpSession session, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="unassignedRequirements", required=false) String unassignedRequirements, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="statusIds", required=false) List statusIds, @RequestParam(value="requirementConditions", required=false) org.apache.ofbiz.entity.condition.EntityCondition requirementConditions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("unassignedRequirements",unassignedRequirements);
		paramMap.put("partyId",partyId);
		paramMap.put("statusIds",statusIds);
		paramMap.put("requirementConditions",requirementConditions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRequirementsForSupplier", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkReqFulfType")
	public ResponseEntity<Map<String, Object>> createWorkReqFulfType(HttpSession session, @RequestParam(value="workReqFulfTypeId", required=false) String workReqFulfTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workReqFulfTypeId",workReqFulfTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkReqFulfType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirementBudgetAllocation")
	public ResponseEntity<Map<String, Object>> createRequirementBudgetAllocation(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="amount", required=false) BigDecimal amount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("amount",amount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirementBudgetAllocation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/associatedRequirementWithRequestItem")
	public ResponseEntity<Map<String, Object>> associatedRequirementWithRequestItem(HttpSession session, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("associatedRequirementWithRequestItem", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderRequirementCommitment")
	public ResponseEntity<Map<String, Object>> createOrderRequirementCommitment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderRequirementCommitment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAutoRequirementsForOrder")
	public ResponseEntity<Map<String, Object>> createAutoRequirementsForOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAutoRequirementsForOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkCreateStockRequirementAtp")
	public ResponseEntity<Map<String, Object>> checkCreateStockRequirementAtp(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCreateStockRequirementAtp", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkReqFulfType")
	public ResponseEntity<Map<String, Object>> updateWorkReqFulfType(HttpSession session, @RequestParam(value="workReqFulfTypeId") String workReqFulfTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workReqFulfTypeId",workReqFulfTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkReqFulfType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirementFromItemATP")
	public ResponseEntity<Map<String, Object>> createRequirementFromItemATP(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirementFromItemATP", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequirementRole")
	public ResponseEntity<Map<String, Object>> createRequirementRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRequirementRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRequirementTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteRequirementTypeAttr(HttpSession session, @RequestParam(value="requirementTypeId") String requirementTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRequirementTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkCreateOrderRequirement")
	public ResponseEntity<Map<String, Object>> checkCreateOrderRequirement(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCreateOrderRequirement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTransferFromRequirement")
	public ResponseEntity<Map<String, Object>> createTransferFromRequirement(HttpSession session, @RequestParam(value="fromFacilityId") String fromFacilityId, @RequestParam(value="requirementId") String requirementId, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromFacilityId",fromFacilityId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTransferFromRequirement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}



}
