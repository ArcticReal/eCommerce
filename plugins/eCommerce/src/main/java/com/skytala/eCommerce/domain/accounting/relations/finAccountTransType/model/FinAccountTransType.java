package com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.mapper.FinAccountTransTypeMapper;

public class FinAccountTransType implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountTransTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getFinAccountTransTypeId() {
return finAccountTransTypeId;
}

public void setFinAccountTransTypeId(String  finAccountTransTypeId) {
this.finAccountTransTypeId = finAccountTransTypeId;
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
return FinAccountTransTypeMapper.map(this);
}
}
