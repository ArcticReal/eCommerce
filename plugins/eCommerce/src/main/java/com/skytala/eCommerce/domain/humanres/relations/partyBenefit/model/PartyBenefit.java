package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.mapper.PartyBenefitMapper;

public class PartyBenefit implements Serializable{

private static final long serialVersionUID = 1L;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private String partyIdFrom;
private String partyIdTo;
private String benefitTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String periodTypeId;
private BigDecimal cost;
private BigDecimal actualEmployerPaidPercent;
private Long availableTime;

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

public String getBenefitTypeId() {
return benefitTypeId;
}

public void setBenefitTypeId(String  benefitTypeId) {
this.benefitTypeId = benefitTypeId;
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

public String getPeriodTypeId() {
return periodTypeId;
}

public void setPeriodTypeId(String  periodTypeId) {
this.periodTypeId = periodTypeId;
}

public BigDecimal getCost() {
return cost;
}

public void setCost(BigDecimal  cost) {
this.cost = cost;
}

public BigDecimal getActualEmployerPaidPercent() {
return actualEmployerPaidPercent;
}

public void setActualEmployerPaidPercent(BigDecimal  actualEmployerPaidPercent) {
this.actualEmployerPaidPercent = actualEmployerPaidPercent;
}

public Long getAvailableTime() {
return availableTime;
}

public void setAvailableTime(Long  availableTime) {
this.availableTime = availableTime;
}


public Map<String, Object> mapAttributeField() {
return PartyBenefitMapper.map(this);
}
}
