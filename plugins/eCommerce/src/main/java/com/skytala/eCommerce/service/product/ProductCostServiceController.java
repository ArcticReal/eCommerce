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
@RequestMapping("/service/productCost")
public class ProductCostServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/getProductCost")
	public ResponseEntity<Map<String, Object>> getProductCost(HttpSession session, @RequestParam(value="costComponentTypePrefix") String costComponentTypePrefix, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypePrefix",costComponentTypePrefix);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductCost", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calculateAllProductsCosts")
	public ResponseEntity<Map<String, Object>> calculateAllProductsCosts(HttpSession session, @RequestParam(value="costComponentTypePrefix") String costComponentTypePrefix, @RequestParam(value="currencyUomId") String currencyUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypePrefix",costComponentTypePrefix);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateAllProductsCosts", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductCostComponentCalc")
	public ResponseEntity<Map<String, Object>> deleteProductCostComponentCalc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductCostComponentCalc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calculateProductCosts")
	public ResponseEntity<Map<String, Object>> calculateProductCosts(HttpSession session, @RequestParam(value="costComponentTypePrefix") String costComponentTypePrefix, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypePrefix",costComponentTypePrefix);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateProductCosts", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCostComponentAttribute")
	public ResponseEntity<Map<String, Object>> updateCostComponentAttribute(HttpSession session, @RequestParam(value="attrName") String attrName, @RequestParam(value="costComponentId") String costComponentId, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrName",attrName);
		paramMap.put("costComponentId",costComponentId);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCostComponentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCostComponentCalc")
	public ResponseEntity<Map<String, Object>> updateProductCostComponentCalc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="productId") String productId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="costComponentCalcId", required=false) String costComponentCalcId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("productId",productId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCostComponentCalc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCostComponentTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteCostComponentTypeAttr(HttpSession session, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCostComponentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getTaskCost")
	public ResponseEntity<Map<String, Object>> getTaskCost(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="routingId", required=false) String routingId, @RequestParam(value="productId", required=false) String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("routingId",routingId);
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getTaskCost", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCostComponentTypeAttr")
	public ResponseEntity<Map<String, Object>> createCostComponentTypeAttr(HttpSession session, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCostComponentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCostComponentType")
	public ResponseEntity<Map<String, Object>> createCostComponentType(HttpSession session, @RequestParam(value="costComponentTypeId", required=false) String costComponentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCostComponentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancelCostComponents")
	public ResponseEntity<Map<String, Object>> cancelCostComponents(HttpSession session, @RequestParam(value="costComponentTypeId", required=false) String costComponentTypeId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="costUomId", required=false) String costUomId, @RequestParam(value="costComponentId", required=false) String costComponentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("productId",productId);
		paramMap.put("costUomId",costUomId);
		paramMap.put("costComponentId",costComponentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancelCostComponents", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCostComponent")
	public ResponseEntity<Map<String, Object>> deleteCostComponent(HttpSession session, @RequestParam(value="costComponentId") String costComponentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentId",costComponentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCostComponent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCostComponent")
	public ResponseEntity<Map<String, Object>> createCostComponent(HttpSession session, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="cost", required=false) BigDecimal cost, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="costUomId", required=false) String costUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="costComponentCalcId", required=false) String costComponentCalcId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("cost",cost);
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("costUomId",costUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCostComponent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCostComponent")
	public ResponseEntity<Map<String, Object>> updateCostComponent(HttpSession session, @RequestParam(value="costComponentId") String costComponentId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="costComponentTypeId", required=false) String costComponentTypeId, @RequestParam(value="cost", required=false) BigDecimal cost, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="costUomId", required=false) String costUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="costComponentCalcId", required=false) String costComponentCalcId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentId",costComponentId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("cost",cost);
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("costUomId",costUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCostComponent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCostComponentTypeAttr")
	public ResponseEntity<Map<String, Object>> updateCostComponentTypeAttr(HttpSession session, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCostComponentTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCostComponentCalc")
	public ResponseEntity<Map<String, Object>> createProductCostComponentCalc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="productId") String productId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="costComponentCalcId", required=false) String costComponentCalcId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("productId",productId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCostComponentCalc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCostComponentAttribute")
	public ResponseEntity<Map<String, Object>> createCostComponentAttribute(HttpSession session, @RequestParam(value="attrName") String attrName, @RequestParam(value="costComponentId") String costComponentId, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrName",attrName);
		paramMap.put("costComponentId",costComponentId);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCostComponentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCostComponentAttribute")
	public ResponseEntity<Map<String, Object>> deleteCostComponentAttribute(HttpSession session, @RequestParam(value="attrName") String attrName, @RequestParam(value="costComponentId") String costComponentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrName",attrName);
		paramMap.put("costComponentId",costComponentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCostComponentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/productCostCalcInterface")
	public ResponseEntity<Map<String, Object>> productCostCalcInterface(HttpSession session, @RequestParam(value="productCostComponentCalc") GenericValue productCostComponentCalc, @RequestParam(value="costComponentTypePrefix") String costComponentTypePrefix, @RequestParam(value="baseCost") BigDecimal baseCost, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="costComponentCalc") GenericValue costComponentCalc) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCostComponentCalc",productCostComponentCalc);
		paramMap.put("costComponentTypePrefix",costComponentTypePrefix);
		paramMap.put("baseCost",baseCost);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("costComponentCalc",costComponentCalc);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productCostCalcInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/productCostPercentageFormula")
	public ResponseEntity<Map<String, Object>> productCostPercentageFormula(HttpSession session, @RequestParam(value="productCostComponentCalc") GenericValue productCostComponentCalc, @RequestParam(value="costComponentTypePrefix") String costComponentTypePrefix, @RequestParam(value="baseCost") BigDecimal baseCost, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="costComponentCalc") GenericValue costComponentCalc) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCostComponentCalc",productCostComponentCalc);
		paramMap.put("costComponentTypePrefix",costComponentTypePrefix);
		paramMap.put("baseCost",baseCost);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("costComponentCalc",costComponentCalc);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productCostPercentageFormula", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCostComponentType")
	public ResponseEntity<Map<String, Object>> updateCostComponentType(HttpSession session, @RequestParam(value="costComponentTypeId") String costComponentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCostComponentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/recreateCostComponent")
	public ResponseEntity<Map<String, Object>> recreateCostComponent(HttpSession session, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="costComponentTypeId", required=false) String costComponentTypeId, @RequestParam(value="cost", required=false) BigDecimal cost, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="productFeatureId", required=false) String productFeatureId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="costUomId", required=false) String costUomId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="costComponentCalcId", required=false) String costComponentCalcId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("cost",cost);
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("costUomId",costUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("costComponentCalcId",costComponentCalcId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("recreateCostComponent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/calculateProductAverageCost")
	public ResponseEntity<Map<String, Object>> calculateProductAverageCost(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="ownerPartyId", required=false) String ownerPartyId, @RequestParam(value="facilityId", required=false) String facilityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("ownerPartyId",ownerPartyId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateProductAverageCost", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCostComponentType")
	public ResponseEntity<Map<String, Object>> deleteCostComponentType(HttpSession session, @RequestParam(value="costComponentTypeId") String costComponentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("costComponentTypeId",costComponentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCostComponentType", paramMap);
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
