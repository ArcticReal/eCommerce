package com.skytala.eCommerce.domain.accounting.relations.budgetReview.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetReview.mapper.BudgetReviewMapper;

public class BudgetReview implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String budgetReviewId;
private String partyId;
private String budgetReviewResultTypeId;
private Timestamp reviewDate;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getBudgetReviewId() {
return budgetReviewId;
}

public void setBudgetReviewId(String  budgetReviewId) {
this.budgetReviewId = budgetReviewId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getBudgetReviewResultTypeId() {
return budgetReviewResultTypeId;
}

public void setBudgetReviewResultTypeId(String  budgetReviewResultTypeId) {
this.budgetReviewResultTypeId = budgetReviewResultTypeId;
}

public Timestamp getReviewDate() {
return reviewDate;
}

public void setReviewDate(Timestamp  reviewDate) {
this.reviewDate = reviewDate;
}


public Map<String, Object> mapAttributeField() {
return BudgetReviewMapper.map(this);
}
}
