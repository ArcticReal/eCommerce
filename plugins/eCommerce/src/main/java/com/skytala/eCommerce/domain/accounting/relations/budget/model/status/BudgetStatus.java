package com.skytala.eCommerce.domain.accounting.relations.budget.model.status;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.status.BudgetStatusMapper;

public class BudgetStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String statusId;
private Timestamp statusDate;
private String comments;
private String changeByUserLoginId;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getStatusDate() {
return statusDate;
}

public void setStatusDate(Timestamp  statusDate) {
this.statusDate = statusDate;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return BudgetStatusMapper.map(this);
}
}
