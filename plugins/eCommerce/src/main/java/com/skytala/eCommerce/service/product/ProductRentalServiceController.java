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
@RequestMapping("/service/productRental")
public class ProductRentalServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/getProductFirstRelatedFixedAsset")
	public ResponseEntity<Object> getProductFirstRelatedFixedAsset(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductFirstRelatedFixedAsset", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFixedAssetAndLinkToProduct")
	public ResponseEntity<Object> createFixedAssetAndLinkToProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="dateNextService", required=false) Timestamp dateNextService, @RequestParam(value="purchaseCostUomId", required=false) String purchaseCostUomId, @RequestParam(value="productionCapacity", required=false) BigDecimal productionCapacity, @RequestParam(value="acquireOrderId", required=false) String acquireOrderId, @RequestParam(value="locatedAtLocationSeqId", required=false) String locatedAtLocationSeqId, @RequestParam(value="actualEndOfLife", required=false) Timestamp actualEndOfLife, @RequestParam(value="dateLastServiced", required=false) Timestamp dateLastServiced, @RequestParam(value="acquireOrderItemSeqId", required=false) String acquireOrderItemSeqId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="locatedAtFacilityId", required=false) String locatedAtFacilityId, @RequestParam(value="depreciation", required=false) BigDecimal depreciation, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dateAcquired", required=false) Timestamp dateAcquired, @RequestParam(value="purchaseCost", required=false) BigDecimal purchaseCost, @RequestParam(value="serialNumber", required=false) String serialNumber, @RequestParam(value="fixedAssetTypeId", required=false) String fixedAssetTypeId, @RequestParam(value="classEnumId", required=false) String classEnumId, @RequestParam(value="fixedAssetName", required=false) String fixedAssetName, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="calendarId", required=false) String calendarId, @RequestParam(value="salvageValue", required=false) BigDecimal salvageValue, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="parentFixedAssetId", required=false) String parentFixedAssetId, @RequestParam(value="expectedEndOfLife", required=false) Timestamp expectedEndOfLife, @RequestParam(value="instanceOfProductId", required=false) String instanceOfProductId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("dateNextService",dateNextService);
		paramMap.put("purchaseCostUomId",purchaseCostUomId);
		paramMap.put("productionCapacity",productionCapacity);
		paramMap.put("acquireOrderId",acquireOrderId);
		paramMap.put("locatedAtLocationSeqId",locatedAtLocationSeqId);
		paramMap.put("actualEndOfLife",actualEndOfLife);
		paramMap.put("dateLastServiced",dateLastServiced);
		paramMap.put("acquireOrderItemSeqId",acquireOrderItemSeqId);
		paramMap.put("partyId",partyId);
		paramMap.put("locatedAtFacilityId",locatedAtFacilityId);
		paramMap.put("depreciation",depreciation);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dateAcquired",dateAcquired);
		paramMap.put("purchaseCost",purchaseCost);
		paramMap.put("serialNumber",serialNumber);
		paramMap.put("fixedAssetTypeId",fixedAssetTypeId);
		paramMap.put("classEnumId",classEnumId);
		paramMap.put("fixedAssetName",fixedAssetName);
		paramMap.put("uomId",uomId);
		paramMap.put("calendarId",calendarId);
		paramMap.put("salvageValue",salvageValue);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("parentFixedAssetId",parentFixedAssetId);
		paramMap.put("expectedEndOfLife",expectedEndOfLife);
		paramMap.put("instanceOfProductId",instanceOfProductId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFixedAssetAndLinkToProduct", paramMap);
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
