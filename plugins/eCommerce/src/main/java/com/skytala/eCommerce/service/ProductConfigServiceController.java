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
@RequestMapping("/service/ProductConfigController")
public class ProductConfigServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createProdConfItemContentType")
	public ResponseEntity<Object> createProdConfItemContentType(HttpSession session, @RequestParam(value="confItemContentTypeId", required=false) String confItemContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProdConfItemContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createConfigOptionProductOption")
	public ResponseEntity<Object> createConfigOptionProductOption(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="productId") String productId, @RequestParam(value="configId") String configId, @RequestParam(value="configOptionId") String configOptionId, @RequestParam(value="productOptionId", required=false) String productOptionId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("configId",configId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("productOptionId",productOptionId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createConfigOptionProductOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateConfigOptionProductOption")
	public ResponseEntity<Object> updateConfigOptionProductOption(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="productId") String productId, @RequestParam(value="configId") String configId, @RequestParam(value="configOptionId") String configOptionId, @RequestParam(value="productOptionId", required=false) String productOptionId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("configId",configId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("productOptionId",productOptionId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateConfigOptionProductOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteConfigOptionProductOption")
	public ResponseEntity<Object> deleteConfigOptionProductOption(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="productId") String productId, @RequestParam(value="configId") String configId, @RequestParam(value="configOptionId") String configOptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("productId",productId);
		paramMap.put("configId",configId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteConfigOptionProductOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProdConfItemContentType")
	public ResponseEntity<Object> deleteProdConfItemContentType(HttpSession session, @RequestParam(value="confItemContentTypeId") String confItemContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProdConfItemContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProdConfItemContentType")
	public ResponseEntity<Object> updateProdConfItemContentType(HttpSession session, @RequestParam(value="confItemContentTypeId") String confItemContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) Boolean hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProdConfItemContentType", paramMap);
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
