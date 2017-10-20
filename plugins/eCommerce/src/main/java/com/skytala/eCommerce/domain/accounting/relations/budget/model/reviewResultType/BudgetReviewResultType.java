package com.skytala.eCommerce.domain.accounting.relations.budget.model.reviewResultType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.reviewResultType.BudgetReviewResultTypeMapper;

public class BudgetReviewResultType implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetReviewResultTypeId;
private String description;
private String comments;

public String getBudgetReviewResultTypeId() {
return budgetReviewResultTypeId;
}

public void setBudgetReviewResultTypeId(String  budgetReviewResultTypeId) {
this.budgetReviewResultTypeId = budgetReviewResultTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return BudgetReviewResultTypeMapper.map(this);
}
}
