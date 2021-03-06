package com.skytala.eCommerce.domain.accounting.relations.budget.model.scenario;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.scenario.BudgetScenarioMapper;

public class BudgetScenario implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetScenarioId;
private String description;

public String getBudgetScenarioId() {
return budgetScenarioId;
}

public void setBudgetScenarioId(String  budgetScenarioId) {
this.budgetScenarioId = budgetScenarioId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return BudgetScenarioMapper.map(this);
}
}
