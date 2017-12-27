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
@RequestMapping("/service/productInventory")
public class ProductInventoryServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInventoryItemTempRes")
	public ResponseEntity<Map<String, Object>> deleteInventoryItemTempRes(HttpSession session, @RequestParam(value="visitId") String visitId, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visitId",visitId);
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInventoryItemTempRes", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemTempRes")
	public ResponseEntity<Map<String, Object>> updateInventoryItemTempRes(HttpSession session, @RequestParam(value="visitId") String visitId, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="reservedDate", required=false) Timestamp reservedDate, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visitId",visitId);
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("reservedDate",reservedDate);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemTempRes", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInventoryItemAttribute")
	public ResponseEntity<Map<String, Object>> deleteInventoryItemAttribute(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInventoryItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemTypeAttr")
	public ResponseEntity<Map<String, Object>> updateInventoryItemTypeAttr(HttpSession session, @RequestParam(value="attrName") String attrName, @RequestParam(value="inventoryItemTypeId") String inventoryItemTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrName",attrName);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemTempRes")
	public ResponseEntity<Map<String, Object>> createInventoryItemTempRes(HttpSession session, @RequestParam(value="visitId") String visitId, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="reservedDate", required=false) Timestamp reservedDate, @RequestParam(value="quantity", required=false) BigDecimal quantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visitId",visitId);
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("reservedDate",reservedDate);
		paramMap.put("quantity",quantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemTempRes", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemAttribute")
	public ResponseEntity<Map<String, Object>> createInventoryItemAttribute(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createInventoryItemTypeAttr")
	public ResponseEntity<Map<String, Object>> createInventoryItemTypeAttr(HttpSession session, @RequestParam(value="attrName") String attrName, @RequestParam(value="inventoryItemTypeId") String inventoryItemTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrName",attrName);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createInventoryItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteInventoryItemTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteInventoryItemTypeAttr(HttpSession session, @RequestParam(value="attrName") String attrName, @RequestParam(value="inventoryItemTypeId") String inventoryItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attrName",attrName);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteInventoryItemTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateInventoryItemAttribute")
	public ResponseEntity<Map<String, Object>> updateInventoryItemAttribute(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateInventoryItemAttribute", paramMap);
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
