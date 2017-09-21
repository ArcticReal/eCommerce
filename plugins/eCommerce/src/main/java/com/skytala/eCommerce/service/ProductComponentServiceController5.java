package com.skytala.eCommerce.service;

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
@RequestMapping("/service/ProductComponentController5")
public class ProductComponentServiceController5{

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreShipMeth")
	public ResponseEntity<Object> createProductStoreShipMeth(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="companyPartyId", required=false) String companyPartyId, @RequestParam(value="sequenceNumber", required=false) long sequenceNumber, @RequestParam(value="allowancePercent", required=false) BigDecimal allowancePercent, @RequestParam(value="includeFeatureGroup", required=false) String includeFeatureGroup, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="minTotal", required=false) BigDecimal minTotal, @RequestParam(value="allowUspsAddr", required=false) boolean allowUspsAddr, @RequestParam(value="minWeight", required=false) BigDecimal minWeight, @RequestParam(value="requireCompanyAddr", required=false) boolean requireCompanyAddr, @RequestParam(value="maxSize", required=false) BigDecimal maxSize, @RequestParam(value="maxWeight", required=false) BigDecimal maxWeight, @RequestParam(value="excludeGeoId", required=false) String excludeGeoId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="includeGeoId", required=false) String includeGeoId, @RequestParam(value="maxTotal", required=false) BigDecimal maxTotal, @RequestParam(value="requireUspsAddr", required=false) boolean requireUspsAddr, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="configProps", required=false) String configProps, @RequestParam(value="minSize", required=false) BigDecimal minSize, @RequestParam(value="includeNoChargeItems", required=false) boolean includeNoChargeItems, @RequestParam(value="allowCompanyAddr", required=false) boolean allowCompanyAddr, @RequestParam(value="minimumPrice", required=false) BigDecimal minimumPrice, @RequestParam(value="excludeFeatureGroup", required=false) String excludeFeatureGroup) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("companyPartyId",companyPartyId);
		paramMap.put("sequenceNumber",sequenceNumber);
		paramMap.put("allowancePercent",allowancePercent);
		paramMap.put("includeFeatureGroup",includeFeatureGroup);
		paramMap.put("shipmentCustomMethodId",shipmentCustomMethodId);
		paramMap.put("minTotal",minTotal);
		paramMap.put("allowUspsAddr",allowUspsAddr);
		paramMap.put("minWeight",minWeight);
		paramMap.put("requireCompanyAddr",requireCompanyAddr);
		paramMap.put("maxSize",maxSize);
		paramMap.put("maxWeight",maxWeight);
		paramMap.put("excludeGeoId",excludeGeoId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("includeGeoId",includeGeoId);
		paramMap.put("maxTotal",maxTotal);
		paramMap.put("requireUspsAddr",requireUspsAddr);
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("configProps",configProps);
		paramMap.put("minSize",minSize);
		paramMap.put("includeNoChargeItems",includeNoChargeItems);
		paramMap.put("allowCompanyAddr",allowCompanyAddr);
		paramMap.put("minimumPrice",minimumPrice);
		paramMap.put("excludeFeatureGroup",excludeFeatureGroup);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreShipMeth", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreFacility")
	public ResponseEntity<Object> deleteProductStoreFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productStoreId") String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreFacility", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupRole")
	public ResponseEntity<Object> createProductStoreGroupRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreGroupRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreCatalog")
	public ResponseEntity<Object> updateProductStoreCatalog(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreCatalog", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroupRollup")
	public ResponseEntity<Object> deleteProductStoreGroupRollup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="parentGroupId") String parentGroupId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("parentGroupId",parentGroupId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreGroupRollup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/isStoreInventoryAvailableOrNotRequired")
	public ResponseEntity<Object> isStoreInventoryAvailableOrNotRequired(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="product", required=false) org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="productStore", required=false) org.apache.ofbiz.entity.GenericValue productStore) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("product",product);
		paramMap.put("productStore",productStore);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("isStoreInventoryAvailableOrNotRequired", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreCatalog")
	public ResponseEntity<Object> deleteProductStoreCatalog(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreCatalog", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreVendorPayment")
	public ResponseEntity<Object> createProductStoreVendorPayment(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId, @RequestParam(value="creditCardEnumId") String creditCardEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("creditCardEnumId",creditCardEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreVendorPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStorePaymentSetting")
	public ResponseEntity<Object> createProductStorePaymentSetting(HttpSession session, @RequestParam(value="paymentServiceTypeEnumId") String paymentServiceTypeEnumId, @RequestParam(value="applyToAllProducts") boolean applyToAllProducts, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="paymentService", required=false) long paymentService, @RequestParam(value="paymentPropertiesPath", required=false) long paymentPropertiesPath, @RequestParam(value="paymentCustomMethodId", required=false) String paymentCustomMethodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentServiceTypeEnumId",paymentServiceTypeEnumId);
		paramMap.put("applyToAllProducts",applyToAllProducts);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("paymentService",paymentService);
		paramMap.put("paymentPropertiesPath",paymentPropertiesPath);
		paramMap.put("paymentCustomMethodId",paymentCustomMethodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStorePaymentSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreFacility")
	public ResponseEntity<Object> createProductStoreFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreFacility", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroupRole")
	public ResponseEntity<Object> deleteProductStoreGroupRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreGroupRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreFinActSetting")
	public ResponseEntity<Object> updateProductStoreFinActSetting(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="authValidDays", required=false) long authValidDays, @RequestParam(value="allowAuthToNegative", required=false) boolean allowAuthToNegative, @RequestParam(value="accountCodeLength", required=false) long accountCodeLength, @RequestParam(value="purchSurveyCopyMe", required=false) String purchSurveyCopyMe, @RequestParam(value="validateGCFinAcct", required=false) boolean validateGCFinAcct, @RequestParam(value="replenishThreshold", required=false) BigDecimal replenishThreshold, @RequestParam(value="accountValidDays", required=false) long accountValidDays, @RequestParam(value="replenishMethodEnumId", required=false) String replenishMethodEnumId, @RequestParam(value="purchSurveySendTo", required=false) String purchSurveySendTo, @RequestParam(value="minBalance", required=false) BigDecimal minBalance, @RequestParam(value="purchaseSurveyId", required=false) String purchaseSurveyId, @RequestParam(value="requirePinCode", required=false) boolean requirePinCode, @RequestParam(value="pinCodeLength", required=false) long pinCodeLength) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("authValidDays",authValidDays);
		paramMap.put("allowAuthToNegative",allowAuthToNegative);
		paramMap.put("accountCodeLength",accountCodeLength);
		paramMap.put("purchSurveyCopyMe",purchSurveyCopyMe);
		paramMap.put("validateGCFinAcct",validateGCFinAcct);
		paramMap.put("replenishThreshold",replenishThreshold);
		paramMap.put("accountValidDays",accountValidDays);
		paramMap.put("replenishMethodEnumId",replenishMethodEnumId);
		paramMap.put("purchSurveySendTo",purchSurveySendTo);
		paramMap.put("minBalance",minBalance);
		paramMap.put("purchaseSurveyId",purchaseSurveyId);
		paramMap.put("requirePinCode",requirePinCode);
		paramMap.put("pinCodeLength",pinCodeLength);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreFinActSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreEmailSetting")
	public ResponseEntity<Object> removeProductStoreEmailSetting(HttpSession session, @RequestParam(value="emailType") String emailType, @RequestParam(value="productStoreId") String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailType",emailType);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductStoreEmailSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreShipMeth")
	public ResponseEntity<Object> updateProductStoreShipMeth(HttpSession session, @RequestParam(value="productStoreShipMethId") String productStoreShipMethId, @RequestParam(value="companyPartyId", required=false) String companyPartyId, @RequestParam(value="allowancePercent", required=false) BigDecimal allowancePercent, @RequestParam(value="includeFeatureGroup", required=false) String includeFeatureGroup, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="minTotal", required=false) BigDecimal minTotal, @RequestParam(value="minWeight", required=false) BigDecimal minWeight, @RequestParam(value="requireCompanyAddr", required=false) boolean requireCompanyAddr, @RequestParam(value="maxSize", required=false) BigDecimal maxSize, @RequestParam(value="excludeGeoId", required=false) String excludeGeoId, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="minSize", required=false) BigDecimal minSize, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="allowCompanyAddr", required=false) boolean allowCompanyAddr, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="minimumPrice", required=false) BigDecimal minimumPrice, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="excludeFeatureGroup", required=false) String excludeFeatureGroup, @RequestParam(value="sequenceNumber", required=false) long sequenceNumber, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="allowUspsAddr", required=false) boolean allowUspsAddr, @RequestParam(value="maxWeight", required=false) BigDecimal maxWeight, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="includeGeoId", required=false) String includeGeoId, @RequestParam(value="maxTotal", required=false) BigDecimal maxTotal, @RequestParam(value="requireUspsAddr", required=false) boolean requireUspsAddr, @RequestParam(value="configProps", required=false) String configProps, @RequestParam(value="includeNoChargeItems", required=false) boolean includeNoChargeItems) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("companyPartyId",companyPartyId);
		paramMap.put("allowancePercent",allowancePercent);
		paramMap.put("includeFeatureGroup",includeFeatureGroup);
		paramMap.put("shipmentCustomMethodId",shipmentCustomMethodId);
		paramMap.put("minTotal",minTotal);
		paramMap.put("minWeight",minWeight);
		paramMap.put("requireCompanyAddr",requireCompanyAddr);
		paramMap.put("maxSize",maxSize);
		paramMap.put("excludeGeoId",excludeGeoId);
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("minSize",minSize);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("allowCompanyAddr",allowCompanyAddr);
		paramMap.put("partyId",partyId);
		paramMap.put("minimumPrice",minimumPrice);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("excludeFeatureGroup",excludeFeatureGroup);
		paramMap.put("sequenceNumber",sequenceNumber);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("allowUspsAddr",allowUspsAddr);
		paramMap.put("maxWeight",maxWeight);
		paramMap.put("serviceName",serviceName);
		paramMap.put("includeGeoId",includeGeoId);
		paramMap.put("maxTotal",maxTotal);
		paramMap.put("requireUspsAddr",requireUspsAddr);
		paramMap.put("configProps",configProps);
		paramMap.put("includeNoChargeItems",includeNoChargeItems);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreShipMeth", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/isStoreInventoryAvailable")
	public ResponseEntity<Object> isStoreInventoryAvailable(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="product", required=false) org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="productStore", required=false) org.apache.ofbiz.entity.GenericValue productStore) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("product",product);
		paramMap.put("productStore",productStore);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("isStoreInventoryAvailable", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreFinActSetting")
	public ResponseEntity<Object> removeProductStoreFinActSetting(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="finAccountTypeId") String finAccountTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductStoreFinActSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreRole")
	public ResponseEntity<Object> removeProductStoreRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductStoreRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreCatalog")
	public ResponseEntity<Object> createProductStoreCatalog(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreCatalog", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupRollup")
	public ResponseEntity<Object> createProductStoreGroupRollup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="parentGroupId") String parentGroupId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("parentGroupId",parentGroupId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreGroupRollup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreFacility")
	public ResponseEntity<Object> updateProductStoreFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreFacility", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreShipMeth")
	public ResponseEntity<Object> removeProductStoreShipMeth(HttpSession session, @RequestParam(value="productStoreShipMethId") String productStoreShipMethId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductStoreShipMeth", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStorePromoAppl")
	public ResponseEntity<Object> deleteProductStorePromoAppl(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStorePromoAppl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreVendorShipment")
	public ResponseEntity<Object> createProductStoreVendorShipment(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreVendorShipment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroup")
	public ResponseEntity<Object> deleteProductStoreGroup(HttpSession session, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreVendorShipment")
	public ResponseEntity<Object> deleteProductStoreVendorShipment(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("carrierPartyId",carrierPartyId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("shipmentMethodTypeId",shipmentMethodTypeId);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreVendorShipment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStore")
	public ResponseEntity<Object> createProductStore(HttpSession session, @RequestParam(value="storeName") String storeName, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="viewCartOnAdd", required=false) boolean viewCartOnAdd, @RequestParam(value="requireCustomerRole", required=false) boolean requireCustomerRole, @RequestParam(value="companyName", required=false) String companyName, @RequestParam(value="headerDeclinedStatus", required=false) String headerDeclinedStatus, @RequestParam(value="requireInventory", required=false) boolean requireInventory, @RequestParam(value="checkInventory", required=false) boolean checkInventory, @RequestParam(value="autoApproveInvoice", required=false) boolean autoApproveInvoice, @RequestParam(value="addToCartRemoveIncompat", required=false) boolean addToCartRemoveIncompat, @RequestParam(value="enableAutoSuggestionList", required=false) boolean enableAutoSuggestionList, @RequestParam(value="defaultSalesChannelEnumId", required=false) String defaultSalesChannelEnumId, @RequestParam(value="autoApproveOrder", required=false) boolean autoApproveOrder, @RequestParam(value="allowPassword", required=false) boolean allowPassword, @RequestParam(value="showTaxIsExempt", required=false) boolean showTaxIsExempt, @RequestParam(value="oldHeaderLogo", required=false) String oldHeaderLogo, @RequestParam(value="autoSaveCart", required=false) boolean autoSaveCart, @RequestParam(value="inventoryFacilityId", required=false) String inventoryFacilityId, @RequestParam(value="headerApprovedStatus", required=false) String headerApprovedStatus, @RequestParam(value="oldHeaderMiddleBackground", required=false) String oldHeaderMiddleBackground, @RequestParam(value="defaultTimeZoneString", required=false) String defaultTimeZoneString, @RequestParam(value="prorateTaxes", required=false) boolean prorateTaxes, @RequestParam(value="showCheckoutGiftOptions", required=false) boolean showCheckoutGiftOptions, @RequestParam(value="oldStyleSheet", required=false) String oldStyleSheet, @RequestParam(value="manualAuthIsCapture", required=false) boolean manualAuthIsCapture, @RequestParam(value="itemDeclinedStatus", required=false) String itemDeclinedStatus, @RequestParam(value="visualThemeId", required=false) String visualThemeId, @RequestParam(value="storeCreditAccountEnumId", required=false) String storeCreditAccountEnumId, @RequestParam(value="showOutOfStockProducts", required=false) boolean showOutOfStockProducts, @RequestParam(value="itemApprovedStatus", required=false) String itemApprovedStatus, @RequestParam(value="allowComment", required=false) boolean allowComment, @RequestParam(value="storeCreditValidDays", required=false) long storeCreditValidDays, @RequestParam(value="splitPayPrefPerShpGrp", required=false) boolean splitPayPrefPerShpGrp, @RequestParam(value="authFraudMessage", required=false) String authFraudMessage, @RequestParam(value="managedByLot", required=false) boolean managedByLot, @RequestParam(value="reqShipAddrForDigItems", required=false) boolean reqShipAddrForDigItems, @RequestParam(value="oneInventoryFacility", required=false) boolean oneInventoryFacility, @RequestParam(value="orderNumberPrefix", required=false) String orderNumberPrefix, @RequestParam(value="autoInvoiceDigitalItems", required=false) boolean autoInvoiceDigitalItems, @RequestParam(value="subtitle", required=false) String subtitle, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="isDemoStore", required=false) boolean isDemoStore, @RequestParam(value="retryFailedAuths", required=false) boolean retryFailedAuths, @RequestParam(value="selectPaymentTypePerItem", required=false) boolean selectPaymentTypePerItem, @RequestParam(value="balanceResOnOrderCreation", required=false) boolean balanceResOnOrderCreation, @RequestParam(value="autoApproveReviews", required=false) boolean autoApproveReviews, @RequestParam(value="explodeOrderItems", required=false) boolean explodeOrderItems, @RequestParam(value="usePrimaryEmailUsername", required=false) boolean usePrimaryEmailUsername, @RequestParam(value="headerCancelStatus", required=false) String headerCancelStatus, @RequestParam(value="title", required=false) String title, @RequestParam(value="primaryStoreGroupId", required=false) String primaryStoreGroupId, @RequestParam(value="oldHeaderRightBackground", required=false) String oldHeaderRightBackground, @RequestParam(value="enableDigProdUpload", required=false) boolean enableDigProdUpload, @RequestParam(value="autoOrderCcTryOtherCards", required=false) boolean autoOrderCcTryOtherCards, @RequestParam(value="autoOrderCcTryLaterMax", required=false) long autoOrderCcTryLaterMax, @RequestParam(value="digitalItemApprovedStatus", required=false) String digitalItemApprovedStatus, @RequestParam(value="reserveInventory", required=false) boolean reserveInventory, @RequestParam(value="isImmediatelyFulfilled", required=false) boolean isImmediatelyFulfilled, @RequestParam(value="defaultPassword", required=false) String defaultPassword, @RequestParam(value="itemCancelStatus", required=false) String itemCancelStatus, @RequestParam(value="autoOrderCcTryExp", required=false) boolean autoOrderCcTryExp, @RequestParam(value="authDeclinedMessage", required=false) String authDeclinedMessage, @RequestParam(value="prodSearchExcludeVariants", required=false) boolean prodSearchExcludeVariants, @RequestParam(value="shipIfCaptureFails", required=false) boolean shipIfCaptureFails, @RequestParam(value="showPricesWithVatTax", required=false) boolean showPricesWithVatTax, @RequestParam(value="defaultCurrencyUomId", required=false) String defaultCurrencyUomId, @RequestParam(value="orderDecimalQuantity", required=false) boolean orderDecimalQuantity, @RequestParam(value="prorateShipping", required=false) boolean prorateShipping, @RequestParam(value="digProdUploadCategoryId", required=false) String digProdUploadCategoryId, @RequestParam(value="reqReturnInventoryReceive", required=false) boolean reqReturnInventoryReceive, @RequestParam(value="authErrorMessage", required=false) String authErrorMessage, @RequestParam(value="setOwnerUponIssuance", required=false) boolean setOwnerUponIssuance, @RequestParam(value="checkGcBalance", required=false) boolean checkGcBalance, @RequestParam(value="autoOrderCcTryLaterNsf", required=false) boolean autoOrderCcTryLaterNsf, @RequestParam(value="addToCartReplaceUpsell", required=false) boolean addToCartReplaceUpsell, @RequestParam(value="vatTaxAuthGeoId", required=false) String vatTaxAuthGeoId, @RequestParam(value="vatTaxAuthPartyId", required=false) String vatTaxAuthPartyId, @RequestParam(value="daysToCancelNonPay", required=false) long daysToCancelNonPay, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="defaultLocaleString", required=false) String defaultLocaleString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("storeName",storeName);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("viewCartOnAdd",viewCartOnAdd);
		paramMap.put("requireCustomerRole",requireCustomerRole);
		paramMap.put("companyName",companyName);
		paramMap.put("headerDeclinedStatus",headerDeclinedStatus);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("checkInventory",checkInventory);
		paramMap.put("autoApproveInvoice",autoApproveInvoice);
		paramMap.put("addToCartRemoveIncompat",addToCartRemoveIncompat);
		paramMap.put("enableAutoSuggestionList",enableAutoSuggestionList);
		paramMap.put("defaultSalesChannelEnumId",defaultSalesChannelEnumId);
		paramMap.put("autoApproveOrder",autoApproveOrder);
		paramMap.put("allowPassword",allowPassword);
		paramMap.put("showTaxIsExempt",showTaxIsExempt);
		paramMap.put("oldHeaderLogo",oldHeaderLogo);
		paramMap.put("autoSaveCart",autoSaveCart);
		paramMap.put("inventoryFacilityId",inventoryFacilityId);
		paramMap.put("headerApprovedStatus",headerApprovedStatus);
		paramMap.put("oldHeaderMiddleBackground",oldHeaderMiddleBackground);
		paramMap.put("defaultTimeZoneString",defaultTimeZoneString);
		paramMap.put("prorateTaxes",prorateTaxes);
		paramMap.put("showCheckoutGiftOptions",showCheckoutGiftOptions);
		paramMap.put("oldStyleSheet",oldStyleSheet);
		paramMap.put("manualAuthIsCapture",manualAuthIsCapture);
		paramMap.put("itemDeclinedStatus",itemDeclinedStatus);
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("storeCreditAccountEnumId",storeCreditAccountEnumId);
		paramMap.put("showOutOfStockProducts",showOutOfStockProducts);
		paramMap.put("itemApprovedStatus",itemApprovedStatus);
		paramMap.put("allowComment",allowComment);
		paramMap.put("storeCreditValidDays",storeCreditValidDays);
		paramMap.put("splitPayPrefPerShpGrp",splitPayPrefPerShpGrp);
		paramMap.put("authFraudMessage",authFraudMessage);
		paramMap.put("managedByLot",managedByLot);
		paramMap.put("reqShipAddrForDigItems",reqShipAddrForDigItems);
		paramMap.put("oneInventoryFacility",oneInventoryFacility);
		paramMap.put("orderNumberPrefix",orderNumberPrefix);
		paramMap.put("autoInvoiceDigitalItems",autoInvoiceDigitalItems);
		paramMap.put("subtitle",subtitle);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("isDemoStore",isDemoStore);
		paramMap.put("retryFailedAuths",retryFailedAuths);
		paramMap.put("selectPaymentTypePerItem",selectPaymentTypePerItem);
		paramMap.put("balanceResOnOrderCreation",balanceResOnOrderCreation);
		paramMap.put("autoApproveReviews",autoApproveReviews);
		paramMap.put("explodeOrderItems",explodeOrderItems);
		paramMap.put("usePrimaryEmailUsername",usePrimaryEmailUsername);
		paramMap.put("headerCancelStatus",headerCancelStatus);
		paramMap.put("title",title);
		paramMap.put("primaryStoreGroupId",primaryStoreGroupId);
		paramMap.put("oldHeaderRightBackground",oldHeaderRightBackground);
		paramMap.put("enableDigProdUpload",enableDigProdUpload);
		paramMap.put("autoOrderCcTryOtherCards",autoOrderCcTryOtherCards);
		paramMap.put("autoOrderCcTryLaterMax",autoOrderCcTryLaterMax);
		paramMap.put("digitalItemApprovedStatus",digitalItemApprovedStatus);
		paramMap.put("reserveInventory",reserveInventory);
		paramMap.put("isImmediatelyFulfilled",isImmediatelyFulfilled);
		paramMap.put("defaultPassword",defaultPassword);
		paramMap.put("itemCancelStatus",itemCancelStatus);
		paramMap.put("autoOrderCcTryExp",autoOrderCcTryExp);
		paramMap.put("authDeclinedMessage",authDeclinedMessage);
		paramMap.put("prodSearchExcludeVariants",prodSearchExcludeVariants);
		paramMap.put("shipIfCaptureFails",shipIfCaptureFails);
		paramMap.put("showPricesWithVatTax",showPricesWithVatTax);
		paramMap.put("defaultCurrencyUomId",defaultCurrencyUomId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("prorateShipping",prorateShipping);
		paramMap.put("digProdUploadCategoryId",digProdUploadCategoryId);
		paramMap.put("reqReturnInventoryReceive",reqReturnInventoryReceive);
		paramMap.put("authErrorMessage",authErrorMessage);
		paramMap.put("setOwnerUponIssuance",setOwnerUponIssuance);
		paramMap.put("checkGcBalance",checkGcBalance);
		paramMap.put("autoOrderCcTryLaterNsf",autoOrderCcTryLaterNsf);
		paramMap.put("addToCartReplaceUpsell",addToCartReplaceUpsell);
		paramMap.put("vatTaxAuthGeoId",vatTaxAuthGeoId);
		paramMap.put("vatTaxAuthPartyId",vatTaxAuthPartyId);
		paramMap.put("daysToCancelNonPay",daysToCancelNonPay);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("payToPartyId",payToPartyId);
		paramMap.put("defaultLocaleString",defaultLocaleString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStore", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreEmailSetting")
	public ResponseEntity<Object> updateProductStoreEmailSetting(HttpSession session, @RequestParam(value="emailType") String emailType, @RequestParam(value="bodyScreenLocation") String bodyScreenLocation, @RequestParam(value="subject") String subject, @RequestParam(value="fromAddress") String fromAddress, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="bccAddress", required=false) String bccAddress, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="ccAddress", required=false) String ccAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailType",emailType);
		paramMap.put("bodyScreenLocation",bodyScreenLocation);
		paramMap.put("subject",subject);
		paramMap.put("fromAddress",fromAddress);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("xslfoAttachScreenLocation",xslfoAttachScreenLocation);
		paramMap.put("bccAddress",bccAddress);
		paramMap.put("contentType",contentType);
		paramMap.put("ccAddress",ccAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreEmailSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupType")
	public ResponseEntity<Object> createProductStoreGroupType(HttpSession session, @RequestParam(value="productStoreGroupTypeId", required=false) String productStoreGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupTypeId",productStoreGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreKeywordOvrd")
	public ResponseEntity<Object> createProductStoreKeywordOvrd(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="targetTypeEnumId") String targetTypeEnumId, @RequestParam(value="keyword") String keyword, @RequestParam(value="target") String target, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("targetTypeEnumId",targetTypeEnumId);
		paramMap.put("keyword",keyword);
		paramMap.put("target",target);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreKeywordOvrd", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroupRollup")
	public ResponseEntity<Object> updateProductStoreGroupRollup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="parentGroupId") String parentGroupId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("parentGroupId",parentGroupId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreGroupRollup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroupType")
	public ResponseEntity<Object> updateProductStoreGroupType(HttpSession session, @RequestParam(value="productStoreGroupTypeId") String productStoreGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupTypeId",productStoreGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStorePaymentSetting")
	public ResponseEntity<Object> updateProductStorePaymentSetting(HttpSession session, @RequestParam(value="paymentServiceTypeEnumId") String paymentServiceTypeEnumId, @RequestParam(value="applyToAllProducts") boolean applyToAllProducts, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="paymentService", required=false) long paymentService, @RequestParam(value="paymentPropertiesPath", required=false) long paymentPropertiesPath, @RequestParam(value="paymentCustomMethodId", required=false) String paymentCustomMethodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentServiceTypeEnumId",paymentServiceTypeEnumId);
		paramMap.put("applyToAllProducts",applyToAllProducts);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("paymentGatewayConfigId",paymentGatewayConfigId);
		paramMap.put("paymentService",paymentService);
		paramMap.put("paymentPropertiesPath",paymentPropertiesPath);
		paramMap.put("paymentCustomMethodId",paymentCustomMethodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStorePaymentSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkProductStoreGroupRollup")
	public ResponseEntity<Object> checkProductStoreGroupRollup(HttpSession session, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="parentGroupId", required=false) String parentGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("primaryParentGroupId",primaryParentGroupId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("parentGroupId",parentGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkProductStoreGroupRollup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreEmailSetting")
	public ResponseEntity<Object> createProductStoreEmailSetting(HttpSession session, @RequestParam(value="emailType") String emailType, @RequestParam(value="bodyScreenLocation") String bodyScreenLocation, @RequestParam(value="subject") String subject, @RequestParam(value="fromAddress") String fromAddress, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="bccAddress", required=false) String bccAddress, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="ccAddress", required=false) String ccAddress) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailType",emailType);
		paramMap.put("bodyScreenLocation",bodyScreenLocation);
		paramMap.put("subject",subject);
		paramMap.put("fromAddress",fromAddress);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("xslfoAttachScreenLocation",xslfoAttachScreenLocation);
		paramMap.put("bccAddress",bccAddress);
		paramMap.put("contentType",contentType);
		paramMap.put("ccAddress",ccAddress);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreEmailSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreRole")
	public ResponseEntity<Object> createProductStoreRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreFinActSetting")
	public ResponseEntity<Object> createProductStoreFinActSetting(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="authValidDays", required=false) long authValidDays, @RequestParam(value="allowAuthToNegative", required=false) boolean allowAuthToNegative, @RequestParam(value="accountCodeLength", required=false) long accountCodeLength, @RequestParam(value="purchSurveyCopyMe", required=false) String purchSurveyCopyMe, @RequestParam(value="validateGCFinAcct", required=false) boolean validateGCFinAcct, @RequestParam(value="replenishThreshold", required=false) BigDecimal replenishThreshold, @RequestParam(value="accountValidDays", required=false) long accountValidDays, @RequestParam(value="replenishMethodEnumId", required=false) String replenishMethodEnumId, @RequestParam(value="purchSurveySendTo", required=false) String purchSurveySendTo, @RequestParam(value="minBalance", required=false) BigDecimal minBalance, @RequestParam(value="purchaseSurveyId", required=false) String purchaseSurveyId, @RequestParam(value="requirePinCode", required=false) boolean requirePinCode, @RequestParam(value="pinCodeLength", required=false) long pinCodeLength) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("authValidDays",authValidDays);
		paramMap.put("allowAuthToNegative",allowAuthToNegative);
		paramMap.put("accountCodeLength",accountCodeLength);
		paramMap.put("purchSurveyCopyMe",purchSurveyCopyMe);
		paramMap.put("validateGCFinAcct",validateGCFinAcct);
		paramMap.put("replenishThreshold",replenishThreshold);
		paramMap.put("accountValidDays",accountValidDays);
		paramMap.put("replenishMethodEnumId",replenishMethodEnumId);
		paramMap.put("purchSurveySendTo",purchSurveySendTo);
		paramMap.put("minBalance",minBalance);
		paramMap.put("purchaseSurveyId",purchaseSurveyId);
		paramMap.put("requirePinCode",requirePinCode);
		paramMap.put("pinCodeLength",pinCodeLength);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreFinActSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/isStoreInventoryRequired")
	public ResponseEntity<Object> isStoreInventoryRequired(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="product", required=false) org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="productStore", required=false) org.apache.ofbiz.entity.GenericValue productStore) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("product",product);
		paramMap.put("productStore",productStore);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("isStoreInventoryRequired", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupMember")
	public ResponseEntity<Object> createProductStoreGroupMember(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreGroupMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroup")
	public ResponseEntity<Object> updateProductStoreGroup(HttpSession session, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="productStoreGroupTypeId", required=false) String productStoreGroupTypeId, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="productStoreGroupName", required=false) String productStoreGroupName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("productStoreGroupTypeId",productStoreGroupTypeId);
		paramMap.put("primaryParentGroupId",primaryParentGroupId);
		paramMap.put("productStoreGroupName",productStoreGroupName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroupType")
	public ResponseEntity<Object> deleteProductStoreGroupType(HttpSession session, @RequestParam(value="productStoreGroupTypeId") String productStoreGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupTypeId",productStoreGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreVendorPayment")
	public ResponseEntity<Object> deleteProductStoreVendorPayment(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId, @RequestParam(value="creditCardEnumId") String creditCardEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("creditCardEnumId",creditCardEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreVendorPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStorePaymentSetting")
	public ResponseEntity<Object> deleteProductStorePaymentSetting(HttpSession session, @RequestParam(value="paymentServiceTypeEnumId") String paymentServiceTypeEnumId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentServiceTypeEnumId",paymentServiceTypeEnumId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStorePaymentSetting", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreSurveyAppl")
	public ResponseEntity<Object> deleteProductStoreSurveyAppl(HttpSession session, @RequestParam(value="productStoreSurveyId") String productStoreSurveyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreSurveyId",productStoreSurveyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreSurveyAppl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroupMember")
	public ResponseEntity<Object> updateProductStoreGroupMember(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreGroupMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreKeywordOvrd")
	public ResponseEntity<Object> updateProductStoreKeywordOvrd(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="keyword") String keyword, @RequestParam(value="targetTypeEnumId", required=false) String targetTypeEnumId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="target", required=false) String target) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("keyword",keyword);
		paramMap.put("targetTypeEnumId",targetTypeEnumId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("target",target);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreKeywordOvrd", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStorePromoAppl")
	public ResponseEntity<Object> updateProductStorePromoAppl(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="manualOnly", required=false) boolean manualOnly, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("manualOnly",manualOnly);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStorePromoAppl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStorePromoAppl")
	public ResponseEntity<Object> createProductStorePromoAppl(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="manualOnly", required=false) boolean manualOnly, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("manualOnly",manualOnly);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStorePromoAppl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreKeywordOvrd")
	public ResponseEntity<Object> deleteProductStoreKeywordOvrd(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="keyword") String keyword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("keyword",keyword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreKeywordOvrd", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreSurveyAppl")
	public ResponseEntity<Object> createProductStoreSurveyAppl(HttpSession session, @RequestParam(value="productStoreSurveyId") String productStoreSurveyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="groupName", required=false) String groupName, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="surveyTemplate", required=false) String surveyTemplate, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="resultTemplate", required=false) String resultTemplate, @RequestParam(value="surveyApplTypeId", required=false) String surveyApplTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreSurveyId",productStoreSurveyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("groupName",groupName);
		paramMap.put("surveyId",surveyId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("productId",productId);
		paramMap.put("surveyTemplate",surveyTemplate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("resultTemplate",resultTemplate);
		paramMap.put("surveyApplTypeId",surveyApplTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreSurveyAppl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroup")
	public ResponseEntity<Object> createProductStoreGroup(HttpSession session, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="productStoreGroupTypeId", required=false) String productStoreGroupTypeId, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="productStoreGroupName", required=false) String productStoreGroupName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("productStoreGroupTypeId",productStoreGroupTypeId);
		paramMap.put("primaryParentGroupId",primaryParentGroupId);
		paramMap.put("productStoreGroupName",productStoreGroupName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductStoreGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reserveStoreInventory")
	public ResponseEntity<Object> reserveStoreInventory(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderId",orderId);
		paramMap.put("shipGroupSeqId",shipGroupSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("reserveStoreInventory", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreRole")
	public ResponseEntity<Object> updateProductStoreRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="sequenceNum", required=false) long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStoreRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/productStoreGenericPermission")
	public ResponseEntity<Object> productStoreGenericPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productStoreGenericPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStore")
	public ResponseEntity<Object> updateProductStore(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="viewCartOnAdd", required=false) boolean viewCartOnAdd, @RequestParam(value="requireCustomerRole", required=false) boolean requireCustomerRole, @RequestParam(value="companyName", required=false) String companyName, @RequestParam(value="headerDeclinedStatus", required=false) String headerDeclinedStatus, @RequestParam(value="requireInventory", required=false) boolean requireInventory, @RequestParam(value="checkInventory", required=false) boolean checkInventory, @RequestParam(value="autoApproveInvoice", required=false) boolean autoApproveInvoice, @RequestParam(value="addToCartRemoveIncompat", required=false) boolean addToCartRemoveIncompat, @RequestParam(value="enableAutoSuggestionList", required=false) boolean enableAutoSuggestionList, @RequestParam(value="defaultSalesChannelEnumId", required=false) String defaultSalesChannelEnumId, @RequestParam(value="autoApproveOrder", required=false) boolean autoApproveOrder, @RequestParam(value="allowPassword", required=false) boolean allowPassword, @RequestParam(value="showTaxIsExempt", required=false) boolean showTaxIsExempt, @RequestParam(value="oldHeaderLogo", required=false) String oldHeaderLogo, @RequestParam(value="autoSaveCart", required=false) boolean autoSaveCart, @RequestParam(value="inventoryFacilityId", required=false) String inventoryFacilityId, @RequestParam(value="headerApprovedStatus", required=false) String headerApprovedStatus, @RequestParam(value="oldHeaderMiddleBackground", required=false) String oldHeaderMiddleBackground, @RequestParam(value="defaultTimeZoneString", required=false) String defaultTimeZoneString, @RequestParam(value="prorateTaxes", required=false) boolean prorateTaxes, @RequestParam(value="showCheckoutGiftOptions", required=false) boolean showCheckoutGiftOptions, @RequestParam(value="oldStyleSheet", required=false) String oldStyleSheet, @RequestParam(value="manualAuthIsCapture", required=false) boolean manualAuthIsCapture, @RequestParam(value="itemDeclinedStatus", required=false) String itemDeclinedStatus, @RequestParam(value="visualThemeId", required=false) String visualThemeId, @RequestParam(value="storeCreditAccountEnumId", required=false) String storeCreditAccountEnumId, @RequestParam(value="showOutOfStockProducts", required=false) boolean showOutOfStockProducts, @RequestParam(value="itemApprovedStatus", required=false) String itemApprovedStatus, @RequestParam(value="allowComment", required=false) boolean allowComment, @RequestParam(value="storeCreditValidDays", required=false) long storeCreditValidDays, @RequestParam(value="splitPayPrefPerShpGrp", required=false) boolean splitPayPrefPerShpGrp, @RequestParam(value="authFraudMessage", required=false) String authFraudMessage, @RequestParam(value="managedByLot", required=false) boolean managedByLot, @RequestParam(value="reqShipAddrForDigItems", required=false) boolean reqShipAddrForDigItems, @RequestParam(value="oneInventoryFacility", required=false) boolean oneInventoryFacility, @RequestParam(value="orderNumberPrefix", required=false) String orderNumberPrefix, @RequestParam(value="autoInvoiceDigitalItems", required=false) boolean autoInvoiceDigitalItems, @RequestParam(value="subtitle", required=false) String subtitle, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="isDemoStore", required=false) boolean isDemoStore, @RequestParam(value="retryFailedAuths", required=false) boolean retryFailedAuths, @RequestParam(value="selectPaymentTypePerItem", required=false) boolean selectPaymentTypePerItem, @RequestParam(value="balanceResOnOrderCreation", required=false) boolean balanceResOnOrderCreation, @RequestParam(value="autoApproveReviews", required=false) boolean autoApproveReviews, @RequestParam(value="explodeOrderItems", required=false) boolean explodeOrderItems, @RequestParam(value="usePrimaryEmailUsername", required=false) boolean usePrimaryEmailUsername, @RequestParam(value="headerCancelStatus", required=false) String headerCancelStatus, @RequestParam(value="title", required=false) String title, @RequestParam(value="primaryStoreGroupId", required=false) String primaryStoreGroupId, @RequestParam(value="oldHeaderRightBackground", required=false) String oldHeaderRightBackground, @RequestParam(value="enableDigProdUpload", required=false) boolean enableDigProdUpload, @RequestParam(value="autoOrderCcTryOtherCards", required=false) boolean autoOrderCcTryOtherCards, @RequestParam(value="autoOrderCcTryLaterMax", required=false) long autoOrderCcTryLaterMax, @RequestParam(value="digitalItemApprovedStatus", required=false) String digitalItemApprovedStatus, @RequestParam(value="storeName", required=false) String storeName, @RequestParam(value="reserveInventory", required=false) boolean reserveInventory, @RequestParam(value="isImmediatelyFulfilled", required=false) boolean isImmediatelyFulfilled, @RequestParam(value="defaultPassword", required=false) String defaultPassword, @RequestParam(value="itemCancelStatus", required=false) String itemCancelStatus, @RequestParam(value="autoOrderCcTryExp", required=false) boolean autoOrderCcTryExp, @RequestParam(value="authDeclinedMessage", required=false) String authDeclinedMessage, @RequestParam(value="prodSearchExcludeVariants", required=false) boolean prodSearchExcludeVariants, @RequestParam(value="shipIfCaptureFails", required=false) boolean shipIfCaptureFails, @RequestParam(value="showPricesWithVatTax", required=false) boolean showPricesWithVatTax, @RequestParam(value="defaultCurrencyUomId", required=false) String defaultCurrencyUomId, @RequestParam(value="orderDecimalQuantity", required=false) boolean orderDecimalQuantity, @RequestParam(value="prorateShipping", required=false) boolean prorateShipping, @RequestParam(value="digProdUploadCategoryId", required=false) String digProdUploadCategoryId, @RequestParam(value="reqReturnInventoryReceive", required=false) boolean reqReturnInventoryReceive, @RequestParam(value="authErrorMessage", required=false) String authErrorMessage, @RequestParam(value="setOwnerUponIssuance", required=false) boolean setOwnerUponIssuance, @RequestParam(value="checkGcBalance", required=false) boolean checkGcBalance, @RequestParam(value="autoOrderCcTryLaterNsf", required=false) boolean autoOrderCcTryLaterNsf, @RequestParam(value="addToCartReplaceUpsell", required=false) boolean addToCartReplaceUpsell, @RequestParam(value="vatTaxAuthGeoId", required=false) String vatTaxAuthGeoId, @RequestParam(value="vatTaxAuthPartyId", required=false) String vatTaxAuthPartyId, @RequestParam(value="daysToCancelNonPay", required=false) long daysToCancelNonPay, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="defaultLocaleString", required=false) String defaultLocaleString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("viewCartOnAdd",viewCartOnAdd);
		paramMap.put("requireCustomerRole",requireCustomerRole);
		paramMap.put("companyName",companyName);
		paramMap.put("headerDeclinedStatus",headerDeclinedStatus);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("checkInventory",checkInventory);
		paramMap.put("autoApproveInvoice",autoApproveInvoice);
		paramMap.put("addToCartRemoveIncompat",addToCartRemoveIncompat);
		paramMap.put("enableAutoSuggestionList",enableAutoSuggestionList);
		paramMap.put("defaultSalesChannelEnumId",defaultSalesChannelEnumId);
		paramMap.put("autoApproveOrder",autoApproveOrder);
		paramMap.put("allowPassword",allowPassword);
		paramMap.put("showTaxIsExempt",showTaxIsExempt);
		paramMap.put("oldHeaderLogo",oldHeaderLogo);
		paramMap.put("autoSaveCart",autoSaveCart);
		paramMap.put("inventoryFacilityId",inventoryFacilityId);
		paramMap.put("headerApprovedStatus",headerApprovedStatus);
		paramMap.put("oldHeaderMiddleBackground",oldHeaderMiddleBackground);
		paramMap.put("defaultTimeZoneString",defaultTimeZoneString);
		paramMap.put("prorateTaxes",prorateTaxes);
		paramMap.put("showCheckoutGiftOptions",showCheckoutGiftOptions);
		paramMap.put("oldStyleSheet",oldStyleSheet);
		paramMap.put("manualAuthIsCapture",manualAuthIsCapture);
		paramMap.put("itemDeclinedStatus",itemDeclinedStatus);
		paramMap.put("visualThemeId",visualThemeId);
		paramMap.put("storeCreditAccountEnumId",storeCreditAccountEnumId);
		paramMap.put("showOutOfStockProducts",showOutOfStockProducts);
		paramMap.put("itemApprovedStatus",itemApprovedStatus);
		paramMap.put("allowComment",allowComment);
		paramMap.put("storeCreditValidDays",storeCreditValidDays);
		paramMap.put("splitPayPrefPerShpGrp",splitPayPrefPerShpGrp);
		paramMap.put("authFraudMessage",authFraudMessage);
		paramMap.put("managedByLot",managedByLot);
		paramMap.put("reqShipAddrForDigItems",reqShipAddrForDigItems);
		paramMap.put("oneInventoryFacility",oneInventoryFacility);
		paramMap.put("orderNumberPrefix",orderNumberPrefix);
		paramMap.put("autoInvoiceDigitalItems",autoInvoiceDigitalItems);
		paramMap.put("subtitle",subtitle);
		paramMap.put("reserveOrderEnumId",reserveOrderEnumId);
		paramMap.put("isDemoStore",isDemoStore);
		paramMap.put("retryFailedAuths",retryFailedAuths);
		paramMap.put("selectPaymentTypePerItem",selectPaymentTypePerItem);
		paramMap.put("balanceResOnOrderCreation",balanceResOnOrderCreation);
		paramMap.put("autoApproveReviews",autoApproveReviews);
		paramMap.put("explodeOrderItems",explodeOrderItems);
		paramMap.put("usePrimaryEmailUsername",usePrimaryEmailUsername);
		paramMap.put("headerCancelStatus",headerCancelStatus);
		paramMap.put("title",title);
		paramMap.put("primaryStoreGroupId",primaryStoreGroupId);
		paramMap.put("oldHeaderRightBackground",oldHeaderRightBackground);
		paramMap.put("enableDigProdUpload",enableDigProdUpload);
		paramMap.put("autoOrderCcTryOtherCards",autoOrderCcTryOtherCards);
		paramMap.put("autoOrderCcTryLaterMax",autoOrderCcTryLaterMax);
		paramMap.put("digitalItemApprovedStatus",digitalItemApprovedStatus);
		paramMap.put("storeName",storeName);
		paramMap.put("reserveInventory",reserveInventory);
		paramMap.put("isImmediatelyFulfilled",isImmediatelyFulfilled);
		paramMap.put("defaultPassword",defaultPassword);
		paramMap.put("itemCancelStatus",itemCancelStatus);
		paramMap.put("autoOrderCcTryExp",autoOrderCcTryExp);
		paramMap.put("authDeclinedMessage",authDeclinedMessage);
		paramMap.put("prodSearchExcludeVariants",prodSearchExcludeVariants);
		paramMap.put("shipIfCaptureFails",shipIfCaptureFails);
		paramMap.put("showPricesWithVatTax",showPricesWithVatTax);
		paramMap.put("defaultCurrencyUomId",defaultCurrencyUomId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("prorateShipping",prorateShipping);
		paramMap.put("digProdUploadCategoryId",digProdUploadCategoryId);
		paramMap.put("reqReturnInventoryReceive",reqReturnInventoryReceive);
		paramMap.put("authErrorMessage",authErrorMessage);
		paramMap.put("setOwnerUponIssuance",setOwnerUponIssuance);
		paramMap.put("checkGcBalance",checkGcBalance);
		paramMap.put("autoOrderCcTryLaterNsf",autoOrderCcTryLaterNsf);
		paramMap.put("addToCartReplaceUpsell",addToCartReplaceUpsell);
		paramMap.put("vatTaxAuthGeoId",vatTaxAuthGeoId);
		paramMap.put("vatTaxAuthPartyId",vatTaxAuthPartyId);
		paramMap.put("daysToCancelNonPay",daysToCancelNonPay);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("payToPartyId",payToPartyId);
		paramMap.put("defaultLocaleString",defaultLocaleString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductStore", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(value = (" * "))
	public ResponseEntity<Object> returnErrorPage(HttpSession session) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested service does not exist. JSESSIONID=" + session.getId());
	}

}
