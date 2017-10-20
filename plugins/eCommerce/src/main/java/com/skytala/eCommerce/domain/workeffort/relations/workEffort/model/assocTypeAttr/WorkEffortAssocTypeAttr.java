package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocTypeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocTypeAttr.WorkEffortAssocTypeAttrMapper;

public class WorkEffortAssocTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortAssocTypeId;
private String attrName;
private String description;

public String getWorkEffortAssocTypeId() {
return workEffortAssocTypeId;
}

public void setWorkEffortAssocTypeId(String  workEffortAssocTypeId) {
this.workEffortAssocTypeId = workEffortAssocTypeId;
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
return WorkEffortAssocTypeAttrMapper.map(this);
}
}
