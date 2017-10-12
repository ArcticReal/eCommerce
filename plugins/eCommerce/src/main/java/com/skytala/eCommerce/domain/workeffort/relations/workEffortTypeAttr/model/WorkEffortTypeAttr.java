package com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.mapper.WorkEffortTypeAttrMapper;

public class WorkEffortTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortTypeId;
private String attrName;
private String description;

public String getWorkEffortTypeId() {
return workEffortTypeId;
}

public void setWorkEffortTypeId(String  workEffortTypeId) {
this.workEffortTypeId = workEffortTypeId;
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
return WorkEffortTypeAttrMapper.map(this);
}
}
