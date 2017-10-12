package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.mapper.BudgetItemTypeAttrMapper;

public class BudgetItemTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetItemTypeId;
private String attrName;
private String description;

public String getBudgetItemTypeId() {
return budgetItemTypeId;
}

public void setBudgetItemTypeId(String  budgetItemTypeId) {
this.budgetItemTypeId = budgetItemTypeId;
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
return BudgetItemTypeAttrMapper.map(this);
}
}
