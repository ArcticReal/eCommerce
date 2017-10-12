package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.mapper.ApplicationSandboxMapper;

public class ApplicationSandbox implements Serializable{

private static final long serialVersionUID = 1L;
private String applicationId;
private String workEffortId;
private String partyId;
private String roleTypeId;
private Timestamp fromDate;
private String runtimeDataId;

public String getApplicationId() {
return applicationId;
}

public void setApplicationId(String  applicationId) {
this.applicationId = applicationId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public String getRuntimeDataId() {
return runtimeDataId;
}

public void setRuntimeDataId(String  runtimeDataId) {
this.runtimeDataId = runtimeDataId;
}


public Map<String, Object> mapAttributeField() {
return ApplicationSandboxMapper.map(this);
}
}
