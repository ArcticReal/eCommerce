package com.skytala.eCommerce.domain.order.relations.requirement.model.status;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.status.RequirementStatusMapper;

public class RequirementStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String requirementId;
private String statusId;
private Timestamp statusDate;
private String changeByUserLoginId;

public String getRequirementId() {
return requirementId;
}

public void setRequirementId(String  requirementId) {
this.requirementId = requirementId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getStatusDate() {
return statusDate;
}

public void setStatusDate(Timestamp  statusDate) {
this.statusDate = statusDate;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return RequirementStatusMapper.map(this);
}
}
