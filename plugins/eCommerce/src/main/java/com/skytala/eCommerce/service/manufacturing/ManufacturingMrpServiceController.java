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
@RequestMapping("/service/manufacturingMrp")
public class ManufacturingMrpServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/executeMrp")
	public ResponseEntity<Map<String, Object>> executeMrp(HttpSession session, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="defaultYearsOffset", required=false) Integer defaultYearsOffset, @RequestParam(value="facilityGroupId", required=false) String facilityGroupId, @RequestParam(value="mrpName", required=false) String mrpName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("defaultYearsOffset",defaultYearsOffset);
		paramMap.put("facilityGroupId",facilityGroupId);
		paramMap.put("mrpName",mrpName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("executeMrp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setEstimatedDeliveryDates")
	public ResponseEntity<Map<String, Object>> setEstimatedDeliveryDates(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setEstimatedDeliveryDates", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteMrpEventType")
	public ResponseEntity<Map<String, Object>> deleteMrpEventType(HttpSession session, @RequestParam(value="mrpEventTypeId") String mrpEventTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mrpEventTypeId",mrpEventTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteMrpEventType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findProductMrpQoh")
	public ResponseEntity<Map<String, Object>> findProductMrpQoh(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findProductMrpQoh", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateMrpEventType")
	public ResponseEntity<Map<String, Object>> updateMrpEventType(HttpSession session, @RequestParam(value="mrpEventTypeId") String mrpEventTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mrpEventTypeId",mrpEventTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateMrpEventType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createMrpEventType")
	public ResponseEntity<Map<String, Object>> createMrpEventType(HttpSession session, @RequestParam(value="mrpEventTypeId", required=false) String mrpEventTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mrpEventTypeId",mrpEventTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMrpEventType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/initMrpEvents")
	public ResponseEntity<Map<String, Object>> initMrpEvents(HttpSession session, @RequestParam(value="reInitialize") Boolean reInitialize, @RequestParam(value="manufacturingFacilityId") String manufacturingFacilityId, @RequestParam(value="mrpId") String mrpId, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="defaultYearsOffset", required=false) Integer defaultYearsOffset) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("reInitialize",reInitialize);
		paramMap.put("manufacturingFacilityId",manufacturingFacilityId);
		paramMap.put("mrpId",mrpId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("defaultYearsOffset",defaultYearsOffset);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("initMrpEvents", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createMrpEvent")
	public ResponseEntity<Map<String, Object>> createMrpEvent(HttpSession session, @RequestParam(value="mrpEventTypeId") String mrpEventTypeId, @RequestParam(value="mrpId") String mrpId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="eventDate") Timestamp eventDate, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="eventName", required=false) String eventName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mrpEventTypeId",mrpEventTypeId);
		paramMap.put("mrpId",mrpId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("eventDate",eventDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("eventName",eventName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMrpEvent", paramMap);
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
