package com.skytala.eCommerce.domain.returnItemResponse.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.returnItemResponse.mapper.ReturnItemResponseMapper;

public class ReturnItemResponse implements Serializable{

private static final long serialVersionUID = 1L;
private String returnItemResponseId;
private String orderPaymentPreferenceId;
private String replacementOrderId;
private String paymentId;
private String billingAccountId;
private String finAccountTransId;
private BigDecimal responseAmount;
private Timestamp responseDate;

public String getReturnItemResponseId() {
return returnItemResponseId;
}

public void setReturnItemResponseId(String  returnItemResponseId) {
this.returnItemResponseId = returnItemResponseId;
}

public String getOrderPaymentPreferenceId() {
return orderPaymentPreferenceId;
}

public void setOrderPaymentPreferenceId(String  orderPaymentPreferenceId) {
this.orderPaymentPreferenceId = orderPaymentPreferenceId;
}

public String getReplacementOrderId() {
return replacementOrderId;
}

public void setReplacementOrderId(String  replacementOrderId) {
this.replacementOrderId = replacementOrderId;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public String getBillingAccountId() {
return billingAccountId;
}

public void setBillingAccountId(String  billingAccountId) {
this.billingAccountId = billingAccountId;
}

public String getFinAccountTransId() {
return finAccountTransId;
}

public void setFinAccountTransId(String  finAccountTransId) {
this.finAccountTransId = finAccountTransId;
}

public BigDecimal getResponseAmount() {
return responseAmount;
}

public void setResponseAmount(BigDecimal  responseAmount) {
this.responseAmount = responseAmount;
}

public Timestamp getResponseDate() {
return responseDate;
}

public void setResponseDate(Timestamp  responseDate) {
this.responseDate = responseDate;
}


public Map<String, Object> mapAttributeField() {
return ReturnItemResponseMapper.map(this);
}
}
