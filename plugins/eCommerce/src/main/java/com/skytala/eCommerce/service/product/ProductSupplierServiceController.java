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
@RequestMapping("/service/productSupplier")
public class ProductSupplierServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSupplierRatingType")
	public ResponseEntity<Map<String, Object>> deleteSupplierRatingType(HttpSession session, @RequestParam(value="supplierRatingTypeId") String supplierRatingTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("supplierRatingTypeId",supplierRatingTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSupplierRatingType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createReorderGuideline")
	public ResponseEntity<Map<String, Object>> createReorderGuideline(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="reorderLevel", required=false) BigDecimal reorderLevel, @RequestParam(value="reorderGuidelineId", required=false) String reorderGuidelineId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="reorderQuantity", required=false) BigDecimal reorderQuantity, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("reorderLevel",reorderLevel);
		paramMap.put("reorderGuidelineId",reorderGuidelineId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("reorderQuantity",reorderQuantity);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createReorderGuideline", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateReorderGuideline")
	public ResponseEntity<Map<String, Object>> updateReorderGuideline(HttpSession session, @RequestParam(value="reorderGuidelineId") String reorderGuidelineId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="reorderLevel", required=false) BigDecimal reorderLevel, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="reorderQuantity", required=false) BigDecimal reorderQuantity, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("reorderGuidelineId",reorderGuidelineId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("reorderLevel",reorderLevel);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("reorderQuantity",reorderQuantity);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateReorderGuideline", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSupplierRatingType")
	public ResponseEntity<Map<String, Object>> createSupplierRatingType(HttpSession session, @RequestParam(value="supplierRatingTypeId", required=false) String supplierRatingTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("supplierRatingTypeId",supplierRatingTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSupplierRatingType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSupplierRatingType")
	public ResponseEntity<Map<String, Object>> updateSupplierRatingType(HttpSession session, @RequestParam(value="supplierRatingTypeId") String supplierRatingTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("supplierRatingTypeId",supplierRatingTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSupplierRatingType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteReorderGuideline")
	public ResponseEntity<Map<String, Object>> deleteReorderGuideline(HttpSession session, @RequestParam(value="reorderGuidelineId") String reorderGuidelineId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("reorderGuidelineId",reorderGuidelineId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteReorderGuideline", paramMap);
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
