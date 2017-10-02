package com.skytala.eCommerce.domain.subscription.mapper;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.subscription.model.Subscription;

public class SubscriptionMapper {

	public static Map<String, Object> map(Subscription subscription) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (subscription.getSubscriptionId() != null) {
			returnVal.put("subscriptionId", subscription.getSubscriptionId());
		}

		if (subscription.getDescription() != null) {
			returnVal.put("description", subscription.getDescription());
		}

		if (subscription.getSubscriptionResourceId() != null) {
			returnVal.put("subscriptionResourceId", subscription.getSubscriptionResourceId());
		}

		if (subscription.getCommunicationEventId() != null) {
			returnVal.put("communicationEventId", subscription.getCommunicationEventId());
		}

		if (subscription.getContactMechId() != null) {
			returnVal.put("contactMechId", subscription.getContactMechId());
		}

		if (subscription.getOriginatedFromPartyId() != null) {
			returnVal.put("originatedFromPartyId", subscription.getOriginatedFromPartyId());
		}

		if (subscription.getOriginatedFromRoleTypeId() != null) {
			returnVal.put("originatedFromRoleTypeId", subscription.getOriginatedFromRoleTypeId());
		}

		if (subscription.getPartyId() != null) {
			returnVal.put("partyId", subscription.getPartyId());
		}

		if (subscription.getRoleTypeId() != null) {
			returnVal.put("roleTypeId", subscription.getRoleTypeId());
		}

		if (subscription.getPartyNeedId() != null) {
			returnVal.put("partyNeedId", subscription.getPartyNeedId());
		}

		if (subscription.getNeedTypeId() != null) {
			returnVal.put("needTypeId", subscription.getNeedTypeId());
		}

		if (subscription.getOrderId() != null) {
			returnVal.put("orderId", subscription.getOrderId());
		}

		if (subscription.getOrderItemSeqId() != null) {
			returnVal.put("orderItemSeqId", subscription.getOrderItemSeqId());
		}

		if (subscription.getProductId() != null) {
			returnVal.put("productId", subscription.getProductId());
		}

		if (subscription.getProductCategoryId() != null) {
			returnVal.put("productCategoryId", subscription.getProductCategoryId());
		}

		if (subscription.getInventoryItemId() != null) {
			returnVal.put("inventoryItemId", subscription.getInventoryItemId());
		}

		if (subscription.getSubscriptionTypeId() != null) {
			returnVal.put("subscriptionTypeId", subscription.getSubscriptionTypeId());
		}

		if (subscription.getExternalSubscriptionId() != null) {
			returnVal.put("externalSubscriptionId", subscription.getExternalSubscriptionId());
		}

		if (subscription.getFromDate() != null) {
			returnVal.put("fromDate", subscription.getFromDate());
		}

		if (subscription.getThruDate() != null) {
			returnVal.put("thruDate", subscription.getThruDate());
		}

		if (subscription.getPurchaseFromDate() != null) {
			returnVal.put("purchaseFromDate", subscription.getPurchaseFromDate());
		}

		if (subscription.getPurchaseThruDate() != null) {
			returnVal.put("purchaseThruDate", subscription.getPurchaseThruDate());
		}

		if (subscription.getMaxLifeTime() != null) {
			returnVal.put("maxLifeTime", subscription.getMaxLifeTime());
		}

		if (subscription.getMaxLifeTimeUomId() != null) {
			returnVal.put("maxLifeTimeUomId", subscription.getMaxLifeTimeUomId());
		}

		if (subscription.getAvailableTime() != null) {
			returnVal.put("availableTime", subscription.getAvailableTime());
		}

		if (subscription.getAvailableTimeUomId() != null) {
			returnVal.put("availableTimeUomId", subscription.getAvailableTimeUomId());
		}

		if (subscription.getUseCountLimit() != null) {
			returnVal.put("useCountLimit", subscription.getUseCountLimit());
		}

		if (subscription.getUseTime() != null) {
			returnVal.put("useTime", subscription.getUseTime());
		}

		if (subscription.getUseTimeUomId() != null) {
			returnVal.put("useTimeUomId", subscription.getUseTimeUomId());
		}

