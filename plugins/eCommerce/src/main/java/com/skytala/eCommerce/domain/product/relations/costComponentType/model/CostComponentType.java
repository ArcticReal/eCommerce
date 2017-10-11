package com.skytala.eCommerce.domain.product.relations.costComponentType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.costComponentType.mapper.CostComponentTypeMapper;

public class CostComponentType implements Serializable{

private static final long serialVersionUID = 1L;
private String costComponentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getCostComponentTypeId() {
return costComponentTypeId;
}

public void setCostComponentTypeId(String  costComponentTypeId) {
this.costComponentTypeId = costComponentTypeId;
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
return CostComponentTypeMapper.map(this);
}
}
