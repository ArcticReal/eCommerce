package com.skytala.eCommerce.domain.trackingCode.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.trackingCode.mapper.TrackingCodeMapper;

public class TrackingCode implements Serializable{

private static final long serialVersionUID = 1L;
private String trackingCodeId;
private String trackingCodeTypeId;
private String marketingCampaignId;
private String redirectUrl;
private String overrideLogo;
private String overrideCss;
private String prodCatalogId;
private String comments;
private String description;
private Long trackableLifetime;
private Long billableLifetime;
private Timestamp fromDate;
private Timestamp thruDate;
private String groupId;
private String subgroupId;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getTrackingCodeId() {
return trackingCodeId;
}

public void setTrackingCodeId(String  trackingCodeId) {
this.trackingCodeId = trackingCodeId;
}

public String getTrackingCodeTypeId() {
return trackingCodeTypeId;
}

public void setTrackingCodeTypeId(String  trackingCodeTypeId) {
this.trackingCodeTypeId = trackingCodeTypeId;
}

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getRedirectUrl() {
return redirectUrl;
}

public void setRedirectUrl(String  redirectUrl) {
this.redirectUrl = redirectUrl;
}

public String getOverrideLogo() {
return overrideLogo;
}

public void setOverrideLogo(String  overrideLogo) {
this.overrideLogo = overrideLogo;
}

public String getOverrideCss() {
return overrideCss;
}

public void setOverrideCss(String  overrideCss) {
this.overrideCss = overrideCss;
}

public String getProdCatalogId() {
return prodCatalogId;
}

public void setProdCatalogId(String  prodCatalogId) {
this.prodCatalogId = prodCatalogId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Long getTrackableLifetime() {
return trackableLifetime;
}

public void setTrackableLifetime(Long  trackableLifetime) {
this.trackableLifetime = trackableLifetime;
}

public Long getBillableLifetime() {
return billableLifetime;
}

public void setBillableLifetime(Long  billableLifetime) {
this.billableLifetime = billableLifetime;
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

public String getGroupId() {
return groupId;
}

public void setGroupId(String  groupId) {
this.groupId = groupId;
}

public String getSubgroupId() {
return subgroupId;
}

public void setSubgroupId(String  subgroupId) {
this.subgroupId = subgroupId;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp  createdDate) {
this.createdDate = createdDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public Timestamp getLastModifiedDate() {
return lastModifiedDate;
}

public void setLastModifiedDate(Timestamp  lastModifiedDate) {
this.lastModifiedDate = lastModifiedDate;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return TrackingCodeMapper.map(this);
}
}
