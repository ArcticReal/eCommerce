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
@RequestMapping("/service/workefforts")
public class WorkeffortsServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/quickAssignPartyToWorkEffort")
	public ResponseEntity<Map<String, Object>> quickAssignPartyToWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quickAssignPartyId") String quickAssignPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quickAssignPartyId",quickAssignPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickAssignPartyToWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortICalData")
	public ResponseEntity<Map<String, Object>> createWorkEffortICalData(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="icalData", required=false) String icalData) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("icalData",icalData);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortICalData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/workEffortICalendarPermission")
	public ResponseEntity<Map<String, Object>> workEffortICalendarPermission(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("workEffortICalendarPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortQuote")
	public ResponseEntity<Map<String, Object>> createWorkEffortQuote(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="quoteTypeId", required=false) String quoteTypeId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="validFromDate", required=false) Timestamp validFromDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="issueDate", required=false) Timestamp issueDate, @RequestParam(value="validThruDate", required=false) Timestamp validThruDate, @RequestParam(value="quoteName", required=false) String quoteName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quoteId",quoteId);
		paramMap.put("quoteTypeId",quoteTypeId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("statusId",statusId);
		paramMap.put("validFromDate",validFromDate);
		paramMap.put("description",description);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("issueDate",issueDate);
		paramMap.put("validThruDate",validThruDate);
		paramMap.put("quoteName",quoteName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortQuote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortAssignedEventsForRole")
	public ResponseEntity<Map<String, Object>> getWorkEffortAssignedEventsForRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortAssignedEventsForRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickAssignPartyToWorkEffortWithRole")
	public ResponseEntity<Map<String, Object>> quickAssignPartyToWorkEffortWithRole(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="quickAssignPartyId") String quickAssignPartyId, @RequestParam(value="roleTypeId") String roleTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quickAssignPartyId",quickAssignPartyId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickAssignPartyToWorkEffortWithRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortNote")
	public ResponseEntity<Map<String, Object>> updateWorkEffortNote(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="internalNote") String internalNote, @RequestParam(value="noteId") String noteId, @RequestParam(value="noteInfo", required=false) String noteInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("internalNote",internalNote);
		paramMap.put("noteId",noteId);
		paramMap.put("noteInfo",noteInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortAndAssoc")
	public ResponseEntity<Map<String, Object>> createWorkEffortAndAssoc(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="currentStatusId") String currentStatusId, @RequestParam(value="workEffortName") String workEffortName, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="revisionNumber", required=false) Long revisionNumber, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="workEffortIdTo", required=false) String workEffortIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quickAssignPartyId", required=false) String quickAssignPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="lastStatusUpdate", required=false) Timestamp lastStatusUpdate, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("revisionNumber",revisionNumber);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("thruDate",thruDate);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quickAssignPartyId",quickAssignPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("lastStatusUpdate",lastStatusUpdate);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("createdDate",createdDate);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortAndAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceWorkEffort")
	public ResponseEntity<Map<String, Object>> interfaceWorkEffort(HttpSession session, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="workEffortName", required=false) String workEffortName, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="currentStatusId", required=false) String currentStatusId, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("specialTerms",specialTerms);
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
			result = dispatcher.runSync("interfaceWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortQuote")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortQuote(HttpSession session, @RequestParam(value="quoteId") String quoteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortQuote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortKeywords")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortKeywords(HttpSession session, @RequestParam(value="workEffortId") String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortSkillStandard")
	public ResponseEntity<Map<String, Object>> createWorkEffortSkillStandard(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="estimatedNumPeople", required=false) BigDecimal estimatedNumPeople, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="estimatedDuration", required=false) BigDecimal estimatedDuration) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("estimatedNumPeople",estimatedNumPeople);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("estimatedDuration",estimatedDuration);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortSkillStandard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortContactMech")
	public ResponseEntity<Map<String, Object>> createWorkEffortContactMech(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="contactMechTypeId") String contactMechTypeId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="verified", required=false) String verified, @RequestParam(value="monthsWithContactMech", required=false) Long monthsWithContactMech, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="allowSolicitation", required=false) String allowSolicitation, @RequestParam(value="yearsWithContactMech", required=false) Long yearsWithContactMech, @RequestParam(value="infoString", required=false) String infoString, @RequestParam(value="contactMechPurposeTypeId", required=false) String contactMechPurposeTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
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
		paramMap.put("contactMechPurposeTypeId",contactMechPurposeTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/duplicateWorkEffort")
	public ResponseEntity<Map<String, Object>> duplicateWorkEffort(HttpSession session, @RequestParam(value="oldWorkEffortId") String oldWorkEffortId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="duplicateWorkEffortAssignmentRates", required=false) String duplicateWorkEffortAssignmentRates, @RequestParam(value="removeWorkEffortContents", required=false) String removeWorkEffortContents, @RequestParam(value="duplicateWorkEffortNotes", required=false) String duplicateWorkEffortNotes, @RequestParam(value="removeWorkEffortAssocs", required=false) String removeWorkEffortAssocs, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="removeWorkEffortNotes", required=false) String removeWorkEffortNotes, @RequestParam(value="removeWorkEffortAssignmentRates", required=false) String removeWorkEffortAssignmentRates, @RequestParam(value="duplicateWorkEffortAssocs", required=false) String duplicateWorkEffortAssocs, @RequestParam(value="duplicateWorkEffortContents", required=false) String duplicateWorkEffortContents) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("oldWorkEffortId",oldWorkEffortId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("duplicateWorkEffortAssignmentRates",duplicateWorkEffortAssignmentRates);
		paramMap.put("removeWorkEffortContents",removeWorkEffortContents);
		paramMap.put("duplicateWorkEffortNotes",duplicateWorkEffortNotes);
		paramMap.put("removeWorkEffortAssocs",removeWorkEffortAssocs);
		paramMap.put("statusId",statusId);
		paramMap.put("removeWorkEffortNotes",removeWorkEffortNotes);
		paramMap.put("removeWorkEffortAssignmentRates",removeWorkEffortAssignmentRates);
		paramMap.put("duplicateWorkEffortAssocs",duplicateWorkEffortAssocs);
		paramMap.put("duplicateWorkEffortContents",duplicateWorkEffortContents);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("duplicateWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCommunicationEventWorkEff")
	public ResponseEntity<Map<String, Object>> deleteCommunicationEventWorkEff(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCommunicationEventWorkEff", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortFixedAssetStd")
	public ResponseEntity<Map<String, Object>> updateWorkEffortFixedAssetStd(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="estimatedQuantity", required=false) BigDecimal estimatedQuantity, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="estimatedDuration", required=false) BigDecimal estimatedDuration) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("estimatedQuantity",estimatedQuantity);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("estimatedDuration",estimatedDuration);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortFixedAssetStd", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortFixedAssetAssign")
	public ResponseEntity<Map<String, Object>> updateWorkEffortFixedAssetAssign(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="allocatedCost", required=false) BigDecimal allocatedCost, @RequestParam(value="availabilityStatusId", required=false) String availabilityStatusId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("allocatedCost",allocatedCost);
		paramMap.put("availabilityStatusId",availabilityStatusId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortFixedAssetAssign", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortAttribute")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortAttribute(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortTextContent")
	public ResponseEntity<Map<String, Object>> createWorkEffortTextContent(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
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
			result = dispatcher.runSync("createWorkEffortTextContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortSurveyAppl")
	public ResponseEntity<Map<String, Object>> createWorkEffortSurveyAppl(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("surveyId",surveyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortSurveyAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/makeCommunicationEventWorkEffort")
	public ResponseEntity<Map<String, Object>> makeCommunicationEventWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="revisionNumber", required=false) Long revisionNumber, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="currentStatusId", required=false) String currentStatusId, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="workEffortName", required=false) String workEffortName, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="relationDescription", required=false) String relationDescription, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="lastStatusUpdate", required=false) Timestamp lastStatusUpdate, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("revisionNumber",revisionNumber);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("relationDescription",relationDescription);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("lastStatusUpdate",lastStatusUpdate);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("createdDate",createdDate);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("makeCommunicationEventWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortRequestItem")
	public ResponseEntity<Map<String, Object>> createWorkEffortRequestItem(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId, @RequestParam(value="custRequestItemExists", required=false) java.lang.String custRequestItemExists, @RequestParam(value="custRequestResolutionId", required=false) String custRequestResolutionId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="maximumAmount", required=false) BigDecimal maximumAmount, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount, @RequestParam(value="story", required=false) String story) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("custRequestItemExists",custRequestItemExists);
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("maximumAmount",maximumAmount);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("description",description);
		paramMap.put("priority",priority);
		paramMap.put("statusId",statusId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("story",story);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortRequestItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortEventsByPeriod")
	public ResponseEntity<Map<String, Object>> getWorkEffortEventsByPeriod(HttpSession session, @RequestParam(value="periodType") java.lang.Integer periodType, @RequestParam(value="numPeriods") java.lang.Integer numPeriods, @RequestParam(value="start") java.sql.Timestamp start, @RequestParam(value="partyIds", required=false) java.util.Collection partyIds, @RequestParam(value="calendarType", required=false) String calendarType, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId, @RequestParam(value="filterOutCanceledEvents", required=false) java.lang.Boolean filterOutCanceledEvents, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="entityExprList", required=false) java.util.List entityExprList, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("periodType",periodType);
		paramMap.put("numPeriods",numPeriods);
		paramMap.put("start",start);
		paramMap.put("partyIds",partyIds);
		paramMap.put("calendarType",calendarType);
		paramMap.put("facilityId",facilityId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("filterOutCanceledEvents",filterOutCanceledEvents);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("entityExprList",entityExprList);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortEventsByPeriod", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkRequirementFulfillment")
	public ResponseEntity<Map<String, Object>> createWorkRequirementFulfillment(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="requirementTypeId") String requirementTypeId, @RequestParam(value="workReqFulfTypeId", required=false) String workReqFulfTypeId, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="estimatedBudget", required=false) BigDecimal estimatedBudget, @RequestParam(value="description", required=false) String description, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="requirementStartDate", required=false) Timestamp requirementStartDate, @RequestParam(value="useCase", required=false) String useCase, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="requirementId", required=false) String requirementId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deliverableId", required=false) String deliverableId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("requirementTypeId",requirementTypeId);
		paramMap.put("workReqFulfTypeId",workReqFulfTypeId);
		paramMap.put("reason",reason);
		paramMap.put("facilityId",facilityId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("estimatedBudget",estimatedBudget);
		paramMap.put("description",description);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("requirementStartDate",requirementStartDate);
		paramMap.put("useCase",useCase);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deliverableId",deliverableId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkRequirementFulfillment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/timesheetUpdatePermission")
	public ResponseEntity<Map<String, Object>> timesheetUpdatePermission(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("timesheetUpdatePermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductManufacturingSummaryByFacility")
	public ResponseEntity<Map<String, Object>> getProductManufacturingSummaryByFacility(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="facilityId", required=false) String facilityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductManufacturingSummaryByFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortReview")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortReview(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="reviewDate") Timestamp reviewDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("reviewDate",reviewDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/indexWorkEffortKeywords")
	public ResponseEntity<Map<String, Object>> indexWorkEffortKeywords(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortInstance", required=false) org.apache.ofbiz.entity.GenericValue workEffortInstance) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortInstance",workEffortInstance);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("indexWorkEffortKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffort")
	public ResponseEntity<Map<String, Object>> getWorkEffort(HttpSession session, @RequestParam(value="workEffortId", required=false) java.lang.String workEffortId, @RequestParam(value="currentStatusId", required=false) java.lang.String currentStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortRequestItem")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortRequestItem(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortRequestItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyToWorkEffortAssignment")
	public ResponseEntity<Map<String, Object>> deletePartyToWorkEffortAssignment(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyToWorkEffortAssignment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffort")
	public ResponseEntity<Map<String, Object>> deleteWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortRequestItemAndRequestItem")
	public ResponseEntity<Map<String, Object>> createWorkEffortRequestItemAndRequestItem(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="custRequestItemSeqId") String custRequestItemSeqId, @RequestParam(value="custRequestResolutionId", required=false) String custRequestResolutionId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="maximumAmount", required=false) BigDecimal maximumAmount, @RequestParam(value="requiredByDate", required=false) Timestamp requiredByDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="selectedAmount", required=false) BigDecimal selectedAmount, @RequestParam(value="story", required=false) String story) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("custRequestResolutionId",custRequestResolutionId);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("maximumAmount",maximumAmount);
		paramMap.put("requiredByDate",requiredByDate);
		paramMap.put("description",description);
		paramMap.put("priority",priority);
		paramMap.put("statusId",statusId);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("selectedAmount",selectedAmount);
		paramMap.put("story",story);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortRequestItemAndRequestItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffort")
	public ResponseEntity<Map<String, Object>> updateWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="workEffortName", required=false) String workEffortName, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="currentStatusId", required=false) String currentStatusId, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("reason",reason);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteOrderHeaderWorkEffort")
	public ResponseEntity<Map<String, Object>> deleteOrderHeaderWorkEffort(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteOrderHeaderWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortICalData")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortICalData(HttpSession session, @RequestParam(value="workEffortId") String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortICalData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/assignInventoryToWorkEffort")
	public ResponseEntity<Map<String, Object>> assignInventoryToWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("assignInventoryToWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortSurveyAppl")
	public ResponseEntity<Map<String, Object>> updateWorkEffortSurveyAppl(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("surveyId",surveyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortSurveyAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/workEffortManagerPermission")
	public ResponseEntity<Map<String, Object>> workEffortManagerPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("workEffortManagerPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeWorkEffortGoodStandard")
	public ResponseEntity<Map<String, Object>> removeWorkEffortGoodStandard(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortGoodStdTypeId") String workEffortGoodStdTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortGoodStdTypeId",workEffortGoodStdTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWorkEffortGoodStandard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortFixedAssetStd")
	public ResponseEntity<Map<String, Object>> createWorkEffortFixedAssetStd(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId, @RequestParam(value="estimatedQuantity", required=false) BigDecimal estimatedQuantity, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="estimatedDuration", required=false) BigDecimal estimatedDuration) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("estimatedQuantity",estimatedQuantity);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("estimatedDuration",estimatedDuration);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortFixedAssetStd", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortEventReminder")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortEventReminder(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="sequenceId") String sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortEventReminder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortRequest")
	public ResponseEntity<Map<String, Object>> createWorkEffortRequest(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="custRequestId") String custRequestId, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="fromPartyId", required=false) String fromPartyId, @RequestParam(value="custRequestName", required=false) String custRequestName, @RequestParam(value="responseRequiredDate", required=false) Timestamp responseRequiredDate, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="fulfillContactMechId", required=false) String fulfillContactMechId, @RequestParam(value="description", required=false) String description, @RequestParam(value="custRequestDate", required=false) Timestamp custRequestDate, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="maximumAmountUomId", required=false) String maximumAmountUomId, @RequestParam(value="openDateTime", required=false) Timestamp openDateTime, @RequestParam(value="internalComment", required=false) String internalComment, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="custRequestTypeId", required=false) String custRequestTypeId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="custRequestCategoryId", required=false) String custRequestCategoryId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="closedDateTime", required=false) Timestamp closedDateTime) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("reason",reason);
		paramMap.put("fromPartyId",fromPartyId);
		paramMap.put("custRequestName",custRequestName);
		paramMap.put("responseRequiredDate",responseRequiredDate);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("fulfillContactMechId",fulfillContactMechId);
		paramMap.put("description",description);
		paramMap.put("custRequestDate",custRequestDate);
		paramMap.put("priority",priority);
		paramMap.put("maximumAmountUomId",maximumAmountUomId);
		paramMap.put("openDateTime",openDateTime);
		paramMap.put("internalComment",internalComment);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("custRequestTypeId",custRequestTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("custRequestCategoryId",custRequestCategoryId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("closedDateTime",closedDateTime);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortSkillStandard")
	public ResponseEntity<Map<String, Object>> updateWorkEffortSkillStandard(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="estimatedNumPeople", required=false) BigDecimal estimatedNumPeople, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="estimatedDuration", required=false) BigDecimal estimatedDuration) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("estimatedNumPeople",estimatedNumPeople);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("estimatedDuration",estimatedDuration);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortSkillStandard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortContent")
	public ResponseEntity<Map<String, Object>> updateWorkEffortContent(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortGoodStandard")
	public ResponseEntity<Map<String, Object>> createWorkEffortGoodStandard(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortGoodStdTypeId") String workEffortGoodStdTypeId, @RequestParam(value="productId") String productId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="estimatedQuantity", required=false) BigDecimal estimatedQuantity, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortGoodStdTypeId",workEffortGoodStdTypeId);
		paramMap.put("productId",productId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("estimatedQuantity",estimatedQuantity);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortGoodStandard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortContactMech")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortContactMech(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyId") String partyId, @RequestParam(value="contactMechId") String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyId",partyId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getICalWorkEfforts")
	public ResponseEntity<Map<String, Object>> getICalWorkEfforts(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getICalWorkEfforts", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortAttribute")
	public ResponseEntity<Map<String, Object>> createWorkEffortAttribute(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortKeyword")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortKeyword(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="keyword") String keyword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("keyword",keyword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventWorkEff")
	public ResponseEntity<Map<String, Object>> createCommunicationEventWorkEff(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="note", required=false) String note, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="ccString", required=false) String ccString, @RequestParam(value="description", required=false) String description, @RequestParam(value="contactMechTypeId", required=false) String contactMechTypeId, @RequestParam(value="communicationEventTypeId", required=false) String communicationEventTypeId, @RequestParam(value="content", required=false) String content, @RequestParam(value="contentMimeTypeId", required=false) String contentMimeTypeId, @RequestParam(value="datetimeStarted", required=false) Timestamp datetimeStarted, @RequestParam(value="contactListId", required=false) String contactListId, @RequestParam(value="contactMechIdFrom", required=false) String contactMechIdFrom, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="parentCommEventId", required=false) String parentCommEventId, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="datetimeEnded", required=false) Timestamp datetimeEnded, @RequestParam(value="origCommEventId", required=false) String origCommEventId, @RequestParam(value="messageId", required=false) Long messageId, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="contactMechIdTo", required=false) String contactMechIdTo, @RequestParam(value="headerString", required=false) String headerString, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="bccString", required=false) String bccString, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="toString", required=false) String toString, @RequestParam(value="fromString", required=false) String fromString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("note",note);
		paramMap.put("subject",subject);
		paramMap.put("ccString",ccString);
		paramMap.put("description",description);
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
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("bccString",bccString);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("toString",toString);
		paramMap.put("fromString",fromString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventWorkEff", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeWorkEffortFixedAssetStd")
	public ResponseEntity<Map<String, Object>> removeWorkEffortFixedAssetStd(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fixedAssetTypeId") String fixedAssetTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWorkEffortFixedAssetStd", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeWorkEffortAssoc")
	public ResponseEntity<Map<String, Object>> removeWorkEffortAssoc(HttpSession session, @RequestParam(value="workEffortIdTo") String workEffortIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWorkEffortAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processWorkEffortEventReminder")
	public ResponseEntity<Map<String, Object>> processWorkEffortEventReminder(HttpSession session, @RequestParam(value="bodyParameters") Map bodyParameters, @RequestParam(value="reminder") GenericValue reminder) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bodyParameters",bodyParameters);
		paramMap.put("reminder",reminder);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processWorkEffortEventReminder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortReview")
	public ResponseEntity<Map<String, Object>> updateWorkEffortReview(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="reviewDate") Timestamp reviewDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="rating", required=false) BigDecimal rating, @RequestParam(value="postedAnonymous", required=false) String postedAnonymous, @RequestParam(value="reviewText", required=false) String reviewText) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("reviewDate",reviewDate);
		paramMap.put("statusId",statusId);
		paramMap.put("rating",rating);
		paramMap.put("postedAnonymous",postedAnonymous);
		paramMap.put("reviewText",reviewText);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setWorkEffortFixedAssetAssign")
	public ResponseEntity<Map<String, Object>> setWorkEffortFixedAssetAssign(HttpSession session, @RequestParam(value="workEffortId") String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setWorkEffortFixedAssetAssign", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortNote")
	public ResponseEntity<Map<String, Object>> createWorkEffortNote(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="noteInfo") String noteInfo, @RequestParam(value="noteName", required=false) String noteName, @RequestParam(value="internalNote", required=false) String internalNote, @RequestParam(value="noteParty", required=false) String noteParty) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("noteInfo",noteInfo);
		paramMap.put("noteName",noteName);
		paramMap.put("internalNote",internalNote);
		paramMap.put("noteParty",noteParty);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyWorkEffortAssocs")
	public ResponseEntity<Map<String, Object>> copyWorkEffortAssocs(HttpSession session, @RequestParam(value="sourceWorkEffortId") String sourceWorkEffortId, @RequestParam(value="copyRelatedValues", required=false) String copyRelatedValues, @RequestParam(value="excludeExpiredAssocs", required=false) String excludeExpiredAssocs, @RequestParam(value="targetWorkEffortId", required=false) String targetWorkEffortId, @RequestParam(value="excludeExpiredRelations", required=false) String excludeExpiredRelations, @RequestParam(value="deepCopy", required=false) String deepCopy) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sourceWorkEffortId",sourceWorkEffortId);
		paramMap.put("copyRelatedValues",copyRelatedValues);
		paramMap.put("excludeExpiredAssocs",excludeExpiredAssocs);
		paramMap.put("targetWorkEffortId",targetWorkEffortId);
		paramMap.put("excludeExpiredRelations",excludeExpiredRelations);
		paramMap.put("deepCopy",deepCopy);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyWorkEffortAssocs", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortAndAssoc")
	public ResponseEntity<Map<String, Object>> updateWorkEffortAndAssoc(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="workEffortTypeId", required=false) String workEffortTypeId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="revisionNumber", required=false) Long revisionNumber, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="workEffortIdFrom", required=false) String workEffortIdFrom, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="currentStatusId", required=false) String currentStatusId, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="workEffortIdTo", required=false) String workEffortIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="workEffortName", required=false) String workEffortName, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="lastStatusUpdate", required=false) Timestamp lastStatusUpdate, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="workEffortAssocTypeId", required=false) String workEffortAssocTypeId, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("revisionNumber",revisionNumber);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("thruDate",thruDate);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("lastStatusUpdate",lastStatusUpdate);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("createdDate",createdDate);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortAndAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortTextContent")
	public ResponseEntity<Map<String, Object>> updateWorkEffortTextContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="description", required=false) String description, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("description",description);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("textData",textData);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortTextContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortAndPartyAssign")
	public ResponseEntity<Map<String, Object>> createWorkEffortAndPartyAssign(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="statusId") String statusId, @RequestParam(value="partyId") String partyId, @RequestParam(value="currentStatusId") String currentStatusId, @RequestParam(value="workEffortName") String workEffortName, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quickAssignPartyId", required=false) String quickAssignPartyId, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="requirementId", required=false) String requirementId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("partyId",partyId);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quickAssignPartyId",quickAssignPartyId);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortAndPartyAssign", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyWorkEffort")
	public ResponseEntity<Map<String, Object>> copyWorkEffort(HttpSession session, @RequestParam(value="sourceWorkEffortId") String sourceWorkEffortId, @RequestParam(value="copyRelatedValues", required=false) String copyRelatedValues, @RequestParam(value="excludeExpiredAssocs", required=false) String excludeExpiredAssocs, @RequestParam(value="targetWorkEffortId", required=false) String targetWorkEffortId, @RequestParam(value="copyWorkEffortAssocs", required=false) String copyWorkEffortAssocs, @RequestParam(value="excludeExpiredRelations", required=false) String excludeExpiredRelations, @RequestParam(value="deepCopy", required=false) String deepCopy) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sourceWorkEffortId",sourceWorkEffortId);
		paramMap.put("copyRelatedValues",copyRelatedValues);
		paramMap.put("excludeExpiredAssocs",excludeExpiredAssocs);
		paramMap.put("targetWorkEffortId",targetWorkEffortId);
		paramMap.put("copyWorkEffortAssocs",copyWorkEffortAssocs);
		paramMap.put("excludeExpiredRelations",excludeExpiredRelations);
		paramMap.put("deepCopy",deepCopy);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeWorkEffortFixedAssetAssign")
	public ResponseEntity<Map<String, Object>> removeWorkEffortFixedAssetAssign(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="fixedAssetId") String fixedAssetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWorkEffortFixedAssetAssign", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingListWorkEffort")
	public ResponseEntity<Map<String, Object>> createShoppingListWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="parentShoppingListId", required=false) String parentShoppingListId, @RequestParam(value="productPromoCodeId", required=false) String productPromoCodeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="isActive", required=false) String isActive, @RequestParam(value="shoppingListTypeId", required=false) String shoppingListTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="shoppingListId", required=false) String shoppingListId, @RequestParam(value="currencyUom", required=false) String currencyUom, @RequestParam(value="lastOrderedDate", required=false) Timestamp lastOrderedDate, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="listName", required=false) String listName, @RequestParam(value="lastAdminModified", required=false) Timestamp lastAdminModified, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="visitorId", required=false) String visitorId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("parentShoppingListId",parentShoppingListId);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("description",description);
		paramMap.put("isActive",isActive);
		paramMap.put("shoppingListTypeId",shoppingListTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("lastOrderedDate",lastOrderedDate);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("listName",listName);
		paramMap.put("lastAdminModified",lastAdminModified);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("visitorId",visitorId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShoppingListWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkCustRequestItemExists")
	public ResponseEntity<Map<String, Object>> checkCustRequestItemExists(HttpSession session, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="custRequestItemSeqId", required=false) String custRequestItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("custRequestItemSeqId",custRequestItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCustRequestItemExists", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processWorkEffortEventReminders")
	public ResponseEntity<Map<String, Object>> processWorkEffortEventReminders(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processWorkEffortEventReminders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShoppingListWorkEffort")
	public ResponseEntity<Map<String, Object>> deleteShoppingListWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="shoppingListId") String shoppingListId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShoppingListWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortEventReminder")
	public ResponseEntity<Map<String, Object>> createWorkEffortEventReminder(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="timeZoneId", required=false) String timeZoneId, @RequestParam(value="currentCount", required=false) Long currentCount, @RequestParam(value="reminderDateTime", required=false) Timestamp reminderDateTime, @RequestParam(value="repeatInterval", required=false) Long repeatInterval, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="reminderOffset", required=false) Long reminderOffset, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="localeId", required=false) String localeId, @RequestParam(value="repeatCount", required=false) Long repeatCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("timeZoneId",timeZoneId);
		paramMap.put("currentCount",currentCount);
		paramMap.put("reminderDateTime",reminderDateTime);
		paramMap.put("repeatInterval",repeatInterval);
		paramMap.put("partyId",partyId);
		paramMap.put("reminderOffset",reminderOffset);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("localeId",localeId);
		paramMap.put("repeatCount",repeatCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortEventReminder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortAssignedTasks")
	public ResponseEntity<Map<String, Object>> getWorkEffortAssignedTasks(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortAssignedTasks", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortAssignedActivitiesByGroup")
	public ResponseEntity<Map<String, Object>> getWorkEffortAssignedActivitiesByGroup(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortAssignedActivitiesByGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortAssoc")
	public ResponseEntity<Map<String, Object>> createWorkEffortAssoc(HttpSession session, @RequestParam(value="workEffortIdTo") String workEffortIdTo, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortInventoryProduced")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortInventoryProduced(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortInventoryProduced", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortFixedAssetAssign")
	public ResponseEntity<Map<String, Object>> createWorkEffortFixedAssetAssign(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="allocatedCost", required=false) BigDecimal allocatedCost, @RequestParam(value="availabilityStatusId", required=false) String availabilityStatusId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("allocatedCost",allocatedCost);
		paramMap.put("availabilityStatusId",availabilityStatusId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortFixedAssetAssign", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortAssignedActivitiesByRole")
	public ResponseEntity<Map<String, Object>> getWorkEffortAssignedActivitiesByRole(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortAssignedActivitiesByRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortGoodStandard")
	public ResponseEntity<Map<String, Object>> updateWorkEffortGoodStandard(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortGoodStdTypeId") String workEffortGoodStdTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="estimatedQuantity", required=false) BigDecimal estimatedQuantity, @RequestParam(value="estimatedCost", required=false) BigDecimal estimatedCost, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortGoodStdTypeId",workEffortGoodStdTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("estimatedQuantity",estimatedQuantity);
		paramMap.put("estimatedCost",estimatedCost);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortGoodStandard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortRequest")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortRequest(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="custRequestId") String custRequestId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortSkillStandard")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortSkillStandard(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="skillTypeId") String skillTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortSkillStandard", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getPartyICalUrl")
	public ResponseEntity<Map<String, Object>> getPartyICalUrl(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPartyICalUrl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/workEffortGenericPermission")
	public ResponseEntity<Map<String, Object>> workEffortGenericPermission(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("workEffortGenericPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/uploadWorkEffortContentFile")
	public ResponseEntity<Map<String, Object>> uploadWorkEffortContentFile(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
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
			result = dispatcher.runSync("uploadWorkEffortContentFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortAssignedEventsForRoleOfAllParties")
	public ResponseEntity<Map<String, Object>> getWorkEffortAssignedEventsForRoleOfAllParties(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortAssignedEventsForRoleOfAllParties", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortKeywords")
	public ResponseEntity<Map<String, Object>> createWorkEffortKeywords(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="keyword") String keyword, @RequestParam(value="relevancyWeight", required=false) Long relevancyWeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("keyword",keyword);
		paramMap.put("relevancyWeight",relevancyWeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortContent")
	public ResponseEntity<Map<String, Object>> createWorkEffortContent(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffort")
	public ResponseEntity<Map<String, Object>> createWorkEffort(HttpSession session, @RequestParam(value="workEffortTypeId") String workEffortTypeId, @RequestParam(value="currentStatusId") String currentStatusId, @RequestParam(value="workEffortName") String workEffortName, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="totalMoneyAllowed", required=false) BigDecimal totalMoneyAllowed, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="workEffortParentId", required=false) String workEffortParentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="moneyUomId", required=false) String moneyUomId, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quickAssignPartyId", required=false) String quickAssignPartyId, @RequestParam(value="showAsEnumId", required=false) String showAsEnumId, @RequestParam(value="infoUrl", required=false) String infoUrl, @RequestParam(value="universalId", required=false) String universalId, @RequestParam(value="accommodationMapId", required=false) String accommodationMapId, @RequestParam(value="locationDesc", required=false) String locationDesc, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="custRequestId", required=false) String custRequestId, @RequestParam(value="scopeEnumId", required=false) String scopeEnumId, @RequestParam(value="requirementId", required=false) String requirementId, @RequestParam(value="quantityProduced", required=false) BigDecimal quantityProduced, @RequestParam(value="actualMilliSeconds", required=false) BigDecimal actualMilliSeconds, @RequestParam(value="quantityToProduce", required=false) BigDecimal quantityToProduce, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="estimatedSetupMillis", required=false) BigDecimal estimatedSetupMillis, @RequestParam(value="workEffortPurposeTypeId", required=false) String workEffortPurposeTypeId, @RequestParam(value="serviceLoaderName", required=false) String serviceLoaderName, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="sourceReferenceId", required=false) String sourceReferenceId, @RequestParam(value="sendNotificationEmail", required=false) String sendNotificationEmail, @RequestParam(value="noteId", required=false) String noteId, @RequestParam(value="percentComplete", required=false) Long percentComplete, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="priority", required=false) Long priority, @RequestParam(value="totalMilliSecondsAllowed", required=false) BigDecimal totalMilliSecondsAllowed, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="estimatedMilliSeconds", required=false) BigDecimal estimatedMilliSeconds, @RequestParam(value="specialTerms", required=false) String specialTerms, @RequestParam(value="timeTransparency", required=false) Long timeTransparency, @RequestParam(value="actualCompletionDate", required=false) Timestamp actualCompletionDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="actualSetupMillis", required=false) BigDecimal actualSetupMillis, @RequestParam(value="estimatedCompletionDate", required=false) Timestamp estimatedCompletionDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortTypeId",workEffortTypeId);
		paramMap.put("currentStatusId",currentStatusId);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("totalMoneyAllowed",totalMoneyAllowed);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("workEffortParentId",workEffortParentId);
		paramMap.put("description",description);
		paramMap.put("moneyUomId",moneyUomId);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quickAssignPartyId",quickAssignPartyId);
		paramMap.put("showAsEnumId",showAsEnumId);
		paramMap.put("infoUrl",infoUrl);
		paramMap.put("universalId",universalId);
		paramMap.put("accommodationMapId",accommodationMapId);
		paramMap.put("locationDesc",locationDesc);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("custRequestId",custRequestId);
		paramMap.put("scopeEnumId",scopeEnumId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("quantityProduced",quantityProduced);
		paramMap.put("actualMilliSeconds",actualMilliSeconds);
		paramMap.put("quantityToProduce",quantityToProduce);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("estimatedSetupMillis",estimatedSetupMillis);
		paramMap.put("workEffortPurposeTypeId",workEffortPurposeTypeId);
		paramMap.put("serviceLoaderName",serviceLoaderName);
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("sourceReferenceId",sourceReferenceId);
		paramMap.put("sendNotificationEmail",sendNotificationEmail);
		paramMap.put("noteId",noteId);
		paramMap.put("percentComplete",percentComplete);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("priority",priority);
		paramMap.put("totalMilliSecondsAllowed",totalMilliSecondsAllowed);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("estimatedMilliSeconds",estimatedMilliSeconds);
		paramMap.put("specialTerms",specialTerms);
		paramMap.put("timeTransparency",timeTransparency);
		paramMap.put("actualCompletionDate",actualCompletionDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("actualSetupMillis",actualSetupMillis);
		paramMap.put("estimatedCompletionDate",estimatedCompletionDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkRequirementFulfillment")
	public ResponseEntity<Map<String, Object>> deleteWorkRequirementFulfillment(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="requirementId") String requirementId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("requirementId",requirementId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkRequirementFulfillment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortICalData")
	public ResponseEntity<Map<String, Object>> updateWorkEffortICalData(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="icalData", required=false) String icalData) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("icalData",icalData);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortICalData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortInventoryProduced")
	public ResponseEntity<Map<String, Object>> createWorkEffortInventoryProduced(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortInventoryProduced", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeDuplicateWorkEfforts")
	public ResponseEntity<Map<String, Object>> removeDuplicateWorkEfforts(HttpSession session, @RequestParam(value="workEffortIterator", required=false) java.util.ListIterator workEffortIterator, @RequestParam(value="workEfforts", required=false) java.util.List workEfforts) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIterator",workEffortIterator);
		paramMap.put("workEfforts",workEfforts);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeDuplicateWorkEfforts", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWorkEffortContent")
	public ResponseEntity<Map<String, Object>> deleteWorkEffortContent(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="workEffortContentTypeId") String workEffortContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortContentTypeId",workEffortContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWorkEffortContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortAssoc")
	public ResponseEntity<Map<String, Object>> updateWorkEffortAssoc(HttpSession session, @RequestParam(value="workEffortIdTo") String workEffortIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/assignPartyToWorkEffort")
	public ResponseEntity<Map<String, Object>> assignPartyToWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="statusId") String statusId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="mustRsvp", required=false) String mustRsvp, @RequestParam(value="assignedByUserLoginId", required=false) String assignedByUserLoginId, @RequestParam(value="expectationEnumId", required=false) String expectationEnumId, @RequestParam(value="availabilityStatusId", required=false) String availabilityStatusId, @RequestParam(value="delegateReasonEnumId", required=false) String delegateReasonEnumId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("mustRsvp",mustRsvp);
		paramMap.put("assignedByUserLoginId",assignedByUserLoginId);
		paramMap.put("expectationEnumId",expectationEnumId);
		paramMap.put("availabilityStatusId",availabilityStatusId);
		paramMap.put("delegateReasonEnumId",delegateReasonEnumId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("assignPartyToWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyToWorkEffortAssignment")
	public ResponseEntity<Map<String, Object>> updatePartyToWorkEffortAssignment(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="mustRsvp", required=false) String mustRsvp, @RequestParam(value="assignedByUserLoginId", required=false) String assignedByUserLoginId, @RequestParam(value="expectationEnumId", required=false) String expectationEnumId, @RequestParam(value="availabilityStatusId", required=false) String availabilityStatusId, @RequestParam(value="delegateReasonEnumId", required=false) String delegateReasonEnumId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("mustRsvp",mustRsvp);
		paramMap.put("assignedByUserLoginId",assignedByUserLoginId);
		paramMap.put("expectationEnumId",expectationEnumId);
		paramMap.put("availabilityStatusId",availabilityStatusId);
		paramMap.put("delegateReasonEnumId",delegateReasonEnumId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyToWorkEffortAssignment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortReview")
	public ResponseEntity<Map<String, Object>> createWorkEffortReview(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reviewDate", required=false) Timestamp reviewDate, @RequestParam(value="rating", required=false) BigDecimal rating, @RequestParam(value="postedAnonymous", required=false) String postedAnonymous, @RequestParam(value="reviewText", required=false) String reviewText) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("statusId",statusId);
		paramMap.put("reviewDate",reviewDate);
		paramMap.put("rating",rating);
		paramMap.put("postedAnonymous",postedAnonymous);
		paramMap.put("reviewText",reviewText);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortAttribute")
	public ResponseEntity<Map<String, Object>> updateWorkEffortAttribute(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/unassignPartyFromWorkEffort")
	public ResponseEntity<Map<String, Object>> unassignPartyFromWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("unassignPartyFromWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderHeaderWorkEffort")
	public ResponseEntity<Map<String, Object>> createOrderHeaderWorkEffort(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="invoicePerShipment", required=false) String invoicePerShipment, @RequestParam(value="salesChannelEnumId", required=false) String salesChannelEnumId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="orderTypeId", required=false) String orderTypeId, @RequestParam(value="billingAccountId", required=false) String billingAccountId, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="isViewed", required=false) String isViewed, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="remainingSubTotal", required=false) BigDecimal remainingSubTotal, @RequestParam(value="orderName", required=false) String orderName, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="isRushOrder", required=false) String isRushOrder, @RequestParam(value="entryDate", required=false) Timestamp entryDate, @RequestParam(value="grandTotal", required=false) BigDecimal grandTotal, @RequestParam(value="autoOrderShoppingListId", required=false) String autoOrderShoppingListId, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="priority", required=false) String priority, @RequestParam(value="transactionId", required=false) String transactionId, @RequestParam(value="firstAttemptOrderId", required=false) String firstAttemptOrderId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="currencyUom", required=false) String currencyUom, @RequestParam(value="syncStatusId", required=false) String syncStatusId, @RequestParam(value="pickSheetPrintedDate", required=false) Timestamp pickSheetPrintedDate, @RequestParam(value="needsInventoryIssuance", required=false) String needsInventoryIssuance, @RequestParam(value="orderDate", required=false) Timestamp orderDate, @RequestParam(value="internalCode", required=false) String internalCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("invoicePerShipment",invoicePerShipment);
		paramMap.put("salesChannelEnumId",salesChannelEnumId);
		paramMap.put("orderId",orderId);
		paramMap.put("orderTypeId",orderTypeId);
		paramMap.put("billingAccountId",billingAccountId);
		paramMap.put("terminalId",terminalId);
		paramMap.put("isViewed",isViewed);
		paramMap.put("visitId",visitId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("remainingSubTotal",remainingSubTotal);
		paramMap.put("orderName",orderName);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("isRushOrder",isRushOrder);
		paramMap.put("entryDate",entryDate);
		paramMap.put("grandTotal",grandTotal);
		paramMap.put("autoOrderShoppingListId",autoOrderShoppingListId);
		paramMap.put("externalId",externalId);
		paramMap.put("priority",priority);
		paramMap.put("transactionId",transactionId);
		paramMap.put("firstAttemptOrderId",firstAttemptOrderId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("statusId",statusId);
		paramMap.put("createdBy",createdBy);
		paramMap.put("currencyUom",currencyUom);
		paramMap.put("syncStatusId",syncStatusId);
		paramMap.put("pickSheetPrintedDate",pickSheetPrintedDate);
		paramMap.put("needsInventoryIssuance",needsInventoryIssuance);
		paramMap.put("orderDate",orderDate);
		paramMap.put("internalCode",internalCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderHeaderWorkEffort", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommunicationEventWorkEff")
	public ResponseEntity<Map<String, Object>> updateCommunicationEventWorkEff(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommunicationEventWorkEff", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWorkEffortEventReminder")
	public ResponseEntity<Map<String, Object>> updateWorkEffortEventReminder(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="sequenceId") String sequenceId, @RequestParam(value="timeZoneId", required=false) String timeZoneId, @RequestParam(value="currentCount", required=false) Long currentCount, @RequestParam(value="reminderDateTime", required=false) Timestamp reminderDateTime, @RequestParam(value="repeatInterval", required=false) Long repeatInterval, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="reminderOffset", required=false) Long reminderOffset, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="localeId", required=false) String localeId, @RequestParam(value="repeatCount", required=false) Long repeatCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("timeZoneId",timeZoneId);
		paramMap.put("currentCount",currentCount);
		paramMap.put("reminderDateTime",reminderDateTime);
		paramMap.put("repeatInterval",repeatInterval);
		paramMap.put("partyId",partyId);
		paramMap.put("reminderOffset",reminderOffset);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("localeId",localeId);
		paramMap.put("repeatCount",repeatCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWorkEffortEventReminder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWorkEffortKeyword")
	public ResponseEntity<Map<String, Object>> createWorkEffortKeyword(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="keyword") String keyword, @RequestParam(value="relevancyWeight", required=false) Long relevancyWeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("keyword",keyword);
		paramMap.put("relevancyWeight",relevancyWeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWorkEffortKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getWorkEffortAssignedActivities")
	public ResponseEntity<Map<String, Object>> getWorkEffortAssignedActivities(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWorkEffortAssignedActivities", paramMap);
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
