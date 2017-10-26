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

@RestController
@RequestMapping("/service/productShipmentUps")
public class ProductShipmentUpsServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/upsShipmentAccept")
	public ResponseEntity<Object> upsShipmentAccept(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsShipmentAccept", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsShipmentAlternateRatesEstimate")
	public ResponseEntity<Object> upsShipmentAlternateRatesEstimate(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentRouteSegmentId", required=false) String shipmentRouteSegmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsShipmentAlternateRatesEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsShipmentConfirm")
	public ResponseEntity<Object> upsShipmentConfirm(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsShipmentConfirm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsEmailReturnLabel")
	public ResponseEntity<Object> upsEmailReturnLabel(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsEmailReturnLabel", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsAddressValidation")
	public ResponseEntity<Object> upsAddressValidation(HttpSession session, @RequestParam(value="city", required=false) String city, @RequestParam(value="postalCode", required=false) String postalCode, @RequestParam(value="stateProvinceGeoId", required=false) String stateProvinceGeoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("city",city);
		paramMap.put("postalCode",postalCode);
		paramMap.put("stateProvinceGeoId",stateProvinceGeoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsAddressValidation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsVoidShipment")
	public ResponseEntity<Object> upsVoidShipment(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsVoidShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsRateEstimateByPostalCode")
	public ResponseEntity<Object> upsRateEstimateByPostalCode(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="carrierRoleTypeId") String carrierRoleTypeId, @RequestParam(value="shippableItemInfo") List shippableItemInfo, @RequestParam(value="shippableWeight") BigDecimal shippableWeight, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="shippableQuantity") BigDecimal shippableQuantity, @RequestParam(value="shippableTotal") BigDecimal shippableTotal, @RequestParam(value="shippingCountryCode", required=false) String shippingCountryCode, @RequestParam(value="shipFromAddress", required=false) org.apache.ofbiz.entity.GenericValue shipFromAddress, @RequestParam(value="serviceConfigProps", required=false) String serviceConfigProps, @RequestParam(value="upsRateInquireMode", required=false) String upsRateInquireMode, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="shippingPostalCode", required=false) String shippingPostalCode, @RequestParam(value="initialEstimateAmt", required=false) BigDecimal initialEstimateAmt, @RequestParam(value="isResidentialAddress", required=false) String isResidentialAddress, @RequestParam(value="packageWeights", required=false) List packageWeights) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("shippableItemInfo",shippableItemInfo);
		paramMap.put("shippableWeight",shippableWeight);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("shippableQuantity",shippableQuantity);
		paramMap.put("shippableTotal",shippableTotal);
		paramMap.put("shippingCountryCode",shippingCountryCode);
		paramMap.put("shipFromAddress",shipFromAddress);
		paramMap.put("serviceConfigProps",serviceConfigProps);
		paramMap.put("upsRateInquireMode",upsRateInquireMode);
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("shippingPostalCode",shippingPostalCode);
		paramMap.put("initialEstimateAmt",initialEstimateAmt);
		paramMap.put("isResidentialAddress",isResidentialAddress);
		paramMap.put("packageWeights",packageWeights);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsRateEstimateByPostalCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsRateEstimate")
	public ResponseEntity<Object> upsRateEstimate(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="carrierRoleTypeId") String carrierRoleTypeId, @RequestParam(value="shippableItemInfo") List shippableItemInfo, @RequestParam(value="shippableWeight") BigDecimal shippableWeight, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="shippableQuantity") BigDecimal shippableQuantity, @RequestParam(value="shippableTotal") BigDecimal shippableTotal, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="shippingContactMechId", required=false) String shippingContactMechId, @RequestParam(value="shippingCountryCode", required=false) String shippingCountryCode, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="serviceConfigProps", required=false) String serviceConfigProps, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="upsRateInquireMode", required=false) String upsRateInquireMode, @RequestParam(value="shippingOriginContactMechId", required=false) String shippingOriginContactMechId, @RequestParam(value="shippingPostalCode", required=false) String shippingPostalCode, @RequestParam(value="initialEstimateAmt", required=false) BigDecimal initialEstimateAmt, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="packageWeights", required=false) List packageWeights) {
		
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
		paramMap.put("upsRateInquireMode",upsRateInquireMode);
		paramMap.put("shippingOriginContactMechId",shippingOriginContactMechId);
		paramMap.put("shippingPostalCode",shippingPostalCode);
		paramMap.put("initialEstimateAmt",initialEstimateAmt);
		paramMap.put("partyId",partyId);
		paramMap.put("packageWeights",packageWeights);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsRateEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/upsTrackShipment")
	public ResponseEntity<Object> upsTrackShipment(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("upsTrackShipment", paramMap);
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
