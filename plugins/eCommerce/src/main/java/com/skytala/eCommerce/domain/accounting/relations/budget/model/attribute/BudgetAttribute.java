package com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.attribute.BudgetAttributeMapper;

public class BudgetAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String attrName;
private String attrValue;
private String attrDescription;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getAttrValue() {
return attrValue;
}

public void setAttrValue(String  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return BudgetAttributeMapper.map(this);
}
}
