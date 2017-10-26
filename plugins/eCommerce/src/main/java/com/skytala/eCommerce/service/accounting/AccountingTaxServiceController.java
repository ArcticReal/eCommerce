package com.skytala.eCommerce.service.accounting;

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
@RequestMapping("/service/accountingTax")
public class AccountingTaxServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateTaxAuthorityCategory")
	public ResponseEntity<Object> updateTaxAuthorityCategory(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTaxAuthorityCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTaxAuthorityRateProduct")
	public ResponseEntity<Object> deleteTaxAuthorityRateProduct(HttpSession session, @RequestParam(value="taxAuthorityRateSeqId") String taxAuthorityRateSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTaxAuthorityRateProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTaxAuthority")
	public ResponseEntity<Object> createTaxAuthority(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="requireTaxIdForExemption", required=false) String requireTaxIdForExemption, @RequestParam(value="includeTaxInPrice", required=false) String includeTaxInPrice, @RequestParam(value="taxIdFormatPattern", required=false) String taxIdFormatPattern) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("requireTaxIdForExemption",requireTaxIdForExemption);
		paramMap.put("includeTaxInPrice",includeTaxInPrice);
		paramMap.put("taxIdFormatPattern",taxIdFormatPattern);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTaxAuthority", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyTaxAuthInfo")
	public ResponseEntity<Object> createPartyTaxAuthInfo(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="partyTaxId", required=false) String partyTaxId, @RequestParam(value="isNexus", required=false) String isNexus, @RequestParam(value="isExempt", required=false) String isExempt, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyTaxId",partyTaxId);
		paramMap.put("isNexus",isNexus);
		paramMap.put("isExempt",isExempt);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyTaxAuthInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcTaxTotalForDisplayInterface")
	public ResponseEntity<Object> calcTaxTotalForDisplayInterface(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="basePrice") BigDecimal basePrice, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="shippingPrice", required=false) BigDecimal shippingPrice, @RequestParam(value="billToPartyId", required=false) String billToPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("basePrice",basePrice);
		paramMap.put("quantity",quantity);
		paramMap.put("shippingPrice",shippingPrice);
		paramMap.put("billToPartyId",billToPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calcTaxTotalForDisplayInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTaxAuthorityRateProduct")
	public ResponseEntity<Object> updateTaxAuthorityRateProduct(HttpSession session, @RequestParam(value="taxAuthorityRateSeqId") String taxAuthorityRateSeqId, @RequestParam(value="taxShipping", required=false) String taxShipping, @RequestParam(value="taxPercentage", required=false) BigDecimal taxPercentage, @RequestParam(value="description", required=false) String description, @RequestParam(value="titleTransferEnumId", required=false) String titleTransferEnumId, @RequestParam(value="taxPromotions", required=false) String taxPromotions, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="minItemPrice", required=false) BigDecimal minItemPrice, @RequestParam(value="minPurchase", required=false) BigDecimal minPurchase, @RequestParam(value="taxAuthorityRateTypeId", required=false) String taxAuthorityRateTypeId, @RequestParam(value="productStoreId", required=false) String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthorityRateSeqId",taxAuthorityRateSeqId);
		paramMap.put("taxShipping",taxShipping);
		paramMap.put("taxPercentage",taxPercentage);
		paramMap.put("description",description);
		paramMap.put("titleTransferEnumId",titleTransferEnumId);
		paramMap.put("taxPromotions",taxPromotions);
		paramMap.put("thruDate",thruDate);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("minItemPrice",minItemPrice);
		paramMap.put("minPurchase",minPurchase);
		paramMap.put("taxAuthorityRateTypeId",taxAuthorityRateTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTaxAuthorityRateProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcTaxInterface")
	public ResponseEntity<Object> calcTaxInterface(HttpSession session, @RequestParam(value="itemPriceList") java.util.List itemPriceList, @RequestParam(value="itemAmountList") java.util.List itemAmountList, @RequestParam(value="itemProductList") java.util.List itemProductList, @RequestParam(value="orderPromotionsAmount", required=false) BigDecimal orderPromotionsAmount, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="orderShippingAmount", required=false) BigDecimal orderShippingAmount, @RequestParam(value="billToPartyId", required=false) String billToPartyId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="itemQuantityList", required=false) java.util.List itemQuantityList, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="itemShippingList", required=false) java.util.List itemShippingList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemPriceList",itemPriceList);
		paramMap.put("itemAmountList",itemAmountList);
		paramMap.put("itemProductList",itemProductList);
		paramMap.put("orderPromotionsAmount",orderPromotionsAmount);
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderShippingAmount",orderShippingAmount);
		paramMap.put("billToPartyId",billToPartyId);
		paramMap.put("shippingAddress",shippingAddress);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("itemQuantityList",itemQuantityList);
		paramMap.put("payToPartyId",payToPartyId);
		paramMap.put("itemShippingList",itemShippingList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calcTaxInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyTaxAuthInfo")
	public ResponseEntity<Object> updatePartyTaxAuthInfo(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="partyId") String partyId, @RequestParam(value="partyTaxId", required=false) String partyTaxId, @RequestParam(value="isNexus", required=false) String isNexus, @RequestParam(value="isExempt", required=false) String isExempt, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("partyId",partyId);
		paramMap.put("partyTaxId",partyTaxId);
		paramMap.put("isNexus",isNexus);
		paramMap.put("isExempt",isExempt);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyTaxAuthInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTaxAuthorityAssoc")
	public ResponseEntity<Object> deleteTaxAuthorityAssoc(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="toTaxAuthPartyId") String toTaxAuthPartyId, @RequestParam(value="toTaxAuthGeoId") String toTaxAuthGeoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("toTaxAuthPartyId",toTaxAuthPartyId);
		paramMap.put("toTaxAuthGeoId",toTaxAuthGeoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTaxAuthorityAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTaxAuthorityGlAccount")
	public ResponseEntity<Object> deleteTaxAuthorityGlAccount(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTaxAuthorityGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTaxAuthority")
	public ResponseEntity<Object> updateTaxAuthority(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="requireTaxIdForExemption", required=false) String requireTaxIdForExemption, @RequestParam(value="includeTaxInPrice", required=false) String includeTaxInPrice, @RequestParam(value="taxIdFormatPattern", required=false) String taxIdFormatPattern) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("requireTaxIdForExemption",requireTaxIdForExemption);
		paramMap.put("includeTaxInPrice",includeTaxInPrice);
		paramMap.put("taxIdFormatPattern",taxIdFormatPattern);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTaxAuthority", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTaxAuthorityAssocType")
	public ResponseEntity<Object> deleteTaxAuthorityAssocType(HttpSession session, @RequestParam(value="taxAuthorityAssocTypeId") String taxAuthorityAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthorityAssocTypeId",taxAuthorityAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTaxAuthorityAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustomerTaxAuthInfo")
	public ResponseEntity<Object> createCustomerTaxAuthInfo(HttpSession session, @RequestParam(value="taxAuthPartyGeoIds") String taxAuthPartyGeoIds, @RequestParam(value="partyId") String partyId, @RequestParam(value="partyTaxId", required=false) String partyTaxId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="isNexus", required=false) String isNexus, @RequestParam(value="isExempt", required=false) String isExempt, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyGeoIds",taxAuthPartyGeoIds);
		paramMap.put("partyId",partyId);
		paramMap.put("partyTaxId",partyTaxId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("isNexus",isNexus);
		paramMap.put("isExempt",isExempt);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustomerTaxAuthInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTaxAuthorityRateType")
	public ResponseEntity<Object> updateTaxAuthorityRateType(HttpSession session, @RequestParam(value="taxAuthorityRateTypeId") String taxAuthorityRateTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthorityRateTypeId",taxAuthorityRateTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTaxAuthorityRateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcTaxForDisplay")
	public ResponseEntity<Object> calcTaxForDisplay(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="basePrice") BigDecimal basePrice, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="shippingPrice", required=false) BigDecimal shippingPrice, @RequestParam(value="billToPartyId", required=false) String billToPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("basePrice",basePrice);
		paramMap.put("quantity",quantity);
		paramMap.put("shippingPrice",shippingPrice);
		paramMap.put("billToPartyId",billToPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calcTaxForDisplay", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTaxAuthorityGlAccount")
	public ResponseEntity<Object> updateTaxAuthorityGlAccount(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="glAccountId", required=false) String glAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTaxAuthorityGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTaxAuthorityAssocType")
	public ResponseEntity<Object> createTaxAuthorityAssocType(HttpSession session, @RequestParam(value="taxAuthorityAssocTypeId", required=false) String taxAuthorityAssocTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthorityAssocTypeId",taxAuthorityAssocTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTaxAuthorityAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTaxAuthorityAssocType")
	public ResponseEntity<Object> updateTaxAuthorityAssocType(HttpSession session, @RequestParam(value="taxAuthorityAssocTypeId") String taxAuthorityAssocTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthorityAssocTypeId",taxAuthorityAssocTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTaxAuthorityAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTaxAuthorityAssoc")
	public ResponseEntity<Object> createTaxAuthorityAssoc(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="toTaxAuthPartyId") String toTaxAuthPartyId, @RequestParam(value="toTaxAuthGeoId") String toTaxAuthGeoId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="taxAuthorityAssocTypeId", required=false) String taxAuthorityAssocTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("toTaxAuthPartyId",toTaxAuthPartyId);
		paramMap.put("toTaxAuthGeoId",toTaxAuthGeoId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("taxAuthorityAssocTypeId",taxAuthorityAssocTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTaxAuthorityAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTaxAuthorityRateType")
	public ResponseEntity<Object> deleteTaxAuthorityRateType(HttpSession session, @RequestParam(value="taxAuthorityRateTypeId") String taxAuthorityRateTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthorityRateTypeId",taxAuthorityRateTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTaxAuthorityRateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTaxAuthorityRateProduct")
	public ResponseEntity<Object> createTaxAuthorityRateProduct(HttpSession session, @RequestParam(value="taxShipping", required=false) String taxShipping, @RequestParam(value="taxPercentage", required=false) BigDecimal taxPercentage, @RequestParam(value="description", required=false) String description, @RequestParam(value="titleTransferEnumId", required=false) String titleTransferEnumId, @RequestParam(value="taxPromotions", required=false) String taxPromotions, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="minItemPrice", required=false) BigDecimal minItemPrice, @RequestParam(value="minPurchase", required=false) BigDecimal minPurchase, @RequestParam(value="taxAuthorityRateTypeId", required=false) String taxAuthorityRateTypeId, @RequestParam(value="productStoreId", required=false) String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxShipping",taxShipping);
		paramMap.put("taxPercentage",taxPercentage);
		paramMap.put("description",description);
		paramMap.put("titleTransferEnumId",titleTransferEnumId);
		paramMap.put("taxPromotions",taxPromotions);
		paramMap.put("thruDate",thruDate);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("minItemPrice",minItemPrice);
		paramMap.put("minPurchase",minPurchase);
		paramMap.put("taxAuthorityRateTypeId",taxAuthorityRateTypeId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTaxAuthorityRateProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTaxAuthorityCategory")
	public ResponseEntity<Object> createTaxAuthorityCategory(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTaxAuthorityCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/importZipSalesTaxData")
	public ResponseEntity<Object> importZipSalesTaxData(HttpSession session, @RequestParam(value="ruleFileLocation") String ruleFileLocation, @RequestParam(value="taxFileLocation") String taxFileLocation) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ruleFileLocation",ruleFileLocation);
		paramMap.put("taxFileLocation",taxFileLocation);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("importZipSalesTaxData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyTaxAuthInfo")
	public ResponseEntity<Object> deletePartyTaxAuthInfo(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyTaxAuthInfo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTaxAuthorityRateType")
	public ResponseEntity<Object> createTaxAuthorityRateType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="taxAuthorityRateTypeId", required=false) String taxAuthorityRateTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("taxAuthorityRateTypeId",taxAuthorityRateTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTaxAuthorityRateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTaxAuthorityAssoc")
	public ResponseEntity<Object> updateTaxAuthorityAssoc(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="toTaxAuthPartyId") String toTaxAuthPartyId, @RequestParam(value="toTaxAuthGeoId") String toTaxAuthGeoId, @RequestParam(value="taxAuthorityAssocTypeId", required=false) String taxAuthorityAssocTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("toTaxAuthPartyId",toTaxAuthPartyId);
		paramMap.put("toTaxAuthGeoId",toTaxAuthGeoId);
		paramMap.put("taxAuthorityAssocTypeId",taxAuthorityAssocTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTaxAuthorityAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTaxAuthorityCategory")
	public ResponseEntity<Object> deleteTaxAuthorityCategory(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTaxAuthorityCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteTaxAuthority")
	public ResponseEntity<Object> deleteTaxAuthority(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteTaxAuthority", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calcTax")
	public ResponseEntity<Object> calcTax(HttpSession session, @RequestParam(value="itemPriceList") java.util.List itemPriceList, @RequestParam(value="itemAmountList") java.util.List itemAmountList, @RequestParam(value="itemProductList") java.util.List itemProductList, @RequestParam(value="orderPromotionsAmount", required=false) BigDecimal orderPromotionsAmount, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="orderShippingAmount", required=false) BigDecimal orderShippingAmount, @RequestParam(value="billToPartyId", required=false) String billToPartyId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="itemQuantityList", required=false) java.util.List itemQuantityList, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="itemShippingList", required=false) java.util.List itemShippingList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemPriceList",itemPriceList);
		paramMap.put("itemAmountList",itemAmountList);
		paramMap.put("itemProductList",itemProductList);
		paramMap.put("orderPromotionsAmount",orderPromotionsAmount);
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderShippingAmount",orderShippingAmount);
		paramMap.put("billToPartyId",billToPartyId);
		paramMap.put("shippingAddress",shippingAddress);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("itemQuantityList",itemQuantityList);
		paramMap.put("payToPartyId",payToPartyId);
		paramMap.put("itemShippingList",itemShippingList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calcTax", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTaxAuthorityGlAccount")
	public ResponseEntity<Object> createTaxAuthorityGlAccount(HttpSession session, @RequestParam(value="taxAuthPartyId") String taxAuthPartyId, @RequestParam(value="taxAuthGeoId") String taxAuthGeoId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="glAccountId", required=false) String glAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTaxAuthorityGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/flatZipSalesTaxCalc")
	public ResponseEntity<Object> flatZipSalesTaxCalc(HttpSession session, @RequestParam(value="itemPriceList") java.util.List itemPriceList, @RequestParam(value="itemAmountList") java.util.List itemAmountList, @RequestParam(value="itemProductList") java.util.List itemProductList, @RequestParam(value="orderPromotionsAmount", required=false) BigDecimal orderPromotionsAmount, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="orderShippingAmount", required=false) BigDecimal orderShippingAmount, @RequestParam(value="billToPartyId", required=false) String billToPartyId, @RequestParam(value="shippingAddress", required=false) org.apache.ofbiz.entity.GenericValue shippingAddress, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="itemQuantityList", required=false) java.util.List itemQuantityList, @RequestParam(value="payToPartyId", required=false) String payToPartyId, @RequestParam(value="itemShippingList", required=false) java.util.List itemShippingList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemPriceList",itemPriceList);
		paramMap.put("itemAmountList",itemAmountList);
		paramMap.put("itemProductList",itemProductList);
		paramMap.put("orderPromotionsAmount",orderPromotionsAmount);
		paramMap.put("facilityId",facilityId);
		paramMap.put("orderShippingAmount",orderShippingAmount);
		paramMap.put("billToPartyId",billToPartyId);
		paramMap.put("shippingAddress",shippingAddress);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("itemQuantityList",itemQuantityList);
		paramMap.put("payToPartyId",payToPartyId);
		paramMap.put("itemShippingList",itemShippingList);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("flatZipSalesTaxCalc", paramMap);
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
