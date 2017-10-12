package com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.mapper.GiftCardFulfillmentMapper;

public class GiftCardFulfillment implements Serializable{

private static final long serialVersionUID = 1L;
private String fulfillmentId;
private String typeEnumId;
private String merchantId;
private String partyId;
private String orderId;
private String orderItemSeqId;
private String surveyResponseId;
private String cardNumber;
private String pinNumber;
private BigDecimal amount;
private String responseCode;
private String referenceNum;
private String authCode;
private Timestamp fulfillmentDate;

public String getFulfillmentId() {
return fulfillmentId;
}

public void setFulfillmentId(String  fulfillmentId) {
this.fulfillmentId = fulfillmentId;
}

public String getTypeEnumId() {
return typeEnumId;
}

public void setTypeEnumId(String  typeEnumId) {
this.typeEnumId = typeEnumId;
}

public String getMerchantId() {
return merchantId;
}

public void setMerchantId(String  merchantId) {
this.merchantId = merchantId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
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

public String getSurveyResponseId() {
return surveyResponseId;
}

public void setSurveyResponseId(String  surveyResponseId) {
this.surveyResponseId = surveyResponseId;
}

public String getCardNumber() {
return cardNumber;
}

public void setCardNumber(String  cardNumber) {
this.cardNumber = cardNumber;
}

public String getPinNumber() {
return pinNumber;
}

public void setPinNumber(String  pinNumber) {
this.pinNumber = pinNumber;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getResponseCode() {
return responseCode;
}

public void setResponseCode(String  responseCode) {
this.responseCode = responseCode;
}

public String getReferenceNum() {
return referenceNum;
}

public void setReferenceNum(String  referenceNum) {
this.referenceNum = referenceNum;
}

public String getAuthCode() {
return authCode;
}

public void setAuthCode(String  authCode) {
this.authCode = authCode;
}

public Timestamp getFulfillmentDate() {
return fulfillmentDate;
}

public void setFulfillmentDate(Timestamp  fulfillmentDate) {
this.fulfillmentDate = fulfillmentDate;
}


public Map<String, Object> mapAttributeField() {
return GiftCardFulfillmentMapper.map(this);
}
}
