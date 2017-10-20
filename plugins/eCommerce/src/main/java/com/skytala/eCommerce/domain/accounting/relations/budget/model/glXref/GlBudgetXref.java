package com.skytala.eCommerce.domain.accounting.relations.budget.model.glXref;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.glXref.GlBudgetXrefMapper;

public class GlBudgetXref implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountId;
private String budgetItemTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal allocationPercentage;

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getBudgetItemTypeId() {
return budgetItemTypeId;
}

public void setBudgetItemTypeId(String  budgetItemTypeId) {
this.budgetItemTypeId = budgetItemTypeId;
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

public BigDecimal getAllocationPercentage() {
return allocationPercentage;
}

public void setAllocationPercentage(BigDecimal  allocationPercentage) {
this.allocationPercentage = allocationPercentage;
}


public Map<String, Object> mapAttributeField() {
return GlBudgetXrefMapper.map(this);
}
}
