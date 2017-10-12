package com.skytala.eCommerce.domain.accounting.relations.budget.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.BudgetMapper;

public class Budget implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String budgetTypeId;
private String customTimePeriodId;
private String comments;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getBudgetTypeId() {
return budgetTypeId;
}

public void setBudgetTypeId(String  budgetTypeId) {
this.budgetTypeId = budgetTypeId;
}

public String getCustomTimePeriodId() {
return customTimePeriodId;
}

public void setCustomTimePeriodId(String  customTimePeriodId) {
this.customTimePeriodId = customTimePeriodId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return BudgetMapper.map(this);
}
}
