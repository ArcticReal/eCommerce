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
@RequestMapping("/service/productPricepromo")
public class ProductPricepromoServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoCode")
	public ResponseEntity<Map<String, Object>> deleteProductPromoCode(HttpSession session, @RequestParam(value="productPromoCodeId") String productPromoCodeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromoCategory")
	public ResponseEntity<Map<String, Object>> updateProductPromoCategory(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="andGroupId") String andGroupId, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId, @RequestParam(value="includeSubCategories", required=false) String includeSubCategories, @RequestParam(value="productPromoApplEnumId", required=false) String productPromoApplEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("andGroupId",andGroupId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("includeSubCategories",includeSubCategories);
		paramMap.put("productPromoApplEnumId",productPromoApplEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromoCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calculatePurchasePrice")
	public ResponseEntity<Map<String, Object>> calculatePurchasePrice(HttpSession session, @RequestParam(value="product") org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product",product);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculatePurchasePrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPriceAction")
	public ResponseEntity<Map<String, Object>> updateProductPriceAction(HttpSession session, @RequestParam(value="productPriceActionSeqId") String productPriceActionSeqId, @RequestParam(value="productPriceRuleId") String productPriceRuleId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="productPriceActionTypeId", required=false) String productPriceActionTypeId, @RequestParam(value="rateCode", required=false) String rateCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceActionSeqId",productPriceActionSeqId);
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("amount",amount);
		paramMap.put("productPriceActionTypeId",productPriceActionTypeId);
		paramMap.put("rateCode",rateCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPriceAction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoCodeSet")
	public ResponseEntity<Map<String, Object>> createProductPromoCodeSet(HttpSession session, @RequestParam(value="quantity") Long quantity, @RequestParam(value="useLimitPerCode", required=false) Long useLimitPerCode, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="userEntered", required=false) String userEntered, @RequestParam(value="codeLength", required=false) Integer codeLength, @RequestParam(value="promoCodeLayout", required=false) String promoCodeLayout, @RequestParam(value="requireEmailOrParty", required=false) String requireEmailOrParty, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="useLimitPerCustomer", required=false) Long useLimitPerCustomer, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("useLimitPerCode",useLimitPerCode);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userEntered",userEntered);
		paramMap.put("codeLength",codeLength);
		paramMap.put("promoCodeLayout",promoCodeLayout);
		paramMap.put("requireEmailOrParty",requireEmailOrParty);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("useLimitPerCustomer",useLimitPerCustomer);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoCodeSet", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoRule")
	public ResponseEntity<Map<String, Object>> deleteProductPromoRule(HttpSession session, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPriceAction")
	public ResponseEntity<Map<String, Object>> deleteProductPriceAction(HttpSession session, @RequestParam(value="productPriceActionSeqId") String productPriceActionSeqId, @RequestParam(value="productPriceRuleId") String productPriceRuleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceActionSeqId",productPriceActionSeqId);
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPriceAction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoCodeEmail")
	public ResponseEntity<Map<String, Object>> deleteProductPromoCodeEmail(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="productPromoCodeId") String productPromoCodeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoCodeEmail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPriceAutoNotice")
	public ResponseEntity<Map<String, Object>> updateProductPriceAutoNotice(HttpSession session, @RequestParam(value="productPriceNoticeId") String productPriceNoticeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="runDate", required=false) Timestamp runDate, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceNoticeId",productPriceNoticeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("runDate",runDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPriceAutoNotice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPriceCond")
	public ResponseEntity<Map<String, Object>> deleteProductPriceCond(HttpSession session, @RequestParam(value="productPriceCondSeqId") String productPriceCondSeqId, @RequestParam(value="productPriceRuleId") String productPriceRuleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceCondSeqId",productPriceCondSeqId);
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPriceCond", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoProduct")
	public ResponseEntity<Map<String, Object>> deleteProductPromoProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoCond")
	public ResponseEntity<Map<String, Object>> deleteProductPromoCond(HttpSession session, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoCond", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoProduct")
	public ResponseEntity<Map<String, Object>> createProductPromoProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId, @RequestParam(value="productPromoApplEnumId", required=false) String productPromoApplEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("productPromoApplEnumId",productPromoApplEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calculateProductPrice")
	public ResponseEntity<Map<String, Object>> calculateProductPrice(HttpSession session, @RequestParam(value="product") org.apache.ofbiz.entity.GenericValue product, @RequestParam(value="currencyUomIdTo", required=false) String currencyUomIdTo, @RequestParam(value="autoUserLogin", required=false) org.apache.ofbiz.entity.GenericValue autoUserLogin, @RequestParam(value="optimizeForLargeRuleSet", required=false) String optimizeForLargeRuleSet, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="termUomId", required=false) String termUomId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="productStoreGroupId", required=false) String productStoreGroupId, @RequestParam(value="findAllQuantityPrices", required=false) String findAllQuantityPrices, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="agreementId", required=false) String agreementId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="productPricePurposeId", required=false) String productPricePurposeId, @RequestParam(value="checkIncludeVat", required=false) String checkIncludeVat, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId, @RequestParam(value="webSiteId", required=false) String webSiteId, @RequestParam(value="customAttributes", required=false) Map customAttributes) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product",product);
		paramMap.put("currencyUomIdTo",currencyUomIdTo);
		paramMap.put("autoUserLogin",autoUserLogin);
		paramMap.put("optimizeForLargeRuleSet",optimizeForLargeRuleSet);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("termUomId",termUomId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("findAllQuantityPrices",findAllQuantityPrices);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("agreementId",agreementId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("checkIncludeVat",checkIncludeVat);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("customAttributes",customAttributes);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateProductPrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromo")
	public ResponseEntity<Map<String, Object>> deleteProductPromo(HttpSession session, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoAction")
	public ResponseEntity<Map<String, Object>> deleteProductPromoAction(HttpSession session, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoAction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoCodeParty")
	public ResponseEntity<Map<String, Object>> createProductPromoCodeParty(HttpSession session, @RequestParam(value="productPromoCodeId") String productPromoCodeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoCodeParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getAssociatedPriceRulesConds")
	public ResponseEntity<Map<String, Object>> getAssociatedPriceRulesConds(HttpSession session, @RequestParam(value="inputParamEnumId") String inputParamEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputParamEnumId",inputParamEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAssociatedPriceRulesConds", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromo")
	public ResponseEntity<Map<String, Object>> createProductPromo(HttpSession session, @RequestParam(value="promoName") String promoName, @RequestParam(value="showToCustomer", required=false) String showToCustomer, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="requireCode", required=false) String requireCode, @RequestParam(value="overrideOrgPartyId", required=false) String overrideOrgPartyId, @RequestParam(value="useLimitPerCustomer", required=false) Long useLimitPerCustomer, @RequestParam(value="useLimitPerOrder", required=false) Long useLimitPerOrder, @RequestParam(value="useLimitPerPromotion", required=false) Long useLimitPerPromotion, @RequestParam(value="promoText", required=false) String promoText, @RequestParam(value="userEntered", required=false) String userEntered, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="billbackFactor", required=false) BigDecimal billbackFactor, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("promoName",promoName);
		paramMap.put("showToCustomer",showToCustomer);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("requireCode",requireCode);
		paramMap.put("overrideOrgPartyId",overrideOrgPartyId);
		paramMap.put("useLimitPerCustomer",useLimitPerCustomer);
		paramMap.put("useLimitPerOrder",useLimitPerOrder);
		paramMap.put("useLimitPerPromotion",useLimitPerPromotion);
		paramMap.put("promoText",promoText);
		paramMap.put("userEntered",userEntered);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("billbackFactor",billbackFactor);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoAction")
	public ResponseEntity<Map<String, Object>> createProductPromoAction(HttpSession session, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoActionEnumId") String productPromoActionEnumId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderAdjustmentTypeId", required=false) String orderAdjustmentTypeId, @RequestParam(value="useCartQuantity", required=false) String useCartQuantity, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoActionEnumId",productPromoActionEnumId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderAdjustmentTypeId",orderAdjustmentTypeId);
		paramMap.put("useCartQuantity",useCartQuantity);
		paramMap.put("serviceName",serviceName);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoAction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromoAction")
	public ResponseEntity<Map<String, Object>> updateProductPromoAction(HttpSession session, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderAdjustmentTypeId", required=false) String orderAdjustmentTypeId, @RequestParam(value="useCartQuantity", required=false) String useCartQuantity, @RequestParam(value="productPromoActionEnumId", required=false) String productPromoActionEnumId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("orderAdjustmentTypeId",orderAdjustmentTypeId);
		paramMap.put("useCartQuantity",useCartQuantity);
		paramMap.put("productPromoActionEnumId",productPromoActionEnumId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromoAction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromoProduct")
	public ResponseEntity<Map<String, Object>> updateProductPromoProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId, @RequestParam(value="productPromoApplEnumId", required=false) String productPromoApplEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("productPromoApplEnumId",productPromoApplEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromoProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromoRule")
	public ResponseEntity<Map<String, Object>> updateProductPromoRule(HttpSession session, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="ruleName") String ruleName, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("ruleName",ruleName);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromoRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPriceAutoNotice")
	public ResponseEntity<Map<String, Object>> deleteProductPriceAutoNotice(HttpSession session, @RequestParam(value="productPriceNoticeId") String productPriceNoticeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceNoticeId",productPriceNoticeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPriceAutoNotice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoCond")
	public ResponseEntity<Map<String, Object>> createProductPromoCond(HttpSession session, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="inputParamEnumId", required=false) String inputParamEnumId, @RequestParam(value="condValue", required=false) String condValue, @RequestParam(value="carrierShipmentMethod", required=false) String carrierShipmentMethod, @RequestParam(value="operatorEnumId", required=false) String operatorEnumId, @RequestParam(value="otherValue", required=false) String otherValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("inputParamEnumId",inputParamEnumId);
		paramMap.put("condValue",condValue);
		paramMap.put("carrierShipmentMethod",carrierShipmentMethod);
		paramMap.put("operatorEnumId",operatorEnumId);
		paramMap.put("otherValue",otherValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoCond", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromoCode")
	public ResponseEntity<Map<String, Object>> updateProductPromoCode(HttpSession session, @RequestParam(value="productPromoCodeId") String productPromoCodeId, @RequestParam(value="useLimitPerCode", required=false) Long useLimitPerCode, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="userEntered", required=false) String userEntered, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="requireEmailOrParty", required=false) String requireEmailOrParty, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="useLimitPerCustomer", required=false) Long useLimitPerCustomer, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("useLimitPerCode",useLimitPerCode);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userEntered",userEntered);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("requireEmailOrParty",requireEmailOrParty);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("useLimitPerCustomer",useLimitPerCustomer);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromoCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPriceActionType")
	public ResponseEntity<Map<String, Object>> deleteProductPriceActionType(HttpSession session, @RequestParam(value="productPriceActionTypeId") String productPriceActionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceActionTypeId",productPriceActionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPriceActionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPriceAction")
	public ResponseEntity<Map<String, Object>> createProductPriceAction(HttpSession session, @RequestParam(value="productPriceRuleId") String productPriceRuleId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="productPriceActionTypeId", required=false) String productPriceActionTypeId, @RequestParam(value="rateCode", required=false) String rateCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("amount",amount);
		paramMap.put("productPriceActionTypeId",productPriceActionTypeId);
		paramMap.put("rateCode",rateCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPriceAction", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoCategory")
	public ResponseEntity<Map<String, Object>> createProductPromoCategory(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="andGroupId") String andGroupId, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId, @RequestParam(value="includeSubCategories", required=false) String includeSubCategories, @RequestParam(value="productPromoApplEnumId", required=false) String productPromoApplEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("andGroupId",andGroupId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("includeSubCategories",includeSubCategories);
		paramMap.put("productPromoApplEnumId",productPromoApplEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBulkProductPromoCodeEmail")
	public ResponseEntity<Map<String, Object>> createBulkProductPromoCodeEmail(HttpSession session, @RequestParam(value="productPromoCodeId") String productPromoCodeId, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBulkProductPromoCodeEmail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPriceRule")
	public ResponseEntity<Map<String, Object>> updateProductPriceRule(HttpSession session, @RequestParam(value="productPriceRuleId") String productPriceRuleId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="ruleName", required=false) String ruleName, @RequestParam(value="description", required=false) String description, @RequestParam(value="isSale", required=false) String isSale, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("ruleName",ruleName);
		paramMap.put("description",description);
		paramMap.put("isSale",isSale);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPriceRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPriceCond")
	public ResponseEntity<Map<String, Object>> createProductPriceCond(HttpSession session, @RequestParam(value="productPriceRuleId") String productPriceRuleId, @RequestParam(value="condValueInput", required=false) String condValueInput, @RequestParam(value="inputParamEnumId", required=false) String inputParamEnumId, @RequestParam(value="condValue", required=false) String condValue, @RequestParam(value="operatorEnumId", required=false) String operatorEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("condValueInput",condValueInput);
		paramMap.put("inputParamEnumId",inputParamEnumId);
		paramMap.put("condValue",condValue);
		paramMap.put("operatorEnumId",operatorEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPriceCond", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoCode")
	public ResponseEntity<Map<String, Object>> createProductPromoCode(HttpSession session, @RequestParam(value="useLimitPerCode", required=false) Long useLimitPerCode, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="userEntered", required=false) String userEntered, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="productPromoCodeId", required=false) String productPromoCodeId, @RequestParam(value="requireEmailOrParty", required=false) String requireEmailOrParty, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="useLimitPerCustomer", required=false) Long useLimitPerCustomer, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("useLimitPerCode",useLimitPerCode);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userEntered",userEntered);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("requireEmailOrParty",requireEmailOrParty);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("useLimitPerCustomer",useLimitPerCustomer);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoCodeParty")
	public ResponseEntity<Map<String, Object>> deleteProductPromoCodeParty(HttpSession session, @RequestParam(value="productPromoCodeId") String productPromoCodeId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoCodeParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPriceActionType")
	public ResponseEntity<Map<String, Object>> createProductPriceActionType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="productPriceActionTypeId", required=false) String productPriceActionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("productPriceActionTypeId",productPriceActionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPriceActionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoRule")
	public ResponseEntity<Map<String, Object>> createProductPromoRule(HttpSession session, @RequestParam(value="ruleName") String ruleName, @RequestParam(value="productPromoId") String productPromoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ruleName",ruleName);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPriceAutoNotice")
	public ResponseEntity<Map<String, Object>> createProductPriceAutoNotice(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="runDate", required=false) Timestamp runDate, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="productPriceNoticeId", required=false) String productPriceNoticeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("runDate",runDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productPriceNoticeId",productPriceNoticeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPriceAutoNotice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPriceActionType")
	public ResponseEntity<Map<String, Object>> updateProductPriceActionType(HttpSession session, @RequestParam(value="productPriceActionTypeId") String productPriceActionTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceActionTypeId",productPriceActionTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPriceActionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBulkProductPromoCode")
	public ResponseEntity<Map<String, Object>> createBulkProductPromoCode(HttpSession session, @RequestParam(value="useLimitPerCode", required=false) Long useLimitPerCode, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="productPromoId", required=false) String productPromoId, @RequestParam(value="useLimitPerCustomer", required=false) Long useLimitPerCustomer, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="userEntered", required=false) String userEntered, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="requireEmailOrParty", required=false) String requireEmailOrParty, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("useLimitPerCode",useLimitPerCode);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("useLimitPerCustomer",useLimitPerCustomer);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userEntered",userEntered);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("requireEmailOrParty",requireEmailOrParty);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBulkProductPromoCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromo")
	public ResponseEntity<Map<String, Object>> updateProductPromo(HttpSession session, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="showToCustomer", required=false) String showToCustomer, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="requireCode", required=false) String requireCode, @RequestParam(value="overrideOrgPartyId", required=false) String overrideOrgPartyId, @RequestParam(value="useLimitPerCustomer", required=false) Long useLimitPerCustomer, @RequestParam(value="useLimitPerOrder", required=false) Long useLimitPerOrder, @RequestParam(value="useLimitPerPromotion", required=false) Long useLimitPerPromotion, @RequestParam(value="promoName", required=false) String promoName, @RequestParam(value="promoText", required=false) String promoText, @RequestParam(value="userEntered", required=false) String userEntered, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="billbackFactor", required=false) BigDecimal billbackFactor, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("showToCustomer",showToCustomer);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("requireCode",requireCode);
		paramMap.put("overrideOrgPartyId",overrideOrgPartyId);
		paramMap.put("useLimitPerCustomer",useLimitPerCustomer);
		paramMap.put("useLimitPerOrder",useLimitPerOrder);
		paramMap.put("useLimitPerPromotion",useLimitPerPromotion);
		paramMap.put("promoName",promoName);
		paramMap.put("promoText",promoText);
		paramMap.put("userEntered",userEntered);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("billbackFactor",billbackFactor);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPriceRule")
	public ResponseEntity<Map<String, Object>> createProductPriceRule(HttpSession session, @RequestParam(value="ruleName") String ruleName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="isSale", required=false) String isSale, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ruleName",ruleName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("description",description);
		paramMap.put("isSale",isSale);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPriceRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPriceCond")
	public ResponseEntity<Map<String, Object>> updateProductPriceCond(HttpSession session, @RequestParam(value="productPriceCondSeqId") String productPriceCondSeqId, @RequestParam(value="productPriceRuleId") String productPriceRuleId, @RequestParam(value="condValueInput", required=false) String condValueInput, @RequestParam(value="inputParamEnumId", required=false) String inputParamEnumId, @RequestParam(value="condValue", required=false) String condValue, @RequestParam(value="operatorEnumId", required=false) String operatorEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceCondSeqId",productPriceCondSeqId);
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("condValueInput",condValueInput);
		paramMap.put("inputParamEnumId",inputParamEnumId);
		paramMap.put("condValue",condValue);
		paramMap.put("operatorEnumId",operatorEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPriceCond", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPriceRule")
	public ResponseEntity<Map<String, Object>> deleteProductPriceRule(HttpSession session, @RequestParam(value="productPriceRuleId") String productPriceRuleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPriceRuleId",productPriceRuleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPriceRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPromoCategory")
	public ResponseEntity<Map<String, Object>> deleteProductPromoCategory(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="andGroupId") String andGroupId, @RequestParam(value="productPromoActionSeqId") String productPromoActionSeqId, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("andGroupId",andGroupId);
		paramMap.put("productPromoActionSeqId",productPromoActionSeqId);
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPromoCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoCodeEmail")
	public ResponseEntity<Map<String, Object>> createProductPromoCodeEmail(HttpSession session, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="productPromoCodeId") String productPromoCodeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("productPromoCodeId",productPromoCodeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoCodeEmail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromoCond")
	public ResponseEntity<Map<String, Object>> updateProductPromoCond(HttpSession session, @RequestParam(value="productPromoRuleId") String productPromoRuleId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="productPromoCondSeqId") String productPromoCondSeqId, @RequestParam(value="inputParamEnumId", required=false) String inputParamEnumId, @RequestParam(value="condValue", required=false) String condValue, @RequestParam(value="carrierShipmentMethod", required=false) String carrierShipmentMethod, @RequestParam(value="operatorEnumId", required=false) String operatorEnumId, @RequestParam(value="otherValue", required=false) String otherValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoRuleId",productPromoRuleId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("productPromoCondSeqId",productPromoCondSeqId);
		paramMap.put("inputParamEnumId",inputParamEnumId);
		paramMap.put("condValue",condValue);
		paramMap.put("carrierShipmentMethod",carrierShipmentMethod);
		paramMap.put("operatorEnumId",operatorEnumId);
		paramMap.put("otherValue",otherValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromoCond", paramMap);
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
