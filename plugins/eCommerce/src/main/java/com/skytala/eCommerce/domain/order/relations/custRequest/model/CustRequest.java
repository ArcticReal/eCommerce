package com.skytala.eCommerce.domain.order.relations.custRequest.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.CustRequestMapper;

public class CustRequest implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String custRequestTypeId;
private String custRequestCategoryId;
private String statusId;
private String fromPartyId;
private Long priority;
private Timestamp custRequestDate;
private Timestamp responseRequiredDate;
private String custRequestName;
private String description;
private String maximumAmountUomId;
private String productStoreId;
private String salesChannelEnumId;
private String fulfillContactMechId;
private String currencyUomId;
private Timestamp openDateTime;
private Timestamp closedDateTime;
private String internalComment;
private String reason;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getCustRequestTypeId() {
return custRequestTypeId;
}

public void setCustRequestTypeId(String  custRequestTypeId) {
this.custRequestTypeId = custRequestTypeId;
}

public String getCustRequestCategoryId() {
return custRequestCategoryId;
}

public void setCustRequestCategoryId(String  custRequestCategoryId) {
this.custRequestCategoryId = custRequestCategoryId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getFromPartyId() {
return fromPartyId;
}

public void setFromPartyId(String  fromPartyId) {
this.fromPartyId = fromPartyId;
}

public Long getPriority() {
return priority;
}

public void setPriority(Long  priority) {
this.priority = priority;
}

public Timestamp getCustRequestDate() {
return custRequestDate;
}

public void setCustRequestDate(Timestamp  custRequestDate) {
this.custRequestDate = custRequestDate;
}

public Timestamp getResponseRequiredDate() {
return responseRequiredDate;
}

public void setResponseRequiredDate(Timestamp  responseRequiredDate) {
this.responseRequiredDate = responseRequiredDate;
}

public String getCustRequestName() {
return custRequestName;
}

public void setCustRequestName(String  custRequestName) {
this.custRequestName = custRequestName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getMaximumAmountUomId() {
return maximumAmountUomId;
}

public void setMaximumAmountUomId(String  maximumAmountUomId) {
this.maximumAmountUomId = maximumAmountUomId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getSalesChannelEnumId() {
return salesChannelEnumId;
}

public void setSalesChannelEnumId(String  salesChannelEnumId) {
this.salesChannelEnumId = salesChannelEnumId;
}

public String getFulfillContactMechId() {
return fulfillContactMechId;
}

public void setFulfillContactMechId(String  fulfillContactMechId) {
this.fulfillContactMechId = fulfillContactMechId;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public Timestamp getOpenDateTime() {
return openDateTime;
}

public void setOpenDateTime(Timestamp  openDateTime) {
this.openDateTime = openDateTime;
}

public Timestamp getClosedDateTime() {
return closedDateTime;
}

public void setClosedDateTime(Timestamp  closedDateTime) {
this.closedDateTime = closedDateTime;
}

public String getInternalComment() {
return internalComment;
}

public void setInternalComment(String  internalComment) {
this.internalComment = internalComment;
}

public String getReason() {
return reason;
}

public void setReason(String  reason) {
this.reason = reason;
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
return CustRequestMapper.map(this);
}
}
