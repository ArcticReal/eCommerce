package com.skytala.eCommerce.service.manufacturing;

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
@RequestMapping("/service/manufacturingRouting")
public class ManufacturingRoutingServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/checkRoutingTaskAssoc")
	public ResponseEntity<Map<String, Object>> checkRoutingTaskAssoc(HttpSession session, @RequestParam(value="workEffortIdTo") String workEffortIdTo, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="workEffortIdFrom") String workEffortIdFrom, @RequestParam(value="workEffortAssocTypeId") String workEffortAssocTypeId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="create", required=false) String create, @RequestParam(value="thruDate", required=false) java.sql.Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortIdTo",workEffortIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("workEffortIdFrom",workEffortIdFrom);
		paramMap.put("workEffortAssocTypeId",workEffortAssocTypeId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("create",create);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkRoutingTaskAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getEstimatedTaskTime")
	public ResponseEntity<Map<String, Object>> getEstimatedTaskTime(HttpSession session, @RequestParam(value="taskId") String taskId, @RequestParam(value="routingId", required=false) String routingId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taskId",taskId);
		paramMap.put("routingId",routingId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getEstimatedTaskTime", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getRoutingTaskAssocs")
	public ResponseEntity<Map<String, Object>> getRoutingTaskAssocs(HttpSession session, @RequestParam(value="workEffortId") String workEffortId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getRoutingTaskAssocs", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getProductRouting")
	public ResponseEntity<Map<String, Object>> getProductRouting(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="applicableDate", required=false) java.sql.Timestamp applicableDate, @RequestParam(value="ignoreDefaultRouting", required=false) String ignoreDefaultRouting) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("applicableDate",applicableDate);
		paramMap.put("ignoreDefaultRouting",ignoreDefaultRouting);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductRouting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/lookupRoutingTask")
	public ResponseEntity<Map<String, Object>> lookupRoutingTask(HttpSession session, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="workEffortName", required=false) String workEffortName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("workEffortName",workEffortName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("lookupRoutingTask", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
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
