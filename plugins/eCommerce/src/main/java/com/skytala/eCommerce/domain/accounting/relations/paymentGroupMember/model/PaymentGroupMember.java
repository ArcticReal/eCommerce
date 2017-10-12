package com.skytala.eCommerce.domain.accounting.relations.paymentGroupMember.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGroupMember.mapper.PaymentGroupMemberMapper;

public class PaymentGroupMember implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGroupId;
private String paymentId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getPaymentGroupId() {
return paymentGroupId;
}

public void setPaymentGroupId(String  paymentGroupId) {
this.paymentGroupId = paymentGroupId;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
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

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return PaymentGroupMemberMapper.map(this);
}
}
