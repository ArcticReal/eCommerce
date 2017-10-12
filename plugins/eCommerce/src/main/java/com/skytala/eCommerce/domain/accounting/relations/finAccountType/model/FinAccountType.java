package com.skytala.eCommerce.domain.accounting.relations.finAccountType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountType.mapper.FinAccountTypeMapper;

public class FinAccountType implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountTypeId;
private String parentTypeId;
private String replenishEnumId;
private Boolean isRefundable;
private Boolean hasTable;
private String description;

public String getFinAccountTypeId() {
return finAccountTypeId;
}

public void setFinAccountTypeId(String  finAccountTypeId) {
this.finAccountTypeId = finAccountTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public String getReplenishEnumId() {
return replenishEnumId;
}

public void setReplenishEnumId(String  replenishEnumId) {
this.replenishEnumId = replenishEnumId;
}

public Boolean getIsRefundable() {
return isRefundable;
}

public void setIsRefundable(Boolean  isRefundable) {
this.isRefundable = isRefundable;
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
return FinAccountTypeMapper.map(this);
}
}
