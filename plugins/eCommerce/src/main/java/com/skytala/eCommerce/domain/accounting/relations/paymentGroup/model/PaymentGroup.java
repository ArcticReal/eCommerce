package com.skytala.eCommerce.domain.accounting.relations.paymentGroup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGroup.mapper.PaymentGroupMapper;

public class PaymentGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGroupId;
private String paymentGroupTypeId;
private String paymentGroupName;

public String getPaymentGroupId() {
return paymentGroupId;
}

public void setPaymentGroupId(String  paymentGroupId) {
this.paymentGroupId = paymentGroupId;
}

public String getPaymentGroupTypeId() {
return paymentGroupTypeId;
}

public void setPaymentGroupTypeId(String  paymentGroupTypeId) {
this.paymentGroupTypeId = paymentGroupTypeId;
}

public String getPaymentGroupName() {
return paymentGroupName;
}

public void setPaymentGroupName(String  paymentGroupName) {
this.paymentGroupName = paymentGroupName;
}


public Map<String, Object> mapAttributeField() {
return PaymentGroupMapper.map(this);
}
}
