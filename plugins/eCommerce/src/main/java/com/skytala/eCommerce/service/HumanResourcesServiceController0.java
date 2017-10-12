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
@RequestMapping("/service/HumanResourcesController0")
public class HumanResourcesServiceController0{

	@RequestMapping(method = RequestMethod.POST, value = "/createAgreementEmploymentAppl")
	public ResponseEntity<Object> createAgreementEmploymentAppl(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="agreementId") String agreementId, @RequestParam(value="agreementItemSeqId") String agreementItemSeqId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="agreementDate", required=false) Timestamp agreementDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("agreementId",agreementId);
		paramMap.put("agreementItemSeqId",agreementItemSeqId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("agreementDate",agreementDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAgreementEmploymentAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePayHistory")
	public ResponseEntity<Object> updatePayHistory(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="salaryStepSeqId", required=false) String salaryStepSeqId, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="payGradeId", required=false) String payGradeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("salaryStepSeqId",salaryStepSeqId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePayHistory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyResume")
	public ResponseEntity<Object> createPartyResume(HttpSession session, @RequestParam(value="resumeText", required=false) String resumeText, @RequestParam(value="resumeId", required=false) String resumeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="resumeDate", required=false) Timestamp resumeDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resumeText",resumeText);
		paramMap.put("resumeId",resumeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("resumeDate",resumeDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyResume", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplPositionReportingStruct")
	public ResponseEntity<Object> deleteEmplPositionReportingStruct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionIdManagedBy") String emplPositionIdManagedBy, @RequestParam(value="emplPositionIdReportingTo") String emplPositionIdReportingTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionIdManagedBy",emplPositionIdManagedBy);
		paramMap.put("emplPositionIdReportingTo",emplPositionIdReportingTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplPositionReportingStruct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmployment")
	public ResponseEntity<Object> updateEmployment(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="terminationReasonId", required=false) String terminationReasonId, @RequestParam(value="terminationTypeId", required=false) String terminationTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("terminationReasonId",terminationReasonId);
		paramMap.put("terminationTypeId",terminationTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmployment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPositionTypeRate")
	public ResponseEntity<Object> updateEmplPositionTypeRate(HttpSession session, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="periodTypeId") String periodTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="rateAmount", required=false) BigDecimal rateAmount, @RequestParam(value="salaryStepSeqId", required=false) String salaryStepSeqId, @RequestParam(value="rateCurrencyUomId", required=false) String rateCurrencyUomId, @RequestParam(value="payGradeId", required=false) String payGradeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateAmount",rateAmount);
		paramMap.put("salaryStepSeqId",salaryStepSeqId);
		paramMap.put("rateCurrencyUomId",rateCurrencyUomId);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPositionTypeRate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSalaryStep")
	public ResponseEntity<Object> deleteSalaryStep(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="salaryStepSeqId") String salaryStepSeqId, @RequestParam(value="payGradeId") String payGradeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("salaryStepSeqId",salaryStepSeqId);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSalaryStep", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePerfReview")
	public ResponseEntity<Object> updatePerfReview(HttpSession session, @RequestParam(value="employeeRoleTypeId") String employeeRoleTypeId, @RequestParam(value="employeePartyId") String employeePartyId, @RequestParam(value="perfReviewId") String perfReviewId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="managerRoleTypeId", required=false) String managerRoleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="managerPartyId", required=false) String managerPartyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employeeRoleTypeId",employeeRoleTypeId);
		paramMap.put("employeePartyId",employeePartyId);
		paramMap.put("perfReviewId",perfReviewId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("managerRoleTypeId",managerRoleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("paymentId",paymentId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("managerPartyId",managerPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePerfReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPerfReview")
	public ResponseEntity<Object> createPerfReview(HttpSession session, @RequestParam(value="employeeRoleTypeId") String employeeRoleTypeId, @RequestParam(value="employeePartyId") String employeePartyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="managerRoleTypeId", required=false) String managerRoleTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="perfReviewId", required=false) String perfReviewId, @RequestParam(value="managerPartyId", required=false) String managerPartyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employeeRoleTypeId",employeeRoleTypeId);
		paramMap.put("employeePartyId",employeePartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("managerRoleTypeId",managerRoleTypeId);
		paramMap.put("comments",comments);
		paramMap.put("paymentId",paymentId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("perfReviewId",perfReviewId);
		paramMap.put("managerPartyId",managerPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPerfReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplPositionFulfillment")
	public ResponseEntity<Object> createEmplPositionFulfillment(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionId") String emplPositionId, @RequestParam(value="partyId") String partyId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("partyId",partyId);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplPositionFulfillment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPositionFulfillment")
	public ResponseEntity<Object> updateEmplPositionFulfillment(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionId") String emplPositionId, @RequestParam(value="partyId") String partyId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("partyId",partyId);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPositionFulfillment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteJobInterviewType")
	public ResponseEntity<Object> deleteJobInterviewType(HttpSession session, @RequestParam(value="jobInterviewTypeId") String jobInterviewTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobInterviewTypeId",jobInterviewTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteJobInterviewType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTrainingTypes")
	public ResponseEntity<Object> updateTrainingTypes(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="trainingClassTypeId") String trainingClassTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("trainingClassTypeId",trainingClassTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTrainingTypes", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePerformanceNote")
	public ResponseEntity<Object> updatePerformanceNote(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="communicationDate", required=false) Timestamp communicationDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("comments",comments);
		paramMap.put("communicationDate",communicationDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePerformanceNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTrainingTypes")
	public ResponseEntity<Object> createTrainingTypes(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="trainingClassTypeId") String trainingClassTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("trainingClassTypeId",trainingClassTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTrainingTypes", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePayGrade")
	public ResponseEntity<Object> deletePayGrade(HttpSession session, @RequestParam(value="payGradeId") String payGradeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePayGrade", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplLeaveReasonType")
	public ResponseEntity<Object> createEmplLeaveReasonType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="emplLeaveReasonTypeId", required=false) String emplLeaveReasonTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("emplLeaveReasonTypeId",emplLeaveReasonTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplLeaveReasonType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTerminationReason")
	public ResponseEntity<Object> updateTerminationReason(HttpSession session, @RequestParam(value="terminationReasonId") String terminationReasonId, @RequestParam(value="description") String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("terminationReasonId",terminationReasonId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTerminationReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAgreementEmploymentAppl")
	public ResponseEntity<Object> deleteAgreementEmploymentAppl(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="agreementId") String agreementId, @RequestParam(value="agreementItemSeqId") String agreementItemSeqId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("agreementId",agreementId);
		paramMap.put("agreementItemSeqId",agreementItemSeqId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAgreementEmploymentAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createJobInterviewType")
	public ResponseEntity<Object> createJobInterviewType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="jobInterviewTypeId", required=false) String jobInterviewTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("jobInterviewTypeId",jobInterviewTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createJobInterviewType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTerminationReason")
	public ResponseEntity<Object> createTerminationReason(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="terminationReasonId", required=false) String terminationReasonId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("terminationReasonId",terminationReasonId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTerminationReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPayHistory")
	public ResponseEntity<Object> createPayHistory(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="salaryStepSeqId", required=false) String salaryStepSeqId, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="payGradeId", required=false) String payGradeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("amount",amount);
		paramMap.put("comments",comments);
		paramMap.put("salaryStepSeqId",salaryStepSeqId);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPayHistory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createResponsibilityType")
	public ResponseEntity<Object> createResponsibilityType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="responsibilityTypeId", required=false) String responsibilityTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createResponsibilityType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmploymentApp")
	public ResponseEntity<Object> createEmploymentApp(HttpSession session, @RequestParam(value="applyingPartyId", required=false) String applyingPartyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="approverPartyId", required=false) String approverPartyId, @RequestParam(value="employmentAppSourceTypeId", required=false) String employmentAppSourceTypeId, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="applicationId", required=false) String applicationId, @RequestParam(value="referredByPartyId", required=false) String referredByPartyId, @RequestParam(value="jobRequisitionId", required=false) String jobRequisitionId, @RequestParam(value="applicationDate", required=false) Timestamp applicationDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applyingPartyId",applyingPartyId);
		paramMap.put("statusId",statusId);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("applicationId",applicationId);
		paramMap.put("referredByPartyId",referredByPartyId);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("applicationDate",applicationDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmploymentApp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateAgreementEmploymentAppl")
	public ResponseEntity<Object> updateAgreementEmploymentAppl(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="agreementId") String agreementId, @RequestParam(value="agreementItemSeqId") String agreementItemSeqId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="agreementDate", required=false) Timestamp agreementDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("agreementId",agreementId);
		paramMap.put("agreementItemSeqId",agreementItemSeqId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("agreementDate",agreementDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAgreementEmploymentAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplLeaveType")
	public ResponseEntity<Object> createEmplLeaveType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="leaveTypeId", required=false) String leaveTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("leaveTypeId",leaveTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplLeaveType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplLeave")
	public ResponseEntity<Object> createEmplLeave(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="approverPartyId") String approverPartyId, @RequestParam(value="leaveTypeId") String leaveTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate") Timestamp thruDate, @RequestParam(value="emplLeaveReasonTypeId", required=false) String emplLeaveReasonTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="leaveStatus", required=false) String leaveStatus) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("leaveTypeId",leaveTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("emplLeaveReasonTypeId",emplLeaveReasonTypeId);
		paramMap.put("description",description);
		paramMap.put("leaveStatus",leaveStatus);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplLeave", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getCurrentPartyEmploymentData")
	public ResponseEntity<Object> getCurrentPartyEmploymentData(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getCurrentPartyEmploymentData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteResponsibilityType")
	public ResponseEntity<Object> deleteResponsibilityType(HttpSession session, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteResponsibilityType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createJobRequisition")
	public ResponseEntity<Object> createJobRequisition(HttpSession session, @RequestParam(value="qualification") String qualification, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="experienceMonths") Long experienceMonths, @RequestParam(value="jobLocation") String jobLocation, @RequestParam(value="noOfResources") Long noOfResources, @RequestParam(value="experienceYears") Long experienceYears, @RequestParam(value="durationMonths", required=false) Long durationMonths, @RequestParam(value="gender", required=false) Boolean gender, @RequestParam(value="jobRequisitionId", required=false) String jobRequisitionId, @RequestParam(value="examTypeEnumId", required=false) String examTypeEnumId, @RequestParam(value="jobRequisitionDate", required=false) Timestamp jobRequisitionDate, @RequestParam(value="requiredOnDate", required=false) Timestamp requiredOnDate, @RequestParam(value="age", required=false) Long age, @RequestParam(value="jobPostingTypeEnumId", required=false) String jobPostingTypeEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("qualification",qualification);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("experienceMonths",experienceMonths);
		paramMap.put("jobLocation",jobLocation);
		paramMap.put("noOfResources",noOfResources);
		paramMap.put("experienceYears",experienceYears);
		paramMap.put("durationMonths",durationMonths);
		paramMap.put("gender",gender);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("examTypeEnumId",examTypeEnumId);
		paramMap.put("jobRequisitionDate",jobRequisitionDate);
		paramMap.put("requiredOnDate",requiredOnDate);
		paramMap.put("age",age);
		paramMap.put("jobPostingTypeEnumId",jobPostingTypeEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createJobRequisition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplLeaveReasonType")
	public ResponseEntity<Object> deleteEmplLeaveReasonType(HttpSession session, @RequestParam(value="emplLeaveReasonTypeId") String emplLeaveReasonTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplLeaveReasonTypeId",emplLeaveReasonTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplLeaveReasonType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createUnemploymentClaim")
	public ResponseEntity<Object> createUnemploymentClaim(HttpSession session, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="unemploymentClaimId", required=false) String unemploymentClaimId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="unemploymentClaimDate", required=false) Timestamp unemploymentClaimDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("unemploymentClaimId",unemploymentClaimId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("statusId",statusId);
		paramMap.put("description",description);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("unemploymentClaimDate",unemploymentClaimDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUnemploymentClaim", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplPositionResponsibility")
	public ResponseEntity<Object> deleteEmplPositionResponsibility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId, @RequestParam(value="emplPositionId") String emplPositionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplPositionResponsibility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPayrollPreference")
	public ResponseEntity<Object> createPayrollPreference(HttpSession session, @RequestParam(value="payrollPreferenceSeqId") String payrollPreferenceSeqId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="routingNumber", required=false) String routingNumber, @RequestParam(value="flatAmount", required=false) BigDecimal flatAmount, @RequestParam(value="percentage", required=false) BigDecimal percentage, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="deductionTypeId", required=false) String deductionTypeId, @RequestParam(value="bankName", required=false) String bankName, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="accountNumber", required=false) String accountNumber, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payrollPreferenceSeqId",payrollPreferenceSeqId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("routingNumber",routingNumber);
		paramMap.put("flatAmount",flatAmount);
		paramMap.put("percentage",percentage);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("deductionTypeId",deductionTypeId);
		paramMap.put("bankName",bankName);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPayrollPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInternalJobPosting")
	public ResponseEntity<Object> updateInternalJobPosting(HttpSession session, @RequestParam(value="applyingPartyId") String applyingPartyId, @RequestParam(value="approverPartyId") String approverPartyId, @RequestParam(value="applicationId") String applicationId, @RequestParam(value="jobRequisitionId") String jobRequisitionId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="employmentAppSourceTypeId", required=false) String employmentAppSourceTypeId, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="referredByPartyId", required=false) String referredByPartyId, @RequestParam(value="applicationDate", required=false) Timestamp applicationDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applyingPartyId",applyingPartyId);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("applicationId",applicationId);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("statusId",statusId);
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("referredByPartyId",referredByPartyId);
		paramMap.put("applicationDate",applicationDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInternalJobPosting", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePayrollPreference")
	public ResponseEntity<Object> deletePayrollPreference(HttpSession session, @RequestParam(value="payrollPreferenceSeqId") String payrollPreferenceSeqId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payrollPreferenceSeqId",payrollPreferenceSeqId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePayrollPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyQualType")
	public ResponseEntity<Object> deletePartyQualType(HttpSession session, @RequestParam(value="partyQualTypeId") String partyQualTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyQualTypeId",partyQualTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyQualType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartySkill")
	public ResponseEntity<Object> deletePartySkill(HttpSession session, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartySkill", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmploymentApp")
	public ResponseEntity<Object> deleteEmploymentApp(HttpSession session, @RequestParam(value="applicationId") String applicationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applicationId",applicationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmploymentApp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTerminationType")
	public ResponseEntity<Object> deleteTerminationType(HttpSession session, @RequestParam(value="terminationTypeId") String terminationTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("terminationTypeId",terminationTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTerminationType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplPositionTypeRate")
	public ResponseEntity<Object> deleteEmplPositionTypeRate(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="rateTypeId") String rateTypeId, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="rateAmountFromDate", required=false) Timestamp rateAmountFromDate, @RequestParam(value="periodTypeId", required=false) String periodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("rateTypeId",rateTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("rateAmountFromDate",rateAmountFromDate);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplPositionTypeRate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTrainingStatus")
	public ResponseEntity<Object> updateTrainingStatus(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="reason") String reason, @RequestParam(value="partyId") String partyId, @RequestParam(value="trainingClassTypeId") String trainingClassTypeId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="approvalStatus", required=false) String approvalStatus, @RequestParam(value="trainingRequestId", required=false) String trainingRequestId, @RequestParam(value="approverId", required=false) String approverId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("reason",reason);
		paramMap.put("partyId",partyId);
		paramMap.put("trainingClassTypeId",trainingClassTypeId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("approvalStatus",approvalStatus);
		paramMap.put("trainingRequestId",trainingRequestId);
		paramMap.put("approverId",approverId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTrainingStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInternalJobPosting")
	public ResponseEntity<Object> createInternalJobPosting(HttpSession session, @RequestParam(value="applyingPartyId") String applyingPartyId, @RequestParam(value="approverPartyId") String approverPartyId, @RequestParam(value="jobRequisitionId") String jobRequisitionId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="employmentAppSourceTypeId", required=false) String employmentAppSourceTypeId, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="applicationId", required=false) String applicationId, @RequestParam(value="referredByPartyId", required=false) String referredByPartyId, @RequestParam(value="applicationDate", required=false) Timestamp applicationDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applyingPartyId",applyingPartyId);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("statusId",statusId);
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("applicationId",applicationId);
		paramMap.put("referredByPartyId",referredByPartyId);
		paramMap.put("applicationDate",applicationDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInternalJobPosting", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyQualType")
	public ResponseEntity<Object> createPartyQualType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="partyQualTypeId", required=false) String partyQualTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("partyQualTypeId",partyQualTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyQualType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePerfReviewItem")
	public ResponseEntity<Object> deletePerfReviewItem(HttpSession session, @RequestParam(value="employeeRoleTypeId") String employeeRoleTypeId, @RequestParam(value="perfReviewItemSeqId") String perfReviewItemSeqId, @RequestParam(value="employeePartyId") String employeePartyId, @RequestParam(value="perfReviewId") String perfReviewId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employeeRoleTypeId",employeeRoleTypeId);
		paramMap.put("perfReviewItemSeqId",perfReviewItemSeqId);
		paramMap.put("employeePartyId",employeePartyId);
		paramMap.put("perfReviewId",perfReviewId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePerfReviewItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPerfReviewItem")
	public ResponseEntity<Object> createPerfReviewItem(HttpSession session, @RequestParam(value="employeeRoleTypeId") String employeeRoleTypeId, @RequestParam(value="employeePartyId") String employeePartyId, @RequestParam(value="perfReviewId") String perfReviewId, @RequestParam(value="perfReviewItemTypeId", required=false) String perfReviewItemTypeId, @RequestParam(value="perfRatingTypeId", required=false) String perfRatingTypeId, @RequestParam(value="comments", required=false) String comments) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employeeRoleTypeId",employeeRoleTypeId);
		paramMap.put("employeePartyId",employeePartyId);
		paramMap.put("perfReviewId",perfReviewId);
		paramMap.put("perfReviewItemTypeId",perfReviewItemTypeId);
		paramMap.put("perfRatingTypeId",perfRatingTypeId);
		paramMap.put("comments",comments);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPerfReviewItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPayGrade")
	public ResponseEntity<Object> createPayGrade(HttpSession session, @RequestParam(value="payGradeName") String payGradeName, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="payGradeId", required=false) String payGradeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payGradeName",payGradeName);
		paramMap.put("comments",comments);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPayGrade", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPositionType")
	public ResponseEntity<Object> updateEmplPositionType(HttpSession session, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPositionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplPositionType")
	public ResponseEntity<Object> deleteEmplPositionType(HttpSession session, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplPositionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyQual")
	public ResponseEntity<Object> createPartyQual(HttpSession session, @RequestParam(value="partyQualTypeId") String partyQualTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="qualificationDesc", required=false) String qualificationDesc, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="verifStatusId", required=false) String verifStatusId, @RequestParam(value="title", required=false) String title, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyQualTypeId",partyQualTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("qualificationDesc",qualificationDesc);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("verifStatusId",verifStatusId);
		paramMap.put("title",title);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyQual", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSkillType")
	public ResponseEntity<Object> deleteSkillType(HttpSession session, @RequestParam(value="skillTypeId") String skillTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSkillType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createValidResponsibility")
	public ResponseEntity<Object> createValidResponsibility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createValidResponsibility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPartySkill")
	public ResponseEntity<Object> createPartySkill(HttpSession session, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="rating", required=false) Long rating, @RequestParam(value="yearsExperience", required=false) Long yearsExperience, @RequestParam(value="startedUsingDate", required=false) Timestamp startedUsingDate, @RequestParam(value="skillLevel", required=false) Long skillLevel) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("rating",rating);
		paramMap.put("yearsExperience",yearsExperience);
		paramMap.put("startedUsingDate",startedUsingDate);
		paramMap.put("skillLevel",skillLevel);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartySkill", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyBenefit")
	public ResponseEntity<Object> deletePartyBenefit(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="benefitTypeId") String benefitTypeId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("benefitTypeId",benefitTypeId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyBenefit", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmployment")
	public ResponseEntity<Object> deleteEmployment(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmployment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplPosition")
	public ResponseEntity<Object> createEmplPosition(HttpSession session, @RequestParam(value="estimatedThruDate", required=false) Timestamp estimatedThruDate, @RequestParam(value="actualThruDate", required=false) Timestamp actualThruDate, @RequestParam(value="exemptFlag", required=false) Boolean exemptFlag, @RequestParam(value="temporaryFlag", required=false) Boolean temporaryFlag, @RequestParam(value="fulltimeFlag", required=false) Boolean fulltimeFlag, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="actualFromDate", required=false) Timestamp actualFromDate, @RequestParam(value="budgetItemSeqId", required=false) String budgetItemSeqId, @RequestParam(value="budgetId", required=false) String budgetId, @RequestParam(value="salaryFlag", required=false) Boolean salaryFlag, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="estimatedFromDate", required=false) Timestamp estimatedFromDate, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("estimatedThruDate",estimatedThruDate);
		paramMap.put("actualThruDate",actualThruDate);
		paramMap.put("exemptFlag",exemptFlag);
		paramMap.put("temporaryFlag",temporaryFlag);
		paramMap.put("fulltimeFlag",fulltimeFlag);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("actualFromDate",actualFromDate);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("salaryFlag",salaryFlag);
		paramMap.put("statusId",statusId);
		paramMap.put("estimatedFromDate",estimatedFromDate);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplPosition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePayHistory")
	public ResponseEntity<Object> deletePayHistory(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePayHistory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePerfReview")
	public ResponseEntity<Object> deletePerfReview(HttpSession session, @RequestParam(value="employeeRoleTypeId") String employeeRoleTypeId, @RequestParam(value="employeePartyId") String employeePartyId, @RequestParam(value="perfReviewId") String perfReviewId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employeeRoleTypeId",employeeRoleTypeId);
		paramMap.put("employeePartyId",employeePartyId);
		paramMap.put("perfReviewId",perfReviewId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePerfReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalaryStep")
	public ResponseEntity<Object> createSalaryStep(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="salaryStepSeqId") String salaryStepSeqId, @RequestParam(value="payGradeId") String payGradeId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dateModified", required=false) Timestamp dateModified, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("salaryStepSeqId",salaryStepSeqId);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("amount",amount);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dateModified",dateModified);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalaryStep", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createJobInterview")
	public ResponseEntity<Object> createJobInterview(HttpSession session, @RequestParam(value="jobIntervieweePartyId") String jobIntervieweePartyId, @RequestParam(value="jobInterviewerPartyId") String jobInterviewerPartyId, @RequestParam(value="jobRequisitionId") String jobRequisitionId, @RequestParam(value="jobInterviewId", required=false) String jobInterviewId, @RequestParam(value="jobInterviewTypeId", required=false) String jobInterviewTypeId, @RequestParam(value="gradeSecuredEnumId", required=false) String gradeSecuredEnumId, @RequestParam(value="jobInterviewDate", required=false) Timestamp jobInterviewDate, @RequestParam(value="jobInterviewResult", required=false) String jobInterviewResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobIntervieweePartyId",jobIntervieweePartyId);
		paramMap.put("jobInterviewerPartyId",jobInterviewerPartyId);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("jobInterviewId",jobInterviewId);
		paramMap.put("jobInterviewTypeId",jobInterviewTypeId);
		paramMap.put("gradeSecuredEnumId",gradeSecuredEnumId);
		paramMap.put("jobInterviewDate",jobInterviewDate);
		paramMap.put("jobInterviewResult",jobInterviewResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createJobInterview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSalaryStep")
	public ResponseEntity<Object> updateSalaryStep(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="salaryStepSeqId") String salaryStepSeqId, @RequestParam(value="payGradeId") String payGradeId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dateModified", required=false) Timestamp dateModified, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("salaryStepSeqId",salaryStepSeqId);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("amount",amount);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dateModified",dateModified);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSalaryStep", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPositionReportingStruct")
	public ResponseEntity<Object> updateEmplPositionReportingStruct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionIdManagedBy") String emplPositionIdManagedBy, @RequestParam(value="emplPositionIdReportingTo") String emplPositionIdReportingTo, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="primaryFlag", required=false) Boolean primaryFlag, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionIdManagedBy",emplPositionIdManagedBy);
		paramMap.put("emplPositionIdReportingTo",emplPositionIdReportingTo);
		paramMap.put("comments",comments);
		paramMap.put("primaryFlag",primaryFlag);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPositionReportingStruct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPosition")
	public ResponseEntity<Object> updateEmplPosition(HttpSession session, @RequestParam(value="emplPositionId") String emplPositionId, @RequestParam(value="estimatedThruDate", required=false) Timestamp estimatedThruDate, @RequestParam(value="actualThruDate", required=false) Timestamp actualThruDate, @RequestParam(value="exemptFlag", required=false) Boolean exemptFlag, @RequestParam(value="temporaryFlag", required=false) Boolean temporaryFlag, @RequestParam(value="fulltimeFlag", required=false) Boolean fulltimeFlag, @RequestParam(value="actualFromDate", required=false) Timestamp actualFromDate, @RequestParam(value="budgetItemSeqId", required=false) String budgetItemSeqId, @RequestParam(value="budgetId", required=false) String budgetId, @RequestParam(value="salaryFlag", required=false) Boolean salaryFlag, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="estimatedFromDate", required=false) Timestamp estimatedFromDate, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("estimatedThruDate",estimatedThruDate);
		paramMap.put("actualThruDate",actualThruDate);
		paramMap.put("exemptFlag",exemptFlag);
		paramMap.put("temporaryFlag",temporaryFlag);
		paramMap.put("fulltimeFlag",fulltimeFlag);
		paramMap.put("actualFromDate",actualFromDate);
		paramMap.put("budgetItemSeqId",budgetItemSeqId);
		paramMap.put("budgetId",budgetId);
		paramMap.put("salaryFlag",salaryFlag);
		paramMap.put("statusId",statusId);
		paramMap.put("estimatedFromDate",estimatedFromDate);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPosition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteJobRequisition")
	public ResponseEntity<Object> deleteJobRequisition(HttpSession session, @RequestParam(value="jobRequisitionId") String jobRequisitionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteJobRequisition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePerformanceNote")
	public ResponseEntity<Object> deletePerformanceNote(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePerformanceNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyBenefit")
	public ResponseEntity<Object> updatePartyBenefit(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="benefitTypeId") String benefitTypeId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="availableTime", required=false) Long availableTime, @RequestParam(value="cost", required=false) BigDecimal cost, @RequestParam(value="actualEmployerPaidPercent", required=false) BigDecimal actualEmployerPaidPercent, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("benefitTypeId",benefitTypeId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("availableTime",availableTime);
		paramMap.put("cost",cost);
		paramMap.put("actualEmployerPaidPercent",actualEmployerPaidPercent);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyBenefit", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplLeave")
	public ResponseEntity<Object> updateEmplLeave(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="approverPartyId") String approverPartyId, @RequestParam(value="leaveTypeId") String leaveTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate") Timestamp thruDate, @RequestParam(value="emplLeaveReasonTypeId", required=false) String emplLeaveReasonTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="leaveStatus", required=false) String leaveStatus) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("leaveTypeId",leaveTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("emplLeaveReasonTypeId",emplLeaveReasonTypeId);
		paramMap.put("description",description);
		paramMap.put("leaveStatus",leaveStatus);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplLeave", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPositionResponsibility")
	public ResponseEntity<Object> updateEmplPositionResponsibility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId, @RequestParam(value="emplPositionId") String emplPositionId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPositionResponsibility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUnemploymentClaim")
	public ResponseEntity<Object> deleteUnemploymentClaim(HttpSession session, @RequestParam(value="unemploymentClaimId") String unemploymentClaimId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("unemploymentClaimId",unemploymentClaimId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteUnemploymentClaim", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateJobInterviewType")
	public ResponseEntity<Object> updateJobInterviewType(HttpSession session, @RequestParam(value="jobInterviewTypeId") String jobInterviewTypeId, @RequestParam(value="description") String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobInterviewTypeId",jobInterviewTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateJobInterviewType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmploymentApp")
	public ResponseEntity<Object> updateEmploymentApp(HttpSession session, @RequestParam(value="applicationId") String applicationId, @RequestParam(value="applyingPartyId", required=false) String applyingPartyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="approverPartyId", required=false) String approverPartyId, @RequestParam(value="employmentAppSourceTypeId", required=false) String employmentAppSourceTypeId, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="referredByPartyId", required=false) String referredByPartyId, @RequestParam(value="jobRequisitionId", required=false) String jobRequisitionId, @RequestParam(value="applicationDate", required=false) Timestamp applicationDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applicationId",applicationId);
		paramMap.put("applyingPartyId",applyingPartyId);
		paramMap.put("statusId",statusId);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("referredByPartyId",referredByPartyId);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("applicationDate",applicationDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmploymentApp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplLeaveType")
	public ResponseEntity<Object> updateEmplLeaveType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="leaveTypeId") String leaveTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("leaveTypeId",leaveTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplLeaveType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/assignTraining")
	public ResponseEntity<Object> assignTraining(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="approverId") String approverId, @RequestParam(value="partyId") String partyId, @RequestParam(value="trainingClassTypeId") String trainingClassTypeId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="approvalStatus", required=false) String approvalStatus, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="trainingRequestId", required=false) String trainingRequestId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("approverId",approverId);
		paramMap.put("partyId",partyId);
		paramMap.put("trainingClassTypeId",trainingClassTypeId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("approvalStatus",approvalStatus);
		paramMap.put("reason",reason);
		paramMap.put("trainingRequestId",trainingRequestId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("assignTraining", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTrainingTypes")
	public ResponseEntity<Object> deleteTrainingTypes(HttpSession session, @RequestParam(value="trainingClassTypeId") String trainingClassTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("trainingClassTypeId",trainingClassTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTrainingTypes", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTerminationType")
	public ResponseEntity<Object> updateTerminationType(HttpSession session, @RequestParam(value="terminationTypeId") String terminationTypeId, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("terminationTypeId",terminationTypeId);
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTerminationType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplLeaveStatus")
	public ResponseEntity<Object> updateEmplLeaveStatus(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="leaveTypeId") String leaveTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="approverPartyId", required=false) String approverPartyId, @RequestParam(value="emplLeaveReasonTypeId", required=false) String emplLeaveReasonTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="leaveStatus", required=false) String leaveStatus, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("leaveTypeId",leaveTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("emplLeaveReasonTypeId",emplLeaveReasonTypeId);
		paramMap.put("description",description);
		paramMap.put("leaveStatus",leaveStatus);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplLeaveStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplPositionResponsibility")
	public ResponseEntity<Object> createEmplPositionResponsibility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId, @RequestParam(value="emplPositionId") String emplPositionId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplPositionResponsibility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplPositionReportingStruct")
	public ResponseEntity<Object> createEmplPositionReportingStruct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionIdManagedBy") String emplPositionIdManagedBy, @RequestParam(value="emplPositionIdReportingTo") String emplPositionIdReportingTo, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="primaryFlag", required=false) Boolean primaryFlag, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionIdManagedBy",emplPositionIdManagedBy);
		paramMap.put("emplPositionIdReportingTo",emplPositionIdReportingTo);
		paramMap.put("comments",comments);
		paramMap.put("primaryFlag",primaryFlag);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplPositionReportingStruct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateJobRequisition")
	public ResponseEntity<Object> updateJobRequisition(HttpSession session, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="experienceMonths") Long experienceMonths, @RequestParam(value="jobLocation") String jobLocation, @RequestParam(value="noOfResources") Long noOfResources, @RequestParam(value="jobRequisitionId") String jobRequisitionId, @RequestParam(value="experienceYears") Long experienceYears, @RequestParam(value="qualification", required=false) String qualification, @RequestParam(value="durationMonths", required=false) Long durationMonths, @RequestParam(value="gender", required=false) Boolean gender, @RequestParam(value="examTypeEnumId", required=false) String examTypeEnumId, @RequestParam(value="jobRequisitionDate", required=false) Timestamp jobRequisitionDate, @RequestParam(value="requiredOnDate", required=false) Timestamp requiredOnDate, @RequestParam(value="age", required=false) Long age, @RequestParam(value="jobPostingTypeEnumId", required=false) String jobPostingTypeEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("experienceMonths",experienceMonths);
		paramMap.put("jobLocation",jobLocation);
		paramMap.put("noOfResources",noOfResources);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("experienceYears",experienceYears);
		paramMap.put("qualification",qualification);
		paramMap.put("durationMonths",durationMonths);
		paramMap.put("gender",gender);
		paramMap.put("examTypeEnumId",examTypeEnumId);
		paramMap.put("jobRequisitionDate",jobRequisitionDate);
		paramMap.put("requiredOnDate",requiredOnDate);
		paramMap.put("age",age);
		paramMap.put("jobPostingTypeEnumId",jobPostingTypeEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateJobRequisition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyQual")
	public ResponseEntity<Object> deletePartyQual(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyQualTypeId") String partyQualTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyQualTypeId",partyQualTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyQual", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/applyTraining")
	public ResponseEntity<Object> applyTraining(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="approverId") String approverId, @RequestParam(value="partyId") String partyId, @RequestParam(value="trainingClassTypeId") String trainingClassTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="approvalStatus", required=false) String approvalStatus, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="trainingRequestId", required=false) String trainingRequestId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("approverId",approverId);
		paramMap.put("partyId",partyId);
		paramMap.put("trainingClassTypeId",trainingClassTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("approvalStatus",approvalStatus);
		paramMap.put("reason",reason);
		paramMap.put("trainingRequestId",trainingRequestId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("applyTraining", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplPosition")
	public ResponseEntity<Object> deleteEmplPosition(HttpSession session, @RequestParam(value="emplPositionId") String emplPositionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplPosition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyResume")
	public ResponseEntity<Object> deletePartyResume(HttpSession session, @RequestParam(value="resumeId") String resumeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resumeId",resumeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyResume", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplLeaveReasonType")
	public ResponseEntity<Object> updateEmplLeaveReasonType(HttpSession session, @RequestParam(value="emplLeaveReasonTypeId") String emplLeaveReasonTypeId, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplLeaveReasonTypeId",emplLeaveReasonTypeId);
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplLeaveReasonType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePayGrade")
	public ResponseEntity<Object> updatePayGrade(HttpSession session, @RequestParam(value="payGradeName") String payGradeName, @RequestParam(value="payGradeId") String payGradeId, @RequestParam(value="comments", required=false) String comments) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payGradeName",payGradeName);
		paramMap.put("payGradeId",payGradeId);
		paramMap.put("comments",comments);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePayGrade", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateResponsibilityType")
	public ResponseEntity<Object> updateResponsibilityType(HttpSession session, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateResponsibilityType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/humanResManagerPermission")
	public ResponseEntity<Object> humanResManagerPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("humanResManagerPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyResume")
	public ResponseEntity<Object> updatePartyResume(HttpSession session, @RequestParam(value="resumeId") String resumeId, @RequestParam(value="resumeText", required=false) String resumeText, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="resumeDate", required=false) Timestamp resumeDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resumeId",resumeId);
		paramMap.put("resumeText",resumeText);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("resumeDate",resumeDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyResume", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteValidResponsibility")
	public ResponseEntity<Object> deleteValidResponsibility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteValidResponsibility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSkillType")
	public ResponseEntity<Object> createSkillType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="skillTypeId", required=false) String skillTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSkillType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteJobInterview")
	public ResponseEntity<Object> deleteJobInterview(HttpSession session, @RequestParam(value="jobInterviewId") String jobInterviewId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobInterviewId",jobInterviewId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteJobInterview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplLeaveType")
	public ResponseEntity<Object> deleteEmplLeaveType(HttpSession session, @RequestParam(value="leaveTypeId") String leaveTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("leaveTypeId",leaveTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplLeaveType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartySkill")
	public ResponseEntity<Object> updatePartySkill(HttpSession session, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="rating", required=false) Long rating, @RequestParam(value="yearsExperience", required=false) Long yearsExperience, @RequestParam(value="startedUsingDate", required=false) Timestamp startedUsingDate, @RequestParam(value="skillLevel", required=false) Long skillLevel) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("rating",rating);
		paramMap.put("yearsExperience",yearsExperience);
		paramMap.put("startedUsingDate",startedUsingDate);
		paramMap.put("skillLevel",skillLevel);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartySkill", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateUnemploymentClaim")
	public ResponseEntity<Object> updateUnemploymentClaim(HttpSession session, @RequestParam(value="unemploymentClaimId") String unemploymentClaimId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="description", required=false) String description, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="unemploymentClaimDate", required=false) Timestamp unemploymentClaimDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("unemploymentClaimId",unemploymentClaimId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("statusId",statusId);
		paramMap.put("description",description);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("unemploymentClaimDate",unemploymentClaimDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUnemploymentClaim", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplPositionType")
	public ResponseEntity<Object> createEmplPositionType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="emplPositionTypeId", required=false) String emplPositionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplPositionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTerminationType")
	public ResponseEntity<Object> createTerminationType(HttpSession session, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="terminationTypeId", required=false) String terminationTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("terminationTypeId",terminationTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTerminationType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplPositionFulfillment")
	public ResponseEntity<Object> deleteEmplPositionFulfillment(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionId") String emplPositionId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplPositionFulfillment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmployment")
	public ResponseEntity<Object> createEmployment(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="terminationReasonId", required=false) String terminationReasonId, @RequestParam(value="terminationTypeId", required=false) String terminationTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("terminationReasonId",terminationReasonId);
		paramMap.put("terminationTypeId",terminationTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmployment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyBenefit")
	public ResponseEntity<Object> createPartyBenefit(HttpSession session, @RequestParam(value="roleTypeIdTo") String roleTypeIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyIdFrom") String partyIdFrom, @RequestParam(value="benefitTypeId") String benefitTypeId, @RequestParam(value="partyIdTo") String partyIdTo, @RequestParam(value="roleTypeIdFrom") String roleTypeIdFrom, @RequestParam(value="availableTime", required=false) Long availableTime, @RequestParam(value="cost", required=false) BigDecimal cost, @RequestParam(value="actualEmployerPaidPercent", required=false) BigDecimal actualEmployerPaidPercent, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("benefitTypeId",benefitTypeId);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("availableTime",availableTime);
		paramMap.put("cost",cost);
		paramMap.put("actualEmployerPaidPercent",actualEmployerPaidPercent);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyBenefit", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyQual")
	public ResponseEntity<Object> updatePartyQual(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyQualTypeId") String partyQualTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="qualificationDesc", required=false) String qualificationDesc, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="verifStatusId", required=false) String verifStatusId, @RequestParam(value="title", required=false) String title, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyQualTypeId",partyQualTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("qualificationDesc",qualificationDesc);
		paramMap.put("statusId",statusId);
		paramMap.put("verifStatusId",verifStatusId);
		paramMap.put("title",title);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyQual", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyQualType")
	public ResponseEntity<Object> updatePartyQualType(HttpSession session, @RequestParam(value="partyQualTypeId") String partyQualTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyQualTypeId",partyQualTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyQualType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmployee")
	public ResponseEntity<Object> createEmployee(HttpSession session, @RequestParam(value="postalAddContactMechPurpTypeId") String postalAddContactMechPurpTypeId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="emailAddress", required=false) String emailAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("postalAddContactMechPurpTypeId",postalAddContactMechPurpTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmployee", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSkillType")
	public ResponseEntity<Object> updateSkillType(HttpSession session, @RequestParam(value="skillTypeId") String skillTypeId, @RequestParam(value="description") String description, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("skillTypeId",skillTypeId);
		paramMap.put("description",description);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSkillType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplLeave")
	public ResponseEntity<Object> deleteEmplLeave(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="leaveTypeId") String leaveTypeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("leaveTypeId",leaveTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplLeave", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateValidResponsibility")
	public ResponseEntity<Object> updateValidResponsibility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="responsibilityTypeId") String responsibilityTypeId, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("responsibilityTypeId",responsibilityTypeId);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateValidResponsibility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPerformanceNote")
	public ResponseEntity<Object> createPerformanceNote(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="communicationDate", required=false) Timestamp communicationDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("comments",comments);
		paramMap.put("communicationDate",communicationDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPerformanceNote", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePerfReviewItem")
	public ResponseEntity<Object> updatePerfReviewItem(HttpSession session, @RequestParam(value="employeeRoleTypeId") String employeeRoleTypeId, @RequestParam(value="perfReviewItemSeqId") String perfReviewItemSeqId, @RequestParam(value="employeePartyId") String employeePartyId, @RequestParam(value="perfReviewId") String perfReviewId, @RequestParam(value="perfReviewItemTypeId", required=false) String perfReviewItemTypeId, @RequestParam(value="perfRatingTypeId", required=false) String perfRatingTypeId, @RequestParam(value="comments", required=false) String comments) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("employeeRoleTypeId",employeeRoleTypeId);
		paramMap.put("perfReviewItemSeqId",perfReviewItemSeqId);
		paramMap.put("employeePartyId",employeePartyId);
		paramMap.put("perfReviewId",perfReviewId);
		paramMap.put("perfReviewItemTypeId",perfReviewItemTypeId);
		paramMap.put("perfRatingTypeId",perfRatingTypeId);
		paramMap.put("comments",comments);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePerfReviewItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInternalJobPosting")
	public ResponseEntity<Object> deleteInternalJobPosting(HttpSession session, @RequestParam(value="applicationId") String applicationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applicationId",applicationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInternalJobPosting", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateJobInterview")
	public ResponseEntity<Object> updateJobInterview(HttpSession session, @RequestParam(value="jobInterviewId") String jobInterviewId, @RequestParam(value="jobInterviewTypeId") String jobInterviewTypeId, @RequestParam(value="jobIntervieweePartyId") String jobIntervieweePartyId, @RequestParam(value="jobRequisitionId") String jobRequisitionId, @RequestParam(value="gradeSecuredEnumId", required=false) String gradeSecuredEnumId, @RequestParam(value="jobInterviewDate", required=false) Timestamp jobInterviewDate, @RequestParam(value="jobInterviewerPartyId", required=false) String jobInterviewerPartyId, @RequestParam(value="jobInterviewResult", required=false) String jobInterviewResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobInterviewId",jobInterviewId);
		paramMap.put("jobInterviewTypeId",jobInterviewTypeId);
		paramMap.put("jobIntervieweePartyId",jobIntervieweePartyId);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("gradeSecuredEnumId",gradeSecuredEnumId);
		paramMap.put("jobInterviewDate",jobInterviewDate);
		paramMap.put("jobInterviewerPartyId",jobInterviewerPartyId);
		paramMap.put("jobInterviewResult",jobInterviewResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateJobInterview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePayrollPreference")
	public ResponseEntity<Object> updatePayrollPreference(HttpSession session, @RequestParam(value="payrollPreferenceSeqId") String payrollPreferenceSeqId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="routingNumber", required=false) String routingNumber, @RequestParam(value="flatAmount", required=false) BigDecimal flatAmount, @RequestParam(value="percentage", required=false) BigDecimal percentage, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="deductionTypeId", required=false) String deductionTypeId, @RequestParam(value="bankName", required=false) String bankName, @RequestParam(value="paymentMethodTypeId", required=false) String paymentMethodTypeId, @RequestParam(value="accountNumber", required=false) String accountNumber, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payrollPreferenceSeqId",payrollPreferenceSeqId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("routingNumber",routingNumber);
		paramMap.put("flatAmount",flatAmount);
		paramMap.put("percentage",percentage);
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("deductionTypeId",deductionTypeId);
		paramMap.put("bankName",bankName);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("accountNumber",accountNumber);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePayrollPreference", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTerminationReason")
	public ResponseEntity<Object> deleteTerminationReason(HttpSession session, @RequestParam(value="terminationReasonId") String terminationReasonId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("terminationReasonId",terminationReasonId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTerminationReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateApprovalStatus")
	public ResponseEntity<Object> updateApprovalStatus(HttpSession session, @RequestParam(value="applicationId") String applicationId, @RequestParam(value="applyingPartyId", required=false) String applyingPartyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="approverPartyId", required=false) String approverPartyId, @RequestParam(value="employmentAppSourceTypeId", required=false) String employmentAppSourceTypeId, @RequestParam(value="emplPositionId", required=false) String emplPositionId, @RequestParam(value="referredByPartyId", required=false) String referredByPartyId, @RequestParam(value="jobRequisitionId", required=false) String jobRequisitionId, @RequestParam(value="applicationDate", required=false) Timestamp applicationDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applicationId",applicationId);
		paramMap.put("applyingPartyId",applyingPartyId);
		paramMap.put("statusId",statusId);
		paramMap.put("approverPartyId",approverPartyId);
		paramMap.put("employmentAppSourceTypeId",employmentAppSourceTypeId);
		paramMap.put("emplPositionId",emplPositionId);
		paramMap.put("referredByPartyId",referredByPartyId);
		paramMap.put("jobRequisitionId",jobRequisitionId);
		paramMap.put("applicationDate",applicationDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateApprovalStatus", paramMap);
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
