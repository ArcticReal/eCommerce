package com.skytala.eCommerce.domain.humanres.relations.salaryStep.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.mapper.SalaryStepMapper;

public class SalaryStep implements Serializable{

private static final long serialVersionUID = 1L;
private String salaryStepSeqId;
private String payGradeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp dateModified;
private BigDecimal amount;
private String createdByUserLogin;
private String lastModifiedByUserLogin;

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

public Timestamp getDateModified() {
return dateModified;
}

public void setDateModified(Timestamp  dateModified) {
this.dateModified = dateModified;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return SalaryStepMapper.map(this);
}
}
