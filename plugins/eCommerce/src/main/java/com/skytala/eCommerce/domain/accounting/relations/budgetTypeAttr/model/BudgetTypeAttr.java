package com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.mapper.BudgetTypeAttrMapper;

public class BudgetTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetTypeId;
private String attrName;
private String description;

public String getBudgetTypeId() {
return budgetTypeId;
}

public void setBudgetTypeId(String  budgetTypeId) {
this.budgetTypeId = budgetTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return BudgetTypeAttrMapper.map(this);
}
}
