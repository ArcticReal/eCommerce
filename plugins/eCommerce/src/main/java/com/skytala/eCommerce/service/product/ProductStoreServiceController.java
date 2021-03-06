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
@RequestMapping("/service/productStore")
public class ProductStoreServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreShipMeth")
	public ResponseEntity<Map<String, Object>> createProductStoreShipMeth(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="productStoreShipMethId", required=false) String productStoreShipMethId, @RequestParam(value="companyPartyId", required=false) String companyPartyId, @RequestParam(value="sequenceNumber", required=false) Long sequenceNumber, @RequestParam(value="allowancePercent", required=false) BigDecimal allowancePercent, @RequestParam(value="includeFeatureGroup", required=false) String includeFeatureGroup, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="minTotal", required=false) BigDecimal minTotal, @RequestParam(value="allowUspsAddr", required=false) String allowUspsAddr, @RequestParam(value="minWeight", required=false) BigDecimal minWeight, @RequestParam(value="requireCompanyAddr", required=false) String requireCompanyAddr, @RequestParam(value="maxSize", required=false) BigDecimal maxSize, @RequestParam(value="maxWeight", required=false) BigDecimal maxWeight, @RequestParam(value="excludeGeoId", required=false) String excludeGeoId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="includeGeoId", required=false) String includeGeoId, @RequestParam(value="maxTotal", required=false) BigDecimal maxTotal, @RequestParam(value="requireUspsAddr", required=false) String requireUspsAddr, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="configProps", required=false) String configProps, @RequestParam(value="minSize", required=false) BigDecimal minSize, @RequestParam(value="includeNoChargeItems", required=false) String includeNoChargeItems, @RequestParam(value="allowCompanyAddr", required=false) String allowCompanyAddr, @RequestParam(value="minimumPrice", required=false) BigDecimal minimumPrice, @RequestParam(value="excludeFeatureGroup", required=false) String excludeFeatureGroup) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreFacility")
	public ResponseEntity<Map<String, Object>> deleteProductStoreFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productStoreId") String productStoreId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupRole")
	public ResponseEntity<Map<String, Object>> createProductStoreGroupRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreCatalog")
	public ResponseEntity<Map<String, Object>> updateProductStoreCatalog(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroupRollup")
	public ResponseEntity<Map<String, Object>> deleteProductStoreGroupRollup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="parentGroupId") String parentGroupId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/isStoreInventoryAvailableOrNotRequired")
	public ResponseEntity<Map<String, Object>> isStoreInventoryAvailableOrNotRequired(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="product", required=false) org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="productStore", required=false) org.apache.ofbiz.entity.GenericValue productStore) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreCatalog")
	public ResponseEntity<Map<String, Object>> deleteProductStoreCatalog(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreVendorPayment")
	public ResponseEntity<Map<String, Object>> createProductStoreVendorPayment(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId, @RequestParam(value="creditCardEnumId") String creditCardEnumId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStorePaymentSetting")
	public ResponseEntity<Map<String, Object>> createProductStorePaymentSetting(HttpSession session, @RequestParam(value="paymentServiceTypeEnumId") String paymentServiceTypeEnumId, @RequestParam(value="applyToAllProducts") String applyToAllProducts, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="paymentService", required=false) Long paymentService, @RequestParam(value="paymentPropertiesPath", required=false) Long paymentPropertiesPath, @RequestParam(value="paymentCustomMethodId", required=false) String paymentCustomMethodId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreFacility")
	public ResponseEntity<Map<String, Object>> createProductStoreFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroupRole")
	public ResponseEntity<Map<String, Object>> deleteProductStoreGroupRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreFinActSetting")
	public ResponseEntity<Map<String, Object>> updateProductStoreFinActSetting(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="authValidDays", required=false) Long authValidDays, @RequestParam(value="allowAuthToNegative", required=false) String allowAuthToNegative, @RequestParam(value="accountCodeLength", required=false) Long accountCodeLength, @RequestParam(value="purchSurveyCopyMe", required=false) String purchSurveyCopyMe, @RequestParam(value="validateGCFinAcct", required=false) String validateGCFinAcct, @RequestParam(value="replenishThreshold", required=false) BigDecimal replenishThreshold, @RequestParam(value="accountValidDays", required=false) Long accountValidDays, @RequestParam(value="replenishMethodEnumId", required=false) String replenishMethodEnumId, @RequestParam(value="purchSurveySendTo", required=false) String purchSurveySendTo, @RequestParam(value="minBalance", required=false) BigDecimal minBalance, @RequestParam(value="purchaseSurveyId", required=false) String purchaseSurveyId, @RequestParam(value="requirePinCode", required=false) String requirePinCode, @RequestParam(value="pinCodeLength", required=false) Long pinCodeLength) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreEmailSetting")
	public ResponseEntity<Map<String, Object>> removeProductStoreEmailSetting(HttpSession session, @RequestParam(value="emailType") String emailType, @RequestParam(value="productStoreId") String productStoreId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreShipMeth")
	public ResponseEntity<Map<String, Object>> updateProductStoreShipMeth(HttpSession session, @RequestParam(value="productStoreShipMethId") String productStoreShipMethId, @RequestParam(value="companyPartyId", required=false) String companyPartyId, @RequestParam(value="allowancePercent", required=false) BigDecimal allowancePercent, @RequestParam(value="includeFeatureGroup", required=false) String includeFeatureGroup, @RequestParam(value="shipmentCustomMethodId", required=false) String shipmentCustomMethodId, @RequestParam(value="minTotal", required=false) BigDecimal minTotal, @RequestParam(value="minWeight", required=false) BigDecimal minWeight, @RequestParam(value="requireCompanyAddr", required=false) String requireCompanyAddr, @RequestParam(value="maxSize", required=false) BigDecimal maxSize, @RequestParam(value="excludeGeoId", required=false) String excludeGeoId, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="minSize", required=false) BigDecimal minSize, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="allowCompanyAddr", required=false) String allowCompanyAddr, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="minimumPrice", required=false) BigDecimal minimumPrice, @RequestParam(value="shipmentMethodTypeId", required=false) String shipmentMethodTypeId, @RequestParam(value="excludeFeatureGroup", required=false) String excludeFeatureGroup, @RequestParam(value="sequenceNumber", required=false) Long sequenceNumber, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="allowUspsAddr", required=false) String allowUspsAddr, @RequestParam(value="maxWeight", required=false) BigDecimal maxWeight, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="includeGeoId", required=false) String includeGeoId, @RequestParam(value="maxTotal", required=false) BigDecimal maxTotal, @RequestParam(value="requireUspsAddr", required=false) String requireUspsAddr, @RequestParam(value="configProps", required=false) String configProps, @RequestParam(value="includeNoChargeItems", required=false) String includeNoChargeItems) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/isStoreInventoryAvailable")
	public ResponseEntity<Map<String, Object>> isStoreInventoryAvailable(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="product", required=false) org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="productStore", required=false) org.apache.ofbiz.entity.GenericValue productStore) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreFinActSetting")
	public ResponseEntity<Map<String, Object>> removeProductStoreFinActSetting(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="finAccountTypeId") String finAccountTypeId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreRole")
	public ResponseEntity<Map<String, Object>> removeProductStoreRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreCatalog")
	public ResponseEntity<Map<String, Object>> createProductStoreCatalog(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupRollup")
	public ResponseEntity<Map<String, Object>> createProductStoreGroupRollup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="parentGroupId") String parentGroupId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreFacility")
	public ResponseEntity<Map<String, Object>> updateProductStoreFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductStoreShipMeth")
	public ResponseEntity<Map<String, Object>> removeProductStoreShipMeth(HttpSession session, @RequestParam(value="productStoreShipMethId") String productStoreShipMethId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreShipMethId",productStoreShipMethId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductStoreShipMeth", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStorePromoAppl")
	public ResponseEntity<Map<String, Object>> deleteProductStorePromoAppl(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productPromoId") String productPromoId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreVendorShipment")
	public ResponseEntity<Map<String, Object>> createProductStoreVendorShipment(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroup")
	public ResponseEntity<Map<String, Object>> deleteProductStoreGroup(HttpSession session, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreGroup", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreVendorShipment")
	public ResponseEntity<Map<String, Object>> deleteProductStoreVendorShipment(HttpSession session, @RequestParam(value="carrierPartyId") String carrierPartyId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="shipmentMethodTypeId") String shipmentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStore")
	public ResponseEntity<Map<String, Object>> createProductStore(HttpSession session, @RequestParam(value="storeName") String storeName, @RequestParam(value="viewCartOnAdd", required=false) String viewCartOnAdd, @RequestParam(value="requireCustomerRole", required=false) String requireCustomerRole, @RequestParam(value="companyName", required=false) String companyName, @RequestParam(value="headerDeclinedStatus", required=false) String headerDeclinedStatus, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="checkInventory", required=false) String checkInventory, @RequestParam(value="autoApproveInvoice", required=false) String autoApproveInvoice, @RequestParam(value="addToCartRemoveIncompat", required=false) String addToCartRemoveIncompat, @RequestParam(value="enableAutoSuggestionList", required=false) String enableAutoSuggestionList, @RequestParam(value="defaultSalesChannelEnumId", required=false) String defaultSalesChannelEnumId, @RequestParam(value="autoApproveOrder", required=false) String autoApproveOrder, @RequestParam(value="allowPassword", required=false) String allowPassword, @RequestParam(value="showTaxIsExempt", required=false) String showTaxIsExempt, @RequestParam(value="oldHeaderLogo", required=false) String oldHeaderLogo, @RequestParam(value="autoSaveCart", required=false) String autoSaveCart, @RequestParam(value="inventoryFacilityId", required=false) String inventoryFacilityId, @RequestParam(value="headerApprovedStatus", required=false) String headerApprovedStatus, @RequestParam(value="oldHeaderMiddleBackground", required=false) String oldHeaderMiddleBackground, @RequestParam(value="defaultTimeZoneString", required=false) String defaultTimeZoneString, @RequestParam(value="prorateTaxes", required=false) String prorateTaxes, @RequestParam(value="showCheckoutGiftOptions", required=false) String showCheckoutGiftOptions, @RequestParam(value="oldStyleSheet", required=false) String oldStyleSheet, @RequestParam(value="manualAuthIsCapture", required=false) String manualAuthIsCapture, @RequestParam(value="itemDeclinedStatus", required=false) String itemDeclinedStatus, @RequestParam(value="visualThemeId", required=false) String visualThemeId, @RequestParam(value="storeCreditAccountEnumId", required=false) String storeCreditAccountEnumId, @RequestParam(value="showOutOfStockProducts", required=false) String showOutOfStockProducts, @RequestParam(value="itemApprovedStatus", required=false) String itemApprovedStatus, @RequestParam(value="allowComment", required=false) String allowComment, @RequestParam(value="storeCreditValidDays", required=false) Long storeCreditValidDays, @RequestParam(value="splitPayPrefPerShpGrp", required=false) String splitPayPrefPerShpGrp, @RequestParam(value="authFraudMessage", required=false) String authFraudMessage, @RequestParam(value="managedByLot", required=false) String managedByLot, @RequestParam(value="reqShipAddrForDigItems", required=false) String reqShipAddrForDigItems, @RequestParam(value="oneInventoryFacility", required=false) String oneInventoryFacility, @RequestParam(value="orderNumberPrefix", required=false) String orderNumberPrefix, @RequestParam(value="autoInvoiceDigitalItems", required=false) String autoInvoiceDigitalItems, @RequestParam(value="subtitle", required=false) String subtitle, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="isDemoStore", required=false) String isDemoStore, @RequestParam(value="retryFailedAuths", required=false) String retryFailedAuths, @RequestParam(value="selectPaymentTypePerItem", required=false) String selectPaymentTypePerItem, @RequestParam(value="balanceResOnOrderCreation", required=false) String balanceResOnOrderCreation, @RequestParam(value="autoApproveReviews", required=false) String autoApproveReviews, @RequestParam(value="explodeOrderItems", required=false) String explodeOrderItems, @RequestParam(value="usePrimaryEmailUsername", required=false) String usePrimaryEmailUsername, @RequestParam(value="headerCancelStatus", required=false) String headerCancelStatus, @RequestParam(value="title", required=false) String title, @RequestParam(value="primaryStoreGroupId", required=false) String primaryStoreGroupId, @RequestParam(value="oldHeaderRightBackground", required=false) String oldHeaderRightBackground, @RequestParam(value="enableDigProdUpload", required=false) String enableDigProdUpload, @RequestParam(value="autoOrderCcTryOtherCards", required=false) String autoOrderCcTryOtherCards, @RequestParam(value="autoOrderCcTryLaterMax", required=false) Long autoOrderCcTryLaterMax, @RequestParam(value="digitalItemApprovedStatus", required=false) String digitalItemApprovedStatus, @RequestParam(value="reserveInventory", required=false) String reserveInventory, @RequestParam(value="isImmediatelyFulfilled", required=false) String isImmediatelyFulfilled, @RequestParam(value="defaultPassword", required=false) String defaultPassword, @RequestParam(value="itemCancelStatus", required=false) String itemCancelStatus, @RequestParam(value="autoOrderCcTryExp", required=false) String autoOrderCcTryExp, @RequestParam(value="authDeclinedMessage", required=false) String authDeclinedMessage, @RequestParam(value="prodSearchExcludeVariants", required=false) String prodSearchExcludeVariants, @RequestParam(value="shipIfCaptureFails", required=false) String shipIfCaptureFails, @RequestParam(value="showPricesWithVatTax", required=false) String showPricesWithVatTax, @RequestParam(value="defaultCurrencyUomId", required=false) String defaultCurrencyUomId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="prorateShipping", required=false) String prorateShipping, @RequestParam(value="digProdUploadCategoryId", required=false) String digProdUploadCategoryId, @RequestParam(value="reqReturnInventoryReceive", required=false) String reqReturnInventoryReceive, @RequestParam(value="authErrorMessage", required=false) String authErrorMessage, @RequestParam(value="setOwnerUponIssuance", required=false) String setOwnerUponIssuance, @RequestParam(value="checkGcBalance", required=false) String checkGcBalance, @RequestParam(value="autoOrderCcTryLaterNsf", required=false) String autoOrderCcTryLaterNsf, @RequestParam(value="addToCartReplaceUpsell", required=false) String addToCartReplaceUpsell, @RequestParam(value="vatTaxAuthGeoId", required=false) String vatTaxAuthGeoId, @RequestParam(value="vatTaxAuthPartyId", required=false) String vatTaxAuthPartyId, @RequestParam(value="daysToCancelNonPay", required=false) Long daysToCancelNonPay, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="defaultLocaleString", required=false) String defaultLocaleString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("storeName",storeName);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreEmailSetting")
	public ResponseEntity<Map<String, Object>> updateProductStoreEmailSetting(HttpSession session, @RequestParam(value="emailType") String emailType, @RequestParam(value="bodyScreenLocation") String bodyScreenLocation, @RequestParam(value="subject") String subject, @RequestParam(value="fromAddress") String fromAddress, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="bccAddress", required=false) String bccAddress, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="ccAddress", required=false) String ccAddress) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupType")
	public ResponseEntity<Map<String, Object>> createProductStoreGroupType(HttpSession session, @RequestParam(value="productStoreGroupTypeId", required=false) String productStoreGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreKeywordOvrd")
	public ResponseEntity<Map<String, Object>> createProductStoreKeywordOvrd(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="targetTypeEnumId") String targetTypeEnumId, @RequestParam(value="keyword") String keyword, @RequestParam(value="target") String target, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroupRollup")
	public ResponseEntity<Map<String, Object>> updateProductStoreGroupRollup(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="parentGroupId") String parentGroupId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroupType")
	public ResponseEntity<Map<String, Object>> updateProductStoreGroupType(HttpSession session, @RequestParam(value="productStoreGroupTypeId") String productStoreGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStorePaymentSetting")
	public ResponseEntity<Map<String, Object>> updateProductStorePaymentSetting(HttpSession session, @RequestParam(value="paymentServiceTypeEnumId") String paymentServiceTypeEnumId, @RequestParam(value="applyToAllProducts") String applyToAllProducts, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="paymentGatewayConfigId", required=false) String paymentGatewayConfigId, @RequestParam(value="paymentService", required=false) Long paymentService, @RequestParam(value="paymentPropertiesPath", required=false) Long paymentPropertiesPath, @RequestParam(value="paymentCustomMethodId", required=false) String paymentCustomMethodId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkProductStoreGroupRollup")
	public ResponseEntity<Map<String, Object>> checkProductStoreGroupRollup(HttpSession session, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="parentGroupId", required=false) String parentGroupId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreEmailSetting")
	public ResponseEntity<Map<String, Object>> createProductStoreEmailSetting(HttpSession session, @RequestParam(value="emailType") String emailType, @RequestParam(value="bodyScreenLocation") String bodyScreenLocation, @RequestParam(value="subject") String subject, @RequestParam(value="fromAddress") String fromAddress, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="xslfoAttachScreenLocation", required=false) String xslfoAttachScreenLocation, @RequestParam(value="bccAddress", required=false) String bccAddress, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="ccAddress", required=false) String ccAddress) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreRole")
	public ResponseEntity<Map<String, Object>> createProductStoreRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreFinActSetting")
	public ResponseEntity<Map<String, Object>> createProductStoreFinActSetting(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="finAccountTypeId") String finAccountTypeId, @RequestParam(value="authValidDays", required=false) Long authValidDays, @RequestParam(value="allowAuthToNegative", required=false) String allowAuthToNegative, @RequestParam(value="accountCodeLength", required=false) Long accountCodeLength, @RequestParam(value="purchSurveyCopyMe", required=false) String purchSurveyCopyMe, @RequestParam(value="validateGCFinAcct", required=false) String validateGCFinAcct, @RequestParam(value="replenishThreshold", required=false) BigDecimal replenishThreshold, @RequestParam(value="accountValidDays", required=false) Long accountValidDays, @RequestParam(value="replenishMethodEnumId", required=false) String replenishMethodEnumId, @RequestParam(value="purchSurveySendTo", required=false) String purchSurveySendTo, @RequestParam(value="minBalance", required=false) BigDecimal minBalance, @RequestParam(value="purchaseSurveyId", required=false) String purchaseSurveyId, @RequestParam(value="requirePinCode", required=false) String requirePinCode, @RequestParam(value="pinCodeLength", required=false) Long pinCodeLength) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/isStoreInventoryRequired")
	public ResponseEntity<Map<String, Object>> isStoreInventoryRequired(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="product", required=false) org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="productStore", required=false) org.apache.ofbiz.entity.GenericValue productStore) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroupMember")
	public ResponseEntity<Map<String, Object>> createProductStoreGroupMember(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroup")
	public ResponseEntity<Map<String, Object>> updateProductStoreGroup(HttpSession session, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="productStoreGroupTypeId", required=false) String productStoreGroupTypeId, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="productStoreGroupName", required=false) String productStoreGroupName, @RequestParam(value="description", required=false) String description) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreGroupType")
	public ResponseEntity<Map<String, Object>> deleteProductStoreGroupType(HttpSession session, @RequestParam(value="productStoreGroupTypeId") String productStoreGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreGroupTypeId",productStoreGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreGroupType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreVendorPayment")
	public ResponseEntity<Map<String, Object>> deleteProductStoreVendorPayment(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="vendorPartyId") String vendorPartyId, @RequestParam(value="creditCardEnumId") String creditCardEnumId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStorePaymentSetting")
	public ResponseEntity<Map<String, Object>> deleteProductStorePaymentSetting(HttpSession session, @RequestParam(value="paymentServiceTypeEnumId") String paymentServiceTypeEnumId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreSurveyAppl")
	public ResponseEntity<Map<String, Object>> deleteProductStoreSurveyAppl(HttpSession session, @RequestParam(value="productStoreSurveyId") String productStoreSurveyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreSurveyId",productStoreSurveyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductStoreSurveyAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreGroupMember")
	public ResponseEntity<Map<String, Object>> updateProductStoreGroupMember(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreKeywordOvrd")
	public ResponseEntity<Map<String, Object>> updateProductStoreKeywordOvrd(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="keyword") String keyword, @RequestParam(value="targetTypeEnumId", required=false) String targetTypeEnumId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="target", required=false) String target) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStorePromoAppl")
	public ResponseEntity<Map<String, Object>> updateProductStorePromoAppl(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="manualOnly", required=false) String manualOnly, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStorePromoAppl")
	public ResponseEntity<Map<String, Object>> createProductStorePromoAppl(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="manualOnly", required=false) String manualOnly, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductStoreKeywordOvrd")
	public ResponseEntity<Map<String, Object>> deleteProductStoreKeywordOvrd(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="keyword") String keyword) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreSurveyAppl")
	public ResponseEntity<Map<String, Object>> createProductStoreSurveyAppl(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="groupName", required=false) String groupName, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="surveyTemplate", required=false) String surveyTemplate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="resultTemplate", required=false) String resultTemplate, @RequestParam(value="surveyApplTypeId", required=false) String surveyApplTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductStoreGroup")
	public ResponseEntity<Map<String, Object>> createProductStoreGroup(HttpSession session, @RequestParam(value="productStoreGroupTypeId", required=false) String productStoreGroupTypeId, @RequestParam(value="primaryParentGroupId", required=false) String primaryParentGroupId, @RequestParam(value="productStoreGroupName", required=false) String productStoreGroupName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
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

	@RequestMapping(method = RequestMethod.POST, value = "/reserveStoreInventory")
	public ResponseEntity<Map<String, Object>> reserveStoreInventory(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="shipGroupSeqId", required=false) String shipGroupSeqId) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStoreRole")
	public ResponseEntity<Map<String, Object>> updateProductStoreRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="partyId") String partyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/productStoreGenericPermission")
	public ResponseEntity<Map<String, Object>> productStoreGenericPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductStore")
	public ResponseEntity<Map<String, Object>> updateProductStore(HttpSession session, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="viewCartOnAdd", required=false) String viewCartOnAdd, @RequestParam(value="requireCustomerRole", required=false) String requireCustomerRole, @RequestParam(value="companyName", required=false) String companyName, @RequestParam(value="headerDeclinedStatus", required=false) String headerDeclinedStatus, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="checkInventory", required=false) String checkInventory, @RequestParam(value="autoApproveInvoice", required=false) String autoApproveInvoice, @RequestParam(value="addToCartRemoveIncompat", required=false) String addToCartRemoveIncompat, @RequestParam(value="enableAutoSuggestionList", required=false) String enableAutoSuggestionList, @RequestParam(value="defaultSalesChannelEnumId", required=false) String defaultSalesChannelEnumId, @RequestParam(value="autoApproveOrder", required=false) String autoApproveOrder, @RequestParam(value="allowPassword", required=false) String allowPassword, @RequestParam(value="showTaxIsExempt", required=false) String showTaxIsExempt, @RequestParam(value="oldHeaderLogo", required=false) String oldHeaderLogo, @RequestParam(value="autoSaveCart", required=false) String autoSaveCart, @RequestParam(value="inventoryFacilityId", required=false) String inventoryFacilityId, @RequestParam(value="headerApprovedStatus", required=false) String headerApprovedStatus, @RequestParam(value="oldHeaderMiddleBackground", required=false) String oldHeaderMiddleBackground, @RequestParam(value="defaultTimeZoneString", required=false) String defaultTimeZoneString, @RequestParam(value="prorateTaxes", required=false) String prorateTaxes, @RequestParam(value="showCheckoutGiftOptions", required=false) String showCheckoutGiftOptions, @RequestParam(value="oldStyleSheet", required=false) String oldStyleSheet, @RequestParam(value="manualAuthIsCapture", required=false) String manualAuthIsCapture, @RequestParam(value="itemDeclinedStatus", required=false) String itemDeclinedStatus, @RequestParam(value="visualThemeId", required=false) String visualThemeId, @RequestParam(value="storeCreditAccountEnumId", required=false) String storeCreditAccountEnumId, @RequestParam(value="showOutOfStockProducts", required=false) String showOutOfStockProducts, @RequestParam(value="itemApprovedStatus", required=false) String itemApprovedStatus, @RequestParam(value="allowComment", required=false) String allowComment, @RequestParam(value="storeCreditValidDays", required=false) Long storeCreditValidDays, @RequestParam(value="splitPayPrefPerShpGrp", required=false) String splitPayPrefPerShpGrp, @RequestParam(value="authFraudMessage", required=false) String authFraudMessage, @RequestParam(value="managedByLot", required=false) String managedByLot, @RequestParam(value="reqShipAddrForDigItems", required=false) String reqShipAddrForDigItems, @RequestParam(value="oneInventoryFacility", required=false) String oneInventoryFacility, @RequestParam(value="orderNumberPrefix", required=false) String orderNumberPrefix, @RequestParam(value="autoInvoiceDigitalItems", required=false) String autoInvoiceDigitalItems, @RequestParam(value="subtitle", required=false) String subtitle, @RequestParam(value="reserveOrderEnumId", required=false) String reserveOrderEnumId, @RequestParam(value="isDemoStore", required=false) String isDemoStore, @RequestParam(value="retryFailedAuths", required=false) String retryFailedAuths, @RequestParam(value="selectPaymentTypePerItem", required=false) String selectPaymentTypePerItem, @RequestParam(value="balanceResOnOrderCreation", required=false) String balanceResOnOrderCreation, @RequestParam(value="autoApproveReviews", required=false) String autoApproveReviews, @RequestParam(value="explodeOrderItems", required=false) String explodeOrderItems, @RequestParam(value="usePrimaryEmailUsername", required=false) String usePrimaryEmailUsername, @RequestParam(value="headerCancelStatus", required=false) String headerCancelStatus, @RequestParam(value="title", required=false) String title, @RequestParam(value="primaryStoreGroupId", required=false) String primaryStoreGroupId, @RequestParam(value="oldHeaderRightBackground", required=false) String oldHeaderRightBackground, @RequestParam(value="enableDigProdUpload", required=false) String enableDigProdUpload, @RequestParam(value="autoOrderCcTryOtherCards", required=false) String autoOrderCcTryOtherCards, @RequestParam(value="autoOrderCcTryLaterMax", required=false) Long autoOrderCcTryLaterMax, @RequestParam(value="digitalItemApprovedStatus", required=false) String digitalItemApprovedStatus, @RequestParam(value="storeName", required=false) String storeName, @RequestParam(value="reserveInventory", required=false) String reserveInventory, @RequestParam(value="isImmediatelyFulfilled", required=false) String isImmediatelyFulfilled, @RequestParam(value="defaultPassword", required=false) String defaultPassword, @RequestParam(value="itemCancelStatus", required=false) String itemCancelStatus, @RequestParam(value="autoOrderCcTryExp", required=false) String autoOrderCcTryExp, @RequestParam(value="authDeclinedMessage", required=false) String authDeclinedMessage, @RequestParam(value="prodSearchExcludeVariants", required=false) String prodSearchExcludeVariants, @RequestParam(value="shipIfCaptureFails", required=false) String shipIfCaptureFails, @RequestParam(value="showPricesWithVatTax", required=false) String showPricesWithVatTax, @RequestParam(value="defaultCurrencyUomId", required=false) String defaultCurrencyUomId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="prorateShipping", required=false) String prorateShipping, @RequestParam(value="digProdUploadCategoryId", required=false) String digProdUploadCategoryId, @RequestParam(value="reqReturnInventoryReceive", required=false) String reqReturnInventoryReceive, @RequestParam(value="authErrorMessage", required=false) String authErrorMessage, @RequestParam(value="setOwnerUponIssuance", required=false) String setOwnerUponIssuance, @RequestParam(value="checkGcBalance", required=false) String checkGcBalance, @RequestParam(value="autoOrderCcTryLaterNsf", required=false) String autoOrderCcTryLaterNsf, @RequestParam(value="addToCartReplaceUpsell", required=false) String addToCartReplaceUpsell, @RequestParam(value="vatTaxAuthGeoId", required=false) String vatTaxAuthGeoId, @RequestParam(value="vatTaxAuthPartyId", required=false) String vatTaxAuthPartyId, @RequestParam(value="daysToCancelNonPay", required=false) Long daysToCancelNonPay, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="defaultLocaleString", required=false) String defaultLocaleString) {
		
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
