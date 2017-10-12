package com.skytala.eCommerce.domain.accounting.relations.budgetRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetRole.mapper.BudgetRoleMapper;

public class BudgetRole implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String partyId;
private String roleTypeId;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}


public Map<String, Object> mapAttributeField() {
return BudgetRoleMapper.map(this);
}
}
