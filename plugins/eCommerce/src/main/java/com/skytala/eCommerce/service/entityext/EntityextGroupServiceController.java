package com.skytala.eCommerce.service.entityext;

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
@RequestMapping("/service/entityextGroup")
public class EntityextGroupServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createEntityGroup")
	public ResponseEntity<Map<String, Object>> createEntityGroup(HttpSession session, @RequestParam(value="entityGroupId", required=false) String entityGroupId, @RequestParam(value="entityGroupName", required=false) String entityGroupName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityGroupId",entityGroupId);
		paramMap.put("entityGroupName",entityGroupName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEntityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEntityGroup")
	public ResponseEntity<Map<String, Object>> updateEntityGroup(HttpSession session, @RequestParam(value="entityGroupId") String entityGroupId, @RequestParam(value="entityGroupName", required=false) String entityGroupName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityGroupId",entityGroupId);
		paramMap.put("entityGroupName",entityGroupName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEntityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEntityGroupEntry")
	public ResponseEntity<Map<String, Object>> updateEntityGroupEntry(HttpSession session, @RequestParam(value="entityOrPackage") String entityOrPackage, @RequestParam(value="entityGroupId") String entityGroupId, @RequestParam(value="applEnumId", required=false) String applEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityOrPackage",entityOrPackage);
		paramMap.put("entityGroupId",entityGroupId);
		paramMap.put("applEnumId",applEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEntityGroupEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEntityGroupEntry")
	public ResponseEntity<Map<String, Object>> deleteEntityGroupEntry(HttpSession session, @RequestParam(value="entityOrPackage") String entityOrPackage, @RequestParam(value="entityGroupId") String entityGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityOrPackage",entityOrPackage);
		paramMap.put("entityGroupId",entityGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEntityGroupEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEntityGroup")
	public ResponseEntity<Map<String, Object>> deleteEntityGroup(HttpSession session, @RequestParam(value="entityGroupId") String entityGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityGroupId",entityGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEntityGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEntityGroupEntry")
	public ResponseEntity<Map<String, Object>> createEntityGroupEntry(HttpSession session, @RequestParam(value="entityOrPackage") String entityOrPackage, @RequestParam(value="entityGroupId") String entityGroupId, @RequestParam(value="applEnumId", required=false) String applEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityOrPackage",entityOrPackage);
		paramMap.put("entityGroupId",entityGroupId);
		paramMap.put("applEnumId",applEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEntityGroupEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
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
