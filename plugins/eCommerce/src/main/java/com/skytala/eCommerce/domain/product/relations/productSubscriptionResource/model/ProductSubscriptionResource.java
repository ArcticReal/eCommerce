package com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.mapper.ProductSubscriptionResourceMapper;

public class ProductSubscriptionResource implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String subscriptionResourceId;
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
private String useRoleTypeId;
private Boolean automaticExtend;
private Long canclAutmExtTime;
private String canclAutmExtTimeUomId;
private Long gracePeriodOnExpiry;
private String gracePeriodOnExpiryUomId;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getSubscriptionResourceId() {
return subscriptionResourceId;
}

public void setSubscriptionResourceId(String  subscriptionResourceId) {
this.subscriptionResourceId = subscriptionResourceId;
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

public String getUseRoleTypeId() {
return useRoleTypeId;
}

public void setUseRoleTypeId(String  useRoleTypeId) {
this.useRoleTypeId = useRoleTypeId;
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


public Map<String, Object> mapAttributeField() {
return ProductSubscriptionResourceMapper.map(this);
}
}
