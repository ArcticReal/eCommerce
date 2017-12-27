package com.skytala.eCommerce.service.humanres;

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
@RequestMapping("/service/humanresPosition")
public class HumanresPositionServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplPositionClassType")
	public ResponseEntity<Map<String, Object>> createEmplPositionClassType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="emplPositionClassTypeId", required=false) String emplPositionClassTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("emplPositionClassTypeId",emplPositionClassTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplPositionClassType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmplPositionClassType")
	public ResponseEntity<Map<String, Object>> deleteEmplPositionClassType(HttpSession session, @RequestParam(value="emplPositionClassTypeId") String emplPositionClassTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplPositionClassTypeId",emplPositionClassTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteEmplPositionClassType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEmplPositionTypeClass")
	public ResponseEntity<Map<String, Object>> createEmplPositionTypeClass(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="emplPositionClassTypeId") String emplPositionClassTypeId, @RequestParam(value="standardHoursPerWeek", required=false) BigDecimal standardHoursPerWeek, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("emplPositionClassTypeId",emplPositionClassTypeId);
		paramMap.put("standardHoursPerWeek",standardHoursPerWeek);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmplPositionTypeClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPositionClassType")
	public ResponseEntity<Map<String, Object>> updateEmplPositionClassType(HttpSession session, @RequestParam(value="emplPositionClassTypeId") String emplPositionClassTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emplPositionClassTypeId",emplPositionClassTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPositionClassType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/expireEmplPositionTypeClass")
	public ResponseEntity<Map<String, Object>> expireEmplPositionTypeClass(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="emplPositionClassTypeId") String emplPositionClassTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("emplPositionClassTypeId",emplPositionClassTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireEmplPositionTypeClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmplPositionTypeClass")
	public ResponseEntity<Map<String, Object>> updateEmplPositionTypeClass(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="emplPositionTypeId") String emplPositionTypeId, @RequestParam(value="emplPositionClassTypeId") String emplPositionClassTypeId, @RequestParam(value="standardHoursPerWeek", required=false) BigDecimal standardHoursPerWeek, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("emplPositionTypeId",emplPositionTypeId);
		paramMap.put("emplPositionClassTypeId",emplPositionClassTypeId);
		paramMap.put("standardHoursPerWeek",standardHoursPerWeek);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmplPositionTypeClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
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
