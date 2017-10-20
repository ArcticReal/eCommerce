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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/service/ProductShipmentgateway")
public class ProductShipmentgateway{

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentGatewayConfigType")
	public ResponseEntity<Object> createShipmentGatewayConfigType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentGatewayConfTypeId", required=false) String shipmentGatewayConfTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("shipmentGatewayConfTypeId",shipmentGatewayConfTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentGatewayConfigType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentGatewayUsps")
	public ResponseEntity<Object> deleteShipmentGatewayUsps(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentGatewayUsps", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentGatewayUsps")
	public ResponseEntity<Object> updateShipmentGatewayUsps(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessUserId", required=false) Long accessUserId, @RequestParam(value="test", required=false) String test, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="connectUrlLabels", required=false) Long connectUrlLabels, @RequestParam(value="accessPassword", required=false) Long accessPassword, @RequestParam(value="maxEstimateWeight", required=false) Long maxEstimateWeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessUserId",accessUserId);
		paramMap.put("test",test);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("connectUrlLabels",connectUrlLabels);
		paramMap.put("accessPassword",accessPassword);
		paramMap.put("maxEstimateWeight",maxEstimateWeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentGatewayUsps", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentType")
	public ResponseEntity<Object> createShipmentType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentTypeId", required=false) String shipmentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentGatewayFedex")
	public ResponseEntity<Object> updateShipmentGatewayFedex(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessMeterNumber", required=false) Long accessMeterNumber, @RequestParam(value="rateEstimateTemplate", required=false) Long rateEstimateTemplate, @RequestParam(value="labelImageType", required=false) String labelImageType, @RequestParam(value="defaultDropoffType", required=false) Long defaultDropoffType, @RequestParam(value="accessUserKey", required=false) Long accessUserKey, @RequestParam(value="templateShipment", required=false) Long templateShipment, @RequestParam(value="templateSubscription", required=false) Long templateSubscription, @RequestParam(value="accessUserPwd", required=false) Long accessUserPwd, @RequestParam(value="defaultPackagingType", required=false) Long defaultPackagingType, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="accessAccountNbr", required=false) Long accessAccountNbr, @RequestParam(value="connectSoapUrl", required=false) Long connectSoapUrl) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessMeterNumber",accessMeterNumber);
		paramMap.put("rateEstimateTemplate",rateEstimateTemplate);
		paramMap.put("labelImageType",labelImageType);
		paramMap.put("defaultDropoffType",defaultDropoffType);
		paramMap.put("accessUserKey",accessUserKey);
		paramMap.put("templateShipment",templateShipment);
		paramMap.put("templateSubscription",templateSubscription);
		paramMap.put("accessUserPwd",accessUserPwd);
		paramMap.put("defaultPackagingType",defaultPackagingType);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("accessAccountNbr",accessAccountNbr);
		paramMap.put("connectSoapUrl",connectSoapUrl);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentGatewayFedex", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentGatewayDhl")
	public ResponseEntity<Object> createShipmentGatewayDhl(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessUserId", required=false) Long accessUserId, @RequestParam(value="accessShippingKey", required=false) Long accessShippingKey, @RequestParam(value="rateEstimateTemplate", required=false) Long rateEstimateTemplate, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="accessAccountNbr", required=false) Long accessAccountNbr, @RequestParam(value="labelImageFormat", required=false) String labelImageFormat, @RequestParam(value="headVersion", required=false) String headVersion, @RequestParam(value="headAction", required=false) Long headAction, @RequestParam(value="accessPassword", required=false) Long accessPassword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessUserId",accessUserId);
		paramMap.put("accessShippingKey",accessShippingKey);
		paramMap.put("rateEstimateTemplate",rateEstimateTemplate);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("accessAccountNbr",accessAccountNbr);
		paramMap.put("labelImageFormat",labelImageFormat);
		paramMap.put("headVersion",headVersion);
		paramMap.put("headAction",headAction);
		paramMap.put("accessPassword",accessPassword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentGatewayDhl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentGatewayDhl")
	public ResponseEntity<Object> updateShipmentGatewayDhl(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessUserId", required=false) Long accessUserId, @RequestParam(value="accessShippingKey", required=false) Long accessShippingKey, @RequestParam(value="rateEstimateTemplate", required=false) Long rateEstimateTemplate, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="accessAccountNbr", required=false) Long accessAccountNbr, @RequestParam(value="labelImageFormat", required=false) String labelImageFormat, @RequestParam(value="headVersion", required=false) String headVersion, @RequestParam(value="headAction", required=false) Long headAction, @RequestParam(value="accessPassword", required=false) Long accessPassword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessUserId",accessUserId);
		paramMap.put("accessShippingKey",accessShippingKey);
		paramMap.put("rateEstimateTemplate",rateEstimateTemplate);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("accessAccountNbr",accessAccountNbr);
		paramMap.put("labelImageFormat",labelImageFormat);
		paramMap.put("headVersion",headVersion);
		paramMap.put("headAction",headAction);
		paramMap.put("accessPassword",accessPassword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentGatewayDhl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentType")
	public ResponseEntity<Object> deleteShipmentType(HttpSession session, @RequestParam(value="shipmentTypeId") String shipmentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentGatewayConfig")
	public ResponseEntity<Object> deleteShipmentGatewayConfig(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentGatewayConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentType")
	public ResponseEntity<Object> updateShipmentType(HttpSession session, @RequestParam(value="shipmentTypeId") String shipmentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentTypeId",shipmentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentGatewayFedex")
	public ResponseEntity<Object> deleteShipmentGatewayFedex(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentGatewayFedex", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentGatewayDhl")
	public ResponseEntity<Object> deleteShipmentGatewayDhl(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentGatewayDhl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentGatewayConfigType")
	public ResponseEntity<Object> deleteShipmentGatewayConfigType(HttpSession session, @RequestParam(value="shipmentGatewayConfTypeId") String shipmentGatewayConfTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfTypeId",shipmentGatewayConfTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentGatewayConfigType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteShipmentGatewayUps")
	public ResponseEntity<Object> deleteShipmentGatewayUps(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteShipmentGatewayUps", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentGatewayUsps")
	public ResponseEntity<Object> createShipmentGatewayUsps(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessUserId", required=false) Long accessUserId, @RequestParam(value="test", required=false) String test, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="connectUrlLabels", required=false) Long connectUrlLabels, @RequestParam(value="accessPassword", required=false) Long accessPassword, @RequestParam(value="maxEstimateWeight", required=false) Long maxEstimateWeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessUserId",accessUserId);
		paramMap.put("test",test);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("connectUrlLabels",connectUrlLabels);
		paramMap.put("accessPassword",accessPassword);
		paramMap.put("maxEstimateWeight",maxEstimateWeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentGatewayUsps", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentGatewayFedex")
	public ResponseEntity<Object> createShipmentGatewayFedex(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessMeterNumber", required=false) Long accessMeterNumber, @RequestParam(value="rateEstimateTemplate", required=false) Long rateEstimateTemplate, @RequestParam(value="labelImageType", required=false) String labelImageType, @RequestParam(value="defaultDropoffType", required=false) Long defaultDropoffType, @RequestParam(value="accessUserKey", required=false) Long accessUserKey, @RequestParam(value="templateShipment", required=false) Long templateShipment, @RequestParam(value="templateSubscription", required=false) Long templateSubscription, @RequestParam(value="accessUserPwd", required=false) Long accessUserPwd, @RequestParam(value="defaultPackagingType", required=false) Long defaultPackagingType, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="accessAccountNbr", required=false) Long accessAccountNbr, @RequestParam(value="connectSoapUrl", required=false) Long connectSoapUrl) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessMeterNumber",accessMeterNumber);
		paramMap.put("rateEstimateTemplate",rateEstimateTemplate);
		paramMap.put("labelImageType",labelImageType);
		paramMap.put("defaultDropoffType",defaultDropoffType);
		paramMap.put("accessUserKey",accessUserKey);
		paramMap.put("templateShipment",templateShipment);
		paramMap.put("templateSubscription",templateSubscription);
		paramMap.put("accessUserPwd",accessUserPwd);
		paramMap.put("defaultPackagingType",defaultPackagingType);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("accessAccountNbr",accessAccountNbr);
		paramMap.put("connectSoapUrl",connectSoapUrl);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentGatewayFedex", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentGatewayUps")
	public ResponseEntity<Object> createShipmentGatewayUps(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessUserId", required=false) Long accessUserId, @RequestParam(value="saveCertPath", required=false) Long saveCertPath, @RequestParam(value="billShipperAccountNumber", required=false) Long billShipperAccountNumber, @RequestParam(value="defaultReturnLabelSubject", required=false) Long defaultReturnLabelSubject, @RequestParam(value="customerClassification", required=false) String customerClassification, @RequestParam(value="shipperNumber", required=false) Long shipperNumber, @RequestParam(value="maxEstimateWeight", required=false) BigDecimal maxEstimateWeight, @RequestParam(value="codSurchargeApplyToPackage", required=false) String codSurchargeApplyToPackage, @RequestParam(value="saveCertInfo", required=false) String saveCertInfo, @RequestParam(value="codSurchargeCurrencyUomId", required=false) String codSurchargeCurrencyUomId, @RequestParam(value="codFundsCode", required=false) String codFundsCode, @RequestParam(value="shipperPickupType", required=false) String shipperPickupType, @RequestParam(value="minEstimateWeight", required=false) BigDecimal minEstimateWeight, @RequestParam(value="codAllowCod", required=false) Long codAllowCod, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="codSurchargeAmount", required=false) BigDecimal codSurchargeAmount, @RequestParam(value="accessPassword", required=false) Long accessPassword, @RequestParam(value="accessLicenseNumber", required=false) Long accessLicenseNumber, @RequestParam(value="defaultReturnLabelMemo", required=false) Long defaultReturnLabelMemo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessUserId",accessUserId);
		paramMap.put("saveCertPath",saveCertPath);
		paramMap.put("billShipperAccountNumber",billShipperAccountNumber);
		paramMap.put("defaultReturnLabelSubject",defaultReturnLabelSubject);
		paramMap.put("customerClassification",customerClassification);
		paramMap.put("shipperNumber",shipperNumber);
		paramMap.put("maxEstimateWeight",maxEstimateWeight);
		paramMap.put("codSurchargeApplyToPackage",codSurchargeApplyToPackage);
		paramMap.put("saveCertInfo",saveCertInfo);
		paramMap.put("codSurchargeCurrencyUomId",codSurchargeCurrencyUomId);
		paramMap.put("codFundsCode",codFundsCode);
		paramMap.put("shipperPickupType",shipperPickupType);
		paramMap.put("minEstimateWeight",minEstimateWeight);
		paramMap.put("codAllowCod",codAllowCod);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("codSurchargeAmount",codSurchargeAmount);
		paramMap.put("accessPassword",accessPassword);
		paramMap.put("accessLicenseNumber",accessLicenseNumber);
		paramMap.put("defaultReturnLabelMemo",defaultReturnLabelMemo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentGatewayUps", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentGatewayUps")
	public ResponseEntity<Object> updateShipmentGatewayUps(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="connectUrl", required=false) Long connectUrl, @RequestParam(value="accessUserId", required=false) Long accessUserId, @RequestParam(value="saveCertPath", required=false) Long saveCertPath, @RequestParam(value="billShipperAccountNumber", required=false) Long billShipperAccountNumber, @RequestParam(value="defaultReturnLabelSubject", required=false) Long defaultReturnLabelSubject, @RequestParam(value="customerClassification", required=false) String customerClassification, @RequestParam(value="shipperNumber", required=false) Long shipperNumber, @RequestParam(value="maxEstimateWeight", required=false) BigDecimal maxEstimateWeight, @RequestParam(value="codSurchargeApplyToPackage", required=false) String codSurchargeApplyToPackage, @RequestParam(value="saveCertInfo", required=false) String saveCertInfo, @RequestParam(value="codSurchargeCurrencyUomId", required=false) String codSurchargeCurrencyUomId, @RequestParam(value="codFundsCode", required=false) String codFundsCode, @RequestParam(value="shipperPickupType", required=false) String shipperPickupType, @RequestParam(value="minEstimateWeight", required=false) BigDecimal minEstimateWeight, @RequestParam(value="codAllowCod", required=false) Long codAllowCod, @RequestParam(value="connectTimeout", required=false) Long connectTimeout, @RequestParam(value="codSurchargeAmount", required=false) BigDecimal codSurchargeAmount, @RequestParam(value="accessPassword", required=false) Long accessPassword, @RequestParam(value="accessLicenseNumber", required=false) Long accessLicenseNumber, @RequestParam(value="defaultReturnLabelMemo", required=false) Long defaultReturnLabelMemo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("connectUrl",connectUrl);
		paramMap.put("accessUserId",accessUserId);
		paramMap.put("saveCertPath",saveCertPath);
		paramMap.put("billShipperAccountNumber",billShipperAccountNumber);
		paramMap.put("defaultReturnLabelSubject",defaultReturnLabelSubject);
		paramMap.put("customerClassification",customerClassification);
		paramMap.put("shipperNumber",shipperNumber);
		paramMap.put("maxEstimateWeight",maxEstimateWeight);
		paramMap.put("codSurchargeApplyToPackage",codSurchargeApplyToPackage);
		paramMap.put("saveCertInfo",saveCertInfo);
		paramMap.put("codSurchargeCurrencyUomId",codSurchargeCurrencyUomId);
		paramMap.put("codFundsCode",codFundsCode);
		paramMap.put("shipperPickupType",shipperPickupType);
		paramMap.put("minEstimateWeight",minEstimateWeight);
		paramMap.put("codAllowCod",codAllowCod);
		paramMap.put("connectTimeout",connectTimeout);
		paramMap.put("codSurchargeAmount",codSurchargeAmount);
		paramMap.put("accessPassword",accessPassword);
		paramMap.put("accessLicenseNumber",accessLicenseNumber);
		paramMap.put("defaultReturnLabelMemo",defaultReturnLabelMemo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentGatewayUps", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createShipmentGatewayConfig")
	public ResponseEntity<Object> createShipmentGatewayConfig(HttpSession session, @RequestParam(value="shipmentGatewayConfigId", required=false) String shipmentGatewayConfigId, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentGatewayConfTypeId", required=false) String shipmentGatewayConfTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("description",description);
		paramMap.put("shipmentGatewayConfTypeId",shipmentGatewayConfTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createShipmentGatewayConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentGatewayConfig")
	public ResponseEntity<Object> updateShipmentGatewayConfig(HttpSession session, @RequestParam(value="shipmentGatewayConfigId") String shipmentGatewayConfigId, @RequestParam(value="description", required=false) String description, @RequestParam(value="shipmentGatewayConfTypeId", required=false) String shipmentGatewayConfTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfigId",shipmentGatewayConfigId);
		paramMap.put("description",description);
		paramMap.put("shipmentGatewayConfTypeId",shipmentGatewayConfTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentGatewayConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateShipmentGatewayConfigType")
	public ResponseEntity<Object> updateShipmentGatewayConfigType(HttpSession session, @RequestParam(value="shipmentGatewayConfTypeId") String shipmentGatewayConfTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipmentGatewayConfTypeId",shipmentGatewayConfTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateShipmentGatewayConfigType", paramMap);
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
