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
@RequestMapping("/service/testSe")
public class TestSeServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoUpdateTestingStatus")
	public ResponseEntity<Map<String, Object>> testEntityAutoUpdateTestingStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="testingStatusId") String testingStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("testingStatusId",testingStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoUpdateTestingStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testXmlRpcAdd")
	public ResponseEntity<Map<String, Object>> testXmlRpcAdd(HttpSession session, @RequestParam(value="num1") Integer num1, @RequestParam(value="num2") Integer num2) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("num1",num1);
		paramMap.put("num2",num2);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testXmlRpcAdd", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceLockWaitTimeoutRetryCantRecoverWaiter")
	public ResponseEntity<Map<String, Object>> testServiceLockWaitTimeoutRetryCantRecoverWaiter(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceLockWaitTimeoutRetryCantRecoverWaiter", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceEcaGlobalEventExec")
	public ResponseEntity<Map<String, Object>> testServiceEcaGlobalEventExec(HttpSession session, @RequestParam(value="test") junit.framework.Test test, @RequestParam(value="testResult") junit.framework.TestResult testResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("test",test);
		paramMap.put("testResult",testResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceEcaGlobalEventExec", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceEcaGlobalEventExecToRollback")
	public ResponseEntity<Map<String, Object>> testServiceEcaGlobalEventExecToRollback(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceEcaGlobalEventExecToRollback", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoCreateTestingItemPkMissing")
	public ResponseEntity<Map<String, Object>> testEntityAutoCreateTestingItemPkMissing(HttpSession session, @RequestParam(value="testingId") String testingId, @RequestParam(value="testingHistory", required=false) String testingHistory) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingId",testingId);
		paramMap.put("testingHistory",testingHistory);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoCreateTestingItemPkMissing", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceLockWaitTimeoutRetryGrabber")
	public ResponseEntity<Map<String, Object>> testServiceLockWaitTimeoutRetryGrabber(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceLockWaitTimeoutRetryGrabber", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoCreateTestingPkPresent")
	public ResponseEntity<Map<String, Object>> testEntityAutoCreateTestingPkPresent(HttpSession session, @RequestParam(value="testingId") String testingId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="testingTypeId", required=false) String testingTypeId, @RequestParam(value="testingSize", required=false) Long testingSize, @RequestParam(value="description", required=false) String description, @RequestParam(value="testingDate", required=false) Timestamp testingDate, @RequestParam(value="testingName", required=false) String testingName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingId",testingId);
		paramMap.put("comments",comments);
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("testingSize",testingSize);
		paramMap.put("description",description);
		paramMap.put("testingDate",testingDate);
		paramMap.put("testingName",testingName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoCreateTestingPkPresent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceLockWaitTimeoutRetryCantRecover")
	public ResponseEntity<Map<String, Object>> testServiceLockWaitTimeoutRetryCantRecover(HttpSession session, @RequestParam(value="test") junit.framework.Test test, @RequestParam(value="testResult") junit.framework.TestResult testResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("test",test);
		paramMap.put("testResult",testResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceLockWaitTimeoutRetryCantRecover", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceOwnTxSubServiceAfterSetRollbackOnlyInParentErrorCatchWrapper")
	public ResponseEntity<Map<String, Object>> testServiceOwnTxSubServiceAfterSetRollbackOnlyInParentErrorCatchWrapper(HttpSession session, @RequestParam(value="test") junit.framework.Test test, @RequestParam(value="testResult") junit.framework.TestResult testResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("test",test);
		paramMap.put("testResult",testResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceOwnTxSubServiceAfterSetRollbackOnlyInParentErrorCatchWrapper", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoCreateTestingNodeMemberPkMissing")
	public ResponseEntity<Map<String, Object>> testEntityAutoCreateTestingNodeMemberPkMissing(HttpSession session, @RequestParam(value="testingId") String testingId, @RequestParam(value="testingNodeId") String testingNodeId, @RequestParam(value="extendFromDate", required=false) Timestamp extendFromDate, @RequestParam(value="extendThruDate", required=false) Timestamp extendThruDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingId",testingId);
		paramMap.put("testingNodeId",testingNodeId);
		paramMap.put("extendFromDate",extendFromDate);
		paramMap.put("extendThruDate",extendThruDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoCreateTestingNodeMemberPkMissing", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceEcaGlobalEventExecOnCommit")
	public ResponseEntity<Map<String, Object>> testServiceEcaGlobalEventExecOnCommit(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceEcaGlobalEventExecOnCommit", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoCreateTestingItemPkPresent")
	public ResponseEntity<Map<String, Object>> testEntityAutoCreateTestingItemPkPresent(HttpSession session, @RequestParam(value="testingId") String testingId, @RequestParam(value="testingSeqId") String testingSeqId, @RequestParam(value="testingHistory", required=false) String testingHistory) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingId",testingId);
		paramMap.put("testingSeqId",testingSeqId);
		paramMap.put("testingHistory",testingHistory);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoCreateTestingItemPkPresent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoExpireTestingNodeMember")
	public ResponseEntity<Map<String, Object>> testEntityAutoExpireTestingNodeMember(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="testingId") String testingId, @RequestParam(value="testingNodeId") String testingNodeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("testingId",testingId);
		paramMap.put("testingNodeId",testingNodeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoExpireTestingNodeMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceOwnTxSubServiceAfterSetRollbackOnlyInParentSubService")
	public ResponseEntity<Map<String, Object>> testServiceOwnTxSubServiceAfterSetRollbackOnlyInParentSubService(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceOwnTxSubServiceAfterSetRollbackOnlyInParentSubService", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceDeadLockRetryThreadB")
	public ResponseEntity<Map<String, Object>> testServiceDeadLockRetryThreadB(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceDeadLockRetryThreadB", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceDeadLockRetryThreadA")
	public ResponseEntity<Map<String, Object>> testServiceDeadLockRetryThreadA(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceDeadLockRetryThreadA", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceLockWaitTimeoutRetry")
	public ResponseEntity<Map<String, Object>> testServiceLockWaitTimeoutRetry(HttpSession session, @RequestParam(value="test") junit.framework.Test test, @RequestParam(value="testResult") junit.framework.TestResult testResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("test",test);
		paramMap.put("testResult",testResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceLockWaitTimeoutRetry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoUpdateTesting")
	public ResponseEntity<Map<String, Object>> testEntityAutoUpdateTesting(HttpSession session, @RequestParam(value="testingId") String testingId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="testingTypeId", required=false) String testingTypeId, @RequestParam(value="testingSize", required=false) Long testingSize, @RequestParam(value="description", required=false) String description, @RequestParam(value="testingDate", required=false) Timestamp testingDate, @RequestParam(value="testingName", required=false) String testingName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingId",testingId);
		paramMap.put("comments",comments);
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("testingSize",testingSize);
		paramMap.put("description",description);
		paramMap.put("testingDate",testingDate);
		paramMap.put("testingName",testingName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoUpdateTesting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoRemoveTesting")
	public ResponseEntity<Map<String, Object>> testEntityAutoRemoveTesting(HttpSession session, @RequestParam(value="testingId") String testingId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingId",testingId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoRemoveTesting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceLockWaitTimeoutRetryWaiter")
	public ResponseEntity<Map<String, Object>> testServiceLockWaitTimeoutRetryWaiter(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceLockWaitTimeoutRetryWaiter", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoCreateTestingStatus")
	public ResponseEntity<Map<String, Object>> testEntityAutoCreateTestingStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="testingId") String testingId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("testingId",testingId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoCreateTestingStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoDeleteTestingStatus")
	public ResponseEntity<Map<String, Object>> testEntityAutoDeleteTestingStatus(HttpSession session, @RequestParam(value="testingStatusId") String testingStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingStatusId",testingStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoDeleteTestingStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceEcaGlobalEventExecOnRollback")
	public ResponseEntity<Map<String, Object>> testServiceEcaGlobalEventExecOnRollback(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceEcaGlobalEventExecOnRollback", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoCreateTestingPkMissing")
	public ResponseEntity<Map<String, Object>> testEntityAutoCreateTestingPkMissing(HttpSession session, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="testingTypeId", required=false) String testingTypeId, @RequestParam(value="testingSize", required=false) Long testingSize, @RequestParam(value="description", required=false) String description, @RequestParam(value="testingDate", required=false) Timestamp testingDate, @RequestParam(value="testingName", required=false) String testingName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comments",comments);
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("testingSize",testingSize);
		paramMap.put("description",description);
		paramMap.put("testingDate",testingDate);
		paramMap.put("testingName",testingName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoCreateTestingPkMissing", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoCreateTestingNodeMemberPkPresent")
	public ResponseEntity<Map<String, Object>> testEntityAutoCreateTestingNodeMemberPkPresent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="testingId") String testingId, @RequestParam(value="testingNodeId") String testingNodeId, @RequestParam(value="extendFromDate", required=false) Timestamp extendFromDate, @RequestParam(value="extendThruDate", required=false) Timestamp extendThruDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("testingId",testingId);
		paramMap.put("testingNodeId",testingNodeId);
		paramMap.put("extendFromDate",extendFromDate);
		paramMap.put("extendThruDate",extendThruDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoCreateTestingNodeMemberPkPresent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testXmlRpcClientAdd")
	public ResponseEntity<Map<String, Object>> testXmlRpcClientAdd(HttpSession session, @RequestParam(value="test") junit.framework.Test test, @RequestParam(value="testResult") junit.framework.TestResult testResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("test",test);
		paramMap.put("testResult",testResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testXmlRpcClientAdd", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceOwnTxSubServiceAfterSetRollbackOnlyInParent")
	public ResponseEntity<Map<String, Object>> testServiceOwnTxSubServiceAfterSetRollbackOnlyInParent(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceOwnTxSubServiceAfterSetRollbackOnlyInParent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testServiceDeadLockRetry")
	public ResponseEntity<Map<String, Object>> testServiceDeadLockRetry(HttpSession session, @RequestParam(value="test") junit.framework.Test test, @RequestParam(value="testResult") junit.framework.TestResult testResult) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("test",test);
		paramMap.put("testResult",testResult);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testServiceDeadLockRetry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testEntityAutoExpireTestFieldType")
	public ResponseEntity<Map<String, Object>> testEntityAutoExpireTestFieldType(HttpSession session, @RequestParam(value="testFieldTypeId") String testFieldTypeId, @RequestParam(value="dateTimeField", required=false) Timestamp dateTimeField) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testFieldTypeId",testFieldTypeId);
		paramMap.put("dateTimeField",dateTimeField);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testEntityAutoExpireTestFieldType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testXmlRpcLocalEngine")
	public ResponseEntity<Map<String, Object>> testXmlRpcLocalEngine(HttpSession session, @RequestParam(value="num1") Integer num1, @RequestParam(value="num2") Integer num2) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("num1",num1);
		paramMap.put("num2",num2);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("testXmlRpcLocalEngine", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
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
