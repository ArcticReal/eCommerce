package com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.orderReturn;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.orderReturn.TrackingCodeOrderReturnMapper;

public class TrackingCodeOrderReturn implements Serializable{

private static final long serialVersionUID = 1L;
private String returnId;
private String orderId;
private String orderItemSeqId;
private String trackingCodeTypeId;
private String trackingCodeId;
private Boolean isBillable;
private String siteId;
private Boolean hasExported;
private Timestamp affiliateReferredTimeStamp;

public String getReturnId() {
return returnId;
}

public void setReturnId(String  returnId) {
this.returnId = returnId;
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

public String getTrackingCodeTypeId() {
return trackingCodeTypeId;
}

public void setTrackingCodeTypeId(String  trackingCodeTypeId) {
this.trackingCodeTypeId = trackingCodeTypeId;
}

public String getTrackingCodeId() {
return trackingCodeId;
}

public void setTrackingCodeId(String  trackingCodeId) {
this.trackingCodeId = trackingCodeId;
}

public Boolean getIsBillable() {
return isBillable;
}

public void setIsBillable(Boolean  isBillable) {
this.isBillable = isBillable;
}

public String getSiteId() {
return siteId;
}

public void setSiteId(String  siteId) {
this.siteId = siteId;
}

public Boolean getHasExported() {
return hasExported;
}

public void setHasExported(Boolean  hasExported) {
this.hasExported = hasExported;
}

public Timestamp getAffiliateReferredTimeStamp() {
return affiliateReferredTimeStamp;
}

public void setAffiliateReferredTimeStamp(Timestamp  affiliateReferredTimeStamp) {
this.affiliateReferredTimeStamp = affiliateReferredTimeStamp;
}


public Map<String, Object> mapAttributeField() {
return TrackingCodeOrderReturnMapper.map(this);
}
}
