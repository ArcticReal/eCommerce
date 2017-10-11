package com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.mapper.OrderPaymentPreferenceMapper;

public class OrderPaymentPreference implements Serializable{

private static final long serialVersionUID = 1L;
private String orderPaymentPreferenceId;
private String orderId;
private String orderItemSeqId;
private String shipGroupSeqId;
private String productPricePurposeId;
private String paymentMethodTypeId;
private String paymentMethodId;
private String finAccountId;
private String securityCode;
private String track2;
private Boolean presentFlag;
private Boolean swipedFlag;
private Boolean overflowFlag;
private BigDecimal maxAmount;
private Long processAttempt;
private String billingPostalCode;
private String manualAuthCode;
private String manualRefNum;
private String statusId;
private Boolean needsNsfRetry;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getOrderPaymentPreferenceId() {
return orderPaymentPreferenceId;
}

public void setOrderPaymentPreferenceId(String  orderPaymentPreferenceId) {
this.orderPaymentPreferenceId = orderPaymentPreferenceId;
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

public String getShipGroupSeqId() {
return shipGroupSeqId;
}

public void setShipGroupSeqId(String  shipGroupSeqId) {
this.shipGroupSeqId = shipGroupSeqId;
}

public String getProductPricePurposeId() {
return productPricePurposeId;
}

public void setProductPricePurposeId(String  productPricePurposeId) {
this.productPricePurposeId = productPricePurposeId;
}

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getPaymentMethodId() {
return paymentMethodId;
}

public void setPaymentMethodId(String  paymentMethodId) {
this.paymentMethodId = paymentMethodId;
}

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public String getSecurityCode() {
return securityCode;
}

public void setSecurityCode(String  securityCode) {
this.securityCode = securityCode;
}

public String getTrack2() {
return track2;
}

public void setTrack2(String  track2) {
this.track2 = track2;
}

public Boolean getPresentFlag() {
return presentFlag;
}

public void setPresentFlag(Boolean  presentFlag) {
this.presentFlag = presentFlag;
}

public Boolean getSwipedFlag() {
return swipedFlag;
}

public void setSwipedFlag(Boolean  swipedFlag) {
this.swipedFlag = swipedFlag;
}

public Boolean getOverflowFlag() {
return overflowFlag;
}

public void setOverflowFlag(Boolean  overflowFlag) {
this.overflowFlag = overflowFlag;
}

public BigDecimal getMaxAmount() {
return maxAmount;
}

public void setMaxAmount(BigDecimal  maxAmount) {
this.maxAmount = maxAmount;
}

public Long getProcessAttempt() {
return processAttempt;
}

public void setProcessAttempt(Long  processAttempt) {
this.processAttempt = processAttempt;
}

public String getBillingPostalCode() {
return billingPostalCode;
}

public void setBillingPostalCode(String  billingPostalCode) {
this.billingPostalCode = billingPostalCode;
}

public String getManualAuthCode() {
return manualAuthCode;
}

public void setManualAuthCode(String  manualAuthCode) {
this.manualAuthCode = manualAuthCode;
}

public String getManualRefNum() {
return manualRefNum;
}

public void setManualRefNum(String  manualRefNum) {
this.manualRefNum = manualRefNum;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Boolean getNeedsNsfRetry() {
return needsNsfRetry;
}

public void setNeedsNsfRetry(Boolean  needsNsfRetry) {
this.needsNsfRetry = needsNsfRetry;
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
return OrderPaymentPreferenceMapper.map(this);
}
}
