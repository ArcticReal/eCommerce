package com.skytala.eCommerce.service.service;

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
@RequestMapping("/service/s")
public class sServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createCatalinaSession")
	public ResponseEntity<Map<String, Object>> createCatalinaSession(HttpSession session, @RequestParam(value="sessionInfo", required=false) java.nio.ByteBuffer sessionInfo, @RequestParam(value="maxIdle", required=false) Long maxIdle, @RequestParam(value="isValid", required=false) String isValid, @RequestParam(value="lastAccessed", required=false) Long lastAccessed, @RequestParam(value="sessionId", required=false) String sessionId, @RequestParam(value="sessionSize", required=false) Long sessionSize) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sessionInfo",sessionInfo);
		paramMap.put("maxIdle",maxIdle);
		paramMap.put("isValid",isValid);
		paramMap.put("lastAccessed",lastAccessed);
		paramMap.put("sessionId",sessionId);
		paramMap.put("sessionSize",sessionSize);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCatalinaSession", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCatalinaSession")
	public ResponseEntity<Map<String, Object>> updateCatalinaSession(HttpSession session, @RequestParam(value="sessionId") String sessionId, @RequestParam(value="sessionInfo", required=false) java.nio.ByteBuffer sessionInfo, @RequestParam(value="maxIdle", required=false) Long maxIdle, @RequestParam(value="isValid", required=false) String isValid, @RequestParam(value="lastAccessed", required=false) Long lastAccessed, @RequestParam(value="sessionSize", required=false) Long sessionSize) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sessionId",sessionId);
		paramMap.put("sessionInfo",sessionInfo);
		paramMap.put("maxIdle",maxIdle);
		paramMap.put("isValid",isValid);
		paramMap.put("lastAccessed",lastAccessed);
		paramMap.put("sessionSize",sessionSize);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCatalinaSession", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelJobRetries")
	public ResponseEntity<Map<String, Object>> cancelJobRetries(HttpSession session, @RequestParam(value="jobId") String jobId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobId",jobId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelJobRetries", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createStandardLanguage")
	public ResponseEntity<Map<String, Object>> createStandardLanguage(HttpSession session, @RequestParam(value="langCode3t", required=false) String langCode3t, @RequestParam(value="langCode2", required=false) String langCode2, @RequestParam(value="standardLanguageId", required=false) String standardLanguageId, @RequestParam(value="langCode3b", required=false) String langCode3b, @RequestParam(value="langFamily", required=false) String langFamily, @RequestParam(value="langName", required=false) String langName, @RequestParam(value="langCharset", required=false) String langCharset) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("langCode3t",langCode3t);
		paramMap.put("langCode2",langCode2);
		paramMap.put("standardLanguageId",standardLanguageId);
		paramMap.put("langCode3b",langCode3b);
		paramMap.put("langFamily",langFamily);
		paramMap.put("langName",langName);
		paramMap.put("langCharset",langCharset);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createStandardLanguage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/effectiveDateEcaCondition")
	public ResponseEntity<Map<String, Object>> effectiveDateEcaCondition(HttpSession session, @RequestParam(value="serviceContext") Map serviceContext, @RequestParam(value="serviceName") String serviceName, @RequestParam(value="fromDate", required=false) java.sql.Timestamp fromDate, @RequestParam(value="thruDate", required=false) java.sql.Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("serviceContext",serviceContext);
		paramMap.put("serviceName",serviceName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("effectiveDateEcaCondition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelScheduledJob")
	public ResponseEntity<Map<String, Object>> cancelScheduledJob(HttpSession session, @RequestParam(value="jobId") String jobId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobId",jobId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelScheduledJob", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/resetScheduledJob")
	public ResponseEntity<Map<String, Object>> resetScheduledJob(HttpSession session, @RequestParam(value="jobId") String jobId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobId",jobId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resetScheduledJob", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSequenceValueItem")
	public ResponseEntity<Map<String, Object>> updateSequenceValueItem(HttpSession session, @RequestParam(value="seqName") String seqName, @RequestParam(value="seqId", required=false) Long seqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("seqName",seqName);
		paramMap.put("seqId",seqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSequenceValueItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/authenticationInterface")
	public ResponseEntity<Map<String, Object>> authenticationInterface(HttpSession session, @RequestParam(value="login.username") String loginusername, @RequestParam(value="login.password") String loginpassword, @RequestParam(value="visitId", required=false) String visitId, @RequestParam(value="isServiceAuth", required=false) Boolean isServiceAuth) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("login.username",loginusername);
		paramMap.put("login.password",loginpassword);
		paramMap.put("visitId",visitId);
		paramMap.put("isServiceAuth",isServiceAuth);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("authenticationInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateJobManagerLock")
	public ResponseEntity<Map<String, Object>> updateJobManagerLock(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="instanceId") String instanceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("instanceId",instanceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("comments",comments);
		paramMap.put("createdDate",createdDate);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateJobManagerLock", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createStatusItem")
	public ResponseEntity<Map<String, Object>> createStatusItem(HttpSession session, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="statusTypeId", required=false) String statusTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="sequenceId", required=false) String sequenceId, @RequestParam(value="statusCode", required=false) String statusCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("statusTypeId",statusTypeId);
		paramMap.put("description",description);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("statusCode",statusCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createStatusItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createRuntimeData")
	public ResponseEntity<Map<String, Object>> createRuntimeData(HttpSession session, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="runtimeInfo", required=false) String runtimeInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("runtimeInfo",runtimeInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRuntimeData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createJobSandbox")
	public ResponseEntity<Map<String, Object>> createJobSandbox(HttpSession session, @RequestParam(value="jobName", required=false) String jobName, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="runByInstanceId", required=false) String runByInstanceId, @RequestParam(value="maxRecurrenceCount", required=false) Long maxRecurrenceCount, @RequestParam(value="currentRecurrenceCount", required=false) Long currentRecurrenceCount, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="cancelDateTime", required=false) Timestamp cancelDateTime, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="authUserLoginId", required=false) String authUserLoginId, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="currentRetryCount", required=false) Long currentRetryCount, @RequestParam(value="runAsUser", required=false) String runAsUser, @RequestParam(value="finishDateTime", required=false) Timestamp finishDateTime, @RequestParam(value="jobId", required=false) String jobId, @RequestParam(value="maxRetry", required=false) Long maxRetry, @RequestParam(value="loaderName", required=false) String loaderName, @RequestParam(value="startDateTime", required=false) Timestamp startDateTime, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="previousJobId", required=false) String previousJobId, @RequestParam(value="parentJobId", required=false) String parentJobId, @RequestParam(value="jobResult", required=false) Long jobResult, @RequestParam(value="poolId", required=false) String poolId, @RequestParam(value="runTime", required=false) Timestamp runTime) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobName",jobName);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("runByInstanceId",runByInstanceId);
		paramMap.put("maxRecurrenceCount",maxRecurrenceCount);
		paramMap.put("currentRecurrenceCount",currentRecurrenceCount);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("cancelDateTime",cancelDateTime);
		paramMap.put("serviceName",serviceName);
		paramMap.put("authUserLoginId",authUserLoginId);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("currentRetryCount",currentRetryCount);
		paramMap.put("runAsUser",runAsUser);
		paramMap.put("finishDateTime",finishDateTime);
		paramMap.put("jobId",jobId);
		paramMap.put("maxRetry",maxRetry);
		paramMap.put("loaderName",loaderName);
		paramMap.put("startDateTime",startDateTime);
		paramMap.put("statusId",statusId);
		paramMap.put("previousJobId",previousJobId);
		paramMap.put("parentJobId",parentJobId);
		paramMap.put("jobResult",jobResult);
		paramMap.put("poolId",poolId);
		paramMap.put("runTime",runTime);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createJobSandbox", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/serviceStreamInterface")
	public ResponseEntity<Map<String, Object>> serviceStreamInterface(HttpSession session, @RequestParam(value="inputStream") java.io.InputStream inputStream, @RequestParam(value="outputStream") java.io.OutputStream outputStream) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputStream",inputStream);
		paramMap.put("outputStream",outputStream);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("serviceStreamInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateRuntimeData")
	public ResponseEntity<Map<String, Object>> updateRuntimeData(HttpSession session, @RequestParam(value="runtimeDataId") String runtimeDataId, @RequestParam(value="runtimeInfo", required=false) String runtimeInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("runtimeInfo",runtimeInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRuntimeData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateJobSandbox")
	public ResponseEntity<Map<String, Object>> updateJobSandbox(HttpSession session, @RequestParam(value="jobId") String jobId, @RequestParam(value="jobName", required=false) String jobName, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="runByInstanceId", required=false) String runByInstanceId, @RequestParam(value="maxRecurrenceCount", required=false) Long maxRecurrenceCount, @RequestParam(value="currentRecurrenceCount", required=false) Long currentRecurrenceCount, @RequestParam(value="tempExprId", required=false) String tempExprId, @RequestParam(value="cancelDateTime", required=false) Timestamp cancelDateTime, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="authUserLoginId", required=false) String authUserLoginId, @RequestParam(value="runtimeDataId", required=false) String runtimeDataId, @RequestParam(value="currentRetryCount", required=false) Long currentRetryCount, @RequestParam(value="runAsUser", required=false) String runAsUser, @RequestParam(value="finishDateTime", required=false) Timestamp finishDateTime, @RequestParam(value="maxRetry", required=false) Long maxRetry, @RequestParam(value="loaderName", required=false) String loaderName, @RequestParam(value="startDateTime", required=false) Timestamp startDateTime, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="previousJobId", required=false) String previousJobId, @RequestParam(value="parentJobId", required=false) String parentJobId, @RequestParam(value="jobResult", required=false) Long jobResult, @RequestParam(value="poolId", required=false) String poolId, @RequestParam(value="runTime", required=false) Timestamp runTime) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobId",jobId);
		paramMap.put("jobName",jobName);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("runByInstanceId",runByInstanceId);
		paramMap.put("maxRecurrenceCount",maxRecurrenceCount);
		paramMap.put("currentRecurrenceCount",currentRecurrenceCount);
		paramMap.put("tempExprId",tempExprId);
		paramMap.put("cancelDateTime",cancelDateTime);
		paramMap.put("serviceName",serviceName);
		paramMap.put("authUserLoginId",authUserLoginId);
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("currentRetryCount",currentRetryCount);
		paramMap.put("runAsUser",runAsUser);
		paramMap.put("finishDateTime",finishDateTime);
		paramMap.put("maxRetry",maxRetry);
		paramMap.put("loaderName",loaderName);
		paramMap.put("startDateTime",startDateTime);
		paramMap.put("statusId",statusId);
		paramMap.put("previousJobId",previousJobId);
		paramMap.put("parentJobId",parentJobId);
		paramMap.put("jobResult",jobResult);
		paramMap.put("poolId",poolId);
		paramMap.put("runTime",runTime);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateJobSandbox", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteJobSandbox")
	public ResponseEntity<Map<String, Object>> deleteJobSandbox(HttpSession session, @RequestParam(value="jobId") String jobId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("jobId",jobId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteJobSandbox", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCatalinaSession")
	public ResponseEntity<Map<String, Object>> deleteCatalinaSession(HttpSession session, @RequestParam(value="sessionId") String sessionId, @RequestParam(value="sessionInfo", required=false) java.nio.ByteBuffer sessionInfo, @RequestParam(value="maxIdle", required=false) Long maxIdle, @RequestParam(value="isValid", required=false) String isValid, @RequestParam(value="lastAccessed", required=false) Long lastAccessed, @RequestParam(value="sessionSize", required=false) Long sessionSize) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sessionId",sessionId);
		paramMap.put("sessionInfo",sessionInfo);
		paramMap.put("maxIdle",maxIdle);
		paramMap.put("isValid",isValid);
		paramMap.put("lastAccessed",lastAccessed);
		paramMap.put("sessionSize",sessionSize);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCatalinaSession", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createJobManagerLock")
	public ResponseEntity<Map<String, Object>> createJobManagerLock(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="instanceId") String instanceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("instanceId",instanceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("comments",comments);
		paramMap.put("createdDate",createdDate);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createJobManagerLock", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/serviceEcaConditionInterface")
	public ResponseEntity<Map<String, Object>> serviceEcaConditionInterface(HttpSession session, @RequestParam(value="serviceContext") Map serviceContext, @RequestParam(value="serviceName") String serviceName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("serviceContext",serviceContext);
		paramMap.put("serviceName",serviceName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("serviceEcaConditionInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/serviceMcaConditionInterface")
	public ResponseEntity<Map<String, Object>> serviceMcaConditionInterface(HttpSession session, @RequestParam(value="messageWrapper") org.apache.ofbiz.service.mail.MimeMessageWrapper messageWrapper) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("messageWrapper",messageWrapper);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("serviceMcaConditionInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteStandardLanguage")
	public ResponseEntity<Map<String, Object>> deleteStandardLanguage(HttpSession session, @RequestParam(value="standardLanguageId") String standardLanguageId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("standardLanguageId",standardLanguageId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteStandardLanguage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteStatusItem")
	public ResponseEntity<Map<String, Object>> deleteStatusItem(HttpSession session, @RequestParam(value="statusId") String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteStatusItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/mailProcessInterface")
	public ResponseEntity<Map<String, Object>> mailProcessInterface(HttpSession session, @RequestParam(value="messageWrapper") org.apache.ofbiz.service.mail.MimeMessageWrapper messageWrapper) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("messageWrapper",messageWrapper);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("mailProcessInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSequenceValueItem")
	public ResponseEntity<Map<String, Object>> deleteSequenceValueItem(HttpSession session, @RequestParam(value="seqName") String seqName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("seqName",seqName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSequenceValueItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateStatusItem")
	public ResponseEntity<Map<String, Object>> updateStatusItem(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="statusTypeId", required=false) String statusTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="sequenceId", required=false) String sequenceId, @RequestParam(value="statusCode", required=false) String statusCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("statusTypeId",statusTypeId);
		paramMap.put("description",description);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("statusCode",statusCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateStatusItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateStatusType")
	public ResponseEntity<Map<String, Object>> updateStatusType(HttpSession session, @RequestParam(value="statusTypeId") String statusTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusTypeId",statusTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateStatusType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteStatusType")
	public ResponseEntity<Map<String, Object>> deleteStatusType(HttpSession session, @RequestParam(value="statusTypeId") String statusTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusTypeId",statusTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteStatusType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRuntimeData")
	public ResponseEntity<Map<String, Object>> deleteRuntimeData(HttpSession session, @RequestParam(value="runtimeDataId") String runtimeDataId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("runtimeDataId",runtimeDataId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRuntimeData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/permissionInterface")
	public ResponseEntity<Map<String, Object>> permissionInterface(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("permissionInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateStandardLanguage")
	public ResponseEntity<Map<String, Object>> updateStandardLanguage(HttpSession session, @RequestParam(value="standardLanguageId") String standardLanguageId, @RequestParam(value="langCode3t", required=false) String langCode3t, @RequestParam(value="langCode2", required=false) String langCode2, @RequestParam(value="langCode3b", required=false) String langCode3b, @RequestParam(value="langFamily", required=false) String langFamily, @RequestParam(value="langName", required=false) String langName, @RequestParam(value="langCharset", required=false) String langCharset) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("standardLanguageId",standardLanguageId);
		paramMap.put("langCode3t",langCode3t);
		paramMap.put("langCode2",langCode2);
		paramMap.put("langCode3b",langCode3b);
		paramMap.put("langFamily",langFamily);
		paramMap.put("langName",langName);
		paramMap.put("langCharset",langCharset);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateStandardLanguage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSequenceValueItem")
	public ResponseEntity<Map<String, Object>> createSequenceValueItem(HttpSession session, @RequestParam(value="seqName", required=false) String seqName, @RequestParam(value="seqId", required=false) Long seqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("seqName",seqName);
		paramMap.put("seqId",seqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSequenceValueItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/purgeOldJobs")
	public ResponseEntity<Map<String, Object>> purgeOldJobs(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("purgeOldJobs", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createStatusType")
	public ResponseEntity<Map<String, Object>> createStatusType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="statusTypeId", required=false) String statusTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("statusTypeId",statusTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createStatusType", paramMap);
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
