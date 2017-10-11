package com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.mapper.RequirementTypeAttrMapper;

public class RequirementTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String requirementTypeId;
private String attrName;
private String description;

public String getRequirementTypeId() {
return requirementTypeId;
}

public void setRequirementTypeId(String  requirementTypeId) {
this.requirementTypeId = requirementTypeId;
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
return RequirementTypeAttrMapper.map(this);
}
}
