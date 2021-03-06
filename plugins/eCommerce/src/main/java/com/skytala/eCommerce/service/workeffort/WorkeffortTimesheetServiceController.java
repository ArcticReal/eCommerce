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
@RequestMapping("/service/workeffortTimesheet")
public class WorkeffortTimesheetServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createTimeEntry")
	public ResponseEntity<Map<String, Object>> createTimeEntry(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="timesheetId", required=false) String timesheetId, @RequestParam(value="rateTypeId", required=false) String rateTypeId, @RequestParam(value="hours", required=false) BigDecimal hours, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("hours",hours);
		paramMap.put("comments",comments);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTimeEntry", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTimesheet")
	public ResponseEntity<Map<String, Object>> deleteTimesheet(HttpSession session, @RequestParam(value="timesheetId") String timesheetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTimesheet", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTimeEntry")
	public ResponseEntity<Map<String, Object>> deleteTimeEntry(HttpSession session, @RequestParam(value="timeEntryId") String timeEntryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timeEntryId",timeEntryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTimeEntry", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTimeEntry")
	public ResponseEntity<Map<String, Object>> updateTimeEntry(HttpSession session, @RequestParam(value="timeEntryId") String timeEntryId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="timesheetId", required=false) String timesheetId, @RequestParam(value="rateTypeId", required=false) String rateTypeId, @RequestParam(value="hours", required=false) BigDecimal hours, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="invoiceItemSeqId", required=false) String invoiceItemSeqId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timeEntryId",timeEntryId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("hours",hours);
		paramMap.put("comments",comments);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("invoiceItemSeqId",invoiceItemSeqId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTimeEntry", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addTimesheetToInvoice")
	public ResponseEntity<Map<String, Object>> addTimesheetToInvoice(HttpSession session, @RequestParam(value="timesheetId") String timesheetId, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addTimesheetToInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addWorkEffortTimeToNewInvoice")
	public ResponseEntity<Map<String, Object>> addWorkEffortTimeToNewInvoice(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyId") String partyId, @RequestParam(value="combineInvoiceItem", required=false) String combineInvoiceItem, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyId",partyId);
		paramMap.put("combineInvoiceItem",combineInvoiceItem);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addWorkEffortTimeToNewInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addWorkEffortTimeToInvoice")
	public ResponseEntity<Map<String, Object>> addWorkEffortTimeToInvoice(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="combineInvoiceItem", required=false) String combineInvoiceItem, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("combineInvoiceItem",combineInvoiceItem);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addWorkEffortTimeToInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTimesheetToInProcess")
	public ResponseEntity<Map<String, Object>> updateTimesheetToInProcess(HttpSession session, @RequestParam(value="timesheetId") String timesheetId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTimesheetToInProcess", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTimesheetForThisWeek")
	public ResponseEntity<Map<String, Object>> createTimesheetForThisWeek(HttpSession session, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="approvedByUserLoginId", required=false) String approvedByUserLoginId, @RequestParam(value="requiredDate", required=false) Timestamp requiredDate, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="clientPartyId", required=false) String clientPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("approvedByUserLoginId",approvedByUserLoginId);
		paramMap.put("requiredDate",requiredDate);
		paramMap.put("partyId",partyId);
		paramMap.put("clientPartyId",clientPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTimesheetForThisWeek", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addTimesheetToNewInvoice")
	public ResponseEntity<Map<String, Object>> addTimesheetToNewInvoice(HttpSession session, @RequestParam(value="timesheetId") String timesheetId, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addTimesheetToNewInvoice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/unlinkInvoiceFromTimeEntry")
	public ResponseEntity<Map<String, Object>> unlinkInvoiceFromTimeEntry(HttpSession session, @RequestParam(value="timeEntryId") String timeEntryId, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timeEntryId",timeEntryId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("unlinkInvoiceFromTimeEntry", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTimesheet")
	public ResponseEntity<Map<String, Object>> updateTimesheet(HttpSession session, @RequestParam(value="timesheetId") String timesheetId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="approvedByUserLoginId", required=false) String approvedByUserLoginId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="clientPartyId", required=false) String clientPartyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("approvedByUserLoginId",approvedByUserLoginId);
		paramMap.put("partyId",partyId);
		paramMap.put("clientPartyId",clientPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTimesheet", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTimesheet")
	public ResponseEntity<Map<String, Object>> createTimesheet(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="approvedByUserLoginId", required=false) String approvedByUserLoginId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="clientPartyId", required=false) String clientPartyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("comments",comments);
		paramMap.put("statusId",statusId);
		paramMap.put("approvedByUserLoginId",approvedByUserLoginId);
		paramMap.put("partyId",partyId);
		paramMap.put("clientPartyId",clientPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTimesheet", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTimesheets")
	public ResponseEntity<Map<String, Object>> createTimesheets(HttpSession session, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="partyIdList", required=false) List partyIdList, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="clientPartyId", required=false) String clientPartyId, @RequestParam(value="thruDate", required=false) String thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdList",partyIdList);
		paramMap.put("comments",comments);
		paramMap.put("clientPartyId",clientPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTimesheets", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTimesheetRole")
	public ResponseEntity<Map<String, Object>> deleteTimesheetRole(HttpSession session, @RequestParam(value="timesheetId") String timesheetId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTimesheetRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTimesheetRole")
	public ResponseEntity<Map<String, Object>> createTimesheetRole(HttpSession session, @RequestParam(value="timesheetId") String timesheetId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timesheetId",timesheetId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTimesheetRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getTimeEntryRate")
	public ResponseEntity<Map<String, Object>> getTimeEntryRate(HttpSession session, @RequestParam(value="timeEntryId") String timeEntryId, @RequestParam(value="currencyUomId", required=false) String currencyUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("timeEntryId",timeEntryId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getTimeEntryRate", paramMap);
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
