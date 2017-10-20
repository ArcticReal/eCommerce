package com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioApplication;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.scenarioApplication.BudgetScenarioApplicationMapper;

public class BudgetScenarioApplication implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetScenarioApplicId;
private String budgetScenarioId;
private String budgetId;
private String budgetItemSeqId;
private BigDecimal amountChange;
private BigDecimal percentageChange;

public String getBudgetScenarioApplicId() {
return budgetScenarioApplicId;
}

public void setBudgetScenarioApplicId(String  budgetScenarioApplicId) {
this.budgetScenarioApplicId = budgetScenarioApplicId;
}

public String getBudgetScenarioId() {
return budgetScenarioId;
}

public void setBudgetScenarioId(String  budgetScenarioId) {
this.budgetScenarioId = budgetScenarioId;
}

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

public BigDecimal getAmountChange() {
return amountChange;
}

public void setAmountChange(BigDecimal  amountChange) {
this.amountChange = amountChange;
}

public BigDecimal getPercentageChange() {
return percentageChange;
}

public void setPercentageChange(BigDecimal  percentageChange) {
this.percentageChange = percentageChange;
}


public Map<String, Object> mapAttributeField() {
return BudgetScenarioApplicationMapper.map(this);
}
}
