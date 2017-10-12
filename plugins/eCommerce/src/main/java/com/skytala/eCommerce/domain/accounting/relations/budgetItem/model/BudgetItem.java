package com.skytala.eCommerce.domain.accounting.relations.budgetItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetItem.mapper.BudgetItemMapper;

public class BudgetItem implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String budgetItemSeqId;
private String budgetItemTypeId;
private BigDecimal amount;
private String purpose;
private String justification;

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

public String getBudgetItemTypeId() {
return budgetItemTypeId;
}

public void setBudgetItemTypeId(String  budgetItemTypeId) {
this.budgetItemTypeId = budgetItemTypeId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getPurpose() {
return purpose;
}

public void setPurpose(String  purpose) {
this.purpose = purpose;
}

public String getJustification() {
return justification;
}

public void setJustification(String  justification) {
this.justification = justification;
}


public Map<String, Object> mapAttributeField() {
return BudgetItemMapper.map(this);
}
}
