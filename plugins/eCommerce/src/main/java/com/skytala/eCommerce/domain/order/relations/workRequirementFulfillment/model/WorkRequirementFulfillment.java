package com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.mapper.WorkRequirementFulfillmentMapper;

public class WorkRequirementFulfillment implements Serializable{

private static final long serialVersionUID = 1L;
private String requirementId;
private String workEffortId;
private String workReqFulfTypeId;

public String getRequirementId() {
return requirementId;
}

public void setRequirementId(String  requirementId) {
this.requirementId = requirementId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getWorkReqFulfTypeId() {
return workReqFulfTypeId;
}

public void setWorkReqFulfTypeId(String  workReqFulfTypeId) {
this.workReqFulfTypeId = workReqFulfTypeId;
}


public Map<String, Object> mapAttributeField() {
return WorkRequirementFulfillmentMapper.map(this);
}
}
