package com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.mapper.RequirementBudgetAllocationMapper;

public class RequirementBudgetAllocation implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String budgetItemSeqId;
private String requirementId;
private BigDecimal amount;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getBudgetItemSeqId() {
return budgetItemSeqId;
}

public void setBudgetItemSeqId(String  budgetItemSeqId) {
this.budgetItemSeqId = budgetItemSeqId;
}

public String getRequirementId() {
return requirementId;
}

public void setRequirementId(String  requirementId) {
this.requirementId = requirementId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}


public Map<String, Object> mapAttributeField() {
return RequirementBudgetAllocationMapper.map(this);
}
}
