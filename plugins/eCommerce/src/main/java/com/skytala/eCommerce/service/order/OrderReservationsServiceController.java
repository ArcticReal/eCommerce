package com.skytala.eCommerce.service.order;

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
@RequestMapping("/service/orderReservations")
public class OrderReservationsServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateAccommodationSpot")
	public ResponseEntity<Map<String, Object>> updateAccommodationSpot(HttpSession session, @RequestParam(value="accommodationSpotId") String accommodationSpotId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="numberOfSpaces", required=false) Long numberOfSpaces, @RequestParam(value="description", required=false) String description, @RequestParam(value="accommodationClassId", required=false) String accommodationClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("numberOfSpaces",numberOfSpaces);
		paramMap.put("description",description);
		paramMap.put("accommodationClassId",accommodationClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAccommodationSpot", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAccommodationSpot")
	public ResponseEntity<Map<String, Object>> deleteAccommodationSpot(HttpSession session, @RequestParam(value="accommodationSpotId") String accommodationSpotId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAccommodationSpot", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createAccommodationSpot")
	public ResponseEntity<Map<String, Object>> createAccommodationSpot(HttpSession session, @RequestParam(value="accommodationSpotId", required=false) String accommodationSpotId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="numberOfSpaces", required=false) Long numberOfSpaces, @RequestParam(value="description", required=false) String description, @RequestParam(value="accommodationClassId", required=false) String accommodationClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accommodationSpotId",accommodationSpotId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("numberOfSpaces",numberOfSpaces);
		paramMap.put("description",description);
		paramMap.put("accommodationClassId",accommodationClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAccommodationSpot", paramMap);
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
