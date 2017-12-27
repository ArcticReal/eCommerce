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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/accountingBudget")
public class AccountingBudgetServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetReview")
	public ResponseEntity<Map<String, Object>> createBudgetReview(HttpSession session, @RequestParam(value="budgetReviewResultTypeId") String budgetReviewResultTypeId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="partyId") String partyId, @RequestParam(value="reviewDate", required=false) Timestamp reviewDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetReviewResultTypeId",budgetReviewResultTypeId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("partyId",partyId);
		paramMap.put("reviewDate",reviewDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetItemTypeAttr")
	public ResponseEntity<Map<String, Object>> createBudgetItemTypeAttr(HttpSession session, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetItemAttribute")
	public ResponseEntity<Map<String, Object>> deleteBudgetItemAttribute(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetScenario")
	public ResponseEntity<Map<String, Object>> updateBudgetScenario(HttpSession session, @RequestParam(value="budgetScenarioId") String budgetScenarioId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetScenario", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetScenarioRule")
	public ResponseEntity<Map<String, Object>> updateBudgetScenarioRule(HttpSession session, @RequestParam(value="budgetScenarioId") String budgetScenarioId, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="amountChange", required=false) BigDecimal amountChange, @RequestParam(value="percentageChange", required=false) BigDecimal percentageChange) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("amountChange",amountChange);
		paramMap.put("percentageChange",percentageChange);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetScenarioRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetScenarioApplication")
	public ResponseEntity<Map<String, Object>> createBudgetScenarioApplication(HttpSession session, @RequestParam(value="budgetScenarioApplicId") String budgetScenarioApplicId, @RequestParam(value="budgetScenarioId") String budgetScenarioId, @RequestParam(value="amountChange", required=false) BigDecimal amountChange, @RequestParam(value="percentageChange", required=false) BigDecimal percentageChange, @RequestParam(value="budgetItemSeqId", required=false) String budgetItemSeqId, @RequestParam(value="budgetId", required=false) String budgetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioApplicId",budgetScenarioApplicId);
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("amountChange",amountChange);
		paramMap.put("percentageChange",percentageChange);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetScenarioApplication", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetRevisionImpact")
	public ResponseEntity<Map<String, Object>> updateBudgetRevisionImpact(HttpSession session, @RequestParam(value="revisionSeqId") String revisionSeqId, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="addDeleteFlag", required=false) String addDeleteFlag, @RequestParam(value="revisionReason", required=false) String revisionReason, @RequestParam(value="revisedAmount", required=false) BigDecimal revisedAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("revisionSeqId",revisionSeqId);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("addDeleteFlag",addDeleteFlag);
		paramMap.put("revisionReason",revisionReason);
		paramMap.put("revisedAmount",revisedAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetRevisionImpact", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetTypeAttr")
	public ResponseEntity<Map<String, Object>> createBudgetTypeAttr(HttpSession session, @RequestParam(value="budgetTypeId") String budgetTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetScenarioRule")
	public ResponseEntity<Map<String, Object>> deleteBudgetScenarioRule(HttpSession session, @RequestParam(value="budgetScenarioId") String budgetScenarioId, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetScenarioRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetScenario")
	public ResponseEntity<Map<String, Object>> createBudgetScenario(HttpSession session, @RequestParam(value="budgetScenarioId", required=false) String budgetScenarioId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetScenario", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeBudgetItem")
	public ResponseEntity<Map<String, Object>> removeBudgetItem(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBudgetItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetRevision")
	public ResponseEntity<Map<String, Object>> createBudgetRevision(HttpSession session, @RequestParam(value="revisionSeqId") String revisionSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="dateRevised", required=false) Timestamp dateRevised) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("revisionSeqId",revisionSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("dateRevised",dateRevised);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetRevision", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetType")
	public ResponseEntity<Map<String, Object>> updateBudgetType(HttpSession session, @RequestParam(value="budgetTypeId") String budgetTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetType")
	public ResponseEntity<Map<String, Object>> deleteBudgetType(HttpSession session, @RequestParam(value="budgetTypeId") String budgetTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudget")
	public ResponseEntity<Map<String, Object>> updateBudget(HttpSession session, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="budgetTypeId", required=false) String budgetTypeId, @RequestParam(value="customTimePeriodId", required=false) String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetId",budgetId);
		paramMap.put("comments",comments);
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudget", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetType")
	public ResponseEntity<Map<String, Object>> createBudgetType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="budgetTypeId", required=false) String budgetTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetRole")
	public ResponseEntity<Map<String, Object>> createBudgetRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteBudgetTypeAttr(HttpSession session, @RequestParam(value="budgetTypeId") String budgetTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeBudgetAttribute")
	public ResponseEntity<Map<String, Object>> removeBudgetAttribute(HttpSession session, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetId",budgetId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBudgetAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetRevisionImpact")
	public ResponseEntity<Map<String, Object>> createBudgetRevisionImpact(HttpSession session, @RequestParam(value="revisionSeqId") String revisionSeqId, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="addDeleteFlag", required=false) String addDeleteFlag, @RequestParam(value="revisionReason", required=false) String revisionReason, @RequestParam(value="revisedAmount", required=false) BigDecimal revisedAmount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("revisionSeqId",revisionSeqId);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("addDeleteFlag",addDeleteFlag);
		paramMap.put("revisionReason",revisionReason);
		paramMap.put("revisedAmount",revisedAmount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetRevisionImpact", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetRevision")
	public ResponseEntity<Map<String, Object>> updateBudgetRevision(HttpSession session, @RequestParam(value="revisionSeqId") String revisionSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="dateRevised", required=false) Timestamp dateRevised) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("revisionSeqId",revisionSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("dateRevised",dateRevised);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetRevision", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetReviewResultType")
	public ResponseEntity<Map<String, Object>> createBudgetReviewResultType(HttpSession session, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="budgetReviewResultTypeId", required=false) String budgetReviewResultTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comments",comments);
		paramMap.put("budgetReviewResultTypeId",budgetReviewResultTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetReviewResultType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetTypeAttr")
	public ResponseEntity<Map<String, Object>> updateBudgetTypeAttr(HttpSession session, @RequestParam(value="budgetTypeId") String budgetTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetStatus")
	public ResponseEntity<Map<String, Object>> createBudgetStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="statusDate", required=false) Timestamp statusDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="changeByUserLoginId", required=false) String changeByUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("comments",comments);
		paramMap.put("changeByUserLoginId",changeByUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetItemType")
	public ResponseEntity<Map<String, Object>> createBudgetItemType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="budgetItemTypeId", required=false) String budgetItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetRevision")
	public ResponseEntity<Map<String, Object>> deleteBudgetRevision(HttpSession session, @RequestParam(value="revisionSeqId") String revisionSeqId, @RequestParam(value="budgetId") String budgetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("revisionSeqId",revisionSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetRevision", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetItemAttribute")
	public ResponseEntity<Map<String, Object>> createBudgetItemAttribute(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetReviewResultType")
	public ResponseEntity<Map<String, Object>> deleteBudgetReviewResultType(HttpSession session, @RequestParam(value="budgetReviewResultTypeId") String budgetReviewResultTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetReviewResultTypeId",budgetReviewResultTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetReviewResultType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetScenarioRule")
	public ResponseEntity<Map<String, Object>> createBudgetScenarioRule(HttpSession session, @RequestParam(value="budgetScenarioId") String budgetScenarioId, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="amountChange", required=false) BigDecimal amountChange, @RequestParam(value="percentageChange", required=false) BigDecimal percentageChange) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("amountChange",amountChange);
		paramMap.put("percentageChange",percentageChange);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetScenarioRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetItemTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteBudgetItemTypeAttr(HttpSession session, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeBudgetItemType")
	public ResponseEntity<Map<String, Object>> removeBudgetItemType(HttpSession session, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBudgetItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetAttribute")
	public ResponseEntity<Map<String, Object>> updateBudgetAttribute(HttpSession session, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetId",budgetId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudget")
	public ResponseEntity<Map<String, Object>> createBudget(HttpSession session, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="budgetTypeId", required=false) String budgetTypeId, @RequestParam(value="budgetId", required=false) String budgetId, @RequestParam(value="customTimePeriodId", required=false) String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comments",comments);
		paramMap.put("budgetTypeId",budgetTypeId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudget", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeBudgetRole")
	public ResponseEntity<Map<String, Object>> removeBudgetRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBudgetRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeBudgetReview")
	public ResponseEntity<Map<String, Object>> removeBudgetReview(HttpSession session, @RequestParam(value="budgetReviewResultTypeId") String budgetReviewResultTypeId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="partyId") String partyId, @RequestParam(value="budgetReviewId") String budgetReviewId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetReviewResultTypeId",budgetReviewResultTypeId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("partyId",partyId);
		paramMap.put("budgetReviewId",budgetReviewId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBudgetReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetStatus")
	public ResponseEntity<Map<String, Object>> updateBudgetStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="statusDate", required=false) Timestamp statusDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="changeByUserLoginId", required=false) String changeByUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("comments",comments);
		paramMap.put("changeByUserLoginId",changeByUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetReviewResultType")
	public ResponseEntity<Map<String, Object>> updateBudgetReviewResultType(HttpSession session, @RequestParam(value="budgetReviewResultTypeId") String budgetReviewResultTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetReviewResultTypeId",budgetReviewResultTypeId);
		paramMap.put("comments",comments);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetReviewResultType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetAttribute")
	public ResponseEntity<Map<String, Object>> createBudgetAttribute(HttpSession session, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetId",budgetId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetItem")
	public ResponseEntity<Map<String, Object>> updateBudgetItem(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="purpose", required=false) String purpose, @RequestParam(value="budgetItemTypeId", required=false) String budgetItemTypeId, @RequestParam(value="justification", required=false) String justification) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("amount",amount);
		paramMap.put("purpose",purpose);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("justification",justification);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetItemType")
	public ResponseEntity<Map<String, Object>> updateBudgetItemType(HttpSession session, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetItemType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetScenarioApplication")
	public ResponseEntity<Map<String, Object>> updateBudgetScenarioApplication(HttpSession session, @RequestParam(value="budgetScenarioApplicId") String budgetScenarioApplicId, @RequestParam(value="budgetScenarioId") String budgetScenarioId, @RequestParam(value="amountChange", required=false) BigDecimal amountChange, @RequestParam(value="percentageChange", required=false) BigDecimal percentageChange, @RequestParam(value="budgetItemSeqId", required=false) String budgetItemSeqId, @RequestParam(value="budgetId", required=false) String budgetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioApplicId",budgetScenarioApplicId);
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("amountChange",amountChange);
		paramMap.put("percentageChange",percentageChange);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetScenarioApplication", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeBudgetRevisionImpact")
	public ResponseEntity<Map<String, Object>> removeBudgetRevisionImpact(HttpSession session, @RequestParam(value="revisionSeqId") String revisionSeqId, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("revisionSeqId",revisionSeqId);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeBudgetRevisionImpact", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetItemAttribute")
	public ResponseEntity<Map<String, Object>> updateBudgetItemAttribute(HttpSession session, @RequestParam(value="budgetItemSeqId") String budgetItemSeqId, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetScenarioApplication")
	public ResponseEntity<Map<String, Object>> deleteBudgetScenarioApplication(HttpSession session, @RequestParam(value="budgetScenarioApplicId") String budgetScenarioApplicId, @RequestParam(value="budgetScenarioId") String budgetScenarioId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioApplicId",budgetScenarioApplicId);
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetScenarioApplication", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBudgetItem")
	public ResponseEntity<Map<String, Object>> createBudgetItem(HttpSession session, @RequestParam(value="budgetId") String budgetId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="purpose", required=false) String purpose, @RequestParam(value="budgetItemTypeId", required=false) String budgetItemTypeId, @RequestParam(value="justification", required=false) String justification) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetId",budgetId);
		paramMap.put("amount",amount);
		paramMap.put("purpose",purpose);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("justification",justification);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBudgetItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBudgetItemTypeAttr")
	public ResponseEntity<Map<String, Object>> updateBudgetItemTypeAttr(HttpSession session, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBudgetItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBudgetScenario")
	public ResponseEntity<Map<String, Object>> deleteBudgetScenario(HttpSession session, @RequestParam(value="budgetScenarioId") String budgetScenarioId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budgetScenarioId",budgetScenarioId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBudgetScenario", paramMap);
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
