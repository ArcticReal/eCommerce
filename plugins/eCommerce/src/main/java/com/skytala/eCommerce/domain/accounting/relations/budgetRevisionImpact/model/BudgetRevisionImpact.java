package com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.mapper.BudgetRevisionImpactMapper;

public class BudgetRevisionImpact implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String budgetItemSeqId;
private String revisionSeqId;
private BigDecimal revisedAmount;
private Boolean addDeleteFlag;
private String revisionReason;

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

public String getRevisionSeqId() {
return revisionSeqId;
}

public void setRevisionSeqId(String  revisionSeqId) {
this.revisionSeqId = revisionSeqId;
}

public BigDecimal getRevisedAmount() {
return revisedAmount;
}

public void setRevisedAmount(BigDecimal  revisedAmount) {
this.revisedAmount = revisedAmount;
}

public Boolean getAddDeleteFlag() {
return addDeleteFlag;
}

public void setAddDeleteFlag(Boolean  addDeleteFlag) {
this.addDeleteFlag = addDeleteFlag;
}

public String getRevisionReason() {
return revisionReason;
}

public void setRevisionReason(String  revisionReason) {
this.revisionReason = revisionReason;
}


public Map<String, Object> mapAttributeField() {
return BudgetRevisionImpactMapper.map(this);
}
}
