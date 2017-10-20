package com.skytala.eCommerce.service.entityext;

import org.apache.ofbiz.entity.GenericEntity;
import org.apache.ofbiz.entity.GenericPK;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/service/Entityexts")
public class EntityextServices{

	@RequestMapping(method = RequestMethod.POST, value = "/updateTestingSubtype")
	public ResponseEntity<Object> updateTestingSubtype(HttpSession session, @RequestParam(value="testingTypeId") String testingTypeId, @RequestParam(value="subtypeDescription", required=false) String subtypeDescription) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("subtypeDescription",subtypeDescription);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTestingSubtype", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/distributedClearCacheLineByCondition")
	public ResponseEntity<Object> distributedClearCacheLineByCondition(HttpSession session, @RequestParam(value="condition") org.apache.ofbiz.entity.condition.EntityCondition condition, @RequestParam(value="entityName") String entityName, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("condition",condition);
		paramMap.put("entityName",entityName);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("distributedClearCacheLineByCondition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserAgentType")
	public ResponseEntity<Object> updateUserAgentType(HttpSession session, @RequestParam(value="userAgentTypeId") String userAgentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userAgentTypeId",userAgentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUserAgentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/pullAndReportEntitySyncData")
	public ResponseEntity<Object> pullAndReportEntitySyncData(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="delegatorName", required=false) String delegatorName, @RequestParam(value="toCreateInserted", required=false) Long toCreateInserted, @RequestParam(value="toStoreUpdated", required=false) Long toStoreUpdated, @RequestParam(value="toCreateUpdated", required=false) Long toCreateUpdated, @RequestParam(value="toCreateNotUpdated", required=false) Long toCreateNotUpdated, @RequestParam(value="toRemoveAlreadyDeleted", required=false) Long toRemoveAlreadyDeleted, @RequestParam(value="toStoreNotUpdated", required=false) Long toStoreNotUpdated, @RequestParam(value="toRemoveDeleted", required=false) Long toRemoveDeleted, @RequestParam(value="toStoreInserted", required=false) Long toStoreInserted, @RequestParam(value="startDate", required=false) Timestamp startDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("delegatorName",delegatorName);
		paramMap.put("toCreateInserted",toCreateInserted);
		paramMap.put("toStoreUpdated",toStoreUpdated);
		paramMap.put("toCreateUpdated",toCreateUpdated);
		paramMap.put("toCreateNotUpdated",toCreateNotUpdated);
		paramMap.put("toRemoveAlreadyDeleted",toRemoveAlreadyDeleted);
		paramMap.put("toStoreNotUpdated",toStoreNotUpdated);
		paramMap.put("toRemoveDeleted",toRemoveDeleted);
		paramMap.put("toStoreInserted",toStoreInserted);
		paramMap.put("startDate",startDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("pullAndReportEntitySyncData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTestingType")
	public ResponseEntity<Object> updateTestingType(HttpSession session, @RequestParam(value="testingTypeId") String testingTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTestingType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBrowserType")
	public ResponseEntity<Object> createBrowserType(HttpSession session, @RequestParam(value="browserVersion", required=false) String browserVersion, @RequestParam(value="browserName", required=false) String browserName, @RequestParam(value="browserTypeId", required=false) String browserTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("browserVersion",browserVersion);
		paramMap.put("browserName",browserName);
		paramMap.put("browserTypeId",browserTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBrowserType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProtocolType")
	public ResponseEntity<Object> deleteProtocolType(HttpSession session, @RequestParam(value="protocolTypeId") String protocolTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("protocolTypeId",protocolTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProtocolType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/importDelimitedEntityFile")
	public ResponseEntity<Object> importDelimitedEntityFile(HttpSession session, @RequestParam(value="file") java.io.File file, @RequestParam(value="delimiter", required=false) String delimiter) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("file",file);
		paramMap.put("delimiter",delimiter);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("importDelimitedEntityFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/runPullEntitySync")
	public ResponseEntity<Object> runPullEntitySync(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="remotePullAndReportEntitySyncDataName") String remotePullAndReportEntitySyncDataName, @RequestParam(value="remoteDelegatorName", required=false) String remoteDelegatorName, @RequestParam(value="localDelegatorName", required=false) String localDelegatorName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("remotePullAndReportEntitySyncDataName",remotePullAndReportEntitySyncDataName);
		paramMap.put("remoteDelegatorName",remoteDelegatorName);
		paramMap.put("localDelegatorName",localDelegatorName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("runPullEntitySync", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTestingType")
	public ResponseEntity<Object> deleteTestingType(HttpSession session, @RequestParam(value="testingTypeId") String testingTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTestingType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cleanSyncRemoveInfo")
	public ResponseEntity<Object> cleanSyncRemoveInfo(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cleanSyncRemoveInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserAgentMethodType")
	public ResponseEntity<Object> updateUserAgentMethodType(HttpSession session, @RequestParam(value="userAgentMethodTypeId") String userAgentMethodTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userAgentMethodTypeId",userAgentMethodTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateUserAgentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createUserAgentMethodType")
	public ResponseEntity<Object> createUserAgentMethodType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="userAgentMethodTypeId", required=false) String userAgentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("userAgentMethodTypeId",userAgentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUserAgentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUserAgentMethodType")
	public ResponseEntity<Object> deleteUserAgentMethodType(HttpSession session, @RequestParam(value="userAgentMethodTypeId") String userAgentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userAgentMethodTypeId",userAgentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteUserAgentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/localhostClearAllEntityCaches")
	public ResponseEntity<Object> localhostClearAllEntityCaches(HttpSession session, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("localhostClearAllEntityCaches", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/distributedClearCacheLineByPrimaryKey")
	public ResponseEntity<Object> distributedClearCacheLineByPrimaryKey(HttpSession session, @RequestParam(value="primaryKey") GenericPK primaryKey, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryKey",primaryKey);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("distributedClearCacheLineByPrimaryKey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTestingSubtype")
	public ResponseEntity<Object> createTestingSubtype(HttpSession session, @RequestParam(value="testingTypeId", required=false) String testingTypeId, @RequestParam(value="subtypeDescription", required=false) String subtypeDescription) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("subtypeDescription",subtypeDescription);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTestingSubtype", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createServerHitType")
	public ResponseEntity<Object> createServerHitType(HttpSession session, @RequestParam(value="hitTypeId", required=false) String hitTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("hitTypeId",hitTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createServerHitType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearCacheLineByCondition")
	public ResponseEntity<Object> clearCacheLineByCondition(HttpSession session, @RequestParam(value="condition") org.apache.ofbiz.entity.condition.EntityCondition condition, @RequestParam(value="entityName") String entityName, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("condition",condition);
		paramMap.put("entityName",entityName);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearCacheLineByCondition", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/remotePullAndReportEntitySyncDataHttp")
	public ResponseEntity<Object> remotePullAndReportEntitySyncDataHttp(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="delegatorName", required=false) String delegatorName, @RequestParam(value="toCreateInserted", required=false) Long toCreateInserted, @RequestParam(value="toStoreUpdated", required=false) Long toStoreUpdated, @RequestParam(value="toCreateUpdated", required=false) Long toCreateUpdated, @RequestParam(value="toCreateNotUpdated", required=false) Long toCreateNotUpdated, @RequestParam(value="toRemoveAlreadyDeleted", required=false) Long toRemoveAlreadyDeleted, @RequestParam(value="toStoreNotUpdated", required=false) Long toStoreNotUpdated, @RequestParam(value="toRemoveDeleted", required=false) Long toRemoveDeleted, @RequestParam(value="toStoreInserted", required=false) Long toStoreInserted, @RequestParam(value="startDate", required=false) Timestamp startDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("delegatorName",delegatorName);
		paramMap.put("toCreateInserted",toCreateInserted);
		paramMap.put("toStoreUpdated",toStoreUpdated);
		paramMap.put("toCreateUpdated",toCreateUpdated);
		paramMap.put("toCreateNotUpdated",toCreateNotUpdated);
		paramMap.put("toRemoveAlreadyDeleted",toRemoveAlreadyDeleted);
		paramMap.put("toStoreNotUpdated",toStoreNotUpdated);
		paramMap.put("toRemoveDeleted",toRemoveDeleted);
		paramMap.put("toStoreInserted",toStoreInserted);
		paramMap.put("startDate",startDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("remotePullAndReportEntitySyncDataHttp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateBrowserType")
	public ResponseEntity<Object> updateBrowserType(HttpSession session, @RequestParam(value="browserTypeId") String browserTypeId, @RequestParam(value="browserVersion", required=false) String browserVersion, @RequestParam(value="browserName", required=false) String browserName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("browserTypeId",browserTypeId);
		paramMap.put("browserVersion",browserVersion);
		paramMap.put("browserName",browserName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBrowserType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearCacheLineByValue")
	public ResponseEntity<Object> clearCacheLineByValue(HttpSession session, @RequestParam(value="value") org.apache.ofbiz.entity.GenericValue value, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("value",value);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearCacheLineByValue", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearAllEntityCaches")
	public ResponseEntity<Object> clearAllEntityCaches(HttpSession session, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearAllEntityCaches", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/importEntityFileDirectory")
	public ResponseEntity<Object> importEntityFileDirectory(HttpSession session, @RequestParam(value="rootDirectory") String rootDirectory, @RequestParam(value="delimiter", required=false) String delimiter) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rootDirectory",rootDirectory);
		paramMap.put("delimiter",delimiter);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("importEntityFileDirectory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/runEntitySync")
	public ResponseEntity<Object> runEntitySync(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("runEntitySync", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/remotePullAndReportEntitySyncDataRmi")
	public ResponseEntity<Object> remotePullAndReportEntitySyncDataRmi(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="delegatorName", required=false) String delegatorName, @RequestParam(value="toCreateInserted", required=false) Long toCreateInserted, @RequestParam(value="toStoreUpdated", required=false) Long toStoreUpdated, @RequestParam(value="toCreateUpdated", required=false) Long toCreateUpdated, @RequestParam(value="toCreateNotUpdated", required=false) Long toCreateNotUpdated, @RequestParam(value="toRemoveAlreadyDeleted", required=false) Long toRemoveAlreadyDeleted, @RequestParam(value="toStoreNotUpdated", required=false) Long toStoreNotUpdated, @RequestParam(value="toRemoveDeleted", required=false) Long toRemoveDeleted, @RequestParam(value="toStoreInserted", required=false) Long toStoreInserted, @RequestParam(value="startDate", required=false) Timestamp startDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("delegatorName",delegatorName);
		paramMap.put("toCreateInserted",toCreateInserted);
		paramMap.put("toStoreUpdated",toStoreUpdated);
		paramMap.put("toCreateUpdated",toCreateUpdated);
		paramMap.put("toCreateNotUpdated",toCreateNotUpdated);
		paramMap.put("toRemoveAlreadyDeleted",toRemoveAlreadyDeleted);
		paramMap.put("toStoreNotUpdated",toStoreNotUpdated);
		paramMap.put("toRemoveDeleted",toRemoveDeleted);
		paramMap.put("toStoreInserted",toStoreInserted);
		paramMap.put("startDate",startDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("remotePullAndReportEntitySyncDataRmi", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/entitySyncPermissionCheck")
	public ResponseEntity<Object> entitySyncPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("entitySyncPermissionCheck", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTestingType")
	public ResponseEntity<Object> createTestingType(HttpSession session, @RequestParam(value="testingTypeId", required=false) String testingTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTestingType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/remoteStoreEntitySyncDataHttp")
	public ResponseEntity<Object> remoteStoreEntitySyncDataHttp(HttpSession session, @RequestParam(value="valuesToStore") List valuesToStore, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="keysToRemove") List keysToRemove, @RequestParam(value="valuesToCreate") List valuesToCreate, @RequestParam(value="delegatorName", required=false) String delegatorName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("valuesToStore",valuesToStore);
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("keysToRemove",keysToRemove);
		paramMap.put("valuesToCreate",valuesToCreate);
		paramMap.put("delegatorName",delegatorName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("remoteStoreEntitySyncDataHttp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEntitySyncInclude")
	public ResponseEntity<Object> createEntitySyncInclude(HttpSession session, @RequestParam(value="entityOrPackage") String entityOrPackage, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="applEnumId") String applEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityOrPackage",entityOrPackage);
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("applEnumId",applEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEntitySyncInclude", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/localhostClearCacheLineByValue")
	public ResponseEntity<Object> localhostClearCacheLineByValue(HttpSession session, @RequestParam(value="value") org.apache.ofbiz.entity.GenericValue value, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("value",value);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("localhostClearCacheLineByValue", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTestingSubtype")
	public ResponseEntity<Object> deleteTestingSubtype(HttpSession session, @RequestParam(value="testingTypeId") String testingTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testingTypeId",testingTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTestingSubtype", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/localhostClearCacheLineByDummyPK")
	public ResponseEntity<Object> localhostClearCacheLineByDummyPK(HttpSession session, @RequestParam(value="dummyPK") GenericEntity dummyPK, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dummyPK",dummyPK);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("localhostClearCacheLineByDummyPK", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/resetEntitySyncStatusToNotStarted")
	public ResponseEntity<Object> resetEntitySyncStatusToNotStarted(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resetEntitySyncStatusToNotStarted", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/storeEntitySyncData")
	public ResponseEntity<Object> storeEntitySyncData(HttpSession session, @RequestParam(value="valuesToStore") List valuesToStore, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="keysToRemove") List keysToRemove, @RequestParam(value="valuesToCreate") List valuesToCreate, @RequestParam(value="delegatorName", required=false) String delegatorName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("valuesToStore",valuesToStore);
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("keysToRemove",keysToRemove);
		paramMap.put("valuesToCreate",valuesToCreate);
		paramMap.put("delegatorName",delegatorName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("storeEntitySyncData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePlatformType")
	public ResponseEntity<Object> deletePlatformType(HttpSession session, @RequestParam(value="platformTypeId") String platformTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("platformTypeId",platformTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePlatformType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEntitySyncRunning")
	public ResponseEntity<Object> updateEntitySyncRunning(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="targetDelegatorName", required=false) String targetDelegatorName, @RequestParam(value="maxRunningNoUpdateMillis", required=false) Long maxRunningNoUpdateMillis, @RequestParam(value="lastHistoryStartDate", required=false) Timestamp lastHistoryStartDate, @RequestParam(value="preOfflineSynchTime", required=false) Timestamp preOfflineSynchTime, @RequestParam(value="forPushOnly", required=false) String forPushOnly, @RequestParam(value="forPullOnly", required=false) String forPullOnly, @RequestParam(value="lastSuccessfulSynchTime", required=false) Timestamp lastSuccessfulSynchTime, @RequestParam(value="keepRemoveInfoHours", required=false) BigDecimal keepRemoveInfoHours, @RequestParam(value="offlineSyncSplitMillis", required=false) Long offlineSyncSplitMillis, @RequestParam(value="targetServiceName", required=false) String targetServiceName, @RequestParam(value="runStatusId", required=false) String runStatusId, @RequestParam(value="syncSplitMillis", required=false) Long syncSplitMillis, @RequestParam(value="syncEndBufferMillis", required=false) Long syncEndBufferMillis) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("targetDelegatorName",targetDelegatorName);
		paramMap.put("maxRunningNoUpdateMillis",maxRunningNoUpdateMillis);
		paramMap.put("lastHistoryStartDate",lastHistoryStartDate);
		paramMap.put("preOfflineSynchTime",preOfflineSynchTime);
		paramMap.put("forPushOnly",forPushOnly);
		paramMap.put("forPullOnly",forPullOnly);
		paramMap.put("lastSuccessfulSynchTime",lastSuccessfulSynchTime);
		paramMap.put("keepRemoveInfoHours",keepRemoveInfoHours);
		paramMap.put("offlineSyncSplitMillis",offlineSyncSplitMillis);
		paramMap.put("targetServiceName",targetServiceName);
		paramMap.put("runStatusId",runStatusId);
		paramMap.put("syncSplitMillis",syncSplitMillis);
		paramMap.put("syncEndBufferMillis",syncEndBufferMillis);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEntitySyncRunning", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/rebuildEntityIndexesAndKeys")
	public ResponseEntity<Object> rebuildEntityIndexesAndKeys(HttpSession session, @RequestParam(value="groupName") String groupName, @RequestParam(value="fixColSizes", required=false) Boolean fixColSizes) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupName",groupName);
		paramMap.put("fixColSizes",fixColSizes);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("rebuildEntityIndexesAndKeys", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/unwrapByteWrappers")
	public ResponseEntity<Object> unwrapByteWrappers(HttpSession session, @RequestParam(value="fieldName") String fieldName, @RequestParam(value="entityName") String entityName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fieldName",fieldName);
		paramMap.put("entityName",entityName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("unwrapByteWrappers", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/distributedClearAllEntityCaches")
	public ResponseEntity<Object> distributedClearAllEntityCaches(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("distributedClearAllEntityCaches", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEntitySyncHistory")
	public ResponseEntity<Object> updateEntitySyncHistory(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="startDate") Timestamp startDate, @RequestParam(value="totalRowsExported", required=false) Long totalRowsExported, @RequestParam(value="perSplitMinItems", required=false) Long perSplitMinItems, @RequestParam(value="toCreateInserted", required=false) Long toCreateInserted, @RequestParam(value="toStoreUpdated", required=false) Long toStoreUpdated, @RequestParam(value="toCreateUpdated", required=false) Long toCreateUpdated, @RequestParam(value="toCreateNotUpdated", required=false) Long toCreateNotUpdated, @RequestParam(value="perSplitMinMillis", required=false) Long perSplitMinMillis, @RequestParam(value="totalStoreCalls", required=false) Long totalStoreCalls, @RequestParam(value="perSplitMaxMillis", required=false) Long perSplitMaxMillis, @RequestParam(value="runningTimeMillis", required=false) Long runningTimeMillis, @RequestParam(value="beginningSynchTime", required=false) Timestamp beginningSynchTime, @RequestParam(value="toStoreInserted", required=false) Long toStoreInserted, @RequestParam(value="lastSplitStartTime", required=false) Long lastSplitStartTime, @RequestParam(value="totalRowsToRemove", required=false) Long totalRowsToRemove, @RequestParam(value="totalSplits", required=false) Long totalSplits, @RequestParam(value="lastSuccessfulSynchTime", required=false) Timestamp lastSuccessfulSynchTime, @RequestParam(value="totalRowsToCreate", required=false) Long totalRowsToCreate, @RequestParam(value="perSplitMaxItems", required=false) Long perSplitMaxItems, @RequestParam(value="lastCandidateEndTime", required=false) Timestamp lastCandidateEndTime, @RequestParam(value="totalRowsToStore", required=false) Long totalRowsToStore, @RequestParam(value="toRemoveAlreadyDeleted", required=false) Long toRemoveAlreadyDeleted, @RequestParam(value="toStoreNotUpdated", required=false) Long toStoreNotUpdated, @RequestParam(value="toRemoveDeleted", required=false) Long toRemoveDeleted, @RequestParam(value="runStatusId", required=false) String runStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("startDate",startDate);
		paramMap.put("totalRowsExported",totalRowsExported);
		paramMap.put("perSplitMinItems",perSplitMinItems);
		paramMap.put("toCreateInserted",toCreateInserted);
		paramMap.put("toStoreUpdated",toStoreUpdated);
		paramMap.put("toCreateUpdated",toCreateUpdated);
		paramMap.put("toCreateNotUpdated",toCreateNotUpdated);
		paramMap.put("perSplitMinMillis",perSplitMinMillis);
		paramMap.put("totalStoreCalls",totalStoreCalls);
		paramMap.put("perSplitMaxMillis",perSplitMaxMillis);
		paramMap.put("runningTimeMillis",runningTimeMillis);
		paramMap.put("beginningSynchTime",beginningSynchTime);
		paramMap.put("toStoreInserted",toStoreInserted);
		paramMap.put("lastSplitStartTime",lastSplitStartTime);
		paramMap.put("totalRowsToRemove",totalRowsToRemove);
		paramMap.put("totalSplits",totalSplits);
		paramMap.put("lastSuccessfulSynchTime",lastSuccessfulSynchTime);
		paramMap.put("totalRowsToCreate",totalRowsToCreate);
		paramMap.put("perSplitMaxItems",perSplitMaxItems);
		paramMap.put("lastCandidateEndTime",lastCandidateEndTime);
		paramMap.put("totalRowsToStore",totalRowsToStore);
		paramMap.put("toRemoveAlreadyDeleted",toRemoveAlreadyDeleted);
		paramMap.put("toStoreNotUpdated",toStoreNotUpdated);
		paramMap.put("toRemoveDeleted",toRemoveDeleted);
		paramMap.put("runStatusId",runStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEntitySyncHistory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reencryptFields")
	public ResponseEntity<Object> reencryptFields(HttpSession session, @RequestParam(value="groupName", required=false) String groupName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupName",groupName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reencryptFields", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEntitySync")
	public ResponseEntity<Object> createEntitySync(HttpSession session, @RequestParam(value="targetDelegatorName", required=false) String targetDelegatorName, @RequestParam(value="maxRunningNoUpdateMillis", required=false) Long maxRunningNoUpdateMillis, @RequestParam(value="lastHistoryStartDate", required=false) Timestamp lastHistoryStartDate, @RequestParam(value="preOfflineSynchTime", required=false) Timestamp preOfflineSynchTime, @RequestParam(value="forPushOnly", required=false) String forPushOnly, @RequestParam(value="forPullOnly", required=false) String forPullOnly, @RequestParam(value="lastSuccessfulSynchTime", required=false) Timestamp lastSuccessfulSynchTime, @RequestParam(value="keepRemoveInfoHours", required=false) BigDecimal keepRemoveInfoHours, @RequestParam(value="offlineSyncSplitMillis", required=false) Long offlineSyncSplitMillis, @RequestParam(value="targetServiceName", required=false) String targetServiceName, @RequestParam(value="runStatusId", required=false) String runStatusId, @RequestParam(value="syncSplitMillis", required=false) Long syncSplitMillis, @RequestParam(value="syncEndBufferMillis", required=false) Long syncEndBufferMillis) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("targetDelegatorName",targetDelegatorName);
		paramMap.put("maxRunningNoUpdateMillis",maxRunningNoUpdateMillis);
		paramMap.put("lastHistoryStartDate",lastHistoryStartDate);
		paramMap.put("preOfflineSynchTime",preOfflineSynchTime);
		paramMap.put("forPushOnly",forPushOnly);
		paramMap.put("forPullOnly",forPullOnly);
		paramMap.put("lastSuccessfulSynchTime",lastSuccessfulSynchTime);
		paramMap.put("keepRemoveInfoHours",keepRemoveInfoHours);
		paramMap.put("offlineSyncSplitMillis",offlineSyncSplitMillis);
		paramMap.put("targetServiceName",targetServiceName);
		paramMap.put("runStatusId",runStatusId);
		paramMap.put("syncSplitMillis",syncSplitMillis);
		paramMap.put("syncEndBufferMillis",syncEndBufferMillis);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEntitySync", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEntitySync")
	public ResponseEntity<Object> updateEntitySync(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="targetDelegatorName", required=false) String targetDelegatorName, @RequestParam(value="maxRunningNoUpdateMillis", required=false) Long maxRunningNoUpdateMillis, @RequestParam(value="lastHistoryStartDate", required=false) Timestamp lastHistoryStartDate, @RequestParam(value="preOfflineSynchTime", required=false) Timestamp preOfflineSynchTime, @RequestParam(value="forPushOnly", required=false) String forPushOnly, @RequestParam(value="forPullOnly", required=false) String forPullOnly, @RequestParam(value="lastSuccessfulSynchTime", required=false) Timestamp lastSuccessfulSynchTime, @RequestParam(value="keepRemoveInfoHours", required=false) BigDecimal keepRemoveInfoHours, @RequestParam(value="offlineSyncSplitMillis", required=false) Long offlineSyncSplitMillis, @RequestParam(value="targetServiceName", required=false) String targetServiceName, @RequestParam(value="runStatusId", required=false) String runStatusId, @RequestParam(value="syncSplitMillis", required=false) Long syncSplitMillis, @RequestParam(value="syncEndBufferMillis", required=false) Long syncEndBufferMillis) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("targetDelegatorName",targetDelegatorName);
		paramMap.put("maxRunningNoUpdateMillis",maxRunningNoUpdateMillis);
		paramMap.put("lastHistoryStartDate",lastHistoryStartDate);
		paramMap.put("preOfflineSynchTime",preOfflineSynchTime);
		paramMap.put("forPushOnly",forPushOnly);
		paramMap.put("forPullOnly",forPullOnly);
		paramMap.put("lastSuccessfulSynchTime",lastSuccessfulSynchTime);
		paramMap.put("keepRemoveInfoHours",keepRemoveInfoHours);
		paramMap.put("offlineSyncSplitMillis",offlineSyncSplitMillis);
		paramMap.put("targetServiceName",targetServiceName);
		paramMap.put("runStatusId",runStatusId);
		paramMap.put("syncSplitMillis",syncSplitMillis);
		paramMap.put("syncEndBufferMillis",syncEndBufferMillis);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEntitySync", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEntitySyncHistory")
	public ResponseEntity<Object> createEntitySyncHistory(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="totalRowsExported", required=false) Long totalRowsExported, @RequestParam(value="perSplitMinItems", required=false) Long perSplitMinItems, @RequestParam(value="toCreateInserted", required=false) Long toCreateInserted, @RequestParam(value="toStoreUpdated", required=false) Long toStoreUpdated, @RequestParam(value="toCreateUpdated", required=false) Long toCreateUpdated, @RequestParam(value="toCreateNotUpdated", required=false) Long toCreateNotUpdated, @RequestParam(value="perSplitMinMillis", required=false) Long perSplitMinMillis, @RequestParam(value="totalStoreCalls", required=false) Long totalStoreCalls, @RequestParam(value="perSplitMaxMillis", required=false) Long perSplitMaxMillis, @RequestParam(value="runningTimeMillis", required=false) Long runningTimeMillis, @RequestParam(value="beginningSynchTime", required=false) Timestamp beginningSynchTime, @RequestParam(value="toStoreInserted", required=false) Long toStoreInserted, @RequestParam(value="lastSplitStartTime", required=false) Long lastSplitStartTime, @RequestParam(value="totalRowsToRemove", required=false) Long totalRowsToRemove, @RequestParam(value="totalSplits", required=false) Long totalSplits, @RequestParam(value="lastSuccessfulSynchTime", required=false) Timestamp lastSuccessfulSynchTime, @RequestParam(value="totalRowsToCreate", required=false) Long totalRowsToCreate, @RequestParam(value="perSplitMaxItems", required=false) Long perSplitMaxItems, @RequestParam(value="lastCandidateEndTime", required=false) Timestamp lastCandidateEndTime, @RequestParam(value="totalRowsToStore", required=false) Long totalRowsToStore, @RequestParam(value="toRemoveAlreadyDeleted", required=false) Long toRemoveAlreadyDeleted, @RequestParam(value="toStoreNotUpdated", required=false) Long toStoreNotUpdated, @RequestParam(value="toRemoveDeleted", required=false) Long toRemoveDeleted, @RequestParam(value="runStatusId", required=false) String runStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("totalRowsExported",totalRowsExported);
		paramMap.put("perSplitMinItems",perSplitMinItems);
		paramMap.put("toCreateInserted",toCreateInserted);
		paramMap.put("toStoreUpdated",toStoreUpdated);
		paramMap.put("toCreateUpdated",toCreateUpdated);
		paramMap.put("toCreateNotUpdated",toCreateNotUpdated);
		paramMap.put("perSplitMinMillis",perSplitMinMillis);
		paramMap.put("totalStoreCalls",totalStoreCalls);
		paramMap.put("perSplitMaxMillis",perSplitMaxMillis);
		paramMap.put("runningTimeMillis",runningTimeMillis);
		paramMap.put("beginningSynchTime",beginningSynchTime);
		paramMap.put("toStoreInserted",toStoreInserted);
		paramMap.put("lastSplitStartTime",lastSplitStartTime);
		paramMap.put("totalRowsToRemove",totalRowsToRemove);
		paramMap.put("totalSplits",totalSplits);
		paramMap.put("lastSuccessfulSynchTime",lastSuccessfulSynchTime);
		paramMap.put("totalRowsToCreate",totalRowsToCreate);
		paramMap.put("perSplitMaxItems",perSplitMaxItems);
		paramMap.put("lastCandidateEndTime",lastCandidateEndTime);
		paramMap.put("totalRowsToStore",totalRowsToStore);
		paramMap.put("toRemoveAlreadyDeleted",toRemoveAlreadyDeleted);
		paramMap.put("toStoreNotUpdated",toStoreNotUpdated);
		paramMap.put("toRemoveDeleted",toRemoveDeleted);
		paramMap.put("runStatusId",runStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEntitySyncHistory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/remoteStoreEntitySyncDataRmi")
	public ResponseEntity<Object> remoteStoreEntitySyncDataRmi(HttpSession session, @RequestParam(value="valuesToStore") List valuesToStore, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="keysToRemove") List keysToRemove, @RequestParam(value="valuesToCreate") List valuesToCreate, @RequestParam(value="delegatorName", required=false) String delegatorName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("valuesToStore",valuesToStore);
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("keysToRemove",keysToRemove);
		paramMap.put("valuesToCreate",valuesToCreate);
		paramMap.put("delegatorName",delegatorName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("remoteStoreEntitySyncDataRmi", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearCacheLineByDummyPK")
	public ResponseEntity<Object> clearCacheLineByDummyPK(HttpSession session, @RequestParam(value="dummyPK") GenericEntity dummyPK, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dummyPK",dummyPK);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearCacheLineByDummyPK", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/watchEntity")
	public ResponseEntity<Object> watchEntity(HttpSession session, @RequestParam(value="newValue") org.apache.ofbiz.entity.GenericValue newValue, @RequestParam(value="fieldName", required=false) String fieldName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("newValue",newValue);
		paramMap.put("fieldName",fieldName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("watchEntity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOfflineEntitySync")
	public ResponseEntity<Object> updateOfflineEntitySync(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOfflineEntitySync", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProtocolType")
	public ResponseEntity<Object> createProtocolType(HttpSession session, @RequestParam(value="protocolTypeId", required=false) String protocolTypeId, @RequestParam(value="protocolName", required=false) String protocolName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("protocolTypeId",protocolTypeId);
		paramMap.put("protocolName",protocolName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProtocolType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/loadOfflineEntitySyncData")
	public ResponseEntity<Object> loadOfflineEntitySyncData(HttpSession session, @RequestParam(value="xmlFileName") String xmlFileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("xmlFileName",xmlFileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("loadOfflineEntitySyncData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/localhostClearCacheLineByPrimaryKey")
	public ResponseEntity<Object> localhostClearCacheLineByPrimaryKey(HttpSession session, @RequestParam(value="primaryKey") GenericPK primaryKey, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryKey",primaryKey);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("localhostClearCacheLineByPrimaryKey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateServerHitType")
	public ResponseEntity<Object> updateServerHitType(HttpSession session, @RequestParam(value="hitTypeId") String hitTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("hitTypeId",hitTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateServerHitType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUserAgentType")
	public ResponseEntity<Object> deleteUserAgentType(HttpSession session, @RequestParam(value="userAgentTypeId") String userAgentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userAgentTypeId",userAgentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteUserAgentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createUserAgentType")
	public ResponseEntity<Object> createUserAgentType(HttpSession session, @RequestParam(value="userAgentTypeId", required=false) String userAgentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userAgentTypeId",userAgentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUserAgentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/reencryptPrivateKeys")
	public ResponseEntity<Object> reencryptPrivateKeys(HttpSession session, @RequestParam(value="oldKey", required=false) String oldKey, @RequestParam(value="newKey", required=false) String newKey) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("oldKey",oldKey);
		paramMap.put("newKey",newKey);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reencryptPrivateKeys", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPlatformType")
	public ResponseEntity<Object> createPlatformType(HttpSession session, @RequestParam(value="platformVersion", required=false) String platformVersion, @RequestParam(value="platformTypeId", required=false) String platformTypeId, @RequestParam(value="platformName", required=false) String platformName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("platformVersion",platformVersion);
		paramMap.put("platformTypeId",platformTypeId);
		paramMap.put("platformName",platformName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPlatformType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/distributedClearCacheLineByDummyPK")
	public ResponseEntity<Object> distributedClearCacheLineByDummyPK(HttpSession session, @RequestParam(value="dummyPK") GenericEntity dummyPK) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dummyPK",dummyPK);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("distributedClearCacheLineByDummyPK", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEntitySyncInclude")
	public ResponseEntity<Object> updateEntitySyncInclude(HttpSession session, @RequestParam(value="entityOrPackage") String entityOrPackage, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="applEnumId", required=false) String applEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityOrPackage",entityOrPackage);
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("applEnumId",applEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEntitySyncInclude", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProtocolType")
	public ResponseEntity<Object> updateProtocolType(HttpSession session, @RequestParam(value="protocolTypeId") String protocolTypeId, @RequestParam(value="protocolName", required=false) String protocolName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("protocolTypeId",protocolTypeId);
		paramMap.put("protocolName",protocolName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProtocolType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePlatformType")
	public ResponseEntity<Object> updatePlatformType(HttpSession session, @RequestParam(value="platformTypeId") String platformTypeId, @RequestParam(value="platformVersion", required=false) String platformVersion, @RequestParam(value="platformName", required=false) String platformName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("platformTypeId",platformTypeId);
		paramMap.put("platformVersion",platformVersion);
		paramMap.put("platformName",platformName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePlatformType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEntitySyncHistory")
	public ResponseEntity<Object> deleteEntitySyncHistory(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="startDate") Timestamp startDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("startDate",startDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEntitySyncHistory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEntitySyncInclude")
	public ResponseEntity<Object> deleteEntitySyncInclude(HttpSession session, @RequestParam(value="entityOrPackage") String entityOrPackage, @RequestParam(value="entitySyncId") String entitySyncId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityOrPackage",entityOrPackage);
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEntitySyncInclude", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteBrowserType")
	public ResponseEntity<Object> deleteBrowserType(HttpSession session, @RequestParam(value="browserTypeId") String browserTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("browserTypeId",browserTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteBrowserType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearCacheLineByPrimaryKey")
	public ResponseEntity<Object> clearCacheLineByPrimaryKey(HttpSession session, @RequestParam(value="primaryKey") GenericPK primaryKey, @RequestParam(value="distribute", required=false) Boolean distribute) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryKey",primaryKey);
		paramMap.put("distribute",distribute);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearCacheLineByPrimaryKey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/runOfflineEntitySync")
	public ResponseEntity<Object> runOfflineEntitySync(HttpSession session, @RequestParam(value="entitySyncId") String entitySyncId, @RequestParam(value="fileName", required=false) String fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entitySyncId",entitySyncId);
		paramMap.put("fileName",fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("runOfflineEntitySync", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/distributedClearCacheLineByValue")
	public ResponseEntity<Object> distributedClearCacheLineByValue(HttpSession session, @RequestParam(value="value") org.apache.ofbiz.entity.GenericValue value) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("value",value);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("distributedClearCacheLineByValue", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteServerHitType")
	public ResponseEntity<Object> deleteServerHitType(HttpSession session, @RequestParam(value="hitTypeId") String hitTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("hitTypeId",hitTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteServerHitType", paramMap);
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
