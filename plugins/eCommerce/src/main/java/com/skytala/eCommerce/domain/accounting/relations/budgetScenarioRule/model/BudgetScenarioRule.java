package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.mapper.BudgetScenarioRuleMapper;

public class BudgetScenarioRule implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetScenarioId;
private String budgetItemTypeId;
private BigDecimal amountChange;
private BigDecimal percentageChange;

public String getBudgetScenarioId() {
return budgetScenarioId;
}

public void setBudgetScenarioId(String  budgetScenarioId) {
this.budgetScenarioId = budgetScenarioId;
}

public String getBudgetItemTypeId() {
return budgetItemTypeId;
}

public void setBudgetItemTypeId(String  budgetItemTypeId) {
this.budgetItemTypeId = budgetItemTypeId;
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
return BudgetScenarioRuleMapper.map(this);
}
}
