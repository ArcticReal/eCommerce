package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.mapper.TrackingCodeOrderMapper;

public class TrackingCodeOrder implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String trackingCodeTypeId;
private String trackingCodeId;
private Boolean isBillable;
private String siteId;
private Boolean hasExported;
private Timestamp affiliateReferredTimeStamp;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
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
return TrackingCodeOrderMapper.map(this);
}
}
