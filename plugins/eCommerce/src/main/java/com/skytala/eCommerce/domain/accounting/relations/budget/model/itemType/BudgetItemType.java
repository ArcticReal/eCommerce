package com.skytala.eCommerce.domain.accounting.relations.budget.model.itemType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.itemType.BudgetItemTypeMapper;

public class BudgetItemType implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetItemTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getBudgetItemTypeId() {
return budgetItemTypeId;
}

public void setBudgetItemTypeId(String  budgetItemTypeId) {
this.budgetItemTypeId = budgetItemTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return BudgetItemTypeMapper.map(this);
}
}
