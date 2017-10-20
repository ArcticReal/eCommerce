package com.skytala.eCommerce.domain.order.relations.requirement.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.type.RequirementTypeMapper;

public class RequirementType implements Serializable{

private static final long serialVersionUID = 1L;
private String requirementTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getRequirementTypeId() {
return requirementTypeId;
}

public void setRequirementTypeId(String  requirementTypeId) {
this.requirementTypeId = requirementTypeId;
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
return RequirementTypeMapper.map(this);
}
}