package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocAttribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocAttribute.WorkEffortAssocAttributeMapper;

public class WorkEffortAssocAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortIdFrom;
private String workEffortIdTo;
private String workEffortAssocTypeId;
private Timestamp fromDate;
private String attrName;
private String attrValue;
private String attrDescription;

public String getWorkEffortIdFrom() {
return workEffortIdFrom;
}

public void setWorkEffortIdFrom(String  workEffortIdFrom) {
this.workEffortIdFrom = workEffortIdFrom;
}

public String getWorkEffortIdTo() {
return workEffortIdTo;
}

public void setWorkEffortIdTo(String  workEffortIdTo) {
this.workEffortIdTo = workEffortIdTo;
}

public String getWorkEffortAssocTypeId() {
return workEffortAssocTypeId;
}

public void setWorkEffortAssocTypeId(String  workEffortAssocTypeId) {
this.workEffortAssocTypeId = workEffortAssocTypeId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
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
return WorkEffortAssocAttributeMapper.map(this);
}
}
