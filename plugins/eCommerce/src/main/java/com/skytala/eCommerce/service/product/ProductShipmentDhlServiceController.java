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
@RequestMapping("/service/productShipmentDhl")
public class ProductShipmentDhlServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/dhlShipmentConfirm")
	public ResponseEntity<Map<String, Object>> dhlShipmentConfirm(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("dhlShipmentConfirm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/dhlRateEstimate")
	public ResponseEntity<Map<String, Object>> dhlRateEstimate(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="carrierRoleTypeId") String carrierRoleTypeId, @RequestParam(value="shippableItemInfo") List shippableItemInfo, @RequestParam(value="shippableWeight") BigDecimal shippableWeight, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="shippableQuantity") BigDecimal shippableQuantity, @RequestParam(value="shippableTotal") BigDecimal shippableTotal, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="shippingContactMechId", required=false) String shippingContactMechId, @RequestParam(value="shippingCountryCode", required=false) String shippingCountryCode, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="serviceConfigProps", required=false) String serviceConfigProps, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="shippingOriginContactMechId", required=false) String shippingOriginContactMechId, @RequestParam(value="shippingPostalCode", required=false) String shippingPostalCode, @RequestParam(value="initialEstimateAmt", required=false) BigDecimal initialEstimateAmt, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("shippableItemInfo",shippableItemInfo);
		paramMap.put("shippableWeight",shippableWeight);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("shippableQuantity",shippableQuantity);
		paramMap.put("shippableTotal",shippableTotal);
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("shippingContactMechId",shippingContactMechId);
		paramMap.put("shippingCountryCode",shippingCountryCode);
		paramMap.put("shipmentCustomMethodId",shipmentCustomMethodId);
		paramMap.put("serviceConfigProps",serviceConfigProps);
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("shippingOriginContactMechId",shippingOriginContactMechId);
		paramMap.put("shippingPostalCode",shippingPostalCode);
		paramMap.put("initialEstimateAmt",initialEstimateAmt);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("dhlRateEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/dhlRegisterAccount")
	public ResponseEntity<Map<String, Object>> dhlRegisterAccount(HttpSession session, @RequestParam(value="postalCode") String postalCode, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="configProps") String configProps) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("postalCode",postalCode);
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("configProps",configProps);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("dhlRegisterAccount", paramMap);
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
