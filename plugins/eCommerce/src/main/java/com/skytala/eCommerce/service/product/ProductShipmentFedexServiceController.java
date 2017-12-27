package com.skytala.eCommerce.service.product;

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
@RequestMapping("/service/productShipmentFedex")
public class ProductShipmentFedexServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/fedexSubscriptionRequest")
	public ResponseEntity<Map<String, Object>> fedexSubscriptionRequest(HttpSession session, @RequestParam(value="companyPartyId") String companyPartyId, @RequestParam(value="replaceMeterNumber") Boolean replaceMeterNumber, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="configProps") String configProps, @RequestParam(value="contactPartyName") String contactPartyName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("companyPartyId",companyPartyId);
		paramMap.put("replaceMeterNumber",replaceMeterNumber);
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("configProps",configProps);
		paramMap.put("contactPartyName",contactPartyName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("fedexSubscriptionRequest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/fedexShipRequest")
	public ResponseEntity<Map<String, Object>> fedexShipRequest(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("fedexShipRequest", paramMap);
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
