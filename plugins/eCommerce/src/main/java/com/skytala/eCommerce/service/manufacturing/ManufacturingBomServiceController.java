package com.skytala.eCommerce.service.manufacturing;

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
@RequestMapping("/service/manufacturingBom")
public class ManufacturingBomServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/initLowLevelCode")
	public ResponseEntity<Map<String, Object>> initLowLevelCode(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("initLowLevelCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/searchDuplicatedAncestor")
	public ResponseEntity<Map<String, Object>> searchDuplicatedAncestor(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productIdTo") String productIdTo, @RequestParam(value="productAssocTypeId") String productAssocTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productIdTo",productIdTo);
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("searchDuplicatedAncestor", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyBOMAssocs")
	public ResponseEntity<Map<String, Object>> copyBOMAssocs(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productAssocTypeId") String productAssocTypeId, @RequestParam(value="copyToProductId") String copyToProductId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="productIdTo", required=false) String productIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("copyToProductId",copyToProductId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("productIdTo",productIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyBOMAssocs", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getManufacturingComponents")
	public ResponseEntity<Map<String, Object>> getManufacturingComponents(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="excludeWIPs", required=false) Boolean excludeWIPs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("excludeWIPs",excludeWIPs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getManufacturingComponents", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getBOMTree")
	public ResponseEntity<Map<String, Object>> getBOMTree(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="bomType") String bomType, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="type", required=false) Integer type) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("bomType",bomType);
		paramMap.put("fromDate",fromDate);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("type",type);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getBOMTree", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductsInPackages")
	public ResponseEntity<Map<String, Object>> getProductsInPackages(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductsInPackages", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateLowLevelCode")
	public ResponseEntity<Map<String, Object>> updateLowLevelCode(HttpSession session, @RequestParam(value="productIdTo") String productIdTo, @RequestParam(value="alsoComponents", required=false) Boolean alsoComponents, @RequestParam(value="alsoVariants", required=false) Boolean alsoVariants) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productIdTo",productIdTo);
		paramMap.put("alsoComponents",alsoComponents);
		paramMap.put("alsoVariants",alsoVariants);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateLowLevelCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addProductManufacturingRule")
	public ResponseEntity<Map<String, Object>> addProductManufacturingRule(HttpSession session, @RequestParam(value="productIdIn") String productIdIn, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="ruleOperator", required=false) String ruleOperator, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="productIdFor", required=false) String productIdFor, @RequestParam(value="productIdInSubst", required=false) String productIdInSubst, @RequestParam(value="ruleId", required=false) String ruleId, @RequestParam(value="productFeature", required=false) String productFeature, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productIdIn",productIdIn);
		paramMap.put("fromDate",fromDate);
		paramMap.put("ruleOperator",ruleOperator);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("productIdFor",productIdFor);
		paramMap.put("productIdInSubst",productIdInSubst);
		paramMap.put("ruleId",ruleId);
		paramMap.put("productFeature",productFeature);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProductManufacturingRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getNotAssembledComponents")
	public ResponseEntity<Map<String, Object>> getNotAssembledComponents(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("amount",amount);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getNotAssembledComponents", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductManufacturingRule")
	public ResponseEntity<Map<String, Object>> deleteProductManufacturingRule(HttpSession session, @RequestParam(value="ruleId") String ruleId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ruleId",ruleId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductManufacturingRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createBOMAssoc")
	public ResponseEntity<Map<String, Object>> createBOMAssoc(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productIdTo") String productIdTo, @RequestParam(value="productAssocTypeId") String productAssocTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="scrapFactor", required=false) BigDecimal scrapFactor, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="instruction", required=false) String instruction, @RequestParam(value="routingWorkEffortId", required=false) String routingWorkEffortId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productIdTo",productIdTo);
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("reason",reason);
		paramMap.put("scrapFactor",scrapFactor);
		paramMap.put("quantity",quantity);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("instruction",instruction);
		paramMap.put("routingWorkEffortId",routingWorkEffortId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBOMAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductManufacturingRule")
	public ResponseEntity<Map<String, Object>> updateProductManufacturingRule(HttpSession session, @RequestParam(value="ruleId") String ruleId, @RequestParam(value="productIdIn") String productIdIn, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="ruleOperator", required=false) String ruleOperator, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="productIdFor", required=false) String productIdFor, @RequestParam(value="productIdInSubst", required=false) String productIdInSubst, @RequestParam(value="productFeature", required=false) String productFeature, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ruleId",ruleId);
		paramMap.put("productIdIn",productIdIn);
		paramMap.put("fromDate",fromDate);
		paramMap.put("ruleOperator",ruleOperator);
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("productIdFor",productIdFor);
		paramMap.put("productIdInSubst",productIdInSubst);
		paramMap.put("productFeature",productFeature);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductManufacturingRule", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getMaxDepth")
	public ResponseEntity<Map<String, Object>> getMaxDepth(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="bomType", required=false) String bomType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("bomType",bomType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getMaxDepth", paramMap);
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
