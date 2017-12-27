package com.skytala.eCommerce.service.common;

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
@RequestMapping("/service/commonSecurity")
public class CommonSecurityServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/removeUserLoginToSecurityGroup")
	public ResponseEntity<Map<String, Object>> removeUserLoginToSecurityGroup(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="groupId") String groupId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("groupId",groupId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeUserLoginToSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSecurityPermission")
	public ResponseEntity<Map<String, Object>> updateSecurityPermission(HttpSession session, @RequestParam(value="permissionId") String permissionId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("permissionId",permissionId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSecurityPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeUserLoginSecurityQuestion")
	public ResponseEntity<Map<String, Object>> removeUserLoginSecurityQuestion(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="questionEnumId") String questionEnumId, @RequestParam(value="securityAnswer", required=false) String securityAnswer) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("questionEnumId",questionEnumId);
		paramMap.put("securityAnswer",securityAnswer);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeUserLoginSecurityQuestion", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSecurityGroup")
	public ResponseEntity<Map<String, Object>> deleteSecurityGroup(HttpSession session, @RequestParam(value="groupId") String groupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupId",groupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSecurityGroup")
	public ResponseEntity<Map<String, Object>> createSecurityGroup(HttpSession session, @RequestParam(value="groupId") String groupId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupId",groupId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSecurityGroup")
	public ResponseEntity<Map<String, Object>> updateSecurityGroup(HttpSession session, @RequestParam(value="groupId") String groupId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupId",groupId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addSecurityPermissionToSecurityGroup")
	public ResponseEntity<Map<String, Object>> addSecurityPermissionToSecurityGroup(HttpSession session, @RequestParam(value="permissionId") String permissionId, @RequestParam(value="groupId") String groupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("permissionId",permissionId);
		paramMap.put("groupId",groupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addSecurityPermissionToSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserLoginToSecurityGroup")
	public ResponseEntity<Map<String, Object>> updateUserLoginToSecurityGroup(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="groupId") String groupId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("groupId",groupId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUserLoginToSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSecurityPermission")
	public ResponseEntity<Map<String, Object>> createSecurityPermission(HttpSession session, @RequestParam(value="permissionId") String permissionId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("permissionId",permissionId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSecurityPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProtectedViewToSecurityGroup")
	public ResponseEntity<Map<String, Object>> updateProtectedViewToSecurityGroup(HttpSession session, @RequestParam(value="viewNameId") String viewNameId, @RequestParam(value="maxHitsDuration") Long maxHitsDuration, @RequestParam(value="tarpitDuration") Long tarpitDuration, @RequestParam(value="groupId") String groupId, @RequestParam(value="maxHits") Long maxHits) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("viewNameId",viewNameId);
		paramMap.put("maxHitsDuration",maxHitsDuration);
		paramMap.put("tarpitDuration",tarpitDuration);
		paramMap.put("groupId",groupId);
		paramMap.put("maxHits",maxHits);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProtectedViewToSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addProtectedViewToSecurityGroup")
	public ResponseEntity<Map<String, Object>> addProtectedViewToSecurityGroup(HttpSession session, @RequestParam(value="viewNameId") String viewNameId, @RequestParam(value="maxHitsDuration") Long maxHitsDuration, @RequestParam(value="tarpitDuration") Long tarpitDuration, @RequestParam(value="groupId") String groupId, @RequestParam(value="maxHits") Long maxHits) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("viewNameId",viewNameId);
		paramMap.put("maxHitsDuration",maxHitsDuration);
		paramMap.put("tarpitDuration",tarpitDuration);
		paramMap.put("groupId",groupId);
		paramMap.put("maxHits",maxHits);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProtectedViewToSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeSecurityPermissionFromSecurityGroup")
	public ResponseEntity<Map<String, Object>> removeSecurityPermissionFromSecurityGroup(HttpSession session, @RequestParam(value="permissionId") String permissionId, @RequestParam(value="groupId") String groupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("permissionId",permissionId);
		paramMap.put("groupId",groupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeSecurityPermissionFromSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserLoginSecurityQuestion")
	public ResponseEntity<Map<String, Object>> updateUserLoginSecurityQuestion(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="questionEnumId") String questionEnumId, @RequestParam(value="securityAnswer", required=false) String securityAnswer) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("questionEnumId",questionEnumId);
		paramMap.put("securityAnswer",securityAnswer);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUserLoginSecurityQuestion", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeProtectedViewFromSecurityGroup")
	public ResponseEntity<Map<String, Object>> removeProtectedViewFromSecurityGroup(HttpSession session, @RequestParam(value="viewNameId") String viewNameId, @RequestParam(value="groupId") String groupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("viewNameId",viewNameId);
		paramMap.put("groupId",groupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProtectedViewFromSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUserLoginSecurityQuestion")
	public ResponseEntity<Map<String, Object>> createUserLoginSecurityQuestion(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="questionEnumId") String questionEnumId, @RequestParam(value="securityAnswer", required=false) String securityAnswer) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("questionEnumId",questionEnumId);
		paramMap.put("securityAnswer",securityAnswer);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUserLoginSecurityQuestion", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addUserLoginToSecurityGroup")
	public ResponseEntity<Map<String, Object>> addUserLoginToSecurityGroup(HttpSession session, @RequestParam(value="userLoginId") String userLoginId, @RequestParam(value="groupId") String groupId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("groupId",groupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addUserLoginToSecurityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/securityPermissionCheck")
	public ResponseEntity<Map<String, Object>> securityPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("securityPermissionCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
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
