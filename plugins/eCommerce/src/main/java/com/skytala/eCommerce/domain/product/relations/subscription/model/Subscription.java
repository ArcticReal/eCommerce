package com.skytala.eCommerce.domain.product.relations.subscription.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.SubscriptionMapper;

public class Subscription implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionId;
private String description;
private String subscriptionResourceId;
private String communicationEventId;
private String contactMechId;
private String originatedFromPartyId;
private String originatedFromRoleTypeId;
private String partyId;
private String roleTypeId;
private String partyNeedId;
private String needTypeId;
private String orderId;
private String orderItemSeqId;
private String productId;
private String productCategoryId;
private String inventoryItemId;
private String subscriptionTypeId;
private String externalSubscriptionId;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp purchaseFromDate;
private Timestamp purchaseThruDate;
private Long maxLifeTime;
private String maxLifeTimeUomId;
private Long availableTime;
private String availableTimeUomId;
private Long useCountLimit;
private Long useTime;
private String useTimeUomId;
private Boolean automaticExtend;
private Long canclAutmExtTime;
private String canclAutmExtTimeUomId;
private Long gracePeriodOnExpiry;
private String gracePeriodOnExpiryUomId;
private Timestamp expirationCompletedDate;

public String getSubscriptionId() {
return subscriptionId;
}

public void setSubscriptionId(String  subscriptionId) {
this.subscriptionId = subscriptionId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getSubscriptionResourceId() {
return subscriptionResourceId;
}

public void setSubscriptionResourceId(String  subscriptionResourceId) {
this.subscriptionResourceId = subscriptionResourceId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getOriginatedFromPartyId() {
return originatedFromPartyId;
}

public void setOriginatedFromPartyId(String  originatedFromPartyId) {
this.originatedFromPartyId = originatedFromPartyId;
}

public String getOriginatedFromRoleTypeId() {
return originatedFromRoleTypeId;
}

public void setOriginatedFromRoleTypeId(String  originatedFromRoleTypeId) {
this.originatedFromRoleTypeId = originatedFromRoleTypeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getPartyNeedId() {
return partyNeedId;
}

public void setPartyNeedId(String  partyNeedId) {
this.partyNeedId = partyNeedId;
}

public String getNeedTypeId() {
return needTypeId;
}

public void setNeedTypeId(String  needTypeId) {
this.needTypeId = needTypeId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getSubscriptionTypeId() {
return subscriptionTypeId;
}

public void setSubscriptionTypeId(String  subscriptionTypeId) {
this.subscriptionTypeId = subscriptionTypeId;
}

public String getExternalSubscriptionId() {
return externalSubscriptionId;
}

public void setExternalSubscriptionId(String  externalSubscriptionId) {
this.externalSubscriptionId = externalSubscriptionId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public Timestamp getPurchaseFromDate() {
return purchaseFromDate;
}

public void setPurchaseFromDate(Timestamp  purchaseFromDate) {
this.purchaseFromDate = purchaseFromDate;
}

public Timestamp getPurchaseThruDate() {
return purchaseThruDate;
}

public void setPurchaseThruDate(Timestamp  purchaseThruDate) {
this.purchaseThruDate = purchaseThruDate;
}

public Long getMaxLifeTime() {
return maxLifeTime;
}

public void setMaxLifeTime(Long  maxLifeTime) {
this.maxLifeTime = maxLifeTime;
}

public String getMaxLifeTimeUomId() {
return maxLifeTimeUomId;
}

public void setMaxLifeTimeUomId(String  maxLifeTimeUomId) {
this.maxLifeTimeUomId = maxLifeTimeUomId;
}

public Long getAvailableTime() {
return availableTime;
}

public void setAvailableTime(Long  availableTime) {
this.availableTime = availableTime;
}

public String getAvailableTimeUomId() {
return availableTimeUomId;
}

public void setAvailableTimeUomId(String  availableTimeUomId) {
this.availableTimeUomId = availableTimeUomId;
}

public Long getUseCountLimit() {
return useCountLimit;
}

public void setUseCountLimit(Long  useCountLimit) {
this.useCountLimit = useCountLimit;
}

public Long getUseTime() {
return useTime;
}

public void setUseTime(Long  useTime) {
this.useTime = useTime;
}

public String getUseTimeUomId() {
return useTimeUomId;
}

public void setUseTimeUomId(String  useTimeUomId) {
this.useTimeUomId = useTimeUomId;
}

public Boolean getAutomaticExtend() {
return automaticExtend;
}

public void setAutomaticExtend(Boolean  automaticExtend) {
this.automaticExtend = automaticExtend;
}

public Long getCanclAutmExtTime() {
return canclAutmExtTime;
}

public void setCanclAutmExtTime(Long  canclAutmExtTime) {
this.canclAutmExtTime = canclAutmExtTime;
}

public String getCanclAutmExtTimeUomId() {
return canclAutmExtTimeUomId;
}

public void setCanclAutmExtTimeUomId(String  canclAutmExtTimeUomId) {
this.canclAutmExtTimeUomId = canclAutmExtTimeUomId;
}

public Long getGracePeriodOnExpiry() {
return gracePeriodOnExpiry;
}

public void setGracePeriodOnExpiry(Long  gracePeriodOnExpiry) {
this.gracePeriodOnExpiry = gracePeriodOnExpiry;
}

public String getGracePeriodOnExpiryUomId() {
return gracePeriodOnExpiryUomId;
}

public void setGracePeriodOnExpiryUomId(String  gracePeriodOnExpiryUomId) {
this.gracePeriodOnExpiryUomId = gracePeriodOnExpiryUomId;
}

public Timestamp getExpirationCompletedDate() {
return expirationCompletedDate;
}

public void setExpirationCompletedDate(Timestamp  expirationCompletedDate) {
this.expirationCompletedDate = expirationCompletedDate;
}


public Map<String, Object> mapAttributeField() {
return SubscriptionMapper.map(this);
}
}
