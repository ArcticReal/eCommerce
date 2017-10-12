package com.skytala.eCommerce.domain.humanres.relations.payHistory.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.mapper.PayHistoryMapper;

public class PayHistory implements Serializable{

private static final long serialVersionUID = 1L;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private String partyIdFrom;
private String partyIdTo;
private Timestamp fromDate;
private Timestamp thruDate;
private String salaryStepSeqId;
private String payGradeId;
private String periodTypeId;
private BigDecimal amount;
private String comments;

public String getRoleTypeIdFrom() {
return roleTypeIdFrom;
}

public void setRoleTypeIdFrom(String  roleTypeIdFrom) {
this.roleTypeIdFrom = roleTypeIdFrom;
}

public String getRoleTypeIdTo() {
return roleTypeIdTo;
}

public void setRoleTypeIdTo(String  roleTypeIdTo) {
this.roleTypeIdTo = roleTypeIdTo;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
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

public String getSalaryStepSeqId() {
return salaryStepSeqId;
}

public void setSalaryStepSeqId(String  salaryStepSeqId) {
this.salaryStepSeqId = salaryStepSeqId;
}

public String getPayGradeId() {
return payGradeId;
}

public void setPayGradeId(String  payGradeId) {
this.payGradeId = payGradeId;
}

public String getPeriodTypeId() {
return periodTypeId;
}

public void setPeriodTypeId(String  periodTypeId) {
this.periodTypeId = periodTypeId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return PayHistoryMapper.map(this);
}
}
