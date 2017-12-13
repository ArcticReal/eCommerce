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
@RequestMapping("/service/productShipment")
public class ProductShipmentServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/getQuantityForShipment")
	public ResponseEntity<Object> getQuantityForShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getQuantityForShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcPackSessionAdditionalShippingCharge")
	public ResponseEntity<Object> calcPackSessionAdditionalShippingCharge(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="shippingContactMechId") String shippingContactMechId, @RequestParam(value="carrierRoleTypeId") String carrierRoleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="packageWeights", required=false) Map packageWeights) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("shippingContactMechId",shippingContactMechId);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("packingSession",packingSession);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("packageWeights",packageWeights);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calcPackSessionAdditionalShippingCharge", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDelivery")
	public ResponseEntity<Object> createDelivery(HttpSession session, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="destFacilityId", required=false) String destFacilityId, @RequestParam(value="actualArrivalDate", required=false) Timestamp actualArrivalDate, @RequestParam(value="deliveryId", required=false) String deliveryId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="fuelUsed", required=false) BigDecimal fuelUsed, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="startMileage", required=false) BigDecimal startMileage, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="endMileage", required=false) BigDecimal endMileage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("destFacilityId",destFacilityId);
		paramMap.put("actualArrivalDate",actualArrivalDate);
		paramMap.put("deliveryId",deliveryId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("fuelUsed",fuelUsed);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("startMileage",startMileage);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("endMileage",endMileage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDelivery", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentPackage")
	public ResponseEntity<Object> deleteShipmentPackage(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentPackage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDelivery")
	public ResponseEntity<Object> deleteDelivery(HttpSession session, @RequestParam(value="deliveryId") String deliveryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliveryId",deliveryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDelivery", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentRouteSegment")
	public ResponseEntity<Object> deleteShipmentRouteSegment(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentRouteSegment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentTypeAttr")
	public ResponseEntity<Object> deleteShipmentTypeAttr(HttpSession session, @RequestParam(value="shipmentTypeId") String shipmentTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentBoxType")
	public ResponseEntity<Object> deleteShipmentBoxType(HttpSession session, @RequestParam(value="shipmentBoxTypeId") String shipmentBoxTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentBoxType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentAndItemsForVendorReturn")
	public ResponseEntity<Object> createShipmentAndItemsForVendorReturn(HttpSession session, @RequestParam(value="primaryReturnId") String primaryReturnId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="latestCancelDate", required=false) Timestamp latestCancelDate, @RequestParam(value="estimatedReadyDate", required=false) Timestamp estimatedReadyDate, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="estimatedArrivalWorkEffId", required=false) String estimatedArrivalWorkEffId, @RequestParam(value="additionalShippingCharge", required=false) BigDecimal additionalShippingCharge, @RequestParam(value="destinationTelecomNumberId", required=false) String destinationTelecomNumberId, @RequestParam(value="estimatedShipCost", required=false) BigDecimal estimatedShipCost, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="estimatedShipWorkEffId", required=false) String estimatedShipWorkEffId, @RequestParam(value="destinationFacilityId", required=false) String destinationFacilityId, @RequestParam(value="addtlShippingChargeDesc", required=false) String addtlShippingChargeDesc, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="destinationContactMechId", required=false) String destinationContactMechId, @RequestParam(value="originTelecomNumberId", required=false) String originTelecomNumberId, @RequestParam(value="primaryOrderId", required=false) String primaryOrderId, @RequestParam(value="estimatedShipDate", required=false) Timestamp estimatedShipDate, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="shipmentTypeId", required=false) String shipmentTypeId, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="picklistBinId", required=false) String picklistBinId, @RequestParam(value="primaryShipGroupSeqId", required=false) String primaryShipGroupSeqId, @RequestParam(value="handlingInstructions", required=false) String handlingInstructions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryReturnId",primaryReturnId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("latestCancelDate",latestCancelDate);
		paramMap.put("estimatedReadyDate",estimatedReadyDate);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("estimatedArrivalWorkEffId",estimatedArrivalWorkEffId);
		paramMap.put("additionalShippingCharge",additionalShippingCharge);
		paramMap.put("destinationTelecomNumberId",destinationTelecomNumberId);
		paramMap.put("estimatedShipCost",estimatedShipCost);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("estimatedShipWorkEffId",estimatedShipWorkEffId);
		paramMap.put("destinationFacilityId",destinationFacilityId);
		paramMap.put("addtlShippingChargeDesc",addtlShippingChargeDesc);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("destinationContactMechId",destinationContactMechId);
		paramMap.put("originTelecomNumberId",originTelecomNumberId);
		paramMap.put("primaryOrderId",primaryOrderId);
		paramMap.put("estimatedShipDate",estimatedShipDate);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("picklistBinId",picklistBinId);
		paramMap.put("primaryShipGroupSeqId",primaryShipGroupSeqId);
		paramMap.put("handlingInstructions",handlingInstructions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentAndItemsForVendorReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCarrierShipmentMethod")
	public ResponseEntity<Object> deleteCarrierShipmentMethod(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCarrierShipmentMethod", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcShipmentCostEstimate")
	public ResponseEntity<Object> calcShipmentCostEstimate(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="carrierRoleTypeId") String carrierRoleTypeId, @RequestParam(value="shippableItemInfo") List shippableItemInfo, @RequestParam(value="shippableWeight") BigDecimal shippableWeight, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="shippableQuantity") BigDecimal shippableQuantity, @RequestParam(value="shippableTotal") BigDecimal shippableTotal, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="shippingContactMechId", required=false) String shippingContactMechId, @RequestParam(value="shippingCountryCode", required=false) String shippingCountryCode, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="serviceConfigProps", required=false) String serviceConfigProps, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="shippingOriginContactMechId", required=false) String shippingOriginContactMechId, @RequestParam(value="shippingPostalCode", required=false) String shippingPostalCode, @RequestParam(value="initialEstimateAmt", required=false) BigDecimal initialEstimateAmt, @RequestParam(value="partyId", required=false) String partyId) {
		
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
			result = dispatcher.runSync("calcShipmentCostEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePackedLine")
	public ResponseEntity<Object> deletePackedLine(HttpSession session, @RequestParam(value="weightPackageSession") org.apache.ofbiz.shipment.weightPackage.WeightPackageSession weightPackageSession, @RequestParam(value="weightPackageSeqId") Integer weightPackageSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("weightPackageSession",weightPackageSession);
		paramMap.put("weightPackageSeqId",weightPackageSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePackedLine", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueInventoryItemToFixedAssetMaint")
	public ResponseEntity<Object> issueInventoryItemToFixedAssetMaint(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="fixedAssetId") String fixedAssetId, @RequestParam(value="maintHistSeqId") String maintHistSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueInventoryItemToFixedAssetMaint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentReceiptRole")
	public ResponseEntity<Object> createShipmentReceiptRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="receiptId") String receiptId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentReceiptRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentCostEstimate")
	public ResponseEntity<Object> createShipmentCostEstimate(HttpSession session, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="quantityUnitPrice", required=false) BigDecimal quantityUnitPrice, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="featurePercent", required=false) BigDecimal featurePercent, @RequestParam(value="featurePrice", required=false) BigDecimal featurePrice, @RequestParam(value="weightUnitPrice", required=false) BigDecimal weightUnitPrice, @RequestParam(value="oversizeUnit", required=false) BigDecimal oversizeUnit, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="productFeatureGroupId", required=false) String productFeatureGroupId, @RequestParam(value="oversizePrice", required=false) BigDecimal oversizePrice, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="geoIdTo", required=false) String geoIdTo, @RequestParam(value="orderPricePercent", required=false) BigDecimal orderPricePercent, @RequestParam(value="orderItemFlatPrice", required=false) BigDecimal orderItemFlatPrice, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="shippingPricePercent", required=false) BigDecimal shippingPricePercent, @RequestParam(value="weightBreakId", required=false) String weightBreakId, @RequestParam(value="quantityBreakId", required=false) String quantityBreakId, @RequestParam(value="priceBreakId", required=false) String priceBreakId, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="geoIdFrom", required=false) String geoIdFrom, @RequestParam(value="orderFlatPrice", required=false) BigDecimal orderFlatPrice, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="priceUnitPrice", required=false) BigDecimal priceUnitPrice, @RequestParam(value="shipmentCostEstimateId", required=false) String shipmentCostEstimateId, @RequestParam(value="priceUomId", required=false) String priceUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("quantityUnitPrice",quantityUnitPrice);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("featurePercent",featurePercent);
		paramMap.put("featurePrice",featurePrice);
		paramMap.put("weightUnitPrice",weightUnitPrice);
		paramMap.put("oversizeUnit",oversizeUnit);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("productFeatureGroupId",productFeatureGroupId);
		paramMap.put("oversizePrice",oversizePrice);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("geoIdTo",geoIdTo);
		paramMap.put("orderPricePercent",orderPricePercent);
		paramMap.put("orderItemFlatPrice",orderItemFlatPrice);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("shippingPricePercent",shippingPricePercent);
		paramMap.put("weightBreakId",weightBreakId);
		paramMap.put("quantityBreakId",quantityBreakId);
		paramMap.put("priceBreakId",priceBreakId);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("geoIdFrom",geoIdFrom);
		paramMap.put("orderFlatPrice",orderFlatPrice);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("priceUnitPrice",priceUnitPrice);
		paramMap.put("shipmentCostEstimateId",shipmentCostEstimateId);
		paramMap.put("priceUomId",priceUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentCostEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickShipOrderByItem")
	public ResponseEntity<Object> quickShipOrderByItem(HttpSession session, @RequestParam(value="itemShipList") List itemShipList, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="setPackedOnly", required=false) String setPackedOnly) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemShipList",itemShipList);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("setPackedOnly",setPackedOnly);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickShipOrderByItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentTypeAttr")
	public ResponseEntity<Object> createShipmentTypeAttr(HttpSession session, @RequestParam(value="shipmentTypeId") String shipmentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCarrierShipmentMethod")
	public ResponseEntity<Object> createCarrierShipmentMethod(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="sequenceNumber", required=false) Long sequenceNumber, @RequestParam(value="carrierServiceCode", required=false) String carrierServiceCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("sequenceNumber",sequenceNumber);
		paramMap.put("carrierServiceCode",carrierServiceCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCarrierShipmentMethod", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/completeShipment")
	public ResponseEntity<Object> completeShipment(HttpSession session, @RequestParam(value="weightPackageSession") org.apache.ofbiz.shipment.weightPackage.WeightPackageSession weightPackageSession, @RequestParam(value="orderId") String orderId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="shipmentId", required=false) String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("weightPackageSession",weightPackageSession);
		paramMap.put("orderId",orderId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("completeShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderShipmentPlan")
	public ResponseEntity<Object> createOrderShipmentPlan(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOrderShipmentPlan", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/splitShipmentItemByQuantity")
	public ResponseEntity<Object> splitShipmentItemByQuantity(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="newItemQuantity") BigDecimal newItemQuantity, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("newItemQuantity",newItemQuantity);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("splitShipmentItemByQuantity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueOrderItemToShipment")
	public ResponseEntity<Object> issueOrderItemToShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueOrderItemToShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentPackageRouteSeg")
	public ResponseEntity<Object> updateShipmentPackageRouteSeg(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId, @RequestParam(value="labelIntlSignImage", required=false) byte[] labelIntlSignImage, @RequestParam(value="trackingCode", required=false) String trackingCode, @RequestParam(value="boxNumber", required=false) String boxNumber, @RequestParam(value="packageServiceCost", required=false) BigDecimal packageServiceCost, @RequestParam(value="packageOtherCost", required=false) BigDecimal packageOtherCost, @RequestParam(value="labelImage", required=false) byte[] labelImage, @RequestParam(value="labelPrinted", required=false) String labelPrinted, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="internationalInvoice", required=false) byte[] internationalInvoice, @RequestParam(value="packageTransportCost", required=false) BigDecimal packageTransportCost, @RequestParam(value="codAmount", required=false) BigDecimal codAmount, @RequestParam(value="insuredAmount", required=false) BigDecimal insuredAmount, @RequestParam(value="labelHtml", required=false) String labelHtml) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("labelIntlSignImage",labelIntlSignImage);
		paramMap.put("trackingCode",trackingCode);
		paramMap.put("boxNumber",boxNumber);
		paramMap.put("packageServiceCost",packageServiceCost);
		paramMap.put("packageOtherCost",packageOtherCost);
		paramMap.put("labelImage",labelImage);
		paramMap.put("labelPrinted",labelPrinted);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("internationalInvoice",internationalInvoice);
		paramMap.put("packageTransportCost",packageTransportCost);
		paramMap.put("codAmount",codAmount);
		paramMap.put("insuredAmount",insuredAmount);
		paramMap.put("labelHtml",labelHtml);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentPackageRouteSeg", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDelivery")
	public ResponseEntity<Object> updateDelivery(HttpSession session, @RequestParam(value="deliveryId") String deliveryId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="destFacilityId", required=false) String destFacilityId, @RequestParam(value="actualArrivalDate", required=false) Timestamp actualArrivalDate, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="fuelUsed", required=false) BigDecimal fuelUsed, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="startMileage", required=false) BigDecimal startMileage, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="endMileage", required=false) BigDecimal endMileage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deliveryId",deliveryId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("destFacilityId",destFacilityId);
		paramMap.put("actualArrivalDate",actualArrivalDate);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("fuelUsed",fuelUsed);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("startMileage",startMileage);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("endMileage",endMileage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDelivery", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendShipmentScheduledNotification")
	public ResponseEntity<Object> sendShipmentScheduledNotification(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendShipmentScheduledNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteRejectionReason")
	public ResponseEntity<Object> deleteRejectionReason(HttpSession session, @RequestParam(value="rejectionId") String rejectionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteRejectionReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickDropShipOrder")
	public ResponseEntity<Object> quickDropShipOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickDropShipOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setPackageInfo")
	public ResponseEntity<Object> setPackageInfo(HttpSession session, @RequestParam(value="weightPackageSession") org.apache.ofbiz.shipment.weightPackage.WeightPackageSession weightPackageSession, @RequestParam(value="orderId") String orderId, @RequestParam(value="packageHeight", required=false) BigDecimal packageHeight, @RequestParam(value="packageWeight", required=false) BigDecimal packageWeight, @RequestParam(value="packageLength", required=false) BigDecimal packageLength, @RequestParam(value="packageWidth", required=false) BigDecimal packageWidth, @RequestParam(value="shipmentBoxTypeId", required=false) String shipmentBoxTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("weightPackageSession",weightPackageSession);
		paramMap.put("orderId",orderId);
		paramMap.put("packageHeight",packageHeight);
		paramMap.put("packageWeight",packageWeight);
		paramMap.put("packageLength",packageLength);
		paramMap.put("packageWidth",packageWidth);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setPackageInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setShipmentSettingsFromPrimaryOrder")
	public ResponseEntity<Object> setShipmentSettingsFromPrimaryOrder(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setShipmentSettingsFromPrimaryOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcShipmentEstimateInterface")
	public ResponseEntity<Object> calcShipmentEstimateInterface(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="carrierRoleTypeId") String carrierRoleTypeId, @RequestParam(value="shippableItemInfo") List shippableItemInfo, @RequestParam(value="shippableWeight") BigDecimal shippableWeight, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="shippableQuantity") BigDecimal shippableQuantity, @RequestParam(value="shippableTotal") BigDecimal shippableTotal, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="shippingContactMechId", required=false) String shippingContactMechId, @RequestParam(value="shippingCountryCode", required=false) String shippingCountryCode, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="serviceConfigProps", required=false) String serviceConfigProps, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="shippingOriginContactMechId", required=false) String shippingOriginContactMechId, @RequestParam(value="shippingPostalCode", required=false) String shippingPostalCode, @RequestParam(value="initialEstimateAmt", required=false) BigDecimal initialEstimateAmt, @RequestParam(value="partyId", required=false) String partyId) {
		
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
			result = dispatcher.runSync("calcShipmentEstimateInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentContactMech")
	public ResponseEntity<Object> updateShipmentContactMech(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentContactMechTypeId") String shipmentContactMechTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentContactMechTypeId",shipmentContactMechTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/sendShipmentCompleteNotification")
	public ResponseEntity<Object> sendShipmentCompleteNotification(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="sendTo", required=false) String sendTo, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="screenUri", required=false) String screenUri) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("sendTo",sendTo);
		paramMap.put("comments",comments);
		paramMap.put("screenUri",screenUri);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendShipmentCompleteNotification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelAllRows")
	public ResponseEntity<Object> cancelAllRows(HttpSession session, @RequestParam(value="verifyPickSession") org.apache.ofbiz.shipment.verify.VerifyPickSession verifyPickSession) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("verifyPickSession",verifyPickSession);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelAllRows", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/completePackage")
	public ResponseEntity<Object> completePackage(HttpSession session, @RequestParam(value="weightPackageSession") org.apache.ofbiz.shipment.weightPackage.WeightPackageSession weightPackageSession, @RequestParam(value="orderId") String orderId, @RequestParam(value="invoiceId") String invoiceId, @RequestParam(value="newEstimatedShippingCost", required=false) BigDecimal newEstimatedShippingCost, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="dimensionUomId", required=false) String dimensionUomId, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="estimatedShippingCost", required=false) BigDecimal estimatedShippingCost) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("weightPackageSession",weightPackageSession);
		paramMap.put("orderId",orderId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("newEstimatedShippingCost",newEstimatedShippingCost);
		paramMap.put("facilityId",facilityId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("dimensionUomId",dimensionUomId);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("estimatedShippingCost",estimatedShippingCost);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("completePackage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickReceiveReturn")
	public ResponseEntity<Object> quickReceiveReturn(HttpSession session, @RequestParam(value="returnId") String returnId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("statusId",statusId);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickReceiveReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateIssuanceShipmentAndPoOnReceiveInventory")
	public ResponseEntity<Object> updateIssuanceShipmentAndPoOnReceiveInventory(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="quantityAccepted") BigDecimal quantityAccepted, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="unitCost", required=false) String unitCost, @RequestParam(value="orderCurrencyUnitPrice", required=false) String orderCurrencyUnitPrice, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("orderCurrencyUnitPrice",orderCurrencyUnitPrice);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateIssuanceShipmentAndPoOnReceiveInventory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentContactMechType")
	public ResponseEntity<Object> createShipmentContactMechType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentContactMechTypeId", required=false) String shipmentContactMechTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("shipmentContactMechTypeId",shipmentContactMechTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentContactMechType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addOrderShipmentToShipment")
	public ResponseEntity<Object> addOrderShipmentToShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addOrderShipmentToShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceShipmentReceiptRole")
	public ResponseEntity<Object> interfaceShipmentReceiptRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="receiptId") String receiptId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceShipmentReceiptRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/packSingleItem")
	public ResponseEntity<Object> packSingleItem(HttpSession session, @RequestParam(value="packageSeq") Integer packageSeq, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="pickerPartyId", required=false) String pickerPartyId, @RequestParam(value="handlingInstructions", required=false) String handlingInstructions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("packageSeq",packageSeq);
		paramMap.put("packingSession",packingSession);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("pickerPartyId",pickerPartyId);
		paramMap.put("handlingInstructions",handlingInstructions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("packSingleItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentBoxType")
	public ResponseEntity<Object> createShipmentBoxType(HttpSession session, @RequestParam(value="boxLength", required=false) BigDecimal boxLength, @RequestParam(value="boxWeight", required=false) BigDecimal boxWeight, @RequestParam(value="dimensionUomId", required=false) String dimensionUomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="boxWidth", required=false) BigDecimal boxWidth, @RequestParam(value="boxHeight", required=false) BigDecimal boxHeight, @RequestParam(value="shipmentBoxTypeId", required=false) String shipmentBoxTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("boxLength",boxLength);
		paramMap.put("boxWeight",boxWeight);
		paramMap.put("dimensionUomId",dimensionUomId);
		paramMap.put("description",description);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("boxWidth",boxWidth);
		paramMap.put("boxHeight",boxHeight);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentBoxType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceShipmentReceipt")
	public ResponseEntity<Object> interfaceShipmentReceipt(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="productId") String productId, @RequestParam(value="quantityAccepted") BigDecimal quantityAccepted, @RequestParam(value="quantityRejected") BigDecimal quantityRejected, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="rejectionId", required=false) String rejectionId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="receivedByUserLoginId", required=false) String receivedByUserLoginId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="datetimeReceived", required=false) Timestamp datetimeReceived, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="itemDescription", required=false) String itemDescription, @RequestParam(value="inventoryItemDetailSeqId", required=false) String inventoryItemDetailSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("productId",productId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("receivedByUserLoginId",receivedByUserLoginId);
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("datetimeReceived",datetimeReceived);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("itemDescription",itemDescription);
		paramMap.put("inventoryItemDetailSeqId",inventoryItemDetailSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceShipmentReceipt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/balanceItemIssuancesForShipment")
	public ResponseEntity<Object> balanceItemIssuancesForShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("balanceItemIssuancesForShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteQuantityBreak")
	public ResponseEntity<Object> deleteQuantityBreak(HttpSession session, @RequestParam(value="quantityBreakId") String quantityBreakId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantityBreakId",quantityBreakId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteQuantityBreak", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCarrierShipmentBoxType")
	public ResponseEntity<Object> createCarrierShipmentBoxType(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentBoxTypeId") String shipmentBoxTypeId, @RequestParam(value="packagingTypeCode", required=false) String packagingTypeCode, @RequestParam(value="oversizeCode", required=false) String oversizeCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("packagingTypeCode",packagingTypeCode);
		paramMap.put("oversizeCode",oversizeCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCarrierShipmentBoxType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setShipmentSettingsFromFacilities")
	public ResponseEntity<Object> setShipmentSettingsFromFacilities(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setShipmentSettingsFromFacilities", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeShipmentReceiptRole")
	public ResponseEntity<Object> removeShipmentReceiptRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="receiptId") String receiptId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeShipmentReceiptRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentAttribute")
	public ResponseEntity<Object> updateShipmentAttribute(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeOrderShipmentFromShipment")
	public ResponseEntity<Object> removeOrderShipmentFromShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeOrderShipmentFromShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentContactMechType")
	public ResponseEntity<Object> updateShipmentContactMechType(HttpSession session, @RequestParam(value="shipmentContactMechTypeId") String shipmentContactMechTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentContactMechTypeId",shipmentContactMechTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentContactMechType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentPackage")
	public ResponseEntity<Object> createShipmentPackage(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="boxLength", required=false) BigDecimal boxLength, @RequestParam(value="dimensionUomId", required=false) String dimensionUomId, @RequestParam(value="insuredValue", required=false) BigDecimal insuredValue, @RequestParam(value="weight", required=false) BigDecimal weight, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="boxHeight", required=false) BigDecimal boxHeight, @RequestParam(value="boxWidth", required=false) BigDecimal boxWidth, @RequestParam(value="shipmentBoxTypeId", required=false) String shipmentBoxTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("boxLength",boxLength);
		paramMap.put("dimensionUomId",dimensionUomId);
		paramMap.put("insuredValue",insuredValue);
		paramMap.put("weight",weight);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("boxHeight",boxHeight);
		paramMap.put("boxWidth",boxWidth);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentPackage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCarrierShipmentMethod")
	public ResponseEntity<Object> updateCarrierShipmentMethod(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="sequenceNumber", required=false) Long sequenceNumber, @RequestParam(value="carrierServiceCode", required=false) String carrierServiceCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("sequenceNumber",sequenceNumber);
		paramMap.put("carrierServiceCode",carrierServiceCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCarrierShipmentMethod", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentMethodType")
	public ResponseEntity<Object> deleteShipmentMethodType(HttpSession session, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentEstimate")
	public ResponseEntity<Object> createShipmentEstimate(HttpSession session, @RequestParam(value="productStoreShipMethId") String productStoreShipMethId, @RequestParam(value="flatPercent") BigDecimal flatPercent, @RequestParam(value="toGeo", required=false) String toGeo, @RequestParam(value="pmax", required=false) BigDecimal pmax, @RequestParam(value="wprice", required=false) BigDecimal wprice, @RequestParam(value="wuom", required=false) String wuom, @RequestParam(value="qprice", required=false) BigDecimal qprice, @RequestParam(value="wmin", required=false) BigDecimal wmin, @RequestParam(value="featurePercent", required=false) BigDecimal featurePercent, @RequestParam(value="featurePrice", required=false) BigDecimal featurePrice, @RequestParam(value="fromGeo", required=false) String fromGeo, @RequestParam(value="pmin", required=false) BigDecimal pmin, @RequestParam(value="puom", required=false) String puom, @RequestParam(value="oversizeUnit", required=false) BigDecimal oversizeUnit, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="productFeatureGroupId", required=false) String productFeatureGroupId, @RequestParam(value="oversizePrice", required=false) BigDecimal oversizePrice, @RequestParam(value="flatPrice", required=false) BigDecimal flatPrice, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="shippingPricePercent", required=false) BigDecimal shippingPricePercent, @RequestParam(value="qmax", required=false) BigDecimal qmax, @RequestParam(value="pprice", required=false) BigDecimal pprice, @RequestParam(value="weightBreakId", required=false) String weightBreakId, @RequestParam(value="quantityBreakId", required=false) String quantityBreakId, @RequestParam(value="priceBreakId", required=false) String priceBreakId, @RequestParam(value="flatItemPrice", required=false) BigDecimal flatItemPrice, @RequestParam(value="qmin", required=false) BigDecimal qmin, @RequestParam(value="quom", required=false) String quom, @RequestParam(value="wmax", required=false) BigDecimal wmax) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("flatPercent",flatPercent);
		paramMap.put("toGeo",toGeo);
		paramMap.put("pmax",pmax);
		paramMap.put("wprice",wprice);
		paramMap.put("wuom",wuom);
		paramMap.put("qprice",qprice);
		paramMap.put("wmin",wmin);
		paramMap.put("featurePercent",featurePercent);
		paramMap.put("featurePrice",featurePrice);
		paramMap.put("fromGeo",fromGeo);
		paramMap.put("pmin",pmin);
		paramMap.put("puom",puom);
		paramMap.put("oversizeUnit",oversizeUnit);
		paramMap.put("partyId",partyId);
		paramMap.put("productFeatureGroupId",productFeatureGroupId);
		paramMap.put("oversizePrice",oversizePrice);
		paramMap.put("flatPrice",flatPrice);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("shippingPricePercent",shippingPricePercent);
		paramMap.put("qmax",qmax);
		paramMap.put("pprice",pprice);
		paramMap.put("weightBreakId",weightBreakId);
		paramMap.put("quantityBreakId",quantityBreakId);
		paramMap.put("priceBreakId",priceBreakId);
		paramMap.put("flatItemPrice",flatItemPrice);
		paramMap.put("qmin",qmin);
		paramMap.put("quom",quom);
		paramMap.put("wmax",wmax);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createRejectionReason")
	public ResponseEntity<Object> createRejectionReason(HttpSession session, @RequestParam(value="rejectionId", required=false) String rejectionId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRejectionReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShippingDocument")
	public ResponseEntity<Object> deleteShippingDocument(HttpSession session, @RequestParam(value="documentId") String documentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("documentId",documentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShippingDocument", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createQuantityBreak")
	public ResponseEntity<Object> createQuantityBreak(HttpSession session, @RequestParam(value="thruQuantity", required=false) BigDecimal thruQuantity, @RequestParam(value="quantityBreakTypeId", required=false) String quantityBreakTypeId, @RequestParam(value="fromQuantity", required=false) BigDecimal fromQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("thruQuantity",thruQuantity);
		paramMap.put("quantityBreakTypeId",quantityBreakTypeId);
		paramMap.put("fromQuantity",fromQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createQuantityBreak", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentTypeAttr")
	public ResponseEntity<Object> updateShipmentTypeAttr(HttpSession session, @RequestParam(value="shipmentTypeId") String shipmentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentItemFeature")
	public ResponseEntity<Object> createShipmentItemFeature(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="productFeatureId") String productFeatureId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentItemFeature", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentContactMech")
	public ResponseEntity<Object> deleteShipmentContactMech(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentContactMechTypeId") String shipmentContactMechTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentContactMechTypeId",shipmentContactMechTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createItemIssuanceRole")
	public ResponseEntity<Object> createItemIssuanceRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="itemIssuanceId") String itemIssuanceId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createItemIssuanceRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentReceipt")
	public ResponseEntity<Object> updateShipmentReceipt(HttpSession session, @RequestParam(value="receiptId") String receiptId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="quantityAccepted", required=false) BigDecimal quantityAccepted, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="rejectionId", required=false) String rejectionId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="receivedByUserLoginId", required=false) String receivedByUserLoginId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId, @RequestParam(value="datetimeReceived", required=false) Timestamp datetimeReceived, @RequestParam(value="itemDescription", required=false) String itemDescription) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("receiptId",receiptId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("receivedByUserLoginId",receivedByUserLoginId);
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("datetimeReceived",datetimeReceived);
		paramMap.put("itemDescription",itemDescription);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentReceipt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createItemIssuance")
	public ResponseEntity<Object> createItemIssuance(HttpSession session, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="issuedDateTime", required=false) Timestamp issuedDateTime, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="issuedByUserLoginId", required=false) String issuedByUserLoginId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("issuedDateTime",issuedDateTime);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("issuedByUserLoginId",issuedByUserLoginId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createItemIssuance", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCarrierShipmentBoxType")
	public ResponseEntity<Object> deleteCarrierShipmentBoxType(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentBoxTypeId") String shipmentBoxTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCarrierShipmentBoxType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/completePack")
	public ResponseEntity<Object> completePack(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession, @RequestParam(value="additionalShippingCharge", required=false) BigDecimal additionalShippingCharge, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="dimensionUomId", required=false) String dimensionUomId, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="boxTypes", required=false) Map boxTypes, @RequestParam(value="pickerPartyId", required=false) String pickerPartyId, @RequestParam(value="forceComplete", required=false) Boolean forceComplete, @RequestParam(value="handlingInstructions", required=false) String handlingInstructions, @RequestParam(value="packageWeights", required=false) Map packageWeights) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("packingSession",packingSession);
		paramMap.put("additionalShippingCharge",additionalShippingCharge);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("dimensionUomId",dimensionUomId);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("boxTypes",boxTypes);
		paramMap.put("pickerPartyId",pickerPartyId);
		paramMap.put("forceComplete",forceComplete);
		paramMap.put("handlingInstructions",handlingInstructions);
		paramMap.put("packageWeights",packageWeights);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("completePack", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipment")
	public ResponseEntity<Object> createShipment(HttpSession session, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="latestCancelDate", required=false) Timestamp latestCancelDate, @RequestParam(value="estimatedReadyDate", required=false) Timestamp estimatedReadyDate, @RequestParam(value="estimatedArrivalWorkEffId", required=false) String estimatedArrivalWorkEffId, @RequestParam(value="additionalShippingCharge", required=false) BigDecimal additionalShippingCharge, @RequestParam(value="destinationTelecomNumberId", required=false) String destinationTelecomNumberId, @RequestParam(value="estimatedShipCost", required=false) BigDecimal estimatedShipCost, @RequestParam(value="estimatedShipWorkEffId", required=false) String estimatedShipWorkEffId, @RequestParam(value="destinationFacilityId", required=false) String destinationFacilityId, @RequestParam(value="addtlShippingChargeDesc", required=false) String addtlShippingChargeDesc, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="primaryReturnId", required=false) String primaryReturnId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="destinationContactMechId", required=false) String destinationContactMechId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="originTelecomNumberId", required=false) String originTelecomNumberId, @RequestParam(value="primaryOrderId", required=false) String primaryOrderId, @RequestParam(value="estimatedShipDate", required=false) Timestamp estimatedShipDate, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="shipmentTypeId", required=false) String shipmentTypeId, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="picklistBinId", required=false) String picklistBinId, @RequestParam(value="primaryShipGroupSeqId", required=false) String primaryShipGroupSeqId, @RequestParam(value="handlingInstructions", required=false) String handlingInstructions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("latestCancelDate",latestCancelDate);
		paramMap.put("estimatedReadyDate",estimatedReadyDate);
		paramMap.put("estimatedArrivalWorkEffId",estimatedArrivalWorkEffId);
		paramMap.put("additionalShippingCharge",additionalShippingCharge);
		paramMap.put("destinationTelecomNumberId",destinationTelecomNumberId);
		paramMap.put("estimatedShipCost",estimatedShipCost);
		paramMap.put("estimatedShipWorkEffId",estimatedShipWorkEffId);
		paramMap.put("destinationFacilityId",destinationFacilityId);
		paramMap.put("addtlShippingChargeDesc",addtlShippingChargeDesc);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("primaryReturnId",primaryReturnId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("destinationContactMechId",destinationContactMechId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("originTelecomNumberId",originTelecomNumberId);
		paramMap.put("primaryOrderId",primaryOrderId);
		paramMap.put("estimatedShipDate",estimatedShipDate);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("picklistBinId",picklistBinId);
		paramMap.put("primaryShipGroupSeqId",primaryShipGroupSeqId);
		paramMap.put("handlingInstructions",handlingInstructions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentMethodType")
	public ResponseEntity<Object> updateShipmentMethodType(HttpSession session, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueOrderItemShipGrpInvResToShipment")
	public ResponseEntity<Object> issueOrderItemShipGrpInvResToShipment(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="eventDate", required=false) Timestamp eventDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("eventDate",eventDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueOrderItemShipGrpInvResToShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setNextPackageSeq")
	public ResponseEntity<Object> setNextPackageSeq(HttpSession session, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("packingSession",packingSession);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setNextPackageSeq", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCarrierShipmentBoxType")
	public ResponseEntity<Object> updateCarrierShipmentBoxType(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentBoxTypeId") String shipmentBoxTypeId, @RequestParam(value="packagingTypeCode", required=false) String packagingTypeCode, @RequestParam(value="oversizeCode", required=false) String oversizeCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("packagingTypeCode",packagingTypeCode);
		paramMap.put("oversizeCode",oversizeCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCarrierShipmentBoxType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipment")
	public ResponseEntity<Object> updateShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="latestCancelDate", required=false) Timestamp latestCancelDate, @RequestParam(value="estimatedReadyDate", required=false) Timestamp estimatedReadyDate, @RequestParam(value="estimatedArrivalWorkEffId", required=false) String estimatedArrivalWorkEffId, @RequestParam(value="additionalShippingCharge", required=false) BigDecimal additionalShippingCharge, @RequestParam(value="destinationTelecomNumberId", required=false) String destinationTelecomNumberId, @RequestParam(value="estimatedShipCost", required=false) BigDecimal estimatedShipCost, @RequestParam(value="estimatedShipWorkEffId", required=false) String estimatedShipWorkEffId, @RequestParam(value="destinationFacilityId", required=false) String destinationFacilityId, @RequestParam(value="addtlShippingChargeDesc", required=false) String addtlShippingChargeDesc, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="primaryReturnId", required=false) String primaryReturnId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="destinationContactMechId", required=false) String destinationContactMechId, @RequestParam(value="originTelecomNumberId", required=false) String originTelecomNumberId, @RequestParam(value="primaryOrderId", required=false) String primaryOrderId, @RequestParam(value="estimatedShipDate", required=false) Timestamp estimatedShipDate, @RequestParam(value="partyIdTo", required=false) String partyIdTo, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="shipmentTypeId", required=false) String shipmentTypeId, @RequestParam(value="picklistBinId", required=false) String picklistBinId, @RequestParam(value="primaryShipGroupSeqId", required=false) String primaryShipGroupSeqId, @RequestParam(value="handlingInstructions", required=false) String handlingInstructions, @RequestParam(value="eventDate", required=false) Timestamp eventDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("latestCancelDate",latestCancelDate);
		paramMap.put("estimatedReadyDate",estimatedReadyDate);
		paramMap.put("estimatedArrivalWorkEffId",estimatedArrivalWorkEffId);
		paramMap.put("additionalShippingCharge",additionalShippingCharge);
		paramMap.put("destinationTelecomNumberId",destinationTelecomNumberId);
		paramMap.put("estimatedShipCost",estimatedShipCost);
		paramMap.put("estimatedShipWorkEffId",estimatedShipWorkEffId);
		paramMap.put("destinationFacilityId",destinationFacilityId);
		paramMap.put("addtlShippingChargeDesc",addtlShippingChargeDesc);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("primaryReturnId",primaryReturnId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("statusId",statusId);
		paramMap.put("destinationContactMechId",destinationContactMechId);
		paramMap.put("originTelecomNumberId",originTelecomNumberId);
		paramMap.put("primaryOrderId",primaryOrderId);
		paramMap.put("estimatedShipDate",estimatedShipDate);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("picklistBinId",picklistBinId);
		paramMap.put("primaryShipGroupSeqId",primaryShipGroupSeqId);
		paramMap.put("handlingInstructions",handlingInstructions);
		paramMap.put("eventDate",eventDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentCostEstimate")
	public ResponseEntity<Object> updateShipmentCostEstimate(HttpSession session, @RequestParam(value="shipmentCostEstimateId") String shipmentCostEstimateId, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="quantityUnitPrice", required=false) BigDecimal quantityUnitPrice, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="featurePercent", required=false) BigDecimal featurePercent, @RequestParam(value="featurePrice", required=false) BigDecimal featurePrice, @RequestParam(value="weightUnitPrice", required=false) BigDecimal weightUnitPrice, @RequestParam(value="oversizeUnit", required=false) BigDecimal oversizeUnit, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="productFeatureGroupId", required=false) String productFeatureGroupId, @RequestParam(value="oversizePrice", required=false) BigDecimal oversizePrice, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="geoIdTo", required=false) String geoIdTo, @RequestParam(value="orderPricePercent", required=false) BigDecimal orderPricePercent, @RequestParam(value="orderItemFlatPrice", required=false) BigDecimal orderItemFlatPrice, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="shippingPricePercent", required=false) BigDecimal shippingPricePercent, @RequestParam(value="weightBreakId", required=false) String weightBreakId, @RequestParam(value="quantityBreakId", required=false) String quantityBreakId, @RequestParam(value="priceBreakId", required=false) String priceBreakId, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="geoIdFrom", required=false) String geoIdFrom, @RequestParam(value="orderFlatPrice", required=false) BigDecimal orderFlatPrice, @RequestParam(value="carrierRoleTypeId", required=false) String carrierRoleTypeId, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="priceUnitPrice", required=false) BigDecimal priceUnitPrice, @RequestParam(value="priceUomId", required=false) String priceUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentCostEstimateId",shipmentCostEstimateId);
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("quantityUnitPrice",quantityUnitPrice);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("featurePercent",featurePercent);
		paramMap.put("featurePrice",featurePrice);
		paramMap.put("weightUnitPrice",weightUnitPrice);
		paramMap.put("oversizeUnit",oversizeUnit);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("productFeatureGroupId",productFeatureGroupId);
		paramMap.put("oversizePrice",oversizePrice);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("geoIdTo",geoIdTo);
		paramMap.put("orderPricePercent",orderPricePercent);
		paramMap.put("orderItemFlatPrice",orderItemFlatPrice);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("shippingPricePercent",shippingPricePercent);
		paramMap.put("weightBreakId",weightBreakId);
		paramMap.put("quantityBreakId",quantityBreakId);
		paramMap.put("priceBreakId",priceBreakId);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("geoIdFrom",geoIdFrom);
		paramMap.put("orderFlatPrice",orderFlatPrice);
		paramMap.put("carrierRoleTypeId",carrierRoleTypeId);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("priceUnitPrice",priceUnitPrice);
		paramMap.put("priceUomId",priceUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentCostEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateRejectionReason")
	public ResponseEntity<Object> updateRejectionReason(HttpSession session, @RequestParam(value="rejectionId") String rejectionId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRejectionReason", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentItem")
	public ResponseEntity<Object> createShipmentItem(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="shipmentContentDescription", required=false) String shipmentContentDescription, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("shipmentContentDescription",shipmentContentDescription);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearLastPackage")
	public ResponseEntity<Object> clearLastPackage(HttpSession session, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("packingSession",packingSession);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearLastPackage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentRouteSegment")
	public ResponseEntity<Object> updateShipmentRouteSegment(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="actualOtherCost", required=false) BigDecimal actualOtherCost, @RequestParam(value="carrierDeliveryZone", required=false) String carrierDeliveryZone, @RequestParam(value="thirdPartyPostalCode", required=false) String thirdPartyPostalCode, @RequestParam(value="destTelecomNumberId", required=false) String destTelecomNumberId, @RequestParam(value="carrierRestrictionCodes", required=false) String carrierRestrictionCodes, @RequestParam(value="trackingIdNumber", required=false) String trackingIdNumber, @RequestParam(value="deliveryId", required=false) String deliveryId, @RequestParam(value="carrierServiceStatusId", required=false) String carrierServiceStatusId, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="trackingDigest", required=false) String trackingDigest, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="actualCost", required=false) BigDecimal actualCost, @RequestParam(value="upsHighValueReport", required=false) byte[] upsHighValueReport, @RequestParam(value="actualArrivalDate", required=false) Timestamp actualArrivalDate, @RequestParam(value="actualServiceCost", required=false) BigDecimal actualServiceCost, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="carrierRestrictionDesc", required=false) String carrierRestrictionDesc, @RequestParam(value="actualTransportCost", required=false) BigDecimal actualTransportCost, @RequestParam(value="billingWeightUomId", required=false) String billingWeightUomId, @RequestParam(value="homeDeliveryDate", required=false) Timestamp homeDeliveryDate, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="destFacilityId", required=false) String destFacilityId, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="lastUpdatedDate", required=false) Timestamp lastUpdatedDate, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="billingWeight", required=false) BigDecimal billingWeight, @RequestParam(value="originTelecomNumberId", required=false) String originTelecomNumberId, @RequestParam(value="thirdPartyCountryGeoCode", required=false) String thirdPartyCountryGeoCode, @RequestParam(value="destContactMechId", required=false) String destContactMechId, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="homeDeliveryType", required=false) String homeDeliveryType, @RequestParam(value="thirdPartyAccountNumber", required=false) String thirdPartyAccountNumber, @RequestParam(value="updatedByUserLoginId", required=false) String updatedByUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("actualOtherCost",actualOtherCost);
		paramMap.put("carrierDeliveryZone",carrierDeliveryZone);
		paramMap.put("thirdPartyPostalCode",thirdPartyPostalCode);
		paramMap.put("destTelecomNumberId",destTelecomNumberId);
		paramMap.put("carrierRestrictionCodes",carrierRestrictionCodes);
		paramMap.put("trackingIdNumber",trackingIdNumber);
		paramMap.put("deliveryId",deliveryId);
		paramMap.put("carrierServiceStatusId",carrierServiceStatusId);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("trackingDigest",trackingDigest);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("actualCost",actualCost);
		paramMap.put("upsHighValueReport",upsHighValueReport);
		paramMap.put("actualArrivalDate",actualArrivalDate);
		paramMap.put("actualServiceCost",actualServiceCost);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("carrierRestrictionDesc",carrierRestrictionDesc);
		paramMap.put("actualTransportCost",actualTransportCost);
		paramMap.put("billingWeightUomId",billingWeightUomId);
		paramMap.put("homeDeliveryDate",homeDeliveryDate);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("destFacilityId",destFacilityId);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("lastUpdatedDate",lastUpdatedDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("billingWeight",billingWeight);
		paramMap.put("originTelecomNumberId",originTelecomNumberId);
		paramMap.put("thirdPartyCountryGeoCode",thirdPartyCountryGeoCode);
		paramMap.put("destContactMechId",destContactMechId);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("homeDeliveryType",homeDeliveryType);
		paramMap.put("thirdPartyAccountNumber",thirdPartyAccountNumber);
		paramMap.put("updatedByUserLoginId",updatedByUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentRouteSegment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/duplicateShipmentRouteSegment")
	public ResponseEntity<Object> duplicateShipmentRouteSegment(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="actualOtherCost", required=false) BigDecimal actualOtherCost, @RequestParam(value="carrierDeliveryZone", required=false) String carrierDeliveryZone, @RequestParam(value="thirdPartyPostalCode", required=false) String thirdPartyPostalCode, @RequestParam(value="destTelecomNumberId", required=false) String destTelecomNumberId, @RequestParam(value="carrierRestrictionCodes", required=false) String carrierRestrictionCodes, @RequestParam(value="trackingIdNumber", required=false) String trackingIdNumber, @RequestParam(value="deliveryId", required=false) String deliveryId, @RequestParam(value="carrierServiceStatusId", required=false) String carrierServiceStatusId, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="trackingDigest", required=false) String trackingDigest, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="actualCost", required=false) BigDecimal actualCost, @RequestParam(value="upsHighValueReport", required=false) byte[] upsHighValueReport, @RequestParam(value="actualArrivalDate", required=false) Timestamp actualArrivalDate, @RequestParam(value="actualServiceCost", required=false) BigDecimal actualServiceCost, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="carrierRestrictionDesc", required=false) String carrierRestrictionDesc, @RequestParam(value="actualTransportCost", required=false) BigDecimal actualTransportCost, @RequestParam(value="billingWeightUomId", required=false) String billingWeightUomId, @RequestParam(value="homeDeliveryDate", required=false) Timestamp homeDeliveryDate, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="destFacilityId", required=false) String destFacilityId, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="lastUpdatedDate", required=false) Timestamp lastUpdatedDate, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="billingWeight", required=false) BigDecimal billingWeight, @RequestParam(value="originTelecomNumberId", required=false) String originTelecomNumberId, @RequestParam(value="thirdPartyCountryGeoCode", required=false) String thirdPartyCountryGeoCode, @RequestParam(value="destContactMechId", required=false) String destContactMechId, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="homeDeliveryType", required=false) String homeDeliveryType, @RequestParam(value="thirdPartyAccountNumber", required=false) String thirdPartyAccountNumber, @RequestParam(value="updatedByUserLoginId", required=false) String updatedByUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("actualOtherCost",actualOtherCost);
		paramMap.put("carrierDeliveryZone",carrierDeliveryZone);
		paramMap.put("thirdPartyPostalCode",thirdPartyPostalCode);
		paramMap.put("destTelecomNumberId",destTelecomNumberId);
		paramMap.put("carrierRestrictionCodes",carrierRestrictionCodes);
		paramMap.put("trackingIdNumber",trackingIdNumber);
		paramMap.put("deliveryId",deliveryId);
		paramMap.put("carrierServiceStatusId",carrierServiceStatusId);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("trackingDigest",trackingDigest);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("actualCost",actualCost);
		paramMap.put("upsHighValueReport",upsHighValueReport);
		paramMap.put("actualArrivalDate",actualArrivalDate);
		paramMap.put("actualServiceCost",actualServiceCost);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("carrierRestrictionDesc",carrierRestrictionDesc);
		paramMap.put("actualTransportCost",actualTransportCost);
		paramMap.put("billingWeightUomId",billingWeightUomId);
		paramMap.put("homeDeliveryDate",homeDeliveryDate);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("destFacilityId",destFacilityId);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("lastUpdatedDate",lastUpdatedDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("billingWeight",billingWeight);
		paramMap.put("originTelecomNumberId",originTelecomNumberId);
		paramMap.put("thirdPartyCountryGeoCode",thirdPartyCountryGeoCode);
		paramMap.put("destContactMechId",destContactMechId);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("homeDeliveryType",homeDeliveryType);
		paramMap.put("thirdPartyAccountNumber",thirdPartyAccountNumber);
		paramMap.put("updatedByUserLoginId",updatedByUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("duplicateShipmentRouteSegment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setShipmentStatusPackedAndShipped")
	public ResponseEntity<Object> setShipmentStatusPackedAndShipped(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setShipmentStatusPackedAndShipped", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/verifySingleItem")
	public ResponseEntity<Object> verifySingleItem(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="verifyPickSession") org.apache.ofbiz.shipment.verify.VerifyPickSession verifyPickSession, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("verifyPickSession",verifyPickSession);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("verifySingleItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentPackageRouteSeg")
	public ResponseEntity<Object> deleteShipmentPackageRouteSeg(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentPackageRouteSeg", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeShipmentEstimate")
	public ResponseEntity<Object> removeShipmentEstimate(HttpSession session, @RequestParam(value="shipmentCostEstimateId") String shipmentCostEstimateId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentCostEstimateId",shipmentCostEstimateId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeShipmentEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentAndItemsForReturn")
	public ResponseEntity<Object> createShipmentAndItemsForReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentAndItemsForReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentAttribute")
	public ResponseEntity<Object> createShipmentAttribute(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearPackLine")
	public ResponseEntity<Object> clearPackLine(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="productId") String productId, @RequestParam(value="orderId") String orderId, @RequestParam(value="packageSeqId") Integer packageSeqId, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("packageSeqId",packageSeqId);
		paramMap.put("packingSession",packingSession);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearPackLine", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShippingDocument")
	public ResponseEntity<Object> updateShippingDocument(HttpSession session, @RequestParam(value="documentId") String documentId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("documentId",documentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("description",description);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShippingDocument", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/receiveInventoryProduct")
	public ResponseEntity<Object> receiveInventoryProduct(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productId") String productId, @RequestParam(value="quantityAccepted") BigDecimal quantityAccepted, @RequestParam(value="quantityRejected") BigDecimal quantityRejected, @RequestParam(value="inventoryItemTypeId") String inventoryItemTypeId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="binNumber", required=false) String binNumber, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="priorityOrderItemSeqId", required=false) String priorityOrderItemSeqId, @RequestParam(value="inventoryItemDetailSeqId", required=false) String inventoryItemDetailSeqId, @RequestParam(value="oldAvailableToPromise", required=false) BigDecimal oldAvailableToPromise, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="itemDescription", required=false) String itemDescription, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="containerId", required=false) String containerId, @RequestParam(value="locationSeqId", required=false) String locationSeqId, @RequestParam(value="quantityOnHandDiff", required=false) BigDecimal quantityOnHandDiff, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="serialNumber", required=false) Long serialNumber, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="availableToPromiseDiff", required=false) BigDecimal availableToPromiseDiff, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="softIdentifier", required=false) Long softIdentifier, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="currentInventoryItemId", required=false) String currentInventoryItemId, @RequestParam(value="accountingQuantityDiff", required=false) BigDecimal accountingQuantityDiff, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="datetimeManufactured", required=false) Timestamp datetimeManufactured, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="orderCurrencyUnitPrice", required=false) String orderCurrencyUnitPrice, @RequestParam(value="expireDate", required=false) Timestamp expireDate, @RequestParam(value="oldQuantityOnHand", required=false) BigDecimal oldQuantityOnHand, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="accountingQuantityTotal", required=false) BigDecimal accountingQuantityTotal, @RequestParam(value="rejectionId", required=false) String rejectionId, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="activationValidThru", required=false) Timestamp activationValidThru, @RequestParam(value="activationNumber", required=false) Long activationNumber, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="receivedByUserLoginId", required=false) String receivedByUserLoginId, @RequestParam(value="priorityOrderId", required=false) String priorityOrderId, @RequestParam(value="unitCost", required=false) BigDecimal unitCost, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId, @RequestParam(value="datetimeReceived", required=false) Timestamp datetimeReceived, @RequestParam(value="effectiveDate", required=false) Timestamp effectiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("orderId",orderId);
		paramMap.put("binNumber",binNumber);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("priorityOrderItemSeqId",priorityOrderItemSeqId);
		paramMap.put("inventoryItemDetailSeqId",inventoryItemDetailSeqId);
		paramMap.put("oldAvailableToPromise",oldAvailableToPromise);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("itemDescription",itemDescription);
		paramMap.put("partyId",partyId);
		paramMap.put("containerId",containerId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("quantityOnHandDiff",quantityOnHandDiff);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("uomId",uomId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("statusId",statusId);
		paramMap.put("availableToPromiseDiff",availableToPromiseDiff);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("softIdentifier",softIdentifier);
		paramMap.put("description",description);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("currentInventoryItemId",currentInventoryItemId);
		paramMap.put("accountingQuantityDiff",accountingQuantityDiff);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("datetimeManufactured",datetimeManufactured);
		paramMap.put("returnId",returnId);
		paramMap.put("orderCurrencyUnitPrice",orderCurrencyUnitPrice);
		paramMap.put("expireDate",expireDate);
		paramMap.put("oldQuantityOnHand",oldQuantityOnHand);
		paramMap.put("receiptId",receiptId);
		paramMap.put("comments",comments);
		paramMap.put("lotId",lotId);
		paramMap.put("accountingQuantityTotal",accountingQuantityTotal);
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("activationValidThru",activationValidThru);
		paramMap.put("activationNumber",activationNumber);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("receivedByUserLoginId",receivedByUserLoginId);
		paramMap.put("priorityOrderId",priorityOrderId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("datetimeReceived",datetimeReceived);
		paramMap.put("effectiveDate",effectiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("receiveInventoryProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentPackageContent")
	public ResponseEntity<Object> deleteShipmentPackageContent(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentPackageContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrderItemIssuanceFromSalesShipment")
	public ResponseEntity<Object> cancelOrderItemIssuanceFromSalesShipment(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelOrderItemIssuanceFromSalesShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentBoxType")
	public ResponseEntity<Object> updateShipmentBoxType(HttpSession session, @RequestParam(value="shipmentBoxTypeId") String shipmentBoxTypeId, @RequestParam(value="boxLength", required=false) BigDecimal boxLength, @RequestParam(value="boxWeight", required=false) BigDecimal boxWeight, @RequestParam(value="dimensionUomId", required=false) String dimensionUomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="boxWidth", required=false) BigDecimal boxWidth, @RequestParam(value="boxHeight", required=false) BigDecimal boxHeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("boxLength",boxLength);
		paramMap.put("boxWeight",boxWeight);
		paramMap.put("dimensionUomId",dimensionUomId);
		paramMap.put("description",description);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("boxWidth",boxWidth);
		paramMap.put("boxHeight",boxHeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentBoxType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelReceivedItems")
	public ResponseEntity<Object> cancelReceivedItems(HttpSession session, @RequestParam(value="receiptId") String receiptId, @RequestParam(value="facilityId", required=false) String facilityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("receiptId",receiptId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelReceivedItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipment")
	public ResponseEntity<Object> deleteShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentForReturn")
	public ResponseEntity<Object> createShipmentForReturn(HttpSession session, @RequestParam(value="returnId") String returnId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("returnId",returnId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentForReturn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/returnInventoryItemIssuedToFixedAssetMaint")
	public ResponseEntity<Object> returnInventoryItemIssuedToFixedAssetMaint(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("returnInventoryItemIssuedToFixedAssetMaint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearPackAll")
	public ResponseEntity<Object> clearPackAll(HttpSession session, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("packingSession",packingSession);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearPackAll", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentPackageRouteSeg")
	public ResponseEntity<Object> createShipmentPackageRouteSeg(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId, @RequestParam(value="labelIntlSignImage", required=false) byte[] labelIntlSignImage, @RequestParam(value="trackingCode", required=false) String trackingCode, @RequestParam(value="boxNumber", required=false) String boxNumber, @RequestParam(value="packageServiceCost", required=false) BigDecimal packageServiceCost, @RequestParam(value="packageOtherCost", required=false) BigDecimal packageOtherCost, @RequestParam(value="labelImage", required=false) byte[] labelImage, @RequestParam(value="labelPrinted", required=false) String labelPrinted, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="internationalInvoice", required=false) byte[] internationalInvoice, @RequestParam(value="packageTransportCost", required=false) BigDecimal packageTransportCost, @RequestParam(value="codAmount", required=false) BigDecimal codAmount, @RequestParam(value="insuredAmount", required=false) BigDecimal insuredAmount, @RequestParam(value="labelHtml", required=false) String labelHtml) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("labelIntlSignImage",labelIntlSignImage);
		paramMap.put("trackingCode",trackingCode);
		paramMap.put("boxNumber",boxNumber);
		paramMap.put("packageServiceCost",packageServiceCost);
		paramMap.put("packageOtherCost",packageOtherCost);
		paramMap.put("labelImage",labelImage);
		paramMap.put("labelPrinted",labelPrinted);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("internationalInvoice",internationalInvoice);
		paramMap.put("packageTransportCost",packageTransportCost);
		paramMap.put("codAmount",codAmount);
		paramMap.put("insuredAmount",insuredAmount);
		paramMap.put("labelHtml",labelHtml);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentPackageRouteSeg", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateItemIssuance")
	public ResponseEntity<Object> updateItemIssuance(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="issuedDateTime", required=false) Timestamp issuedDateTime, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId, @RequestParam(value="issuedByUserLoginId", required=false) String issuedByUserLoginId, @RequestParam(value="cancelQuantity", required=false) BigDecimal cancelQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("issuedDateTime",issuedDateTime);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("issuedByUserLoginId",issuedByUserLoginId);
		paramMap.put("cancelQuantity",cancelQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateItemIssuance", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentPackageContent")
	public ResponseEntity<Object> updateShipmentPackageContent(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="subProductQuantity", required=false) BigDecimal subProductQuantity, @RequestParam(value="subProductId", required=false) String subProductId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("subProductQuantity",subProductQuantity);
		paramMap.put("subProductId",subProductId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentPackageContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickShipEntireOrder")
	public ResponseEntity<Object> quickShipEntireOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="setPackedOnly", required=false) String setPackedOnly, @RequestParam(value="eventDate", required=false) Timestamp eventDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("setPackedOnly",setPackedOnly);
		paramMap.put("eventDate",eventDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickShipEntireOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addShipmentContentToPackage")
	public ResponseEntity<Object> addShipmentContentToPackage(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="subProductQuantity", required=false) BigDecimal subProductQuantity, @RequestParam(value="subProductId", required=false) String subProductId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("subProductQuantity",subProductQuantity);
		paramMap.put("subProductId",subProductId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addShipmentContentToPackage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkCancelItemIssuanceAndOrderShipmentFromShipment")
	public ResponseEntity<Object> checkCancelItemIssuanceAndOrderShipmentFromShipment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCancelItemIssuanceAndOrderShipmentFromShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentItem")
	public ResponseEntity<Object> deleteShipmentItem(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentReceipt")
	public ResponseEntity<Object> deleteShipmentReceipt(HttpSession session, @RequestParam(value="receiptId") String receiptId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("receiptId",receiptId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentReceipt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentReceipt")
	public ResponseEntity<Object> createShipmentReceipt(HttpSession session, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="quantityAccepted", required=false) BigDecimal quantityAccepted, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="inventoryItemDetailSeqId", required=false) String inventoryItemDetailSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="rejectionId", required=false) String rejectionId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="receivedByUserLoginId", required=false) String receivedByUserLoginId, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId, @RequestParam(value="datetimeReceived", required=false) Timestamp datetimeReceived, @RequestParam(value="itemDescription", required=false) String itemDescription) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("inventoryItemDetailSeqId",inventoryItemDetailSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("receivedByUserLoginId",receivedByUserLoginId);
		paramMap.put("returnId",returnId);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("datetimeReceived",datetimeReceived);
		paramMap.put("itemDescription",itemDescription);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentReceipt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueSerializedInvToShipmentPackageAndSetTracking")
	public ResponseEntity<Object> issueSerializedInvToShipmentPackageAndSetTracking(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="promisedDatetime") Timestamp promisedDatetime, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="trackingNum", required=false) String trackingNum, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="quantityNotReserved", required=false) BigDecimal quantityNotReserved, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="reservedDatetime", required=false) Timestamp reservedDatetime, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="sequenceId", required=false) Long sequenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("promisedDatetime",promisedDatetime);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("trackingNum",trackingNum);
		paramMap.put("quantity",quantity);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("productId",productId);
		paramMap.put("quantityNotReserved",quantityNotReserved);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("reservedDatetime",reservedDatetime);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("sequenceId",sequenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueSerializedInvToShipmentPackageAndSetTracking", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/verifyBulkItem")
	public ResponseEntity<Object> verifyBulkItem(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="verifyPickSession") org.apache.ofbiz.shipment.verify.VerifyPickSession verifyPickSession, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="productMap", required=false) Map productMap, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="selectedMap", required=false) Map selectedMap, @RequestParam(value="originGeoIdMap", required=false) Map originGeoIdMap, @RequestParam(value="itemMap", required=false) Map itemMap, @RequestParam(value="quantityMap", required=false) Map quantityMap, @RequestParam(value="pickerPartyId", required=false) String pickerPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("verifyPickSession",verifyPickSession);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("productMap",productMap);
		paramMap.put("facilityId",facilityId);
		paramMap.put("selectedMap",selectedMap);
		paramMap.put("originGeoIdMap",originGeoIdMap);
		paramMap.put("itemMap",itemMap);
		paramMap.put("quantityMap",quantityMap);
		paramMap.put("pickerPartyId",pickerPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("verifyBulkItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickShipPurchaseOrder")
	public ResponseEntity<Object> quickShipPurchaseOrder(HttpSession session, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickShipPurchaseOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickScheduleShipmentRouteSegment")
	public ResponseEntity<Object> quickScheduleShipmentRouteSegment(HttpSession session, @RequestParam(value="shipmentRouteSegmentId") String shipmentRouteSegmentId, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickScheduleShipmentRouteSegment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getShipmentPackageValueFromOrders")
	public ResponseEntity<Object> getShipmentPackageValueFromOrders(HttpSession session, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getShipmentPackageValueFromOrders", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/savePackagesInfo")
	public ResponseEntity<Object> savePackagesInfo(HttpSession session, @RequestParam(value="weightPackageSession") org.apache.ofbiz.shipment.weightPackage.WeightPackageSession weightPackageSession, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="facilityId", required=false) String facilityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("weightPackageSession",weightPackageSession);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("savePackagesInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueOrderItemToShipmentAndReceiveAgainstPO")
	public ResponseEntity<Object> issueOrderItemToShipmentAndReceiveAgainstPO(HttpSession session, @RequestParam(value="orderItemSeqId") String orderItemSeqId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="orderId") String orderId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="quantityAccepted", required=false) BigDecimal quantityAccepted, @RequestParam(value="binNumber", required=false) String binNumber, @RequestParam(value="maintHistSeqId", required=false) String maintHistSeqId, @RequestParam(value="priorityOrderItemSeqId", required=false) String priorityOrderItemSeqId, @RequestParam(value="inventoryItemDetailSeqId", required=false) String inventoryItemDetailSeqId, @RequestParam(value="oldAvailableToPromise", required=false) BigDecimal oldAvailableToPromise, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="reasonEnumId", required=false) String reasonEnumId, @RequestParam(value="itemDescription", required=false) String itemDescription, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="containerId", required=false) String containerId, @RequestParam(value="locationSeqId", required=false) String locationSeqId, @RequestParam(value="quantityOnHandDiff", required=false) BigDecimal quantityOnHandDiff, @RequestParam(value="serialNumber", required=false) Long serialNumber, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId, @RequestParam(value="quantityRejected", required=false) BigDecimal quantityRejected, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="availableToPromiseDiff", required=false) BigDecimal availableToPromiseDiff, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="softIdentifier", required=false) Long softIdentifier, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="currentInventoryItemId", required=false) String currentInventoryItemId, @RequestParam(value="accountingQuantityDiff", required=false) BigDecimal accountingQuantityDiff, @RequestParam(value="itemIssuanceId", required=false) String itemIssuanceId, @RequestParam(value="datetimeManufactured", required=false) Timestamp datetimeManufactured, @RequestParam(value="returnId", required=false) String returnId, @RequestParam(value="orderCurrencyUnitPrice", required=false) String orderCurrencyUnitPrice, @RequestParam(value="expireDate", required=false) Timestamp expireDate, @RequestParam(value="oldQuantityOnHand", required=false) BigDecimal oldQuantityOnHand, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="lotId", required=false) String lotId, @RequestParam(value="accountingQuantityTotal", required=false) BigDecimal accountingQuantityTotal, @RequestParam(value="rejectionId", required=false) String rejectionId, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="activationValidThru", required=false) Timestamp activationValidThru, @RequestParam(value="activationNumber", required=false) Long activationNumber, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="receivedByUserLoginId", required=false) String receivedByUserLoginId, @RequestParam(value="priorityOrderId", required=false) String priorityOrderId, @RequestParam(value="unitCost", required=false) BigDecimal unitCost, @RequestParam(value="returnItemSeqId", required=false) String returnItemSeqId, @RequestParam(value="datetimeReceived", required=false) Timestamp datetimeReceived, @RequestParam(value="effectiveDate", required=false) Timestamp effectiveDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("orderId",orderId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("quantityAccepted",quantityAccepted);
		paramMap.put("binNumber",binNumber);
		paramMap.put("maintHistSeqId",maintHistSeqId);
		paramMap.put("priorityOrderItemSeqId",priorityOrderItemSeqId);
		paramMap.put("inventoryItemDetailSeqId",inventoryItemDetailSeqId);
		paramMap.put("oldAvailableToPromise",oldAvailableToPromise);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("reasonEnumId",reasonEnumId);
		paramMap.put("itemDescription",itemDescription);
		paramMap.put("partyId",partyId);
		paramMap.put("containerId",containerId);
		paramMap.put("locationSeqId",locationSeqId);
		paramMap.put("quantityOnHandDiff",quantityOnHandDiff);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("productId",productId);
		paramMap.put("uomId",uomId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("quantityRejected",quantityRejected);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("statusId",statusId);
		paramMap.put("availableToPromiseDiff",availableToPromiseDiff);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("softIdentifier",softIdentifier);
		paramMap.put("description",description);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("currentInventoryItemId",currentInventoryItemId);
		paramMap.put("accountingQuantityDiff",accountingQuantityDiff);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("datetimeManufactured",datetimeManufactured);
		paramMap.put("returnId",returnId);
		paramMap.put("orderCurrencyUnitPrice",orderCurrencyUnitPrice);
		paramMap.put("expireDate",expireDate);
		paramMap.put("oldQuantityOnHand",oldQuantityOnHand);
		paramMap.put("receiptId",receiptId);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("lotId",lotId);
		paramMap.put("accountingQuantityTotal",accountingQuantityTotal);
		paramMap.put("rejectionId",rejectionId);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("activationValidThru",activationValidThru);
		paramMap.put("activationNumber",activationNumber);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("receivedByUserLoginId",receivedByUserLoginId);
		paramMap.put("priorityOrderId",priorityOrderId);
		paramMap.put("unitCost",unitCost);
		paramMap.put("returnItemSeqId",returnItemSeqId);
		paramMap.put("datetimeReceived",datetimeReceived);
		paramMap.put("effectiveDate",effectiveDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueOrderItemToShipmentAndReceiveAgainstPO", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentCostEstimate")
	public ResponseEntity<Object> deleteShipmentCostEstimate(HttpSession session, @RequestParam(value="shipmentCostEstimateId") String shipmentCostEstimateId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentCostEstimateId",shipmentCostEstimateId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentCostEstimate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/completeVerifiedPick")
	public ResponseEntity<Object> completeVerifiedPick(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="verifyPickSession") org.apache.ofbiz.shipment.verify.VerifyPickSession verifyPickSession, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="pickerPartyId", required=false) String pickerPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("verifyPickSession",verifyPickSession);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("pickerPartyId",pickerPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("completeVerifiedPick", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentContactMechType")
	public ResponseEntity<Object> deleteShipmentContactMechType(HttpSession session, @RequestParam(value="shipmentContactMechTypeId") String shipmentContactMechTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentContactMechTypeId",shipmentContactMechTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentContactMechType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentPackage")
	public ResponseEntity<Object> updateShipmentPackage(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId, @RequestParam(value="boxLength", required=false) BigDecimal boxLength, @RequestParam(value="dateCreated", required=false) Timestamp dateCreated, @RequestParam(value="dimensionUomId", required=false) String dimensionUomId, @RequestParam(value="insuredValue", required=false) BigDecimal insuredValue, @RequestParam(value="weight", required=false) BigDecimal weight, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="boxHeight", required=false) BigDecimal boxHeight, @RequestParam(value="boxWidth", required=false) BigDecimal boxWidth, @RequestParam(value="shipmentBoxTypeId", required=false) String shipmentBoxTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("boxLength",boxLength);
		paramMap.put("dateCreated",dateCreated);
		paramMap.put("dimensionUomId",dimensionUomId);
		paramMap.put("insuredValue",insuredValue);
		paramMap.put("weight",weight);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("boxHeight",boxHeight);
		paramMap.put("boxWidth",boxWidth);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentPackage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteItemIssuance")
	public ResponseEntity<Object> deleteItemIssuance(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteItemIssuance", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentStatus")
	public ResponseEntity<Object> createShipmentStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="statusDate", required=false) Timestamp statusDate, @RequestParam(value="changeByUserLoginId", required=false) String changeByUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("statusDate",statusDate);
		paramMap.put("changeByUserLoginId",changeByUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePackedLine")
	public ResponseEntity<Object> updatePackedLine(HttpSession session, @RequestParam(value="weightPackageSession") org.apache.ofbiz.shipment.weightPackage.WeightPackageSession weightPackageSession, @RequestParam(value="weightPackageSeqId") Integer weightPackageSeqId, @RequestParam(value="packageHeight", required=false) BigDecimal packageHeight, @RequestParam(value="packageWeight", required=false) BigDecimal packageWeight, @RequestParam(value="packageLength", required=false) BigDecimal packageLength, @RequestParam(value="packageWidth", required=false) BigDecimal packageWidth, @RequestParam(value="shipmentBoxTypeId", required=false) String shipmentBoxTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("weightPackageSession",weightPackageSession);
		paramMap.put("weightPackageSeqId",weightPackageSeqId);
		paramMap.put("packageHeight",packageHeight);
		paramMap.put("packageWeight",packageWeight);
		paramMap.put("packageLength",packageLength);
		paramMap.put("packageWidth",packageWidth);
		paramMap.put("shipmentBoxTypeId",shipmentBoxTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePackedLine", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentRouteSegment")
	public ResponseEntity<Object> createShipmentRouteSegment(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="actualOtherCost", required=false) BigDecimal actualOtherCost, @RequestParam(value="carrierDeliveryZone", required=false) String carrierDeliveryZone, @RequestParam(value="thirdPartyPostalCode", required=false) String thirdPartyPostalCode, @RequestParam(value="destTelecomNumberId", required=false) String destTelecomNumberId, @RequestParam(value="carrierRestrictionCodes", required=false) String carrierRestrictionCodes, @RequestParam(value="trackingIdNumber", required=false) String trackingIdNumber, @RequestParam(value="deliveryId", required=false) String deliveryId, @RequestParam(value="carrierServiceStatusId", required=false) String carrierServiceStatusId, @RequestParam(value="actualStartDate", required=false) Timestamp actualStartDate, @RequestParam(value="trackingDigest", required=false) String trackingDigest, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="actualCost", required=false) BigDecimal actualCost, @RequestParam(value="upsHighValueReport", required=false) byte[] upsHighValueReport, @RequestParam(value="actualArrivalDate", required=false) Timestamp actualArrivalDate, @RequestParam(value="actualServiceCost", required=false) BigDecimal actualServiceCost, @RequestParam(value="originContactMechId", required=false) String originContactMechId, @RequestParam(value="estimatedStartDate", required=false) Timestamp estimatedStartDate, @RequestParam(value="carrierRestrictionDesc", required=false) String carrierRestrictionDesc, @RequestParam(value="actualTransportCost", required=false) BigDecimal actualTransportCost, @RequestParam(value="billingWeightUomId", required=false) String billingWeightUomId, @RequestParam(value="homeDeliveryDate", required=false) Timestamp homeDeliveryDate, @RequestParam(value="originFacilityId", required=false) String originFacilityId, @RequestParam(value="shipmentRouteSegmentId", required=false) String shipmentRouteSegmentId, @RequestParam(value="destFacilityId", required=false) String destFacilityId, @RequestParam(value="carrierPartyId", required=false) String carrierPartyId, @RequestParam(value="lastUpdatedDate", required=false) Timestamp lastUpdatedDate, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="billingWeight", required=false) BigDecimal billingWeight, @RequestParam(value="originTelecomNumberId", required=false) String originTelecomNumberId, @RequestParam(value="thirdPartyCountryGeoCode", required=false) String thirdPartyCountryGeoCode, @RequestParam(value="destContactMechId", required=false) String destContactMechId, @RequestParam(value="estimatedArrivalDate", required=false) Timestamp estimatedArrivalDate, @RequestParam(value="homeDeliveryType", required=false) String homeDeliveryType, @RequestParam(value="thirdPartyAccountNumber", required=false) String thirdPartyAccountNumber, @RequestParam(value="updatedByUserLoginId", required=false) String updatedByUserLoginId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("actualOtherCost",actualOtherCost);
		paramMap.put("carrierDeliveryZone",carrierDeliveryZone);
		paramMap.put("thirdPartyPostalCode",thirdPartyPostalCode);
		paramMap.put("destTelecomNumberId",destTelecomNumberId);
		paramMap.put("carrierRestrictionCodes",carrierRestrictionCodes);
		paramMap.put("trackingIdNumber",trackingIdNumber);
		paramMap.put("deliveryId",deliveryId);
		paramMap.put("carrierServiceStatusId",carrierServiceStatusId);
		paramMap.put("actualStartDate",actualStartDate);
		paramMap.put("trackingDigest",trackingDigest);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("actualCost",actualCost);
		paramMap.put("upsHighValueReport",upsHighValueReport);
		paramMap.put("actualArrivalDate",actualArrivalDate);
		paramMap.put("actualServiceCost",actualServiceCost);
		paramMap.put("originContactMechId",originContactMechId);
		paramMap.put("estimatedStartDate",estimatedStartDate);
		paramMap.put("carrierRestrictionDesc",carrierRestrictionDesc);
		paramMap.put("actualTransportCost",actualTransportCost);
		paramMap.put("billingWeightUomId",billingWeightUomId);
		paramMap.put("homeDeliveryDate",homeDeliveryDate);
		paramMap.put("originFacilityId",originFacilityId);
		paramMap.put("shipmentRouteSegmentId",shipmentRouteSegmentId);
		paramMap.put("destFacilityId",destFacilityId);
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("lastUpdatedDate",lastUpdatedDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("billingWeight",billingWeight);
		paramMap.put("originTelecomNumberId",originTelecomNumberId);
		paramMap.put("thirdPartyCountryGeoCode",thirdPartyCountryGeoCode);
		paramMap.put("destContactMechId",destContactMechId);
		paramMap.put("estimatedArrivalDate",estimatedArrivalDate);
		paramMap.put("homeDeliveryType",homeDeliveryType);
		paramMap.put("thirdPartyAccountNumber",thirdPartyAccountNumber);
		paramMap.put("updatedByUserLoginId",updatedByUserLoginId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentRouteSegment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShippingDocument")
	public ResponseEntity<Object> createShippingDocument(HttpSession session, @RequestParam(value="documentId") String documentId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentPackageSeqId", required=false) String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId", required=false) String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("documentId",documentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("description",description);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShippingDocument", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentPackageContent")
	public ResponseEntity<Object> createShipmentPackageContent(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentPackageSeqId") String shipmentPackageSeqId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="subProductQuantity", required=false) BigDecimal subProductQuantity, @RequestParam(value="subProductId", required=false) String subProductId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentPackageSeqId",shipmentPackageSeqId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("subProductQuantity",subProductQuantity);
		paramMap.put("subProductId",subProductId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentPackageContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/issueInventoryItemToShipment")
	public ResponseEntity<Object> issueInventoryItemToShipment(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="totalIssuedQty") BigDecimal totalIssuedQty, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("quantity",quantity);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("totalIssuedQty",totalIssuedQty);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("issueInventoryItemToShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentAttribute")
	public ResponseEntity<Object> deleteShipmentAttribute(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/packBulkItems")
	public ResponseEntity<Object> packBulkItems(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="packingSession") org.apache.ofbiz.shipment.packing.PackingSession packingSession, @RequestParam(value="shipGroupSeqId") String shipGroupSeqId, @RequestParam(value="updateQuantity", required=false) Boolean updateQuantity, @RequestParam(value="selInfo", required=false) Map selInfo, @RequestParam(value="pkgInfo", required=false) Map pkgInfo, @RequestParam(value="prdInfo", required=false) Map prdInfo, @RequestParam(value="nextPackageSeq", required=false) Integer nextPackageSeq, @RequestParam(value="qtyInfo", required=false) Map qtyInfo, @RequestParam(value="iteInfo", required=false) Map iteInfo, @RequestParam(value="wgtInfo", required=false) Map wgtInfo, @RequestParam(value="pickerPartyId", required=false) String pickerPartyId, @RequestParam(value="handlingInstructions", required=false) String handlingInstructions, @RequestParam(value="numPackagesInfo", required=false) Map numPackagesInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("packingSession",packingSession);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("updateQuantity",updateQuantity);
		paramMap.put("selInfo",selInfo);
		paramMap.put("pkgInfo",pkgInfo);
		paramMap.put("prdInfo",prdInfo);
		paramMap.put("nextPackageSeq",nextPackageSeq);
		paramMap.put("qtyInfo",qtyInfo);
		paramMap.put("iteInfo",iteInfo);
		paramMap.put("wgtInfo",wgtInfo);
		paramMap.put("pickerPartyId",pickerPartyId);
		paramMap.put("handlingInstructions",handlingInstructions);
		paramMap.put("numPackagesInfo",numPackagesInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("packBulkItems", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentContactMech")
	public ResponseEntity<Object> createShipmentContactMech(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentContactMechTypeId") String shipmentContactMechTypeId, @RequestParam(value="contactMechId", required=false) String contactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentContactMechTypeId",shipmentContactMechTypeId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentContactMech", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentMethodType")
	public ResponseEntity<Object> createShipmentMethodType(HttpSession session, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePurchaseShipmentFromReceipt")
	public ResponseEntity<Object> updatePurchaseShipmentFromReceipt(HttpSession session, @RequestParam(value="shipmentId") String shipmentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePurchaseShipmentFromReceipt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateQuantityBreak")
	public ResponseEntity<Object> updateQuantityBreak(HttpSession session, @RequestParam(value="quantityBreakId") String quantityBreakId, @RequestParam(value="thruQuantity", required=false) BigDecimal thruQuantity, @RequestParam(value="quantityBreakTypeId", required=false) String quantityBreakTypeId, @RequestParam(value="fromQuantity", required=false) BigDecimal fromQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantityBreakId",quantityBreakId);
		paramMap.put("thruQuantity",thruQuantity);
		paramMap.put("quantityBreakTypeId",quantityBreakTypeId);
		paramMap.put("fromQuantity",fromQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateQuantityBreak", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentItem")
	public ResponseEntity<Object> updateShipmentItem(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="shipmentContentDescription", required=false) String shipmentContentDescription) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("shipmentContentDescription",shipmentContentDescription);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentItemFeature")
	public ResponseEntity<Object> deleteShipmentItemFeature(HttpSession session, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="productFeatureId") String productFeatureId, @RequestParam(value="shipmentItemSeqId") String shipmentItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("shipmentItemSeqId",shipmentItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentItemFeature", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteItemIssuanceRole")
	public ResponseEntity<Object> deleteItemIssuanceRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="itemIssuanceId") String itemIssuanceId, @RequestParam(value="shipmentId") String shipmentId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteItemIssuanceRole", paramMap);
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



}
