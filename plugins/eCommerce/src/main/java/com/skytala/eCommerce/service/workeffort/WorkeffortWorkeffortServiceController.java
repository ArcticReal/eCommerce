package com.skytala.eCommerce.service.workeffort;

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
@RequestMapping("/service/workeffortWorkeffort")
public class WorkeffortWorkeffortServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createDeliverableType")
	public ResponseEntity<Map<String, Object>> createDeliverableType(HttpSession session, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDeliverableType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDeliverableType")
	public ResponseEntity<Map<String, Object>> updateDeliverableType(HttpSession session, @RequestParam(value="deliverableTypeId") String deliverableTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDeliverableType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortGoodStandardType")
	public ResponseEntity<Map<String, Object>> updateWorkEffortGoodStandardType(HttpSession session, @RequestParam(value="workEffortGoodStdTypeId") String workEffortGoodStdTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortGoodStdTypeId",workEffortGoodStdTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortGoodStandardType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortTypeAttr")
	public ResponseEntity<Map<String, Object>> updateWorkEffortTypeAttr(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortType")
	public ResponseEntity<Map<String, Object>> createWorkEffortType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortAssocType")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortAssocType(HttpSession session, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortContentType")
	public ResponseEntity<Map<String, Object>> createWorkEffortContentType(HttpSession session, @RequestParam(value="workEffortContentTypeId", required=false) String workEffortContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortBilling")
	public ResponseEntity<Map<String, Object>> createWorkEffortBilling(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="percentage", required=false) BigDecimal percentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("percentage",percentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortBilling", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortContentType")
	public ResponseEntity<Map<String, Object>> updateWorkEffortContentType(HttpSession session, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createApplicationSandbox")
	public ResponseEntity<Map<String, Object>> createApplicationSandbox(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="applicationId", required=false) String applicationId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("applicationId",applicationId);
		paramMap.put("partyId",partyId);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createApplicationSandbox", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortAssocAttribute")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortAssocAttribute(HttpSession session, @RequestParam(value="workEffortIdTo") String workEffortIdTo, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortAssocAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortBilling")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortBilling(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortBilling", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDeliverable")
	public ResponseEntity<Map<String, Object>> deleteDeliverable(HttpSession session, @RequestParam(value="deliverableId") String deliverableId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliverableId",deliverableId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDeliverable", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortAssocType")
	public ResponseEntity<Map<String, Object>> updateWorkEffortAssocType(HttpSession session, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortPurposeType")
	public ResponseEntity<Map<String, Object>> createWorkEffortPurposeType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortPurposeType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortTypeAttr(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortAssocAttribute")
	public ResponseEntity<Map<String, Object>> updateWorkEffortAssocAttribute(HttpSession session, @RequestParam(value="workEffortIdTo") String workEffortIdTo, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortAssocAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDeliverableType")
	public ResponseEntity<Map<String, Object>> deleteDeliverableType(HttpSession session, @RequestParam(value="deliverableTypeId") String deliverableTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDeliverableType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortAssocAttribute")
	public ResponseEntity<Map<String, Object>> createWorkEffortAssocAttribute(HttpSession session, @RequestParam(value="workEffortIdTo") String workEffortIdTo, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortAssocAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortPurposeType")
	public ResponseEntity<Map<String, Object>> updateWorkEffortPurposeType(HttpSession session, @RequestParam(value="workEffortPurposeTypeId") String workEffortPurposeTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortPurposeType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortTypeAttr")
	public ResponseEntity<Map<String, Object>> createWorkEffortTypeAttr(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteApplicationSandbox")
	public ResponseEntity<Map<String, Object>> deleteApplicationSandbox(HttpSession session, @RequestParam(value="applicationId") String applicationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applicationId",applicationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteApplicationSandbox", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortAssocType")
	public ResponseEntity<Map<String, Object>> createWorkEffortAssocType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="workEffortAssocTypeId", required=false) String workEffortAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateApplicationSandbox")
	public ResponseEntity<Map<String, Object>> updateApplicationSandbox(HttpSession session, @RequestParam(value="applicationId") String applicationId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applicationId",applicationId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateApplicationSandbox", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDeliverable")
	public ResponseEntity<Map<String, Object>> updateDeliverable(HttpSession session, @RequestParam(value="deliverableId") String deliverableId, @RequestParam(value="deliverableName", required=false) String deliverableName, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliverableId",deliverableId);
		paramMap.put("deliverableName",deliverableName);
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDeliverable", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortAssocTypeAttr")
	public ResponseEntity<Map<String, Object>> createWorkEffortAssocTypeAttr(HttpSession session, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortAssocTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortBilling")
	public ResponseEntity<Map<String, Object>> updateWorkEffortBilling(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="invoiceItemSeqId") String invoiceItemSeqId, @RequestParam(value="percentage", required=false) BigDecimal percentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("percentage",percentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortBilling", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortType")
	public ResponseEntity<Map<String, Object>> updateWorkEffortType(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDeliverable")
	public ResponseEntity<Map<String, Object>> createDeliverable(HttpSession session, @RequestParam(value="deliverableName", required=false) String deliverableName, @RequestParam(value="deliverableTypeId", required=false) String deliverableTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="deliverableId", required=false) String deliverableId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliverableName",deliverableName);
		paramMap.put("deliverableTypeId",deliverableTypeId);
		paramMap.put("description",description);
		paramMap.put("deliverableId",deliverableId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDeliverable", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortPurposeType")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortPurposeType(HttpSession session, @RequestParam(value="workEffortPurposeTypeId") String workEffortPurposeTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortPurposeType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortGoodStandardType")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortGoodStandardType(HttpSession session, @RequestParam(value="workEffortGoodStdTypeId") String workEffortGoodStdTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortGoodStdTypeId",workEffortGoodStdTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortGoodStandardType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortGoodStandardType")
	public ResponseEntity<Map<String, Object>> createWorkEffortGoodStandardType(HttpSession session, @RequestParam(value="workEffortGoodStdTypeId", required=false) String workEffortGoodStdTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortGoodStdTypeId",workEffortGoodStdTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortGoodStandardType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortAssocTypeAttr")
	public ResponseEntity<Map<String, Object>> updateWorkEffortAssocTypeAttr(HttpSession session, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortAssocTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortContentType")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortContentType(HttpSession session, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortType")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortType(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortAssocTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortAssocTypeAttr(HttpSession session, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortAssocTypeAttr", paramMap);
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