		if (subscription.getAutomaticExtend() != null) {
			returnVal.put("automaticExtend", subscription.getAutomaticExtend());
		}

		if (subscription.getCanclAutmExtTime() != null) {
			returnVal.put("canclAutmExtTime", subscription.getCanclAutmExtTime());
		}

		if (subscription.getCanclAutmExtTimeUomId() != null) {
			returnVal.put("canclAutmExtTimeUomId", subscription.getCanclAutmExtTimeUomId());
		}

		if (subscription.getGracePeriodOnExpiry() != null) {
			returnVal.put("gracePeriodOnExpiry", subscription.getGracePeriodOnExpiry());
		}

		if (subscription.getGracePeriodOnExpiryUomId() != null) {
			returnVal.put("gracePeriodOnExpiryUomId", subscription.getGracePeriodOnExpiryUomId());
		}

		if (subscription.getExpirationCompletedDate() != null) {
			returnVal.put("expirationCompletedDate", subscription.getExpirationCompletedDate());
		}

		return returnVal;
	}

	public static Subscription map(Map<String, Object> fields) {

		Subscription returnVal = new Subscription();

		if (fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("subscriptionResourceId") != null) {
			returnVal.setSubscriptionResourceId((String) fields.get("subscriptionResourceId"));
		}

		if (fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
		}

		if (fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
		}

		if (fields.get("originatedFromPartyId") != null) {
			returnVal.setOriginatedFromPartyId((String) fields.get("originatedFromPartyId"));
		}

		if (fields.get("originatedFromRoleTypeId") != null) {
			returnVal.setOriginatedFromRoleTypeId((String) fields.get("originatedFromRoleTypeId"));
		}

		if (fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
		}

		if (fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
		}

		if (fields.get("partyNeedId") != null) {
			returnVal.setPartyNeedId((String) fields.get("partyNeedId"));
		}

		if (fields.get("needTypeId") != null) {
			returnVal.setNeedTypeId((String) fields.get("needTypeId"));
		}

		if (fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
		}

		if (fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
		}

		if (fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
		}

		if (fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
		}

		if (fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
		}

		if (fields.get("subscriptionTypeId") != null) {
			returnVal.setSubscriptionTypeId((String) fields.get("subscriptionTypeId"));
		}

		if (fields.get("externalSubscriptionId") != null) {
			returnVal.setExternalSubscriptionId((String) fields.get("externalSubscriptionId"));
		}

		if (fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
		}

		if (fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
		}

		if (fields.get("purchaseFromDate") != null) {
			returnVal.setPurchaseFromDate((Timestamp) fields.get("purchaseFromDate"));
		}

		if (fields.get("purchaseThruDate") != null) {
			returnVal.setPurchaseThruDate((Timestamp) fields.get("purchaseThruDate"));
		}

		if (fields.get("maxLifeTime") != null) {
			returnVal.setMaxLifeTime((long) fields.get("maxLifeTime"));
		}

		if (fields.get("maxLifeTimeUomId") != null) {
			returnVal.setMaxLifeTimeUomId((String) fields.get("maxLifeTimeUomId"));
		}

		if (fields.get("availableTime") != null) {
			returnVal.setAvailableTime((long) fields.get("availableTime"));
		}

		if (fields.get("availableTimeUomId") != null) {
			returnVal.setAvailableTimeUomId((String) fields.get("availableTimeUomId"));
		}

		if (fields.get("useCountLimit") != null) {
			returnVal.setUseCountLimit((long) fields.get("useCountLimit"));
		}

		if (fields.get("useTime") != null) {
			returnVal.setUseTime((long) fields.get("useTime"));
		}

		if (fields.get("useTimeUomId") != null) {
			returnVal.setUseTimeUomId((String) fields.get("useTimeUomId"));
		}

		if (fields.get("automaticExtend") != null) {
			returnVal.setAutomaticExtend((boolean) fields.get("automaticExtend"));
		}

		if (fields.get("canclAutmExtTime") != null) {
			returnVal.setCanclAutmExtTime((long) fields.get("canclAutmExtTime"));
		}

		if (fields.get("canclAutmExtTimeUomId") != null) {
			returnVal.setCanclAutmExtTimeUomId((String) fields.get("canclAutmExtTimeUomId"));
		}

		if (fields.get("gracePeriodOnExpiry") != null) {
			returnVal.setGracePeriodOnExpiry((long) fields.get("gracePeriodOnExpiry"));
		}

		if (fields.get("gracePeriodOnExpiryUomId") != null) {
			returnVal.setGracePeriodOnExpiryUomId((String) fields.get("gracePeriodOnExpiryUomId"));
		}

		if (fields.get("expirationCompletedDate") != null) {
			returnVal.setExpirationCompletedDate((Timestamp) fields.get("expirationCompletedDate"));
		}

		return returnVal;
	}

	public static Subscription mapstrstr(Map<String, String> fields) throws Exception {

		Subscription returnVal = new Subscription();

		if (fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("subscriptionResourceId") != null) {
			returnVal.setSubscriptionResourceId((String) fields.get("subscriptionResourceId"));
		}

		if (fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
		}

		if (fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
		}

		if (fields.get("originatedFromPartyId") != null) {
			returnVal.setOriginatedFromPartyId((String) fields.get("originatedFromPartyId"));
		}

		if (fields.get("originatedFromRoleTypeId") != null) {
			returnVal.setOriginatedFromRoleTypeId((String) fields.get("originatedFromRoleTypeId"));
		}

		if (fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
		}

		if (fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
		}

		if (fields.get("partyNeedId") != null) {
			returnVal.setPartyNeedId((String) fields.get("partyNeedId"));
		}

		if (fields.get("needTypeId") != null) {
			returnVal.setNeedTypeId((String) fields.get("needTypeId"));
		}

		if (fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
		}

		if (fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
		}

		if (fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
		}

		if (fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
		}

		if (fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
		}

		if (fields.get("subscriptionTypeId") != null) {
			returnVal.setSubscriptionTypeId((String) fields.get("subscriptionTypeId"));
		}

		if (fields.get("externalSubscriptionId") != null) {
			returnVal.setExternalSubscriptionId((String) fields.get("externalSubscriptionId"));
		}

		if (fields.get("fromDate") != null) {
			String buf = fields.get("fromDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
		}

		if (fields.get("thruDate") != null) {
			String buf = fields.get("thruDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
		}

		if (fields.get("purchaseFromDate") != null) {
			String buf = fields.get("purchaseFromDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseFromDate(ibuf);
		}

		if (fields.get("purchaseThruDate") != null) {
			String buf = fields.get("purchaseThruDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseThruDate(ibuf);
		}

		if (fields.get("maxLifeTime") != null) {
			String buf;
			buf = fields.get("maxLifeTime");
			long ibuf = Long.parseLong(buf);
			returnVal.setMaxLifeTime(ibuf);
		}

		if (fields.get("maxLifeTimeUomId") != null) {
			returnVal.setMaxLifeTimeUomId((String) fields.get("maxLifeTimeUomId"));
		}

		if (fields.get("availableTime") != null) {
			String buf;
			buf = fields.get("availableTime");
			long ibuf = Long.parseLong(buf);
			returnVal.setAvailableTime(ibuf);
		}

		if (fields.get("availableTimeUomId") != null) {
			returnVal.setAvailableTimeUomId((String) fields.get("availableTimeUomId"));
		}

		if (fields.get("useCountLimit") != null) {
			String buf;
			buf = fields.get("useCountLimit");
			long ibuf = Long.parseLong(buf);
			returnVal.setUseCountLimit(ibuf);
		}

		if (fields.get("useTime") != null) {
			String buf;
			buf = fields.get("useTime");
			long ibuf = Long.parseLong(buf);
			returnVal.setUseTime(ibuf);
		}

		if (fields.get("useTimeUomId") != null) {
			returnVal.setUseTimeUomId((String) fields.get("useTimeUomId"));
		}

		if (fields.get("automaticExtend") != null) {
			String buf;
			buf = fields.get("automaticExtend");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutomaticExtend(ibuf);
		}

		if (fields.get("canclAutmExtTime") != null) {
			String buf;
			buf = fields.get("canclAutmExtTime");
			long ibuf = Long.parseLong(buf);
			returnVal.setCanclAutmExtTime(ibuf);
		}

		if (fields.get("canclAutmExtTimeUomId") != null) {
			returnVal.setCanclAutmExtTimeUomId((String) fields.get("canclAutmExtTimeUomId"));
		}

		if (fields.get("gracePeriodOnExpiry") != null) {
			String buf;
			buf = fields.get("gracePeriodOnExpiry");
			long ibuf = Long.parseLong(buf);
			returnVal.setGracePeriodOnExpiry(ibuf);
		}

		if (fields.get("gracePeriodOnExpiryUomId") != null) {
			returnVal.setGracePeriodOnExpiryUomId((String) fields.get("gracePeriodOnExpiryUomId"));
		}

		if (fields.get("expirationCompletedDate") != null) {
			String buf = fields.get("expirationCompletedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpirationCompletedDate(ibuf);
		}

		return returnVal;
	}

	public static Subscription map(GenericValue val) {

		Subscription returnVal = new Subscription();
		returnVal.setSubscriptionId(val.getString("subscriptionId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setSubscriptionResourceId(val.getString("subscriptionResourceId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setOriginatedFromPartyId(val.getString("originatedFromPartyId"));
		returnVal.setOriginatedFromRoleTypeId(val.getString("originatedFromRoleTypeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setPartyNeedId(val.getString("partyNeedId"));
		returnVal.setNeedTypeId(val.getString("needTypeId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setSubscriptionTypeId(val.getString("subscriptionTypeId"));
		returnVal.setExternalSubscriptionId(val.getString("externalSubscriptionId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPurchaseFromDate(val.getTimestamp("purchaseFromDate"));
		returnVal.setPurchaseThruDate(val.getTimestamp("purchaseThruDate"));
		returnVal.setMaxLifeTime(val.getLong("maxLifeTime"));
		returnVal.setMaxLifeTimeUomId(val.getString("maxLifeTimeUomId"));
		returnVal.setAvailableTime(val.getLong("availableTime"));
		returnVal.setAvailableTimeUomId(val.getString("availableTimeUomId"));
		returnVal.setUseCountLimit(val.getLong("useCountLimit"));
		returnVal.setUseTime(val.getLong("useTime"));
		returnVal.setUseTimeUomId(val.getString("useTimeUomId"));
		returnVal.setAutomaticExtend(val.getBoolean("automaticExtend"));
		returnVal.setCanclAutmExtTime(val.getLong("canclAutmExtTime"));
		returnVal.setCanclAutmExtTimeUomId(val.getString("canclAutmExtTimeUomId"));
		returnVal.setGracePeriodOnExpiry(val.getLong("gracePeriodOnExpiry"));
		returnVal.setGracePeriodOnExpiryUomId(val.getString("gracePeriodOnExpiryUomId"));
		returnVal.setExpirationCompletedDate(val.getTimestamp("expirationCompletedDate"));

		return returnVal;

	}

	public static Subscription map(HttpServletRequest request) throws Exception {

		Subscription returnVal = new Subscription();

		Map<String, String[]> paramMap = request.getParameterMap();

		if (paramMap.containsKey("subscriptionId")) {
			returnVal.setSubscriptionId(request.getParameter("subscriptionId"));
		}

		if (paramMap.containsKey("description")) {
			returnVal.setDescription(request.getParameter("description"));
		}
		if (paramMap.containsKey("subscriptionResourceId")) {
			returnVal.setSubscriptionResourceId(request.getParameter("subscriptionResourceId"));
		}
		if (paramMap.containsKey("communicationEventId")) {
			returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
		}
		if (paramMap.containsKey("contactMechId")) {
			returnVal.setContactMechId(request.getParameter("contactMechId"));
		}
		if (paramMap.containsKey("originatedFromPartyId")) {
			returnVal.setOriginatedFromPartyId(request.getParameter("originatedFromPartyId"));
		}
		if (paramMap.containsKey("originatedFromRoleTypeId")) {
			returnVal.setOriginatedFromRoleTypeId(request.getParameter("originatedFromRoleTypeId"));
		}
		if (paramMap.containsKey("partyId")) {
			returnVal.setPartyId(request.getParameter("partyId"));
		}
		if (paramMap.containsKey("roleTypeId")) {
			returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
		}
		if (paramMap.containsKey("partyNeedId")) {
			returnVal.setPartyNeedId(request.getParameter("partyNeedId"));
		}
		if (paramMap.containsKey("needTypeId")) {
			returnVal.setNeedTypeId(request.getParameter("needTypeId"));
		}
		if (paramMap.containsKey("orderId")) {
			returnVal.setOrderId(request.getParameter("orderId"));
		}
		if (paramMap.containsKey("orderItemSeqId")) {
			returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
		}
		if (paramMap.containsKey("productId")) {
			returnVal.setProductId(request.getParameter("productId"));
		}
		if (paramMap.containsKey("productCategoryId")) {
			returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
		}
		if (paramMap.containsKey("inventoryItemId")) {
			returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
		}
		if (paramMap.containsKey("subscriptionTypeId")) {
			returnVal.setSubscriptionTypeId(request.getParameter("subscriptionTypeId"));
		}
		if (paramMap.containsKey("externalSubscriptionId")) {
			returnVal.setExternalSubscriptionId(request.getParameter("externalSubscriptionId"));
		}
		if (paramMap.containsKey("fromDate")) {
			String buf = request.getParameter("fromDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
		}
		if (paramMap.containsKey("thruDate")) {
			String buf = request.getParameter("thruDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
		}
		if (paramMap.containsKey("purchaseFromDate")) {
			String buf = request.getParameter("purchaseFromDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseFromDate(ibuf);
		}
		if (paramMap.containsKey("purchaseThruDate")) {
			String buf = request.getParameter("purchaseThruDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseThruDate(ibuf);
		}
		if (paramMap.containsKey("maxLifeTime")) {
			String buf = request.getParameter("maxLifeTime");
			Long ibuf = Long.parseLong(buf);
			returnVal.setMaxLifeTime(ibuf);
		}
		if (paramMap.containsKey("maxLifeTimeUomId")) {
			returnVal.setMaxLifeTimeUomId(request.getParameter("maxLifeTimeUomId"));
		}
		if (paramMap.containsKey("availableTime")) {
			String buf = request.getParameter("availableTime");
			Long ibuf = Long.parseLong(buf);
			returnVal.setAvailableTime(ibuf);
		}
		if (paramMap.containsKey("availableTimeUomId")) {
			returnVal.setAvailableTimeUomId(request.getParameter("availableTimeUomId"));
		}
		if (paramMap.containsKey("useCountLimit")) {
			String buf = request.getParameter("useCountLimit");
			Long ibuf = Long.parseLong(buf);
			returnVal.setUseCountLimit(ibuf);
		}
		if (paramMap.containsKey("useTime")) {
			String buf = request.getParameter("useTime");
			Long ibuf = Long.parseLong(buf);
			returnVal.setUseTime(ibuf);
		}
		if (paramMap.containsKey("useTimeUomId")) {
			returnVal.setUseTimeUomId(request.getParameter("useTimeUomId"));
		}
		if (paramMap.containsKey("automaticExtend")) {
			String buf = request.getParameter("automaticExtend");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutomaticExtend(ibuf);
		}
		if (paramMap.containsKey("canclAutmExtTime")) {
			String buf = request.getParameter("canclAutmExtTime");
			Long ibuf = Long.parseLong(buf);
			returnVal.setCanclAutmExtTime(ibuf);
		}
		if (paramMap.containsKey("canclAutmExtTimeUomId")) {
			returnVal.setCanclAutmExtTimeUomId(request.getParameter("canclAutmExtTimeUomId"));
		}
		if (paramMap.containsKey("gracePeriodOnExpiry")) {
			String buf = request.getParameter("gracePeriodOnExpiry");
			Long ibuf = Long.parseLong(buf);
			returnVal.setGracePeriodOnExpiry(ibuf);
		}
		if (paramMap.containsKey("gracePeriodOnExpiryUomId")) {
			returnVal.setGracePeriodOnExpiryUomId(request.getParameter("gracePeriodOnExpiryUomId"));
		}
		if (paramMap.containsKey("expirationCompletedDate")) {
			String buf = request.getParameter("expirationCompletedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpirationCompletedDate(ibuf);
		}
		return returnVal;

	}
}
