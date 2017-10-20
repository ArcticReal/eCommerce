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

@RestController
@RequestMapping("/service/ProductSubscription")
public class ProductSubscription{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSubscriptionTypeAttr")
	public ResponseEntity<Object> deleteSubscriptionTypeAttr(HttpSession session, @RequestParam(value="subscriptionTypeId") String subscriptionTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSubscriptionTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSubscriptionResource")
	public ResponseEntity<Object> createSubscriptionResource(HttpSession session, @RequestParam(value="serviceNameOnExpiry", required=false) String serviceNameOnExpiry, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="parentResourceId", required=false) String parentResourceId, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("serviceNameOnExpiry",serviceNameOnExpiry);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("parentResourceId",parentResourceId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSubscriptionResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSubscriptionAttribute")
	public ResponseEntity<Object> updateSubscriptionAttribute(HttpSession session, @RequestParam(value="subscriptionId") String subscriptionId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrValue", required=false) String attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSubscriptionAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductSubscriptionResource")
	public ResponseEntity<Object> deleteProductSubscriptionResource(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="subscriptionResourceId") String subscriptionResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductSubscriptionResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSubscriptionAttribute")
	public ResponseEntity<Object> createSubscriptionAttribute(HttpSession session, @RequestParam(value="subscriptionId") String subscriptionId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSubscriptionAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/isSubscribed")
	public ResponseEntity<Object> isSubscribed(HttpSession session, @RequestParam(value="partyId") String partyId, @RequestParam(value="maxLifeTimeUomId", required=false) String maxLifeTimeUomId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="subscriptionResourceId", required=false) String subscriptionResourceId, @RequestParam(value="description", required=false) String description, @RequestParam(value="subscriptionTypeId", required=false) String subscriptionTypeId, @RequestParam(value="canclAutmExtTimeUomId", required=false) String canclAutmExtTimeUomId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="gracePeriodOnExpiryUomId", required=false) String gracePeriodOnExpiryUomId, @RequestParam(value="availableTime", required=false) Long availableTime, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="partyNeedId", required=false) String partyNeedId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="needTypeId", required=false) String needTypeId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="filterByDate", required=false) String filterByDate, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="expirationCompletedDate", required=false) Timestamp expirationCompletedDate, @RequestParam(value="availableTimeUomId", required=false) String availableTimeUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="canclAutmExtTime", required=false) Long canclAutmExtTime, @RequestParam(value="originatedFromRoleTypeId", required=false) String originatedFromRoleTypeId, @RequestParam(value="automaticExtend", required=false) String automaticExtend, @RequestParam(value="originatedFromPartyId", required=false) String originatedFromPartyId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="maxLifeTime", required=false) Long maxLifeTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="externalSubscriptionId", required=false) String externalSubscriptionId, @RequestParam(value="gracePeriodOnExpiry", required=false) Long gracePeriodOnExpiry, @RequestParam(value="subscriptionId", required=false) String subscriptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("maxLifeTimeUomId",maxLifeTimeUomId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("orderId",orderId);
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("description",description);
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("canclAutmExtTimeUomId",canclAutmExtTimeUomId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("gracePeriodOnExpiryUomId",gracePeriodOnExpiryUomId);
		paramMap.put("availableTime",availableTime);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyNeedId",partyNeedId);
		paramMap.put("useTime",useTime);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("needTypeId",needTypeId);
		paramMap.put("productId",productId);
		paramMap.put("filterByDate",filterByDate);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("expirationCompletedDate",expirationCompletedDate);
		paramMap.put("availableTimeUomId",availableTimeUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("canclAutmExtTime",canclAutmExtTime);
		paramMap.put("originatedFromRoleTypeId",originatedFromRoleTypeId);
		paramMap.put("automaticExtend",automaticExtend);
		paramMap.put("originatedFromPartyId",originatedFromPartyId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("maxLifeTime",maxLifeTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("externalSubscriptionId",externalSubscriptionId);
		paramMap.put("gracePeriodOnExpiry",gracePeriodOnExpiry);
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("isSubscribed", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductSubscriptionResource")
	public ResponseEntity<Object> createProductSubscriptionResource(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="subscriptionResourceId") String subscriptionResourceId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="maxLifeTimeUomId", required=false) String maxLifeTimeUomId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="canclAutmExtTimeUomId", required=false) String canclAutmExtTimeUomId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="availableTimeUomId", required=false) String availableTimeUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="canclAutmExtTime", required=false) Long canclAutmExtTime, @RequestParam(value="gracePeriodOnExpiryUomId", required=false) String gracePeriodOnExpiryUomId, @RequestParam(value="availableTime", required=false) Long availableTime, @RequestParam(value="automaticExtend", required=false) String automaticExtend, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="maxLifeTime", required=false) Long maxLifeTime, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="gracePeriodOnExpiry", required=false) Long gracePeriodOnExpiry) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("maxLifeTimeUomId",maxLifeTimeUomId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("canclAutmExtTimeUomId",canclAutmExtTimeUomId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("availableTimeUomId",availableTimeUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("canclAutmExtTime",canclAutmExtTime);
		paramMap.put("gracePeriodOnExpiryUomId",gracePeriodOnExpiryUomId);
		paramMap.put("availableTime",availableTime);
		paramMap.put("automaticExtend",automaticExtend);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("maxLifeTime",maxLifeTime);
		paramMap.put("useTime",useTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("gracePeriodOnExpiry",gracePeriodOnExpiry);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductSubscriptionResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSubscriptionActivity")
	public ResponseEntity<Object> updateSubscriptionActivity(HttpSession session, @RequestParam(value="subscriptionActivityId") String subscriptionActivityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="dateSent", required=false) Timestamp dateSent) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionActivityId",subscriptionActivityId);
		paramMap.put("comments",comments);
		paramMap.put("dateSent",dateSent);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSubscriptionActivity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/runSubscriptionExpired")
	public ResponseEntity<Object> runSubscriptionExpired(HttpSession session, @RequestParam(value="subscriptionId") String subscriptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("runSubscriptionExpired", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeSubscriptionCommEvent")
	public ResponseEntity<Object> removeSubscriptionCommEvent(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="subscriptionId") String subscriptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeSubscriptionCommEvent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSubscriptionType")
	public ResponseEntity<Object> updateSubscriptionType(HttpSession session, @RequestParam(value="subscriptionTypeId") String subscriptionTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSubscriptionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getSubscriptionEnt")
	public ResponseEntity<Object> getSubscriptionEnt(HttpSession session, @RequestParam(value="subscriptionId") String subscriptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getSubscriptionEnt", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSubscriptionAttribute")
	public ResponseEntity<Object> deleteSubscriptionAttribute(HttpSession session, @RequestParam(value="subscriptionId") String subscriptionId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSubscriptionAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductSubscriptionResource")
	public ResponseEntity<Object> updateProductSubscriptionResource(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="subscriptionResourceId") String subscriptionResourceId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="maxLifeTimeUomId", required=false) String maxLifeTimeUomId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="canclAutmExtTimeUomId", required=false) String canclAutmExtTimeUomId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="availableTimeUomId", required=false) String availableTimeUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="canclAutmExtTime", required=false) Long canclAutmExtTime, @RequestParam(value="gracePeriodOnExpiryUomId", required=false) String gracePeriodOnExpiryUomId, @RequestParam(value="availableTime", required=false) Long availableTime, @RequestParam(value="automaticExtend", required=false) String automaticExtend, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="maxLifeTime", required=false) Long maxLifeTime, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="gracePeriodOnExpiry", required=false) Long gracePeriodOnExpiry) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("maxLifeTimeUomId",maxLifeTimeUomId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("canclAutmExtTimeUomId",canclAutmExtTimeUomId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("availableTimeUomId",availableTimeUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("canclAutmExtTime",canclAutmExtTime);
		paramMap.put("gracePeriodOnExpiryUomId",gracePeriodOnExpiryUomId);
		paramMap.put("availableTime",availableTime);
		paramMap.put("automaticExtend",automaticExtend);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("maxLifeTime",maxLifeTime);
		paramMap.put("useTime",useTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("gracePeriodOnExpiry",gracePeriodOnExpiry);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductSubscriptionResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSubscriptionActivity")
	public ResponseEntity<Object> deleteSubscriptionActivity(HttpSession session, @RequestParam(value="subscriptionActivityId") String subscriptionActivityId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionActivityId",subscriptionActivityId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSubscriptionActivity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processExtendSubscriptionByOrder")
	public ResponseEntity<Object> processExtendSubscriptionByOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processExtendSubscriptionByOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/runServiceOnSubscriptionExpiry")
	public ResponseEntity<Object> runServiceOnSubscriptionExpiry(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("runServiceOnSubscriptionExpiry", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSubscription")
	public ResponseEntity<Object> createSubscription(HttpSession session, @RequestParam(value="maxLifeTimeUomId", required=false) String maxLifeTimeUomId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="subscriptionResourceId", required=false) String subscriptionResourceId, @RequestParam(value="description", required=false) String description, @RequestParam(value="subscriptionTypeId", required=false) String subscriptionTypeId, @RequestParam(value="canclAutmExtTimeUomId", required=false) String canclAutmExtTimeUomId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="gracePeriodOnExpiryUomId", required=false) String gracePeriodOnExpiryUomId, @RequestParam(value="availableTime", required=false) Long availableTime, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="partyNeedId", required=false) String partyNeedId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="needTypeId", required=false) String needTypeId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="expirationCompletedDate", required=false) Timestamp expirationCompletedDate, @RequestParam(value="availableTimeUomId", required=false) String availableTimeUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="canclAutmExtTime", required=false) Long canclAutmExtTime, @RequestParam(value="originatedFromRoleTypeId", required=false) String originatedFromRoleTypeId, @RequestParam(value="automaticExtend", required=false) String automaticExtend, @RequestParam(value="originatedFromPartyId", required=false) String originatedFromPartyId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="maxLifeTime", required=false) Long maxLifeTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="externalSubscriptionId", required=false) String externalSubscriptionId, @RequestParam(value="gracePeriodOnExpiry", required=false) Long gracePeriodOnExpiry, @RequestParam(value="subscriptionId", required=false) String subscriptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("maxLifeTimeUomId",maxLifeTimeUomId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("orderId",orderId);
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("description",description);
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("canclAutmExtTimeUomId",canclAutmExtTimeUomId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("gracePeriodOnExpiryUomId",gracePeriodOnExpiryUomId);
		paramMap.put("availableTime",availableTime);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyNeedId",partyNeedId);
		paramMap.put("useTime",useTime);
		paramMap.put("partyId",partyId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("needTypeId",needTypeId);
		paramMap.put("productId",productId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("expirationCompletedDate",expirationCompletedDate);
		paramMap.put("availableTimeUomId",availableTimeUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("canclAutmExtTime",canclAutmExtTime);
		paramMap.put("originatedFromRoleTypeId",originatedFromRoleTypeId);
		paramMap.put("automaticExtend",automaticExtend);
		paramMap.put("originatedFromPartyId",originatedFromPartyId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("maxLifeTime",maxLifeTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("externalSubscriptionId",externalSubscriptionId);
		paramMap.put("gracePeriodOnExpiry",gracePeriodOnExpiry);
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSubscription", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSubscription")
	public ResponseEntity<Object> updateSubscription(HttpSession session, @RequestParam(value="subscriptionId") String subscriptionId, @RequestParam(value="maxLifeTimeUomId", required=false) String maxLifeTimeUomId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="subscriptionResourceId", required=false) String subscriptionResourceId, @RequestParam(value="description", required=false) String description, @RequestParam(value="subscriptionTypeId", required=false) String subscriptionTypeId, @RequestParam(value="canclAutmExtTimeUomId", required=false) String canclAutmExtTimeUomId, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="gracePeriodOnExpiryUomId", required=false) String gracePeriodOnExpiryUomId, @RequestParam(value="availableTime", required=false) Long availableTime, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="partyNeedId", required=false) String partyNeedId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="needTypeId", required=false) String needTypeId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="expirationCompletedDate", required=false) Timestamp expirationCompletedDate, @RequestParam(value="availableTimeUomId", required=false) String availableTimeUomId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="canclAutmExtTime", required=false) Long canclAutmExtTime, @RequestParam(value="originatedFromRoleTypeId", required=false) String originatedFromRoleTypeId, @RequestParam(value="automaticExtend", required=false) String automaticExtend, @RequestParam(value="originatedFromPartyId", required=false) String originatedFromPartyId, @RequestParam(value="communicationEventId", required=false) String communicationEventId, @RequestParam(value="maxLifeTime", required=false) Long maxLifeTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="externalSubscriptionId", required=false) String externalSubscriptionId, @RequestParam(value="gracePeriodOnExpiry", required=false) Long gracePeriodOnExpiry) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("maxLifeTimeUomId",maxLifeTimeUomId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("orderId",orderId);
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("description",description);
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("canclAutmExtTimeUomId",canclAutmExtTimeUomId);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("gracePeriodOnExpiryUomId",gracePeriodOnExpiryUomId);
		paramMap.put("availableTime",availableTime);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyNeedId",partyNeedId);
		paramMap.put("useTime",useTime);
		paramMap.put("partyId",partyId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("needTypeId",needTypeId);
		paramMap.put("productId",productId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("expirationCompletedDate",expirationCompletedDate);
		paramMap.put("availableTimeUomId",availableTimeUomId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("canclAutmExtTime",canclAutmExtTime);
		paramMap.put("originatedFromRoleTypeId",originatedFromRoleTypeId);
		paramMap.put("automaticExtend",automaticExtend);
		paramMap.put("originatedFromPartyId",originatedFromPartyId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("maxLifeTime",maxLifeTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("externalSubscriptionId",externalSubscriptionId);
		paramMap.put("gracePeriodOnExpiry",gracePeriodOnExpiry);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSubscription", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSubscriptionActivity")
	public ResponseEntity<Object> createSubscriptionActivity(HttpSession session, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="subscriptionActivityId", required=false) String subscriptionActivityId, @RequestParam(value="dateSent", required=false) Timestamp dateSent) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comments",comments);
		paramMap.put("subscriptionActivityId",subscriptionActivityId);
		paramMap.put("dateSent",dateSent);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSubscriptionActivity", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSubscriptionType")
	public ResponseEntity<Object> createSubscriptionType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="subscriptionTypeId", required=false) String subscriptionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSubscriptionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSubscriptionResource")
	public ResponseEntity<Object> updateSubscriptionResource(HttpSession session, @RequestParam(value="subscriptionResourceId") String subscriptionResourceId, @RequestParam(value="serviceNameOnExpiry", required=false) String serviceNameOnExpiry, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="parentResourceId", required=false) String parentResourceId, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("serviceNameOnExpiry",serviceNameOnExpiry);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("parentResourceId",parentResourceId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSubscriptionResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/subscriptionPermissionCheck")
	public ResponseEntity<Object> subscriptionPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("subscriptionPermissionCheck", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processExtendSubscriptionByProduct")
	public ResponseEntity<Object> processExtendSubscriptionByProduct(HttpSession session, @RequestParam(value="quantity") Integer quantity, @RequestParam(value="productId") String productId, @RequestParam(value="partyId") String partyId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="orderCreatedDate", required=false) Timestamp orderCreatedDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("partyId",partyId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("orderId",orderId);
		paramMap.put("orderCreatedDate",orderCreatedDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processExtendSubscriptionByProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSubscriptionTypeAttr")
	public ResponseEntity<Object> updateSubscriptionTypeAttr(HttpSession session, @RequestParam(value="subscriptionTypeId") String subscriptionTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSubscriptionTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSubscriptionType")
	public ResponseEntity<Object> deleteSubscriptionType(HttpSession session, @RequestParam(value="subscriptionTypeId") String subscriptionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSubscriptionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSubscriptionTypeAttr")
	public ResponseEntity<Object> createSubscriptionTypeAttr(HttpSession session, @RequestParam(value="subscriptionTypeId") String subscriptionTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subscriptionTypeId",subscriptionTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSubscriptionTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/processExtendSubscription")
	public ResponseEntity<Object> processExtendSubscription(HttpSession session, @RequestParam(value="useTimeUomId") String useTimeUomId, @RequestParam(value="subscriptionResourceId") String subscriptionResourceId, @RequestParam(value="useTime") Integer useTime, @RequestParam(value="partyId") String partyId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="canclAutmExtTime", required=false) Integer canclAutmExtTime, @RequestParam(value="gracePeriodOnExpiryUomId", required=false) String gracePeriodOnExpiryUomId, @RequestParam(value="alwaysCreateNewRecord", required=false) String alwaysCreateNewRecord, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="automaticExtend", required=false) String automaticExtend, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="canclAutmExtTimeUomId", required=false) String canclAutmExtTimeUomId, @RequestParam(value="gracePeriodOnExpiry", required=false) Integer gracePeriodOnExpiry) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("subscriptionResourceId",subscriptionResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("partyId",partyId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("canclAutmExtTime",canclAutmExtTime);
		paramMap.put("gracePeriodOnExpiryUomId",gracePeriodOnExpiryUomId);
		paramMap.put("alwaysCreateNewRecord",alwaysCreateNewRecord);
		paramMap.put("productId",productId);
		paramMap.put("orderId",orderId);
		paramMap.put("automaticExtend",automaticExtend);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("canclAutmExtTimeUomId",canclAutmExtTimeUomId);
		paramMap.put("gracePeriodOnExpiry",gracePeriodOnExpiry);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("processExtendSubscription", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSubscriptionCommEvent")
	public ResponseEntity<Object> createSubscriptionCommEvent(HttpSession session, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="subscriptionId") String subscriptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("subscriptionId",subscriptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSubscriptionCommEvent", paramMap);
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
