package com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeRate;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.typeRate.EmplPositionTypeRateMapper;

public class EmplPositionTypeRate implements Serializable{

private static final long serialVersionUID = 1L;
private String emplPositionTypeId;
private String rateTypeId;
private String payGradeId;
private String salaryStepSeqId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getEmplPositionTypeId() {
return emplPositionTypeId;
}

public void setEmplPositionTypeId(String  emplPositionTypeId) {
this.emplPositionTypeId = emplPositionTypeId;
}

public String getRateTypeId() {
return rateTypeId;
}

public void setRateTypeId(String  rateTypeId) {
this.rateTypeId = rateTypeId;
}

public String getPayGradeId() {
return payGradeId;
}

public void setPayGradeId(String  payGradeId) {
this.payGradeId = payGradeId;
}

public String getSalaryStepSeqId() {
return salaryStepSeqId;
}

public void setSalaryStepSeqId(String  salaryStepSeqId) {
this.salaryStepSeqId = salaryStepSeqId;
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
return EmplPositionTypeRateMapper.map(this);
}
}
