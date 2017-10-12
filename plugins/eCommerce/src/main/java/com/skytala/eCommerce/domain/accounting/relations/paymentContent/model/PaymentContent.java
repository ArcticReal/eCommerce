package com.skytala.eCommerce.domain.accounting.relations.paymentContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.mapper.PaymentContentMapper;

public class PaymentContent implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentId;
private String paymentContentTypeId;
private String contentId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public String getPaymentContentTypeId() {
return paymentContentTypeId;
}

public void setPaymentContentTypeId(String  paymentContentTypeId) {
this.paymentContentTypeId = paymentContentTypeId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
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


public Map<String, Object> mapAttributeField() {
return PaymentContentMapper.map(this);
}
}
